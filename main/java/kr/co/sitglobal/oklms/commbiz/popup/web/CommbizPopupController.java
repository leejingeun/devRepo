/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016.11. 28         First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.commbiz.popup.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.co.sitglobal.aunuri.service.AunuriLinkService;
import kr.co.sitglobal.oklms.comm.util.Config;
import kr.co.sitglobal.oklms.comm.util.TextStringUtil;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.commbiz.cmmcode.vo.CommonCodeVO;
import kr.co.sitglobal.oklms.la.company.service.CompanyService;
import kr.co.sitglobal.oklms.la.company.vo.CompanyVO;
import kr.co.sitglobal.oklms.la.mail.service.MailService;
import kr.co.sitglobal.oklms.la.mail.vo.MailVO;
import kr.co.sitglobal.oklms.la.member.service.MemberService;
import kr.co.sitglobal.oklms.la.member.vo.MemberVO;
import kr.co.sitglobal.oklms.lu.activitydesc.service.ActivityNoteDescService;
import kr.co.sitglobal.oklms.lu.currproc.service.CurrProcService;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.member.service.MemberStdService;
import kr.co.sitglobal.oklms.lu.member.vo.ExcelMemberStdVO;
import kr.co.sitglobal.oklms.lu.qestnr.service.QestnrService;
import kr.co.sitglobal.oklms.lu.qestnr.vo.QestnrVO;
import kr.co.sitglobal.oklms.lu.traning.service.TraningService;
import kr.co.sitglobal.oklms.lu.traning.service.WeekTraningService;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningProcessMappingVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningVO;
import kr.co.sitglobal.oklms.lu.workcert.service.WorkCertService;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.fdl.string.EgovDateUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
* Controller 클래스에 대한 내용을 작성한다.
* @author 이진근
* @since 2016. 11. 28.
*/
@Controller
public class CommbizPopupController extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(CommbizPopupController.class);

	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;

	@Resource(name = "companyService")
	private CompanyService companyService;

	@Resource(name = "aunuriLinkService")
	private AunuriLinkService aunuriLinkService;

	@Resource(name = "qestnrService")
	private QestnrService qestnrService;

	@Resource(name = "currProcService")
	private CurrProcService currProcService;

	@Resource(name = "memberService")
	private MemberService memberService;

	@Resource(name = "mailService")
	private MailService mailService;

	@Resource(name = "traningService")
	private TraningService traningService;

	@Resource(name= "weekTraningService")
	private WeekTraningService weekTraningService;

	@Resource(name="activityNoteDescService")
	private ActivityNoteDescService activityNoteDescService;

	@Resource(name = "workCertService")
	private WorkCertService workCertService;


	@Resource(name = "MemberStdService")
	private MemberStdService memberStdService;

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

