package kr.co.sitglobal.oklms.lu.grade.web;

import java.net.URLEncoder;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.co.sitglobal.aunuri.service.AunuriLinkService;
import kr.co.sitglobal.aunuri.vo.AunuriLinkSubjectGradeVO;
import kr.co.sitglobal.ifx.vo.CmsCourseContentItemListVO;
import kr.co.sitglobal.oklms.comm.util.CommonUtil;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.commbiz.cmmcode.vo.CommonCodeVO;
import kr.co.sitglobal.oklms.lu.grade.service.GradeService;
import kr.co.sitglobal.oklms.lu.grade.vo.GradeVO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class GradeController  extends BaseController{

	
	@Resource(name = "GradeService")
	private GradeService gradeService;
	
	@Resource(name = "aunuriLinkService")
	private AunuriLinkService aunuriLinkService;
	
	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;
	

	// 학과전담자 - 성적전송 현황
	@RequestMapping(value="/lu/grade/listGradeCdp.do")
	public String listGradeCdp(@ModelAttribute("frmGrade") GradeVO gradeVO, ModelMap model, HttpServletRequest request) throws Exception {
		
		logger.debug("#### URL = /lu/grade/listGradeCdp.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(gradeVO); // session의 정보를 VO에 추가.
		
		if(gradeVO.getYyyy()==null || gradeVO.getYyyy().equals("")){
			// 현재 년도학기조회
			CommonCodeVO commonCodeVO =	commonCodeService.selectYearTerm();
			gradeVO.setYyyy(commonCodeVO.getYyyy());			
			gradeVO.setTerm(commonCodeVO.getTerm());
			gradeVO.setSearch("top");
		}
		
		model.addAttribute("gradeVO", gradeVO);
		
		List <GradeVO> resultList = gradeService.listGradeSendStatusList(gradeVO);
		List<GradeVO> statusList = new ArrayList<GradeVO>();
		
		for( int  i= 0; i < resultList.size(); i++ ){
			GradeVO graVO = resultList.get(i);
			
			AunuriLinkSubjectGradeVO aunuriLinkSubjectGradeVO = new AunuriLinkSubjectGradeVO();
			aunuriLinkSubjectGradeVO.setYy(graVO.getYyyy());
			aunuriLinkSubjectGradeVO.setSemstrCd(graVO.getSemstrCd());
			
			logger.debug("============= graVO.getYyyy() :  "+graVO.getYyyy());
			logger.debug("============= graVO.getSemstrCd() :  "+graVO.getSemstrCd());
			
			// 콤마로 넘어온 값 배열로 세팅
			aunuriLinkSubjectGradeVO.setGroupMemIdArr(graVO.getGroupMemIds().split(","));
			
			
			
			// IN 조건에 사용하기 위해 문자열 변환 후 쿼테이션 세팅
			aunuriLinkSubjectGradeVO.setMemIds(CommonUtil.toArrStr(aunuriLinkSubjectGradeVO.getGroupMemIdArr()));
			
			// 성적이 있는지 확인
			AunuriLinkSubjectGradeVO aVO = aunuriLinkService.getSubectGradeCnt(aunuriLinkSubjectGradeVO);
			
			logger.debug("============= aVO.getGradeCnt() :  "+aVO.getGradeCnt());
			graVO.setGradeCnt(aVO.getGradeCnt());
			
			statusList.add(graVO);
			
		}
		
	    model.addAttribute("statusList", statusList);
		
		
		List <GradeVO> sendList = gradeService.listGradeSendList(gradeVO);
		model.addAttribute("sendList", sendList);
		
		
  		// View호출
		return "oklms/lu/grade/listGradeCdp";
	}
	
	// 학과전담자 - 성적조회
	@RequestMapping(value="/lu/grade/popupGradeCdp.do")
	public String popupGradeCdp(@ModelAttribute("frmGradePop") AunuriLinkSubjectGradeVO aunuriLinkSubjectGradeVO, ModelMap model, HttpServletRequest request) throws Exception {
		
		logger.debug("#### URL = /lu/grade/popupGradeCdp.do" );
		
		
		// 콤마로 넘어온 값 배열로 세팅
		aunuriLinkSubjectGradeVO.setGroupMemIdArr(aunuriLinkSubjectGradeVO.getGroupMemIds().split(","));
		
		// IN 조건에 사용하기 위해 문자열 변환 후 쿼테이션 세팅
		aunuriLinkSubjectGradeVO.setMemIds(CommonUtil.toArrStr(aunuriLinkSubjectGradeVO.getGroupMemIdArr()));
		
		List <AunuriLinkSubjectGradeVO> gradeList = aunuriLinkService.listSubectGrade(aunuriLinkSubjectGradeVO);
	    model.addAttribute("gradeList", gradeList);
		
  		// View호출
		return "oklms_popup/lu/grade/popupSubjectGradeList";
	}
	
	
	// 학과전담자 - 성적전송
	@RequestMapping(value="/lu/grade/insertGradeCdpSend.do")
	public String insertSendGradeCdp(@ModelAttribute("frmGrade") GradeVO gradeVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		
		logger.debug("#### URL = /lu/grade/insertGradeCdpSend.do" );
		
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(gradeVO); // session의 정보를 VO에 추가.
		
		String retMsg = "";
		
		int iResult = gradeService.insertGradeCdpSend(gradeVO);
		
		if( iResult > 0){
			retMsg = "정상적으로 (저장)처리되었습니다.";
		} else {
			retMsg = "처리된건이 없습니다.";
		}
		
		redirectAttributes.addFlashAttribute("frmGrade", gradeVO);
		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		
		return "redirect:/lu/grade/listGradeCdp.do";
		
	}
	
	
	
	// HRD 전담자 - 성적조회
	@RequestMapping(value="/lu/grade/listGradeCcm.do")
	public String listGradeCcm(@ModelAttribute("frmGrade") GradeVO gradeVO, ModelMap model, HttpServletRequest request) throws Exception {
		
		logger.debug("#### URL = /lu/grade/listGradeCcm.do" );
		
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(gradeVO); // session의 정보를 VO에 추가.
		
		// 학과코드,학과명 코드 리스트
		CommonCodeVO codeVO = new  CommonCodeVO();
		codeVO.setCodeGroup("DEPT_CD");
		List<CommonCodeVO> deptCodeList = commonCodeService.selectCmmCodeList(codeVO); // 학과
		model.addAttribute("deptCodeList", deptCodeList);

		if( !"".equals(gradeVO.getYyyy()) ){
			
			// 담당 회사에 전송 된 학습근로자 아이디를 가져옴
			String gradeGroupMemIds = gradeService.getGradeGroupMemIds(gradeVO);
			logger.debug("============================ gradeGroupMemIds : "+gradeGroupMemIds);	
			// 콤마로 넘어온 값 배열로 세팅
			if( null != gradeGroupMemIds ){
				AunuriLinkSubjectGradeVO aunuriLinkSubjectGradeVO = new AunuriLinkSubjectGradeVO();
				aunuriLinkSubjectGradeVO.setGroupMemIdArr(gradeGroupMemIds.split(","));
						
				// IN 조건에 사용하기 위해 문자열 변환 후 쿼테이션 세팅
				aunuriLinkSubjectGradeVO.setMemIds(CommonUtil.toArrStr(aunuriLinkSubjectGradeVO.getGroupMemIdArr()));
				aunuriLinkSubjectGradeVO.setYy(gradeVO.getYyyy());	
				aunuriLinkSubjectGradeVO.setSemstrCd(gradeVO.getTerm());
				
				// 아우누리 연계DB 에서 성적데이터를 가져옴
				List <AunuriLinkSubjectGradeVO> gradeList = aunuriLinkService.listSubectGrade(aunuriLinkSubjectGradeVO);
			    model.addAttribute("gradeList", gradeList);
			    model.addAttribute("gradeVO", gradeVO);
			    
			    // 수신확인 여부를 가져옴
			    GradeVO confirmVO = gradeService.getGradeCcmConfirmInfo(gradeVO); 
				
				model.addAttribute("confirmVO", confirmVO);
			}
			
			// 수신 확인 리스트
			List <GradeVO> confirmList = gradeService.listGradeConfirmList(gradeVO);
			model.addAttribute("confirmList", confirmList);
		}
		
		
  		// View호출
		return "oklms/lu/grade/listGradeCcm";
	}
	
	// HRD 전담자 - 엑셀 다운로드
	@RequestMapping(value = "/lu/grade/excelGradeCcm.do")
	public String excelGradeCcm(@ModelAttribute("frmGrade") GradeVO gradeVO , ModelMap model , HttpServletRequest request ) throws Exception {

		logger.debug("#### URL = /lu/grade/excelGradeCcm.do" );
		
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(gradeVO); // session의 정보를 VO에 추가.
		
		// 담당 회사에 전송 된 학습근로자 아이디를 가져옴
		String gradeGroupMemIds = gradeService.getGradeGroupMemIds(gradeVO);
		AunuriLinkSubjectGradeVO aunuriLinkSubjectGradeVO = new AunuriLinkSubjectGradeVO();
		aunuriLinkSubjectGradeVO.setGroupMemIdArr(gradeGroupMemIds.split(","));
				
		// IN 조건에 사용하기 위해 문자열 변환 후 쿼테이션 세팅
		aunuriLinkSubjectGradeVO.setMemIds(CommonUtil.toArrStr(aunuriLinkSubjectGradeVO.getGroupMemIdArr()));
		aunuriLinkSubjectGradeVO.setYy(gradeVO.getYyyy());	
		aunuriLinkSubjectGradeVO.setSemstrCd(gradeVO.getTerm());
		
		// 아우누리 연계DB 에서 성적데이터를 가져옴
		List <AunuriLinkSubjectGradeVO> gradeList = aunuriLinkService.listSubectGrade(aunuriLinkSubjectGradeVO);
	    model.addAttribute("gradeList", gradeList);
			    

		request.setAttribute("ExcelName", URLEncoder.encode( "성적수신내역".replaceAll(" ", "_") ,"UTF-8") );
 
		return "oklms_excel/lu/grade/excelSubjectGrade";

	}
  	 
	
	// HRD 전담자 - 성적확인
	@RequestMapping(value="/lu/grade/updateGradeCcmConfirmY.json")
	public @ResponseBody Map<String, Object> updateGradeCcmConfirmY(@ModelAttribute("frmGrade") GradeVO gradeVO,RedirectAttributes redirectAttributes
			,HttpServletRequest request
			,ModelMap model) throws Exception {
		logger.debug("#### URL = /lu/grade/updateGradeCcmConfirmY.do" );
		
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(gradeVO); // session의 정보를 VO에 추가.
		
		int iResult = 0;
		
		logger.debug("============ gradeVO.getConfirmYn() : "+gradeVO.getConfirmYn() );
		
		// 확인여부 저장
		iResult = gradeService.updateGradeCcmConfirmY(gradeVO);
		logger.debug("##### getGradeCcmSubmitInfo Cnt : "+iResult );
		
		Map<String , Object> returnMap = new HashMap<String , Object>();
		
		GradeVO data  = gradeService.getGradeCcmSubmitInfo(gradeVO); 
		
		logger.debug("============ data.getConfirmYn() : "+data.getConfirmYn() );
		
		returnMap.put("retData", data);
		
		return returnMap;
		
	}
}
