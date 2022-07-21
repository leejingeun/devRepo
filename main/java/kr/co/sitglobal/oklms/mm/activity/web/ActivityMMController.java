package kr.co.sitglobal.oklms.mm.activity.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.sitglobal.ifx.service.IfxService;
import kr.co.sitglobal.ifx.vo.AunuriMemberVO;
import kr.co.sitglobal.ifx.vo.AunuriSubjectVO;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController; 
import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.atchFile.vo.AtchFileVO;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.commbiz.util.AtchFileUtil;
import kr.co.sitglobal.oklms.la.company.service.CompanyService;
import kr.co.sitglobal.oklms.lu.activity.service.ActivityService;
import kr.co.sitglobal.oklms.lu.activity.vo.ActivityVO;
import kr.co.sitglobal.oklms.lu.activity.vo.MemberVO;
import kr.co.sitglobal.oklms.lu.activity.vo.SubjweekStdVO;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningSchVO;
import kr.co.sitglobal.oklms.lu.report.service.ReportService;
import kr.co.sitglobal.oklms.lu.traning.service.TraningNoteSerivce;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningNoteVO;

@Controller
public class ActivityMMController  extends BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ActivityMMController.class);
	
	@Resource(name = "ActivityService")
	private ActivityService activityService;
	
	@Resource(name = "ReportService")
	private ReportService reportService;
	
	@Resource(name = "traningNoteSerivce")
	private TraningNoteSerivce traningNoteSerivce;
	
	@Resource(name = "companyService")
	private CompanyService companyService;

	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;
	
	@Resource(name = "ifxService")
	private IfxService ifxService;
	
	@Resource(name = "atchFileService")
	private AtchFileService atchFileService;

	@Resource(name = "atchFileUtil")
	private AtchFileUtil atchFileUtil;
	
	@RequestMapping(value = "/mm/activity/listActivity.do")
	public String listActivity(@ModelAttribute("frmActivity") ActivityVO activityVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /mm/activity/listActivity.do" );
		String retView = "";
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(activityVO); // session의 정보를 VO에 추가. 
		//API 통한 교과정보, 관리자 제외한 진행중인 교과정보.
		AunuriMemberVO aunuriMemberVO = new AunuriMemberVO();
		aunuriMemberVO.setSessionMemSeq(loginInfo.getMemSeq());
		
		//String classId_temp =activityVO.getClassId();
		if("STD".equals(loginInfo.getMemType())){ //학습근로자 개설교과
			List<AunuriSubjectVO> listOjtAunuriSubject= ifxService.getOjtAunuriSubjectLesson(aunuriMemberVO);
			model.addAttribute( "listOjtAunuriSubject", listOjtAunuriSubject );
			
			List<AunuriSubjectVO> listOffJtAunuriSubject= ifxService.getOffJtAunuriSubjectLesson(aunuriMemberVO);
			model.addAttribute( "listOffJtAunuriSubject", listOffJtAunuriSubject );
			
			// 로그인권한별 URL 분기처리
			retView = "oklms/mm/activity/listActivityStd";
			
		} else if("PRT".equals(loginInfo.getMemType())){ //담당교수 개설교과
			List<AunuriSubjectVO> listOjtAunuriSubject= ifxService.getOjtAunuriSubjectInsMapping(aunuriMemberVO);
			model.addAttribute( "listOjtAunuriSubject", listOjtAunuriSubject );
			List<AunuriSubjectVO> listOffJtAunuriSubject= ifxService.getOffJtAunuriSubjectInsMapping(aunuriMemberVO);
			model.addAttribute( "listOffJtAunuriSubject", listOffJtAunuriSubject );
			int hSkillCnt = ifxService.getOjtAunuriSubjectInsHSkillCnt(aunuriMemberVO);
			model.addAttribute( "hSkillCnt", hSkillCnt );
			// 로그인권한별 URL 분기처리
			retView = "oklms/mm/activity/listActivity";
			
		} else if("COT".equals(loginInfo.getMemType())){ //기업현장교사 개설교과
			List<AunuriSubjectVO> listOjtAunuriSubject= ifxService.getOjtAunuriSubjectTutMapping(aunuriMemberVO);
			model.addAttribute( "listOjtAunuriSubject", listOjtAunuriSubject );
			// 로그인권한별 URL 분기처리			
			retView = "oklms/mm/activity/listActivity";
			
		}
	
		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy(activityVO.getYyyy());
		currProcVO.setTerm(activityVO.getTerm());
		currProcVO.setSubjectCode(activityVO.getSubjectCode());
		currProcVO.setSubClass(activityVO.getClassId());
		currProcVO = reportService.getCurrproc(currProcVO);
		model.addAttribute("currProcVO", currProcVO);
		
		if(currProcVO!=null && currProcVO.getSubjectTraningType().equals("OJT") && "PRT".equals(loginInfo.getMemType()) ){
 
			TraningNoteVO traningNoteVO = new TraningNoteVO();
			traningNoteVO.setYyyy(activityVO.getYyyy());
			traningNoteVO.setTerm(activityVO.getTerm());
			traningNoteVO.setSubjectCode(activityVO.getSubjectCode());
			
			//회사정보
			List<TraningNoteVO> subjectNameList = traningNoteSerivce.listSubjcetName(traningNoteVO);
			model.put("subjectNameList", subjectNameList);
			// 반정보없을 경우 무조건 첫번째회사 정보
			if(activityVO.getClassId()==null || activityVO.getClassId().equals("")){			

				if(subjectNameList!=null&&subjectNameList.size()>0){

					TraningNoteVO temp = subjectNameList.get(0);
					activityVO.setClassId(temp.getClassId());
					activityVO.setCompanyId(temp.getCompanyId());
					currProcVO.setSubClass(temp.getClassId());
				} 

			}
			retView = "oklms/mm/activity/listActivityOjt";
		}

		// 주차정보 
		List<OnlineTraningSchVO> weeklist =reportService.listLmsSubjWeek(currProcVO);
		model.addAttribute("weeklist", weeklist);
		
		// 주차정보없을때 첫번째주차 정보입력
		// 현재날자에 해당하는 주차 선택기능 추가 필요함
		if(currProcVO!=null&&(activityVO.getWeekCnt()==null||activityVO.getWeekCnt().equals(""))){
			TraningNoteVO  traningNoteVO = new TraningNoteVO();
			traningNoteVO.setYyyy(currProcVO.getYyyy());
			traningNoteVO.setTerm(currProcVO.getTerm());
			traningNoteVO.setSubjectCode(currProcVO.getSubjectCode());				
			traningNoteVO.setClassId(currProcVO.getSubClass());
		 
			if(traningNoteVO.getWeekCnt() == null|| traningNoteVO.getWeekCnt().equals("")){
				// 최근주차정보조회
				TraningNoteVO  traningNowWeekCn = traningNoteSerivce.getTraningNowWeekCnt(traningNoteVO);	
				if(traningNowWeekCn.getWeekCnt()==null||traningNowWeekCn.getWeekCnt().equals("")){
					activityVO.setWeekCnt("1");
				}else{ 
					activityVO.setWeekCnt(traningNowWeekCn.getWeekCnt());
				}
			} 

		}

		if(currProcVO!=null){
			// 학습근로자만
			if("STD".equals(loginInfo.getMemType())){
				
				// 학습근로자
				activityVO.setMemId(activityVO.getSessionMemId());
				
				ActivityVO result = activityService.getActivity(activityVO); 
				List<ActivityVO> resultMember = activityService.getActivityMember(activityVO);
				
				SubjweekStdVO subjweekStdVO = new SubjweekStdVO ();
				subjweekStdVO.setYyyy(activityVO.getYyyy());
				subjweekStdVO.setTerm(activityVO.getTerm());
				subjweekStdVO.setSubjectCode(activityVO.getSubjectCode());
				subjweekStdVO.setClassId(activityVO.getClassId());
				subjweekStdVO.setWeekCnt(activityVO.getWeekCnt());		
				subjweekStdVO = activityService.getSubjWeek(subjweekStdVO);
				model.addAttribute("subjweekStdVO", subjweekStdVO);
				
				AtchFileVO atchFileVO = new AtchFileVO();
				if(result!=null){
					atchFileVO.setFileSn(1);
					atchFileVO.setAtchFileId(result.getAtchFileId());
					//첨부파일
					AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
				    model.addAttribute("resultFile", resultFile);     
					
				}
				model.addAttribute("currProcVO", currProcVO);
				model.addAttribute("activityVO", activityVO);
				model.addAttribute("result", result);
				model.addAttribute("resultMember", resultMember);
				
			}else{
				

				activityVO.setTraningType(currProcVO.getSubjectTraningType());
				
				// 주차정보 
				List<OnlineTraningSchVO> onlineTraningSchVO =reportService.listLmsSubjWeek(currProcVO);
				model.addAttribute("onlineTraningSchVO", onlineTraningSchVO);
		
				// 주차정보없을때 첫번째주차 정보입력
				// 현재날자에 해당하는 주차 선택기능 추가 필요함
				if(activityVO.getWeekCnt()==null||activityVO.getWeekCnt().equals("")){
					TraningNoteVO  traningNoteVO = new TraningNoteVO();
					traningNoteVO.setYyyy(currProcVO.getYyyy());
					traningNoteVO.setTerm(currProcVO.getTerm());
					traningNoteVO.setSubjectCode(currProcVO.getSubjectCode());				
					traningNoteVO.setClassId(currProcVO.getSubClass());
				 
					if(traningNoteVO.getWeekCnt() == null|| traningNoteVO.getWeekCnt().equals("")){
						// 최근주차정보조회
						TraningNoteVO  traningNowWeekCn = traningNoteSerivce.getTraningNowWeekCnt(traningNoteVO);	
						if(traningNowWeekCn.getWeekCnt()==null||traningNowWeekCn.getWeekCnt().equals("")){
							activityVO.setWeekCnt("1");
						}else{ 
							activityVO.setWeekCnt(traningNowWeekCn.getWeekCnt());
						}
					} 

				}
				
				SubjweekStdVO subjweekStdVO = new SubjweekStdVO ();
				subjweekStdVO.setYyyy(activityVO.getYyyy());
				subjweekStdVO.setTerm(activityVO.getTerm());
				subjweekStdVO.setSubjectCode(activityVO.getSubjectCode());
				subjweekStdVO.setClassId(activityVO.getClassId());
				subjweekStdVO.setWeekCnt(activityVO.getWeekCnt());		
				subjweekStdVO = activityService.getSubjWeek(subjweekStdVO);
				model.addAttribute("subjweekStdVO", subjweekStdVO);
				model.addAttribute("currProcVO", currProcVO);
				model.addAttribute("activityVO", activityVO);
				
				//학습근로자 목록 
				MemberVO memberVO = new MemberVO();
				memberVO.setYyyy(activityVO.getYyyy());
				memberVO.setTerm(activityVO.getTerm());
				memberVO.setSubjectCode(activityVO.getSubjectCode());
				memberVO.setClassId(activityVO.getClassId());
				memberVO.setWeekCnt(activityVO.getWeekCnt());
				memberVO.setSearchName(activityVO.getSearchName());
				
				List<MemberVO> memberlist = activityService.listActivityMember(memberVO);
				model.addAttribute("memberlist", memberlist);
				
			}			
		}
		
		model.addAttribute("activityVO", activityVO);
  		// View호출
		return retView;
	}
	
	// 대상조회
	@RequestMapping(value = "/mm/activity/getActivity.do")
	public String getActivity(@ModelAttribute("frmActivity") ActivityVO activityVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /mm/activity/getActivity.do" );
		String retView =     "oklms/mm/activity/getActivity";
  		
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(activityVO); // session의 정보를 VO에 추가. 

		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy(activityVO.getYyyy());
		currProcVO.setTerm(activityVO.getTerm());
		currProcVO.setSubjectCode(activityVO.getSubjectCode());
		currProcVO.setSubClass(activityVO.getClassId());
		currProcVO = reportService.getCurrproc(currProcVO);
		model.addAttribute("currProcVO", currProcVO);	
		
		 		
		ActivityVO result = activityService.getActivity(activityVO);
		// 학습근로자
		List<ActivityVO> resultMember = activityService.getActivityMember(activityVO);
		
		SubjweekStdVO subjweekStdVO = new SubjweekStdVO ();
		subjweekStdVO.setYyyy(activityVO.getYyyy());
		subjweekStdVO.setTerm(activityVO.getTerm());
		subjweekStdVO.setSubjectCode(activityVO.getSubjectCode());
		subjweekStdVO.setClassId(activityVO.getClassId());
		subjweekStdVO.setWeekCnt(activityVO.getWeekCnt());		
		subjweekStdVO = activityService.getSubjWeek(subjweekStdVO);
		model.addAttribute("subjweekStdVO", subjweekStdVO);
		
		AtchFileVO atchFileVO = new AtchFileVO();
		if(result!=null){
			atchFileVO.setFileSn(1);
			atchFileVO.setAtchFileId(result.getAtchFileId());
			//첨부파일
			AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
		    model.addAttribute("resultFile", resultFile);     
			
		}
/*
		//학습근로자 전체목록 
		MemberVO memberVO = new MemberVO();
		memberVO.setYyyy(activityVO.getYyyy());
		memberVO.setTerm(activityVO.getTerm());
		memberVO.setSubjectCode(activityVO.getSubjectCode());
		memberVO.setClassId(activityVO.getClassId());
		memberVO.setWeekCnt(activityVO.getWeekCnt());
		memberVO.setSearchName(activityVO.getSearchName());
		
		List<MemberVO> memberlist = activityService.listActivityMember(memberVO);
		model.addAttribute("memberlist", memberlist);
		*/
		
		model.addAttribute("currProcVO", currProcVO);
		model.addAttribute("activityVO", activityVO);
		model.addAttribute("result", result);
		model.addAttribute("resultMember", resultMember);
		
  		// View호출
		return retView;
	}	
		
	// 학습근로자 등록
  	@RequestMapping(value = "/mm/activity/insertActivity.do")
	public String insertActivityStd(@ModelAttribute("frmActivity")  ActivityVO activityVO, ModelMap model,final MultipartHttpServletRequest multiRequest) throws Exception {
  		// /mm/activity/insertActivityStd.do
  		LOG.debug("#### URL = /mm/activity/insertActivity.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(activityVO); // session의 정보를 VO에 추가.
		
  		activityService.insertActivity(activityVO,multiRequest);
  		
		// View호출
  		return "redirect:/mm/activity/listActivity.do?weekCnt="+activityVO.getWeekCnt()+"&classId="+activityVO.getClassId()+"&subjectCode="+activityVO.getSubjectCode()+"&yyyy="+activityVO.getYyyy()+"&term="+activityVO.getTerm();
	}  
  	
}
