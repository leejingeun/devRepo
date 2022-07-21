package kr.co.sitglobal.oklms.la.egovframework.com.uss.olp.qtm.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
//import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.olp.qtm.service.EgovQustnrTmplatManageService;
import egovframework.com.uss.olp.qtm.service.QustnrTmplatManageVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
 * 설문템플릿 Controller Class 구현
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
public class EgovQustnrTmplatManageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovQustnrTmplatManageController.class);

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "egovQustnrTmplatManageService")
	private EgovQustnrTmplatManageService egovQustnrTmplatManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name="EgovFileMngService")
    private EgovFileMngService fileMngService;

    @Resource(name="EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;

	/**
	 * 설문템플릿 목록을 조회한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrTmplatManageVO
	 * @param model
	 * @return "oklms/la/egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageList"
	 * @throws Exception
	 */
    @IncludedInfo(name="설문템플릿관리", order = 610 ,gid = 50)
	@RequestMapping(value = "/la/uss/olp/qtm/listEgovQustnrTmplatManage.do")
	public String listEgovQustnrTmplatManage(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			QustnrTmplatManageVO qustnrTmplatManageVO,
    		ModelMap model)
    throws Exception {

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

		if(sCmd.equals("del")){
			egovQustnrTmplatManageService.deleteQustnrTmplatManage(qustnrTmplatManageVO);
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

        List<?> sampleList = egovQustnrTmplatManageService.selectQustnrTmplatManageList(searchVO);
        model.addAttribute("resultList", sampleList);

        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

        int totCnt = egovQustnrTmplatManageService.selectQustnrTmplatManageListCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);


		return "oklms/la/egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageList";
	}

	/**
	 * 설문템플릿 이미지 목록을 상세조회 조회한다.
	 * @param request
	 * @param response
	 * @param qustnrTmplatManageVO
	 * @param commandMap
	 * @return "oklms/la/egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageImg"
	 * @throws Exception
	 */
	/*@SuppressWarnings("unused")
	@RequestMapping(value = "/la/uss/olp/qtm/EgovQustnrTmplatManageImg.do")
	public void egovQustnrTmplatManageImg(
			 HttpServletRequest request,
			 HttpServletResponse response,
			 QustnrTmplatManageVO qustnrTmplatManageVO,
			 @RequestParam Map<?, ?> commandMap
			 )throws Exception {

		Map<?, ?> mapResult = egovQustnrTmplatManageService.selectQustnrTmplatManageTmplatImagepathnm(qustnrTmplatManageVO);

		byte[] img = (byte[])mapResult.get("QUSTNR_TMPLAT_IMAGE_INFOPATHNM");

		String imgtype = "jpeg";
		String type = "";

		if(imgtype != null && !"".equals(imgtype)){
		      type="image/"+imgtype;
		}

		response.setHeader("Content-Type", imgtype);
		response.setHeader ("Content-Length", "" + img.length);
		response.getOutputStream().write(img);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}*/

	/**
	 * 설문템플릿 목록을 상세조회 조회한다.
	 * @param searchVO
	 * @param qustnrTmplatManageVO
	 * @param commandMap
	 * @param model
	 * @return "oklms/la/egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageDetail"
	 * @throws Exception
	 */
	@RequestMapping(value = "/la/uss/olp/qtm/getEgovQustnrTmplatManage.do")
	public String getEgovQustnrTmplatManage(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			QustnrTmplatManageVO qustnrTmplatManageVO,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model,
    		RedirectAttributes redirectAttributes)
    throws Exception {

		String sLocationUrl = "oklms/la/egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageDetail";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

		if(sCmd.equals("del")){
			//설문응답결과에 해당 템플릿이 한건이라도 있으면 삭제를 할수 없다.
			int totCnt = 0;
			totCnt = egovQustnrTmplatManageService.selectDelCnt(qustnrTmplatManageVO); 
			if(totCnt == 0){
				egovQustnrTmplatManageService.deleteQustnrTmplatManage(qustnrTmplatManageVO);
				redirectAttributes.addFlashAttribute("retMsg", "정상적으로 (삭제)처리되었습니다.");
			}else{
				redirectAttributes.addFlashAttribute("retMsg", "설문조사가 있는 템플릿이라 삭제할수 없습니다.");
			}
			
			sLocationUrl = "redirect:/la/uss/olp/qtm/listEgovQustnrTmplatManage.do";
		}else{
	        List<?> sampleList = egovQustnrTmplatManageService.selectQustnrTmplatManageDetail(qustnrTmplatManageVO);
	        model.addAttribute("resultList", sampleList);
		}

		return sLocationUrl;
	}

	/**
	 * 설문템플릿를 수정한다.
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrTmplatManageVO
	 * @param model
	 * @return "oklms/la/egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageModify"
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/la/uss/olp/qtm/goUpdateEgovQustnrTmplatManage.do")
	public String goUpdateEgovQustnrTmplatManage(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			QustnrTmplatManageVO qustnrTmplatManageVO,
    		ModelMap model)
    throws Exception {
		String sLocationUrl = "oklms/la/egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageModify";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");

        List<?> sampleList = egovQustnrTmplatManageService.selectQustnrTmplatManageDetail(qustnrTmplatManageVO);
        model.addAttribute("resultList", sampleList);


		return sLocationUrl;
	}

	/**
	 * 설문템플릿를 수정처리 한다.
	 * @param multiRequest
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrTmplatManageVO
	 * @param bindingResult
	 * @param model
	 * @return "oklms/la/egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageModifyActor"
	 * @throws Exception
	 */
	@RequestMapping(value = "/la/uss/olp/qtm/updateEgovQustnrTmplatManage.do")
	public String updateEgovQustnrTmplatManage(
			//final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("qustnrTmplatManageVO") QustnrTmplatManageVO qustnrTmplatManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {

    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
    		return "redirect:/la/uss/olp/qtm/listEgovQustnrTmplatManage.do";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		//서버  validate 체크
        beanValidator.validate(qustnrTmplatManageVO, bindingResult);
		if (bindingResult.hasErrors()){
	        List<?> sampleList = egovQustnrTmplatManageService.selectQustnrTmplatManageDetail(qustnrTmplatManageVO);
	        model.addAttribute("resultList", sampleList);
			return "oklms/la/egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageModify";
		}

		//아이디 설정
		qustnrTmplatManageVO.setFrstRegisterId(loginVO.getUniqId());
		qustnrTmplatManageVO.setLastUpdusrId(loginVO.getUniqId());


			/*final Map<String, MultipartFile> files = multiRequest.getFileMap();

			if (!files.isEmpty()) {
			  for(MultipartFile file : files.values()){
				  LOGGER.info("getName => {}", file.getName() );
				  LOGGER.info("getOriginalFilename => {}", file.getOriginalFilename() );

		          // 파일 수정여부 확인
	          	  if(file.getOriginalFilename() != "") {
			          if(file.getName().equals("qestnrTmplatImage")){
			          	qustnrTmplatManageVO.setQestnrTmplatImagepathnm( file.getBytes() );
			          }
	          	  }
			 }
		    }*/
    	egovQustnrTmplatManageService.updateQustnrTmplatManage(qustnrTmplatManageVO);

		return "redirect:/la/uss/olp/qtm/listEgovQustnrTmplatManage.do";
	}

	/**
	 * 설문템플릿를 등록한다. / 초기등록페이지
	 * @param searchVO
	 * @param commandMap
	 * @param qustnrTmplatManageVO
	 * @param model
	 * @return "oklms/la/egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageRegist"
	 * @throws Exception
	 */
	@RequestMapping(value = "/la/uss/olp/qtm/goInsertEgovQustnrTmplatManage.do")
	public String goInsertEgovQustnrTmplatManage(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("qustnrTmplatManageVO") QustnrTmplatManageVO qustnrTmplatManageVO,
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

		String sLocationUrl = "oklms/la/egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageRegist";

		String sCmd = commandMap.get("cmd") == null ? "" : (String)commandMap.get("cmd");
		LOGGER.info("cmd => {}", sCmd);

		//아이디 설정
		qustnrTmplatManageVO.setFrstRegisterId(loginVO.getUniqId());
		qustnrTmplatManageVO.setLastUpdusrId(loginVO.getUniqId());

		return sLocationUrl;
	}

	/**
	 * 설문템플릿를 등록 처리 한다.  / 등록처리
	 * @param multiRequest
	 * @param searchVO
	 * @param qustnrTmplatManageVO
	 * @param model
	 * @return "oklms/la/egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageRegistActor"
	 * @throws Exception
	 */
	@RequestMapping(value = "/la/uss/olp/qtm/insertEgovQustnrTmplatManage.do")
	public String insertEgovQustnrTmplatManage(
			//final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			QustnrTmplatManageVO qustnrTmplatManageVO,
    		ModelMap model)
    throws Exception {
    	// 0. Spring Security 사용자권한 처리
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(!isAuthenticated) {
    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
    		return "redirect:/la/uss/olp/qtm/listEgovQustnrTmplatManage.do";
    	}

		//로그인 객체 선언
		LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		//아이디 설정
		qustnrTmplatManageVO.setFrstRegisterId(loginVO.getUniqId());
		qustnrTmplatManageVO.setLastUpdusrId(loginVO.getUniqId());

		/*String uploadFolder = "";
    	String tmplatManageImage = "";
    	String tmplatManageFile = "";
    	String atchFileId = "";
    	List<FileVO> result = null;
		final Map<String, MultipartFile> files = multiRequest.getFileMap();

	 if (!files.isEmpty()) {
        		for(MultipartFile file : files.values()){
        			LOGGER.info("getName => {}", file.getName() );
        			LOGGER.info("getOriginalFilename => {}", file.getOriginalFilename() );
        	           if(file.getName().equals("qestnrTmplatImage")){
        	           	qustnrTmplatManageVO.setQestnrTmplatImagepathnm( file.getBytes() );
        	         }
		 }
	     }*/
		


    	//log.info("qestnrTmplatImagepathnm =>" + qustnrTmplatManageVO.getQestnrTmplatImagepathnm() );

    	egovQustnrTmplatManageService.insertQustnrTmplatManage(qustnrTmplatManageVO);

    	return "redirect:/la/uss/olp/qtm/listEgovQustnrTmplatManage.do";
	}


}


