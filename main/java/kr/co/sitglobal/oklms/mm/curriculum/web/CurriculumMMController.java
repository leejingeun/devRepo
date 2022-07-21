package kr.co.sitglobal.oklms.mm.curriculum.web;
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

import kr.co.sitglobal.ifx.service.IfxService;
import kr.co.sitglobal.ifx.vo.AunuriMemberVO;
import kr.co.sitglobal.ifx.vo.AunuriSubjectVO;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.la.company.service.CompanyService;
import kr.co.sitglobal.oklms.lu.activity.vo.ActivityVO;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.online.service.OnlineTraningService;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningSchVO;
import kr.co.sitglobal.oklms.lu.report.service.ReportService;

@Controller
public class CurriculumMMController  extends BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(  CurriculumMMController.class);

	@Resource(name = "companyService")
	private CompanyService companyService;

	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;
	
	@Resource(name = "ifxService")
	private IfxService ifxService;
	
	@Resource(name = "ReportService")
	private ReportService reportService;
	
	@Resource(name = "OnlineTraningService")
	private OnlineTraningService onlineTraningService;
		
	@RequestMapping(value = "/mm/curriculum/listCurriculum.do")
	public String listCurriculum(@ModelAttribute("frmCurriculum")  CurrProcVO currProcVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /mm/curriculum/listCurriculum.do" );
		String retView = "oklms/mm/curriculum/listCurriculum";
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(currProcVO); // session의 정보를 VO에 추가. 
		
		//API 통한 교과정보, 관리자 제외한 진행중인 교과정보.
		AunuriMemberVO aunuriMemberVO = new AunuriMemberVO();
		aunuriMemberVO.setSessionMemSeq(loginInfo.getMemSeq());
		
		
		if("STD".equals(loginInfo.getMemType())){ //학습근로자 개설교과
			List<AunuriSubjectVO> listOjtAunuriSubject= ifxService.getOjtAunuriSubjectLesson(aunuriMemberVO);
			model.addAttribute( "listOjtAunuriSubject", listOjtAunuriSubject );
			
			List<AunuriSubjectVO> listOffJtAunuriSubject= ifxService.getOffJtAunuriSubjectLesson(aunuriMemberVO);
			model.addAttribute( "listOffJtAunuriSubject", listOffJtAunuriSubject );
			
			// 로그인권한별 URL 분기처리
        	OnlineTraningSchVO onlineTraningSchVO = new OnlineTraningSchVO();
        	loginInfo.putSessionToVo(onlineTraningSchVO); // session의 정보를 VO에 추가. 
        	onlineTraningSchVO.setYyyy(currProcVO.getYyyy());
        	onlineTraningSchVO.setTerm(currProcVO.getTerm());
        	onlineTraningSchVO.setSubjectCode(currProcVO.getSubjectCode());
        	onlineTraningSchVO.setSubjectClass(currProcVO.getSubClass());

        	// 진행중인 온라인교과 목록
        	List<OnlineTraningSchVO> resultList = onlineTraningService.listOnlineTraningStdSchedule(onlineTraningSchVO);     	        	
        	model.addAttribute("resultList", resultList);
		} else if("PRT".equals(loginInfo.getMemType())){ //담당교수 개설교과
			List<AunuriSubjectVO> listOjtAunuriSubject= ifxService.getOjtAunuriSubjectInsMapping(aunuriMemberVO);
			model.addAttribute( "listOjtAunuriSubject", listOjtAunuriSubject );
			List<AunuriSubjectVO> listOffJtAunuriSubject= ifxService.getOffJtAunuriSubjectInsMapping(aunuriMemberVO);
			model.addAttribute( "listOffJtAunuriSubject", listOffJtAunuriSubject );
			int hSkillCnt = ifxService.getOjtAunuriSubjectInsHSkillCnt(aunuriMemberVO);
			model.addAttribute( "hSkillCnt", hSkillCnt );
			// 로그인권한별 URL 분기처리
        	OnlineTraningSchVO onlineTraningSchVO = new OnlineTraningSchVO();
        	loginInfo.putSessionToVo(onlineTraningSchVO); // session의 정보를 VO에 추가.
        	onlineTraningSchVO.setYyyy(currProcVO.getYyyy());
        	onlineTraningSchVO.setTerm(currProcVO.getTerm());
        	onlineTraningSchVO.setSubjectCode(currProcVO.getSubjectCode());
        	onlineTraningSchVO.setSubjectClass(currProcVO.getSubClass());

        	// 진행중인 온라인교과 목록
        	List<OnlineTraningSchVO> resultList = onlineTraningService.listOnlineTraningInsSchedule(onlineTraningSchVO);
        	model.addAttribute("resultList", resultList);
			
		} else if("COT".equals(loginInfo.getMemType())){ //기업현장교사 개설교과
			List<AunuriSubjectVO> listOjtAunuriSubject= ifxService.getOjtAunuriSubjectTutMapping(aunuriMemberVO);
			model.addAttribute( "listOjtAunuriSubject", listOjtAunuriSubject );
			// 로그인권한별 URL 분기처리			
			
		}
		 
		// 과정정보 
		currProcVO = reportService.getCurrproc(currProcVO);
		model.addAttribute("currProcVO", currProcVO);
		 
  		// View호출
		return retView;
	}
}
