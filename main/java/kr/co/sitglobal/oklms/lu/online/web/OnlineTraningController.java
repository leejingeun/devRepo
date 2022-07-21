/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 07. 01.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.lu.online.web;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.co.sitglobal.ifx.service.CmsIfxService;
import kr.co.sitglobal.ifx.vo.CmsCourseBaseVO;
import kr.co.sitglobal.ifx.vo.CmsCourseContentItemSubItemListVO;
import kr.co.sitglobal.ifx.vo.CmsCourseContentVO;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.lu.online.service.OnlineTraningService;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningSchVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningWeekVO;
import kr.co.sitglobal.oklms.lu.subject.service.SubjectService;
import kr.co.sitglobal.oklms.lu.subject.vo.SubjectVO;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.Globals;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class OnlineTraningController extends BaseController{
	private static final Logger LOG = LoggerFactory.getLogger(OnlineTraningController.class);
	
	@Resource(name = "OnlineTraningService")
	private OnlineTraningService onlineTraningService;
	/*
	@Resource(name = "companyService")
	private CompanyService companyService;*/
	
	@Resource(name = "SubjectService")
	private SubjectService subjectService;

	@Resource(name = "beanValidatorJSR303")
	Validator validator;
	
	@Resource(name = "cmsIfxService")
	private CmsIfxService cmsIfxService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder){
		try {
			dataBinder.setValidator(this.validator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/lu/online/listOnlineTraning.do")
	public String listOnlineTraning(@ModelAttribute("frmTraning") OnlineTraningSchVO onlineTraningSchVO, ModelMap model,  HttpServletRequest request) throws Exception {
		
		LOG.debug("#### URL = /lu/online/listOnlineTraning.do" );
		String retMsg = "";
  		HttpSession httpSession = request.getSession();
  		
  		SubjectVO subjectVO = new SubjectVO();
  		OnlineTraningVO onlineTraningVO = new OnlineTraningVO();
  		
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();

		loginInfo.putSessionToVo(subjectVO); // session의 정보를 VO에 추가.
 
		subjectVO.setYyyy((String)httpSession.getAttribute(Globals.YYYY));
		subjectVO.setTerm((String)httpSession.getAttribute(Globals.TERM));
		subjectVO.setSubjectClass((String)httpSession.getAttribute(Globals.CLASS));
		subjectVO.setSubjectCode((String)httpSession.getAttribute(Globals.SUBJECT_CODE));

		onlineTraningSchVO.setYyyy((String)httpSession.getAttribute(Globals.YYYY));
		onlineTraningSchVO.setTerm((String)httpSession.getAttribute(Globals.TERM));
		onlineTraningSchVO.setSubClass((String)httpSession.getAttribute(Globals.CLASS));
		onlineTraningSchVO.setSubjectCode((String)httpSession.getAttribute(Globals.SUBJECT_CODE));
		
		onlineTraningVO.setYyyy((String)httpSession.getAttribute(Globals.YYYY));
		onlineTraningVO.setTerm((String)httpSession.getAttribute(Globals.TERM));
		onlineTraningVO.setSubjectClass((String)httpSession.getAttribute(Globals.CLASS));
		onlineTraningVO.setSubjectCode((String)httpSession.getAttribute(Globals.SUBJECT_CODE));
				
		logger.debug("#### getYyyy=" + subjectVO.getYyyy() );
		logger.debug("#### getTerm=" + subjectVO.getTerm() );
		logger.debug("#### getSubClass=" + subjectVO.getSubjectClass() );
		logger.debug("#### getSubjectCode=" + subjectVO.getSubjectCode() );
		
		List<OnlineTraningSchVO> resultList = onlineTraningService.listOnlineTraningSchedule(onlineTraningSchVO);
		
		List<OnlineTraningSchVO> scheduleList = onlineTraningService.listOnlineTraningAllWeekSchedule(onlineTraningSchVO);
		
		subjectVO = subjectService.getSubjectInfo(subjectVO);
		
		onlineTraningVO = onlineTraningService.getOnlineTraningStand(onlineTraningVO);
		
		if(null == subjectVO){
			retMsg = "교과정보가 없습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
		}
		if(null != subjectVO.getSubjectTraningType()){
			if(subjectVO.getSubjectTraningType().equals("OJT")){
				retMsg = "OJT 교과는 온라인훈련을 지원하지 않습니다.";
				retMsg = URLEncoder.encode( retMsg ,"UTF-8");
				logger.debug("#### retMsg=" + retMsg );
				return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
			}
		}
		if(null != subjectVO.getOnlineType()){
			if(subjectVO.getOnlineType().equals("NONE")){
				retMsg = "해당 교과는 온라인훈련을 지원하지 않습니다.";
				retMsg = URLEncoder.encode( retMsg ,"UTF-8");
				logger.debug("#### retMsg=" + retMsg );
				return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
			}
		}
		
		if( resultList.size() == 0 ){
			retMsg = "교과정보에 해당하는 주차정보가 없습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
		}
		
		// 학점형일 경우 걸러낼껀가 확인필요
		model.addAttribute("subjectVO", subjectVO);
		model.addAttribute("onlineTraningVO", onlineTraningVO);
		model.addAttribute("resultList", resultList);
		model.addAttribute("scheduleList", scheduleList);
		
		// View호출
		return "oklms/lu/online/listOnlineTraning";
	}
	
	@RequestMapping(value = "/lu/online/popupOnlineTraning.do")
	public String popupOnlineTraning(@ModelAttribute("frmPop") CmsCourseBaseVO cmsCourseBaseVO, ModelMap model) throws Exception {
		
		
		// 버전목록이 등록되었는지 확인
		int iResult = cmsIfxService.getCourseContentSummaryCnt();
		
		// 등록 된 버전 목록이 없다면 CMS와 연계해 데이터 등록
		if(iResult == 0 ) {
			cmsCourseBaseVO.setOrderByCode(1);
			cmsCourseBaseVO.setAddURL("getCourseContentSummaryList");
			cmsIfxService.insertCourseContentSummary(cmsCourseBaseVO);
		}
			
		// 등록 된 콘텐츠 별 버전목록을 가져온다.
		List<CmsCourseContentVO> resultList = cmsIfxService.listCourseContentSummary(cmsCourseBaseVO);
		
		for(int i=0; i < resultList.size(); i++){
			CmsCourseContentVO vo = resultList.get(i);
		}
		
		Integer pageSize = cmsCourseBaseVO.getPageSize();
		Integer page = cmsCourseBaseVO.getPageIndex();
		
		int totalCnt = 0;
		if( 0 < resultList.size() ){
			totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
		}
		int totalPage = (int) Math.ceil(totalCnt / pageSize);

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(cmsCourseBaseVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(cmsCourseBaseVO.getPageUnit());
        paginationInfo.setPageSize(cmsCourseBaseVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);
        
		
        model.addAttribute("cmsCourseBaseVO", cmsCourseBaseVO);
        model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("resultList", resultList);
		// View호출
		return "oklms_popup/lu/online/popupOnlineTraning";
	}
	
	
	@RequestMapping(value = "/lu/online/saveOnlineTraningStand.json")
	public @ResponseBody Map<String, Object> saveOnlineTraningStand(@ModelAttribute("frmStand") OnlineTraningVO onlineTraningVO 
		,RedirectAttributes redirectAttributes
		,SessionStatus status
		,HttpServletRequest request
		,ModelMap model) throws Exception {
		Map<String , Object> returnMap = new HashMap<String , Object>();
		
		LOG.debug("#### URL = /lu/online/saveOnlineTraningStand.json" );
		
		logger.debug("#### getYyyy=" + onlineTraningVO.getYyyy() );
		logger.debug("#### getTerm=" + onlineTraningVO.getTerm() );
		logger.debug("#### getSubClass=" + onlineTraningVO.getSubjectClass() );
		logger.debug("#### getSubjectCode=" + onlineTraningVO.getSubjectCode() );
		
		int iResult = 0;
		if(onlineTraningVO.getSubjOnStandSeq() == null || onlineTraningVO.getSubjOnStandSeq().equals("") ){
			logger.debug("#### action = insertOnlineTraningStand" );
			iResult = onlineTraningService.insertOnlineTraningStand(onlineTraningVO);
		} else {
			logger.debug("#### action = updateOnlineTraningStand" );
			iResult = onlineTraningService.updateOnlineTraningStand(onlineTraningVO);
		}
		onlineTraningVO = onlineTraningService.getOnlineTraningStand(onlineTraningVO);
		
		if(iResult > 0){
			returnMap.put("retCd", "10000");
			returnMap.put("retData", onlineTraningVO.getSubjOnStandSeq());
		} else {
			returnMap.put("retCd", "");
			returnMap.put("retData", "");
		}
		return returnMap;
	 }
		
	
	@RequestMapping(value = "/lu/online/insertOnlineTraningWeekSchedule.json")
	public @ResponseBody Map<String, Object>  insertOnlineWeekSchedule(@ModelAttribute("frmWeekSch") OnlineTraningWeekVO onlineTraningWeekVO
			,RedirectAttributes redirectAttributes
			,SessionStatus status
			,HttpServletRequest request
			,ModelMap modelt) throws Exception {	
		
		Map<String , Object> returnMap = new HashMap<String , Object>();
		LOG.debug("#### URL = /lu/online/insertOnlineTraningWeekSchedule.json" );
		
		HttpSession httpSession = request.getSession();

		onlineTraningWeekVO.setYyyy((String)httpSession.getAttribute(Globals.YYYY));
		onlineTraningWeekVO.setTerm((String)httpSession.getAttribute(Globals.TERM));
		onlineTraningWeekVO.setSubjectClass((String)httpSession.getAttribute(Globals.CLASS));
		onlineTraningWeekVO.setSubjectCode((String)httpSession.getAttribute(Globals.SUBJECT_CODE));
		
        int iResult = onlineTraningService.insertOnlineWeekSchedule(onlineTraningWeekVO);
       
        if(iResult > 0){
			returnMap.put("retCd", "10000");
		} else {
			returnMap.put("retCd", "");
		}
		
		return returnMap;
	}
		
	
	@RequestMapping(value = "/lu/online/listOnlineTraningWeekSchedule.json")
	public @ResponseBody Map<String, Object> listOnlineTraningWeekSchedule(@ModelAttribute("frmLesson") OnlineTraningSchVO onlineTraningSchVO 
		,RedirectAttributes redirectAttributes
		,SessionStatus status
		,HttpServletRequest request
		,ModelMap model) throws Exception {
		Map<String , Object> returnMap = new HashMap<String , Object>();
		
		LOG.debug("#### URL = /lu/online/listOnlineTraningWeekSchedule.json" );
		
		List<OnlineTraningSchVO> resultList = onlineTraningService.listOnlineTraningWeekSchedule(onlineTraningSchVO);
		
		for(int i=0; i<resultList.size(); i++){
			OnlineTraningSchVO vo = new OnlineTraningSchVO();
			vo = resultList.get(i);
			LOG.debug("--------------------------- vo.getCmsLessonId() :  "+vo.getCmsLessonId());
		}
		
		if(resultList.size() > 0){
			returnMap.put("retCd", "200");
			returnMap.put("retData", resultList);
		} else {
			returnMap.put("retCd", "");
			returnMap.put("retData", "");
		}
		return returnMap;
	 }
	
	
	@RequestMapping(value = "/lu/online/lessonList.json")
	public @ResponseBody Map<String, Object> listLesson(@ModelAttribute("frmLesson") CmsCourseBaseVO cmsCourseBaseVO 
		,RedirectAttributes redirectAttributes
		,SessionStatus status
		,HttpServletRequest request
		,ModelMap model) throws Exception {
		
		Map<String , Object> returnMap = new HashMap<String , Object>();
		String retCd = "SUCCESS";
		String retMsg = "";
		try {
		   cmsCourseBaseVO.setAddURL("viewLesson");  
		   List<CmsCourseContentItemSubItemListVO> data  = cmsIfxService.viewLessonList(cmsCourseBaseVO);
		   returnMap.put("retCd", retCd);
		   returnMap.put("retMsg", retMsg);
		   returnMap.put("retData", data);
		   LOG.debug("#### URL = /lu/online/lessonList.json : "+data.toString() );
		   
		 } catch (Exception e) {
		    e.printStackTrace();
		 }
		
		return returnMap;
	}
	
	@RequestMapping(value = "/lu/online/lesson.json")
	public @ResponseBody Map<String, Object> lesson(@ModelAttribute("frmLesson") CmsCourseBaseVO cmsCourseBaseVO 
		,RedirectAttributes redirectAttributes
		,SessionStatus status
		,HttpServletRequest request
		,ModelMap model) throws Exception {
		
		Map<String , Object> returnMap = new HashMap<String , Object>();
		String retCd = "SUCCESS";
		String retMsg = "";
		try {
		   cmsCourseBaseVO.setAddURL("viewLesson");  
		   HashMap<String, String>  data = cmsIfxService.viewLesson(cmsCourseBaseVO);
		   JSONObject jsonObj = new JSONObject();
		   jsonObj.put("result", data);
		   
		   LOG.debug("=======================================================  ");
		   
		   retMsg = jsonObj.toString();
		 } catch (Exception e) {
		    e.printStackTrace();
		 }
		
		
		returnMap.put("retCd", retCd);
		returnMap.put("retMsg", retMsg);
		return returnMap;
	}
	
	
	@RequestMapping(value = "/lu/online/onlineNote.json")
	public @ResponseBody Map<String, Object> onlineNote(@ModelAttribute("frmTran") CmsCourseBaseVO cmsCourseBaseVO 
		,RedirectAttributes redirectAttributes
		,SessionStatus status
		,HttpServletRequest request
		,ModelMap model) throws Exception {
		
		Map<String , Object> returnMap = new HashMap<String , Object>();
		String retCd = "SUCCESS";
		String retMsg = "";
		try {
		   cmsCourseBaseVO.setAddURL("viewLesson");  
		   HashMap<String, String>  data = cmsIfxService.viewLesson(cmsCourseBaseVO);
		   JSONObject jsonObj = new JSONObject();
		   jsonObj.put("result", data);
		   retMsg = jsonObj.toString();
		 } catch (Exception e) {
		    e.printStackTrace();
		 }
		
		
		returnMap.put("retCd", retCd);
		returnMap.put("retMsg", retMsg);
		return returnMap;
	}
	
	@RequestMapping(value = "/lu/online/getOnlineTraningMovieViewer.do")
	public String getOnlineTraningMovieViewer(@ModelAttribute("frmLesson")  ModelMap model,  HttpServletRequest request) throws Exception {
		LOG.debug("#### URL = /lu/online/getOnlineTraningMovieViewer.do" );
		// View호출
		return "cmm/viewer/movieViewer";
	}
	
	
	/*@RequestMapping(value = "/lu/online/popupOnlineTraning.do")
	public String popupOnlineTraning(@ModelAttribute("frmPop") CmsCourseBaseVO cmsCourseBaseVO, ModelMap model) throws Exception {
		
		
		
		cmsCourseBaseVO.setOrderByCode(1);
		cmsCourseBaseVO.setAddURL("getCourseContentSummaryList");
				
		List<CmsCourseContentVO> result = cmsIfxService.getCourseContentSummaryList(cmsCourseBaseVO);
		
		
		
		List<CmsCourseContentVO> resultList = new ArrayList<CmsCourseContentVO>();
		
		for(int i=0; i < result.size(); i++){
			CmsCourseContentVO contentVO = (CmsCourseContentVO)result.get(i);
			String course_code = contentVO.getCourse_code();
			boolean addFlag1 = false;
			boolean addFlag2 = false;
			boolean addFlag3 = false;
			boolean addFlag4 = false;
			// 평생 과정만
			if(course_code.toLowerCase().indexOf("life") > 0){
				if(course_code.length() > 5){
					contentVO.setYear(course_code.substring(1, 5));
				}
				
				if(!StringUtils.isBlank(cmsCourseBaseVO.getSearchYear()) && !StringUtils.isBlank(cmsCourseBaseVO.getSearchInstitutionId()) && !StringUtils.isBlank(cmsCourseBaseVO.getSearchWrd())
						 && ( !StringUtils.isBlank(cmsCourseBaseVO.getSearchStDate()) && !StringUtils.isBlank(cmsCourseBaseVO.getSearchEdDate()) ) ) {
					
					if(contentVO.getYear().equals(cmsCourseBaseVO.getSearchYear()) ){
						//System.out.println("========== contentVO.getYear() : "+contentVO.getYear());
						//System.out.println("========== cmsCourseBaseVO.getSearchYear() : "+contentVO.getSearchYear());
						System.out.println("-------------- year");
						//resultList.add(contentVO);
						continue;
					} 
					
					if(course_code.length() > 0){
						
						if(cmsCourseBaseVO.getSearchInstitutionId().equals("A") && course_code.substring(0,1).equals("A") ){
							System.out.println("------------------------------------------------------ A");
							//resultList.add(contentVO);
							//continue;
						} else if(cmsCourseBaseVO.getSearchInstitutionId().equals("B") && !course_code.substring(0,1).equals("A")){
							System.out.println("------------------------------------------------------ !B");
							//resultList.add(contentVO);
							//continue;
						} 
						
					}
					if(!cmsCourseBaseVO.getSearchWrd().trim().equals("")){
						if(contentVO.getSubtitle().indexOf(cmsCourseBaseVO.getSearchWrd()) != -1 ){
							
							//	System.out.println("========== contentVO.getSubtitle() : "+contentVO.getSubtitle());
							//	System.out.println("========== contentVO.getSearchWrd() : "+contentVO.getSearchWrd());
								System.out.println("-------------- wrd");
								
								//resultList.add(contentVO);
								//continue;
						} 
					}
					
					if(contentVO.getRegistered_date().length() > 10){
						
						String registeredDate = contentVO.getRegistered_date().substring(0, 10).replaceAll("\\-", "");
						String searchStDate = cmsCourseBaseVO.getSearchStDate().replaceAll("\\-", "");
						String searchEdDate = cmsCourseBaseVO.getSearchEdDate().replaceAll("\\-", "");
						
						if(!StringUtils.isBlank(cmsCourseBaseVO.getSearchStDate()) && !StringUtils.isBlank(cmsCourseBaseVO.getSearchEdDate())){
							if( Integer.parseInt(registeredDate) >= Integer.parseInt(searchStDate) && Integer.parseInt(registeredDate) <= Integer.parseInt(searchEdDate) ){
								System.out.println("-------------- date");
							//	System.out.println("========== registeredDate : "+registeredDate);
							//	System.out.println("========== searchStDate : "+searchStDate);
							//	System.out.println("========== searchEdDate : "+searchEdDate);
								
								//resultList.add(contentVO);
								//continue;
							} 
						}
					}
					
				} else {
					resultList.add(contentVO);
				}
			}
		}
		
			
		Collections.reverse(resultList);
		
		Integer pageSize = cmsCourseBaseVO.getPageSize();
		Integer page = cmsCourseBaseVO.getPageIndex();
		Integer pageTemp = cmsCourseBaseVO.getPageIndex();
		
		System.out.println("================ cmsCourseBaseVO.getPageIndex() : "+cmsCourseBaseVO.getPageIndex());
		
		if(1 != page){
			pageSize = pageTemp*10;
			page = pageSize-9;
		} else if(1 == page){
			page = 1;
			pageSize = 10;
		}
		
		
		
		//int totalPage = (int) Math.ceil(totalCnt / pageSize);
		
		int totalCnt = 0;
		if( 0 < resultList.size() ){
			totalCnt = resultList.size();
		}
        
        System.out.println("pageSize : "+pageSize);
        System.out.println("totalCount : "+totalCnt);
        System.out.println("pageIndex : "+page);
        System.out.println("page : "+page);
        
        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(cmsCourseBaseVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(cmsCourseBaseVO.getPageUnit());
        paginationInfo.setPageSize(cmsCourseBaseVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);
        
        System.out.println("---------------------------------");
		System.out.println("currentPageNo : "+paginationInfo.getCurrentPageNo());
		System.out.println("recordCountPerPage : "+paginationInfo.getRecordCountPerPage());
		System.out.println("pageSize : "+paginationInfo.getPageSize());
		System.out.println("totalRecordCount : "+paginationInfo.getTotalRecordCount());
		System.out.println("---------------------------------");
		
		model.addAttribute("pageSize", cmsCourseBaseVO.getPageUnit());
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", cmsCourseBaseVO.getPageIndex());
        model.addAttribute("page", cmsCourseBaseVO.getPageIndex());
        model.addAttribute("cmsCourseBaseVO", cmsCourseBaseVO);

        
        model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("resultList", resultList);
		// View호출
		return "oklms_popup/lu/online/popupOnlineTraning";
	}*/
	
	
	/*@RequestMapping(value = "/lu/online/popupOnlineTraning.do")
	public String popupOnlineTraning(@ModelAttribute("frmPop") CmsCourseBaseVO cmsCourseBaseVO, ModelMap model) throws Exception {
		
		//if(cmsCourseBaseVO.getPage() == 0) cmsCourseBaseVO.setPage(1); // 페이지수
		//cmsCourseBaseVO.setCount(10); // 페이지수
			
		cmsCourseBaseVO.setAddURL("getCourseCodeSummaryList");
		List<CmsCourseCodeVO> resultCodeList = cmsIfxService.getCourseCodeSummaryList(cmsCourseBaseVO);
		List<CmsCourseContentVO> result = null;
		List<CmsCourseContentVO> resultList = new ArrayList<CmsCourseContentVO>();
		if(resultCodeList.size() > 0){
			LOG.debug("============================= resultCodeList.size() :  "+resultCodeList.size());
			for(int i=0; i < resultCodeList.size(); i++){
				CmsCourseCodeVO courseCodeVO = (CmsCourseCodeVO)resultCodeList.get(i);
				
				LOG.debug("============================= vo.getId() :  "+courseCodeVO.getId());
				
				
				cmsCourseBaseVO.setOrderByCode(1);
				//cmsCourseBaseVO.setIsAvailable(1); // 게시여부
				cmsCourseBaseVO.setAddURL("getCourseContentSummaryList");
				cmsCourseBaseVO.setCourseCodeId(courseCodeVO.getId());
				
				result = cmsIfxService.getCourseContentSummaryList(cmsCourseBaseVO);
				LOG.debug("============================= vo.getId() :  "+courseCodeVO.getId());
				LOG.debug("============================= result.size()) :  "+result.size());
				//if(result.size() > 0){
					for(int x=0; x < result.size(); x++){
						CmsCourseContentVO contentVO = (CmsCourseContentVO)result.get(x);
						contentVO.setCategory_name(courseCodeVO.getCategory_name());
						contentVO.setYear(courseCodeVO.getYear());
						contentVO.setTitle(courseCodeVO.getTitle());
						System.out.println("last_modified_from_device_type_code : "+contentVO.getLast_modified_from_device_type_code());
						
						System.out.println("getRegistered_from_device_type_code_name : "+contentVO.getRegistered_from_device_type_code_name());
						
						resultList.add(contentVO);
					}
				//}
				
			}
		}
		
		
		
		
		
		//String total_count = "0";
		//List<CmsCourseContentVO> resultList = cmsIfxService.getCourseContentSummaryList(cmsCourseBaseVO);
		
		//List<CmsCourseContentVO> resultList = cmsIfxService.getCourseCodeSummaryAllList(cmsCourseBaseVO);
		
		System.out.println("===================== resultList :  "+resultList.size());
		//if( resultList.size() > 0 ){
		//	CmsCourseContentVO vo = (CmsCourseContentVO)resultList.get(0);
		//	total_count = vo.getTotal_count();
		//}
		
		Collections.sort(users, new NameAscCompare());
		System.out.printf("\n\n===== 문자 오름 차순 정렬 =====\n");
		for (User temp : users) {
			System.out.println(temp);
		}
		
		Integer pageSize = cmsCourseBaseVO.getPageSize();
		Integer page = cmsCourseBaseVO.getPageIndex();
		Integer pageTemp = cmsCourseBaseVO.getPageIndex();
		
		if(1 != page){
			pageSize = pageTemp*10;
			page = pageSize-9;
		} else if(1 == page){
			page = 1;
			pageSize = 10;
		}
		
		int totalCnt = 0;
		if( 0 < resultList.size() ){
			totalCnt = resultList.size();
		}
		int totalPage = (int) Math.ceil(totalCnt / pageSize);

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);
        model.addAttribute("page", page);
        
        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(cmsCourseBaseVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(cmsCourseBaseVO.getPageUnit());
        paginationInfo.setPageSize(cmsCourseBaseVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);
        
        model.addAttribute("paginationInfo", paginationInfo);
		
		//model.addAttribute("pageSize", cmsCourseBaseVO.getCount());
		//model.addAttribute("pageIndex", cmsCourseBaseVO.getPage());
		//model.addAttribute("totalCount", total_count);
		
		model.addAttribute("resultList", resultList);
		// View호출
		return "oklms_popup/lu/online/popupOnlineTraning";
	}*/
	
	/*
	 * @RequestMapping(value = "/lu/online/getOnlineTraning.do")
	public String getCourseContent(@ModelAttribute("frmTraning") CmsCourseBaseVO cmsCourseBaseVO, ModelMap model) throws Exception {
		
		cmsCourseBaseVO.setAddURL("getCourseContentSummaryList");  
		cmsCourseBaseVO.setPage(0);
		List<CmsCourseContentVO> resultList = cmsIfxService.getCourseContentSummaryList(cmsCourseBaseVO);
		model.addAttribute("resultList", resultList);
		// View호출
		return "oklms_popup/lu/online/getCourseContent";
	}*/
	
	@RequestMapping(value = "/lu/online/getOnlineTraning.json")
	public @ResponseBody Map<String, Object> traningNote(@ModelAttribute("frmTraning") CmsCourseBaseVO cmsCourseBaseVO 
		,RedirectAttributes redirectAttributes
		,SessionStatus status
		,HttpServletRequest request
		,ModelMap model) throws Exception {
		Map<String , Object> returnMap = new HashMap<String , Object>();
		try {
			cmsCourseBaseVO.setPage(0);
			cmsCourseBaseVO.setAddURL("getCourseContentItemList");  
			//List<CmsCourseContentItemListVO>  data = cmsIfxService.getCourseContentItemList(cmsCourseBaseVO);
			String cmsData = cmsIfxService.getCmsData(cmsCourseBaseVO);
			System.out.println("======================  cmsData : "+cmsData);
			
			LOG.debug("=========================== cmsData : "+cmsData.toString());
			
			JsonParser 		parser 			= new JsonParser();
			JsonElement 	jsonTree 		= parser.parse(cmsData);
			JsonObject 		jsonObject 	= jsonTree.getAsJsonObject();
			String			 	retCd 			= jsonObject.get("code").toString();
			
			
			if(retCd.equals("10000")){
				returnMap.put("retCd", "10000");
				returnMap.put("retData", cmsData);
			} else {
				returnMap.put("retCd", "");
				returnMap.put("retData", "");
			}
		 } catch (Exception e) {
			returnMap.put("retCd", "");
			returnMap.put("retData", "");
		    e.printStackTrace();
		 }
		
		return returnMap;
	}
	
	@RequestMapping(value = "/lu/online/listOnlineTraning.json")
	public @ResponseBody Map<String, Object> traningNote(@RequestParam Map<String, Object> commandMap, @ModelAttribute("frmTran") CmsCourseBaseVO cmsCourseBaseVO 
		,RedirectAttributes redirectAttributes
		,SessionStatus status
		,HttpServletRequest request
		,ModelMap model) throws Exception {
		
		Map<String , Object> returnMap = new HashMap<String , Object>();
		String retCd = "SUCCESS";
		String retMsg = "";
		try {
		   String strId = (String) commandMap.get("id");
		   cmsCourseBaseVO.setAddURL("getCourseContentItemList");  
		   cmsCourseBaseVO.setCourseContentId(strId); 
		   String  data = cmsIfxService.getCmsData(cmsCourseBaseVO);
		   //JSONObject jsonObj = new JSONObject();
		   //jsonObj.put("result", data);
		   //retMsg = jsonObj.toString();
		   retMsg = data;
		 } catch (Exception e) {
		    e.printStackTrace();
		 }
		
		returnMap.put("retCd", retCd);
		returnMap.put("retMsg", retMsg);
		return returnMap;
	}
	
	/*
	@RequestMapping(value = "/lu/traning/traningNote.json")
	public @ResponseBody Map<String, Object> traningNote(@ModelAttribute("frmTraning") CmsCourseBaseVO cmsCourseBaseVO 
		,RedirectAttributes redirectAttributes
		,SessionStatus status
		,HttpServletRequest request
		,ModelMap model) throws Exception {
		
		Map<String , Object> returnMap = new HashMap<String , Object>();
		String retCd = "SUCCESS";
		String retMsg = "";
		try {
		   cmsCourseBaseVO.setAddURL("viewLesson");  
		   HashMap<String, String>  data = cmsIfxService.viewLesson(cmsCourseBaseVO);
		   JSONObject jsonObj = new JSONObject();
		   jsonObj.put("result", data);
		   retMsg = jsonObj.toString();
		 } catch (Exception e) {
		    e.printStackTrace();
		 }
		
		
		returnMap.put("retCd", retCd);
		returnMap.put("retMsg", retMsg);
		return returnMap;
	}*/
	
	/**
	 * 훈련과정관리 목록 화면.
	 * @param searchKeyword1
	 * 
	 * @param searchKeyword2
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping(value = "/{userType}/traning/listTraningProcessManage.do")
	public String listTraningProcessManage(@PathVariable("userType") String userType, @ModelAttribute("frmTraning") TraningVO traningVO, ModelMap model) throws Exception {
		
		CompanyVO companyVO =  new CompanyVO();
		List<TraningVO> listTraningProcessManage = traningService.listTraningProcessManage(traningVO);
		List<CompanyVO> listCompany = companyService.listCompany(companyVO);
		
		model.addAttribute("resultTraningProcessList", listTraningProcessManage);
		model.addAttribute("resultCompanyList", listCompany);
		model.addAttribute("traningVO", traningVO);
		
		// View호출
		return "oklms/"+userType+"/traning/traningProcessManageList";
	}*/
}
