/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2017. 03. 02.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.lu.discuss.web;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.atchFile.vo.AtchFileVO;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.commbiz.cmmcode.vo.CommonCodeVO;
import kr.co.sitglobal.oklms.lu.currproc.service.CurrProcService;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.discuss.service.DiscussService;
import kr.co.sitglobal.oklms.lu.discuss.vo.DiscussVO;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.Globals;
//import egovframework.com.cmm.util.EgovDoubleSubmitHelper;

@Controller
public class DiscussController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(DiscussController.class);

	@Resource(name = "discussService")
	private DiscussService discussService;

	@Resource(name = "currProcService")
	private CurrProcService currProcService;

	@Resource(name = "atchFileService")
	private AtchFileService atchFileService;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

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

	/**
	 * 토론 목록
	 * @param discussVO
	 * @return DiscussVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/{userType}/discuss/listDiscuss.do")
	public String listDiscuss(@PathVariable("userType") String userType, @RequestParam Map<String, Object> commandMap, @ModelAttribute("frmDiscuss") DiscussVO discussVO, ModelMap model, HttpServletRequest request) throws Exception {
		/*====================================================================
    	* 초기화 영역
    	======================================================================*/
		String yyyy	= ""; 					//학년도
	    String term	= ""; 					//학기
	    String subjectCode	= ""; 			//교과목코드
	    String subjectName	= "";    		//교과목명
	    String classs	= ""; 				//분반
	    String retMsg = "";
		HttpSession session = request.getSession(); // 로그인 사용자에 교과별-교과목 관련 세션 Key get 처리
		CurrProcVO currProcVO = new CurrProcVO();
		CurrProcVO currProcReadVO = new CurrProcVO();

		/*====================================================================
    	* 초기값 셋팅 영역
    	====================================================================*/
		HttpSession httpSession = request.getSession(); 
		/* userType 이 관리자 이외 경우는 진행중인 교과목 세션정보를 젯팅한다. */
		if( "lu".equals(userType)){
			
			discussVO.setYyyy(StringUtils.defaultIfBlank( discussVO.getYyyy() ,(String)httpSession.getAttribute(Globals.YYYY)));
			discussVO.setTerm(StringUtils.defaultIfBlank( discussVO.getTerm() ,(String)httpSession.getAttribute(Globals.TERM)));  
			discussVO.setSubClass(StringUtils.defaultIfBlank( discussVO.getSubClass() ,(String)httpSession.getAttribute(Globals.CLASS)));
			discussVO.setSubjectCode(StringUtils.defaultIfBlank( discussVO.getSubjectCode() ,(String)httpSession.getAttribute(Globals.SUBJECT_CODE)));
	 
			httpSession.setAttribute(Globals.YYYY, discussVO.getYyyy());
			httpSession.setAttribute(Globals.TERM, discussVO.getTerm());
			httpSession.setAttribute(Globals.CLASS, discussVO.getSubClass());
			httpSession.setAttribute(Globals.SUBJECT_CODE, discussVO.getSubjectCode());
			
			yyyy = discussVO.getYyyy();
			term = discussVO.getTerm();
			subjectCode = discussVO.getSubjectCode();
			subjectName = discussVO.getSubjectName();
			classs = discussVO.getSubClass();
		}

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(discussVO); // session의 정보를 VO에 추가.

		/*====================================================================
    	* 업무화면에서 사용하는 List Vo 셋팅 영역
    	====================================================================*/
		logger.debug("################################# ");
		logger.debug("#### yyyy : " + yyyy );
		logger.debug("#### term : " + term );
		logger.debug("#### subjectCode : " + subjectCode );
		logger.debug("#### subjectName : " + subjectName );
		logger.debug("#### classs : " + classs );
		logger.debug("################################# ");

		/* userType 이 관리자 이외 경우는 진행중인 교과목 세션정보가 있는지 비교하여
		   null 일경우 마이페이지로 포워딩한다.
		*/
		if( "lu".equals(userType) && StringUtils.isBlank( yyyy )){
			retMsg = "Left 메뉴에서 진행중인과정에 교과목 하나를 선택하세요.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
		} else if ( "lu".equals(userType) && !StringUtils.isBlank( yyyy )){

			discussVO.setYyyy(yyyy);
			discussVO.setTerm(term);
			discussVO.setSubjectCode(subjectCode);
			discussVO.setSubjectName(subjectName);
			discussVO.setSubClass(classs);

			currProcVO.setYyyy(yyyy);
			currProcVO.setTerm(term);
			currProcVO.setSubjectCode(subjectCode);
			currProcVO.setSubClass(classs);

			currProcReadVO = currProcService.getMySubjectInfo(currProcVO);

			List<DiscussVO> listDiscussInfo = discussService.listDiscussInfo(discussVO);
			List<DiscussVO> listDiscussOpinion = discussService.listDiscussOpinion(discussVO);

			model.addAttribute("currProcReadVO", currProcReadVO); 					//개설강좌 정보
			model.addAttribute("resultDiscussList", listDiscussInfo); 				//토론 목록
			model.addAttribute("resultDiscussOpinionList", listDiscussOpinion);     //토론에대한 의견 목록
		}

		model.addAttribute("discussVO", discussVO); //토론 파라메터정보


		if(!"STD".equals(discussVO.getSessionMemType())){
			return "oklms/"+userType+"/discuss/discussList"; // View호출 (교수자, 학과담당자)
		}else{
			return "oklms/"+userType+"/discuss/discussStdList"; // View호출 (학습근로자)
		}
	}

	/**
	 * 토론 상세 조회
	 * @param discussVO
	 * @return DiscussVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/{userType}/discuss/getDiscuss.do")
	public String getDiscuss(@PathVariable("userType") String userType, @ModelAttribute("frmDiscuss") DiscussVO discussVO, ModelMap model) throws Exception {

		CurrProcVO currProcVO = new CurrProcVO();
		CurrProcVO currProcReadVO = new CurrProcVO();
		DiscussVO discussReadVO = new DiscussVO();
		AtchFileVO atchFileVO = new AtchFileVO();

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(discussVO); // session의 정보를 VO에 추가.

		currProcVO.setYyyy(discussVO.getYyyy());
		currProcVO.setTerm(discussVO.getTerm());
		currProcVO.setSubjectCode(discussVO.getSubjectCode());
		currProcVO.setSubClass(discussVO.getSubClass());

		currProcReadVO = currProcService.getMySubjectInfo(currProcVO);
		discussReadVO = discussService.getDiscussInfo(discussVO);
		List<DiscussVO> listDiscussOpinion = discussService.listDiscussOpinion(discussVO);

		System.out.println("===atchFileId : "+discussReadVO.getAtchFileId());

		//첨부파일 정보셋팅
		atchFileVO.setFileSn(1);
		atchFileVO.setAtchFileId(discussReadVO.getAtchFileId());
		AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);

		model.addAttribute("currProcReadVO", currProcReadVO);  //개설강좌 정보
		model.addAttribute("discussReadVO", discussReadVO);    //토론 상세조회
		model.addAttribute("resultDiscussOpinionList", listDiscussOpinion);     //토론에대한 의견 목록
		model.addAttribute("discussVO", discussVO);            //토론 파라메터정보
		model.addAttribute("resultFile", resultFile);

		// View호출
		return "oklms/"+userType+"/discuss/discussRead";
	}

	/**
	 * 학습자별 토론 평가점수 저장 -> 담당교수 역할
	 * @param discussVO
	 * @return DiscussVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/{userType}/discuss/getDiscussEvalScoreResultStd.do")
	public String getDiscussEvalScoreResultStd(@PathVariable("userType") String userType, @ModelAttribute("frmDiscuss") DiscussVO discussVO, ModelMap model) throws Exception {

		CurrProcVO currProcVO = new CurrProcVO();
		CurrProcVO currProcReadVO = new CurrProcVO();
		DiscussVO discussReadVO = new DiscussVO();
		int stdEvalScoreCnt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(discussVO); // session의 정보를 VO에 추가.

		currProcVO.setYyyy(discussVO.getYyyy());
		currProcVO.setTerm(discussVO.getTerm());
		currProcVO.setSubjectCode(discussVO.getSubjectCode());
		currProcVO.setSubClass(discussVO.getSubClass());

		currProcReadVO = currProcService.getMySubjectInfo(currProcVO);
		discussReadVO = discussService.getDiscussInfo(discussVO);
		List<DiscussVO> listEvalScoreResultStd = discussService.listDiscussEvalScoreResultStd(discussVO);
		stdEvalScoreCnt = discussService.getDiscussEvalScoreStdCnt(discussVO);

		model.addAttribute("currProcReadVO", currProcReadVO);  //개설강좌 정보
		model.addAttribute("discussReadVO", discussReadVO);    //토론 상세조회
		model.addAttribute("resultEvalScoreStdList", listEvalScoreResultStd);     //토론에 평가결과점수 저장 목록
		model.addAttribute("discussVO", discussVO);            		//토론 파라메터정보
		model.addAttribute("stdEvalScoreCnt", stdEvalScoreCnt);    //토론별 학습자 평가점수 결과 count

		// View호출
		return "oklms/"+userType+"/discuss/discussEvalScoreResultStdRead";
	}

	/**
	 * 토론의견 상세 조회
	 * @param discussVO
	 * @return DiscussVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/{userType}/discuss/getDiscussOpinion.do")
	public String getDiscussOpinion(@PathVariable("userType") String userType, @ModelAttribute("frmDiscuss") DiscussVO discussVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		CurrProcVO currProcVO = new CurrProcVO();
		CurrProcVO currProcReadVO = new CurrProcVO();
		DiscussVO discussReadVO = new DiscussVO();
		DiscussVO discussOpinionReadVO = new DiscussVO();
		DiscussVO discussCommentReadVO = new DiscussVO();
		AtchFileVO atchFileVO1 = new AtchFileVO();
		AtchFileVO atchFileVO2 = new AtchFileVO();
		String retMsg = "";
		int commentCnt = 0;
		int updateHitCnt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(discussVO); // session의 정보를 VO에 추가.

		currProcVO.setYyyy(discussVO.getYyyy());
		currProcVO.setTerm(discussVO.getTerm());
		currProcVO.setSubjectCode(discussVO.getSubjectCode());
		currProcVO.setSubClass(discussVO.getSubClass());

		currProcReadVO = currProcService.getMySubjectInfo(currProcVO);
		discussReadVO = discussService.getDiscussInfo(discussVO);
		discussOpinionReadVO = discussService.getDiscussOpinionList(discussVO);
		commentCnt = discussService.getDiscussOpinionCommentCnt(discussVO);

		List<DiscussVO> listDiscussOpinionComment = discussService.listDiscussComment(discussVO);

		if(!StringUtils.isBlank(discussVO.getDiscussCommentId())){
			discussCommentReadVO = discussService.getDiscussOpinionComment(discussVO);
			model.addAttribute("commentContent", discussCommentReadVO.getContent());          //토론의견 내용
		}

		logger.debug("======== DISCUSS_OPINION_ID :"+discussVO.getDiscussOpinionId());

		//조회수 업데이트
		updateHitCnt = discussService.updateDiscussOpinionHitCnt(discussVO);
		if(updateHitCnt == 0){
			retMsg = "조회수 업데이트 처리시 오류가 발생하였습니다.!";
			redirectAttributes.addFlashAttribute("retMsg", retMsg);

			return "redirect:/lu/discuss/getDiscussOpinion.do";
		}

		System.out.println("===atchFileId : "+discussOpinionReadVO.getAtchFileId());

		//토론상세 첨부파일 정보셋팅
		atchFileVO1.setFileSn(1);
		atchFileVO1.setAtchFileId(discussReadVO.getAtchFileId());
		AtchFileVO resultFile1 = atchFileService.getAtchFile(atchFileVO1);
		model.addAttribute("resultFile1", resultFile1);

		//토론의견상세 첨부파일 정보셋팅
		atchFileVO2.setFileSn(1);
		atchFileVO2.setAtchFileId(discussOpinionReadVO.getAtchFileId());
		AtchFileVO resultFile2 = atchFileService.getAtchFile(atchFileVO2);
		model.addAttribute("resultFile2", resultFile2);

		model.addAttribute("currProcReadVO", currProcReadVO);  //개설강좌 정보
		model.addAttribute("discussReadVO", discussReadVO);    //토론 상세조회
		model.addAttribute("discussOpinionReadVO", discussOpinionReadVO);     //토론에대한 의견 상세조회
		model.addAttribute("commentCnt", commentCnt);          //토론의견 댓글수
		model.addAttribute("resultDiscussOpinionCommentList", listDiscussOpinionComment);          //토론의견 댓글 목록
		model.addAttribute("discussVO", discussVO);            //토론 파라메터정보

		// View호출
		return "oklms/"+userType+"/discuss/discussOpinionRead";
	}

	/**
	 * 토론 신규화면 이동
	 * @param discussVO
	 * @return DiscussVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/{userType}/discuss/goInsertDiscuss.do")
	public String goInsertDiscuss(@PathVariable("userType") String userType, @ModelAttribute("frmDiscuss") DiscussVO discussVO, ModelMap model) throws Exception {

		CurrProcVO currProcVO = new CurrProcVO();
		CurrProcVO currProcReadVO = new CurrProcVO();
		CommonCodeVO codeVO = new CommonCodeVO();

		currProcVO.setYyyy(discussVO.getYyyy());
		currProcVO.setTerm(discussVO.getTerm());
		currProcVO.setSubjectCode(discussVO.getSubjectCode());
		currProcVO.setSubClass(discussVO.getSubClass());

		currProcReadVO = currProcService.getMySubjectInfo(currProcVO);

		codeVO.setCodeGroup("TIME_HOUR");
		List<CommonCodeVO> timeHourCodeList = commonCodeService.selectCmmCodeList(codeVO);

		codeVO.setCodeGroup("TIME_MIN");
		List<CommonCodeVO> timeMinCodeList = commonCodeService.selectCmmCodeList(codeVO);

		model.addAttribute("currProcReadVO", currProcReadVO);  //개설강좌 정보
		model.addAttribute("timeHourCode", timeHourCodeList);  //공통코드 - 시간 목록
        model.addAttribute("timeMinCode", timeMinCodeList);    //공통코드 - 분 목록
		model.addAttribute("discussVO", discussVO);            //토론 파라메터정보

		// View호출
		return "oklms/"+userType+"/discuss/discussCret";
	}

	/**
	 * 토론의견 신규화면 이동
	 * @param discussVO
	 * @return DiscussVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/{userType}/discuss/goInsertDiscussOpinion.do")
	public String goInsertDiscussOpinion(@PathVariable("userType") String userType, @ModelAttribute("frmDiscuss") DiscussVO discussVO, ModelMap model) throws Exception {

		CurrProcVO currProcVO = new CurrProcVO();
		CurrProcVO currProcReadVO = new CurrProcVO();
		DiscussVO discussReadVO = new DiscussVO();

		currProcVO.setYyyy(discussVO.getYyyy());
		currProcVO.setTerm(discussVO.getTerm());
		currProcVO.setSubjectCode(discussVO.getSubjectCode());
		currProcVO.setSubClass(discussVO.getSubClass());

		currProcReadVO = currProcService.getMySubjectInfo(currProcVO);
		discussReadVO = discussService.getDiscussInfo(discussVO);

		model.addAttribute("currProcReadVO", currProcReadVO);  //개설강좌 정보
		model.addAttribute("discussReadVO", discussReadVO);    //토론 상세조회
		model.addAttribute("discussVO", discussVO);            //토론 파라메터정보

		// View호출
		return "oklms/"+userType+"/discuss/discussOpinionCret";
	}

	/**
	 * 토론 수정화면 이동
	 * @param discussVO
	 * @return DiscussVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/{userType}/discuss/goUpdateDiscuss.do")
	public String goUpdateDiscuss(@PathVariable("userType") String userType, @ModelAttribute("frmDiscuss") DiscussVO discussVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		CurrProcVO currProcVO = new CurrProcVO();
		CurrProcVO currProcReadVO = new CurrProcVO();
		DiscussVO discussReadVO = new DiscussVO();
		CommonCodeVO codeVO = new CommonCodeVO();
		AtchFileVO atchFileVO = new AtchFileVO();
		int opinionCnt = 0;

		//전체토론에 대한 의견수
		opinionCnt = discussService.getDiscussOpinionCnt(discussVO);
		discussReadVO = discussService.getDiscussInfo(discussVO);

		if(opinionCnt > 0){
			String retMsg = "("+discussReadVO.getTitle()+") 토론에 작성된 의견이 있어서 해당 토론을 수정할수 없습니다.";

			redirectAttributes.addFlashAttribute("retMsg", retMsg);
			return "redirect:/lu/discuss/listDiscuss.do";
		}

		currProcVO.setYyyy(discussVO.getYyyy());
		currProcVO.setTerm(discussVO.getTerm());
		currProcVO.setSubjectCode(discussVO.getSubjectCode());
		currProcVO.setSubClass(discussVO.getSubClass());

		currProcReadVO = currProcService.getMySubjectInfo(currProcVO);

		codeVO.setCodeGroup("TIME_HOUR");
		List<CommonCodeVO> timeHourCodeList = commonCodeService.selectCmmCodeList(codeVO);

		codeVO.setCodeGroup("TIME_MIN");
		List<CommonCodeVO> timeMinCodeList = commonCodeService.selectCmmCodeList(codeVO);

		System.out.println("===atchFileId : "+discussReadVO.getAtchFileId());

		//첨부파일 정보셋팅
		atchFileVO.setFileSn(1);
		atchFileVO.setAtchFileId(discussReadVO.getAtchFileId());
		AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
		model.addAttribute("resultFile", resultFile);

		model.addAttribute("currProcReadVO", currProcReadVO);  //개설강좌 정보
		model.addAttribute("timeHourCode", timeHourCodeList);  //공통코드 - 시간 목록
        model.addAttribute("timeMinCode", timeMinCodeList);    //공통코드 - 분 목록
        model.addAttribute("discussReadVO", discussReadVO);    //토론 상세조회
		model.addAttribute("discussVO", discussVO);            //토론 파라메터정보

		// View호출
		return "oklms/"+userType+"/discuss/discussUpdt";
	}

	/**
	 * 토론의견 수정화면 이동
	 * @param discussVO
	 * @return DiscussVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/{userType}/discuss/goUpdateDiscussOpinion.do")
	public String goUpdateDiscussOpinion(@PathVariable("userType") String userType, @ModelAttribute("frmDiscuss") DiscussVO discussVO, ModelMap model) throws Exception {

		CurrProcVO currProcVO = new CurrProcVO();
		CurrProcVO currProcReadVO = new CurrProcVO();
		DiscussVO discussReadVO = new DiscussVO();
		DiscussVO discussOpinionReadVO = new DiscussVO();
		AtchFileVO atchFileVO = new AtchFileVO();

		currProcVO.setYyyy(discussVO.getYyyy());
		currProcVO.setTerm(discussVO.getTerm());
		currProcVO.setSubjectCode(discussVO.getSubjectCode());
		currProcVO.setSubClass(discussVO.getSubClass());

		currProcReadVO = currProcService.getMySubjectInfo(currProcVO);
		discussReadVO = discussService.getDiscussInfo(discussVO);
		discussOpinionReadVO = discussService.getDiscussOpinionList(discussVO);

		//첨부파일 정보셋팅
		atchFileVO.setFileSn(1);
		atchFileVO.setAtchFileId(discussOpinionReadVO.getAtchFileId());
		AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
		model.addAttribute("resultFile", resultFile);

		model.addAttribute("currProcReadVO", currProcReadVO);  //개설강좌 정보
		model.addAttribute("discussReadVO", discussReadVO);    //토론 상세조회
		model.addAttribute("discussOpinionReadVO", discussOpinionReadVO);     //토론에대한 의견 상세조회
		model.addAttribute("discussVO", discussVO);            //토론 파라메터정보

		// View호출
		return "oklms/"+userType+"/discuss/discussOpinionUpdt";
	}

	/**
	 * 토론 신규추가건 저장
	 * @param discussVO
	 * @return DiscussVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/discuss/insertDiscuss.do")
	public String insertDiscuss(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmDiscuss") DiscussVO discussVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status,final MultipartHttpServletRequest multiRequest) throws Exception {

		LOGGER.debug("#### paramMap ==> "+paramMap);
		LOGGER.debug("#### discussVO ==> "+discussVO);

		String retMsg = "입력값을 확인해주세요";
		int insertCnt = 0;

		//첨부파일 저장
		String strStorePath ="Globals.fileStorePath";
		String atchFileId = "";
		final List< MultipartFile > fileObj = multiRequest.getFiles("file-input");
		atchFileId = atchFileService.saveAtchFile( fileObj, "DISC_", "", strStorePath );

		LOGGER.debug("#### getTitleAsHtmlEnterScript ==> "+discussVO.getTitleAsHtmlEnterScript());

		discussVO.setAtchFileId(atchFileId);
		discussVO.setTitle(discussVO.getTitleAsHtmlEnterScript());

		insertCnt = discussService.insertDiscussInfo(discussVO);

		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}

		discussVO.setDiscussId(null);

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmDiscuss", discussVO);

		return "redirect:/lu/discuss/listDiscuss.do";
	}

	/**
	 * 토론의견 신규추가건 저장
	 * @param discussVO
	 * @return DiscussVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/discuss/insertDiscussOpinion.do")
	public String insertDiscussOpinion(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmDiscuss") DiscussVO discussVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status, MultipartHttpServletRequest multiRequest) throws Exception {

		LOGGER.debug("#### paramMap ==> "+paramMap);
		LOGGER.debug("#### discussVO ==> "+discussVO);

		String retMsg = "입력값을 확인해주세요";
		int insertCnt = 0;

		//첨부파일 저장
  		String strStorePath ="Globals.fileStorePath";
		String atchFileId = "";
		final List< MultipartFile > fileObj = multiRequest.getFiles("file-input");
		atchFileId = atchFileService.saveAtchFile( fileObj, "DISO_", "", strStorePath );

		discussVO.setAtchFileId(atchFileId);

		insertCnt = discussService.insertDiscussOpinion(discussVO);

		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmDiscuss", discussVO);

		return "redirect:/lu/discuss/listDiscuss.do";
	}

	/**
	 * 토론의견 댓글 신규추가건 저장
	 * @param discussVO
	 * @return DiscussVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/discuss/insertDiscussOpinionComment.do")
	public String insertDiscussOpinionComment(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmDiscuss") DiscussVO discussVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		LOGGER.debug("#### paramMap ==> "+paramMap);
		LOGGER.debug("#### discussVO ==> "+discussVO);

		String retMsg = "입력값을 확인해주세요";
		int insertCnt = 0;

		insertCnt = discussService.insertDiscussComment(discussVO);

		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmDiscuss", discussVO);

		return "redirect:/lu/discuss/getDiscussOpinion.do";
	}

	/**
	 * 토론 학습자별 평가점수 신규 저장
	 * @param discussVO
	 * @return DiscussVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/discuss/insertDiscussStdEvalScore.do")
	public String insertDiscussStdEvalScore(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmDiscuss") DiscussVO discussVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		LOGGER.debug("#### paramMap ==> "+paramMap);
		LOGGER.debug("#### discussVO ==> "+discussVO);

		String retMsg = "입력값을 확인해주세요";
		int insertCnt = 0;

		insertCnt = discussService.insertDiscussStdEvalScore(discussVO);

		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmDiscuss", discussVO);

		return "redirect:/lu/discuss/listDiscuss.do";
	}

	/**
	 * 토론 수젇 저장
	 * @param discussVO
	 * @return DiscussVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/discuss/updateDiscuss.do")
	public String updateDiscuss(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmDiscuss") DiscussVO discussVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status, MultipartHttpServletRequest multiRequest) throws Exception {

		LOGGER.debug("#### paramMap ==> "+paramMap);
		LOGGER.debug("#### discussVO ==> "+discussVO);

		String retMsg = "입력값을 확인해주세요";
		int insertCnt = 0;

		//첨부파일 저장
		String strStorePath ="Globals.fileStorePath";
		String atchFileId = "";
		final List< MultipartFile > fileObj = multiRequest.getFiles("file-input");
		atchFileId = atchFileService.saveAtchFile( fileObj, "DISC_", "", strStorePath );

		discussVO.setAtchFileId(atchFileId);

		insertCnt = discussService.updateDiscussInfo(discussVO);

		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}

		discussVO.setDiscussId(null);

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmDiscuss", discussVO);

		return "redirect:/lu/discuss/listDiscuss.do";
	}

	/**
	 * 토론의견 수젇 저장
	 * @param discussVO
	 * @return DiscussVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/discuss/updateDiscussOpinion.do")
	public String updateDiscussOpinion(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmDiscuss") DiscussVO discussVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status, MultipartHttpServletRequest multiRequest) throws Exception {

		LOGGER.debug("#### paramMap ==> "+paramMap);
		LOGGER.debug("#### discussVO ==> "+discussVO);

		String retMsg = "입력값을 확인해주세요";
		int insertCnt = 0;

		//첨부파일 저장
		String strStorePath ="Globals.fileStorePath";
		String atchFileId = "";
		final List< MultipartFile > fileObj = multiRequest.getFiles("file-input");
		atchFileId = atchFileService.saveAtchFile( fileObj, "DISO_", "", strStorePath );

		discussVO.setAtchFileId(atchFileId);

		insertCnt = discussService.updateDiscussOpinion(discussVO);

		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmDiscuss", discussVO);

		return "redirect:/lu/discuss/listDiscuss.do";
	}

	/**
	 * 토론의견 댓글수젇 저장
	 * @param discussVO
	 * @return DiscussVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/discuss/updateDiscussOpinionComment.do")
	public String updateDiscussOpinionComment(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmDiscuss") DiscussVO discussVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		LOGGER.debug("#### paramMap ==> "+paramMap);
		LOGGER.debug("#### discussVO ==> "+discussVO);

		String retMsg = "입력값을 확인해주세요";
		int insertCnt = 0;

		insertCnt = discussService.updateDiscussComment(discussVO);

		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}

		discussVO.setMemSeq("");

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmDiscuss", discussVO);

		return "redirect:/lu/discuss/getDiscussOpinion.do";
	}

	/**
	 * 토론의견 추천수 수젇 저장
	 * @param discussVO
	 * @return DiscussVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/discuss/updateDiscussGoodHist.do")
	public String updateDiscussGoodHist(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmDiscuss") DiscussVO discussVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		LOGGER.debug("#### paramMap ==> "+paramMap);
		LOGGER.debug("#### discussVO ==> "+discussVO);

		String retMsg = "입력값을 확인해주세요";
		int readHistCnt = 0;
		int updateCnt = 0;
		int insertCnt = 0;

		//추천수 업데이트시 해당 토론의견에 1번이라도 추천한적 있는지 조회
		readHistCnt = discussService.getDiscussOpinionHistCnt(discussVO);

		if(readHistCnt != 0){
			retMsg = "이미 추천이 된건입니다.!";
		} else {
			//추천수 업데이트
			updateCnt = discussService.updateDiscussOpinionGoodCnt(discussVO);

			if(updateCnt == 0){
				retMsg = "추천수 업데이트 처리시 오류가 발생하였습니다.!";
			} else {
				//추천수 히스토리 테이블 토런 의견에 대한 본인 추천은 무조건 1건만 Insert 한다.
				insertCnt = discussService.insertDiscussGoodHist(discussVO);
				if(insertCnt == 0){
					retMsg = "추천수 업데이트 처리시 오류가 발생하였습니다.!";
				}
			}

			if(updateCnt > 0 && insertCnt > 0){
				retMsg = "정상적으로 추천수가 업데이트 되었습니다.!";
			}
		}

		discussVO.setMemSeq("");

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmDiscuss", discussVO);

		return "redirect:/lu/discuss/listDiscuss.do";
	}

	/**
	 * 토론 학습자별 평가점수 수정 저장
	 * @param discussVO
	 * @return DiscussVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/discuss/updateDiscussStdEvalScore.do")
	public String updateDiscussStdEvalScore(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmDiscuss") DiscussVO discussVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		LOGGER.debug("#### paramMap ==> "+paramMap);
		LOGGER.debug("#### discussVO ==> "+discussVO);

		String retMsg = "입력값을 확인해주세요";
		int insertCnt = 0;

		insertCnt = discussService.updateDiscussStdEvalScore(discussVO);

		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmDiscuss", discussVO);

		return "redirect:/lu/discuss/listDiscuss.do";
	}

	/**
	 * 토론 삭제
	 * @param discussVO
	 * @return DiscussVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/{userType}/discuss/deleteDiscuss.do")
	public String deleteDiscuss(@PathVariable("userType") String userType, @RequestParam Map<String, Object> commandMap, @ModelAttribute("frmDiscuss") DiscussVO discussVO, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "";
		int opinionCnt = 0;
		int deletetCnt = 0;
		DiscussVO discussReadVO = new DiscussVO();

		//전체토론에 대한 의견수
		opinionCnt = discussService.getDiscussOpinionCnt(discussVO);

		if(opinionCnt > 0){
			discussReadVO = discussService.getDiscussInfo(discussVO);
			retMsg = "("+discussReadVO.getTitle()+") 토론에 작성된 의견이 있어서 해당 토론을 삭제할수 없습니다.";

			redirectAttributes.addFlashAttribute("retMsg", retMsg);
			return "redirect:/lu/discuss/listDiscuss.do";
		}

		if( StringUtils.isBlank( discussVO.getDiscussId() ) ){
			retMsg = "삭제할 정보가 없습니다.";
		} else {

			deletetCnt = discussService.deleteDiscussInfo(discussVO);

			if(deletetCnt > 0){
				retMsg = "정상적으로 (삭제)처리되었습니다.!";
			}else{
				retMsg = "처리시 오류가 발생하였습니다.!";
			}
		}

		discussVO.setDiscussId(null);

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmDiscuss", discussVO);

		// View호출
		return "redirect:/lu/discuss/listDiscuss.do";
	}

	/**
	 * 토론의견 삭제
	 * @param discussVO
	 * @return DiscussVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/{userType}/discuss/deleteDiscussOpinion.do")
	public String deleteDiscussOpinion(@PathVariable("userType") String userType, @RequestParam Map<String, Object> commandMap, @ModelAttribute("frmDiscuss") DiscussVO discussVO, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "";
		int opinionCnt = 0;
		int deletetCnt = 0;

		//전체토론에 대한 의견수
		opinionCnt = discussService.getDiscussOpinionCommentCnt(discussVO);

		if(opinionCnt > 0){
			retMsg = "토론 의견에 대한 댓글이 있어서 해당 토론의견을 삭제할수 없습니다.";

			redirectAttributes.addFlashAttribute("retMsg", retMsg);
			return "redirect:/lu/discuss/listDiscuss.do";
		}

		if( StringUtils.isBlank(discussVO.getDiscussId()) && StringUtils.isBlank(discussVO.getDiscussOpinionId())){
			retMsg = "삭제할 정보가 없습니다.";
		} else {

			deletetCnt = discussService.deleteDiscussOpinion(discussVO);

			if(deletetCnt > 0){
				retMsg = "정상적으로 (삭제)처리되었습니다.!";
			}else{
				retMsg = "처리시 오류가 발생하였습니다.!";
			}
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmDiscuss", discussVO);

		// View호출
		return "redirect:/lu/discuss/listDiscuss.do";
	}

	/**
	 * 토론의견 댓글 삭제
	 * @param discussVO
	 * @return DiscussVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/{userType}/discuss/deleteDiscussOpinionComment.do")
	public String deleteDiscussOpinionComment(@PathVariable("userType") String userType, @RequestParam Map<String, Object> commandMap, @ModelAttribute("frmDiscuss") DiscussVO discussVO, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "";
		int opinionCnt = 0;
		int deletetCnt = 0;

		if( StringUtils.isBlank(discussVO.getDiscussOpinionId()) && StringUtils.isBlank(discussVO.getMemSeq())){
			retMsg = "삭제할 정보가 없습니다.";
		} else {

			deletetCnt = discussService.deleteDiscussComment(discussVO);

			if(deletetCnt > 0){
				retMsg = "정상적으로 (삭제)처리되었습니다.!";
			}else{
				retMsg = "처리시 오류가 발생하였습니다.!";
			}
		}

		discussVO.setMemSeq("");

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmDiscuss", discussVO);

		// View호출
		return "redirect:/lu/discuss/getDiscussOpinion.do";
	}


}