//	// 관리자 >  회원정보 팝업화면 이동 서비스
//	@RequestMapping(value = "/la/popup/popup/goPopupMemberInfo.do")
//	public String goPopupMemberInfo(@ModelAttribute("frmPop") MemberVO memberVO, ModelMap model) throws Exception {
//
//		String memSeq = memberVO.getMemSeqPop();
//		String lectureStat = memberVO.getLectureStat();
//		Integer PageSize = memberVO.getPageSize();
//		Integer PageIndex = memberVO.getPageIndex();
//
//		memberVO.setMemSeq(memSeq);
//		memberVO = memberService.getMember( memberVO );
//
//		LessonVO lessonVO = new LessonVO();
//		lessonVO.setMemSeq(memSeq);
//		lessonVO.setLectureStat(lectureStat);
//		lessonVO.setPageIndex(PageIndex);
//		lessonVO.setPageSize(PageSize);
//
//		List<LessonVO> resultList = lessonService.listStdMyLesson(lessonVO);
//
//
//		Integer pageSize = PageSize;
//		Integer page = PageIndex;
//		int totalCnt = 0;
//
//		if( 0 < resultList.size() ){
//			totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
//		}
//
//        model.addAttribute("pageSize", pageSize);
//        model.addAttribute("totalCount", totalCnt);
//        model.addAttribute("pageIndex", page);
//
//        PaginationInfo paginationInfo = new PaginationInfo();
//
//        paginationInfo.setCurrentPageNo(page);
//        paginationInfo.setRecordCountPerPage(lessonVO.getPageUnit());
//        paginationInfo.setPageSize(pageSize);
//        paginationInfo.setTotalRecordCount(totalCnt);
//
//        model.addAttribute("resultCnt", totalCnt );
//        model.addAttribute("paginationInfo", paginationInfo);
//        model.addAttribute("memberVO", memberVO);
//        model.addAttribute("memSeq", memSeq);
//        model.addAttribute("lectureStat", lectureStat);
//        model.addAttribute("resultList", resultList);
//
//		// View호출
//        return "oklms_popup/la/popup/memberInfoPopupList";
//	}
//
//	// 관리자 >  비스콤 콘텐츠 조회 팝업화면 이동 서비스
//	@RequestMapping(value = "/la/lecture/lectureLms/goPopupLectureMapList.do")
//	public String goPopupLectureMapList(@ModelAttribute("frmSearchLecture") LectureLmsVO lectureLmsVO, ModelMap model) throws Exception {
//
//		Integer PageSize = lectureLmsVO.getPageSize();
//		Integer PageIndex = lectureLmsVO.getPageIndex();
//
//		CommonCodeVO codeVO = new CommonCodeVO();
//		List<CommonCodeVO> myLectureCodeList = commonCodeService.selectMyLectureCodeList(codeVO);
//
//		List<LectureLmsVO> resultList = lectureLmsService.nonScromListLecturePop(lectureLmsVO);
//
//		Integer pageSize = PageSize;
//		Integer page = PageIndex;
//		int totalCnt = 0;
//
//		if( 0 < resultList.size() ){
//			totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
//		}
//
//        model.addAttribute("pageSize", pageSize);
//        model.addAttribute("totalCount", totalCnt);
//        model.addAttribute("pageIndex", page);
//
//        PaginationInfo paginationInfo = new PaginationInfo();
//
//        paginationInfo.setCurrentPageNo(page);
//        paginationInfo.setRecordCountPerPage(lectureLmsVO.getPageUnit());
//        paginationInfo.setPageSize(pageSize);
//        paginationInfo.setTotalRecordCount(totalCnt);
//
//        model.addAttribute("resultCnt", totalCnt );
//        model.addAttribute("paginationInfo", paginationInfo);
//        model.addAttribute("lectureLmsVO", lectureLmsVO);
//        model.addAttribute("lecType", lectureLmsVO.getLecType());
//        model.addAttribute("mobileYn", lectureLmsVO.getMobileYn());
//        model.addAttribute("resultList", resultList);
//        model.addAttribute("myLectureCode", myLectureCodeList);
//
//		// View호출
//        return "oklms_popup/la/popup/searchNonContentsPopupList";
//	}
//
//	// 관리자 >  비스콤 콘텐츠 선택 부모창에 필수값 전달
//	@RequestMapping(value = "/la/lecture/lectureLms/goPopupLectureMapSelectLecContent.do")
//	public String goPopupLectureMapSelectLecContent(@RequestParam("selectLcmsLecId") String selectLcmsLecId, ModelMap model) throws Exception {
//
//		String strLcmsLecId = selectLcmsLecId;
//
//		List<LectureSchLmsVO> resultList = lectureLmsService.listNonScormMapContentPop(strLcmsLecId);
//
//		model.addAttribute("lstContent", resultList);
//
//		// View호출
//        return "oklms_popup/la/popup/nonContentsPopupParentSend";
//	}
//
//	@RequestMapping(value = "/la/lecture/lectureLms/goPopupLectureMapPreview.do")
//	public String goPopupLectureMapPreview(@RequestParam("lecIdPop") String lecIdPop, @RequestParam("contentPath") String contentPath, ModelMap model) throws Exception {
//
//		String strLecId = lecIdPop;
//		String strContentPath = contentPath;
//		NonScormContentsVO nonScormContentsVO = new NonScormContentsVO();
//
//		nonScormContentsVO = lectureLmsService.getNonScormContentsStyle(strLecId);
//		nonScormContentsVO.setContentPath(strContentPath);
//
//		logger.debug("#### goPopupLectureMapPreview : nonScormContentsVO=" + nonScormContentsVO.toString() );
//
//		model.addAttribute("nscVO", nonScormContentsVO);
//
//		// View호출
//        return "oklms_popup/la/popup/previewContentPopup";
//	}
//
//	// 분반관리 팝업
//	@RequestMapping(value = "/la/lecture/lectureLms/goPopupLectureForPeriodClassList.do")
//	public String goPopupLectureForPeriodClassList(@ModelAttribute("frmLectureLms") LectureLmsVO lectureLmsVO, ModelMap model) throws Exception {
//		/*@RequestParam Map<String, Object> commandMap,
//		String strPeriodId = (String) commandMap.get("periodId");
//		String strLecId = (String) commandMap.get("lecId");*/
//
//		String strPeriodId = lectureLmsVO.getPeriodId();
//		String strLecId = lectureLmsVO.getLecId();
//
//		ClassVO classVO = new ClassVO();
//		PeriodVO periodVO = new PeriodVO();
//
//		classVO.setPeriodId(strPeriodId);
//		List<ClassVO> classList = classService.listClass(classVO);
//		List<ClassVO> tutorList = classService.tutorList(classVO);
//
//		periodVO.setPeriodId(strPeriodId);
//		periodVO = periodService.getPeriod(periodVO);
//
//		model.addAttribute("classList", classList);
//		model.addAttribute("tutorList", tutorList);
//		model.addAttribute("listSize", classList.size());
//		model.addAttribute("tutorListCount", tutorList.size());
//		model.addAttribute("lecId", strLecId);
//		model.addAttribute("periodId", strPeriodId);
//		model.addAttribute("lecName", periodVO.getLecName());
//		model.addAttribute("periodName", periodVO.getPeriodName());
//
//		// View호출
//        return "oklms_popup/la/popup/updatePeriodClassPopupList";
//	}
//
//	// 분반관리 팝업 화면에서 강사 검색 팝업 호출
//	@RequestMapping(value = "/la/lecture/lectureLms/goPopupLectureForPeriodClassLecturerList.do")
//	public String goPopupLectureForPeriodClassLecturerList(@ModelAttribute("frmLectureLms") LectureLmsVO lectureLmsVO, ModelMap model) throws Exception {
//
//		String classId = "";
//		String authType = "";
//		String memSeq = "";
//		classId = lectureLmsVO.getClassId();
//		authType = lectureLmsVO.getAuthType();
//		memSeq = lectureLmsVO.getMemSeq();
//
//		MemberVO memberVO = new MemberVO();
//		memberVO.setScsnYn("N");
//		memberVO.setDeleteYn("N");
//		memberVO.setSearchAuthGroupId(Config.DEFAULT_AUTH_INS_NEW);
//		memberVO.setSearchMemName(lectureLmsVO.getSearchWord1());
//		List<MemberVO> resultList = memberService.listMember(memberVO);
//
//		Integer pageSize = lectureLmsVO.getPageSize();
//		Integer page = lectureLmsVO.getPageIndex();
//		int totalCnt = 0;
//		if( 0 < resultList.size() ){
//			totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
//		}
//
//        model.addAttribute("pageSize", pageSize);
//        model.addAttribute("totalCount", totalCnt);
//        model.addAttribute("pageIndex", page);
//
//        PaginationInfo paginationInfo = new PaginationInfo();
//
//        paginationInfo.setCurrentPageNo(lectureLmsVO.getPageIndex());
//        paginationInfo.setRecordCountPerPage(lectureLmsVO.getPageUnit());
//        paginationInfo.setPageSize(lectureLmsVO.getPageSize());
//        paginationInfo.setTotalRecordCount(totalCnt);
//
//        model.addAttribute("resultCnt", totalCnt );
//        model.addAttribute("paginationInfo", paginationInfo);
//        model.addAttribute("lectureLcmsVO", lectureLmsVO);
//        model.addAttribute("resultList", resultList);
//        model.addAttribute("classId", classId);
//        model.addAttribute("authType", authType);
//        model.addAttribute("memSeq", memSeq);
//
//		// View호출
//        return "oklms_popup/la/popup/searchPeriodClassAdminPopupList";
//	}
//
//	@RequestMapping(value = "/la/lecture/lectureLms/insertLectureForPeriodClassPop.do")
//	public String insertLectureForPeriodClassPop(@RequestParam Map<String, Object> commandMap, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
//
//		String retMsg = "";
//		String strLecId = (String) commandMap.get("lecId");
//		String strPeriodId = (String) commandMap.get("periodId");
//		String strClassId = (String) commandMap.get("classId");
//		String strClassName = (String) commandMap.get("className");
//		int insertCnt = 0;
//		ClassVO classVO = new ClassVO();
//		LectureLmsVO lectureLmsVO = new LectureLmsVO();
//
//		classVO.setPeriodId(strPeriodId);
//		classVO.setClassName(strClassName);
//		insertCnt = classService.insertClass(classVO);
//
//		if( 0 < insertCnt ){
//			retMsg = "정상적으로 처리되었습니다.";
//		}else{
//			retMsg = "처리된 정보가 없습니다.";
//		}
//
//		lectureLmsVO.setLecId(strLecId);
//		lectureLmsVO.setPeriodId(strPeriodId);
//		lectureLmsVO.setClassId(strClassId);
//		lectureLmsVO.setClassName(strClassName);
//
//		redirectAttributes.addFlashAttribute("retMsg", retMsg);
//		redirectAttributes.addFlashAttribute("frmLectureLms", lectureLmsVO);
//
//		// View호출
//        return "redirect:/la/lecture/lectureLms/goPopupLectureForPeriodClassList.do";
//	}
//
//	@RequestMapping(value = "/la/lecture/lectureLms/updateLectureForPeriodClassPop.do")
//	public String updateLectureForPeriodClassPop(@RequestParam Map<String, Object> commandMap, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
//		String retMsg = "";
//		String strLecId = (String) commandMap.get("lecId");
//		String strPeriodId = (String) commandMap.get("periodId");
//		String strClassId = (String) commandMap.get("classId");
//		String strClassName = (String) commandMap.get("className");
//		int updateCnt = 0;
//		LectureLmsVO lectureLmsVO = new LectureLmsVO();
//		ClassVO classVO = new ClassVO();
//
//		if( StringUtils.isBlank( strClassId )){
//			retMsg = "수정할 정보가 없습니다.";
//		} else {
//			classVO.setClassId(strClassId);
//			classVO.setClassName(strClassName);
//			updateCnt = classService.updateClass(classVO);
//
//			if( 0 < updateCnt ){
//				retMsg = "정상적으로 처리되었습니다.";
//			}else{
//				retMsg = "처리된 정보가 없습니다.";
//			}
//		}
//
//		lectureLmsVO.setLecId(strLecId);
//		lectureLmsVO.setPeriodId(strPeriodId);
//		lectureLmsVO.setClassId(strClassId);
//		lectureLmsVO.setClassName(strClassName);
//
//		redirectAttributes.addFlashAttribute("retMsg", retMsg);
//		redirectAttributes.addFlashAttribute("frmLectureLms", lectureLmsVO);
//
//		// View호출
//        return "redirect:/la/lecture/lectureLms/goPopupLectureForPeriodClassList.do";
//	}
//
//	@RequestMapping(value = "/la/lecture/lectureLms/updateLectureForPeriodClassTutorPop.do")
//	public String updateLectureForPeriodClassTutorPop(@RequestParam Map<String, Object> commandMap, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
//
//		String retMsg = "";
//		String strPeriodId = (String) commandMap.get("periodId");
//		String strInsMemSeq = (String) commandMap.get("insMemSeq");
//		String strClassId = (String) commandMap.get("classId");
//		String strClassName = (String) commandMap.get("className");
//		String strMemSeq = (String) commandMap.get("memSeq");
//		String strAuthType = (String) commandMap.get("authType");
//		String strLecId = (String) commandMap.get("lecId");
//		int resultCnt = 0;
//		LectureAuthVO resultVO = new LectureAuthVO();
//		LectureAuthVO lectureAuthVO = new LectureAuthVO();
//		LectureLmsVO lectureLmsVO = new LectureLmsVO();
//
//		lectureAuthVO.setLecId(strLecId);
//		lectureAuthVO.setClassId(strClassId);
//		lectureAuthVO.setPeriodId(strPeriodId);
//		lectureAuthVO.setAuthType(strAuthType);
//		lectureAuthVO.setMemSeq(strMemSeq);
//		lectureAuthVO.setInsMemSeq(strInsMemSeq);
//		lectureLmsVO.setLecId(strLecId);
//		lectureLmsVO.setPeriodId(strPeriodId);
//		lectureLmsVO.setClassId(strClassId);
//		lectureLmsVO.setClassName(strClassName);
//		lectureLmsVO.setAuthType(strAuthType);
//		lectureLmsVO.setMemSeq(strMemSeq);
//		lectureLmsVO.setInsMemSeq(strInsMemSeq);
//
//		resultCnt = lectureAuthService.updateMemSeq(lectureAuthVO);
//
//		if( 0 < resultCnt ){
//			retMsg = "정상적으로 처리되었습니다.";
//		}else{
//			retMsg = "처리된 정보가 없습니다.";
//		}
//
//		redirectAttributes.addFlashAttribute("retMsg", retMsg);
//		redirectAttributes.addFlashAttribute("frmLectureLms", lectureLmsVO);
//
//		// View호출
//        return "redirect:/la/lecture/lectureLms/goPopupLectureForPeriodClassList.do";
//	}
//
//	@RequestMapping(value = "/la/lecture/lectureLms/deleteLectureForPeriodClassPop.do")
//	public String deleteLectureForPeriodClassPop(@RequestParam Map<String, Object> commandMap, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
//
//		String retMsg = "";
//		String strPeriodId = (String) commandMap.get("periodId");
//		String strInsMemSeq = (String) commandMap.get("insMemSeq");
//		String strClassId = (String) commandMap.get("classId");
//		String strLecId = (String) commandMap.get("lecId");
//
//		int deleteCnt1 = 0;
//		int deleteCnt2 = 0;
//		ClassVO classVO = new ClassVO();
//		LectureLmsVO lectureLmsVO = new LectureLmsVO();
//
//		if( StringUtils.isBlank(strLecId) && StringUtils.isBlank(strPeriodId) && StringUtils.isBlank(strClassId)){
//			retMsg = "삭제할 정보가 없습니다.";
//		} else {
//			classVO.setClassId(strClassId);
//			classVO.setPeriodId(strPeriodId);
//			classVO.setLecId(strLecId);
//
//			deleteCnt1 = classService.deleteClass(classVO);
//			if( !StringUtils.isBlank( strInsMemSeq )){
//				deleteCnt2 = classService.deleteClassTutor(classVO);
//			}
//
//			if( 0 < deleteCnt1 || 0 < deleteCnt2){
//				retMsg = "정상적으로 처리되었습니다.";
//			}else{
//				retMsg = "처리된 정보가 없습니다.";
//			}
//		}
//
//		lectureLmsVO.setLecId(strLecId);
//		lectureLmsVO.setPeriodId(strPeriodId);
//		lectureLmsVO.setClassId(strClassId);
//
//		redirectAttributes.addFlashAttribute("retMsg", retMsg);
//		redirectAttributes.addFlashAttribute("frmLectureLms", lectureLmsVO);
//
//		// View호출
//        return "redirect:/la/lecture/lectureLms/goPopupLectureForPeriodClassList.do";
//	}
//
//	// 관리자 > 강의명 선택 팝업 목록
//	@RequestMapping(value = "/la/survey/survey/goPopupLecSurveyList.do")
//	public String goPopupLecSurveyList(@ModelAttribute("frmSurvey") LectureLmsVO lectureLmsVO, ModelMap model) throws Exception {
//
//		List<LectureLmsVO> resultList = lectureLmsService.listTotalLecture(lectureLmsVO);
//
//		Integer pageSize = lectureLmsVO.getPageSize();
//		Integer page = lectureLmsVO.getPageIndex();
//		int totalCnt = 0;
//		if( 0 < resultList.size() ){
//			totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
//		}
//
//        model.addAttribute("pageSize", pageSize);
//        model.addAttribute("totalCount", totalCnt);
//        model.addAttribute("pageIndex", page);
//
//        PaginationInfo paginationInfo = new PaginationInfo();
//
//        paginationInfo.setCurrentPageNo(lectureLmsVO.getPageIndex());
//        paginationInfo.setRecordCountPerPage(lectureLmsVO.getPageUnit());
//        paginationInfo.setPageSize(lectureLmsVO.getPageSize());
//        paginationInfo.setTotalRecordCount(totalCnt);
//
//        model.addAttribute("resultCnt", totalCnt );
//        model.addAttribute("paginationInfo", paginationInfo);
//        model.addAttribute("lectureLmsVO", lectureLmsVO);
//        model.addAttribute("resultList", resultList);
//
//		// View호출
//        return "oklms_popup/la/popup/searchApplyLectureNamePopupList";
//	}
//
//	// 관리자 > 수료증관리 > SMS 보내기 팝업화면 이동 서비스
//	@RequestMapping(value = "/la/lesson/lesson/goPopupCertificateSmsSend.do")
//	public String goPopupCertificateSmsSend(@ModelAttribute("frmSmsPop") MemberVO memberVO, ModelMap model) throws Exception {
//
//		String memberName = "";
//		String lessonIds = "";
//		String recvMemSeqSet = "";
//		String trPhoneNo = "";
//		String trCallBack = "";
//		String sendSuccess = "";
//		SmsVO smsVO = new SmsVO();
//		LoginInfo loginInfo = new LoginInfo();
//        loginInfo.putSessionToVo(memberVO); // session의 정보를 VO에 추가.
//        sendSuccess = memberVO.getSendSuccess();
//
//        if("N".equals(sendSuccess)){
//			memberName = memberService.getMemNameGet(memberVO);
//			lessonIds = memberService.getMemTutorLessonId(memberVO);
//	        recvMemSeqSet = memberService.getMemSeqTutor(memberVO);
//	        trPhoneNo = memberService.getMemPhoneNo(memberVO);
//	        trCallBack = EgovProperties.getProperty("Globals.sms.sender.default.phoneno");
//
//	        smsVO.setReceiveName(memberName);
//	        smsVO.setReceiverLessonId(lessonIds);
//	        smsVO.setRecvMemSeqSet(recvMemSeqSet);
//	        smsVO.setTrPhone(trPhoneNo);
//	        smsVO.setSendName(memberVO.getSessionMemName());
//	        smsVO.setSenderMemSeq(memberVO.getSessionMemSeq());
//		}
//
//		model.addAttribute("memberVO", memberVO);
//		model.addAttribute("successYn", sendSuccess);
//		model.addAttribute("smsModel", smsVO);
//		model.addAttribute("trCallBack", trCallBack);
//
//		// View호출
//        return "oklms_popup/la/popup/smsSendMemberPopup";
//	}
//
//	// 관리자 > 회원관리 > SMS 보내기 팝업화면 이동 서비스
//	@RequestMapping(value = "/la/popup/popup/goPopupMemberInfoSmsSend.do")
//	public String goPopupMemberInfoSmsSend(@ModelAttribute("frmSmsPop") MemberVO memberVO, ModelMap model) throws Exception {
//
//		String memberName = "";
//		String lessonIds = "";
//		String recvMemSeqSet = "";
//		String trPhoneNo = "";
//		String trCallBack = "";
//		String sendSuccess = "";
//		SmsVO smsVO = new SmsVO();
//		LoginInfo loginInfo = new LoginInfo();
//        loginInfo.putSessionToVo(memberVO); // session의 정보를 VO에 추가.
//        sendSuccess = memberVO.getSendSuccess();
//
//		if("N".equals(sendSuccess)){
//			memberName = memberService.getMemNameGet(memberVO);
//			lessonIds = memberService.getMemTutorLessonId(memberVO);
//	        recvMemSeqSet = memberService.getMemSeqTutor(memberVO);
//	        trPhoneNo = memberService.getMemPhoneNo(memberVO);
//	        trCallBack = EgovProperties.getProperty("Globals.sms.sender.default.phoneno");
//
//	        smsVO.setReceiveName(memberName);
//	        smsVO.setReceiverLessonId(lessonIds);
//	        smsVO.setRecvMemSeqSet(recvMemSeqSet);
//	        smsVO.setTrPhone(trPhoneNo);
//	        smsVO.setSendName(memberVO.getSessionMemName());
//	        smsVO.setSenderMemSeq(memberVO.getSessionMemSeq());
//		}
//
//		model.addAttribute("memberVO", memberVO);
//		model.addAttribute("sendSuccess", sendSuccess);
//		model.addAttribute("smsModel", smsVO);
//		model.addAttribute("trCallBack", trCallBack);
//
//		// View호출
//        return "oklms_popup/la/popup/smsSendMemberPopup";
//	}
//
	// 관리자 > 회원관리 > 전체메일보내기 화면
	@RequestMapping(value = "/la/popup/popup/goPopupMemberInfoAllMailSendList.do")
	public String goPopupMemberInfoAllMailSendList(@ModelAttribute("frmMember") MemberVO memberVO, ModelMap model) throws Exception {

		LoginInfo loginInfo = new LoginInfo();
        loginInfo.putSessionToVo(memberVO); // session의 정보를 VO에 추가.

		CommonCodeVO codeVO = new CommonCodeVO();
		codeVO.setCodeGroup("");
		List<CommonCodeVO> authGroupCode = commonCodeService.selectAuthGroupCodeList(codeVO);

		model.addAttribute("authGroupCode", authGroupCode);
		model.addAttribute("memberVO", memberVO);

		// View호출
        return "oklms_popup/la/popup/mailAllSendPopup";
	}
