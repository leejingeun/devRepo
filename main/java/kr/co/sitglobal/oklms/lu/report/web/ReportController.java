package kr.co.sitglobal.oklms.lu.report.web;

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

import egovframework.com.cmm.LoginVO; 
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.service.Globals;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController; 
import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.atchFile.vo.AtchFileVO;
import kr.co.sitglobal.oklms.commbiz.util.AtchFileUtil;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO; 
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningSchVO;
import kr.co.sitglobal.oklms.lu.report.service.ReportService;
import kr.co.sitglobal.oklms.lu.report.vo.ReportVO;
import kr.co.sitglobal.oklms.lu.subject.service.SubjectService;
import kr.co.sitglobal.oklms.lu.subject.vo.SubjectVO;

@Controller
public class ReportController extends BaseController{
	private static final Logger LOG = LoggerFactory.getLogger(ReportController.class);
	
	@Resource(name = "beanValidatorJSR303")
	Validator validator;
	
	@Resource(name = "ReportService")
	private ReportService reportService;
	
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

  	@RequestMapping(value = "/lu/report/listReport.do")
	public String listReport(@ModelAttribute("frmReport") ReportVO reportVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/report/listReport.do" );
		String retMsg = "";
  		HttpSession httpSession = request.getSession();
  		
  		CurrProcVO currProcVO=new CurrProcVO();
  		SubjectVO subjectVO = new SubjectVO();
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(reportVO); // session의 정보를 VO에 추가.
		loginInfo.putSessionToVo(currProcVO); // session의 정보를 VO에 추가.
 

		reportVO.setYyyy(StringUtils.defaultIfBlank( reportVO.getYyyy() ,(String)httpSession.getAttribute(Globals.YYYY)));
		reportVO.setTerm(StringUtils.defaultIfBlank( reportVO.getTerm() ,(String)httpSession.getAttribute(Globals.TERM)));  
		reportVO.setSubClass(StringUtils.defaultIfBlank( reportVO.getSubClass() ,(String)httpSession.getAttribute(Globals.CLASS)));
		reportVO.setSubjectCode(StringUtils.defaultIfBlank( reportVO.getSubjectCode() ,(String)httpSession.getAttribute(Globals.SUBJECT_CODE)));

		httpSession.setAttribute(Globals.YYYY, reportVO.getYyyy());
		httpSession.setAttribute(Globals.TERM, reportVO.getTerm());
		httpSession.setAttribute(Globals.CLASS, reportVO.getSubClass());
		httpSession.setAttribute(Globals.SUBJECT_CODE, reportVO.getSubjectCode());
				
		currProcVO.setYyyy(reportVO.getYyyy());
		currProcVO.setTerm(reportVO.getTerm());
		currProcVO.setSubClass(reportVO.getSubClass() );
		currProcVO.setSubjectCode(reportVO.getSubjectCode()); 
		
		subjectVO.setYyyy(reportVO.getYyyy());
        subjectVO.setTerm(reportVO.getTerm());
        subjectVO.setSubjectCode(reportVO.getSubClass());
		subjectVO.setSubjectClass(reportVO.getSubjectCode());

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
		
		currProcVO = reportService.getCurrproc(currProcVO);
		
		List<ReportVO> result=reportService.listReport(reportVO);
		
		
		model.addAttribute("result", result);
		model.addAttribute("currProcVO", currProcVO);
		model.addAttribute("reportVO", reportVO);
		// View호출
		return "oklms/lu/report/listReport";
	}
	
  	@RequestMapping(value = "/lu/report/getReport.do")
	public String getReport(@ModelAttribute("frmReport") ReportVO reportVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/report/getReport.do" );
		String retMsg = "";
  		HttpSession httpSession = request.getSession();
		
		String retMsgEncode = StringUtils.defaultIfBlank( (String) request.getParameter("retMsgEncode"), "");
		retMsgEncode = URLDecoder.decode( retMsgEncode ,"UTF-8");
		model.addAttribute("retMsgEncode", retMsgEncode );
		
  		CurrProcVO currProcVO=new CurrProcVO();
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(reportVO); // session의 정보를 VO에 추가.
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
		currProcVO = reportService.getCurrproc(currProcVO);
		reportVO=reportService.getReport(reportVO);
		reportVO.setPageSize(100000);
		List<ReportVO> result=reportService.listReportSubmit(reportVO);
		
		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setFileSn(1);
		atchFileVO.setAtchFileId(reportVO.getAtchFileId());

		//첨부파일
		AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
        model.addAttribute("resultFile", resultFile);     
		
		
		model.addAttribute("result", result);
		model.addAttribute("currProcVO", currProcVO);
		model.addAttribute("reportVO", reportVO);
		
		// View호출
		return "oklms/lu/report/getReport";
	}

