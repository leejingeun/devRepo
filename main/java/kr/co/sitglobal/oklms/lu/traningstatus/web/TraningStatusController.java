package kr.co.sitglobal.oklms.lu.traningstatus.web;

import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.online.service.OnlineTraningService;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningSchVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningVO;
import kr.co.sitglobal.oklms.lu.report.service.ReportService;
import kr.co.sitglobal.oklms.lu.traning.service.TraningNoteSerivce;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningNoteVO;
import kr.co.sitglobal.oklms.lu.traningstatus.service.TraningStatusService;
import kr.co.sitglobal.oklms.lu.traningstatus.vo.TraningStatusVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.Globals;


@Controller
public class TraningStatusController  extends BaseController{

	private static final Logger LOG = LoggerFactory.getLogger(TraningStatusController.class);
	
	@Resource(name = "ReportService")
	private ReportService reportService;
	
	@Resource(name = "OnlineTraningService")
	private OnlineTraningService onlineTraningService;
	
	@Resource(name = "TraningStatusService")
	private TraningStatusService traningStatusService;

	@Resource(name = "traningNoteSerivce")
	private TraningNoteSerivce traningNoteSerivce;
	
	//학습근로자 관리 (교수)
  	@RequestMapping(value = "/lu/traningstatus/listTraningstatusPrt.do")
	public String listTraningstatusPrt(@ModelAttribute("frmTraningstatus") TraningStatusVO traningStatusVO, ModelMap model, HttpServletRequest request) throws Exception {
  		
  		LOG.debug("#### URL = /lu/traningstatus/listTraningstatusPrt.do" );
		String retMsg = ""; 
  		HttpSession httpSession = request.getSession(); 
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(traningStatusVO); // session의 정보를 VO에 추가.
		 
		traningStatusVO.setYyyy((String)httpSession.getAttribute(Globals.YYYY));
		traningStatusVO.setTerm((String)httpSession.getAttribute(Globals.TERM));
		traningStatusVO.setSubjectCode((String)httpSession.getAttribute(Globals.SUBJECT_CODE));
		
		
		String traningType= (String)httpSession.getAttribute(Globals.SUBJECT_TRANING_TYPE);
		
		if( traningType.equals("OJT")){
			traningStatusVO.setClassId("");
		}else{
			traningStatusVO.setClassId((String)httpSession.getAttribute(Globals.CLASS));
		}
		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy(traningStatusVO.getYyyy());
		currProcVO.setTerm(traningStatusVO.getTerm());
		currProcVO.setSubjectCode(traningStatusVO.getSubjectCode());		
		currProcVO.setSubClass(traningStatusVO.getClassId());
		
		currProcVO = reportService.getCurrproc(currProcVO);  		
		model.addAttribute("currProcVO", currProcVO);

		// 출석정책조회
		OnlineTraningVO onlineTraningVO = new OnlineTraningVO();
		onlineTraningVO.setYyyy(traningStatusVO.getYyyy());
		onlineTraningVO.setTerm(traningStatusVO.getTerm());
		onlineTraningVO.setSubjectCode(traningStatusVO.getSubjectCode());
		onlineTraningVO.setSubjectClass(traningStatusVO.getClassId());
		
		onlineTraningVO = onlineTraningService.getOnlineTraningStand(onlineTraningVO);
		model.addAttribute("onlineTraningVO", onlineTraningVO);
		//권장진도율 조회
		TraningStatusVO getProgress = traningStatusService.getProgress(traningStatusVO);
		model.addAttribute("getProgress", getProgress);
		
		//전체조회 목록
		List<TraningStatusVO> resultlist = traningStatusService.listTraningStatus(traningStatusVO);
		model.addAttribute("resultlist", resultlist);
				
 
		// 주차정보 
		List<OnlineTraningSchVO> onlineTraningSchVO =reportService.listLmsSubjWeek(currProcVO);
		model.addAttribute("onlineTraningSchVO", onlineTraningSchVO);
		
		if( httpSession==null || traningStatusVO.getSubjectCode()==null ||traningStatusVO.getSubjectCode().equals("")  ){
			retMsg = "교과정보가 없습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
		}
		 
		
		// 주차정보없을때 첫번째주차 정보입력
		if(traningStatusVO.getWeekCnt()==null||traningStatusVO.getWeekCnt().equals("")){
			// 최근주차정보조회
			TraningNoteVO  traningNowWeekCn = traningNoteSerivce.getTraningNowWeekCnt(traningStatusVO);	
			if(traningNowWeekCn.getWeekCnt()==null||traningNowWeekCn.getWeekCnt().equals("")){
				traningStatusVO.setWeekCnt("1");
			}else{
				traningStatusVO.setWeekCnt(traningNowWeekCn.getWeekCnt());
			}
		}

		//상세조회 목록
		List<TraningStatusVO> resultBottomlist = traningStatusService.listTraningStatusDetail(traningStatusVO);
		model.addAttribute("resultBottomlist", resultBottomlist);
		
		
		model.addAttribute("traningStatusVO", traningStatusVO);		
		
		if(traningStatusVO.getMode()==1){
			// 인쇄페이지
			if(currProcVO!=null && currProcVO.getSubjectTraningType().equals("OFF")){
	
	
				return "oklms_popup/lu/traningstatus/printTraningstatusPrtOff";			
			}else{
	
	
				return "oklms_popup/lu/traningstatus/printTraningstatusPrtOjt";
			}
	
		}else{
			// 조회페이지			
			if(currProcVO!=null && currProcVO.getSubjectTraningType().equals("OFF")){
	
	
				return "oklms/lu/traningstatus/listTraningstatusPrtOff";			
			}else{
	
	
				return "oklms/lu/traningstatus/listTraningstatusPrtOjt";
			}
		}
	}

