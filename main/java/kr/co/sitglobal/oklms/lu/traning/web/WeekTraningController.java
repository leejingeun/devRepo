package kr.co.sitglobal.oklms.lu.traning.web;
 

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 

import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.commbiz.cmmcode.vo.CommonCodeVO;
import kr.co.sitglobal.oklms.lu.interview.service.InterviewService;
import kr.co.sitglobal.oklms.lu.interview.vo.InterviewCompanyVO;
import kr.co.sitglobal.oklms.lu.report.service.ReportService;
import kr.co.sitglobal.oklms.lu.traning.service.TraningNoteSerivce;
import kr.co.sitglobal.oklms.lu.traning.service.WeekTraningService;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningNoteVO;  

/**
 * 주간훈련일지
 *
 */
@Controller
public class WeekTraningController  extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(WeekTraningController.class);

	@Resource(name = "traningNoteSerivce")
	private TraningNoteSerivce traningNoteSerivce;

	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;

	@Resource(name= "weekTraningService")
	private WeekTraningService weekTraningService;

	@Resource(name = "ReportService")
	private ReportService reportService;
	
	@Resource(name = "InterviewService")
	private InterviewService interviewService;
	/**
	 * 훈련지원 > 주간훈련일지 (기업현장교사)
	 * @param traningNoteVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/weektraning/listWeekTraningNoteCot.do")
	public String listWeekTraningNote(@ModelAttribute("frmWeekTraning") TraningNoteVO  traningNoteVO ,ModelMap model, HttpSession session) throws Exception {

		LOGGER.debug("#### URL = /lu/weektraning/listWeekTraningNoteCot.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.
				
		if(traningNoteVO.getYyyy()==null || traningNoteVO.getYyyy().equals("")){
			// 현재 년도학기조회
			CommonCodeVO commonCodeVO =	commonCodeService.selectYearTerm();
			traningNoteVO.setYyyy(commonCodeVO.getYyyy());			
			traningNoteVO.setTerm(commonCodeVO.getTerm());
		}
		traningNoteVO.setMemSeq(traningNoteVO.getSessionMemSeq());
		
		//주간훈련일지하단고정목록
		List<TraningNoteVO> bottomList =weekTraningService.listWeekTraningNoteBottomCot(traningNoteVO);
		model.put("bottomList", bottomList);
		
		if(traningNoteVO.getWeekCnt()==null||traningNoteVO.getWeekCnt().equals("")){
			if(bottomList!=null && bottomList.size()>0){
				traningNoteVO.setWeekCnt(bottomList.get(0).getNowWeekCnt());
			}else{
				traningNoteVO.setWeekCnt("1");
			}
		}
		
		//주간훈련일지목록
		List<TraningNoteVO> topList =weekTraningService.listWeekTraningNoteCot(traningNoteVO);
		model.put("topList", topList);
				
		
		model.put("traningNoteVO", traningNoteVO);
		//현장 교수
		return "oklms/lu/weektraning/listWeekTraningNoteCot";

	}

	/**
	 * 훈련지원 > 주간훈련일지 제출 (기업현장교사)
	 * @param traningNoteVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/weektraning/insertWeekTraningNoteCot.do")
	public String insertWeekTraningNoteCot(@ModelAttribute("frmWeekTraning") TraningNoteVO  traningNoteVO ,ModelMap model, HttpSession session,  RedirectAttributes redirectAttributes) throws Exception {

		LOGGER.debug("#### URL = /lu/weektraning/insertWeekTraningNoteCot.do" );
		int insertCnt = 0;
		String retMsg="";
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.
				
		traningNoteVO.setMemSeq(traningNoteVO.getSessionMemSeq());
		 
		//주간훈련일지하단고정목록
		insertCnt = weekTraningService.updateWeekTraningNoteCot(traningNoteVO);

		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}
		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		
		//제출
		return "redirect:/lu/weektraning/listWeekTraningNoteCot.do?weekCnt="+traningNoteVO.getWeekCnt()+"&yyyy="+traningNoteVO.getYyyy()+"&term="+traningNoteVO.getTerm();

	}

	/**
	 * 훈련지원 > 주간훈련일지인쇄 (기업현장교사)
	 * @param traningNoteVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/weektraning/printWeekTraningNoteCot.do")
	public String printWeekTraningNoteCot(@ModelAttribute("frmWeekTraning") TraningNoteVO  traningNoteVO ,ModelMap model ) throws Exception {

		LOGGER.debug("#### URL = /lu/weektraning/printWeekTraningNoteCot.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.
		 
		List<TraningNoteVO> detailTraningNoteCot =weekTraningService.detailTraningNoteCot(traningNoteVO);
		model.addAttribute("detailTraningNotePrd", detailTraningNoteCot);
		
		traningNoteVO.setMemSeq(traningNoteVO.getSessionMemSeq());
			// OJT출력
		if(traningNoteVO.getAddyn().equals("N")){

	  		model.addAttribute("traningNoteVO", traningNoteVO);
	  		 
	  		//주간훈련일지하단고정목록
			List<TraningNoteVO> resultList =weekTraningService.getWeekTraningNoteCot(traningNoteVO);
			model.addAttribute("resultList", resultList);
	 
		}else{
 
		  	model.addAttribute("traningNoteVO", traningNoteVO);
	  		 
	  		//주간훈련일지하단고정목록
			List<TraningNoteVO> resultList =weekTraningService.getWeekTraningNoteAddCot(traningNoteVO);
			model.addAttribute("resultList", resultList);
 
		}		
		if(traningNoteVO.getCurrent().equals("sign")){
			return "oklms_popup/lu/weektraning/printWeekTraningNoteCotOjt";	
		}else{
			return "oklms_popup/lu/weektraning/printWeekTraningNoteCotOjtPrd";		
		}
		 
	}
	/**
	 * 훈련지원 > 주간훈련일지 (교수)
	 * @param traningNoteVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/weektraning/listWeekTraningNotePrd.do")
	public String listWeekTraningNotePrd(@ModelAttribute("frmWeekTraning") TraningNoteVO  traningNoteVO ,ModelMap model, HttpSession session) throws Exception {

		LOGGER.debug("#### URL = /lu/weektraning/listWeekTraningNotePrd.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.
				
		if(traningNoteVO.getYyyy()==null || traningNoteVO.getYyyy().equals("")){
			// 현재 년도학기조회
			CommonCodeVO commonCodeVO =	commonCodeService.selectYearTerm();
			traningNoteVO.setYyyy(commonCodeVO.getYyyy());			
			traningNoteVO.setTerm(commonCodeVO.getTerm());
		}
		traningNoteVO.setMemSeq(traningNoteVO.getSessionMemSeq());
		
		//주간훈련일지하단고정목록
		List<TraningNoteVO> bottomList =weekTraningService.listWeekTraningNoteBottomPrd(traningNoteVO);
		model.put("bottomList", bottomList);
		
		if(traningNoteVO.getWeekCnt()==null||traningNoteVO.getWeekCnt().equals("")){
			if(bottomList!=null && bottomList.size()>0){
				traningNoteVO.setWeekCnt(bottomList.get(0).getNowWeekCnt());
			}else{
				traningNoteVO.setWeekCnt("1");
			}
		}

		//주간훈련일지목록
		List<TraningNoteVO> topList =weekTraningService.listWeekTraningNotePrd(traningNoteVO);
		model.put("topList", topList);

		model.put("traningNoteVO", traningNoteVO);
		//현장 교수
		return "oklms/lu/weektraning/listWeekTraningNotePrd";

	}

	/**
	 * 훈련지원 > 주간훈련일지 제출 (교수)
	 * @param traningNoteVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/weektraning/insertWeekTraningNotePrd.do")
	public String insertWeekTraningNotePrd(@ModelAttribute("frmWeekTraning") TraningNoteVO  traningNoteVO ,ModelMap model, HttpSession session,  RedirectAttributes redirectAttributes) throws Exception {

		LOGGER.debug("#### URL = /lu/weektraning/insertWeekTraningNotePrd.do" );
		int insertCnt = 0;
		String retMsg="";
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.
				
		traningNoteVO.setMemSeq(traningNoteVO.getSessionMemSeq());
		 
		//주간훈련일지하단고정목록
		insertCnt = weekTraningService.updateWeekTraningNotePrd(traningNoteVO);

		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}
		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		
		//제출
		return "redirect:/lu/weektraning/listWeekTraningNotePrd.do?weekCnt="+traningNoteVO.getWeekCnt()+"&yyyy="+traningNoteVO.getYyyy()+"&term="+traningNoteVO.getTerm();

	}
	
	/**
	 * 훈련지원 > 주간훈련일지인쇄 (교수)
	 * @param traningNoteVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/weektraning/printWeekTraningNotePrd.do")
	public String printWeekTraningNotePrd(@ModelAttribute("frmWeekTraning") TraningNoteVO  traningNoteVO ,ModelMap model ) throws Exception {

		LOGGER.debug("#### URL = /lu/weektraning/printWeekTraningNotePrd.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.

		 
		List<TraningNoteVO> detailTraningNotePrd =weekTraningService.detailTraningNotePrd(traningNoteVO);
		model.addAttribute("detailTraningNotePrd", detailTraningNotePrd);
		
		if(traningNoteVO.getTraningType().equals("OFF")){
			// OFF-JT출력
			
			if(traningNoteVO.getAddyn().equals("N")){


		  		model.addAttribute("traningNoteVO", traningNoteVO);
		  		 
		  		//주간훈련일지하단고정목록
				List<TraningNoteVO> resultList =weekTraningService.getWeekTraningNotePrd(traningNoteVO);
				model.addAttribute("resultList", resultList);
	 
			}else{
 
		  		model.addAttribute("traningNoteVO", traningNoteVO);
		  		 
		  		//주간훈련일지 
				List<TraningNoteVO> resultList =weekTraningService.getWeekTraningNoteAddPrd(traningNoteVO);
				model.addAttribute("resultList", resultList);
	 
			}
			if(traningNoteVO.getCurrent().equals("sign")){
				return "oklms_popup/lu/weektraning/printWeekTraningNoteOff";
			}else{
				return "oklms_popup/lu/weektraning/printWeekTraningNoteOffPrd";
			}
			 
			
		}else{
			// OJT출력

			if(traningNoteVO.getAddyn().equals("N")){


		  		model.addAttribute("traningNoteVO", traningNoteVO);
		  		 
		  		//주간훈련일지하단고정목록
				List<TraningNoteVO> resultList =weekTraningService.getWeekTraningNotePrd(traningNoteVO);
				model.addAttribute("resultList", resultList);
	 
			}else{
 
		  		model.addAttribute("traningNoteVO", traningNoteVO);
		  		 
		  		//주간훈련일지 
				List<TraningNoteVO> resultList =weekTraningService.getWeekTraningNoteAddPrd(traningNoteVO);
				model.addAttribute("resultList", resultList);
	 
			}
			if(traningNoteVO.getCurrent().equals("sign")){
				return "oklms_popup/lu/weektraning/printWeekTraningNoteOjt";	
			}else{
				return "oklms_popup/lu/weektraning/printWeekTraningNoteOjtPrd";	
			}
			
			 	
		}

	}
	
	/**
	 * 훈련지원 > 주간훈련일지 (센터전담자)
	 * @param traningNoteVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/weektraning/listWeekTraningNoteCcn.do")
	public String listWeekTraningNoteCcn(@ModelAttribute("frmWeekTraning") TraningNoteVO  traningNoteVO ,ModelMap model ) throws Exception {

		LOGGER.debug("#### URL = /lu/weektraning/listWeekTraningNoteCcn.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.

  		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
  		interviewCompanyVO.setCompanyId(traningNoteVO.getCompanyId());
  		interviewCompanyVO.setTraningProcessId(traningNoteVO.getTraningProcessId());

  		InterviewCompanyVO result=interviewService.InterviewCompany(interviewCompanyVO);
  		model.addAttribute("result", result);
  		model.addAttribute("traningNoteVO", traningNoteVO);

		//주간훈련일지하단고정목록
		List<TraningNoteVO> bottomList =weekTraningService.listWeekTraningNoteBottomCcn(traningNoteVO);
		model.put("bottomList", bottomList);
 
		return "oklms/lu/weektraning/listWeekTraningNoteCcn";
	}
	/**
	 * 훈련지원 > 주간훈련일지인쇄 (센터전담자)
	 * @param traningNoteVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/weektraning/printWeekTraningNote.do")
	public String printWeekTraningNote(@ModelAttribute("frmWeekTraning") TraningNoteVO  traningNoteVO ,ModelMap model ) throws Exception {

		LOGGER.debug("#### URL = /lu/weektraning/printWeekTraningNote.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.

  		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
  		interviewCompanyVO.setCompanyId(traningNoteVO.getCompanyId());
  		interviewCompanyVO.setTraningProcessId(traningNoteVO.getTraningProcessId());

  		InterviewCompanyVO result=interviewService.InterviewCompany(interviewCompanyVO);
  		model.addAttribute("result", result);
  		model.addAttribute("traningNoteVO", traningNoteVO);

		//주간훈련일지하단고정목록
		List<TraningNoteVO> bottomList =weekTraningService.listWeekTraningNoteBottomCcn(traningNoteVO);
		model.put("bottomList", bottomList);
 
		return "oklms_popup/lu/weektraning/printWeekTraningNote";

	}
	/**
	 * 훈련지원 > 주간훈련일지엑셀다운로드 (센터전담자)
	 * @param traningNoteVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/weektraning/excelWeekTraningNote.do")
	public String excelWeekTraningNote(@ModelAttribute("frmWeekTraning") TraningNoteVO  traningNoteVO ,ModelMap model , HttpServletRequest request ) throws Exception {

		LOGGER.debug("#### URL = /lu/weektraning/excelWeekTraningNote.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.

  		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
  		interviewCompanyVO.setCompanyId(traningNoteVO.getCompanyId());
  		interviewCompanyVO.setTraningProcessId(traningNoteVO.getTraningProcessId());
  		
  		InterviewCompanyVO result=interviewService.InterviewCompany(interviewCompanyVO);
  		model.addAttribute("result", result);
  		model.addAttribute("traningNoteVO", traningNoteVO);

		//주간훈련일지하단고정목록
		List<TraningNoteVO> bottomList =weekTraningService.listWeekTraningNoteBottomCcn(traningNoteVO);
		model.put("bottomList", bottomList);

		request.setAttribute("ExcelName", URLEncoder.encode( "훈련일지 제출현황".replaceAll(" ", "_") ,"UTF-8") );
 
		return "oklms_excel/lu/weektraning/excelWeekTraningNote";

	}
	
	/**
	 * 훈련지원 > 주간훈련일지 상세보기 (센터전담자)
	 * @param traningNoteVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/weektraning/getWeekTraningNoteCcn.do")
	public String getWeekTraningNoteCcn(@ModelAttribute("frmWeekTraning") TraningNoteVO  traningNoteVO ,ModelMap model ) throws Exception {

		LOGGER.debug("#### URL = /lu/weektraning/getWeekTraningNoteCcn.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.

  		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
  		interviewCompanyVO.setCompanyId(traningNoteVO.getCompanyId());
  		interviewCompanyVO.setTraningProcessId(traningNoteVO.getTraningProcessId());
  		 
  		InterviewCompanyVO result=interviewService.InterviewCompany(interviewCompanyVO);
  		model.addAttribute("result", result);
  		model.addAttribute("traningNoteVO", traningNoteVO);
  		
  		 if(traningNoteVO.getAddyn().equals("N")){
  	  		//주간훈련일지하단고정목록
  			List<TraningNoteVO> resultList =weekTraningService.getWeekTraningNoteAddCcn(traningNoteVO);
  			model.addAttribute("resultList", resultList);  			 
  		 }else{
  	  		//주간훈련일지하단고정목록
  			List<TraningNoteVO> resultList =weekTraningService.getWeekTraningNoteAddCcnAdd(traningNoteVO);
  			model.addAttribute("resultList", resultList);
  		 }

 

		return "oklms/lu/weektraning/getWeekTraningNoteCcn"; 
	}

	/**
	 * 훈련지원 > 주간훈련일지수정 (센터전담자)
	 * @param traningNoteVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/weektraning/updateWeekTraningNoteCcn.do")
	public String updateWeekTraningNoteCcn(@ModelAttribute("frmWeekTraning") TraningNoteVO  traningNoteVO ,ModelMap model, HttpSession session,  RedirectAttributes redirectAttributes) throws Exception {

		LOGGER.debug("#### URL = /lu/weektraning/updateWeekTraningNoteCcn.do" );
		int insertCnt = 0;
		String retMsg="";
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.

		if(traningNoteVO.getStatusType().equals("U")){
			insertCnt  = traningNoteSerivce.goInsertTraningNoteDetail(traningNoteVO);			
		}else if(traningNoteVO.getStatusType().equals("X")){ //반려
			traningNoteVO.setState("X");
			insertCnt = weekTraningService.updateWeekTraningNoteCcn(traningNoteVO);
		}else if(traningNoteVO.getStatusType().equals("C")){ //확정
			traningNoteVO.setState("C");
			insertCnt = weekTraningService.updateWeekTraningNoteCcn(traningNoteVO);
		}	
		
		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}
		redirectAttributes.addFlashAttribute("retMsg", retMsg);

		//제출 
		return "redirect:/lu/weektraning/getWeekTraningNoteCcn.do?traningType="+traningNoteVO.getTraningType() +"&addyn="+traningNoteVO.getAddyn()+"&weekCnt="+traningNoteVO.getWeekCnt()+"&yyyy="+traningNoteVO.getYyyy()+"&term="+traningNoteVO.getTerm()+"&companyId="+traningNoteVO.getCompanyId()+"&traningProcessId="+traningNoteVO.getTraningProcessId();

	}
	/**
	 * 훈련지원 > 주간훈련일지 (HRD담당자)
	 * @param traningNoteVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/weektraning/listWeekTraningNoteHrd.do")
	public String listWeekTraningNoteHrd(@ModelAttribute("frmWeekTraning") TraningNoteVO  traningNoteVO ,ModelMap model ) throws Exception {

		LOGGER.debug("#### URL = /lu/weektraning/listWeekTraningNoteHrd.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.

  		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
  		interviewCompanyVO.setCompanyId(traningNoteVO.getCompanyId());
  		interviewCompanyVO.setTraningProcessId(traningNoteVO.getTraningProcessId());

  		InterviewCompanyVO result=interviewService.InterviewCompany(interviewCompanyVO);
  		model.addAttribute("result", result);
  		model.addAttribute("traningNoteVO", traningNoteVO);

		//주간훈련일지하단고정목록
		List<TraningNoteVO> bottomList =weekTraningService.listWeekTraningNoteBottomCcn(traningNoteVO);
		model.put("bottomList", bottomList);
 
		return "oklms/lu/weektraning/listWeekTraningNoteHrd";
	}	

	/**
	 * 훈련지원 > 주간훈련일지 상세보기 (HRD담당자)
	 * @param traningNoteVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/weektraning/getWeekTraningNoteHrd.do")
	public String getWeekTraningNoteHrd(@ModelAttribute("frmWeekTraning") TraningNoteVO  traningNoteVO ,ModelMap model ) throws Exception {

		LOGGER.debug("#### URL = /lu/weektraning/getWeekTraningNoteHrd.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.
		
  		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
  		interviewCompanyVO.setCompanyId(traningNoteVO.getCompanyId());
  		interviewCompanyVO.setTraningProcessId(traningNoteVO.getTraningProcessId());
  		 
  		InterviewCompanyVO result=interviewService.InterviewCompany(interviewCompanyVO);
  		model.addAttribute("result", result);
  		model.addAttribute("traningNoteVO", traningNoteVO);
  		
		if(traningNoteVO.getAddyn().equals("N")){
	  		//주간훈련일지하단고정목록
			List<TraningNoteVO> resultList =weekTraningService.getWeekTraningNoteCcn(traningNoteVO);
			model.addAttribute("resultList", resultList);
 
		}else{
	  		//주간훈련일지하단고정목록
			List<TraningNoteVO> resultList =weekTraningService.getWeekTraningNoteAddCcnAdd(traningNoteVO);
			model.addAttribute("resultList", resultList);

		}
		return "oklms/lu/weektraning/getWeekTraningNoteHrd";		

	}
	
	/**
	 * 월간학습근로자 출석부 (센터담당자)
	 * @param traningNoteVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/weektraning/listMonthTraningNoteCcn.do")
	public String listMonthTraningNoteCcn(@ModelAttribute("frmWeekTraning") TraningNoteVO  traningNoteVO ,ModelMap model ) throws Exception {

		LOGGER.debug("#### URL = /lu/weektraning/listMonthTraningNoteCcn.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.
		

  		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
  		interviewCompanyVO.setCompanyId(traningNoteVO.getCompanyId());
  		interviewCompanyVO.setTraningProcessId(traningNoteVO.getTraningProcessId());

  		InterviewCompanyVO result=interviewService.InterviewCompany(interviewCompanyVO);
  		model.addAttribute("result", result); 
  		// 조회 연도(기본현재년)
  		String yyyy = traningNoteVO.getYyyy();
  		if(yyyy==null||yyyy.equals("")){
  			yyyy=	kr.co.sitglobal.oklms.commbiz.util.BizUtil.getCurrentDateString("yyyy");
  			traningNoteVO.setYyyy(yyyy);
  		}
  		// 조회 월( 기본현재월)
  		String mm = traningNoteVO.getMm();
  		if(mm==null||mm.equals("")){
  			mm=	kr.co.sitglobal.oklms.commbiz.util.BizUtil.getCurrentDateString("MM");
  			traningNoteVO.setMm(mm);
  		}
  		if(mm.length()==1){
  			mm="0"+mm;
  		}

  		// 월의 마지막날짜반환  		
  		int lastDay = kr.co.sitglobal.oklms.commbiz.util.BizUtil.getMonthLastday(yyyy+mm+"01");
  		model.addAttribute("lastDay", lastDay);

		traningNoteVO.setTraningMonth(yyyy+"."+mm);
  		List<List<TraningNoteVO>> resultlistSum = new ArrayList<List<TraningNoteVO>>(); 
  		if(traningNoteVO.getCompanyId()!=null&&!traningNoteVO.getCompanyId().equals("")){
	  			
	  		for(int a=1;lastDay>=a ;a++){
		  		//주간훈련일지하단고정목록
	  			String day = ""+a;
	  			if(a<=9){
	  				day="0"+day;
	  			}
	  			traningNoteVO.setTraningDate(yyyy+"."+mm+"."+day);

	  			
				List<TraningNoteVO> resultList =weekTraningService.selectDayTraningNoteAll(traningNoteVO);
				resultlistSum.add(resultList);
	  		}
	  		
	  		List<TraningNoteVO> resultTotal = weekTraningService.selectDayTraningNoteTotal(traningNoteVO);
	  		model.addAttribute("resultTotal", resultTotal);
	  		
	  		TraningNoteVO resultSum = weekTraningService.selectDayTraningNoteAllSum(traningNoteVO);
	  		model.addAttribute("resultSum", resultSum);
  		}  	
  		
  		traningNoteVO.setTraningNoteVOArr(resultlistSum);
  		
  		model.addAttribute("traningNoteVO", traningNoteVO);

		return "oklms/lu/weektraning/listMonthTraningNoteCcn";		

	}
	
	/**
	 * 월간학습근로자 출석부인쇄페이지 (센터담당자)
	 * @param traningNoteVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/weektraning/printMonthTraningNoteCcn.do")
	public String printMonthTraningNoteCcn(@ModelAttribute("frmWeekTraning") TraningNoteVO  traningNoteVO ,ModelMap model ) throws Exception {

		LOGGER.debug("#### URL = /lu/weektraning/printMonthTraningNoteCcn.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.
		

  		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
  		interviewCompanyVO.setCompanyId(traningNoteVO.getCompanyId());
  		interviewCompanyVO.setTraningProcessId(traningNoteVO.getTraningProcessId());

  		InterviewCompanyVO result=interviewService.InterviewCompany(interviewCompanyVO);
  		model.addAttribute("result", result); 
  		// 조회 연도(기본현재년)
  		String yyyy = traningNoteVO.getYyyy();
  		if(yyyy==null||yyyy.equals("")){
  			yyyy=	kr.co.sitglobal.oklms.commbiz.util.BizUtil.getCurrentDateString("yyyy");
  			traningNoteVO.setYyyy(yyyy);
  		}
  		// 조회 월( 기본현재월)
  		String mm = traningNoteVO.getMm();
  		if(mm==null||mm.equals("")){
  			mm=	kr.co.sitglobal.oklms.commbiz.util.BizUtil.getCurrentDateString("MM");
  			traningNoteVO.setMm(mm);
  		}
  		if(mm.length()==1){
  			mm="0"+mm;
  		}

  		// 월의 마지막날짜반환  		
  		int lastDay = kr.co.sitglobal.oklms.commbiz.util.BizUtil.getMonthLastday(yyyy+mm+"01");
  		model.addAttribute("lastDay", lastDay);

  		traningNoteVO.setTraningMonth(yyyy+"."+mm);
  		List<List<TraningNoteVO>> resultlistSum = new ArrayList<List<TraningNoteVO>>(); 
  		if(traningNoteVO.getCompanyId()!=null&&!traningNoteVO.getCompanyId().equals("")){
	  			
	  		for(int a=1;lastDay>=a ;a++){
		  		//주간훈련일지하단고정목록
	  			String day = ""+a;
	  			if(a<=9){
	  				day="0"+day;
	  			}
	  			traningNoteVO.setTraningDate(yyyy+"."+mm+"."+day);
				List<TraningNoteVO> resultList =weekTraningService.selectDayTraningNoteAll(traningNoteVO);
				resultlistSum.add(resultList);
	  		}
	  		List<TraningNoteVO> resultTotal = weekTraningService.selectDayTraningNoteTotal(traningNoteVO);
	  		model.addAttribute("resultTotal", resultTotal);
	  		
	  		TraningNoteVO resultSum = weekTraningService.selectDayTraningNoteAllSum(traningNoteVO);
	  		model.addAttribute("resultSum", resultSum);
  		}  	
  		
  		traningNoteVO.setTraningNoteVOArr(resultlistSum);
  		
  		model.addAttribute("traningNoteVO", traningNoteVO);

		return "oklms_popup/lu/weektraning/printMonthTraningNoteCcn";		

	}		
	/**
	 * 월간학습근로자 출석부엑셀다운로드 (센터담당자)
	 * @param traningNoteVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/weektraning/excelMonthTraningNoteCcn.do")
	public String excelMonthTraningNoteCcn(@ModelAttribute("frmWeekTraning") TraningNoteVO  traningNoteVO ,ModelMap model , HttpServletRequest request ) throws Exception {

		LOGGER.debug("#### URL = /lu/weektraning/listMonthTraningNoteCcn.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.
		

  		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
  		interviewCompanyVO.setCompanyId(traningNoteVO.getCompanyId());
  		interviewCompanyVO.setTraningProcessId(traningNoteVO.getTraningProcessId());

  		InterviewCompanyVO result=interviewService.InterviewCompany(interviewCompanyVO);
  		model.addAttribute("result", result); 
  		// 조회 연도(기본현재년)
  		String yyyy = traningNoteVO.getYyyy();
  		if(yyyy==null||yyyy.equals("")){
  			yyyy=	kr.co.sitglobal.oklms.commbiz.util.BizUtil.getCurrentDateString("yyyy");
  			traningNoteVO.setYyyy(yyyy);
  		}
  		// 조회 월( 기본현재월)
  		String mm = traningNoteVO.getMm();
  		if(mm==null||mm.equals("")){
  			mm=	kr.co.sitglobal.oklms.commbiz.util.BizUtil.getCurrentDateString("MM");
  			traningNoteVO.setMm(mm);
  		}
  		if(mm.length()==1){
  			mm="0"+mm;
  		}

  		// 월의 마지막날짜반환  		
  		int lastDay = kr.co.sitglobal.oklms.commbiz.util.BizUtil.getMonthLastday(yyyy+mm+"01");
  		model.addAttribute("lastDay", lastDay);

  		traningNoteVO.setTraningMonth(yyyy+"."+mm);
  		List<List<TraningNoteVO>> resultlistSum = new ArrayList<List<TraningNoteVO>>(); 
  		if(traningNoteVO.getCompanyId()!=null&&!traningNoteVO.getCompanyId().equals("")){
	  			
	  		for(int a=1;lastDay>=a ;a++){
		  		//주간훈련일지하단고정목록
	  			String day = ""+a;
	  			if(a<=9){
	  				day="0"+day;
	  			}
	  			traningNoteVO.setTraningDate(yyyy+"."+mm+"."+day);
				List<TraningNoteVO> resultList =weekTraningService.selectDayTraningNoteAll(traningNoteVO);
				resultlistSum.add(resultList);
	  		}
	  		List<TraningNoteVO> resultTotal = weekTraningService.selectDayTraningNoteTotal(traningNoteVO);
	  		model.addAttribute("resultTotal", resultTotal);
	  		
	  		TraningNoteVO resultSum = weekTraningService.selectDayTraningNoteAllSum(traningNoteVO);
	  		model.addAttribute("resultSum", resultSum);
  		}  		
  		traningNoteVO.setTraningNoteVOArr(resultlistSum);
  		
  		model.addAttribute("traningNoteVO", traningNoteVO);
 
		request.setAttribute("ExcelName", URLEncoder.encode( "월간출석부".replaceAll(" ", "_") ,"UTF-8") );
 
		return "oklms_excel/lu/weektraning/excelMonthTraningNoteCcn";

	}	
}