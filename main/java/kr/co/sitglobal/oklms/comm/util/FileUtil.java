package kr.co.sitglobal.oklms.comm.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.List;
import java.util.Stack;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.mozilla.universalchardet.UniversalDetector;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * 파일 Util: 파일 처리를 위한 콤포넌트 class
 * @author hsw (hsw@anamit.com)
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *       수정일                         수정자                              수정내용
 *  ----------------    ------------    ---------------------------
 *  2014.10.16			황상원			최초 생성
 * 
 * </pre>
 */
@SuppressWarnings("serial")
@Component
public class FileUtil{
	
	private static final Logger logger = LogManager.getLogger(FileUtil.class);
	
	// constructor
    public FileUtil() {}
    
    /**
	 * 파일 존재 여부 체크
	 * @param		(String) 	path 파일 경로
	 * @param		(boolean)	isMake 생성여부 (없으면 생성)
	 * @return 		(boolean)	결과값
	 */
    public static boolean checkFileExistence(String path,boolean isMake)	{
        try{
            File tempFile = new File(path);
            
            return checkFileExistence(tempFile,isMake);
		}catch(Exception e){
			logger.error(e.toString());
			return false;
		}
	}
    
    /**
	 * 파일 존재 여부 체크
	 * @param		(File) 	File 파일
	 * @param		(boolean)	isMake 생성여부 (없으면 생성)
	 * @return 		(boolean)	결과값
	 */
    public static boolean checkFileExistence(File tempFile,boolean isMake)	{
        try{
            if(tempFile.exists())
            	return true;
            
            if(isMake)
                tempFile.createNewFile();
            
			return true;
		}catch(Exception e){
			logger.error(e.toString());
			return false;
		}
	}
    
    /**
	 * 폴더 존재 여부 체크 후 생성
	 * @param		(String) 	path 경로
	 * @param		(boolean)	isOverwrite 덮어쓰기 (없으면 생성)
	 * @return 		(boolean)	결과값
	 */ 
    public static boolean forceMkdir(String path,boolean isOverwrite)	{
        try{
            File tempFile = new File(path);
            
            return forceMkdir(tempFile,isOverwrite);
		}catch(Exception e){
			logger.error(e.toString());
			return false;
		}
	}
    
    /**
	 * 폴더 존재 여부 체크 후 생성
	 * @param		(File) 		File 경로
	 * @param		(boolean)	isOverwrite 덮어쓰기 (없으면 생성)
	 * @return 		(boolean)	결과값
	 */
    public static boolean forceMkdir(File tempFile,boolean isOverwrite)	{
        try{
            if(tempFile.exists()){
                if(isOverwrite)
                	forceDelete(tempFile);
                else
                	return true;
            }
                        
            org.apache.commons.io.FileUtils.forceMkdir(tempFile);
			return true;
		}catch(Exception e){
			logger.error(e.toString());
			return false;
		}
	}
    
    /**
	 * 파일 삭제
	 * @param		(String) 	path 경로
	 * @return 		(boolean)	결과값
	 */ 
    public static boolean forceDelete(String path)	{
        try{
            File tempFile = new File(path);
            
            return forceDelete(tempFile);
		}catch(Exception e){
			logger.error(e.toString());
			return false;
		}
	}
    
    /**
	 * 파일 삭제
	 * @param		(File) 		File 경로
	 * @return 		(boolean)	결과값
	 */
    public static boolean forceDelete(File tempFile)	{
        try{
            if(!tempFile.exists())	
            	return true;
            
            org.apache.commons.io.FileUtils.forceDelete(tempFile);
            
            return true;
		}catch(Exception e){
			logger.error(e.toString());
			return false;
		}
	}    

