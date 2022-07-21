/*******************************************************************************
 * COPYRIGHT(C) 2016 WIZI LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of WIZI LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 12. 28.        First Draft.
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.lu.train.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.co.sitglobal.ifx.service.CmsIfxService;
import kr.co.sitglobal.ifx.vo.CmsCourseBaseVO;
import kr.co.sitglobal.ifx.vo.CmsCourseCodeVO;
import kr.co.sitglobal.ifx.vo.CmsCourseContentPropertiesVO;
import kr.co.sitglobal.ifx.vo.CmsCourseContentVO;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.commbiz.cmmcode.vo.CommonCodeVO;
import kr.co.sitglobal.oklms.lu.member.vo.MemberStdVO;
import kr.co.sitglobal.oklms.lu.train.service.TrainService;
import kr.co.sitglobal.oklms.lu.train.vo.TrainVO;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonElement;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
* Controller 클래스에 대한 내용을 작성한다.
* @author 이진근
* @since 2016. 10. 27.
*/
@Controller
public class TrainController extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(TrainController.class);
	
	/*@Resource(name = "trainService")
	private TrainService trainService;*/
	
	@Resource(name = "cmsIfxService")
	private CmsIfxService cmsIfxService;
	
	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;
	
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
	
	// 학과전담자 > 훈련일지 목록 메소드
	@RequestMapping(value = "/lu/train/listTrain.do")
	public String listTrain(@RequestParam Map<String, Object> commandMap, ModelMap model, @ModelAttribute("frmTrain") TrainVO trainVO, HttpServletRequest request) throws Exception {
		
		//year=2016&term=1&subjectCode=AAA001&subClass=01
		/*====================================================================
    	* 초기화 영역
    	====================================================================*/
		CommonCodeVO codeVO = new CommonCodeVO();
		
		//수정화면에서 업데이트시 memId항목이 필수값이므로 목록으로 포워딩시 Null로 초기화하는 로직추가함. - jglee
		/*memberVO.setMemId("");
		List<MemberVO> resultList = memberService.listMember( memberVO );
		
		//회원유형 공통코드 조회
		codeVO.setCodeGroup("member");
		List<CommonCodeVO> searchAuthGroupCodeList = commonCodeService.selectAuthGroupCodeList(codeVO);
		
		//회원검색 공통코드 조회
		codeVO.setCodeGroup("SEARCH_MEMBER");
		List<CommonCodeVO> searchMemberCodeList = commonCodeService.selectCmmCodeList(codeVO);
		
		//탈퇴여부 공통코드 조회
		codeVO.setCodeGroup("SCSN_YN");
		List<CommonCodeVO> searchScsnYnCodeList = commonCodeService.selectCmmCodeList(codeVO);*/
		CmsCourseBaseVO cmsCourseBaseVO =  new CmsCourseBaseVO();
		cmsCourseBaseVO.setAddURL("getCourseCodeSummaryList");  
		cmsCourseBaseVO.setYear("2016");
		List<CmsCourseCodeVO> resultList = new ArrayList<CmsCourseCodeVO>();
		//List<CmsCourseCodeVO> categoryCodeList = new ArrayList<CmsCourseCodeVO>();
		resultList = cmsIfxService.getCourseCodeSummaryList(cmsCourseBaseVO);
		System.out.println("---------------------------------");
		System.out.println("resultList : "+resultList.toString());
		System.out.println("---------------------------------");
		//List<TrainVO> resultList = trainService.listTrain( trainVO );
		//List<TrainVO> resultList = new ArrayList<TrainVO>();
		
	    /*for (int i = 0; i < resultList.size(); i++) {
	    	CmsCourseCodeVO cmsCourseCodeVO =  new CmsCourseCodeVO();
	    	cmsCourseCodeVO.setCategory_code(resultList.get(i).getCategory_code());
	    	cmsCourseCodeVO.setCategory_name(resultList.get(i).getCategory_name());
	        categoryCodeList.add(cmsCourseCodeVO);
	    }*/

		Integer pageSize = trainVO.getPageSize();
		Integer page = trainVO.getPageIndex();
		Integer pageTemp = trainVO.getPageIndex();
		
		if(1 != page){
			pageSize = pageTemp*10;
			page = pageSize-9;
		} else if(1 == page){
			page = 1;
			pageSize = 10;
		}
		
		int totalCnt = 0;
		if( 0 < resultList.size() ){
			//totalCnt = Integer.parseInt( resultList.get(0).getTotal_count() );
			totalCnt = resultList.size();
		}
		int totalPage = (int) Math.ceil(totalCnt / pageSize);

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);
        model.addAttribute("page", page);

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(trainVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(trainVO.getPageUnit());
        paginationInfo.setPageSize(trainVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);
        
        System.out.println("---------------------------------");
		System.out.println("currentPageNo : "+paginationInfo.getCurrentPageNo());
		System.out.println("recordCountPerPage : "+paginationInfo.getRecordCountPerPage());
		System.out.println("pageSize : "+paginationInfo.getPageSize());
		System.out.println("totalRecordCount : "+paginationInfo.getTotalRecordCount());
		System.out.println("---------------------------------");

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);

        model.addAttribute("trainVO", trainVO);
        model.addAttribute("resultList", resultList);
        //model.addAttribute("categoryCode", categoryCodeList);
        /*model.addAttribute("searchAuthGroupCode", searchAuthGroupCodeList);
        model.addAttribute("searchMemberCode", searchMemberCodeList);
        model.addAttribute("searchScsnYnCode", searchScsnYnCodeList);*/

        /*====================================================================
    	* 업무화면 Jsp File Path 셋팅영역
    	====================================================================*/
		return "oklms/lu/train/listTrain";
	}
	
	// 학과전담자 > 훈련일지 상세 메소드
	@RequestMapping(value = "/lu/train/getTrain.do")
	public String getTrain(@RequestParam Map<String, Object> commandMap, ModelMap model, @ModelAttribute("frmTrain") TrainVO trainVO, HttpServletRequest request) throws Exception {
		//trainVO = trainService.getMember( trainVO );

		//year=2016&term=1&subjectCode=AAA001&subClass=01
		/*====================================================================
    	* 초기화 영역
    	====================================================================*/
		CommonCodeVO codeVO = new CommonCodeVO();
		
		//수정화면에서 업데이트시 memId항목이 필수값이므로 목록으로 포워딩시 Null로 초기화하는 로직추가함. - jglee
		/*memberVO.setMemId("");
		List<MemberVO> resultList = memberService.listMember( memberVO );
		
		//회원유형 공통코드 조회
		codeVO.setCodeGroup("member");getTrain
		List<CommonCodeVO> searchAuthGroupCodeList = commonCodeService.selectAuthGroupCodeList(codeVO);
		
		//회원검색 공통코드 조회
		codeVO.setCodeGroup("SEARCH_MEMBER");
		List<CommonCodeVO> searchMemberCodeList = commonCodeService.selectCmmCodeList(codeVO);
		
		//탈퇴여부 공통코드 조회
		codeVO.setCodeGroup("SCSN_YN");
		List<CommonCodeVO> searchScsnYnCodeList = commonCodeService.selectCmmCodeList(codeVO);*/
		
		String strId = (String) commandMap.get("id");
		String strCourseCodeId = "";
		
		CmsCourseBaseVO cmsCourseBaseVO =  new CmsCourseBaseVO();
		cmsCourseBaseVO.setAddURL("getCourseContent");  
		cmsCourseBaseVO.setId(strId);
		List<CmsCourseContentPropertiesVO> detailData = cmsIfxService.getCourseContent(cmsCourseBaseVO);
		System.out.println("---------------------------------");
		System.out.println("detailData : "+detailData.toString());
		System.out.println("---------------------------------");
		
		strCourseCodeId = detailData.get(0).getCourse_code_id();
		System.out.println("---------------------------------");
		System.out.println("strCourseCodeId : "+strCourseCodeId);
		System.out.println("---------------------------------");
		
		cmsCourseBaseVO =  new CmsCourseBaseVO();
		cmsCourseBaseVO.setAddURL("getCourseContentSummaryList");  
		cmsCourseBaseVO.setCourseCodeId(strCourseCodeId);
		
		List<CmsCourseContentVO> resultList = cmsIfxService.getCourseContentSummaryList(cmsCourseBaseVO);
		System.out.println("---------------------------------");
		System.out.println("resultList : "+resultList.toString());
		System.out.println("---------------------------------");
		
		

		Integer pageSize = trainVO.getPageSize();
		Integer page = trainVO.getPageIndex();
		Integer pageTemp = trainVO.getPageIndex();
		
		if(1 != page){
			pageSize = pageTemp*10;
			page = pageSize-9;
		} else if(1 == page){
			page = 1;
			pageSize = 10;
		}
		
		int totalCnt = 0;
		if( 0 < resultList.size() ){
			//totalCnt = Integer.parseInt( resultList.get(0).getTotal_count() );
			totalCnt = resultList.size();
		}
		int totalPage = (int) Math.ceil(totalCnt / pageSize);

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);
        model.addAttribute("page", page);

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(trainVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(trainVO.getPageUnit());
        paginationInfo.setPageSize(trainVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);
        
        System.out.println("---------------------------------");
		System.out.println("currentPageNo : "+paginationInfo.getCurrentPageNo());
		System.out.println("recordCountPerPage : "+paginationInfo.getRecordCountPerPage());
		System.out.println("pageSize : "+paginationInfo.getPageSize());
		System.out.println("totalRecordCount : "+paginationInfo.getTotalRecordCount());
		System.out.println("---------------------------------");

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);

        model.addAttribute("trainVO", trainVO);
        model.addAttribute("resultDetailData", detailData);
        model.addAttribute("resultList", resultList);
        //model.addAttribute("categoryCode", categoryCodeList);
        /*model.addAttribute("searchAuthGroupCode", searchAuthGroupCodeList);
        model.addAttribute("searchMemberCode", searchMemberCodeList);
        model.addAttribute("searchScsnYnCode", searchScsnYnCodeList);*/

		// View호출
		return "oklms/lu/train/trainRead";
	}
	
	@RequestMapping(value = "/lu/train/listTrain.json")
	public @ResponseBody Map<String, Object> traningNote(@RequestParam Map<String, Object> commandMap, @ModelAttribute("frmTran") CmsCourseBaseVO cmsCourseBaseVO 
		,RedirectAttributes redirectAttributes
		,SessionStatus status
		,HttpServletRequest request
		,ModelMap model) throws Exception {
		
		Map<String , Object> returnMap = new HashMap<String , Object>();
		String retCd = "SUCCESS";
		String retMsg = "";
		try {
		   String strId = (String) commandMap.get("id");
		   cmsCourseBaseVO.setAddURL("getCourseContentItemList");  
		   cmsCourseBaseVO.setCourseContentId(strId); 
		   String  data = cmsIfxService.getCmsData(cmsCourseBaseVO);
		   //JSONObject jsonObj = new JSONObject();
		   //jsonObj.put("result", data);
		   //retMsg = jsonObj.toString();
		   retMsg = data;
		 } catch (Exception e) {
		    e.printStackTrace();
		 }
		
		returnMap.put("retCd", retCd);
		returnMap.put("retMsg", retMsg);
		return returnMap;
	}
	
	@RequestMapping(value = "/lu/train/trainNote.json")
	public @ResponseBody Map<String, Object> traningNote(@ModelAttribute("frmTran") CmsCourseBaseVO cmsCourseBaseVO 
		,RedirectAttributes redirectAttributes
		,SessionStatus status
		,HttpServletRequest request
		,ModelMap model) throws Exception {
		
		Map<String , Object> returnMap = new HashMap<String , Object>();
		String retCd = "SUCCESS";
		String retMsg = "";
		try {
		   cmsCourseBaseVO.setAddURL("viewLesson");  
		   HashMap<String, String>  data = cmsIfxService.viewLesson(cmsCourseBaseVO);
		   JSONObject jsonObj = new JSONObject();
		   jsonObj.put("result", data);
		   retMsg = jsonObj.toString();
		 } catch (Exception e) {
		    e.printStackTrace();
		 }
		
		
		returnMap.put("retCd", retCd);
		returnMap.put("retMsg", retMsg);
		return returnMap;
	}
		
}
