package kr.co.sitglobal.oklms.la.egovframework.com.uss.olp.qim.web;

import java.util.List;
import java.util.Map;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olp.qim.service.EgovQustnrItemManageService;
import egovframework.com.uss.olp.qim.service.QustnrItemManageVO;
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
 * 설문항목관리를 처리하는 Controller Class 구현
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
public class EgovQustnrItemManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovQustnrItemManageController.class);

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "egovQustnrItemManageService")
	private EgovQustnrItemManageService egovQustnrItemManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name = "egovQustnrTmplatManageService")
	private EgovQustnrTmplatManageService egovQustnrTmplatManageService;

	/**
	 * 설문항목 팝업 목록을 조회한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrItemManageVO
	 * @param model
	 * @return "oklms/la/egovframework/com/uss/olp/qim/EgovQustnrItemManageListPopup"
	 * @throws Exception
	 */
	@RequestMapping(value = "/la/uss/olp/qim/popupEgovQustnrItemManageList.do")
	public String egovQustnrItemManageListPopup(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			QustnrItemManageVO qustnrItemManageVO,
    		ModelMap model)
    throws Exception {

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		if(sCmd.equals("del")){
			egovQustnrItemManageService.deleteQustnrItemManage(qustnrItemManageVO);
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

        List<?> sampleList = egovQustnrItemManageService.selectQustnrItemManageList(searchVO);
        model.addAttribute("resultList", sampleList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

        int totCnt = egovQustnrItemManageService.selectQustnrItemManageListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

		return "oklms_popup/la/egovframework/com/uss/olp/qim/EgovQustnrItemManageListPopup";
	}

	/**
	 * 설문항목 목록을 조회한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrItemManageVO
	 * @param model
	 * @return "oklms/la/egovframework/com/uss/olp/qim/EgovQustnrItemManageList"
	 * @throws Exception
	 */
	@IncludedInfo(name="항목관리", order = 640 ,gid = 50)
	@RequestMapping(value = "/la/uss/olp/qim/listEgovQustnrItemManage.do")
	public String egovQustnrItemManageList(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			QustnrItemManageVO qustnrItemManageVO,
    		ModelMap model)
    throws Exception {

		String sSearchMode = commandMap.get("searchMode") == null ? "" : (String)commandMap.get("searchMode");

		//설문문항에 넘어온 건에 대해 조회
		if(sSearchMode.equals("Y")){
			searchVO.setSearchCondition("QUSTNR_QESITM_ID");//qestnrQesitmId
			searchVO.setSearchKeyword(qustnrItemManageVO.getQestnrQesitmId());
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

        List<?> sampleList = egovQustnrItemManageService.selectQustnrItemManageList(searchVO);
        model.addAttribute("resultList", sampleList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

        int totCnt = egovQustnrItemManageService.selectQustnrItemManageListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

		return "oklms/la/egovframework/com/uss/olp/qim/EgovQustnrItemManageList";
	}

	/**
	 * 설문항목 목록을 상세조회 조회한다.
	 * @param searchVO
	 * @param qustnrItemManageVO
	 * @param commandMap
	 * @param model
	 * @return  "/uss/olp/qim/EgovQustnrItemManageDetail"
	 * @throws Exception
	 */
	@RequestMapping(value = "/la/uss/olp/qim/getEgovQustnrItemManage.do")
	public String egovQustnrItemManageDetail(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			QustnrItemManageVO qustnrItemManageVO,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model, RedirectAttributes redirectAttributes)
    throws Exception {

		String sLocationUrl = "oklms/la/egovframework/com/uss/olp/qim/EgovQustnrItemManageDetail";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

		if(sCmd.equals("del")){
			QustnrTmplatManageVO qustnrTmplatManageVO = new QustnrTmplatManageVO();
			qustnrTmplatManageVO.setQestnrTmplatId(qustnrItemManageVO.getQestnrTmplatId());
			
			//설문응답결과에 해당 템플릿이 한건이라도 있으면 삭제를 할수 없다.
			int totCnt = 0;
			totCnt = egovQustnrTmplatManageService.selectDelCnt(qustnrTmplatManageVO); 
			if(totCnt == 0){
				egovQustnrItemManageService.deleteQustnrItemManage(qustnrItemManageVO);
				redirectAttributes.addFlashAttribute("retMsg", "정상적으로 (삭제)처리되었습니다.");
			}else{
				redirectAttributes.addFlashAttribute("retMsg", "설문조사가 있는 템플릿이라 삭제할수 없습니다.");
			}
			
			sLocationUrl = "redirect:/la/uss/olp/qim/listEgovQustnrItemManage.do";
		}else{
	        List<?> sampleList = egovQustnrItemManageService.selectQustnrItemManageDetail(qustnrItemManageVO);
	        model.addAttribute("resultList", sampleList);
		}

		return sLocationUrl;
	}

	/**
	 * 설문항목를 수정한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrItemManageVO
	 * @param bindingResult
	 * @param model
	 * @return "oklms/la/egovframework/com/uss/olp/qim/EgovQustnrItemManageModify"
	 * @throws Exception
	 */
	@RequestMapping(value = "/la/uss/olp/qim/goUpdateEgovQustnrItemManage.do")
	public String qustnrItemManageModify(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("qustnrItemManageVO") QustnrItemManageVO qustnrItemManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "oklms/la/egovframework/com/uat/uia/EgovLoginUsr";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "oklms/la/egovframework/com/uss/olp/qim/EgovQustnrItemManageModify";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

        if(sCmd.equals("save")){

    		//서버  validate 체크
            /*beanValidator.validate(qustnrItemManageVO, bindingResult);
    		if(bindingResult.hasErrors()){
            	//설문항목(을)를  정보 불러오기
                List<?> listQustnrTmplat = egovQustnrItemManageService.selectQustnrTmplatManageList(qustnrItemManageVO);
                model.addAttribute("listQustnrTmplat", listQustnrTmplat);
            	//게시물 불러오기
                List<?> sampleList = egovQustnrItemManageService.selectQustnrItemManageDetail(qustnrItemManageVO);
                model.addAttribute("resultList", sampleList);

                return "oklms/la/egovframework/com/uss/olp/qim/goUpdateEgovQustnrItemManage";
    		}*/

    		//아이디 설정
    		qustnrItemManageVO.setFrstRegisterId(loginVO.getUniqId());
    		qustnrItemManageVO.setLastUpdusrId(loginVO.getUniqId());

        	egovQustnrItemManageService.updateQustnrItemManage(qustnrItemManageVO);
        	sLocationUrl = "redirect:/la/uss/olp/qim/listEgovQustnrItemManage.do";
        }else{
            List<?> sampleList = egovQustnrItemManageService.selectQustnrItemManageDetail(qustnrItemManageVO);
            model.addAttribute("resultList", sampleList);

        	//설문항목(을)를  정보 불러오기
            //List<?> listQustnrTmplat = egovQustnrItemManageService.selectQustnrTmplatManageList(qustnrItemManageVO);
            //model.addAttribute("listQustnrTmplat", listQustnrTmplat);
        }

		return sLocationUrl;
	}

	/**
	 * 설문항목를 등록한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrItemManageVO
	 * @param bindingResult
	 * @param model
	 * @return "oklms/la/egovframework/com/uss/olp/qim/EgovQustnrItemManageRegist"
	 * @throws Exception
	 */
	@RequestMapping(value = "/la/uss/olp/qim/goInsertEgovQustnrItemManage.do")
	public String qustnrItemManageRegist(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("qustnrItemManageVO") QustnrItemManageVO qustnrItemManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
        	return "redirect:/la/uss/olp/qim/listEgovQustnrItemManage.do";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		String sLocationUrl = "oklms/la/egovframework/com/uss/olp/qim/EgovQustnrItemManageRegist";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		LOGGER.info("cmd => {}", sCmd);

        if(sCmd.equals("save")){

    		//서버  validate 체크
            /*beanValidator.validate(qustnrItemManageVO, bindingResult);
    		if(bindingResult.hasErrors()){
            	//설문항목(을)를  정보 불러오기
                List<?> listQustnrTmplat = egovQustnrItemManageService.selectQustnrTmplatManageList(qustnrItemManageVO);
                model.addAttribute("listQustnrTmplat", listQustnrTmplat);
                return "oklms/la/egovframework/com/uss/olp/qim/EgovQustnrItemManageRegist";
    		}*/

    		//아이디 설정
    		qustnrItemManageVO.setFrstRegisterId(loginVO.getUniqId());
    		qustnrItemManageVO.setLastUpdusrId(loginVO.getUniqId());

        	egovQustnrItemManageService.insertQustnrItemManage(qustnrItemManageVO);
        	sLocationUrl = "redirect:/la/uss/olp/qim/listEgovQustnrItemManage.do";
        }else{
        	//설문항목(을)를  정보 불러오기
            List<?> listQustnrTmplat = egovQustnrItemManageService.selectQustnrTmplatManageList(qustnrItemManageVO);
            model.addAttribute("listQustnrTmplat", listQustnrTmplat);
        }

		return sLocationUrl;
	}

}