    /**
	 * 파일 삭제
	 * @param		(String)	dirPath 디렉토리 경로
	 * @param		(String)	filterFileName 필터파일명 (해당 파일명을 포함한 파일은 삭제에서 제외)
	 * @return 		(boolean)	결과값
	 */
    public static boolean forceDelete(String dirPath,String filterFileName)	{
        try{
        	File dirFile = new File(dirPath);        	
        	File[] files = dirFile.listFiles();
        	for(int i=0;i<files.length;i++) {
        		File tempFile = files[i];
        		
        		if(tempFile.getName().indexOf(filterFileName) < 0)
        			continue;
        		
        		forceDelete(tempFile);        		
        	}
        	
            return true;
		}catch(Exception e){
			logger.error(e.toString());
			return false;
		}
	}
    
    /**
	 * 폴더 사이즈(용량)
	 * @param		(String)	filePath 디렉토리 경로
	 * @return 		(long)		폴더 사이즈(용량)
	 */
    public static long sizeOfDirectory(String filePath){
        try{
            File tempFile = new File(filePath);
			return sizeOfDirectory(tempFile);
		}catch(Exception e) {
		    logger.error(e.toString());
			return -1;
		}
	}
	
    /**
	 * 폴더 사이즈(용량)
	 * @param		(File)		directory 디렉토리 경로
	 * @return 		(long)		폴더 사이즈(용량)
	 */
    public static long sizeOfDirectory(File directory){
		try{
			if(!directory.exists())	return 0;
			
        	return org.apache.commons.io.FileUtils.sizeOfDirectory(directory);
        }catch(Exception e) {
            logger.error(e.toString());
        	return -1;
        }
    }
    
    /**
   	 * 파일 읽기
   	 * @param		(String)	filePath 파일경로
   	 * @param		(String)	encode 파일인코딩 타입 (ex: utf-8)
   	 * @return 		(String)	파일 내용
   	 */    
	public static String readFile(String filePath,String encode){
		try{
			File tempFile = new File(filePath);
			return readFile(tempFile,encode);
		}catch(Exception e){
		    logger.error(e.toString());
			return null;
		}
	}

	 /**
   	 * 파일 읽기
   	 * @param		(File)		tempFile 파일경로
   	 * @param		(String)	encode 파일인코딩 타입 (ex: utf-8)
   	 * @return 		(String)	파일 내용
   	 */ 
	public static String readFile(File tempFile,String encode){
		try{		    
		    if(tempFile.exists()){
		        if(!tempFile.canRead()){
		        	logger.error(tempFile.getAbsolutePath() + " 의 파일을 읽을 수 없습니다.");
		            return null;
		        }
		    }else{
		        return null;
		    }
		    
            return org.apache.commons.io.FileUtils.readFileToString(tempFile,encode);
		}catch(Exception e){
		    logger.error(e.toString());
			return null;
		}
	}
	
	/**
   	 * 파일 쓰기
   	 * @param		(String)	path 파일경로
   	 * @param		(String)	path 파일내용
   	 * @param		(String)	encode 파일인코딩 타입 (ex: utf-8)
   	 * @return 		(boolean)	결과값
   	 */  
	public static boolean writeStringToFile(String path,String content,String encode){
		try{            
	        return writeStringToFile(new File(path),content,encode);
		}catch(Exception e){
		    logger.error(e.toString());
			return false;
		}
	}	
	
	/**
   	 * 파일 쓰기
   	 * @param		(File)		tempFile 파일경로
   	 * @param		(String)	path 파일내용
   	 * @param		(String)	encode 파일인코딩 타입 (ex: utf-8)
   	 * @return 		(boolean)	결과값
   	 */ 
	public static boolean writeStringToFile(File tempFile,String content,String encode){
		try{
		    if(tempFile.exists()){
		        if(!tempFile.canWrite()){
		        	logger.error(tempFile.getAbsolutePath() + " is not allowed to write!");		            		            
		            return false;
		        }
		    }
		    		    
		    org.apache.commons.io.FileUtils.writeStringToFile(tempFile,content,encode);

	        return true;
		}catch(Exception e){
		    logger.error(e.toString());
			return false;
		}
	}

	/**
   	 * 파일 URL 복사
   	 * @param		(URL)		url 경로
   	 * @param		(File)		File 생성 파일명
   	 * @return 		(boolean)	결과값
   	 */ 
	public static boolean copyURLToFile(URL url,File file){
		try{		    
            org.apache.commons.io.FileUtils.copyURLToFile(url,file);
            return true;
		}catch(Exception e){
		    logger.error(e.toString());
			return false;
		}	    
	}
	
