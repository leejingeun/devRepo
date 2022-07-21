package kr.co.sitglobal.oklms.mm.report.web;
import java.util.List;
import javax.annotation.Resource;
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
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.report.service.ReportService;
import kr.co.sitglobal.oklms.lu.report.vo.ReportVO;

@Controller
public class ReportMMController  extends BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(  ReportMMController.class);
	
	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;
	
	@Resource(name = "ifxService")
	private IfxService ifxService;

	@Resource(name = "ReportService")
	private ReportService reportService;
	
	@Resource(name = "atchFileService")
	private AtchFileService atchFileService;

	@RequestMapping(value = "/mm/report/listReport.do")
	public String listReport(@ModelAttribute("frmReport") ReportVO reportVO, ModelMap model ) throws Exception {
		
  		LOG.debug("#### URL = /mm/report/listReport.do" );
		String retView = "oklms/mm/report/listReport"; 
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(reportVO); // session의 정보를 VO에 추가. 
		
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
		currProcVO.setYyyy(reportVO.getYyyy());
		currProcVO.setTerm(reportVO.getTerm());
		currProcVO.setSubjectCode(reportVO.getSubjectCode());
		currProcVO.setSubClass(reportVO.getClassId());
		currProcVO = reportService.getCurrproc(currProcVO);
		model.addAttribute("currProcVO", currProcVO);
		
		// 목록
		List<ReportVO> result=reportService.listReport(reportVO);
				
		model.addAttribute("result", result);
		
		model.addAttribute( "reportVO", reportVO );
  		// View호출
		return retView;
	}
	@RequestMapping(value = "/mm/report/getReport.do")
	public String getReport(@ModelAttribute("frmReport") ReportVO reportVO, ModelMap model ) throws Exception {
		
  		LOG.debug("#### URL = /mm/report/getReport.do" );
		String retView = "oklms/mm/report/getReport";
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(reportVO); // session의 정보를 VO에 추가. 
		
		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy(reportVO.getYyyy());
		currProcVO.setTerm(reportVO.getTerm());
		currProcVO.setSubjectCode(reportVO.getSubjectCode());
		currProcVO.setSubClass(reportVO.getClassId());
		currProcVO = reportService.getCurrproc(currProcVO);
		model.addAttribute("currProcVO", currProcVO);
		
		reportVO=reportService.getReport(reportVO);
		reportVO.setPageSize(100000);
		
		if("STD".equals(loginInfo.getMemType())){ //학습근로자 개설교과
	        ReportVO reportVO1= new ReportVO(); 
	        loginInfo.putSessionToVo(reportVO1); // session의 정보를 VO에 추가.
	        reportVO1.setReportId(reportVO.getReportId());
	        reportVO1=reportService.getReportSubmit(reportVO1);
			model.addAttribute("result", reportVO1);
			
			retView = "oklms/mm/report/getReportStd";
			
		} else if("PRT".equals(loginInfo.getMemType())){ //담당교수 개설교과
			List<ReportVO> result=reportService.listReportSubmit(reportVO);
			model.addAttribute("result", result);
		}  
		
		
		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setFileSn(1);
		atchFileVO.setAtchFileId(reportVO.getAtchFileId());

		//첨부파일
		AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
        model.addAttribute("resultFile", resultFile);     
		        
		model.addAttribute("reportVO", reportVO );
  		// View호출
		return retView;
	}
}
