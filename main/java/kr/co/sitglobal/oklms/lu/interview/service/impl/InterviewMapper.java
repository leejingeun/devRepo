package kr.co.sitglobal.oklms.lu.interview.service.impl;

import java.util.List;

import kr.co.sitglobal.oklms.lu.interview.vo.InterviewCompanyVO;
import kr.co.sitglobal.oklms.lu.interview.vo.InterviewMemberVO;
import kr.co.sitglobal.oklms.lu.interview.vo.InterviewVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("InterviewMapper")
public interface InterviewMapper {
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param interviewVO
	 * @return
	 * InterviewVO
	 */
	List<InterviewVO> listInterview(InterviewVO interview) throws Exception;
	
	InterviewVO getInterview(InterviewVO interview) throws Exception;
	
	List<InterviewMemberVO> InterviewMembers(InterviewVO interview) throws Exception;
	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param interviewVO
	 * @return
	 * String
	 */
	int insertInterview(InterviewVO interviewVO) throws Exception;
	
	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param interviewVO
	 * @return
	 * String
	 */
	int insertInterviewMember(InterviewVO interviewVO) throws Exception;
	
	
	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param interviewVO
	 * @return
	 * String
	 */
	int updateInterview(InterviewVO interviewVO) throws Exception;
	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param interviewVO
	 * @return
	 * String
	 */
	int deleteInterview(InterviewVO interviewVO) throws Exception;
	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param interviewVO
	 * @return
	 * String
	 */
	int deleteInterviewMember(InterviewVO interviewVO) throws Exception;
	
	/** 센터전담자 */
	List<InterviewCompanyVO> InterviewCompanys(InterviewCompanyVO interview) throws Exception;
	
	InterviewCompanyVO InterviewCompany(InterviewCompanyVO interview) throws Exception;
	
	InterviewCompanyVO InterviewCompanyMember(InterviewCompanyVO interview) throws Exception;
	
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param interviewVO
	 * @return
	 * InterviewVO
	 */
	List<InterviewVO> listInterviewCenter(InterviewVO interview) throws Exception;
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param interviewVO
	 * @return
	 * InterviewVO
	 */
	List<InterviewVO> getInterviewCenter(InterviewVO interview) throws Exception;
	
	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param interviewVO
	 * @return
	 * String
	 */
	int updateInterviewCenterSend(InterviewVO interviewVO) throws Exception;
	
	/**
	 * 
	 *  기업체 조회 강사가 맡고있는 훈련과정조회
	 *  
	 */
	List<InterviewCompanyVO> listCompanyini(InterviewCompanyVO interviewCompanyVO) throws Exception;
	/**
	 * 
	 *  센터전담자용 전체 훈련과정조회
	 *  
	 */
	List<InterviewCompanyVO> listCompanyCcn(InterviewCompanyVO interviewCompanyVO) throws Exception;
}
