
/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * AA    2016. 07. 01.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.commbiz.atchFile.service;

import java.util.List;
import java.util.Map;

import kr.co.sitglobal.oklms.commbiz.atchFile.vo.AtchFileVO;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

 /**
 * ATCH_FILE에 대한 Service Interface 입니다.
 * @author AA
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
public interface AtchFileService {

	/**
	 * ATCH_FILE 에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param atchFileVO
	 * @return
	 * List<AtchFileVO>
	 */
	List<AtchFileVO> listAtchFile(AtchFileVO atchFileVO) throws Exception;
	
	/**
	 * 목록은 조회와 동일한 검색 조건을 수행하였을 때의 총 data건수를 조회하는 SQL 을 호출한다.
	 * @param atchFileVO
	 * @return
	 * @throws Exception
	 * Integer
	 */
	Integer getAtchFileCnt(AtchFileVO atchFileVO) throws Exception;
	
	/**
	 * ATCH_FILE 에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param atchFileVO
	 * @return
	 * AtchFileVO
	 */
	AtchFileVO getAtchFile(AtchFileVO atchFileVO) throws Exception;

	/**
	 * ATCH_FILE 테이블에 Data를 insert 한다.
	 * @param fvoList
	 * @return
	 * @throws Exception
	 * Integer
	 */
	Integer insertAtchFile(List<AtchFileVO> fvoList) throws Exception;
	

	/**
	 * ATCH_FILE 테이블에 Data를 update 한다.
	 * @param udtResult
	 * @return
	 * String
	 */
	int updateAtchFile(List<AtchFileVO> udtResult) throws Exception;

	/**
	 * ATCH_FILE 테이블에 Data를 Delete 하고 파일도 서버에서 삭제한다.
	 * @param atchFileVO
	 * @return
	 * int
	 */
	int deleteAtchFile(List<AtchFileVO> fvoList) throws Exception;

//	/**
//	 * ATCH_FILE 에서 PK값 여부로 Data를 추가 or 수정하는 로직을 수행한다.
//	 * @param atchFileVO
//	 * @return
//	 * @throws Exception
//	 * AtchFileVO
//	 */
//	int saveAtchFile(AtchFileVO atchFileVO) throws Exception;

	

    /**
     * 파일 구분자에 대한 최대값을 구한다.( 파일 아이디에 대한 순번값을 구한다. )
     */
    public Integer getMaxAtchFileSN(AtchFileVO atchFileVO) throws Exception;


	/**
	 * DB 데이터 저장 및 파일 저장을 동시에 처리함.
	 * 화면에서 모두 같은 input name인 경우.( <form method='post' action=...> <input name="atchFiles" type="file">   ,<input name="atchFiles" type="file">   ,<input name="atchFiles" type="file">   < /form >)
	 * @param fileObj : 저장할 파일 객체
	 * @param keyStr : 서버에 저장될 파일명( 접두사 )
	 * @param atchFileId : 값이 없으면 내부적으로 atchFileIdGnrService.getNextStringId(); 호출하여 처리함.
	 * @param storePath : 서버에 저장될 위치. (없으면 Global properties에 저장된 경로로 저장됨.)
	 * @return atchFileId
	 * @throws Exception
	 */
	String saveAtchFile(List<MultipartFile> fileObj, String keyStr, String atchFileId, String storePath) throws Exception;

	/**
	 * DB 데이터 저장 및 파일 저장을 동시에 처리함.
	 * 화면에서 모두 같은 input name인 경우.( <form method='post' action=...> <input name="atchFiles" type="file">   ,<input name="atchFiles" type="file">   ,<input name="atchFiles" type="file">   < /form >)
	 * @param fileObj : 저장할 파일 객체
	 * @param keyStr : 서버에 저장될 파일명( 접두사 )
	 * @param atchFileId : 값이 없으면 내부적으로 atchFileIdGnrService.getNextStringId(); 호출하여 처리함.
	 * @param storePath : 서버에 저장될 위치. (없으면 Global properties에 저장된 경로로 저장됨.)
	 * @param addPath : 서버에 저장될 위치 추가경로.
	 * @return atchFileId
	 * @throws Exception
	 */
	String saveAtchFile(List<MultipartFile> fileObj, String keyStr, String atchFileId, String storePath,String addPath) throws Exception;
	
	/**
	 * DB 데이터 저장 및 파일 저장을 동시에 처리함.
	 * 화면에서 모두 다른 input name인 경우.( <form method='post' action=...> <input name="atchFiles_1" type="file"> ,<input name="atchFiles_2" type="file"> ,<input name="atchFiles_3" type="file"> < /form >)
	 * @param fileObj : 저장할 파일 객체
	 * @param keyStr : 서버에 저장될 파일명( 접두사 )
	 * @param atchFileId : 값이 없으면 내부적으로 atchFileIdGnrService.getNextStringId(); 호출하여 처리함.
	 * @param storePath : 서버에 저장될 위치. (없으면 Global properties에 저장된 경로로 저장됨.)
	 * @return atchFileId
	 * @throws Exception
	 * String
	 */
	String saveAtchFile(Map<String, MultipartFile> fileObj, String keyStr, String atchFileId, String storePathString) throws Exception;

}
