/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2017. 03. 27.    First Draft.( Auto Code Generate )
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.lu.traningchange.web;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.commbiz.cmmcode.vo.CommonCodeVO;
import kr.co.sitglobal.oklms.la.company.service.CompanyService;
import kr.co.sitglobal.oklms.la.company.vo.CompanyVO;
import kr.co.sitglobal.oklms.lu.currproc.service.CurrProcService;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.subject.service.SubjectService;
import kr.co.sitglobal.oklms.lu.subject.vo.SubjectVO;
import kr.co.sitglobal.oklms.lu.traning.service.TraningService;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningScheduleVO;
import kr.co.sitglobal.oklms.lu.traningchange.service.TraningChangeService;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import egovframework.com.cmm.util.EgovDoubleSubmitHelper;






import egovframework.com.cmm.service.Globals;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class TraningChangeController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(TraningChangeController.class);

	@Resource(name = "traningChangeService")
	private TraningChangeService traningChangeService;

	@Resource(name = "traningService")
	private TraningService traningService;

	@Resource(name = "SubjectService")
	private SubjectService subjectService;

	@Resource(name = "companyService")
	private CompanyService companyService;

	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;

	@Resource(name = "currProcService")
	private CurrProcService currProcService;

	@Resource(name = "atchFileService")
	private AtchFileService atchFileService;

	@Resource(name = "beanValidatorJSR303")
	Validator validator;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder){
		try {
			dataBinder.setValidator(this.validator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//변경신청 목록
	@RequestMapping(value = "/lu/traningChange/listTraningChangeScheduleApplication.do")
	public String listTraningChangeScheduleApplication(@RequestParam Map<String, Object> commandMap, @ModelAttribute("frmTraning") TraningScheduleVO traningScheduleVO, ModelMap model, HttpServletRequest request) throws Exception {
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(traningScheduleVO);
		String returnUrl = "";
		String retMsg = "";

		CommonCodeVO codeVO = new CommonCodeVO();

		List<CommonCodeVO> companyNameCodeList = commonCodeService.selectCompanyNameCodeList(codeVO); //기업명
		List<CommonCodeVO> traningProcessCodeList = commonCodeService.selectTraningProcessCodeList(codeVO); //훈련과정명
		List<CommonCodeVO> subjectNameCodeList = commonCodeService.selectSubjectCodeList(codeVO); //개설강좌명

		model.addAttribute("companyNameCode", companyNameCodeList);
		model.addAttribute("traningProcessCode", traningProcessCodeList);
		model.addAttribute("subjectNameCode", subjectNameCodeList);

		//미승인 변경신청 목록 공통
		List<TraningScheduleVO> resultList1 = traningChangeService.listTraningChangeScheduleDisapproved(traningScheduleVO);
		if("CCN".equals(traningScheduleVO.getSessionMemType())){
			//승인된 변경이력 목록 센터담당자
			List<TraningScheduleVO> resultList2 = traningChangeService.listTraningChangeScheduleApproved(traningScheduleVO);
			model.addAttribute("resultList1", resultList1);
			model.addAttribute("resultList2", resultList2);

			returnUrl = "oklms/lu/traningchange/traningChangeApplicationCcnList";
		} else if("CCM".equals(traningScheduleVO.getSessionMemType())){
			//승인된 변경이력 목록 HRD전담자
			List<TraningScheduleVO> resultList2 = traningChangeService.listTraningChangeScheduleApproved(traningScheduleVO);
			model.addAttribute("resultList1", resultList1);
			model.addAttribute("resultList2", resultList2);

			returnUrl = "oklms/lu/traningchange/traningChangeApplicationCcmList";
		} else {
			HttpSession session = request.getSession(); // 로그인 사용자에 교과별-교과목 관련 세션 Key get 처리
			SubjectVO subjectVO = new SubjectVO();
			loginInfo.putSessionToVo(subjectVO); // session의 정보를 VO에 추가.

			String subjectTraningType = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_TRANING_TYPE) , "" );
			String subjectType = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_TYPE) , "" );
			String yyyy = StringUtils.defaultString( (String)session.getAttribute(Globals.YYYY) , "" );
			String term = StringUtils.defaultString( (String)session.getAttribute(Globals.TERM) , "" );
			String subjectCode = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_CODE) , "" );
			String subClass = StringUtils.defaultString( (String)session.getAttribute(Globals.CLASS) , "" );

			logger.debug("################################# ");
			logger.debug("#### yyyy : " + yyyy );
			logger.debug("#### term : " + term );
			logger.debug("#### subjectCode : " + subjectCode );
			logger.debug("#### subClass : " + subClass );
			logger.debug("################################# ");

			if( StringUtils.isBlank( yyyy )){
				retMsg = "Left 메뉴에서 진행중인과정에 교과목 하나를 선택하세요.";
				retMsg = URLEncoder.encode( retMsg ,"UTF-8");
				logger.debug("#### retMsg=" + retMsg );
				return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
			}

			subjectVO.setYyyy(yyyy);
	        subjectVO.setTerm(term);
	        subjectVO.setSubjectCode(subjectCode);
	        subjectVO.setSubjectClass(subClass);
	        subjectVO = subjectService.getSubjectInfo(subjectVO);
	        model.addAttribute("subjectVO", subjectVO);

			//기업현장교사
			model.addAttribute("resultList1", resultList1);

			returnUrl = "oklms/lu/traningchange/traningChangeApplicationCotList";
		}

		model.addAttribute("resultList1", resultList1); //미승인 변경신청 목록 공통
		model.addAttribute("sessionMemName", traningScheduleVO.getSessionMemName()); //파라메터 VO
		model.addAttribute("traningSchVO", traningScheduleVO); //파라메터 VO

		// View호출
		return returnUrl;
	}

	//승인된 변경신청 내역 목록 엑셀다운로드
	@RequestMapping(value = "/lu/traningChange/listTraningChangeScheduleApplicationApprovedExcelDownload.do")
	public String listTraningChangeScheduleApplicationApprovedExcelDownload(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmTraning") TraningScheduleVO traningScheduleVO, ModelMap model, HttpServletRequest request) throws Exception {

		traningScheduleVO.setPageSize(10000); // 1만건 조회
		List<TraningScheduleVO> resultList = traningChangeService.listTraningChangeScheduleApprovedExcelDownload(traningScheduleVO);
		String ExcelName = "훈련시간표 변경신청 승인내역";

        model.addAttribute("resultList", resultList);
        request.setAttribute("ExcelName", URLEncoder.encode( ExcelName.replaceAll(" ", "_") ,"UTF-8") );

		// View호출
		return "oklms_excel/lu/traningchange/traningChangeScheduleApprovedExcelList";
	}

	//훈련시간표 변경신청 상세 목록
	@RequestMapping(value = "/lu/traningChange/getTraningChangeScheduleApplication.do")
	public String getTraningChangeScheduleApplication(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmTraning") TraningScheduleVO traningScheduleVO, ModelMap model) throws Exception {
		String companyId ="";
		String traningProcessId ="";
		String yyyy ="";
		String term ="";
		String subjectCode ="";
		String subClass ="";
		String returnUrl = "";

		CompanyVO companyVO = new CompanyVO();
		CurrProcVO currProcVO = new CurrProcVO();
		CurrProcVO currProcReadVO = new CurrProcVO();
		TraningScheduleVO readVO = new TraningScheduleVO();
		//AtchFileVO atchFileVO = new AtchFileVO();

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(traningScheduleVO);

		//기업현장교사 일떼만
		if("COT".equals(traningScheduleVO.getSessionMemType())){
			String strArr[] = traningScheduleVO.getTraningProcessIdArr().split(",");
			if (strArr != null && strArr.length > 0) {
				companyId = strArr[0];
				traningProcessId = strArr[1];
				yyyy = strArr[2];
				term = strArr[3];
				subjectCode = strArr[4];
				subClass = strArr[5];

				traningScheduleVO.setCompanyId(companyId);
				traningScheduleVO.setTraningProcessId(traningProcessId);
				traningScheduleVO.setYyyy(yyyy);
				traningScheduleVO.setTerm(term);
				traningScheduleVO.setSubjectCode(subjectCode);
				traningScheduleVO.setSubClass(subClass);
			}
		}

		if(!"".equals(traningScheduleVO.getCompanyId())){
			//기업체 훈련과정 검색목록
			companyVO.setCompanyId(traningScheduleVO.getCompanyId());
			companyVO.setTraningProcessId(traningScheduleVO.getTraningProcessId());
			List<CompanyVO> listCompanyTraningProcess = companyService.listCompanyTraningProcess(companyVO);
			model.addAttribute("resultList", listCompanyTraningProcess);

			if(!"".equals(traningScheduleVO.getSearchYyyy())){
				currProcVO.setYyyy(traningScheduleVO.getSearchYyyy());
				currProcVO.setTerm(traningScheduleVO.getSearchTerm());
				currProcVO.setSubClass(traningScheduleVO.getSearchSubClass());
				currProcVO.setSubjectCode(traningScheduleVO.getSearchSubjectCode());

				currProcReadVO = currProcService.getMyTrainSubjectInfo(currProcVO);
				readVO = traningService.getTraningSubjWeekInfo(traningScheduleVO);

				model.addAttribute("currProcReadVO", currProcReadVO);
				model.addAttribute("traningChangeReadVO", readVO);

				List<TraningScheduleVO> listTraningChangeScheduleDisapprovedDetail = traningChangeService.listTraningChangeScheduleDisapprovedDetail(traningScheduleVO);
				model.addAttribute("resultTraningChangeReadList", listTraningChangeScheduleDisapprovedDetail);
				model.addAttribute("resultTraningChangeReadListCount", listTraningChangeScheduleDisapprovedDetail.size());
			}
		}

		if("CCN".equals(traningScheduleVO.getSessionMemType())){
			if("1".equals(readVO.getChangeStatus())){
				returnUrl = "oklms/lu/traningchange/traningChangeApplicationCcnRead"; //변경신청 미승인 상세
			}else{
				returnUrl = "oklms/lu/traningchange/traningChangeApplicationApprovalRead"; //변경신청 승인 상세
			}
		} else if("CCM".equals(traningScheduleVO.getSessionMemType())){
			if("1".equals(readVO.getChangeStatus())){
				returnUrl = "oklms/lu/traningchange/traningChangeApplicationCcmRead"; //변경신청 미승인 상세
			}else{
				returnUrl = "oklms/lu/traningchange/traningChangeApplicationApprovalRead"; //변경신청 승인 상세
			}
		} else {
			returnUrl = "oklms/lu/traningchange/traningChangeApplicationCotRead";
		}

		model.addAttribute("traningSchVO", traningScheduleVO); //파라메터 VO

		// View호출
		return returnUrl;
	}

	//훈련시간표 변경신청 상세 목록
	@RequestMapping(value = "/lu/traningChange/getTraningChangeScheduleApplicationCot.do")
	public String getTraningChangeScheduleApplicationCot(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmTraning") TraningScheduleVO traningScheduleVO, ModelMap model) throws Exception {
		String yyyy ="";
		String term ="";
		String subjectCode ="";
		String subClass ="";
		String returnUrl = "";

		CurrProcVO currProcVO = new CurrProcVO();
		CurrProcVO currProcReadVO = new CurrProcVO();
		TraningScheduleVO readVO = new TraningScheduleVO();
		//AtchFileVO atchFileVO = new AtchFileVO();

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(traningScheduleVO);

		String strArr[] = traningScheduleVO.getTraningProcessIdArr().split(",");
		if (strArr != null && strArr.length > 0) {
			yyyy = strArr[0];
			term = strArr[1];
			subjectCode = strArr[2];
			subClass = strArr[3];

			traningScheduleVO.setYyyy(yyyy);
			traningScheduleVO.setTerm(term);
			traningScheduleVO.setSubjectCode(subjectCode);
			traningScheduleVO.setSubClass(subClass);
		}

		currProcVO.setYyyy(traningScheduleVO.getYyyy());
		currProcVO.setTerm(traningScheduleVO.getTerm());
		currProcVO.setSubjectCode(traningScheduleVO.getSubjectCode());
		currProcVO.setSubClass(traningScheduleVO.getSubClass());

		traningScheduleVO.setSearchYyyy(traningScheduleVO.getYyyy());
		traningScheduleVO.setSearchTerm(traningScheduleVO.getTerm());
		traningScheduleVO.setSearchSubjectCode(traningScheduleVO.getSubjectCode());
		traningScheduleVO.setSearchSubClass(traningScheduleVO.getSubClass());

		currProcReadVO = currProcService.getMyTrainSubjectInfo(currProcVO);
		readVO = traningService.getTraningSubjWeekInfo(traningScheduleVO);

		model.addAttribute("currProcReadVO", currProcReadVO);
		model.addAttribute("traningChangeReadVO", readVO);

		List<TraningScheduleVO> listTraningChangeScheduleDisapprovedDetail = traningChangeService.listTraningChangeScheduleDisapprovedDetail(traningScheduleVO);
		model.addAttribute("resultTraningChangeReadList", listTraningChangeScheduleDisapprovedDetail);
		model.addAttribute("resultTraningChangeReadListCount", listTraningChangeScheduleDisapprovedDetail.size());

		returnUrl = "oklms/lu/traningchange/traningChangeApplicationCotRead";

		model.addAttribute("traningSchVO", traningScheduleVO); //파라메터 VO

		// View호출
		return returnUrl;
	}

	//훈련과정시간표 변경신청건 신규신청
	@RequestMapping(value = "/lu/traningChange/goInsertTraningChangeScheduleApplication.do")
	public String goInsertTraningChangeScheduleApplication(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmTraning") TraningScheduleVO traningScheduleVO, ModelMap model) throws Exception {
		String returnUrl = "";
		String returnMsg = "POPUP_LIST_FAIL";
		String tempTraningProcessId = StringUtils.defaultString((String)paramMap.get("tempTraningProcessId"),"");
		String searchKeyword = StringUtils.defaultString((String)paramMap.get("searchKeyword"),"");
		String searchKeywordNull = StringUtils.defaultString((String)paramMap.get("searchKeywordNull"),"");
		String pageSizeStr = StringUtils.defaultString((String)paramMap.get("pageSize"),"");
		String pageIndexStr = StringUtils.defaultString((String)paramMap.get("pageIndex"),"");

		CompanyVO companyVO = new CompanyVO();
		CommonCodeVO codeVO = new CommonCodeVO();
		CurrProcVO currProcVO = new CurrProcVO();
		CurrProcVO currProcReadVO = new CurrProcVO();
		TraningScheduleVO readVO = new TraningScheduleVO();

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(traningScheduleVO);

		if(!searchKeyword.equals("") && !"noDivPopup".equals(tempTraningProcessId)){
			companyVO.setSearchCompanyName(searchKeyword);
			returnMsg = "POPUP_LIST_SUCCESS";
			model.addAttribute("returnMsg", returnMsg);
		}

		if(!searchKeywordNull.equals("") && !"noDivPopup".equals(tempTraningProcessId)){
			companyVO.setSearchCompanyName(null);
			returnMsg = "POPUP_LIST_SUCCESS";
			model.addAttribute("returnMsg", returnMsg);
		}

		model.addAttribute("searchKeyword", searchKeyword);

		if(!"".equals(pageIndexStr) && !"noDivPopup".equals(tempTraningProcessId)){
			companyVO.setPageIndex(Integer.parseInt(pageIndexStr));
			returnMsg = "POPUP_LIST_SUCCESS";
			model.addAttribute("returnMsg", returnMsg);
		}

 		companyVO.setCompanyId(null);
		companyVO.setTraningProcessId(null);
		List<CompanyVO> listCompanyTraningProcessSearch = companyService.listCompanyTraningProcessSearch(companyVO);
		Integer pageSize = companyVO.getPageSize();
		Integer page = 1;
		if("".equals(pageIndexStr)){
			page = companyVO.getPageIndex();
		}else{
			page = Integer.parseInt(pageIndexStr);
		}

		int totalCnt = 0;
		if( 0 < listCompanyTraningProcessSearch.size() ){
			totalCnt = Integer.parseInt( listCompanyTraningProcessSearch.get(0).getTotalCount() );
		}
		int totalPage = (int) Math.ceil(totalCnt / pageSize);

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(companyVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(pageSize);
        paginationInfo.setPageSize(companyVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);

		model.addAttribute("resultSearchList", listCompanyTraningProcessSearch); //기업체 훈련과정 검색목록

		//기업체검색 팝업화면에서 훈련과정을 선택했을경우
		if(!"".equals(traningScheduleVO.getTraningProcessId())){
			companyVO.setCompanyId(traningScheduleVO.getCompanyId());
			companyVO.setTraningProcessId(traningScheduleVO.getTraningProcessId());
			List<CompanyVO> listCompanyTraningProcess = companyService.listCompanyTraningProcess(companyVO);
			model.addAttribute("resultCompanyTraningProcessList", listCompanyTraningProcess); //기업체 훈련과정 검색목록

			codeVO.setCompanyId(traningScheduleVO.getCompanyId());
			codeVO.setTraningProcessId(traningScheduleVO.getTraningProcessId());
			codeVO.setCodeGroup("TIME_HOUR");

			List<CommonCodeVO> yearSubjCodeList = commonCodeService.selectYearCodeList(codeVO); //학년도-코드
			List<CommonCodeVO> termSubjCodeList = commonCodeService.selectTermCodeList(codeVO); //학기-코드
			List<CommonCodeVO> subjNameSubjCodeList = commonCodeService.selectSubjectCodeList(codeVO); //개설강좌-코드
			List<CommonCodeVO> classSubjCodeList = commonCodeService.selectClassCodeList(codeVO); //분반-코드
			List<CommonCodeVO> timeHourCodeList = commonCodeService.selectCmmCodeList(codeVO); //날짜에 시간

			model.addAttribute("yearSubjCode", yearSubjCodeList);
			model.addAttribute("termSubjCode", termSubjCodeList);
			model.addAttribute("subjNameSubjCode", subjNameSubjCodeList);
			model.addAttribute("classSubjCode", classSubjCodeList);
			model.addAttribute("searchYyyyCodeId", yearSubjCodeList.get(0).getCodeId());
			model.addAttribute("timeHourCode", timeHourCodeList);

			//훈련시간표변경신청을 위한 기업체 개설강좌 조회 버튼을 클릭시
			if(!"".equals(traningScheduleVO.getSearchYyyy())){
				currProcVO.setYyyy(traningScheduleVO.getSearchYyyy());
				currProcVO.setTerm(traningScheduleVO.getSearchTerm());
				currProcVO.setSubClass(traningScheduleVO.getSearchSubClass());
				currProcVO.setSubjectCode(traningScheduleVO.getSearchSubjectCode());

				currProcReadVO = currProcService.getMyTrainSubjectInfo(currProcVO);
			    readVO = traningService.getTraningSubjWeekInfo(traningScheduleVO);

				model.addAttribute("currProcReadVO", currProcReadVO);
				model.addAttribute("traningChangeReadVO", readVO);

				List<TraningScheduleVO> listTraningChangeScheduleDisapprovedDetail = traningChangeService.listTraningChangeScheduleDisapprovedDetail(traningScheduleVO);
				model.addAttribute("resultTraningChangeReadList", listTraningChangeScheduleDisapprovedDetail);
				model.addAttribute("resultTraningChangeReadListCount", listTraningChangeScheduleDisapprovedDetail.size());
			}
		}

		model.addAttribute("traningSchVO", traningScheduleVO); //파라메터 VO

		if("CCN".equals(traningScheduleVO.getSessionMemType())){
			//센터전담자
			returnUrl = "oklms/lu/traningchange/traningChangeApplicationCcnCret";
		} else if("CCM".equals(traningScheduleVO.getSessionMemType())){
			//HRD전담자
			returnUrl = "oklms/lu/traningchange/traningChangeApplicationCcmCret";
		}

		// View호출
		return returnUrl;
	}

	//훈련과정시간표 변경신청건 신규신청 -> 기업현장교사용
	@RequestMapping(value = "/lu/traningChange/goInsertTraningChangeScheduleApplicationCot.do")
	public String goInsertTraningChangeScheduleApplicationCot(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmTraning") TraningScheduleVO traningScheduleVO, ModelMap model, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(); // 로그인 사용자에 교과별-교과목 관련 세션 Key get 처리

		String returnUrl = "";
		String yyyy = StringUtils.defaultString( (String)session.getAttribute(Globals.YYYY) , "" );
		String term = StringUtils.defaultString( (String)session.getAttribute(Globals.TERM) , "" );
		String subjectCode = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_CODE) , "" );
		String subClass = StringUtils.defaultString( (String)session.getAttribute(Globals.CLASS) , "" );

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(traningScheduleVO);

		CommonCodeVO codeVO = new CommonCodeVO();
		CurrProcVO currProcVO = new CurrProcVO();
		CurrProcVO currProcReadVO = new CurrProcVO();
		TraningScheduleVO readVO = new TraningScheduleVO();


		codeVO.setCodeGroup("TIME_HOUR");
		List<CommonCodeVO> timeHourCodeList = commonCodeService.selectCmmCodeList(codeVO); //날짜에 시간
		model.addAttribute("timeHourCode", timeHourCodeList);

		//훈련시간표등록을 위한 기업체 개설강좌 조회 버튼을 클릭시
		currProcVO.setYyyy(yyyy);
		currProcVO.setTerm(term);
		currProcVO.setSubjectCode(subjectCode);
		currProcVO.setSubClass(subClass);

		traningScheduleVO.setSearchYyyy(yyyy);
		traningScheduleVO.setSearchTerm(term);
		traningScheduleVO.setSearchSubjectCode(subjectCode);
		traningScheduleVO.setSearchSubClass(subClass);

		currProcReadVO = currProcService.getMyTrainSubjectInfo(currProcVO);
	    readVO = traningService.getTraningSubjWeekInfo(traningScheduleVO);

		model.addAttribute("currProcReadVO", currProcReadVO);
		model.addAttribute("traningChangeReadVO", readVO);
		model.addAttribute("traningSchVO", traningScheduleVO); //파라메터 VO

		List<TraningScheduleVO> listTraningChangeScheduleDisapprovedDetail = traningChangeService.listTraningChangeScheduleDisapprovedDetail(traningScheduleVO);
		model.addAttribute("resultTraningChangeReadList", listTraningChangeScheduleDisapprovedDetail);
		model.addAttribute("resultTraningChangeReadListCount", listTraningChangeScheduleDisapprovedDetail.size());

		returnUrl = "oklms/lu/traningchange/traningChangeApplicationCotCret";

		// View호출
		return returnUrl;
	}

	//훈련과정시간표 변경신청건 신규신청 수정-> 기업현장교사용
	@RequestMapping(value = "/lu/traningChange/goUpdateTraningChangeScheduleApplicationCot.do")
	public String goUpdateTraningChangeScheduleApplicationCot(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmTraning") TraningScheduleVO traningScheduleVO, ModelMap model) throws Exception {
		String returnUrl = "";
		String yyyy = "";
		String term = "";
		String subjectCode = "";
		String subClass = "";

		CommonCodeVO codeVO = new CommonCodeVO();
		CurrProcVO currProcVO = new CurrProcVO();
		CurrProcVO currProcReadVO = new CurrProcVO();
		TraningScheduleVO readVO = new TraningScheduleVO();

		codeVO.setCodeGroup("TIME_HOUR");
		List<CommonCodeVO> timeHourCodeList = commonCodeService.selectCmmCodeList(codeVO); //날짜에 시간
		model.addAttribute("timeHourCode", timeHourCodeList);

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(traningScheduleVO);

		String strArr[] = traningScheduleVO.getTraningProcessIdArr().split(",");
		if (strArr != null && strArr.length > 0) {
			yyyy = strArr[0];
			term = strArr[1];
			subjectCode = strArr[2];
			subClass = strArr[3];

			traningScheduleVO.setYyyy(yyyy);
			traningScheduleVO.setTerm(term);
			traningScheduleVO.setSubjectCode(subjectCode);
			traningScheduleVO.setSubClass(subClass);
		}

		currProcVO.setYyyy(traningScheduleVO.getYyyy());
		currProcVO.setTerm(traningScheduleVO.getTerm());
		currProcVO.setSubjectCode(traningScheduleVO.getSubjectCode());
		currProcVO.setSubClass(traningScheduleVO.getSubClass());

		traningScheduleVO.setSearchYyyy(traningScheduleVO.getYyyy());
		traningScheduleVO.setSearchTerm(traningScheduleVO.getTerm());
		traningScheduleVO.setSearchSubjectCode(traningScheduleVO.getSubjectCode());
		traningScheduleVO.setSearchSubClass(traningScheduleVO.getSubClass());

		currProcReadVO = currProcService.getMyTrainSubjectInfo(currProcVO);
		readVO = traningService.getTraningSubjWeekInfo(traningScheduleVO);

		model.addAttribute("currProcReadVO", currProcReadVO);
		model.addAttribute("traningChangeReadVO", readVO);

		List<TraningScheduleVO> listTraningChangeScheduleDisapprovedDetail = traningChangeService.listTraningChangeScheduleDisapprovedDetail(traningScheduleVO);
		model.addAttribute("resultTraningChangeReadList", listTraningChangeScheduleDisapprovedDetail);
		model.addAttribute("resultTraningChangeReadListCount", listTraningChangeScheduleDisapprovedDetail.size());
		model.addAttribute("traningSchVO", traningScheduleVO);

		returnUrl = "oklms/lu/traningchange/traningChangeApplicationCotUpdt";

		// View호출
		return returnUrl;
	}

	//훈련과정시간표 변경신청건 수정 저장
	@RequestMapping(value = "/lu/traningChange/goUpdateTraningChangeScheduleApplication.do")
	public String goUpdateTraningChangeScheduleApplication(@ModelAttribute("frmTraning") TraningScheduleVO traningScheduleVO, ModelMap model) throws Exception {
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(traningScheduleVO);
		String returnUrl = "";

		CompanyVO companyVO = new CompanyVO();
		CommonCodeVO codeVO = new CommonCodeVO();
		CurrProcVO currProcVO = new CurrProcVO();
		CurrProcVO currProcReadVO = new CurrProcVO();
		TraningScheduleVO readVO = new TraningScheduleVO();

		//1.HRD전담자는 센터전담자, 기업현장교사 변경신청한건을 수정할수 있다.
		//2.기업현장교사는 본인이 변경신청한건만 수정할수 있다.
		if("CCM".equals(traningScheduleVO.getSessionMemType()) || "COT".equals(traningScheduleVO.getSessionMemType())){
			String strArr[] = traningScheduleVO.getTraningProcessIdArr().split(",");
			if (strArr != null && strArr.length > 0) {
				String companyId = strArr[0];
				String traningProcessId = strArr[1];
				String yyyy = strArr[2];
				String term = strArr[3];
				String subjectCode = strArr[4];
				String subClass = strArr[5];

				traningScheduleVO.setCompanyId(companyId);
				traningScheduleVO.setTraningProcessId(traningProcessId);
				traningScheduleVO.setYyyy(yyyy);
				traningScheduleVO.setTerm(term);
				traningScheduleVO.setSubjectCode(subjectCode);
				traningScheduleVO.setSubClass(subClass);

				traningScheduleVO.setSearchYyyy(yyyy);
				traningScheduleVO.setSearchTerm(term);
				traningScheduleVO.setSearchSubjectCode(subjectCode);
				traningScheduleVO.setSearchSubClass(subClass);
			}
		}

		if(!"".equals(traningScheduleVO.getTraningProcessId())){
			companyVO.setCompanyId(traningScheduleVO.getCompanyId());
			companyVO.setTraningProcessId(traningScheduleVO.getTraningProcessId());
			List<CompanyVO> listCompanyTraningProcess = companyService.listCompanyTraningProcess(companyVO);
			model.addAttribute("resultCompanyTraningProcessList", listCompanyTraningProcess); //기업체 훈련과정 검색목록

			codeVO.setCompanyId(traningScheduleVO.getCompanyId());
			codeVO.setTraningProcessId(traningScheduleVO.getTraningProcessId());
			codeVO.setCodeGroup("TIME_HOUR");

			List<CommonCodeVO> yearSubjCodeList = commonCodeService.selectYearCodeList(codeVO); //학년도-코드
			List<CommonCodeVO> termSubjCodeList = commonCodeService.selectTermCodeList(codeVO); //학기-코드
			List<CommonCodeVO> subjNameSubjCodeList = commonCodeService.selectSubjectCodeList(codeVO); //개설강좌-코드
			List<CommonCodeVO> classSubjCodeList = commonCodeService.selectClassCodeList(codeVO); //분반-코드
			List<CommonCodeVO> timeHourCodeList = commonCodeService.selectCmmCodeList(codeVO); //날짜에 시간

			model.addAttribute("yearSubjCode", yearSubjCodeList);
			model.addAttribute("termSubjCode", termSubjCodeList);
			model.addAttribute("subjNameSubjCode", subjNameSubjCodeList);
			model.addAttribute("classSubjCode", classSubjCodeList);
			model.addAttribute("timeHourCode", timeHourCodeList);

			//훈련시간표등록을 위한 기업체 개설강좌 조회 버튼을 클릭시
			if(!"".equals(traningScheduleVO.getSearchYyyy())){
				currProcVO.setYyyy(traningScheduleVO.getSearchYyyy());
				currProcVO.setTerm(traningScheduleVO.getSearchTerm());
				currProcVO.setSubClass(traningScheduleVO.getSearchSubClass());
				currProcVO.setSubjectCode(traningScheduleVO.getSearchSubjectCode());

				currProcReadVO = currProcService.getMyTrainSubjectInfo(currProcVO);
			    readVO = traningService.getTraningSubjWeekInfo(traningScheduleVO);

				model.addAttribute("currProcReadVO", currProcReadVO);
				model.addAttribute("traningChangeReadVO", readVO);

				List<TraningScheduleVO> listTraningChangeRead = traningChangeService.listTraningChangeScheduleDisapprovedDetail(traningScheduleVO);
				model.addAttribute("resultTraningChangeReadList", listTraningChangeRead);
				model.addAttribute("resultTraningChangeReadListCount", listTraningChangeRead.size());

			}
		}

		model.addAttribute("traningSchVO", traningScheduleVO);

		if("CCM".equals(traningScheduleVO.getSessionMemType())){
			//HRD전담자
			returnUrl = "oklms/lu/traningchange/traningChangeApplicationCcmUpdt";
		}

		// View호출
		return returnUrl;
	}

	//훈련시간 변경신청건 수정, 승인, 반려처리
	@RequestMapping(value = "/lu/traningChange/updateTraningChangeScheduleApplication.do")
	public String updateTraningChangeScheduleApplication(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmTraning") TraningScheduleVO traningScheduleVO, ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(traningScheduleVO);

		String updateStatusYn = StringUtils.defaultIfBlank( (String) request.getParameter("updateStatusYn"), "");
		String addTempCount = StringUtils.defaultString( (String)paramMap.get("count") , "0" );

		String yyyy = StringUtils.defaultIfBlank( (String) request.getParameter("yyyy"), "");
		String term = StringUtils.defaultIfBlank( (String) request.getParameter("term"), "");
		String subjectCode = StringUtils.defaultIfBlank( (String) request.getParameter("subjectCode"), "");
		String subClass = StringUtils.defaultIfBlank( (String) request.getParameter("subClass"), "");

		String retMsg = "";
		String traningDate = "";
		String traningStHour = "";
		String traningStMin = "";
		String traningEdHour = "";
		String traningEdMin = "";
		String leadTime = "";
		String changeTraningDate = "";
		String changeTraningStHour = "";
		String changeTraningEdHour = "";
		String changeLeadTime = "";
		String timeId = "";

		int updateCnt1 = 0;
		int updateCnt2 = 0;
		int addCount = 0;
		addCount = Integer.parseInt(addTempCount);

		LOGGER.debug("#### addCount ==> "+addCount);

		if( StringUtils.isBlank( traningScheduleVO.getYyyy() ) ){
			if("Y".equals(updateStatusYn)){
				retMsg = "훈련시간표 변경신청건에 승인할 교과정보가 없습니다."; //변경신청건 승인처리
			}else{
				retMsg = "훈련시간표 변경신청 수정할 교과정보가 없습니다."; //변경신청건 수정처리
			}
		} else {
			if("Y".equals(updateStatusYn)){
				traningScheduleVO.setSearchYyyy(yyyy);
				traningScheduleVO.setSearchTerm(term);
				traningScheduleVO.setSearchSubjectCode(subjectCode);
				traningScheduleVO.setSearchSubClass(subClass);
				updateCnt1 = traningChangeService.updateTraningChangeSchedule(traningScheduleVO); //변경신청건 수정처리
				if(updateCnt1 > 0){
					for( int idx = 1 ; idx <= addCount ; idx++ ) {
			  			changeTraningDate = StringUtils.defaultString( (String)paramMap.get("changeTraningDate-"+idx) , "" );
			  			changeTraningStHour = StringUtils.defaultString( (String)paramMap.get("changeTraningStHour-"+idx) , "" );
			  			changeTraningEdHour = StringUtils.defaultString( (String)paramMap.get("changeTraningEdHour-"+idx) , "" );
			  			changeLeadTime = StringUtils.defaultString( (String)paramMap.get("changeLeadTime-"+idx) , "" );
			  			timeId = StringUtils.defaultString( (String)paramMap.get("timeId-"+idx) , "" );

			  			LOGGER.debug("#### idx ==> ["+idx + "] #### changeTraningDate ==> "+changeTraningDate);
						LOGGER.debug("#### idx ==> ["+idx + "] #### changeTraningStHour ==> "+changeTraningStHour);
						LOGGER.debug("#### idx ==> ["+idx + "] #### changeTraningEdHour ==> "+changeTraningEdHour);
						LOGGER.debug("#### idx ==> ["+idx + "] #### changeLeadTime ==> "+changeLeadTime);
						LOGGER.debug("#### idx ==> ["+idx + "] #### timeId ==> "+timeId);

						traningScheduleVO.setChangeTraningDate(changeTraningDate);
						traningScheduleVO.setChangeTraningStHour (changeTraningStHour);
						traningScheduleVO.setChangeTraningEdHour (changeTraningEdHour);
						traningScheduleVO.setChangeLeadTime(changeLeadTime);
						traningScheduleVO.setTimeId(timeId);

			  			updateCnt2 += traningChangeService.updateTraningChangeScheduleTime(traningScheduleVO); //변경신청건 수정처리
			  		}
				}

				if(updateCnt1 > 0 && updateCnt2 > 0){
					retMsg = "정상적으로 훈련시간변청 처리건이 (수정)처리되었습니다.!";
				}else{
					retMsg = "훈련시간변청 신건청건 (수정)처리시 에러가 발생하였습니다.!";
				}
			}else{
				LOGGER.debug("#### changeStatus ==> "+traningScheduleVO.getChangeStatus());

				//승인처리시 주차별시간에 변경후 훈련일자로 업데이트
				if("2".equals(traningScheduleVO.getChangeStatus())){
					for( int idx = 1 ; idx <= addCount ; idx++ ) {
						TraningScheduleVO changeScheduleVO = new TraningScheduleVO();
						loginInfo.putSessionToVo(changeScheduleVO);

						traningDate = StringUtils.defaultString( (String)paramMap.get("traningDate-"+idx) , "" );
			  			traningStHour = StringUtils.defaultString( (String)paramMap.get("traningStHour-"+idx) , "" );
			  			traningStMin = StringUtils.defaultString( (String)paramMap.get("traningStMin-"+idx) , "" );
			  			traningEdHour = StringUtils.defaultString( (String)paramMap.get("traningEdHour-"+idx) , "" );
			  			traningEdMin = StringUtils.defaultString( (String)paramMap.get("traningEdMin-"+idx) , "" );
			  			leadTime = StringUtils.defaultString( (String)paramMap.get("leadTime-"+idx) , "" );

			  			LOGGER.debug("#### idx ==> ["+idx + "] #### traningDate ==> "+traningDate);
						LOGGER.debug("#### idx ==> ["+idx + "] #### traningStHour ==> "+traningStHour);
						LOGGER.debug("#### idx ==> ["+idx + "] #### traningStMin ==> "+traningStMin);
						LOGGER.debug("#### idx ==> ["+idx + "] #### traningEdHour ==> "+traningEdHour);
						LOGGER.debug("#### idx ==> ["+idx + "] #### traningEdMin ==> "+traningEdMin);
						LOGGER.debug("#### idx ==> ["+idx + "] #### leadTime ==> "+leadTime);

			  			changeTraningDate = StringUtils.defaultString( (String)paramMap.get("changeTraningDate-"+idx) , "" );
			  			changeTraningStHour = StringUtils.defaultString( (String)paramMap.get("changeTraningStHour-"+idx) , "" );
			  			changeTraningEdHour = StringUtils.defaultString( (String)paramMap.get("changeTraningEdHour-"+idx) , "" );
			  			changeLeadTime = StringUtils.defaultString( (String)paramMap.get("changeLeadTime-"+idx) , "" );

			  			LOGGER.debug("#### idx ==> ["+idx + "] #### changeTraningDate ==> "+changeTraningDate);
						LOGGER.debug("#### idx ==> ["+idx + "] #### changeTraningStHour ==> "+changeTraningStHour);
						LOGGER.debug("#### idx ==> ["+idx + "] #### changeTraningEdHour ==> "+changeTraningEdHour);
						LOGGER.debug("#### idx ==> ["+idx + "] #### changeLeadTime ==> "+changeLeadTime);

						changeScheduleVO.setTraningDate(traningDate);
						changeScheduleVO.setTraningStHour(traningStHour);
						changeScheduleVO.setTraningStMin(traningStMin);
						changeScheduleVO.setTraningEdHour(traningEdHour);
						changeScheduleVO.setTraningEdMin(traningEdMin);
						changeScheduleVO.setLeadTime(leadTime);

						changeScheduleVO.setChangeTraningDate(changeTraningDate);
						changeScheduleVO.setChangeTraningStHour(changeTraningStHour);
						changeScheduleVO.setChangeTraningStMin("00");
						changeScheduleVO.setChangeTraningEdHour(changeTraningEdHour);
						changeScheduleVO.setChangeTraningEdMin("00");
						changeScheduleVO.setChangeLeadTime(changeLeadTime);

						changeScheduleVO.setSearchYyyy(traningScheduleVO.getSearchYyyy());
						changeScheduleVO.setSearchTerm(traningScheduleVO.getSearchTerm());
						changeScheduleVO.setSearchSubjectCode(traningScheduleVO.getSearchSubjectCode());
						changeScheduleVO.setSearchSubClass(traningScheduleVO.getSearchSubClass());

			  			updateCnt1 += traningChangeService.updateSubjWeekTime(changeScheduleVO); //변경신청건 수정처리
			  		}

					if(updateCnt1 == addCount){
						updateCnt2 = traningChangeService.updateTraningChangeScheduleStatus(traningScheduleVO); //변경신청건 승인처리
					}

					if(updateCnt1 > 0 && updateCnt2 > 0){
						retMsg = "정상적으로 훈련시간변청 처리건이 (승인)처리되었습니다.!";
					}else{
						retMsg = "훈련시간변청 신건청건 (승인)처리시 에러가 발생하였습니다.!";
					}
				}
			}
		}

		TraningScheduleVO VO = new TraningScheduleVO();
		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmTraning", VO);

		// View호출
		return "redirect:/lu/traningChange/listTraningChangeScheduleApplication.do";
	}

	//훈련시간 변경신청건 수정, 승인, 반려처리
	@RequestMapping(value = "/lu/traningChange/updateTraningChangeScheduleApplicationNot.do")
	public String updateTraningChangeScheduleApplicationNot(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmTraning") TraningScheduleVO traningScheduleVO, ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(traningScheduleVO);

		String retMsg = "";
		int updateCnt = 0;

		if( StringUtils.isBlank( traningScheduleVO.getYyyy() ) ){
			retMsg = "훈련시간표 변경신청건에 반려처리할 교과정보가 없습니다."; //변경신청건 반려처리
		} else {
			updateCnt = traningChangeService.updateTraningChangeScheduleStatusApprovalNot(traningScheduleVO); //변경신청건 반려처리

			if(updateCnt > 0){
				retMsg = "정상적으로 훈련시간변청 처리건이 (반려)처리되었습니다.!";
			}else{
				retMsg = "훈련시간변청 신건청건 (반려)처리시 에러가 발생하였습니다.!";
			}
		}

		TraningScheduleVO VO = new TraningScheduleVO();
		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmTraning", VO);

		// View호출
		return "redirect:/lu/traningChange/listTraningChangeScheduleApplication.do";
	}

	//HRD담당자, 기입현장교사가 삭제처리
	@RequestMapping(value = "/lu/traningChange/deleteTraningChangeScheduleApplication.do")
	public String deleteTraningChangeScheduleApplication(@ModelAttribute("frmTraning") TraningScheduleVO traningScheduleVO, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(traningScheduleVO);

		String retMsg = "";
		int deletetCnt1 = 0;
		int deletetCnt2 = 0;

		//1.HRD전담자는 센터전담자, 기업현장교사 변경신청한건을 삭제할수 있다.
		//2.기업현장교사는 본인이 변경신청한건만 삭제할수 있다.
		if("CCM".equals(traningScheduleVO.getSessionMemType()) || "COT".equals(traningScheduleVO.getSessionMemType())){
			String strArr[] = traningScheduleVO.getTraningProcessIdArr().split(",");
			if (strArr != null && strArr.length > 0) {
				String companyId = strArr[0];
				String traningProcessId = strArr[1];
				String yyyy = strArr[2];
				String term = strArr[3];
				String subjectCode = strArr[4];
				String subClass = strArr[5];

				traningScheduleVO.setCompanyId(companyId);
				traningScheduleVO.setTraningProcessId(traningProcessId);
				traningScheduleVO.setYyyy(yyyy);
				traningScheduleVO.setTerm(term);
				traningScheduleVO.setSubjectCode(subjectCode);
				traningScheduleVO.setSubClass(subClass);

				traningScheduleVO.setSearchYyyy(yyyy);
				traningScheduleVO.setSearchTerm(term);
				traningScheduleVO.setSearchSubjectCode(subjectCode);
				traningScheduleVO.setSearchSubClass(subClass);
			}
		}

		if( StringUtils.isBlank( traningScheduleVO.getYyyy() ) ){
			retMsg = "삭제할 정보가 없습니다.";
		} else {
			deletetCnt1 = traningChangeService.deleteTraningChangeScheduleDisapproved(traningScheduleVO);

			if(deletetCnt1 > 0){
				deletetCnt2 = traningChangeService.deleteTraningChangeScheduleTimeDisapproved(traningScheduleVO);
			}

			if(deletetCnt1 > 0 && deletetCnt2 > 0){
				retMsg = "정상적으로 훈련시간변경건이 (삭제)처리되었습니다.!";
			}else{
				retMsg = "훈련시간변경건을 삭제 처리시 오류가 발생하였습니다.!";
			}
		}

		TraningScheduleVO VO = new TraningScheduleVO();
		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmTraning", VO);

		// View호출
		return "redirect:/lu/traningChange/listTraningChangeScheduleApplication.do";
	}


}