	/**
   	 * 파일명 변경
   	 * @param		(String)	path 원본파일명
   	 * @param		(String)	targetFileName 복사파일
   	 * @return 		(boolean)	결과값
   	 */ 
	public static boolean renameFile(String path,String targetFileName)	{
        try{
            File tempFile = new File(path);
            
            return renameFile(tempFile,targetFileName);
		}catch(Exception e){
			logger.error(e.toString());
			return false;
		}
	}
	
	/**
   	 * 파일명 변경
   	 * @param		(File)		tempFile 원본파일명
   	 * @param		(String)	targetFileName 복사파일
   	 * @return 		(boolean)	결과값
   	 */
	public static boolean renameFile(File tempFile,String targetFileName)	{
        try{
            if(!tempFile.exists()){
            	logger.error("There is no " + tempFile.getAbsolutePath());
                return false;
            }
            
            String path = getPathWithoutFileName(tempFile);             
            File newFile = new File(path + "/" + targetFileName);
            
            return tempFile.renameTo(newFile);            
		}catch(Exception e){
			logger.error(e.toString());
			return false;
		}
	}
	
	/**
   	 * 전체 경로 중 파일명을 제외한 폴더 경로
   	 * @param		(String)	path 전체 파일 경로
   	 * @return		(String)	path 폴더 경로
   	 */ 
	public static String getPathWithoutFileName(String path)	{
        try{
            path = StringUtil.replaceAll(path,"\\","/");
            
            int i = path.lastIndexOf("/");            
            if(i<0)
                return path;
            
            path = path.substring(0,i);
            
            return path;            
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}
	}
    
	/**
   	 * 전체 경로 중 파일명을 제외한 폴더 경로
   	 * @param		(File)		File 파일
   	 * @return		(String)	path 폴더 경로
   	 */ 
	public static String getPathWithoutFileName(File tempFile)	{
        try{
            return getPathWithoutFileName(tempFile.getAbsolutePath());            
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}
	}
	
	/** 
	 * 파입 압축 시작
	 * @param targetPath	(String) 압축대상 폴더 or 파일경로
	 * @param zipPath		(String) 압축파일 경로
	 * @return
	 */
	public static boolean createZipFile(String targetPath, String zipPath){
		try{
			return createZipFile(targetPath, zipPath, "UTF-8", false);
		}catch(Exception e){
			logger.error(e.toString());
			return false;
		}
        
	}
	
	/**
	 * 파일 압축 시작
	 * @param targetPath	(String) 압축대상 폴더 or 파일경로
	 * @param zipPath		(String) 압축파일 경로
	 * @param isDirCre		(boolean) 압축파일 경로 폴더 생성 여부
	 * @return
	 */
	public static boolean createZipFile(String targetPath, String zipPath, boolean isDirCre){
		try{
			File zippedFile = new File(zipPath);
			if(!zippedFile.exists()){
				if(zipPath.toLowerCase().indexOf(".zip") > -1){
					if(isDirCre){						
			        	//디렉토리가 없을경우 생성
			        	forceMkdir(getPathWithoutFileName(zipPath), false);
			        }
				}else{
					if(isDirCre){
			        	//디렉토리가 없을경우 생성
			        	forceMkdir(zippedFile, false);
			        }
				}
			}               
	        
	        return createZipFile(targetPath, zipPath, "UTF-8", false);
		}catch(Exception e){
			logger.error(e.toString());			
			return false;
		}
	}
	