	//학습근로자 관리 (교수) 출석부
  	@RequestMapping(value = "/lu/traningstatus/popupTraningstatusPrt.do")
	public String popupTraningstatusPrt(@ModelAttribute("frmTraningstatus") TraningStatusVO traningStatusVO, ModelMap model, HttpServletRequest request) throws Exception {
  		
  		LOG.debug("#### URL = /lu/traningstatus/popupTraningstatusPrt.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(traningStatusVO); // session의 정보를 VO에 추가.
		
		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy(traningStatusVO.getYyyy());
		currProcVO.setTerm(traningStatusVO.getTerm());
		currProcVO.setSubClass(traningStatusVO.getClassId());
		currProcVO.setSubjectCode(traningStatusVO.getSubjectCode());
		currProcVO = reportService.getCurrproc(currProcVO);  		
		model.addAttribute("currProcVO", currProcVO);
 
		//권장진도율 조회
		TraningStatusVO getProgress = traningStatusService.getProgress(traningStatusVO);
		model.addAttribute("getProgress", getProgress);
		
		if(currProcVO!=null && currProcVO.getSubjectTraningType().equals("OFF")){
			if(traningStatusVO.getSearchCondition().equals("offline") ){
	
				//전체조회 목록
				List<TraningStatusVO> resultlist = traningStatusService.popupAttendListOff(traningStatusVO);
				model.addAttribute("resultlist", resultlist);
						
				model.addAttribute("traningStatusVO", traningStatusVO);		
				
				return "oklms_popup/lu/traningstatus/popupAttendanceRecord";	
			}else{
	
				//전체조회 목록
				List<TraningStatusVO> resultlist = traningStatusService.popupAttendListOnlineOff(traningStatusVO);
				model.addAttribute("resultlist", resultlist);					 
						
				model.addAttribute("traningStatusVO", traningStatusVO);		
				
				return "oklms_popup/lu/traningstatus/popupOnlineAttendanceRecord";
				
			}
		}else{
			
			//회사정보
			List<TraningNoteVO> subjectNameList = traningNoteSerivce.listSubjcetName(traningStatusVO);
			model.put("subjectNameList", subjectNameList); 
				//전체조회 목록
				List<TraningStatusVO> resultlist = traningStatusService.popupAttendListOff(traningStatusVO);
				model.addAttribute("resultlist", resultlist);
						
				model.addAttribute("traningStatusVO", traningStatusVO);		
				
			return "oklms_popup/lu/traningstatus/popupAttendanceRecordOjt";	
		}			

	}

	//학습근로자 관리 (교수OFF) 출석부 엑셀다운로드
  	@RequestMapping(value = "/lu/traningstatus/excelTraningstatusPrt.do")
	public String excelTraningstatusPrt(@ModelAttribute("frmTraningstatus") TraningStatusVO traningStatusVO, ModelMap model, HttpServletRequest request) throws Exception {
  		
  		LOG.debug("#### URL = /lu/traningstatus/excelTraningstatusPrt.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(traningStatusVO); // session의 정보를 VO에 추가.
		 

		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy(traningStatusVO.getYyyy());
		currProcVO.setTerm(traningStatusVO.getTerm());
		currProcVO.setSubClass(traningStatusVO.getClassId());
		currProcVO.setSubjectCode(traningStatusVO.getSubjectCode());
		currProcVO = reportService.getCurrproc(currProcVO);  		
		model.addAttribute("currProcVO", currProcVO);
 
		//권장진도율 조회
		TraningStatusVO getProgress = traningStatusService.getProgress(traningStatusVO);
		model.addAttribute("getProgress", getProgress);
		
		
		request.setAttribute("ExcelName", URLEncoder.encode( "출석부".replaceAll(" ", "_") ,"UTF-8") );
        
		 
		if(traningStatusVO.getSearchCondition().equals("offline") ){

			//전체조회 목록
			List<TraningStatusVO> resultlist = traningStatusService.popupAttendListOff(traningStatusVO);
			model.addAttribute("resultlist", resultlist);
					
			model.addAttribute("traningStatusVO", traningStatusVO);		
			
		}else{

			//전체조회 목록
			List<TraningStatusVO> resultlist = traningStatusService.popupAttendListOnlineOff(traningStatusVO);
			model.addAttribute("resultlist", resultlist);					 
					
			model.addAttribute("traningStatusVO", traningStatusVO);		
			
			
		}
		return "oklms_excel/lu/traningstatus/excelAttendanceRecord";		

	}

	//학습근로자 관리 (기업현장교사)
  	@RequestMapping(value = "/lu/traningstatus/listTraningstatusCot.do")
	public String listTraningstatusCot(@ModelAttribute("frmTraningstatus") TraningStatusVO traningStatusVO, ModelMap model, HttpServletRequest request) throws Exception {
  		
  		LOG.debug("#### URL = /lu/traningstatus/listTraningstatusCot.do" );
		String retMsg = ""; 
  		HttpSession httpSession = request.getSession(); 
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(traningStatusVO); // session의 정보를 VO에 추가.

		traningStatusVO.setYyyy((String)httpSession.getAttribute(Globals.YYYY));
		traningStatusVO.setTerm((String)httpSession.getAttribute(Globals.TERM));
		traningStatusVO.setClassId((String)httpSession.getAttribute(Globals.CLASS));
		traningStatusVO.setSubjectCode((String)httpSession.getAttribute(Globals.SUBJECT_CODE));

		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy(traningStatusVO.getYyyy());
		currProcVO.setTerm(traningStatusVO.getTerm());
		currProcVO.setSubjectCode(traningStatusVO.getSubjectCode());		
		currProcVO.setSubClass(traningStatusVO.getClassId());

		currProcVO = reportService.getCurrproc(currProcVO);  		
		model.addAttribute("currProcVO", currProcVO);

		//권장진도율 조회
		TraningStatusVO getProgress = traningStatusService.getProgress(traningStatusVO);
		model.addAttribute("getProgress", getProgress);

		//전체조회 목록
		List<TraningStatusVO> resultlist = traningStatusService.listTraningStatus(traningStatusVO);
		model.addAttribute("resultlist", resultlist);

		// 주차정보 
		List<OnlineTraningSchVO> onlineTraningSchVO =reportService.listLmsSubjWeek(currProcVO);
		model.addAttribute("onlineTraningSchVO", onlineTraningSchVO);
		
		if( httpSession==null || traningStatusVO.getSubjectCode()==null ||traningStatusVO.getSubjectCode().equals("")  ){
			retMsg = "교과정보가 없습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
		}
		 
		
		// 주차정보없을때 첫번째주차 정보입력
		if(traningStatusVO.getWeekCnt()==null||traningStatusVO.getWeekCnt().equals("")){
			// 최근주차정보조회
			TraningNoteVO  traningNowWeekCn = traningNoteSerivce.getTraningNowWeekCnt(traningStatusVO);	
			if(traningNowWeekCn.getWeekCnt()==null||traningNowWeekCn.getWeekCnt().equals("")){
				traningStatusVO.setWeekCnt("1");
			}else{
				traningStatusVO.setWeekCnt(traningNowWeekCn.getWeekCnt());
			}
		}

		//상세조회 목록
		List<TraningStatusVO> resultBottomlist = traningStatusService.listTraningStatusDetail(traningStatusVO);
		model.addAttribute("resultBottomlist", resultBottomlist);
		
		
		model.addAttribute("traningStatusVO", traningStatusVO);		
		
		if(traningStatusVO.getMode()==1){
			// 인쇄페이지
			
				return "oklms_popup/lu/traningstatus/printTraningstatusPrtOjt";

		}else{
			// 조회페이지
			
				return "oklms/lu/traningstatus/listTraningstatusCotOjt";
		}
	}

	//학습근로자 관리 (기업현장교사 출석부)
  	@RequestMapping(value = "/lu/traningstatus/popupTraningstatusCot.do")
	public String popupTraningstatusCot(@ModelAttribute("frmTraningstatus") TraningStatusVO traningStatusVO, ModelMap model, HttpServletRequest request) throws Exception {
  		
  		LOG.debug("#### URL = /lu/traningstatus/popupTraningstatusCot.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(traningStatusVO); // session의 정보를 VO에 추가.
		
		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy(traningStatusVO.getYyyy());
		currProcVO.setTerm(traningStatusVO.getTerm());
		currProcVO.setSubClass(traningStatusVO.getClassId());
		currProcVO.setSubjectCode(traningStatusVO.getSubjectCode());
		currProcVO = reportService.getCurrproc(currProcVO);  		
		model.addAttribute("currProcVO", currProcVO);
 
		//권장진도율 조회
		TraningStatusVO getProgress = traningStatusService.getProgress(traningStatusVO);
		model.addAttribute("getProgress", getProgress);
		
		//전체조회 목록
		List<TraningStatusVO> resultlist = traningStatusService.popupAttendListOff(traningStatusVO);
		model.addAttribute("resultlist", resultlist);
				
		model.addAttribute("traningStatusVO", traningStatusVO);		
		
		return "oklms_popup/lu/traningstatus/popupAttendanceRecord";	
	}  	

	//학습근로자 관리 (학과전담자)
  	@RequestMapping(value = "/lu/traningstatus/listTraningstatusCdp.do")
	public String listTraningstatusCdp(@ModelAttribute("frmTraningstatus") TraningStatusVO traningStatusVO, ModelMap model, HttpServletRequest request) throws Exception {
  		
  		LOG.debug("#### URL = /lu/traningstatus/listTraningstatusCdp.do" );
		String retMsg = ""; 
  		HttpSession httpSession = request.getSession(); 
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(traningStatusVO); // session의 정보를 VO에 추가.

		traningStatusVO.setYyyy((String)httpSession.getAttribute(Globals.YYYY));
		traningStatusVO.setTerm((String)httpSession.getAttribute(Globals.TERM));
		traningStatusVO.setClassId((String)httpSession.getAttribute(Globals.CLASS));
		traningStatusVO.setSubjectCode((String)httpSession.getAttribute(Globals.SUBJECT_CODE));

		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy(traningStatusVO.getYyyy());
		currProcVO.setTerm(traningStatusVO.getTerm());
		currProcVO.setSubjectCode(traningStatusVO.getSubjectCode());		
		currProcVO.setSubClass(traningStatusVO.getClassId());

		currProcVO = reportService.getCurrproc(currProcVO);  		
		model.addAttribute("currProcVO", currProcVO);

		//권장진도율 조회
		TraningStatusVO getProgress = traningStatusService.getProgress(traningStatusVO);
		model.addAttribute("getProgress", getProgress);

		//전체조회 목록
		List<TraningStatusVO> resultlist = traningStatusService.listTraningStatus(traningStatusVO);
		model.addAttribute("resultlist", resultlist);

		// 주차정보 
		List<OnlineTraningSchVO> onlineTraningSchVO =reportService.listLmsSubjWeek(currProcVO);
		model.addAttribute("onlineTraningSchVO", onlineTraningSchVO);
		
		if( httpSession==null || traningStatusVO.getSubjectCode()==null ||traningStatusVO.getSubjectCode().equals("")  ){
			retMsg = "교과정보가 없습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
		}
		 
		
		// 주차정보없을때 첫번째주차 정보입력
		if(traningStatusVO.getWeekCnt()==null||traningStatusVO.getWeekCnt().equals("")){
			// 최근주차정보조회
			TraningNoteVO  traningNowWeekCn = traningNoteSerivce.getTraningNowWeekCnt(traningStatusVO);	
			if(traningNowWeekCn.getWeekCnt()==null||traningNowWeekCn.getWeekCnt().equals("")){
				traningStatusVO.setWeekCnt("1");
			}else{
				traningStatusVO.setWeekCnt(traningNowWeekCn.getWeekCnt());
			}
		}

		//상세조회 목록
		List<TraningStatusVO> resultBottomlist = traningStatusService.listTraningStatusDetail(traningStatusVO);
		model.addAttribute("resultBottomlist", resultBottomlist);
		
		
		model.addAttribute("traningStatusVO", traningStatusVO);		
		
		if(traningStatusVO.getMode()==1){
			// 인쇄페이지
			
				return "oklms_popup/lu/traningstatus/printTraningstatusCdpOff";

		}else{
			// 조회페이지
			
				return "oklms/lu/traningstatus/listTraningstatusCdpOff";
		}
	}  	
	//학습근로자 관리 (학과전담자 출석부)
  	@RequestMapping(value = "/lu/traningstatus/popupTraningstatusCdp.do")
	public String popupTraningstatusCdp(@ModelAttribute("frmTraningstatus") TraningStatusVO traningStatusVO, ModelMap model, HttpServletRequest request) throws Exception {
  		
  		LOG.debug("#### URL = /lu/traningstatus/popupTraningstatusCdp.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(traningStatusVO); // session의 정보를 VO에 추가.
		
		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy(traningStatusVO.getYyyy());
		currProcVO.setTerm(traningStatusVO.getTerm());
		currProcVO.setSubClass(traningStatusVO.getClassId());
		currProcVO.setSubjectCode(traningStatusVO.getSubjectCode());
		currProcVO = reportService.getCurrproc(currProcVO);  		
		model.addAttribute("currProcVO", currProcVO);
 
		//권장진도율 조회
		TraningStatusVO getProgress = traningStatusService.getProgress(traningStatusVO);
		model.addAttribute("getProgress", getProgress);
		
		
		//전체조회 목록
		List<TraningStatusVO> resultlist = traningStatusService.popupAttendListOnlineOff(traningStatusVO);
		model.addAttribute("resultlist", resultlist);					 
				
		model.addAttribute("traningStatusVO", traningStatusVO);		
		 
		
		return "oklms_popup/lu/traningstatus/popupOnlineAttendanceRecordCdp";	
	}

	//학습근로자 관리(학과전담자 훈련현황) 엑셀다운로드
  	@RequestMapping(value = "/lu/traningstatus/excelTraningstatusCdp.do")
	public String excelTraningstatusCdp(@ModelAttribute("frmTraningstatus") TraningStatusVO traningStatusVO, ModelMap model, HttpServletRequest request) throws Exception {
  		
  		LOG.debug("#### URL = /lu/traningstatus/excelTraningstatusCdp.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(traningStatusVO); // session의 정보를 VO에 추가.

		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy(traningStatusVO.getYyyy());
		currProcVO.setTerm(traningStatusVO.getTerm());
		currProcVO.setSubjectCode(traningStatusVO.getSubjectCode());		
		currProcVO.setSubClass(traningStatusVO.getClassId());

		currProcVO = reportService.getCurrproc(currProcVO);  		
		model.addAttribute("currProcVO", currProcVO);

		//권장진도율 조회
		TraningStatusVO getProgress = traningStatusService.getProgress(traningStatusVO);
		model.addAttribute("getProgress", getProgress);

		//전체조회 목록
		List<TraningStatusVO> resultlist = traningStatusService.listTraningStatus(traningStatusVO);
		model.addAttribute("resultlist", resultlist);
  
		
		model.addAttribute("traningStatusVO", traningStatusVO);		
		
		
		request.setAttribute("ExcelName", URLEncoder.encode( "현황".replaceAll(" ", "_") ,"UTF-8") );
        
		  
		return "oklms_excel/lu/traningstatus/excelAttendanceRecordCdp";		

	}
}