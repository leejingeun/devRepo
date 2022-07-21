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
package kr.co.sitglobal.oklms.mm.login.web;

import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovBBSManageService;
import egovframework.com.uss.ion.bnr.service.BannerVO;
import egovframework.com.uss.ion.bnr.service.EgovBannerService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
 /**
 * 
 * @author 
 * @since 2016. 07. 01.
 */
@Controller
public class LmsLoginController extends BaseController{

	@Resource(name = "beanValidatorJSR303")
	Validator validator;
	//공통 게시판 서비스
	@Resource(name = "EgovBBSManageService")
	private EgovBBSManageService bbsMngService;
	
	@Resource(name = "egovBannerService")
    private EgovBannerService egovBannerService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder){
		try {
			dataBinder.setValidator(this.validator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 로그인 페이지 이동.
	 * @param commandMap
	 * @param model
	 * @return
	 * @throws Exception
	 * String
	 */
	@RequestMapping(value = { "/mm/login/goLmsMobileLogin.do" })
	public String goLcmsLogin(HttpServletRequest request, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
		
		/*====================================================================
    	* 초기화 영역
    	=====================================================================*/
		// View호출
		String returnPage = "";
		BoardVO boardVO = new BoardVO();
		BannerVO bannerVO = new BannerVO(); 
		Map<String, Object> map1 = null;
		Map<String, Object> map2 = null;
		
		/*====================================================================
    	* 초기값 셋팅 영역
    	====================================================================*/
		String reqUri = StringUtils.defaultIfBlank( (String)commandMap.get("reqUri") , "/mm/main/lmsMainPage.do" );
		String retMsg = StringUtils.defaultIfBlank( (String)commandMap.get("retMsg") , "" );
		String memId = StringUtils.defaultIfBlank( (String)commandMap.get("memId") , "" );
		
		/*====================================================================
    	* 업무화면에서 사용하는 List Vo 셋팅 영역
    	====================================================================*/
		
		/*
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		
		if( null == user ){ // 방문자 정보로 셋팅함.( 로그인 하지않고 게시판 작업을 하는경우에 필요함. )
			user = new LoginVO();
			user.setUniqId("0000MEMB0000000");
			user.setId("GUEST");
			user.setName("방문자");
		}
		*/
		
		//공지사항 Main Page 게시물 Lsit 
		boardVO.setBbsId("BBSMSTR_000000000000");
		map1 = bbsMngService.selectMainPageBoardArticles(boardVO, "BBSA01");
		//주요학사일정 Main Page 게시물 Lsit
		boardVO.setBbsId("BBSMSTR_000000000020");
		map2 = bbsMngService.selectMainPageBoardArticles(boardVO, "BBSA01");
		
		//메인배너 관련 서비스 호출 시작
		/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(bannerVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(9999);
		paginationInfo.setPageSize(bannerVO.getPageSize());

		bannerVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		bannerVO.setLastIndex(paginationInfo.getLastRecordIndex());
		bannerVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		//메인-팝존배너
		bannerVO.setBannerType("BN0002");
		bannerVO.setBannerList(egovBannerService.selectBannerList(bannerVO));

		logger.debug("################################# ");
		logger.debug("popupzoneResultList : " + bannerVO.getBannerList());
		logger.debug("################################# ");
		model.addAttribute("popupzoneResultList", bannerVO.getBannerList());
		
		//메인-팝존배너
		bannerVO.setBannerType("BN0001");
		bannerVO.setBannerList(egovBannerService.selectBannerList(bannerVO));

		logger.debug("################################# ");
		logger.debug("mainResultList : " + bannerVO.getBannerList());
		logger.debug("################################# ");
		model.addAttribute("mainResultList", bannerVO.getBannerList());
		
		request.setAttribute("reqUri" , reqUri );
		request.setAttribute("retMsg" , retMsg );
		request.setAttribute("memId" , memId );
		model.addAttribute("noticeResultList", map1.get("resultList"));
		model.addAttribute("academicCalendarResultList", map2.get("resultList"));
		
		
		returnPage = "oklms_body/mm/login/lmsUserLogin";
		return returnPage;
	}
	
}
