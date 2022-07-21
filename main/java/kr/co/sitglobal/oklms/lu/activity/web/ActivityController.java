package kr.co.sitglobal.oklms.lu.activity.web;

import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.atchFile.vo.AtchFileVO;
import kr.co.sitglobal.oklms.commbiz.util.AtchFileUtil;
import kr.co.sitglobal.oklms.commbiz.util.BizUtil;
import kr.co.sitglobal.oklms.lu.activity.service.ActivityService;
import kr.co.sitglobal.oklms.lu.activity.vo.ActivityVO; 
import kr.co.sitglobal.oklms.lu.activity.vo.MemberVO;
import kr.co.sitglobal.oklms.lu.activity.vo.SubjweekStdVO;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.interview.service.InterviewService;
import kr.co.sitglobal.oklms.lu.interview.vo.InterviewCompanyVO; 
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningSchVO;
import kr.co.sitglobal.oklms.lu.report.service.ReportService; 
import kr.co.sitglobal.oklms.lu.traning.service.TraningNoteSerivce;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningNoteVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.service.Globals;

@Controller
public class ActivityController  extends BaseController{

	private static final Logger LOG = LoggerFactory.getLogger(ActivityController.class);
	
	@Resource(name = "ActivityService")
	private ActivityService activityService;
	
	@Resource(name = "ReportService")
	private ReportService reportService;
	
	@Resource(name = "InterviewService")
	private InterviewService interviewService;
	
	@Resource(name = "atchFileService")
	private AtchFileService atchFileService;

	@Resource(name = "atchFileUtil")
	private AtchFileUtil atchFileUtil;
	
	@Resource(name = "traningNoteSerivce")
	private TraningNoteSerivce traningNoteSerivce;

	//학습근로자
  	@RequestMapping(value = "/lu/activity/listActivityStd.do")
	public String listActivityStd(@ModelAttribute("frmActivity") ActivityVO activityVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/activity/listActivityStd.do" );
		String retMsg = "";
  		HttpSession httpSession = request.getSession(); 
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(activityVO); // session의 정보를 VO에 추가.
			
		activityVO.setYyyy(StringUtils.defaultIfBlank( activityVO.getYyyy() ,(String)httpSession.getAttribute(Globals.YYYY)));
		activityVO.setTerm(StringUtils.defaultIfBlank( activityVO.getTerm() ,(String)httpSession.getAttribute(Globals.TERM)));  
		activityVO.setClassId(StringUtils.defaultIfBlank( activityVO.getClassId() ,(String)httpSession.getAttribute(Globals.CLASS)));
		activityVO.setSubjectCode(StringUtils.defaultIfBlank( activityVO.getSubjectCode() ,(String)httpSession.getAttribute(Globals.SUBJECT_CODE)));

		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy(activityVO.getYyyy());
		currProcVO.setTerm(activityVO.getTerm());
		currProcVO.setSubClass(activityVO.getClassId());
		currProcVO.setSubjectCode(activityVO.getSubjectCode());
		currProcVO = reportService.getCurrproc(currProcVO);
		

		activityVO.setTraningType(currProcVO.getSubjectTraningType());
		
		// 주차정보 
		List<OnlineTraningSchVO> onlineTraningSchVO =reportService.listLmsSubjWeek(currProcVO);
		model.addAttribute("onlineTraningSchVO", onlineTraningSchVO);
		
		if( httpSession==null || activityVO.getSubjectCode()==null ||activityVO.getSubjectCode().equals("")  ){
			retMsg = "교과정보가 없습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
		}	
		// 주차정보없을때 첫번째주차 정보입력
		// 현재날자에 해당하는 주차 선택기능 추가 필요함
		if(activityVO.getWeekCnt()==null||activityVO.getWeekCnt().equals("")){
			TraningNoteVO  traningNoteVO = new TraningNoteVO();
			traningNoteVO.setYyyy(currProcVO.getYyyy());
			traningNoteVO.setTerm(currProcVO.getTerm());
			traningNoteVO.setSubjectCode(currProcVO.getSubjectCode());				
			traningNoteVO.setClassId(currProcVO.getSubClass());
		 
			if(traningNoteVO.getWeekCnt() == null|| traningNoteVO.getWeekCnt().equals("")){
				// 최근주차정보조회
				TraningNoteVO  traningNowWeekCn = traningNoteSerivce.getTraningNowWeekCnt(traningNoteVO);	
				if(traningNowWeekCn.getWeekCnt()==null||traningNowWeekCn.getWeekCnt().equals("")){
					activityVO.setWeekCnt("1");
				}else{ 
					activityVO.setWeekCnt(traningNowWeekCn.getWeekCnt());
				}
			} 

		}
		// 학습근로자
		activityVO.setMemId(activityVO.getSessionMemId());
		
		ActivityVO result = activityService.getActivity(activityVO); 
		List<ActivityVO> resultMember = activityService.getActivityMember(activityVO);
		
		SubjweekStdVO subjweekStdVO = new SubjweekStdVO ();
		subjweekStdVO.setYyyy(activityVO.getYyyy());
		subjweekStdVO.setTerm(activityVO.getTerm());
		subjweekStdVO.setSubjectCode(activityVO.getSubjectCode());
		subjweekStdVO.setClassId(activityVO.getClassId());
		subjweekStdVO.setWeekCnt(activityVO.getWeekCnt());		
		subjweekStdVO = activityService.getSubjWeek(subjweekStdVO);
		model.addAttribute("subjweekStdVO", subjweekStdVO);
		
		AtchFileVO atchFileVO = new AtchFileVO();
		if(result!=null){
			atchFileVO.setFileSn(1);
			atchFileVO.setAtchFileId(result.getAtchFileId());
			//첨부파일
			AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
		    model.addAttribute("resultFile", resultFile);     
			
		}
		model.addAttribute("currProcVO", currProcVO);
		model.addAttribute("activityVO", activityVO);
		model.addAttribute("result", result);
		model.addAttribute("resultMember", resultMember);
  		// View호출
		return "oklms/lu/activity/listActivityStd";
	}
  	@RequestMapping(value = "/lu/activity/insertActivityStd.do")
	public String insertActivityStd(@ModelAttribute("frmActivity")  ActivityVO activityVO, ModelMap model,RedirectAttributes redirectAttributes,final MultipartHttpServletRequest multiRequest) throws Exception {
		
  		LOG.debug("#### URL = /lu/activity/insertActivityStd.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(activityVO); // session의 정보를 VO에 추가.
		String retMsg="";
  		int insertCnt = activityService.insertActivity(activityVO,multiRequest);
		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);  		
		redirectAttributes.addFlashAttribute("frmActivity", activityVO);
		
		// View호출
  		return "redirect:/lu/activity/listActivityStd.do";
	}  	