//
//	// 관리자 > 수료증관리 > 선택한 학습자 메일보내기 화면
//	@RequestMapping(value = "/la/lesson/lesson/goPopupCertificateMailSendList.do")
//	public String goPopupCertificateMailSendList(@ModelAttribute("frmLesson") MemberVO memberVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
//		String retMsg = "";
//		String memberName = "";
//        String emailset = "";
//        String recvMemSeqSet = "";
//        MailVO mailVO = new MailVO();
//
//		LoginInfo loginInfo = new LoginInfo();
//        loginInfo.putSessionToVo(memberVO); // session의 정보를 VO에 추가.
//        memberVO.setMemSeq(memberVO.getMemSeqPop());
//
//        memberName = memberService.getMemNameGet(memberVO);
//        emailset = memberService.getMemTypeEmail(memberVO);
//        recvMemSeqSet = memberService.getMemSeq(memberVO);
//
//        mailVO.setRecvEmailSet(emailset);
//        mailVO.setReceiveName(memberName);
//        mailVO.setRecvMemSeqSet(recvMemSeqSet);
//        mailVO.setSendName(memberVO.getSessionMemName());
//        mailVO.setSenderMemSeq(memberVO.getSessionMemSeq());
//
//		model.addAttribute("memberVO", memberVO);
//		model.addAttribute("mailModel", mailVO);
//		model.addAttribute("returnUrlGubun", "certificate");
//
//		// View호출
//        return "oklms_popup/la/popup/mailSendPopup";
//	}
//
	// 관리자 > 회원관리 > 선택한 학습자 메일보내기 화면
	@RequestMapping(value = "/la/popup/popup/goPopupMemberInfoMailSendList.do")
	public String goPopupMemberInfoMailSendList(@ModelAttribute("frmMember") MemberVO memberVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		String memberName = "";
        String emailset = "";
        String recvMemSeqSet = "";
        MailVO mailVO = new MailVO();

		LoginInfo loginInfo = new LoginInfo();
        loginInfo.putSessionToVo(memberVO);  //session의 정보를 VO에 추가.

        memberName = memberService.getMemNameGet(memberVO);
        emailset = memberService.getMemTypeEmail(memberVO);
        recvMemSeqSet = memberService.getMemSeq(memberVO);

        mailVO.setRecvEmailSet(emailset);
        mailVO.setReceiveName(memberName);
        mailVO.setRecvMemSeqSet(recvMemSeqSet);
        mailVO.setSendName(memberVO.getSessionMemName());
        mailVO.setSenderMemSeq(memberVO.getSessionMemSeq());

		model.addAttribute("memberVO", memberVO);
		model.addAttribute("mailModel", mailVO);
		model.addAttribute("returnUrlGubun", "member");

		// View호출
        return "oklms_popup/la/popup/mailSendPopup";
	}

	// 관리자 > 회원관리 > 선택한 학습자 SMS보내기 화면
	@RequestMapping(value = "/la/popup/popup/goPopupMemberInfoSmsSendList.do")
	public String goPopupMemberInfoSmsSendList(@ModelAttribute("frmMember") MemberVO memberVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		String trCallback = "";
		String memberNameArr = "";
        String memerPhoneNoArr = "";
        String recvMemSeqSet = "";
        MailVO mailVO = new MailVO();

		LoginInfo loginInfo = new LoginInfo();
        loginInfo.putSessionToVo(memberVO);  //session의 정보를 VO에 추가.

        trCallback = EgovProperties.getProperty("Globals.sms.sender.default.phoneno");
        memberNameArr = memberService.getMemNameGet(memberVO);
        memerPhoneNoArr = memberService.getMemPhoneNo(memberVO);
        recvMemSeqSet = memberService.getMemSeq(memberVO);

        mailVO.setReceiverHp(memerPhoneNoArr);
        mailVO.setReceiveName(memberNameArr);
        mailVO.setRecvMemSeqSet(recvMemSeqSet);
        mailVO.setSendName(memberVO.getSessionMemName());
        mailVO.setSenderMemSeq(memberVO.getSessionMemSeq());

		model.addAttribute("memberVO", memberVO);
		model.addAttribute("mailModel", mailVO);
		model.addAttribute("sendName", mailVO.getSendName());
		model.addAttribute("trCallback", trCallback);
		model.addAttribute("receiveName", memberNameArr);
		model.addAttribute("trPhone", memerPhoneNoArr);

		// View호출
        return "oklms_popup/la/popup/smsSendPopup";
	}

	// 관리자 > 아이디중복체크 팝업화면 이동 서비스
	@RequestMapping(value = "/la/popup/popup/goPopupMemberInfoIdCheck.do")
	public String goPopupMemberInfoIdCheck(@RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

		String memberId = (String) commandMap.get("memIdPop");

		logger.debug("#### goPopupMemberIdCheck : memberId=" + memberId );

		MemberVO memberVO = new MemberVO();
		memberVO.setMemId(memberId);
		int memIdCount = memberService.getMemberCnt(memberVO);

		model.addAttribute("memIdCount", memIdCount);
		model.addAttribute("memberId", memberId);

		// View호출
        return "oklms_popup/la/popup/memberIdCheckPopup";
	}

	// 관리자 > 아이디중복체크 팝업화면 이동 서비스
	@RequestMapping(value = "/lu/popup/goPopupSearchMemberIdCheck.do")
	public String goPopupMemberIdCheck(@RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

		String memberId = (String) commandMap.get("memIdPop");

		logger.debug("#### 사용자 회원ID 중복여부체크 : memberId=" + memberId );

		MemberVO memberVO = new MemberVO();
		memberVO.setMemId(memberId);
		int memIdCount = memberService.getMemberCnt(memberVO);

		model.addAttribute("memIdCount", memIdCount);
		model.addAttribute("memberId", memberId);

		// View호출
        return "oklms_popup/la/popup/memberIdCheckPopup";
	}

	// 관리자 > 회원관리 > 전체메일보내기
	@RequestMapping(value = "/la/popup/popup/saveMemberInfoAllMailSend.do")
	public String saveMemberInfoAllMailSend(@ModelAttribute("frmMail") MailVO mailVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
        String strTemplateFile = "";
        String strTempalte = "";
        String successYn = "N";
        String recvEmailSet = "";
        String recvMemSeqSet = "";
        String mailContent = "";
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.putSessionToVo(mailVO); // session의 정보를 VO에 추가.

        mailContent = mailVO.getMailContent();
        mailContent = mailContent.replace("context", TextStringUtil.getHtmlString(mailContent));

        mailVO.setMailClass(Config.MAIL_CLASS_ENTER); //회원가입
        mailVO.setMailContent(mailContent);
        mailVO.setMsgType(1);

        recvEmailSet = mailService.getMemTypeEmailSet(mailVO);
        mailVO.setRecvEmailSet( recvEmailSet );//여기 수정한다.

        //RecvMemSeq 값을 안가져 올때 값을 가져온다.
        if(null == mailVO.getRecvMemSeqSet() || "".equals(mailVO.getRecvMemSeqSet())){
        	recvMemSeqSet = mailService.getMemSeqEmailSet(mailVO);
        	mailVO.setRecvMemSeqSet( recvMemSeqSet );//여기 수정한다.
        }
        mailVO.setSendEmail(mailVO.getSessionEmail());
        mailVO.setSenderMemSeq(mailVO.getSessionMemSeq());

        successYn = mailService.sendMail(mailVO);

        redirectAttributes.addFlashAttribute("successYn", successYn);

		// View호출
        return "redirect:/la/popup/popup/goPopupMemberInfoAllMailSendList.do";
	}

	// 관리자 > 회원관리 > 선택회원 메일보내기
	@RequestMapping(value = "/la/member/member/saveMemberMailSend.do")
	public String saveMemberMailSend(@ModelAttribute("frmMail") MailVO mailVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
        String strTemplateFile = "";
        String strTempalte = "";
        String successYn = "N";
        String recvEmailSet = "";
        String recvMemSeqSet = "";
        String mailContent = "";
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.putSessionToVo(mailVO); // session의 정보를 VO에 추가.

        mailContent = mailVO.getMailContent();
        mailContent = mailContent.replace("context", TextStringUtil.getHtmlString(mailContent));

        mailVO.setMsgType(1);
        mailVO.setSendEmail(mailVO.getSessionEmail());
        mailVO.setMailClass(Config.MAIL_CLASS_DIRECT); //메일유형(직접선택)

        mailService.sendMail(mailVO);

        successYn = "Y";
        redirectAttributes.addFlashAttribute("successYn", successYn);

        return "redirect:/la/popup/popup/goPopupMemberInfoMailSendList.do";
	}

	// 관리자 > 회원관리 > 회원관리 등록 및 수정화면에서 기업정보 목록 팝업 호출시
	@RequestMapping(value = "/la/popup/popup/goPopupCompanyList.do")
	public String goPopupCompanyList(@ModelAttribute("frmCompany") CompanyVO companyVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
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
        paginationInfo.setRecordCountPerPage(companyVO.getPageUnit());
        paginationInfo.setPageSize(companyVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);

        model.addAttribute("companyVO", companyVO);
        model.addAttribute("resultList", resultList);

		// View호출
        return "oklms_popup/la/popup/searchCompanyListPopup";
	}


