/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
* 이진근    2016. 07. 01.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.la.statistics.web;

import java.util.List;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.la.comcode.vo.ComcodeVO;
import kr.co.sitglobal.oklms.la.popup.vo.PopupVO;
import kr.co.sitglobal.oklms.la.statistics.service.LoginLogService;
import kr.co.sitglobal.oklms.la.statistics.vo.LoginLogVO;

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

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

 /**
 * 관리자가 LoginLog 설정
 * @author 
 * @since 2016. 12. 02.
 */
@Controller
public class LoginLogController extends BaseController{
	private static final Logger LOG = LoggerFactory.getLogger(LoginLogController.class);
	@Resource(name = "loginLogService")
	private LoginLogService loginLogService;

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
	
	/**
	 * 사용자 로그 관리
	 * @param		clientIp						(String) 검색 조건/ 로그인 아이피
	 * @param		logMemId 						(String) 사용자 아이디
	 * @param		startDate,finishDate			(String) 로그인 기간
	 * @param		paging				(Map<String, Object>) 페이징 처리 데이터
	 * @return		ResultModel			(ResultModel) status, count, data, page, error object
	 * @throws		Exception 
	 */
	@RequestMapping(value = "/la/statistics/listLoginLog.do")
	public String listLoginLog(@ModelAttribute("frmLoginLog") LoginLogVO loginLogVO, ModelMap model) throws Exception {
		
		LOG.debug("listLoginLog : loginLogVO=" + loginLogVO.toString() );
		
		String startDate = loginLogVO.getStartDate();
		String endDate = loginLogVO.getFinishDate();
		List<LoginLogVO> resultList = null;
		Integer pageSize = loginLogVO.getPageSize();
		Integer page = loginLogVO.getPageIndex();
		int totalCnt = 0;
		if(startDate != null){
			if (StringUtils.isNotEmpty(startDate)) {
				startDate = startDate.replaceAll("-", "");
				startDate = ( startDate + "000000" );
				loginLogVO.setStartDate(startDate);
			}
			
			if (StringUtils.isNotEmpty(endDate)) {
				endDate = endDate.replaceAll("-", "");
				endDate = ( endDate + "235900" );
				loginLogVO.setFinishDate(endDate);
			}
			resultList = loginLogService.listLoginLog(loginLogVO);
			
			if( 0 < resultList.size() ){
				totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
			}
		}
		
		int totalPage = (int) Math.ceil(totalCnt / pageSize);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);
        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(loginLogVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(loginLogVO.getPageUnit());
        paginationInfo.setPageSize(loginLogVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("resultList", resultList);
        
		// View호출
		return "oklms/la/statistics/listLoginLog";
	}
}
