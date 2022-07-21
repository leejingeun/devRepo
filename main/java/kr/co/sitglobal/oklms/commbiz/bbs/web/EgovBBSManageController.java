package kr.co.sitglobal.oklms.commbiz.bbs.web;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.atchFile.vo.AtchFileVO;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.commbiz.cmmcode.vo.CommonCodeVO;
import kr.co.sitglobal.oklms.lu.currproc.service.CurrProcService;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.service.Globals;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.bbs.service.Board;
import egovframework.com.cop.bbs.service.BoardMaster;
import egovframework.com.cop.bbs.service.BoardMasterVO;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovBBSAttributeManageService;
import egovframework.com.cop.bbs.service.EgovBBSCommentService;
import egovframework.com.cop.bbs.service.EgovBBSManageService;
import egovframework.com.cop.bbs.service.EgovBBSSatisfactionService;
import egovframework.com.cop.bbs.service.EgovBBSScrapService;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 게시물 관리를 위한 컨트롤러 클래스
 *
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------       --------    ---------------------------
 *   2009.3.19  이삼섭          최초 생성
 *   2009.06.29	한성곤			2단계 기능 추가 (댓글관리, 만족도조사)
 *   2011.07.01 안민정		 	댓글, 스크랩, 만족도 조사 기능의 종속성 제거
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *   2011.09.07 서준식           유효 게시판 게시일 지나도 게시물이 조회되던 오류 수정
 *  2016.8.30	AA			프로젝트에 맞게 호출 URL 및 tiles 적용
 * </pre>
 */

@Controller
public class EgovBBSManageController extends BaseController {

	@Resource(name = "EgovBBSManageService")
	private EgovBBSManageService bbsMngService;

