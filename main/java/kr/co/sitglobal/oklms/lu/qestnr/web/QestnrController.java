/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2017. 04. 10.         First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.lu.qestnr.web;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.lu.currproc.service.CurrProcService;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.qestnr.service.QestnrService;
import kr.co.sitglobal.oklms.lu.qestnr.vo.QestnrVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.Globals;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
//import egovframework.com.cmm.util.EgovDoubleSubmitHelper;

@Controller
public class QestnrController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(QestnrController.class);

	@Resource(name = "qestnrService")
	private QestnrService qestnrService;

	@Resource(name = "currProcService")
	private CurrProcService currProcService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

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

	/**
	 * 설문 목록
	 * @param qestnrVO
	 * @return QestnrVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/qestnr/listQestnr.do")
	public String listQestnr(@RequestParam Map<String, Object> commandMap, @ModelAttribute("frmQestnr") QestnrVO qestnrVO, ModelMap model, HttpServletRequest request) throws Exception {
		/*====================================================================
    	* 초기화 영역
    	======================================================================*/
	    String retMsg = "";
		HttpSession session = request.getSession(); // 로그인 사용자에 교과별-교과목 관련 세션 Key get 처리
		CurrProcVO currProcVO = new CurrProcVO();
		CurrProcVO currProcReadVO = new CurrProcVO();

		/*====================================================================
    	* 초기값 셋팅 영역
    	====================================================================*/
		String yyyy = StringUtils.defaultString( (String)session.getAttribute(Globals.YYYY) , "" );
		String term = StringUtils.defaultString( (String)session.getAttribute(Globals.TERM) , "" );
		String subjectCode = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_CODE) , "" );
		String subjectName = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_NAME) , "" );
		String classs = StringUtils.defaultString( (String)session.getAttribute(Globals.CLASS) , "" );
		String rowNumber = StringUtils.defaultString( (String)commandMap.get("rowNumber") , "" );

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(qestnrVO); // session의 정보를 VO에 추가.

		/*====================================================================
    	* 업무화면에서 사용하는 List Vo 셋팅 영역
    	====================================================================*/
		logger.debug("################################# ");
		logger.debug("#### yyyy : " + yyyy );
		logger.debug("#### term : " + term );
		logger.debug("#### subjectCode : " + subjectCode );
		logger.debug("#### subjectName : " + subjectName );
		logger.debug("#### classs : " + classs );
		logger.debug("################################# ");

		if( StringUtils.isBlank( yyyy )){
			retMsg = "Left 메뉴에서 진행중인과정에 교과목 하나를 선택하세요.";
			retMsg = URLEncoder.encode( retMsg ,"UTF-8");
			logger.debug("#### retMsg=" + retMsg );
			return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
		} else {

			qestnrVO.setYyyy(yyyy);
			qestnrVO.setTerm(term);
			qestnrVO.setSubjectCode(subjectCode);
			qestnrVO.setSubClass(classs);

			currProcVO.setYyyy(yyyy);
			currProcVO.setTerm(term);
			currProcVO.setSubjectCode(subjectCode);
			currProcVO.setSubClass(classs);

			currProcReadVO = currProcService.getMySubjectInfo(currProcVO);

			logger.debug("################################# ");
			logger.debug("#### pageSize : " + qestnrVO.getPageSize() );
			logger.debug("#### page : " + qestnrVO.getPageIndex() );
			logger.debug("#### rowNumber : " + rowNumber );
			logger.debug("################################# ");

			if(!"STD".equals(qestnrVO.getSessionMemType())){
				List<QestnrVO> listQestnrInfo = qestnrService.listQestnrInfo(qestnrVO);
				Integer pageSize = qestnrVO.getPageSize();
				Integer page = qestnrVO.getPageIndex();

				int totalCnt = 0;
				if( 0 < listQestnrInfo.size() ){
					totalCnt = Integer.parseInt( listQestnrInfo.get(0).getTotalCount() );
				}
				int totalPage = (int) Math.ceil(totalCnt / pageSize);

		        model.addAttribute("pageSize", pageSize);
		        model.addAttribute("totalCount", totalCnt);
		        model.addAttribute("pageIndex", page);

		        PaginationInfo paginationInfo = new PaginationInfo();

		        paginationInfo.setCurrentPageNo(qestnrVO.getPageIndex());
		        paginationInfo.setRecordCountPerPage(qestnrVO.getPageSize());
		        paginationInfo.setPageSize(qestnrVO.getPageSize());
		        paginationInfo.setTotalRecordCount(totalCnt);

		        model.addAttribute("resultCnt", totalCnt );
		        model.addAttribute("paginationInfo", paginationInfo);
				model.addAttribute("resultList", listQestnrInfo); 	  //설문지정보 목록
			}else{
				List<QestnrVO> listQestnrStdInfo = qestnrService.listQestnrStdInfo(qestnrVO);

				Integer pageSize = qestnrVO.getPageSize();
				Integer page = qestnrVO.getPageIndex();

				int totalCnt = 0;
				if( 0 < listQestnrStdInfo.size() ){
					totalCnt = Integer.parseInt( listQestnrStdInfo.get(0).getTotalCount() );
				}
				int totalPage = (int) Math.ceil(totalCnt / pageSize);

		        model.addAttribute("pageSize", pageSize);
		        model.addAttribute("totalCount", totalCnt);
		        model.addAttribute("pageIndex", page);

		        PaginationInfo paginationInfo = new PaginationInfo();

		        paginationInfo.setCurrentPageNo(qestnrVO.getPageIndex());
		        paginationInfo.setRecordCountPerPage(qestnrVO.getPageSize());
		        paginationInfo.setPageSize(qestnrVO.getPageSize());
		        paginationInfo.setTotalRecordCount(totalCnt);

		        model.addAttribute("resultCnt", totalCnt );
		        model.addAttribute("paginationInfo", paginationInfo);
				model.addAttribute("resultList", listQestnrStdInfo); 	  //설문지정보 목록
			}

			model.addAttribute("rowNumber", rowNumber); //List RowNumber
			model.addAttribute("currProcReadVO", currProcReadVO); //개설강좌 정보
		}

		model.addAttribute("qestnrVO", qestnrVO); //설문 파라메터정보

		if(!"STD".equals(qestnrVO.getSessionMemType())){
			return "oklms/lu/qestnr/qestnrList"; // View호출 (교수자, 학과담당자)
		}else{
			return "oklms/lu/qestnr/qestnrStdList"; // View호출 (학습근로자)
		}
	}

	/**
	 * 설문 상세 조회
	 * @param qestnrVO
	 * @return QestnrVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/qestnr/getQestnr.do")
	public String getQestnr(@ModelAttribute("frmQestnr") QestnrVO qestnrVO, ModelMap model) throws Exception {

		CurrProcVO currProcVO = new CurrProcVO();
		CurrProcVO currProcReadVO = new CurrProcVO();
		QestnrVO readQestnrInfoVO = new QestnrVO();
		QestnrVO readQestnrItemVO = new QestnrVO();

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(qestnrVO); // session의 정보를 VO에 추가.

		currProcVO.setYyyy(qestnrVO.getYyyy());
		currProcVO.setTerm(qestnrVO.getTerm());
		currProcVO.setSubjectCode(qestnrVO.getSubjectCode());
		currProcVO.setSubClass(qestnrVO.getSubClass());

		currProcReadVO = currProcService.getMySubjectInfo(currProcVO);
	    readQestnrInfoVO = qestnrService.getQestnrInfo(qestnrVO);
	    readQestnrItemVO = qestnrService.getQestnrItem(qestnrVO);

		model.addAttribute("currProcReadVO", currProcReadVO);  			//개설강좌 정보
		model.addAttribute("readQestnrInfoVO", readQestnrInfoVO);    	//설문 상세조회
		model.addAttribute("readQestnrItemVO", readQestnrItemVO); 	  	//설문문항 목록
		model.addAttribute("qestnrVO", qestnrVO);  						//설문 파라메터정보

		if(!"STD".equals(qestnrVO.getSessionMemType())){
			return "oklms/lu/qestnr/qestnrRead"; // View호출 (교수자, 학과담당자)
		}else{
			return "oklms/lu/qestnr/qestnrStdRead"; // View호출 (학습근로자)
		}
	}

	/**
	 * 설문 신규화면 이동
	 * @param qestnrVO
	 * @return QestnrVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/qestnr/goInsertQestnr.do")
	public String goInsertQestnr(@ModelAttribute("frmQestnr") QestnrVO qestnrVO, ModelMap model) throws Exception {

		CurrProcVO currProcVO = new CurrProcVO();
		CurrProcVO currProcReadVO = new CurrProcVO();

		currProcVO.setYyyy(qestnrVO.getYyyy());
		currProcVO.setTerm(qestnrVO.getTerm());
		currProcVO.setSubjectCode(qestnrVO.getSubjectCode());
		currProcVO.setSubClass(qestnrVO.getSubClass());

		currProcReadVO = currProcService.getMySubjectInfo(currProcVO);

		model.addAttribute("currProcReadVO", currProcReadVO);  //개설강좌 정보
		model.addAttribute("qestnrVO", qestnrVO);              //설문 파라메터정보

		// View호출
		return "oklms/lu/qestnr/qestnrCret";
	}

	/**
	 * 설문 수정화면 이동
	 * @param qestnrVO
	 * @return QestnrVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/qestnr/goUpdateQestnr.do")
	public String goUpdateQestnr(@ModelAttribute("frmQestnr") QestnrVO qestnrVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		CurrProcVO currProcVO = new CurrProcVO();
		CurrProcVO currProcReadVO = new CurrProcVO();
		QestnrVO readQestnrInfoVO = new QestnrVO();
		QestnrVO readQestnrItemVO = new QestnrVO();

		currProcVO.setYyyy(qestnrVO.getYyyy());
		currProcVO.setTerm(qestnrVO.getTerm());
		currProcVO.setSubjectCode(qestnrVO.getSubjectCode());
		currProcVO.setSubClass(qestnrVO.getSubClass());

		currProcReadVO = currProcService.getMySubjectInfo(currProcVO);
		readQestnrInfoVO = qestnrService.getQestnrInfo(qestnrVO);
	    readQestnrItemVO = qestnrService.getQestnrItem(qestnrVO);

		model.addAttribute("currProcReadVO", currProcReadVO);  			//개설강좌 정보
		model.addAttribute("readQestnrInfoVO", readQestnrInfoVO);    	//설문 상세조회
		model.addAttribute("readQestnrItemVO", readQestnrItemVO); 	  	//설문문항 목록
		model.addAttribute("qestnrVO", qestnrVO);  						//설문 파라메터정보

		// View호출
		return "oklms/lu/qestnr/qestnrUpdt";
	}

	/**
	 * 설문지 저장
	 * @param qestnrVO
	 * @return QestnrVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/qestnr/insertQestnr.do")
	public String insertQestnr(@ModelAttribute("frmQestnr") QestnrVO qestnrVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "입력값을 확인해주세요";
		int insertCnt1 = 0;
		int insertCnt2 = 0;

		insertCnt1 = qestnrService.insertQestnrInfo(qestnrVO);

		if(insertCnt1 > 0){
			insertCnt2 = qestnrService.insertQestnrItem(qestnrVO);
		}

		if(insertCnt1 > 0  && insertCnt2 > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmQestnr", qestnrVO);

		return "redirect:/lu/qestnr/listQestnr.do";
	}

	/**
	 * 교과별 설문지 학습자별 저장
	 * @param qestnrVO
	 * @return QestnrVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/qestnr/insertQestnrAnswerResult.do")
	public String insertQestnrAnswerResult(@ModelAttribute("frmQestnr") QestnrVO qestnrVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "입력값을 확인해주세요";
		int insertCnt = 0;

		insertCnt = qestnrService.insertQestnrAnswerResult(qestnrVO);

		if(insertCnt > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "저장 처리된건이 없습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmQestnr", qestnrVO);

		return "redirect:/lu/qestnr/listQestnr.do";
	}

	/**
	 * 설문지 수정
	 * @param qestnrVO
	 * @return QestnrVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/qestnr/updateQestnr.do")
	public String updateQestnr(@ModelAttribute("frmQestnr") QestnrVO qestnrVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "입력값을 확인해주세요";
		int updateCnt1 = 0;
		int updateCnt2 = 0;

		updateCnt1 = qestnrService.updateQestnrInfo(qestnrVO);

		if(updateCnt1 > 0){
			updateCnt2 = qestnrService.updateQestnrItem(qestnrVO);
		}

		if(updateCnt1 > 0  && updateCnt2 > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "수정 처리된건이 없습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmQestnr", qestnrVO);

		return "redirect:/lu/qestnr/listQestnr.do";
	}

	/**
	 * 설문지 삭제
	 * @param qestnrVO
	 * @return QestnrVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/qestnr/deleteQestnr.do")
	public String deleteQestnrQestnr(@ModelAttribute("frmQestnr") QestnrVO qestnrVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "입력값을 확인해주세요";
		int deleteCnt1 = 0;
		int deleteCnt2 = 0;

		deleteCnt1 = qestnrService.deleteQestnrInfo(qestnrVO);
		deleteCnt2 = qestnrService.deleteQestnrItem(qestnrVO);

		if(deleteCnt1 > 0  && deleteCnt2 > 0){
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "삭제 처리된건이 없습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("frmQestnr", qestnrVO);

		return "redirect:/lu/qestnr/listQestnr.do";
	}

}
