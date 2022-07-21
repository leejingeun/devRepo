package kr.co.sitglobal.oklms.lu.teamproject.web;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.Globals;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.atchFile.vo.AtchFileVO;
import kr.co.sitglobal.oklms.commbiz.util.AtchFileUtil;
import kr.co.sitglobal.oklms.lu.activity.vo.MemberVO;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.teamproject.service.TeamprojectService;
import kr.co.sitglobal.oklms.lu.teamproject.vo.TeamprojectSubmitVO;
import kr.co.sitglobal.oklms.lu.teamproject.vo.TeamprojectVO;

@Controller
public class TeamprojectController extends BaseController{
	private static final Logger LOG = LoggerFactory.getLogger(TeamprojectController.class);
	
	@Resource(name = "beanValidatorJSR303")
	Validator validator;
	
	@Resource(name="TeamprojectService")
	private TeamprojectService teamprojectService;
	
	@Resource(name = "atchFileService")
	private AtchFileService atchFileService;

	@Resource(name = "atchFileUtil")
	private AtchFileUtil atchFileUtil;
	
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	 
	@InitBinder
	public void initBinder(WebDataBinder dataBinder){
		try {
			dataBinder.setValidator(this.validator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

  	@RequestMapping(value = "/lu/teamproject/listTeamproject.do")
	public String listTeamproject(@ModelAttribute("frmTeamproject") TeamprojectVO teamprojectVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = teamproject/listTeamproject.do" );
		String retMsg = "";
  		HttpSession httpSession = request.getSession();
  		
  		CurrProcVO currProcVO=new CurrProcVO();
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
//		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(teamprojectVO); // session의 정보를 VO에 추가.
		loginInfo.putSessionToVo(currProcVO); // session의 정보를 VO에 추가.
		
		teamprojectVO.setYyyy(StringUtils.defaultIfBlank( teamprojectVO.getYyyy() ,(String)httpSession.getAttribute(Globals.YYYY)));
		teamprojectVO.setTerm(StringUtils.defaultIfBlank( teamprojectVO.getTerm() ,(String)httpSession.getAttribute(Globals.TERM)));  
		teamprojectVO.setSubClass(StringUtils.defaultIfBlank( teamprojectVO.getSubClass() ,(String)httpSession.getAttribute(Globals.CLASS)));
		teamprojectVO.setSubjectCode(StringUtils.defaultIfBlank( teamprojectVO.getSubjectCode() ,(String)httpSession.getAttribute(Globals.SUBJECT_CODE)));

		httpSession.setAttribute(Globals.YYYY, teamprojectVO.getYyyy());
		httpSession.setAttribute(Globals.TERM, teamprojectVO.getTerm());
		httpSession.setAttribute(Globals.CLASS, teamprojectVO.getSubClass());
		httpSession.setAttribute(Globals.SUBJECT_CODE, teamprojectVO.getSubjectCode());
				
		currProcVO.setYyyy(teamprojectVO.getYyyy());
		currProcVO.setTerm(teamprojectVO.getTerm());
		currProcVO.setSubClass(teamprojectVO.getSubClass() );
		currProcVO.setSubjectCode(teamprojectVO.getSubjectCode()); 				
 		
		logger.debug("#### getYyyy=" + currProcVO.getYyyy() );
		logger.debug("#### getTerm=" + currProcVO.getTerm() );
		logger.debug("#### getSubClass=" + currProcVO.getSubClass() );
		logger.debug("#### getSubjectCode=" + currProcVO.getSubjectCode() ); 
		
		if( httpSession==null || currProcVO.getSubjectCode()==null ||currProcVO.getSubjectCode().equals("")  ){
			retMsg = "교과정보가 없습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
		}	
		
		currProcVO = teamprojectService.getCurrproc(currProcVO);
		
		List<TeamprojectVO> result=teamprojectService.listTeamproject(teamprojectVO);
		
		
		model.addAttribute("result", result);
		model.addAttribute("currProcVO", currProcVO);
		model.addAttribute("teamprojVO", teamprojectVO);
		// View호출
		return "oklms/lu/teamproject/listTeamproject";
	}
  	
	@RequestMapping(value = "/lu/teamproject/goInsertTeamproject.do")
	public String goInsertTeamproject(@ModelAttribute("frmTeamproject") TeamprojectVO teamprojectVO, ModelMap model, HttpServletRequest request) throws Exception {
		
		LOG.debug("#### URL = /lu/teamproject/goInsertTeamproject.do" );
		String retMsg = "";
		HttpSession httpSession = request.getSession();
			
		CurrProcVO currProcVO=new CurrProcVO(); 
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
	//	LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(teamprojectVO); // session의 정보를 VO에 추가.
		loginInfo.putSessionToVo(currProcVO); // session의 정보를 VO에 추가. 
	
		currProcVO.setYyyy((String)httpSession.getAttribute(Globals.YYYY));
		currProcVO.setTerm((String)httpSession.getAttribute(Globals.TERM));
		currProcVO.setSubClass((String)httpSession.getAttribute(Globals.CLASS));
		currProcVO.setSubjectCode((String)httpSession.getAttribute(Globals.SUBJECT_CODE));
	
		logger.debug("#### getYyyy=" + currProcVO.getYyyy() );
		logger.debug("#### getTerm=" + currProcVO.getTerm() );
		logger.debug("#### getSubClass=" + currProcVO.getSubClass() );
		logger.debug("#### getSubjectCode=" + currProcVO.getSubjectCode() );
		
		if( httpSession==null || currProcVO.getSubjectCode()==null ||currProcVO.getSubjectCode().equals("")  ){
			retMsg = "교과정보가 없습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
		}	
		
		currProcVO = teamprojectService.getCurrproc(currProcVO);
		List<MemberVO> listStudents =teamprojectService.listStudents(currProcVO);
		
		
		Gson gson = new Gson();
		
		
		model.addAttribute("currProcVO", currProcVO);
		model.addAttribute("listStudents", gson.toJson(listStudents) );
		model.addAttribute("teamprojectVO", teamprojectVO);
		// View호출
		return "oklms/lu/teamproject/insertTeamproject";
	}
	
	@RequestMapping(value = "/lu/teamproject/insertTeamproject.do")
	public String insertTeamproject(@ModelAttribute("frmTeamproject") TeamprojectVO teamprojectVO, ModelMap model, final MultipartHttpServletRequest multiRequest) throws Exception {

		LOG.debug("#### URL = /lu/teamproject/insertTeamproject.do");
		// 세션자동복사
		LoginInfo loginInfo = new LoginInfo();
//		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(teamprojectVO); // session의 정보를 VO에 추가.
		teamprojectService.insertTeamproject(teamprojectVO, multiRequest);

		// View호출
		return "redirect:/lu/teamproject/listTeamproject.do";
	}

	@RequestMapping(value = "/lu/teamproject/goUpdateTeamproject.do")
	public String goUpdateTeamproject(@ModelAttribute("frmTeamproject") TeamprojectVO teamprojectVO, ModelMap model,
			HttpServletRequest request) throws Exception {

		LOG.debug("#### URL = /lu/teamproject/goUpdateTeamproject.do");
		String retMsg = "";
		HttpSession httpSession = request.getSession();

		CurrProcVO currProcVO = new CurrProcVO();
		// 세션자동복사
		LoginInfo loginInfo = new LoginInfo();
//		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(teamprojectVO); // session의 정보를 VO에 추가.
		loginInfo.putSessionToVo(currProcVO); // session의 정보를 VO에 추가.

		currProcVO.setYyyy((String) httpSession.getAttribute(Globals.YYYY));
		currProcVO.setTerm((String) httpSession.getAttribute(Globals.TERM));
		currProcVO.setSubClass((String) httpSession.getAttribute(Globals.CLASS));
		currProcVO.setSubjectCode((String) httpSession.getAttribute(Globals.SUBJECT_CODE));

		logger.debug("#### getYyyy=" + currProcVO.getYyyy());
		logger.debug("#### getTerm=" + currProcVO.getTerm());
		logger.debug("#### getSubClass=" + currProcVO.getSubClass());
		logger.debug("#### getSubjectCode=" + currProcVO.getSubjectCode());

		if (httpSession == null || currProcVO.getSubjectCode() == null || currProcVO.getSubjectCode().equals("")) {
			retMsg = "교과정보가 없습니다.";
			retMsg = URLEncoder.encode(retMsg, "UTF-8");
			logger.debug("#### retMsg=" + retMsg);
			return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode=" + retMsg;
		}

		teamprojectVO = teamprojectService.getTeamproject(teamprojectVO);

		List<TeamprojectSubmitVO> listSubmits = teamprojectService.listTeamprojectSubmit(new TeamprojectSubmitVO( teamprojectVO));

		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setFileSn(1);
		atchFileVO.setAtchFileId(teamprojectVO.getAtchFileId());

		// 첨부파일
		AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
		model.addAttribute("resultFile", resultFile);
		
		currProcVO = teamprojectService.getCurrproc(currProcVO);
		List<MemberVO> listStudents =teamprojectService.listStudents(currProcVO);
		Gson gson = new Gson();

		model.addAttribute("currProcVO", currProcVO);
		model.addAttribute("listSubmits", gson.toJson(listSubmits));
		model.addAttribute("teamprojectVO", teamprojectVO);
		model.addAttribute("listStudents", gson.toJson(listStudents) );

		// View호출
		return "oklms/lu/teamproject/updateTeamproject";
	}
	
	@RequestMapping(value = "/lu/teamproject/updateTeamproject.do")
	public String updateTeamproject(@ModelAttribute("frmTeamproject") TeamprojectVO teamprojectVO, ModelMap model, final MultipartHttpServletRequest multiRequest) throws Exception {

		LOG.debug("#### URL = /lu/teamproject/updateTeamproject.do");
		// 세션자동복사
		LoginInfo loginInfo = new LoginInfo();
//		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(teamprojectVO); // session의 정보를 VO에 추가.
		teamprojectService.updateTeamproject(teamprojectVO, multiRequest);
		// View호출
		return "redirect:/lu/teamproject/listTeamproject.do";
	}

	@RequestMapping(value = "/lu/teamproject/deleteTeamproject.do")
	public String deleteTeamproject(@ModelAttribute("frmTeamproject") TeamprojectVO teamprojectVO, ModelMap model) throws Exception {

		LOG.debug("#### URL = /lu/teamproject/deleteTeamproject.do");
		// 세션자동복사
		LoginInfo loginInfo = new LoginInfo();
//		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(teamprojectVO); // session의 정보를 VO에 추가.
		teamprojectService.deleteTeamproject(teamprojectVO);
		// View호출
		return "redirect:/lu/teamproject/listTeamproject.do";
	}
  	@RequestMapping(value = "/lu/teamproject/getTeamproject.do")
	public String getTeamproject(@ModelAttribute("frmTeamproject") TeamprojectVO teamprojectVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/teamproject/getTeamproject.do" );
		String retMsg = "";
  		HttpSession httpSession = request.getSession();
		
		String retMsgEncode = StringUtils.defaultIfBlank( (String) request.getParameter("retMsgEncode"), "");
		retMsgEncode = URLDecoder.decode( retMsgEncode ,"UTF-8");
		model.addAttribute("retMsgEncode", retMsgEncode );
		
  		CurrProcVO currProcVO=new CurrProcVO();
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
//		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(teamprojectVO); // session의 정보를 VO에 추가.
		loginInfo.putSessionToVo(currProcVO); // session의 정보를 VO에 추가.
 
		currProcVO.setYyyy((String)httpSession.getAttribute(Globals.YYYY));
		currProcVO.setTerm((String)httpSession.getAttribute(Globals.TERM));
		currProcVO.setSubClass((String)httpSession.getAttribute(Globals.CLASS));
		currProcVO.setSubjectCode((String)httpSession.getAttribute(Globals.SUBJECT_CODE));

		logger.debug("#### getYyyy=" + currProcVO.getYyyy() );
		logger.debug("#### getTerm=" + currProcVO.getTerm() );
		logger.debug("#### getSubClass=" + currProcVO.getSubClass() );
		logger.debug("#### getSubjectCode=" + currProcVO.getSubjectCode() );
		
		if( httpSession==null || currProcVO.getSubjectCode()==null ||currProcVO.getSubjectCode().equals("")  ){
			retMsg = "교과정보가 없습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
		}	
		currProcVO = teamprojectService.getCurrproc(currProcVO);
		teamprojectVO=teamprojectService.getTeamproject(teamprojectVO);
		teamprojectVO.setOrderByLeader("Y");
		List<TeamprojectSubmitVO> result=teamprojectService.listTeamprojectSubmit(new TeamprojectSubmitVO( teamprojectVO));
		
		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setFileSn(1);
		atchFileVO.setAtchFileId(teamprojectVO.getAtchFileId());

		//첨부파일
		AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
        model.addAttribute("resultFile", resultFile);     
		
		
		model.addAttribute("result", result);
		model.addAttribute("currProcVO", currProcVO);
		model.addAttribute("teamprojectVO", teamprojectVO);
		
		// View호출
		return "oklms/lu/teamproject/getTeamproject";
	}

  	@RequestMapping(value = "/lu/teamproject/popupTeamprojectExcel.do")
  	public String popupTeamprojectExcel(@ModelAttribute("frmTeamproject") TeamprojectVO teamprojectVO, ModelMap model, HttpServletRequest request) throws Exception {
  		LOG.debug("#### URL = /lu/teamproject/popupTeamprojectExcel.do" );
		String retMsg = "";
  		HttpSession httpSession = request.getSession();
  		
  		CurrProcVO currProcVO=new CurrProcVO();
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
//		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo(); 		
		loginInfo.putSessionToVo(teamprojectVO); // session의 정보를 VO에 추가.
		loginInfo.putSessionToVo(currProcVO); // session의 정보를 VO에 추가.

		currProcVO.setYyyy((String)httpSession.getAttribute(Globals.YYYY));
		currProcVO.setTerm((String)httpSession.getAttribute(Globals.TERM));
		currProcVO.setSubClass((String)httpSession.getAttribute(Globals.CLASS));
		currProcVO.setSubjectCode((String)httpSession.getAttribute(Globals.SUBJECT_CODE));

		if( httpSession==null || currProcVO.getSubjectCode()==null ||currProcVO.getSubjectCode().equals("")  ){
			retMsg = "교과정보가 없습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
		}

		currProcVO = teamprojectService.getCurrproc(currProcVO);
		
		TeamprojectVO teamproject =teamprojectService.getTeamproject(teamprojectVO);
		teamproject.setPageIndex(teamprojectVO.getPageIndex());
		List<TeamprojectSubmitVO> result=teamprojectService.listTeamprojectSubmit(new TeamprojectSubmitVO(teamprojectVO));

		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setFileSn(1);
		atchFileVO.setAtchFileId(teamprojectVO.getAtchFileId());

		//첨부파일
		AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
        model.addAttribute("resultFile", resultFile);     

		model.addAttribute("result", result);
		model.addAttribute("currProcVO", currProcVO);
		model.addAttribute("teamprojectVO", teamproject);
		
		request.setAttribute("ExcelName", URLEncoder.encode( "팀프로젝트_제출현황_팝업".replaceAll(" ", "_") ,"UTF-8") );
		
		// View호출
		return "oklms_excel/lu/teamproject/popupTeamprojectExcel";
  	}
  	@RequestMapping(value = "/lu/teamproject/getTeamprojectExcel.do")
	public String getTeamprojectExcel(@ModelAttribute("frmTeamproject") TeamprojectVO teamprojectVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/teamproject/getTeamprojectExcel.do" );
		String retMsg = "";
  		HttpSession httpSession = request.getSession();
  		
  		CurrProcVO currProcVO=new CurrProcVO();
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
//		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(teamprojectVO); // session의 정보를 VO에 추가.
		loginInfo.putSessionToVo(currProcVO); // session의 정보를 VO에 추가.
 
		currProcVO.setYyyy((String)httpSession.getAttribute(Globals.YYYY));
		currProcVO.setTerm((String)httpSession.getAttribute(Globals.TERM));
		currProcVO.setSubClass((String)httpSession.getAttribute(Globals.CLASS));
		currProcVO.setSubjectCode((String)httpSession.getAttribute(Globals.SUBJECT_CODE));

		logger.debug("#### getYyyy=" + currProcVO.getYyyy() );
		logger.debug("#### getTerm=" + currProcVO.getTerm() );
		logger.debug("#### getSubClass=" + currProcVO.getSubClass() );
		logger.debug("#### getSubjectCode=" + currProcVO.getSubjectCode() );
		
		if( httpSession==null || currProcVO.getSubjectCode()==null ||currProcVO.getSubjectCode().equals("")  ){
			retMsg = "교과정보가 없습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
		}	
//		currProcVO = teamprojectService.getCurrproc(currProcVO);
		teamprojectVO=teamprojectService.getTeamproject(teamprojectVO);
		new TeamprojectSubmitVO();
		
		List<TeamprojectSubmitVO> result=teamprojectService.listTeamprojectSubmit(new TeamprojectSubmitVO( teamprojectVO));
		
		request.setAttribute("ExcelName", URLEncoder.encode( "팀프로젝트_제출현황".replaceAll(" ", "_") ,"UTF-8") );
        
		
		
		model.addAttribute("result", result);
//		model.addAttribute("currProcVO", currProcVO);
		model.addAttribute("teamprojectVO", teamprojectVO);
		
		// View호출
		return "oklms_excel/lu/teamproject/getTeamprojectExcel";
	}
  	
  	

  	@RequestMapping(value = "/lu/teamproject/goScoreTeamproject.do")
	public String goScoreTeamproject(@ModelAttribute("frmTeamproject") TeamprojectVO teamprojectVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/teamproject/goScoreTeamproject.do" );
		String retMsg = "";
  		HttpSession httpSession = request.getSession();
  		
  		CurrProcVO currProcVO=new CurrProcVO();
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
//		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(teamprojectVO); // session의 정보를 VO에 추가.
		loginInfo.putSessionToVo(currProcVO); // session의 정보를 VO에 추가.
 
		currProcVO.setYyyy((String)httpSession.getAttribute(Globals.YYYY));
		currProcVO.setTerm((String)httpSession.getAttribute(Globals.TERM));
		currProcVO.setSubClass((String)httpSession.getAttribute(Globals.CLASS));
		currProcVO.setSubjectCode((String)httpSession.getAttribute(Globals.SUBJECT_CODE));

		logger.debug("#### getYyyy=" + currProcVO.getYyyy() );
		logger.debug("#### getTerm=" + currProcVO.getTerm() );
		logger.debug("#### getSubClass=" + currProcVO.getSubClass() );
		logger.debug("#### getSubjectCode=" + currProcVO.getSubjectCode() );
		
		if( httpSession==null || currProcVO.getSubjectCode()==null ||currProcVO.getSubjectCode().equals("")  ){
			retMsg = "교과정보가 없습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
		}
					
		currProcVO = teamprojectService.getCurrproc(currProcVO);
		teamprojectVO=teamprojectService.getTeamproject(teamprojectVO);
		
		List<TeamprojectSubmitVO> result=teamprojectService.listTeamprojectSubmit( new TeamprojectSubmitVO(teamprojectVO));
		
		
		
		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setFileSn(1);
		atchFileVO.setAtchFileId(teamprojectVO.getAtchFileId());

		//첨부파일
		AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
        model.addAttribute("resultFile", resultFile);     
		
		model.addAttribute("result", result);
		model.addAttribute("currProcVO", currProcVO);
		model.addAttribute("teamprojectVO", teamprojectVO);
		
  		// View호출
		return "oklms/lu/teamproject/scoreTeamproject";
	}
  	@RequestMapping(value = "/lu/teamproject/scoreTeamproject.do")
	public String scoreTeamproject(@ModelAttribute("frmTeamproject") TeamprojectSubmitVO teamprojectSubmitVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/teamproject/scoreTeamproject.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
//		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(teamprojectSubmitVO); // session의 정보를 VO에 추가.
  		teamprojectService.updateTeamprojectSubmitArr(teamprojectSubmitVO, request);
  		// View호출
  		return "redirect:/lu/teamproject/listTeamproject.do";
	}
  	
  	
  	@RequestMapping(value = "/lu/teamproject/popupTeamproject.do")
	public String goPopupTeamproject(@ModelAttribute("frmTeamproject") TeamprojectVO teamprojectVO, ModelMap model , HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/teamproject/popupTeamproject.do" );
		String retMsg = "";
  		HttpSession httpSession = request.getSession();
  		
  		CurrProcVO currProcVO=new CurrProcVO();
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
//		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo(); 		
		loginInfo.putSessionToVo(teamprojectVO); // session의 정보를 VO에 추가.
		loginInfo.putSessionToVo(currProcVO); // session의 정보를 VO에 추가.

		currProcVO.setYyyy((String)httpSession.getAttribute(Globals.YYYY));
		currProcVO.setTerm((String)httpSession.getAttribute(Globals.TERM));
		currProcVO.setSubClass((String)httpSession.getAttribute(Globals.CLASS));
		currProcVO.setSubjectCode((String)httpSession.getAttribute(Globals.SUBJECT_CODE));

		if( httpSession==null || currProcVO.getSubjectCode()==null ||currProcVO.getSubjectCode().equals("")  ){
			retMsg = "교과정보가 없습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
		}

		currProcVO = teamprojectService.getCurrproc(currProcVO);
		
		TeamprojectVO teamproject =teamprojectService.getTeamproject(teamprojectVO);
		teamproject.setPageIndex(teamprojectVO.getPageIndex());
		List<TeamprojectSubmitVO> result=teamprojectService.listTeamprojectSubmit(new TeamprojectSubmitVO(teamprojectVO));

		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setFileSn(1);
		atchFileVO.setAtchFileId(teamprojectVO.getAtchFileId());

//		Integer pageSize = teamprojectVO.getPageSize();
//		Integer page = teamprojectVO.getPageIndex();
//		Integer pageTemp = teamprojectVO.getPageIndex();
//
//		if(1 != page){
//			pageSize = pageTemp*10;
//			page = pageSize-9;
//		} else if(1 == page){
//			page = 1;
//			pageSize = 10;
//		}
//		int totalCnt = 0;
//		if(result!=null && 0 < result.size() ){
//			if(null == result.get(0).getTotalCount()){
//				totalCnt =  0;
//			}else{
//				totalCnt = Integer.parseInt( result.get(0).getTotalCount() );	
//			}
//		}
//		int totalPage = (int) Math.ceil(totalCnt / pageSize);
//
//    	/** paging */
//    	PaginationInfo paginationInfo = new PaginationInfo();
//        paginationInfo.setCurrentPageNo(teamprojectVO.getPageIndex());
//        paginationInfo.setRecordCountPerPage(teamprojectVO.getPageUnit());
//        paginationInfo.setTotalRecordCount(totalCnt);

//        model.addAttribute("resultCnt", totalCnt );
//        model.addAttribute("paginationInfo", paginationInfo);
        
		//첨부파일
		AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
        model.addAttribute("resultFile", resultFile);     

		model.addAttribute("result", result);
		model.addAttribute("currProcVO", currProcVO);
		model.addAttribute("teamprojectVO", teamproject); 		
		// View호출
  		return "oklms_popup/lu/teamproject/popupTeamproject";
	}  	
  	@RequestMapping(value = "/lu/teamproject/listTeamprojectStd.do")
	public String listTeamprojectStd(@ModelAttribute("frmTeamproject") TeamprojectVO teamprojectVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/teamproject/listTeamprojectStd.do" );
  		String retMsg = "";
  		HttpSession httpSession = request.getSession();
  		
  		CurrProcVO currProcVO=new CurrProcVO();
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
//		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(teamprojectVO); // session의 정보를 VO에 추가.
		loginInfo.putSessionToVo(currProcVO); // session의 정보를 VO에 추가.
		
		
		teamprojectVO.setYyyy(StringUtils.defaultIfBlank( teamprojectVO.getYyyy() ,(String)httpSession.getAttribute(Globals.YYYY)));
		teamprojectVO.setTerm(StringUtils.defaultIfBlank( teamprojectVO.getTerm() ,(String)httpSession.getAttribute(Globals.TERM)));  
		teamprojectVO.setSubClass(StringUtils.defaultIfBlank( teamprojectVO.getSubClass() ,(String)httpSession.getAttribute(Globals.CLASS)));
		teamprojectVO.setSubjectCode(StringUtils.defaultIfBlank( teamprojectVO.getSubjectCode() ,(String)httpSession.getAttribute(Globals.SUBJECT_CODE)));

		httpSession.setAttribute(Globals.YYYY, teamprojectVO.getYyyy());
		httpSession.setAttribute(Globals.TERM, teamprojectVO.getTerm());
		httpSession.setAttribute(Globals.CLASS, teamprojectVO.getSubClass());
		httpSession.setAttribute(Globals.SUBJECT_CODE, teamprojectVO.getSubjectCode());
				
		currProcVO.setYyyy(teamprojectVO.getYyyy());
		currProcVO.setTerm(teamprojectVO.getTerm());
		currProcVO.setSubClass(teamprojectVO.getSubClass() );
		currProcVO.setSubjectCode(teamprojectVO.getSubjectCode()); 		
		
 
		logger.debug("#### getYyyy=" + currProcVO.getYyyy() );
		logger.debug("#### getTerm=" + currProcVO.getTerm() );
		logger.debug("#### getSubClass=" + currProcVO.getSubClass() );
		logger.debug("#### getSubjectCode=" + currProcVO.getSubjectCode() );
		
		if( httpSession==null || currProcVO.getSubjectCode()==null ||currProcVO.getSubjectCode().equals("")  ){
			retMsg = "교과정보가 없습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
		}		
		currProcVO = teamprojectService.getCurrproc(currProcVO);
		
		
		List<TeamprojectVO> result=teamprojectService.listTeamprojectStd(teamprojectVO);
		
		
		model.addAttribute("result", result);
		model.addAttribute("currProcVO", currProcVO);
//		model.addAttribute("teamprojectVO", teamprojectVO);
  		// View호출
		return "oklms/lu/teamproject/listTeamprojectStd";
	}
  	
  	@RequestMapping(value = "/lu/teamproject/getTeamprojectStd.do")
	public String getTeamprojectStd(@ModelAttribute("frmTeamproject") TeamprojectVO teamprojectVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/teamproject/getTeamprojectStd.do" );
  		String retMsg = "";
  		HttpSession httpSession = request.getSession();
  		
  		CurrProcVO currProcVO=new CurrProcVO();
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
//		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(teamprojectVO); // session의 정보를 VO에 추가.
		loginInfo.putSessionToVo(currProcVO); // session의 정보를 VO에 추가.
 
 

		teamprojectVO.setYyyy((String)httpSession.getAttribute(Globals.YYYY));
		teamprojectVO.setTerm((String)httpSession.getAttribute(Globals.TERM));
		teamprojectVO.setSubClass((String)httpSession.getAttribute(Globals.CLASS));
		teamprojectVO.setSubjectCode((String)httpSession.getAttribute(Globals.SUBJECT_CODE));
		
		currProcVO.setYyyy(teamprojectVO.getYyyy());
		currProcVO.setTerm(teamprojectVO.getTerm());
		currProcVO.setSubClass(teamprojectVO.getSubClass() );
		currProcVO.setSubjectCode(teamprojectVO.getSubjectCode()); 	
		
		logger.debug("#### getYyyy=" + currProcVO.getYyyy() );
		logger.debug("#### getTerm=" + currProcVO.getTerm() );
		logger.debug("#### getSubClass=" + currProcVO.getSubClass() );
		logger.debug("#### getSubjectCode=" + currProcVO.getSubjectCode() );
		
		if( httpSession==null || currProcVO.getSubjectCode()==null ||currProcVO.getSubjectCode().equals("")  ){
			retMsg = "교과정보가 없습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
		}		
		currProcVO = teamprojectService.getCurrproc(currProcVO);
		
		teamprojectVO=teamprojectService.getTeamproject(teamprojectVO);
		
		//과제첨부파일
		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setFileSn(1);
		atchFileVO.setAtchFileId(teamprojectVO.getAtchFileId());
		AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
        model.addAttribute("resultFile", resultFile);
		
        TeamprojectSubmitVO teamprojectSubmitVO= new TeamprojectSubmitVO(); 
        loginInfo.putSessionToVo(teamprojectSubmitVO); // session의 정보를 VO에 추가.
        teamprojectSubmitVO.setTeamprojectId(teamprojectVO.getTeamprojectId());
        teamprojectSubmitVO.setMemId(teamprojectSubmitVO.getSessionMemId());
        teamprojectSubmitVO=teamprojectService.getTeamprojectSubmit(teamprojectSubmitVO);
        
		//과제제출첨부파일
		AtchFileVO atchFileVO1 = new AtchFileVO();
		atchFileVO1.setFileSn(1);
		if(teamprojectSubmitVO!=null && teamprojectSubmitVO.getAtchFileId()!=null && !teamprojectSubmitVO.getAtchFileId().equals("")){
			atchFileVO1.setAtchFileId(teamprojectSubmitVO.getAtchFileId());
			AtchFileVO resultFile1 = atchFileService.getAtchFile(atchFileVO1);
			model.addAttribute("resultFile1", resultFile1);
		}
		            
		model.addAttribute("currProcVO", currProcVO);
		model.addAttribute("teamprojectVO", teamprojectVO);
		model.addAttribute("teamprojectSubmitVO", teamprojectSubmitVO);
		
  		// View호출
		return "oklms/lu/teamproject/getTeamprojectStd";
	}
  	@RequestMapping(value = "/lu/teamproject/insertTeamprojectStd.do")
	public String insertTeamprojectStd(@ModelAttribute("frmTeamproject") TeamprojectSubmitVO teamprojectSubmitVO, ModelMap model,final MultipartHttpServletRequest multiRequest) throws Exception {
		
  		LOG.debug("#### URL = /lu/teamproject/insertTeamprojectStd.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
//		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(teamprojectSubmitVO); // session의 정보를 VO에 추가.		
  		teamprojectService.insertTeamprojectStd(teamprojectSubmitVO,multiRequest);
  		
		// View호출
  		return "redirect:/lu/teamproject/listTeamprojectStd.do";
	}  	
  	@RequestMapping(value = "/lu/teamproject/deleteTeamprojectStd.do")
	public String deleteTeamprojectStd(@ModelAttribute("frmTeamproject") TeamprojectSubmitVO teamprojectSubmitVO, ModelMap model) throws Exception {
		
  		LOG.debug("#### URL = /lu/teamproject/deleteTeamprojectStd.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
//		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(teamprojectSubmitVO); // session의 정보를 VO에 추가.
  		teamprojectService.deleteTeamprojectSubmitAttechFile(teamprojectSubmitVO);
  		// View호출
		return "redirect:/lu/teamproject/getTeamprojectStd.do?teamprojectId="+teamprojectSubmitVO.getTeamprojectId();
	}
  	

  	@RequestMapping(value = "/lu/teamproject/listTeamprojectExcel.do")
	public ModelAndView listTeamprojectExcel(@ModelAttribute("frmTeamproject") TeamprojectVO teamprojectVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/teamproject/listTeamprojectExcel.do" );
		String retMsg = "";
  		HttpSession httpSession = request.getSession();
  		
  		ModelAndView mav = new ModelAndView("zipdownloadView");
  		
  		CurrProcVO currProcVO=new CurrProcVO();
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
//		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(teamprojectVO); // session의 정보를 VO에 추가.
		loginInfo.putSessionToVo(currProcVO); // session의 정보를 VO에 추가.
 
		currProcVO.setYyyy((String)httpSession.getAttribute(Globals.YYYY));
		currProcVO.setTerm((String)httpSession.getAttribute(Globals.TERM));
		currProcVO.setSubClass((String)httpSession.getAttribute(Globals.CLASS));
		currProcVO.setSubjectCode((String)httpSession.getAttribute(Globals.SUBJECT_CODE));
		currProcVO = teamprojectService.getCurrproc(currProcVO);
		teamprojectVO=teamprojectService.getTeamproject(teamprojectVO);
		
		List<TeamprojectSubmitVO> result=teamprojectService.listTeamprojectSubmit(new TeamprojectSubmitVO( teamprojectVO));
		
	    String fileStr = "";
        String oldFileStr = "";
        String atchFileId = "";
        boolean isAddObj = true;
		for(int a=0;result.size()>a ;a++){
			atchFileId = result.get(a).getAtchFileId();
			AtchFileVO atchFileVO1 = new AtchFileVO();
			atchFileVO1.setFileSn(1);
			atchFileVO1.setAtchFileId(atchFileId);
			if(atchFileId!=null&&!atchFileId.equals("")){
				
				if("T".equals(teamprojectVO.getSubmitType())){
					// 팀장만 제출이면 팀장일경우만 저장.
					if("Y".equals(result.get(a).getLeaderYn())){
						isAddObj = true;
					}else{
						isAddObj = false;
					}
				}else{
					isAddObj = true;
				}
				if(isAddObj){
					AtchFileVO resultfile = atchFileService.getAtchFile(atchFileVO1);			
					fileStr += result.get(a).getGroupId()+"팀"+( ("T".equals(teamprojectVO.getSubmitType()))? "":"_"+result.get(a).getMemName() )+"_"+resultfile.getOrgFileName()+"?";
					oldFileStr += resultfile.getFileSavePath()+"/"+resultfile.getSaveFileName()+"?";
					
				}
			}
		}
		
		if(fileStr != null && !fileStr.equals("")) {
			mav.addObject("reNameStr", currProcVO.getYyyy()+"_"+currProcVO.getTerm()+"학기_"+currProcVO.getSubjectName()+"_"+currProcVO.getSubClass()+"반_"+teamprojectVO.getProjectName()+".zip");
            mav.addObject("fileStr", fileStr);
            mav.addObject("oldFileStr", oldFileStr);
            //다운로드 완료 정보 수정
		}else{
			retMsg = "제출한 과제물이 없습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			mav.setViewName("redirect:/lu/teamproject/getTeamproject.do?teamprojectId="+teamprojectVO.getTeamprojectId()+"&retMsgEncode="+ retMsg);
		}
		// View호출
		return mav;
	}  	
}
