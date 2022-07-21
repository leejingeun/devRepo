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
package kr.co.sitglobal.oklms.la.company.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.commbiz.cmmcode.vo.CommonCodeVO;
import kr.co.sitglobal.oklms.commbiz.util.BizUtil;
import kr.co.sitglobal.oklms.la.company.service.CompanyService;
import kr.co.sitglobal.oklms.la.company.vo.CompanyVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
//import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class CompanyController extends BaseController{
	private static final Logger LOG = LoggerFactory.getLogger(CompanyController.class);

	@Resource(name = "companyService")
	private CompanyService companyService;

	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;

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

	//CompanyVO companyVO
	@RequestMapping(value = "/{userType}/company/listCompany.do")
	public String listCompany(@PathVariable("userType") String userType,@ModelAttribute("frmCompany") CompanyVO companyVO, ModelMap model) throws Exception {
		/*
		 * Role 따른 조회기준 변경처리
		 * -전체기업 조회
		 * 	2016AUTH0000001	관리자
		 * 	2016AUTH0000005	센터전담자
		 * -해당기업 조회
		 * 	2016AUTH0000004	기업전담자
		 */
		/*if(user.getAuthGroupId().equals("2016AUTH0000004")){
			companyVO.setCompanyId(user.getCompanyId());
		}*/
		//logger.debug("================ userType : "+userType);
		//ogger.debug("======================================== companyVO.getPageIndex() : "+companyVO.getPageIndex());

		List<CompanyVO> resultList = companyService.listCompany(companyVO);
		Integer pageSize = companyVO.getPageSize();
		Integer page = companyVO.getPageIndex();

		int totalCnt = 0;
		if( 0 < resultList.size() ){
			totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
		}
		int totalPage = (int) Math.ceil(totalCnt / pageSize);

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);
        

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(companyVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(pageSize);
        paginationInfo.setPageSize(companyVO.getPageUnit());
        paginationInfo.setTotalRecordCount(totalCnt);

        //model.addAttribute("authGroupId", user.getAuthGroupId() );

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);

        model.addAttribute("companyVO", companyVO);
        model.addAttribute("resultList", resultList);

		// View호출
		return "oklms/"+userType+"/company/companyList";
	}


	//CompanyVO companyVO
	@RequestMapping(value = "/{userType}/company/listChoiceCompany.do")
	public String listChoiceCompany(@PathVariable("userType") String userType,@ModelAttribute("frmCompany") CompanyVO companyVO, ModelMap model) throws Exception {
		CommonCodeVO codeVO = new CommonCodeVO();
		List<CompanyVO> resultList = companyService.listCompany(companyVO);

		Integer pageSize = companyVO.getPageSize();
		Integer page = companyVO.getPageIndex();

		int totalCnt = 0;
		if( 0 < resultList.size() ){
			totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
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
        model.addAttribute("resultList", resultList);

		// View호출
		return "oklms/"+userType+"/company/choiceCompanyList";

	}

	@RequestMapping("/{userType}/company/ChoiceCompany.json")
	public @ResponseBody Map<String, Object> ChoiceCompany(@ModelAttribute("frmSessionCompany") CompanyVO companyVO
		,RedirectAttributes redirectAttributes
		,SessionStatus status
		,HttpServletRequest request
		,ModelMap model) throws Exception {
		Map<String , Object> returnMap = new HashMap<String , Object>();
		Map<String , Object> paramMap = new HashMap<String , Object>();

		String retCd = "SUCCESS";
		String retMsg = companyVO.getCompanyName()+"의 기업으로 설정 되었습니다.";

		if(companyVO.getCompanyId().equals("") || companyVO.getCompanyName().equals("")){
			retCd = "FAIL";
			retMsg = "기업아이디와 기업명 파라미터 오류입니다.";
		}else{
			paramMap.put("companyId", companyVO.getCompanyId());
			paramMap.put("companyName", companyVO.getCompanyName());

			//Session 등록
			BizUtil.setSessionCompanyInfo(request, paramMap);
		}

		returnMap.put("retCd", retCd);
		returnMap.put("retMsg", retMsg);
		return returnMap;
	}

	@RequestMapping(value = "/{userType}/company/getCompany.do")
	public String getCompany(@PathVariable("userType") String userType,@ModelAttribute("frmCompany") CompanyVO companyVO,  ModelMap model) throws Exception {
		companyVO = companyService.getCompany(companyVO);
        model.addAttribute("companyVO", companyVO);

		// View호출
		return "oklms/"+userType+"/company/companyRead";
	}


	@RequestMapping(value = "/{userType}/company/goInsertCompany.do")
	public String goInsertCompany(@PathVariable("userType") String userType,@ModelAttribute("frmCompany") CompanyVO companyVO,  ModelMap model) throws Exception {
		model.addAttribute("companyVO", companyVO);

		CommonCodeVO codeVO = new CommonCodeVO();
		//지역번호 공통코드 조회
		codeVO.setCodeGroup("LOCAL_NUM");
		List<CommonCodeVO> localTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);
		model.addAttribute("localTelNoCode", localTelNoCodeList);
		return "oklms/"+userType+"/company/companyInsert";
	}


	@RequestMapping(value = "/{userType}/company/goUpdateCompany.do")
	public String goUpdateCompany(@PathVariable("userType") String userType,@ModelAttribute("frmCompany") CompanyVO companyVO,  ModelMap model) throws Exception {

		logger.debug("get companyVO : "+companyVO.toString());

		companyVO = companyService.getCompany(companyVO);
        model.addAttribute("companyVO", companyVO);

        CommonCodeVO codeVO = new CommonCodeVO();
        //지역번호 공통코드 조회
  		codeVO.setCodeGroup("LOCAL_NUM");
  		List<CommonCodeVO> localTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);
  		model.addAttribute("localTelNoCode", localTelNoCodeList);

		return "oklms/"+userType+"/company/companyUpdate";
	}


	@RequestMapping(value = "/{userType}/company/insertCompany.do")
	public String insertCompany(@PathVariable("userType") String userType,@ModelAttribute("frmCompany") CompanyVO companyVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "입력값을 확인해주세요";

		if (bindingResult.hasErrors()) {

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){

				retMsg = retMsg + "\\n" + fieldError.getDefaultMessage();
			}

			model.addAttribute("retMsg", retMsg);

			return "oklms/"+userType+"/company/companyInsert";
		}

	    //if (EgovDoubleSubmitHelper.checkAndSaveToken("frmMemberToken")) {
			logger.debug("insert companyVO : "+companyVO.toString());

	        String insertPK = companyService.insertCompany(companyVO);
	        retMsg = "정상적으로 (저장)처리되었습니다.!";
	    //}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/"+userType+"/company/listCompany.do";
	}

	@RequestMapping(value = "/{userType}/company/updateCompany.do")
	public String updateCompany(@PathVariable("userType") String userType,@ModelAttribute("frmCompany") CompanyVO companyVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "입력값을 확인해주세요";

		if (bindingResult.hasErrors()) {

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){

				retMsg = retMsg + "\\n" + fieldError.getDefaultMessage();
			}

			model.addAttribute("retMsg", retMsg);

			return "oklms/"+userType+"/company/companyUpdate";
		}

		if( StringUtils.isBlank( companyVO.getCompanyId()) ){
			retMsg = "존재하지 않는 정보입니다.";
		}else{
				logger.debug("update companyVO : "+companyVO.toString());
		    	int updateCnt = companyService.updateCompany(companyVO);
		    	retMsg = "정상적으로 (수정)처리되었습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/"+userType+"/company/listCompany.do";
	}

	@RequestMapping(value = "/{userType}/company/deleteCompany.do")
	public String deleteCompany(@PathVariable("userType") String userType,@ModelAttribute("frmCompany") CompanyVO companyVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		String retMsg = "";

		if( StringUtils.isBlank( companyVO.getCompanyId() ) ){
			retMsg = "삭제할 정보가 없습니다.";
		}else{

			int deletetCnt = companyService.deleteCompany(companyVO);
			retMsg = "정상적으로 (삭제)처리되었습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/"+userType+"/company/listCompany.do";
	}
}