	@Resource(name = "EgovBBSAttributeManageService")
	private EgovBBSAttributeManageService bbsAttrbService;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name = "atchFileService")
	private AtchFileService atchFileService;

	@Resource(name = "currProcService")
	private CurrProcService currProcService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;

	// ---------------------------------
	// 2009.06.29 : 2단계 기능 추가
	// 2011.07.01 : 댓글, 스크랩, 만족도 조사 기능의 종속성 제거
	// ---------------------------------
	@Autowired(required = false)
	private EgovBBSCommentService bbsCommentService;

	@Autowired(required = false)
	private EgovBBSSatisfactionService bbsSatisfactionService;

	@Autowired(required = false)
	private EgovBBSScrapService bbsScrapService;
	// //-------------------------------

	@Autowired
	private DefaultBeanValidator beanValidator;

	// protected Logger log = Logger.getLogger(this.getClass());

	/**
	 * XSS 방지 처리.
	 *
	 * @param data
	 * @return
	 */
	protected String unscript(String data) {
		if (data == null || data.trim().equals("")) {
			return "";
		}

		String ret = data;

		ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;script");
		ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/script");

		ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;object");
		ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/object");

		ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;applet");
		ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/applet");

		ret = ret.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
		ret = ret.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");

		ret = ret.replaceAll("<(F|f)(O|o)(R|r)(M|m)", "&lt;form");
		ret = ret.replaceAll("</(F|f)(O|o)(R|r)(M|m)", "&lt;form");

		return ret;
	}

	/**
	 * 팝업화면에 게시물에 대한 목록을 조회한다.
	 *
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/{pathBbsId}/popupSelectBoardList.do")
	public String popupSelectBoardList(@RequestParam Map<String, Object> commandMap, @PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model, RedirectAttributes redirectAttributes,
			HttpServletRequest request) throws Exception {

		/*====================================================================
    	* 초기화 영역
    	==============================================================r======*/
	    String retMsg = "";

		/*====================================================================
    	* 업무화면에서 사용하는 List Vo 셋팅 영역
    	====================================================================*/
		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		if( null == user ){ // 방문자 정보로 셋팅함.( 로그인 하지않고 게시판 작업을 하는경우에 필요함. )
			user = new LoginVO();
			user.setUniqId("0000MEMB0000000");
			user.setId("GUEST");
			user.setName("방문자");
		}

		boardVO.setBbsId(boardVO.getBbsId());
		boardVO.setBbsNm(boardVO.getBbsNm());

		BoardMasterVO vo = new BoardMasterVO();

		vo.setBbsId(boardVO.getBbsId());
		vo.setUniqId(user.getUniqId());

		BoardMasterVO master = bbsAttrbService.selectBBSMasterInf(vo);

		// -------------------------------
		// 방명록이면 방명록 URL로 forward
		// -------------------------------
		if (master.getBbsTyCode().equals("BBST04")) {
			return "forward:/" + serviceArea + "/cop/bbs/" + pathBbsId + "/selectGuestList.do";
		}
		// //-----------------------------

		logger.debug("################################# ");
		logger.debug("pageUnit : " + propertyService.getInt("pageUnit"));
		logger.debug("pageSize : " + propertyService.getInt("pageSize"));
		logger.debug("################################# ");

		boardVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardVO.setPageSize(propertyService.getInt("pageSize"));

		String selectRecordIndex = "";
		selectRecordIndex = (String) commandMap.get("selectRecordIndex");

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
		//목록에서 Page View 라디어버튼 onchange시 분기처리
		logger.debug("################################# ");
		logger.debug("selectRecordIndex1 : " + selectRecordIndex);
		logger.debug("selectRecordIndex2 : " + selectRecordIndex);
		logger.debug("################################# ");
		if(null != selectRecordIndex ){
			paginationInfo.setRecordCountPerPage(Integer.valueOf(selectRecordIndex));
		}else{
			paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
		}
		//paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
		paginationInfo.setPageSize(boardVO.getPageSize());

		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());

		if(null != selectRecordIndex ){
			boardVO.setLastIndex(Integer.valueOf(selectRecordIndex));
		}else{
			boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		}

		// -------------------------------
		// 게시판마스터테이블에 답장기능여부 'N' 이면 selectTopNoticeYnBoardArticles 메소드호출
		// 게시판마스터테이블에 답장기능여부 'Y' 이면 selectBoardArticles 메소드호출
		// -------------------------------
		int totCnt = 0;
		Map<String, Object> map = null;
		if("N".equals(master.getReplyPosblAt())) {
			//답장기능이 없는 게시판은 조회쿼리에 상단노출 항목을 포함.
			map = bbsMngService.selectTopNoticeYnBoardArticles(boardVO, master.getBbsAttrbCode());// 2011.09.07
			totCnt = Integer.parseInt((String) map.get("resultCnt"));
		} else {
			//답장기능이 있는 게시판은 조회쿼리에 상단노출 항목을 미포함
			map = bbsMngService.selectBoardArticles(boardVO, master.getBbsAttrbCode());// 2011.09.07
			totCnt = Integer.parseInt((String) map.get("resultCnt"));
		}

		paginationInfo.setTotalRecordCount(totCnt);

		// -------------------------------
		// 기본 BBS template 지정
		// -------------------------------
		if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
			master.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
		}
		// //-----------------------------

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("brdMstrVO", master);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("memType", user.getMemType());
		model.addAttribute("selectRecordIndex", selectRecordIndex);

		return "oklms_popup/" + serviceArea + "/egovframework/com/cop/bbs/SearchEgovNoticeListPopup";
		// return "oklms/commbiz/bbs/EgovNoticeList";
	}

	/**
	 * 팝업화면 게시물에 대한 상세 정보를 조회한다.
	 *
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/{pathBbsId}/popupSelectBoardArticle.do")
	public String popupSelectBoardArticle(@RequestParam Map<String, Object> commandMap, @PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {

		String tmpNttId = StringUtils.defaultIfBlank( (String)commandMap.get("nttId") , "" );
		long nttId = Long.parseLong(tmpNttId);

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		model.addAttribute("bbsId" , pathBbsId);
		boardVO.setBbsId(pathBbsId);
		boardVO.setNttId(nttId);

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		if( null == user ){ // 방문자 정보로 셋팅함.( 로그인 하지않고 게시판 작업을 하는경우에 필요함. )
			user = new LoginVO();
			user.setUniqId("0000MEMB0000000");
			user.setId("GUEST");
			user.setName("방문자");
		}

		// 조회수 증가 여부 지정
		boardVO.setPlusCount(true);

		// ---------------------------------
		// 2009.06.29 : 2단계 기능 추가
		// ---------------------------------
		if (!boardVO.getSubPageIndex().equals("")) {
			boardVO.setPlusCount(false);
		}
		// //-------------------------------

		boardVO.setLastUpdusrId(user.getUniqId());
		BoardVO vo = bbsMngService.selectBoardArticle(boardVO);

		model.addAttribute("result", vo);
		// CommandMap의 형태로 개선????

		model.addAttribute("sessionUniqId", user.getUniqId());

		// ----------------------------
		// template 처리 (기본 BBS template 지정 포함)
		// ----------------------------
		BoardMasterVO master = new BoardMasterVO();

		master.setBbsId(boardVO.getBbsId());
		master.setUniqId(user.getUniqId());

		BoardMasterVO masterVo = bbsAttrbService.selectBBSMasterInf(master);

		if (masterVo.getTmplatCours() == null || masterVo.getTmplatCours().equals("")) {
			masterVo.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
		}

		model.addAttribute("brdMstrVO", masterVo);
		// //-----------------------------

		// ----------------------------
		// 2009.06.29 : 2단계 기능 추가
		// 2011.07.01 : 댓글, 스크랩, 만족도 조사 기능의 종속성 제거
		// ----------------------------
		if (bbsCommentService != null) {
			if (bbsCommentService.canUseComment(boardVO.getBbsId())) {
				model.addAttribute("useComment", "true");
			}
		}
		if (bbsSatisfactionService != null) {
			if (bbsSatisfactionService.canUseSatisfaction(boardVO.getBbsId())) {
				model.addAttribute("useSatisfaction", "true");
			}
		}
		if (bbsScrapService != null) {
			if (bbsScrapService.canUseScrap()) {
				model.addAttribute("useScrap", "true");
			}
		}
		// //--------------------------

		return "oklms_popup/" + serviceArea + "/egovframework/com/cop/bbs/EgovNoticeInqirePopup";
		// return "oklms/commbiz/bbs/EgovNoticeInqire";
	}

	/**
	 * 팝업화면 게시물에 대한 상세 정보를 조회한다.
	 *
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/lu/cop/bbs/popupSelectBoardArticleMain.do")
	public String popupSelectBoardArticleMain(@RequestParam Map<String, Object> commandMap,
			@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {

		String tmpNttId = StringUtils.defaultIfBlank( (String)commandMap.get("nttId") , "" );
		String tmpBbsId = StringUtils.defaultIfBlank( (String)commandMap.get("bbsId") , "" );
		long nttId = Long.parseLong(tmpNttId);

		model.addAttribute("pathBbsIdStr" , tmpBbsId);
		model.addAttribute("bbsId" , tmpBbsId);
		boardVO.setBbsId(tmpBbsId);
		boardVO.setNttId(nttId);

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		if( null == user ){ // 방문자 정보로 셋팅함.( 로그인 하지않고 게시판 작업을 하는경우에 필요함. )
			user = new LoginVO();
			user.setUniqId("0000MEMB0000000");
			user.setId("GUEST");
			user.setName("방문자");
		}

		// 조회수 증가 여부 지정
		boardVO.setPlusCount(true);

		// ---------------------------------
		// 2009.06.29 : 2단계 기능 추가
		// ---------------------------------
		if (!boardVO.getSubPageIndex().equals("")) {
			boardVO.setPlusCount(false);
		}
		// //-------------------------------

		boardVO.setLastUpdusrId(user.getUniqId());
		BoardVO vo = bbsMngService.selectBoardArticle(boardVO);

		model.addAttribute("result", vo);
		// CommandMap의 형태로 개선????

		model.addAttribute("sessionUniqId", user.getUniqId());

		// ----------------------------
		// template 처리 (기본 BBS template 지정 포함)
		// ----------------------------
		BoardMasterVO master = new BoardMasterVO();

		master.setBbsId(boardVO.getBbsId());
		master.setUniqId(user.getUniqId());

		BoardMasterVO masterVo = bbsAttrbService.selectBBSMasterInf(master);

		if (masterVo.getTmplatCours() == null || masterVo.getTmplatCours().equals("")) {
			masterVo.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
		}

		model.addAttribute("brdMstrVO", masterVo);
		// //-----------------------------

		// ----------------------------
		// 2009.06.29 : 2단계 기능 추가
		// 2011.07.01 : 댓글, 스크랩, 만족도 조사 기능의 종속성 제거
		// ----------------------------
		if (bbsCommentService != null) {
			if (bbsCommentService.canUseComment(boardVO.getBbsId())) {
				model.addAttribute("useComment", "true");
			}
		}
		if (bbsSatisfactionService != null) {
			if (bbsSatisfactionService.canUseSatisfaction(boardVO.getBbsId())) {
				model.addAttribute("useSatisfaction", "true");
			}
		}
		if (bbsScrapService != null) {
			if (bbsScrapService.canUseScrap()) {
				model.addAttribute("useScrap", "true");
			}
		}
		// //--------------------------

		return "oklms_popup/lu/egovframework/com/cop/bbs/EgovNoticeInqirePopup";
		// return "oklms/commbiz/bbs/EgovNoticeInqire";
	}

	/**
	 * 게시물에 대한 목록을 조회한다.
	 *
	 * @param boardVO
	 * @param sessionVO
	 * @param modelclasss
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/{pathBbsId}/selectBoardList.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/selectBoardList.do")
	public String selectBoardArticles(@RequestParam Map<String, Object> paramMap, @PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model, RedirectAttributes redirectAttributes,
			HttpServletRequest request) throws Exception {

		/*====================================================================
    	* 초기화 영역
    	==============================================================r======*/
		String subjectTraningType = ""; 			//과목구분코드(01:ojt, 02:offjt)
		String yyyy	= ""; 					//학년도
	    String term	= ""; 					//학기
	    String subjectCode	= ""; 			//교과목코드
	    String subjectName	= "";    		//교과목명
	    String classs	= ""; 				//분반
	    String weekCnt	= ""; 				//학습주차
	    String lectureMenuMarkYn	= "N"; 	//진행중인강의 표시여부
	    String retMsg = "";
	    String searchSubClass = StringUtils.defaultString((String)paramMap.get("searchSubClass"),"");
	    int totCnt = 0;
		Map<String, Object> map = null;
		HttpSession session = request.getSession(); // 로그인 사용자에 교과별-교과목 관련 세션 Key get 처리
		CurrProcVO currProcVO = new CurrProcVO();
		CurrProcVO currProcReadVO = new CurrProcVO();

		/*====================================================================
    	* 초기값 셋팅 영역
    	====================================================================*/
		if("BBSMSTR_000000000005".equals(pathBbsId)
		 ||"BBSMSTR_000000000006".equals(pathBbsId)
		 ||"BBSMSTR_000000000007".equals(pathBbsId)
		 ){
			lectureMenuMarkYn = "N";
		}else{
			lectureMenuMarkYn = "Y";
		}

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		/*if( null == user ){ // 방문자 정보로 셋팅함.( 로그인 하지않고 게시판 작업을 하는경우에 필요함. )
			user = new LoginVO();
			user.setUniqId("0000MEMB0000000");
			user.setId("GUEST");
			user.setName("방문자");
		}*/

		/*serviceArea 이 관리자 이외 경우는 진행중인 교과목 세션정보를 젯팅한다. */
		if( ("lu".equals(serviceArea) ||  "mm".equals(serviceArea) ) && "Y".equals(lectureMenuMarkYn)){
			subjectTraningType = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_TRANING_TYPE) , "" );
			yyyy = StringUtils.defaultString( (String)session.getAttribute(Globals.YYYY) , "" );
			term = StringUtils.defaultString( (String)session.getAttribute(Globals.TERM) , "" );
			subjectCode = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_CODE) , "" );
			subjectName = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_NAME) , "" );
			classs = StringUtils.defaultString( (String)session.getAttribute(Globals.CLASS) , "" );
			weekCnt = StringUtils.defaultString( (String)session.getAttribute(Globals.WEEK_CNT) , "" );
			List<CurrProcVO> listSubjectClassInfo = null;

			model.addAttribute("subjectTraningType", subjectTraningType);

			currProcVO.setYyyy(yyyy);
			currProcVO.setTerm(term);
			currProcVO.setSubjectCode(subjectCode);

			if("OJT".equals(subjectTraningType) && "".equals(searchSubClass) && "PRT".equals(user.getMemType())){
				listSubjectClassInfo = currProcService.listSubjectClassInfo(currProcVO);
				model.addAttribute("resultSubjectClassList", listSubjectClassInfo);
			}

			if("OJT".equals(subjectTraningType) && !"".equals(searchSubClass) && "PRT".equals(user.getMemType())){
				currProcVO.setSubClass(searchSubClass);
				listSubjectClassInfo = currProcService.listSubjectClassInfo(currProcVO);
				model.addAttribute("resultSubjectClassList", listSubjectClassInfo);

				currProcReadVO = currProcService.getMySubjectInfo(currProcVO);
				model.addAttribute("currProcReadVO", currProcReadVO);
			}
		}

		/*====================================================================
    	* 업무화면에서 사용하는 List Vo 셋팅 영역
    	====================================================================*/
		logger.debug("################################# ");
		logger.debug("#### subjectDivCd : " + subjectTraningType );
		logger.debug("#### yyyy : " + yyyy );
		logger.debug("#### term : " + term );
		logger.debug("#### subjectCode : " + subjectCode );
		logger.debug("#### subjectName : " + subjectName );
		logger.debug("#### classs : " + classs );
		logger.debug("#### weekCnt : " + weekCnt );
		logger.debug("################################# ");
		logger.debug("serviceArea : " + serviceArea);
		logger.debug("pathBbsId : " + pathBbsId);
		logger.debug("################################# ");

		/* serviceArea 이 관리자 이외 경우는 진행중인 교과목 세션정보가 있는지 비교하여
		   null 일경우 마이페이지로 포워딩한다.
		*/
		if("Y".equals(lectureMenuMarkYn)){
			if( ( "lu".equals(serviceArea) ||  "mm".equals(serviceArea) ) && StringUtils.isBlank( yyyy )){
				retMsg = "Left 메뉴에서 진행중인과정에 교과목 하나를 선택하세요.";
				retMsg = URLEncoder.encode( retMsg ,"UTF-8");
				logger.debug("#### retMsg=" + retMsg );
				return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
			}

			// 관리자 이외 화면에서만 세션에 교과목 정보를 셋팅하여 목록 게시판에 파라메터을 전달한다.
			if(  "lu".equals(serviceArea) ||  "mm".equals(serviceArea)){
				model.addAttribute("yyyy" , yyyy);
				model.addAttribute("term" , term);
				model.addAttribute("subjectCode" , subjectCode);

				if("OJT".equals(subjectTraningType) && !"".equals(searchSubClass) && "PRT".equals(user.getMemType())){
					model.addAttribute("subClass" , searchSubClass);
				} else if("OJT".equals(subjectTraningType) && "".equals(searchSubClass) && "PRT".equals(user.getMemType())){
					model.addAttribute("subClass" , classs);
				} else if("OFF".equals(subjectTraningType)){
					model.addAttribute("subClass" , classs);
				} else if( "OJT".equals(subjectTraningType) && "".equals(searchSubClass) && "COT".equals(user.getMemType())){ // 기업현장교사이며 OJT 일 경우 분반 추가 (담당교과 글작성시 분반정보가 없음)
					model.addAttribute("subClass" , classs);
				}

			}
		}

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);

		boardVO.setBbsId(boardVO.getBbsId());
		boardVO.setBbsNm(boardVO.getBbsNm());
		boardVO.setFrstRegisterId(user.getUniqId());

		BoardMasterVO vo = new BoardMasterVO();

		vo.setBbsId(boardVO.getBbsId());
		vo.setUniqId(user.getUniqId());

		BoardMasterVO master = bbsAttrbService.selectBBSMasterInf(vo);

		// -------------------------------
		// 방명록이면 방명록 URL로 forward
		// -------------------------------
		if (master.getBbsTyCode().equals("BBST04")) {
			return "forward:/" + serviceArea + "/cop/bbs/" + pathBbsId + "/selectGuestList.do";
		}
		// //-----------------------------

		logger.debug("################################# ");
		logger.debug("pageUnit : " + propertyService.getInt("pageUnit"));
		logger.debug("pageSize : " + propertyService.getInt("pageSize"));
		logger.debug("################################# ");

		boardVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardVO.setPageSize(propertyService.getInt("pageSize"));

		String selectRecordIndex = "";
		selectRecordIndex = (String) paramMap.get("selectRecordIndex");

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
		//목록에서 Page View 라디어버튼 onchange시 분기처리
		logger.debug("################################# ");
		logger.debug("selectRecordIndex : " + selectRecordIndex);
		logger.debug("################################# ");
		if(null != selectRecordIndex ){
			paginationInfo.setRecordCountPerPage(Integer.valueOf(selectRecordIndex));
		}else{
			paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
		}
		//paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
		paginationInfo.setPageSize(boardVO.getPageSize());

		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());

		if(null != selectRecordIndex ){
			boardVO.setLastIndex(Integer.valueOf(selectRecordIndex));
		}else{
			boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		}

		// -------------------------------
		// 게시판마스터테이블에 답장기능여부 'N' 이면 selectTopNoticeYnBoardArticles 메소드호출
		// 게시판마스터테이블에 답장기능여부 'Y' 이면 selectBoardArticles 메소드호출
		// -------------------------------
		if("Y".equals(lectureMenuMarkYn)){
			if (boardVO.getSearchYyyy() == null || boardVO.getSearchYyyy().equals("")) {
				boardVO.setSearchYyyy(yyyy);
				boardVO.setSearchTerm(term);
				boardVO.setSearchSubjectCode(subjectCode);
				boardVO.setSearchSubClass(classs);

				if("OJT".equals(subjectTraningType) && !"".equals(searchSubClass) && "PRT".equals(user.getMemType())){
					boardVO.setSearchSubClass(searchSubClass);
				} else if("OJT".equals(subjectTraningType) && "".equals(searchSubClass) && "PRT".equals(user.getMemType())){
					boardVO.setSearchSubClass(classs);
				} else if("OFF".equals(subjectTraningType)){
					boardVO.setSearchSubClass(classs);
				}

			}

			if("OJT".equals(subjectTraningType) && !"".equals(searchSubClass) && "PRT".equals(user.getMemType())){
				boardVO.setSubClass(searchSubClass);
			}
		}

		logger.debug("################################# ");
		logger.debug("replyAt : " + boardVO.getReplyAt());
		logger.debug("frstRegisterId : " + boardVO.getFrstRegisterId());
		logger.debug("memType : " + user.getMemType());
		logger.debug("memSeq : " + user.getUniqId());
		logger.debug("################################# ");

		if("N".equals(lectureMenuMarkYn) && "Y".equals(boardVO.getReplyAt())){
			boardVO.setSearchYyyy(null);
			boardVO.setSearchTerm(null);
			boardVO.setSearchSubjectCode(null);
			boardVO.setSearchSubClass(null);

			boardVO.setYyyy(null);
			boardVO.setTerm(null);
			boardVO.setSubjectCode(null);
			boardVO.setSubClass(null);
		}

		boardVO.setMemType(user.getMemType());
		boardVO.setMemSeq(user.getUniqId());
		if("N".equals(master.getReplyPosblAt())) {
			//답장기능이 없는 게시판은 조회쿼리에 상단노출 항목을 포함.
			map = bbsMngService.selectTopNoticeYnBoardArticles(boardVO, master.getBbsAttrbCode());// 2011.09.07
			totCnt = Integer.parseInt((String) map.get("resultCnt"));
		} else {
			//답장기능이 있는 게시판은 조회쿼리에 상단노출 항목을 미포함
			map = bbsMngService.selectBoardArticles(boardVO, master.getBbsAttrbCode());// 2011.09.07
			totCnt = Integer.parseInt((String) map.get("resultCnt"));
		}

		paginationInfo.setTotalRecordCount(totCnt);

		// -------------------------------
		// 기본 BBS template 지정
		// -------------------------------
		if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
			master.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
		}
		// //-----------------------------
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("brdMstrVO", master);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("memType", user.getMemType());
		model.addAttribute("selectRecordIndex", selectRecordIndex);
		model.addAttribute("lectureMenuMarkYn", lectureMenuMarkYn);

		String retrunUrl = "";
		String serviceAreaUrl = "";
		String bbsBordId = "";
		serviceAreaUrl = serviceArea;
		bbsBordId = pathBbsId;

		if("la".equals(serviceAreaUrl)){
			retrunUrl = "oklms/la/egovframework/com/cop/bbs/EgovNoticeList";
		}else if("mm".equals(serviceAreaUrl)){
			if("N".equals(lectureMenuMarkYn)){
				retrunUrl = "oklms/mm/egovframework/com/cop/bbs/EgovAllSubjectNoticeList";
			}else{
				retrunUrl = "oklms/mm/egovframework/com/cop/bbs/EgovSubjectNoticeList";
			}
		}else{
			if("N".equals(lectureMenuMarkYn)){
				retrunUrl = "oklms/lu/egovframework/com/cop/bbs/EgovAllSubjectNoticeList";
			}else{
				retrunUrl = "oklms/lu/egovframework/com/cop/bbs/EgovSubjectNoticeList";
			}
		}

		return retrunUrl;
		// return "oklms/commbiz/bbs/EgovNoticeList";
	}

	/**
	 * 게시물에 대한 상세 정보를 조회한다.
	 *
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */

	@RequestMapping("/{serviceArea}/cop/bbs/{pathBbsId}/selectBoardArticle.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/selectBoardArticle.do")
	public String selectBoardArticle(@RequestParam Map<String, Object> paramMap, @PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {

		String retrunUrl = "";
		String serviceAreaUrl = "";
		String lectureMenuMarkYn = StringUtils.defaultString((String)paramMap.get("lectureMenuMarkYn"),"N");
		serviceAreaUrl = serviceArea;
		AtchFileVO atchFileVO = new AtchFileVO();

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		model.addAttribute("bbsId" , pathBbsId);
		boardVO.setBbsId(pathBbsId);

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		/*if( null == user ){ // 방문자 정보로 셋팅함.( 로그인 하지않고 게시판 작업을 하는경우에 필요함. )
			user = new LoginVO();
			user.setUniqId("0000MEMB0000000");
			user.setId("GUEST");
			user.setName("방문자");
		}*/

		// 조회수 증가 여부 지정
		boardVO.setPlusCount(true);

		// ---------------------------------
		// 2009.06.29 : 2단계 기능 추가
		// ---------------------------------
		if (!boardVO.getSubPageIndex().equals("")) {
			boardVO.setPlusCount(false);
		}
		// //-------------------------------

		boardVO.setLastUpdusrId(user.getUniqId());
		BoardVO vo = bbsMngService.selectBoardArticle(boardVO);

		model.addAttribute("result", vo);
		// CommandMap의 형태로 개선????

		model.addAttribute("sessionUniqId", user.getUniqId());

		// ----------------------------
		// template 처리 (기본 BBS template 지정 포함)
		// ----------------------------
		BoardMasterVO master = new BoardMasterVO();

		master.setBbsId(boardVO.getBbsId());
		master.setUniqId(user.getUniqId());

		BoardMasterVO masterVo = bbsAttrbService.selectBBSMasterInf(master);

		if (masterVo.getTmplatCours() == null || masterVo.getTmplatCours().equals("")) {
			masterVo.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
		}

		model.addAttribute("brdMstrVO", masterVo);
		model.addAttribute("lectureMenuMarkYn", lectureMenuMarkYn);
		// //-----------------------------

		// ----------------------------
		// 2009.06.29 : 2단계 기능 추가
		// 2011.07.01 : 댓글, 스크랩, 만족도 조사 기능의 종속성 제거
		// ----------------------------
		if (bbsCommentService != null) {
			if (bbsCommentService.canUseComment(boardVO.getBbsId())) {
				model.addAttribute("useComment", "true");
			}
		}
		if (bbsSatisfactionService != null) {
			if (bbsSatisfactionService.canUseSatisfaction(boardVO.getBbsId())) {
				model.addAttribute("useSatisfaction", "true");
			}
		}
		if (bbsScrapService != null) {
			if (bbsScrapService.canUseScrap()) {
				model.addAttribute("useScrap", "true");
			}
		}
		// //--------------------------

		logger.debug("################################# ");
		logger.debug("yyyy : " + vo.getYyyy());
		logger.debug("term : " + vo.getTerm());
		logger.debug("subjectCode : " + vo.getSubjectCode());
		logger.debug("subClass : " + vo.getSubClass());
		logger.debug("################################# ");
		logger.debug("user.getId() : " + user.getId());
		logger.debug("user.getName() : " + user.getName());
		logger.debug("user.getUniqId() : " + user.getUniqId());
		logger.debug("memType : " + user.getMemType());
		logger.debug("################################# ");

		if( "la".equals(serviceAreaUrl)){
			retrunUrl = "oklms/la/egovframework/com/cop/bbs/EgovNoticeInqire";
		} else if( "mm".equals(serviceAreaUrl)){
			//첨부파일 정보셋팅
			atchFileVO.setFileSn(1);
			atchFileVO.setAtchFileId(vo.getAtchFileId());
			AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
			model.addAttribute("resultFile", resultFile);
			model.addAttribute("memType", user.getMemType());

			if("N".equals(lectureMenuMarkYn)){
				retrunUrl = "oklms/mm/egovframework/com/cop/bbs/EgovAllSubjectNoticeInqire";
			} else {
				retrunUrl = "oklms/mm/egovframework/com/cop/bbs/EgovSubjectNoticeInqire";


				CurrProcVO currProcVO = new CurrProcVO();
				CurrProcVO currProcReadVO = new CurrProcVO();

				currProcVO.setYyyy(vo.getYyyy());
				currProcVO.setTerm(vo.getTerm());
				currProcVO.setSubjectCode(vo.getSubjectCode());
				currProcVO.setSubClass(vo.getSubClass());

				currProcReadVO = currProcService.getMySubjectInfo(currProcVO);
				model.addAttribute("currProcReadVO", currProcReadVO);

				model.addAttribute("yyyy", vo.getYyyy());
				model.addAttribute("term", vo.getTerm());
				model.addAttribute("subjectCode", vo.getSubjectCode());
				model.addAttribute("subClass", vo.getSubClass());
			}
		} else {
			//첨부파일 정보셋팅
			atchFileVO.setFileSn(1);
			atchFileVO.setAtchFileId(vo.getAtchFileId());
			AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
			model.addAttribute("resultFile", resultFile);
			model.addAttribute("memType", user.getMemType());

			if("N".equals(lectureMenuMarkYn)){
				retrunUrl = "oklms/lu/egovframework/com/cop/bbs/EgovAllSubjectNoticeInqire";
			} else {
				retrunUrl = "oklms/lu/egovframework/com/cop/bbs/EgovSubjectNoticeInqire";


				CurrProcVO currProcVO = new CurrProcVO();
				CurrProcVO currProcReadVO = new CurrProcVO();

				currProcVO.setYyyy(vo.getYyyy());
				currProcVO.setTerm(vo.getTerm());
				currProcVO.setSubjectCode(vo.getSubjectCode());
				currProcVO.setSubClass(vo.getSubClass());

				currProcReadVO = currProcService.getMySubjectInfo(currProcVO);
				model.addAttribute("currProcReadVO", currProcReadVO);

				model.addAttribute("yyyy", vo.getYyyy());
				model.addAttribute("term", vo.getTerm());
				model.addAttribute("subjectCode", vo.getSubjectCode());
				model.addAttribute("subClass", vo.getSubClass());
			}
		}

		return retrunUrl;
		// return "oklms/commbiz/bbs/EgovNoticeInqire";
	}

	/**
	 * 게시물 등록을 위한 등록페이지로 이동한다.
	 *
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/{pathBbsId}/addBoardArticle.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/addBoardArticle.do")
	public String addBoardArticle(@RequestParam Map<String, Object> paramMap, @PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,  @ModelAttribute("searchVO") BoardVO boardVO,
			ModelMap model) throws Exception {

		String retrunUrl = "";
		String serviceAreaUrl = "";
		String lectureMenuMarkYn = StringUtils.defaultString((String)paramMap.get("lectureMenuMarkYn"),"N");
		String categoryCd = StringUtils.defaultString((String)paramMap.get("categoryCd"),"");
		String subjectTraningtype = StringUtils.defaultString((String)paramMap.get("subjectTraningtype"),"");
		String subjectName = StringUtils.defaultString((String)paramMap.get("subjectName"),"");
		String subClass = StringUtils.defaultString((String)paramMap.get("subClass"),"");
		String nttSj = StringUtils.defaultString((String)paramMap.get("nttSj"),"");
		String nttCn = StringUtils.defaultString((String)paramMap.get("nttCn"),"");
		String ntceBgndeView = StringUtils.defaultString((String)paramMap.get("ntceBgndeView"),"");
		String ntceEnddeView = StringUtils.defaultString((String)paramMap.get("ntceEnddeView"),"");
		String topNoticeYn = StringUtils.defaultString((String)paramMap.get("topNoticeYn"),"");
		serviceAreaUrl = serviceArea;

		CommonCodeVO codeVO = new CommonCodeVO();

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		/*if( null == user ){ // 방문자 정보로 셋팅함.( 로그인 하지않고 게시판 작업을 하는경우에 필요함. )
			user = new LoginVO();
			user.setUniqId("0000MEMB0000000");
			user.setId("GUEST");
			user.setName("방문자");
		}*/

		BoardMasterVO bdMstr = new BoardMasterVO();

		if (isAuthenticated) {

			BoardMasterVO vo = new BoardMasterVO();
			vo.setBbsId(boardVO.getBbsId());
			vo.setUniqId(user.getUniqId());

			bdMstr = bbsAttrbService.selectBBSMasterInf(vo);
			model.addAttribute("bdMstr", bdMstr);
			model.addAttribute("lectureMenuMarkYn", lectureMenuMarkYn);
			model.addAttribute("subjectTraningtype", subjectTraningtype);
			model.addAttribute("subjectName", subjectName);
			model.addAttribute("categoryCd", categoryCd);
			model.addAttribute("subClass", subClass);
			model.addAttribute("nttSj", nttSj);
			model.addAttribute("nttCn", nttCn);
			model.addAttribute("ntceBgndeView", ntceBgndeView);
			model.addAttribute("ntceEnddeView", ntceEnddeView);
			model.addAttribute("topNoticeYn", topNoticeYn);

			//사용자 게시판 일떼
			if("lu".equals(serviceAreaUrl)){
				// 전체 커뮤니티 게시판일떼
				if("N".equals(lectureMenuMarkYn)){
					List<CommonCodeVO> selectMyPrtSubjectTypeCodeList = null;
					List<CommonCodeVO> prtSubjectCodeList = null;
					List<CommonCodeVO> prtClassCodeList = null;

					//담당교수일떼
					if("PRT".equals(user.getMemType())){
						//과정공지 일떼
						if("02".equals(categoryCd)){
							codeVO.setCodeGroup(subjectTraningtype);
							codeVO.setSessionMemSeq(user.getUniqId());
							selectMyPrtSubjectTypeCodeList = commonCodeService.selectMyPrtSubjectTypeCodeList(codeVO);

							model.addAttribute("prtSubjectTypeCode", selectMyPrtSubjectTypeCodeList);
						} else {
						//전체공지 일떼
							selectMyPrtSubjectTypeCodeList = null;
							prtSubjectCodeList = null;
							prtClassCodeList = null;
						}

						//과정공지 일떼
						if("02".equals(categoryCd) && !"".equals(subjectTraningtype)){
							//개설강좌코드 조회
							codeVO.setCodeGroup(subjectTraningtype);
							codeVO.setSessionMemSeq(user.getUniqId());
							prtSubjectCodeList = commonCodeService.selectMyPrtSubjectCodeList(codeVO);

							model.addAttribute("prtSubjectCode", prtSubjectCodeList);

							//개설강좌코드에 따른 분반코드
							if(!"".equals(subjectName)){
								String yyyy = "";
								String term = "";
								String subjectCode = "";
								boardVO.setSubjectNames(subjectName.split("-"));

								for( int idx = 0 ; idx < boardVO.getSubjectNames().length ; idx++ ) {
									if(idx == 0){
										yyyy = boardVO.getSubjectNames()[idx];
									}
									if(idx == 1){
										term = boardVO.getSubjectNames()[idx];
									}
									if(idx == 2){
										subjectCode = boardVO.getSubjectNames()[idx];
									}
								}

								//개설강좌 분반코드 조회
								codeVO.setSessionYyyy(yyyy);
								codeVO.setSessionTerm(term);
								codeVO.setSessionSubjectCode(subjectCode);
								prtClassCodeList = commonCodeService.selectMyPrtClassCodeList(codeVO);

								model.addAttribute("prtClassCode", prtClassCodeList);
							}
						}
					} else if("COT".equals(user.getMemType())){ //기업현장교사
							//과정공지 일떼
							if("02".equals(categoryCd)){

								//개설강좌코드 조회
								subjectTraningtype = "OJT";
								codeVO.setCodeGroup(subjectTraningtype);
								codeVO.setSessionMemSeq(user.getUniqId());
								prtSubjectCodeList = commonCodeService.selectMyCotSubjectCodeList(codeVO);

								model.addAttribute("prtSubjectCode", prtSubjectCodeList);

								//개설강좌코드에 따른 분반코드
								if(!"".equals(subjectName)){
									String yyyy = "";
									String term = "";
									String subjectCode = "";
									boardVO.setSubjectNames(subjectName.split("-"));

									for( int idx = 0 ; idx < boardVO.getSubjectNames().length ; idx++ ) {
										if(idx == 0){
											yyyy = boardVO.getSubjectNames()[idx];
										}
										if(idx == 1){
											term = boardVO.getSubjectNames()[idx];
										}
										if(idx == 2){
											subjectCode = boardVO.getSubjectNames()[idx];
										}
									}

									//개설강좌 분반코드 조회
									codeVO.setSessionYyyy(yyyy);
									codeVO.setSessionTerm(term);
									codeVO.setSessionSubjectCode(subjectCode);
									prtClassCodeList = commonCodeService.selectMyCotClassCodeList(codeVO);

									model.addAttribute("prtClassCode", prtClassCodeList);
								}
							}
						}else if("CDP".equals(user.getMemType())){ //학과담당자
							//과정공지 일떼
							if("02".equals(categoryCd)){

								//개설강좌코드 조회
								subjectTraningtype = "OFF";
								codeVO.setCodeGroup(subjectTraningtype);
								codeVO.setSessionMemSeq(user.getUniqId());
								prtSubjectCodeList = commonCodeService.selectMyCdpSubjectCodeList(codeVO);

								model.addAttribute("prtSubjectCode", prtSubjectCodeList);

								//개설강좌코드에 따른 분반코드
								if(!"".equals(subjectName)){
									String yyyy = "";
									String term = "";
									String subjectCode = "";
									boardVO.setSubjectNames(subjectName.split("-"));

									for( int idx = 0 ; idx < boardVO.getSubjectNames().length ; idx++ ) {
										if(idx == 0){
											yyyy = boardVO.getSubjectNames()[idx];
										}
										if(idx == 1){
											term = boardVO.getSubjectNames()[idx];
										}
										if(idx == 2){
											subjectCode = boardVO.getSubjectNames()[idx];
										}
									}

									//개설강좌 분반코드 조회
									codeVO.setSessionYyyy(yyyy);
									codeVO.setSessionTerm(term);
									codeVO.setSessionSubjectCode(subjectCode);
									prtClassCodeList = commonCodeService.selectMyCdpClassCodeList(codeVO);

									model.addAttribute("prtClassCode", prtClassCodeList);
								}
							}
						} else if("STD".equals(user.getMemType())){ //학습근로자
							//과정공지 일떼
							if("02".equals(categoryCd)){
								codeVO.setCodeGroup(subjectTraningtype);
								codeVO.setSessionMemSeq(user.getUniqId());
								selectMyPrtSubjectTypeCodeList = commonCodeService.selectMyStdSubjectTypeCodeList(codeVO);

								model.addAttribute("prtSubjectTypeCode", selectMyPrtSubjectTypeCodeList);
							} else {
							//전체공지 일떼
								selectMyPrtSubjectTypeCodeList = null;
								prtSubjectCodeList = null;
								prtClassCodeList = null;
							}

							//과정공지 일떼
							if("02".equals(categoryCd) && !"".equals(subjectTraningtype)){
								//개설강좌코드 조회
								codeVO.setCodeGroup(subjectTraningtype);
								codeVO.setSessionMemSeq(user.getUniqId());
								prtSubjectCodeList = commonCodeService.selectMyStdSubjectCodeList(codeVO);

								model.addAttribute("prtSubjectCode", prtSubjectCodeList);

								//개설강좌코드에 따른 분반코드
								if(!"".equals(subjectName)){
									String yyyy = "";
									String term = "";
									String subjectCode = "";
									boardVO.setSubjectNames(subjectName.split("-"));

									for( int idx = 0 ; idx < boardVO.getSubjectNames().length ; idx++ ) {
										if(idx == 0){
											yyyy = boardVO.getSubjectNames()[idx];
										}
										if(idx == 1){
											term = boardVO.getSubjectNames()[idx];
										}
										if(idx == 2){
											subjectCode = boardVO.getSubjectNames()[idx];
										}
									}

									//개설강좌 분반코드 조회
									codeVO.setSessionYyyy(yyyy);
									codeVO.setSessionTerm(term);
									codeVO.setSessionSubjectCode(subjectCode);
									prtClassCodeList = commonCodeService.selectMyStdClassCodeList(codeVO);

									model.addAttribute("prtClassCode", prtClassCodeList);
								}
							}
						}

				} else {
					//개별 커뮤니티 게시판 일떼
				}
			}
		}

		// ----------------------------
		// 기본 BBS template 지정
		// ----------------------------
		//if (bdMstr.getTmplatCours() == null || bdMstr.getTmplatCours().equals("")) {
			//bdMstr.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
		//}

		logger.debug("################################# ");
		logger.debug("yyyy : " + boardVO.getYyyy());
		logger.debug("term : " + boardVO.getTerm());
		logger.debug("subjectCode : " + boardVO.getSubjectCode());
		logger.debug("subClass : " + boardVO.getSubClass());
		logger.debug("################################# ");
		logger.debug("user.getId() : " + user.getId());
		logger.debug("user.getName() : " + user.getName());
		logger.debug("user.getUniqId() : " + user.getUniqId());
		logger.debug("memType : " + user.getMemType());
		logger.debug("################################# ");

		if( "lu".equals(serviceArea) && "Y".equals(lectureMenuMarkYn)){
			model.addAttribute("yyyy", boardVO.getYyyy());
			model.addAttribute("term", boardVO.getTerm());
			model.addAttribute("subjectCode", boardVO.getSubjectCode());
			model.addAttribute("subClass", boardVO.getSubClass());
			model.addAttribute("memType", user.getMemType());

			CurrProcVO currProcVO = new CurrProcVO();
			CurrProcVO currProcReadVO = new CurrProcVO();

			currProcVO.setYyyy(boardVO.getYyyy());
			currProcVO.setTerm(boardVO.getTerm());
			currProcVO.setSubjectCode(boardVO.getSubjectCode());
			currProcVO.setSubClass(boardVO.getSubClass());

			currProcReadVO = currProcService.getMySubjectInfo(currProcVO);
			model.addAttribute("currProcReadVO", currProcReadVO);
		}

		// //-----------------------------
		model.addAttribute("memType", user.getMemType());

		if("la".equals(serviceAreaUrl)){
			retrunUrl = "oklms/la/egovframework/com/cop/bbs/EgovNoticeRegist";
		}else{
			if("N".equals(lectureMenuMarkYn)){
				retrunUrl = "oklms/lu/egovframework/com/cop/bbs/EgovAllSubjectNoticeRegist";
			}else{
				retrunUrl = "oklms/lu/egovframework/com/cop/bbs/EgovSubjectNoticeRegist";
			}
		}

		return retrunUrl;
		// return "oklms/commbiz/bbs/EgovNoticeRegist";
	}

	/**
	 * 게시물을 등록한다.
	 *
	 * @param boardVO
	 * @param board
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/{pathBbsId}/insertBoardArticle.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/insertBoardArticle.do")
	public String insertBoardArticle(@RequestParam Map<String, Object> paramMap, @PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO boardVO,
			@ModelAttribute("bdMstr") BoardMaster bdMstr, @ModelAttribute("board") Board board, BindingResult bindingResult,
			SessionStatus status, ModelMap model) throws Exception {

		String fileInput = "";
		String lectureMenuMarkYn = "";
		String categoryCd = "";
		String subjectTraningtype = "";
		String subjectName = "";
		String posblAtchFileNumber = "";

		if("lu".equals(serviceArea)){
			fileInput = multiRequest.getFiles("file-input").get(0).getOriginalFilename();
			lectureMenuMarkYn = StringUtils.defaultString((String)paramMap.get("lectureMenuMarkYn"),"N");
			categoryCd = StringUtils.defaultString((String)paramMap.get("categoryCd"),"");
			subjectTraningtype = StringUtils.defaultString((String)paramMap.get("subjectTraningtype"),"");
			subjectName = StringUtils.defaultString((String)paramMap.get("subjectName"),"");
			posblAtchFileNumber = StringUtils.defaultString((String)paramMap.get("posblAtchFileNumber"),"");
		}
		CommonCodeVO codeVO = new CommonCodeVO();

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		System.out.println("user.getId : "+user.getId());
		System.out.println("user.getName : "+user.getName());

		/*if( null == user ){ // 방문자 정보로 셋팅함.( 로그인 하지않고 게시판 작업을 하는경우에 필요함. )
			user = new LoginVO();
			user.setUniqId("0000MEMB0000000");
			user.setId("GUEST");
			user.setName("방문자");
		}*/

		beanValidator.validate(board, bindingResult);
		if (bindingResult.hasErrors()) {

			BoardMasterVO master = new BoardMasterVO();
			BoardMasterVO vo = new BoardMasterVO();

			vo.setBbsId(boardVO.getBbsId());
			vo.setUniqId(user.getUniqId());

			master = bbsAttrbService.selectBBSMasterInf(vo);

			model.addAttribute("bdMstr", master);

			// ----------------------------
			// 기본 BBS template 지정
			// ----------------------------
			if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
				master.setTmplatCours("css/egovframework/com/cop/tpl/egovBaseTemplate.css");
			}

			model.addAttribute("brdMstrVO", master);
			// //-----------------------------

			return "oklms/" + serviceArea + "/egovframework/com/cop/bbs/EgovNoticeRegist";
			// return "oklms/commbiz/bbs/EgovNoticeRegist";
		}

		//관리자용 게시판일떼
		if("la".equals(serviceArea)){
			if (isAuthenticated) {
				List<FileVO> result = null;
				String atchFileId = "";

				final Map<String, MultipartFile> files = multiRequest.getFileMap();
				if (!files.isEmpty()) {
					result = fileUtil.parseFileInf(files, "BBS_", 0, "", "");
					atchFileId = fileMngService.insertFileInfs(result);
				}
				board.setAtchFileId(atchFileId);
				board.setFrstRegisterId(user.getUniqId());
				board.setBbsId(board.getBbsId());

				// board.setNtcrNm(""); // dummy 오류 수정 (익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨)
				board.setPassword(""); // dummy 오류 수정 (익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨)

				board.setNtcrId(user.getId()); // 게시물 통계 집계를 위해 등록자 ID 저장
				board.setNtcrNm(user.getName()); // 게시물 통계 집계를 위해 등록자 Name 저장

//				<div id="__KO_DIC_LAYER__"
				if( -1 < (board.getNttCn()).indexOf( "_DIC_LAYER_") ){

					model.addAttribute("retMsg", "사전 플러그인 기능을 끄고 작업하여야 합니다!!");
				}else{

					board.setNttCn(unscript(board.getNttCn())); // XSS 방지

					bbsMngService.insertBoardArticle(board);
				}
			}
		}else{
		//사용자용 게시판일떼
			if (isAuthenticated) {
				//List<FileVO> result = null;
				String strStorePath ="Globals.fileStorePath";
				String atchFileIdStd = "";

				if("1".equals(posblAtchFileNumber)){
					// 첨부파일 갯수가 1일떼 사용
					System.out.println("fileInput ==> "+fileInput);
					if(!"".equals(fileInput)){
						final List< MultipartFile > fileObj = multiRequest.getFiles("file-input");
						atchFileIdStd = atchFileService.saveAtchFile( fileObj, "BBS_", "", strStorePath );
					}
				}else{
					/*
					 //첨부파일 갯수가 1이상일떼 사용
					 final Map<String, MultipartFile> files = multiRequest.getFileMap();
					if (!files.isEmpty()) {
						result = fileUtil.parseFileInf(files, "BBS_", 0, "", "");
						atchFileId = fileMngService.insertFileInfs(result);
					}*/
				}

				board.setAtchFileId(atchFileIdStd);
				board.setFrstRegisterId(user.getUniqId());
				board.setBbsId(board.getBbsId());

				board.setPassword(""); // dummy 오류 수정 (익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨)
				board.setNtcrId(user.getId()); // 게시물 통계 집계를 위해 등록자 ID 저장
				board.setNtcrNm(user.getName()); // 게시물 통계 집계를 위해 등록자 Name 저장

//				<div id="__KO_DIC_LAYER__"
				if( -1 < (board.getNttCn()).indexOf( "_DIC_LAYER_") ){

					model.addAttribute("retMsg", "사전 플러그인 기능을 끄고 작업하여야 합니다!!");
				}else{

					board.setNttCn(unscript(board.getNttCn())); // XSS 방지

					// 전체 커뮤니티 게시판일떼
					if("N".equals(lectureMenuMarkYn)){
						codeVO.setSessionMemSeq(user.getMemSeq());
						board.setSubjectTraningType(subjectTraningtype);

						//전체 과정공지 일떼
						if("01".equals(categoryCd)){
							//담당교수일떼
							if("PRT".equals(user.getMemType())){
								codeVO.setCodeGroup("OFF");
								List<CommonCodeVO> resultList1 = commonCodeService.selectMyPrtSubjectList(codeVO);
								for(int i = 0; i<resultList1.size(); i++ ){
									board.setYyyy(resultList1.get(i).getYyyy());
									board.setTerm(resultList1.get(i).getTerm());
									board.setSubjectCode(resultList1.get(i).getSubjectCode());
									board.setSubClass(resultList1.get(i).getSubClass());
									board.setSubjectTraningType("OFF");

									bbsMngService.insertBoardArticle(board);
								}

								codeVO.setCodeGroup("OJT");
								List<CommonCodeVO> resultList2 = commonCodeService.selectMyPrtSubjectList(codeVO);
								for(int i = 0; i<resultList2.size(); i++ ){
									board.setYyyy(resultList2.get(i).getYyyy());
									board.setTerm(resultList2.get(i).getTerm());
									board.setSubjectCode(resultList2.get(i).getSubjectCode());
									board.setSubClass(resultList2.get(i).getSubClass());
									board.setSubjectTraningType("OJT");

									bbsMngService.insertBoardArticle(board);
								}
							} else if("COT".equals(user.getMemType())){ //기업현장교사일떼
								List<CommonCodeVO> resultList2 = commonCodeService.selectMyCotSubjectList(codeVO);
								for(int i = 0; i<resultList2.size(); i++ ){
									board.setYyyy(resultList2.get(i).getYyyy());
									board.setTerm(resultList2.get(i).getTerm());
									board.setSubjectCode(resultList2.get(i).getSubjectCode());
									board.setSubClass(resultList2.get(i).getSubClass());
									board.setSubjectTraningType("OJT");

									bbsMngService.insertBoardArticle(board);
								}
							} else if("CDP".equals(user.getMemType())){ //학과전담자일떼
								List<CommonCodeVO> resultList1 = commonCodeService.selectMyCdpSubjectList(codeVO);
								for(int i = 0; i<resultList1.size(); i++ ){
									board.setYyyy(resultList1.get(i).getYyyy());
									board.setTerm(resultList1.get(i).getTerm());
									board.setSubjectCode(resultList1.get(i).getSubjectCode());
									board.setSubClass(resultList1.get(i).getSubClass());
									board.setSubjectTraningType("OFF");

									bbsMngService.insertBoardArticle(board);
								}
							}

						} else {
						//과정공지 일떼
							String yyyy = "";
							String term = "";
							String subjectCode = "";
							boardVO.setSubjectNames(subjectName.split("-"));

							for( int idx = 0 ; idx < boardVO.getSubjectNames().length ; idx++ ) {
								if(idx == 0){
									yyyy = boardVO.getSubjectNames()[idx];
								}
								if(idx == 1){
									term = boardVO.getSubjectNames()[idx];
								}
								if(idx == 2){
									subjectCode = boardVO.getSubjectNames()[idx];
								}
							}

							board.setYyyy(yyyy);
							board.setTerm(term);
							board.setSubjectCode(subjectCode);

							//특정 개설교과 과정공지 일떼
							bbsMngService.insertBoardArticle(board);
						}
					} else {
					// 개별 개설강좌 커뮤니티 게시판일떼
						bbsMngService.insertBoardArticle(board);
					}
				}
			}
		}


		// status.setComplete();
		return "forward:/" + serviceArea + "/cop/bbs/" + pathBbsId + "/selectBoardList.do";
	}

	/**
	 * 게시물에 대한 답변 등록을 위한 등록페이지로 이동한다.
	 * 관리자용
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/{pathBbsId}/addReplyBoardArticle.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/addReplyBoardArticle.do")
	public String addReplyBoardArticle(@RequestParam Map<String, Object> paramMap, @PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		/*if( null == user ){ // 방문자 정보로 셋팅함.( 로그인 하지않고 게시판 작업을 하는경우에 필요함. )
			user = new LoginVO();
			user.setUniqId("0000MEMB0000000");
			user.setId("GUEST");
			user.setName("방문자");
		}*/

		BoardMasterVO master = new BoardMasterVO();
		BoardMasterVO vo = new BoardMasterVO();

		vo.setBbsId(boardVO.getBbsId());
		vo.setUniqId(user.getUniqId());

		master = bbsAttrbService.selectBBSMasterInf(vo);

		model.addAttribute("bdMstr", master);
		model.addAttribute("result", boardVO);

		// ----------------------------
		// 기본 BBS template 지정
		// ----------------------------
		if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
			master.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
		}

		model.addAttribute("brdMstrVO", master);

		// //-----------------------------

		logger.debug("################################# ");
		logger.debug("yyyy : " + boardVO.getYyyy());
		logger.debug("term : " + boardVO.getTerm());
		logger.debug("subjectCode : " + boardVO.getSubjectCode());
		logger.debug("subClass : " + boardVO.getSubClass());
		logger.debug("################################# ");
		logger.debug("user.getId() : " + user.getId());
		logger.debug("user.getName() : " + user.getName());
		logger.debug("user.getUniqId() : " + user.getUniqId());
		logger.debug("memType : " + user.getMemType());
		logger.debug("################################# ");

		/*if( "lu".equals(serviceArea) && "Y".equals(lectureMenuMarkYn)){
			model.addAttribute("yyyy", boardVO.getYyyy());
			model.addAttribute("term", boardVO.getTerm());
			model.addAttribute("subjectCode", boardVO.getSubjectCode());
			model.addAttribute("subClass", boardVO.getSubClass());
			model.addAttribute("memType", user.getMemType());
		}*/

		String retrunUrl = "oklms/la/egovframework/com/cop/bbs/EgovNoticeReply";

		return retrunUrl;
		// return "oklms/commbiz/bbs/EgovNoticeReply";
	}

	/**
	 * 게시물에 대한 답변 등록을 위한 등록페이지로 이동한다.
	 * 사용자용
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/{pathBbsId}/addBoardArticleReply.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/addReplyBoardArticle.do")
	public String addBoardArticleReply(@RequestParam Map<String, Object> paramMap, @PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {

		String retrunUrl = "";
		String serviceAreaUrl = "";
		String yyyy = "";
		String term = "";
		String subjectCode = "";
		String lectureMenuMarkYn = StringUtils.defaultString((String)paramMap.get("lectureMenuMarkYn"),"N");
		String categoryCd = StringUtils.defaultString((String)paramMap.get("categoryCd"),"");
		String subjectTraningtype = StringUtils.defaultString((String)paramMap.get("subjectTraningtype"),"");
		String subjectName = StringUtils.defaultString((String)paramMap.get("subjectName"),"");
		String subClass = StringUtils.defaultString((String)paramMap.get("subClass"),"");
		String nttSj = StringUtils.defaultString((String)paramMap.get("nttSj"),"");
		String nttCn = StringUtils.defaultString((String)paramMap.get("nttCn"),"");
		String ntceBgndeView = StringUtils.defaultString((String)paramMap.get("ntceBgndeView"),"");
		String ntceEnddeView = StringUtils.defaultString((String)paramMap.get("ntceEnddeView"),"");
		String topNoticeYn = StringUtils.defaultString((String)paramMap.get("topNoticeYn"),"");
		serviceAreaUrl = serviceArea;

		CommonCodeVO codeVO = new CommonCodeVO();

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		boardVO.setFrstRegisterId(user.getUniqId());

		/*if( null == user ){ // 방문자 정보로 셋팅함.( 로그인 하지않고 게시판 작업을 하는경우에 필요함. )
			user = new LoginVO();
			user.setUniqId("0000MEMB0000000");
			user.setId("GUEST");
			user.setName("방문자");
		}*/

		BoardMasterVO master = new BoardMasterVO();
		BoardMasterVO vo = new BoardMasterVO();
		BoardVO bdvo = new BoardVO();

		vo.setBbsId(boardVO.getBbsId());
		vo.setUniqId(user.getUniqId());

		master = bbsAttrbService.selectBBSMasterInf(vo);
		bdvo = bbsMngService.selectBoardArticle(boardVO);

		model.addAttribute("bdMstr", master);
		model.addAttribute("result", boardVO);

		// ----------------------------
		// 기본 BBS template 지정
		// ----------------------------
		if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
			master.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
		}

		model.addAttribute("brdMstrVO", master);
		model.addAttribute("lectureMenuMarkYn", lectureMenuMarkYn);
		if("BBSMSTR_000000000007".equals(pathBbsId)){
			model.addAttribute("subjectTraningtype", bdvo.getSubjectTraningType());
		}else{
			model.addAttribute("subjectTraningtype", subjectTraningtype);
		}
		model.addAttribute("subjectName", subjectName);
		model.addAttribute("categoryCd", categoryCd);
		model.addAttribute("subClass", subClass);
		model.addAttribute("nttSj", nttSj);
		model.addAttribute("nttCn", nttCn);
		model.addAttribute("ntceBgndeView", ntceBgndeView);
		model.addAttribute("ntceEnddeView", ntceEnddeView);
		model.addAttribute("topNoticeYn", topNoticeYn);

		//사용자 게시판 일떼
		if("lu".equals(serviceAreaUrl)){
			// 전체 커뮤니티 게시판일떼
			if("N".equals(lectureMenuMarkYn)){
				List<CommonCodeVO> selectMyPrtSubjectTypeCodeList = null;
				List<CommonCodeVO> prtSubjectCodeList = null;
				List<CommonCodeVO> prtClassCodeList = null;

				//과정공지 일떼
				if("02".equals(categoryCd) || "02".equals(bdvo.getCategoryCd())){
					if(!"".equals(subjectTraningtype)){
						codeVO.setCodeGroup(subjectTraningtype);
					}else{
						codeVO.setCodeGroup(bdvo.getSubjectTraningType());
					}
					codeVO.setSessionMemSeq(user.getUniqId());
					selectMyPrtSubjectTypeCodeList = commonCodeService.selectMyPrtSubjectTypeCodeList(codeVO);

					model.addAttribute("prtSubjectTypeCode", selectMyPrtSubjectTypeCodeList);
				} else {
					selectMyPrtSubjectTypeCodeList = null;
					prtSubjectCodeList = null;
					prtClassCodeList = null;
				}

				//과정구분이 null 아닐떼
				if(("02".equals(categoryCd) && !"".equals(subjectTraningtype))
				|| ("02".equals(bdvo.getCategoryCd()) && !"".equals(bdvo.getSubjectTraningType()))){

					//개설강좌코드 조회
					if(!"".equals(subjectTraningtype)){
						codeVO.setCodeGroup(subjectTraningtype);
					}else{
						codeVO.setCodeGroup(bdvo.getSubjectTraningType());
					}
					codeVO.setSessionMemSeq(user.getUniqId());
					prtSubjectCodeList = commonCodeService.selectMyPrtSubjectCodeList(codeVO);

					model.addAttribute("prtSubjectCode", prtSubjectCodeList);

					//개설강좌코드에 따른 분반코드
					if(!"".equals(subjectName) || !"".equals(bdvo.getSubjectName())){
						if(!"".equals(subjectName)){
							boardVO.setSubjectNames(subjectName.split("-"));

							for( int idx = 0 ; idx < boardVO.getSubjectNames().length ; idx++ ) {
								if(idx == 0){
									yyyy = boardVO.getSubjectNames()[idx];
								}
								if(idx == 1){
									term = boardVO.getSubjectNames()[idx];
								}
								if(idx == 2){
									subjectCode = boardVO.getSubjectNames()[idx];
								}
							}

							//개설강좌 분반코드 조회
							codeVO.setSessionYyyy(yyyy);
							codeVO.setSessionTerm(term);
							codeVO.setSessionSubjectCode(subjectCode);

							prtClassCodeList = commonCodeService.selectMyPrtClassCodeList(codeVO);
							model.addAttribute("prtClassCode", prtClassCodeList);
						}else{
							//개설강좌 분반코드 조회
							codeVO.setSessionYyyy(bdvo.getYyyy());
							codeVO.setSessionTerm(bdvo.getTerm());
							codeVO.setSessionSubjectCode(bdvo.getSubjectCode());

							prtClassCodeList = commonCodeService.selectMyPrtClassCodeList(codeVO);
							model.addAttribute("prtClassCode", prtClassCodeList);
						}
					}
				}
			} else {
				//개별 커뮤니티 게시판 일떼
			}
		}

		// //-----------------------------

		model.addAttribute("bdvo", bdvo);

		logger.debug("################################# ");
		logger.debug("yyyy : " + boardVO.getYyyy());
		logger.debug("term : " + boardVO.getTerm());
		logger.debug("subjectCode : " + boardVO.getSubjectCode());
		logger.debug("subClass : " + boardVO.getSubClass());
		logger.debug("################################# ");
		logger.debug("user.getId() : " + user.getId());
		logger.debug("user.getName() : " + user.getName());
		logger.debug("user.getUniqId() : " + user.getUniqId());
		logger.debug("memType : " + user.getMemType());
		logger.debug("################################# ");

		if( "lu".equals(serviceArea) && "Y".equals(lectureMenuMarkYn)){
			model.addAttribute("yyyy", boardVO.getYyyy());
			model.addAttribute("term", boardVO.getTerm());
			model.addAttribute("subjectCode", boardVO.getSubjectCode());
			model.addAttribute("subClass", boardVO.getSubClass());
			model.addAttribute("memType", user.getMemType());

			CurrProcVO currProcVO = new CurrProcVO();
			CurrProcVO currProcReadVO = new CurrProcVO();

			currProcVO.setYyyy(boardVO.getYyyy());
			currProcVO.setTerm(boardVO.getTerm());
			currProcVO.setSubjectCode(boardVO.getSubjectCode());
			currProcVO.setSubClass(boardVO.getSubClass());

			currProcReadVO = currProcService.getMySubjectInfo(currProcVO);
			model.addAttribute("currProcReadVO", currProcReadVO);
		}

		if("la".equals(serviceAreaUrl)){
			retrunUrl = "oklms/la/egovframework/com/cop/bbs/EgovNoticeReply";
		}else{
			if("N".equals(lectureMenuMarkYn)){
				retrunUrl = "oklms/lu/egovframework/com/cop/bbs/EgovAllSubjectReply";
			}else{
				retrunUrl = "oklms/lu/egovframework/com/cop/bbs/EgovSubjectReply";
			}
		}

		return retrunUrl;
		// return "oklms/commbiz/bbs/EgovNoticeReply";
	}

	/**
	 * 게시물에 대한 답변을 등록한다.
	 * 관리자용
	 * @param boardVO
	 * @param board
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/{pathBbsId}/replyBoardArticle.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/replyBoardArticle.do")
	public String replyBoardArticle(@PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO boardVO,
			@ModelAttribute("bdMstr") BoardMaster bdMstr, @ModelAttribute("board") Board board, BindingResult bindingResult,
			ModelMap model, SessionStatus status) throws Exception {

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);


		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		/*if( null == user ){ // 방문자 정보로 셋팅함.( 로그인 하지않고 게시판 작업을 하는경우에 필요함. )
			user = new LoginVO();
			user.setUniqId("0000MEMB0000000");
			user.setId("GUEST");
			user.setName("방문자");
		}*/

		beanValidator.validate(board, bindingResult);
		if (bindingResult.hasErrors()) {
			BoardMasterVO master = new BoardMasterVO();
			BoardMasterVO vo = new BoardMasterVO();

			vo.setBbsId(boardVO.getBbsId());
			vo.setUniqId(user.getUniqId());

			master = bbsAttrbService.selectBBSMasterInf(vo);

			model.addAttribute("bdMstr", master);
			model.addAttribute("result", boardVO);

			// ----------------------------
			// 기본 BBS template 지정
			// ----------------------------
			if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
				master.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
			}

			model.addAttribute("brdMstrVO", master);
			// //-----------------------------

			return "oklms/" + serviceArea + "/egovframework/com/cop/bbs/EgovNoticeReply";
			// return "oklms/commbiz/bbs/EgovNoticeReply";
		}

		if (isAuthenticated) {
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			String atchFileId = "";

			if (!files.isEmpty()) {
				List<FileVO> result = fileUtil.parseFileInf(files, "BBS_", 0, "", "");
				atchFileId = fileMngService.insertFileInfs(result);
			}

			board.setAtchFileId(atchFileId);
			board.setReplyAt("Y");
			board.setFrstRegisterId(user.getUniqId());
			board.setBbsId(board.getBbsId());
			board.setParnts(Long.toString(boardVO.getNttId()));
			board.setSortOrdr(boardVO.getSortOrdr());
			board.setReplyLc(Integer.toString(Integer.parseInt(boardVO.getReplyLc()) + 1));

			board.setPassword(""); // dummy 오류 수정 (익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨)
			board.setNtcrId(user.getId()); // 게시물 통계 집계를 위해 등록자 ID 저장
			board.setNtcrNm(user.getName()); // 게시물 통계 집계를 위해 등록자 Name 저장

			board.setNttCn(unscript(board.getNttCn())); // XSS 방지

			bbsMngService.insertBoardArticle(board);
		}

		return "forward:/" + serviceArea + "/cop/bbs/" + pathBbsId + "/selectBoardList.do";
	}

	/**
	 * 게시물에 대한 답변을 등록한다.
	 * 사용자용
	 * @param boardVO
	 * @param board
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/{pathBbsId}/insertBoardArticleReply.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/replyBoardArticle.do")
	public String insertBoardArticleReply(@RequestParam Map<String, Object> paramMap, @PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO boardVO,
			@ModelAttribute("bdMstr") BoardMaster bdMstr, @ModelAttribute("board") Board board, BindingResult bindingResult,
			ModelMap model, SessionStatus status) throws Exception {

		String fileInput = "";
		String lectureMenuMarkYn = StringUtils.defaultString((String)paramMap.get("lectureMenuMarkYn"),"N");
		String categoryCd = StringUtils.defaultString((String)paramMap.get("categoryCd"),"");
		String subjectTraningtype = StringUtils.defaultString((String)paramMap.get("subjectTraningtype"),"");
		String subjectName = StringUtils.defaultString((String)paramMap.get("subjectName"),"");
		String posblAtchFileNumber = StringUtils.defaultString((String)paramMap.get("posblAtchFileNumber"),"");
		String yyyy = StringUtils.defaultString((String)paramMap.get("yyyy"),"");
		String term = StringUtils.defaultString((String)paramMap.get("term"),"");
		String subjectCode = StringUtils.defaultString((String)paramMap.get("subjectCode"),"");
		String subClass = StringUtils.defaultString((String)paramMap.get("subClass"),"");
		fileInput = multiRequest.getFiles("file-input").get(0).getOriginalFilename();
		CommonCodeVO codeVO = new CommonCodeVO();

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);


		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		/*if( null == user ){ // 방문자 정보로 셋팅함.( 로그인 하지않고 게시판 작업을 하는경우에 필요함. )
			user = new LoginVO();
			user.setUniqId("0000MEMB0000000");
			user.setId("GUEST");
			user.setName("방문자");
		}*/

		beanValidator.validate(board, bindingResult);
		if (bindingResult.hasErrors()) {
			BoardMasterVO master = new BoardMasterVO();
			BoardMasterVO vo = new BoardMasterVO();

			vo.setBbsId(boardVO.getBbsId());
			vo.setUniqId(user.getUniqId());

			master = bbsAttrbService.selectBBSMasterInf(vo);

			model.addAttribute("bdMstr", master);
			model.addAttribute("result", boardVO);

			// ----------------------------
			// 기본 BBS template 지정
			// ----------------------------
			if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
				master.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
			}

			model.addAttribute("brdMstrVO", master);
			// //-----------------------------

			return "oklms/" + serviceArea + "/egovframework/com/cop/bbs/EgovNoticeReply";
			// return "oklms/commbiz/bbs/EgovNoticeReply";
		}

		if (isAuthenticated) {
			/*final Map<String, MultipartFile> files = multiRequest.getFileMap();
			String atchFileId = "";

			if (!files.isEmpty()) {
				List<FileVO> result = fileUtil.parseFileInf(files, "BBS_", 0, "", "");
				atchFileId = fileMngService.insertFileInfs(result);
			}*/

			String atchFileId = "";
			String strStorePath ="Globals.fileStorePath";
			if("1".equals(posblAtchFileNumber)){
				// 첨부파일 갯수가 1일떼 사용
				System.out.println("fileInput ==> "+fileInput);
				if(!"".equals(fileInput)){
					final List< MultipartFile > fileObj = multiRequest.getFiles("file-input");
					atchFileId = atchFileService.saveAtchFile( fileObj, "BBS_", "", strStorePath );
				}
			}else{
				/*
				 //첨부파일 갯수가 1이상일떼 사용
				 final Map<String, MultipartFile> files = multiRequest.getFileMap();
				if (!files.isEmpty()) {
					result = fileUtil.parseFileInf(files, "BBS_", 0, "", "");
					atchFileId = fileMngService.insertFileInfs(result);
				}*/
			}

			board.setAtchFileId(atchFileId);
			board.setReplyAt("Y");
			board.setFrstRegisterId(user.getUniqId());
			board.setBbsId(board.getBbsId());
			board.setParnts(Long.toString(boardVO.getNttId()));
			board.setSortOrdr(boardVO.getSortOrdr());
			board.setReplyLc(Integer.toString(Integer.parseInt(boardVO.getReplyLc()) + 1));

			board.setPassword(""); // dummy 오류 수정 (익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨)
			board.setNtcrId(user.getId()); // 게시물 통계 집계를 위해 등록자 ID 저장
			board.setNtcrNm(user.getName()); // 게시물 통계 집계를 위해 등록자 Name 저장

			board.setNttCn(unscript(board.getNttCn())); // XSS 방지

			board.setYyyy(yyyy);
			board.setTerm(term);
			board.setSubjectCode(subjectCode);
			board.setSubClass(subClass);
			board.setCategoryCd(categoryCd);


			bbsMngService.insertBoardArticle(board);
		}

		return "forward:/" + serviceArea + "/cop/bbs/" + pathBbsId + "/selectBoardList.do";
	}

	/**
	 * 게시물 수정을 위한 수정페이지로 이동한다.
	 *
	 * @param boardVO
	 * @param vo
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/{pathBbsId}/forUpdateBoardArticle.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/forUpdateBoardArticle.do")
	public String selectBoardArticleForUpdt(@RequestParam Map<String, Object> paramMap, @PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("board") BoardVO vo, ModelMap model) throws Exception {

		/*====================================================================
    	* 초기화 영역
    	==============================================================r======*/
		String yyyy	= ""; 				//학년도
	    String term	= ""; 				//학기
	    String subjectCode	= ""; 		//교과목코드
	    //String subjectName	= ""; 	//교과목명
	    String retrunUrl = "";
		String serviceAreaUrl = "";
		String lectureMenuMarkYn = StringUtils.defaultString((String)paramMap.get("lectureMenuMarkYn"),"N");
		String categoryCd = StringUtils.defaultString((String)paramMap.get("categoryCd"),"");
		String subjectTraningtype = StringUtils.defaultString((String)paramMap.get("subjectTraningtype"),"");
		String subjectName = StringUtils.defaultString((String)paramMap.get("subjectName"),"");
		String subClass = StringUtils.defaultString((String)paramMap.get("subClass"),"");
		String nttSj = StringUtils.defaultString((String)paramMap.get("nttSj"),"");
		String nttCn = StringUtils.defaultString((String)paramMap.get("nttCn"),"");
		String ntceBgndeView = StringUtils.defaultString((String)paramMap.get("ntceBgndeView"),"");
		String ntceEnddeView = StringUtils.defaultString((String)paramMap.get("ntceEnddeView"),"");
		String topNoticeYn = StringUtils.defaultString((String)paramMap.get("topNoticeYn"),"");
		serviceAreaUrl = serviceArea;
		AtchFileVO atchFileVO = new AtchFileVO();
		CommonCodeVO codeVO = new CommonCodeVO();

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);

		// log.debug(this.getClass().getName()+"selectBoardArticleForUpdt getNttId "+boardVO.getNttId());
		// log.debug(this.getClass().getName()+"selectBoardArticleForUpdt getBbsId "+boardVO.getBbsId());

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		/*if( null == user ){ // 방문자 정보로 셋팅함.( 로그인 하지않고 게시판 작업을 하는경우에 필요함. )
			user = new LoginVO();
			user.setUniqId("0000MEMB0000000");
			user.setId("GUEST");
			user.setName("방문자");
		}*/

		boardVO.setFrstRegisterId(user.getUniqId());

		BoardMaster master = new BoardMaster();
		BoardMasterVO bmvo = new BoardMasterVO();
		BoardVO bdvo = new BoardVO();

		vo.setBbsId(boardVO.getBbsId());

		master.setBbsId(boardVO.getBbsId());
		master.setUniqId(user.getUniqId());

		if (isAuthenticated) {
			bmvo = bbsAttrbService.selectBBSMasterInf(master);
			bdvo = bbsMngService.selectBoardArticle(boardVO);

			model.addAttribute("lectureMenuMarkYn", lectureMenuMarkYn);

			if("BBSMSTR_000000000007".equals(pathBbsId)){
				model.addAttribute("subjectTraningtype", bdvo.getSubjectTraningType());
			}else{
				model.addAttribute("subjectTraningtype", subjectTraningtype);
			}

			model.addAttribute("subjectName", subjectName);
			model.addAttribute("categoryCd", categoryCd);
			model.addAttribute("subClass", subClass);
			model.addAttribute("nttSj", nttSj);
			model.addAttribute("nttCn", nttCn);
			model.addAttribute("ntceBgndeView", ntceBgndeView);
			model.addAttribute("ntceEnddeView", ntceEnddeView);
			model.addAttribute("topNoticeYn", topNoticeYn);

			//사용자 게시판 일떼
			if("lu".equals(serviceAreaUrl)){
				// 전체 커뮤니티 게시판일떼
				if("N".equals(lectureMenuMarkYn)){
					List<CommonCodeVO> selectMyPrtSubjectTypeCodeList = null;
					List<CommonCodeVO> prtSubjectCodeList = null;
					List<CommonCodeVO> prtClassCodeList = null;

					//과정공지 일떼
					if("02".equals(categoryCd) || "02".equals(bdvo.getCategoryCd())){
						if(!"".equals(subjectTraningtype)){
							codeVO.setCodeGroup(subjectTraningtype);
						}else{
							codeVO.setCodeGroup(bdvo.getSubjectTraningType());
						}
						codeVO.setSessionMemSeq(user.getUniqId());
						selectMyPrtSubjectTypeCodeList = commonCodeService.selectMyPrtSubjectTypeCodeList(codeVO);

						model.addAttribute("prtSubjectTypeCode", selectMyPrtSubjectTypeCodeList);
					} else {
						selectMyPrtSubjectTypeCodeList = null;
						prtSubjectCodeList = null;
						prtClassCodeList = null;
					}

					//과정구분이 null 아닐떼
					if(("02".equals(categoryCd) && !"".equals(subjectTraningtype))
					|| ("02".equals(bdvo.getCategoryCd()) && !"".equals(bdvo.getSubjectTraningType()))){

						//개설강좌코드 조회
						if(!"".equals(subjectTraningtype)){
							codeVO.setCodeGroup(subjectTraningtype);
						}else{
							codeVO.setCodeGroup(bdvo.getSubjectTraningType());
						}
						codeVO.setSessionMemSeq(user.getUniqId());
						prtSubjectCodeList = commonCodeService.selectMyPrtSubjectCodeList(codeVO);

						model.addAttribute("prtSubjectCode", prtSubjectCodeList);

						//개설강좌코드에 따른 분반코드
						if(!"".equals(subjectName) || !"".equals(bdvo.getSubjectName())){
							if(!"".equals(subjectName)){
								boardVO.setSubjectNames(subjectName.split("-"));

								for( int idx = 0 ; idx < boardVO.getSubjectNames().length ; idx++ ) {
									if(idx == 0){
										yyyy = boardVO.getSubjectNames()[idx];
									}
									if(idx == 1){
										term = boardVO.getSubjectNames()[idx];
									}
									if(idx == 2){
										subjectCode = boardVO.getSubjectNames()[idx];
									}
								}

								//개설강좌 분반코드 조회
								codeVO.setSessionYyyy(yyyy);
								codeVO.setSessionTerm(term);
								codeVO.setSessionSubjectCode(subjectCode);

								prtClassCodeList = commonCodeService.selectMyPrtClassCodeList(codeVO);
								model.addAttribute("prtClassCode", prtClassCodeList);
							}else{
								//개설강좌 분반코드 조회
								codeVO.setSessionYyyy(bdvo.getYyyy());
								codeVO.setSessionTerm(bdvo.getTerm());
								codeVO.setSessionSubjectCode(bdvo.getSubjectCode());

								prtClassCodeList = commonCodeService.selectMyPrtClassCodeList(codeVO);
								model.addAttribute("prtClassCode", prtClassCodeList);
							}
						}
					}
				} else {
					//개별 커뮤니티 게시판 일떼
				}
			}

		}

		model.addAttribute("result", bdvo);
		model.addAttribute("bdMstr", bmvo);

		// ----------------------------
		// 기본 BBS template 지정
		// ----------------------------
		if (bmvo.getTmplatCours() == null || bmvo.getTmplatCours().equals("")) {
			bmvo.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
		}

		model.addAttribute("brdMstrVO", bmvo);
		model.addAttribute("lectureMenuMarkYn", lectureMenuMarkYn);
		// //-----------------------------

		logger.debug("################################# ");
		logger.debug("yyyy : " + vo.getYyyy());
		logger.debug("term : " + vo.getTerm());
		logger.debug("subjectCode : " + vo.getSubjectCode());
		logger.debug("subClass : " + vo.getSubClass());
		logger.debug("################################# ");
		logger.debug("user.getId() : " + user.getId());
		logger.debug("user.getName() : " + user.getName());
		logger.debug("user.getUniqId() : " + user.getUniqId());
		logger.debug("memType : " + user.getMemType());
		logger.debug("################################# ");

		model.addAttribute("memType", user.getMemType());

		if( "la".equals(serviceAreaUrl)){
			retrunUrl = "oklms/la/egovframework/com/cop/bbs/EgovNoticeUpdt";
		} else {
			//첨부파일 정보셋팅
			atchFileVO.setFileSn(1);
			System.out.println("atchFileId1 : "+vo.getAtchFileId());
			atchFileVO.setAtchFileId(vo.getAtchFileId().replace(",", ""));
			System.out.println("atchFileId2 : "+atchFileVO.getAtchFileId());
			AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
			model.addAttribute("resultFile", resultFile);
			model.addAttribute("memType", user.getMemType());

			if("N".equals(lectureMenuMarkYn)){
				retrunUrl = "oklms/lu/egovframework/com/cop/bbs/EgovAllSubjectNoticeUpdt";
			} else {
				retrunUrl = "oklms/lu/egovframework/com/cop/bbs/EgovSubjectNoticeUpdt";

				CurrProcVO currProcVO = new CurrProcVO();
				CurrProcVO currProcReadVO = new CurrProcVO();

				currProcVO.setYyyy(vo.getYyyy());
				currProcVO.setTerm(vo.getTerm());
				currProcVO.setSubjectCode(vo.getSubjectCode());
				currProcVO.setSubClass(vo.getSubClass());

				currProcReadVO = currProcService.getMySubjectInfo(currProcVO);
				model.addAttribute("currProcReadVO", currProcReadVO);

				model.addAttribute("yyyy", vo.getYyyy());
				model.addAttribute("term", vo.getTerm());
				model.addAttribute("subjectCode", vo.getSubjectCode());
				model.addAttribute("subClass", vo.getSubClass());
			}
		}

		return retrunUrl;
		// return "oklms/commbiz/bbs/EgovNoticeUpdt";
	}

	/**
	 * 게시물에 대한 내용을 수정한다.
	 *
	 * @param boardVO
	 * @param board
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/{pathBbsId}/updateBoardArticle.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/updateBoardArticle.do")
	public String updateBoardArticle(@RequestParam Map<String, Object> paramMap, @PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO boardVO,
			@ModelAttribute("bdMstr") BoardMaster bdMstr, @ModelAttribute("board") Board board, BindingResult bindingResult,
			ModelMap model, SessionStatus status) throws Exception {

		String serviceAreaUrl = "";
		serviceAreaUrl = serviceArea;
		String lectureMenuMarkYn = "";
		String posblAtchFileNumber = "";
		String tempAtchFileId = "";
		String atchFileId = "";

		if("lu".equals(serviceAreaUrl)){
			lectureMenuMarkYn = StringUtils.defaultString((String)paramMap.get("lectureMenuMarkYn"),"N");
			posblAtchFileNumber = StringUtils.defaultString((String)paramMap.get("posblAtchFileNumber"),"");
			tempAtchFileId = StringUtils.defaultString((String)paramMap.get("atchFileId"),"");

			atchFileId = tempAtchFileId;
		} else {
			atchFileId = boardVO.getAtchFileId();
		}

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		System.out.println("user.getId : "+user.getId());
		System.out.println("user.getName : "+user.getName());

		/*if( null == user ){ // 방문자 정보로 셋팅함.( 로그인 하지않고 게시판 작업을 하는경우에 필요함. )
			user = new LoginVO();
			user.setUniqId("0000MEMB0000000");
			user.setId("GUEST");
			user.setName("방문자");
		}*/


		//String atchFileId = "";

		beanValidator.validate(board, bindingResult);
		if (bindingResult.hasErrors()) {

			boardVO.setFrstRegisterId(user.getUniqId());

			BoardMaster master = new BoardMaster();
			BoardMasterVO bmvo = new BoardMasterVO();
			BoardVO bdvo = new BoardVO();

			master.setBbsId(boardVO.getBbsId());
			master.setUniqId(user.getUniqId());

			bmvo = bbsAttrbService.selectBBSMasterInf(master);
			bdvo = bbsMngService.selectBoardArticle(boardVO);

			model.addAttribute("result", bdvo);
			model.addAttribute("bdMstr", bmvo);

			return "oklms/" + serviceArea + "/egovframework/com/cop/bbs/EgovNoticeUpdt";
			// return "oklms/commbiz/bbs/EgovNoticeUpdt";
		}

		/*
		 * boardVO.setFrstRegisterId(user.getUniqId()); BoardMaster _bdMstr = new BoardMaster(); BoardMasterVO bmvo = new
		 * BoardMasterVO(); BoardVO bdvo = new BoardVO(); vo.setBbsId(boardVO.getBbsId()); _bdMstr.setBbsId(boardVO.getBbsId());
		 * _bdMstr.setUniqId(user.getUniqId());
		 *
		 * if (isAuthenticated) { bmvo = bbsAttrbService.selectBBSMasterInf(_bdMstr); bdvo =
		 * bbsMngService.selectBoardArticle(boardVO); } //
		 */
		if("lu".equals(serviceAreaUrl)){
			if (isAuthenticated) {
				if("lu".equals(serviceAreaUrl)){
					String strStorePath ="Globals.fileStorePath";
					//첨부파일 갯수가 1일떼
					if("1".equals(posblAtchFileNumber)){
						// 첨부파일 갯수가 1일떼 사용
						System.out.println("atchFileId ==> "+atchFileId);
						if("".equals(atchFileId)){
							final List< MultipartFile > fileObj = multiRequest.getFiles("file-input");
							atchFileId = atchFileService.saveAtchFile( fileObj, "BBS_", "", strStorePath );
							board.setAtchFileId(atchFileId);
						}
					}else{
					//첨부파일 갯수가 1이상 일떼

					}
				} else {
					atchFileId = boardVO.getAtchFileId();
					final Map<String, MultipartFile> files = multiRequest.getFileMap();

					if (!files.isEmpty()) {
						if ("".equals(atchFileId)) {
							List<FileVO> result = fileUtil.parseFileInf(files, "BBS_", 0, atchFileId, "");
							atchFileId = fileMngService.insertFileInfs(result);
							board.setAtchFileId(atchFileId);
						} else {
							FileVO fvo = new FileVO();
							fvo.setAtchFileId(atchFileId);
							int cnt = fileMngService.getMaxFileSN(fvo);
							List<FileVO> _result = fileUtil.parseFileInf(files, "BBS_", cnt, atchFileId, "");
							fileMngService.updateFileInfs(_result);
						}
					}
				}


				board.setLastUpdusrId(user.getUniqId());

				board.setPassword(""); // dummy 오류 수정 (익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨)
				board.setNtcrId(user.getId()); // 게시물 통계 집계를 위해 등록자 ID 저장
				board.setNtcrNm(user.getName()); // 게시물 통계 집계를 위해 등록자 Name 저장

//				<div id="__KO_DIC_LAYER__"
				if( -1 < (board.getNttCn()).indexOf( "_DIC_LAYER_") ){

					model.addAttribute("retMsg", "사전 플러그인 기능을 끄고 작업하여야 합니다!!");
				}else{
					board.setNttCn(unscript(board.getNttCn())); // XSS 방지

					bbsMngService.updateBoardArticle(board);
				}
			}
		} else {
			if (isAuthenticated) {
				final Map<String, MultipartFile> files = multiRequest.getFileMap();
				if (!files.isEmpty()) {
					if ("".equals(atchFileId)) {
						List<FileVO> result = fileUtil.parseFileInf(files, "BBS_", 0, atchFileId, "");
						atchFileId = fileMngService.insertFileInfs(result);
						board.setAtchFileId(atchFileId);
					} else {
						FileVO fvo = new FileVO();
						fvo.setAtchFileId(atchFileId);
						int cnt = fileMngService.getMaxFileSN(fvo);
						List<FileVO> _result = fileUtil.parseFileInf(files, "BBS_", cnt, atchFileId, "");
						fileMngService.updateFileInfs(_result);
					}
				}

				board.setLastUpdusrId(user.getUniqId());

				board.setPassword(""); // dummy 오류 수정 (익명이 아닌 경우 validator 처리를 위해 dummy로 지정됨)
				board.setNtcrId(user.getId()); // 게시물 통계 집계를 위해 등록자 ID 저장
				board.setNtcrNm(user.getName()); // 게시물 통계 집계를 위해 등록자 Name 저장

//				<div id="__KO_DIC_LAYER__"
				if( -1 < (board.getNttCn()).indexOf( "_DIC_LAYER_") ){

					model.addAttribute("retMsg", "사전 플러그인 기능을 끄고 작업하여야 합니다!!");
				}else{
					board.setNttCn(unscript(board.getNttCn())); // XSS 방지

					bbsMngService.updateBoardArticle(board);
				}
			}
		}

		return "forward:/" + serviceArea + "/cop/bbs/" + pathBbsId + "/selectBoardList.do";
	}

	/**
	 * 게시물에 대한 내용을 삭제한다.
	 *
	 * @param boardVO
	 * @param board
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/{pathBbsId}/deleteBoardArticle.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/deleteBoardArticle.do")
	public String deleteBoardArticle(@PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("board") Board board,
			@ModelAttribute("bdMstr") BoardMaster bdMstr, ModelMap model) throws Exception {

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);


		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		/*if( null == user ){ // 방문자 정보로 셋팅함.( 로그인 하지않고 게시판 작업을 하는경우에 필요함. )
			user = new LoginVO();
			user.setUniqId("0000MEMB0000000");
			user.setId("GUEST");
			user.setName("방문자");
		}*/

		if (isAuthenticated) {
			board.setLastUpdusrId(user.getUniqId());

			bbsMngService.deleteBoardArticle(board);
		}

		return "forward:/" + serviceArea + "/cop/bbs/" + pathBbsId + "/selectBoardList.do";
	}

	/**
	 * 방명록에 대한 목록을 조회한다.
	 *
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/{pathBbsId}/selectGuestList.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/selectGuestList.do")
	public String selectGuestList(@PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,  @ModelAttribute("searchVO") BoardVO boardVO,
			ModelMap model) throws Exception {

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);


		@SuppressWarnings("unused")
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		/*if( null == user ){ // 방문자 정보로 셋팅함.( 로그인 하지않고 게시판 작업을 하는경우에 필요함. )
			user = new LoginVO();
			user.setUniqId("0000MEMB0000000");
			user.setId("GUEST");
			user.setName("방문자");
		}*/

		// 수정 및 삭제 기능 제어를 위한 처리
		model.addAttribute("sessionUniqId", user.getUniqId());

		BoardVO vo = new BoardVO();

		vo.setBbsId(boardVO.getBbsId());
		vo.setBbsNm(boardVO.getBbsNm());
		vo.setNtcrNm(user.getName());
		vo.setNtcrId(user.getUniqId());

		BoardMasterVO masterVo = new BoardMasterVO();

		masterVo.setBbsId(vo.getBbsId());
		masterVo.setUniqId(user.getUniqId());

		BoardMasterVO mstrVO = bbsAttrbService.selectBBSMasterInf(masterVo);

		vo.setPageUnit(propertyService.getInt("pageUnit"));
		vo.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getPageIndex());
		paginationInfo.setRecordCountPerPage(vo.getPageUnit());
		paginationInfo.setPageSize(vo.getPageSize());

		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = bbsMngService.selectGuestList(vo);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("brdMstrVO", mstrVO);
		model.addAttribute("boardVO", vo);
		model.addAttribute("paginationInfo", paginationInfo);

		return "oklms/" + serviceArea + "/egovframework/com/cop/bbs/EgovGuestList";
		// return "oklms/commbiz/bbs/EgovGuestList";
	}

	/**
	 * 방명록 수정을 위한 특정 내용을 조회한다.
	 *
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/{pathBbsId}/selectSingleGuestList.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/selectSingleGuestList.do")
	public String selectSingleGuestList(@PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("brdMstrVO") BoardMasterVO brdMstrVO, ModelMap model)
			throws Exception {

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);


		@SuppressWarnings("unused")
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		/*if( null == user ){ // 방문자 정보로 셋팅함.( 로그인 하지않고 게시판 작업을 하는경우에 필요함. )
			user = new LoginVO();
			user.setUniqId("0000MEMB0000000");
			user.setId("GUEST");
			user.setName("방문자");
		}*/

		BoardVO vo = bbsMngService.selectBoardArticle(boardVO);

		boardVO.setBbsId(boardVO.getBbsId());
		boardVO.setBbsNm(boardVO.getBbsNm());
		boardVO.setNtcrNm(user.getName());

		boardVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
		paginationInfo.setPageSize(boardVO.getPageSize());

		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = bbsMngService.selectGuestList(boardVO);
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("boardVO", vo);
		model.addAttribute("brdMstrVO", brdMstrVO);
		model.addAttribute("paginationInfo", paginationInfo);

		return "oklms/" + serviceArea + "/egovframework/com/cop/bbs/EgovGuestList";
		// return "oklms/commbiz/bbs/EgovGuestList";
	}

	/**
	 * 방명록에 대한 내용을 삭제한다.
	 *
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/{pathBbsId}/deleteGuestList.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/deleteGuestList.do")
	public String deleteGuestList(@PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,  @ModelAttribute("searchVO") BoardVO boardVO,
			@ModelAttribute("board") Board board, ModelMap model) throws Exception {

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);

		@SuppressWarnings("unused")
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		/*if( null == user ){ // 방문자 정보로 셋팅함.( 로그인 하지않고 게시판 작업을 하는경우에 필요함. )
			user = new LoginVO();
			user.setUniqId("0000MEMB0000000");
			user.setId("GUEST");
			user.setName("방문자");
		}*/

		if (isAuthenticated) {
			bbsMngService.deleteGuestList(boardVO);
		}

		return "forward:/" + serviceArea + "/cop/bbs/" + pathBbsId + "/selectGuestList.do";
	}

	/**
	 * 방명록 수정의 위한 목록을 조회한다.
	 *
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/{pathBbsId}/updateGuestList.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/updateGuestList.do")
	public String updateGuestList(@PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,  @ModelAttribute("searchVO") BoardVO boardVO,
			@ModelAttribute("board") Board board, BindingResult bindingResult, ModelMap model) throws Exception {

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);


		// BBST02, BBST04
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

/*		if( null == user ){ // 방문자 정보로 셋팅함.( 로그인 하지않고 게시판 작업을 하는경우에 필요함. )
			user = new LoginVO();
			user.setUniqId("0000MEMB0000000");
			user.setId("GUEST");
			user.setName("방문자");
		}*/

		beanValidator.validate(board, bindingResult);
		if (bindingResult.hasErrors()) {

			BoardVO vo = new BoardVO();

			vo.setBbsId(boardVO.getBbsId());
			vo.setBbsNm(boardVO.getBbsNm());
			vo.setNtcrNm(user.getName());
			vo.setNtcrId(user.getUniqId());

			BoardMasterVO masterVo = new BoardMasterVO();

			masterVo.setBbsId(vo.getBbsId());
			masterVo.setUniqId(user.getUniqId());

			BoardMasterVO mstrVO = bbsAttrbService.selectBBSMasterInf(masterVo);

			vo.setPageUnit(propertyService.getInt("pageUnit"));
			vo.setPageSize(propertyService.getInt("pageSize"));

			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(vo.getPageIndex());
			paginationInfo.setRecordCountPerPage(vo.getPageUnit());
			paginationInfo.setPageSize(vo.getPageSize());

			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

			Map<String, Object> map = bbsMngService.selectGuestList(vo);
			int totCnt = Integer.parseInt((String) map.get("resultCnt"));

			paginationInfo.setTotalRecordCount(totCnt);

			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("brdMstrVO", mstrVO);
			model.addAttribute("boardVO", vo);
			model.addAttribute("paginationInfo", paginationInfo);

			return "oklms/" + serviceArea + "/egovframework/com/cop/bbs/EgovGuestList";
			// return "oklms/commbiz/bbs/EgovGuestList";
		}

		if (isAuthenticated) {
			bbsMngService.updateBoardArticle(board);
			boardVO.setNttCn("");
			boardVO.setPassword("");
			boardVO.setNtcrId("");
			boardVO.setNttId(0);
		}

		return "forward:/" + serviceArea + "/cop/bbs/" + pathBbsId + "/selectGuestList.do";
	}

	/**
	 * 방명록에 대한 내용을 등록한다.
	 *
	 * @param boardVO
	 * @param board
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/{pathBbsId}/insertGuestList.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/insertGuestList.do")
	public String insertGuestList(@PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,  @ModelAttribute("searchVO") BoardVO boardVO,
			@ModelAttribute("board") Board board, BindingResult bindingResult, ModelMap model) throws Exception {

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);


		// 그러니까 무인증은 아니고 - _- 익명으로 등록이 가능한 부분임
		// 무인증이 되려면 별도의 컨트롤러를 하나 더 등록해야함

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

/*		if( null == user ){ // 방문자 정보로 셋팅함.( 로그인 하지않고 게시판 작업을 하는경우에 필요함. )
			user = new LoginVO();
			user.setUniqId("0000MEMB0000000");
			user.setId("GUEST");
			user.setName("방문자");
		}*/

		beanValidator.validate(board, bindingResult);
		if (bindingResult.hasErrors()) {

			BoardVO vo = new BoardVO();

			vo.setBbsId(boardVO.getBbsId());
			vo.setBbsNm(boardVO.getBbsNm());
			vo.setNtcrNm(user.getName());
			vo.setNtcrId(user.getUniqId());

			BoardMasterVO masterVo = new BoardMasterVO();

			masterVo.setBbsId(vo.getBbsId());
			masterVo.setUniqId(user.getUniqId());

			BoardMasterVO mstrVO = bbsAttrbService.selectBBSMasterInf(masterVo);

			vo.setPageUnit(propertyService.getInt("pageUnit"));
			vo.setPageSize(propertyService.getInt("pageSize"));

			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(vo.getPageIndex());
			paginationInfo.setRecordCountPerPage(vo.getPageUnit());
			paginationInfo.setPageSize(vo.getPageSize());

			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			vo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

			Map<String, Object> map = bbsMngService.selectGuestList(vo);
			int totCnt = Integer.parseInt((String) map.get("resultCnt"));

			paginationInfo.setTotalRecordCount(totCnt);

			model.addAttribute("resultList", map.get("resultList"));
			model.addAttribute("resultCnt", map.get("resultCnt"));
			model.addAttribute("brdMstrVO", mstrVO);
			model.addAttribute("boardVO", vo);
			model.addAttribute("paginationInfo", paginationInfo);

			return "oklms/" + serviceArea + "/egovframework/com/cop/bbs/EgovGuestList";
			// return "oklms/commbiz/bbs/EgovGuestList";

		}

		if (isAuthenticated) {
			board.setFrstRegisterId(user.getUniqId());

			bbsMngService.insertBoardArticle(board);

			boardVO.setNttCn("");
			boardVO.setPassword("");
			boardVO.setNtcrId("");
			boardVO.setNttId(0);
		}

		return "forward:/" + serviceArea + "/cop/bbs/" + pathBbsId + "/selectGuestList.do";
	}

	/**
	 * 익명게시물에 대한 목록을 조회한다.
	 *
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/anonymous/{pathBbsId}/selectBoardList.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/anonymous/selectBoardList.do")
	public String selectAnonymousBoardArticles(@PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);

		// LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		// log.debug("user.getId() "+ user.getId());
		// log.debug("user.getName() "+ user.getName());
		// log.debug("user.getUniqId() "+ user.getUniqId());
		// log.debug("user.getOrgnztId() "+ user.getOrgnztId());
		// log.debug("user.getUserSe() "+ user.getUserSe());
		// log.debug("user.getEmail() "+ user.getEmail());

		// String attrbFlag = "";

		boardVO.setBbsId(boardVO.getBbsId());
		boardVO.setBbsNm(boardVO.getBbsNm());

		BoardMasterVO vo = new BoardMasterVO();

		vo.setBbsId(boardVO.getBbsId());
		vo.setUniqId("ANONYMOUS"); // 익명

		BoardMasterVO master = bbsAttrbService.selectBBSMasterInf(vo);

		// -------------------------------
		// 익명게시판이 아니면.. 원래 게시판 URL로 forward
		// -------------------------------
		if (!master.getBbsTyCode().equals("BBST02")) {
			return "forward:/" + serviceArea + "/cop/bbs/" + pathBbsId + "/selectBoardList.do";
		}
		// //-----------------------------

		boardVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
		paginationInfo.setPageSize(boardVO.getPageSize());

		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		Map<String, Object> map = bbsMngService.selectBoardArticles(boardVO, vo.getBbsAttrbCode());
		int totCnt = Integer.parseInt((String) map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);

		// -------------------------------
		// 기본 BBS template 지정
		// -------------------------------
		if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
			master.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
		}
		// //-----------------------------

		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("brdMstrVO", master);
		model.addAttribute("paginationInfo", paginationInfo);

		model.addAttribute("anonymous", "true");

		return "oklms/" + serviceArea + "/egovframework/com/cop/bbs/EgovNoticeList";
		// return "oklms/commbiz/bbs/EgovNoticeList";
	}

	/**
	 * 익명게시물 등록을 위한 등록페이지로 이동한다.
	 *
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/anonymous/{pathBbsId}/addBoardArticle.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/anonymous/addBoardArticle.do")
	public String addAnonymousBoardArticle(@PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);

		// LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		// Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		Boolean isAuthenticated = true;

		BoardMasterVO bdMstr = new BoardMasterVO();

		if (isAuthenticated) {
			BoardMasterVO vo = new BoardMasterVO();
			vo.setBbsId(boardVO.getBbsId());
			vo.setUniqId("ANONYMOUS");

			bdMstr = bbsAttrbService.selectBBSMasterInf(vo);
			model.addAttribute("bdMstr", bdMstr);
		}

		// -------------------------------
		// 익명게시판이 아니면.. 원래 게시판 URL로 forward
		// -------------------------------
		if (!bdMstr.getBbsTyCode().equals("BBST02")) {
			return "forward:/" + serviceArea + "/cop/bbs/" + pathBbsId + "/addBoardArticle.do";
		}
		// //-----------------------------

		// ----------------------------
		// 기본 BBS template 지정
		// ----------------------------
		if (bdMstr.getTmplatCours() == null || bdMstr.getTmplatCours().equals("")) {
			bdMstr.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
		}

		model.addAttribute("brdMstrVO", bdMstr);
		// //-----------------------------

		model.addAttribute("anonymous", "true");

		return "oklms/" + serviceArea + "/egovframework/com/cop/bbs/EgovNoticeRegist";
		// return "oklms/commbiz/bbs/EgovNoticeRegist";
	}

	/**
	 * 익명게시물을 등록한다.
	 *
	 * @param boardVO
	 * @param board
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/anonymous/{pathBbsId}/insertBoardArticle.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/anonymous/insertBoardArticle.do")
	public String insertAnonymousBoardArticle(@PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO boardVO,
			@ModelAttribute("bdMstr") BoardMaster bdMstr, @ModelAttribute("board") Board board, BindingResult bindingResult,
			SessionStatus status, ModelMap model) throws Exception {

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);


		// LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		// Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		Boolean isAuthenticated = true;

		beanValidator.validate(board, bindingResult);
		if (bindingResult.hasErrors()) {

			BoardMasterVO master = new BoardMasterVO();
			BoardMasterVO vo = new BoardMasterVO();

			vo.setBbsId(boardVO.getBbsId());
			vo.setUniqId("ANONYMOUS");

			master = bbsAttrbService.selectBBSMasterInf(vo);

			model.addAttribute("bdMstr", master);

			// -------------------------------
			// 익명게시판이 아니면.. 원래 게시판 URL로 forward
			// -------------------------------
			if (!bdMstr.getBbsTyCode().equals("BBST02")) {
				return "forward:/" + serviceArea + "/cop/bbs/" + pathBbsId + "/insertBoardArticle.do";
			}
			// //-----------------------------

			// ----------------------------
			// 기본 BBS template 지정
			// ----------------------------
			if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
				master.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
			}

			model.addAttribute("brdMstrVO", master);
			// //-----------------------------

			model.addAttribute("anonymous", "true");

			return "oklms/" + serviceArea + "/egovframework/com/cop/bbs/EgovNoticeRegist";
			// return "oklms/commbiz/bbs/EgovNoticeRegist";
		}

		if (isAuthenticated) {
			List<FileVO> result = null;
			String atchFileId = "";

			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			if (!files.isEmpty()) {
				result = fileUtil.parseFileInf(files, "BBS_", 0, "", "");
				atchFileId = fileMngService.insertFileInfs(result);
			}
			board.setAtchFileId(atchFileId);
			board.setFrstRegisterId("ANONYMOUS");
			board.setBbsId(board.getBbsId());

			// 익명게시판 관련
			board.setNtcrNm(board.getNtcrNm());
			board.setPassword(EgovFileScrty.encryptPassword(board.getPassword(), boardVO.getBbsId()));

			board.setNttCn(unscript(board.getNttCn())); // XSS 방지

			bbsMngService.insertBoardArticle(board);
		}

		// status.setComplete();
		return "forward:/" + serviceArea + "/cop/bbs/anonymous/" + pathBbsId + "/selectBoardList.do";
	}

	/**
	 * 익명게시물에 대한 상세 정보를 조회한다.
	 *
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/anonymous/{pathBbsId}/selectBoardArticle.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/anonymous/selectBoardArticle.do")
	public String selectAnonymousBoardArticle(@PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);

		// LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		// 조회수 증가 여부 지정
		boardVO.setPlusCount(true);

		// ---------------------------------
		// 2009.06.29 : 2단계 기능 추가
		// ---------------------------------
		if (!boardVO.getSubPageIndex().equals("")) {
			boardVO.setPlusCount(false);
		}
		// //-------------------------------

		boardVO.setLastUpdusrId("ANONYMOUS");
		BoardVO vo = bbsMngService.selectBoardArticle(boardVO);

		model.addAttribute("result", vo);
		// CommandMap의 형태로 개선????

		model.addAttribute("sessionUniqId", "ANONYMOUS");

		// ----------------------------
		// template 처리 (기본 BBS template 지정 포함)
		// ----------------------------
		BoardMasterVO master = new BoardMasterVO();

		master.setBbsId(boardVO.getBbsId());
		master.setUniqId("ANONYMOUS");

		BoardMasterVO masterVo = bbsAttrbService.selectBBSMasterInf(master);

		// -------------------------------
		// 익명게시판이 아니면.. 원래 게시판 URL로 forward
		// -------------------------------
		if (!masterVo.getBbsTyCode().equals("BBST02")) {
			return "forward:/" + serviceArea + "/cop/bbs/" + pathBbsId + "/selectBoardArticle.do";
		}
		// //-----------------------------

		if (masterVo.getTmplatCours() == null || masterVo.getTmplatCours().equals("")) {
			masterVo.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
		}

		model.addAttribute("brdMstrVO", masterVo);
		// //-----------------------------

		model.addAttribute("anonymous", "true");

		// ----------------------------
		// 2009.06.29 : 2단계 기능 추가
		// 2011.07.01 : 댓글, 스크랩, 만족도 조사 기능의 종속성 제거
		// ----------------------------
		if (bbsCommentService != null) {
			if (bbsCommentService.canUseComment(boardVO.getBbsId())) {
				model.addAttribute("useComment", "true");
			}
		}
		if (bbsSatisfactionService != null) {
			if (bbsSatisfactionService.canUseSatisfaction(boardVO.getBbsId())) {
				model.addAttribute("useSatisfaction", "true");
			}
		}
		if (bbsScrapService != null) {
			if (bbsScrapService.canUseScrap()) {
				model.addAttribute("useScrap", "true");
			}
		}
		// //--------------------------

		return "oklms/" + serviceArea + "/egovframework/com/cop/bbs/EgovNoticeInqire";
		// return "oklms/commbiz/bbs/EgovNoticeInqire";
	}

	/**
	 * 익명게시물에 대한 내용을 삭제한다.
	 *
	 * @param boardVO
	 * @param board
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/anonymous/{pathBbsId}/deleteBoardArticle.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/anonymous/deleteBoardArticle.do")
	public String deleteAnonymousBoardArticle(@PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("board") Board board,
			@ModelAttribute("bdMstr") BoardMaster bdMstr, ModelMap model) throws Exception {

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);


		// LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		// Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		Boolean isAuthenticated = true;

		// --------------------------------------------------
		// 마스터 정보 얻기
		// --------------------------------------------------
		BoardMasterVO master = new BoardMasterVO();

		master.setBbsId(boardVO.getBbsId());
		master.setUniqId("ANONYMOUS");

		BoardMasterVO masterVo = bbsAttrbService.selectBBSMasterInf(master);

		// -------------------------------
		// 익명게시판이 아니면.. 원래 게시판 URL로 forward
		// -------------------------------
		if (!masterVo.getBbsTyCode().equals("BBST02")) {
			return "forward:/" + serviceArea + "/cop/bbs/" + pathBbsId + "/deleteBoardArticle.do";
		}
		// //-----------------------------

		// -------------------------------
		// 패스워드 비교
		// -------------------------------
		String dbpassword = bbsMngService.getPasswordInf(board);
		String enpassword = EgovFileScrty.encryptPassword(board.getPassword(), boardVO.getBbsId());

		if (!dbpassword.equals(enpassword)) {

			model.addAttribute("msg", egovMessageSource.getMessage("cop.password.not.same.msg"));

			return "forward:/" + serviceArea + "/cop/bbs/anonymous/" + pathBbsId + "/selectBoardArticle.do";
		}
		// //-----------------------------

		if (isAuthenticated) {
			board.setLastUpdusrId("ANONYMOUS");

			bbsMngService.deleteBoardArticle(board);
		}

		return "forward:/" + serviceArea + "/cop/bbs/anonymous/" + pathBbsId + "/selectBoardList.do";
	}

	/**
	 * 익명게시물 수정을 위한 수정페이지로 이동한다.
	 *
	 * @param boardVO
	 * @param vo
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/anonymous/{pathBbsId}/forUpdateBoardArticle.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/anonymous/forUpdateBoardArticle.do")
	public String selectAnonymousBoardArticleForUpdt(@PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			@ModelAttribute("searchVO") BoardVO boardVO, @ModelAttribute("board") BoardVO vo, ModelMap model) throws Exception {

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);


		// log.debug(this.getClass().getName()+"selectBoardArticleForUpdt getNttId "+boardVO.getNttId());
		// log.debug(this.getClass().getName()+"selectBoardArticleForUpdt getBbsId "+boardVO.getBbsId());

		// LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		// Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		Boolean isAuthenticated = true;

		boardVO.setFrstRegisterId("ANONYMOUS");

		BoardMaster master = new BoardMaster();
		BoardMasterVO bmvo = new BoardMasterVO();
		BoardVO bdvo = new BoardVO();

		vo.setBbsId(boardVO.getBbsId());

		master.setBbsId(boardVO.getBbsId());
		master.setUniqId("ANONYMOUS");

		if (isAuthenticated) {
			bmvo = bbsAttrbService.selectBBSMasterInf(master);

			// -------------------------------
			// 익명게시판이 아니면.. 원래 게시판 URL로 forward
			// -------------------------------
			if (!bmvo.getBbsTyCode().equals("BBST02")) {
				return "forward:/" + serviceArea + "/cop/bbs/" + pathBbsId + "/forUpdateBoardArticle.do";
			}
			// //-----------------------------

			// -------------------------------
			// 패스워드 비교
			// -------------------------------
			String dbpassword = bbsMngService.getPasswordInf(boardVO);
			String enpassword = EgovFileScrty.encryptPassword(boardVO.getPassword(), boardVO.getBbsId());

			if (!dbpassword.equals(enpassword)) {

				model.addAttribute("msg", egovMessageSource.getMessage("cop.password.not.same.msg"));

				return "forward:/" + serviceArea + "/cop/bbs/anonymous/" + pathBbsId + "/selectBoardArticle.do";
			}
			// //-----------------------------

			bdvo = bbsMngService.selectBoardArticle(boardVO);
		}

		model.addAttribute("result", bdvo);
		model.addAttribute("bdMstr", bmvo);

		// ----------------------------
		// 기본 BBS template 지정
		// ----------------------------
		if (bmvo.getTmplatCours() == null || bmvo.getTmplatCours().equals("")) {
			bmvo.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
		}

		model.addAttribute("brdMstrVO", bmvo);
		// //-----------------------------

		model.addAttribute("anonymous", "true");

		return "oklms/" + serviceArea + "/egovframework/com/cop/bbs/EgovNoticeUpdt";
		// return "oklms/commbiz/bbs/EgovNoticeUpdt";
	}

	/**
	 * 익명게시물에 대한 내용을 수정한다.
	 *
	 * @param boardVO
	 * @param board
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/anonymous/{pathBbsId}/updateBoardArticle.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/anonymous/updateBoardArticle.do")
	public String updateAnonymousBoardArticle(@PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO boardVO,
			@ModelAttribute("bdMstr") BoardMaster bdMstr, @ModelAttribute("board") Board board, BindingResult bindingResult,
			ModelMap model, SessionStatus status) throws Exception {

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);


		// LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		// Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		Boolean isAuthenticated = true;

		String atchFileId = boardVO.getAtchFileId();

		beanValidator.validate(board, bindingResult);
		if (bindingResult.hasErrors()) {

			boardVO.setFrstRegisterId("ANONYMOUS");

			BoardMaster master = new BoardMaster();
			BoardMasterVO bmvo = new BoardMasterVO();
			BoardVO bdvo = new BoardVO();

			master.setBbsId(boardVO.getBbsId());
			master.setUniqId("ANONYMOUS");

			bmvo = bbsAttrbService.selectBBSMasterInf(master);

			// -------------------------------
			// 익명게시판이 아니면.. 원래 게시판 URL로 forward
			// -------------------------------
			if (!bdMstr.getBbsTyCode().equals("BBST02")) {
				return "forward:/" + serviceArea + "/cop/bbs/" + pathBbsId + "/updateBoardArticle.do";
			}
			// //-----------------------------

			bdvo = bbsMngService.selectBoardArticle(boardVO);

			model.addAttribute("result", bdvo);
			model.addAttribute("bdMstr", bmvo);

			model.addAttribute("anonymous", "true");

			return "oklms/" + serviceArea + "/egovframework/com/cop/bbs/EgovNoticeUpdt";
			// return "oklms/commbiz/bbs/EgovNoticeUpdt";
		}

		if (isAuthenticated) {
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			if (!files.isEmpty()) {
				if ("".equals(atchFileId)) {
					List<FileVO> result = fileUtil.parseFileInf(files, "BBS_", 0, atchFileId, "");
					atchFileId = fileMngService.insertFileInfs(result);
					board.setAtchFileId(atchFileId);
				} else {
					FileVO fvo = new FileVO();
					fvo.setAtchFileId(atchFileId);
					int cnt = fileMngService.getMaxFileSN(fvo);
					List<FileVO> _result = fileUtil.parseFileInf(files, "BBS_", cnt, atchFileId, "");
					fileMngService.updateFileInfs(_result);
				}
			}

			board.setLastUpdusrId("ANONYMOUS");

			// 익명게시판 관련
			board.setNtcrNm(board.getNtcrNm());
			board.setPassword(EgovFileScrty.encryptPassword(board.getPassword(), boardVO.getBbsId()));

			board.setNttCn(unscript(board.getNttCn())); // XSS 방지

			bbsMngService.updateBoardArticle(board);
		}

		return "forward:/" + serviceArea + "/cop/bbs/anonymous/" + pathBbsId + "/selectBoardList.do";
	}

	/**
	 * 익명게시물에 대한 답변 등록을 위한 등록페이지로 이동한다.
	 *
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/anonymous/{pathBbsId}/addReplyBoardArticle.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/anonymous/addReplyBoardArticle.do")
	public String addAnonymousReplyBoardArticle(@PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);

		// LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		BoardMasterVO master = new BoardMasterVO();
		BoardMasterVO vo = new BoardMasterVO();

		vo.setBbsId(boardVO.getBbsId());
		vo.setUniqId("ANONYMOUS");

		master = bbsAttrbService.selectBBSMasterInf(vo);

		// -------------------------------
		// 익명게시판이 아니면.. 원래 게시판 URL로 forward
		// -------------------------------
		if (!master.getBbsTyCode().equals("BBST02")) {
			return "forward:/" + serviceArea + "/cop/bbs/" + pathBbsId + "/addReplyBoardArticle.do";
		}
		// //-----------------------------

		model.addAttribute("bdMstr", master);
		model.addAttribute("result", boardVO);

		// ----------------------------
		// 기본 BBS template 지정
		// ----------------------------
		if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
			master.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
		}

		model.addAttribute("brdMstrVO", master);
		// //-----------------------------

		model.addAttribute("anonymous", "true");

		return "oklms/" + serviceArea + "/egovframework/com/cop/bbs/EgovNoticeReply";
		// return "oklms/commbiz/bbs/EgovNoticeReply";
	}

	/**
	 * 익명게시물에 대한 답변을 등록한다.
	 *
	 * @param boardVO
	 * @param board
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/anonymous/{pathBbsId}/replyBoardArticle.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/anonymous/replyBoardArticle.do")
	public String replyAnonymousBoardArticle(@PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO boardVO,
			@ModelAttribute("bdMstr") BoardMaster bdMstr, @ModelAttribute("board") Board board, BindingResult bindingResult,
			ModelMap model, SessionStatus status) throws Exception {

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);


		// LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		// Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		Boolean isAuthenticated = true;

		beanValidator.validate(board, bindingResult);
		if (bindingResult.hasErrors()) {
			BoardMasterVO master = new BoardMasterVO();
			BoardMasterVO vo = new BoardMasterVO();

			vo.setBbsId(boardVO.getBbsId());
			vo.setUniqId("ANONYMOUS");

			master = bbsAttrbService.selectBBSMasterInf(vo);

			// -------------------------------
			// 익명게시판이 아니면.. 원래 게시판 URL로 forward
			// -------------------------------
			if (!master.getBbsTyCode().equals("BBST02")) {
				return "forward:/" + serviceArea + "/cop/bbs/" + pathBbsId + "/replyBoardArticle.do";
			}
			// //-----------------------------

			model.addAttribute("bdMstr", master);
			model.addAttribute("result", boardVO);

			// ----------------------------
			// 기본 BBS template 지정
			// ----------------------------
			if (master.getTmplatCours() == null || master.getTmplatCours().equals("")) {
				master.setTmplatCours("/css/egovframework/com/cop/tpl/egovBaseTemplate.css");
			}

			model.addAttribute("brdMstrVO", master);
			// //-----------------------------

			model.addAttribute("anonymous", "true");

			return "oklms/" + serviceArea + "/egovframework/com/cop/bbs/EgovNoticeReply";
			// return "oklms/commbiz/bbs/EgovNoticeReply";
		}

		if (isAuthenticated) {
			final Map<String, MultipartFile> files = multiRequest.getFileMap();
			String atchFileId = "";

			if (!files.isEmpty()) {
				List<FileVO> result = fileUtil.parseFileInf(files, "BBS_", 0, "", "");
				atchFileId = fileMngService.insertFileInfs(result);
			}

			board.setAtchFileId(atchFileId);
			board.setReplyAt("Y");
			board.setFrstRegisterId("ANONYMOUS");
			board.setBbsId(board.getBbsId());
			board.setParnts(Long.toString(boardVO.getNttId()));
			board.setSortOrdr(boardVO.getSortOrdr());
			board.setReplyLc(Integer.toString(Integer.parseInt(boardVO.getReplyLc()) + 1));

			// 익명게시판 관련
			board.setNtcrNm(board.getNtcrNm());
			board.setPassword(EgovFileScrty.encryptPassword(board.getPassword(), boardVO.getBbsId()));

			board.setNttCn(unscript(board.getNttCn())); // XSS 방지

			bbsMngService.insertBoardArticle(board);
		}

		return "forward:/" + serviceArea + "/cop/bbs/anonymous/" + pathBbsId + "/selectBoardList.do";
	}

	/**
	 * 템플릿에 대한 미리보기용 게시물 목록을 조회한다.
	 *
	 * @param boardVO
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{serviceArea}/cop/bbs/{pathBbsId}/previewBoardList.do")
//	@RequestMapping("/{serviceArea}/cop/bbs/previewBoardList.do")
	public String previewBoardArticles(@PathVariable("serviceArea") String serviceArea , @PathVariable("pathBbsId") String pathBbsId ,
			@ModelAttribute("searchVO") BoardVO boardVO, ModelMap model) throws Exception {

		model.addAttribute("pathBbsIdStr" , pathBbsId);
		boardVO.setBbsId(pathBbsId);

		// LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String template = boardVO.getSearchWrd(); // 템플릿 URL

		BoardMasterVO master = new BoardMasterVO();

		master.setBbsNm("미리보기 게시판");

		boardVO.setPageUnit(propertyService.getInt("pageUnit"));
		boardVO.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
		paginationInfo.setPageSize(boardVO.getPageSize());

		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		BoardVO target = null;
		List<BoardVO> list = new ArrayList<BoardVO>();

		target = new BoardVO();
		target.setNttSj("게시판 기능 설명");
		target.setFrstRegisterId("ID");
		target.setFrstRegisterNm("관리자");
		target.setFrstRegisterPnttm("2009-01-01");
		target.setInqireCo(7);
		target.setParnts("0");
		target.setReplyAt("N");
		target.setReplyLc("0");
		target.setUseAt("Y");

		list.add(target);

		target = new BoardVO();
		target.setNttSj("게시판 부가 기능 설명");
		target.setFrstRegisterId("ID");
		target.setFrstRegisterNm("관리자");
		target.setFrstRegisterPnttm("2009-01-01");
		target.setInqireCo(7);
		target.setParnts("0");
		target.setReplyAt("N");
		target.setReplyLc("0");
		target.setUseAt("Y");

		list.add(target);

		boardVO.setSearchWrd("");

		int totCnt = list.size();

		paginationInfo.setTotalRecordCount(totCnt);

		master.setTmplatCours(template);

		model.addAttribute("resultList", list);
		model.addAttribute("resultCnt", Integer.toString(totCnt));
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("brdMstrVO", master);
		model.addAttribute("paginationInfo", paginationInfo);

		model.addAttribute("preview", "true");

		return "oklms_body/" + serviceArea + "/egovframework/com/cop/bbs/EgovNoticeList";
	}
}
