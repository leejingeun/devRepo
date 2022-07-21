package kr.co.sitglobal.oklms.mm.discuss.web;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import kr.co.sitglobal.oklms.lu.currproc.service.CurrProcService;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.discuss.service.DiscussService;
import kr.co.sitglobal.oklms.lu.discuss.vo.DiscussVO;

@Controller
public class DiscussMMController  extends BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(  DiscussMMController.class);
	
	@Resource(name = "discussService")
	private DiscussService discussService;
	
	@Resource(name = "currProcService")
	private CurrProcService currProcService;
	
	@Resource(name = "ifxService")
	private IfxService ifxService;
	
	@RequestMapping(value = "/mm/discuss/listDiscuss.do")
	public String listDiscuss(@ModelAttribute("frmDiscuss") DiscussVO discussVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		LOG.debug("#### URL = /mm/discuss/listCurriculum.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(discussVO); // session의 정보를 VO에 추가. 
		
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
  		loginInfo.putSessionToVo(currProcVO); // session의 정보를 VO에 추가. 
		currProcVO.setYyyy(discussVO.getYyyy());
		currProcVO.setTerm(discussVO.getTerm());
		currProcVO.setSubjectCode(discussVO.getSubjectCode());
		currProcVO.setSubClass(discussVO.getSubClass());
		 
		
		currProcVO = currProcService.getMySubjectInfo(currProcVO);
		
		List<DiscussVO> listDiscussInfo = discussService.listDiscussInfo(discussVO);
		List<DiscussVO> listDiscussOpinion = discussService.listDiscussOpinion(discussVO);
		
		model.addAttribute("currProcReadVO", currProcVO); 					//개설강좌 정보
		model.addAttribute("resultDiscussList", listDiscussInfo); 				//토론 목록
		model.addAttribute("resultDiscussOpinionList", listDiscussOpinion);     //토론에대한 의견 목록
				
		
		model.addAttribute("discussVO", discussVO); //토론 파라메터정보
  		// View호출
		return "oklms/mm/discuss/listDiscuss";
	}
	
	@RequestMapping(value = "/mm/discuss/getDiscuss.do")
	public String getDiscuss(@ModelAttribute("frmDiscuss") DiscussVO discussVO, ModelMap model) throws Exception {
		
  		LOG.debug("#### URL = /mm/discuss/getDiscuss.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(discussVO); // session의 정보를 VO에 추가. 
		 
		
		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
  		loginInfo.putSessionToVo(currProcVO); // session의 정보를 VO에 추가. 
		currProcVO.setYyyy(discussVO.getYyyy());
		currProcVO.setTerm(discussVO.getTerm());
		currProcVO.setSubjectCode(discussVO.getSubjectCode());
		currProcVO.setSubClass(discussVO.getSubClass());
		currProcVO = currProcService.getMySubjectInfo(currProcVO);
		model.addAttribute("currProcReadVO", currProcVO); 					//개설강좌 정보
		
		DiscussVO discussReadVO = discussService.getDiscussInfo(discussVO);
		List<DiscussVO> listDiscussOpinion = discussService.listDiscussOpinion(discussVO);
		for(int a=0;listDiscussOpinion.size()>a;a++){
			DiscussVO distemp =listDiscussOpinion.get(a);
			List<DiscussVO> listDiscussOpinionComment = discussService.listDiscussComment(distemp);  //토론의견 댓글 목록
			listDiscussOpinion.get(a).setListDiscussOpinionComment(listDiscussOpinionComment);
		}		
		model.addAttribute("discussReadVO", discussReadVO);    //토론 상세조회
		model.addAttribute("resultDiscussOpinionList", listDiscussOpinion);     //토론에대한 의견 목록
		
		model.addAttribute("discussVO", discussVO); //토론 파라메터정보
  		// View호출
		return "oklms/mm/discuss/getDiscuss";
	}
	
	@RequestMapping(value = "/mm/discuss/goInsertDiscuss.do")
	public String goInsertDiscuss(@ModelAttribute("frmDiscuss") DiscussVO discussVO, ModelMap model) throws Exception {
		
  		LOG.debug("#### URL = /mm/discuss/goInsertDiscuss.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(discussVO); // session의 정보를 VO에 추가. 
		
		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
  		loginInfo.putSessionToVo(currProcVO); // session의 정보를 VO에 추가. 
		currProcVO.setYyyy(discussVO.getYyyy());
		currProcVO.setTerm(discussVO.getTerm());
		currProcVO.setSubjectCode(discussVO.getSubjectCode());
		currProcVO.setSubClass(discussVO.getSubClass());
		currProcVO = currProcService.getMySubjectInfo(currProcVO);
		model.addAttribute("currProcReadVO", currProcVO); 					//개설강좌 정보		 

		DiscussVO discussReadVO = discussService.getDiscussInfo(discussVO);		 
		model.addAttribute("discussReadVO", discussReadVO);    //토론 상세조회 		
		
		model.addAttribute("discussVO", discussVO); //토론 파라메터정보
  		// View호출
		return "oklms/mm/discuss/insertDiscuss";
	}
	
	@RequestMapping(value = "/mm/discuss/insertDiscuss.do")
	public String insertDiscuss(@ModelAttribute("frmDiscuss") DiscussVO discussVO, ModelMap model,RedirectAttributes redirectAttributes) throws Exception {
		
  		LOG.debug("#### URL = /mm/discuss/insertDiscuss.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(discussVO); // session의 정보를 VO에 추가. 
		
		int insertCnt = discussService.insertDiscussOpinion(discussVO);
		String retMsg="";
		
		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}
		
		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		
		model.addAttribute("discussVO", discussVO); //토론 파라메터정보
  		// View호출 
		return "redirect:/mm/discuss/getDiscuss.do?yyyy="+discussVO.getYyyy()+"&term="+discussVO.getTerm()+"&subjectCode="+discussVO.getSubjectCode()+"&subClass="+discussVO.getSubClass()+"&discussId="+discussVO.getDiscussId();
	}
	
	/**
	 * 토론의견 댓글 신규추가건 저장
	 * @param discussVO 
	 * @return DiscussVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/discuss/insertDiscussComment.do")
	public String insertDiscussComment(@ModelAttribute("frmDiscuss") DiscussVO discussVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		
		LOG.debug("#### URL = /mm/discuss/insertDiscussComment.do" );;

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
		
		return "redirect:/mm/discuss/getDiscuss.do?yyyy="+discussVO.getYyyy()+"&term="+discussVO.getTerm()+"&subjectCode="+discussVO.getSubjectCode()+"&subClass="+discussVO.getSubClass()+"&discussId="+discussVO.getDiscussId();
	}
	
	@RequestMapping(value = "/mm/discuss/selectDiscuss.do")
	public String selectDiscuss(@ModelAttribute("frmDiscuss") DiscussVO discussVO, ModelMap model) throws Exception {
		
  		LOG.debug("#### URL = /mm/discuss/selectDiscuss.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(discussVO); // session의 정보를 VO에 추가. 
		 
		
		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
  		loginInfo.putSessionToVo(currProcVO); // session의 정보를 VO에 추가. 
		currProcVO.setYyyy(discussVO.getYyyy());
		currProcVO.setTerm(discussVO.getTerm());
		currProcVO.setSubjectCode(discussVO.getSubjectCode());
		currProcVO.setSubClass(discussVO.getSubClass());
		currProcVO = currProcService.getMySubjectInfo(currProcVO);
		model.addAttribute("currProcReadVO", currProcVO); 					//개설강좌 정보		 
		
		DiscussVO discussReadVO = discussService.getDiscussInfo(discussVO);		 
		model.addAttribute("discussReadVO", discussReadVO);    //토론 상세조회 		
		
		List<DiscussVO> listEvalScoreResultStd = discussService.listDiscussEvalScoreResultStd(discussVO);
		model.addAttribute("resultEvalScoreStdList", listEvalScoreResultStd);     //토론에 평가결과점수 저장 목록
		
		model.addAttribute("discussVO", discussVO); //토론 파라메터정보
  		// View호출
		return "oklms/mm/discuss/selectDiscuss";
	}	
}
