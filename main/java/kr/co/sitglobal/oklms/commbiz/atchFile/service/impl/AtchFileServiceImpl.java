
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
package kr.co.sitglobal.oklms.commbiz.atchFile.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.atchFile.vo.AtchFileVO;
import kr.co.sitglobal.oklms.commbiz.util.AtchFileUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
//import org.apache.commons.beanutils.BeanUtils;

 /**
 * Service Implements 클레스 : 비지니스 로직을 구현하는 클레스.
 * @author AA
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
@Service("atchFileService")
public class AtchFileServiceImpl extends EgovAbstractServiceImpl implements AtchFileService {

	/** ID Generation */
    @Resource(name="atchFileIdGnrService")
    private EgovIdGnrService atchFileIdGnrService;
    
    @Resource(name = "atchFileMapper")
    private AtchFileMapper atchFileMapper;

	@Resource(name = "atchFileUtil")
	private AtchFileUtil atchFileUtil;

	@Override
	public Integer getMaxAtchFileSN(AtchFileVO fvo) throws Exception {
		// TODO Auto-generated method stub
		return atchFileMapper.getMaxAtchFileSN(fvo);
	}
	
	
	
	@Override
	public List<AtchFileVO> listAtchFile(AtchFileVO atchFileVO) throws Exception {
		// TODO Auto-generated method stub
		List<AtchFileVO> data = atchFileMapper.listAtchFile(atchFileVO);
		return data;
	}

	@Override
	public Integer getAtchFileCnt(AtchFileVO atchFileVO) throws Exception {
		// TODO Auto-generated method stub
		return atchFileMapper.getAtchFileCnt(atchFileVO);
	}

	@Override
	public AtchFileVO getAtchFile(AtchFileVO atchFileVO) throws Exception {
		// TODO Auto-generated method stub
		AtchFileVO data = atchFileMapper.getAtchFile(atchFileVO);
		return data;
	}


	@Override
	public String saveAtchFile(List<MultipartFile> fileObj, String keyStr , String atchFileId, String storePath ) throws Exception {
		
		//첨부파일 저장	 		
//		final Map< String , MultipartFile > fileObj = multiRequest.getFileMap();  // 화면에서 모두 다른 input name인 경우.( <form method='post' action=...> <input name="xx_1" type="file"> ,<input name="xx_2" type="file"> ,<input name="xx_3" type="file"> < /form >)
//		final List< MultipartFile > fileObj = multiRequest.getFiles("atchFiles"); // 화면에서 모두 같은 input name인 경우.( <form method='post' action=...> <input name="xx" type="file">   ,<input name="xx" type="file">   ,<input name="xx" type="file">   < /form >)
		if(!fileObj.isEmpty()) {
			
			AtchFileVO fvo = new AtchFileVO();
			fvo.setAtchFileId( atchFileId );
			int cnt = this.getMaxAtchFileSN( fvo );
			
			if( StringUtils.isBlank( atchFileId ) || 1 == cnt ||atchFileId.indexOf("BDAT")>0){
				//게시판첨부파일 키값 게시물키
				if(atchFileId.indexOf("BDAT")<0){
					atchFileId = atchFileIdGnrService.getNextStringId();
				}
				// 1. 실제 파일 저장
				List< AtchFileVO > intResult = atchFileUtil.parseAtchFileInfo( fileObj, keyStr , 0, atchFileId, storePath );
				
				// 2. 파일 정보 DB에 저장
				this.insertAtchFile( intResult );
				
				if( 0 < intResult.size() ){
					atchFileId = intResult.get(0).getAtchFileId();
				}
			}else{
				List< AtchFileVO > udtResult = atchFileUtil.parseAtchFileInfo( fileObj, keyStr , cnt, atchFileId, storePath  );
				
				this.updateAtchFile( udtResult );
			}
		}	
		
		return atchFileId;
	}


	@Override
	public String saveAtchFile(Map<String, MultipartFile> fileObj, String keyStr , String atchFileId, String storePath ) throws Exception {

		
		if(!fileObj.isEmpty()) {
			
			AtchFileVO fvo = new AtchFileVO();
			fvo.setAtchFileId( atchFileId );
			int cnt = this.getMaxAtchFileSN( fvo );
			
			if( StringUtils.isBlank( atchFileId ) || 1 == cnt ){
				atchFileId = atchFileIdGnrService.getNextStringId();
				// 1. 실제 파일 저장
				List< AtchFileVO > intResult = atchFileUtil.parseAtchFileInfo( fileObj, keyStr , 0, atchFileId, storePath );
				
				// 2. 파일 정보 DB에 저장
				this.insertAtchFile( intResult );
				
				if( 0 < intResult.size() ){
					atchFileId = intResult.get(0).getAtchFileId();
				}
			}else{
				List< AtchFileVO > udtResult = atchFileUtil.parseAtchFileInfo( fileObj, keyStr , cnt, atchFileId, storePath  );
				
				this.updateAtchFile( udtResult );
			}
		}	
		
		return atchFileId;
	}


	@Override
	public Integer insertAtchFile(List<AtchFileVO> fvoList) throws Exception {
		
		
      LoginInfo loginInfo = new LoginInfo();
      LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
//	      //BeanUtils.copyProperties(loginVO,atchFileVO); // 객체 전체를 복사 하는 경우. atchFileVO에서 loginVO와 일치하는 모든 필드의 값을 복사한다. 
//      atchFileVO.setSessionMemSeq(loginVO.getSessionMemSeq()); 
		Integer resultCnt = 0;
		for(AtchFileVO fvo : fvoList ){
			if( StringUtils.isNotBlank( fvo.getAtchFileId() ) && StringUtils.isNotBlank( fvo.getFileSn().toString() ) ){
				
				loginInfo.putSessionToVo(fvo); // session의 정보를 VO에 추가.

				Integer fileSn = this.getMaxAtchFileSN(fvo);
				fvo.setFileSn(fileSn);
				
				resultCnt = resultCnt + atchFileMapper.insertAtchFile(fvo);
			}
		}
		return resultCnt;
	}



	@Override
	public int updateAtchFile(List< AtchFileVO > fvoList) throws Exception {
		// TODO Auto-generated method stub

        LoginInfo loginInfo = new LoginInfo();
        LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
//	      //BeanUtils.copyProperties(loginVO,atchFileVO); // 객체 전체를 복사 하는 경우. atchFileVO에서 loginVO와 일치하는 모든 필드의 값을 복사한다. 
//        atchFileVO.setSessionMemSeq(loginVO.getSessionMemSeq()); 
		
		Integer resultCnt = 0;
		for(AtchFileVO fvo : fvoList ){
			if( StringUtils.isNotBlank( fvo.getAtchFileId() ) && StringUtils.isNotBlank( fvo.getFileSn().toString() ) ){
				
				loginInfo.putSessionToVo(fvo); // session의 정보를 VO에 추가.
				int sqlResultInt = atchFileMapper.updateAtchFile(fvo);
				resultCnt = resultCnt + sqlResultInt;
			}
		}
		return resultCnt;
	}


	@Override
	public int deleteAtchFile(List<AtchFileVO> fvoList) throws Exception {

		Integer resultCnt = 0;
		// Data 삭제
		for(AtchFileVO fvo : fvoList ){
			resultCnt = resultCnt + atchFileMapper.deleteAtchFile(fvo);
		}
		
		// 서버 경로에서 파일 삭제.
		for(AtchFileVO fvo : fvoList ){
			String filePath = fvo.getFileSavePath() + File.separator + fvo.getSaveFileName();
			atchFileUtil.deleteFile(filePath);
		}
		
		
		return resultCnt;
	}



	@Override
	public String saveAtchFile(List<MultipartFile> fileObj, String keyStr,String atchFileId, String storePath, String addPath)	throws Exception {
		//첨부파일 저장	 		
//		final Map< String , MultipartFile > fileObj = multiRequest.getFileMap();  // 화면에서 모두 다른 input name인 경우.( <form method='post' action=...> <input name="xx_1" type="file"> ,<input name="xx_2" type="file"> ,<input name="xx_3" type="file"> < /form >)
//		final List< MultipartFile > fileObj = multiRequest.getFiles("atchFiles"); // 화면에서 모두 같은 input name인 경우.( <form method='post' action=...> <input name="xx" type="file">   ,<input name="xx" type="file">   ,<input name="xx" type="file">   < /form >)
		if(!fileObj.isEmpty()) {
			
			AtchFileVO fvo = new AtchFileVO();
			fvo.setAtchFileId( atchFileId );
			int cnt = this.getMaxAtchFileSN( fvo );
			
			if( StringUtils.isBlank( atchFileId ) || 1 == cnt){
				
				atchFileId = atchFileIdGnrService.getNextStringId();
				// 1. 실제 파일 저장
				List< AtchFileVO > intResult = atchFileUtil.parseAtchFileInfo( fileObj, keyStr , 0, atchFileId, storePath , addPath );
				
				// 2. 파일 정보 DB에 저장
				int result = this.insertAtchFile( intResult );
				
				if( 0 < intResult.size() ){
					atchFileId = intResult.get(0).getAtchFileId();
				}
				
				if(result==0){
					atchFileId = "";
				}
				
			}else{
				List< AtchFileVO > udtResult = atchFileUtil.parseAtchFileInfo( fileObj, keyStr , cnt, atchFileId, storePath , addPath );
				
				this.updateAtchFile( udtResult );
			}
		}	
		
		return atchFileId;
	}


}
