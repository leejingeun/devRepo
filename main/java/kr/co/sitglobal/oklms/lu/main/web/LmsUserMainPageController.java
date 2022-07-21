package kr.co.sitglobal.oklms.lu.main.web;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.sitglobal.aunuri.service.AunuriLinkService;
import kr.co.sitglobal.aunuri.vo.AunuriLinkMemberVO;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.commbiz.cmmcode.vo.CommonCodeVO;
import kr.co.sitglobal.oklms.commbiz.util.BizUtil;
import kr.co.sitglobal.oklms.la.menu.vo.MenuVO;
import kr.co.sitglobal.oklms.la.popup.service.PopupService;
import kr.co.sitglobal.oklms.la.popup.vo.PopupVO;
import kr.co.sitglobal.oklms.lu.main.service.LmsUserMainPageService;
import kr.co.sitglobal.oklms.lu.main.vo.LmsUserMainPageVO;
import kr.co.sitglobal.oklms.lu.subject.service.SubjectService;
import kr.co.sitglobal.oklms.lu.subject.vo.SubjectCompanyVO;
import kr.co.sitglobal.oklms.lu.subject.vo.SubjectVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.Globals;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovBBSAttributeManageService;
import egovframework.com.cop.bbs.service.EgovBBSManageService;
import egovframework.com.uss.ion.pwm.service.EgovPopupManageService;
import egovframework.com.uss.ion.pwm.service.PopupManageVO;

@Controller
public class LmsUserMainPageController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LmsUserMainPageController.class);

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
	
	@Resource(name = "aunuriLinkService")
	private AunuriLinkService aunuriLinkService;
	
	@Resource(name = "SubjectService")
	private SubjectService subjectService;
	
	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;
