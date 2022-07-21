package kr.co.sitglobal.oklms.lu.activitydesc.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.LoginVO;
import kr.co.sitglobal.ifx.service.CmsIfxService;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.la.company.service.CompanyService;
import kr.co.sitglobal.oklms.lu.activitydesc.service.ActivityNoteDescService;
import kr.co.sitglobal.oklms.lu.activitydesc.vo.ActivityNoteVO;
import kr.co.sitglobal.oklms.lu.interview.service.InterviewService;
import kr.co.sitglobal.oklms.lu.interview.vo.InterviewCompanyVO;
import kr.co.sitglobal.oklms.lu.traning.web.TraningController;

/**
 * 활동내역서
 * @author user
 *
 */
@Controller
public class ActivityNoteDescController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityNoteDescController.class);

	@Resource(name = "companyService")
	private CompanyService companyService;

	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;

	@Resource(name = "beanValidatorJSR303")
	Validator validator;

	@Resource(name = "cmsIfxService")
	private CmsIfxService cmsIfxService;

	@Resource(name="activityNoteDescService")
	private ActivityNoteDescService activityNoteDescService;

	@Resource(name = "InterviewService")
	private InterviewService interviewService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder){
		try {
			dataBinder.setValidator(this.validator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 활동 내역
	 * @param activityVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value= "/lu/activitydesc/listActivityDesc.do" )
	public String listActivityDesc (@ModelAttribute("frmActivity") ActivityNoteVO activityVO, ModelMap model )throws Exception{
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(activityVO); // session의 정보를 VO에 추가.

  		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
  		interviewCompanyVO.setCompanyId(activityVO.getCompanyId());
  		interviewCompanyVO.setTraningProcessId(activityVO.getTraningProcessId());

  		InterviewCompanyVO result=interviewService.InterviewCompany(interviewCompanyVO);
  		model.addAttribute("result", result);

		List<ActivityNoteVO> resultList=null;
		// 훈련과정이 있을 경우만조회
		if(result!=null){
			resultList = activityNoteDescService.listActivityDesc(activityVO);			
		}

		model.put("resultList" , resultList);
		model.put("activityVO", activityVO);
		return "oklms/lu/activitydesc/listActivityDesc";

	}
	/**
	 * 활동 내역 등록
	 * @param activityVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/lu/activitydesc/goInsertActivityDesc.do")
	public String goInsertActivityDesc (@ModelAttribute("frmActivity") ActivityNoteVO activityVO, ModelMap model )throws Exception{
 
		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
  		interviewCompanyVO.setCompanyId(activityVO.getCompanyId());
  		interviewCompanyVO.setTraningProcessId(activityVO.getTraningProcessId());

  		InterviewCompanyVO result=interviewService.InterviewCompany(interviewCompanyVO);		
  		
  		model.addAttribute("result", result);
  		model.put("activityVO", activityVO);
		//상세 조회 리스트
		List<ActivityNoteVO> resultList = activityNoteDescService.listActivityDescDetail(activityVO);
		model.put("resultList", resultList);  		
  		
		return "oklms/lu/activitydesc/activityDescProc";
	}
	/**
	 * 활동내역 등록 /수정
	 * @param activityVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/lu/activitydesc/insertActivityDesc.do")
	public String insertActivityDesc (@ModelAttribute("frmActivity") ActivityNoteVO activityVO,RedirectAttributes redirectAttributes, ModelMap model, HttpSession session )throws Exception{
		String retMsg = "입력값을 확인해주세요";
		int insertCnt = 0; 
		insertCnt = activityNoteDescService.insertActivityDesc(activityVO);


		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);

		return "redirect:/lu/activitydesc/listActivityDesc.do?companyId="+activityVO.getCompanyId()+"&traningProcessId="+activityVO.getTraningProcessId() ;
	}
	/**
	 * 활동 내역 출력시 제출확인 출력 하는 로직도 만들어야함 차후
	 * @param activityVO
	 * @param redirectAttributes
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/lu/activitydesc/deleteActivityDesc.do")
	public String deleteActivityDesc(@ModelAttribute("frmActivity") ActivityNoteVO activityVO,RedirectAttributes redirectAttributes, ModelMap model, HttpSession session )throws Exception{

		String retMsg = "입력값을 확인해주세요";
		int insertCnt = 0; 
		insertCnt = activityNoteDescService.deleteActivityDesc(activityVO);
		
		if(insertCnt > 0){
			retMsg = "정상적으로 삭제되었습니다.!";
		}else{
			retMsg = "처리된건이 없습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);

		return "redirect:/lu/activitydesc/goInsertActivityDesc.do?companyId="+activityVO.getCompanyId()+"&traningProcessId="+activityVO.getTraningProcessId()+"&yyyy="+activityVO.getYyyy()+"&mm="+activityVO.getMm()+"&activityType="+activityVO.getActivityType() ;
	}	
	/**
	 * 훈련관리 > 활동내역서 > 활동내역서 상세조회
	 * @param activityVO
	 * @param redirectAttributes
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/lu/activitydesc/getActivityDesc.do")
	public String getActivityDesc(@ModelAttribute("frmActivity") ActivityNoteVO activityVO , ModelMap model  )throws Exception{

		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
  		interviewCompanyVO.setCompanyId(activityVO.getCompanyId());
  		interviewCompanyVO.setTraningProcessId(activityVO.getTraningProcessId());

  		InterviewCompanyVO result=interviewService.InterviewCompany(interviewCompanyVO);	
		//상세 조회 리스트
		List<ActivityNoteVO> resultList = activityNoteDescService.listActivityDescDetail(activityVO);


		model.put("resultList", resultList);
		model.put("result", result);
		model.put("activityVO", activityVO);
		if(activityVO.getSubjectCode()!=null&&activityVO.getSubjectCode().equals("print")){
			return "oklms_popup/lu/activitydesc/printActivityDesc";
		}else{
			return "oklms/lu/activitydesc/activityDescRead";			
		}

		
	}
	/**
	 * 활동 내역 출력시 제출확인 출력 하는 로직도 만들어야함 차후
	 * @param activityVO
	 * @param redirectAttributes
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/lu/activitydesc/updateActivityDescPrint.do")
	public String updateActivityDescPrint(@ModelAttribute("frmActivity") ActivityNoteVO activityVO,RedirectAttributes redirectAttributes, ModelMap model, HttpSession session )throws Exception{

		String retMsg = "입력값을 확인해주세요";
		int insertCnt = 0;
		insertCnt = activityNoteDescService.updateActivityDescPrint(activityVO);


		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);

		return "redirect:/lu/activitydesc/listActivityDesc.do";
	}

}
