package kr.co.sitglobal.oklms.commbiz.file.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 파일 관리: 파일 정보 CRUD에 대한 데이터처리에 대한 Data Access Object implementation Class
 * @author 황상원 (hsw@anamit.com)
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *       수정일                         수정자                              수정내용
 *  ----------------    ------------    ---------------------------
 *  2014.09.25			황상원			최초 생성
 * 
 * </pre>
 */
@SuppressWarnings("serial")
@Component
public class FileDaoImpl implements FileDao {
	
	public FileDaoImpl(){}
	
	private static final Logger logger = LogManager.getLogger(FileDaoImpl.class);
	
	private static int bufferSize = 8192; 
	
	/////////////////////////////	FEATURES METHOD	//////////////////////////
	/**
	* 폴더내 파일 정보 확인
	* @param id (String) 파일명아이디
	* @return List<String> 파일명 리스트 배열
	*/	
    public List<String> getFileListById(String fileUploadDirectory, String fileId){
    	try{
    		List<String> alFileName = new ArrayList<String>();
    		
    		File tempDir = new File(fileUploadDirectory);
    		String[] fileNames = tempDir.list();
    		for(String temp : fileNames){
    			File f = new File(fileUploadDirectory + "/" + temp);
    			
    			if(f.isHidden())
    				continue;
    			
    			if(f.getName().startsWith(fileId))
    				alFileName.add(f.getName());
    		}
    		
    		return alFileName;
    	}catch(Exception e){
    		logger.error("Search getFileName: " + fileId, e);
    		return null;
    	}
    }
//    
//    /**
//	* 임시파일 아카이빙
//	* @param tempFileId 	(String) 임시 파일 아이디
//	* @param fileId 		(String) 파일 아이디
//	* @return 				(boolean) 결과값
//	*/
//	public boolean fileTransferArchiving(String tempFileId, String fileId){
//		try{
//			String fileUploadDirectory = "";
//    		if(tempFileId.length() == 14){
//    			fileUploadDirectory = SecurityUtil.getWebStudioUserConfig().getFileManagerConfig().getFileArchiveDirectory();
//    			fileUploadDirectory += File.separator + fileId.substring(0,4) + File.separator + fileId.substring(4,6) + File.separator + fileId.substring(6,8); 
//    		}else{
//    			fileUploadDirectory = SecurityUtil.getWebStudioUserConfig().getFileManagerConfig().getFileTempUploadDirectory();
//    		}
//    		
//			List<String> alFileName = getFileListById(fileUploadDirectory, tempFileId);
//			if(alFileName == null)
//				return false;
//			
//			boolean blnResult = true;
//			// java JDK 1.4 up NIO
//			for(int i=0;i<alFileName.size();i++){
//				File sourceFile = new File(SecurityUtil.getWebStudioUserConfig().getFileManagerConfig().getFileTempUploadDirectory() + File.separator + alFileName.get(i));
//				String archivePath = SecurityUtil.getWebStudioUserConfig().getFileManagerConfig().getFileArchiveDirectory();
//				archivePath += File.separator + fileId.substring(0,4) + File.separator + fileId.substring(4,6) + File.separator + fileId.substring(6,8);
//				
//				// 폴더 생성
//				FileUtil.forceMkdir(archivePath, false);				
//				archivePath += File.separator + alFileName.get(i).replaceAll(tempFileId, fileId);								
//				
//				FileInputStream inputStream = null;
//				FileOutputStream outputStream = null;
//				ScatteringByteChannel sbc = null;  
//			    GatheringByteChannel gbc = null;
//						
//				try{
//					//스트림 생성	
//					inputStream = new FileInputStream(sourceFile);
//					outputStream = new FileOutputStream(archivePath);
//					
//					sbc = inputStream.getChannel();  
//				    gbc = outputStream.getChannel();  
//				      
//				    ByteBuffer bb = ByteBuffer.allocateDirect(bufferSize);  
//				    while (sbc.read(bb) != -1) {  
//				        bb.flip();  
//				        gbc.write(bb);  
//				        bb.clear();  
//				    }
//				}catch (Exception e){
//					blnResult = false;
//					break;					
//				}finally{
//				   //자원 해제
//					try{inputStream.close();}catch(IOException ioe){}
//				    try{outputStream.close();}catch(IOException ioe){}
//				    try{sbc.close();}catch(IOException ioe){}
//				    try{gbc.close();}catch(IOException ioe){}
//				}
//				// temp file delete
//				sourceFile.delete();
//			}			
//			
//			// java JDK 1.7 NIO2
//			/*
//			for(int i=0;i<alFileName.size();i++){
//				Path copyFrom = Paths.get(SecurityUtil.getWebStudioUserConfig().getFileManagerConfig().getFileTempUploadDirectory(), alFileName.get(i));
//				
//				String archivePath = SecurityUtil.getWebStudioUserConfig().getFileManagerConfig().getFileArchiveDirectory();
//				archivePath += File.separator + fileId.substring(0,4) + File.separator + fileId.substring(4,6) + File.separator + fileId.substring(6,8);
//				
//				FileUtil.forceMkdir(archivePath, false);
//				Path copyTo = Paths.get(archivePath, alFileName.get(i).replaceAll(tempFileId, fileId));
//				
//				try{
//					Files.copy(copyFrom, copyTo, REPLACE_EXISTING);
//					Files.delete(copyFrom);
//				}catch(Exception e){
//					blnResult = false;
//					break;
//				}				
//			}*/
//			
//			return blnResult;
//		}catch(Exception e){
//			logger.error("fileTransferArchiving: " + e);
//    		return false;
//		}
//	}
//	
//	/**
//	* 에디터 첨부 이미지 아카이빙
//	* @param tempFileId 	(String) 임시 파일 아이디
//	* @return 				(boolean) 결과값
//	*/
//	public String fileTransferEditorImageArchive(String tempFileId){
//		try{
//			String fileUploadDirectory = SecurityUtil.getWebStudioUserConfig().getFileManagerConfig().getFileTempUploadDirectory();
//    		List<String> alFileName = getFileListById(fileUploadDirectory, tempFileId);
//			
//			if(alFileName == null)
//				return null;
//			
//			String currentDate = DateUtil.getCurrentDateTime();			
//			// java JDK 1.4 up NIO
//			for(int i=0;i<alFileName.size();i++){
//				File sourceFile = new File(SecurityUtil.getWebStudioUserConfig().getFileManagerConfig().getFileTempUploadDirectory() + File.separator + alFileName.get(i));
//				String archivePath = SecurityUtil.getWebStudioUserConfig().getFileManagerConfig().getEditorArchiveDirectory();
//				archivePath += File.separator + currentDate.substring(0,4) + File.separator + currentDate.substring(4,6) + File.separator + currentDate.substring(6,8);
//				
//				// 폴더 생성
//				FileUtil.forceMkdir(archivePath, false);				
//				archivePath += File.separator + alFileName.get(i);								
//				
//				FileInputStream inputStream = null;
//				FileOutputStream outputStream = null;
//				ScatteringByteChannel sbc = null;  
//			    GatheringByteChannel gbc = null;
//						
//				try{
//					//스트림 생성	
//					inputStream = new FileInputStream(sourceFile);
//					outputStream = new FileOutputStream(archivePath);
//					
//					sbc = inputStream.getChannel();  
//				    gbc = outputStream.getChannel();  
//				      
//				    ByteBuffer bb = ByteBuffer.allocateDirect(bufferSize);  
//				    while (sbc.read(bb) != -1) {  
//				        bb.flip();  
//				        gbc.write(bb);  
//				        bb.clear();  
//				    }
//				}catch (Exception e){
//					return null;			
//				}finally{
//				   //자원 해제
//					try{inputStream.close();}catch(IOException ioe){}
//				    try{outputStream.close();}catch(IOException ioe){}
//				    try{sbc.close();}catch(IOException ioe){}
//				    try{gbc.close();}catch(IOException ioe){}
//				}
//				// temp file delete
//				sourceFile.delete();
//			}
//			
//			String editorImageDomain = SecurityUtil.getWebStudioUserConfig().getFileManagerConfig().getEditorImageDomain();
//			if(StringUtil.trimString(editorImageDomain).equals(""))
//				return "../filemanager/content-image/" + currentDate.substring(0,8) + "/" + tempFileId;			
//			else
//				return editorImageDomain + "/" + currentDate.substring(0,4) + "/" + currentDate.substring(4,6) + "/" + currentDate.substring(6,8) + "/" + tempFileId;  
//			
//		}catch(Exception e){
//			logger.error("fileTransferEditorImageArchive: " + e);
//    		return null;
//		}
//	}
//		
//	/////////////////////////////	LOADING METHOD	//////////////////////////
//	
//	
//	/////////////////////////////	CREATE METHOD	//////////////////////////
//	
//	
//	/////////////////////////////	MODIFY METHOD	//////////////////////////
//	
//	
//	/////////////////////////////	DELETE METHOD	//////////////////////////
//	/**
//	* 파일 삭제
//	* @param fileId 		(String) 파일 아이디
//	* @return 				(boolean) 결과값
//	*/
//	public boolean deleteFile(String fileId){
//		try{
//			String fileUploadDirectory = "";
//    		if(fileId.length() == 14){
//    			fileUploadDirectory = SecurityUtil.getWebStudioUserConfig().getFileManagerConfig().getFileArchiveDirectory();
//    			fileUploadDirectory += File.separator + fileId.substring(0,4) + File.separator + fileId.substring(4,6) + File.separator + fileId.substring(6,8); 
//    		}else{
//    			fileUploadDirectory = SecurityUtil.getWebStudioUserConfig().getFileManagerConfig().getFileTempUploadDirectory();
//    		}
//    		
//			List<String> alFileName = getFileListById(fileUploadDirectory, fileId);
//			if(alFileName == null)
//				return false;
//			
//			boolean blnResult = true; 
//			for(int i=0;i<alFileName.size();i++){
//				
//				String archivePath = SecurityUtil.getWebStudioUserConfig().getFileManagerConfig().getFileArchiveDirectory();
//				archivePath += File.separator + fileId.substring(0,4) + File.separator + fileId.substring(4,6) + File.separator + fileId.substring(6,8);
//				
//				File deleteFile = new File(archivePath + File.separator + alFileName.get(i));
//				try{
//					deleteFile.delete();
//				}catch(Exception e){
//					blnResult = false;
//					break;
//				}
//				
//				// java JDK 1.7 NIO2
//				/*Path delPath = Paths.get(archivePath, alFileName.get(i));						
//				try{
//					Files.delete(delPath);
//				}catch(Exception e){
//					blnResult = false;
//					break;
//				}*/				
//			}
//			
//			return blnResult;
//		}catch(Exception e){
//			logger.error("fileTransferArchiving: " + e);
//    		return false;
//		}
//	}
//	
//	/**
//	* 이미지 에디터 내 파일 삭제
//	* @param delFilePath 		(String) 파일 경로
//	* @return 					(boolean) 결과값
//	*/
//	public boolean deleteEditorImageFile(String delFilePath){
//		try{
//			String editorImageDomain = SecurityUtil.getWebStudioUserConfig().getFileManagerConfig().getEditorImageDomain();
//			delFilePath = delFilePath.replaceAll(editorImageDomain, "");
//			delFilePath = delFilePath.replaceAll("../filemanager/content-image", "");
//			
//			// 외부 경로일 경우
//			if(delFilePath.toLowerCase().indexOf("http://") > -1)
//				return true;
//			
//			String tempFileId = delFilePath.substring(delFilePath.lastIndexOf("/")+1);
//			String fileUploadDirectory = SecurityUtil.getWebStudioUserConfig().getFileManagerConfig().getEditorArchiveDirectory();
//			
//			String[] temp = delFilePath.substring(0, delFilePath.lastIndexOf("/")).split("/");
//			for(int i=0;i<temp.length;i++){
//				if(temp[i].length() > 0){
//					if(temp[i].length() == 8)
//						fileUploadDirectory += File.separator + temp[i].substring(0,4) + File.separator + temp[i].substring(4,6) + File.separator + temp[i].substring(6,8);
//					else
//						fileUploadDirectory += File.separator + temp[i];
//				}				
//			}
//			
//			File tempDir = new File(fileUploadDirectory);
//    		String[] fileNames = tempDir.list();
//    		for(String tempFile : fileNames){
//    			File f = new File(fileUploadDirectory + "/" + tempFile);
//    			
//    			if(f.isHidden())
//    				continue;
//    			
//    			if(f.getName().startsWith(tempFileId))
//    				f.delete();
//    		}			
//			
//			return true;
//		}catch(Exception e){
//			logger.error("fileTransferArchiving: " + e);
//    		return false;
//		}
//	}
}