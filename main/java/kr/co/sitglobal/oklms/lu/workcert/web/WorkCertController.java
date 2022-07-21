package kr.co.sitglobal.oklms.lu.workcert.web;

import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Validator;

import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.atchFile.vo.AtchFileVO;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.commbiz.cmmcode.vo.CommonCodeVO;
import kr.co.sitglobal.oklms.commbiz.util.AtchFileUtil;
import kr.co.sitglobal.oklms.la.company.service.CompanyService;
import kr.co.sitglobal.oklms.lu.workcert.service.WorkCertService;
import kr.co.sitglobal.oklms.lu.workcert.vo.WorkCertVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
/**
 * 재직증빙서류
 * @author user
 *
 */
@Controller
public class WorkCertController {
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkCertController.class);
	@Resource(name = "companyService")
	private CompanyService companyService;

	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;

	@Resource(name = "beanValidatorJSR303")
	Validator validator;

	@Resource(name = "workCertService")
	private WorkCertService workCertService;


	@Resource(name = "atchFileService")
	private  AtchFileService atchFileService;

	@Resource(name = "atchFileUtil")
	private AtchFileUtil atchFileUtil; 

	/**
	 * 제직증빙서류 리스트 기간 및 현황
	 * @param workCertVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/lu/workcert/listWorkCert.do")
	public String listWorkCert(@ModelAttribute("frmWorkCert") WorkCertVO workCertVO, ModelMap model,HttpSession session)throws Exception{

		LOGGER.debug("#### URL = /lu/workcert/listWorkCert.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(workCertVO); // session의 정보를 VO에 추가.
		
		if(workCertVO.getYyyy()==null || workCertVO.getYyyy().equals("")){
			// 현재 년도학기조회
			CommonCodeVO commonCodeVO =	commonCodeService.selectYearTerm();
			workCertVO.setYyyy(commonCodeVO.getYyyy());			
			workCertVO.setTerm(commonCodeVO.getTerm());
			workCertVO.setSearch("top");
		}
		
		WorkCertVO workCert = null;
		// 학과코드
		workCertVO.setDeptNo(workCertVO.getSessionDeptNo());
		
		workCert = workCertService.getWorkCertPeriod(workCertVO);	
 
		if(workCert !=null && workCert.getPeriodId()!=null){
			 
			workCertVO.setPeriodId(workCert.getPeriodId());
			workCertVO.setYyyy(workCert.getYyyy());
			workCertVO.setTerm(workCert.getTerm());
			workCertVO.setStartTime(workCert.getStartTime());
			workCertVO.setEndTime(workCert.getEndTime());			
			workCertVO.setRelativeRegulation(workCert.getRelativeRegulation());
			workCertVO.setDeptNo(workCert.getDeptNo());
			workCertVO.setInsuranceYn(workCert.getInsuranceYn());
			workCertVO.setReceiptYn(workCert.getReceiptYn());
		}else{
			// 상단 초기화
			workCertVO.setPeriodId("");
			workCertVO.setStartTime("");
			workCertVO.setEndTime("");			
			workCertVO.setRelativeRegulation("");
			workCertVO.setInsuranceYn("");
			workCertVO.setReceiptYn("");
		}
		
		model.addAttribute("workCertVO", workCertVO);
		
		//하단목록		
		List <WorkCertVO> workCertList = workCertService.listWorkCertPeriod(workCertVO);
		model.addAttribute("workCertList", workCertList);
		
		//다음 탭목록조회
 
		List <WorkCertVO> workCertDetailList = workCertService.listWorkCertDetail(workCertVO);
		model.addAttribute("workCertDetailList", workCertDetailList);
 
		return "oklms/lu/workcert/listWorkCert";
	}


	/**
	 * 제직증빙서류 등록 및 수정
	 * @param workCertVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/lu/workcert/insertWorkCertPeriod.do")
	public String insertWorkCertPeriod(@ModelAttribute("frmWorkCert") WorkCertVO workCertVO, ModelMap model,RedirectAttributes redirectAttributes)throws Exception{

		LOGGER.debug("#### URL = /lu/workcert/listWorkCert.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(workCertVO); // session의 정보를 VO에 추가.
		// 부서코드 입력
		workCertVO.setDeptNo(workCertVO.getSessionDeptNo());
		
		String retMsg = "입력값을 확인해주세요";
		int insertCnt = 0;
		insertCnt = workCertService.goInsertWorkCertPeriod(workCertVO);

		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);

		return "redirect:/lu/workcert/listWorkCert.do?periodId="+workCertVO.getPeriodId();
	}
	
	/**
	 * 제직증빙서류 삭제
	 * @param workCertVO
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/lu/workcert/deleteWorkCertPeriod.do")
	public String deleteWorkCertPeriod(@ModelAttribute("frmWorkCert") WorkCertVO workCertVO, ModelMap model,RedirectAttributes redirectAttributes)throws Exception{
		
		LOGGER.debug("#### URL = /lu/workcert/deleteWorkCertPeriod.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(workCertVO); // session의 정보를 VO에 추가.

		String retMsg = "입력값을 확인해주세요";
		int delectCnt = workCertService.deleteWorkCertPeriod(workCertVO);
		if(delectCnt > 0){
			retMsg = "삭제 처리되었습니다.!";
		}else{
			retMsg = "처리된건이 없습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		
		return "redirect:/lu/workcert/listWorkCert.do";
	}

	/**
	 * 제직증빙서류 학기별 현황
	 * @param workCertVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/lu/workcert/popupWorkCert.do")
	public String popupWorkCert(@ModelAttribute("frmWorkCert") WorkCertVO workCertVO, ModelMap model,HttpSession session)throws Exception{

		LOGGER.debug("#### URL = /lu/workcert/popupWorkCert.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(workCertVO); // session의 정보를 VO에 추가.
		 
		// 학과코드
		workCertVO.setDeptNo(workCertVO.getSessionDeptNo());
		
		List<WorkCertVO> resultList =workCertService.listWorkCertStatePopup(workCertVO);
		
		
		model.addAttribute("workCertVO", workCertVO);
		model.addAttribute("resultList", resultList);
		
		return "oklms_popup/lu/workcert/popupWorkCertlist";
	}
	/**
	 * 제직증빙서류 학기별 현황
	 * @param workCertVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/lu/workcert/excelWorkCert.do")
	public String excelWorkCert(@ModelAttribute("frmWorkCert") WorkCertVO workCertVO, ModelMap model, HttpServletRequest request)throws Exception{

		LOGGER.debug("#### URL = /lu/workcert/excelWorkCert.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(workCertVO); // session의 정보를 VO에 추가.
		 
		// 학과코드
		workCertVO.setDeptNo(workCertVO.getSessionDeptNo());
		
		List<WorkCertVO> resultList =workCertService.listWorkCertStatePopup(workCertVO);
		
		
		model.addAttribute("workCertVO", workCertVO);
		model.addAttribute("resultList", resultList);
		request.setAttribute("ExcelName", URLEncoder.encode( "학기별재직증빙서류".replaceAll(" ", "_") ,"UTF-8") );
		
		return "oklms_excel/lu/workcert/excelWorkCertlist";
	}
	
	/**
	 * 제직증빙서류  현황 팝업
	 * @param workCertVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/lu/workcert/popupWorkCertDetail.do")
	public String popupWorkCertDetail(@ModelAttribute("frmWorkCert") WorkCertVO workCertVO, ModelMap model,HttpSession session)throws Exception{

		LOGGER.debug("#### URL = /lu/workcert/popupWorkCertDetail.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(workCertVO); // session의 정보를 VO에 추가.
		 
		// 학과코드
		workCertVO.setDeptNo(workCertVO.getSessionDeptNo());
		//다음 탭목록조회
		 
		List <WorkCertVO> resultList = workCertService.listWorkCertDetail(workCertVO);
		
		model.addAttribute("workCertVO", workCertVO);
		model.addAttribute("resultList", resultList);
		
		return "oklms_popup/lu/workcert/popupWorkCertDetail";
	}
	/**
	 * 제직증빙서류 학기별 현황
	 * @param workCertVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/lu/workcert/excelWorkCertDetail.do")
	public String excelWorkCertDetail(@ModelAttribute("frmWorkCert") WorkCertVO workCertVO, ModelMap model, HttpServletRequest request)throws Exception{

		LOGGER.debug("#### URL = /lu/workcert/excelWorkCertDetail.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(workCertVO); // session의 정보를 VO에 추가.
		 
		// 학과코드
		workCertVO.setDeptNo(workCertVO.getSessionDeptNo());
		
		List <WorkCertVO> resultList = workCertService.listWorkCertDetail(workCertVO);
		
		
		model.addAttribute("workCertVO", workCertVO);
		model.addAttribute("resultList", resultList);
		request.setAttribute("ExcelName", URLEncoder.encode( "재직증빙서류현황".replaceAll(" ", "_") ,"UTF-8") );
		
		return "oklms_excel/lu/workcert/excelWorkCertlistDetail";
	}
	 
	/**
	 *  승인 반려 처리
	 * @param workCertVO
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/lu/workcert/updateWorkCertMember.do")
	public String updateWorkCertMember(@ModelAttribute("frmWorkCert") WorkCertVO workCertVO, ModelMap model,RedirectAttributes redirectAttributes)throws Exception{
		
		LOGGER.debug("#### URL = /lu/workcert/updateWorkCertMember.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(workCertVO); // session의 정보를 VO에 추가.

		String retMsg = "입력값을 확인해주세요";

		int delectCnt = workCertService.updateWorkCertMember(workCertVO);
		
		if(delectCnt > 0){
			retMsg = "정상처리 되었습니다.!";
		}else{
			retMsg = "처리된건이 없습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);

		return "redirect:/lu/workcert/listWorkCert.do?seach=detail&yyyy="+workCertVO.getYyyy()+"&term="+workCertVO.getTerm();
	}
	/**
	 *  오프라인 제출 처리
	 * @param workCertVO
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/lu/workcert/updateOffWorkCertMember.do")
	public String updateOffWorkCertMember(@ModelAttribute("frmWorkCert") WorkCertVO workCertVO, ModelMap model,RedirectAttributes redirectAttributes)throws Exception{
		
		LOGGER.debug("#### URL = /lu/workcert/updateOffWorkCertMember.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(workCertVO); // session의 정보를 VO에 추가.

		String retMsg = "입력값을 확인해주세요";

		int delectCnt = workCertService.updateOffWorkCertMember(workCertVO);
		
		if(delectCnt > 0){
			retMsg = "정상처리 되었습니다.!";
		}else{
			retMsg = "처리된건이 없습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);

		return "redirect:/lu/workcert/listWorkCert.do?search=top&seach=detail&yyyy="+workCertVO.getYyyy()+"&term="+workCertVO.getTerm();
	}
 
	@RequestMapping("/lu/workcert/downloadWorkCertMember.do")
	public ModelAndView downloadWorkCertMember(WorkCertVO workCertVO ,RedirectAttributes redirectAttributes,  ModelMap model) throws Exception {
		
		LOGGER.debug("#### URL = /lu/workcert/downloadWorkCertMember.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(workCertVO); // session의 정보를 VO에 추가.
		ModelAndView mav = new ModelAndView("zipdownloadView");
		String retMsg ="";
		List<WorkCertVO> resultList = workCertService.selectAtchFileIdList(workCertVO);
  

	    String fileStr = "";
        String oldFileStr = "";
        String atchFileId = "";
        String memId = "";
        String memName= "";
		for(int a=0;resultList.size()>a ;a++){
			
			memId =  resultList.get(a).getMemId();
			memName =resultList.get(a).getMemName();
			
			atchFileId = resultList.get(a).getAtchFileIdInc();			
			if(atchFileId!=null&&!atchFileId.equals("")){
				AtchFileVO atchFileVO1 = new AtchFileVO();
				atchFileVO1.setFileSn(1);
				atchFileVO1.setAtchFileId(atchFileId);
				if(atchFileId!=null&&!atchFileId.equals("")){
					AtchFileVO resultfile = atchFileService.getAtchFile(atchFileVO1);			
					fileStr +=  memId+"_"+memName+"."+resultfile.getFileExtn() + "?";					
		            oldFileStr += resultfile.getFileSavePath()+"/"+resultfile.getSaveFileName()+"?";				
				}				
			}
			
			atchFileId = resultList.get(a).getAtchFileIdRec();			
			if(atchFileId!=null&&!atchFileId.equals("")){
				AtchFileVO atchFileVO1 = new AtchFileVO();
				atchFileVO1.setFileSn(1);
				atchFileVO1.setAtchFileId(atchFileId);
				if(atchFileId!=null&&!atchFileId.equals("")){
					AtchFileVO resultfile = atchFileService.getAtchFile(atchFileVO1);			
					fileStr +=  memId+"_"+memName+"."+resultfile.getFileExtn() + "?";					
		            oldFileStr += resultfile.getFileSavePath()+"/"+resultfile.getSaveFileName()+"?";				
				}				
			}
			atchFileId = resultList.get(a).getAtchFileIdDoc();			
			if(atchFileId!=null&&!atchFileId.equals("")){
				AtchFileVO atchFileVO1 = new AtchFileVO();
				atchFileVO1.setFileSn(1);
				atchFileVO1.setAtchFileId(atchFileId);
				if(atchFileId!=null&&!atchFileId.equals("")){
					AtchFileVO resultfile = atchFileService.getAtchFile(atchFileVO1);			
					fileStr +=  memId+"_"+memName+"."+resultfile.getFileExtn() + "?";
		            oldFileStr += resultfile.getFileSavePath()+"/"+resultfile.getSaveFileName()+"?";				
				}				
			}			

		}
		
		if(fileStr != null && !fileStr.equals("")) {
            mav.addObject("fileStr", fileStr);
            mav.addObject("oldFileStr", oldFileStr);
            mav.addObject("reNameStr",workCertVO.getYyyy()+"_"+workCertVO.getTerm()+".zip");
            workCertService.updateWorkCertMemberFiledown(workCertVO);
            //다운로드 완료 정보 수정
		}else{
			retMsg = "파일이 없습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			LOGGER.debug("#### retMsg=" + retMsg );
			mav.setViewName("redirect:/lu/workcert/listWorkCert.do?seach=detail&yyyy="+workCertVO.getYyyy()+"&term="+workCertVO.getTerm()+"&retMsgEncode="+ retMsg);
		}
		return mav;
	}
	/**
	 * 학습 근로자 제직증빙서류 조회화면  
	 * @param workCertVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/lu/workcert/listWorkCertStd.do")
	public String listWorkCertStd(@ModelAttribute("frmWorkCert") WorkCertVO workCertVO, ModelMap model,HttpSession session)throws Exception{

		LOGGER.debug("#### URL = /lu/workcert/listWorkCertStd.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(workCertVO); // session의 정보를 VO에 추가.
		
		workCertVO.setDeptNo(workCertVO.getSessionDeptNo());
		workCertVO.setMemId(workCertVO.getSessionMemId());
		
		List <WorkCertVO> workCertList = workCertService.listWorkCertStatePop(workCertVO);
		
		model.addAttribute("workCertList", workCertList);


		WorkCertVO workCert = null;
		// 학과코드
		workCertVO.setDeptNo(workCertVO.getSessionDeptNo());		
		workCertVO.setSearch("bottom");
		
		if(workCertVO.getWorkProofId()==null||workCertVO.getWorkProofId().equals("")){
			//등록건이 없을때			
			workCert = workCertService.getWorkCertPeriod(workCertVO);			
		}else{
			//등록건이 있을때			
			workCert = workCertService.selectAtchFileId(workCertVO);
			AtchFileVO atchFileVO = new AtchFileVO();
			atchFileVO.setFileSn(1);
			AtchFileVO resultFile1 = null;
			AtchFileVO resultFile2 = null;
			AtchFileVO resultFile3 = null;
			
			if(workCert!=null && workCert.getAtchFileIdInc()!=null){
				atchFileVO.setAtchFileId(workCert.getAtchFileIdInc());
				//첨부파일
				resultFile1 = atchFileService.getAtchFile(atchFileVO);		        	        				
			}
			if(workCert!=null && workCert.getAtchFileIdRec()!=null){
				atchFileVO.setAtchFileId(workCert.getAtchFileIdRec());
				//첨부파일
				resultFile2 = atchFileService.getAtchFile(atchFileVO);
			}
			if(workCert!=null && workCert.getAtchFileIdDoc()!=null){
				atchFileVO.setAtchFileId(workCert.getAtchFileIdDoc());
				//첨부파일
				resultFile3 = atchFileService.getAtchFile(atchFileVO);
			}
			model.addAttribute("resultFile1", resultFile1);
			model.addAttribute("resultFile2", resultFile2);
	        model.addAttribute("resultFile3", resultFile3);
	        
		}

		model.addAttribute("workCertVO", workCert);
		
		
		return "oklms/lu/workcert/listWorkCertStd";
	}


	/**
	 * 학습 근로자 제직증빙서류 등록 및 수정
	 * @param workCertVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/lu/workcert/insertWorkCertStd.do")
	public String insertWorkCertStd(WorkCertVO workCertVO, ModelMap model,RedirectAttributes redirectAttributes,final MultipartHttpServletRequest multiRequest)throws Exception{
		
		LOGGER.debug("#### URL = /lu/workcert/insertWorkCertStd.do" );
		String retMsg = "입력값을 확인해주세요";
		int insertCnt = 0;
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(workCertVO); // session의 정보를 VO에 추가.
		
		workCertVO.setMemId(workCertVO.getSessionMemId());
		workCertVO.setMemName(workCertVO.getSessionMemName());
		workCertVO.setDeptNo(workCertVO.getSessionDeptNo());
		
		insertCnt = workCertService.goInsertWorkCert(workCertVO,multiRequest);
		
		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}
		
		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		
		return "redirect:/lu/workcert/listWorkCertStd.do?periodId="+workCertVO.getPeriodId()+"&workProofId="+workCertVO.getWorkProofId();
	}
	
	 


}