  	@RequestMapping(value = "/lu/report/getReportExcel.do")
	public String getReportExcel(@ModelAttribute("frmReport") ReportVO reportVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/report/getReportExcel.do" );
		String retMsg = "";
  		HttpSession httpSession = request.getSession();
  		
  		CurrProcVO currProcVO=new CurrProcVO();
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(reportVO); // session의 정보를 VO에 추가.
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
		currProcVO = reportService.getCurrproc(currProcVO);
		reportVO=reportService.getReport(reportVO);
		reportVO.setPageSize(100000);
		List<ReportVO> result=reportService.listReportSubmit(reportVO);
		
		request.setAttribute("ExcelName", URLEncoder.encode( "제출현황".replaceAll(" ", "_") ,"UTF-8") );
        
		
		
		model.addAttribute("result", result);
		model.addAttribute("currProcVO", currProcVO);
		model.addAttribute("reportVO", reportVO);
		
		// View호출
		return "oklms_excel/lu/report/getReportExcel";
	}
  	
  	
  	@RequestMapping(value = "/lu/report/goInsertReport.do")
	public String goInsertReport(@ModelAttribute("frmReport") ReportVO reportVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/report/goInsertReport.do" );
		String retMsg = "";
  		HttpSession httpSession = request.getSession();
  		
  		CurrProcVO currProcVO=new CurrProcVO(); 
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(reportVO); // session의 정보를 VO에 추가.
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
		
		currProcVO = reportService.getCurrproc(currProcVO);
		List<OnlineTraningSchVO> onlineTraningSchVO =reportService.listLmsSubjWeek(currProcVO);
		
		
		
		
		
		model.addAttribute("currProcVO", currProcVO);
		model.addAttribute("onlineTraningSchVO", onlineTraningSchVO);
		model.addAttribute("reportVO", reportVO);
		// View호출
		return "oklms/lu/report/insertReport";
	}
  	
