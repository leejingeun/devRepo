
/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
* 이진근    2016. 7. 21.         First Draft.
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.comm.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

 /**
 * 공통/유틸리티성 작업을 위한 Controller
* 이진근
 * @since 2016. 7. 21.
 */
@Controller
public class ComUtilController {

	/**
	 * 권한제한 화면 이동
	 * 
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping( "/accessDenied.do" )
	public String accessDenied(HttpServletRequest request, ModelMap model) throws Exception {


		model.addAttribute("retMsg", "접근권한이 없습니다.");
		
		return "cmm/error/comAccessDenied";
	}
	
	
	@RequestMapping( "/cmm/error/{errorType}.do" )
	public String errorPage(@PathVariable("errorType") String errorType, ModelMap model) throws Exception {
		
		return "cmm/error/" + errorType;
	}
}