//	@Resource(name = "mainPageService")
//	private MainPageService mainPageService;


	@RequestMapping(value = "/lu/main/lmsUserMainPage.do")
	public String lmsUserMainPage(@ModelAttribute("frmSubject") SubjectVO subjectVO ,HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
		// View호출

		LOGGER.debug("#### /lu/main/lmsUserMainPage.do" );
		String view="oklms/lu/main/lmsUserMainPage";
		Map<String, Object> map1 = null;
		Map<String, Object> map2 = null;
		Map<String, Object> map3 = null; 
		List<LmsUserMainPageVO> listLmsUserMainPageTodayCnt = null;
		List<LmsUserMainPageVO> listLmsUserMainPageStatusCnt = null;
		List<LmsUserMainPageVO> listLectureS​chedule = null;
		LmsUserMainPageVO mainPageVO = new LmsUserMainPageVO();

		// 현재일자
		String nowDay =  StringUtils.defaultIfBlank( (String) commandMap.get("nowDay"), BizUtil.getCurrentDateString());
		nowDay = nowDay.replaceAll("-", "");
		nowDay = nowDay.replaceAll("\\.", "");
		
		model.addAttribute("yesDay", BizUtil.addDate(nowDay,-1));
		model.addAttribute("tomDay", BizUtil.addDate(nowDay,1));
		
		nowDay = BizUtil.toDateString(nowDay,"yyyyMMdd","yyyy.MM.dd");
		
		model.addAttribute("nowDay", nowDay);		
		model.addAttribute("nowDayName", BizUtil.getDayWeek(nowDay));
		
		//강의 일정
		mainPageVO.setTraningDate(nowDay);
 
		
		// 강의매뉴 표시 여부 Key 세션 설정
		HttpSession session = request.getSession(true);
		session.setAttribute("lecMenuMarkYn" , "N" );

		
		
		//교과목 관련 세션삭제 2017.02.20 csh
		BizUtil.setEmptyLecInfo(request);

		
		
		String retMsg = StringUtils.defaultIfBlank( (String) commandMap.get("retMsg"), "");
		model.addAttribute("retMsg", retMsg );

		String retMsgEncode = StringUtils.defaultIfBlank( (String) commandMap.get("retMsgEncode"), "");
		LOGGER.debug("#######retMsgEncode:"+retMsgEncode );
		retMsgEncode = URLDecoder.decode( retMsgEncode ,"UTF-8");
		LOGGER.debug("#######retMsgEncodeEncode:"+retMsgEncode );
		model.addAttribute("retMsgEncode", retMsgEncode );

		//사용자 권한
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(mainPageVO);
		String userUniqId = "";
		if( loginInfo.isLogin() ){
			MenuVO aa = new MenuVO();
			LOGGER.debug("#### aa.getSessionMemSeq() 1 : " + aa.getSessionMemSeq() );

			BeanUtils.copyProperties(loginVO,aa);
			LOGGER.debug("#### aa.getSessionMemSeq() 2 : " + aa.getSessionMemSeq() );

			model.addAttribute("authGroupId", loginVO.getAuthGroupId());
			model.addAttribute("usrId", loginVO.getMemId());
			model.addAttribute("usrSeq", loginVO.getMemSeq());
			model.addAttribute("memType", loginVO.getMemType());

			userUniqId = loginVO.getMemSeq();
		}

		PopupVO popupVO = new PopupVO();
		popupVO.setPageType("P0001");//메인
		List<PopupVO> popupList = popupService.getPopupAllList(popupVO);
		model.addAttribute("popupList", popupList);

		//전체 공지사항
		BoardVO boardVO = new BoardVO();
		boardVO.setFrstRegisterId(loginVO.getMemSeq());
		boardVO.setMemType(loginVO.getMemType());
		boardVO.setMemSeq(loginVO.getMemSeq());

		boardVO.setBbsId("BBSMSTR_000000000005");
		boardVO.setOrderByFlag(null);
		map1 = bbsMngService.selectMainPageBoardArticles(boardVO, "BBSA01");

		//전체 학습자료실
		boardVO.setBbsId("BBSMSTR_000000000006");
		map2 = bbsMngService.selectMainPageBoardArticles(boardVO, "BBSA01");

		//전체 Q&A
		boardVO.setBbsId("BBSMSTR_000000000007");
		map3 = bbsMngService.selectMainPageBoardArticles(boardVO, "BBSA02");

		model.addAttribute("noticeResultList", map1.get("resultList"));
		model.addAttribute("stdRefResultList", map2.get("resultList"));
		model.addAttribute("quAndAnResultList", map3.get("resultList"));

 
		listLmsUserMainPageTodayCnt = lmsUserMainPageService.listLmsUserMainPageTodayCnt(mainPageVO);
		listLectureS​chedule = lmsUserMainPageService.listLectureS​chedule(mainPageVO);
 
		model.addAttribute("readTodayCnt", listLmsUserMainPageTodayCnt);
		model.addAttribute("resultList", listLectureS​chedule);
		
				
		model.addAttribute("mainPageVO", mainPageVO);
		
		if(loginVO.getMemType().equals("STD")){
			
			mainPageVO.setSubjectTraningType("OFF");
			List<LmsUserMainPageVO> offlistLectureS​chedule =  lmsUserMainPageService.listLmsUserMainPageStdSchedule(mainPageVO);
			mainPageVO.setSubjectTraningType("OJT");
			List<LmsUserMainPageVO> ojtlistLectureS​chedule =  lmsUserMainPageService.listLmsUserMainPageStdSchedule(mainPageVO);

			model.addAttribute("offListSchedule", offlistLectureS​chedule);
			model.addAttribute("ojtListSchedule", ojtlistLectureS​chedule);
			
			listLmsUserMainPageStatusCnt = lmsUserMainPageService.listLmsUserMainPageStatusCnt(mainPageVO);
			model.addAttribute("listLmsUserMainPageStatusCnt", listLmsUserMainPageStatusCnt);
			view="oklms/lu/main/lmsUserMainPageStd";
		}else if(loginVO.getMemType().equals("PRT")){

			mainPageVO.setSubjectTraningType("OFF");
			List<LmsUserMainPageVO> offlistLectureS​chedule =  lmsUserMainPageService.listLmsUserMainPagePrtSchedule(mainPageVO);
			List<LmsUserMainPageVO> offStatus =  listLmsUserMainPageStatusCnt = lmsUserMainPageService.listLmsUserMainPageStatusCnt(mainPageVO);
			
			mainPageVO.setSubjectTraningType("OJT");
			List<LmsUserMainPageVO> ojtlistLectureS​chedule =  lmsUserMainPageService.listLmsUserMainPagePrtSchedule(mainPageVO);
			List<LmsUserMainPageVO> ojtStatus = listLmsUserMainPageStatusCnt = lmsUserMainPageService.listLmsUserMainPagePrtStatusPrtCnt(mainPageVO);
			
			model.addAttribute("offStatus", offStatus);
			model.addAttribute("ojtStatus", ojtStatus);			
			model.addAttribute("offListSchedule", offlistLectureS​chedule);
			model.addAttribute("ojtListSchedule", ojtlistLectureS​chedule);
			
			view="oklms/lu/main/lmsUserMainPagePrt";
		}else if(loginVO.getMemType().equals("COT")){
			// 훈련일정 
			List<LmsUserMainPageVO> listLectureCotSchedule =  lmsUserMainPageService.listLmsUserMainPageCotSchedule(mainPageVO);
			model.addAttribute("listLectureCotSchedule", listLectureCotSchedule);
			
			listLmsUserMainPageStatusCnt = lmsUserMainPageService.listLmsUserMainPageStatusCnt(mainPageVO);
			model.addAttribute("listLmsUserMainPageStatusCnt", listLmsUserMainPageStatusCnt);
			
			view="oklms/lu/main/lmsUserMainPageCot";
		}else if(loginVO.getMemType().equals("CCN")){
			
			// 기업체현황조회
			listLmsUserMainPageStatusCnt = lmsUserMainPageService.listLmsUserMainPageCcnCnt(mainPageVO);
			model.addAttribute("listLmsUserMainPageStatusCnt", listLmsUserMainPageStatusCnt);			
			// 훈련일정 
			List<LmsUserMainPageVO> listLectureCcnSchedule =  lmsUserMainPageService.listLmsUserMainPageCcnSchedule(mainPageVO);

			model.addAttribute("listLectureCcnSchedule", listLectureCcnSchedule);
			
			view="oklms/lu/main/lmsUserMainPageCcn";
		}else if(loginVO.getMemType().equals("CDP")){
			 
			loginInfo.putSessionToVo(subjectVO);
			// 학과코드,학과명 코드 리스트
			CommonCodeVO codeVO = new  CommonCodeVO();
			codeVO.setCodeGroup("DEPT_CD");
			List<CommonCodeVO> deptCodeList = commonCodeService.selectCmmCodeList(codeVO); // 학과
			model.addAttribute("deptCodeList", deptCodeList);
			
			List <SubjectVO> resultList = subjectService.listSubjectCdp(subjectVO);
			model.addAttribute("resultList", resultList);
			model.addAttribute("subjectVO", subjectVO);

			view="oklms/lu/main/lmsUserMainPageCdp";
		}else if(loginVO.getMemType().equals("CCM")){
			// 참여기업현황
			listLmsUserMainPageStatusCnt = lmsUserMainPageService.listLmsUserMainPageCcmCnt(mainPageVO);
			model.addAttribute("listLmsUserMainPageStatusCnt", listLmsUserMainPageStatusCnt);		
			
			List<LmsUserMainPageVO> listLectureScheduleCcm =  lmsUserMainPageService.listLmsUserMainPageCcmSchedule(mainPageVO);
			model.addAttribute("listLectureScheduleCcm", listLectureScheduleCcm);	
			
			view="oklms/lu/main/lmsUserMainPageCcm";
		}
 
		return view;
	}
	@RequestMapping(value = "/lu/main/lmsUserMainPageUpdate.json")
	public @ResponseBody Map<String, Object>  lmsUserMainPageUpdate(@ModelAttribute("frmMember") AunuriLinkMemberVO aunuriLinkMemberVO
			,RedirectAttributes redirectAttributes
			,SessionStatus status
			,HttpServletRequest request
			,ModelMap modelt) throws Exception {	
		
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(aunuriLinkMemberVO); // session의 정보를 VO에 추가.
		
		Map<String , Object> returnMap = new HashMap<String , Object>();
		logger.debug("#### URL = /lu/main/lmsUserMainPageUpdate.json" );
		
		
        int iResult = aunuriLinkService.updateAunuriOutMemberInfoEtc(aunuriLinkMemberVO);
       
        if(iResult > 0){
			returnMap.put("retCd", "10000");
		} else {
			returnMap.put("retCd", "");
		}
		
		return returnMap;
	}
 
	
	
	
	@RequestMapping(value = "/lu/test/currproc/listCurrProc.do")
	public String test(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
		return null;
	}
}
