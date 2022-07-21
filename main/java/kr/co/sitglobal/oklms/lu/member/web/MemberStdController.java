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
package kr.co.sitglobal.oklms.lu.member.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import kr.co.sitglobal.aunuri.service.AunuriLinkService;
import kr.co.sitglobal.aunuri.vo.AunuriLinkEvalPlanNcsVO;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.commbiz.cmmcode.vo.CommonCodeVO;
import kr.co.sitglobal.oklms.la.company.service.CompanyService;
import kr.co.sitglobal.oklms.la.company.vo.CompanyVO;
import kr.co.sitglobal.oklms.lu.member.service.MemberStdService;
import kr.co.sitglobal.oklms.lu.member.vo.ExcelMemberStdVO;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import egovframework.com.cmm.util.EgovDoubleSubmitHelper;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
* Controller 클래스에 대한 내용을 작성한다.
* @author 이진근
* @since 2016. 10. 27.
*/
@Controller
public class MemberStdController extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(MemberStdController.class);

	@Resource(name = "MemberStdService")
	private MemberStdService memberStdService;

	@Resource(name = "aunuriLinkService")
	private AunuriLinkService aunuriLinkService;

	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;

	@Resource(name = "companyService")
	private CompanyService companyService;

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

	//센터담장자 > 담당기업체관리 > 사용자관리 > 회원 목록
	@RequestMapping(value = "/lu/member/listMember.do")
	public String listMember(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmMember") ExcelMemberStdVO memberStdVO, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

  		LOGGER.debug("#### paramMap ==> "+paramMap);
		LOGGER.debug("#### memberStdVO ==> "+memberStdVO);

		String retMsg = "";
		String searchKeyword = "";
		String searchKeywordNull = "";
		String pageSizeStr = "";
		String pageIndexStr = "";
		String tempTraningProcessId = "";
		String returnMsg = "POPUP_LIST_FAIL";
		String returnUrl = "oklms/lu/member/memberCotList";
		String companyId = StringUtils.defaultString( (String)paramMap.get("companyId") , "" );
		String memberType = StringUtils.defaultString( (String)paramMap.get("memberType") , "COT" );
		String traningProcessMappingId = StringUtils.defaultString( (String)paramMap.get("traningProcessMappingId") , "" );
		String subjectMappingMemSeq = StringUtils.defaultString( (String)paramMap.get("subjectMappingMemSeq") , "" );
		String subjectMappingDetailYn = StringUtils.defaultString( (String)paramMap.get("subjectMappingDetailYn") , "N" );
		String addTempCount = StringUtils.defaultString( (String)paramMap.get("addCount") , "0" );
		searchKeyword = StringUtils.defaultString((String)paramMap.get("searchKeyword"),"");
		searchKeywordNull = StringUtils.defaultString((String)paramMap.get("searchKeywordNull"),"");
		pageSizeStr = StringUtils.defaultString((String)paramMap.get("pageSize"),"");
		pageIndexStr = StringUtils.defaultString((String)paramMap.get("pageIndex"),"");
		tempTraningProcessId = StringUtils.defaultString((String)paramMap.get("tempTraningProcessId"),"");

		int cnt = 0;
		int addCount = 0;
		addCount = Integer.parseInt(addTempCount);
 		CompanyVO companyVO = new CompanyVO();

		if(!"".equals(memberStdVO.getMemType())){
			memberType = memberStdVO.getMemType();
		}

		if("".equals(companyId)){
			companyId = memberStdVO.getCompanyId();
		}

		//기업체에 메핑된 훈련과정정보 조회
		if(StringUtils.isBlank( companyId)){
			companyVO.setCompanyId(null);
			companyVO.setTraningProcessId(null);

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
		}

 		if(!StringUtils.isBlank( companyId)){
			companyVO.setCompanyId(companyId);
			memberStdVO.setCompanyId(companyId);

			List<CompanyVO> listCompanyTraningProcess = companyService.listCompanySearch(companyVO);
			cnt = listCompanyTraningProcess.size();
			model.addAttribute("resultCompanyTraningProcessList", listCompanyTraningProcess);
			//model.addAttribute("hrdTraningName", listCompanyTraningProcess.get(0).getHrdTraningName());

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

			if("COT".equals(memberType)){ //기업현장교사 목록 조회
				memberStdVO.setSubjectMappingCnt("1"); //기업현장교사 개설강좌 메핑전

				List<ExcelMemberStdVO> listMemberCot = memberStdService.listMemberCot( memberStdVO );
				model.addAttribute("resultMemberCotList", listMemberCot);
				returnUrl = "oklms/lu/member/memberCotList";

				//기업체에 메핑된 훈련과정정보 조회
				if(!StringUtils.isBlank( traningProcessMappingId )){
					memberStdVO.setTraningProcessMappingIdArr(traningProcessMappingId.split(","));
					memberStdVO.setMemType("COT");
					memberStdVO.setSubjectMappingCnt("2"); //기업현장교사 개설강좌 메핑후
					memberStdVO.setMemSeq(subjectMappingMemSeq);

					List<ExcelMemberStdVO> listMemberSubjectCodeMapping = memberStdService.listMemberSubjectCodeMapping(memberStdVO);
					model.addAttribute("resultList1", listMemberSubjectCodeMapping);
				} else {
					retMsg = "기업현장교사에게 메핑할 개설강좌 정보가없습니다.!";

			        redirectAttributes.addFlashAttribute("retMsg", retMsg);
				}

				//기업현장교사에 메핑된 개설강좌 정보 조회 목록
				if("Y".equals(subjectMappingDetailYn)){
					memberStdVO.setMemType("COT");
					memberStdVO.setSubjectMappingCnt("3"); //기업현장교사 개설강좌 메핑된건 상세정보
					memberStdVO.setMemSeq(subjectMappingMemSeq);

					List<ExcelMemberStdVO> listMemberSubjectCodeMappingDetail = memberStdService.listMemberSubjectCodeMappingDetail(memberStdVO);
					model.addAttribute("resultList2", listMemberSubjectCodeMappingDetail);
				}

			} else if("CCM".equals(memberType)){ //HRD담당자 목록 조회
				List<ExcelMemberStdVO> listMemberCcm = memberStdService.listMemberCcm( memberStdVO );
				model.addAttribute("resultMemberCcmList", listMemberCcm);
				returnUrl = "oklms/lu/member/memberCcmList";
			} else if("STD".equals(memberType)){ //학습근로자 목록 조회
				List<ExcelMemberStdVO> listMemberStd = memberStdService.listMemberStd( memberStdVO );
				model.addAttribute("resultMemberStdList", listMemberStd);
				returnUrl = "oklms/lu/member/memberStdList";
			} else { //지도교수 목록 조회
				List<ExcelMemberStdVO> listMemberPrt = memberStdService.listMemberPrt( memberStdVO );
				model.addAttribute("resultMemberPrtList", listMemberPrt);
				returnUrl = "oklms/lu/member/memberPrtList";
			}
		}

/*		if("COT".equals(memberType)){ //기업현장교사 목록 조회
			returnUrl = "oklms/lu/member/memberCotList";
		} else if("CCM".equals(memberType)){ //HRD담당자 목록 조회
			returnUrl = "oklms/lu/member/memberCcmList";
		} else if("STD".equals(memberType)){ //학습근로자 목록 조회
			returnUrl = "oklms/lu/member/memberStdList";
		} else { //지도교수 목록 조회
			returnUrl = "oklms/lu/member/memberPrtList";
		}*/

        model.addAttribute("MemberStdVO", memberStdVO);
        model.addAttribute("companyId", memberStdVO.getCompanyId());
        model.addAttribute("companyName", memberStdVO.getCompanyName());
        model.addAttribute("traningProcessId", traningProcessMappingId);
        model.addAttribute("cnt", cnt);
        model.addAttribute("addCount", addCount);
        model.addAttribute("subjectMappingCnt", memberStdVO.getSubjectMappingCnt());

		// View호출
		return returnUrl;
	}

	//센터담장자 > 담당기업체관리 > 사용자관리 > 특정 기업현장교사에 메핑할 개설강좌 목록
	@RequestMapping(value = "/lu/member/listMemberSubjectCodeMapping.do")
	public String listMemberSubjectCodeMapping(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmMember") ExcelMemberStdVO memberStdVO, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		LOGGER.debug("#### paramMap ==> "+paramMap);
		LOGGER.debug("#### memberStdVO ==> "+memberStdVO);

		String retMsg = "";
		String traningProcessMappingId = StringUtils.defaultString( (String)paramMap.get("traningProcessMappingId") , "" );

		//기업체에 메핑된 훈련과정정보 조회
		if(!StringUtils.isBlank( traningProcessMappingId)){
			memberStdVO.setTraningProcessMappingIdArr(traningProcessMappingId.split(","));
			memberStdVO.setMemType("COT");
			memberStdVO.setSubjectMappingCnt("Y");

			List<ExcelMemberStdVO> listMemberSubjectCodeMapping = memberStdService.listMemberSubjectCodeMapping(memberStdVO);
			model.addAttribute("resultList", listMemberSubjectCodeMapping);
		} else {
			retMsg = "기업현장교사에게 메핑할 개설강좌 정보가없습니다.!";

	        redirectAttributes.addFlashAttribute("retMsg", retMsg);
			redirectAttributes.addFlashAttribute("frmMember", memberStdVO);
		}

        model.addAttribute("MemberStdVO", memberStdVO);



		// View호출
		return "redirect:/lu/member/listMember.do";
	}

	//센터담장자 > 담당기업체관리 > 담당자변경신청 > 담당자 변경신청, 담당자 변경내역 목록
	@RequestMapping(value = "/lu/member/listMemberChangeApplication.do")
	public String listMemberChangeApplication(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmMember") ExcelMemberStdVO memberStdVO, ModelMap model) throws Exception {
		LOGGER.debug("#### paramMap ==> "+paramMap);
		LOGGER.debug("#### memberStdVO ==> "+memberStdVO);

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(memberStdVO);

		List<ExcelMemberStdVO> list1 = memberStdService.listMemberChangeApplicationNew( memberStdVO ); 	//담당자 변경신청 목록
		List<ExcelMemberStdVO> list2 = memberStdService.listMemberChangeApplication( memberStdVO ); 	//담당자 변경내역 목록

		model.addAttribute("resultChangeAppNewList", list1);
		model.addAttribute("resultChangeAppList", list2);
		model.addAttribute("cnt1", list1.size());
		model.addAttribute("cnt2", list2.size());
		model.addAttribute("MemberStdVO", memberStdVO);

		// View호출
		return "oklms/lu/member/memberChangeApplicationList";
	}

	//센터담장자 > 담당기업체관리 > 담당자변경신청 > 신규화면 이동
	@RequestMapping(value = "/lu/member/goInsertMemberChangeApplication.do")
	public String goInsertMemberChangeApplication(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmMember") ExcelMemberStdVO memberStdVO, ModelMap model) throws Exception {
		String returnUrl = "";
		String returnMsg = "POPUP_LIST_FAIL";
		String companyId = StringUtils.defaultString((String)paramMap.get("companyId"),"");
		String traningProcessId = StringUtils.defaultString((String)paramMap.get("traningProcessId"),"");
		String tempTraningProcessId = StringUtils.defaultString((String)paramMap.get("tempTraningProcessId"),"");
		String searchKeyword = StringUtils.defaultString((String)paramMap.get("searchKeyword"),"");
		String searchKeywordNull = StringUtils.defaultString((String)paramMap.get("searchKeywordNull"),"");
		String pageSizeStr = StringUtils.defaultString((String)paramMap.get("pageSize"),"");
		String pageIndexStr = StringUtils.defaultString((String)paramMap.get("pageIndex"),"");
		//int cnt = 0;

		CommonCodeVO codeVO = new CommonCodeVO();
		CompanyVO companyVO = new CompanyVO();

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(memberStdVO);

		codeVO.setCodeGroup("JOB_POSITION"); //직위
		List<CommonCodeVO> jobPositionCodeList = commonCodeService.selectCmmCodeList(codeVO);

		codeVO.setCodeGroup("QFE_ABILITY"); //학력
		List<CommonCodeVO> qfeAbilityCodeList = commonCodeService.selectCmmCodeList(codeVO);

		codeVO.setCodeGroup("MOBILE_NUM");
		List<CommonCodeVO> mobileTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);

		codeVO.setCodeGroup("EMAIL_DOMAIN");
		List<CommonCodeVO> emailDomainCodeList = commonCodeService.selectCmmCodeList(codeVO);

		codeVO.setCodeGroup("CALENDAR_YEAR");
		List<CommonCodeVO> yearCodeList = commonCodeService.selectCmmCodeList(codeVO);

		codeVO.setCodeGroup("CALENDAR_MONTH");
		List<CommonCodeVO> monthCodeList = commonCodeService.selectCmmCodeList(codeVO);

		codeVO.setCodeGroup("CALENDAR_DAY");
		List<CommonCodeVO> dayCodeList = commonCodeService.selectCmmCodeList(codeVO);

		//List<CompanyVO> listCompanyTraningProcessSearch = companyService.listCompanyTraningProcess(companyVO);

		/*if(!"".equals(companyId) && !"".equals(traningProcessId)){
			cnt = cnt+1;
		}*/

		model.addAttribute("jobPositionCode", jobPositionCodeList);  //공통코드 - 직위
        model.addAttribute("qfeAbilityCode", qfeAbilityCodeList);    //공통코드 - 학력
        model.addAttribute("mobileTelNoCode", mobileTelNoCodeList);  //공통코드 - 핸드론번호
        model.addAttribute("emailDomainCode", emailDomainCodeList);  //공통코드 - 이메일
        model.addAttribute("yearCode", yearCodeList); 				 //공통코드 - 년도
		model.addAttribute("monthCode", monthCodeList); 		     //공통코드 - 월
		model.addAttribute("dayCode", dayCodeList); 				 //공통코드 - 일
        //model.addAttribute("resultSearchList", listCompanyTraningProcessSearch); //기업체 훈련과정 검색목록
        //model.addAttribute("cnt", cnt);

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
		if(!"".equals(companyId) && !"".equals(traningProcessId)){
			memberStdVO.setCompanyId(companyId);
			memberStdVO.setTraningProcessId(traningProcessId);

			companyVO.setCompanyId(companyId);
			companyVO.setTraningProcessId(traningProcessId);
			List<CompanyVO> listCompanyTraningProcess = companyService.listCompanyTraningProcess(companyVO);
			model.addAttribute("resultCompanyTraningProcessList", listCompanyTraningProcess); //기업체 훈련과정 검색목록
			model.addAttribute("cnt", listCompanyTraningProcess.size());
		}

		model.addAttribute("MemberStdVO", memberStdVO);

        if("01".equals(memberStdVO.getCretGubun())){ //변경신청
        	returnUrl = "oklms/lu/member/memberChangeApplicationModCret";
        }else{ //신규작성
        	returnUrl = "oklms/lu/member/memberChangeApplicationNewCret";
        }

		// View호출
		return returnUrl;
	}

	//센터담장자 > 담당기업체관리 > 담당자변경신청 > 수정화면 이동
	@RequestMapping(value = "/lu/member/goUpdateMemberChangeApplication.do")
	public String goUpdateMemberChangeApplication(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmMember") ExcelMemberStdVO memberStdVO, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		String returnUrl = "";
		String retMsg = "";
		CommonCodeVO codeVO = new CommonCodeVO();
		ExcelMemberStdVO readVO = new ExcelMemberStdVO();
		//CompanyVO paramVO = new CompanyVO();

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(memberStdVO);

		memberStdVO.setMemSeqs(memberStdVO.getMemSeq().split(","));

		codeVO.setCodeGroup("JOB_POSITION"); //직위
		List<CommonCodeVO> jobPositionCodeList = commonCodeService.selectCmmCodeList(codeVO);

		codeVO.setCodeGroup("QFE_ABILITY"); //학력
		List<CommonCodeVO> qfeAbilityCodeList = commonCodeService.selectCmmCodeList(codeVO);

		codeVO.setCodeGroup("MOBILE_NUM");
		List<CommonCodeVO> mobileTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);

		codeVO.setCodeGroup("EMAIL_DOMAIN");
		List<CommonCodeVO> emailDomainCodeList = commonCodeService.selectCmmCodeList(codeVO);

		codeVO.setCodeGroup("CALENDAR_YEAR");
		List<CommonCodeVO> yearCodeList = commonCodeService.selectCmmCodeList(codeVO);

		codeVO.setCodeGroup("CALENDAR_MONTH");
		List<CommonCodeVO> monthCodeList = commonCodeService.selectCmmCodeList(codeVO);

		codeVO.setCodeGroup("CALENDAR_DAY");
		List<CommonCodeVO> dayCodeList = commonCodeService.selectCmmCodeList(codeVO);

		readVO = memberStdService.getMemberChangeApplication(memberStdVO);

		System.out.println("goUpdateMemberChangeApplication ReadVO ==> "+readVO);

		if(readVO == null){
			ExcelMemberStdVO retrunVO = new ExcelMemberStdVO();

			retMsg = "기업현장교사 혹은 HRD전담자가 메핑이 안된 데이타입니다. ";

			redirectAttributes.addFlashAttribute("retMsg", retMsg);
			redirectAttributes.addFlashAttribute("frmMember", retrunVO);

			return "redirect:/lu/member/listMemberChangeApplication.do";

		} else {
			if("COT".equals(readVO.getMemType())){
				returnUrl = "oklms/lu/member/memberChangeApplicationCotUpdt";
			} else {
				returnUrl = "oklms/lu/member/memberChangeApplicationCcmUpdt";
			}

			model.addAttribute("jobPositionCode", jobPositionCodeList);  //공통코드 - 직위
	        model.addAttribute("qfeAbilityCode", qfeAbilityCodeList);    //공통코드 - 학력
	        model.addAttribute("mobileTelNoCode", mobileTelNoCodeList);  //공통코드 - 핸드론번호
	        model.addAttribute("emailDomainCode", emailDomainCodeList);  //공통코드 - 이메일
	        model.addAttribute("yearCode", yearCodeList); 				 //공통코드 - 년도
			model.addAttribute("monthCode", monthCodeList); 		     //공통코드 - 월
			model.addAttribute("dayCode", dayCodeList); 				 //공통코드 - 일
	        //model.addAttribute("resultList", listCompanyTraningProcess); //기업체 훈련과정 메핑목록
	        model.addAttribute("MemberReadVO", readVO); //상세 정보
	        model.addAttribute("MemberStdVO", memberStdVO); //파라메터 정보
		}

		// View호출
		return returnUrl;
	}

	@RequestMapping(value = "/lu/member/getMemberChangeApplication.do")
	public String getMemberChangeApplication(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmMember")ExcelMemberStdVO memberStdVO,  ModelMap model) throws Exception {
        String returnUrl = "";
		ExcelMemberStdVO readVO = new ExcelMemberStdVO();
		//CompanyVO paramVO = new CompanyVO();

        LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(memberStdVO);

		memberStdVO.setMemSeqs(memberStdVO.getMemSeq().split(","));

		/*paramVO.setCompanyId(memberStdVO.getCompanyId());
		paramVO.setTraningProcessId(memberStdVO.getTraningProcessId());
		List<CompanyVO> listCompanyTraningProcess = companyService.listCompanyTraningProcess(paramVO);*/
		readVO = memberStdService.getMemberChangeApplication(memberStdVO);

		System.out.println("getMemberChangeApplication ReadVO ==> "+readVO);

		if(readVO != null && "COT".equals(readVO.getMemType())){
			returnUrl = "oklms/lu/member/memberChangeApplicationCotRead";
		} else {

			returnUrl = "oklms/lu/member/memberChangeApplicationCcmRead";
		}

        //model.addAttribute("resultCompanyTraningProcessList", listCompanyTraningProcess); //기업체 훈련과정 메핑 목록
        model.addAttribute("MemberReadVO", readVO); //상세 정보
        model.addAttribute("MemberStdVO", memberStdVO);

		// View호출
		return returnUrl;
	}

	//센터담장자 > 담당기업체관리 > 사용자 관리 > 기업현장교사, HRD전담자 엑실일괄업로드 저장
	@RequestMapping(value = "/lu/member/insertMemberExcel.do")
	public String insertMemberExcel(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmMember") @Valid ExcelMemberStdVO memberStdVO, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status, MultipartHttpServletRequest multiRequest) throws Exception {
		String companyId = StringUtils.defaultString( (String)paramMap.get("companyId") , "" );
		String companyName = StringUtils.defaultString( (String)paramMap.get("companyName") , "" );
		String traningProcessId = StringUtils.defaultString( (String)paramMap.get("traningProcessId") , "" );
		String memberType = StringUtils.defaultString( (String)paramMap.get("memberType") , "COT" );
		String retMsg = "";

		memberStdVO.setCompanyId(companyId);
		//memberStdVO.setCompanyName(companyName);
		memberStdVO.setTraningProcessId(traningProcessId);
		memberStdVO.setMemType(memberType);
		String result = memberStdService.insertMemberAllExcel(memberStdVO, multiRequest);

		 if(result == "SUCCESS"){
			 if("COT".equals(memberType)){
				 retMsg = "정상적으로 기업현장교사 사용자정보를 엑셀 일괄업로드 처리되었습니다.!";
			 }else{
				 retMsg = "정상적으로 HRD담당자 사용자정보를 엑셀 일괄업로드 처리되었습니다.!";
			 }
		 }else if(result == "FAIL"){
			 if("COT".equals(memberType)){
				 retMsg = "기업현장교사 사용자정보 엑셀 일괄등록시 에러가 발생하였습니다.!";
			 }else{
				 retMsg = "HRD담당자 사용자정보 엑셀 일괄등록시 에러가 발생하였습니다.!";
			 }
		 }else{
			 if("COT".equals(memberType)){
				 retMsg = "("+ result + ")" + "이미등록된 기업현장교사ID 입니다.!";
			 }else{
				 retMsg = "("+ result + ")" + "이미등록된 HRD담당자ID 입니다.!";
			 }
		 }

		ExcelMemberStdVO vo = new ExcelMemberStdVO();
		vo.setCompanyId(companyId);
		vo.setCompanyName(companyName);
		vo.setTraningProcessId(traningProcessId);
		vo.setMemType(memberType);

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
	    redirectAttributes.addFlashAttribute("companyId", companyId);
	    redirectAttributes.addFlashAttribute("companyName", companyName);
	    redirectAttributes.addFlashAttribute("traningProcessId", traningProcessId);
	    redirectAttributes.addFlashAttribute("memberType", memberType);
		redirectAttributes.addFlashAttribute("frmMember", vo);

		return "redirect:/lu/member/listMember.do";
	}

	//센터담장자 > 담당기업체관리 > 담당자변경신청 > 신규건 저장
	@RequestMapping(value = "/lu/member/insertMemberChangeApplication.do")
	public String insertMemberChangeApplication(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmMember") @Valid ExcelMemberStdVO memberStdVO, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		String retMsg = "";
		String resultSave = "";
		ExcelMemberStdVO retrunVO = new ExcelMemberStdVO();

		resultSave = memberStdService.insertMemberChangeApplication(memberStdVO);

		if("FAIL".equals(resultSave)){
			retMsg = "담당자 변경신청이 실패 하였습니다.!";
		}else{
			retMsg = "담당자 변경신청이 정상적으로 처리되었습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmMember", retrunVO);

		return "redirect:/lu/member/listMemberChangeApplication.do";
	}

	@RequestMapping(value = "/lu/member/updateMemberCotList.do")
	public String updateMemberCotList(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmMember") @Valid ExcelMemberStdVO memberStdVO, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		LOGGER.debug("#### paramMap ==> "+paramMap.toString());
		LOGGER.debug("#### memberStdVO ==> "+memberStdVO);
		String result  = "";
		String retMsg = "";
		String addTempCount = StringUtils.defaultString( (String)paramMap.get("addCount") , "0" );
		int addCount = 0;
		int updateCount = 0;
		int readAunuriCount = 0;
		int updateAunuriCount = 0;
		addCount = Integer.parseInt(addTempCount);

		if( addCount == 0 ){
			retMsg = "기업현장교사에게 메핑할 개설교과 정보가 없습니다.";
		} else {
			for( int idx = 1 ; idx <= addCount ; idx++ ) {
				String yyyyArr = StringUtils.defaultString( (String)paramMap.get("yyyy-"+idx) , "" );
				String termArr = StringUtils.defaultString( (String)paramMap.get("term-"+idx) , "" );
				String subjectCodeArr = StringUtils.defaultString( (String)paramMap.get("subjectCode-"+idx) , "" );
				String subClassArr = StringUtils.defaultString( (String)paramMap.get("subClass-"+idx) , "" );
				String subjectNameArr = StringUtils.defaultString( (String)paramMap.get("subjectName-"+idx) , "" );
				String memSeqArr = StringUtils.defaultString( (String)paramMap.get("memSeq-"+idx) , "" );

				LOGGER.debug("#### idx ==> ["+idx + "] #### yyyyArr ==> "+yyyyArr);
				LOGGER.debug("#### idx ==> ["+idx + "] #### termArr ==> "+termArr);
				LOGGER.debug("#### idx ==> ["+idx + "] #### subjectCodeArr ==> "+subjectCodeArr);
				LOGGER.debug("#### idx ==> ["+idx + "] #### subClassArr ==> "+subClassArr);
				LOGGER.debug("#### idx ==> ["+idx + "] #### subjectNameArr ==> "+subjectNameArr);
				LOGGER.debug("#### idx ==> ["+idx + "] #### memSeqArr ==> "+memSeqArr);

				memberStdVO.setYyyy(yyyyArr);
				memberStdVO.setTerm(termArr);
				memberStdVO.setSubjectCode(subjectCodeArr);
				memberStdVO.setSubClass(subClassArr);
				memberStdVO.setMemSeq(memSeqArr);

				memberStdService.deleteMemberTutMapping(memberStdVO);
				updateCount = memberStdService.insertMemberTutMapping(memberStdVO);

				if(updateCount > 0){
					result = memberStdService.getMemberTutMappingGgoupName(memberStdVO);

					//아우누리에 체크된 개설강좌코드로 메핑된 기업현장교사가 있는지 조회
					AunuriLinkEvalPlanNcsVO evalPlanNcsVO = new AunuriLinkEvalPlanNcsVO();
					evalPlanNcsVO.setYyyy(yyyyArr);
					evalPlanNcsVO.setTerm("0"+termArr);
					evalPlanNcsVO.setSubjectCode(subjectCodeArr);
					evalPlanNcsVO.setSubClass(subClassArr);
					evalPlanNcsVO.setCodeName(result);
					readAunuriCount = aunuriLinkService.getAunuriMemberCnt(evalPlanNcsVO);

					//기업현장교사 개설강좌코드 정보 신규
					if(readAunuriCount == 0){
						updateAunuriCount += aunuriLinkService.insertAunuriSubjectInfoTutMapping(evalPlanNcsVO);
					}else{
					//기업현장교사 개설강좌코드 정보 수정
						updateAunuriCount += aunuriLinkService.updateAunuriSubjectInfoTutMapping(evalPlanNcsVO);
					}
				}
			}

			if(updateCount > 0 && updateAunuriCount > 0){
				retMsg = "개설교과 메핑이 정상적으로 처리되었습니다.!";
			}else{
				retMsg = "개설교과 메핑시 오류가 발생하였습니다.";
			}
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmMember", memberStdVO);

		return "redirect:/lu/member/listMember.do";
	}

	@RequestMapping(value = "/lu/member/updateMemberCcmList.do")
	public String updateMemberCcmList(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmMember") @Valid ExcelMemberStdVO memberStdVO, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		LOGGER.debug("#### paramMap ==> "+paramMap);
		LOGGER.debug("#### memberStdVO ==> "+memberStdVO);

		String retMsg = "";
		String retrunMemName = "";
		String memName = "";
		String companyId = StringUtils.defaultString( (String)paramMap.get("companyId") , "" );
		int updateCount = 0;

		if( StringUtils.isBlank(memberStdVO.getMemSeq()) ){
			retMsg = "훈련과정관리 정보가 없습니다.";
		} else {
			String[] memSeq1 = memberStdVO.getMemSeq().split(",");

			for( int i = 0 ; i < memSeq1.length; i++ ) {
				String[] memSeq2 = memSeq1[i].split("-");
				String tmpMemSeq = memSeq2[0];
				int tmpMemIdCount = Integer.valueOf(memSeq2[2]);
				String traningProcessId = StringUtils.defaultString( (String)paramMap.get("traningProcessId-"+tmpMemSeq) , "" );
				memName = memberStdService.getMemberName(tmpMemSeq);

				LOGGER.debug("#### tmpMemSeq ==> "+tmpMemSeq);
				LOGGER.debug("#### traningProcessId ==> "+traningProcessId);
				LOGGER.debug("#### memName ==> "+memName);

				memberStdVO.setCompanyId(companyId);
				memberStdVO.setTraningProcessId(traningProcessId);
				memberStdVO.setMemSeq(tmpMemSeq);
				memberStdVO.setMemIdCount(tmpMemIdCount);
				if(!"".equals(companyId) && !"".equals(traningProcessId) && !"".equals(tmpMemSeq)){
					updateCount += memberStdService.updateSubjCcmMapping(memberStdVO);
				}

				if(updateCount == 0){
					retrunMemName += memName + ",";
				}
			}

			if(updateCount > 0){
				retMsg = "정상적으로 (수정)처리되었습니다.!";
			} else {
				retMsg = "수정시 데이타가 없어 수정처리하지 못했습니다.!("+retrunMemName+")";
			}

		}

			/*memberStdVO.setMemSeqs(memberStdVO.getMemSeq().split(","));

			LOGGER.debug("#### memberStdVO.getMemSeqs().length ==> "+memberStdVO.getMemSeqs().length);

			for( int idx = 0 ; idx < memberStdVO.getMemSeqs().length ; idx++ ) {
				String traningProcessIdArr = "";
				String oldTraningProcessIdArr = "";
				String memSeq = "";

				memSeq = memberStdVO.getMemSeqs()[idx];
				traningProcessIdArr = StringUtils.defaultString( (String)paramMap.get("traningProcessId-"+memSeq) , "" );
				oldTraningProcessIdArr = StringUtils.defaultString( (String)paramMap.get("oldTraningProcessId-"+memSeq) , "" );

				LOGGER.debug("#### traningProcessIdArr ==> "+traningProcessIdArr);
				LOGGER.debug("#### oldTraningProcessIdArr ==> "+oldTraningProcessIdArr);
				LOGGER.debug("#### memSeqs ==> "+memSeq);

				ExcelMemberStdVO paramVO = new ExcelMemberStdVO();
				paramVO.setCompanyId(memberStdVO.getCompanyId());
				paramVO.setTraningProcessId(traningProcessIdArr);
				paramVO.setOldTraningProcessId(oldTraningProcessIdArr);
				paramVO.setMemSeq(memSeq);

				if( !StringUtils.isBlank( traningProcessIdArr ) ){
					updateCount = memberStdService.updateSubjCcmMapping(paramVO);
				}
			}

			if(updateCount > 0){
				retMsg = "정상적으로 (수정)처리되었습니다.!";
			}else{
				retMsg = "훈련과정을 메핑할 HRD전담자 정보가 없습니다.";
			}*/


		ExcelMemberStdVO vo = new ExcelMemberStdVO();
		vo.setCompanyId(companyId);
		//vo.setTraningProcessId(traningProcessId);
		vo.setMemType("CCM");

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmMember", vo);

		return "redirect:/lu/member/listMember.do";
	}

	@RequestMapping(value = "/lu/member/updateMemberChangeApplicationNew.do")
	public String updateMemberChangeApplicationNew(@ModelAttribute("frmMember") @Valid ExcelMemberStdVO memberStdVO, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "";
		int updateCnt = 0;
		ExcelMemberStdVO retrunVO = new ExcelMemberStdVO();

		if( StringUtils.isBlank( memberStdVO.getMemSeq() ) ){
			retMsg = "존재하지 않는 정보입니다.";
		}else{
			updateCnt = memberStdService.updateMemberChangeApplicationNew( memberStdVO );
			if(updateCnt == 0){
				retMsg = "기존에 메핑된 정보가 있어 처리하지 못하였습니다.!";
			}else{
				retMsg = "정상적으로 처리되었습니다.!";
			}
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmMember", retrunVO);

		return "redirect:/lu/member/listMemberChangeApplication.do";
	}

	@RequestMapping(value = "/lu/member/updateMemberChangeApplication.do")
	public String updateMemberChangeApplication(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmMember") @Valid ExcelMemberStdVO memberStdVO, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String updtApplicationStatus = StringUtils.defaultString( (String)paramMap.get("updtApplicationStatus") , "" );
		String retMsg = "";
		int updateCnt = 0;
		ExcelMemberStdVO retrunVO = new ExcelMemberStdVO();

		if( StringUtils.isBlank( memberStdVO.getMemSeq() ) ){
			retMsg = "존재하지 않는 정보입니다.";
		}else{
			memberStdVO.setUpdtApplicationStatus(updtApplicationStatus);
			updateCnt = memberStdService.updateMemberChangeApplication( memberStdVO );
			if(updateCnt == 0){
				retMsg = "수정처리시 에러가 발생하였습니다.!";
			}else{
				retMsg = "정상적으로 (수정)처리되었습니다.!";
			}
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmMember", retrunVO);

		return "redirect:/lu/member/listMemberChangeApplication.do";
	}

	@RequestMapping(value = "/lu/member/updateMemberChangeApplicationApproval.do")
	public String updateMemberChangeApplicationApproval(@ModelAttribute("frmMember") @Valid ExcelMemberStdVO memberStdVO, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "";
		ExcelMemberStdVO retrunVO = new ExcelMemberStdVO();

		if( StringUtils.isBlank( memberStdVO.getMemSeq() ) ){
			retMsg = "존재하지 않는 정보입니다.";
		}else{
	    	memberStdService.updateMemberChangeApplicationApproval( memberStdVO );
	    	if("2".equals(memberStdVO.getStatus())){
	    		retMsg = "정상적으로 (승인)처리되었습니다.!";
	    	}else{
	    		retMsg = "정상적으로 (반려)처리되었습니다.!";
	    	}
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmMember", retrunVO);

		return "redirect:/lu/member/listMemberChangeApplication.do";
	}

	@RequestMapping(value = "/lu/member/updateMemberChangeApplicationReturn.do")
	public String updateMemberChangeApplicationReturn(@ModelAttribute("frmMember") @Valid ExcelMemberStdVO memberStdVO, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "";
		ExcelMemberStdVO retrunVO = new ExcelMemberStdVO();

		if( StringUtils.isBlank( memberStdVO.getMemSeq() ) ){
			retMsg = "존재하지 않는 정보입니다.";
		}else{
	    	memberStdService.updateMemberChangeApplicationApproval( memberStdVO );
	    	retMsg = "정상적으로 (수정)처리되었습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmMember", retrunVO);

		return "redirect:/lu/member/listMemberChangeApplication.do";
	}

	@RequestMapping(value = "/lu/member/deleteMemberChangeApplicationCot.do")
	public String deleteMemberChangeApplicationCot(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmMember") ExcelMemberStdVO memberStdVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		String retMsg = "";
		String yyyy = StringUtils.defaultString( (String)paramMap.get("oldYyyy") , "" );
		String term = StringUtils.defaultString( (String)paramMap.get("oldTerm") , "" );
		String subjectCode = StringUtils.defaultString( (String)paramMap.get("oldSubjectCode") , "" );
		String subClass = StringUtils.defaultString( (String)paramMap.get("oldSubClass") , "" );
		int deletetCnt1 = 0;
		int deletetCnt2 = 0;

		if( StringUtils.isBlank( memberStdVO.getMemSeq() ) && StringUtils.isBlank( yyyy )){
			retMsg = "삭제할 정보가 없습니다.";
		}else{

			deletetCnt1 = memberStdService.deleteChangeMember(memberStdVO);

			if(deletetCnt1 > 0){
				memberStdVO.setYyyy(yyyy);
				memberStdVO.setTerm(term);
				memberStdVO.setSubjectCode(subjectCode);
				memberStdVO.setSubClass(subClass);
				deletetCnt2 = memberStdService.deleteChangeMemberCot(memberStdVO);
			}
		}

		if(deletetCnt1 > 0 && deletetCnt2 > 0){
			retMsg = "정상적으로 삭제처리 되었습니다.";
		} else {
			retMsg = "삭제처리시 에러가 발생하였습니다.";
		}

		ExcelMemberStdVO vo = new ExcelMemberStdVO();

		redirectAttributes.addFlashAttribute("frmMember", vo);
		redirectAttributes.addFlashAttribute("retMsg", retMsg);

		return "redirect:/lu/member/listMemberChangeApplication.do";
	}

	@RequestMapping(value = "/lu/member/deleteMemberChangeApplicationCcm.do")
	public String deleteMemberChangeApplicationCcm(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmMember") ExcelMemberStdVO memberStdVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		String retMsg = "";
		int deletetCnt1 = 0;
		int deletetCnt2 = 0;

		if( StringUtils.isBlank( memberStdVO.getMemSeq() )){
			retMsg = "삭제할 정보가 없습니다.";
		}else{
			deletetCnt1 = memberStdService.deleteChangeMember(memberStdVO);

			if(deletetCnt1 > 0){
				deletetCnt2 = memberStdService.deleteMemberCcmList(memberStdVO);
			}
		}

		if(deletetCnt1 > 0 && deletetCnt2 > 0){
			retMsg = "정상적으로 삭제처리 되었습니다.";
		} else {
			retMsg = "삭제처리시 에러가 발생하였습니다.";
		}

		ExcelMemberStdVO vo = new ExcelMemberStdVO();

		redirectAttributes.addFlashAttribute("frmMember", vo);
		redirectAttributes.addFlashAttribute("retMsg", retMsg);

		return "redirect:/lu/member/listMemberChangeApplication.do";
	}

	@RequestMapping(value = "/lu/member/deleteMemberList.do")
	public String deleteMemberList(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmMember") ExcelMemberStdVO memberStdVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		String retMsg = "";
		String memberType = StringUtils.defaultString( (String)paramMap.get("memberType") , "" );
		String companyName = StringUtils.defaultString( (String)paramMap.get("tmpCompanyName") , "" );
		String companyId = StringUtils.defaultString( (String)paramMap.get("companyId") , "" );
		String memName = "";
		String retrunMemName = "";
		int deletetCnt = 0;
		int delReadCnt = 0;
		int tutNmLengthCnt = 0;

		if( StringUtils.isBlank( memberStdVO.getMemSeq() ) ){
			retMsg = "삭제할 정보가 없습니다.";
		}else{

			//기업현장교사 삭제시
			if("COT".equals(memberType)){
				memberStdVO.setMemSeqs(memberStdVO.getMemSeq().split(","));

				memberStdService.deleteMemberList( memberStdVO );

				for( int idx = 0 ; idx < memberStdVO.getMemSeqs().length; idx++ ) {
					memName = memberStdService.getMemberName(memberStdVO.getMemSeqs()[idx]);

					AunuriLinkEvalPlanNcsVO evalPlanNcsVO = new AunuriLinkEvalPlanNcsVO();
					evalPlanNcsVO.setCompanyName(companyName);
					evalPlanNcsVO.setTutorName(memName);
					delReadCnt = aunuriLinkService.getAunuriMemberTutCnt(evalPlanNcsVO);

					if(delReadCnt > 0){
						tutNmLengthCnt = aunuriLinkService.getAunuriMemberTutSize(evalPlanNcsVO);
						if(tutNmLengthCnt == 3){
							deletetCnt += aunuriLinkService.deleteAunuriTutMappingNull(evalPlanNcsVO);
						} else {
							deletetCnt += aunuriLinkService.deleteAunuriTutMapping(evalPlanNcsVO);
						}
					} else {
						if(idx == 0){
							retrunMemName += memName;
						} else {
							retrunMemName += "," + memName;
						}
					}
				}

				if(deletetCnt > 0){
					retMsg = "정상적으로 (삭제)처리되었습니다.!";
				} else {
					retMsg = "OKLMS 개설강좌 메핑된 기업현장교사는 삭제 됬으나.! 아우누리에 개설강좌 메핑된 기업현장교사는 데이타가 없어 삭제처리하지 못했습니다.!("+retrunMemName+")";
				}

				ExcelMemberStdVO vo = new ExcelMemberStdVO();
				vo.setCompanyId(companyId);
				//vo.setTraningProcessId(traningProcessId);
				vo.setMemType("COT");

			} else if("CCM".equals(memberType)){
				String[] memSeq1 = memberStdVO.getMemSeq().split(",");

				for( int i = 0 ; i < memSeq1.length; i++ ) {
					String[] memSeq2 = memSeq1[i].split("-");
					String tmpMemSeq = memSeq2[0];
					String tmpTraningProcessId = memSeq2[1];
					memName = memberStdService.getMemberName(tmpMemSeq);

					LOGGER.debug("#### tmpTraningProcessId ==> "+tmpTraningProcessId);
					LOGGER.debug("#### tmpMemSeq ==> "+tmpMemSeq);

					memberStdVO.setCompanyId(companyId);
					memberStdVO.setTraningProcessId(tmpTraningProcessId);
					memberStdVO.setMemSeq(tmpMemSeq);
					deletetCnt += memberStdService.deleteMemberCcmList(memberStdVO);

					if(delReadCnt == 0){
						retrunMemName += memName + ",";
					}
				}

				if(deletetCnt > 0){
					retMsg = "정상적으로 (삭제)처리되었습니다.!";
				} else {
					retMsg = "삭제시 데이타가 없어 삭제처리하지 못했습니다.!("+retrunMemName+")";
				}

			}

			ExcelMemberStdVO vo = new ExcelMemberStdVO();
			vo.setCompanyId(companyId);
			//vo.setTraningProcessId(traningProcessId);
			vo.setMemType("CCM");

			redirectAttributes.addFlashAttribute("frmMember", vo);
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		//redirectAttributes.addFlashAttribute("frmMember", memberStdVO);

		return "redirect:/lu/member/listMember.do";
	}

	/*@RequestMapping(value = "/lu/member/member/listExcelMember.do")
	public String listExcelMember(@ModelAttribute("frmMemberExcel") ExcelMemberStdVO excelMemberStdVO, ModelMap model) throws Exception {

		List<ExcelMemberStdVO> resultList = memberStdService.listMemberAllExcelList( excelMemberStdVO );

        model.addAttribute("MemberStdVO", excelMemberStdVO);
        model.addAttribute("resultList", resultList);

		// View호출
		return "oklms/lu/member/memberAllCretExcelList";
	}

	@RequestMapping(value = "/lu/member/member/getMember.do")
	public String getMember(@ModelAttribute("frmMember") MemberStdVO memberStdVO,  ModelMap model) throws Exception {
		memberStdVO = memberStdService.getMember( memberStdVO );

        model.addAttribute("MemberStdVO", memberStdVO);

		// View호출
		return "oklms/lu/member/memberRead";
	}

	@RequestMapping(value = "/lu/member/member/getTutorMember.do")
	public String getTutorMember(@ModelAttribute("frmTutorMember") MemberStdVO memberStdVO,  ModelMap model) throws Exception {
		memberStdVO = memberStdService.getMember( memberStdVO );

        model.addAttribute("MemberStdVO", memberStdVO);

		// View호출
		return "oklms/lu/member/tutorMemberRead";
	}

	@RequestMapping(value = "/lu/member/member/goInsertMember.do")
	public String goInsertMember(@ModelAttribute("frmMember") MemberStdVO memberStdVO,  ModelMap model)  throws Exception {

		//회원유형 공통코드 조회
		CommonCodeVO codeVO = new CommonCodeVO();
		codeVO.setCodeGroup("member");
		List<CommonCodeVO> authGroupCodeList = commonCodeService.selectAuthGroupCodeList(codeVO);

		//지역번호 공통코드 조회
		codeVO.setCodeGroup("LOCAL_NUM");
		List<CommonCodeVO> localTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);

		//핸드폰번호 공통코드 조회
		codeVO.setCodeGroup("MOBILE_NUM");
		List<CommonCodeVO> mobileTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);

		//달력년도 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_YEAR");
		List<CommonCodeVO> yearCodeList = commonCodeService.selectCmmCodeList(codeVO);

		//달력월 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_MONTH");
		List<CommonCodeVO> monthCodeList = commonCodeService.selectCmmCodeList(codeVO);

		//달력일 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_DAY");
		List<CommonCodeVO> dayCodeList = commonCodeService.selectCmmCodeList(codeVO);

		//이메일 도메인 공통코드 조회
		codeVO.setCodeGroup("EMAIL_DOMAIN");
		List<CommonCodeVO> emailDomainCodeList = commonCodeService.selectCmmCodeList(codeVO);

		model.addAttribute("MemberStdVO", memberStdVO);
		model.addAttribute("authGroupCode", authGroupCodeList);
		model.addAttribute("localTelNoCode", localTelNoCodeList);
		model.addAttribute("mobileTelNoCode", mobileTelNoCodeList);
		model.addAttribute("yearCode", yearCodeList);
		model.addAttribute("monthCode", monthCodeList);
		model.addAttribute("dayCode", dayCodeList);
		model.addAttribute("emailDomainCode", emailDomainCodeList);

		return "oklms/lu/member/memberCret";
	}

	@RequestMapping(value = "/lu/member/member/goInsertTutorMember.do")
	public String goInsertTutorMember(@ModelAttribute("frmTutorMember") MemberStdVO memberStdVO,  ModelMap model)  throws Exception {

		//지역번호 공통코드 조회
		CommonCodeVO codeVO = new CommonCodeVO();
		codeVO.setCodeGroup("LOCAL_NUM");
		List<CommonCodeVO> localTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);

		//핸드폰번호 공통코드 조회
		codeVO.setCodeGroup("MOBILE_NUM");
		List<CommonCodeVO> mobileTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);

		//달력년도 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_YEAR");
		List<CommonCodeVO> yearCodeList = commonCodeService.selectCmmCodeList(codeVO);

		//달력월 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_MONTH");
		List<CommonCodeVO> monthCodeList = commonCodeService.selectCmmCodeList(codeVO);

		//달력일 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_DAY");
		List<CommonCodeVO> dayCodeList = commonCodeService.selectCmmCodeList(codeVO);

		//이메일 도메인 공통코드 조회
		codeVO.setCodeGroup("EMAIL_DOMAIN");
		List<CommonCodeVO> emailDomainCodeList = commonCodeService.selectCmmCodeList(codeVO);

		model.addAttribute("MemberStdVO", memberStdVO);
		model.addAttribute("localTelNoCode", localTelNoCodeList);
		model.addAttribute("mobileTelNoCode", mobileTelNoCodeList);
		model.addAttribute("yearCode", yearCodeList);
		model.addAttribute("monthCode", monthCodeList);
		model.addAttribute("dayCode", dayCodeList);
		model.addAttribute("emailDomainCode", emailDomainCodeList);

		return "oklms/lu/member/tutorMemberCret";
	}

	@RequestMapping(value = "/lu/member/member/goUpdateMember.do")
	public String goUpdateMember(@ModelAttribute("frmMember") MemberStdVO memberStdVO,  ModelMap model) throws Exception {

		memberStdVO = memberStdService.getMember( memberStdVO );

		//회원유형 공통코드 조회
		CommonCodeVO codeVO = new CommonCodeVO();
		codeVO.setCodeGroup("member");
		List<CommonCodeVO> authGroupCodeList = commonCodeService.selectAuthGroupCodeList(codeVO);

		//지역번호 공통코드 조회
		codeVO.setCodeGroup("LOCAL_NUM");
		List<CommonCodeVO> localTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);

		//핸드폰번호 공통코드 조회
		codeVO.setCodeGroup("MOBILE_NUM");
		List<CommonCodeVO> mobileTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);

		//달력년도 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_YEAR");
		List<CommonCodeVO> yearCodeList = commonCodeService.selectCmmCodeList(codeVO);

		//달력월 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_MONTH");
		List<CommonCodeVO> monthCodeList = commonCodeService.selectCmmCodeList(codeVO);

		//달력일 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_DAY");
		List<CommonCodeVO> dayCodeList = commonCodeService.selectCmmCodeList(codeVO);

		//이메일 도메인 공통코드 조회
		codeVO.setCodeGroup("EMAIL_DOMAIN");
		List<CommonCodeVO> emailDomainCodeList = commonCodeService.selectCmmCodeList(codeVO);

        model.addAttribute("MemberStdVO", memberStdVO);
        model.addAttribute("authGroupCode", authGroupCodeList);
		model.addAttribute("localTelNoCode", localTelNoCodeList);
		model.addAttribute("mobileTelNoCode", mobileTelNoCodeList);
		model.addAttribute("yearCode", yearCodeList);
		model.addAttribute("monthCode", monthCodeList);
		model.addAttribute("dayCode", dayCodeList);
		model.addAttribute("emailDomainCode", emailDomainCodeList);

		return "oklms/lu/member/memberUpdt";
	}

	@RequestMapping(value = "/lu/member/member/goUpdateTutorMember.do")
	public String goUpdateTutorMember(@ModelAttribute("frmTutorMember") MemberStdVO memberStdVO,  ModelMap model) throws Exception {

		memberStdVO = memberStdService.getMember( memberStdVO );

		//지역번호 공통코드 조회
		CommonCodeVO codeVO = new CommonCodeVO();
		codeVO.setCodeGroup("LOCAL_NUM");
		List<CommonCodeVO> localTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);

		//핸드폰번호 공통코드 조회
		codeVO.setCodeGroup("MOBILE_NUM");
		List<CommonCodeVO> mobileTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);

		//달력년도 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_YEAR");
		List<CommonCodeVO> yearCodeList = commonCodeService.selectCmmCodeList(codeVO);

		//달력월 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_MONTH");
		List<CommonCodeVO> monthCodeList = commonCodeService.selectCmmCodeList(codeVO);

		//달력일 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_DAY");
		List<CommonCodeVO> dayCodeList = commonCodeService.selectCmmCodeList(codeVO);

		//이메일 도메인 공통코드 조회
		codeVO.setCodeGroup("EMAIL_DOMAIN");
		List<CommonCodeVO> emailDomainCodeList = commonCodeService.selectCmmCodeList(codeVO);

        model.addAttribute("MemberStdVO", memberStdVO);
		model.addAttribute("localTelNoCode", localTelNoCodeList);
		model.addAttribute("mobileTelNoCode", mobileTelNoCodeList);
		model.addAttribute("yearCode", yearCodeList);
		model.addAttribute("monthCode", monthCodeList);
		model.addAttribute("dayCode", dayCodeList);
		model.addAttribute("emailDomainCode", emailDomainCodeList);

		return "oklms/lu/member/tutorMemberUpdt";
	}

	@RequestMapping(value = "/lu/member/member/insertMember.do")
	public String insertMember(@ModelAttribute("frmMember") @Valid MemberStdVO memberStdVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "입력값을 확인해주세요";

		if (bindingResult.hasErrors()) {

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){

				retMsg = retMsg + "\\n" + fieldError.getDefaultMessage();
			}

			//회원유형 공통코드 조회
			CommonCodeVO codeVO = new CommonCodeVO();
			codeVO.setCodeGroup("member");
			List<CommonCodeVO> authGroupCodeList = commonCodeService.selectAuthGroupCodeList(codeVO);

			//지역번호 공통코드 조회
			codeVO.setCodeGroup("LOCAL_NUM");
			List<CommonCodeVO> localTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);

			//핸드폰번호 공통코드 조회
			codeVO.setCodeGroup("MOBILE_NUM");
			List<CommonCodeVO> mobileTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);

			//달력년도 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_YEAR");
			List<CommonCodeVO> yearCodeList = commonCodeService.selectCmmCodeList(codeVO);

			//달력월 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_MONTH");
			List<CommonCodeVO> monthCodeList = commonCodeService.selectCmmCodeList(codeVO);

			//달력일 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_DAY");
			List<CommonCodeVO> dayCodeList = commonCodeService.selectCmmCodeList(codeVO);

			//이메일 도메인 공통코드 조회
			codeVO.setCodeGroup("EMAIL_DOMAIN");
			List<CommonCodeVO> emailDomainCodeList = commonCodeService.selectCmmCodeList(codeVO);

			model.addAttribute("MemberStdVO", memberStdVO);
			model.addAttribute("authGroupCode", authGroupCodeList);
			model.addAttribute("localTelNoCode", localTelNoCodeList);
			model.addAttribute("mobileTelNoCode", mobileTelNoCodeList);
			model.addAttribute("yearCode", yearCodeList);
			model.addAttribute("monthCode", monthCodeList);
			model.addAttribute("dayCode", dayCodeList);
			model.addAttribute("emailDomainCode", emailDomainCodeList);
			model.addAttribute("retMsg", retMsg);

			return "oklms/lu/member/memberCret";
		}

	    //if (EgovDoubleSubmitHelper.checkAndSaveToken("frmMemberToken")) {
	        String insertPK = memberStdService.insertMember(memberStdVO);
	        retMsg = "정상적으로 (저장)처리되었습니다.!";
	    //}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/lu/member/member/listMember.do";
	}

	@RequestMapping(value = "/lu/member/member/insertTutorMember.do")
	public String insertTutorMember(@ModelAttribute("frmTutorMember") @Valid MemberStdVO memberStdVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "입력값을 확인해주세요";

		//memberStdVO.setAuthGroupId(Config.DEFAULT_AUTH_INS_NEW);

		if (bindingResult.hasErrors()) {

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){

				retMsg = retMsg + "\\n" + fieldError.getDefaultMessage();
			}

			//지역번호 공통코드 조회
			CommonCodeVO codeVO = new CommonCodeVO();
			codeVO.setCodeGroup("LOCAL_NUM");
			List<CommonCodeVO> localTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);

			//핸드폰번호 공통코드 조회
			codeVO.setCodeGroup("MOBILE_NUM");
			List<CommonCodeVO> mobileTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);

			//달력년도 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_YEAR");
			List<CommonCodeVO> yearCodeList = commonCodeService.selectCmmCodeList(codeVO);

			//달력월 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_MONTH");
			List<CommonCodeVO> monthCodeList = commonCodeService.selectCmmCodeList(codeVO);

			//달력일 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_DAY");
			List<CommonCodeVO> dayCodeList = commonCodeService.selectCmmCodeList(codeVO);

			//이메일 도메인 공통코드 조회
			codeVO.setCodeGroup("EMAIL_DOMAIN");
			List<CommonCodeVO> emailDomainCodeList = commonCodeService.selectCmmCodeList(codeVO);

			model.addAttribute("MemberStdVO", memberStdVO);
			model.addAttribute("localTelNoCode", localTelNoCodeList);
			model.addAttribute("mobileTelNoCode", mobileTelNoCodeList);
			model.addAttribute("yearCode", yearCodeList);
			model.addAttribute("monthCode", monthCodeList);
			model.addAttribute("dayCode", dayCodeList);
			model.addAttribute("emailDomainCode", emailDomainCodeList);
			model.addAttribute("retMsg", retMsg);

			return "oklms/lu/member/tutorMemberCret";
		}

	    //if (EgovDoubleSubmitHelper.checkAndSaveToken("frmTutorMemberToken")) {
	        String insertPK = memberStdService.insertMember(memberStdVO);
	        retMsg = "정상적으로 (저장)처리되었습니다.!";
	    //}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/lu/member/member/listTutorMember.do";
	}

	@RequestMapping(value = "/lu/member/member/insertExcelMember.do")
	public String insertExcelMember(@ModelAttribute("frmMemberExcel") @Valid ExcelMemberStdVO excelMemberStdVO, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status, MultipartHttpServletRequest multiRequest) throws Exception {

		String retMsg = "입력값을 확인해주세요";

		 int insertCnt = memberStdService.insertMemberAllExcel(excelMemberStdVO, multiRequest);

		 if(insertCnt > 0){
			 retMsg = "정상적으로 단체등록 처리되었습니다.!";
		 }else{
			 retMsg = "단체등록된 건이 없습니다.!";
		 }

		redirectAttributes.addFlashAttribute("retMsg", retMsg);

		return "redirect:/lu/member/member/listExcelMember.do";
	}

	@RequestMapping(value = "/lu/member/member/updateMember.do")
	public String updateMember(@ModelAttribute("frmMember") @Valid MemberStdVO memberStdVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "입력값을 확인해주세요";

		if (bindingResult.hasErrors()) {

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){

				retMsg = retMsg + "\\n" + fieldError.getDefaultMessage();
			}

			//회원유형 공통코드 조회
			CommonCodeVO codeVO = new CommonCodeVO();
			codeVO.setCodeGroup("member");
			List<CommonCodeVO> authGroupCodeList = commonCodeService.selectAuthGroupCodeList(codeVO);

			//지역번호 공통코드 조회
			codeVO.setCodeGroup("LOCAL_NUM");
			List<CommonCodeVO> localTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);

			//핸드폰번호 공통코드 조회
			codeVO.setCodeGroup("MOBILE_NUM");
			List<CommonCodeVO> mobileTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);

			//달력년도 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_YEAR");
			List<CommonCodeVO> yearCodeList = commonCodeService.selectCmmCodeList(codeVO);

			//달력월 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_MONTH");
			List<CommonCodeVO> monthCodeList = commonCodeService.selectCmmCodeList(codeVO);

			//달력일 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_DAY");
			List<CommonCodeVO> dayCodeList = commonCodeService.selectCmmCodeList(codeVO);

			//이메일 도메인 공통코드 조회
			codeVO.setCodeGroup("EMAIL_DOMAIN");
			List<CommonCodeVO> emailDomainCodeList = commonCodeService.selectCmmCodeList(codeVO);

			model.addAttribute("MemberStdVO", memberStdVO);
			model.addAttribute("authGroupCode", authGroupCodeList);
			model.addAttribute("localTelNoCode", localTelNoCodeList);
			model.addAttribute("mobileTelNoCode", mobileTelNoCodeList);
			model.addAttribute("yearCode", yearCodeList);
			model.addAttribute("monthCode", monthCodeList);
			model.addAttribute("dayCode", dayCodeList);
			model.addAttribute("emailDomainCode", emailDomainCodeList);
			model.addAttribute("retMsg", retMsg);

			return "oklms/lu/member/memberUpdt";
		}

		if( StringUtils.isBlank( memberStdVO.getMemSeq() ) ){
			retMsg = "존재하지 않는 정보입니다.";
		}else{
		    //if (EgovDoubleSubmitHelper.checkAndSaveToken("frmMemberToken")) {

		    	int updateCnt = memberStdService.updateMember( memberStdVO );
		    	retMsg = "정상적으로 (수정)처리되었습니다.!";
		    //}
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/lu/member/member/listMember.do";
	}

	@RequestMapping(value = "/lu/member/member/updateTutorMember.do")
	public String updateTutorMember(@ModelAttribute("frmTutorMember") @Valid MemberStdVO memberStdVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "입력값을 확인해주세요";

		//memberStdVO.setAuthGroupId(Config.DEFAULT_AUTH_INS_NEW);

		if (bindingResult.hasErrors()) {

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){

				retMsg = retMsg + "\\n" + fieldError.getDefaultMessage();
			}

			//지역번호 공통코드 조회
			CommonCodeVO codeVO = new CommonCodeVO();
			codeVO.setCodeGroup("LOCAL_NUM");
			List<CommonCodeVO> localTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);

			//핸드폰번호 공통코드 조회
			codeVO.setCodeGroup("MOBILE_NUM");
			List<CommonCodeVO> mobileTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);

			//달력년도 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_YEAR");
			List<CommonCodeVO> yearCodeList = commonCodeService.selectCmmCodeList(codeVO);

			//달력월 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_MONTH");
			List<CommonCodeVO> monthCodeList = commonCodeService.selectCmmCodeList(codeVO);

			//달력일 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_DAY");
			List<CommonCodeVO> dayCodeList = commonCodeService.selectCmmCodeList(codeVO);

			//이메일 도메인 공통코드 조회
			codeVO.setCodeGroup("EMAIL_DOMAIN");
			List<CommonCodeVO> emailDomainCodeList = commonCodeService.selectCmmCodeList(codeVO);

			model.addAttribute("MemberStdVO", memberStdVO);
			model.addAttribute("localTelNoCode", localTelNoCodeList);
			model.addAttribute("mobileTelNoCode", mobileTelNoCodeList);
			model.addAttribute("yearCode", yearCodeList);
			model.addAttribute("monthCode", monthCodeList);
			model.addAttribute("dayCode", dayCodeList);
			model.addAttribute("emailDomainCode", emailDomainCodeList);
			model.addAttribute("retMsg", retMsg);

			return "oklms/lu/member/tutorMemberUpdt";
		}

		if( StringUtils.isBlank( memberStdVO.getMemSeq() ) ){
			retMsg = "존재하지 않는 정보입니다.";
		}else{
		    //if (EgovDoubleSubmitHelper.checkAndSaveToken("frmTutorMemberToken")) {
		    	int updateCnt = memberStdService.updateMember( memberStdVO );
		    	retMsg = "정상적으로 (수정)처리되었습니다.!";
		    //}
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/lu/member/member/listTutorMember.do";
	}

	@RequestMapping(value = "/lu/member/member/updateMemberPassWordInit.do")
	public String updateMemberPassWordInit(@ModelAttribute("frmMember") @Valid MemberStdVO memberStdVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "입력값을 확인해주세요";

		if( StringUtils.isBlank( memberStdVO.getMemSeq() ) && StringUtils.isBlank( memberStdVO.getMemId() )){
			retMsg = "존재하지 않는 정보입니다.";
		}else{
			int updateCnt = memberStdService.updateMemberPassWordInit( memberStdVO );
			if(updateCnt > 0){
				retMsg = "정상적으로 (초기화)처리되었습니다.!";
			}else{
				retMsg = "초기화된 비밀번호가 없습니다.!";
			}

		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/lu/member/member/listMember.do";
	}

	@RequestMapping(value = "/lu/member/member/updateTutorMemberPassWordInit.do")
	public String updateTutorMemberPassWordInit(@ModelAttribute("frmTutorMember") @Valid MemberStdVO memberStdVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "입력값을 확인해주세요";

		if( StringUtils.isBlank( memberStdVO.getMemSeq() ) && StringUtils.isBlank( memberStdVO.getMemId() )){
			retMsg = "존재하지 않는 정보입니다.";
		}else{
			int updateCnt = memberStdService.updateMemberPassWordInit( memberStdVO );
			if(updateCnt > 0){
				retMsg = "정상적으로 (초기화)처리되었습니다.!";
			}else{
				retMsg = "초기화된 비밀번호가 없습니다.!";
			}

		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/lu/member/member/listTutorMember.do";
	}

	@RequestMapping(value = "/lu/member/member/deleteMemberList.do")
	public String deleteMemberList(@ModelAttribute("frmMember") MemberStdVO memberStdVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		String retMsg = "";


		if( StringUtils.isBlank( memberStdVO.getMemSeq() ) ){
			retMsg = "삭제할 정보가 없습니다.";
		}else{

			memberStdVO.setMemSeqs(memberStdVO.getMemSeq().split(","));

			int deletetCnt = memberStdService.deleteMemberList( memberStdVO );
			retMsg = "정상적으로 (삭제)처리되었습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/lu/member/member/listMember.do";
	}

	@RequestMapping(value = "/lu/member/member/deleteTutorMemberList.do")
	public String deleteTutorMemberList(@ModelAttribute("frmTutorMember") MemberStdVO memberStdVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		String retMsg = "";


		if( StringUtils.isBlank( memberStdVO.getMemSeq() ) ){
			retMsg = "삭제할 정보가 없습니다.";
		}else{

			memberStdVO.setMemSeqs(memberStdVO.getMemSeq().split(","));

			int deletetCnt = memberStdService.deleteMemberList( memberStdVO );
			retMsg = "정상적으로 (삭제)처리되었습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/lu/member/member/listTutorMember.do";
	}

	@RequestMapping(value = "/lu/member/member/deleteMember.do")
	public String deletetMember(@ModelAttribute("frmMember") MemberStdVO memberStdVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		String retMsg = "";

		if( StringUtils.isBlank( memberStdVO.getMemSeq() ) ){
			retMsg = "삭제할 정보가 없습니다.";
		}else{

			int deletetCnt = memberStdService.deleteMember( memberStdVO );
			retMsg = "정상적으로 (삭제)처리되었습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/lu/member/member/listMember.do";
	}

	@RequestMapping(value = "/lu/member/member/deleteTutorMember.do")
	public String deletetTutorMember(@ModelAttribute("frmTutorMember") MemberStdVO memberStdVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		String retMsg = "";

		if( StringUtils.isBlank( memberStdVO.getMemSeq() ) ){
			retMsg = "삭제할 정보가 없습니다.";
		}else{

			int deletetCnt = memberStdService.deleteMember( memberStdVO );
			retMsg = "정상적으로 (삭제)처리되었습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/lu/member/member/listTutorMember.do";
	}*/

}
