package kr.co.sitglobal.oklms.lu.activity.service;

import java.util.List;

import kr.co.sitglobal.oklms.lu.activity.vo.ActivityVO;
import kr.co.sitglobal.oklms.lu.activity.vo.MemberVO;
import kr.co.sitglobal.oklms.lu.activity.vo.SubjweekStdVO;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Transactional(rollbackFor=Exception.class)
public interface ActivityService {
	
	/**
	 * 주차별학습활동서 미작성건을 조회하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * ReportVO
	 */
	List<SubjweekStdVO> listWeekActivityMakeStd(SubjweekStdVO activityVO) throws Exception;
	/**
	 * 학습활동서 미작성건을 조회하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * ReportVO
	 */
	List<ActivityVO> listActivityNotMake(ActivityVO activityVO) throws Exception;
	
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * ReportVO
	 */
	List<ActivityVO> listActivity(ActivityVO activityVO) throws Exception;
	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * ReportVO
	 */
	ActivityVO getActivity(ActivityVO activityVO) throws Exception;
	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * ReportVO
	 */
	List<ActivityVO> getActivityMember(ActivityVO activityVO) throws Exception;	
 	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * String
	 */
	int insertActivity(ActivityVO activityVO,final MultipartHttpServletRequest multiRequest) throws Exception;
	
	/**
	 * 정보를 수정하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * String
	 */ 
	int updateActivity(ActivityVO activityVO ,final MultipartHttpServletRequest multiRequest) throws Exception;
	/**
	 * 정보를 수정하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * String
	 */ 
	int updateActivitySubmit(ActivityVO activityVO  ) throws Exception;
	/**
	 * 정보를 삭제하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * int
	 */
	int deleteActivity(ActivityVO activityVO) throws Exception;	
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * ActivityVO
	 */
	SubjweekStdVO getSubjWeek(SubjweekStdVO subjweekStdVO) throws Exception;
	
	List<MemberVO>  listActivityMember(MemberVO memberVO)  throws Exception;
	List<MemberVO>  listActivityCompany(MemberVO memberVO)  throws Exception;
	
	List<SubjweekStdVO> listActivityHrd(SubjweekStdVO subjweekStdVO)   throws Exception;
	
	List<SubjweekStdVO> selectActivityHrd(SubjweekStdVO subjweekStdVO)   throws Exception;
	 
	List<SubjweekStdVO> listWeekActivityStd(SubjweekStdVO subjweekStdVO)   throws Exception;
	
}
