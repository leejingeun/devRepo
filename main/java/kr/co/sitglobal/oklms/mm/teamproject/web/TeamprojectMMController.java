package kr.co.sitglobal.oklms.mm.teamproject.web;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.atchFile.vo.AtchFileVO;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.report.service.ReportService;
import kr.co.sitglobal.oklms.lu.teamproject.service.TeamprojectService;
import kr.co.sitglobal.oklms.lu.teamproject.vo.TeamprojectSubmitVO;
import kr.co.sitglobal.oklms.lu.teamproject.vo.TeamprojectVO;

@Controller
public class TeamprojectMMController  extends BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(  TeamprojectMMController.class);

	@Resource(name = "ifxService")
	private IfxService ifxService;

	@Resource(name = "ReportService")
	private ReportService reportService;
	
	@Resource(name="TeamprojectService")
	private TeamprojectService teamprojectService;
	
	@Resource(name = "atchFileService")
	private AtchFileService atchFileService;
	
	@RequestMapping(value = "/mm/teamproject/listTeamproject.do")
	public String listTeamproject(@ModelAttribute("frmTeamproject") TeamprojectVO teamprojectVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /mm/teamproject/listTeamproject.do" );
		String retView = "oklms/mm/teamproject/listTeamproject"; 
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(teamprojectVO); // session의 정보를 VO에 추가. 
		
		//API 통한 교과정보, 관리자 제외한 진행중인 교과정보.
		AunuriMemberVO aunuriMemberVO = new AunuriMemberVO();
		aunuriMemberVO.setSessionMemSeq(loginInfo.getMemSeq());
		
		if("STD".equals(loginInfo.getMemType())){ //학습근로자 개설교과
			List<AunuriSubjectVO> listOffJtAunuriSubject= ifxService.getOffJtAunuriSubjectLesson(aunuriMemberVO);
			model.addAttribute( "listOffJtAunuriSubject", listOffJtAunuriSubject );
			
		} else if("PRT".equals(loginInfo.getMemType())){ //담당교수 개설교과
			List<AunuriSubjectVO> listOffJtAunuriSubject= ifxService.getOffJtAunuriSubjectInsMapping(aunuriMemberVO);
			model.addAttribute( "listOffJtAunuriSubject", listOffJtAunuriSubject );
		}  
		
		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy(teamprojectVO.getYyyy());
		currProcVO.setTerm(teamprojectVO.getTerm());
		currProcVO.setSubjectCode(teamprojectVO.getSubjectCode());
		currProcVO.setSubClass(teamprojectVO.getSubClass());
		currProcVO = reportService.getCurrproc(currProcVO);
		model.addAttribute("currProcVO", currProcVO);
		
		List<TeamprojectVO> result=teamprojectService.listTeamproject(teamprojectVO);				
		model.addAttribute("result", result);
		
		model.addAttribute("teamprojectVO", teamprojectVO);
  		// View호출
		return retView;
	}
	@RequestMapping(value = "/mm/teamproject/getTeamproject.do")
	public String getTeamproject(@ModelAttribute("frmTeamproject") TeamprojectSubmitVO teamprojectVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /mm/teamproject/getTeamproject.do" );
		String retView = "oklms/mm/teamproject/getTeamproject"; 
		
		
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(teamprojectVO); // session의 정보를 VO에 추가. 
		
		TeamprojectVO teamproject =teamprojectService.getTeamproject(teamprojectVO);
		teamproject.setOrderByLeader("Y");

		
		List<TeamprojectSubmitVO> result= null;
		if("STD".equals(loginInfo.getMemType())){ //학습근로자 개설교과
			TeamprojectSubmitVO teamprojectTemp = new TeamprojectSubmitVO( teamproject);
			teamprojectTemp.setMemId(teamprojectVO.getSessionMemId());
			result=teamprojectService.listTeamprojectSubmit(teamprojectTemp);
			retView = "oklms/mm/teamproject/getTeamprojectStd"; 
		}else{
			result=teamprojectService.listTeamprojectSubmit(new TeamprojectSubmitVO( teamproject));
		}
		
		teamprojectVO.setYyyy( teamproject.getYyyy());
		teamprojectVO.setTerm( teamproject.getTerm());
		teamprojectVO.setSubjectCode( teamproject.getSubjectCode());
		teamprojectVO.setSubClass( teamproject.getSubClass());
		teamprojectVO.setProjectName( teamproject.getProjectName());
		teamprojectVO.setScore(teamproject.getScore() );
		teamprojectVO.setProjectStartDate(teamproject.getProjectStartDate() );
		teamprojectVO.setProjectEndDate(teamproject.getProjectEndDate() );
		teamprojectVO.setSubmitEndDate( teamproject.getSubmitEndDate());
		teamprojectVO.setSubmitType( teamproject.getSubmitType());
		teamprojectVO.setProjectDesc(teamproject.getProjectDesc());
		teamprojectVO.setAtchFileId(teamproject.getAtchFileId());
		teamprojectVO.setTeamCnt(teamproject.getTeamCnt());
		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy(teamprojectVO.getYyyy());
		currProcVO.setTerm(teamprojectVO.getTerm());
		currProcVO.setSubjectCode(teamprojectVO.getSubjectCode());
		currProcVO.setSubClass(teamprojectVO.getSubClass());
		currProcVO = reportService.getCurrproc(currProcVO);
		model.addAttribute("currProcVO", currProcVO);
		
		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setFileSn(1);
		atchFileVO.setAtchFileId(teamprojectVO.getAtchFileId());

		//첨부파일
		AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
        model.addAttribute("resultFile", resultFile);     
		
		model.addAttribute("result", result);
		model.addAttribute("teamprojectVO", teamprojectVO);
  		// View호출
		return retView;
	}
	
}
