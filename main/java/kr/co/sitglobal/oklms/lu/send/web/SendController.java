package kr.co.sitglobal.oklms.lu.send.web;

import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.commbiz.cmmcode.vo.CommonCodeVO;
import kr.co.sitglobal.oklms.lu.activity.vo.ActivityVO; 
import kr.co.sitglobal.oklms.lu.grade.service.GradeService;
import kr.co.sitglobal.oklms.lu.send.service.SendService;
import kr.co.sitglobal.oklms.lu.send.vo.SendVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class SendController  extends BaseController{

	
	@Resource(name = "sendService")
	private SendService sendService;
	
	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;

	
  	@RequestMapping(value = "/lu/send/listSendCdp.do")
	public String listSendCdp(@ModelAttribute("frmSend") SendVO sendVO, ModelMap model, HttpServletRequest request) throws Exception {
  		
  		logger.debug("#### URL = /lu/send/listSendCdp.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(sendVO); // session의 정보를 VO에 추가.
		
		// 학과코드,학과명 코드 리스트
		CommonCodeVO codeVO = new  CommonCodeVO();
		codeVO.setCodeGroup("DEPT_CD");
		List<CommonCodeVO> deptCodeList = commonCodeService.selectCmmCodeList(codeVO); // 학과
		model.addAttribute("deptCodeList", deptCodeList);
		
		List <SendVO> resultList = sendService.listSendCdp(sendVO);
		model.addAttribute("resultList", resultList);
		
		logger.debug("===========================================  sendVO.getPageSize(): "+sendVO.getPageSize() );
		
		Integer pageSize = sendVO.getPageSize();
		Integer page = sendVO.getPageIndex();
		
		logger.debug("===========================================  pageSize: "+pageSize );
		
		int totalCnt = 0;
		if( 0 < resultList.size() ){
			totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
		}

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(sendVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(sendVO.getPageSize());
        paginationInfo.setPageSize(sendVO.getPageUnit());
        paginationInfo.setTotalRecordCount(totalCnt);

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);
		
        model.addAttribute("sendVO", sendVO);
		
  		// View호출
		return "oklms/lu/send/listSendCdp";
	}
}
