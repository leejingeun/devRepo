
/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2017. 01. 06.         First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.lu.subject.service;

import java.util.List;

import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.grade.vo.GradeVO;
import kr.co.sitglobal.oklms.lu.subject.vo.SubjectCompanyVO;
import kr.co.sitglobal.oklms.lu.subject.vo.SubjectVO;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

 /**
 * 클래스에 대한 내용을 작성한다.
 * @author 이진근
 * @since 2017. 01. 06.
 */
@Transactional(rollbackFor=Exception.class)
public interface SubjectService {

	/**
	 * 단건을 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * SubjectVO
	 */
	SubjectVO getSubjectInfo(SubjectVO SubjectVO) throws Exception;


	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectVO
	 * @return
	 * GradeVO
	 */

	List<SubjectVO> listSubjectPrt(SubjectVO subjectVO) throws Exception;

	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectVO
	 * @return
	 * GradeVO
	 */

	List<SubjectVO> listSubjectCdp(SubjectVO subjectVO) throws Exception;


	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectVO
	 * @return
	 * GradeVO
	 */

	List<SubjectVO> listSubjectStd(SubjectVO subjectVO) throws Exception;


	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectVO
	 * @return
	 * GradeVO
	 */

	List<SubjectVO> listSubjectCot(SubjectVO subjectVO) throws Exception;


	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectVO
	 * @return
	 * GradeVO
	 */

	List<SubjectCompanyVO> listCompanyCcn(SubjectCompanyVO subjectCompnyVO) throws Exception;

	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectVO
	 * @return
	 * GradeVO
	 */

	List<SubjectCompanyVO> listCompanyCcm(SubjectCompanyVO subjectCompnyVO) throws Exception;

	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectVO
	 * @return
	 * GradeVO
	 */
	SubjectCompanyVO getActivityNoteMemInfos(SubjectCompanyVO subjectCompanyVO) throws Exception;


	List<SubjectVO> listOjtClassName(SubjectVO subjectVO) throws Exception;

	List<SubjectVO> listOjtClassStdName(SubjectVO subjectVO) throws Exception;

	String getMinSubjectClass(SubjectVO subjectVO) throws Exception;

}
