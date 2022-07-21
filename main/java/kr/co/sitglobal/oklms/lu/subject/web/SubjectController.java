package kr.co.sitglobal.oklms.lu.subject.web;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.co.sitglobal.oklms.comm.util.CommonUtil;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.atchFile.vo.AtchFileVO;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.commbiz.cmmcode.vo.CommonCodeVO;
import kr.co.sitglobal.oklms.commbiz.util.AtchFileUtil;
import kr.co.sitglobal.oklms.commbiz.util.BizUtil;
import kr.co.sitglobal.oklms.lu.activity.service.ActivityService;
import kr.co.sitglobal.oklms.lu.activity.vo.ActivityVO; 
import kr.co.sitglobal.oklms.lu.activity.vo.MemberVO;
import kr.co.sitglobal.oklms.lu.activity.vo.SubjweekStdVO;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.grade.vo.GradeVO;
import kr.co.sitglobal.oklms.lu.interview.service.InterviewService;
import kr.co.sitglobal.oklms.lu.interview.vo.InterviewCompanyVO; 
import kr.co.sitglobal.oklms.lu.online.service.OnlineTraningService;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningSchVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningVO;
import kr.co.sitglobal.oklms.lu.report.service.ReportService; 
import kr.co.sitglobal.oklms.lu.subject.service.SubjectService;
import kr.co.sitglobal.oklms.lu.subject.vo.SubjectCompanyVO;
import kr.co.sitglobal.oklms.lu.subject.vo.SubjectVO;
import kr.co.sitglobal.oklms.lu.traning.service.TraningNoteSerivce;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningNoteVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.Globals;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class SubjectController  extends BaseController{

	@Resource(name = "SubjectService")
	private SubjectService subjectService;
	
	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;
	
	@Resource(name = "OnlineTraningService")
	private OnlineTraningService onlineTraningService;
	
	
	// 담당개설교과 - 교수
	@RequestMapping(value="/lu/subject/listSubjectPrt.do")
	public String listSubjectPrt(@ModelAttribute("frmSubject") SubjectVO subjectVO, ModelMap model, HttpServletRequest request) throws Exception {
		
		logger.debug("#### URL = /lu/subject/listSubjectPrt.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(subjectVO); // session의 정보를 VO에 추가.
		
		String returnView = "";
		
		// 학과코드,학과명 코드 리스트
		CommonCodeVO codeVO = new  CommonCodeVO();
		codeVO.setCodeGroup("DEPT_CD");
		List<CommonCodeVO> deptCodeList = commonCodeService.selectCmmCodeList(codeVO); // 학과
		model.addAttribute("deptCodeList", deptCodeList);
		
		logger.debug("########### subjectVO.getPageSize() : " +subjectVO.getPageSize());
		logger.debug("########### subjectVO.getPageSize() : " +subjectVO.getPageSize());
		
		// 훈련방식 - 검색조건 없을시 OJT 세팅
		if( subjectVO.getSubjectTraningType() == null || subjectVO.getSubjectTraningType().equals("") ){
			subjectVO.setSubjectTraningType("OJT");
		}
		
		// 훈련방식 - 검색조건 없을시 학기로 세팅
		if( subjectVO.getSearchPeriodType() == null || subjectVO.getSearchPeriodType().equals("") ){
			subjectVO.setSearchPeriodType("TERM");
		}
		
		logger.debug("============ subjectVO.getPageSize() : " +subjectVO.getPageSize());
		logger.debug("============ subjectVO.getPageSize() : " +subjectVO.getPageSize());
		
		List <SubjectVO> resultList = subjectService.listSubjectPrt(subjectVO);
		model.addAttribute("resultList", resultList);
		
		Integer pageSize = subjectVO.getPageSize();
		Integer page = subjectVO.getPageIndex();
		
		int totalCnt = 0;
		if( 0 < resultList.size() ){
			totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
		}

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(subjectVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(subjectVO.getPageSize());
        paginationInfo.setPageSize(subjectVO.getPageUnit());
        paginationInfo.setTotalRecordCount(totalCnt);

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);
		
		
		if(subjectVO.getSubjectTraningType().equals("OJT")){		// 훈련방식 OJT
			if(subjectVO.getSearchPeriodType().equals("TERM")){	// 학기별
				returnView="oklms/lu/subject/listSubjectOjtTermPrt";
			} else {																			// 주차별
				returnView="oklms/lu/subject/listSubjectOjtWeekPrt";
			}
		} else { 																				// 훈련방식 Off-JT
			if(subjectVO.getSearchPeriodType().equals("TERM")){	// 학기별
				returnView="oklms/lu/subject/listSubjectOffTermPrt";
			} else {																			// 주차별
				returnView="oklms/lu/subject/listSubjectOffWeekPrt";
			}
		}
		
		model.addAttribute("subjectVO", subjectVO);
		
		// View호출
		return returnView;
	}
	
	// 담당개설교과 - 학과전담자
	@RequestMapping(value="/lu/subject/listSubjectCdp.do")
	public String listSubjectCdp(@ModelAttribute("frmSubject") SubjectVO subjectVO, ModelMap model, HttpServletRequest request) throws Exception {
		
		logger.debug("#### URL = /lu/subject/listSubjectCdp.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(subjectVO); // session의 정보를 VO에 추가.
		
		
		// 학과코드,학과명 코드 리스트
		CommonCodeVO codeVO = new  CommonCodeVO();
		codeVO.setCodeGroup("DEPT_CD");
		List<CommonCodeVO> deptCodeList = commonCodeService.selectCmmCodeList(codeVO); // 학과
		model.addAttribute("deptCodeList", deptCodeList);
		
		List <SubjectVO> resultList = subjectService.listSubjectCdp(subjectVO);
		model.addAttribute("resultList", resultList);
		
		Integer pageSize = subjectVO.getPageSize();
		Integer page = subjectVO.getPageIndex();
		
		int totalCnt = 0;
		if( 0 < resultList.size() ){
			totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
		}

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(subjectVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(subjectVO.getPageSize());
        paginationInfo.setPageSize(subjectVO.getPageUnit());
        paginationInfo.setTotalRecordCount(totalCnt);

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);
		
		
		model.addAttribute("subjectVO", subjectVO);
		
		// View호출
		return "oklms/lu/subject/listSubjectOffTermCdp";
	}
	
	// 훈련개설교과 - 학습근로자
	@RequestMapping(value="/lu/subject/listSubjectStd.do")
	public String listSubjectStd(@ModelAttribute("frmSubject") SubjectVO subjectVO, ModelMap model, HttpServletRequest request) throws Exception {
		
		logger.debug("#### URL = /lu/subject/listSubjectStd.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(subjectVO); // session의 정보를 VO에 추가.
		
		String returnView = "";
		
		// 학과코드,학과명 코드 리스트
		CommonCodeVO codeVO = new  CommonCodeVO();
		codeVO.setCodeGroup("DEPT_CD");
		List<CommonCodeVO> deptCodeList = commonCodeService.selectCmmCodeList(codeVO); // 학과
		model.addAttribute("deptCodeList", deptCodeList);
		
		
		// 훈련방식 - 검색조건 없을시 OJT 세팅
		if( subjectVO.getSubjectTraningType() == null || subjectVO.getSubjectTraningType().equals("") ){
			subjectVO.setSubjectTraningType("OJT");
		}
		
		// 훈련방식 - 검색조건 없을시 학기로 세팅
		if( subjectVO.getSearchPeriodType() == null || subjectVO.getSearchPeriodType().equals("") ){
			subjectVO.setSearchPeriodType("TERM");
		}
		
		
		List <SubjectVO> resultList = subjectService.listSubjectStd(subjectVO);
		model.addAttribute("resultList", resultList);
		
		Integer pageSize = subjectVO.getPageSize();
		Integer page = subjectVO.getPageIndex();
		
		int totalCnt = 0;
		if( 0 < resultList.size() ){
			totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
		}

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(subjectVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(subjectVO.getPageSize());
        paginationInfo.setPageSize(subjectVO.getPageUnit());
        paginationInfo.setTotalRecordCount(totalCnt);

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);
		
		
		if(subjectVO.getSubjectTraningType().equals("OJT")){		// 훈련방식 OJT
			if(subjectVO.getSearchPeriodType().equals("TERM")){	// 학기별
				returnView="oklms/lu/subject/listSubjectOjtTermStd";
			} else {																			// 주차별
				returnView="oklms/lu/subject/listSubjectOjtWeekStd";
			}
		} else { 																				// 훈련방식 Off-JT
			if(subjectVO.getSearchPeriodType().equals("TERM")){	// 학기별
				returnView="oklms/lu/subject/listSubjectOffTermStd";
			} else {																			// 주차별
				returnView="oklms/lu/subject/listSubjectOffWeekStd";
			}
		}
		
		model.addAttribute("subjectVO", subjectVO);
		
		// View호출
		return returnView;
	}
	
	// 담당개설교과 - 기업현장교사
	@RequestMapping(value="/lu/subject/listSubjectCot.do")
	public String listSubjectCot(@ModelAttribute("frmSubject") SubjectVO subjectVO, ModelMap model, HttpServletRequest request) throws Exception {
		
		logger.debug("#### URL = /lu/subject/listSubjectCot.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(subjectVO); // session의 정보를 VO에 추가.
		
		String returnView = "";
		
		// 학과코드,학과명 코드 리스트
		CommonCodeVO codeVO = new  CommonCodeVO();
		codeVO.setCodeGroup("DEPT_CD");
		List<CommonCodeVO> deptCodeList = commonCodeService.selectCmmCodeList(codeVO); // 학과
		model.addAttribute("deptCodeList", deptCodeList);
		
		
		// 훈련방식 - 검색조건 없을시 학기로 세팅
		if( subjectVO.getSearchPeriodType() == null || subjectVO.getSearchPeriodType().equals("") ){
			subjectVO.setSearchPeriodType("TERM");
		}
		
		List <SubjectVO> resultList = subjectService.listSubjectCot(subjectVO);
		model.addAttribute("resultList", resultList);
		
		Integer pageSize = subjectVO.getPageSize();
		Integer page = subjectVO.getPageIndex();
		
		int totalCnt = 0;
		if( 0 < resultList.size() ){
			totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
		}

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(subjectVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(subjectVO.getPageSize());
        paginationInfo.setPageSize(subjectVO.getPageUnit());
        paginationInfo.setTotalRecordCount(totalCnt);

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);
		
		
		if(subjectVO.getSearchPeriodType().equals("TERM")){	// 학기별
			returnView="oklms/lu/subject/listSubjectOjtTermCot";
		} else {																			// 주차별
			returnView="oklms/lu/subject/listSubjectOjtWeekCot";
		}
		
		model.addAttribute("subjectVO", subjectVO);
		
		
		// View호출
		return returnView;
	}
	
	// 담당개설교과 - 센터 당당자
	@RequestMapping(value="/lu/subject/listCompanyCcn.do")
	public String listCompanyCcn(@ModelAttribute("frmCompany") SubjectCompanyVO subjectCompnyVO, ModelMap model, HttpServletRequest request) throws Exception {
		
		logger.debug("#### URL = /lu/subject/listCompanyCcn.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(subjectCompnyVO); // session의 정보를 VO에 추가.
		
		if(subjectCompnyVO.getYyyy()==null || subjectCompnyVO.getYyyy().equals("")){
			// 현재 년도학기조회
			CommonCodeVO commonCodeVO =	commonCodeService.selectYearTerm();
			subjectCompnyVO.setYyyy(commonCodeVO.getYyyy());			
		}
		
		String returnView = "";
		
		// 훈련방식 - 검색조건 없을시 학기로 세팅
		if( subjectCompnyVO.getSearchStatusType() == null || subjectCompnyVO.getSearchStatusType().equals("") ){
			subjectCompnyVO.setSearchStatusType("STU");
		}
		
		
		if(subjectCompnyVO.getSearchStatusType().equals("STU")){	// 학습
			List<SubjectCompanyVO> resultList = new ArrayList<SubjectCompanyVO>();
			ArrayList<Integer> memInfoList = new ArrayList<Integer>();
			
			if( subjectCompnyVO.getInfoNumArr() != null && subjectCompnyVO.getInfoNumArr().length > 0 ){
				logger.debug("================   subjectCompnyVO.getInfoNumArr().length : "+subjectCompnyVO.getInfoNumArr().length);
				// IN 조건에 사용하기 위해 문자열 변환 후 쿼테이션 세팅
				subjectCompnyVO.setInfoNums(CommonUtil.toArrStr(subjectCompnyVO.getInfoNumArr()));
			}
			
			List <SubjectCompanyVO> list = subjectService.listCompanyCcn(subjectCompnyVO);
			
			for( int i = 0; i < list.size(); i++ ){
				SubjectCompanyVO compVO = list.get(i);
				compVO.setYyyy(subjectCompnyVO.getYyyy());
				SubjectCompanyVO memVO = subjectService.getActivityNoteMemInfos(compVO);
				
				logger.debug("================   memVO.getMemInfos() : "+memVO.getMemInfos());
				logger.debug("================   memVO.getMemInfosLength() : "+memVO.getMemInfosLength());
				
				compVO.setMemInfos(memVO.getMemInfos());
				compVO.setMemInfosLength(memVO.getMemInfosLength());
				
				int length = compVO.getMemInfosLength();
				memInfoList.add(length);
				
				resultList.add(compVO);
			}
			
			if(memInfoList.size() > 0){
				int maxCnt = Collections.max(memInfoList);
				model.addAttribute("maxCnt", maxCnt);
				logger.debug("================   maxCnt : "+maxCnt);
			}
			
			
			model.addAttribute("resultList", resultList);
			
			returnView="oklms/lu/subject/listCompanyStudyCcn";
		} else {																			// 기업
			
			List <SubjectCompanyVO> resultList = subjectService.listCompanyCcn(subjectCompnyVO);
			model.addAttribute("resultList", resultList);
			returnView="oklms/lu/subject/listCompanyCcn";
		}
		
		model.addAttribute("compVO", subjectCompnyVO);
		
		// View호출
		return returnView;
	}
	
	
	// 담당개설교과 - 센터 당당자 엑셀
	@RequestMapping(value="/lu/subject/excelCompanyCcn.do")
	public String excelCompanyCcn(@ModelAttribute("frmCompany") SubjectCompanyVO subjectCompnyVO, ModelMap model, HttpServletRequest request) throws Exception {
		
		logger.debug("#### URL = /lu/subject/excelCompanyCcn.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(subjectCompnyVO); // session의 정보를 VO에 추가.
		
		if(subjectCompnyVO.getYyyy()==null || subjectCompnyVO.getYyyy().equals("")){
			// 현재 년도학기조회
			CommonCodeVO commonCodeVO =	commonCodeService.selectYearTerm();
			subjectCompnyVO.setYyyy(commonCodeVO.getYyyy());			
		}
		
		String returnView = "";
		
		// 훈련방식 - 검색조건 없을시 학기로 세팅
		if( subjectCompnyVO.getSearchStatusType() == null || subjectCompnyVO.getSearchStatusType().equals("") ){
			subjectCompnyVO.setSearchStatusType("STU");
		}
		
		
		if(subjectCompnyVO.getSearchStatusType().equals("STU")){	// 학습
			List<SubjectCompanyVO> resultList = new ArrayList<SubjectCompanyVO>();
			ArrayList<Integer> memInfoList = new ArrayList<Integer>();
			
			if( subjectCompnyVO.getInfoNumArr() != null && subjectCompnyVO.getInfoNumArr().length > 0 ){
				logger.debug("================   subjectCompnyVO.getInfoNumArr().length : "+subjectCompnyVO.getInfoNumArr().length);
				// IN 조건에 사용하기 위해 문자열 변환 후 쿼테이션 세팅
				subjectCompnyVO.setInfoNums(CommonUtil.toArrStr(subjectCompnyVO.getInfoNumArr()));
			}
			
			List <SubjectCompanyVO> list = subjectService.listCompanyCcn(subjectCompnyVO);
			
			for( int i = 0; i < list.size(); i++ ){
				SubjectCompanyVO compVO = list.get(i);
				compVO.setYyyy(subjectCompnyVO.getYyyy());
				SubjectCompanyVO memVO = subjectService.getActivityNoteMemInfos(compVO);
				
				logger.debug("================   memVO.getMemInfos() : "+memVO.getMemInfos());
				logger.debug("================   memVO.getMemInfosLength() : "+memVO.getMemInfosLength());
				
				compVO.setMemInfos(memVO.getMemInfos());
				compVO.setMemInfosLength(memVO.getMemInfosLength());
				
				int length = compVO.getMemInfosLength();
				memInfoList.add(length);
				
				resultList.add(compVO);
			}
			
			if(memInfoList.size() > 0){
				int maxCnt = Collections.max(memInfoList);
				model.addAttribute("maxCnt", maxCnt);
				logger.debug("================   maxCnt : "+maxCnt);
			}
			
			model.addAttribute("resultList", resultList);
			request.setAttribute("ExcelName", URLEncoder.encode( "담당기업체현황-학습관리현황".replaceAll(" ", "_") ,"UTF-8") );
			returnView="oklms_excel/lu/subject/excelCompanyStudyCcn";
		} else {																			// 기업
			
			if( subjectCompnyVO.getInfoNumArr() != null && subjectCompnyVO.getInfoNumArr().length > 0 ){
				logger.debug("================   subjectCompnyVO.getInfoNumArr().length : "+subjectCompnyVO.getInfoNumArr().length);
				// IN 조건에 사용하기 위해 문자열 변환 후 쿼테이션 세팅
				subjectCompnyVO.setInfoNums(CommonUtil.toArrStr(subjectCompnyVO.getInfoNumArr()));
			}
			
			List <SubjectCompanyVO> resultList = subjectService.listCompanyCcn(subjectCompnyVO);
			model.addAttribute("resultList", resultList);
			request.setAttribute("ExcelName", URLEncoder.encode( "담당기업체현황-참여기업현황".replaceAll(" ", "_") ,"UTF-8") );
			
			returnView="oklms_excel/lu/subject/excelCompanyCcn";
		}
		
		
		
		model.addAttribute("compVO", subjectCompnyVO);
		
		// View호출
		return returnView;
	}
	
	// 담당개설교과 - 센터 당당자 엑셀
	@RequestMapping(value="/lu/subject/printCompanyCcn.do")
	public String printCompanyCcn(@ModelAttribute("frmCompany") SubjectCompanyVO subjectCompnyVO, ModelMap model, HttpServletRequest request) throws Exception {
		
		logger.debug("#### URL = /lu/subject/printCompanyCcn.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(subjectCompnyVO); // session의 정보를 VO에 추가.
		
		if(subjectCompnyVO.getYyyy()==null || subjectCompnyVO.getYyyy().equals("")){
			// 현재 년도학기조회
			CommonCodeVO commonCodeVO =	commonCodeService.selectYearTerm();
			subjectCompnyVO.setYyyy(commonCodeVO.getYyyy());			
		}
		
		String returnView = "";
		
		// 훈련방식 - 검색조건 없을시 학기로 세팅
		if( subjectCompnyVO.getSearchStatusType() == null || subjectCompnyVO.getSearchStatusType().equals("") ){
			subjectCompnyVO.setSearchStatusType("STU");
		}
		
		
		if(subjectCompnyVO.getSearchStatusType().equals("STU")){	// 학습
			List<SubjectCompanyVO> resultList = new ArrayList<SubjectCompanyVO>();
			ArrayList<Integer> memInfoList = new ArrayList<Integer>();
			
			if( subjectCompnyVO.getInfoNumArr() != null && subjectCompnyVO.getInfoNumArr().length > 0 ){
				logger.debug("================   subjectCompnyVO.getInfoNumArr().length : "+subjectCompnyVO.getInfoNumArr().length);
				// IN 조건에 사용하기 위해 문자열 변환 후 쿼테이션 세팅
				subjectCompnyVO.setInfoNums(CommonUtil.toArrStr(subjectCompnyVO.getInfoNumArr()));
			}
			
			List <SubjectCompanyVO> list = subjectService.listCompanyCcn(subjectCompnyVO);
			
			for( int i = 0; i < list.size(); i++ ){
				SubjectCompanyVO compVO = list.get(i);
				compVO.setYyyy(subjectCompnyVO.getYyyy());
				SubjectCompanyVO memVO = subjectService.getActivityNoteMemInfos(compVO);
				
				logger.debug("================   memVO.getMemInfos() : "+memVO.getMemInfos());
				logger.debug("================   memVO.getMemInfosLength() : "+memVO.getMemInfosLength());
				
				compVO.setMemInfos(memVO.getMemInfos());
				compVO.setMemInfosLength(memVO.getMemInfosLength());
				
				int length = compVO.getMemInfosLength();
				memInfoList.add(length);
				
				resultList.add(compVO);
			}
			
			if(memInfoList.size() > 0){
				int maxCnt = Collections.max(memInfoList);
				model.addAttribute("maxCnt", maxCnt);
				logger.debug("================   maxCnt : "+maxCnt);
			}
			
			model.addAttribute("resultList", resultList);
			request.setAttribute("ExcelName", URLEncoder.encode( "담당기업체현황-학습관리현황".replaceAll(" ", "_") ,"UTF-8") );
			returnView="oklms_popup/lu/subject/printCompanyStudyCcn";
		} else {																			// 기업
			
			if( subjectCompnyVO.getInfoNumArr() != null && subjectCompnyVO.getInfoNumArr().length > 0 ){
				logger.debug("================   subjectCompnyVO.getInfoNumArr().length : "+subjectCompnyVO.getInfoNumArr().length);
				// IN 조건에 사용하기 위해 문자열 변환 후 쿼테이션 세팅
				subjectCompnyVO.setInfoNums(CommonUtil.toArrStr(subjectCompnyVO.getInfoNumArr()));
			}
			
			List <SubjectCompanyVO> resultList = subjectService.listCompanyCcn(subjectCompnyVO);
			model.addAttribute("resultList", resultList);
			request.setAttribute("ExcelName", URLEncoder.encode( "담당기업체현황-참여기업현황".replaceAll(" ", "_") ,"UTF-8") );
			
			returnView="oklms_popup/lu/subject/printCompanyCcn";
		}
		
		
		
		model.addAttribute("compVO", subjectCompnyVO);
		
		// View호출
		return returnView;
	}
	
	
	// 담당개설교과 - hrd 당당자
	@RequestMapping(value="/lu/subject/listCompanyCcm.do")
	public String listCompanyCcm(@ModelAttribute("frmCompany") SubjectCompanyVO subjectCompnyVO, ModelMap model, HttpServletRequest request) throws Exception {
		
		logger.debug("#### URL = /lu/subject/listCompanyCcm.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(subjectCompnyVO); // session의 정보를 VO에 추가.
		
		if(subjectCompnyVO.getYyyy()==null || subjectCompnyVO.getYyyy().equals("")){
			// 현재 년도학기조회
			CommonCodeVO commonCodeVO =	commonCodeService.selectYearTerm();
			subjectCompnyVO.setYyyy(commonCodeVO.getYyyy());			
		}
		
		String returnView = "";
		
		// 훈련방식 - 검색조건 없을시 학기로 세팅
		if( subjectCompnyVO.getSearchStatusType() == null || subjectCompnyVO.getSearchStatusType().equals("") ){
			subjectCompnyVO.setSearchStatusType("STU");
		}
		
		if(subjectCompnyVO.getSearchStatusType().equals("STU")){	// 학습
			List<SubjectCompanyVO> resultList = new ArrayList<SubjectCompanyVO>();
			ArrayList<Integer> memInfoList = new ArrayList<Integer>();
			
			if( subjectCompnyVO.getInfoNumArr() != null && subjectCompnyVO.getInfoNumArr().length > 0 ){
				logger.debug("================   subjectCompnyVO.getInfoNumArr().length : "+subjectCompnyVO.getInfoNumArr().length);
				// IN 조건에 사용하기 위해 문자열 변환 후 쿼테이션 세팅
				subjectCompnyVO.setInfoNums(CommonUtil.toArrStr(subjectCompnyVO.getInfoNumArr()));
			}
			
			List <SubjectCompanyVO> list = subjectService.listCompanyCcm(subjectCompnyVO);
			
			for( int i = 0; i < list.size(); i++ ){
				SubjectCompanyVO compVO = list.get(i);
				compVO.setYyyy(subjectCompnyVO.getYyyy());
				SubjectCompanyVO memVO = subjectService.getActivityNoteMemInfos(compVO);
				
				logger.debug("================   memVO.getMemInfos() : "+memVO.getMemInfos());
				logger.debug("================   memVO.getMemInfosLength() : "+memVO.getMemInfosLength());
				
				compVO.setMemInfos(memVO.getMemInfos());
				compVO.setMemInfosLength(memVO.getMemInfosLength());
				
				int length = compVO.getMemInfosLength();
				memInfoList.add(length);
				
				resultList.add(compVO);
			}
			
			if(memInfoList.size() > 0){
				int maxCnt = Collections.max(memInfoList);
				model.addAttribute("maxCnt", maxCnt);
				logger.debug("================   maxCnt : "+maxCnt);
			}
			
			
			model.addAttribute("resultList", resultList);
			
			returnView="oklms/lu/subject/listCompanyStudyCcm";
		} else {																			// 기업
			
			List <SubjectCompanyVO> resultList = subjectService.listCompanyCcm(subjectCompnyVO);
			model.addAttribute("resultList", resultList);
			returnView="oklms/lu/subject/listCompanyCcm";
		}
		
		model.addAttribute("compVO", subjectCompnyVO);
		
		// View호출
		return returnView;
	}
	
	
	// 담당개설교과 - 센터 당당자 엑셀
	@RequestMapping(value="/lu/subject/excelCompanyCcm.do")
	public String excelCompanyCcm(@ModelAttribute("frmCompany") SubjectCompanyVO subjectCompnyVO, ModelMap model, HttpServletRequest request) throws Exception {
		
		logger.debug("#### URL = /lu/subject/excelCompanyCcm.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(subjectCompnyVO); // session의 정보를 VO에 추가.
		
		if(subjectCompnyVO.getYyyy()==null || subjectCompnyVO.getYyyy().equals("")){
			// 현재 년도학기조회
			CommonCodeVO commonCodeVO =	commonCodeService.selectYearTerm();
			subjectCompnyVO.setYyyy(commonCodeVO.getYyyy());			
		}
		
		String returnView = "";
		
		// 훈련방식 - 검색조건 없을시 학기로 세팅
		if( subjectCompnyVO.getSearchStatusType() == null || subjectCompnyVO.getSearchStatusType().equals("") ){
			subjectCompnyVO.setSearchStatusType("STU");
		}
		
		
		if(subjectCompnyVO.getSearchStatusType().equals("STU")){	// 학습
			List<SubjectCompanyVO> resultList = new ArrayList<SubjectCompanyVO>();
			ArrayList<Integer> memInfoList = new ArrayList<Integer>();
			
			if( subjectCompnyVO.getInfoNumArr() != null && subjectCompnyVO.getInfoNumArr().length > 0 ){
				logger.debug("================   subjectCompnyVO.getInfoNumArr().length : "+subjectCompnyVO.getInfoNumArr().length);
				// IN 조건에 사용하기 위해 문자열 변환 후 쿼테이션 세팅
				subjectCompnyVO.setInfoNums(CommonUtil.toArrStr(subjectCompnyVO.getInfoNumArr()));
			}
			
			List <SubjectCompanyVO> list = subjectService.listCompanyCcm(subjectCompnyVO);
			
			for( int i = 0; i < list.size(); i++ ){
				SubjectCompanyVO compVO = list.get(i);
				compVO.setYyyy(subjectCompnyVO.getYyyy());
				SubjectCompanyVO memVO = subjectService.getActivityNoteMemInfos(compVO);
				
				logger.debug("================   memVO.getMemInfos() : "+memVO.getMemInfos());
				logger.debug("================   memVO.getMemInfosLength() : "+memVO.getMemInfosLength());
				
				compVO.setMemInfos(memVO.getMemInfos());
				compVO.setMemInfosLength(memVO.getMemInfosLength());
				
				int length = compVO.getMemInfosLength();
				memInfoList.add(length);
				
				resultList.add(compVO);
			}
			
			if(memInfoList.size() > 0){
				int maxCnt = Collections.max(memInfoList);
				model.addAttribute("maxCnt", maxCnt);
				logger.debug("================   maxCnt : "+maxCnt);
			}
			
			model.addAttribute("resultList", resultList);
			request.setAttribute("ExcelName", URLEncoder.encode( "담당기업체현황-학습관리현황".replaceAll(" ", "_") ,"UTF-8") );
			returnView="oklms_excel/lu/subject/excelCompanyStudyCcm";
		} else {																			// 기업
			
			if( subjectCompnyVO.getInfoNumArr() != null && subjectCompnyVO.getInfoNumArr().length > 0 ){
				logger.debug("================   subjectCompnyVO.getInfoNumArr().length : "+subjectCompnyVO.getInfoNumArr().length);
				// IN 조건에 사용하기 위해 문자열 변환 후 쿼테이션 세팅
				subjectCompnyVO.setInfoNums(CommonUtil.toArrStr(subjectCompnyVO.getInfoNumArr()));
			}
			
			List <SubjectCompanyVO> resultList = subjectService.listCompanyCcm(subjectCompnyVO);
			model.addAttribute("resultList", resultList);
			request.setAttribute("ExcelName", URLEncoder.encode( "담당기업체현황-참여기업현황".replaceAll(" ", "_") ,"UTF-8") );
			
			returnView="oklms_excel/lu/subject/excelCompanyCcm";
		}
		
		
		
		model.addAttribute("compVO", subjectCompnyVO);
		
		// View호출
		return returnView;
	}
	
	// 담당개설교과 - HRD 당당자 엑셀
	@RequestMapping(value="/lu/subject/printCompanyCcm.do")
	public String printCompanyCcm(@ModelAttribute("frmCompany") SubjectCompanyVO subjectCompnyVO, ModelMap model, HttpServletRequest request) throws Exception {
		
		logger.debug("#### URL = /lu/subject/printCompanyCcm.do" );
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(subjectCompnyVO); // session의 정보를 VO에 추가.
		
		if(subjectCompnyVO.getYyyy()==null || subjectCompnyVO.getYyyy().equals("")){
			// 현재 년도학기조회
			CommonCodeVO commonCodeVO =	commonCodeService.selectYearTerm();
			subjectCompnyVO.setYyyy(commonCodeVO.getYyyy());			
		}
		
		String returnView = "";
		
		// 훈련방식 - 검색조건 없을시 학기로 세팅
		if( subjectCompnyVO.getSearchStatusType() == null || subjectCompnyVO.getSearchStatusType().equals("") ){
			subjectCompnyVO.setSearchStatusType("STU");
		}
		
		
		if(subjectCompnyVO.getSearchStatusType().equals("STU")){	// 학습
			List<SubjectCompanyVO> resultList = new ArrayList<SubjectCompanyVO>();
			ArrayList<Integer> memInfoList = new ArrayList<Integer>();
			
			if( subjectCompnyVO.getInfoNumArr() != null && subjectCompnyVO.getInfoNumArr().length > 0 ){
				logger.debug("================   subjectCompnyVO.getInfoNumArr().length : "+subjectCompnyVO.getInfoNumArr().length);
				// IN 조건에 사용하기 위해 문자열 변환 후 쿼테이션 세팅
				subjectCompnyVO.setInfoNums(CommonUtil.toArrStr(subjectCompnyVO.getInfoNumArr()));
			}
			
			List <SubjectCompanyVO> list = subjectService.listCompanyCcm(subjectCompnyVO);
			
			for( int i = 0; i < list.size(); i++ ){
				SubjectCompanyVO compVO = list.get(i);
				compVO.setYyyy(subjectCompnyVO.getYyyy());
				SubjectCompanyVO memVO = subjectService.getActivityNoteMemInfos(compVO);
				
				logger.debug("================   memVO.getMemInfos() : "+memVO.getMemInfos());
				logger.debug("================   memVO.getMemInfosLength() : "+memVO.getMemInfosLength());
				
				compVO.setMemInfos(memVO.getMemInfos());
				compVO.setMemInfosLength(memVO.getMemInfosLength());
				
				int length = compVO.getMemInfosLength();
				memInfoList.add(length);
				
				resultList.add(compVO);
			}
			
			if(memInfoList.size() > 0){
				int maxCnt = Collections.max(memInfoList);
				model.addAttribute("maxCnt", maxCnt);
				logger.debug("================   maxCnt : "+maxCnt);
			}
			
			model.addAttribute("resultList", resultList);
			request.setAttribute("ExcelName", URLEncoder.encode( "담당기업체현황-학습관리현황".replaceAll(" ", "_") ,"UTF-8") );
			returnView="oklms_popup/lu/subject/printCompanyStudyCcm";
		} else {																			// 기업
			
			if( subjectCompnyVO.getInfoNumArr() != null && subjectCompnyVO.getInfoNumArr().length > 0 ){
				logger.debug("================   subjectCompnyVO.getInfoNumArr().length : "+subjectCompnyVO.getInfoNumArr().length);
				// IN 조건에 사용하기 위해 문자열 변환 후 쿼테이션 세팅
				subjectCompnyVO.setInfoNums(CommonUtil.toArrStr(subjectCompnyVO.getInfoNumArr()));
			}
			
			List <SubjectCompanyVO> resultList = subjectService.listCompanyCcm(subjectCompnyVO);
			model.addAttribute("resultList", resultList);
			request.setAttribute("ExcelName", URLEncoder.encode( "담당기업체현황-참여기업현황".replaceAll(" ", "_") ,"UTF-8") );
			
			returnView="oklms_popup/lu/subject/printCompanyCcm";
		}
		
		
		
		model.addAttribute("compVO", subjectCompnyVO);
		
		// View호출
		return returnView;
	}
	
	
	// 관리자 > 강의실관리 > 강의관리 > 기본정보 목록 메소드
	@RequestMapping(value = "/lu/subject/excelSubjectScheduleCdp.do")
	public String excelSubjectScheduleCdp(@ModelAttribute("frmExcel") OnlineTraningSchVO onlineTraningSchVO, ModelMap model, HttpServletRequest request) throws Exception {
		
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		OnlineTraningVO onlineTraningVO = new OnlineTraningVO();
        loginInfo.putSessionToVo(onlineTraningVO); // session의 정보를 VO에 추가.
        
		String returnUrl = "";
        
		List<OnlineTraningSchVO> resultList = onlineTraningService.listOnlineTraningCdpSchedule(onlineTraningSchVO);
		List<OnlineTraningSchVO> scheduleList = onlineTraningService.listOnlineTraningAllWeekSchedule(onlineTraningSchVO);
		List<OnlineTraningSchVO> resultList1 = onlineTraningService.listOfflineTraningCdpSchedule(onlineTraningSchVO);
		
		model.addAttribute("resultList1", resultList1);
		model.addAttribute("resultList", resultList);
		model.addAttribute("scheduleList", scheduleList);
    	if("OFF".equals(onlineTraningSchVO.getPageType())){
    		return "oklms_excel/lu/grade/excelSubjectGrade";
    	} else {
    		return "oklms_excel/lu/grade/excelSubjectGrade";
    	}
        
	}
	
	
  	 
}
