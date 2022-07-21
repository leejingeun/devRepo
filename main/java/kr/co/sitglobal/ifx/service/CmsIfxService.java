package kr.co.sitglobal.ifx.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kr.co.sitglobal.ifx.vo.CmsCourseBaseVO;
import kr.co.sitglobal.ifx.vo.CmsCourseCodeVO;
import kr.co.sitglobal.ifx.vo.CmsCourseContentItemListVO;
import kr.co.sitglobal.ifx.vo.CmsCourseContentItemSubItemListVO;
import kr.co.sitglobal.ifx.vo.CmsCourseContentPropertiesVO;
import kr.co.sitglobal.ifx.vo.CmsCourseContentVO;

import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor=Exception.class)
public interface CmsIfxService {
	
	/**
	 * CMS에서 과정 개발 코드 목록 가져오기
	 * @param cmsCourseBaseVO
	 * @return
	 * List<CmsCourseCodeVO>
	 */
	List<CmsCourseCodeVO> getCourseCodeSummaryList(CmsCourseBaseVO cmsCourseBaseVO) throws Exception;

	/**
	 * CMS에서 특정 과정 개발 코드 내의 콘텐츠 버전 목록 가져오기
	 * @param cmsCourseBaseVO
	 * @return
	 * List<CmsCourseContentVO>
	 */
	List<CmsCourseContentVO> getCourseContentSummaryList(CmsCourseBaseVO cmsCourseBaseVO) throws Exception;
	
	/**
	 * CMS에서 버전 별 콘텐츠 정보 보기
	 * @param cmsCourseBaseVO
	 * @return
	 * List<CmsCourseContentPropertiesVO>
	 */
	List<CmsCourseContentPropertiesVO> getCourseContent(CmsCourseBaseVO cmsCourseBaseVO) throws Exception;
	
	
	/**
	 * CMS에서 회차/클립 목록보기
	 * @param cmsCourseBaseVO
	 * @return
	 * List<CmsCourseContentItemListVO>
	 */
	List<CmsCourseContentItemListVO> getCourseContentItemList(CmsCourseBaseVO cmsCourseBaseVO) throws Exception;
	
	/**
	 * CMS Data 가져온다.
	 * @param cmsCourseBaseVO
	 * @return
	 * String
	 */
	String getCmsData(CmsCourseBaseVO cmsCourseBaseVO) throws Exception;
	
	
	/**
	 * CMS 컨텐츠 URL 정보를 가져온다.
	 * @param cmsCourseBaseVO
	 * @return
	 *  HashMap<String, String> 
	 */
	 HashMap<String, String>  viewLesson(CmsCourseBaseVO cmsCourseBaseVO) throws Exception;
	 
	 
	HashMap<String, String> viewLessonOne(CmsCourseBaseVO cmsCourseBaseVO)
throws Exception;

	 
	 /**
	 * CMS 콘텐츠 > 회차 > 클립 정보를 가져온다.
	 * @param cmsCourseBaseVO
	 * @return
	 *  HashMap<String, String> 
	 */
	 
	List<CmsCourseContentItemSubItemListVO> viewLessonList(CmsCourseBaseVO cmsCourseBaseVO)throws Exception;
	 
	 /**
	 * DB에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param 
	 * @return
	 * Interger
	 */
	 int getCourseContentSummaryCnt() throws Exception;
	 
	 /**
	 * CMS 콘텐츠별 버전정보를 저장한다
	 * @param cmsCourseBaseVO
	 * @return
	 * Interger
	 */
	 int insertCourseContentSummary(CmsCourseBaseVO cmsCourseBaseVO) throws Exception;
	 
	 /**
	 * 콘텐츠별 버전정보 목록을 조회하는 SQL 을 호출한다.
	 * @param cmsCourseContentVO
	 * @return
	 * List<CmsCourseContentVO>
	 */
	 List<CmsCourseContentVO> listCourseContentSummary(CmsCourseBaseVO cmsCourseBaseVO) throws Exception;
	 

	void getCourseContentItemList1(CmsCourseBaseVO cmsCourseBaseVO)
			throws Exception;

	List<CmsCourseContentVO> getCourseContentSummaryOldList(
			CmsCourseBaseVO cmsCourseBaseVO) throws Exception;


	

	

	

	




	
	
}