  	// 주차별학습활동서
  	@RequestMapping(value = "/lu/activity/listWeekActivityStd.do")
	public String listWeekActivityStd(@ModelAttribute("frmActivity") ActivityVO activityVO, ModelMap model) throws Exception {
		
  		LOG.debug("#### URL = /lu/activity/listWeekActivityStd.do" ); 
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(activityVO); // session의 정보를 VO에 추가.
		
		if(activityVO.getYyyy()==null||activityVO.getYyyy().equals("")){
			activityVO.setYyyy(BizUtil.getCurrentDateString("yyyy"));	
			activityVO.setTerm("1");
		}
		
  		SubjweekStdVO subjweekStdVO = new SubjweekStdVO ();
		subjweekStdVO.setYyyy(activityVO.getYyyy());
		subjweekStdVO.setTerm(activityVO.getTerm());
		subjweekStdVO.setMemSeq(activityVO.getSessionMemSeq()); 
		
  		List<SubjweekStdVO>  resultlist = activityService.listWeekActivityStd(subjweekStdVO);
  		
  		 
		model.addAttribute("resultlist", resultlist );
		model.addAttribute("activityVO", activityVO);
  		// View호출
  		return "oklms/lu/activity/listWeekActivityStd";
  	}

  	@RequestMapping(value = "/lu/activity/getWeekActivityStd.do")
	public String getWeekActivityStd(@ModelAttribute("frmActivity") ActivityVO activityVO, ModelMap model) throws Exception {
		
  		LOG.debug("#### URL = /lu/activity/getWeekActivityStd.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(activityVO); // session의 정보를 VO에 추가.
  		
 		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
 		
  		interviewCompanyVO.setYyyy(activityVO.getYyyy());
  		interviewCompanyVO.setTerm(activityVO.getTerm());
  		interviewCompanyVO.setMemSeq(activityVO.getSessionMemSeq());
  		 
  		InterviewCompanyVO result=interviewService.InterviewCompanyMember(interviewCompanyVO);
  		if(result==null){
  			  result= new InterviewCompanyVO();	
  		}
  		SubjweekStdVO subjweekStdVO = new SubjweekStdVO ();
		subjweekStdVO.setYyyy(activityVO.getYyyy());
		subjweekStdVO.setTerm(activityVO.getTerm());
		subjweekStdVO.setCompanyId(result.getCompanyId());
		subjweekStdVO.setTraningProcessId(result.getTraningProcessId());
		subjweekStdVO.setTraningType(activityVO.getTraningType());
		subjweekStdVO.setWeekCnt(activityVO.getWeekCnt());
		
		subjweekStdVO.setMemSeq(activityVO.getSessionMemSeq());
 
  		List<SubjweekStdVO>  resultlist = activityService.selectActivityHrd(subjweekStdVO);
		subjweekStdVO = activityService.getSubjWeek(subjweekStdVO);
		model.addAttribute("subjweekStdVO", subjweekStdVO);  		

		model.addAttribute("result", result );
		model.addAttribute("resultlist", resultlist );
		model.addAttribute("activityVO", activityVO);
  		// View호출
  		return "oklms/lu/activity/getActivityStd";
  	}

  	@RequestMapping(value = "/lu/activity/updateWeekActivityStd.do")
	public String insertWeekActivityStd(@ModelAttribute("frmActivity")  ActivityVO activityVO, ModelMap model,RedirectAttributes redirectAttributes ) throws Exception {

  		LOG.debug("#### URL = /lu/activity/updateWeekActivityStd.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(activityVO); // session의 정보를 VO에 추가.
		// 제출기능
		activityVO.setState("C");
		String retMsg="";
  		int insertCnt = activityService.updateActivitySubmit(activityVO);
  		
		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);  		
		redirectAttributes.addFlashAttribute("frmActivity", activityVO);
		// View호출
  		return "redirect:/lu/activity/listWeekActivityStd.do";
	}
  	@RequestMapping(value = "/lu/activity/printWeekActivityStd.do")
	public String printWeekActivityStd(@ModelAttribute("frmActivity") ActivityVO activityVO, ModelMap model) throws Exception {

  		LOG.debug("#### URL = /lu/activity/printWeekActivityStd.do" );
  		 
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(activityVO); // session의 정보를 VO에 추가.
  		
 		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
 		
  		interviewCompanyVO.setYyyy(activityVO.getYyyy());
  		interviewCompanyVO.setTerm(activityVO.getTerm());
  		interviewCompanyVO.setMemSeq(activityVO.getSessionMemSeq());
  		 
  		InterviewCompanyVO result=interviewService.InterviewCompanyMember(interviewCompanyVO);
  		if(result==null){
  			  result= new InterviewCompanyVO();	
  		}
  		SubjweekStdVO subjweekStdVO = new SubjweekStdVO ();
		subjweekStdVO.setYyyy(activityVO.getYyyy());
		subjweekStdVO.setTerm(activityVO.getTerm());
		subjweekStdVO.setCompanyId(result.getCompanyId());
		subjweekStdVO.setTraningProcessId(result.getTraningProcessId());
		subjweekStdVO.setTraningType(activityVO.getTraningType());
		subjweekStdVO.setWeekCnt(activityVO.getWeekCnt());
		
		subjweekStdVO.setMemSeq(activityVO.getSessionMemSeq());
 
  		List<SubjweekStdVO>  resultlist = activityService.selectActivityHrd(subjweekStdVO);
		subjweekStdVO = activityService.getSubjWeek(subjweekStdVO);
		model.addAttribute("subjweekStdVO", subjweekStdVO);  		

		model.addAttribute("result", result );
		model.addAttribute("resultlist", resultlist );
		model.addAttribute("activityVO", activityVO);
  		  
  		/*
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(activityVO); // session의 정보를 VO에 추가.
		
  		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
  		interviewCompanyVO.setCompanyId(activityVO.getCompanyId());
  		interviewCompanyVO.setTraningProcessId(activityVO.getTraningProcessId());

  		InterviewCompanyVO result=interviewService.InterviewCompany(interviewCompanyVO);

  		SubjweekStdVO subjweekStdVO = new SubjweekStdVO ();
		subjweekStdVO.setYyyy(activityVO.getYyyy());
		subjweekStdVO.setTerm(activityVO.getTerm());
		subjweekStdVO.setCompanyId(activityVO.getCompanyId());
		subjweekStdVO.setTraningProcessId(activityVO.getTraningProcessId());

  		List<SubjweekStdVO>  resultlist = activityService.listActivityHrd(subjweekStdVO);

		model.addAttribute("result", result );
		model.addAttribute("resultlist", resultlist );
		model.addAttribute("activityVO", activityVO);
		*/
  		// View호출
  		return "oklms_popup/lu/activity/listActivityStdPrint";
  	}

	// 담당교수
  	@RequestMapping(value = "/lu/activity/listActivityPrt.do")
	public String listActivityPrt(@ModelAttribute("frmActivity") ActivityVO activityVO, ModelMap model, HttpServletRequest request) throws Exception {
  		LOG.debug("#### URL = /lu/activity/listActivityPrt.do" );
  		
  		String classId_temp="";
		String retMsg = "";
  		HttpSession httpSession = request.getSession(); 
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(activityVO); // session의 정보를 VO에 추가. 

		classId_temp =activityVO.getClassId();
		
		activityVO.setYyyy(StringUtils.defaultIfBlank( activityVO.getYyyy() ,(String)httpSession.getAttribute(Globals.YYYY)));
		activityVO.setTerm(StringUtils.defaultIfBlank( activityVO.getTerm() ,(String)httpSession.getAttribute(Globals.TERM)));  
		activityVO.setClassId(StringUtils.defaultIfBlank( activityVO.getClassId() ,(String)httpSession.getAttribute(Globals.CLASS)));
		activityVO.setSubjectCode(StringUtils.defaultIfBlank( activityVO.getSubjectCode() ,(String)httpSession.getAttribute(Globals.SUBJECT_CODE)));
 

		httpSession.setAttribute(Globals.YYYY, activityVO.getYyyy());
		httpSession.setAttribute(Globals.TERM, activityVO.getTerm());
		httpSession.setAttribute(Globals.CLASS, activityVO.getClassId());
		httpSession.setAttribute(Globals.SUBJECT_CODE, activityVO.getSubjectCode());
		
		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy(activityVO.getYyyy());
		currProcVO.setTerm(activityVO.getTerm());
		currProcVO.setSubClass(activityVO.getClassId());
		currProcVO.setSubjectCode(activityVO.getSubjectCode()); 
		currProcVO = reportService.getCurrproc(currProcVO);
		
		if(currProcVO!=null && currProcVO.getSubjectTraningType().equals("OFF")){
// OFF 과정			
		activityVO.setTraningType(currProcVO.getSubjectTraningType());
		
		// 주차정보 
		List<OnlineTraningSchVO> onlineTraningSchVO =reportService.listLmsSubjWeek(currProcVO);
		model.addAttribute("onlineTraningSchVO", onlineTraningSchVO);
		
		if( httpSession==null || activityVO.getSubjectCode()==null ||activityVO.getSubjectCode().equals("")  ){
			retMsg = "교과정보가 없습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
		}	
		// 주차정보없을때 첫번째주차 정보입력
		// 현재날자에 해당하는 주차 선택기능 추가 필요함
		if(activityVO.getWeekCnt()==null||activityVO.getWeekCnt().equals("")){
			TraningNoteVO  traningNoteVO = new TraningNoteVO();
			traningNoteVO.setYyyy(currProcVO.getYyyy());
			traningNoteVO.setTerm(currProcVO.getTerm());
			traningNoteVO.setSubjectCode(currProcVO.getSubjectCode());				
			traningNoteVO.setClassId(currProcVO.getSubClass());
		 
			if(traningNoteVO.getWeekCnt() == null|| traningNoteVO.getWeekCnt().equals("")){
				// 최근주차정보조회
				TraningNoteVO  traningNowWeekCn = traningNoteSerivce.getTraningNowWeekCnt(traningNoteVO);	
				if(traningNowWeekCn.getWeekCnt()==null||traningNowWeekCn.getWeekCnt().equals("")){
					activityVO.setWeekCnt("1");
				}else{ 
					activityVO.setWeekCnt(traningNowWeekCn.getWeekCnt());
				}
			} 

		}
		
		SubjweekStdVO subjweekStdVO = new SubjweekStdVO ();
		subjweekStdVO.setYyyy(activityVO.getYyyy());
		subjweekStdVO.setTerm(activityVO.getTerm());
		subjweekStdVO.setSubjectCode(activityVO.getSubjectCode());
		subjweekStdVO.setClassId(activityVO.getClassId());
		subjweekStdVO.setWeekCnt(activityVO.getWeekCnt());		
		subjweekStdVO = activityService.getSubjWeek(subjweekStdVO);
		model.addAttribute("subjweekStdVO", subjweekStdVO);
		model.addAttribute("currProcVO", currProcVO);
		model.addAttribute("activityVO", activityVO);
		
		//model.addAttribute("result", result);
  		
		//학습근로자 목록 
		MemberVO memberVO = new MemberVO();
		memberVO.setYyyy(activityVO.getYyyy());
		memberVO.setTerm(activityVO.getTerm());
		memberVO.setSubjectCode(activityVO.getSubjectCode());
		memberVO.setClassId(activityVO.getClassId());
		memberVO.setWeekCnt(activityVO.getWeekCnt());
		memberVO.setSearchName(activityVO.getSearchName());
		
		
		List<MemberVO> memberlist = activityService.listActivityMember(memberVO);
		model.addAttribute("memberlist", memberlist);
		
	 
  			// View호출
	  		return "oklms/lu/activity/listActivityPrtOff";	
	  		
		}else{
// OJT과정			
			// 반정보가 있을때			
			if(classId_temp!=null&&!classId_temp.equals("")){
				activityVO.setClassId(classId_temp);
			}
			activityVO.setTraningType("OJT");

			// 주차정보 
			List<OnlineTraningSchVO> onlineTraningSchVO =reportService.listLmsSubjWeek(currProcVO);
			model.addAttribute("onlineTraningSchVO", onlineTraningSchVO);
			
			if( httpSession==null || activityVO.getSubjectCode()==null ||activityVO.getSubjectCode().equals("")  ){
				retMsg = "교과정보가 없습니다.";
				retMsg = URLEncoder.encode( retMsg ,"UTF-8");
				logger.debug("#### retMsg=" + retMsg );
				return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
			}	
			// 주차정보없을때 첫번째주차 정보입력
			// 현재날자에 해당하는 주차 선택기능 추가 필요함
			if(activityVO.getWeekCnt()==null||activityVO.getWeekCnt().equals("")){
				TraningNoteVO  traningNoteVO = new TraningNoteVO();
				traningNoteVO.setYyyy(activityVO.getYyyy());
				traningNoteVO.setTerm(activityVO.getTerm());
				traningNoteVO.setSubjectCode(activityVO.getSubjectCode());				
				traningNoteVO.setClassId(activityVO.getClassId());
			 
				if(traningNoteVO.getWeekCnt() == null|| traningNoteVO.getWeekCnt().equals("")){
					// 최근주차정보조회
					TraningNoteVO  traningNowWeekCn = traningNoteSerivce.getTraningNowWeekCnt(traningNoteVO);	
					if(traningNowWeekCn.getWeekCnt()==null||traningNowWeekCn.getWeekCnt().equals("")){
						activityVO.setWeekCnt("1");
					}else{ 
						activityVO.setWeekCnt(traningNowWeekCn.getWeekCnt());
					}
				} 
			}

			//학습근로자 목록 
			MemberVO memberVO = new MemberVO();
			memberVO.setYyyy(activityVO.getYyyy());
			memberVO.setTerm(activityVO.getTerm());
			memberVO.setSubjectCode(activityVO.getSubjectCode());			
			memberVO.setWeekCnt(activityVO.getWeekCnt());
			memberVO.setSearchCompanyName(activityVO.getSearchCompanyName());
			loginInfo.putSessionToVo(memberVO); // session의 정보를 VO에 추가. 
			
			//기업목록
			List<MemberVO> companylist = activityService.listActivityCompany(memberVO);
			model.addAttribute("companylist", companylist);
			
			if(companylist!=null && companylist.size()>0){
				if(classId_temp==null|| classId_temp.equals("")){					
					activityVO.setClassId(companylist.get(0).getClassId());
				}
			}
			memberVO.setClassId(activityVO.getClassId());
			
			memberVO.setSearchName(activityVO.getSearchName());
			
			List<MemberVO> memberlist = activityService.listActivityMember(memberVO);
			model.addAttribute("memberlist", memberlist);
						
			SubjweekStdVO subjweekStdVO = new SubjweekStdVO ();
			subjweekStdVO.setYyyy(activityVO.getYyyy());
			subjweekStdVO.setTerm(activityVO.getTerm());
			subjweekStdVO.setSubjectCode(activityVO.getSubjectCode());
			subjweekStdVO.setClassId(activityVO.getClassId());
			subjweekStdVO.setWeekCnt(activityVO.getWeekCnt());		
			subjweekStdVO = activityService.getSubjWeek(subjweekStdVO);
			
			
			model.addAttribute("subjweekStdVO", subjweekStdVO);
			model.addAttribute("currProcVO", currProcVO);
			model.addAttribute("activityVO", activityVO);
			 			
			return "oklms/lu/activity/listActivityPrt";
		}
  		
  	}
	// 과제물 다운로드
  	@RequestMapping(value = "/lu/activity/downloadActivityPrt.do")
	public ModelAndView downloadActivityPrt(@ModelAttribute("frmActivity") MemberVO memberVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/activity/downloadActivityPrt.do" );
  		ModelAndView mav = new ModelAndView("zipdownloadView");
  		HttpSession httpSession = request.getSession(); 
  		String retMsg = "";
		//다운로드 학습근로자 목록 
		//학습근로자 목록   
		List<MemberVO> memberlist = activityService.listActivityMember(memberVO);
		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy((String)httpSession.getAttribute(Globals.YYYY));
		currProcVO.setTerm((String)httpSession.getAttribute(Globals.TERM));
		currProcVO.setSubClass((String)httpSession.getAttribute(Globals.CLASS));
		currProcVO.setSubjectCode((String)httpSession.getAttribute(Globals.SUBJECT_CODE));
		currProcVO = reportService.getCurrproc(currProcVO);
		
	    String fileStr = "";
        String oldFileStr = "";
        
		for(int a=0;memberVO.getMemSeqs().length>a ;a++){
	        String atchFileId = "";
	        String memId = "";
	        String memName = "";
			String [] tempMemSeq = memberVO.getMemSeqs()[a].split(",");
			if(tempMemSeq!=null && tempMemSeq.length>1){
				memId = tempMemSeq[0];
				for(int b=0;memberlist.size()>b ;b++){
					if(memId.equals(memberlist.get(b).getMemId())){
						memName = memberlist.get(b).getMemName();
						break;
					}
				}
				
				atchFileId =tempMemSeq[1];
				
				if(atchFileId!=null){
					AtchFileVO atchFileVO1 = new AtchFileVO();
					atchFileVO1.setFileSn(1);
					atchFileVO1.setAtchFileId(atchFileId);
					if(atchFileId!=null&&!atchFileId.equals("")){
						AtchFileVO resultfile = atchFileService.getAtchFile(atchFileVO1);			
						//fileStr += resultfile.getOrgFileName()+"?";
			            fileStr += memId+"_"+memName+"."+resultfile.getFileExtn() + "?";						
			            oldFileStr += resultfile.getFileSavePath()+"/"+resultfile.getSaveFileName()+"?";
					}
				}
			}


		}

		if(fileStr != null && !fileStr.equals("")) {
            mav.addObject("fileStr", fileStr);
            mav.addObject("oldFileStr", oldFileStr);
            
            mav.addObject("reNameStr",currProcVO.getYyyy()+"_"+currProcVO.getTerm()+"_"+currProcVO.getSubjectName()+"_"+currProcVO.getSubClass()+".zip");
            //다운로드 완료 정보 수정
		}else{
			retMsg = "선택한 과제가 없습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			mav.setViewName("redirect:/lu/activity/listActivityPrt.do?retMsgEncode="+ retMsg);
		}
  		// View호출
  		return mav;
  	} 
  	
  	
  	// HRD담당자
  	@RequestMapping(value = "/lu/activity/listActivityHrd.do")
	public String listActivityHrd(@ModelAttribute("frmActivity") ActivityVO activityVO, ModelMap model) throws Exception {
		
  		LOG.debug("#### URL = /lu/activity/listActivityHrd.do" );
  		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
  		interviewCompanyVO.setCompanyId(activityVO.getCompanyId());
  		interviewCompanyVO.setTraningProcessId(activityVO.getTraningProcessId());
  		 
  		InterviewCompanyVO result=interviewService.InterviewCompany(interviewCompanyVO);
  		
  		SubjweekStdVO subjweekStdVO = new SubjweekStdVO ();
		subjweekStdVO.setYyyy(activityVO.getYyyy());
		subjweekStdVO.setTerm(activityVO.getTerm());
		subjweekStdVO.setCompanyId(activityVO.getCompanyId());
		subjweekStdVO.setTraningProcessId(activityVO.getTraningProcessId());
		
  		List<SubjweekStdVO>  resultlist = activityService.listActivityHrd(subjweekStdVO);
  		
  		
		model.addAttribute("result", result );
		model.addAttribute("resultlist", resultlist );
		model.addAttribute("activityVO", activityVO);
  		// View호출
  		return "oklms/lu/activity/listActivityHrd";
  	}
  	@RequestMapping(value = "/lu/activity/listActivityHrdExcel.do")
	public String listActivityHrdExcel(@ModelAttribute("frmActivity") ActivityVO activityVO, ModelMap model , HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/activity/listActivityHrdExcel.do" );
  		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
  		interviewCompanyVO.setCompanyId(activityVO.getCompanyId());
  		interviewCompanyVO.setTraningProcessId(activityVO.getTraningProcessId());
  		 
  		InterviewCompanyVO result=interviewService.InterviewCompany(interviewCompanyVO);
  		
  		SubjweekStdVO subjweekStdVO = new SubjweekStdVO ();
		subjweekStdVO.setYyyy(activityVO.getYyyy());
		subjweekStdVO.setTerm(activityVO.getTerm());
		subjweekStdVO.setCompanyId(activityVO.getCompanyId());
		subjweekStdVO.setTraningProcessId(activityVO.getTraningProcessId());
		
  		List<SubjweekStdVO>  resultlist = activityService.listActivityHrd(subjweekStdVO);
  		
		request.setAttribute("ExcelName", URLEncoder.encode( "학습활동서현황".replaceAll(" ", "_") ,"UTF-8") );
        
		model.addAttribute("result", result );
		model.addAttribute("resultlist", resultlist );
		model.addAttribute("activityVO", activityVO);
  		// View호출
  		return "oklms_excel/lu/activity/listActivityHrdExcel";
  	}
  	@RequestMapping(value = "/lu/activity/printActivityHrd.do")
	public String printActivityHrd(@ModelAttribute("frmActivity") ActivityVO activityVO, ModelMap model) throws Exception {
		
  		LOG.debug("#### URL = /lu/activity/printActivityHrd.do" );
  		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
  		interviewCompanyVO.setCompanyId(activityVO.getCompanyId());
  		interviewCompanyVO.setTraningProcessId(activityVO.getTraningProcessId());

  		InterviewCompanyVO result=interviewService.InterviewCompany(interviewCompanyVO);

  		SubjweekStdVO subjweekStdVO = new SubjweekStdVO ();
		subjweekStdVO.setYyyy(activityVO.getYyyy());
		subjweekStdVO.setTerm(activityVO.getTerm());
		subjweekStdVO.setCompanyId(activityVO.getCompanyId());
		subjweekStdVO.setTraningProcessId(activityVO.getTraningProcessId());

  		List<SubjweekStdVO>  resultlist = activityService.listActivityHrd(subjweekStdVO);

		model.addAttribute("result", result );
		model.addAttribute("resultlist", resultlist );
		model.addAttribute("activityVO", activityVO);
  		// View호출
  		return "oklms_popup/lu/activity/listActivityHrdPrint";
  	}
  	@RequestMapping(value = "/lu/activity/selectActivityHrd.do")
	public String selectActivityHrd(@ModelAttribute("frmActivity") ActivityVO activityVO, ModelMap model) throws Exception {
		
  		LOG.debug("#### URL = /lu/activity/selectActivityHrd.do" );
  		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
  		interviewCompanyVO.setCompanyId(activityVO.getCompanyId());
  		interviewCompanyVO.setTraningProcessId(activityVO.getTraningProcessId());
  		 
  		InterviewCompanyVO result=interviewService.InterviewCompany(interviewCompanyVO);
  		
  		SubjweekStdVO subjweekStdVO = new SubjweekStdVO ();
		subjweekStdVO.setYyyy(activityVO.getYyyy());
		subjweekStdVO.setTerm(activityVO.getTerm());
		subjweekStdVO.setCompanyId(activityVO.getCompanyId());
		subjweekStdVO.setTraningProcessId(activityVO.getTraningProcessId());
		subjweekStdVO.setTraningType(activityVO.getTraningType());
		subjweekStdVO.setWeekCnt(activityVO.getWeekCnt());
  		List<SubjweekStdVO>  resultlist = activityService.selectActivityHrd(subjweekStdVO);
  		
  		
		model.addAttribute("result", result );
		model.addAttribute("resultlist", resultlist );
		model.addAttribute("activityVO", activityVO);
  		// View호출
  		return "oklms/lu/activity/selectActivityHrd";
  	}
  	
  	@RequestMapping(value = "/lu/activity/getActivityHrd.do")
	public String getActivityHrd(@ModelAttribute("frmActivity") ActivityVO activityVO, ModelMap model) throws Exception {
		
  		LOG.debug("#### URL = /lu/activity/getActivityHrd.do" );
 		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
  		interviewCompanyVO.setCompanyId(activityVO.getCompanyId());
  		interviewCompanyVO.setTraningProcessId(activityVO.getTraningProcessId());
  		 
  		InterviewCompanyVO result=interviewService.InterviewCompany(interviewCompanyVO);
  		
  		SubjweekStdVO subjweekStdVO = new SubjweekStdVO ();
		subjweekStdVO.setYyyy(activityVO.getYyyy());
		subjweekStdVO.setTerm(activityVO.getTerm());
		subjweekStdVO.setCompanyId(activityVO.getCompanyId());
		subjweekStdVO.setTraningProcessId(activityVO.getTraningProcessId());
		subjweekStdVO.setTraningType(activityVO.getTraningType());
		subjweekStdVO.setWeekCnt(activityVO.getWeekCnt());
		
		subjweekStdVO.setMemSeq(activityVO.getMemSeq());
		String memSeqPrev="";
		String memSeqNext="";
		String memSeqStr =  "";
		String [] tempMemseq= activityVO.getMemSeqs().split(","); 
		for(int a=1;tempMemseq.length>a ;a++){
			memSeqStr =  tempMemseq[a];
			 
			if(a>0&&memSeqStr.equals(activityVO.getMemSeq())){
				if(a==1&&tempMemseq.length>1 ){
					memSeqNext=tempMemseq[a+1]; 
					break;
				}else{
					memSeqPrev=tempMemseq[a-1];
					if(tempMemseq.length>a+1){
						memSeqNext=tempMemseq[a+1];	
					}					 
					break;
				}
			}
		}
  		List<SubjweekStdVO>  resultlist = activityService.selectActivityHrd(subjweekStdVO);
		subjweekStdVO = activityService.getSubjWeek(subjweekStdVO);
		model.addAttribute("subjweekStdVO", subjweekStdVO);  		
  		
		
		model.addAttribute("memSeqPrev", memSeqPrev );
		model.addAttribute("memSeqNext", memSeqNext );
		
		model.addAttribute("result", result );
		model.addAttribute("resultlist", resultlist );
		model.addAttribute("activityVO", activityVO);
  		// View호출
  		return "oklms/lu/activity/getActivityHrd";
  	}  	
  	
  	
  	
  	
  	

	// 기업현장교사
  	@RequestMapping(value = "/lu/activity/listActivityOt.do")
	public String listActivityOt(@ModelAttribute("frmActivity") ActivityVO activityVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/activity/listActivityOt.do" );
  		
  		String classId_temp="";
		String retMsg = "";
  		HttpSession httpSession = request.getSession(); 
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(activityVO); // session의 정보를 VO에 추가. 

		classId_temp =activityVO.getClassId();
		activityVO.setYyyy((String)httpSession.getAttribute(Globals.YYYY));
		activityVO.setTerm((String)httpSession.getAttribute(Globals.TERM));
		activityVO.setClassId((String)httpSession.getAttribute(Globals.CLASS));
		activityVO.setSubjectCode((String)httpSession.getAttribute(Globals.SUBJECT_CODE));

		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy((String)httpSession.getAttribute(Globals.YYYY));
		currProcVO.setTerm((String)httpSession.getAttribute(Globals.TERM));
		currProcVO.setSubClass((String)httpSession.getAttribute(Globals.CLASS));
		currProcVO.setSubjectCode((String)httpSession.getAttribute(Globals.SUBJECT_CODE));
		currProcVO = reportService.getCurrproc(currProcVO);
		
 
// OJT과정			
			// 반정보가 있을때			
			if(classId_temp!=null&&!classId_temp.equals("")){
				activityVO.setClassId(classId_temp);
			}
			activityVO.setTraningType(currProcVO.getSubjectTraningType());

			// 주차정보 
			List<OnlineTraningSchVO> onlineTraningSchVO =reportService.listLmsSubjWeek(currProcVO);
			model.addAttribute("onlineTraningSchVO", onlineTraningSchVO);
			
			if( httpSession==null || activityVO.getSubjectCode()==null ||activityVO.getSubjectCode().equals("")  ){
				retMsg = "교과정보가 없습니다.";
				retMsg = URLEncoder.encode( retMsg ,"UTF-8");
				logger.debug("#### retMsg=" + retMsg );
				return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
			}	
			// 주차정보없을때 첫번째주차 정보입력
			// 현재날자에 해당하는 주차 선택기능 추가 필요함
			if(activityVO.getWeekCnt()==null||activityVO.getWeekCnt().equals("")){
				TraningNoteVO  traningNoteVO = new TraningNoteVO();
				traningNoteVO.setYyyy(currProcVO.getYyyy());
				traningNoteVO.setTerm(currProcVO.getTerm());
				traningNoteVO.setSubjectCode(currProcVO.getSubjectCode());				
				traningNoteVO.setClassId(currProcVO.getSubClass());
			 
				if(traningNoteVO.getWeekCnt() == null|| traningNoteVO.getWeekCnt().equals("")){
					// 최근주차정보조회
					TraningNoteVO  traningNowWeekCn = traningNoteSerivce.getTraningNowWeekCnt(traningNoteVO);	
					if(traningNowWeekCn.getWeekCnt()==null||traningNowWeekCn.getWeekCnt().equals("")){
						activityVO.setWeekCnt("1");
					}else{ 
						activityVO.setWeekCnt(traningNowWeekCn.getWeekCnt());
					}
				} 
			}

			//학습근로자 목록 
			MemberVO memberVO = new MemberVO();
			memberVO.setYyyy(activityVO.getYyyy());
			memberVO.setTerm(activityVO.getTerm());
			memberVO.setSubjectCode(activityVO.getSubjectCode());			
			memberVO.setWeekCnt(activityVO.getWeekCnt());
			memberVO.setSearchCompanyName(activityVO.getSearchCompanyName());

 
			memberVO.setClassId(activityVO.getClassId());
			List<MemberVO> memberlist = activityService.listActivityMember(memberVO);
			model.addAttribute("memberlist", memberlist);
						
			SubjweekStdVO subjweekStdVO = new SubjweekStdVO ();
			subjweekStdVO.setYyyy(activityVO.getYyyy());
			subjweekStdVO.setTerm(activityVO.getTerm());
			subjweekStdVO.setSubjectCode(activityVO.getSubjectCode());
			subjweekStdVO.setClassId(activityVO.getClassId());
			subjweekStdVO.setWeekCnt(activityVO.getWeekCnt());		
			subjweekStdVO = activityService.getSubjWeek(subjweekStdVO);
			
			
			model.addAttribute("subjweekStdVO", subjweekStdVO);
			model.addAttribute("currProcVO", currProcVO);
			model.addAttribute("activityVO", activityVO);
			 			
			return "oklms/lu/activity/listActivityOt";
	 
  		
  	}
	// 과제물 다운로드
  	@RequestMapping(value = "/lu/activity/downloadActivityOt.do")
	public ModelAndView downloadActivityOt(@ModelAttribute("frmActivity") MemberVO memberVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/activity/downloadActivityOt.do" );
  		ModelAndView mav = new ModelAndView("zipdownloadView");
  		HttpSession httpSession = request.getSession(); 
  		String retMsg = "";
		//다운로드 학습근로자 목록 
		//학습근로자 목록   
		List<MemberVO> memberlist = activityService.listActivityMember(memberVO);
		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy((String)httpSession.getAttribute(Globals.YYYY));
		currProcVO.setTerm((String)httpSession.getAttribute(Globals.TERM));
		currProcVO.setSubClass((String)httpSession.getAttribute(Globals.CLASS));
		currProcVO.setSubjectCode((String)httpSession.getAttribute(Globals.SUBJECT_CODE));
		currProcVO = reportService.getCurrproc(currProcVO);
		
	    String fileStr = "";
        String oldFileStr = "";
        
		for(int a=0;memberVO.getMemSeqs().length>a ;a++){
	        String atchFileId = "";
	        String memId = "";
	        String memName = "";
			String [] tempMemSeq = memberVO.getMemSeqs()[a].split(",");
			if(tempMemSeq!=null && tempMemSeq.length>1){
				memId = tempMemSeq[0];
				for(int b=0;memberlist.size()>b ;b++){
					if(memId.equals(memberlist.get(b).getMemId())){
						memName = memberlist.get(b).getMemName();
						break;
					}
				}
				
				atchFileId =tempMemSeq[1];
				
				if(atchFileId!=null){
					AtchFileVO atchFileVO1 = new AtchFileVO();
					atchFileVO1.setFileSn(1);
					atchFileVO1.setAtchFileId(atchFileId);
					if(atchFileId!=null&&!atchFileId.equals("")){
						AtchFileVO resultfile = atchFileService.getAtchFile(atchFileVO1);			
						//fileStr += resultfile.getOrgFileName()+"?";
			            fileStr += memId+"_"+memName+"."+resultfile.getFileExtn() + "?";						
			            oldFileStr += resultfile.getFileSavePath()+"/"+resultfile.getSaveFileName()+"?";
					}
				}
			}


		}

		if(fileStr != null && !fileStr.equals("")) {
            mav.addObject("fileStr", fileStr);
            mav.addObject("oldFileStr", oldFileStr);
            
            mav.addObject("reNameStr",currProcVO.getYyyy()+"_"+currProcVO.getTerm()+"_"+currProcVO.getSubjectName()+"_"+currProcVO.getSubClass()+".zip");
            //다운로드 완료 정보 수정
		}else{
			retMsg = "선택한 과제가 없습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			mav.setViewName("redirect:/lu/activity/listActivityPrt.do?retMsgEncode="+ retMsg);
		}
  		// View호출
  		return mav;
  	} 
  	 
}
