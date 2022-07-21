/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
* 이진근    2016. 07. 01.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.la.popup.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.atchFile.vo.AtchFileVO;
import kr.co.sitglobal.oklms.la.comcode.service.ComcodeService;
import kr.co.sitglobal.oklms.la.comcode.vo.ComcodeVO;
import kr.co.sitglobal.oklms.la.popup.service.PopupService;
import kr.co.sitglobal.oklms.la.popup.vo.PopupVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

 /**
 * 관리자가 Popup 설정
 * @author 
 * @since 2016. 12. 02.
 */
@Controller
public class PopupController extends BaseController{
	private static final Logger LOG = LoggerFactory.getLogger(PopupController.class);
	@Resource(name = "popupService")
	private PopupService popupService;

	@Resource(name = "beanValidatorJSR303")
	Validator validator;
	
	@Resource(name = "comcodeService")
	private ComcodeService comcodeService;
	
    @Resource(name = "atchFileService")
	private  AtchFileService atchFileService;
	
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder){
		try {
			dataBinder.setValidator(this.validator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 팝업 관리
	 * @param		searchCondition		(Map<String, Object>) 검색 조건/ 리스트에 포함된 필드 기준
	 * @param		searchKey 			(String) 검색 조건 (TEXT 검색옵션)
	 * @param		searchValue			(String) 검색어
	 * @param		paging				(Map<String, Object>) 페이징 처리 데이터
	 * @return		ResultModel			(ResultModel) status, count, data, page, error object
	 * @throws		Exception 
	 */

	@RequestMapping(value = "/la/popmng/listPopup.do")
	public String listPopup(@ModelAttribute("frmPopup") PopupVO popupVO, ModelMap model) throws Exception {
		
		LOG.debug("listComcode : popupVO=" + popupVO.toString() );
		List<PopupVO> resultList = popupService.getPopupList(popupVO);
		ComcodeVO ccVO = new ComcodeVO();
		ccVO.setSearchCodeGroup("POPUP_FOCUS");
		List<ComcodeVO> ccFocusList = comcodeService.listComcode(ccVO);
		//POPUP_SEARCH_GROUP
		ccVO  = new ComcodeVO();
		ccVO.setSearchCodeGroup("POPUP_SEARCH_GROUP");
		List<ComcodeVO> ccSearchList = comcodeService.listComcode(ccVO);
		
		Integer pageSize = popupVO.getPageSize();
		Integer page = popupVO.getPageIndex();
		int totalCnt = 0;
		if( 0 < resultList.size() ){
			totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
		}
		int totalPage = (int) Math.ceil(totalCnt / pageSize);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);
        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(popupVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(popupVO.getPageUnit());
        paginationInfo.setPageSize(popupVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);

        model.addAttribute("ccFocusList", ccFocusList);
        model.addAttribute("ccSearchList", ccSearchList);
        model.addAttribute("popupVO", popupVO);
        model.addAttribute("resultList", resultList);
//        model.addAttribute("searchCodeGroup", popupVO.getSearchCodeGroup());
//        model.addAttribute("searchCodeName", comcodeVO.getSearchCodeName());
        
		// View호출
		return "oklms/la/popmng/popupList";
	}
	
	
	/**
	 * 팝업뷰 
	 */	
	@RequestMapping(value = "/la/popmng/insertPopupView.do")
	public String insertPopupView(@ModelAttribute("frmPopup") PopupVO popupVO, ModelMap model) throws Exception {
		ComcodeVO ccVO = new ComcodeVO();
		ccVO.setSearchCodeGroup("POPUP_FOCUS");
		List<ComcodeVO> ccFocusList = comcodeService.listComcode(ccVO);
		model.addAttribute("ccFocusList", ccFocusList);
		return "oklms/la/popmng/popupSave";
	}
	
	/**
	* 팝업정보생성
	*/
	@RequestMapping(value = "/la/popmng/insertPopup.do")
	public String insertPopup(@ModelAttribute("frmPopup") PopupVO popupVO
			,HttpServletRequest request
			,final MultipartHttpServletRequest multiRequest
			,RedirectAttributes redirectAttributes
			,ModelMap model) throws Exception {
		LOG.debug("insertPopup : popupVO=" + popupVO.toString() );
		//session 정보 가지고 온다.
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(popupVO);
		
		if(popupVO.getContentType().equals("I")){//이미지
			List<FileVO> fileResult = null;
			String atchFileId = "";
			List<MultipartFile> files = multiRequest.getFiles("file-input");
			if (!files.isEmpty()) {
				atchFileId = atchFileService.saveAtchFile(files, "POPUP_",  "", "Globals.UploadImageFilePath");
				popupVO.setImageFileId(atchFileId);
			}else{
				redirectAttributes.addFlashAttribute("retMsg", "이미지 업로드 실패 했습니다.");
				return "redirect:/la/popmng/listPopup.do";
			}
			
			//초기화
			popupVO.setContent("");
			
			
		}
		
		int result = popupService.insertPopup(popupVO);
		
		LOG.debug("insertPopup : result=" + result );
		String retMsg="";
		
		if( 0 < result ){
			retMsg = "정상적으로 (저장)처리되었습니다.";
		}else{
			retMsg = "처리된 정보가 없습니다.";
		}

	    redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/la/popmng/listPopup.do";
	}
	
	/**
	* 팝업 상세 뷰
	*/
	@RequestMapping(value = "/la/popmng/detailPopup.do")
	public String detailPopup(@ModelAttribute("frmPopup") PopupVO popupVO, ModelMap model) throws Exception {
		
		LOG.debug("listComcode : popupVO=" + popupVO.toString() );
		PopupVO result = popupService.getPopup(popupVO);

		ComcodeVO ccVO = new ComcodeVO();
		ccVO.setSearchCodeGroup("POPUP_FOCUS");
		List<ComcodeVO> ccFocusList = comcodeService.listComcode(ccVO);
		model.addAttribute("ccFocusList", ccFocusList);
        model.addAttribute("result", result);

        // View호출
		return "oklms/la/popmng/popupDetail";
	}
	
	/**
	* 팝업 업데이트 뷰
	*/
	@RequestMapping(value = "/la/popmng/updatePopupView.do")
	public String updatePopupView(@ModelAttribute("frmPopup") PopupVO popupVO, ModelMap model) throws Exception {
		LOG.debug("updatePopupView : popupVO=" + popupVO.toString() );
		PopupVO result = popupService.getPopup(popupVO);

		ComcodeVO ccVO = new ComcodeVO();
		ccVO.setSearchCodeGroup("POPUP_FOCUS");
		List<ComcodeVO> ccFocusList = comcodeService.listComcode(ccVO);
		model.addAttribute("ccFocusList", ccFocusList);
        model.addAttribute("result", result);

		return "oklms/la/popmng/popupUpdt";
	}
	
	
	/**
	* 팝업수정
	*/
	@RequestMapping(value = "/la/popmng/updatePopup.do")
	public String updatePopup(@ModelAttribute("frmPopup") PopupVO popupVO
			,HttpServletRequest request
			,final MultipartHttpServletRequest multiRequest
			,RedirectAttributes redirectAttributes
			,ModelMap model) throws Exception {
		LOG.debug("updatePopup : popupVO=" + popupVO.toString() );
		//session 정보 가지고 온다.
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(popupVO);
		
		if(popupVO.getContentType().equals("I")){//이미지
			List<FileVO> fileResult = null;
			String atchFileId = "";
			List<MultipartFile> files = multiRequest.getFiles("file-input");
			if (!files.isEmpty()) {
				/*
				 * 삭제이후 저장하는 형태로 업데이트 처리.
				 */
				AtchFileVO atchFileVO = new AtchFileVO();
				atchFileVO.setAtchFileId(popupVO.getImageFileId());
				
				//삭제할 파일정보 조회.
				List<AtchFileVO> fvoList  = atchFileService.listAtchFile(atchFileVO);
				
				//DB 및 물리 파일 삭제 처리.
				atchFileService.deleteAtchFile(fvoList);
				
				//DB 및 물리 파일 저장 처리.
				atchFileId = atchFileService.saveAtchFile(files, "POPUP_",  "", "Globals.UploadImageFilePath");
				
				//새로운 File ID로 업데이트
				popupVO.setImageFileId(atchFileId);
			}else{
				redirectAttributes.addFlashAttribute("retMsg", "이미지 업로드 실패 했습니다.");
				return "redirect:/la/popmng/listPopup.do";
			}
			//내용 초기화
			popupVO.setContent("");
		}else{
			//이미지 업로드 초기화
			popupVO.setImageFileId("");
			//이미지 링크 초기화
			popupVO.setPageUrl("");
		}
		int result = popupService.updatePopup(popupVO);
		LOG.debug("updatePopup : result=" + result );
		String retMsg="";
		
		if( 0 < result ){
			retMsg = "정상적으로 (수정)처리되었습니다.";
		}else{
			retMsg = "처리된 정보가 없습니다.";
		}

	    redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/la/popmng/listPopup.do";
	}
	
	/**
	* 팝업삭제
	*/
	@RequestMapping(value = "/la/popmng/deletePopup.do")
	public String deletePopup(@ModelAttribute("frmPopup") PopupVO popupVO
			,HttpServletRequest request
			,RedirectAttributes redirectAttributes
			,ModelMap model) throws Exception {
		LOG.debug("deletePopup : popupVO=" + popupVO.toString() );
		//session 정보 가지고 온다.
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(popupVO);
		
		if(popupVO.getContentType().equals("I")){//이미지
				/*
				 * FILE DB 및 물리적으로 삭제
				 */
				AtchFileVO atchFileVO = new AtchFileVO();
				atchFileVO.setAtchFileId(popupVO.getImageFileId());
				
				//삭제할 파일정보 조회.
				List<AtchFileVO> fvoList  = atchFileService.listAtchFile(atchFileVO);
				
				//DB 및 물리 파일 삭제 처리.
				atchFileService.deleteAtchFile(fvoList);
		}
		
		int result = popupService.deletePopup(popupVO);
		LOG.debug("deletePopup : result=" + result );
		String retMsg="";
		
		if( 0 < result ){
			retMsg = "정상적으로 (삭제)처리되었습니다.";
		}else{
			retMsg = "처리된 정보가 없습니다.";
		}

	    redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/la/popmng/listPopup.do";
	}
	
	/**
	* open 팝업 뷰
	*/
	@RequestMapping(value = "/{userType}/popmng/openPopup.do")
	public String openPopup(@PathVariable("userType") String userType,@ModelAttribute("frmPopup") PopupVO popupVO, ModelMap model) throws Exception {
		LOG.debug("openPopup : popupVO=" + popupVO.toString() );
		PopupVO result = popupService.getPopup(popupVO);
        model.addAttribute("result", result);
        // View호출
		return "oklms_popup/"+userType+"/popmng/openPopup";
	}
	
}
