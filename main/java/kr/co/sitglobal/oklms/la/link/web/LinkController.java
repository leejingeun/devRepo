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
package kr.co.sitglobal.oklms.la.link.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.co.sitglobal.aunuri.vo.AunuriLinkSubjectVO;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.la.company.vo.CompanyVO;
import kr.co.sitglobal.oklms.la.link.service.LinkService;
import kr.co.sitglobal.oklms.lu.report.vo.ReportVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//import egovframework.com.cmm.util.EgovDoubleSubmitHelper;

@Controller
public class LinkController extends BaseController{
	
	@Resource(name = "linkService")
	private LinkService linkService;
	
	
	
	@RequestMapping(value = "/la/aunuri/listAunuriLink.do")
	public String listAunuriLink(@ModelAttribute("frmLink") AunuriLinkSubjectVO aunuriLinkSubjectVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		logger.debug("#### URL =/la/aunuri/listAunuriLink.doo" );
		String retMsg = "";
  		
		// View호출
		return "oklms/lu/report/listReport";
	}
	
	
	@RequestMapping(value = "/la/aunuri/insertAunuriLinkTerm.do")
	public String insertAunuriLink(@ModelAttribute("frmLink") AunuriLinkSubjectVO aunuriLinkSubjectVO, ModelMap model, HttpServletRequest request) throws Exception {
		
  		logger.debug("#### URL =/la/aunuri/insertAunuriLinkTerm.do" );
  		
  		// 해당학기의 교과목이 등록되어 있는지 체크 (비학점 OJT 제외)
  		
  		// 해당학기의 교과가 없다면 등록진행
  		int iResult = linkService.insertAunuriLinkTerm(aunuriLinkSubjectVO);
  		
  		
		String retMsg = "";
  		
		// View호출
		return "oklms/lu/report/listReport";
	}

	
}