//	// 관리자 > 회원관리 > SMS보내기
//	@RequestMapping(value = "/la/member/member/saveMemberSmsSend.do")
//	public String saveMemberSmsSend(@ModelAttribute("frmMail") SmsVO smsVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
//		String sender = "";
//        String trPhone = "";
//        String reservation = "";
//        String useFlag = "";
//        String smsContent = "";
//        LoginInfo loginInfo = new LoginInfo();
//        loginInfo.putSessionToVo(smsVO); // session의 정보를 VO에 추가.
//        SmsVO sms =  new SmsVO();
//
//        sender = smsVO.getTrCallBack();
//        trPhone = smsVO.getTrPhone();
//        reservation = smsVO.getReservation();
//        useFlag = smsVO.gettFlag();
//        smsContent = smsVO.getSmsContent();
//
//        sms.setTrCallBack(sender);
//		sms.setTrPhone(trPhone);
//		sms.setTrSenddate(reservation);
//		sms.settFlag(useFlag);
//		sms.setTrMsgtype("0");
//		sms.setTrMsg(smsContent);
//		sms.setSenderMemSeq(smsVO.getSenderMemSeq());		//보내는 사람 SEQ
//		sms.setRecvMemSeqSet(smsVO.getRecvMemSeqSet());		//받는 사람 SEQ
//		sms.setReceiverLessonId(smsVO.getReceiverLessonId());	//받는 사람 LESSON_ID
//
//		mailService.insertSms(sms);
//
//        MemberVO memberVO = new MemberVO();
//        memberVO.setSendSuccess("Y");
//        redirectAttributes.addFlashAttribute("frmSmsPop", memberVO);
//
//		// View호출
//        return "redirect:/la/popup/popup/goPopupMemberInfoSmsSend.do";
//	}
//
	/**
	 * 팝업 페이지를 호출한다.
	 * egov 의 /cop/com/openPopup.do 를 대체함.
	 * @param userVO
	 * @param sessionVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{serviceArea}/commbiz/popup/openPopupWindow.do")
	public String openPopupWindow(@PathVariable("serviceArea") String serviceArea , @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

		String requestUrl = (String) commandMap.get("requestUrl");
		String trgetId = (String) commandMap.get("trgetId");
		String width = (String) commandMap.get("width");
		String height = (String) commandMap.get("height");
		String typeFlag = (String) commandMap.get("typeFlag");

		if (trgetId != null && trgetId != "") {
			if (typeFlag != null && typeFlag != "") {
				model.addAttribute("requestUrl", requestUrl + "?trgetId=" + trgetId + "&PopFlag=Y&typeFlag=" + typeFlag);
			} else {
				model.addAttribute("requestUrl", requestUrl + "?trgetId=" + trgetId + "&PopFlag=Y");
			}
		} else {
			if (typeFlag != null && typeFlag != "") {
				model.addAttribute("requestUrl", requestUrl + "?PopFlag=Y&typeFlag=" + typeFlag);
			} else {
				model.addAttribute("requestUrl", requestUrl + "?PopFlag=Y");
			}

		}

		model.addAttribute("width", width);
		model.addAttribute("height", height);

		return "oklms_popup/" + serviceArea + "/egovframework/com/cop/com/EgovModalPopupFrame";
	}

	// /lu/popup/goPopupTraningProcessManage.do

	// 관리자 > 기업사업자번호 중복체크 팝업화면 이동 서비스
	@RequestMapping(value = "/{userType}/popup/popup/goPopupCompanyInfoIdCheck.do")
	public String goPopupCompanyInfoIdCheck(@PathVariable("userType") String userType,@RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

		String companyNo = (String) commandMap.get("companyNoPop");

		logger.debug("#### goPopupCompanyInfoIdCheck : companyNo=" + companyNo );

		CompanyVO companyVO =  new CompanyVO();
		companyVO.setCompanyNo(companyNo);

		int companyNoCount = companyService.getCompanyNoCnt(companyVO);
		model.addAttribute("companyNoCount", companyNoCount);
		model.addAttribute("companyNo", companyNo);

		// View호출
        return "oklms_popup/la/popup/companyIdCheckPopup";
	}

	/**
	 * 개설강좌 검색 팝업 모듈
	 * @param currProcVO
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/popup/goPopupSearchSubjectName.do")
	public String goPopupSearchSubjectName(@ModelAttribute("frmTraning") CurrProcVO currProcVO, ModelMap model) throws Exception {

		if("".equals(currProcVO.getSearchYyyyCd()) || currProcVO.getSearchYyyyCd() == null){
			System.out.println("CurrentYear :: "+EgovDateUtil.getCurrentYearAsString());
			currProcVO.setSearchYyyyCd(EgovDateUtil.getCurrentYearAsString());
		}

		List<CurrProcVO> resultList = currProcService.listSubjectSeach(currProcVO);

		Integer pageSize = currProcVO.getPageSize();
		Integer page = currProcVO.getPageIndex();
		int totalCnt = 0;
		if( 0 < resultList.size() ){
			totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
		}

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(currProcVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(currProcVO.getPageSize());
        paginationInfo.setPageSize(currProcVO.getPageUnit());
        paginationInfo.setTotalRecordCount(totalCnt);

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("currProcVO", currProcVO);
        model.addAttribute("resultSubjectSeachList", resultList);
		model.addAttribute("parmCount", currProcVO.getCount());
		

		// View호출
		return "oklms_popup/lu/popup/searchSubjectNameListPopup";
	}

	/**
	 * 기업체 훈련과정에 메핑된 사용자 검색
	 * @param currProcVO
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/popup/goPopupSearchCompanyMappingMemberInfo.do")
	public String goPopupSearchCompanyMappingMemberInfo(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmMember") ExcelMemberStdVO memberStdVO, ModelMap model) throws Exception {

		if("COT".equals(memberStdVO.getMemType())){
			//기업현장교사 목록 조회
			List<ExcelMemberStdVO> listMemberCot = memberStdService.listMemberCot( memberStdVO );
			model.addAttribute("resultSeachList", listMemberCot);
		} else {
			//HRD담당자 목록 조회
			List<ExcelMemberStdVO> listMemberCcm = memberStdService.listMemberCcm( memberStdVO );
			model.addAttribute("resultSeachList", listMemberCcm);
		}

		// View호출
		return "oklms_popup/lu/popup/searchCompanyTraningMemberListPopup";
	}

	/**
	 * 기업별 훈련과정에 메핑된 개설강좌 검색 팝업 모듈
	 * @param currProcVO
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/popup/goPopupSearchCompanySubjectName.do")
	public String goPopupSearchCompanySubjectName(@ModelAttribute("frmTraning") CurrProcVO currProcVO, ModelMap model) throws Exception {

/*		if("".equals(currProcVO.getSearchYyyyCd()) || currProcVO.getSearchYyyyCd() == null){
			System.out.println("CurrentYear :: "+EgovDateUtil.getCurrentYearAsString());
			currProcVO.setSearchYyyyCd(EgovDateUtil.getCurrentYearAsString());
		}*/

		List<CurrProcVO> resultList = currProcService.listTrainSubjectSeach(currProcVO);

		Integer pageSize = currProcVO.getPageSize();
		Integer page = currProcVO.getPageIndex();
		int totalCnt = 0;
		if( 0 < resultList.size() ){
			totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
		}

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(currProcVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(currProcVO.getPageUnit());
        paginationInfo.setPageSize(currProcVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("currProcVO", currProcVO);
        model.addAttribute("resultSubjectSeachList", resultList);
		model.addAttribute("parmCount", currProcVO.getCount());

		// View호출
		return "oklms_popup/lu/popup/searchSubjectNameListPopup";
	}

	/**
	 * 사용자관리 > 기업별 훈련과정 검색 팝업 모듈
	 * @param traningMappingVO
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/popup/goPopupSearchTraingName.do")
	public String goPopupSearchTraingName(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmMember") TraningProcessMappingVO traningMappingVO, ModelMap model) throws Exception {

		LOGGER.debug("#### paramMap ==> "+paramMap);
		LOGGER.debug("#### traningMappingVO ==> "+traningMappingVO);

		traningMappingVO.setTraningProcessId(null);

		List<TraningProcessMappingVO> resultList = traningService.listTraningProcessInfo(traningMappingVO);

		Integer pageSize = traningMappingVO.getPageSize();
		Integer page = traningMappingVO.getPageIndex();
		int totalCnt = 0;
		if( 0 < resultList.size() ){
			LOGGER.debug("#### totalCnt ==> "+resultList.size());
			totalCnt = resultList.size();
		}

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(traningMappingVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(traningMappingVO.getPageUnit());
        paginationInfo.setPageSize(traningMappingVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("traningVO", traningMappingVO);
        model.addAttribute("resultTraningProcessSeachList", resultList);

		// View호출
		return "oklms_popup/lu/popup/searchTraningProcessInfoListPopup";
	}

	/**
	 * 사용자관리 > 기업현장교사에 기업별 훈련과정 메핑할 교과목검색 팝업
	 * @param currProcVO
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/popup/goPopupSearchTrainSubject.do")
	public String goPopupSearchTrainSubject(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmMember") CurrProcVO currProcVO, ModelMap model) throws Exception {

		//String searchYyyyCd = StringUtils.defaultString( (String)paramMap.get("searchYyyyCd") , "" );

		/*if("".equals(currProcVO.getSearchYyyyCd()) || currProcVO.getSearchYyyyCd() == null){
			System.out.println("CurrentYear :: "+EgovDateUtil.getCurrentYearAsString());
			currProcVO.setSearchYyyyCd(EgovDateUtil.getCurrentYearAsString());
		}*/

		List<CurrProcVO> resultList = currProcService.listTrainSubjectSeach(currProcVO);

		Integer pageSize = currProcVO.getPageSize();
		Integer page = currProcVO.getPageIndex();
		int totalCnt = 0;
		if( 0 < resultList.size() ){
			totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
		}

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(currProcVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(currProcVO.getPageUnit());
        paginationInfo.setPageSize(currProcVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("currProcVO", currProcVO);
        model.addAttribute("resultTrainSubjectSeachList", resultList);
        model.addAttribute("resultTrainSubjectSeachListCnt", resultList.size());
        if(resultList.size() > 0){
        	model.addAttribute("nowDate", resultList.get(0).getNowDate());
        }

		// View호출
		return "oklms_popup/lu/popup/searchTrainSubjectListPopup";
	}

	/**
	 * 사용자관리 > 기업현장교사에 기업별 훈련과정 메핑된 교과목검색 팝업 상세
	 * @param currProcVO
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/popup/goPopupSearchTrainSubjectRead.do")
	public String goPopupSearchTrainSubjectRead(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmMember") CurrProcVO currProcVO, ModelMap model) throws Exception {

		String memName = StringUtils.defaultString((String)paramMap.get("memName"),"");

		List<CurrProcVO> resultList = currProcService.listCotMappingTrainSubjectDetail(currProcVO);

		Integer pageSize = currProcVO.getPageSize();
		Integer page = currProcVO.getPageIndex();
		int totalCnt = 0;
		if( 0 < resultList.size() ){
			totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
		}

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(currProcVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(currProcVO.getPageUnit());
        paginationInfo.setPageSize(currProcVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("currProcVO", currProcVO);
        model.addAttribute("resultList", resultList);
        model.addAttribute("memName", memName);

        /*if(resultList.size() > 0){
        	model.addAttribute("memName", resultList.get(0).getMemName());
        }*/


		// View호출
		return "oklms_popup/lu/popup/searchTrainSubjectReadListPopup";
	}

	/**
	 * 사용자 공통 팝업 모듈
	 * @param searchKeyword
	 * @param uiGubun
	 * @param returnUrl
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/popup/goPopupSearch.do")
	public String goPopupSearch(@RequestParam Map<String, Object> commandMap,ModelMap model) throws Exception {

		String searchKeyword = (String) commandMap.get("searchKeyword");
		String uiGubun = (String) commandMap.get("uiGubun");
		String returnUrl = (String) commandMap.get("returnUrl");
		String returnMsg = "POPUP_LIST_FAIL";
		CompanyVO companyVO =  new CompanyVO();
		TraningVO traningVO =  new TraningVO();

		logger.debug("###################################### goPopupSearch.do Param Start ");
		logger.debug("#### searchKeyword : " + searchKeyword );
		logger.debug("#### uiGubun : " + uiGubun );
		logger.debug("#### returnUrl : " + returnUrl );
		logger.debug("###################################### goPopupSearch.do Param End ");

		//센터당당자 > 담당기업체관리 > 훈련과정관리 > 기업체검색 팝업
		if("traningProcessCompanyPop".equals(uiGubun)
		|| "traningProcessCompanyReadPop".equals(uiGubun)){
			companyVO.setSearchCompanyName(searchKeyword);

			List<CompanyVO> listCompany = companyService.listCompanySearch(companyVO);
			returnMsg = "POPUP_LIST_SUCCESS";

			model.addAttribute("resultCompanyList", listCompany);
		}

		//센터당당자 > 담당기업체관리 > 훈련과정관리 > 훈련과정검색 팝업
		if("traningProcessPop".equals(uiGubun)){
			traningVO.setSearchKeyword(searchKeyword);

			List<TraningVO> listTraningProcessManage = traningService.listTraningProcessManage(traningVO);
			returnMsg = "POPUP_LIST_SUCCESS";

			model.addAttribute("resultTraningProcessList", listTraningProcessManage);
		}

		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("uiGubun", uiGubun);
		model.addAttribute("returnMsg", returnMsg);

		// View호출
		return "oklms"+ returnUrl;
	}

	/**
	 * 기업체정보를 검색한다.
	 * @param searchKeyword
	 * @param returnUrlAt
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{userType}/popup/goPopupCompanyInfoSearch.do")
	public String goPopupCompanyInfoSearch(@PathVariable("userType") String userType,@RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

		String searchKeyword = (String) commandMap.get("searchKeyword");
		String returnUrlAt = (String) commandMap.get("returnUrlAt");

		logger.debug("#### searchKeyword :=" + searchKeyword );
		logger.debug("#### returnUrlAt :=" + returnUrlAt );

		CompanyVO companyVO =  new CompanyVO();
		companyVO.setSearchCompanyName(searchKeyword);

		List<CompanyVO> listCompany = companyService.listCompanySearch(companyVO);
		model.addAttribute("listCompany", listCompany);
		model.addAttribute("returnUrlAt", returnUrlAt);

		// View호출
        return "oklms_popup/la/popup/searchCompanyInfoPopup";
	}

	/**
	 * 훈련과정을 검색한다.
	 * @param searchKeyword1
	 * @param searchKeyword2
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{userType}/popup/goPopupTraningProcessSearch.do")
	public String goPopupTraningProcessSearch(@PathVariable("userType") String userType,@RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

		String searchKeyword = (String) commandMap.get("searchKeyword");

		logger.debug("#### searchKeyword :=" + searchKeyword );

		TraningVO searchVO =  new TraningVO();
		searchVO.setSearchKeyword(searchKeyword);

		List<TraningVO> listTraningProcessManage = traningService.listTraningProcessManage(searchVO);
		model.addAttribute("listTraningProcess", listTraningProcessManage);

		// View호출
        return "oklms_popup/la/popup/searchTraningProcessPopup";
	}

	/**
	 * 센터담당자 > 평가결과조회 목록에서 해당 학습근로자 조회버튼 클릭시 수강자별 레포트 팝업 호출
	 * @param memId
	 * @param memName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/evalPlan/popupEvalResultMemberReportView.do")
	public String popupEvalResultMemberReportView(@RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

		String memId = StringUtils.defaultString((String)commandMap.get("memId"),"");
		String memName = StringUtils.defaultString((String)commandMap.get("memName"),"");

		logger.debug("#### memId :=" + memId );
		logger.debug("#### memName :=" + memName );

		model.addAttribute("memId", memId);
		model.addAttribute("memName", memName);

		// View호출
        return "oklms_popup/lu/popup/evalResultMemberStdInfoReportPopup";
	}

	/**
	 * 설문응답 학습자별 기타의견보기
	 * @param memId
	 * @param memName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/qestnr/popupQestnrEtcAnswerCnView.do")
	public String popupQestnrEtcAnswerCnView(@RequestParam Map<String, Object> commandMap, @ModelAttribute("frmQestnr") QestnrVO qestnrVO, ModelMap model) throws Exception {

		List<QestnrVO> listQestnrEtcAnswerCn = qestnrService.listQestnrEtcAnswerCn(qestnrVO);

		model.addAttribute("resultList", listQestnrEtcAnswerCn);

		// View호출
        return "oklms_popup/lu/popup/qestnrEtcAnswerCnPopup";
	}

	/**
	 * 설문응답 학습자별 결과보기 프린트
	 * @param memId
	 * @param memName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/qestnr/popupQestnrPrint.do")
	public String popupQestnrPrint(@RequestParam Map<String, Object> commandMap, @ModelAttribute("frmQestnr") QestnrVO qestnrVO, ModelMap model) throws Exception {

		CurrProcVO currProcVO = new CurrProcVO();
		CurrProcVO currProcReadVO = new CurrProcVO();
		QestnrVO readQestnrInfoVO = new QestnrVO();
		QestnrVO readQestnrItemVO = new QestnrVO();

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(qestnrVO); // session의 정보를 VO에 추가.

		currProcVO.setYyyy(qestnrVO.getYyyy());
		currProcVO.setTerm(qestnrVO.getTerm());
		currProcVO.setSubjectCode(qestnrVO.getSubjectCode());
		currProcVO.setSubClass(qestnrVO.getSubClass());

		currProcReadVO = currProcService.getMySubjectInfo(currProcVO);
	    readQestnrInfoVO = qestnrService.getQestnrInfo(qestnrVO);
	    readQestnrItemVO = qestnrService.getQestnrItem(qestnrVO);

		model.addAttribute("currProcReadVO", currProcReadVO);  			//개설강좌 정보
		model.addAttribute("readQestnrInfoVO", readQestnrInfoVO);    	//설문 상세조회
		model.addAttribute("readQestnrItemVO", readQestnrItemVO); 	  	//설문문항 목록
		model.addAttribute("qestnrVO", qestnrVO);  						//설문 파라메터정보

		// View호출
        return "oklms_popup/lu/popup/qestnrReadPrintPopup";
	}

	/**
	 * 팝업창을 오픈 한다.
	 * @param commandMap
	 * @param popupManageVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/commbiz/uss/ion/pwm/openPopupManage.do")
	public String egovPopupManagePopupOpen(@RequestParam("fileUrl") String fileUrl, @RequestParam("stopVewAt") String stopVewAt, @RequestParam("popupId") String popupId,
			ModelMap model) throws Exception {

		model.addAttribute("stopVewAt", stopVewAt);
		model.addAttribute("popupId", popupId);
		return fileUrl;
	}





}
