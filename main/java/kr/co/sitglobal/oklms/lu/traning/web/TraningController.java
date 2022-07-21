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
package kr.co.sitglobal.oklms.lu.traning.web;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import kr.co.sitglobal.ifx.service.CmsIfxService;
import kr.co.sitglobal.ifx.vo.CmsCourseBaseVO;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.atchFile.vo.AtchFileVO;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.commbiz.cmmcode.vo.CommonCodeVO;
import kr.co.sitglobal.oklms.la.company.service.CompanyService;
import kr.co.sitglobal.oklms.la.company.vo.CompanyVO;
import kr.co.sitglobal.oklms.lu.currproc.service.CurrProcService;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.traning.service.TraningService;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningProcessMappingVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningScheduleVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningVO;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class TraningController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(TraningController.class);

	@Resource(name = "traningService")
	private TraningService traningService;

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

	}

	/**
	 * 훈련과정관리 목록 첫화면 기업체검색 List Data.
	 * @param searchKeyword1
	 * @param searchKeyword2
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{userType}/traning/listTraningProcessManage.do")
	public String listTraningProcessManage(@PathVariable("userType") String userType, @RequestParam Map<String, Object> commandMap, @ModelAttribute("frmTraning") TraningVO traningVO, ModelMap model) throws Exception {

		String companyId = "";
		String traningProcessId = "";
		String tempCompanyId = "";
		String tempCompanyName = "";
		String tempAddress = "";
		String tempChoiceDay = "";
		String tempEmployInsManageNo = "";
		String searchKeyword = "";
		String searchKeywordNull = "";
		String pageSizeStr = "";
		String pageIndexStr = "";
		String tempTraningProcessId = "";
		String returnMsg = "POPUP_LIST_FAIL";
		int tdOffJtTotalCnt = 0;
		int tdOjtTotalCnt = 0;
		CompanyVO companyVO = new CompanyVO();
		TraningProcessMappingVO traningMappingVO = new TraningProcessMappingVO();

		tempCompanyId = StringUtils.defaultString((String)commandMap.get("companyId"),"");
		tempCompanyName = StringUtils.defaultString((String)commandMap.get("tempCompanyName"),"");
		tempAddress = StringUtils.defaultString((String)commandMap.get("tempAddress"),"");
		tempChoiceDay = StringUtils.defaultString((String)commandMap.get("tempChoiceDay"),"");
		tempEmployInsManageNo = StringUtils.defaultString((String)commandMap.get("tempEmployInsManageNo"),"");
		searchKeyword = StringUtils.defaultString((String)commandMap.get("searchKeyword"),"");
		searchKeywordNull = StringUtils.defaultString((String)commandMap.get("searchKeywordNull"),"");
		pageSizeStr = StringUtils.defaultString((String)commandMap.get("pageSize"),"");
		pageIndexStr = StringUtils.defaultString((String)commandMap.get("pageIndex"),"");
		tempTraningProcessId = StringUtils.defaultString((String)commandMap.get("tempTraningProcessId"),"");

		if(!"".equals(traningVO.getTempTraningProcessId())){
			tempTraningProcessId = traningVO.getTempTraningProcessId();
		}

		if(tempCompanyName.equals("")){
			tempCompanyName = traningVO.getCompanyName();
		}

		if(tempAddress.equals("")){
			tempAddress = traningVO.getAddress();
		}

		if(tempChoiceDay.equals("")){
			tempChoiceDay = traningVO.getChoiceDay();
		}

		if(tempEmployInsManageNo.equals("")){
			tempEmployInsManageNo = traningVO.getEmployInsManageNo();
		}

		if(!tempCompanyId.equals("")){
			companyId = tempCompanyId;
		}else{
			companyId = traningVO.getCompanyId();
		}

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

		List<CompanyVO> listCompanySearch = companyService.listCompany(companyVO);
		Integer pageSize = companyVO.getPageSize();
		Integer page = 1;
		if("".equals(pageIndexStr)){
			page = companyVO.getPageIndex();
		}else{
			page = Integer.parseInt(pageIndexStr);
		}

		int totalCnt = 0;
		if( 0 < listCompanySearch.size() ){
			totalCnt = Integer.parseInt( listCompanySearch.get(0).getTotalCount() );
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

        model.addAttribute("companyVO", companyVO);
		model.addAttribute("resultCompanyList", listCompanySearch);

		if( !StringUtils.isBlank( companyId ) ){
			traningProcessId = traningVO.getTraningProcessId();
			traningMappingVO.setCompanyId(companyId);

			if(!traningProcessId.equals("")){
				traningMappingVO.setTraningProcessId(traningProcessId);

				//훈련과정에 메핑된 개설강좌
				traningVO.setCompanyId(tempCompanyId);
				traningVO.setTraningProcessId(traningProcessId);
				tdOffJtTotalCnt = traningService.getTdOffJtTotalCnt(traningVO);
				tdOjtTotalCnt = traningService.getTdOjtTotalCnt(traningVO);

				TraningProcessMappingVO mappingVO = new TraningProcessMappingVO();
				TraningProcessMappingVO resultMappingVO = new TraningProcessMappingVO();
				mappingVO.setCompanyId(tempCompanyId);
				mappingVO.setTraningProcessId(traningProcessId);
				resultMappingVO = traningService.getOffJtSubjectInfo(traningMappingVO);
				List<TraningProcessMappingVO> listOjtSubjectInfo = traningService.listOjtSubjectInfo(mappingVO);
				List<TraningProcessMappingVO> listOffjtSubjectInfo = traningService.listOffJtSubjectInfo(mappingVO);
				resultMappingVO = traningService.getOffJtSubjectInfo(mappingVO);

				model.addAttribute("offJtTraingOneVO", resultMappingVO);
				model.addAttribute("resultOjtSubjectInfoList", listOjtSubjectInfo);
				model.addAttribute("resultOffjtSubjectInfoList", listOffjtSubjectInfo);
				model.addAttribute("tdOffJtTotalCnt", tdOffJtTotalCnt);
				model.addAttribute("tdOjtTotalCnt", tdOjtTotalCnt);
			}

			List<TraningVO> listTraningProcess = traningService.listTraningProcess(traningVO);
			model.addAttribute("resultTraningProcessList", listTraningProcess);
			model.addAttribute("resultTraningProcessListCnt", listTraningProcess.size());

		}

		model.addAttribute("traningVO", traningVO);
		model.addAttribute("companyId", companyId);
		model.addAttribute("traningProcessId", traningProcessId);
		model.addAttribute("tempCompanyId", companyId);
		model.addAttribute("tempCompanyName", tempCompanyName);
		model.addAttribute("tempAddress", tempAddress);
		model.addAttribute("tempChoiceDay", tempChoiceDay);
		model.addAttribute("tempEmployInsManageNo", tempEmployInsManageNo);

		// View호출
		return "oklms/"+userType+"/traning/traningProcessManageList";
	}

	/**
	 * 훈련과정관리 목록 첫화면 기업체검색 Data null 여부.
	 * @param searchKeyword1
	 * @param searchKeyword2
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{userType}/traning/choiceCompany.json")
	public @ResponseBody Map<String, Object> choiceCompany(@ModelAttribute("frmTraning") TraningVO traningVO
		,RedirectAttributes redirectAttributes
		,SessionStatus status
		,HttpServletRequest request
		,ModelMap model) throws Exception {

		String companyId = traningVO.getCompanyId();
		TraningProcessMappingVO traningMappingVO = new TraningProcessMappingVO();
		Map<String , Object> returnMap = new HashMap<String , Object>();

		//traningProcessList
		traningMappingVO.setCompanyId(companyId);
		List<TraningProcessMappingVO> resultList = new ArrayList<TraningProcessMappingVO>();
		resultList = traningService.listTraningProcessInfo(traningMappingVO);

		returnMap.put("retCd", 200);
		returnMap.put("retMsg", "[" + resultList.size() + "]건 조회되었습니다." );
		returnMap.put("page", "");
		returnMap.put("total", "");
		returnMap.put("records", resultList);
		returnMap.put("totalRecords", resultList.size());
		returnMap.put("rows", resultList );

		return returnMap;
	}

	/**
	 * 훈련시간표등록 목록 첫화면 기업체검색 List Data.
	 * @param searchKeyword
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{userType}/traning/listTraningSchedule.do")
	public String listTraningSchedule(@PathVariable("userType") String userType, @RequestParam Map<String, Object> paramMap, @ModelAttribute("frmTraning") TraningScheduleVO traningScheduleVO, ModelMap model) throws Exception {

		String returnMsg = "POPUP_LIST_FAIL";
		String companyId = StringUtils.defaultString((String)paramMap.get("companyId"),"");
		String traningProcessId = StringUtils.defaultString((String)paramMap.get("traningProcessId"),"");
		String searchYyyy = StringUtils.defaultString((String)paramMap.get("searchYyyy"),""); 					//학년도
		String searchTerm = StringUtils.defaultString((String)paramMap.get("searchTerm"),"");					//학기
		String searchSubjectCode = StringUtils.defaultString((String)paramMap.get("searchSubjectCode"),"");	//개설강좌코드
		String searchSubClass = StringUtils.defaultString((String)paramMap.get("searchSubClass"),"");          //분반
		String searchKeyword = StringUtils.defaultString((String)paramMap.get("searchKeyword"),"");
		String searchKeywordNull = StringUtils.defaultString((String)paramMap.get("searchKeywordNull"),"");
		String pageSizeStr = StringUtils.defaultString((String)paramMap.get("pageSize"),"");
		String pageIndexStr = StringUtils.defaultString((String)paramMap.get("pageIndex"),"");
		String tempTraningProcessId = StringUtils.defaultString((String)paramMap.get("tempTraningProcessId"),"");

		CompanyVO companyVO = new CompanyVO();
		CommonCodeVO codeVO = new CommonCodeVO();
		CurrProcVO currProcVO = new CurrProcVO();
		CurrProcVO currProcReadVO = new CurrProcVO();
		TraningScheduleVO readVO = new TraningScheduleVO();
		AtchFileVO atchFileVO = new AtchFileVO();

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
		if(!"".equals(traningProcessId)){
			companyVO.setCompanyId(companyId);
			companyVO.setTraningProcessId(traningProcessId);
			List<CompanyVO> listCompanyTraningProcess = companyService.listCompanyTraningProcess(companyVO);
			model.addAttribute("resultList", listCompanyTraningProcess); //기업체 훈련과정 검색목록

			codeVO.setCompanyId(companyId);
			codeVO.setTraningProcessId(traningProcessId);
			
			if(searchYyyy==null || searchYyyy.equals("")){
				// 현재 년도학기조회
				CommonCodeVO commonCodeVO =	commonCodeService.selectYearTerm();
				codeVO.setYyyy(commonCodeVO.getYyyy());			
				codeVO.setTerm(commonCodeVO.getTerm());
			}
			

			List<CommonCodeVO> yearSubjCodeList = commonCodeService.selectYearCodeList(codeVO); //학년도-코드
			List<CommonCodeVO> termSubjCodeList = commonCodeService.selectTermCodeList(codeVO); //학기-코드
			List<CommonCodeVO> subjNameSubjCodeList = commonCodeService.selectSubjectCodeList(codeVO); //개설강좌-코드
			List<CommonCodeVO> classSubjCodeList = commonCodeService.selectClassCodeList(codeVO); //분반-코드

			model.addAttribute("searchYyyyCodeId", yearSubjCodeList.get(0).getCodeId());
			model.addAttribute("yearSubjCode", yearSubjCodeList);
			model.addAttribute("termSubjCode", termSubjCodeList);
			model.addAttribute("subjNameSubjCode", subjNameSubjCodeList);
			model.addAttribute("classSubjCode", classSubjCodeList);

			//훈련시간표등록을 위한 기업체 개설강좌 조회 버튼을 클릭시
			if(!"".equals(searchYyyy) && !"".equals(searchSubClass)){
 				currProcVO.setYyyy(searchYyyy);
				currProcVO.setTerm(searchTerm);
				currProcVO.setSubClass(searchSubClass);
				currProcVO.setSubjectCode(searchSubjectCode);

				currProcReadVO = currProcService.getMyTrainSubjectInfo(currProcVO);

				traningScheduleVO.setYyyy(searchYyyy);
				traningScheduleVO.setTerm(searchTerm);
				traningScheduleVO.setSubClass(searchSubClass);
				traningScheduleVO.setSubjectCode(searchSubjectCode);

				List<TraningScheduleVO> listTraningScheduleView = traningService.listTraningScheduleView(traningScheduleVO);
				
				String status = traningService.getTraningScheduleStatus(traningScheduleVO);
				
				model.addAttribute("status", status);
				model.addAttribute("resultScheduleViewList", listTraningScheduleView);
				model.addAttribute("resultScheduleViewListCnt", listTraningScheduleView.size());

				if(listTraningScheduleView.size() > 0){
					readVO = traningService.getTraningSubjWeekInfo(traningScheduleVO);
					//첨부파일 정보셋팅
					if(readVO != null){
						atchFileVO.setFileSn(1);
						atchFileVO.setAtchFileId(readVO.getAtchFileId());
						AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
						model.addAttribute("resultFile", resultFile);
						model.addAttribute("subjctTitle", readVO.getSubjctTitle());
						model.addAttribute("atchFileId", readVO.getAtchFileId());
					}
				}
			}
		}

		model.addAttribute("companyId", companyId);
		model.addAttribute("traningProcessId", traningProcessId);
		model.addAttribute("traningSchVO", traningScheduleVO);
		model.addAttribute("currProcReadVO", currProcReadVO); 	//개설강좌 정보

		// View호출
		return "oklms/"+userType+"/traning/traningScheduleExcelUploadList";
	}
	
	
	
	/**
	 * 훈련과정관리 삭제
	 * @param TraningVO
	 * @return TraningVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/{userType}/traning/deleteTraningSchedule.do")
	public String deleteTraningSchedule(@PathVariable("userType") String userType, @RequestParam Map<String, Object> paramMap, @ModelAttribute("frmTraning") TraningScheduleVO traningScheduleVO, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String companyId = StringUtils.defaultString((String)paramMap.get("companyId"),"");
		String traningProcessId = StringUtils.defaultString((String)paramMap.get("traningProcessId"),"");
		String searchYyyy = StringUtils.defaultString((String)paramMap.get("searchYyyy"),""); 					//학년도
		String searchTerm = StringUtils.defaultString((String)paramMap.get("searchTerm"),"");					//학기
		String searchSubjectCode = StringUtils.defaultString((String)paramMap.get("searchSubjectCode"),"");	//개설강좌코드
		String searchSubClass = StringUtils.defaultString((String)paramMap.get("searchSubClass"),"");          //분반
		String retMsg = "";
		int deleteCnt = 0;
		
		if( StringUtils.isBlank(searchYyyy) && StringUtils.isBlank(searchTerm) && StringUtils.isBlank(searchSubjectCode) && StringUtils.isBlank(searchSubClass)  ){
			retMsg = "삭제할 정보가 없습니다.";
		} else {
			traningScheduleVO.setCompanyId(companyId);
			traningScheduleVO.setTraningProcessId(traningProcessId);
			traningScheduleVO.setYyyy(searchYyyy);
			traningScheduleVO.setTerm(searchTerm);
			traningScheduleVO.setSubjectCode(searchSubjectCode);
			traningScheduleVO.setSubClass(searchSubClass);
			
			traningScheduleVO.setSearchYyyy(searchYyyy);
			traningScheduleVO.setSearchTerm(searchTerm);
			traningScheduleVO.setSearchSubjectCode(searchSubjectCode);
			traningScheduleVO.setSearchSubClass(searchSubClass);
			
			deleteCnt = traningService.deleteTraningSchedule(traningScheduleVO);

			if(deleteCnt > 0 ){
				retMsg = "정상적으로 (삭제)처리되었습니다.!";
			}else{
				retMsg = "처리시 오류가 발생하였습니다.!";
			}
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmTraning", traningScheduleVO);

		return "redirect:/lu/traning/listTraningSchedule.do";
	}

	/**
	 * 훈련시간표등록 목록 첫화면 기업체검색 List Data.
	 * @param searchKeyword
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{userType}/traning/getTraningSchedule.do")
	public String getTraningSchedule(@PathVariable("userType") String userType, @RequestParam Map<String, Object> paramMap, @ModelAttribute("frmTraning") TraningScheduleVO traningScheduleVO, ModelMap model) throws Exception {

		String companyId = "";
		String traningProcessId = "";
		String yyyy = "";
		String term = "";
		String subjectCode = "";
		String subClass = "";
		String searchYyyy = "";
		String searchTerm = "";
		String searchSubjectCode = "";
		String searchSubClass = "";
		String status = "";
		String scheduleViewYn = "N";
		CompanyVO companyVO = new CompanyVO();
		CommonCodeVO codeVO = new CommonCodeVO();
		CurrProcVO currProcVO = new CurrProcVO();
		CurrProcVO currProcReadVO = new CurrProcVO();
		TraningScheduleVO readVO = new TraningScheduleVO();
		TraningScheduleVO leadTimeSumVO = new TraningScheduleVO();
		AtchFileVO atchFileVO = new AtchFileVO();

  		companyId = traningScheduleVO.getCompanyId();
		traningProcessId = traningScheduleVO.getTraningProcessId();
		yyyy = traningScheduleVO.getYyyy();
		term = traningScheduleVO.getTerm();
		subjectCode = traningScheduleVO.getSubjectCode();
		subClass = traningScheduleVO.getSubClass();
		searchYyyy = traningScheduleVO.getSearchYyyy(); 				//학년도
		searchTerm = traningScheduleVO.getSearchTerm();					//학기
		searchSubjectCode = traningScheduleVO.getSearchSubjectCode();	//개설강좌코드
		searchSubClass = traningScheduleVO.getSearchSubClass();         //분반
		scheduleViewYn = traningScheduleVO.getScheduleViewYn();         //시간표보기 버튼 클릭여부

		//기업체검색 팝업화면에서 훈련과정을 선택했을경우
		if(!"".equals(traningProcessId)){
			companyVO.setCompanyId(companyId);
			companyVO.setTraningProcessId(traningProcessId);
			List<CompanyVO> listCompanyTraningProcess = companyService.listCompanyTraningProcess(companyVO);
			model.addAttribute("resultList", listCompanyTraningProcess); //기업체 훈련과정 검색목록

			codeVO.setCompanyId(companyId);
			codeVO.setTraningProcessId(traningProcessId);

			List<CommonCodeVO> yearSubjCodeList = commonCodeService.selectYearCodeList(codeVO); //학년도-코드
			List<CommonCodeVO> termSubjCodeList = commonCodeService.selectTermCodeList(codeVO); //학기-코드
			List<CommonCodeVO> subjNameSubjCodeList = commonCodeService.selectSubjectCodeList(codeVO); //개설강좌-코드
			List<CommonCodeVO> classSubjCodeList = commonCodeService.selectClassCodeList(codeVO); //분반-코드

			model.addAttribute("searchYyyyCodeId", yearSubjCodeList.get(0).getCodeId());
			model.addAttribute("yearSubjCode", yearSubjCodeList);
			model.addAttribute("termSubjCode", termSubjCodeList);
			model.addAttribute("subjNameSubjCode", subjNameSubjCodeList);
			model.addAttribute("classSubjCode", classSubjCodeList);

			//훈련시간표등록을 위한 기업체 개설강좌 조회 버튼을 클릭시
			if(!"".equals(searchYyyy)){
				currProcVO.setYyyy(searchYyyy);
				currProcVO.setTerm(searchTerm);
				currProcVO.setSubClass(searchSubClass);
				currProcVO.setSubjectCode(searchSubjectCode);

				currProcReadVO = currProcService.getMyTrainSubjectInfo(currProcVO);
				readVO = traningService.getTraningSubjWeekInfo(traningScheduleVO);
				
				String tStatus = traningService.getTraningScheduleStatus(traningScheduleVO);
				
				model.addAttribute("status", tStatus);
			}

			//훈련시간표보기 버튼 클릭시
			if("Y".equals(scheduleViewYn)){
				List<TraningScheduleVO> listTraningScheduleView = traningService.listTraningScheduleView(traningScheduleVO);
				model.addAttribute("resultScheduleViewList", listTraningScheduleView);

				List<TraningScheduleVO> listTraningScheduleModifyView = traningService.listTraningScheduleModifyView(traningScheduleVO);
				model.addAttribute("resultScheduleModifyViewList", listTraningScheduleModifyView);

				leadTimeSumVO = traningService.getTraningSubjWeekTimeSum(traningScheduleVO);
				model.addAttribute("leadTimeSum", leadTimeSumVO.getLeadTimeSum());
			}
		}

		//첨부파일 정보셋팅
		if(readVO != null){
			atchFileVO.setFileSn(1);
			atchFileVO.setAtchFileId(readVO.getAtchFileId());
			AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
			model.addAttribute("resultFile", resultFile);
			model.addAttribute("subjctTitle", readVO.getSubjctTitle());
			model.addAttribute("atchFileId", readVO.getAtchFileId());
			status = readVO.getStatus();
		} else {
			status = "1";
		}

		model.addAttribute("companyId", companyId);
		model.addAttribute("traningProcessId", traningProcessId);
		model.addAttribute("yyyy", yyyy);
		model.addAttribute("term", term);
		model.addAttribute("subjectCode", subjectCode);
		model.addAttribute("subClass", subClass);
		model.addAttribute("searchYyyy", searchYyyy);
		model.addAttribute("searchTerm", searchTerm);
		model.addAttribute("searchSubjectCode", searchSubjectCode);
		model.addAttribute("searchSubClass", searchSubClass);
		model.addAttribute("scheduleViewYn", scheduleViewYn);
		model.addAttribute("status", status);
		model.addAttribute("traningSchVO", traningScheduleVO);
		model.addAttribute("currProcReadVO", currProcReadVO);

		// View호출
		return "oklms/"+userType+"/traning/traningScheduleExcelRead";
	}

	// 강의정보의 목록 엑셀다운로드
	@RequestMapping(value = "/{userType}/traning/listTraningScheduleExcelDownload.do")
	public String listTraningScheduleExcelDownload(@PathVariable("userType") String userType, @RequestParam Map<String, Object> paramMap, @ModelAttribute("frmTraning") TraningScheduleVO traningScheduleVO, ModelMap model, HttpServletRequest request) throws Exception {

		traningScheduleVO.setPageSize(10000); // 1만건 조회
		List<TraningScheduleVO> resultList = traningService.listTraningScheduleExcelDownload( traningScheduleVO );
		String ExcelName = "훈련시간표등록";
		//int cnt = 0;
		//++cnt;

		//ExcelName = ExcelName;

        model.addAttribute("resultList", resultList);
        request.setAttribute("ExcelName", URLEncoder.encode( ExcelName.replaceAll(" ", "_") ,"UTF-8") );

		// View호출
		return "oklms_excel/"+userType+"/traning/traningScheduleExcelList";
	}

	/**
	 * 훈련과정관리 신규추가
	 * @param companyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{userType}/traning/goInsertTraningProcessManageList.do")
	public String goInsertTraningProcessManageList(@RequestParam Map<String, Object> commandMap, @PathVariable("userType") String userType, @ModelAttribute("frmTraning") TraningVO traningVO, ModelMap model) throws Exception {

		String searchKeyword = "";
		String searchKeywordNull = "";
		String tempTraningProcessId = "";
		String returnMsg = "POPUP_LIST_FAIL";

		searchKeyword = StringUtils.defaultString((String)commandMap.get("searchKeyword"),"");
		searchKeywordNull = StringUtils.defaultString((String)commandMap.get("searchKeywordNull"),"");
		tempTraningProcessId = StringUtils.defaultString((String)commandMap.get("tempTraningProcessId"),"");

		CompanyVO companyVO =  new CompanyVO();
		companyVO.setCompanyId(traningVO.getCompanyId());

		if(!searchKeyword.equals("") && !"noDivPopup".equals(tempTraningProcessId)){
			returnMsg = "POPUP_LIST_SUCCESS";
			model.addAttribute("returnMsg", returnMsg);
		}

		if(!searchKeywordNull.equals("") && !"noDivPopup".equals(tempTraningProcessId)){
			returnMsg = "POPUP_LIST_SUCCESS";
			model.addAttribute("returnMsg", returnMsg);
		}

		List<TraningVO> listTraningProcessManage = traningService.listTraningProcessManage(traningVO);
		List<CompanyVO> listCompany = companyService.listCompanySearch(companyVO);

		Integer pageSize = traningVO.getPageSize();
		Integer page = traningVO.getPageIndex();

		int totalCnt = 0;
		if( 0 < listTraningProcessManage.size() ){
			totalCnt = Integer.parseInt( listTraningProcessManage.get(0).getTotalCount() );
		}
		int totalPage = (int) Math.ceil(totalCnt / pageSize);
		
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(traningVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(traningVO.getPageUnit());
        paginationInfo.setPageSize(traningVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);

		model.addAttribute("resultTraningProcessList", listTraningProcessManage);
		model.addAttribute("resultCompanyList", listCompany);
		model.addAttribute("companyId", traningVO.getCompanyId());
		model.addAttribute("searchKeyword", searchKeyword);

		// View호출
		return "oklms/"+userType+"/traning/traningProcessManageInsertList";
	}

	/**
	 * 훈련과정관리 수정
	 * @param companyId
	 * @param traningProcessId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{userType}/traning/goUpdateTraningProcessManageList.do")
	public String goUpdateTraningProcessManageList (@PathVariable("userType") String userType, @ModelAttribute("frmTraning") TraningVO traningVO, ModelMap model) throws Exception {

		int ojtCnt = 0;
		CompanyVO companyVO =  new CompanyVO();
		TraningProcessMappingVO traningMappingVO = new TraningProcessMappingVO();
		TraningProcessMappingVO resultMappingVO = new TraningProcessMappingVO();

		String strArr[] = traningVO.getTraningProcessIdArr().split(",");
		if (strArr != null && strArr.length > 0) {
			String strCompanyId = strArr[0];
			String strCompanyName = strArr[1];
			String strTraningProcessId = strArr[2];

			traningVO.setCompanyId(strCompanyId);
        	traningVO.setCompanyName(strCompanyName);
        	traningVO.setTraningProcessId(strTraningProcessId);
		}

		companyVO.setCompanyId(traningVO.getCompanyId());
		traningMappingVO.setCompanyId(traningVO.getCompanyId());
		traningMappingVO.setTraningProcessId(traningVO.getTraningProcessId());

		ojtCnt = traningService.getTdOjtTotalCnt(traningVO);
		resultMappingVO = traningService.getOffJtSubjectInfo(traningMappingVO);
		List<CompanyVO> listCompanyOne = companyService.listCompanySearch(companyVO);
		List<TraningProcessMappingVO> listOffJtSubjectInfo = traningService.listOffJtSubjectInfo(traningMappingVO);
		List<TraningProcessMappingVO> listOjtSubjectInfo = traningService.listOjtSubjectInfo(traningMappingVO);

		model.addAttribute("resultCompanyOneList", listCompanyOne);
		model.addAttribute("resultOffJtSubjectInfoList", listOffJtSubjectInfo);
		model.addAttribute("resultOjtSubjectInfoList", listOjtSubjectInfo);
		model.addAttribute("offJtTraingOneVO", resultMappingVO);
		model.addAttribute("ojtCnt", ojtCnt);
		model.addAttribute("companyId", traningVO.getCompanyId());
		model.addAttribute("traningProcessId", traningVO.getTraningProcessId());

		// View호출
		return "oklms/"+userType+"/traning/traningProcessManageUpdateList";
	}

	/**
	 * 훈련과정관리 상세 조회
	 * @param TraningVO
	 * @return TraningVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/{userType}/traning/getTraningProcessManageList.do")
	public String getTraningProcessManageList(@RequestParam Map<String, Object> commandMap, @PathVariable("userType") String userType, @ModelAttribute("frmTraning") TraningVO traningVO, ModelMap model) throws Exception {

		String searchKeyword = "";
		String searchKeywordNull = "";
		String tempTraningProcessId = "";
		String pageSizeStr = "";
		String pageIndexStr = "";
		String returnMsg = "POPUP_LIST_FAIL";
		int tdOffJtTotalCnt = 0;
		int tdOjtTotalCnt = 0;
		int tdTotalCnt = 0;
		
		searchKeyword = StringUtils.defaultString((String)commandMap.get("searchKeyword"),"");
		searchKeywordNull = StringUtils.defaultString((String)commandMap.get("searchKeywordNull"),"");
		pageSizeStr = StringUtils.defaultString((String)commandMap.get("pageSize"),"");
		pageIndexStr = StringUtils.defaultString((String)commandMap.get("pageIndex"),"");
		tempTraningProcessId = StringUtils.defaultString((String)commandMap.get("tempTraningProcessId"),"");

		CompanyVO companyVO =  new CompanyVO();
		TraningProcessMappingVO mappingVO = new TraningProcessMappingVO();
		TraningProcessMappingVO resultMappingVO = new TraningProcessMappingVO();

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

		List<CompanyVO> listCompanySearch = companyService.listCompany(companyVO);
		Integer pageSize = companyVO.getPageSize();
		Integer page = 1;
		if("".equals(pageIndexStr)){
			page = companyVO.getPageIndex();
		}else{
			page = Integer.parseInt(pageIndexStr);
		}

		int totalCnt = 0;
		if( 0 < listCompanySearch.size() ){
			totalCnt = Integer.parseInt( listCompanySearch.get(0).getTotalCount() );
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

        model.addAttribute("companyVO", companyVO);
		model.addAttribute("resultCompanyList", listCompanySearch);

		companyVO.setCompanyId(traningVO.getCompanyId());
		companyVO.setSearchCompanyName(null);
		List<CompanyVO> listCompanyOne = companyService.listCompanySearch(companyVO);
		//List<TraningVO> listTraningProcessSearch = traningService.listTraningProcessManage(traningVO);
		List<TraningVO> listTraningProcess = traningService.listTraningProcess(traningVO);

		mappingVO.setCompanyId(traningVO.getCompanyId());
		mappingVO.setTraningProcessId(traningVO.getTraningProcessId());
		resultMappingVO = traningService.getOffJtSubjectInfo(mappingVO);
		
		List<TraningProcessMappingVO> listOjtSubjectInfo = traningService.listOjtSubjectInfo(mappingVO);
		List<TraningProcessMappingVO> listOffjtSubjectInfo = traningService.listOffJtSubjectInfo(mappingVO);

		tdOffJtTotalCnt = traningService.getTdOffJtTotalCnt(traningVO);
		tdOjtTotalCnt = traningService.getTdOjtTotalCnt(traningVO);
		tdTotalCnt = tdOffJtTotalCnt+tdOjtTotalCnt;

		LOGGER.debug("tdOffJtTotalCnt################################"+tdOffJtTotalCnt);
		LOGGER.debug("tdOjtTotalCnt################################"+tdOjtTotalCnt);
		LOGGER.debug("tdTotalCnt################################"+tdOffJtTotalCnt);
		model.addAttribute("resultCompanyOneList", listCompanyOne);
		//model.addAttribute("resultTraningProcessSearchList", listTraningProcessSearch);
		model.addAttribute("resultTraningProcessList", listTraningProcess);
		model.addAttribute("offJtTraingOneVO", resultMappingVO);
		model.addAttribute("resultOjtSubjectInfoList", listOjtSubjectInfo);
		model.addAttribute("resultOffjtSubjectInfoList", listOffjtSubjectInfo);

		model.addAttribute("tdOffJtTotalCnt", tdOffJtTotalCnt);
		model.addAttribute("tdOjtTotalCnt", tdOjtTotalCnt);
		model.addAttribute("tdTotalCnt", tdTotalCnt);
		model.addAttribute("companyId", traningVO.getCompanyId());
		model.addAttribute("traningProcessId", traningVO.getTraningProcessId());

		// View호출
		return "oklms/"+userType+"/traning/traningProcessManageReadList";
	}

	/**
	 * 훈련과정관리 삭제
	 * @param TraningVO
	 * @return TraningVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/{userType}/traning/deleteTraningProcessManage.do")
	public String deleteTraningProcessManage(@PathVariable("userType") String userType, @RequestParam Map<String, Object> commandMap, @ModelAttribute("frmTraning") TraningVO traningVO, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "";
		String tempAddress = StringUtils.defaultString((String)commandMap.get("tempAddress"),(String)commandMap.get("address"));
		String tempChoiceDay = StringUtils.defaultString((String)commandMap.get("tempChoiceDay"),(String)commandMap.get("choiceDay"));
		String tempEmployInsManageNo = StringUtils.defaultString((String)commandMap.get("tempEmployInsManageNo"),(String)commandMap.get("employInsManageNo"));
		int deletetCnt1 = 0;
		int deletetCnt2 = 0;

		if( StringUtils.isBlank( traningVO.getTraningProcessIdArr() ) ){
			retMsg = "삭제할 정보가 없습니다.";
		} else {

			String strArr[] = traningVO.getTraningProcessIdArr().split(",");
			if (strArr != null && strArr.length > 0) {
				String strCompanyId = strArr[0];
				String strCompanyName = strArr[1];
				String strTraningProcessId = strArr[2];

				traningVO.setCompanyId(strCompanyId);
            	traningVO.setCompanyName(strCompanyName);
            	traningVO.setTraningProcessId(strTraningProcessId);
			}

			deletetCnt1 = traningService.deleteTraningProcessMappingInfoList(traningVO);
			deletetCnt2 = traningService.deleteTraningProcessInfoList(traningVO);

			if(deletetCnt1 > 0 && deletetCnt2 > 0){
				retMsg = "정상적으로 (삭제)처리되었습니다.!";
			}else{
				retMsg = "처리시 오류가 발생하였습니다.!";
			}
		}

		/*traningVO.setAddress(tempAddress);
		traningVO.setChoiceDay(tempChoiceDay);
		traningVO.setEmployInsManageNo(tempEmployInsManageNo);*/

		TraningVO VO = new TraningVO();
		VO.setTempTraningProcessId("noDivPopup");

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmTraning", VO);

		// View호출
		return "redirect:/lu/traning/listTraningProcessManage.do";
	}

	/**
	 * 훈련과정관리 신규추가건 저장
	 * @param TraningVO
	 * @return TraningVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/traning/insertTraningProcessManageList.do")
	public String insertTraningProcessManageList(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmTraning") TraningProcessMappingVO traningMappingVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String companyId = StringUtils.defaultString( (String)paramMap.get("companyId") , "" );
		String traningProcessId = StringUtils.defaultString( (String)paramMap.get("traningProcessId") , "" );
		String traningProcessName = StringUtils.defaultString( (String)paramMap.get("traningProcessName") , "" );
		String traningProcessNo = StringUtils.defaultString( (String)paramMap.get("traningProcessNo") , "" );
		String yyyy = StringUtils.defaultString( (String)paramMap.get("yyyy1") , "" );
		String addTempCount = StringUtils.defaultString( (String)paramMap.get("count") , "0" );
		String retMsg = "훈련과정관리에 처리할 훈련과정명, 훈련과정번호, 개설강좌 정보가 없습니다.";
		int insertCount1 = 0;
		int insertTempCount2 = 0;
		int insertCount2 = 0;
		int subjChkCount = 0;
		int addCount = 0;
		addCount = Integer.parseInt(addTempCount);

		LOGGER.debug("#### addCount ==> "+addCount);
		LOGGER.debug("#### traningProcessId ==> "+traningProcessId);

		//훈련과정 lms_traning_process 훈련과정 테이블에 insert 한다
		if(!StringUtils.isBlank(companyId) && !StringUtils.isBlank(traningProcessId)){
			traningMappingVO.setCompanyId(companyId);
			traningMappingVO.setTraningProcessId(traningProcessId);
			traningMappingVO.setHrdTraningName(traningProcessName);
			traningMappingVO.setHrdTraningNo(traningProcessNo);

			insertCount1 = traningService.insertTraningProcessInfoList(traningMappingVO);

			LOGGER.debug("#### insertCount1 ==> "+insertCount1);

			if(insertCount1 == 0 || insertCount1 == 99){
				TraningVO VO = new TraningVO();
				if(insertCount1 == 0){
					retMsg = "처리시 오류가 발생하였습니다.!";
				} else {
					retMsg = "처리할 기업에 훈련과정명 ("+traningProcessName+")건으로 훈련과정 정보가 이미 처리되어 있습니다.";
				}

				VO.setTempTraningProcessId("noDivPopup");

				redirectAttributes.addFlashAttribute("frmTraning", VO);
				redirectAttributes.addFlashAttribute("retMsg", retMsg);

				return "redirect:/lu/traning/listTraningProcessManage.do";
			}
		}

		//훈련과정에 메핑된 개설강좌 목록 count 만큼 lms_traning_process_mapping 훈련과정메핑 테이블에 insert 한다
		if(!StringUtils.isBlank(yyyy) && insertCount1 != 0 && insertCount1 != 99){
			String yyyyArr = "";
			String termArr = "";
			String subjectCodeArr = "";
			String subClassArr = "";
			String subjectNameArr = "";
			String subjTraningType = "";

			//해당기업에 훈련과정으로 개설교과메핑정보 생성
			for( int idx = 1 ; idx <= addCount ; idx++ ) {
				yyyyArr = StringUtils.defaultString( (String)paramMap.get("yyyy"+idx) , "" );
				termArr = StringUtils.defaultString( (String)paramMap.get("term"+idx) , "" );
				subjectCodeArr = StringUtils.defaultString( (String)paramMap.get("subjectCode"+idx) , "" );
				subClassArr = StringUtils.defaultString( (String)paramMap.get("subClass"+idx) , "" );
				subjectNameArr = StringUtils.defaultString( (String)paramMap.get("subjectName"+idx) , "" );
				subjTraningType = StringUtils.defaultString( (String)paramMap.get("subjType"+idx) , "" );
						
				LOGGER.debug("#### idx ==> ["+idx + "] #### yyyyArr ==> "+yyyyArr);
				LOGGER.debug("#### idx ==> ["+idx + "] #### termArr ==> "+termArr);
				LOGGER.debug("#### idx ==> ["+idx + "] #### subjectCodeArr ==> "+subjectCodeArr);
				LOGGER.debug("#### idx ==> ["+idx + "] #### subClassArr ==> "+subClassArr);
				LOGGER.debug("#### idx ==> ["+idx + "] #### subjectNameArr ==> "+subjectNameArr);

				traningMappingVO.setYyyy(yyyyArr);
				traningMappingVO.setTerm(termArr);
				traningMappingVO.setSubjectCode(subjectCodeArr);
				traningMappingVO.setSubClass(subClassArr);
/*
				if( idx == 1){
					insertTempCount2 = traningService.insertTraningProcessMappingInfoList(traningMappingVO);
				} else if( idx == 2){
					insertTempCount2 = traningService.insertTraningProcessMappingInfoList(traningMappingVO);
				} else if( idx > 2){
					subjChkCount = traningService.getTraningProcessMappingCnt(traningMappingVO);
					LOGGER.debug("#### subjChkCount ==> "+subjChkCount);

					if(subjChkCount > 0){
						TraningVO traningVO = new TraningVO();
						traningVO.setCompanyId(companyId);
						traningVO.setTraningProcessId(traningProcessId);

						traningService.deleteTraningProcessMappingInfoList(traningVO);
						traningService.deleteTraningProcessInfoList(traningVO);

						TraningVO VO = new TraningVO();
						retMsg = "처리할 기업에 개설강좌명 ("+subjectNameArr+")건으로 이미 메핑 처리되어 있습니다.";

						VO.setTempTraningProcessId("noDivPopup");

						redirectAttributes.addFlashAttribute("frmTraning", VO);
						redirectAttributes.addFlashAttribute("retMsg", retMsg);

						return "redirect:/lu/traning/listTraningProcessManage.do";
					} else {
						insertTempCount2 = traningService.insertTraningProcessMappingInfoList(traningMappingVO);
					}
				}
*/
				subjChkCount = traningService.getTraningProcessMappingCnt(traningMappingVO);
				LOGGER.debug("#### subjChkCount ==> "+subjChkCount);

				if(subjChkCount > 0){
					TraningVO traningVO = new TraningVO();
					traningVO.setCompanyId(companyId);
					traningVO.setTraningProcessId(traningProcessId);

					traningService.deleteTraningProcessMappingInfoList(traningVO);
					traningService.deleteTraningProcessInfoList(traningVO);

					TraningVO VO = new TraningVO();
					retMsg = "처리할 기업에 개설강좌명 ("+subjectNameArr+")건으로 이미 메핑 처리되어 있습니다.";

					VO.setTempTraningProcessId("noDivPopup");

					redirectAttributes.addFlashAttribute("frmTraning", VO);
					redirectAttributes.addFlashAttribute("retMsg", retMsg);

					return "redirect:/lu/traning/listTraningProcessManage.do";
				} else {
					insertCount2 += traningService.insertTraningProcessMappingInfoList(traningMappingVO);
				}
				
				
				LOGGER.debug("#### insertTempCount2 ==> "+insertCount2);

			}
		}

		if(insertCount1 > 0 && insertCount2 > 0){
			retMsg = "정상적으로 (저장)처리되었습니다.!";
			TraningVO VO = new TraningVO();
			VO.setCompanyId(companyId);
			VO.setTraningProcessId(traningProcessId);

			redirectAttributes.addFlashAttribute("frmTraning", VO);
			redirectAttributes.addFlashAttribute("retMsg", retMsg);

			return "redirect:/lu/traning/getTraningProcessManageList.do";
		} else {
			TraningVO VO = new TraningVO();

			VO.setTempTraningProcessId("noDivPopup");

			redirectAttributes.addFlashAttribute("frmTraning", VO);
			redirectAttributes.addFlashAttribute("retMsg", retMsg);

			return "redirect:/lu/traning/listTraningProcessManage.do";
		}
	}

	//센터담장자 > 담당기업체관리 > 훈련시간표등록 엑셀일괄등록 임시저장
	@RequestMapping(value = "/lu/traning/insertTraningScheduleExcel.do")
	public String insertTraningScheduleExcel(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmTraning") @Valid TraningScheduleVO traningScheduleVO, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status, MultipartHttpServletRequest multiRequest, HttpServletRequest request) throws Exception {

		String retMsg = "";
		String result = traningService.insertTraningScheduleExcel(traningScheduleVO, multiRequest, request);

		 if(result == "SUCCESS"){
			 retMsg = "(OJT)훈련시간표 엑셀 일괄업로드가 정상적으로 처리되었습니다.!";
		 }else {
			 retMsg = "(OJT)훈련시간표 엑셀 일괄업로드 처리시 에러가 발생하었습니다.!";
		 }

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
	    redirectAttributes.addFlashAttribute("frmTraning", traningScheduleVO);

		return "redirect:/lu/traning/getTraningSchedule.do";
	}

	//센터담장자 > 담당기업체관리 > 훈련시간표 임시등록된 정보를 실제 사용하는 주자정보에 생성
	@RequestMapping(value = "/lu/traning/insertTraningSchedule.do")
	public String insertTraningSchedule(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmTraning") @Valid TraningScheduleVO traningScheduleVO, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "";
		String result = traningService.insertTraningSchedule(traningScheduleVO);

		 if(result == "SUCCESS"){
			 retMsg = "임시 (OJT)훈련시간표가 실제사용되는 주차정보로 정상적으로 처리되었습니다.!";
		 }else {
			 retMsg = "임시 (OJT)훈련시간표가 실제사용되는 주차정보로 처리시 에러가 발생하었습니다.!";
		 }

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
	    redirectAttributes.addFlashAttribute("frmTraning", traningScheduleVO);

		return "redirect:/lu/traning/getTraningSchedule.do";
	}

	/**
	 * 훈련과정관리 수정 저장
	 * @param TraningVO
	 * @return TraningVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/traning/updateTraningProcessManageList.do")
	public String updateTraningProcessManageList(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmTraning") TraningProcessMappingVO traningMappingVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		LOGGER.debug("#### paramMap ==> "+paramMap);
		LOGGER.debug("#### traningMappingVO ==> "+traningMappingVO);

		String companyId = StringUtils.defaultString( (String)paramMap.get("companyId") , "" );
		String traningProcessId = StringUtils.defaultString( (String)paramMap.get("traningProcessId") , "" );
		String traningProcessName = StringUtils.defaultString( (String)paramMap.get("traningProcessName") , "" );
		String yyyy = StringUtils.defaultString( (String)paramMap.get("yyyy1") , "" );
		String addTempCount = StringUtils.defaultString( (String)paramMap.get("count") , "0" );
		String retMsg = "훈련과정관리에 처리할 훈련과정명, 훈련과정번호, 개설강좌 정보가 없습니다.";
		int insertTempCount = 0;
		int insertCount = 0;
		int updateTempCount2 = 0;
		int updateCount2 = 0;
		int subjChkCount = 0;
		int addCount = 0;
		addCount = Integer.parseInt(addTempCount);
		
		String yyyyArr = "";
		String termArr = "";
		String subjectCodeArr = "";
		String subClassArr = "";
		
		
		traningMappingVO.setCompanyId(companyId);
		traningMappingVO.setTraningProcessId(traningProcessId);

		LOGGER.debug("#### addCount ==> "+addCount);

		//훈련과정에 메핑된 개설강좌 목록 count 만큼 lms_traning_process_mapping 훈련과정메핑 테이블에 update 한다
		if(!StringUtils.isBlank(yyyy)){
			/*
			for( int idx = 1 ; idx <= addCount ; idx++ ) {

				//신규 개설교과 메핑 count 조회
				if(!"".equals(newYyyyArr)){
					newTermArr = StringUtils.defaultString( (String)paramMap.get("term-"+idx) , "" );
					newSubjectCodeArr = StringUtils.defaultString( (String)paramMap.get("subjectCode-"+idx) , "" );
					newSubClassArr = StringUtils.defaultString( (String)paramMap.get("subClass-"+idx) , "" );

					traningMappingVO.setYyyy(newYyyyArr);
					traningMappingVO.setTerm(newTermArr);
					traningMappingVO.setSubjectCode(newSubjectCodeArr);
					traningMappingVO.setSubClass(newSubClassArr);

					subjChkCount = traningService.getTraningProcessMappingCnt(traningMappingVO);

					LOGGER.debug("#### subjChkCount ==> "+subjChkCount);

					if(subjChkCount > 0){
						TraningVO VO = new TraningVO();
						retMsg = "처리할 기업에 훈련과정명 ("+traningProcessName+")건으로 훈련과정 개설강좌 메핑이 이미 처리되어 있습니다.";

						VO.setTempTraningProcessId("noDivPopup");

						redirectAttributes.addFlashAttribute("frmTraning", VO);
						redirectAttributes.addFlashAttribute("retMsg", retMsg);
						return "redirect:/lu/traning/listTraningProcessManage.do";
					}
				}
			}
				*/
			
			for( int idx = 1 ; idx <= addCount ; idx++ ) {
				yyyyArr = StringUtils.defaultString( (String)paramMap.get("yyyy"+idx) , "" );
				termArr = StringUtils.defaultString( (String)paramMap.get("term"+idx) , "" );
				subjectCodeArr = StringUtils.defaultString( (String)paramMap.get("subjectCode"+idx) , "" );
				subClassArr = StringUtils.defaultString( (String)paramMap.get("subClass"+idx) , "" );

				LOGGER.debug("#### idx ==> ["+idx + "] #### yyyyArr ==> "+yyyyArr);

				if(!"".equals(yyyyArr)){ //기존 훈련정보 개설교과 메핑정보 수정

					traningMappingVO.setYyyy(yyyyArr);
					traningMappingVO.setTerm(termArr);
					traningMappingVO.setSubjectCode(subjectCodeArr);
					traningMappingVO.setSubClass(subClassArr);
					
					subjChkCount = traningService.getTraningProcessMappingCnt(traningMappingVO);

					if(subjChkCount > 0)
					{
						updateCount2 = traningService.updateTraningProcessMappingInfoList(traningMappingVO);
					}
					else
					{
						insertCount = traningService.insertTraningProcessMappingInfoListUpdate(traningMappingVO);
					}
					LOGGER.debug("#### insertTempCount ==> "+insertTempCount);

					if(insertTempCount > 0){
						insertCount = insertTempCount+1;
					}
				}
			}
		}

		if(updateCount2 > 0 || insertCount > 0){
			
			//훈련과정에 메핑된 개설강좌
			TraningProcessMappingVO mappingVO = new TraningProcessMappingVO();
			TraningProcessMappingVO resultMappingVO = new TraningProcessMappingVO();
			List<TraningProcessMappingVO> listOjtSubjectInfo = traningService.listOjtSubjectInfo(traningMappingVO);
			List<TraningProcessMappingVO> listOffjtSubjectInfo = traningService.listOffJtSubjectInfo(traningMappingVO);
			
			TraningProcessMappingVO tempVo = new TraningProcessMappingVO();
			listOjtSubjectInfo.addAll(listOffjtSubjectInfo);
			
			boolean deleteFlag = true;
			for(int i=0; i < listOjtSubjectInfo.size(); i++)
			{
				tempVo = listOjtSubjectInfo.get(i);
				deleteFlag = true;
				
				for( int idx = 1 ; idx <= addCount ; idx++ ) {
					yyyyArr = StringUtils.defaultString( (String)paramMap.get("yyyy"+idx) , "" );
					termArr = StringUtils.defaultString( (String)paramMap.get("term"+idx) , "" );
					subjectCodeArr = StringUtils.defaultString( (String)paramMap.get("subjectCode"+idx) , "" );
					subClassArr = StringUtils.defaultString( (String)paramMap.get("subClass"+idx) , "" );
					
					if(tempVo.getYyyy().equals(yyyyArr) && tempVo.getTerm().equals(termArr) && tempVo.getSubjectCode().equals(subjectCodeArr) && tempVo.getSubClass().equals(subClassArr))
					{
						deleteFlag = false;
						break;
					}
				}
				
				if(deleteFlag)
				{
					traningService.deleteTraningProcessMappingInfoSubjList(tempVo);
				}
			}
			
			TraningVO VO = new TraningVO();

			retMsg = "정상적으로 (저장)처리되었습니다.!";
			
			VO.setCompanyId(companyId);
			VO.setTraningProcessId(traningProcessId);
			VO.setTempTraningProcessId("noDivPopup");

			redirectAttributes.addFlashAttribute("frmTraning", VO);
			redirectAttributes.addFlashAttribute("retMsg", retMsg);
			
			return "redirect:/lu/traning/getTraningProcessManageList.do";
		}
		else
		{
			TraningVO VO = new TraningVO();

			retMsg = "처리된건이 없습니다.!";
			VO.setTempTraningProcessId("noDivPopup");

			redirectAttributes.addFlashAttribute("frmTraning", VO);
			redirectAttributes.addFlashAttribute("retMsg", retMsg);

			return "redirect:/lu/traning/listTraningProcessManage.do";
		}
	}
}
