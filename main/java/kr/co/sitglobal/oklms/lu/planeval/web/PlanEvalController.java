/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2017. 04. 17.         First Draft
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.lu.planeval.web;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.co.sitglobal.aunuri.service.AunuriLinkService;
import kr.co.sitglobal.aunuri.vo.AunuriLinkEvalPlanNcsVO;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.commbiz.cmmcode.vo.CommonCodeVO;
import kr.co.sitglobal.oklms.la.company.service.CompanyService;
import kr.co.sitglobal.oklms.la.company.vo.CompanyVO;
import kr.co.sitglobal.oklms.lu.member.service.MemberStdService;
import kr.co.sitglobal.oklms.lu.member.vo.ExcelMemberStdVO;
import kr.co.sitglobal.oklms.lu.planeval.service.PlanEvalService;
import kr.co.sitglobal.oklms.lu.planeval.vo.PlanEvalVO;
import kr.co.sitglobal.oklms.lu.subject.service.SubjectService;
import kr.co.sitglobal.oklms.lu.subject.vo.SubjectVO;
import kr.co.sitglobal.oklms.lu.traning.service.TraningNoteSerivce;
import kr.co.sitglobal.oklms.lu.traning.service.TraningService;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.service.Globals;
//import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class PlanEvalController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(PlanEvalController.class);

	@Resource(name = "planEvalService")
	private PlanEvalService planEvalService;

	@Resource(name = "SubjectService")
	private SubjectService subjectService;

	@Resource(name = "companyService")
	private CompanyService companyService;

	@Resource(name = "traningService")
	private TraningService traningService;

	@Resource(name = "traningNoteSerivce")
	private TraningNoteSerivce traningNoteSerivce;

	@Resource(name = "MemberStdService")
	private MemberStdService memberStdService;

	@Resource(name = "aunuriLinkService")
	private AunuriLinkService aunuriLinkService;

	@Resource(name = "atchFileService")
	private AtchFileService atchFileService;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

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
	 * 직무수행능력평가(평가계획 등록) 목록
	 * @param planEvalVO
	 * @return PlanEvalVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/evalPlan/listNcsEvalPlan.do")
	public String listNcsEvalPlan(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmEvalPlan") PlanEvalVO planEvalVO, ModelMap model, HttpServletRequest request) throws Exception {
		/*====================================================================
    	* 초기화 영역
    	======================================================================*/
	    String retMsg = "";
	    String returnUrl = "";
		HttpSession session = request.getSession(); // 로그인 사용자에 교과별-교과목 관련 세션 Key get 처리

		/*====================================================================
    	* 초기값 셋팅 영역
    	====================================================================*/

		/* userType 이 관리자 이외 경우는 진행중인 교과목 세션정보를 젯팅한다. */
		String yyyy = StringUtils.defaultString( (String)session.getAttribute(Globals.YYYY) , "" );
		String term = StringUtils.defaultString( (String)session.getAttribute(Globals.TERM) , "" );
		String subjectCode = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_CODE) , "" );
		String subjectName = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_NAME) , "" );
		String classs = StringUtils.defaultString( (String)session.getAttribute(Globals.CLASS) , "" );
		String subjectTraningType = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_TRANING_TYPE) , "" );
		String subjectType = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_TYPE) , "" );
		String memSeq = StringUtils.defaultString( (String)paramMap.get("memSeq") , "" );
		String memId = StringUtils.defaultString( (String)paramMap.get("memId") , "" );
		String searchKeyword = StringUtils.defaultString( (String)paramMap.get("searchKeyword") , "" );
		String searchKeywordNull = StringUtils.defaultString((String)paramMap.get("searchKeywordNull"),"");
		String searchYyyy = StringUtils.defaultString((String)paramMap.get("searchYyyy"),"");
		String searchTerm = StringUtils.defaultString((String)paramMap.get("searchTerm"),"");
		String searchSubClass = StringUtils.defaultString((String)paramMap.get("searchSubClass"),"");
		String searchSubjectCode = StringUtils.defaultString((String)paramMap.get("searchSubjectCode"),"");
		String searchSubjectTraningType = StringUtils.defaultString((String)paramMap.get("searchSubjectTraningType"),"");
		String traningProcessId = StringUtils.defaultString( (String)paramMap.get("traningProcessId") , "" );
		String tempTraningProcessId = StringUtils.defaultString((String)paramMap.get("tempTraningProcessId"),"");
		String companyId = StringUtils.defaultString( (String)paramMap.get("companyId") , "" );
		String  pageSizeStr = StringUtils.defaultString((String)paramMap.get("pageSize"),"");
		String  pageIndexStr = StringUtils.defaultString((String)paramMap.get("pageIndex"),"");
		String  tabPageGubun = StringUtils.defaultString((String)paramMap.get("tabPageGubun"),"");
		String  evDivCd = StringUtils.defaultString((String)paramMap.get("evDivCd"),"");

		String returnMsg = "POPUP_LIST_FAIL";
		int cnt = 0;
		CompanyVO companyVO = new CompanyVO();
		CommonCodeVO codeVO = new CommonCodeVO();

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

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

		AunuriLinkEvalPlanNcsVO evalPlanNcsVO = new AunuriLinkEvalPlanNcsVO();

		/* userType 이 관리자 이외 경우는 진행중인 교과목 세션정보가 있는지 비교하여
		   null 일경우 마이페이지로 포워딩한다.
		*/
		if(!"CCM".equals(planEvalVO.getSessionMemType())){ //HRD전담자일떼
			if( StringUtils.isBlank( yyyy )){
				retMsg = "Left 메뉴에서 진행중인과정에 교과목 하나를 선택하세요.";
				retMsg = URLEncoder.encode( retMsg ,"UTF-8");
				logger.debug("#### retMsg=" + retMsg );
				return "redirect:/lu/main/lmsUserMainPage.do?retMsgEncode="+ retMsg;
			}
		}

		if("COT".equals(planEvalVO.getSessionMemType())){ //기업현장교사일떼.

			evalPlanNcsVO.setYyyy(yyyy);
			evalPlanNcsVO.setTerm("10"+term);
			evalPlanNcsVO.setSubjectCode(subjectCode);
			evalPlanNcsVO.setSubClass(classs);

			planEvalVO.setYyyy(yyyy);
			planEvalVO.setTerm(term);
			planEvalVO.setSubjectCode(subjectCode);
			planEvalVO.setSubClass(classs);

			List<AunuriLinkEvalPlanNcsVO> listAunuriWeekNcsEvalPlanCode = aunuriLinkService.listAunuriWeekNcsEvalPlanCode(evalPlanNcsVO);
			List<AunuriLinkEvalPlanNcsVO> listAunuriWeekNcsEvalPlanNote = aunuriLinkService.listAunuriWeekNcsEvalPlanNote(evalPlanNcsVO);
			List<AunuriLinkEvalPlanNcsVO> listAunuriWeekNcsEvalWay = aunuriLinkService.listAunuriWeekNcsEvalWay(evalPlanNcsVO);

			List<PlanEvalVO> listNcsEvalPlanInfo = planEvalService.listNcsEvalPlanInfo(planEvalVO);
			List<PlanEvalVO> listNcsEvalPlanElemInfo = planEvalService.listNcsEvalPlanElemInfo(planEvalVO);

			model.addAttribute("evalPlanCode", listAunuriWeekNcsEvalPlanCode);
			model.addAttribute("resultList1", listAunuriWeekNcsEvalPlanNote);
			model.addAttribute("resultList2", listAunuriWeekNcsEvalWay);
			model.addAttribute("resultListCnt1", listAunuriWeekNcsEvalPlanNote.size());
			model.addAttribute("resultListCnt2", listAunuriWeekNcsEvalWay.size());

			model.addAttribute("resultNcsEvalPlanInfoList", listNcsEvalPlanInfo);
			model.addAttribute("resultNcsEvalPlanElemInfoList", listNcsEvalPlanElemInfo);
			model.addAttribute("resultNcsEvalPlanInfoListCnt", listNcsEvalPlanInfo.size());

			returnUrl = "oklms/lu/planeval/planEvalList"; // View호출 (기업현장교사)
		}else if("CCM".equals(planEvalVO.getSessionMemType())){ //HRD전담자일떼

			if(!searchKeyword.equals("") && !"noDivPopup".equals(tempTraningProcessId)){
				companyVO.setSearchCompanyName(searchKeyword);
				returnMsg = "POPUP_LIST_SUCCESS";
				model.addAttribute("returnMsg", returnMsg);
			}

			if(!searchKeywordNull.equals("") && !"noDivPopup".equals(tempTraningProcessId)){
				companyVO.setSearchCompanyName(null);
				returnMsg = "POPUP_LIST_SUCCESS";
				model.addAttribute("returnMsg", returnMsg);
			}

			model.addAttribute("searchKeyword", searchKeyword);

			if(!"".equals(pageIndexStr) && !"noDivPopup".equals(tempTraningProcessId)){
				companyVO.setPageIndex(Integer.parseInt(pageIndexStr));
				returnMsg = "POPUP_LIST_SUCCESS";
				model.addAttribute("returnMsg", returnMsg);
			}

	 		companyVO.setCompanyId(null);
			companyVO.setTraningProcessId(null);
			List<CompanyVO> listCompanyTraningProcessSearch = companyService.listCompanyTraningProcessSearch(companyVO);
			Integer pageSize = companyVO.getPageSize();
			Integer page = 1;
			if("".equals(pageIndexStr)){
				page = companyVO.getPageIndex();
			}else{
				page = Integer.parseInt(pageIndexStr);
			}

			int totalCnt = 0;
			if( 0 < listCompanyTraningProcessSearch.size() ){
				totalCnt = Integer.parseInt( listCompanyTraningProcessSearch.get(0).getTotalCount() );
			}
			int totalPage = (int) Math.ceil(totalCnt / pageSize);

	        model.addAttribute("pageSize", pageSize);
	        model.addAttribute("totalCount", totalCnt);
	        model.addAttribute("pageIndex", page);

	        PaginationInfo paginationInfo = new PaginationInfo();

	        paginationInfo.setCurrentPageNo(companyVO.getPageIndex());
	        paginationInfo.setRecordCountPerPage(pageSize);
	        paginationInfo.setPageSize(companyVO.getPageSize());
	        paginationInfo.setTotalRecordCount(totalCnt);

	        model.addAttribute("resultCnt", totalCnt );
	        model.addAttribute("paginationInfo", paginationInfo);

			model.addAttribute("resultSearchList", listCompanyTraningProcessSearch); //기업체 훈련과정 검색목록

			//기업체검색 팝업화면에서 훈련과정을 선택했을경우
			if(!"".equals(traningProcessId)){
				companyVO.setCompanyId(companyId);
				companyVO.setTraningProcessId(traningProcessId);
				List<CompanyVO> listCompanyTraningProcess = companyService.listCompanyTraningProcess(companyVO);
				model.addAttribute("resultList", listCompanyTraningProcess); //기업체 훈련과정 검색목록

				codeVO.setCompanyId(companyId);
				codeVO.setTraningProcessId(traningProcessId);

				List<CommonCodeVO> yearSubjCodeList = commonCodeService.selectYearCodeList(codeVO); //학년도-코드
				List<CommonCodeVO> termSubjCodeList = commonCodeService.selectTermCodeList(codeVO); //학기-코드
				List<CommonCodeVO> selectSubjectNameCodeList = commonCodeService.selectSubjectNameCodeList(codeVO); //개설강좌-코드
				List<CommonCodeVO> classSubjCodeList = commonCodeService.selectClassCodeList(codeVO); //분반-코드

				model.addAttribute("searchYyyyCodeId", yearSubjCodeList.size());
				model.addAttribute("yearSubjCode", yearSubjCodeList);
				model.addAttribute("termSubjCode", termSubjCodeList);
				model.addAttribute("subjNameSubjCode", selectSubjectNameCodeList);
				model.addAttribute("classSubjCode", classSubjCodeList);

				if("01".equals(tabPageGubun)){
					if(!"".equals(searchYyyy)){
						planEvalVO.setYyyy(searchYyyy);
						planEvalVO.setTerm(searchTerm);
						planEvalVO.setSubClass(searchSubClass);
						planEvalVO.setSubjectCode(searchSubjectCode);
						planEvalVO.setSubjectTraningType(searchSubjectTraningType);
					}

					List<PlanEvalVO> listNcsEvalPlanSubjectRebort = planEvalService.listNcsEvalPlanSubjectRebort(planEvalVO);

					model.addAttribute("resultSubjectRebortList", listNcsEvalPlanSubjectRebort);
					model.addAttribute("cnt", listNcsEvalPlanSubjectRebort.size());

					returnUrl = "oklms/lu/planeval/planEvalSubjectCcmReport";
				} else if("02".equals(tabPageGubun)){
					if(!"".equals(searchYyyy)){
						planEvalVO.setYyyy(searchYyyy);
						planEvalVO.setTerm(searchTerm);
						planEvalVO.setSubClass(searchSubClass);
						planEvalVO.setSubjectCode(searchSubjectCode);
						planEvalVO.setSubjectTraningType(searchSubjectTraningType);
					}

					List<PlanEvalVO> listNcsEvalPlanMemberRebort = planEvalService.listNcsEvalPlanMemberRebort(planEvalVO);

					model.addAttribute("resultMemberRebortList", listNcsEvalPlanMemberRebort);
					model.addAttribute("cnt", listNcsEvalPlanMemberRebort.size());

					returnUrl = "oklms/lu/planeval/planEvalMemberCcmReport";
				} else if("".equals(tabPageGubun)){
					returnUrl = "oklms/lu/planeval/planEvalSubjectCcmReport";
				}

			} else {
				if("".equals(tabPageGubun)){
					returnUrl = "oklms/lu/planeval/planEvalSubjectCcmReport";
				}
				if("01".equals(tabPageGubun)){
					returnUrl = "oklms/lu/planeval/planEvalSubjectCcmReport";
				}
				if("02".equals(tabPageGubun)){
					returnUrl = "oklms/lu/planeval/planEvalMemberCcmReport";
				}
			}
		}else if("PRT".equals(planEvalVO.getSessionMemType())){ //담당교사일떼

			planEvalVO.setYyyy(yyyy);
			planEvalVO.setTerm(term);
			planEvalVO.setSubjectCode(subjectCode);
			planEvalVO.setSubClass(classs);

			String tmpTerm = "";
			tmpTerm = "10"+planEvalVO.getTerm();
			planEvalVO.setTmpTerm(tmpTerm);

			if("OFF".equals(subjectTraningType)){ //OFF-JT 과정일떼
				if("".equals(tabPageGubun)){ //개설교과별 OZ 레포트화면 호출
					returnUrl = "oklms/lu/planeval/planEvalOffJtSubjectPrtReport";
				}
				if("01".equals(tabPageGubun)){ //개설교과별 OZ 레포트화면 호출
					returnUrl = "oklms/lu/planeval/planEvalOffJtSubjectPrtReport";
				}
				if("02".equals(tabPageGubun)){ //학습자별 OZ 레포트화면 호출
					PlanEvalVO readVO2 = new PlanEvalVO();

					//학습근로자 목록
					List<PlanEvalVO> listMember = planEvalService.listFistEvalPlanLessonMember(planEvalVO);
					model.addAttribute("resultMemberList", listMember);

					if("".equals(memSeq) && "".equals(searchKeyword)){
						readVO2 = planEvalService.getLessionMember(planEvalVO);

						model.addAttribute("memName", readVO2.getMemName());
						model.addAttribute("memId", readVO2.getMemId());
						model.addAttribute("memSeq", readVO2.getMemSeq());
					} else if(!"".equals(memSeq) && "".equals(searchKeyword)){
						planEvalVO.setMemSeq(memSeq);
						readVO2 = planEvalService.getLessionMember(planEvalVO);
						if(readVO2 != null){
							model.addAttribute("memName", readVO2.getMemName());
							model.addAttribute("memId", readVO2.getMemId());
							model.addAttribute("memSeq", readVO2.getMemSeq());
						} else {
							model.addAttribute("memName", "");
							model.addAttribute("memId", "");
							model.addAttribute("memSeq", "");
						}
					} else if("".equals(memSeq) && !"".equals(searchKeyword)){
						if(listMember.size() > 0){
							model.addAttribute("memName", listMember.get(0).getMemName());
							model.addAttribute("memId", listMember.get(0).getMemId());
							model.addAttribute("memSeq", listMember.get(0).getMemSeq());
						}else{
							model.addAttribute("memName", "");
							model.addAttribute("memId", "");
							model.addAttribute("memSeq", "");
						}
					}

					returnUrl = "oklms/lu/planeval/planEvalOffJtMemberPrtReport";
				}
			}else{ //OJT 과정일떼
				SubjectVO subjectVO = new SubjectVO();
				loginInfo.putSessionToVo(subjectVO); // session의 정보를 VO에 추가.

				// 교수자 and OJT교과 and 분반이 안넘어 올 경우 분반 세팅
				subjectVO.setYyyy(yyyy);
		        subjectVO.setTerm(term);
		        subjectVO.setSubjectCode(subjectCode);

		        if("".equals(searchSubClass)){
		        	 classs = subjectService.getMinSubjectClass(subjectVO);
		        	 logger.debug("OJT init class : " + classs );
		        }

				if("HSKILL".equals(subjectType)){ //고숙련과정일떼 _ 학습자별
					List<SubjectVO> listOjtClassStdName = subjectService.listOjtClassStdName(subjectVO);
	    			model.put("listOjtClassName", listOjtClassStdName);

	    			if("".equals(searchSubClass)){
	    				searchSubClass = classs;
	    				planEvalVO.setSubClass(searchSubClass);
	    			}

	    			if("".equals(memSeq)){
	    				memSeq = listOjtClassStdName.get(0).getStdSeq();
	    				planEvalVO.setMemSeq(memSeq);
	    			}

	    			LOGGER.debug("#### searchSubClass ==> "+searchSubClass);
					LOGGER.debug("#### memSeq ==> "+memSeq);

					model.put("memSeq", memSeq);
					model.put("searchSubClass", searchSubClass);

					subjectVO.setSubjectClass(classs);

					//기업ID_분반 선택시 기업현장교사가 등록한 직무수행등력평가 조회
					if((!"".equals(searchSubClass) && !"".equals(memSeq)) ){
						evalPlanNcsVO.setYyyy(yyyy);
						evalPlanNcsVO.setTerm("10"+term);
						evalPlanNcsVO.setSubjectCode(subjectCode);
						evalPlanNcsVO.setSubClass(searchSubClass);

						planEvalVO.setYyyy(yyyy);
						planEvalVO.setTerm(term);
						planEvalVO.setSubjectCode(subjectCode);
						planEvalVO.setSubClass(searchSubClass);

				        subjectVO.setYyyy(planEvalVO.getYyyy());
				        subjectVO.setTerm(planEvalVO.getTerm());
				        subjectVO.setSubjectCode(planEvalVO.getSubjectCode());
				        subjectVO.setSubjectClass(searchSubClass);
				        subjectVO.setStdSeq(memSeq);
				        subjectVO = subjectService.getSubjectInfo(subjectVO);

				        model.addAttribute("subjectVO", subjectVO);

				        if(!"".equals(evDivCd)){
				        	planEvalVO.setEvDivCd(null);
				        }
				        planEvalVO.setSubjectType(subjectType);
						List<PlanEvalVO> listNcsEvalPlanInfo = planEvalService.listNcsEvalPlanInfo(planEvalVO);
						List<PlanEvalVO> listNcsEvalPlanElemInfo = planEvalService.listNcsEvalPlanElemInfo(planEvalVO);

						model.addAttribute("resultNcsEvalPlanInfoList", listNcsEvalPlanInfo);
						model.addAttribute("resultNcsEvalPlanElemInfoList", listNcsEvalPlanElemInfo);
						model.addAttribute("resultNcsEvalPlanInfoListCnt", listNcsEvalPlanElemInfo.size());

						PlanEvalVO readVO1 = new PlanEvalVO();

						if(!"".equals(evDivCd)){
							planEvalVO.setEvDivCd(evDivCd);

							readVO1 = planEvalService.getEvDivCd(planEvalVO);

							if(readVO1 != null){
								if(!"".equals(readVO1.getEvDivCd())){
									planEvalVO.setEvDivCd(readVO1.getEvDivCd());

									model.addAttribute("evDivCd", readVO1.getEvDivCd());
									model.addAttribute("evDivName", readVO1.getEvDivName());
								}
							} else {
								model.addAttribute("evDivCd", "");
								model.addAttribute("evDivName", "");
							}

							List<AunuriLinkEvalPlanNcsVO> listAunuriWeekNcsEvalPlanNote = aunuriLinkService.listAunuriWeekNcsEvalPlanNote(evalPlanNcsVO);
							List<AunuriLinkEvalPlanNcsVO> listAunuriWeekNcsEvalWay = aunuriLinkService.listAunuriWeekNcsEvalWay(evalPlanNcsVO);

							model.addAttribute("resultList1", listAunuriWeekNcsEvalPlanNote);
							model.addAttribute("resultList2", listAunuriWeekNcsEvalWay);
							model.addAttribute("resultListCnt1", listAunuriWeekNcsEvalPlanNote.size());
							model.addAttribute("resultListCnt2", listAunuriWeekNcsEvalWay.size());
							model.addAttribute("flag", "Y");
						} else {
							model.addAttribute("flag", "N");
						}
					}

				} else { //일반과정일떼 _ 기업별_분반

		        	List<SubjectVO> listOjtClassName = subjectService.listOjtClassName(subjectVO);
	    			model.put("listOjtClassName", listOjtClassName);

	    			if("".equals(searchSubClass)){
	    				searchSubClass = classs;
	    				planEvalVO.setSubClass(searchSubClass);
	    			}

	    			if("".equals(companyId)){
	    				companyId = listOjtClassName.get(0).getCompanyId();
	    				planEvalVO.setCompanyId(companyId);
	    			}

					LOGGER.debug("#### searchSubClass ==> "+searchSubClass);
					LOGGER.debug("#### companyId ==> "+companyId);

					subjectVO.setSubjectClass(classs);

					//기업ID_분반 선택시 기업현장교사가 등록한 직무수행등력평가 조회
					if((!"".equals(searchSubClass) && !"".equals(companyId)) ){
						evalPlanNcsVO.setYyyy(yyyy);
						evalPlanNcsVO.setTerm("10"+term);
						evalPlanNcsVO.setSubjectCode(subjectCode);
						evalPlanNcsVO.setSubClass(searchSubClass);

						planEvalVO.setYyyy(yyyy);
						planEvalVO.setTerm(term);
						planEvalVO.setSubjectCode(subjectCode);
						planEvalVO.setSubClass(searchSubClass);

				        subjectVO.setYyyy(planEvalVO.getYyyy());
				        subjectVO.setTerm(planEvalVO.getTerm());
				        subjectVO.setSubjectCode(planEvalVO.getSubjectCode());
				        subjectVO.setSubjectClass(searchSubClass);
				        subjectVO.setCompanyId(companyId);
				        subjectVO.setPlanEvalOjtPrtYn("Y");
				        subjectVO = subjectService.getSubjectInfo(subjectVO);

				        model.addAttribute("subjectVO", subjectVO);

				        if(!"".equals(evDivCd)){
				        	planEvalVO.setEvDivCd(null);
				        }
						List<PlanEvalVO> listNcsEvalPlanInfo = planEvalService.listNcsEvalPlanInfo(planEvalVO);
						List<PlanEvalVO> listNcsEvalPlanElemInfo = planEvalService.listNcsEvalPlanElemInfo(planEvalVO);

						model.addAttribute("resultNcsEvalPlanInfoList", listNcsEvalPlanInfo);
						model.addAttribute("resultNcsEvalPlanElemInfoList", listNcsEvalPlanElemInfo);
						model.addAttribute("resultNcsEvalPlanInfoListCnt", listNcsEvalPlanElemInfo.size());

						PlanEvalVO readVO1 = new PlanEvalVO();

						if(!"".equals(evDivCd)){
							planEvalVO.setEvDivCd(evDivCd);

							readVO1 = planEvalService.getEvDivCd(planEvalVO);

							if(readVO1 != null){
								if(!"".equals(readVO1.getEvDivCd())){
									planEvalVO.setEvDivCd(readVO1.getEvDivCd());

									model.addAttribute("evDivCd", readVO1.getEvDivCd());
									model.addAttribute("evDivName", readVO1.getEvDivName());
								}
							} else {
								model.addAttribute("evDivCd", "");
								model.addAttribute("evDivName", "");
							}

							List<AunuriLinkEvalPlanNcsVO> listAunuriWeekNcsEvalPlanNote = aunuriLinkService.listAunuriWeekNcsEvalPlanNote(evalPlanNcsVO);
							List<AunuriLinkEvalPlanNcsVO> listAunuriWeekNcsEvalWay = aunuriLinkService.listAunuriWeekNcsEvalWay(evalPlanNcsVO);

							model.addAttribute("resultList1", listAunuriWeekNcsEvalPlanNote);
							model.addAttribute("resultList2", listAunuriWeekNcsEvalWay);
							model.addAttribute("resultListCnt1", listAunuriWeekNcsEvalPlanNote.size());
							model.addAttribute("resultListCnt2", listAunuriWeekNcsEvalWay.size());
						}
					}
				}

				model.addAttribute("subjectType", subjectType);

				returnUrl = "oklms/lu/planeval/planEvalOjtPrtList"; // View호출 (담당교사 OJT 화면 호출)
			}
		} else if("STD".equals(planEvalVO.getSessionMemType())){ //학스자일떼
			if("HSKILL".equals(subjectType)){ //고숙련과정일떼
				evalPlanNcsVO.setYyyy(yyyy);
				evalPlanNcsVO.setTerm("10"+term);
				evalPlanNcsVO.setSubjectCode(subjectCode);
				evalPlanNcsVO.setSubClass(classs);

				planEvalVO.setYyyy(yyyy);
				planEvalVO.setTerm(term);
				planEvalVO.setSubjectCode(subjectCode);
				planEvalVO.setSubClass(classs);

				//테스트 데이타
				//evalPlanNcsVO.setYyyy("2017");
				//evalPlanNcsVO.setTerm("101");
				//evalPlanNcsVO.setSubjectCode("SME915");
				//evalPlanNcsVO.setSubjectCode("MAE915");
				//evalPlanNcsVO.setSubClass("01");

				//planEvalVO.setYyyy("2017");
				//planEvalVO.setTerm("1");
				//planEvalVO.setSubjectCode("SME915");
				//planEvalVO.setSubjectCode("MAE915");
				//planEvalVO.setSubClass("01");

				List<AunuriLinkEvalPlanNcsVO> listAunuriWeekNcsEvalPlanCode = aunuriLinkService.listAunuriWeekNcsEvalPlanCode(evalPlanNcsVO);
				List<AunuriLinkEvalPlanNcsVO> listAunuriWeekNcsEvalPlanNote = aunuriLinkService.listAunuriWeekNcsEvalPlanNote(evalPlanNcsVO);
				List<AunuriLinkEvalPlanNcsVO> listAunuriWeekNcsEvalWay = aunuriLinkService.listAunuriWeekNcsEvalWay(evalPlanNcsVO);

				List<PlanEvalVO> listNcsEvalPlanInfo = planEvalService.listNcsEvalPlanInfo(planEvalVO);
				List<PlanEvalVO> listNcsEvalPlanElemInfo = planEvalService.listNcsEvalPlanElemInfo(planEvalVO);

				model.addAttribute("evalPlanCode", listAunuriWeekNcsEvalPlanCode);
				model.addAttribute("resultList1", listAunuriWeekNcsEvalPlanNote);
				model.addAttribute("resultList2", listAunuriWeekNcsEvalWay);
				model.addAttribute("resultListCnt1", listAunuriWeekNcsEvalPlanNote.size());
				model.addAttribute("resultListCnt2", listAunuriWeekNcsEvalWay.size());

				model.addAttribute("resultNcsEvalPlanInfoList", listNcsEvalPlanInfo);
				model.addAttribute("resultNcsEvalPlanElemInfoList", listNcsEvalPlanElemInfo);
				model.addAttribute("resultNcsEvalPlanInfoListCnt", listNcsEvalPlanElemInfo.size());

				returnUrl = "oklms/lu/planeval/planEvalStdList"; // View호출 (기업현장교사)
			} else { //일반과정일떼
				model.addAttribute("memName", planEvalVO.getSessionMemName());
				model.addAttribute("memId", planEvalVO.getSessionMemId());
				model.addAttribute("memSeq", planEvalVO.getSessionMemSeq());

				returnUrl = "oklms/lu/planeval/planEvalStdInfoReport"; // View호출 (학습근로자)
			}
		}

		model.addAttribute("planEvalVO", planEvalVO); //파라메터정보

		return returnUrl;
	}

	/**
	 * 직무수행능력평가(평가계획 수정-기업현장교사, 학습근로자) 목록
	 * @param planEvalVO
	 * @return PlanEvalVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/evalPlan/goUpdateNcsEvalPlan.do")
	public String goUpdateNcsEvalPlan(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmEvalPlan") PlanEvalVO planEvalVO, ModelMap model, HttpServletRequest request) throws Exception {
		/*====================================================================
    	* 초기화 영역
    	======================================================================*/
	    String retMsg = "";
	    String returnUrl = "";
		HttpSession session = request.getSession(); // 로그인 사용자에 교과별-교과목 관련 세션 Key get 처리

		/*====================================================================
    	* 초기값 셋팅 영역
    	====================================================================*/

		/* userType 이 관리자 이외 경우는 진행중인 교과목 세션정보를 젯팅한다. */
		String yyyy = StringUtils.defaultString( (String)session.getAttribute(Globals.YYYY) , "" );
		String term = StringUtils.defaultString( (String)session.getAttribute(Globals.TERM) , "" );
		String subjectCode = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_CODE) , "" );
		String subjectName = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_NAME) , "" );
		String classs = StringUtils.defaultString( (String)session.getAttribute(Globals.CLASS) , "" );
		String memSeq = StringUtils.defaultString( (String)paramMap.get("memSeq") , "" );
		String evDivCd = StringUtils.defaultString( (String)paramMap.get("evDivCd") , "" );

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		/*====================================================================
    	* 업무화면에서 사용하는 List Vo 셋팅 영역
    	====================================================================*/
		logger.debug("################################# ");
		logger.debug("#### yyyy : " + yyyy );
		logger.debug("#### term : " + term );
		logger.debug("#### subjectCode : " + subjectCode );
		logger.debug("#### subjectName : " + subjectName );
		logger.debug("#### classs : " + classs );
		logger.debug("#### evDivCd : " + evDivCd );
		logger.debug("#### memSeq : " + memSeq );
		logger.debug("################################# ");

		/* userType 이 관리자 이외 경우는 진행중인 교과목 세션정보가 있는지 비교하여
		   null 일경우 마이페이지로 포워딩한다.
		*/
		if("COT".equals(planEvalVO.getSessionMemType())){ //기업현장교사일떼.
			AunuriLinkEvalPlanNcsVO evalPlanNcsVO = new AunuriLinkEvalPlanNcsVO();

			String tmpTerm = "10";
			term = tmpTerm+planEvalVO.getTerm();

			evalPlanNcsVO.setYyyy(yyyy);
			evalPlanNcsVO.setTerm("10"+term);
			evalPlanNcsVO.setSubjectCode(subjectCode);
			evalPlanNcsVO.setSubClass(classs);

			planEvalVO.setYyyy(yyyy);
			planEvalVO.setTerm(term);
			planEvalVO.setSubjectCode(subjectCode);
			planEvalVO.setSubClass(classs);
			planEvalVO.setEvDivCd(evDivCd);

			//테스트 데이타
			//evalPlanNcsVO.setYyyy("2017");
			//evalPlanNcsVO.setTerm("101");
			//evalPlanNcsVO.setSubjectCode("SME915");
			//evalPlanNcsVO.setSubjectCode("MAE915");
			//evalPlanNcsVO.setSubClass("01");

			//planEvalVO.setYyyy("2017");
			//planEvalVO.setTerm("1");
			//planEvalVO.setSubjectCode("SME915");
			//planEvalVO.setSubjectCode("MAE915");
			//planEvalVO.setSubClass("01");

			List<AunuriLinkEvalPlanNcsVO> listAunuriWeekNcsEvalPlanCode = aunuriLinkService.listAunuriWeekNcsEvalPlanCode(evalPlanNcsVO);
			List<AunuriLinkEvalPlanNcsVO> listAunuriWeekNcsEvalPlanNote = aunuriLinkService.listAunuriWeekNcsEvalPlanNote(evalPlanNcsVO);
			List<AunuriLinkEvalPlanNcsVO> listAunuriWeekNcsEvalWay = aunuriLinkService.listAunuriWeekNcsEvalWay(evalPlanNcsVO);

			List<PlanEvalVO> listNcsEvalPlanInfo = planEvalService.listNcsEvalPlanInfo(planEvalVO);
			List<PlanEvalVO> listNcsEvalPlanElemInfo = planEvalService.listNcsEvalPlanElemInfo(planEvalVO);

			model.addAttribute("evalPlanCode", listAunuriWeekNcsEvalPlanCode);
			model.addAttribute("resultList1", listAunuriWeekNcsEvalPlanNote);
			model.addAttribute("resultList2", listAunuriWeekNcsEvalWay);
			model.addAttribute("resultListCnt1", listAunuriWeekNcsEvalPlanNote.size());
			model.addAttribute("resultListCnt2", listAunuriWeekNcsEvalWay.size());

			model.addAttribute("resultNcsEvalPlanInfoList", listNcsEvalPlanInfo);
			model.addAttribute("resultNcsEvalPlanElemInfoList", listNcsEvalPlanElemInfo);

			returnUrl = "oklms/lu/planeval/planEvalListUpdt"; // View호출 (기업현장교사)
		}

		model.addAttribute("planEvalVO", planEvalVO); //파라메터정보

		return returnUrl;
	}

	/**
	 * 직무수행능력평가(1차평가 등록) 목록
	 * @param planEvalVO
	 * @return PlanEvalVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/evalPlan/listNcsEvalPlanFirst.do")
	public String listNcsEvalPlanFirst(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmEvalPlan") PlanEvalVO planEvalVO, ModelMap model, HttpServletRequest request) throws Exception {
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		HttpSession session = request.getSession(); // 로그인 사용자에 교과별-교과목 관련 세션 Key get 처리
		String subjectType = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_TYPE) , "" );
		String classs = StringUtils.defaultString( (String)session.getAttribute(Globals.CLASS) , "" );

		String memSeq = StringUtils.defaultString( (String)paramMap.get("memSeq") , "" );
		String memId = StringUtils.defaultString( (String)paramMap.get("memId") , "" );
		String cnt = StringUtils.defaultString( (String)paramMap.get("cnt") , "" );
		String evDivCd = StringUtils.defaultString( (String)paramMap.get("evDivCd") , "" );
		String searchSubClass = StringUtils.defaultString((String)paramMap.get("searchSubClass"),"");
		String companyId = StringUtils.defaultString( (String)paramMap.get("companyId") , "" );
		String returnUrl = "";
		PlanEvalVO readVO1 = new PlanEvalVO();
		PlanEvalVO readVO2 = new PlanEvalVO();
		List<PlanEvalVO> listRowNumber = null;
		List<PlanEvalVO> listRowspan = null;
		List<PlanEvalVO> listFirstElemId = null;
		List<PlanEvalVO> listFirstInfo = null;
		List<PlanEvalVO> listFirstCtrlSet = null;
		List<PlanEvalVO> listNcsEvalPlanInfo = null;
		List<PlanEvalVO> listNcsEvalPlanElemInfo = null;

		model.addAttribute("cnt", cnt);

		//1.평가계획 목록
		if("STD".equals(planEvalVO.getSessionMemType())){
			planEvalVO.setEvDivCd(null);
			planEvalVO.setMemSeq(planEvalVO.getSessionMemSeq());

			listNcsEvalPlanInfo = planEvalService.listNcsEvalPlanInfo(planEvalVO);
			listNcsEvalPlanElemInfo = planEvalService.listNcsEvalPlanElemInfo(planEvalVO);

			model.addAttribute("resultNcsEvalPlanInfoList", listNcsEvalPlanInfo);
			model.addAttribute("resultNcsEvalPlanElemInfoList", listNcsEvalPlanElemInfo);

			if(!"".equals(evDivCd)){
				planEvalVO.setEvDivCd(evDivCd);
			}

			readVO1 = planEvalService.getEvDivCd(planEvalVO);

			if(!"".equals(readVO1.getEvDivCd())){
				planEvalVO.setEvDivCd(readVO1.getEvDivCd());
			}

			System.out.println("evDivCd :"+readVO1.getEvDivCd());
			System.out.println("evDivName :"+readVO1.getEvDivName());

			model.addAttribute("evDivCd", readVO1.getEvDivCd());
			model.addAttribute("evDivName", readVO1.getEvDivName());

			//3.평가계획등록에 따른 학습자별 취득점수 목록
			//1차평가 목록 rowNumber
			listRowNumber = planEvalService.listNcsEvalPlanFirstRowNumber(planEvalVO);

			//1차평가 목록 rowspan
			listRowspan = planEvalService.listNcsEvalPlanFirstRowspan(planEvalVO);

			//1차평가 중복제거 NCS요소

			listFirstElemId = planEvalService.listNcsEvalPlanFirstElemId(planEvalVO);

			//1차평가 수행준거 목록
			listFirstInfo = planEvalService.listNcsEvalPlanFirstInfo(planEvalVO);

			//1차평가 평가방법 목록
			listFirstCtrlSet = planEvalService.listNcsEvalPlanFirstCtrlSet(planEvalVO);

			model.addAttribute("resultRowNumberList", listRowNumber);
			//model.addAttribute("resultStdScoreRowNumberList", listStdScoreRowNumber);
			model.addAttribute("resultRowSpanList", listRowspan);
			model.addAttribute("resultFirstElemIdList", listFirstElemId);
			model.addAttribute("resultFirstInfoList", listFirstInfo);
			model.addAttribute("resultFirstCtrlSetList", listFirstCtrlSet);

		} else if("COT".equals(planEvalVO.getSessionMemType())){
			planEvalVO.setEvDivCd(null);

			listNcsEvalPlanInfo = planEvalService.listNcsEvalPlanInfo(planEvalVO);
			listNcsEvalPlanElemInfo = planEvalService.listNcsEvalPlanElemInfo(planEvalVO);

			model.addAttribute("resultNcsEvalPlanInfoList", listNcsEvalPlanInfo);
			model.addAttribute("resultNcsEvalPlanElemInfoList", listNcsEvalPlanElemInfo);

			//2.학습근로자 목록
			planEvalVO.setMemSeq(null);
			List<PlanEvalVO> listMember = planEvalService.listFistEvalPlanLessonMember(planEvalVO);
			model.addAttribute("resultMemberList", listMember);

			if("".equals(memSeq)){
				if(!"".equals(evDivCd)){
					planEvalVO.setEvDivCd(evDivCd);
				}

				readVO1 = planEvalService.getEvDivCd(planEvalVO);
				if(readVO1 == null){
					planEvalVO.setEvDivCd(null);

					model.addAttribute("evDivCd", "");
					model.addAttribute("evDivName", "");
				} else {
					planEvalVO.setEvDivCd(readVO1.getEvDivCd());

					model.addAttribute("evDivCd", readVO1.getEvDivCd());
					model.addAttribute("evDivName", readVO1.getEvDivName());
				}

				readVO2 = planEvalService.getLessionMember(planEvalVO);
				if(readVO1 == null){
					planEvalVO.setMemSeq(null);

					model.addAttribute("memName", "");
					model.addAttribute("memId", "");
					model.addAttribute("memSeq", "");
				} else {
					planEvalVO.setMemSeq(readVO2.getMemSeq());

					model.addAttribute("memName", readVO2.getMemName());
					model.addAttribute("memId", readVO2.getMemId());
					model.addAttribute("memSeq", readVO2.getMemSeq());
				}
			} else {
				planEvalVO.setMemSeq(memSeq);
				planEvalVO.setEvDivCd(evDivCd);

				readVO1 = planEvalService.getEvDivCd(planEvalVO);

				model.addAttribute("evDivCd", readVO1.getEvDivCd());
				model.addAttribute("evDivName", readVO1.getEvDivName());

				readVO2 = planEvalService.getLessionMember(planEvalVO);
				planEvalVO.setMemSeq(readVO2.getMemSeq());

				model.addAttribute("memName", readVO2.getMemName());
				model.addAttribute("memId", readVO2.getMemId());
				model.addAttribute("memSeq", readVO2.getMemSeq());

			}

			//학습근로자 검색 목록 size
			if(listMember.size() > 0){
				//3.평가계획등록에 따른 학습자별 취득점수 목록
				//1차평가 목록 rowNumber
				listRowNumber = planEvalService.listNcsEvalPlanFirstRowNumber(planEvalVO);

				//1차평가 목록 rowspan
				listRowspan = planEvalService.listNcsEvalPlanFirstRowspan(planEvalVO);

				//1차평가 중복제거 NCS요소
				listFirstElemId = planEvalService.listNcsEvalPlanFirstElemId(planEvalVO);

				//1차평가 수행준거 목록
				listFirstInfo = planEvalService.listNcsEvalPlanFirstInfo(planEvalVO);

				//1차평가 평가방법 목록
				listFirstCtrlSet = planEvalService.listNcsEvalPlanFirstCtrlSet(planEvalVO);

				//1차평가 학습자별 취득점수
				//listStdScoreRowNumber = planEvalService.listNcsEvalPlanFirstStdScoreRowNumber(planEvalVO);

				if("".equals(memSeq)){
					model.addAttribute("memName", listMember.get(0).getMemName());
					model.addAttribute("memId", listMember.get(0).getMemId());
					model.addAttribute("memSeq", listMember.get(0).getMemSeq());
				}
			}

			model.addAttribute("resultRowNumberList", listRowNumber);
			//model.addAttribute("resultStdScoreRowNumberList", listStdScoreRowNumber);
			model.addAttribute("resultRowSpanList", listRowspan);
			model.addAttribute("resultFirstElemIdList", listFirstElemId);
			model.addAttribute("resultFirstInfoList", listFirstInfo);
			model.addAttribute("resultFirstCtrlSetList", listFirstCtrlSet);
		} else {
			SubjectVO subjectVO = new SubjectVO();
			loginInfo.putSessionToVo(subjectVO); // session의 정보를 VO에 추가.

			if("HSKILL".equals(subjectType)){ //고숙련과정일떼 _ 학습자별
				// 교수자 and OJT교과 and 분반이 안넘어 올 경우 분반 세팅
				subjectVO.setYyyy(planEvalVO.getYyyy());
		        subjectVO.setTerm(planEvalVO.getTerm());
		        subjectVO.setSubjectCode(planEvalVO.getSubjectCode());

		        if("".equals(searchSubClass)){
		        	 classs = subjectService.getMinSubjectClass(subjectVO);
		        	 logger.debug("OJT init class : " + classs );
		        }

				 //고숙련과정일떼 _ 학습자별
				List<SubjectVO> listOjtClassStdName = subjectService.listOjtClassStdName(subjectVO);
    			model.put("listOjtClassName", listOjtClassStdName);

    			if("".equals(searchSubClass)){
    				searchSubClass = classs;
    				planEvalVO.setSubClass(searchSubClass);
    			}

    			if("".equals(memSeq)){
    				memSeq = listOjtClassStdName.get(0).getStdSeq();
    				planEvalVO.setMemSeq(memSeq);
    			}

    			LOGGER.debug("#### searchSubClass ==> "+searchSubClass);
				LOGGER.debug("#### memSeq ==> "+memSeq);

				model.put("memSeq", memSeq);
				model.put("searchSubClass", searchSubClass);

				subjectVO.setSubjectClass(classs);

				//기업ID_분반 선택시 기업현장교사가 등록한 직무수행등력평가 조회
				if((!"".equals(searchSubClass) && !"".equals(memSeq)) ){
					planEvalVO.setSubClass(searchSubClass);

					subjectVO.setYyyy(planEvalVO.getYyyy());
			        subjectVO.setTerm(planEvalVO.getTerm());
			        subjectVO.setSubjectCode(planEvalVO.getSubjectCode());
			        subjectVO.setSubjectClass(searchSubClass);
			        subjectVO.setStdSeq(memSeq);
			        subjectVO = subjectService.getSubjectInfo(subjectVO);

			        model.addAttribute("subjectVO", subjectVO);

			        if(!"".equals(evDivCd)){
			        	planEvalVO.setEvDivCd(null);
			        }
			        listNcsEvalPlanInfo = planEvalService.listNcsEvalPlanInfo(planEvalVO);
					listNcsEvalPlanElemInfo = planEvalService.listNcsEvalPlanElemInfo(planEvalVO);

					model.addAttribute("resultNcsEvalPlanInfoList", listNcsEvalPlanInfo);
					model.addAttribute("resultNcsEvalPlanElemInfoList", listNcsEvalPlanElemInfo);

					if(!"".equals(evDivCd)){
						planEvalVO.setEvDivCd(evDivCd);

						readVO1 = planEvalService.getEvDivCd(planEvalVO);

						if(readVO1 != null){
							if(!"".equals(readVO1.getEvDivCd())){
								planEvalVO.setEvDivCd(readVO1.getEvDivCd());

								model.addAttribute("evDivCd", readVO1.getEvDivCd());
								model.addAttribute("evDivName", readVO1.getEvDivName());
							}
						} else {
							model.addAttribute("evDivCd", "");
							model.addAttribute("evDivName", "");
						}

						//3.평가계획등록에 따른 학습자별 취득점수 목록
						//1차평가 목록 rowNumber
						listRowNumber = planEvalService.listNcsEvalPlanFirstRowNumberPrt(planEvalVO);

						//1차평가 목록 rowspan
						listRowspan = planEvalService.listNcsEvalPlanFirstRowspanPrt(planEvalVO);

						//1차평가 중복제거 NCS요소
						listFirstElemId = planEvalService.listNcsEvalPlanFirstElemIdPrt(planEvalVO);

						//1차평가 수행준거 목록
						listFirstInfo = planEvalService.listNcsEvalPlanFirstInfoPrt(planEvalVO);

						//1차평가 평가방법 목록
						listFirstCtrlSet = planEvalService.listNcsEvalPlanFirstCtrlSetPrt(planEvalVO);

						model.addAttribute("resultRowNumberList", listRowNumber);
						//model.addAttribute("resultStdScoreRowNumberList", listStdScoreRowNumber);
						model.addAttribute("resultRowSpanList", listRowspan);
						model.addAttribute("resultFirstElemIdList", listFirstElemId);
						model.addAttribute("resultFirstInfoList", listFirstInfo);
						model.addAttribute("resultFirstCtrlSetList", listFirstCtrlSet);
					}
				}

			} else { //일반과정일떼 _ 기업별_분반

				// 교수자 and OJT교과 and 분반이 안넘어 올 경우 분반 세팅
				subjectVO.setYyyy(planEvalVO.getYyyy());
		        subjectVO.setTerm(planEvalVO.getTerm());
		        subjectVO.setSubjectCode(planEvalVO.getSubjectCode());

		        if("".equals(searchSubClass)){
		        	 classs = subjectService.getMinSubjectClass(subjectVO);
		        	 logger.debug("OJT init class : " + classs );
		        }

	        	List<SubjectVO> listOjtClassName = subjectService.listOjtClassName(subjectVO);
    			model.put("listOjtClassName", listOjtClassName);

				LOGGER.debug("#### searchSubClass ==> "+searchSubClass);
				LOGGER.debug("#### companyId ==> "+companyId);

				//기업ID_분반 선택시 기업현장교사가 등록한 직무수행등력평가 조회
				if(!"".equals(searchSubClass) && !"".equals(companyId) ){
					planEvalVO.setSubClass(searchSubClass);

			        subjectVO.setYyyy(planEvalVO.getYyyy());
			        subjectVO.setTerm(planEvalVO.getTerm());
			        subjectVO.setSubjectCode(planEvalVO.getSubjectCode());
			        subjectVO.setSubjectClass(searchSubClass);
			        subjectVO.setCompanyId(companyId);
			        subjectVO.setPlanEvalOjtPrtYn("Y");
			        subjectVO = subjectService.getSubjectInfo(subjectVO);

			        model.addAttribute("subjectVO", subjectVO);

			        if(!"".equals(evDivCd)){
			        	planEvalVO.setEvDivCd(null);
			        }
			        listNcsEvalPlanInfo = planEvalService.listNcsEvalPlanInfo(planEvalVO);
					listNcsEvalPlanElemInfo = planEvalService.listNcsEvalPlanElemInfo(planEvalVO);

					model.addAttribute("resultNcsEvalPlanInfoList", listNcsEvalPlanInfo);
					model.addAttribute("resultNcsEvalPlanElemInfoList", listNcsEvalPlanElemInfo);

					//2.학습근로자 목록
					planEvalVO.setMemSeq(null);
					List<PlanEvalVO> listMember = planEvalService.listFistEvalPlanLessonMember(planEvalVO);
					model.addAttribute("resultMemberList", listMember);

					if("".equals(memSeq)){
						if(!"".equals(evDivCd)){
							planEvalVO.setEvDivCd(evDivCd);
						}

						readVO1 = planEvalService.getEvDivCd(planEvalVO);
						if(readVO1 == null){
							planEvalVO.setEvDivCd(null);

							model.addAttribute("evDivCd", "");
							model.addAttribute("evDivName", "");
						} else {
							planEvalVO.setEvDivCd(readVO1.getEvDivCd());

							model.addAttribute("evDivCd", readVO1.getEvDivCd());
							model.addAttribute("evDivName", readVO1.getEvDivName());
						}

						readVO2 = planEvalService.getLessionMember(planEvalVO);
						if(readVO1 == null){
							planEvalVO.setMemSeq(null);

							model.addAttribute("memName", "");
							model.addAttribute("memId", "");
							model.addAttribute("memSeq", "");
						} else {
							planEvalVO.setMemSeq(readVO2.getMemSeq());

							model.addAttribute("memName", readVO2.getMemName());
							model.addAttribute("memId", readVO2.getMemId());
							model.addAttribute("memSeq", readVO2.getMemSeq());
						}
					} else {
						planEvalVO.setMemSeq(memSeq);
						planEvalVO.setEvDivCd(evDivCd);

						readVO1 = planEvalService.getEvDivCd(planEvalVO);

						if(readVO1 != null){
							model.addAttribute("evDivCd", readVO1.getEvDivCd());
							model.addAttribute("evDivName", readVO1.getEvDivName());
						} else {
							model.addAttribute("evDivCd", "");
							model.addAttribute("evDivName", "");
						}

						readVO2 = planEvalService.getLessionMember(planEvalVO);

						if(readVO2 != null){
							planEvalVO.setMemSeq(readVO2.getMemSeq());

							model.addAttribute("memName", readVO2.getMemName());
							model.addAttribute("memId", readVO2.getMemId());
							model.addAttribute("memSeq", readVO2.getMemSeq());

						} else {
							planEvalVO.setMemSeq("");

							model.addAttribute("memName", "");
							model.addAttribute("memId", "");
							model.addAttribute("memSeq", "");
						}
					}

					//학습근로자 검색 목록 size
					if(listMember.size() > 0){
						//3.평가계획등록에 따른 학습자별 취득점수 목록
						//1차평가 목록 rowNumber
						listRowNumber = planEvalService.listNcsEvalPlanFirstRowNumberPrt(planEvalVO);

						//1차평가 목록 rowspan
						listRowspan = planEvalService.listNcsEvalPlanFirstRowspanPrt(planEvalVO);

						//1차평가 중복제거 NCS요소
						listFirstElemId = planEvalService.listNcsEvalPlanFirstElemIdPrt(planEvalVO);

						//1차평가 수행준거 목록
						listFirstInfo = planEvalService.listNcsEvalPlanFirstInfoPrt(planEvalVO);

						//1차평가 평가방법 목록
						listFirstCtrlSet = planEvalService.listNcsEvalPlanFirstCtrlSetPrt(planEvalVO);

						//1차평가 학습자별 취득점수
						//listStdScoreRowNumber = planEvalService.listNcsEvalPlanFirstStdScoreRowNumber(planEvalVO);

/*						if("".equals(memSeq)){
							model.addAttribute("memName", listMember.get(0).getMemName());
							model.addAttribute("memId", listMember.get(0).getMemId());
							model.addAttribute("memSeq", listMember.get(0).getMemSeq());
						}*/
					}

					model.addAttribute("resultRowNumberList", listRowNumber);
					//model.addAttribute("resultStdScoreRowNumberList", listStdScoreRowNumber);
					model.addAttribute("resultRowSpanList", listRowspan);
					model.addAttribute("resultFirstElemIdList", listFirstElemId);
					model.addAttribute("resultFirstInfoList", listFirstInfo);
					model.addAttribute("resultFirstCtrlSetList", listFirstCtrlSet);


				}
			}

			model.addAttribute("subjectType", subjectType);
		}


		model.addAttribute("planEvalVO", planEvalVO); //파라메터정보

		if("COT".equals(planEvalVO.getSessionMemType())){
			returnUrl = "oklms/lu/planeval/firstPlanEvalList"; // View호출 (기업현장교사)
		}else if("STD".equals(planEvalVO.getSessionMemType())){
			returnUrl = "oklms/lu/planeval/firstPlanEvalStdList"; // View호출 (학습근로자)
		} else {
			returnUrl = "oklms/lu/planeval/firstPlanEvalOjtPrtList"; // View호출 (담당교수)
		}

		return returnUrl;
	}

	/**
	 * 직무수행능력평가(개설교과별 조회) 레포트
	 * @param planEvalVO
	 * @return PlanEvalVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/evalPlan/listNcsEvalPlanSubject.do")
	public String listNcsEvalPlanSubject(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmEvalPlan") PlanEvalVO planEvalVO, ModelMap model, HttpServletRequest request) throws Exception {

		String returnUrl = "";
		String cnt = StringUtils.defaultString( (String)paramMap.get("cnt") , "" );
		String searchSubClass = StringUtils.defaultString((String)paramMap.get("searchSubClass"),"");
		String companyId = StringUtils.defaultString( (String)paramMap.get("companyId") , "" );
		String searchKeyword = StringUtils.defaultString( (String)paramMap.get("searchKeyword") , "" );

		HttpSession session = request.getSession(); // 로그인 사용자에 교과별-교과목 관련 세션 Key get 처리
		String subjectTraningType = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_TRANING_TYPE) , "" );
		String subjectType = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_TYPE) , "" );
		String yyyy = StringUtils.defaultString( (String)session.getAttribute(Globals.YYYY) , "" );
		String term = StringUtils.defaultString( (String)session.getAttribute(Globals.TERM) , "" );
		String subjectCode = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_CODE) , "" );
		String classs = StringUtils.defaultString( (String)session.getAttribute(Globals.CLASS) , "" );

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		planEvalVO.setTmpTerm("10"+planEvalVO.getTmpTerm());

		model.addAttribute("planEvalVO", planEvalVO); //파라메터정보
		model.addAttribute("cnt", cnt);

		if("COT".equals(planEvalVO.getSessionMemType())){
			returnUrl = "oklms/lu/planeval/planEvalSubjectInfoReport"; // View호출 (기업현장교사)
		}else{
			if("OJT".equals(subjectTraningType)){
				SubjectVO subjectVO = new SubjectVO();
				loginInfo.putSessionToVo(subjectVO); // session의 정보를 VO에 추가.

				//if("HSKILL".equals(subjectType)){ //고숙련과정일떼 _ 학습자별

				//} else { //일반과정일떼 _ 기업별_분반

					// 교수자 and OJT교과 and 분반이 안넘어 올 경우 분반 세팅
					subjectVO.setYyyy(yyyy);
			        subjectVO.setTerm(term);
			        subjectVO.setSubjectCode(subjectCode);
			        subjectVO.setSearchKeyword(searchKeyword);

			        if("".equals(searchSubClass)){
			        	 classs = subjectService.getMinSubjectClass(subjectVO);
			        	 logger.debug("OJT init class : " + classs );
			        }

		        	List<SubjectVO> listOjtClassName = subjectService.listOjtClassName(subjectVO);
	    			model.put("listOjtClassName", listOjtClassName);

					LOGGER.debug("#### searchSubClass ==> "+searchSubClass);
					LOGGER.debug("#### companyId ==> "+companyId);

					//기업ID_분반 선택시 기업현장교사가 등록한 직무수행등력평가 조회
					if(!"".equals(searchSubClass) && !"".equals(companyId) ){

						planEvalVO.setSubClass(searchSubClass);
						planEvalVO.setTmpTerm("10"+planEvalVO.getTerm());

				        subjectVO.setYyyy(planEvalVO.getYyyy());
				        subjectVO.setTerm(planEvalVO.getTerm());
				        subjectVO.setSubjectCode(planEvalVO.getSubjectCode());
				        subjectVO.setSubjectClass(searchSubClass);
				        subjectVO.setCompanyId(companyId);
				        subjectVO.setPlanEvalOjtPrtYn("Y");
				        subjectVO = subjectService.getSubjectInfo(subjectVO);

				        model.addAttribute("subjectVO", subjectVO);
					}
				//}

				model.addAttribute("subjectType", subjectType);

				returnUrl = "oklms/lu/planeval/planEvalSubjectInfoReportPrt"; // View호출 (담당교사 OJT 화면 호출)
			}
		}

		return returnUrl;
	}

	/**
	 * 직무수행능력평가(학습근로자별 조회) 레포트
	 * @param planEvalVO
	 * @return PlanEvalVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/evalPlan/listNcsEvalPlanMember.do")
	public String listNcsEvalPlanMember(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmEvalPlan") PlanEvalVO planEvalVO, ModelMap model, HttpServletRequest request) throws Exception {
		String returnUrl = "";
		String memSeq = StringUtils.defaultString( (String)paramMap.get("memSeq") , "" );
		String memId = StringUtils.defaultString( (String)paramMap.get("memId") , "" );
		String cnt = StringUtils.defaultString( (String)paramMap.get("cnt") , "" );
		String searchSubClass = StringUtils.defaultString((String)paramMap.get("searchSubClass"),"");
		String companyId = StringUtils.defaultString( (String)paramMap.get("companyId") , "" );
		String searchKeyword = StringUtils.defaultString( (String)paramMap.get("searchKeyword") , "" );

		HttpSession session = request.getSession(); // 로그인 사용자에 교과별-교과목 관련 세션 Key get 처리
		String subjectTraningType = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_TRANING_TYPE) , "" );
		String subjectType = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_TYPE) , "" );
		String yyyy = StringUtils.defaultString( (String)session.getAttribute(Globals.YYYY) , "" );
		String term = StringUtils.defaultString( (String)session.getAttribute(Globals.TERM) , "" );
		String subjectCode = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_CODE) , "" );
		String classs = StringUtils.defaultString( (String)session.getAttribute(Globals.CLASS) , "" );

		PlanEvalVO readVO2 = new PlanEvalVO();

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		String tmpTerm = "";
		tmpTerm = "10"+planEvalVO.getTerm();
		planEvalVO.setTmpTerm(tmpTerm);
		model.addAttribute("cnt", cnt);

		if("COT".equals(planEvalVO.getSessionMemType())){
			//학습근로자 목록
			if(!"".equals(memSeq)){
				planEvalVO.setMemSeq(null);
			}
			List<PlanEvalVO> listMember = planEvalService.listFistEvalPlanLessonMember(planEvalVO);
			model.addAttribute("resultMemberList", listMember);

			if("".equals(memSeq) && "".equals(searchKeyword)){
				readVO2 = planEvalService.getLessionMember(planEvalVO);

				model.addAttribute("memName", readVO2.getMemName());
				model.addAttribute("memId", readVO2.getMemId());
				model.addAttribute("memSeq", readVO2.getMemSeq());
			} else if(!"".equals(memSeq) && "".equals(searchKeyword)){
				planEvalVO.setMemSeq(memSeq);
				readVO2 = planEvalService.getLessionMember(planEvalVO);
				if(readVO2 != null){
					model.addAttribute("memName", readVO2.getMemName());
					model.addAttribute("memId", readVO2.getMemId());
					model.addAttribute("memSeq", readVO2.getMemSeq());
				} else {
					model.addAttribute("memName", "");
					model.addAttribute("memId", "");
					model.addAttribute("memSeq", "");
				}
			} else if("".equals(memSeq) && !"".equals(searchKeyword)){
				if(listMember.size() > 0){
					model.addAttribute("memName", listMember.get(0).getMemName());
					model.addAttribute("memId", listMember.get(0).getMemId());
					model.addAttribute("memSeq", listMember.get(0).getMemSeq());
				}else{
					model.addAttribute("memName", "");
					model.addAttribute("memId", "");
					model.addAttribute("memSeq", "");
				}
			}
		} else if("STD".equals(planEvalVO.getSessionMemType())){
			model.addAttribute("memName", planEvalVO.getSessionMemName());
			model.addAttribute("memId", planEvalVO.getSessionMemId());
			model.addAttribute("memSeq", planEvalVO.getSessionMemSeq());
		} else {
			if("OJT".equals(subjectTraningType)){
				SubjectVO subjectVO = new SubjectVO();
				loginInfo.putSessionToVo(subjectVO); // session의 정보를 VO에 추가.

				//if("HSKILL".equals(subjectType)){ //고숙련과정일떼 _ 학습자별

				//} else { //일반과정일떼 _ 기업별_분반

					// 교수자 and OJT교과 and 분반이 안넘어 올 경우 분반 세팅
					subjectVO.setYyyy(yyyy);
			        subjectVO.setTerm(term);
			        subjectVO.setSubjectCode(subjectCode);

			        if("".equals(searchSubClass)){
			        	 classs = subjectService.getMinSubjectClass(subjectVO);
			        	 logger.debug("OJT init class : " + classs );
			        }

		        	List<SubjectVO> listOjtClassName = subjectService.listOjtClassName(subjectVO);
	    			model.put("listOjtClassName", listOjtClassName);

					LOGGER.debug("#### searchSubClass ==> "+searchSubClass);
					LOGGER.debug("#### companyId ==> "+companyId);

					//기업ID_분반 선택시 기업현장교사가 등록한 직무수행등력평가 조회
					if(!"".equals(searchSubClass) && !"".equals(companyId) ){

						planEvalVO.setSubClass(searchSubClass);
						planEvalVO.setTmpTerm("10"+planEvalVO.getTerm());

				        subjectVO.setYyyy(planEvalVO.getYyyy());
				        subjectVO.setTerm(planEvalVO.getTerm());
				        subjectVO.setSubjectCode(planEvalVO.getSubjectCode());
				        subjectVO.setSubjectClass(searchSubClass);
				        subjectVO.setCompanyId(companyId);
				        subjectVO.setPlanEvalOjtPrtYn("Y");
				        subjectVO = subjectService.getSubjectInfo(subjectVO);

				        model.addAttribute("subjectVO", subjectVO);

				        ExcelMemberStdVO memberStdVO = new ExcelMemberStdVO();
						memberStdVO.setCompanyId(companyId);
						memberStdVO.setSearchYyyy(yyyy);
						memberStdVO.setSearchTerm(term);
						memberStdVO.setSearchSubjectCode(subjectCode);
						memberStdVO.setSearchSubClass(searchSubClass);
						memberStdVO.setSearchSubjectTraningType(subjectTraningType);
						if(!"".equals(searchKeyword)){
							memberStdVO.setSearchKeyword(searchKeyword);
						}

						List<ExcelMemberStdVO> listPlanResultMemberStd = memberStdService.listPlanResultMemberStd( memberStdVO );
						model.addAttribute("resultMemberStdList", listPlanResultMemberStd);

						if(listPlanResultMemberStd.size() > 0){
							model.addAttribute("memName", listPlanResultMemberStd.get(0).getMemName());
							model.addAttribute("memId", listPlanResultMemberStd.get(0).getMemId());
							model.addAttribute("memSeq", listPlanResultMemberStd.get(0).getMemSeq());
						}else{
							model.addAttribute("memName", "");
							model.addAttribute("memId", "");
							model.addAttribute("memSeq", "");
						}

						//기업ID 및 학습근로자성명 클릭시
						if(!"".equals(memSeq) && !"".equals(memId)){
							model.addAttribute("memSeq", memSeq);
							model.addAttribute("memId", memId);
						}
					} else {
						model.addAttribute("memName", "");
						model.addAttribute("memId", "");
						model.addAttribute("memSeq", "");
					}
				//}

				model.addAttribute("subjectType", subjectType);
			}
		}

		model.addAttribute("planEvalVO", planEvalVO); //파라메터정보

		if("COT".equals(planEvalVO.getSessionMemType())){
			returnUrl = "oklms/lu/planeval/planEvalMemberInfoReport"; // View호출 (기업현장교사)
		} else if("STD".equals(planEvalVO.getSessionMemType())){
			returnUrl = "oklms/lu/planeval/planEvalMemberStdInfoReport"; // View호출 (학습근로자)
		} else {
			returnUrl = "oklms/lu/planeval/planEvalMemberStdInfoReportPrt"; // View호출 (담당교사 OJT 화면 호출)
		}

		return returnUrl;
	}

	/**
	 * 평가결과서(학습근로자별 조회) 레포트
	 * @param planEvalVO
	 * @return PlanEvalVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/evalPlan/listEvalResultMember.do")
	public String listEvalResultMember(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmEvalResult") PlanEvalVO planEvalVO, ModelMap model, HttpServletRequest request) throws Exception {

		LOGGER.debug("#### paramMap ==> "+paramMap);
		LOGGER.debug("#### planEvalVO ==> "+planEvalVO);

		/*====================================================================
    	* 초기화 영역
    	======================================================================*/
		String returnUrl = "";
		String returnMsg = "POPUP_LIST_FAIL";
		int cnt = 0;
		HttpSession session = request.getSession(); // 로그인 사용자에 교과별-교과목 관련 세션 Key get 처리

		/*====================================================================
    	* 초기값 셋팅 영역
    	====================================================================*/
		/* userType 이 관리자 이외 경우는 진행중인 교과목 세션정보를 젯팅한다. */
		String subjectTraningType = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_TRANING_TYPE) , "" );
		String subjectType = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_TYPE) , "" );
		String yyyy = StringUtils.defaultString( (String)session.getAttribute(Globals.YYYY) , "" );
		String term = StringUtils.defaultString( (String)session.getAttribute(Globals.TERM) , "" );
		String subjectCode = StringUtils.defaultString( (String)session.getAttribute(Globals.SUBJECT_CODE) , "" );
		String classs = StringUtils.defaultString( (String)session.getAttribute(Globals.CLASS) , "" );
		String memSeq = StringUtils.defaultString( (String)paramMap.get("memSeq") , "" );
		String memId = StringUtils.defaultString( (String)paramMap.get("memId") , "" );
		String searchKeyword = StringUtils.defaultString( (String)paramMap.get("searchKeyword") , "" );
		String searchKeywordNull = StringUtils.defaultString((String)paramMap.get("searchKeywordNull"),"");
		String searchYyyy = StringUtils.defaultString((String)paramMap.get("searchYyyy"),"");
		String searchTerm = StringUtils.defaultString((String)paramMap.get("searchTerm"),"");
		String searchSubClass = StringUtils.defaultString((String)paramMap.get("searchSubClass"),"");
		String searchSubjectTraningType = StringUtils.defaultString((String)paramMap.get("searchSubjectTraningType"),"");
		String traningProcessId = StringUtils.defaultString( (String)paramMap.get("traningProcessId") , "" );
		String tempTraningProcessId = StringUtils.defaultString((String)paramMap.get("tempTraningProcessId"),"");
		String companyId = StringUtils.defaultString( (String)paramMap.get("companyId") , "" );
		String  pageSizeStr = StringUtils.defaultString((String)paramMap.get("pageSize"),"");
		String  pageIndexStr = StringUtils.defaultString((String)paramMap.get("pageIndex"),"");
		PlanEvalVO readVO2 = new PlanEvalVO();
		CompanyVO companyVO = new CompanyVO();

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		planEvalVO.setYyyy(yyyy);
		planEvalVO.setTerm(term);
		planEvalVO.setTmpTerm("10"+term);
		planEvalVO.setSubjectCode(subjectCode);
		planEvalVO.setSubClass(classs);
		planEvalVO.setSearchYyyy(searchYyyy);
		planEvalVO.setSearchTerm(searchTerm);
		planEvalVO.setSearchSubjectTraningType(searchSubjectTraningType);

		if(!"".equals(companyId)){
			planEvalVO.setCompanyId(companyId);
		}
		if(!"".equals(traningProcessId)){
			planEvalVO.setTraningProcessId(traningProcessId);
		}

		if("COT".equals(planEvalVO.getSessionMemType())){
			//학습근로자 목록
			List<PlanEvalVO> listMember = planEvalService.listFistEvalPlanLessonMember(planEvalVO);
			model.addAttribute("resultMemberList", listMember);

			if("".equals(memSeq) && "".equals(searchKeyword)){
				readVO2 = planEvalService.getLessionMember(planEvalVO);

				model.addAttribute("memName", readVO2.getMemName());
				model.addAttribute("memId", readVO2.getMemId());
				model.addAttribute("memSeq", readVO2.getMemSeq());
			} else if(!"".equals(memSeq) && "".equals(searchKeyword)){
				planEvalVO.setMemSeq(memSeq);
				readVO2 = planEvalService.getLessionMember(planEvalVO);
				if(readVO2 != null){
					model.addAttribute("memName", readVO2.getMemName());
					model.addAttribute("memId", readVO2.getMemId());
					model.addAttribute("memSeq", readVO2.getMemSeq());
				} else {
					model.addAttribute("memName", "");
					model.addAttribute("memId", "");
					model.addAttribute("memSeq", "");
				}
			} else if("".equals(memSeq) && !"".equals(searchKeyword)){
				if(listMember.size() > 0){
					model.addAttribute("memName", listMember.get(0).getMemName());
					model.addAttribute("memId", listMember.get(0).getMemId());
					model.addAttribute("memSeq", listMember.get(0).getMemSeq());
				}else{
					model.addAttribute("memName", "");
					model.addAttribute("memId", "");
					model.addAttribute("memSeq", "");
				}
			}

			//학습근로자성명 클릭시
			if(!"".equals(memSeq) && !"".equals(memId)){
				model.addAttribute("memSeq", memSeq);
				model.addAttribute("memId", memId);
			}

			returnUrl = "oklms/lu/planeval/evalResultMemberInfoReport"; // View호출 (기업현장교사)
		}else if("STD".equals(planEvalVO.getSessionMemType())){
			model.addAttribute("memName", planEvalVO.getSessionMemName());
			model.addAttribute("memId", planEvalVO.getSessionMemId());
			model.addAttribute("memSeq", planEvalVO.getSessionMemSeq());

			returnUrl = "oklms/lu/planeval/evalResultMemberStdInfoReport"; // View호출 (학습근로자)
		}else if("CCN".equals(planEvalVO.getSessionMemType()) || "CCM".equals(planEvalVO.getSessionMemType())){

			ExcelMemberStdVO memberStdVO = new ExcelMemberStdVO();

			//기업체에 메핑된 훈련과정정보 없을경우 조회
			if(StringUtils.isBlank( companyId)){
				if(!searchKeyword.equals("") && !"noDivPopup".equals(tempTraningProcessId)){
					companyVO.setSearchCompanyName(searchKeyword);
					returnMsg = "POPUP_LIST_SUCCESS";
					model.addAttribute("returnMsg", returnMsg);
				}

				if(!searchKeywordNull.equals("") && !"noDivPopup".equals(tempTraningProcessId)){
					companyVO.setSearchCompanyName(null);
					returnMsg = "POPUP_LIST_SUCCESS";
					model.addAttribute("returnMsg", returnMsg);
				}

				model.addAttribute("searchKeyword", searchKeyword);

				if(!"".equals(pageIndexStr) && !"noDivPopup".equals(tempTraningProcessId)){
					companyVO.setPageIndex(Integer.parseInt(pageIndexStr));
					returnMsg = "POPUP_LIST_SUCCESS";
					model.addAttribute("returnMsg", returnMsg);
				}

				List<CompanyVO> listCompanySearch = companyService.listCompany(companyVO);
				Integer pageSize = companyVO.getPageSize();
				Integer page = 1;
				if("".equals(pageIndexStr)){
					page = companyVO.getPageIndex();
				}else{
					page = Integer.parseInt(pageIndexStr);
				}

				int totalCnt = 0;
				if( 0 < listCompanySearch.size() ){
					totalCnt = Integer.parseInt( listCompanySearch.get(0).getTotalCount() );
				}
				int totalPage = (int) Math.ceil(totalCnt / pageSize);

		        model.addAttribute("pageSize", pageSize);
		        model.addAttribute("totalCount", totalCnt);
		        model.addAttribute("pageIndex", page);

		        PaginationInfo paginationInfo = new PaginationInfo();

		        paginationInfo.setCurrentPageNo(companyVO.getPageIndex());
		        paginationInfo.setRecordCountPerPage(pageSize);
		        paginationInfo.setPageSize(companyVO.getPageSize());
		        paginationInfo.setTotalRecordCount(totalCnt);

		        model.addAttribute("resultCnt", totalCnt );
		        model.addAttribute("paginationInfo", paginationInfo);

		        model.addAttribute("companyVO", companyVO);
				model.addAttribute("resultCompanyList", listCompanySearch);
				model.addAttribute("cnt", cnt);
				model.addAttribute("seachYn", "N");

			} else {
			//기업체에 메핑된 훈련과정정보 있을경우 조회
				companyVO.setCompanyId(companyId);

				List<CompanyVO> listCompanyTraningProcess = companyService.listCompanySearch(companyVO);
				cnt = listCompanyTraningProcess.size();
				model.addAttribute("resultCompanyTraningProcessList", listCompanyTraningProcess);

				if(!searchKeyword.equals("") && !"noDivPopup".equals(tempTraningProcessId)){
					companyVO.setSearchCompanyName(searchKeyword);
					returnMsg = "POPUP_LIST_SUCCESS";
					model.addAttribute("returnMsg", returnMsg);
				}

				if(!searchKeywordNull.equals("") && !"noDivPopup".equals(tempTraningProcessId)){
					companyVO.setSearchCompanyName(null);
					returnMsg = "POPUP_LIST_SUCCESS";
					model.addAttribute("returnMsg", returnMsg);
				}

				model.addAttribute("searchKeyword", searchKeyword);

				if(!"".equals(pageIndexStr) && !"noDivPopup".equals(tempTraningProcessId)){
					companyVO.setPageIndex(Integer.parseInt(pageIndexStr));
					returnMsg = "POPUP_LIST_SUCCESS";
					model.addAttribute("returnMsg", returnMsg);
				}

				companyVO.setCompanyId(null);
				companyVO.setTraningProcessId(null);
				List<CompanyVO> listCompanySearch = companyService.listCompany(companyVO);
				Integer pageSize = companyVO.getPageSize();
				Integer page = 1;
				if("".equals(pageIndexStr)){
					page = companyVO.getPageIndex();
				}else{
					page = Integer.parseInt(pageIndexStr);
				}

				int totalCnt = 0;
				if( 0 < listCompanySearch.size() ){
					totalCnt = Integer.parseInt( listCompanySearch.get(0).getTotalCount() );
				}
				int totalPage = (int) Math.ceil(totalCnt / pageSize);

		        model.addAttribute("pageSize", pageSize);
		        model.addAttribute("totalCount", totalCnt);
		        model.addAttribute("pageIndex", page);

		        PaginationInfo paginationInfo = new PaginationInfo();

		        paginationInfo.setCurrentPageNo(companyVO.getPageIndex());
		        paginationInfo.setRecordCountPerPage(pageSize);
		        paginationInfo.setPageSize(companyVO.getPageSize());
		        paginationInfo.setTotalRecordCount(totalCnt);

		        model.addAttribute("resultCnt", totalCnt );
		        model.addAttribute("paginationInfo", paginationInfo);
		        model.addAttribute("companyVO", companyVO);
				model.addAttribute("resultCompanyList", listCompanySearch);

				if(!"".equals(companyId) && !"".equals(searchYyyy) && !"".equals(searchTerm) && !"".equals(searchSubjectTraningType)){
					memberStdVO.setCompanyId(companyId);
					memberStdVO.setSearchYyyy(searchYyyy);
					memberStdVO.setSearchTerm(searchTerm);
					memberStdVO.setSearchSubjectTraningType(searchSubjectTraningType);

					List<ExcelMemberStdVO> listPlanResultMemberStd = memberStdService.listPlanResultMemberStd( memberStdVO );
					model.addAttribute("cnt", listPlanResultMemberStd.size());
					model.addAttribute("resultMemberStdList", listPlanResultMemberStd);
					model.addAttribute("seachYn", "Y");
				}
			}

			if("CCN".equals(planEvalVO.getSessionMemType())){
				returnUrl = "oklms/lu/planeval/evalResultMemberCcnInfoReport"; // View호출 (센터전담자)
			}else{
				returnUrl = "oklms/lu/planeval/evalResultMemberCcmInfoReport"; // View호출 (HRD전담자)
			}
		} else if("PRT".equals(planEvalVO.getSessionMemType())){ //담당교수일떼

			//OFF-JT
			if("OFF".equals(subjectTraningType)){
				//학습근로자 목록
				List<PlanEvalVO> listMember = planEvalService.listFistEvalPlanLessonMember(planEvalVO);
				model.addAttribute("resultMemberList", listMember);

				if("".equals(memSeq) && "".equals(searchKeyword)){
					readVO2 = planEvalService.getLessionMember(planEvalVO);

					model.addAttribute("memName", readVO2.getMemName());
					model.addAttribute("memId", readVO2.getMemId());
					model.addAttribute("memSeq", readVO2.getMemSeq());
				} else if(!"".equals(memSeq) && "".equals(searchKeyword)){
					planEvalVO.setMemSeq(memSeq);
					readVO2 = planEvalService.getLessionMember(planEvalVO);
					if(readVO2 != null){
						model.addAttribute("memName", readVO2.getMemName());
						model.addAttribute("memId", readVO2.getMemId());
						model.addAttribute("memSeq", readVO2.getMemSeq());
					} else {
						model.addAttribute("memName", "");
						model.addAttribute("memId", "");
						model.addAttribute("memSeq", "");
					}
				} else if("".equals(memSeq) && !"".equals(searchKeyword)){
					if(listMember.size() > 0){
						model.addAttribute("memName", listMember.get(0).getMemName());
						model.addAttribute("memId", listMember.get(0).getMemId());
						model.addAttribute("memSeq", listMember.get(0).getMemSeq());
					}else{
						model.addAttribute("memName", "");
						model.addAttribute("memId", "");
						model.addAttribute("memSeq", "");
					}
				}

				//학습근로자성명 클릭시
				if(!"".equals(memSeq) && !"".equals(memId)){
					model.addAttribute("memSeq", memSeq);
					model.addAttribute("memId", memId);
				}

				returnUrl = "oklms/lu/planeval/evalResultMemberInfoReport";
			} else {
				//OJT
				SubjectVO subjectVO = new SubjectVO();
				loginInfo.putSessionToVo(subjectVO); // session의 정보를 VO에 추가.

				subjectVO.setYyyy(yyyy);
		        subjectVO.setTerm(term);
		        subjectVO.setSubjectCode(subjectCode);

		        if("".equals(searchSubClass)){
		        	 classs = subjectService.getMinSubjectClass(subjectVO);
		        	 logger.debug("OJT init class : " + classs );
		        }

	        	List<SubjectVO> listOjtClassName = subjectService.listOjtClassName(subjectVO);
    			model.put("listOjtClassName", listOjtClassName);

				LOGGER.debug("#### searchSubClass ==> "+searchSubClass);
				LOGGER.debug("#### companyId ==> "+companyId);

				if(!"".equals(classs)){
					planEvalVO.setSubClass(classs);
				}

				if(!"".equals(searchSubClass)){
					planEvalVO.setSubClass(searchSubClass);
				}

				//기업ID_분반 선택시 학습근로자 조회
				if(!"".equals(searchSubClass) && !"".equals(companyId) ){
					ExcelMemberStdVO memberStdVO = new ExcelMemberStdVO();
					memberStdVO.setCompanyId(companyId);
					memberStdVO.setSearchYyyy(yyyy);
					memberStdVO.setSearchTerm(term);
					memberStdVO.setSearchSubjectCode(subjectCode);
					memberStdVO.setSearchSubClass(searchSubClass);
					memberStdVO.setSearchSubjectTraningType(subjectTraningType);
					if(!"".equals(searchKeyword)){
						memberStdVO.setSearchKeyword(searchKeyword);
					}

					List<ExcelMemberStdVO> listPlanResultMemberStd = memberStdService.listPlanResultMemberStd( memberStdVO );
					model.addAttribute("resultMemberStdList", listPlanResultMemberStd);

					if(listPlanResultMemberStd.size() > 0){
						model.addAttribute("memName", listPlanResultMemberStd.get(0).getMemName());
						model.addAttribute("memId", listPlanResultMemberStd.get(0).getMemId());
						model.addAttribute("memSeq", listPlanResultMemberStd.get(0).getMemSeq());
					}else{
						model.addAttribute("memName", "");
						model.addAttribute("memId", "");
						model.addAttribute("memSeq", "");
					}

					//기업ID 및 학습근로자성명 클릭시
					if(!"".equals(memSeq) && !"".equals(memId)){
						model.addAttribute("memSeq", memSeq);
						model.addAttribute("memId", memId);
					}
				}else{
					model.addAttribute("memName", "");
					model.addAttribute("memId", "");
					model.addAttribute("memSeq", "");
				}

				returnUrl = "oklms/lu/planeval/evalResultMemberOjtInfoReport";
			}
		}

		model.addAttribute("searchSubClass", searchSubClass);
		model.addAttribute("planEvalVO", planEvalVO); //파라메터정보

		return returnUrl;
	}

	/**
	 * 평가결과서(학습근로자별 조회) 레포트
	 * @param planEvalVO
	 * @return PlanEvalVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/evalPlan/listEvalResultMemberExcelDownload.do")
	public String listEvalResultMemberExcelDownload(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmEvalResult") PlanEvalVO planEvalVO, ModelMap model, HttpServletRequest request) throws Exception {

		LOGGER.debug("#### paramMap ==> "+paramMap);
		LOGGER.debug("#### planEvalVO ==> "+planEvalVO);

		/*====================================================================
    	* 초기값 셋팅 영역
    	====================================================================*/
		HttpSession session = request.getSession(); // 로그인 사용자에 교과별-교과목 관련 세션 Key get 처리
		String searchYyyy = StringUtils.defaultString((String)paramMap.get("searchYyyy"),"");
		String searchTerm = StringUtils.defaultString((String)paramMap.get("searchTerm"),"");
		String searchSubjectTraningType = StringUtils.defaultString((String)paramMap.get("searchSubjectTraningType"),"");
		String companyId = StringUtils.defaultString( (String)paramMap.get("companyId") , "" );

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		if(!"".equals(companyId) && !"".equals(searchYyyy) && !"".equals(searchTerm) && !"".equals(searchSubjectTraningType)){
			ExcelMemberStdVO memberStdVO = new ExcelMemberStdVO();

			memberStdVO.setCompanyId(companyId);
			memberStdVO.setSearchYyyy(searchYyyy);
			memberStdVO.setSearchTerm(searchTerm);
			memberStdVO.setSearchSubjectTraningType(searchSubjectTraningType);
			memberStdVO.setPageSize(10000); // 1만건 조회

			List<ExcelMemberStdVO> listPlanResultMemberStd = memberStdService.listPlanResultMemberStd( memberStdVO );
			String ExcelName = "학습자근로자별 평가결과서";

			model.addAttribute("resultMemberStdList", listPlanResultMemberStd);
			request.setAttribute("ExcelName", URLEncoder.encode( ExcelName.replaceAll(" ", "_") ,"UTF-8") );
		}

		return "oklms_excel/lu/planeval/evalResultMemberCcnInfoExcelList";
	}

	/**
	 * 직무수행능력평가(평가계획) 저장
	 * @param planEvalVO
	 * @return PlanEvalVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/evalPlan/insertNcsEvalPlan.do")
	public String insertNcsEvalPlan(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmEvalPlan") PlanEvalVO planEvalVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status, final MultipartHttpServletRequest multiRequest) throws Exception {
		LOGGER.debug("#### paramMap ==> "+paramMap);
		LOGGER.debug("#### planEvalVO ==> "+planEvalVO.toString());

		String firstCheckSplitArr = StringUtils.defaultString( (String)paramMap.get("firstCheckSplitArr") , "" );
		String subCheckArr = StringUtils.defaultString( (String)paramMap.get("subCheckArr") , "" );
		String retMsg = "평가계획을 저장할 정보가 없습니다.";
		String returnUrl = "";
		int insertCnt1 = 0;
		int insertCnt2 = 0;
		int insertCnt3 = 0;
		int insertCnt4 = 0;
		int ncsEvalPlanCnt = 0;

		ncsEvalPlanCnt = planEvalService.getNcsEvalPlanCnt(planEvalVO);
		if(ncsEvalPlanCnt > 0){
			retMsg = "평가계획을 저장할 정보가 이미 있습니다.";

			PlanEvalVO vo = new PlanEvalVO();

			redirectAttributes.addFlashAttribute("frmEvalPlan", vo);
			redirectAttributes.addFlashAttribute("retMsg", retMsg);

			returnUrl = "redirect:/lu/evalPlan/listNcsEvalPlan.do";

		} else {

		AunuriLinkEvalPlanNcsVO evalPlanNcsVO = new AunuriLinkEvalPlanNcsVO();
		AunuriLinkEvalPlanNcsVO aunuriReadVO = new AunuriLinkEvalPlanNcsVO();

		String[] firstCheckSplitList = null;
		String[] subCheckCommaList = null;

		firstCheckSplitList = firstCheckSplitArr.split(",");
		subCheckCommaList = subCheckArr.split(",");

		String tmpTerm = "10";
		String term = tmpTerm+planEvalVO.getTerm();
		evalPlanNcsVO.setYyyy(planEvalVO.getYyyy());
		evalPlanNcsVO.setTerm(term);
		evalPlanNcsVO.setSubjectCode(planEvalVO.getSubjectCode());
		evalPlanNcsVO.setSubClass(planEvalVO.getSubClass());
		evalPlanNcsVO.setEvDivCd(planEvalVO.getEvDivCd());

		aunuriReadVO = aunuriLinkService.getAunuriWeekNcsEvalPlan(evalPlanNcsVO);

		planEvalVO.setEvDivName(aunuriReadVO.getEvDivName());
		planEvalVO.setEtcName(aunuriReadVO.getEtcName());
		planEvalVO.setEvRt(aunuriReadVO.getEvRt());

		//1.평가계획 시험성적 기준정보를 저장한다.
		List<FileVO> result = null;
		String atchFileId = "";

		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "PLAN_", 0, "", "");
			atchFileId = fileMngService.insertFileInfs(result);
		}
		planEvalVO.setAtchFileId(atchFileId);

		insertCnt1 = planEvalService.insertNcsEvalPlanStd(planEvalVO);

		if(insertCnt1 > 0){
			for (int i = 0; i < firstCheckSplitList.length; i++) {
				String tmpNcsElemId = "";
				tmpNcsElemId = firstCheckSplitList[i];
				AunuriLinkEvalPlanNcsVO singleDataVO = new AunuriLinkEvalPlanNcsVO();

				LOGGER.debug("#### tmpNcsElemId ==> "+tmpNcsElemId);
				evalPlanNcsVO.setNcsElemId(tmpNcsElemId);

				singleDataVO = aunuriLinkService.getAunuriWeekNcsEvalPlanElem(evalPlanNcsVO);

				planEvalVO.setNcsElemId(tmpNcsElemId);
				planEvalVO.setNcsElemName(singleDataVO.getNcsElemName());
				planEvalVO.setElemNumCd(singleDataVO.getElemNumCd());
				planEvalVO.setElemVw(singleDataVO.getElemVw());

				//2.NCS개설과목능력요소 정보 저장
				insertCnt3 += planEvalService.insertNcsEvalPlanLctreElem(planEvalVO);
			}
		}

		if(insertCnt3 > 0){
			for (int idx1 = 0; idx1 < firstCheckSplitList.length; idx1++) {
				String firstCheckSplit = "";
				firstCheckSplit = firstCheckSplitList[idx1];
				List<AunuriLinkEvalPlanNcsVO> listAunuriWeekNcsEvalPlanNote = null;

				LOGGER.debug("#### firstCheckSplit ==> "+firstCheckSplit);

				planEvalVO.setNcsElemId(firstCheckSplit);
				evalPlanNcsVO.setNcsElemId(firstCheckSplit);

				listAunuriWeekNcsEvalPlanNote = aunuriLinkService.listAunuriWeekNcsEvalPlanNote(evalPlanNcsVO);

				for (int idx2 = 0; idx2 < listAunuriWeekNcsEvalPlanNote.size(); idx2++) {
					planEvalVO.setNcsElemName(listAunuriWeekNcsEvalPlanNote.get(idx2).getNcsElemName());
					planEvalVO.setLessonCn(listAunuriWeekNcsEvalPlanNote.get(idx2).getLessonCn());

					//3.NCS강의계획서학습내용 정보 저장
					insertCnt2 += planEvalService.insertNcsEvalPlanLrn(planEvalVO);
				}
			}
		}

		if(insertCnt2 > 0){
			//4.NCS개설과목관리항목설정 정보 저장
			for (int idx3 = 0; idx3 < subCheckCommaList.length; idx3++) {
				String subCheckCommaSplit = "";
				subCheckCommaSplit = subCheckCommaList[idx3];

				LOGGER.debug("#### subCheckCommaSplit ==> "+subCheckCommaSplit);

				String[] subCheckUnderBaList = null;
				subCheckUnderBaList = subCheckCommaSplit.split("-");

				//for (int idx4 = 0; idx4 < subCheckUnderBaList.length; idx4++) {
					String cdDiv = "NC016";
					String subCheckNcsElemId = "";
					String dtlCd = "";
					String dtlCdName = "";
					String entDataName = "";

					subCheckNcsElemId = subCheckUnderBaList[0];
					dtlCd = subCheckUnderBaList[1];
					dtlCdName = subCheckUnderBaList[2];

					if(subCheckUnderBaList.length > 3){
						entDataName = subCheckUnderBaList[3];
					}

					LOGGER.debug("#### subNcsElemId ==> "+subCheckNcsElemId);
					LOGGER.debug("#### cdDiv ==> "+cdDiv);
					LOGGER.debug("#### dtlCd ==> "+dtlCd);
					LOGGER.debug("#### dtlCdName ==> "+dtlCdName);
					LOGGER.debug("#### entDataName ==> "+entDataName);

					planEvalVO.setNcsElemId(subCheckNcsElemId);
					planEvalVO.setCdDiv(cdDiv);
					planEvalVO.setDtlCd(dtlCd);
					planEvalVO.setDtlCdName(dtlCdName);

					if(!"".equals(entDataName) && entDataName != null){
						planEvalVO.setEntDataName(entDataName);
					}

					insertCnt4 += planEvalService.insertNcsEvalPlanLctreCtrlSet(planEvalVO);
				//}
			 }
		  }

			if(insertCnt1 > 0 && insertCnt2 > 0 && insertCnt3 > 0 && insertCnt4 > 0){
				retMsg = "평가계획 등록이 정상적으로 처리되었습니다.!";
			}else{
				retMsg = "평가계획 등록시 처리된 건이 없습니다.!";
			}

			PlanEvalVO vo = new PlanEvalVO();

			redirectAttributes.addFlashAttribute("frmEvalPlan", vo);
			redirectAttributes.addFlashAttribute("retMsg", retMsg);

			// 상세조회 화면에서 목록으로 이동 처리 2017.06.01
			//returnUrl = "redirect:/lu/evalPlan/getNcsEvalPlan.do";
			returnUrl = "redirect:/lu/evalPlan/listNcsEvalPlan.do";

		}

		/*
		firstCheckArr :문서기안하기-0202030201_13v1.1,문서기안 자료조사하기-0202030201_13v1.2
		firstCheckSplitArr :0202030201_13v1.1,0202030201_13v1.2
		subCheckArr :0202030201_13v1.1-A-포트폴리오,0202030201_13v1.1-C-서술형시험,0202030201_13v1.1-G-평가자 체크리스트,0202030201_13v1.2-A-포트폴리오,0202030201_13v1.2-C-서술형시험,0202030201_13v1.2-G-평가자 체크리스트
		*/

		// View호출
		return returnUrl;
	}

	/**
	 * 직무수행능력평가(1차평가 - 학습자별취득점수, PASS여부) 단건 저장
	 * @param planEvalVO
	 * @return PlanEvalVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/evalPlan/insertNcsEvalPlanFirst.json")
	public @ResponseBody Map<String, Object> insertNcsEvalPlanFirst(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmEvalPlan") PlanEvalVO planEvalVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		LOGGER.debug("#### paramMap ==> "+paramMap);
		LOGGER.debug("#### planEvalVO ==> "+planEvalVO.toString());

		Map<String , Object> returnMap = new HashMap<String , Object>();
		String retCd = "FAIL";
		String retMsg = "";
		String passAt = "";

		String yyyy = StringUtils.defaultString( (String)paramMap.get("yyyy") , "" );
		String term = StringUtils.defaultString( (String)paramMap.get("term") , "" );
		String subjectCode = StringUtils.defaultString( (String)paramMap.get("subjectCode") , "" );
		String subClass = StringUtils.defaultString( (String)paramMap.get("subClass") , "" );
		String memSeq = StringUtils.defaultString( (String)paramMap.get("memSeq") , "" );
		String evDivCd = StringUtils.defaultString( (String)paramMap.get("evDivCd") , "" );
		String tmpNcsEmelId = StringUtils.defaultString( (String)paramMap.get("tmpNcsEmelId") , "" );
		String newEvalScore = StringUtils.defaultString( (String)paramMap.get("tmpEvalScore") , "" );
		String oldEvalScore = StringUtils.defaultString( (String)paramMap.get("tmpOldEvalScore") , "" );
		String planEvIdArr = StringUtils.defaultString( (String)paramMap.get("planEvIdArr") , "" );
		String newLessonCnPassAtArr = StringUtils.defaultString( (String)paramMap.get("newLessonCnPassAtArr") , "" );
		String oldLessonCnPassAtArr = StringUtils.defaultString( (String)paramMap.get("oldLessonCnPassAtArr") , "" );

		int insertCnt1 = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		planEvalVO.setYyyy(yyyy);
		planEvalVO.setTerm(term);
		planEvalVO.setSubjectCode(subjectCode);
		planEvalVO.setSubClass(subClass);

		if("STD".equals(planEvalVO.getSessionMemType())){
			planEvalVO.setMemSeq(planEvalVO.getSessionMemSeq());
		} else {
			planEvalVO.setMemSeq(memSeq);
		}

		planEvalVO.setEvDivCd(evDivCd);
		planEvalVO.setNewEvalScore(newEvalScore);
		planEvalVO.setOldEvalScore(oldEvalScore);
		planEvalVO.setNcsElemId(tmpNcsEmelId);

		if(!"".equals(planEvIdArr)){
			String[] planEvIdList = null;
			planEvIdList = planEvIdArr.split(",");

			for (int i = 0; i < planEvIdList.length; i++) {
				String planEvId = "";
				planEvId = planEvIdList[i];
				planEvalVO.setPlanEvId(planEvId);

				String[] newLessonCnPassAtList = null;
				newLessonCnPassAtList = newLessonCnPassAtArr.split(",");
				String newLessonCnPassAt = "";

				newLessonCnPassAt = newLessonCnPassAtList[i];
				LOGGER.debug("#### newLessonCnPassAt ==> "+newLessonCnPassAt);
				planEvalVO.setNewLessonCnPassAt(newLessonCnPassAt);

				if("P".equals(newLessonCnPassAt)){
					passAt = "PASS";
				}else{
					passAt = "FAIL";
				}

				if(!"".equals(oldLessonCnPassAtArr)){
					//학습자별 취득점수 및 PASS여부 업데이트
					insertCnt1 += planEvalService.updateFirstEvalStdScore(planEvalVO);
				} else {
					//학습자별 취득점수 및 PASS여부 생성
					insertCnt1 += planEvalService.insertFirstEvalStdScore(planEvalVO);
				}
			}
		}

		if( 0 < insertCnt1 ){
			retCd = "SUCCESS";
			retMsg = "정상적으로 처리되었습니다.";
		}else{
			retMsg = "처리된 정보가 없습니다.";
		}

		// View호출
		returnMap.put("retCd", retCd);
		returnMap.put("retMsg", retMsg);
		returnMap.put("ncsElemId", tmpNcsEmelId);
		returnMap.put("passAt", passAt);

		return returnMap;
	}

	/**
	 * 직무수행능력평가(1차평가 - 학습자별취득점수, PASS여부) 일괄 저장
	 * @param planEvalVO
	 * @return PlanEvalVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/evalPlan/insertNcsEvalPlanFirstAll.json")
	public @ResponseBody Map<String, Object> insertNcsEvalPlanFirstAll(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmEvalPlan") PlanEvalVO planEvalVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		LOGGER.debug("#### paramMap ==> "+paramMap);
		LOGGER.debug("#### planEvalVO ==> "+planEvalVO.toString());

		Map<String , Object> returnMap = new HashMap<String , Object>();
		String retCd = "FAIL";
		String retMsg = "";

		String yyyy = StringUtils.defaultString( (String)paramMap.get("yyyy") , "" );
		String term = StringUtils.defaultString( (String)paramMap.get("term") , "" );
		String subjectCode = StringUtils.defaultString( (String)paramMap.get("subjectCode") , "" );
		String subClass = StringUtils.defaultString( (String)paramMap.get("subClass") , "" );
		String memSeq = StringUtils.defaultString( (String)paramMap.get("memSeq") , "" );
		String evDivCd = StringUtils.defaultString( (String)paramMap.get("evDivCd") , "" );
		String evalScoreArr = StringUtils.defaultString( (String)paramMap.get("evalScoreArr") , "" );
		String planEvIdArr = StringUtils.defaultString( (String)paramMap.get("planEvIdArr") , "" );
		String newLessonCnPassAtArr = StringUtils.defaultString( (String)paramMap.get("newLessonCnPassAtArr") , "" );

		int updateCnt = 0;
		int ncsPlanStdCnt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		planEvalVO.setYyyy(yyyy);
		planEvalVO.setTerm(term);
		planEvalVO.setSubjectCode(subjectCode);
		planEvalVO.setSubClass(subClass);
		planEvalVO.setMemSeq(memSeq);
		planEvalVO.setEvDivCd(evDivCd);

		if(!"".equals(planEvIdArr)){
			String evalScore = "";
			String planEvId = "";
			String lessonCnPassAtArr = "";
			String[] evalScoreList = null;
			String[] tmpPlanEvIdList = null;
			String[] planEvIdList = null;
			String[] newLessonCnPassAtList = null;
			//firstCheckList = firstCheckArr.split(",");
			evalScoreList = evalScoreArr.split(",");
			tmpPlanEvIdList = planEvIdArr.split(",");
			newLessonCnPassAtList = newLessonCnPassAtArr.split(",");

			for (int a = 0; a < tmpPlanEvIdList.length; a++) {
				evalScore = evalScoreList[a];
				LOGGER.debug("#### evalScore ==> "+evalScore);
				planEvalVO.setNewEvalScore(evalScore);

				planEvId = tmpPlanEvIdList[a];
				LOGGER.debug("#### planEvId ==> "+planEvId);
				planEvIdList = planEvId.split("-");
				planEvalVO.setNcsElemId(planEvIdList[0]);
				planEvalVO.setPlanEvId(planEvIdList[1]);

				ncsPlanStdCnt = planEvalService.getNcsEvalPlanStdScoreCnt(planEvalVO);

				lessonCnPassAtArr = newLessonCnPassAtList[a];
				LOGGER.debug("#### lessonCnPassAtArr ==> "+lessonCnPassAtArr);
				planEvalVO.setNewLessonCnPassAt(lessonCnPassAtArr);

				if(ncsPlanStdCnt > 0){
					//학습자별 취득점수 및 PASS여부 업데이트
					updateCnt += planEvalService.updateFirstEvalStdScore(planEvalVO);
				} else {
					//학습자별 취득점수 및 PASS여부 생성
					updateCnt += planEvalService.insertFirstEvalStdScore(planEvalVO);
				}
			}
		}

		if( 0 < updateCnt ){
			retCd = "SUCCESS";
			retMsg = "정상적으로 처리되었습니다.";
		}else{
			retMsg = "처리된 정보가 없습니다.";
		}

		// View호출
		returnMap.put("retCd", retCd);
		returnMap.put("retMsg", retMsg);

		return returnMap;
	}

	/**
	 * 직무수행능력평가(1차평가 첨부파일 업데이트)
	 * @param planEvalVO
	 * @return PlanEvalVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/evalPlan/updateNcsEvalPlanFirstAtchFile.do")
	public String updateNcsEvalPlanFirstAtchFile(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmEvalPlan") PlanEvalVO planEvalVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status, final MultipartHttpServletRequest multiRequest) throws Exception {
		String retMsg = "";
		int cnt = 0;

		List<FileVO> result = null;
		String atchFileId = "";

		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if (!files.isEmpty()) {
			result = fileUtil.parseFileInf(files, "PLAN_", 0, "", "");
			atchFileId = fileMngService.insertFileInfs(result);
		}
		planEvalVO.setAtchFileId(atchFileId);

		cnt = planEvalService.updateNcsEvalPlanFirstAtchFile(planEvalVO);

		if(cnt > 0){
			//retMsg = "정상적으로 승인신청 처리되었습니다.!";
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "처리시 에러가 발생하였습니다.!";
		}

		//PlanEvalVO vo = new PlanEvalVO();

		redirectAttributes.addFlashAttribute("frmEvalPlan", planEvalVO);
		redirectAttributes.addFlashAttribute("retMsg", retMsg);

		// View호출
		return "redirect:/lu/evalPlan/listNcsEvalPlanFirst.do";
	}

	/**
	 * 직무수행능력평가(승인신청여부 업데이트) 학습근로자
	 * @param planEvalVO
	 * @return PlanEvalVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/evalPlan/updateNcsEvalPlanFirstAppYn.do")
	public String updateNcsEvalPlanFirstAppYn(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmEvalPlan") PlanEvalVO planEvalVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		String retMsg = "";
		int deleteCnt = 0;

		deleteCnt = planEvalService.updateNcsEvalPlanFirstAppYn(planEvalVO);

		if(deleteCnt > 0){
			//retMsg = "정상적으로 승인신청 처리되었습니다.!";
			retMsg = "정상적으로 처리되었습니다.!";
		}else{
			retMsg = "승인신청시 에러가 발생하였습니다.!";
		}

		PlanEvalVO vo = new PlanEvalVO();

		redirectAttributes.addFlashAttribute("frmEvalPlan", vo);
		redirectAttributes.addFlashAttribute("retMsg", retMsg);

		// View호출
		return "redirect:/lu/evalPlan/listNcsEvalPlan.do";
	}

	/**
	 * 직무수행능력평가(승인신청여부 업데이트) 학습근로자
	 * @param planEvalVO
	 * @return PlanEvalVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/evalPlan/updateNcsEvalPlanApproval.do")
	public String updateNcsEvalPlanApproval(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmEvalPlan") PlanEvalVO planEvalVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		String retMsg = "";
		int deleteCnt = 0;
		String retunReason = StringUtils.defaultString( (String)paramMap.get("retunReason") , "" );

		deleteCnt = planEvalService.updateNcsEvalPlanApproval(planEvalVO);

		if(deleteCnt > 0){
			if("01".equals(retunReason)){
				//retMsg = "정상적으로 승인 처리되었습니다.!";
				retMsg = "정상적으로 처리되었습니다.!";
			} else {
				retMsg = "정상적으로 반려 처리되었습니다.!";
			}
		}else{
			if("01".equals(retunReason)){
				retMsg = "승인 처리시 에러가발생하였습니다.!";
			} else {
				retMsg = "반려 처리시 에러가발생하였습니다.!";
			}
		}

		PlanEvalVO vo = new PlanEvalVO();

		redirectAttributes.addFlashAttribute("frmEvalPlan", vo);
		redirectAttributes.addFlashAttribute("retMsg", retMsg);

		// View호출
		return "redirect:/lu/evalPlan/listNcsEvalPlan.do";
	}


	/**
	 * 직무수행능력평가(평가계획) 삭제
	 * @param planEvalVO
	 * @return PlanEvalVO
	 * @throws Exception
	 */
	@RequestMapping(value = "/lu/evalPlan/deleteNcsEvalPlan.do")
	public String deleteNcsEvalPlan(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmEvalPlan") PlanEvalVO planEvalVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		LOGGER.debug("#### paramMap ==> "+paramMap);
		LOGGER.debug("#### planEvalVO ==> "+planEvalVO.toString());

		String retMsg = "";
		int deleteCnt1 = 0;
		int deleteCnt2 = 0;
		int deleteCnt3 = 0;
		int deleteCnt4 = 0;
		int stdScoreCnt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		stdScoreCnt = planEvalService.getNcsEvalPlanStdScoreCnt(planEvalVO);

		if(stdScoreCnt == 0){
			//1.평가계획 시험성적 기준정보를 삭제한다.
			deleteCnt1 = planEvalService.deleteNcsEvalPlanStd(planEvalVO);

			//2.NCS개설과목능력요소를 삭제한다.
			deleteCnt2 = planEvalService.deleteNcsEvalPlanLctreElem(planEvalVO);

			//3.NCS강의계획서학습내용정보를 삭제한다.
			deleteCnt3 = planEvalService.deleteNcsEvalPlanLrn(planEvalVO);

			//4.NCS개설과목관리항목설정 정보를 삭제한다.
			deleteCnt4 = planEvalService.deleteNcsEvalPlanLctreCtrlSet(planEvalVO);

			if(deleteCnt1 > 0 && deleteCnt2 > 0 && deleteCnt3 > 0 && deleteCnt4 > 0){
				retMsg = "등록된 평가계획을 정상적으로 삭제 처리되었습니다.!";
			}else{
				retMsg = "등록된 평가계획이 삭제처리건이 없습니다.!";
			}

		} else {
			retMsg = "등록된 평가계획중에 학습자별 취득점수를 처리한건이 있어 삭제불가합니다.!";
		}

		PlanEvalVO vo = new PlanEvalVO();

		redirectAttributes.addFlashAttribute("frmEvalPlan", vo);
		redirectAttributes.addFlashAttribute("retMsg", retMsg);

		// View호출
		return "redirect:/lu/evalPlan/listNcsEvalPlan.do";
	}

	//평가계획 저장후 저장된 정보 상세 조회 화면
	@RequestMapping(value = "/lu/evalPlan/getNcsEvalPlan.do")
	public String getNcsEvalPlan(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmEvalPlan") PlanEvalVO planEvalVO, ModelMap model, HttpServletRequest request) throws Exception {
		String returnUrl = "";

		if("COT".equals(planEvalVO.getSessionMemType())){
			returnUrl = "oklms/lu/planeval/planEvalListRead"; // View호출 (기업현장교사)
		}else{
			//return "oklms/"+userType+"/discuss/discussStdList"; // View호출 (학습근로자)
		}

		// View호출
		return returnUrl;
	}

	//HRD전담자가 report 호출시
	@RequestMapping(value = "/lu/evalPlan/getNcsEvalPlanReportView.do")
	public String getNcsEvalPlanReportView(@RequestParam Map<String, Object> paramMap, @ModelAttribute("frmEvalPlan") PlanEvalVO planEvalVO, ModelMap model, HttpServletRequest request) throws Exception {

		model.addAttribute("planEvalVO", planEvalVO);

		return "oklms/lu/planeval/planEvalCcmReportView";
	}


	@RequestMapping(value = "/lu/evalPlan/getEvalResultMember.do")
	public String popupEvalResultMemberReportView(@RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

		String memId = StringUtils.defaultString((String)commandMap.get("memId"),"");
		String memName = StringUtils.defaultString((String)commandMap.get("memName"),"");

		logger.debug("#### memId :=" + memId );
		logger.debug("#### memName :=" + memName );

		model.addAttribute("memId", memId);
		model.addAttribute("memName", memName);

		// View호출
        return "oklms/lu/popup/evalResultMemberStdInfoReportPopup";
	}


}