  	@RequestMapping(value = "/lu/report/insertReport.do")
	public String insertReport(@ModelAttribute("frmReport") ReportVO reportVO, ModelMap model,final MultipartHttpServletRequest multiRequest) throws Exception {
		
  		LOG.debug("#### URL = /lu/report/insertReport.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(reportVO); // session의 정보를 VO에 추가.
  		reportService.insertReport(reportVO,multiRequest);
  		
		// View호출
  		return "redirect:/lu/report/listReport.do";
	}  	
  	  	
  	
  	@RequestMapping(value = "/lu/report/goUpdateReport.do")
	public String goUpdateReport(@ModelAttribute("frmReport") ReportVO reportVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/report/goUpdateReport.do" );
		String retMsg = "";
  		HttpSession httpSession = request.getSession();
  		
  		CurrProcVO currProcVO=new CurrProcVO();
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(reportVO); // session의 정보를 VO에 추가.
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
		
		currProcVO = reportService.getCurrproc(currProcVO);
		reportVO=reportService.getReport(reportVO);
		
		List<OnlineTraningSchVO> onlineTraningSchVO =reportService.listLmsSubjWeek(currProcVO);		
		
		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setFileSn(1);
		atchFileVO.setAtchFileId(reportVO.getAtchFileId());

		//첨부파일
		AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
        model.addAttribute("resultFile", resultFile);     
        
		model.addAttribute("currProcVO", currProcVO);
		model.addAttribute("onlineTraningSchVO", onlineTraningSchVO);
		model.addAttribute("reportVO", reportVO);
		
  		// View호출
		return "oklms/lu/report/updateReport";
	}

  	@RequestMapping(value = "/lu/report/goScoreReport.do")
	public String goScoreReport(@ModelAttribute("frmReport") ReportVO reportVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/report/goScoreReport.do" );
		String retMsg = "";
  		HttpSession httpSession = request.getSession();
  		
  		CurrProcVO currProcVO=new CurrProcVO();
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(reportVO); // session의 정보를 VO에 추가.
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
					
		currProcVO = reportService.getCurrproc(currProcVO);
		reportVO=reportService.getReport(reportVO);
		
		List<ReportVO> result=reportService.listReportSubmit(reportVO);
		
		
		
		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setFileSn(1);
		atchFileVO.setAtchFileId(reportVO.getAtchFileId());

		//첨부파일
		AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
        model.addAttribute("resultFile", resultFile);     
		
		model.addAttribute("result", result);
		model.addAttribute("currProcVO", currProcVO);
		model.addAttribute("reportVO", reportVO);
		
  		// View호출
		return "oklms/lu/report/scoreReport";
	}
  	@RequestMapping(value = "/lu/report/scoreReport.do")
	public String scoreReport(@ModelAttribute("frmReport") ReportVO reportVO, ModelMap model) throws Exception {
		
  		LOG.debug("#### URL = /lu/report/scoreReport.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(reportVO); // session의 정보를 VO에 추가.
  		reportService.updateReportSubmitArr(reportVO);
  		// View호출
  		return "redirect:/lu/report/listReport.do";
	}
  	
  	@RequestMapping(value = "/lu/report/updateReport.do")
	public String updateReport(@ModelAttribute("frmReport") ReportVO reportVO, ModelMap model,final MultipartHttpServletRequest multiRequest) throws Exception {
		
  		LOG.debug("#### URL = /lu/report/updateReport.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(reportVO); // session의 정보를 VO에 추가.
  		reportService.updateReport(reportVO, multiRequest);
  		// View호출
  		return "redirect:/lu/report/listReport.do";
	}
  	
  	@RequestMapping(value = "/lu/report/deleteReport.do")
	public String deleteReport(@ModelAttribute("frmReport") ReportVO reportVO, ModelMap model) throws Exception {
		
  		LOG.debug("#### URL = /lu/report/deleteReport.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(reportVO); // session의 정보를 VO에 추가.
  		reportService.deleteReport(reportVO);
  		// View호출
		return "redirect:/lu/report/listReport.do";
	}
  	
  	@RequestMapping(value = "/lu/report/popupReport.do")
	public String goPopupReport(@ModelAttribute("frmReport") ReportVO reportVO, ModelMap model , HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/report/popupReport.do" );
		String retMsg = "";
  		HttpSession httpSession = request.getSession();
  		
  		CurrProcVO currProcVO=new CurrProcVO();
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo(); 		
		loginInfo.putSessionToVo(reportVO); // session의 정보를 VO에 추가.
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

		currProcVO = reportService.getCurrproc(currProcVO);
		
		ReportVO report =reportService.getReport(reportVO);
		report.setPageIndex(reportVO.getPageIndex());
		List<ReportVO> result=reportService.listReportSubmit(reportVO);

		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setFileSn(1);
		atchFileVO.setAtchFileId(reportVO.getAtchFileId());

		Integer pageSize = reportVO.getPageSize();
		Integer page = reportVO.getPageIndex();
		Integer pageTemp = reportVO.getPageIndex();

		if(1 != page){
			pageSize = pageTemp*10;
			page = pageSize-9;
		} else if(1 == page){
			page = 1;
			pageSize = 10;
		}
		int totalCnt = 0;
		if(result!=null && 0 < result.size() ){
			if(null == result.get(0).getTotalCount()){
				totalCnt =  0;
			}else{
				totalCnt = Integer.parseInt( result.get(0).getTotalCount() );	
			}
		}
		int totalPage = (int) Math.ceil(totalCnt / pageSize);

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(reportVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(reportVO.getPageUnit());
        paginationInfo.setPageSize(reportVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);
        
		//첨부파일
		AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
        model.addAttribute("resultFile", resultFile);     

		model.addAttribute("result", result);
		model.addAttribute("currProcVO", currProcVO);
		model.addAttribute("reportVO", report); 		
		// View호출
  		return "oklms_popup/lu/report/popupReport";
	}  	
  	@RequestMapping(value = "/lu/report/listReportStd.do")
	public String listReportStd(@ModelAttribute("frmReport") ReportVO reportVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/report/listReportStd.do" );
  		String retMsg = "";
  		HttpSession httpSession = request.getSession();
  		
  		CurrProcVO currProcVO=new CurrProcVO();
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(reportVO); // session의 정보를 VO에 추가.
		loginInfo.putSessionToVo(currProcVO); // session의 정보를 VO에 추가.

		reportVO.setYyyy(StringUtils.defaultIfBlank( reportVO.getYyyy() ,(String)httpSession.getAttribute(Globals.YYYY)));
		reportVO.setTerm(StringUtils.defaultIfBlank( reportVO.getTerm() ,(String)httpSession.getAttribute(Globals.TERM)));  
		reportVO.setSubClass(StringUtils.defaultIfBlank( reportVO.getSubClass() ,(String)httpSession.getAttribute(Globals.CLASS)));
		reportVO.setSubjectCode(StringUtils.defaultIfBlank( reportVO.getSubjectCode() ,(String)httpSession.getAttribute(Globals.SUBJECT_CODE)));

		httpSession.setAttribute(Globals.YYYY, reportVO.getYyyy());
		httpSession.setAttribute(Globals.TERM, reportVO.getTerm());
		httpSession.setAttribute(Globals.CLASS, reportVO.getSubClass());
		httpSession.setAttribute(Globals.SUBJECT_CODE, reportVO.getSubjectCode());
				
		currProcVO.setYyyy(reportVO.getYyyy());
		currProcVO.setTerm(reportVO.getTerm());
		currProcVO.setSubClass(reportVO.getSubClass() );
		currProcVO.setSubjectCode(reportVO.getSubjectCode()); 
		
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
		currProcVO = reportService.getCurrproc(currProcVO);
		
		
		List<ReportVO> result=reportService.listReportStd(reportVO);
		
		
		model.addAttribute("result", result);
		model.addAttribute("currProcVO", currProcVO);
		model.addAttribute("reportVO", reportVO);
  		// View호출
		return "oklms/lu/report/listReportStd";
	}
  	
  	@RequestMapping(value = "/lu/report/getReportStd.do")
	public String getReportStd(@ModelAttribute("frmReport") ReportVO reportVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/report/getReportStd.do" );
  		String retMsg = "";
  		HttpSession httpSession = request.getSession();
  		
  		CurrProcVO currProcVO=new CurrProcVO();
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(reportVO); // session의 정보를 VO에 추가.
		loginInfo.putSessionToVo(currProcVO); // session의 정보를 VO에 추가.
  
		reportVO.setYyyy((String)httpSession.getAttribute(Globals.YYYY));
		reportVO.setTerm((String)httpSession.getAttribute(Globals.TERM));
		reportVO.setSubClass((String)httpSession.getAttribute(Globals.CLASS));
		reportVO.setSubjectCode((String)httpSession.getAttribute(Globals.SUBJECT_CODE));
	
		currProcVO.setYyyy(reportVO.getYyyy());
		currProcVO.setTerm(reportVO.getTerm());
		currProcVO.setSubClass(reportVO.getSubClass() );
		currProcVO.setSubjectCode(reportVO.getSubjectCode()); 
		
		
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
		currProcVO = reportService.getCurrproc(currProcVO);
		
		reportVO=reportService.getReport(reportVO);
		
		//과제첨부파일
		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setFileSn(1);
		atchFileVO.setAtchFileId(reportVO.getAtchFileId());
		AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
        model.addAttribute("resultFile", resultFile);
		
        ReportVO reportVO1= new ReportVO(); 
        loginInfo.putSessionToVo(reportVO1); // session의 정보를 VO에 추가.
        reportVO1.setReportId(reportVO.getReportId());
        reportVO1=reportService.getReportSubmit(reportVO1);
        
		//과제제출첨부파일
		AtchFileVO atchFileVO1 = new AtchFileVO();
		atchFileVO1.setFileSn(1);
		if(reportVO1!=null && reportVO1.getAtchFileId()!=null && !reportVO1.getAtchFileId().equals("")){
			atchFileVO1.setAtchFileId(reportVO1.getAtchFileId());
			AtchFileVO resultFile1 = atchFileService.getAtchFile(atchFileVO1);
			model.addAttribute("resultFile1", resultFile1);
		}
		            
		model.addAttribute("currProcVO", currProcVO);
		model.addAttribute("reportVO", reportVO);
		model.addAttribute("reportVO1", reportVO1);
		
  		// View호출
		return "oklms/lu/report/getReportStd";
	}
  	@RequestMapping(value = "/lu/report/insertReportStd.do")
	public String insertReportStd(@ModelAttribute("frmReport") ReportVO reportVO, ModelMap model,final MultipartHttpServletRequest multiRequest) throws Exception {
		
  		LOG.debug("#### URL = /lu/report/insertReportStd.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(reportVO); // session의 정보를 VO에 추가.		
  		reportService.insertReportStd(reportVO,multiRequest);
  		
		// View호출
  		return "redirect:/lu/report/listReportStd.do";
	}  	
  	@RequestMapping(value = "/lu/report/deleteReportSubmit.do")
	public String deleteReportSubmit(@ModelAttribute("frmReport") ReportVO reportVO, ModelMap model) throws Exception {
		
  		LOG.debug("#### URL = /lu/report/deleteReportSubmit.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(reportVO); // session의 정보를 VO에 추가.
  		reportService.deleteReportSubmit(reportVO);
  		// View호출
		return "redirect:/lu/report/listReportStd.do";
	}
  	

  	@RequestMapping(value = "/lu/report/listReportExcel.do")
	public ModelAndView listReportExcel(@ModelAttribute("frmReport") ReportVO reportVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /lu/report/listReportExcel.do" );
		String retMsg = "";
  		HttpSession httpSession = request.getSession();
  		
  		ModelAndView mav = new ModelAndView("zipdownloadView");
  		
  		CurrProcVO currProcVO=new CurrProcVO();
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(reportVO); // session의 정보를 VO에 추가.
		loginInfo.putSessionToVo(currProcVO); // session의 정보를 VO에 추가.
  
		reportVO.setYyyy((String)httpSession.getAttribute(Globals.YYYY));
		reportVO.setTerm((String)httpSession.getAttribute(Globals.TERM));
		reportVO.setSubClass((String)httpSession.getAttribute(Globals.CLASS));
		reportVO.setSubjectCode((String)httpSession.getAttribute(Globals.SUBJECT_CODE));
	
		currProcVO.setYyyy(reportVO.getYyyy());
		currProcVO.setTerm(reportVO.getTerm());
		currProcVO.setSubClass(reportVO.getSubClass() );
		currProcVO.setSubjectCode(reportVO.getSubjectCode()); 
		
		
		currProcVO = reportService.getCurrproc(currProcVO);
		reportVO=reportService.getReport(reportVO);
		
		List<ReportVO> result=reportService.listReportSubmit(reportVO);
		
	    String fileStr = "";
        String oldFileStr = "";
        String atchFileId = "";
		for(int a=0;result.size()>a ;a++){
			atchFileId = result.get(a).getAtchFileId();
			AtchFileVO atchFileVO1 = new AtchFileVO();
			atchFileVO1.setFileSn(1);
			atchFileVO1.setAtchFileId(atchFileId);
			if(atchFileId!=null&&!atchFileId.equals("")){
				AtchFileVO resultfile = atchFileService.getAtchFile(atchFileVO1);			
				//fileStr += resultfile.getOrgFileName()+"?";
				fileStr += result.get(a).getMemId()+"_"+result.get(a).getMemName()+"."+resultfile.getFileExtn() + "?";	
				
	            oldFileStr += resultfile.getFileSavePath()+"/"+resultfile.getSaveFileName()+"?";				
			}
		}
		
		if(fileStr != null && !fileStr.equals("")) {
            mav.addObject("fileStr", fileStr);
            mav.addObject("oldFileStr", oldFileStr);
            //다운로드 완료 정보 수정
		}else{
			retMsg = "제출한 과제가 없습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			mav.setViewName("redirect:/lu/report/getReport.do?reportId="+reportVO.getReportId()+"&retMsgEncode="+ retMsg);
		}
		// View호출
		return mav;
	}  	
}