	/** 
	 * 파입 압축 시작
	 * @param targetPath	(String) 압축대상 폴더 or 파일경로
	 * @param zipPath		(String) 압축파일 경로
	 * @param charsetName	(String) 인코딩 (ex: utf-8, euc-kr)
	 * @param includeSrc	(String) 하위폴더 포함 여부
	 * @return
	 */
	public static boolean createZipFile(String targetPath, String zipPath, String charsetName, boolean includeSrc){
		try{
			File zippedFile = new File(zipPath);
			if(!zippedFile.exists()){
				if(zipPath.toLowerCase().indexOf(".zip") > -1){
					zippedFile.createNewFile();
				}else{
					forceMkdir(zippedFile, false);
					zippedFile = new File(zipPath + ".zip");
					zippedFile.createNewFile();					
				}					
			}				
			
			OutputStream os = new FileOutputStream(zippedFile);
			File tartgetSrc = new File(targetPath);
			
			ZipArchiveOutputStream zos = new ZipArchiveOutputStream(os);
			if(StringUtil.trimString(charsetName).equals(""))
				charsetName = "UTF-8";
			
			zos.setEncoding(charsetName);
			FileInputStream fis;
			
			int length ;
			ZipArchiveEntry ze ;
			byte [] buf = new byte[8 * 1024];
			String name ;
			
			Stack<File> stack = new Stack<File>();
			File root ;
			if (tartgetSrc.isDirectory()) {
				if(includeSrc){
					stack.push(tartgetSrc);
					root = tartgetSrc.getParentFile();
				}else{
					File [] fs = tartgetSrc.listFiles();
					for (int i = 0; i < fs.length; i++){
						stack.push(fs[i]);
					}
					root = tartgetSrc;
				}
			}else{
				stack.push(tartgetSrc);
				root = tartgetSrc.getParentFile();
			}
			
			while (!stack.isEmpty()){
				File f = stack.pop();
				name = toPath(root, f);
				if(f.isDirectory()){
					File [] fs = f.listFiles();
					for (int i = 0; i < fs.length; i++) {
						if(fs[i].isDirectory())
							stack.push(fs[i]);
						else
							stack.add(0, fs[i]);
					}
				}else{
					ze = new ZipArchiveEntry(name);
					zos.putArchiveEntry(ze);
					fis = new FileInputStream(f);
					while ( (length = fis.read(buf, 0, buf.length)) >= 0 ){
						zos.write(buf, 0, length);
					}
					fis.close();
					zos.closeArchiveEntry();
				}
			}
			zos.close();
			
			return true;
		}catch(Exception e){
			logger.error(e.toString());
			return false;
		}
	}
	
	private static String toPath(File root, File dir){
		String path = dir.getAbsolutePath();
		path = path.substring(root.getAbsolutePath().length()).replace(File.separatorChar, '/');
		if ( path.startsWith("/")) path = path.substring(1);
		if ( dir.isDirectory() && !path.endsWith("/")) path += "/" ;
		return path ;
	}
	
	/**
	 * 파일 압축 시작
	 * @param targetFiles	(String[]) 압축 대상 파일 목록
	 * @param zipPath		(String) 압축 파일  경로
	 * @return
	 */
	public static boolean createZipFile(String[] targetFiles, String zipPath){
		try{
			return createZipFile(targetFiles, zipPath, "UTF-8", false);			
		}catch(Exception e){
			logger.error(e.toString());
			return false;
		}        
	}
	
