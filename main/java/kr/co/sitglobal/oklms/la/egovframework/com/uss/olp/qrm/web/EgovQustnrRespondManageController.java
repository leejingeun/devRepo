package kr.co.sitglobal.oklms.la.egovframework.com.uss.olp.qrm.web;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olp.qrm.service.EgovQustnrRespondManageService;
import egovframework.com.uss.olp.qrm.service.QustnrRespondManageVO;
import egovframework.com.uss.olp.qtm.service.EgovQustnrTmplatManageService;
import egovframework.com.uss.olp.qtm.service.QustnrTmplatManageVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springmodules.validation.commons.DefaultBeanValidator;
/**
 * 설문응답자관리 Controller Class 구현
 * @author 공통서비스 장동한
 * @since 2009.03.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  장동한          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *   2016.12.20	이진근		  모듈 수정
 * </pre>
 */

@Controller
public class EgovQustnrRespondManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovQustnrRespondManageController.class);

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "egovQustnrRespondManageService")
	private EgovQustnrRespondManageService egovQustnrRespondManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;
	
	@Resource(name = "egovQustnrTmplatManageService")
	private EgovQustnrTmplatManageService egovQustnrTmplatManageService;

	/**
	 * 응답자정보 목록을 조회한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrRespondManageVO
	 * @param model
	 * @return "oklms/la/egovframework/com/uss/olp/qrm/EgovQustnrRespondManageList"
	 * @throws Exception
	 */
	@IncludedInfo(name="응답자관리",  order = 620 ,gid = 50)
	@RequestMapping(value = "/la/uss/olp/qrm/listEgovQustnrRespondManage.do")
	public String listEgovQustnrRespondManage(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			QustnrRespondManageVO qustnrRespondManageVO,
    		ModelMap model)
    throws Exception {

		String sSearchMode = commandMap.get("searchMode") == null ? "" : (String)commandMap.get("searchMode");

		//설문지정보에서 넘어오면 자동검색 설정
		if(sSearchMode.equals("Y")){
			searchVO.setSearchCondition("QESTNR_ID");
			searchVO.setSearchKeyword(qustnrRespondManageVO.getQestnrId());
		}

    	/** EgovPropertyService.sample */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> sampleList = egovQustnrRespondManageService.selectQustnrRespondManageList(searchVO);
        model.addAttribute("resultList", sampleList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

        int totCnt = egovQustnrRespondManageService.selectQustnrRespondManageListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

		return "oklms/la/egovframework/com/uss/olp/qrm/EgovQustnrRespondManageList";
	}

	/**
	 * 응답자정보 목록을 상세조회 조회한다.
	 * @param searchVO
	 * @param qustnrRespondManageVO
	 * @param commandMap
	 * @param model
	 * @return "oklms/la/egovframework/com/uss/olp/qrm/EgovQustnrRespondManageDetail"
	 * @throws Exception
	 */
	@RequestMapping(value = "/la/uss/olp/qrm/getEgovQustnrRespondManage.do")
	public String getEgovQustnrRespondManage(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			QustnrRespondManageVO qustnrRespondManageVO,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model, RedirectAttributes redirectAttributes)
    throws Exception {

		String sLocationUrl = "oklms/la/egovframework/com/uss/olp/qrm/EgovQustnrRespondManageDetail";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

		if(sCmd.equals("del")){
			QustnrTmplatManageVO qustnrTmplatManageVO = new QustnrTmplatManageVO();
			qustnrTmplatManageVO.setQestnrTmplatId(qustnrRespondManageVO.getQestnrTmplatId());
			
			//설문응답결과에 해당 템플릿이 한건이라도 있으면 삭제를 할수 없다.
			int totCnt = 0;
			totCnt = egovQustnrTmplatManageService.selectDelCnt(qustnrTmplatManageVO); 
			if(totCnt == 0){
				egovQustnrRespondManageService.deleteQustnrRespondManage(qustnrRespondManageVO);
				redirectAttributes.addFlashAttribute("retMsg", "정상적으로 (삭제)처리되었습니다.");
			}else{
				redirectAttributes.addFlashAttribute("retMsg", "설문조사가 있는 템플릿이라 삭제할수 없습니다.");
			}
			
			sLocationUrl = "redirect:/la/uss/olp/qrm/listEgovQustnrRespondManage.do";
		}else{
	     	//성별코드조회
	    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
	    	voComCode.setCodeId("COM014");
	    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
	    	model.addAttribute("comCode014", listComCode);

	    	//직업코드조회
	    	voComCode.setCodeId("COM034");
	    	listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
	    	model.addAttribute("comCode034", listComCode);

	        List<?> sampleList = egovQustnrRespondManageService.selectQustnrRespondManageDetail(qustnrRespondManageVO);
	        model.addAttribute("resultList", sampleList);
		}

		return sLocationUrl;
	}

	/**
	 * 응답자정보를 수정한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrRespondManageVO
	 * @param bindingResult
	 * @param model
	 * @return "oklms/la/egovframework/com/uss/olp/qrm/EgovQustnrRespondManageModify"
	 * @throws Exception
	 */
	@RequestMapping(value = "/la/uss/olp/qrm/goUpdateEgovQustnrRespondManage.do")
	public String qustnrRespondManageModify(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("qustnrRespondManageVO") QustnrRespondManageVO qustnrRespondManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "redirect:/la/uss/olp/qrm/listEgovQustnrRespondManage.do";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "oklms/la/egovframework/com/uss/olp/qrm/EgovQustnrRespondManageModify";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

     	//성별코드조회
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM014");
    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("comCode014", listComCode);

    	//직업코드조회
    	voComCode.setCodeId("COM034");
    	listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("comCode034", listComCode);

        if(sCmd.equals("save")){
    		//서버  validate 체크
            /*beanValidator.validate(qustnrRespondManageVO, bindingResult);
    		if(bindingResult.hasErrors()){

    			return sLocationUrl;
    		}*/
    		//아이디 설정
    		qustnrRespondManageVO.setFrstRegisterId(loginVO.getUniqId());
    		qustnrRespondManageVO.setLastUpdusrId(loginVO.getUniqId());

        	egovQustnrRespondManageService.updateQustnrRespondManage(qustnrRespondManageVO);
        	sLocationUrl = "redirect:/la/uss/olp/qrm/listEgovQustnrRespondManage.do";
		}else{
	        List<?> sampleList = egovQustnrRespondManageService.selectQustnrRespondManageDetail(qustnrRespondManageVO);
	        model.addAttribute("resultList", sampleList);
		}

		return sLocationUrl;
	}

	/**
	 * 응답자정보를 등록한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrRespondManageVO
	 * @param bindingResult
	 * @param model
	 * @return "oklms/la/egovframework/com/uss/olp/qrm/EgovQustnrRespondManageRegist"
	 * @throws Exception
	 */
	@RequestMapping(value = "/la/uss/olp/qrm/goInsertEgovQustnrRespondManage.do")
	public String qustnrRespondManageRegist(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("qustnrRespondManageVO") QustnrRespondManageVO qustnrRespondManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
    		return "redirect:/la/uss/olp/qrm/listEgovQustnrRespondManage.do";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "oklms/la/egovframework/com/uss/olp/qrm/EgovQustnrRespondManageRegist";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		LOGGER.info("cmd => {}", sCmd);

     	//성별코드조회
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM014");
    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("comCode014", listComCode);

    	//직업코드조회
    	voComCode.setCodeId("COM034");
    	listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("comCode034", listComCode);

        if(sCmd.equals("save")){
    		//서버  validate 체크
            /*beanValidator.validate(qustnrRespondManageVO, bindingResult);
    		if(bindingResult.hasErrors()){

    			return sLocationUrl;
    		}*/
    		//아이디 설정
    		qustnrRespondManageVO.setFrstRegisterId(loginVO.getUniqId());
    		qustnrRespondManageVO.setLastUpdusrId(loginVO.getUniqId());

        	egovQustnrRespondManageService.insertQustnrRespondManage(qustnrRespondManageVO);
        	sLocationUrl = "redirect:/la/uss/olp/qrm/listEgovQustnrRespondManage.do";
        }

		return sLocationUrl;
	}

}


