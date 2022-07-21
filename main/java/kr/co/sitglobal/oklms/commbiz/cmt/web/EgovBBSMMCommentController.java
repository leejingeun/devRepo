package kr.co.sitglobal.oklms.commbiz.cmt.web;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Comment;
import egovframework.com.cop.bbs.service.CommentVO;
import egovframework.com.cop.bbs.service.EgovBBSCommentService;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.atchFile.vo.AtchFileVO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * 댓글관리 서비스 컨트롤러 클래스
 *
 * @author 공통컴포넌트개발팀 한성곤
 * @since 2009.06.29
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.06.29  한성곤          최초 생성
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Controller
public class EgovBBSMMCommentController {

	@Autowired(required = false)
	protected EgovBBSCommentService bbsCommentService;

	@Resource(name = "atchFileService")
	private AtchFileService atchFileService;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Autowired
	private DefaultBeanValidator beanValidator;

	// protected Logger log = Logger.getLogger(this.getClass());

	/**
	 * 댓글관리 목록 조회를 제공한다.
	 *
	 * @param boardVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/cop/cmt/selectCommentList.do")
	public String selectCommentList(@RequestParam Map<String, Object> paramMap, 
			@ModelAttribute("searchVO") CommentVO commentVO, ModelMap model) throws Exception {

		String returnUrl = "";
		String lectureMenuMarkYn = StringUtils.defaultString((String)paramMap.get("lectureMenuMarkYn"),"N");
		AtchFileVO atchFileVO = new AtchFileVO();

		// 수정 처리된 후 댓글 등록 화면으로 처리되기 위한 구현
		if (commentVO.isModified()) {
			commentVO.setCommentNo("");
			commentVO.setCommentCn("");
		}

		// 수정을 위한 처리
		if (!commentVO.getCommentNo().equals("")) {
			return "forward:/mm/cop/cmt/selectSingleComment.do";
		}

		// ------------------------------------------
		// JSP의 <head> 부분 처리 (javascript 생성)
		// ------------------------------------------
		model.addAttribute("type", commentVO.getType()); // head or body

		if (commentVO.getType().equals("head")) {
			return "oklms/mm/egovframework/com/cop/cmt/EgovCommentList";
		}
		// //----------------------------------------

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		model.addAttribute("sessionUniqId", user.getUniqId());

		commentVO.setWrterNm(user.getName());

		commentVO.setSubPageUnit(propertyService.getInt("pageUnit"));
		commentVO.setSubPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(commentVO.getSubPageIndex());
		paginationInfo.setRecordCountPerPage(commentVO.getSubPageUnit());
		paginationInfo.setPageSize(commentVO.getSubPageSize());

		commentVO.setSubFirstIndex(paginationInfo.getFirstRecordIndex());
		commentVO.setSubLastIndex(paginationInfo.getLastRecordIndex());
		commentVO.setSubRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = bbsCommentService.selectCommentList(commentVO);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));
		String commentNo = (String) map.get("commentNo");
		String atchFileId = (String) map.get("atchFileId");

		//첨부파일 정보셋팅
		atchFileVO.setFileSn(1);
		atchFileVO.setAtchFileId(atchFileId);
		AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
		model.addAttribute("resultCommentFile", resultFile);

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("bbsId", commentVO.getBbsId());
		model.addAttribute("nttId", commentVO.getNttId());
		model.addAttribute("commentNo", commentNo);

		commentVO.setCommentCn(""); // 등록 후 댓글 내용 처리


		returnUrl = "/egovframework/com/cop/cmt/EgovAllSubjectCommentList";
	

		return "oklms_blank/mm"  + returnUrl;
	}

	/**
	 * 익명용 댓글관리 목록 조회를 제공한다.
	 *
	 * @param boardVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/cop/cmt/anonymous/selectCommentList.do")
	public String selectAnonymousCommentList(
			@ModelAttribute("searchVO") CommentVO commentVO, ModelMap model) throws Exception {

		// 수정 처리된 후 댓글 등록 화면으로 처리되기 위한 구현
		if (commentVO.isModified()) {
			commentVO.setCommentNo("");
			commentVO.setCommentCn("");
			commentVO.setWrterNm("");
		}

		// 수정을 위한 처리
		if (!commentVO.getCommentNo().equals("")) {
			return "forward:/mm/cop/cmt/anonymous/selectSingleComment.do";
		}

		// ------------------------------------------
		// JSP의 <head> 부분 처리 (javascript 생성)
		// ------------------------------------------
		model.addAttribute("type", commentVO.getType()); // head or body

		if (commentVO.getType().equals("head")) {
			return "oklms/mm/egovframework/com/cop/cmt/EgovCommentList";
		}
		// //----------------------------------------

		model.addAttribute("anonymous", "true");

		commentVO.setSubPageUnit(propertyService.getInt("pageUnit"));
		commentVO.setSubPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(commentVO.getSubPageIndex());
		paginationInfo.setRecordCountPerPage(commentVO.getSubPageUnit());
		paginationInfo.setPageSize(commentVO.getSubPageSize());

		commentVO.setSubFirstIndex(paginationInfo.getFirstRecordIndex());
		commentVO.setSubLastIndex(paginationInfo.getLastRecordIndex());
		commentVO.setSubRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = bbsCommentService.selectCommentList(commentVO);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		commentVO.setWrterNm("");
		commentVO.setCommentCn(""); // 등록 후 댓글 내용 처리

		return "oklms_blank/mm/egovframework/com/cop/cmt/EgovCommentList";
	}

	/**
	 * 댓글을 등록한다.
	 *
	 * @param commentVO
	 * @param comment
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/cop/cmt/insertComment.do")
	public String insertComment( @ModelAttribute("searchVO") CommentVO commentVO,
			@ModelAttribute("comment") Comment comment, BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		String bbsId = commentVO.getBbsId();

		beanValidator.validate(comment, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", "댓글 작성자 및  내용은 필수 입력값입니다.");

			return "forward:/mm/cop/bbs/" + bbsId + "/selectBoardArticle.do";
		}

		if (isAuthenticated) {
			comment.setFrstRegisterId(user.getUniqId());
			comment.setWrterId(user.getUniqId());

			comment.setCommentPassword(""); // dummy

			bbsCommentService.insertComment(comment);

			commentVO.setCommentCn("");
			commentVO.setCommentNo("");
		}

		return "forward:/mm/cop/bbs/" + bbsId + "/selectBoardArticle.do";
	}

	/**
	 * 진행중인 교과목에 대한 댓글을 등록한다.
	 *
	 * @param commentVO
	 * @param comment
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/cop/bbs/{pathBbsId}/insertBoardComment.do")
	public String insertBoardComment(@RequestParam Map<String, Object> paramMap,  @ModelAttribute("searchVO") CommentVO commentVO,
			@ModelAttribute("comment") Comment comment, BindingResult bindingResult, ModelMap model, final MultipartHttpServletRequest multiRequest) throws Exception {

		String commentCn = StringUtils.defaultString((String)paramMap.get("commentCn"),"");
		String tempAtchFileId = StringUtils.defaultString((String)paramMap.get("atchFileId"),"");
		String atchFileId = "";
		atchFileId = tempAtchFileId;
		System.out.println("commentCn: "+commentCn);
		System.out.println("tempAtchFileId: "+tempAtchFileId);
		System.out.println("atchFileId1: "+atchFileId);

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		String bbsId = commentVO.getBbsId();

		if("".equals(commentCn)){
			model.addAttribute("msg", "댓글 작성자 및  내용은 필수 입력값입니다.");
			return "forward:/mm/cop/bbs/" + bbsId + "/selectBoardArticle.do";
		}


		if (isAuthenticated) {
			comment.setFrstRegisterId(user.getUniqId());
			comment.setWrterId(user.getUniqId());
			//comment.setWrterNm(user.getName());

			comment.setCommentPassword(""); // dummy

			String strStorePath ="Globals.fileStorePath";
			System.out.println("atchFileId2 ==> "+atchFileId);
			if(!"".equals(atchFileId)){
				final List< MultipartFile > fileObj = multiRequest.getFiles("file-input");
				atchFileId = atchFileService.saveAtchFile( fileObj, "BBSCOMMENT_", "", strStorePath );
				comment.setAtchFileId(atchFileId);
			}

			bbsCommentService.insertComment(comment);

			commentVO.setCommentCn("");
			commentVO.setCommentNo("");
		}
		return "forward:/mm/cop/bbs/" + bbsId + "/selectBoardArticle.do";
	}

	/**
	 * 익명 댓글을 등록한다.
	 *
	 * @param commentVO
	 * @param comment
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/cop/cmt/anonymous/insertComment.do")
	public String insertAnonymousComment(
			@ModelAttribute("searchVO") CommentVO commentVO, @ModelAttribute("comment") Comment comment,
			BindingResult bindingResult, ModelMap model) throws Exception {

		beanValidator.validate(comment, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", "댓글 작성자, 내용 및 패스워드는 필수 입력값입니다.");

			return "forward:/mm/cop/bbs/anonymous/selectBoardArticle.do";
		}

		comment.setFrstRegisterId("ANONYMOUS");
		comment.setWrterId("");
		comment.setCommentPassword(EgovFileScrty.encryptPassword(comment.getCommentPassword(), commentVO.getBbsId()));

		bbsCommentService.insertComment(comment);

		commentVO.setCommentNo("");
		commentVO.setCommentCn("");
		commentVO.setWrterNm("");

		return "forward:/mm/cop/bbs/anonymous/selectBoardArticle.do";
	}

	/**
	 * 댓글을 삭제한다.
	 *
	 * @param commentVO
	 * @param comment
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/cop/cmt/deleteComment.do")
	public String deleteComment( @ModelAttribute("searchVO") CommentVO commentVO,
			@ModelAttribute("comment") Comment comment, ModelMap model) throws Exception {
		@SuppressWarnings("unused")
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (isAuthenticated) {
			bbsCommentService.deleteComment(commentVO);
		}

		commentVO.setCommentCn("");
		commentVO.setCommentNo("");
 
		String bbsId = commentVO.getBbsId();
		return "forward:/mm/cop/bbs/" + bbsId + "/selectBoardArticle.do";
	}

	/**
	 * 진행중인 교과목에 대한 댓글을 삭제한다.
	 *
	 * @param commentVO
	 * @param comment
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/cop/bbs/{pathBbsId}/deleteBoardComment.do")
	public String deleteBoardComment( @ModelAttribute("searchVO") CommentVO commentVO,
			@ModelAttribute("comment") Comment comment, ModelMap model) throws Exception {
		@SuppressWarnings("unused")
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		if (isAuthenticated) {
			bbsCommentService.deleteComment(commentVO);
		}

		commentVO.setCommentCn("");
		commentVO.setCommentNo("");
 
		String bbsId = commentVO.getBbsId();
		return "forward:/mm/cop/bbs/" + bbsId + "/selectBoardArticle.do";
	}

	/**
	 * 익명 댓글을 삭제한다.
	 *
	 * @param commentVO
	 * @param comment
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/cop/cmt/anonymous/deleteComment.do")
	public String deleteAnonymousComment(
			@ModelAttribute("searchVO") CommentVO commentVO, @ModelAttribute("comment") Comment comment, ModelMap model)
			throws Exception {

		// -------------------------------
		// 패스워드 비교
		// -------------------------------
		String dbpassword = bbsCommentService.getCommentPassword(commentVO);
		String enpassword = EgovFileScrty.encryptPassword(commentVO.getConfirmPassword(), commentVO.getBbsId());

		if (!dbpassword.equals(enpassword)) {

			model.addAttribute("subMsg", egovMessageSource.getMessage("cop.password.not.same.msg"));

			return "forward:/mm/cop/bbs/anonymous/selectBoardArticle.do";
		}
		// //-----------------------------

		bbsCommentService.deleteComment(commentVO);

		commentVO.setCommentNo("");
		commentVO.setCommentCn("");
		commentVO.setWrterNm("");

		return "forward:/mm/cop/bbs/anonymous/selectBoardArticle.do";
	}

	/**
	 * 댓글 수정 페이지로 이동한다.
	 *
	 * @param commentVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/cop/cmt/selectSingleComment.do")
	public String selectSingleComment(@RequestParam Map<String, Object> paramMap, 
			@ModelAttribute("searchVO") CommentVO commentVO, ModelMap model) throws Exception {

		// ------------------------------------------
		// JSP의 <head> 부분 처리 (javascript 생성)
		// ------------------------------------------
		String returnUrl = "";
		String lectureMenuMarkYn = StringUtils.defaultString((String)paramMap.get("lectureMenuMarkYn"),"N");
		AtchFileVO atchFileVO = new AtchFileVO();
		
		model.addAttribute("type", commentVO.getType()); // head or body

		if (commentVO.getType().equals("head")) {
			return "oklms/mm/egovframework/com/cop/cmt/EgovCommentList";
		}
		// //----------------------------------------

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		commentVO.setWrterNm(user.getName());

		commentVO.setSubPageUnit(propertyService.getInt("pageUnit"));
		commentVO.setSubPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(commentVO.getSubPageIndex());
		paginationInfo.setRecordCountPerPage(commentVO.getSubPageUnit());
		paginationInfo.setPageSize(commentVO.getSubPageSize());

		commentVO.setSubFirstIndex(paginationInfo.getFirstRecordIndex());
		commentVO.setSubLastIndex(paginationInfo.getLastRecordIndex());
		commentVO.setSubRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = bbsCommentService.selectCommentList(commentVO);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		Comment data = bbsCommentService.selectComment(commentVO);

		commentVO.setCommentNo(data.getCommentNo());
		commentVO.setNttId(data.getNttId());
		commentVO.setBbsId(data.getBbsId());
		commentVO.setWrterId(data.getWrterId());
		commentVO.setWrterNm(data.getWrterNm());
		commentVO.setCommentPassword(data.getCommentPassword());
		commentVO.setCommentCn(data.getCommentCn());
		commentVO.setUseAt(data.getUseAt());
		commentVO.setFrstRegisterPnttm(data.getFrstRegisterPnttm());
		commentVO.setFrstRegisterNm(data.getFrstRegisterNm());
		

		returnUrl = "/egovframework/com/cop/cmt/EgovAllSubjectCommentList";
	
		return "oklms_blank/mm" + returnUrl;
	}

	/**
	 * 익명 댓글 수정 페이지로 이동한다.
	 *
	 * @param commentVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/cop/cmt/anonymous/selectSingleComment.do")
	public String selectAnonymousSingleComment(
			@ModelAttribute("searchVO") CommentVO commentVO, ModelMap model) throws Exception {

		// ------------------------------------------
		// JSP의 <head> 부분 처리 (javascript 생성)
		// ------------------------------------------
		model.addAttribute("type", commentVO.getType()); // head or body

		if (commentVO.getType().equals("head")) {
			return "oklms/mm/egovframework/com/cop/cmt/EgovCommentList";
		}
		// //----------------------------------------

		model.addAttribute("anonymous", "true");

		commentVO.setSubPageUnit(propertyService.getInt("pageUnit"));
		commentVO.setSubPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(commentVO.getSubPageIndex());
		paginationInfo.setRecordCountPerPage(commentVO.getSubPageUnit());
		paginationInfo.setPageSize(commentVO.getSubPageSize());

		commentVO.setSubFirstIndex(paginationInfo.getFirstRecordIndex());
		commentVO.setSubLastIndex(paginationInfo.getLastRecordIndex());
		commentVO.setSubRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = bbsCommentService.selectCommentList(commentVO);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("paginationInfo", paginationInfo);

		// -------------------------------
		// 패스워드 비교
		// -------------------------------
		String dbpassword = bbsCommentService.getCommentPassword(commentVO);
		String enpassword = EgovFileScrty.encryptPassword(commentVO.getConfirmPassword(), commentVO.getBbsId());

		if (!dbpassword.equals(enpassword)) {

			model.addAttribute("subMsg", egovMessageSource.getMessage("cop.password.not.same.msg"));

			commentVO.setCommentNo("");
			commentVO.setCommentCn("");
			commentVO.setWrterNm("");

		} else {

			Comment data = bbsCommentService.selectComment(commentVO);

			commentVO.setCommentNo(data.getCommentNo());
			commentVO.setNttId(data.getNttId());
			commentVO.setBbsId(data.getBbsId());
			commentVO.setWrterId(data.getWrterId());
			commentVO.setWrterNm(data.getWrterNm());
			commentVO.setCommentPassword(data.getCommentPassword());
			commentVO.setCommentCn(data.getCommentCn());
			commentVO.setUseAt(data.getUseAt());
			commentVO.setFrstRegisterPnttm(data.getFrstRegisterPnttm());
			commentVO.setFrstRegisterNm(data.getFrstRegisterNm());
		}
		// //-----------------------------

		return "oklms_blank/mm/egovframework/com/cop/cmt/EgovCommentList";
	}

	/**
	 * 댓글을 수정한다.
	 *
	 * @param commentVO
	 * @param comment
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/cop/cmt/updateComment.do")
	public String updateCommentList(
			@ModelAttribute("searchVO") CommentVO commentVO, @ModelAttribute("comment") Comment comment,
			BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		String bbsId = commentVO.getBbsId();

		beanValidator.validate(comment, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", "댓글 작성자 및  내용은 필수 입력값입니다.");
 
			return "forward:/mm/cop/bbs/" + bbsId + "/selectBoardArticle.do";
		}

		if (isAuthenticated) {
			comment.setLastUpdusrId(user.getUniqId());

			comment.setCommentPassword(""); // dummy

			bbsCommentService.updateComment(comment);

			commentVO.setCommentCn("");
			commentVO.setCommentNo("");
		}
 
		return "forward:/mm/cop/bbs/" + bbsId + "/selectBoardArticle.do";
	}

	/**
	 * 진행중인 교과목에 대한 댓글을 수정한다.
	 *
	 * @param commentVO
	 * @param comment
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/cop/bbs/{pathBbsId}/updateBoardComment.do")
	public String updateBoardCommentList(@RequestParam Map<String, Object> paramMap, 
			@ModelAttribute("searchVO") CommentVO commentVO, @ModelAttribute("comment") Comment comment,
			BindingResult bindingResult, ModelMap model, final MultipartHttpServletRequest multiRequest) throws Exception {

		String commentCn = StringUtils.defaultString((String)paramMap.get("commentCn"),"");
		String tempAtchFileId = StringUtils.defaultString((String)paramMap.get("atchFileId"),"");
		String atchFileId = "";
		atchFileId = tempAtchFileId;
		System.out.println("commentCn: "+commentCn);
		System.out.println("tempAtchFileId: "+tempAtchFileId);
		System.out.println("atchFileId1: "+atchFileId);
		
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		String bbsId = commentVO.getBbsId();
		
		if("".equals(commentCn)){
			model.addAttribute("msg", "댓글 작성자 및  내용은 필수 입력값입니다.");
			return "forward:/mm/cop/bbs/" + bbsId + "/selectBoardArticle.do";
		}


		if (isAuthenticated) {
			comment.setLastUpdusrId(user.getUniqId());

			comment.setCommentPassword(""); // dummy
			
			String strStorePath ="Globals.fileStorePath";
			System.out.println("atchFileId2 ==> "+atchFileId);
			if(!"".equals(atchFileId)){
				final List< MultipartFile > fileObj = multiRequest.getFiles("file-input");
				atchFileId = atchFileService.saveAtchFile( fileObj, "BBSCOMMENT_", "", strStorePath );
				comment.setAtchFileId(atchFileId);
			}

			bbsCommentService.updateComment(comment);

			commentVO.setCommentCn("");
			commentVO.setCommentNo("");
		}
 
		return "forward:/mm/cop/bbs/" + bbsId + "/selectBoardArticle.do";
	}

	/**
	 * 익명 댓글을 수정한다.
	 *
	 * @param commentVO
	 * @param comment
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mm/cop/cmt/anonymous/updateComment.do")
	public String updateAnonymousCommentList(
			@ModelAttribute("searchVO") CommentVO commentVO, @ModelAttribute("comment") Comment comment,
			BindingResult bindingResult, ModelMap model) throws Exception {

		beanValidator.validate(comment, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", "댓글 작성자 및  내용은 필수 입력값입니다.");

			return "forward:/mm/cop/bbs/anonymous/selectBoardArticle.do";
		}

		comment.setLastUpdusrId("ANONYMOUS");
		comment.setCommentPassword(EgovFileScrty.encryptPassword(comment.getCommentPassword(), commentVO.getBbsId()));

		bbsCommentService.updateComment(comment);

		commentVO.setCommentNo("");
		commentVO.setCommentCn("");
		commentVO.setWrterNm("");

		return "forward:/mm/cop/bbs/anonymous/selectBoardArticle.do";
	}
}