	/**
	 * 파일 압축 시작
	 * @param targetFiles	(String[]) 압축 대상 파일 목록
	 * @param zipPath		(String) 압축 파일  경로
	 * @param isDirCre		(boolean) 압축파일 경로 폴더 생성 여부
	 * @return
	 */
	public static boolean createZipFile(String[] targetFiles, String zipPath, String charsetName, boolean includeSrc){
		try{
			File zippedFile = new File(zipPath);
			if(!zippedFile.exists()){
				if(zipPath.toLowerCase().indexOf(".zip") > -1)
					forceMkdir(zippedFile, false);
				else
					zippedFile.createNewFile();
			}
			
			OutputStream os = new FileOutputStream(zippedFile);
			File tartgetSrc = new File(zipPath);
			if(tartgetSrc.isDirectory()){
	        	//디렉토리가 없을경우 생성
	        	forceMkdir(tartgetSrc, false);
			}else{
				tartgetSrc = new File(tartgetSrc.getParent());
	        	//디렉토리가 없을경우 생성
	        	forceMkdir(tartgetSrc, false);
			}
			
			ZipArchiveOutputStream zos = new ZipArchiveOutputStream(os);
			if(StringUtil.trimString(charsetName).equals(""))
				charsetName = "UTF-8";
			
			zos.setEncoding(charsetName);
			FileInputStream fis;
			
			int length ;
			ZipArchiveEntry ze ;
			byte [] buf = new byte[8 * 1024];
			String name ;
			
			Stack<File> stack = new Stack<File>();
			File root ;
			for(int i=0;i<targetFiles.length;i++){
				File targetFile = new File(targetFiles[i]);
				stack.push(targetFile);
			}
			root = tartgetSrc;
			
			while (!stack.isEmpty()){
				File f = stack.pop();
				name = toPath(root, f);
				if(f.isDirectory()){
					File [] fs = f.listFiles();
					for (int i = 0; i < fs.length; i++) {
						if(fs[i].isDirectory())
							stack.push(fs[i]);
						else
							stack.add(0, fs[i]);
					}
				}else{
					ze = new ZipArchiveEntry(name);
					zos.putArchiveEntry(ze);
					fis = new FileInputStream(f);
					while ( (length = fis.read(buf, 0, buf.length)) >= 0 ){
						zos.write(buf, 0, length);
					}
					fis.close();
					zos.closeArchiveEntry();
				}
			}
			zos.close();
	        
	        return true;
		}catch(Exception e){
			logger.error(e.toString());
			return false;
		}	
	}
	
	/**
	 * 압축 풀기
	 * @param targetZip		(String) 압축 대상 zip파일 경로
	 * @param completeDir	(String) 압축 해제 경로
	 * @return
	 */
	public static boolean unZipFile(String targetZip, String completeDir){
		try{
			return unZipFile(targetZip, completeDir, "UTF-8");
		}catch(Exception e){
			logger.error(e.toString());
			return false;
		}        
	}
	
	/**
	 * 압축 풀기
	 * @param targetZip		(String) 압축 대상 zip파일 경로
	 * @param completeDir	(String) 압축 해제 경로
	 * @param isDirCre		(boolean) 압축 해제 경로 폴더 생성 여부
	 * @return
	 */
	public static boolean unZipFile(String targetZip, String completeDir, boolean isDirCre){
		try{
			File dir = new File(completeDir);
			if(dir.isDirectory()){
				if(isDirCre){
		        	//디렉토리가 없을경우 생성
		        	forceMkdir(dir, false);
		        }
			}else{
				dir = new File(dir.getParent());
				if(isDirCre){
		        	//디렉토리가 없을경우 생성
		        	forceMkdir(dir, false);
		        }
			}
			
			return unZipFile(targetZip, completeDir, "UTF-8");
		}catch(Exception e){
			logger.error(e.toString());
			return false;
		}
	}
	
	/**
	 * 압축 풀기
	 * @param targetZip		(String) 압축 대상 zip파일 경로
	 * @param completeDir	(String) 압축 해제 경로
	 * @param isDirCre		(boolean) 압축 해제 경로 폴더 생성 여부
	 * @return
	 */
	public static boolean unZipFile(String targetZip, String completeDir, String charsetName){
		try{
			File zippedFile = new File(targetZip);
			InputStream is = new FileInputStream(zippedFile);
			ZipArchiveInputStream zis;
			ZipArchiveEntry entry;
			String name;
			File target;
			int nWritten = 0;
			BufferedOutputStream bos;
			byte [] buf = new byte[1024 * 8];
			
			if(StringUtil.trimString(charsetName).equals(""))
				charsetName = "UTF-8";
			
			File destDir = new File(completeDir);
			if(!destDir.exists())
				forceMkdir(destDir, false);

			zis = new ZipArchiveInputStream(is, charsetName, false);
			while ((entry = zis.getNextZipEntry()) != null){
				name = entry.getName();
				target = new File (destDir, name);
				if(entry.isDirectory()){
					target.mkdirs(); /*  does it always work? */
				}else{
					target.createNewFile();
					bos = new BufferedOutputStream(new FileOutputStream(target));
					while ((nWritten = zis.read(buf)) >= 0 ){
						bos.write(buf, 0, nWritten);
					}
					bos.close();
				}
			}
			zis.close();
			
			return true;
		}catch(Exception e){
			logger.error(e.toString());
			return false;
		}
	}
	
