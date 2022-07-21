package kr.co.sitglobal.oklms.mm.traning.web;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import kr.co.sitglobal.ifx.service.IfxService;
import kr.co.sitglobal.ifx.vo.AunuriMemberVO;
import kr.co.sitglobal.ifx.vo.AunuriSubjectVO;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController; 
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.la.company.service.CompanyService;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningSchVO;
import kr.co.sitglobal.oklms.lu.report.service.ReportService;
import kr.co.sitglobal.oklms.lu.traning.service.TraningNoteSerivce;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningNoteVO;

@Controller
public class TraningMMController  extends BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(  TraningMMController.class);

	@Resource(name = "traningNoteSerivce")
	private TraningNoteSerivce traningNoteSerivce;

	@Resource(name = "companyService")
	private CompanyService companyService;

	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;
	
	@Resource(name = "ifxService")
	private IfxService ifxService;
	
	@Resource(name = "ReportService")
	private ReportService reportService;
	
	@RequestMapping(value = "/mm/traning/listTraning.do")
	public String listTraning(@ModelAttribute("frmTraning")  TraningNoteVO  traningNoteVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /mm/traning/listTraning.do" );
		String retView = "oklms/mm/traning/listTraning";	 
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가. 
		
		//API 통한 교과정보, 관리자 제외한 진행중인 교과정보.
		AunuriMemberVO aunuriMemberVO = new AunuriMemberVO();
		aunuriMemberVO.setSessionMemSeq(loginInfo.getMemSeq());
		
		
		if("STD".equals(loginInfo.getMemType())){ //학습근로자 개설교과
			List<AunuriSubjectVO> listOjtAunuriSubject= ifxService.getOjtAunuriSubjectLesson(aunuriMemberVO);
			model.addAttribute( "listOjtAunuriSubject", listOjtAunuriSubject );
			
			List<AunuriSubjectVO> listOffJtAunuriSubject= ifxService.getOffJtAunuriSubjectLesson(aunuriMemberVO);
			model.addAttribute( "listOffJtAunuriSubject", listOffJtAunuriSubject );
			
			// 로그인권한별 URL 분기처리
			retView = "oklms/mm/traning/listTraningStd";
			
		} else if("PRT".equals(loginInfo.getMemType())){ //담당교수 개설교과
			List<AunuriSubjectVO> listOjtAunuriSubject= ifxService.getOjtAunuriSubjectInsMapping(aunuriMemberVO);
			model.addAttribute( "listOjtAunuriSubject", listOjtAunuriSubject );
			List<AunuriSubjectVO> listOffJtAunuriSubject= ifxService.getOffJtAunuriSubjectInsMapping(aunuriMemberVO);
			model.addAttribute( "listOffJtAunuriSubject", listOffJtAunuriSubject );
			int hSkillCnt = ifxService.getOjtAunuriSubjectInsHSkillCnt(aunuriMemberVO);
			model.addAttribute( "hSkillCnt", hSkillCnt );
			// 로그인권한별 URL 분기처리
			retView = "oklms/mm/traning/listTraning";
			
		} else if("COT".equals(loginInfo.getMemType())){ //기업현장교사 개설교과
			List<AunuriSubjectVO> listOjtAunuriSubject= ifxService.getOjtAunuriSubjectTutMapping(aunuriMemberVO);
			model.addAttribute( "listOjtAunuriSubject", listOjtAunuriSubject );
			// 로그인권한별 URL 분기처리			
			retView = "oklms/mm/traning/listTraning";
			
		}
	
		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy(traningNoteVO.getYyyy());
		currProcVO.setTerm(traningNoteVO.getTerm());
		currProcVO.setSubjectCode(traningNoteVO.getSubjectCode());
		currProcVO.setSubClass(traningNoteVO.getClassId());
		currProcVO = reportService.getCurrproc(currProcVO);
		model.addAttribute("currProcVO", currProcVO);
		 
		
		if(currProcVO!=null && currProcVO.getSubjectTraningType()!=null && currProcVO.getSubjectTraningType().equals("OJT") && "PRT".equals(loginInfo.getMemType()) ){

			//회사정보
			List<TraningNoteVO> subjectNameList = traningNoteSerivce.listSubjcetName(traningNoteVO);
			model.put("subjectNameList", subjectNameList);
			// 반정보없을 경우 무조건 첫번째회사 정보
			if(traningNoteVO.getClassId()==null || traningNoteVO.getClassId().equals("")){			

				if(subjectNameList!=null&&subjectNameList.size()>0){

					TraningNoteVO temp = subjectNameList.get(0);
					traningNoteVO.setClassId(temp.getClassId());
					traningNoteVO.setCompanyId(temp.getCompanyId());
					currProcVO.setSubClass(temp.getClassId());
				}else{

					retView = "oklms/mm/traning/listTraning";
					//return "oklms/lu/traning/listTraningNoteOjt";		
				}

			}
			// 주차정보 
			List<OnlineTraningSchVO> weeklist =reportService.listLmsSubjWeek(currProcVO);
			model.addAttribute("weeklist", weeklist);
			
			currProcVO = reportService.getCurrproc(currProcVO);
			
			// 과정정보없을시 무조건 이동
			if(currProcVO==null){

				retView = "oklms/mm/traning/listTraning";
				//return "oklms/lu/traning/listTraningNoteOjt";
			}

			traningNoteVO.setMemSeq(traningNoteVO.getSessionMemSeq());
						
			if(traningNoteVO.getWeekCnt() == null|| traningNoteVO.getWeekCnt().equals("")){

				// 최근주차정보조회
				TraningNoteVO  traningNowWeekCn = traningNoteSerivce.getTraningNowWeekCnt(traningNoteVO);	
				if(traningNowWeekCn.getWeekCnt()==null||traningNowWeekCn.getWeekCnt().equals("")){

					traningNoteVO.setWeekCnt("1");
				}else{

					traningNoteVO.setWeekCnt(traningNowWeekCn.getWeekCnt());
				}
			}
 
		 
			//훈련일지 주차별 정규수업  
			List<TraningNoteVO> timeList =traningNoteSerivce.listTraningRegularTime(traningNoteVO);
			model.put("timeList", timeList);

			List<TraningNoteVO> resultSum = new ArrayList<TraningNoteVO>(); 
			List<List<TraningNoteVO>> resultlistSum = new ArrayList<List<TraningNoteVO>>(); 
			for(int i = 0; i < timeList.size(); i++){
	
				traningNoteVO.setTimeId(timeList.get(i).getTimeId());
				TraningNoteVO result1 = traningNoteSerivce.getTraningRegularTime(traningNoteVO);			
				resultSum.add(result1);			 
				traningNoteVO.setMemSeq("");
				List<TraningNoteVO> resultList = traningNoteSerivce.listTraningRegularClassMember(traningNoteVO);			
				resultlistSum.add(resultList);
			}
			model.put("resultSum",resultSum); 
			model.put("resultlistSum",resultlistSum);
			
			// 보강수업 조회
			List<TraningNoteVO> resultWnrich = traningNoteSerivce.getTraningEnrichmentTime(traningNoteVO);
			List<List<TraningNoteVO>> resultEnrichmentList = new ArrayList<List<TraningNoteVO>>(); 
			model.put("TraningEnrichmentTimeVO",resultWnrich);
			model.put("seachVO", traningNoteVO);

			for(int i = 0; i < resultWnrich.size(); i++){

				traningNoteVO.setTraniningNoteMasterId(resultWnrich.get(i).getTraniningNoteMasterId());
				// 보강수업생 조회
				List<TraningNoteVO> resultEnrichment = traningNoteSerivce.listTraningEnrichmentClassMember(traningNoteVO);
				resultEnrichmentList.add(resultEnrichment);
			}

			model.put("TraningNoteList2", resultEnrichmentList);
	 
			
			if(currProcVO.getSubjectType().equals("HSKILL")){
				retView = "oklms/mm/traning/listTraningOjtHs";	
			}else{
				retView = "oklms/mm/traning/listTraningOjt";
			}

		}else{
			if("STD".equals(loginInfo.getMemType())){
				traningNoteVO.setMemSeq(loginInfo.getMemSeq());
			}
			// 주차정보 
			List<OnlineTraningSchVO> weeklist =reportService.listLmsSubjWeek(currProcVO);
			model.addAttribute("weeklist", weeklist);


			
			if(traningNoteVO.getWeekCnt() == null|| traningNoteVO.getWeekCnt().equals("")){
				// 최근주차정보조회
				TraningNoteVO  traningNowWeekCn = traningNoteSerivce.getTraningNowWeekCnt(traningNoteVO);	
				if(traningNowWeekCn.getWeekCnt()==null||traningNowWeekCn.getWeekCnt().equals("")){
					traningNoteVO.setWeekCnt("1");
				}else{
					traningNoteVO.setWeekCnt(traningNowWeekCn.getWeekCnt());
				}
			}
/**			
			//공통상단 데이터
			TraningNoteVO result = traningNoteSerivce.getTraningNote(traningNoteVO);
			model.put("TraningNoteVO", result);
*/			
			//훈련일지 주차별 정규수업
			List<TraningNoteVO> timeList =traningNoteSerivce.listTraningRegularTime(traningNoteVO);
			model.put("timeList", timeList);
			
			List<TraningNoteVO> resultSum = new ArrayList<TraningNoteVO>(); 
			List<List<TraningNoteVO>> resultlistSum = new ArrayList<List<TraningNoteVO>>(); 
			for(int i = 0; i < timeList.size(); i++){

				traningNoteVO.setTimeId(timeList.get(i).getTimeId());
				TraningNoteVO result1 = traningNoteSerivce.getTraningRegularTime(traningNoteVO);			
				resultSum.add(result1);			 
				List<TraningNoteVO> resultList = traningNoteSerivce.listTraningRegularClassMember(traningNoteVO);			
				resultlistSum.add(resultList);
			}
			model.put("resultSum",resultSum); 
			model.put("resultlistSum",resultlistSum);
				
			// 보강수업 조회
			List<TraningNoteVO> resultWnrich = traningNoteSerivce.getTraningEnrichmentTime(traningNoteVO);
			List<List<TraningNoteVO>> resultEnrichmentList = new ArrayList<List<TraningNoteVO>>(); 
			model.put("TraningEnrichmentTimeVO",resultWnrich);
			model.put("seachVO", traningNoteVO);

			for(int i = 0; i < resultWnrich.size(); i++){

				traningNoteVO.setTraniningNoteMasterId(resultWnrich.get(i).getTraniningNoteMasterId());
				// 보강수업생 조회
				List<TraningNoteVO> resultEnrichment = traningNoteSerivce.listTraningEnrichmentClassMember(traningNoteVO);
				resultEnrichmentList.add(resultEnrichment);
			}
			model.put("TraningNoteList2", resultEnrichmentList);
					
		}
		
 
		model.addAttribute("traningNoteVO", traningNoteVO);
		
  		// View호출
		return retView;
	}

	/**
	 * 정규수업 등록
	 * @param cmsCourseBaseVO
	 *
	 * @param model
	 * @param session
	 * @param traningNoteVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/traning/goInsertTraning.do")
	public String goInsertTraning(@ModelAttribute("frmTraning")TraningNoteVO  traningNoteVO, ModelMap model, HttpSession session,  RedirectAttributes redirectAttributes ) throws Exception {

		LOG.debug("#### URL = /mm/traning/goInsertTraning.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.
		
		String retMsg = "입력값을 확인해주세요";
		int insertCnt = 0;
		traningNoteVO.setTraningType((String)session.getAttribute("subjectTraningType"));
		//상세 등록
		insertCnt  = traningNoteSerivce.goInsertTraningNoteDetail(traningNoteVO);

		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}
		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		
		return "redirect:/mm/traning/listTraning.do?weekCnt="+traningNoteVO.getWeekCnt()+"&classId="+traningNoteVO.getClassId()+"&subjectCode="+traningNoteVO.getSubjectCode()+"&yyyy="+traningNoteVO.getYyyy()+"&term="+traningNoteVO.getTerm();
	} 
	/**
	 * 보강수업 등록
	 * @param cmsCourseBaseVO
	 * @param model
	 * @param session
	 * @param traningNoteVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/traning/insertTraning.do")
	public String goInsertTraningNoteEnrichment(@ModelAttribute("frmTraning")TraningNoteVO  traningNoteVO, ModelMap model, HttpSession session, RedirectAttributes redirectAttributes ) throws Exception {

		LOG.debug("#### URL = /mm/traning/insertTraning.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.
		
		String retMsg = "입력값을 확인해주세요";
		int insertCnt = 0;
		traningNoteVO.setAddyn("Y");
		
		//보강 수업 일시 / 시작 시간 종료 시간 등록 및 수정 처리
		insertCnt = traningNoteSerivce.goInsertEnrichmentTraningTime(traningNoteVO);
 
		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);		
		return "redirect:/mm/traning/listTraning.do?weekCnt="+traningNoteVO.getWeekCnt()+"&classId="+traningNoteVO.getClassId()+"&subjectCode="+traningNoteVO.getSubjectCode()+"&yyyy="+traningNoteVO.getYyyy()+"&term="+traningNoteVO.getTerm();
	}	
	/**
	 * 보강수업 수업생 삭제 처리
	 * @param cmsCourseBaseVO
	 * @param model
	 * @param session
	 * @param traningNoteVO
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/traning/deleteTraning.do")
	public String deleteTraningNoteEnrichment(@ModelAttribute("frmTraning")TraningNoteVO  traningNoteVO, ModelMap model, HttpSession session, RedirectAttributes redirectAttributes ) throws Exception {

		LOG.debug("#### URL = /mm/traning/deleteTraning.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.
		
		String retMsg = "입력값을 확인해주세요";
		int insertCnt = 0;
		insertCnt  = traningNoteSerivce.deleteTraningNoteEnrichment(traningNoteVO);
		if(insertCnt > 0){
			retMsg = "정상적으로  삭제되었습니다.!";
		}else{
			retMsg = "삭제 처리된건이 없습니다.!";
		}
		redirectAttributes.addFlashAttribute("retMsg", retMsg);

		return "redirect:/mm/traning/listTraning.do?weekCnt="+traningNoteVO.getWeekCnt()+"&classId="+traningNoteVO.getClassId()+"&subjectCode="+traningNoteVO.getSubjectCode()+"&yyyy="+traningNoteVO.getYyyy()+"&term="+traningNoteVO.getTerm();
	}
	
}
