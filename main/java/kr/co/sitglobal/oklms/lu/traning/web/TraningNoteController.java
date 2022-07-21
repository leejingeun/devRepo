/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 07. 01.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.lu.traning.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.co.sitglobal.ifx.service.CmsIfxService;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.commbiz.util.BizUtil;
import kr.co.sitglobal.oklms.la.company.service.CompanyService;
import kr.co.sitglobal.oklms.lu.currproc.service.CurrProcService;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningSchVO;
import kr.co.sitglobal.oklms.lu.report.service.ReportService;
import kr.co.sitglobal.oklms.lu.traning.service.TraningNoteSerivce;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningNoteVO;

import org.apache.commons.lang3.StringUtils;
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
import egovframework.com.cmm.service.Globals;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class TraningNoteController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(TraningNoteController.class);


	@Resource(name = "traningNoteSerivce")
	private TraningNoteSerivce traningNoteSerivce;

	@Resource(name = "companyService")
	private CompanyService companyService;

	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;

	@Resource(name = "currProcService")
	private CurrProcService currProcService;

	@Resource(name = "atchFileService")
	private AtchFileService atchFileService;

	@Resource(name = "beanValidatorJSR303")
	Validator validator;

	@Resource(name = "cmsIfxService")
	private CmsIfxService cmsIfxService;

	@Resource(name = "ReportService")
	private ReportService reportService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder){
		try {
			dataBinder.setValidator(this.validator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 훈련지원 > 훈련일지 교수자
	 * @param cmsCourseBaseVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/traning/listTraningNote.do")
	public String listTraningNote(@ModelAttribute("frmTraning") TraningNoteVO  traningNoteVO, ModelMap model, HttpSession session  ) throws Exception {

		LOGGER.debug("#### URL = /lu/traning/listTraningNote.do" );
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.
 
		
   		traningNoteVO.setYyyy(StringUtils.defaultIfBlank( traningNoteVO.getYyyy() ,(String)session.getAttribute(Globals.YYYY)));
		traningNoteVO.setTerm(StringUtils.defaultIfBlank( traningNoteVO.getTerm() ,(String)session.getAttribute(Globals.TERM)));  
		traningNoteVO.setSubjectCode(StringUtils.defaultIfBlank( traningNoteVO.getSubjectCode() ,(String)session.getAttribute(Globals.SUBJECT_CODE)));
		String subjectTraningType = StringUtils.defaultIfBlank( traningNoteVO.getSubjectTraningType() ,(String)session.getAttribute(Globals.SUBJECT_TRANING_TYPE)) ;
		
		if(traningNoteVO.getClassId()==null||traningNoteVO.getClassId().equals("")){
			traningNoteVO.setClassId(StringUtils.defaultIfBlank( traningNoteVO.getClassId() ,(String)session.getAttribute(Globals.CLASS)));
		}
		
	 
			session.setAttribute(Globals.YYYY , traningNoteVO.getYyyy() );         		// 학년도
			session.setAttribute(Globals.TERM , traningNoteVO.getTerm() );      			// 학기 
			session.setAttribute(Globals.SUBJECT_CODE , traningNoteVO.getSubjectCode() );  // 교과목코드
			session.setAttribute(Globals.CLASS , traningNoteVO.getClassId() );      		// 분반
			session.setAttribute(Globals.SUBJECT_TRANING_TYPE,subjectTraningType);
		 		        
		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy(traningNoteVO.getYyyy());
		currProcVO.setTerm(traningNoteVO.getTerm());
		currProcVO.setSubjectCode(traningNoteVO.getSubjectCode());
		currProcVO.setSubClass(traningNoteVO.getClassId());
		
		if(subjectTraningType!=null && subjectTraningType.equals("OJT")){

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

					return "oklms/lu/traning/listTraningNoteOjt";		
				}

			}
			// 주차정보 
			List<OnlineTraningSchVO> weeklist =reportService.listLmsSubjWeek(currProcVO);
			model.addAttribute("weeklist", weeklist);
			
			currProcVO = reportService.getCurrproc(currProcVO);
			
			// 과정정보없을시 무조건 이동
			if(currProcVO==null){

				return "oklms/lu/traning/listTraningNoteOjt";
			}

			traningNoteVO.setMemSeq(loginVO.getMemSeq());
						
			if(traningNoteVO.getWeekCnt() == null|| traningNoteVO.getWeekCnt().equals("")){

				// 최근주차정보조회
				TraningNoteVO  traningNowWeekCn = traningNoteSerivce.getTraningNowWeekCnt(traningNoteVO);	
				if(traningNowWeekCn.getWeekCnt()==null||traningNowWeekCn.getWeekCnt().equals("")){

					traningNoteVO.setWeekCnt("1");
				}else{

					traningNoteVO.setWeekCnt(traningNowWeekCn.getWeekCnt());
				}
			}

			//공통상단 데이터
			TraningNoteVO result = traningNoteSerivce.getTraningNote(traningNoteVO);
			if(result!= null ){

				result.setClassId(traningNoteVO.getClassId());
				result.setCompanyId(traningNoteVO.getCompanyId());				
			}

			model.put("TraningNoteVO", result);
		 
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
				return "oklms/lu/traning/listTraningNoteOjtHs";	
			}else{
				return "oklms/lu/traning/listTraningNoteOjt";				
			}

		}else{
			
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
			//공통상단 데이터
			TraningNoteVO result = traningNoteSerivce.getTraningNote(traningNoteVO);
			model.put("TraningNoteVO", result);
			
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
			
			
			return "oklms/lu/traning/listTraningNote";
		}
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
	@RequestMapping(value = "/lu/traning/goInsertTraningNote.do")
	public String goInsertTraningNote(@ModelAttribute("frmTraning")TraningNoteVO  traningNoteVO, ModelMap model, HttpSession session,  RedirectAttributes redirectAttributes ) throws Exception {

		LOGGER.debug("#### URL = /lu/traning/listTraningNote.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.
		
		String retMsg = "입력값을 확인해주세요";
		int insertCnt = 0;
		/*
		traningNoteVO.setYyyy((String)session.getAttribute("SESSION_YYYY"));
		traningNoteVO.setTerm((String)session.getAttribute("SESSION_TERM"));
		traningNoteVO.setSubjectCode((String)session.getAttribute("SESSION_SUBJECT_CODE"));
		*/
   		traningNoteVO.setYyyy(StringUtils.defaultIfBlank( traningNoteVO.getYyyy() ,(String)session.getAttribute(Globals.YYYY)));
		traningNoteVO.setTerm(StringUtils.defaultIfBlank( traningNoteVO.getTerm() ,(String)session.getAttribute(Globals.TERM)));  
		traningNoteVO.setSubjectCode(StringUtils.defaultIfBlank( traningNoteVO.getSubjectCode() ,(String)session.getAttribute(Globals.SUBJECT_CODE)));
		
		
		
		if(traningNoteVO.getClassId()==null||traningNoteVO.getClassId().equals("")){
			traningNoteVO.setClassId((String)session.getAttribute("SESSION_CLASS"));	
		}	
		traningNoteVO.setTraningType((String)session.getAttribute(Globals.SUBJECT_TRANING_TYPE));
		//상세 등록
		insertCnt  = traningNoteSerivce.goInsertTraningNoteDetail(traningNoteVO);

		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		if(traningNoteVO.getSessionMemType()!=null &&traningNoteVO.getSessionMemType().equals("COT")){
			return "redirect:/lu/traning/listTraningNoteOt.do?weekCnt="+traningNoteVO.getWeekCnt();
		}
		
		return "redirect:/lu/traning/listTraningNote.do?weekCnt="+traningNoteVO.getWeekCnt()+"&classId="+traningNoteVO.getClassId();
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
	@RequestMapping(value = "/lu/traning/deleteTraningNoteEnrichment.do")
	public String deleteTraningNoteEnrichment(@ModelAttribute("frmTraning")TraningNoteVO  traningNoteVO, ModelMap model, HttpSession session, RedirectAttributes redirectAttributes ) throws Exception {

		LOGGER.debug("#### URL = /lu/traning/deleteTraningNoteEnrichment.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.
		
		String retMsg = "입력값을 확인해주세요";
		int insertCnt = 0;
		/*
		traningNoteVO.setYyyy((String)session.getAttribute("SESSION_YYYY"));
		traningNoteVO.setTerm((String)session.getAttribute("SESSION_TERM"));
		traningNoteVO.setSubjectCode((String)session.getAttribute("SESSION_SUBJECT_CODE"));
		*/
   		traningNoteVO.setYyyy(StringUtils.defaultIfBlank( traningNoteVO.getYyyy() ,(String)session.getAttribute(Globals.YYYY)));
		traningNoteVO.setTerm(StringUtils.defaultIfBlank( traningNoteVO.getTerm() ,(String)session.getAttribute(Globals.TERM)));  
		traningNoteVO.setSubjectCode(StringUtils.defaultIfBlank( traningNoteVO.getSubjectCode() ,(String)session.getAttribute(Globals.SUBJECT_CODE)));
		
		if(traningNoteVO.getClassId()==null||traningNoteVO.getClassId().equals("")){
			traningNoteVO.setClassId((String)session.getAttribute("SESSION_CLASS"));
		}	
		insertCnt  = traningNoteSerivce.deleteTraningNoteEnrichment(traningNoteVO);
		if(insertCnt > 0){
			retMsg = "정상적으로  삭제되었습니다.!";
		}else{
			retMsg = "삭제 처리된건이 없습니다.!";
		}
		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		if(traningNoteVO.getSessionMemType()!=null &&traningNoteVO.getSessionMemType().equals("COT")){
			return "redirect:/lu/traning/listTraningNoteOt.do";	
		}
		
		return "redirect:/lu/traning/listTraningNote.do?weekCnt="+traningNoteVO.getWeekCnt()+"&classId="+traningNoteVO.getClassId();
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
	@RequestMapping(value = "/lu/traning/goInsertTraningNoteEnrichment.do")
	public String goInsertTraningNoteEnrichment(@ModelAttribute("frmTraning")TraningNoteVO  traningNoteVO, ModelMap model, HttpSession session, RedirectAttributes redirectAttributes ) throws Exception {

		LOGGER.debug("#### URL = /lu/traning/goInsertTraningNoteEnrichment.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.
		
		String retMsg = "입력값을 확인해주세요";
		int insertCnt = 0;
		/*
		traningNoteVO.setYyyy((String)session.getAttribute("SESSION_YYYY"));
		traningNoteVO.setTerm((String)session.getAttribute("SESSION_TERM"));
		traningNoteVO.setSubjectCode((String)session.getAttribute("SESSION_SUBJECT_CODE"));
		 */
   		traningNoteVO.setYyyy(StringUtils.defaultIfBlank( traningNoteVO.getYyyy() ,(String)session.getAttribute(Globals.YYYY)));
		traningNoteVO.setTerm(StringUtils.defaultIfBlank( traningNoteVO.getTerm() ,(String)session.getAttribute(Globals.TERM)));  
		traningNoteVO.setSubjectCode(StringUtils.defaultIfBlank( traningNoteVO.getSubjectCode() ,(String)session.getAttribute(Globals.SUBJECT_CODE)));
		
		if(traningNoteVO.getClassId()==null||traningNoteVO.getClassId().equals("")){
			traningNoteVO.setClassId((String)session.getAttribute("SESSION_CLASS"));
		}			
		traningNoteVO.setTraningType((String)session.getAttribute(Globals.SUBJECT_TRANING_TYPE));
		traningNoteVO.setAddyn("Y");
		
		//보강 수업 일시 / 시작 시간 종료 시간 등록 및 수정 처리
		insertCnt = traningNoteSerivce.goInsertEnrichmentTraningTime(traningNoteVO);
 
		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		if(traningNoteVO.getSessionMemType()!=null &&traningNoteVO.getSessionMemType().equals("COT")){
			return "redirect:/lu/traning/listTraningNoteOt.do?weekCnt="+traningNoteVO.getWeekCnt();
		}
		
		return "redirect:/lu/traning/listTraningNote.do?weekCnt="+traningNoteVO.getWeekCnt()+"&classId="+traningNoteVO.getClassId();
	}

	/**
	 * 훈련지원 > 훈련일지 학습근로자
	 * @param cmsCourseBaseVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "/lu/traning/listTraningNoteStd.do")
	public String listTraningNoteStd(@ModelAttribute("frmTraning") TraningNoteVO  traningNoteVO, ModelMap model, HttpSession session ) throws Exception {

		LOGGER.debug("#### URL = /lu/traning/listTraningNoteStd.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.
		
		traningNoteVO.setMemSeq(traningNoteVO.getSessionMemSeq());
		/*
		traningNoteVO.setYyyy((String)session.getAttribute("SESSION_YYYY"));
		traningNoteVO.setTerm((String)session.getAttribute("SESSION_TERM"));
		traningNoteVO.setSubjectCode((String)session.getAttribute("SESSION_SUBJECT_CODE"));
		*/
   		traningNoteVO.setYyyy(StringUtils.defaultIfBlank( traningNoteVO.getYyyy() ,(String)session.getAttribute(Globals.YYYY)));
		traningNoteVO.setTerm(StringUtils.defaultIfBlank( traningNoteVO.getTerm() ,(String)session.getAttribute(Globals.TERM)));  
		traningNoteVO.setSubjectCode(StringUtils.defaultIfBlank( traningNoteVO.getSubjectCode() ,(String)session.getAttribute(Globals.SUBJECT_CODE)));
		
		traningNoteVO.setClassId((String)session.getAttribute("SESSION_CLASS"));
	
		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy(traningNoteVO.getYyyy());
		currProcVO.setTerm(traningNoteVO.getTerm());
		currProcVO.setSubClass(traningNoteVO.getClassId());
		currProcVO.setSubjectCode(traningNoteVO.getSubjectCode());		
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
		//공통상단 데이터
		TraningNoteVO result = traningNoteSerivce.getTraningNote(traningNoteVO);
		model.put("TraningNoteVO", result);
		
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
		model.put("TraningEnrichmentTimeVO",resultWnrich);
		model.put("seachVO", traningNoteVO);
		
		// 보강수업생 조회
		List<TraningNoteVO> resultEnrichmentList = traningNoteSerivce.listTraningEnrichmentClassMember(traningNoteVO);
		model.put("TraningNoteList2", resultEnrichmentList);
 
		return "oklms/lu/traning/listTraningNoteStd";
	 
	}
	
	/**
	 * 훈련지원 > 훈련일지 기업현장교사
	 * @param cmsCourseBaseVO
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "/lu/traning/listTraningNoteOt.do")
	public String listTraningNoteOt(@ModelAttribute("frmTraning") TraningNoteVO  traningNoteVO, ModelMap model, HttpSession session ) throws Exception {

		LOGGER.debug("#### URL = /lu/traning/listTraningNoteOt.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.
		/*
		traningNoteVO.setYyyy((String)session.getAttribute("SESSION_YYYY"));
		traningNoteVO.setTerm((String)session.getAttribute("SESSION_TERM"));
		traningNoteVO.setSubjectCode((String)session.getAttribute("SESSION_SUBJECT_CODE"));
		*/
   		traningNoteVO.setYyyy(StringUtils.defaultIfBlank( traningNoteVO.getYyyy() ,(String)session.getAttribute(Globals.YYYY)));
		traningNoteVO.setTerm(StringUtils.defaultIfBlank( traningNoteVO.getTerm() ,(String)session.getAttribute(Globals.TERM)));  
		traningNoteVO.setSubjectCode(StringUtils.defaultIfBlank( traningNoteVO.getSubjectCode() ,(String)session.getAttribute(Globals.SUBJECT_CODE)));
		
		String subjectTraningType = (String)session.getAttribute(Globals.SUBJECT_TRANING_TYPE);
		traningNoteVO.setClassId((String)session.getAttribute("SESSION_CLASS"));

		// 과정정보
  		CurrProcVO currProcVO=new CurrProcVO();
		currProcVO.setYyyy(traningNoteVO.getYyyy());
		currProcVO.setTerm(traningNoteVO.getTerm());
		currProcVO.setSubClass(traningNoteVO.getClassId());
		currProcVO.setSubjectCode(traningNoteVO.getSubjectCode());		
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
		//공통상단 데이터
		TraningNoteVO result = traningNoteSerivce.getTraningNote(traningNoteVO);
		model.put("TraningNoteVO", result);
		
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
//		TraningNoteVO resultWnrich = traningNoteSerivce.getTraningEnrichmentTime(traningNoteVO);
//		model.put("TraningEnrichmentTimeVO",resultWnrich);
		
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
  
		model.put("subjectTraningType", subjectTraningType);
		model.put("memType", traningNoteVO.getSessionMemType());

		return "oklms/lu/traning/listTraningNoteOt";
 
	}	
	
	
  	/** 학습근로자조회 */
  	@RequestMapping(value = "/lu/traning/ajaxTraningNote.do")
	public String ajaxTraningNote(@ModelAttribute("frmTraning") TraningNoteVO  traningNoteVO, ModelMap model , HttpSession session) throws Exception {
  		LOGGER.debug("#### URL = /lu/traning/ajaxTraningNote.do" );
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(traningNoteVO); // session의 정보를 VO에 추가.
		/*
		traningNoteVO.setYyyy((String)session.getAttribute("SESSION_YYYY"));
		traningNoteVO.setTerm((String)session.getAttribute("SESSION_TERM"));
		*/
   		traningNoteVO.setYyyy(StringUtils.defaultIfBlank( traningNoteVO.getYyyy() ,(String)session.getAttribute(Globals.YYYY)));
		traningNoteVO.setTerm(StringUtils.defaultIfBlank( traningNoteVO.getTerm() ,(String)session.getAttribute(Globals.TERM)));  
		
		
   		List<TraningNoteVO> result=traningNoteSerivce.listTraningClassMember(traningNoteVO);
		

		Integer pageSize = traningNoteVO.getPageSize();
		Integer page = traningNoteVO.getPageIndex();
		Integer pageTemp = traningNoteVO.getPageIndex();
		
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
        paginationInfo.setCurrentPageNo(traningNoteVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(traningNoteVO.getPageUnit());
        paginationInfo.setPageSize(traningNoteVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);
        
        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("result", result );
        model.addAttribute("traningNoteVO",traningNoteVO);
		// View호출
		return "oklms_import/lu/traning/ajaxTraningNoteMember";
	}
}
