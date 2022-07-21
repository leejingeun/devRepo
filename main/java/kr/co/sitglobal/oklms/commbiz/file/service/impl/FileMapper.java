package kr.co.sitglobal.oklms.commbiz.file.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 코드 관리: 코드정보 CRUD에 대한 데이터처리 Mapper Class
 * @author hsw (hsw@anamit.com)
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *       수정일                         수정자                              수정내용
 *  ----------------    ------------    ---------------------------
 *  2014.06.03          hsw             최초 생성
 * 
 * </pre>
 */

@Repository(value="fileMapper")
public interface FileMapper {
	
	/////////////////////////////	CHECK METHOD	//////////////////////////
	public int isFileCheck(Map<String, Object> command) throws Exception;
	public int isFileRelationInfoCheck(Map<String, Object> command) throws Exception;
	
	/////////////////////////////	LOADING METHOD	//////////////////////////
	public int getTempFileListTotalCount(@Param("command") Map<String, Object> command) throws Exception;	
	public List<Map<String, Object>> getTempFileList(@Param("command") Map<String, Object> command) throws Exception;
	public int getFileListTotalCount(@Param("command") Map<String, Object> command) throws Exception;
	public List<Map<String, Object>> getFileList(@Param("command") Map<String, Object> command) throws Exception;
	public Map<String, Object> getTempFile(@Param("command") Map<String, Object> command) throws Exception;
	public Map<String, Object> getFile(@Param("command") Map<String, Object> command) throws Exception;
	public List<Map<String, Object>> getFileRelation(@Param("command") Map<String, Object> command) throws Exception;	
	public Map<String, Object> getFile1(@Param("command") Map<String, Object> command) throws Exception;
	
	/////////////////////////////	CREATE METHOD	//////////////////////////
	public int createTempFile(@Param("command") Map<String, Object> command) throws Exception;
	public int createFile(@Param("command") Map<String, Object> command) throws Exception;
	public int createFileRelation(@Param("command") Map<String, Object> command) throws Exception;
	
	
	/////////////////////////////	MODIFY METHOD	//////////////////////////
	public int modifyTempFile(@Param("command") Map<String, Object> command) throws Exception;
	public int modifyFile(@Param("command") Map<String, Object> command) throws Exception;
	
	
	/////////////////////////////	DELETE METHOD	//////////////////////////
	public int deleteTempFile(@Param("command") Map<String, Object> command) throws Exception;
	public int deleteFile(@Param("command") Map<String, Object> command) throws Exception;
	public int deleteFileRelationInfo(@Param("command") Map<String, Object> command) throws Exception;
}
