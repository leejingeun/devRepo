package kr.co.sitglobal.oklms.mm.qestnr.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.co.sitglobal.ifx.service.IfxService;
import kr.co.sitglobal.ifx.vo.AunuriMemberVO;
import kr.co.sitglobal.ifx.vo.AunuriSubjectVO;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.lu.currproc.service.CurrProcService;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.qestnr.service.QestnrService;
import kr.co.sitglobal.oklms.lu.qestnr.vo.QestnrVO;

@Controller
public class QestnrMMController extends BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(QestnrMMController.class);
	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;
	
	@Resource(name = "ifxService")
	private IfxService ifxService;

	@Resource(name = "qestnrService")
	private QestnrService qestnrService;

	@Resource(name = "currProcService")
	private CurrProcService currProcService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	@Resource(name = "beanValidatorJSR303")
	Validator validator;

	/**
	 * 설문 목록
	 * @param qestnrVO
	 * @return QestnrVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/qestnr/listQestnr.do")
	public String listQestnr( @ModelAttribute("frmQestnr") QestnrVO qestnrVO, ModelMap model, HttpServletRequest request) throws Exception {

		LOG.debug("#### URL = /mm/qestnr/listQestnr.do" );
  		
		String retView = "oklms/mm/qestnr/qestnrStdList";
		
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo(); 
		loginInfo.putSessionToVo(qestnrVO); // session의 정보를 VO에 추가. 
		
		//API 통한 교과정보, 관리자 제외한 진행중인 교과정보.
		AunuriMemberVO aunuriMemberVO = new AunuriMemberVO();
		aunuriMemberVO.setSessionMemSeq(loginInfo.getMemSeq());
		
		if("STD".equals(loginInfo.getMemType())){ //학습근로자 개설교과
			List<AunuriSubjectVO> listOffJtAunuriSubject= ifxService.getOffJtAunuriSubjectLesson(aunuriMemberVO);
			model.addAttribute( "listOffJtAunuriSubject", listOffJtAunuriSubject );
			
		} else if("PRT".equals(loginInfo.getMemType())){ //담당교수 개설교과
			List<AunuriSubjectVO> listOffJtAunuriSubject= ifxService.getOffJtAunuriSubjectInsMapping(aunuriMemberVO);
			model.addAttribute( "listOffJtAunuriSubject", listOffJtAunuriSubject );
		}  
	 	  
		qestnrVO.setPageSize(qestnrVO.getRecordCountPerPage());
		
		if(!"STD".equals(qestnrVO.getSessionMemType())){
			List<QestnrVO> listQestnrInfo = qestnrService.listQestnrInfo(qestnrVO);
			Integer pageSize = qestnrVO.getRecordCountPerPage();
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
	        paginationInfo.setRecordCountPerPage(qestnrVO.getRecordCountPerPage());
	        paginationInfo.setPageSize(qestnrVO.getPageSize());
	        paginationInfo.setTotalRecordCount(totalCnt);

	        model.addAttribute("resultCnt", totalCnt );
	        model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("resultList", listQestnrInfo); 	  //설문지정보 목록
		}else{
			List<QestnrVO> listQestnrStdInfo = qestnrService.listQestnrStdInfo(qestnrVO);

			Integer pageSize = qestnrVO.getRecordCountPerPage();
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
	        paginationInfo.setRecordCountPerPage(qestnrVO.getRecordCountPerPage());
	        paginationInfo.setPageSize(qestnrVO.getPageSize());
	        paginationInfo.setTotalRecordCount(totalCnt);

	        model.addAttribute("resultCnt", totalCnt );
	        model.addAttribute("paginationInfo", paginationInfo);
			model.addAttribute("resultList", listQestnrStdInfo); 	  //설문지정보 목록
		}

		
		model.addAttribute("qestnrVO", qestnrVO );
		
		return retView  ;
	}

	/**
	 * 설문 상세 조회
	 * @param qestnrVO
	 * @return QestnrVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/qestnr/getQestnr.do")
	public String getQestnr(@ModelAttribute("frmQestnr") QestnrVO qestnrVO, ModelMap model) throws Exception {
		
		LOG.debug("#### URL = /mm/qestnr/listQestnr.do" );
  		 
		
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
			
			List<QestnrVO> listQestnrEtcAnswerCn = qestnrService.listQestnrEtcAnswerCn(qestnrVO);
			model.addAttribute("resultList", listQestnrEtcAnswerCn);
			
			return "oklms/mm/qestnr/qestnrRead"; // View호출 (교수자, 학과담당자)
		}else{
			return "oklms/mm/qestnr/qestnrStdRead"; // View호출 (학습근로자)
		}
	}
	/**
	 * 교과별 설문지 학습자별 저장
	 * @param qestnrVO
	 * @return QestnrVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/qestnr/insertQestnrAnswerResult.do")
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

		return "redirect:/mm/qestnr/listQestnr.do";
	}
	/**
	 * 설문지 저장
	 * @param qestnrVO
	 * @return QestnrVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/qestnr/insertQestnr.do")
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

		return "redirect:/mm/qestnr/listQestnr.do";
	}
	 
	/**
	 * 설문 신규화면 이동
	 * @param qestnrVO
	 * @return QestnrVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/qestnr/goInsertQestnr.do")
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
		return "oklms/mm/qestnr/qestnrCret";
	}
	/**
	 * 설문 수정화면 이동
	 * @param qestnrVO
	 * @return QestnrVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/qestnr/goUpdateQestnr.do")
	public String goUpdateQestnr(@ModelAttribute("frmQestnr") QestnrVO qestnrVO, ModelMap model ) throws Exception {

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
		return "oklms/mm/qestnr/qestnrUpdt";
	}

	/**
	 * 설문지 수정
	 * @param qestnrVO
	 * @return QestnrVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/qestnr/updateQestnr.do")
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

		return "redirect:/mm/qestnr/listQestnr.do";
	}

	/**
	 * 설문지 삭제
	 * @param qestnrVO
	 * @return QestnrVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/mm/qestnr/deleteQestnr.do")
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

		return "redirect:/mm/qestnr/listQestnr.do";
	}	
}
