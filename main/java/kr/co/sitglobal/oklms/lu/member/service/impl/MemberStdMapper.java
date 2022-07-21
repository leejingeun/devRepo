
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
package kr.co.sitglobal.oklms.lu.member.service.impl;

import java.util.List;

import kr.co.sitglobal.oklms.lu.member.vo.ExcelMemberStdVO;
import kr.co.sitglobal.oklms.lu.member.vo.MemberStdVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

 /**
 * 프로토타입 게시판 CRUD 쿼리를 마이바티스로 연결하는 클레스.
 * @author 이진근
 * @since 2017. 01. 06.insertMemberChangeApplication
 */
@Mapper("MemberStdMapper")
public interface MemberStdMapper {

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param ExcelMemberStdVO
	 * @return
	 * List<ExcelMemberStdVO>
	 */
	List<ExcelMemberStdVO> listChangeSubjectMemberCot(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param ExcelMemberStdVO
	 * @return
	 * List<ExcelMemberStdVO>
	 */
	List<ExcelMemberStdVO> listChangeMemberCcm(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param ExcelMemberStdVO
	 * @return
	 * List<ExcelMemberStdVO>
	 */
	List<ExcelMemberStdVO> listMemberSubjectCodeMapping(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param ExcelMemberStdVO
	 * @return
	 * List<ExcelMemberStdVO>
	 */
	List<ExcelMemberStdVO> listMemberSubjectCodeMappingDetail(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param ExcelMemberStdVO
	 * @return
	 * List<ExcelMemberStdVO>
	 */
	List<ExcelMemberStdVO> listMemberCot(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * List<MemberStdVO>
	 */
	List<ExcelMemberStdVO> listMemberCcm(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * List<MemberStdVO>
	 */
	List<ExcelMemberStdVO> listMemberStd(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * List<MemberStdVO>
	 */
	List<ExcelMemberStdVO> listPlanResultMemberStd(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * List<MemberStdVO>
	 */
	List<ExcelMemberStdVO> listMemberPrt(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * List<MemberStdVO>
	 */
	List<ExcelMemberStdVO> listMemberChangeApplicationNew(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * List<MemberStdVO>
	 */
	List<ExcelMemberStdVO> listMemberChangeApplication(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * MemberVO
	 */
	ExcelMemberStdVO getMemberChangeApplicationCcn(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * MemberVO
	 */
	ExcelMemberStdVO getMemberChangeApplicationCcm(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * MemberVO
	 */
	String getCompanyName(String companyName) throws Exception;

	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * MemberVO
	 */
	int getMemberCnt(MemberStdVO memberStdVO) throws Exception;

	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * MemberVO
	 */
	int getChangeSubjectMemberCnt(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * MemberVO
	 */
	int getChangeTrangeMemberCnt(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * MemberVO
	 */
	int getCcmMappingCnt(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * MemberVO
	 */
	int getCcmMemberCnt(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * String
	 */
	int insertMemberExcel(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * String
	 */
	int insertSubjCcmMapping(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * String
	 */
	int insertMemberTutMapping(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * String
	 */
	int insertMemberChangeApplication(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * String
	 */
	int updateSubjCcmMapping(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * String
	 */
	int updateMemberChangeApplication(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * String
	 */
	int updateMemberChangeApplicationTut(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * String
	 */
	int updateMemberChangeApplicationApproval(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * String
	 */
	int deleteMemberTutMapping(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param ExcelMemberStdVO
	 * @return
	 * int
	 */
	int deleteMemberList(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param ExcelMemberStdVO
	 * @return
	 * int
	 */
	int deleteMemberCcmList(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param ExcelMemberStdVO
	 * @return
	 * int
	 */
	int deleteChangeMemberCot(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param ExcelMemberStdVO
	 * @return
	 * int
	 */
	int deleteChangeMember(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 정보를 단건 호출하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * String
	 */
	String getMemberTutMappingGgoupName(ExcelMemberStdVO memberStdVO) throws Exception;

	/**
	 * 정보를 단건 호출하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * String
	 */
	String getMemberName(String memSeq) throws Exception;
}
