package kr.co.sitglobal.oklms.lu.interview.web;

import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource; 
import javax.servlet.http.HttpServletRequest; 
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest; 

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.co.sitglobal.ifx.service.IfxService; 
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.atchFile.vo.AtchFileVO;
import kr.co.sitglobal.oklms.commbiz.util.AtchFileUtil;
import kr.co.sitglobal.oklms.lu.interview.service.InterviewService; 
import kr.co.sitglobal.oklms.lu.interview.vo.InterviewCompanyVO;
import kr.co.sitglobal.oklms.lu.interview.vo.InterviewMemberVO;
import kr.co.sitglobal.oklms.lu.interview.vo.InterviewVO;  

@Controller
public class InterviewController  extends BaseController{
	
	private static final Logger LOG = LoggerFactory.getLogger(InterviewController.class);
	
	@Resource(name = "beanValidatorJSR303")
	Validator validator;
	
	@Resource(name = "InterviewService")
	private InterviewService interviewService;
	
	@Resource(name = "atchFileService")
	private AtchFileService atchFileService;

	@Resource(name = "ifxService")
	private IfxService ifxService;
	
	@Resource(name = "atchFileUtil")
	private AtchFileUtil atchFileUtil;
	
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder){
		try {
			dataBinder.setValidator(this.validator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

  	@RequestMapping(value = "/lu/interview/listInterview.do")
	public String listInterview(@ModelAttribute("frmInterview") InterviewVO interviewVO, ModelMap model, HttpServletRequest request) throws Exception {
  		LOG.debug("#### URL = /lu/interview/listInterview.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(interviewVO); // session의 정보를 VO에 추가.
		interviewVO.setInterviewMemSeq(interviewVO.getSessionMemSeq());
  		List<InterviewVO> result=interviewService.listInterview(interviewVO);
  		
		Integer pageSize = interviewVO.getPageSize();
		Integer page = interviewVO.getPageIndex();
		Integer pageTemp = interviewVO.getPageIndex();
		
		if(1 != page){
			pageSize = pageTemp*10;
			page = pageSize-9;
		} else if(1 == page){
			page = 1;
			pageSize = 10;
		}
		int totalCnt = 0;
		if(result!=null && 0 < result.size() ){
			if(result.get(0).getTotalCount()==null || result.get(0).getTotalCount().equals("")){
				//if(result.get(0).getTotalCount()==null){	
				totalCnt = 0;
			}else{
				totalCnt = Integer.parseInt( result.get(0).getTotalCount() );	
			}
			//totalCnt = result.size();
		}
	//	int totalPage = (int) Math.ceil(totalCnt / pageSize);

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(interviewVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(interviewVO.getPageUnit());
        paginationInfo.setPageSize(interviewVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);
        
        model.addAttribute("result", result );
        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);
        
        model.addAttribute("interviewVO", interviewVO );
        
		// View호출
		return "oklms/lu/interview/listInterview";
	}
	
  	@RequestMapping(value = "/lu/interview/goInsertInterview.do")
	public String goInsertInterview(@ModelAttribute("frmInterview") InterviewVO interviewVO, ModelMap model, HttpServletRequest request) throws Exception {
  		LOG.debug("#### URL = /lu/interview/goInsertInterview.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(interviewVO); // session의 정보를 VO에 추가.
		model.addAttribute("interviewVO", interviewVO );
		 		 
		InterviewCompanyVO 	interviewCompanyVO = new InterviewCompanyVO();
		loginInfo.putSessionToVo(interviewCompanyVO); // session의 정보를 VO에 추가.
		List<InterviewCompanyVO> resultlist=interviewService.listCompanyini(interviewCompanyVO);
		model.addAttribute("resultlist", resultlist );
		
		
		// View호출
		return "oklms/lu/interview/insertInterview";
	}
  	@RequestMapping(value = "/lu/interview/goUpdateInterview.do")
	public String goUpdateInterview(@ModelAttribute("frmInterview") InterviewVO interviewVO, ModelMap model, HttpServletRequest request) throws Exception {
  		LOG.debug("#### URL = /lu/interview/goUpdateInterview.do" );
  		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(interviewVO); // session의 정보를 VO에 추가.
		interviewVO.setInterviewMemSeq(interviewVO.getSessionMemSeq());
  		InterviewVO  result=interviewService.getInterview(interviewVO);
  		
		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setFileSn(1);
		atchFileVO.setAtchFileId(result.getAtchFileId());

		//첨부파일
		AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
        model.addAttribute("resultFile1", resultFile);     
        
		atchFileVO.setAtchFileId(result.getAtchFileImgId());
		//첨부이미지파일
		AtchFileVO resultFile2 = atchFileService.getAtchFile(atchFileVO);
        model.addAttribute("resultFile2", resultFile2);  
        
        model.addAttribute("result", result );
		InterviewCompanyVO 	interviewCompanyVO = new InterviewCompanyVO();
		loginInfo.putSessionToVo(interviewCompanyVO); // session의 정보를 VO에 추가.
		List<InterviewCompanyVO> resultlist=interviewService.listCompanyini(interviewCompanyVO);
		model.addAttribute("resultlist", resultlist );
        
        
		// View호출
		return "oklms/lu/interview/updateInterview";
	}
  	@RequestMapping(value = "/lu/interview/goUpdateInterviewiframe.do")
	public String goUpdateInterviewiframe(@ModelAttribute("frmInterview") InterviewVO interviewVO, ModelMap model, HttpServletRequest request) throws Exception {
  		LOG.debug("#### URL = /lu/interview/goUpdateInterviewiframe.do" );
  		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(interviewVO); // session의 정보를 VO에 추가.
		
  		InterviewVO  result=interviewService.getInterview(interviewVO);
  		
		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setFileSn(1);
		atchFileVO.setAtchFileId(result.getAtchFileId());

		//첨부파일
		AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
        model.addAttribute("resultFile1", resultFile);     
        
		atchFileVO.setAtchFileId(result.getAtchFileImgId());
		//첨부이미지파일
		AtchFileVO resultFile2 = atchFileService.getAtchFile(atchFileVO);
        model.addAttribute("resultFile2", resultFile2);  
        
        model.addAttribute("result", result );
		InterviewCompanyVO 	interviewCompanyVO = new InterviewCompanyVO();
		loginInfo.putSessionToVo(interviewCompanyVO); // session의 정보를 VO에 추가.
		
		interviewCompanyVO.setSessionMemSeq(interviewVO.getInterviewMemSeq());
		List<InterviewCompanyVO> resultlist;
		if(interviewVO.getSessionMemType().equals("CCN")){
			 resultlist=interviewService.listCompanyCcn(interviewCompanyVO);	
		}else{
			 resultlist=interviewService.listCompanyini(interviewCompanyVO);
		}
		model.addAttribute("resultlist", resultlist );
        
        
		// View호출
		return "oklms_popup/lu/interview/updateInterviewiframe";
	}  	
 	@RequestMapping(value = "/lu/interview/updateInterviewiframe.do")
	public String updateInterviewiframe(@ModelAttribute("frmInterview") InterviewVO interviewVO, ModelMap model, HttpServletRequest request,final MultipartHttpServletRequest multiRequest) throws Exception {
  		LOG.debug("#### URL = /lu/interview/updateInterviewiframe.do" );
  		String retMsg="";
  		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(interviewVO); // session의 정보를 VO에 추가.
		
		// 기업ID 훈련ID 추출
		String temp1 = interviewVO.getTraningProcessId();
		String[] temp2 = temp1.split(",");
		if(temp2!=null && temp2.length>1){
			interviewVO.setCompanyId(temp2[0]);
			interviewVO.setTraningProcessId(temp2[1]);
		}
		
		int resultnum = interviewService.updateInterview(interviewVO, multiRequest);
		if(resultnum==0){
			retMsg = "수정하지 못했습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			return "redirect:/lu/interview/listInterview.do?retMsgEncode="+retMsg;
		}		   
		// View호출//
		return "redirect:/lu/interview/goUpdateInterviewiframe.do?companyId="+ interviewVO.getCompanyId()+"&interviewMemSeq="+ interviewVO.getInterviewMemSeq() +"&interviewNoteId=" + interviewVO.getInterviewNoteId() ;
	}
 	
  	@RequestMapping(value = "/lu/interview/getInterview.do")
	public String getInterview(@ModelAttribute("frmInterview") InterviewVO interviewVO, ModelMap model, HttpServletRequest request) throws Exception {
  		LOG.debug("#### URL = /lu/interview/getInterview.do" );		
  		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(interviewVO); // session의 정보를 VO에 추가.
		interviewVO.setInterviewMemSeq(interviewVO.getSessionMemSeq());		
  		InterviewVO  result=interviewService.getInterview(interviewVO);
  		
		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setFileSn(1);
		atchFileVO.setAtchFileId(result.getAtchFileId());

		//첨부파일
		AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
        model.addAttribute("resultFile1", resultFile);     
        
		atchFileVO.setAtchFileId(result.getAtchFileImgId());
		//첨부이미지파일
		AtchFileVO resultFile2 = atchFileService.getAtchFile(atchFileVO);
        model.addAttribute("resultFile2", resultFile2);  
        
        model.addAttribute("result", result );
		// View호출
		return "oklms/lu/interview/getInterview";
	}
  	  	  	
  	@RequestMapping(value = "/lu/interview/printInterview.do")
	public String printInterview(@ModelAttribute("frmInterview") InterviewVO interviewVO, ModelMap model, HttpServletRequest request) throws Exception {
  		LOG.debug("#### URL = /lu/interview/printInterview.do" );		
  		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(interviewVO); // session의 정보를 VO에 추가.
		interviewVO.setInterviewMemSeq(interviewVO.getSessionMemSeq());		
  		InterviewVO  result=interviewService.getInterview(interviewVO);
  		
		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setFileSn(1);
		atchFileVO.setAtchFileId(result.getAtchFileId());

		//첨부파일
		AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);
        model.addAttribute("resultFile1", resultFile);     
        
		atchFileVO.setAtchFileId(result.getAtchFileImgId());
		//첨부이미지파일
		AtchFileVO resultFile2 = atchFileService.getAtchFile(atchFileVO);
        model.addAttribute("resultFile2", resultFile2);  
        
        model.addAttribute("result", result );
		// View호출
		return "oklms_popup/lu/interview/printInterview";
	}
  	
  	@RequestMapping(value = "/lu/interview/insertInterview.do")
	public String insertInterview(@ModelAttribute("frmInterview") InterviewVO interviewVO, ModelMap model, HttpServletRequest request,final MultipartHttpServletRequest multiRequest) throws Exception {
  		LOG.debug("#### URL = /lu/interview/insertInterview.do" );
  		String retMsg="";
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(interviewVO); // session의 정보를 VO에 추가.
		// 기업ID 훈련ID 추출
		String temp1 = interviewVO.getTraningProcessId();
		String[] temp2 = temp1.split(",");
		if(temp2!=null && temp2.length>1){
			interviewVO.setCompanyId(temp2[0]);
			interviewVO.setTraningProcessId(temp2[1]);
		}		
		// 작성교수
		interviewVO.setInterviewMemSeq(interviewVO.getSessionMemSeq());
		
		int resultnum = interviewService.insertInterview(interviewVO, multiRequest);
		if(resultnum==0){
			retMsg = "등록하지 못했습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			return "redirect:/lu/interview/listInterview.do?retMsgEncode="+retMsg;
		}
		// View호출
		return "redirect:/lu/interview/listInterview.do";
	}
  	@RequestMapping(value = "/lu/interview/updateInterview.do")
	public String updateInterview(@ModelAttribute("frmInterview") InterviewVO interviewVO, ModelMap model, HttpServletRequest request,final MultipartHttpServletRequest multiRequest) throws Exception {
  		LOG.debug("#### URL = /lu/interview/updateInterview.do" );
  		String retMsg="";
  		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(interviewVO); // session의 정보를 VO에 추가.
		
		// 기업ID 훈련ID 추출
		String temp1 = interviewVO.getTraningProcessId();
		String[] temp2 = temp1.split(",");
		if(temp2!=null && temp2.length>1){
			interviewVO.setCompanyId(temp2[0]);
			interviewVO.setTraningProcessId(temp2[1]);
		}
		
		int resultnum = interviewService.updateInterview(interviewVO, multiRequest);
		if(resultnum==0){
			retMsg = "수정하지 못했습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			return "redirect:/lu/interview/listInterview.do?retMsgEncode="+retMsg;
		}		   
		// View호출
		return "redirect:/lu/interview/getInterview.do?interviewNoteId="+interviewVO.getInterviewNoteId();
	}
  	@RequestMapping(value = "/lu/interview/deleteInterview.do")
	public String deleteInterview(@ModelAttribute("frmInterview") InterviewVO interviewVO, ModelMap model, HttpServletRequest request) throws Exception {
  		LOG.debug("#### URL = /lu/interview/deleteInterview.do" );
  		String retMsg="";
  		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(interviewVO); // session의 정보를 VO에 추가.
		int resultnum = interviewService.deleteInterview(interviewVO);
		if(resultnum==0){
			retMsg = "삭제하지 못했습니다.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			return "redirect:/lu/interview/listInterview.do?retMsgEncode="+retMsg;
		}
		// View호출
		return "redirect:/lu/interview/listInterview.do?retMsgEncode="+retMsg;
	}
  	/** 학습근로자조회 */
  	@RequestMapping(value = "/lu/interview/ajaxInterviewMember.do")
	public String ajaxInterviewMember(@ModelAttribute("frmInterview") InterviewVO interviewVO, ModelMap model, HttpServletRequest request) throws Exception {
  		LOG.debug("#### URL = /lu/interview/ajaxInterviewMember.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(interviewVO); // session의 정보를 VO에 추가.
		 
		if(interviewVO.getInterviewMemSeq()!=null && !interviewVO.getInterviewMemSeq().equals("")){
			interviewVO.setSessionMemSeq(interviewVO.getInterviewMemSeq());
		}
   		List<InterviewMemberVO> result=interviewService.InterviewMembers(interviewVO);
		

		Integer pageSize = interviewVO.getPageSize();
		Integer page = interviewVO.getPageIndex();
		Integer pageTemp = interviewVO.getPageIndex();
		
		if(1 != page){
			pageSize = pageTemp*10;
			page = pageSize-9;
		} else if(1 == page){
			page = 1;
			pageSize = 10;
		}
		int totalCnt = 0;
		if(result!=null && 0 < result.size() ){ 
			//totalCnt = result.get(0).getTotalCount();
			//totalCnt = Integer.parseInt( result.get(0).getTotalCount() );			
			if(result.get(0).getTotalCount()==null || result.get(0).getTotalCount().equals("")){
				totalCnt = 0;
			}else{
				totalCnt = Integer.parseInt( result.get(0).getTotalCount() );	
			}
		}
		
		
		
		//int totalPage = (int) Math.ceil(totalCnt / pageSize);

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(interviewVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(interviewVO.getPageUnit());
        paginationInfo.setPageSize(interviewVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);
        
        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("result", result );
        model.addAttribute("interviewVO",interviewVO);
		// View호출
		return "oklms_import/lu/interview/ajaxInterviewMember";
	}
  	/**
  	 *  센터전담자 권한
  	 * */
  	@RequestMapping(value = "/lu/interview/listInterviewCenter.do")
	public String listInterviewCenter(@ModelAttribute("frmInterview") InterviewVO interviewVO, ModelMap model, HttpServletRequest request) throws Exception {
  		LOG.debug("#### URL = /lu/interview/listInterviewCenter.do" );
  		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(interviewVO); // session의 정보를 VO에 추가.
		loginInfo.putSessionToVo(interviewCompanyVO); // session의 정보를 VO에 추가.
		
		interviewCompanyVO.setCompanyId(interviewVO.getCompanyId());
		interviewCompanyVO.setTraningProcessId(interviewVO.getTraningProcessId());
		InterviewCompanyVO result=interviewService.InterviewCompany(interviewCompanyVO);
		List<InterviewVO> resultlist= null;
		if(interviewVO.getCompanyId()!=null && !interviewVO.getCompanyId().equals("")){
			resultlist=interviewService.listInterviewCenter(interviewVO);
		}
		
		model.addAttribute("result", result );
		model.addAttribute("interviewVO",interviewVO);
		model.addAttribute("resultlist", resultlist );
		// View호출
		return "oklms/lu/interview/listInterviewCenter";
	}
	 	
  	@RequestMapping(value = "/lu/interview/getInterviewCenter.do")
	public String getInterviewCenter(@ModelAttribute("frmInterview") InterviewVO interviewVO, ModelMap model, HttpServletRequest request) throws Exception {
  		LOG.debug("#### URL = /lu/interview/getInterviewCenter.do" );
  		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(interviewVO); // session의 정보를 VO에 추가.
		loginInfo.putSessionToVo(interviewCompanyVO); // session의 정보를 VO에 추가.
		
		interviewCompanyVO.setCompanyId(interviewVO.getCompanyId());
		interviewCompanyVO.setTraningProcessId(interviewVO.getTraningProcessId());
		// 조회월
		String param = interviewVO.getInterviewNoteDateMmParam();
		if(interviewVO.getMm()==null || interviewVO.getMm().equals("")){
			interviewVO.setMm(param.substring(5, 7));
		}
		
		InterviewCompanyVO result=interviewService.InterviewCompany(interviewCompanyVO);
		List<InterviewVO> resultlist=interviewService.getInterviewCenter(interviewVO);
		if(interviewVO.getSendYn().equals("Y")){
			// 제출확인 입력
			interviewService.updateInterviewCenterSend(interviewVO);			
		}
		model.addAttribute("interviewVO", interviewVO );		
		model.addAttribute("result", result );
		model.addAttribute("resultlist", resultlist );  	 
		// View호출
		return "oklms/lu/interview/getInterviewCenter";
	}
  	
  	@RequestMapping(value = "/lu/interview/goUpdateInterviewCenter.do")
	public String goUpdateInterviewCenter(@ModelAttribute("frmInterview") InterviewVO interviewVO, ModelMap model, HttpServletRequest request) throws Exception {
  		LOG.debug("#### URL = /lu/interview/goUpdateInterviewCenter.do" );
  		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(interviewVO); // session의 정보를 VO에 추가.
		loginInfo.putSessionToVo(interviewCompanyVO); // session의 정보를 VO에 추가.
		
		interviewCompanyVO.setCompanyId(interviewVO.getCompanyId());
		interviewCompanyVO.setTraningProcessId(interviewVO.getTraningProcessId());
		InterviewCompanyVO result=interviewService.InterviewCompany(interviewCompanyVO);
		List<InterviewVO> resultlist=interviewService.getInterviewCenter(interviewVO);
 
		
		model.addAttribute("interviewVO", interviewVO );		
		model.addAttribute("result", result );
		model.addAttribute("resultlist", resultlist );  	 
		// View호출
		return "oklms/lu/interview/updateInterviewCenter";
	}
  	/** 출력    */ 
  	@RequestMapping(value = "/lu/interview/printInterviewCenter.do")
	public String printInterviewCenter(@ModelAttribute("frmInterview") InterviewVO interviewVO, ModelMap model, HttpServletRequest request) throws Exception {
  		LOG.debug("#### URL = /lu/interview/printInterviewCenter.do" );
  		InterviewCompanyVO interviewCompanyVO = new InterviewCompanyVO();
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(interviewVO); // session의 정보를 VO에 추가.
		loginInfo.putSessionToVo(interviewCompanyVO); // session의 정보를 VO에 추가.
		
		interviewCompanyVO.setCompanyId(interviewVO.getCompanyId());
		interviewCompanyVO.setTraningProcessId(interviewVO.getTraningProcessId());
		
		// 조회월
		String param = interviewVO.getInterviewNoteDateMmParam();
		if(interviewVO.getMm()==null || interviewVO.getMm().equals("")){
			interviewVO.setMm(param.substring(5, 7));
		}
				
		InterviewCompanyVO result=interviewService.InterviewCompany(interviewCompanyVO);
		List<InterviewVO> resultlist=interviewService.getInterviewCenter(interviewVO);

		model.addAttribute("interviewVO", interviewVO );		
		model.addAttribute("result", result );
		model.addAttribute("resultlist", resultlist );  	 
		// View호출
		return "oklms_popup/lu/interview/printInterviewCenter";
	}
  	/** 기업체조회 */
  	@RequestMapping(value = "/lu/interview/ajaxInterviewCompany.do")
	public String ajaxInterviewCompany(@ModelAttribute("frmInterview") InterviewCompanyVO interviewCompanyVO, ModelMap model, HttpServletRequest request) throws Exception {
  		LOG.debug("#### URL = /lu/interview/ajaxInterviewCompany.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(interviewCompanyVO); // session의 정보를 VO에 추가.
		

  		List<InterviewCompanyVO> result=interviewService.InterviewCompanys(interviewCompanyVO);
		

		Integer pageSize = interviewCompanyVO.getPageSize();
		Integer page = interviewCompanyVO.getPageIndex();
		Integer pageTemp = interviewCompanyVO.getPageIndex();
		
		if(1 != page){
			pageSize = pageTemp*10;
			page = pageSize-9;
		} else if(1 == page){
			page = 1;
			pageSize = 10;
		}
		int totalCnt = 0;
		if( result!=null && 0 < result.size() ){ 
			//totalCnt = result.size();
			//totalCnt = Integer.parseInt( result.get(0).getTotalCount() );
			//if(result.get(0).getTotalCount()==null){
			if(result.get(0).getTotalCount()==null || result.get(0).getTotalCount().equals("")){	
				totalCnt = 0;
			}else{
				totalCnt = Integer.parseInt( result.get(0).getTotalCount() );	
			}
		}
		//int totalPage = (int) Math.ceil(totalCnt / pageSize);

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(interviewCompanyVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(interviewCompanyVO.getPageUnit());
        paginationInfo.setPageSize(interviewCompanyVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);
        
        
        model.addAttribute("searchCompanyName",interviewCompanyVO.getSearchCompanyName());
        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("result", result );
  		 
		// View호출
		return "oklms_import/lu/interview/ajaxInterviewCompany";
	}
}
