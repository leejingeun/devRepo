
/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 7. 20.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.la.member.service.impl;

import java.util.List;

import kr.co.sitglobal.oklms.la.member.vo.ExcelMemberVO;
import kr.co.sitglobal.oklms.la.member.vo.MemberVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

 /**
 * 프로토타입 게시판 CRUD 쿼리를 마이바티스로 연결하는 클레스.
 * @author 이진근
 * @since 2016. 7. 20.
 */
@Mapper("memberMapper")
public interface MemberMapper {

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * List<MemberVO>
	 */
	List<MemberVO> listMember(MemberVO memberVO) throws Exception;
	
	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * List<MemberVO>
	 */
	List<ExcelMemberVO> listMemberAllExcelList(ExcelMemberVO excelMemberVO) throws Exception;
	
	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * List<MemberVO>
	 */
	List<MemberVO> listMemberMail(MemberVO memberVO) throws Exception;
	
	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * List<MemberVO>
	 */
	List<ExcelMemberVO> getSearchEmail(ExcelMemberVO excelMemberVO) throws Exception;
	
	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * List<MemberVO>
	 */
	List<ExcelMemberVO> getSearchHpNo(ExcelMemberVO excelMemberVO) throws Exception;

	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * MemberVO
	 */
	MemberVO getMember(MemberVO memberVO) throws Exception;
	
	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * MemberVO
	 */
	int getMemberCnt(MemberVO memberVO) throws Exception;

	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * String
	 */
	int insertMember(MemberVO memberVO) throws Exception;
	
	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * String
	 */
	int insertExcelMember(ExcelMemberVO excelMemberVO) throws Exception;

	/**
	 * 정보를 수정하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * String
	 */ 
	int updateMember(MemberVO memberVO) throws Exception;
	
	/**
	 * 정보를 수정하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * String
	 */ 
	int updateMemberPwInit(MemberVO memberVO) throws Exception;

	/**
	 * 정보를 삭제하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * int
	 */
	int deleteMemberList(MemberVO memberVO) throws Exception;
	
	/**
	 * 정보를 삭제하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * int
	 */
	int deleteMember(MemberVO memberVO) throws Exception;	
	
	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * MemberVO
	 */
	String getMemberSeq(String memberId) throws Exception;
	
	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * MemberVO
	 */
	String getCompanyName(String companyName) throws Exception;

}