	/**
	 * CSV 파일 생성
	 * @param alContent		필수(List<List<String>>) 내용
	 * @param outputPath	필수(String) CSV 파일 생성 경로/파일명 포함
	 * @param charsetName	(String) 인코딩 - 한글이 포함되었을 경우 반드시 EUC-KR 사용
	 * @param separator		(String) CSV 구분자
	 * @return
	 */
	public static boolean makeCsvFile(List<List<String>> alContent, String outputPath, String charsetName, String separator){
		try{
			return makeCsvFile(alContent, outputPath, charsetName, separator, null);
		}catch(Exception e){
			logger.error(e.toString());
			return false;
		}
	}
	
	/**
	 * CSV 파일 생성
	 * @param alContent		필수(List<List<String>>) 내용
	 * @param outputPath	필수(String) CSV 파일 생성 경로/파일명 포함
	 * @param charsetName	(String) 인코딩 - 한글이 포함되었을 경우 반드시 EUC-KR 사용
	 * @param separator		(String) CSV 구분자
	 * @param quote			(String) 컬럼 구분자 (ex: " 사용시 > "a","b","c" 와 같은 형식이 됨)
	 * @return
	 */
	public static boolean makeCsvFile(List<List<String>> alContent, String outputPath, String charsetName, String separator, String quote){
		CSVWriter cw = null;
		try{
			File csvDir = new File(getPathWithoutFileName(outputPath));
			if(!csvDir.exists())
				forceMkdir(csvDir, false);
			
			if(StringUtil.trimString(charsetName).equals(""))
				charsetName = "EUC-KR";
			
			if(StringUtil.trimString(separator).equals(""))
				separator = ",";
			char separatorChar = separator.charAt(0);
			
			char quoteChar = CSVWriter.NO_QUOTE_CHARACTER;
			if(!StringUtil.trimString(quote).equals(""))
				quoteChar = quote.charAt(0);
			
			//cw = new CSVWriter(new OutputStreamWriter(new FileOutputStream(outputPath), charsetName), separatorChar, '"');
			cw = new CSVWriter(new OutputStreamWriter(new FileOutputStream(outputPath), charsetName), separatorChar, quoteChar);
			
			for(int i=0;i<alContent.size();i++){
				String[] content = new String[alContent.get(i).size()];
				for(int j=0;j<alContent.get(i).size();j++){
					if(alContent.get(i).get(j).equals("'null")){
						content[j] = null;
					} else {
						content[j] = alContent.get(i).get(j);
					}
				}                
                cw.writeNext(content);
            }
			
			return true;
		}catch(Exception e){
			logger.error(e.toString());
			return false;
		}finally{
			try{cw.close();}catch(Exception e){}
		}
	}
	
	/**
	 * 파일 인코딩 찾기
	 * @param filePath		(String) 파일 경로
	 * @return encoding 	(String) 인코딩 정보
	 */
	public static String findFileEncoding(String filePath){
		File file = new File(filePath);
		
		return findFileEncoding(file);
	}
	
	/**
	 * 파일 인코딩 찾기
	 * @param file			(File) 파일정보
	 * @return encoding 	(String) 인코딩정보
	 */
	public static String findFileEncoding(File file){
		try{
			byte[] buf = new byte[4096];
			
			@SuppressWarnings("resource")
			java.io.FileInputStream fis = new java.io.FileInputStream(file);
			UniversalDetector detector = new UniversalDetector(null);
			
			int nread;
			while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
				detector.handleData(buf, 0, nread);
			}
	
			detector.dataEnd();
	
			String encoding = detector.getDetectedCharset();	
			if (encoding == null) 
				encoding = "UTF-8";
	
			detector.reset();
			
			return encoding;		
		}catch(IOException e){
			e.printStackTrace();
			return "UTF-8";
		}
	}
	
	
}