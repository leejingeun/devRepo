
/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 07. 01.         First Draft.
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.la.member.service;

import java.util.List;

import kr.co.sitglobal.oklms.la.member.vo.ExcelMemberVO;
import kr.co.sitglobal.oklms.la.member.vo.MemberVO;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

 /**
 * 클래스에 대한 내용을 작성한다.
 * @author 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
public interface MemberService {

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param memberVO
	 * @return
	 * List<MemberVO>
	 */
	List<MemberVO> listMember(MemberVO memberVO) throws Exception;
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param memberVO
	 * @return
	 * List<MemberVO>
	 */
	List<ExcelMemberVO> listMemberAllExcelList(ExcelMemberVO excelMemberVO) throws Exception;
	
	/**
	 * DB에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param memberVO
	 * @return
	 * MemberVO
	 */
	MemberVO getMember(MemberVO memberVO) throws Exception;
	
	/**
	 * DB에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param memberVO
	 * @return
	 * MemberVO
	 */
	MemberVO getMember(String memSeq) throws Exception;
	
	/**
	 * DB에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param memberVO
	 * @return
	 * MemberVO
	 */
	String getMemSeqTutor(MemberVO memberVO) throws Exception;
	
	/**
	 * DB에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param memberVO
	 * @return
	 * MemberVO
	 */
	String getMemPhoneNo(MemberVO memberVO) throws Exception;
	
	/**
	 * DB에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param memberVO
	 * @return
	 * MemberVO
	 */
	String getMemNameGet(MemberVO memberVO) throws Exception;
	
	/**
	 * DB에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param memberVO
	 * @return
	 * MemberVO
	 */
	String getMemTutorLessonId(MemberVO memberVO) throws Exception;
	
	/**
	 * DB에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param memberVO
	 * @return
	 * MemberVO
	 */
	String getMemTypeEmail(MemberVO memberVO) throws Exception;
	
	/**
	 * DB에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param memberVO
	 * @return
	 * MemberVO
	 */
	String getMemSeq(MemberVO memberVO) throws Exception;
	
	/**
	 * DB에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param memberVO
	 * @return
	 * MemberVO
	 */
	int getMemberCnt(MemberVO memberVO) throws Exception;

     /**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param memberVO
	 * @return
	 * String
	 */
	String insertMember(MemberVO memberVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param memberVO
	 * @return
	 * String
	 */
	String insertMemberAllExcel(ExcelMemberVO excelMemberVO, MultipartHttpServletRequest multiRequest) throws Exception;

	/**
	 * DB에서 Data를 수정하는 로직을 수행한다.
	 * @param memberVO
	 * @return
	 * String
	 */
	int updateMember(MemberVO memberVO) throws Exception;
	
	/**
	 * DB에서 Data를 수정하는 로직을 수행한다.
	 * @param memberVO
	 * @return
	 * String
	 */
	int updateMemberPassWordInit(MemberVO memberVO) throws Exception;
	
	/**
	 * DB에서 Data를 삭제하는 로직을 수행한다.
	 * @param memberVO
	 * @return
	 * int
	 */
	int deleteMemberList(MemberVO memberVO) throws Exception;

	/**
	 * DB에서 Data를 삭제하는 로직을 수행한다.
	 * @param memberVO
	 * @return
	 * int
	 */
	int deleteMember(MemberVO memberVO) throws Exception;

}
