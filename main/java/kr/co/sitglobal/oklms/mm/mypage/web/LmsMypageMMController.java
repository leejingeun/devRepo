package kr.co.sitglobal.oklms.mm.mypage.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovBBSAttributeManageService;
import egovframework.com.cop.bbs.service.EgovBBSManageService;
import egovframework.com.uss.ion.bnr.service.EgovBannerService;
import egovframework.com.uss.ion.pwm.service.EgovPopupManageService;
import kr.co.sitglobal.ifx.service.IfxService;
import kr.co.sitglobal.ifx.vo.AunuriMemberVO;
import kr.co.sitglobal.ifx.vo.AunuriSubjectVO;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.la.popup.service.PopupService;
import kr.co.sitglobal.oklms.lu.main.service.LmsUserMainPageService;
import kr.co.sitglobal.oklms.lu.main.vo.LmsUserMainPageVO;
@Controller
public class LmsMypageMMController extends BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(LmsMypageMMController.class);
	
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
	
	@Resource(name = "ifxService")
	private IfxService ifxService;
		
	// 마이페이지
	@RequestMapping(value = "/mm/mypage/lmsMyPage.do")
	public String lmsMyPage(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
		// View호출

		LOG.debug("#### /mm/mypage/lmsMyPage.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		
		Map<String, Object> map1 = null;
		Map<String, Object> map2 = null;
		
		List<LmsUserMainPageVO> listLmsUserMainPageTotalCnt = null;
		List<LmsUserMainPageVO> listLmsUserMainPageTodayCnt = null;
		List<LmsUserMainPageVO> listLectureS​chedule = null;
		
		BoardVO boardVO = new BoardVO();	 
		boardVO.setMemType(loginInfo.getMemType());
		boardVO.setMemSeq(loginInfo.getMemSeq());
		
		String retView ="oklms/mm/mypage/lmsMyPage";
		//공지사항 MYPAGE Page 게시물 Lsit 
		boardVO.setBbsId("BBSMSTR_000000000005"); 
		map1 = bbsMngService.selectMainPageBoardArticles(boardVO, "");
		
//		map1 = bbsMngService.selectTopNoticeYnBoardArticles(boardVO, "");

		//Q & A  MYPAGE Page 게시물 Lsit 
		boardVO.setBbsId("BBSMSTR_000000000007");
		map2 = bbsMngService.selectMainPageBoardArticles(boardVO, "");		
		
		model.addAttribute("noticeResultList", map1.get("resultList"));
		model.addAttribute("qaResultList", map2.get("resultList"));
		

		LmsUserMainPageVO mainPageVO = new LmsUserMainPageVO();
		listLmsUserMainPageTotalCnt = lmsUserMainPageService.listLmsUserMainPageTotalCnt(mainPageVO);
		listLmsUserMainPageTodayCnt = lmsUserMainPageService.listLmsUserMainPageTodayCnt(mainPageVO);
		listLectureS​chedule = lmsUserMainPageService.listLectureS​chedule(mainPageVO);

		model.addAttribute("readTotalCnt", listLmsUserMainPageTotalCnt);
		model.addAttribute("readTodayCnt", listLmsUserMainPageTodayCnt);
		model.addAttribute("resultList", listLectureS​chedule);
		 
		model.addAttribute("memType",loginInfo.getMemType());
		//API 통한 교과정보, 관리자 제외한 진행중인 교과정보.
		AunuriMemberVO aunuriMemberVO = new AunuriMemberVO();
		aunuriMemberVO.setSessionMemSeq(loginInfo.getMemSeq());
		
		
		if("STD".equals(loginInfo.getMemType())){ //학습근로자 개설교과
			List<AunuriSubjectVO> listOjtAunuriSubject= ifxService.getOjtAunuriSubjectLesson(aunuriMemberVO);
			model.addAttribute( "listOjtAunuriSubject", listOjtAunuriSubject );
			
			List<AunuriSubjectVO> listOffJtAunuriSubject= ifxService.getOffJtAunuriSubjectLesson(aunuriMemberVO);
			model.addAttribute( "listOffJtAunuriSubject", listOffJtAunuriSubject );		
			
		} else if("PRT".equals(loginInfo.getMemType())){ //담당교수 개설교과
			List<AunuriSubjectVO> listOjtAunuriSubject= ifxService.getOjtAunuriSubjectInsMapping(aunuriMemberVO);
			model.addAttribute( "listOjtAunuriSubject", listOjtAunuriSubject );
			List<AunuriSubjectVO> listOffJtAunuriSubject= ifxService.getOffJtAunuriSubjectInsMapping(aunuriMemberVO);
			model.addAttribute( "listOffJtAunuriSubject", listOffJtAunuriSubject );
			int hSkillCnt = ifxService.getOjtAunuriSubjectInsHSkillCnt(aunuriMemberVO);
			model.addAttribute( "hSkillCnt", hSkillCnt );
			retView ="oklms/mm/mypage/lmsMyPagePrt";
		} else if("COT".equals(loginInfo.getMemType())){ //기업현장교사 개설교과
			List<AunuriSubjectVO> listOjtAunuriSubject= ifxService.getOjtAunuriSubjectTutMapping(aunuriMemberVO);
			model.addAttribute( "listOjtAunuriSubject", listOjtAunuriSubject );
			// 로그인권한별 URL 분기처리			
			retView ="oklms/mm/mypage/lmsMyPagePrt";
		}
		
		
		
		return retView;
	}	
}
