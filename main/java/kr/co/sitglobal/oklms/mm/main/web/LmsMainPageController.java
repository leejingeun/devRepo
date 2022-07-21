package kr.co.sitglobal.oklms.mm.main.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.la.popup.service.PopupService;
import kr.co.sitglobal.oklms.lu.main.service.LmsUserMainPageService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovBBSAttributeManageService;
import egovframework.com.cop.bbs.service.EgovBBSManageService;
import egovframework.com.uss.ion.bnr.service.BannerVO;
import egovframework.com.uss.ion.bnr.service.EgovBannerService;
import egovframework.com.uss.ion.pwm.service.EgovPopupManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class LmsMainPageController extends BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(LmsMainPageController.class);

	/** EgovPopupManageService */
	@Resource(name = "egovPopupManageService")
	private EgovPopupManageService egovPopupManageService;

	@Resource(name = "EgovBBSAttributeManageService")
	private EgovBBSAttributeManageService bbsAttrbService;

	@Resource(name = "EgovBBSManageService")
	private EgovBBSManageService bbsMngService;

	@Resource(name = "popupService")
	private PopupService popupService;
	
	@Resource(name = "LmsUserMainPageService")
	private LmsUserMainPageService lmsUserMainPageService;
	
	@Resource(name = "egovBannerService")
    private EgovBannerService egovBannerService;
	
	@RequestMapping(value = "/mm/main/lmsMainPage.do")
	public String lcmsMainPage(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
		// View호출

		LOG.debug("#### /mm/main/lmsMainPage.do" );
		
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
		
		
		returnPage = "oklms/mm/main/lmsMainPage";
		return returnPage  ;
	}
 
}
