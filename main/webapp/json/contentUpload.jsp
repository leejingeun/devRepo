<%@page import="egovframework.com.utl.fcc.service.EgovDateUtil"%>
<%@page import="egovframework.com.cmm.EgovWebUtil"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="org.springframework.web.bind.ServletRequestUtils"%>
<%@page import="egovframework.com.cmm.service.EgovProperties"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="kr.co.sitglobal.oklms.comm.util.Config"%>
<%@page import="kr.co.sitglobal.oklms.comm.util.CommonUtil"%>
<%@ page import="java.util.UUID"%>
<%@ page import="java.io.FileOutputStream"%>
<%@ page import="java.io.OutputStream"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.io.File"%>
<%@ page import="java.util.List"%>
<%@ page import="org.apache.commons.fileupload.FileItem"%>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>

<%


String rootDir = ServletRequestUtils.getStringParameter( request, "rootDir", "" );
String currentFileView = ServletRequestUtils.getStringParameter( request, "currentFileView", "" );
String today = EgovDateUtil.getToday();
int sizeLimit = 1024*1024*1024*10;
JSONObject resultJson = new JSONObject();
//String savePath =  CommonUtil.appendPath(EgovProperties.getProperty("Globals.fileUploadPath"), EgovProperties.getProperty("content.nonscorm.root.dir"));
String savePath =  EgovProperties.getProperty("Globals.ContentMovieStorePath")+today+"/";

String tempFileName = "";
String fileName = "";
String msg = "0";

try{
	
	DiskFileItemFactory factory = new DiskFileItemFactory();
	
	LinkedHashMap parameterMap = new LinkedHashMap();
	
	File saveFolder = new File(EgovWebUtil.filePathBlackList(savePath));

	if (!saveFolder.exists() || saveFolder.isFile()) {
		saveFolder.mkdirs();
	}
	
	
	ServletFileUpload upload = new ServletFileUpload(factory);
	upload.setSizeMax(sizeLimit);
	upload.setHeaderEncoding("UTF-8");
	List<FileItem> items = null;
	
	items = upload.parseRequest(request);
	
	Iterator iter = items.iterator();
	
	while (iter.hasNext()) {
		FileItem fileItem = (FileItem) iter.next();
		String fieldName = fileItem.getFieldName();
		
		if (fileItem.isFormField()) {
			parameterMap.put(fieldName, fileItem.getString("UTF-8"));
		} else {
			if (fileItem.getSize() > 0) {
				
				//System.out.println("fileItem.getSize() : "+fileItem.getSize());
				
				File uploadedDir = new File(savePath);

				if (!uploadedDir.exists()) {
					uploadedDir.mkdirs();
				}

				fileName = fileItem.getName();
				String fileExt = "";

				if (!fileName.equals("")) {
					
					
					//============ 같은파일 존재 여부 판단 시작
					 
					File chkFile = new File(savePath + File.separator + fileName);
					 
				    //if (chkFile.isFile()) {
				    //	msg = "1";
				    //}
				    
				    //============ 파일 존재 여부 판단 끝
					
					String dirName = fileName.substring( 0 , fileName.lastIndexOf('.') );
					
					int pos = -1;
					pos = fileName.lastIndexOf("\\");
					if (pos == -1) {
						pos = fileName.lastIndexOf("/");
						
					}
					fileName = fileName.substring(pos + 1);
					pos = fileName.lastIndexOf('.');
					if (pos > -1) {
						fileExt = fileName.substring(pos);
					}
					
					String contentType = fileItem.getContentType();
		            UUID uuid = UUID.randomUUID();
		            tempFileName = uuid.toString()+fileExt; // fileName //
		            
		            //System.out.println("tempFileName : "+tempFileName);
		            //System.out.println("================ savePath : "+savePath);
					File uploadedFile = new File(savePath, tempFileName);
					
					fileItem.write(uploadedFile);
							
					fileItem.delete();
						
				}
			}
		}
	}
	
	resultJson.put("msg", msg);
	
	resultJson.put("contentsIdxFile", tempFileName);
	resultJson.put("contentsDir", EgovProperties.getProperty("content.movie.root.dir")+"/"+today );
	resultJson.put("contentsRealFile", fileName);
	
  //	response.setContentType("text/xml;charset=utf-8");

  if ( (null != request.getHeader("Accept")) && request.getHeader("Accept").contains("application/json") )
  {
      response.setContentType("application/json");
  }
  else
  {
	   response.setContentType("text/plain");
  }
	//response.getWriter().write(jsonResponse);
	
	PrintWriter write = response.getWriter();
	write.print(resultJson);
	write.flush();
	write.close();
}catch(Exception e){
	e.printStackTrace();
}

%>