package kr.co.sitglobal.oklms.mm.traningstatus.web;
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
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.online.service.OnlineTraningService;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningVO;
import kr.co.sitglobal.oklms.lu.report.service.ReportService;
import kr.co.sitglobal.oklms.lu.traningstatus.service.TraningStatusService;
import kr.co.sitglobal.oklms.lu.traningstatus.vo.TraningStatusVO;

@Controller
public class TraningstatusMMController  extends BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(  TraningstatusMMController.class);
	
	@Resource(name = "ifxService")
	private IfxService ifxService;
	
	@Resource(name = "ReportService")
	private ReportService reportService;
	
	@Resource(name = "TraningStatusService")
	private TraningStatusService traningStatusService;
	
	@Resource(name = "OnlineTraningService")
	private OnlineTraningService onlineTraningService;
	
	@RequestMapping(value = "/mm/traningstatus/listTraningstatus.do")
	public String listTraningstatus(@ModelAttribute("frmTraningstatus") TraningStatusVO traningStatusVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /mm/traningstatus/listTraningstatus.do" );
		String retView = "oklms/mm/traningstatus/listTraningstatus";
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(traningStatusVO); // session의 정보를 VO에 추가. 
		
		//API 통한 교과정보, 관리자 제외한 진행중인 교과정보.
		AunuriMemberVO aunuriMemberVO = new AunuriMemberVO();
		aunuriMemberVO.setSessionMemSeq(loginInfo.getMemSeq());
		
		if("PRT".equals(loginInfo.getMemType())){ //담당교수 개설교과
			List<AunuriSubjectVO> listOjtAunuriSubject= ifxService.getOjtAunuriSubjectInsMapping(aunuriMemberVO);
			model.addAttribute( "listOjtAunuriSubject", listOjtAunuriSubject );
			List<AunuriSubjectVO> listOffJtAunuriSubject= ifxService.getOffJtAunuriSubjectInsMapping(aunuriMemberVO);
			model.addAttribute( "listOffJtAunuriSubject", listOffJtAunuriSubject );
			int hSkillCnt = ifxService.getOjtAunuriSubjectInsHSkillCnt(aunuriMemberVO);
			model.addAttribute( "hSkillCnt", hSkillCnt ); 
			
		} else if("COT".equals(loginInfo.getMemType())){ //기업현장교사 개설교과
			List<AunuriSubjectVO> listOjtAunuriSubject= ifxService.getOjtAunuriSubjectTutMapping(aunuriMemberVO);
			model.addAttribute( "listOjtAunuriSubject", listOjtAunuriSubject ); 			
		}
		
		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy(traningStatusVO.getYyyy());
		currProcVO.setTerm(traningStatusVO.getTerm());
		currProcVO.setSubjectCode(traningStatusVO.getSubjectCode());
		currProcVO.setSubClass(traningStatusVO.getClassId());
		currProcVO = reportService.getCurrproc(currProcVO);
		model.addAttribute("currProcVO", currProcVO);
		
		// 출석정책조회
		OnlineTraningVO onlineTraningVO = new OnlineTraningVO();
		onlineTraningVO.setYyyy(traningStatusVO.getYyyy());
		onlineTraningVO.setTerm(traningStatusVO.getTerm());
		onlineTraningVO.setSubjectCode(traningStatusVO.getSubjectCode());
		onlineTraningVO.setSubjectClass(traningStatusVO.getClassId());
		
		onlineTraningVO = onlineTraningService.getOnlineTraningStand(onlineTraningVO);
		model.addAttribute("onlineTraningVO", onlineTraningVO);
		
		//전체조회 목록
		List<TraningStatusVO> resultlist = traningStatusService.listTraningStatus(traningStatusVO);
		model.addAttribute("resultlist", resultlist);
				
		if(currProcVO!=null && currProcVO.getSubjectTraningType()!=null && currProcVO.getSubjectTraningType().equals("OJT")  ){
			retView = "oklms/mm/traningstatus/listTraningstatusOjt";
		}else{
			retView = "oklms/mm/traningstatus/listTraningstatus";
		}
		 
		model.addAttribute("traningStatusVO", traningStatusVO);
  		// View호출		
		return retView;
	}
}
