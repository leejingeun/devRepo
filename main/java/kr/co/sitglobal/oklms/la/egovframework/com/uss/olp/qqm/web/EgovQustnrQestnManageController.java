package kr.co.sitglobal.oklms.la.egovframework.com.uss.olp.qqm.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olp.qqm.service.EgovQustnrQestnManageService;
import egovframework.com.uss.olp.qqm.service.QustnrQestnManageVO;
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
 * 설문문항을 처리하는 Controller Class 구현
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
 *   2011.8.26	정진오		  IncludedInfo annotation 추가
 *   2016.12.20	이진근		  모듈 수정
 *
 * </pre>
 */

@Controller
public class EgovQustnrQestnManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovQustnrQestnManageController.class);

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "egovQustnrQestnManageService")
	private EgovQustnrQestnManageService egovQustnrQestnManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;
	
	@Resource(name = "egovQustnrTmplatManageService")
	private EgovQustnrTmplatManageService egovQustnrTmplatManageService;

    /**
     * 설문항목 통계를 조회한다.
     * @param searchVO
     * @param qustnrQestnManageVO
     * @param commandMap
     * @param model
     * @return "oklms/la/egovframework/com/uss/olp/qqm/EgovQustnrQestnManageStatistics"
     * @throws Exception
     */
    @RequestMapping(value = "/la/uss/olp/qqm/EgovQustnrQestnManageStatistics.do")
	public String egovQustnrQestnManageStatistics(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			QustnrQestnManageVO qustnrQestnManageVO,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model)
    throws Exception {

		String sLocationUrl = "oklms/la/egovframework/com/uss/olp/qqm/EgovQustnrQestnManageStatistics";

        List<?> sampleList = egovQustnrQestnManageService.selectQustnrQestnManageDetail(qustnrQestnManageVO);
        model.addAttribute("resultList", sampleList);
        // 객관식설문통계
        HashMap<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("qestnrQesitmId", qustnrQestnManageVO.getQestnrQesitmId());
        List<?> statisticsList = egovQustnrQestnManageService.selectQustnrManageStatistics(mapParam);
        model.addAttribute("statisticsList", statisticsList);
        // 주관식설문통계
        List<?> statisticsList2 = egovQustnrQestnManageService.selectQustnrManageStatistics2(mapParam);
        model.addAttribute("statisticsList2", statisticsList2);
		return sLocationUrl;
	}

	/**
	 * 설문문항 팝업 록을 조회한다.
	 * @param searchVO
	 * @param qustnrQestnManageVO
	 * @param commandMap
	 * @param model
	 * @return "oklms/la/egovframework/com/uss/olp/qqm/EgovQustnrQestnManageListPopup"
	 * @throws Exception
	 */
	@RequestMapping(value = "/la/uss/olp/qqm/popupEgovQustnrQestnManageList.do")
	public String egovQustnrQestnManageListPopup(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@ModelAttribute("qustnrQestnManageVO") QustnrQestnManageVO qustnrQestnManageVO,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model)
    throws Exception {

		String sSearchMode = commandMap.get("searchMode") == null ? "" : (String)commandMap.get("searchMode");

		//설문지정보에서 넘어오면 자동검색 설정
		if(sSearchMode.equals("Y")){
			searchVO.setSearchCondition("QESTNR_ID");
			searchVO.setSearchKeyword(qustnrQestnManageVO.getQestnrId());
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

        List<?> resultList = egovQustnrQestnManageService.selectQustnrQestnManageList(searchVO);
        model.addAttribute("resultList", resultList);

        int totCnt = egovQustnrQestnManageService.selectQustnrQestnManageListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

		return "oklms_popup/la/egovframework/com/uss/olp/qqm/EgovQustnrQestnManageListPopup";
	}

	/**
	 * 설문문항 목록을 조회한다.
	 * @param searchVO
	 * @param qustnrQestnManageVO
	 * @param commandMap
	 * @param model
	 * @return "oklms/la/egovframework/com/uss/olp/qqm/EgovQustnrQestnManageList"
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@IncludedInfo(name="질문관리", order = 630 ,gid = 50)
	@RequestMapping(value = "/la/uss/olp/qqm/listEgovQustnrQestnManage.do")
	public String egovQustnrQestnManageList(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@ModelAttribute("qustnrQestnManageVO") QustnrQestnManageVO qustnrQestnManageVO,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model)
    throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "forward:/la/uss/olp/qqm/listEgovQustnrQestnManage.do";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		String sSearchMode = commandMap.get("searchMode") == null ? "" : (String)commandMap.get("searchMode");

		if(sCmd.equals("del")){
			egovQustnrQestnManageService.deleteQustnrQestnManage(qustnrQestnManageVO);
		}

		//설문지정보에서 넘어오면 자동검색 설정
		if(sSearchMode.equals("Y")){
			searchVO.setSearchCondition("QESTNR_ID");
			searchVO.setSearchKeyword(qustnrQestnManageVO.getQestnrId());
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

        List<?> sampleList = egovQustnrQestnManageService.selectQustnrQestnManageList(searchVO);
        model.addAttribute("resultList", sampleList);

        int totCnt = egovQustnrQestnManageService.selectQustnrQestnManageListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

		return "oklms/la/egovframework/com/uss/olp/qqm/EgovQustnrQestnManageList";
	}

	/**
	 * 설문문항 목록을 상세조회 조회한다.
	 * @param searchVO
	 * @param qustnrQestnManageVO
	 * @param commandMap
	 * @param model
	 * @return "oklms/la/egovframework/com/uss/olp/qqm/EgovQustnrQestnManageDetail"
	 * @throws Exception
	 */
	@RequestMapping(value = "/la/uss/olp/qqm/getEgovQustnrQestnManage.do")
	public String egovQustnrQestnManageDetail(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@ModelAttribute("qustnrQestnManageVO") QustnrQestnManageVO qustnrQestnManageVO,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model, RedirectAttributes redirectAttributes)
    throws Exception {

		String sLocationUrl = "oklms/la/egovframework/com/uss/olp/qqm/EgovQustnrQestnManageDetail";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

		if(sCmd.equals("del")){
			QustnrTmplatManageVO qustnrTmplatManageVO = new QustnrTmplatManageVO();
			qustnrTmplatManageVO.setQestnrTmplatId(qustnrQestnManageVO.getQestnrTmplatId());
			
			//설문응답결과에 해당 템플릿이 한건이라도 있으면 삭제를 할수 없다.
			int totCnt = 0;
			totCnt = egovQustnrTmplatManageService.selectDelCnt(qustnrTmplatManageVO); 
			if(totCnt == 0){
				egovQustnrQestnManageService.deleteQustnrQestnManage(qustnrQestnManageVO);
				redirectAttributes.addFlashAttribute("retMsg", "정상적으로 (삭제)처리되었습니다.");
			}else{
				redirectAttributes.addFlashAttribute("retMsg", "설문조사가 있는 템플릿이라 삭제할수 없습니다.");
			}
			
			/** 목록으로갈때 검색조건 유지 */
			sLocationUrl = "forward:/la/uss/olp/qqm/listEgovQustnrQestnManage.do";
		}else{
	     	//공통코드 질문유형 조회
	    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
	    	voComCode.setCodeId("COM018");
	    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
	    	model.addAttribute("cmmCode018", listComCode);

	        List<?> sampleList = egovQustnrQestnManageService.selectQustnrQestnManageDetail(qustnrQestnManageVO);
	        model.addAttribute("resultList", sampleList);
		}

		return sLocationUrl;
	}

	/**
	 * 설문문항를 수정한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrQestnManageVO
	 * @param bindingResult
	 * @param model
	 * @return "oklms/la/egovframework/com/uss/olp/qqm/EgovQustnrQestnManageModify"
	 * @throws Exception
	 */
	@RequestMapping(value = "/la/uss/olp/qqm/goUpdateEgovQustnrQestnManage.do")
	public String qustnrQestnManageModify(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("qustnrQestnManageVO") QustnrQestnManageVO qustnrQestnManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
    		return "forward:/la/uss/olp/qqm/listEgovQustnrQestnManage.do";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "oklms/la/egovframework/com/uss/olp/qqm/EgovQustnrQestnManageModify";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

     	//공통코드 질문유형 조회
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM018");
    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("cmmCode018", listComCode);

        if(sCmd.equals("save")){
    		//서버  validate 체크
            /*beanValidator.validate(qustnrQestnManageVO, bindingResult);
    		if (bindingResult.hasErrors()){
            	//설문제목가져오기
            	String sQestnrId = commandMap.get("qestnrId") == null ? "" : (String)commandMap.get("qestnrId");
            	String sQestnrTmplatId = commandMap.get("qestnrTmplatId") == null ? "" : (String)commandMap.get("qestnrTmplatId");

            	LOGGER.info("sQestnrId => {}", sQestnrId);
            	LOGGER.info("sQestnrTmplatId => {}", sQestnrTmplatId);
            	if(!sQestnrId.equals("") && !sQestnrTmplatId.equals("")){

            		Map<String, String> mapQustnrManage = new HashMap<String, String>();
            		mapQustnrManage.put("qestnrId", sQestnrId);
            		mapQustnrManage.put("qestnrTmplatId", sQestnrTmplatId);

            		model.addAttribute("qestnrInfo", egovQustnrQestnManageService.selectQustnrManageQestnrSj(mapQustnrManage));
            	}

                List<?> resultList = egovQustnrQestnManageService.selectQustnrQestnManageDetail(qustnrQestnManageVO);
                model.addAttribute("resultList", resultList);
            	return "oklms/la/egovframework/com/uss/olp/qqm/EgovQustnrQestnManageModify";
    		}*/

    		//아이디 설정
    		qustnrQestnManageVO.setFrstRegisterId(loginVO.getUniqId());
    		qustnrQestnManageVO.setLastUpdusrId(loginVO.getUniqId());

        	egovQustnrQestnManageService.updateQustnrQestnManage(qustnrQestnManageVO);
        	/** 목록으로갈때 검색조건 유지 */
        	sLocationUrl = "forward:/la/uss/olp/qqm/listEgovQustnrQestnManage.do";
        }else{
            List<?> resultList = egovQustnrQestnManageService.selectQustnrQestnManageDetail(qustnrQestnManageVO);
            model.addAttribute("resultList", resultList);

        }

		return sLocationUrl;
	}

	/**
	 * 설문문항를 등록한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrQestnManageVO
	 * @param bindingResult
	 * @param model
	 * @return "oklms/la/egovframework/com/uss/olp/qqm/EgovQustnrQestnManageRegist"
	 * @throws Exception
	 */
	@RequestMapping(value = "/la/uss/olp/qqm/goInsertEgovQustnrQestnManage.do")
	public String qustnrQestnManageRegist(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("qustnrQestnManageVO") QustnrQestnManageVO qustnrQestnManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
    		return "forward:/la/uss/olp/qqm/listEgovQustnrQestnManage.do";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "oklms/la/egovframework/com/uss/olp/qqm/EgovQustnrQestnManageRegist";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		LOGGER.info("cmd => {}", sCmd);

     	//공통코드 질문유형 조회
    	ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM018");
    	List<?> listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("cmmCode018", listComCode);

        if(sCmd.equals("save")){

    		//서버  validate 체크
            /*beanValidator.validate(qustnrQestnManageVO, bindingResult);
    		if (bindingResult.hasErrors()){
            	//설문제목가져오기
            	String sQestnrId = commandMap.get("qestnrId") == null ? "" : (String)commandMap.get("qestnrId");
            	String sQestnrTmplatId = commandMap.get("qestnrTmplatId") == null ? "" : (String)commandMap.get("qestnrTmplatId");

            	LOGGER.info("sQestnrId => {}", sQestnrId);
            	LOGGER.info("sQestnrTmplatId => {}", sQestnrTmplatId);
            	if(!sQestnrId.equals("") && !sQestnrTmplatId.equals("")){

            		Map<String, String> mapQustnrManage = new HashMap<String, String>();
            		mapQustnrManage.put("qestnrId", sQestnrId);
            		mapQustnrManage.put("qestnrTmplatId", sQestnrTmplatId);

            		model.addAttribute("qestnrInfo", egovQustnrQestnManageService.selectQustnrManageQestnrSj(mapQustnrManage));
            	}

    			return "oklms/la/egovframework/com/uss/olp/qqm/EgovQustnrQestnManageRegist";
    		}*/

    		//아이디 설정
    		qustnrQestnManageVO.setFrstRegisterId(loginVO.getUniqId());
    		qustnrQestnManageVO.setLastUpdusrId(loginVO.getUniqId());
    		
        	egovQustnrQestnManageService.insertQustnrQestnManage(qustnrQestnManageVO);
        	/** 목록으로갈때 검색조건 유지 */
        	sLocationUrl = "forward:/la/uss/olp/qqm/listEgovQustnrQestnManage.do";
        }

		return sLocationUrl;
	}
}


