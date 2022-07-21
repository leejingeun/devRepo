
/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * AA    2016. 7. 20.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.commbiz.atchFile.service.impl;

import java.util.List;

import kr.co.sitglobal.oklms.commbiz.atchFile.vo.AtchFileVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

 /**
 * ATCH_FILE에 대한 CRUD 쿼리를 마이바티스로 연결하는 클레스.
 * @author AA
 * @since 2016. 7. 20.
 */
@Mapper("atchFileMapper")
public interface AtchFileMapper {

	/**
	 * ATCH_FILE의 목록을 조회하는 SQL 을 호출한다.
	 * @param atchFileVO
	 * @return
	 * List<AtchFileVO>
	 */
	List<AtchFileVO> listAtchFile(AtchFileVO atchFileVO) throws Exception;
	/**
	 * ATCH_FILE의 목록을 조회와 동일한 검색 조건을 수행하였을 때의 총 data건수를 조회하는 SQL 을 호출한다.
	 * @param atchFileVO
	 * @return
	 * @throws Exception
	 * Integer
	 */
	Integer getAtchFileCnt(AtchFileVO atchFileVO) throws Exception;

	/**
	 * ATCH_FILE에서 단건 조회하는 SQL 을 호출한다.
	 * @param atchFileVO
	 * @return
	 * AtchFileVO
	 */
	AtchFileVO getAtchFile(AtchFileVO atchFileVO) throws Exception;

	/**
	 *ATCH_FILE에  mergeInto 처리하는 SQL 을 호출한다.
	 * @param atchFileVO
	 * @return
	 * AtchFileVO
	 */
	int saveAtchFile(AtchFileVO atchFileVO) throws Exception;

	/**
	 * ATCH_FILE에 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param atchFileVO
	 * @return
	 * String
	 */
	int insertAtchFile(AtchFileVO atchFileVO) throws Exception;

	/**
	 * ATCH_FILE에 정보를 수정하는 SQL 을 호출한다.
	 * @param atchFileVO
	 * @return
	 * String
	 */
	int updateAtchFile(AtchFileVO atchFileVO) throws Exception;

	/**
	 * ATCH_FILE에서 정보를 삭제하는 SQL 을 호출한다.
	 * @param atchFileVO
	 * @return
	 * int
	 */
	int deleteAtchFile(AtchFileVO atchFileVO) throws Exception;
	/**
	 * 해당 AtchFileID 의 Max + 1 값을 반환한다.</BR>
	 * SELECT NVL(MAX(FILE_SN),0)+1 AS FILE_SN
	 * @param fvo
	 * @return
	 * Integer
	 */
	Integer getMaxAtchFileSN(AtchFileVO fvo);

}
