
/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 12. 14.         First Draft.
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.commbiz.util;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.sitglobal.oklms.commbiz.atchFile.vo.AtchFileVO;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovProperties;

 /**
 * 클래스에 대한 내용을 작성한다.
 * @author AA
 * @since 2016. 8. 8.
 */
@Component("atchFileUtil")
public class AtchFileUtil extends EgovFileMngUtil {


	private static final Logger LOG = Logger.getLogger(AtchFileUtil.class.getName());

	public static boolean checkIsFileName(String fileName) throws Exception {

		if (fileName != null && !fileName.equals("")) {
			String temp = fileName.toUpperCase();

			if (temp.endsWith(".DOC") || temp.endsWith(".GIF") || temp.endsWith(".HWP") || temp.endsWith(".JPEG") || temp.endsWith(".JPG") || temp.endsWith(".PNG")
					|| temp.endsWith(".PDF") || temp.endsWith(".PPT") || temp.endsWith(".TXT") || temp.endsWith(".XLS") || temp.endsWith(".ZIP") || temp.endsWith(".XLSX")) {
				// String regex = "[ㄱ-ㅎ가-힣ㅏ-ㅣa-zA-Z0-9-_/()\\[\\] ]+$";
				// Pattern p = Pattern.compile(regex);
				// Matcher m = p.matcher(fileName.split("[.]")[0]);
				// if(!m.matches()){
				// return false;
				// }
			} else {
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서 17자리의 TIMESTAMP값을 구하는 기능
	 *
	 * @param
	 * @return Timestamp 값
	 * @exception MyException
	 * @see
	 */
	public static String getTimeStamp() {

		String rtnStr = null;

		// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
		String pattern = "yyyyMMddhhmmssSSS";

		try {
			SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
			Timestamp ts = new Timestamp(System.currentTimeMillis());

			rtnStr = sdfCurrent.format(ts.getTime());
		} catch (Exception e) {
			// e.printStackTrace();

			// throw new RuntimeException(e); // 보안점검 후속조치
			LOG.debug("IGNORED: " + e.getMessage());
		}

		return rtnStr;
	}
	
	

	/**
	 * 서버의 특정 위치에 파일을 저장하는 역활을 하는 공통 함수</br>
	 * 서버에 저장될 파일명의 명명 규칙 : newName = keyStr + atchFileUtil.getTimeStamp() + fileKey;
	 * @param file : MultipartFile
	 * @param fileKey : 서버에 저장될 파일명 맨뒤에 붙을 순번 값
	 * @param keyStr : 서버에 저장될 파일명 앞에 붙을 prefix 값
	 * @param storePathString : 저장될 서버에서의 경로.
	 * @param atchFileIdString : DB 에서 식별할수있는 PK 값
	 * @return 저장에 사용된 AtchFileVO 객체.
	 * @throws IllegalStateException
	 * @throws IOException
	 * @throws Exception
	 * AtchFileVO
	 */
	private AtchFileVO getUploadFileInfo(MultipartFile file, int fileKey, String keyStr , String storePathString, String atchFileIdString) throws IllegalStateException, IOException, Exception {

		AtchFileVO fvo = null;
		String filePath = "";
	    
	    String orginFileName = file.getOriginalFilename();

	    LOG.debug("#### orginFileName : " + orginFileName );
	    //--------------------------------------
	    // 원 파일명이 없는 경우 처리
	    // (첨부가 되지 않은 input file type)
	    //--------------------------------------
	    if ("".equals(orginFileName)) {
	    	return fvo;
	    }

	    if( this.checkIsFileName(orginFileName) ){
	    	
	    	int index = orginFileName.lastIndexOf(".");
	    	//String fileName = orginFileName.substring(0, index);
	    	String fileExt = orginFileName.substring(index + 1);
	    	String newName = keyStr + this.getTimeStamp() + fileKey;
	    	long fleSize = file.getSize();
	    	
	    	if (!"".equals(orginFileName)) {
	    		filePath = storePathString + File.separator + newName;
	    		file.transferTo(new File(EgovWebUtil.filePathBlackList(filePath)));
	    	}
	    	fvo = new AtchFileVO();
	    	fvo.setFileExtn(fileExt);
	    	fvo.setFileSavePath(storePathString);
	    	fvo.setFileSize(fleSize);
	    	fvo.setOrgFileName(orginFileName);
	    	fvo.setSaveFileName(newName);
	    	fvo.setAtchFileId(atchFileIdString);
	    	fvo.setFileSn(fileKey);
	    	
	    	LOG.debug("#### AtchFileVO.toString : " + fvo.toString() );
	    	//writeFile(file, newName, storePathString);
	    }
		return fvo;
	}

	/**
	 * [File 저장 type 2]</br>
	 * MultipartFile 로 넘어온 File을 서버의 특정 경로에 저장한다.</br>
	 * 화면에서 모두 같은 input name인 경우. <pre>(  < form method='post' action=...> < input name="xx" type="file"> ,< input name="xx" type="file"> ,< input name="xx" type="file"> </ form >  ) </pre>  
	 * @param files 		: MultipartHttpServletRequest 넘어온 파일 (배열형태).
	 * @param keyStr 		: 서버에 저장될 파일명( 접두사 )
	 * @param fileKeyParam 	: 서버에 저장될 파일명( 접미사 : 같은 PK에대한 순번)
	 * @param atchFileId 	: 첨부파일 Table에 저장할 PKey 정보
	 * @param storePath 	: 서버에 저장될 위치. (없으면 Global properties ( "Globals.fileStorePath" )에 저장된 경로로 저장됨.)
	 * @return
	 * @throws Exception
	 * List<AtchFileVO>
	 */
    public List<AtchFileVO> parseAtchFileInfo(List<MultipartFile> files, String keyStr, int fileKeyParam, String atchFileId, String storePath) throws Exception {
		
		int fileKey = fileKeyParam;

		String storePathString = EgovProperties.getProperty(StringUtils.defaultIfEmpty(storePath, "Globals.fileStorePath"));

		File saveFolder = new File(EgovWebUtil.filePathBlackList(storePathString));
	
		if (!saveFolder.exists() || saveFolder.isFile()) {
		    saveFolder.mkdirs();
		}
	
		Iterator<MultipartFile> itr = files.iterator();
				
		List<AtchFileVO> result  = new ArrayList<AtchFileVO>();
	
		if( StringUtils.isNotBlank( atchFileId ) ){
			
			while (itr.hasNext()) {
				
				MultipartFile file = itr.next();
				
				long fleSize = file.getSize();
				
				AtchFileVO fvo = getUploadFileInfo( file , fileKey , keyStr , storePathString , atchFileId );
				if( null != fvo ){
					result.add(fvo);
					++fileKey;		    	
				}
			}
		}
	
		return result;
    }
	/**
	 * [File 저장 type 2]</br>
	 * MultipartFile 로 넘어온 File을 서버의 특정 경로에 저장한다.</br>
	 * 화면에서 모두 같은 input name인 경우. <pre>(  < form method='post' action=...> < input name="xx" type="file"> ,< input name="xx" type="file"> ,< input name="xx" type="file"> </ form >  ) </pre>  
	 * @param files 		: MultipartHttpServletRequest 넘어온 파일 (배열형태).
	 * @param keyStr 		: 서버에 저장될 파일명( 접두사 )
	 * @param fileKeyParam 	: 서버에 저장될 파일명( 접미사 : 같은 PK에대한 순번)
	 * @param atchFileId 	: 첨부파일 Table에 저장할 PKey 정보
	 * @param storePath 	: 서버에 저장될 위치. (없으면 Global properties ( "Globals.fileStorePath" )에 저장된 경로로 저장됨.)
	 * @param addPath 	: 서버에 저장될 위치 하위폴더추가. (없으면 Global properties ( "Globals.fileStorePath" )에 저장된 경로로 저장됨.)
	 * @return
	 * @throws Exception
	 * List<AtchFileVO>
	 */
    public List<AtchFileVO> parseAtchFileInfo(List<MultipartFile> files, String keyStr, int fileKeyParam, String atchFileId, String storePath,String addPath) throws Exception {
		
		int fileKey = fileKeyParam;

		String storePathString = EgovProperties.getProperty(StringUtils.defaultIfEmpty(storePath, "Globals.fileStorePath"))  + addPath+ "/";

		File saveFolder = new File(EgovWebUtil.filePathBlackList(storePathString));
	
		if (!saveFolder.exists() || saveFolder.isFile()) {
		    saveFolder.mkdirs();
		}
	
		Iterator<MultipartFile> itr = files.iterator();
				
		List<AtchFileVO> result  = new ArrayList<AtchFileVO>();
	
		if( StringUtils.isNotBlank( atchFileId ) ){
			
			while (itr.hasNext()) {
				
				MultipartFile file = itr.next();
				
				long fleSize = file.getSize();
				
				AtchFileVO fvo = getUploadFileInfo( file , fileKey , keyStr , storePathString , atchFileId );
				if( null != fvo ){
					result.add(fvo);
					++fileKey;		    	
				}
			}
		}
	
		return result;
    }
	/**
	 * [File 저장 type 1]</br>
	 * MultipartFile 로 넘어온 File을 서버의 특정 경로에 저장한다.</br>
	 * 화면에서 모두 다른 input name인 경우.(<pre> < form method='post' action=...> < input name="xx_1" type="file"> ,< input name="xx_2" type="file"> ,< input name="xx_3" type="file"> < / form > </pre>)
	 * @param files 		: MultipartHttpServletRequest 넘어온 파일(단건).
	 * @param keyStr 		: 서버에 저장될 파일명( 접두사 )
	 * @param fileKeyParam 	: 서버에 저장될 파일명( 접미사 : 같은 PK에대한 순번)
	 * @param atchFileId 	: 첨부파일 Table에 저장할 PKey 정보
	 * @param storePath 	: 서버에 저장될 위치. (없으면 Global properties ( "Globals.fileStorePath" )에 저장된 경로로 저장됨.)
	 * @return
	 * @throws Exception : framework/spring/context-common.xml  -> <bean id="spring.RegularCommonsMultipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
	 * List<AtchFileVO>
	 */
    public List<AtchFileVO> parseAtchFileInfo(Map<String, MultipartFile> files, String keyStr, int fileKeyParam, String atchFileId, String storePath) throws Exception {
		
		int fileKey = fileKeyParam;

		String storePathString = EgovProperties.getProperty(StringUtils.defaultIfEmpty(storePath, "Globals.fileStorePath"));

		File saveFolder = new File(EgovWebUtil.filePathBlackList(storePathString));
	
		if (!saveFolder.exists() || saveFolder.isFile()) {
		    saveFolder.mkdirs();
		}
	
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
				
		List<AtchFileVO> result  = new ArrayList<AtchFileVO>();
		
		if( StringUtils.isNotBlank( atchFileId ) ){
			
			while (itr.hasNext()) {
				
				Entry<String, MultipartFile> entry = itr.next();
				MultipartFile file = entry.getValue();
				
				long fleSize = file.getSize();
				
				AtchFileVO fvo = getUploadFileInfo( file , fileKey , keyStr , storePathString , atchFileId );
				if( null != fvo ){
					result.add(fvo);
					++fileKey;		    	
				}
			}
		}
		
	
		return result;
    }

	public static boolean deleteFile(String filePath) throws Exception {
		boolean retStatus;
		
		File uf = new File(filePath);
		if(uf.exists() && uf.isFile() ){	//파일 삭제
			retStatus = uf.delete();
		}else{
			retStatus = false;
		}
		
		return retStatus;
	}
	
	

	private String getBrowser( HttpServletRequest request ) {
		String header = request.getHeader( "User-Agent" );
		if (header.indexOf( "MSIE" ) > -1) {
			return "MSIE";
		} else if (header.indexOf( "Trident" ) > -1) { // IE11 문자열 깨짐 방지
			return "Trident";
		} else if (header.indexOf( "Chrome" ) > -1) {
			return "Chrome";
		} else if (header.indexOf( "Opera" ) > -1) {
			return "Opera";
		}
		return "Firefox";
	}
	
	/**
	 * Disposition 지정하기.
	 * @param filename
	 * @param request
	 * @param response
	 * @throws Exception
	 * void
	 */
	public void setDisposition( String filename, HttpServletRequest request, HttpServletResponse response ) throws Exception {
		
		String browser = this.getBrowser( request );

		String encodedFilename = this.getAttachFileName(request, filename);

		String dispositionPrefix = "attachment; filename=";
		response.setHeader( "Content-Disposition", dispositionPrefix + encodedFilename );

		if ("Opera".equals( browser )) {
			response.setContentType( "application/octet-stream;charset=UTF-8" );
		}
	}
	/**
	 * 브라우저 종류별로 다운로드 파일명을 엔코딩 한다.
	 * 
	 * @param request
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	private static String getAttachFileName(HttpServletRequest request, String filename) throws Exception {
		// 브라우저 종류를 알아낸다.
		String browser = "Firefox";
		String header = request.getHeader("User-Agent");

		if (header.indexOf("MSIE") > -1)
			browser = "MSIE";
		else if (header.indexOf("Trident") > -1)
			browser = "Trident";				
		else if (header.indexOf("Chrome") > -1)
			browser = "Chrome";
		else if (header.indexOf("Opera") > -1)
			browser = "Opera";

		// 브라우저 종류별로 파일명을 엔코딩한다.
		String encodedFilename = null;
		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		}else if (browser.equals("Trident")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");			
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~')
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				else
					sb.append(c);
			}
			encodedFilename = sb.toString();
		} else {
			throw new RuntimeException("Not supported browser");
		}

		return encodedFilename;
	}

	

}
