package kr.co.sitglobal.oklms.lu.today.web;
 
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController; 
import kr.co.sitglobal.oklms.lu.activity.service.ActivityService;
import kr.co.sitglobal.oklms.lu.activity.vo.ActivityVO;  
import kr.co.sitglobal.oklms.lu.activity.vo.SubjweekStdVO; 
import kr.co.sitglobal.oklms.lu.today.service.TodayService; 
import kr.co.sitglobal.oklms.lu.today.vo.TodayVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningScheduleVO;
import kr.co.sitglobal.oklms.lu.traningchange.service.TraningChangeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; 

import egovframework.com.cmm.LoginVO;
import egovframework.com.cop.bbs.service.BoardVO;

@Controller
public class TodayController  extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(TodayController.class);
	
	@Resource(name = "TodayService")
	private TodayService todayService;
	
	@Resource(name = "ActivityService")
	private ActivityService activityService;
	
	@Resource(name = "traningChangeService")
	private TraningChangeService traningChangeService;
	
	@RequestMapping(value = "/lu/today/lmsUserTodayPage.do")
	public String lmsUserTodayPage(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
		
		LOGGER.debug("#### /lu/today/lmsUserTodayPage.do" );
		// 세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		String viewPage="oklms/lu/today/lmsUserTodayPage";
		TodayVO todayVO = new TodayVO();
		loginInfo.putSessionToVo(todayVO);
		
		
		if(loginVO.getMemType().equals("STD")){
			viewPage="oklms/lu/today/lmsUserTodayPage";
			// 학습활동서 미작성건
			ActivityVO activityVO = new ActivityVO();
			loginInfo.putSessionToVo(activityVO);
			List<ActivityVO> listActivityNotMake = activityService.listActivityNotMake(activityVO);
			model.addAttribute("listActivityNotMake", listActivityNotMake);
			
			// 학습활동서 미작성건
			SubjweekStdVO subjweekStdVO = new SubjweekStdVO();
			loginInfo.putSessionToVo(subjweekStdVO);
			List<SubjweekStdVO> listWeekActivityMakeStd = activityService.listWeekActivityMakeStd(subjweekStdVO);
			model.addAttribute("listWeekActivityMakeStd", listWeekActivityMakeStd);
			  
			// QNA 답변확인
			BoardVO boardVO= new BoardVO(); 
			boardVO.setMemSeq(loginInfo.getMemSeq());
			List<BoardVO> listQnaStd = todayService.listQnaStd(boardVO);
			model.addAttribute("listQnaStd", listQnaStd);
			
			//과제미제출건
			List<TodayVO> listReportSubmitStd =  todayService.listReportSubmitStd (todayVO);
			model.addAttribute("listReportSubmitStd", listReportSubmitStd);
			
			//팀프로젝트 미제출건
			List<TodayVO> listTeamProjectSubmitStd =  todayService.listTeamProjectSubmitStd (todayVO);
			model.addAttribute("listTeamProjectSubmitStd", listTeamProjectSubmitStd);

			//토론 미참여건
			List<TodayVO> listDiscussStd =  todayService.listDiscussStd (todayVO);
			model.addAttribute("listDiscussStd", listDiscussStd);

			//온라인교과 미수강건
			List<TodayVO> listOnlineStd =  todayService.listOnlineStd (todayVO);
			model.addAttribute("listOnlineStd", listOnlineStd);
			
			//재직증빙서류 미승인건	
			List<TodayVO> listWorkCertStd =  todayService.listWorkCertStd (todayVO);
			model.addAttribute("listWorkCertStd", listWorkCertStd);
			
		}else if(loginVO.getMemType().equals("PRT")){

			//교수
			viewPage="oklms/lu/today/lmsUserTodayPagePrt";
			//훈련일지작성
			List<TodayVO> listTraningNotePrt = todayService.listTraningNotePrt(todayVO);
			model.addAttribute("listTraningNotePrt", listTraningNotePrt);
			//학습활동서확인
			List<TodayVO> listActivityNotePrt  = todayService.listActivityNotePrt(todayVO);
			model.addAttribute("listActivityNotePrt", listActivityNotePrt);
			//과제제출현황
			List<TodayVO> listReportSubmitPrt =  todayService.listReportSubmitPrt (todayVO);
			model.addAttribute("listReportSubmitPrt", listReportSubmitPrt);
			//팀프로젝트제출현황
			List<TodayVO> listTeamProjectSubmitPrt =  todayService.listTeamProjectSubmitPrt (todayVO);
			model.addAttribute("listTeamProjectSubmitPrt", listTeamProjectSubmitPrt);
			
			
		}else if(loginVO.getMemType().equals("COT")){
			//기업현장교사
			viewPage="oklms/lu/today/lmsUserTodayPageCot";
			//훈련일지작성 
			List<TodayVO> listTraningNoteCot = todayService.listTraningNoteCot(todayVO);
			model.addAttribute("listTraningNoteCot", listTraningNoteCot);
			//학습활동서확인
			List<TodayVO> listActivityNoteCot  = todayService.listActivityNoteCot(todayVO);
			model.addAttribute("listActivityNoteCot", listActivityNoteCot);			
			//질문과답변
			BoardVO boardVO= new BoardVO(); 
			boardVO.setMemSeq(loginInfo.getMemSeq());
			List<BoardVO> listQnaCot = todayService.listQnaCot(boardVO);
			model.addAttribute("listQnaCot", listQnaCot);			
			//면담일지작성
			List<TodayVO> listInterviewCot  = todayService.listInterviewCot(todayVO);
			model.addAttribute("listInterviewCot", listInterviewCot);	
		}else if(loginVO.getMemType().equals("CDP")){
			// 학과전담자
			viewPage="oklms/lu/today/lmsUserTodayPageCdp";
			//Q&A답변대기
			BoardVO boardVO= new BoardVO(); 
			boardVO.setMemSeq(loginInfo.getMemSeq());
			List<BoardVO> listQnaCdp = todayService.listQnaCdp(boardVO);
			model.addAttribute("listQnaCdp", listQnaCdp);			
			//과제제출현황
			List<TodayVO> listReportSubmitPrt =  todayService.listReportSubmitPrt (todayVO);
			model.addAttribute("listReportSubmitPrt", listReportSubmitPrt);
			//팀프로젝트제출현황
			List<TodayVO> listTeamProjectSubmitPrt =  todayService.listTeamProjectSubmitPrt (todayVO);
			model.addAttribute("listTeamProjectSubmitPrt", listTeamProjectSubmitPrt);
			//토론참여현황
			List<TodayVO> listDiscussCdp =  todayService.listDiscussCdp (todayVO);
			model.addAttribute("listDiscussCdp", listDiscussCdp);
			//온라인교과수강현황
			List<TodayVO> listOnlineCdp =  todayService.listOnlineCdp (todayVO);
			model.addAttribute("listOnlineCdp", listOnlineCdp);	
			//재직증빙서류제출현황
			List<TodayVO> listWorkCertCdp =  todayService.listWorkCertCdp (todayVO);
			model.addAttribute("listWorkCertCdp", listWorkCertCdp);
			
		}else if(loginVO.getMemType().equals("CCM")){
			//hrd담당자
			viewPage="oklms/lu/today/lmsUserTodayPageCcm";
			//활동내역서 미작성건			 
			List<TodayVO> listActivityHrd =  todayService.listActivityHrd (todayVO);
			model.addAttribute("listActivityHrd", listActivityHrd);			
			//주간훈련일지 미제출건			
			List<TodayVO> listWeekTraningNoteHrd =  todayService.listWeekTraningNoteHrd (todayVO);
			model.addAttribute("listWeekTraningNoteHrd", listWeekTraningNoteHrd);
			//주차별 학습활동서 미제출건
			
		}else if(loginVO.getMemType().equals("CCN")){
			//센터전담자
			viewPage="oklms/lu/today/lmsUserTodayPageCcn";
			//주간훈련일지미제출기업			 
			List<TodayVO> listWeekTraningNoteCompany =  todayService.listWeekTraningNoteCompany (todayVO);
			model.addAttribute("listWeekTraningNoteCompany", listWeekTraningNoteCompany);			
			//주차별학습활동서미제출자
			
			//면담일지 미제출기업
			List<TodayVO> listInterviewCcn  = todayService.listInterviewCcn(todayVO);
			model.addAttribute("listInterviewCcn", listInterviewCcn);	
			
			//담당자변경신청
			List<TodayVO> listComMemberCcm =  todayService.listComMemberCcm (todayVO);
			model.addAttribute("listComMemberCcm", listComMemberCcm);
		
			//훈련시간표변경신청 
			TraningScheduleVO traningScheduleVO = new TraningScheduleVO();
			loginInfo.putSessionToVo(traningScheduleVO);
			List<TraningScheduleVO> listTraningChangeScheduleDisapproved = traningChangeService.listTraningChangeScheduleDisapproved(traningScheduleVO);		
			model.addAttribute("listTraningChangeScheduleDisapproved", listTraningChangeScheduleDisapproved);
		}
		
		return viewPage ;
	}
	
 
}
