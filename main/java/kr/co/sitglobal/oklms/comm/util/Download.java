package kr.co.sitglobal.oklms.comm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import egovframework.com.cmm.service.EgovProperties;
import kr.co.sitglobal.oklms.comm.util.Config;

public class Download extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ① 파일명 가져오기
		String fileName = "";
		String uploadFilePath = "";
		String saveDir = "";
		fileName = request.getParameter("filename");
		System.out.println("파일명1 : " + fileName);
		fileName = URLDecoder.decode(fileName, "UTF-8");
		System.out.println("파일명2 : " + fileName);
		uploadFilePath = request.getParameter("uploadFilePath");
		System.out.println("파일경로 : " + uploadFilePath);
		//fileName = URLEncoder.encode(fileName.replaceAll(" ", "_") ,"UTF-8");
		
		if("/upload/task/".equals(uploadFilePath)){
			uploadFilePath = "upload/task/";
		}
		
		// ② 경로 가져오기
		//과제제출파일 다운로드시
		if("upload/task/".equals(uploadFilePath)){
			saveDir = EgovProperties.getProperty( "Globals.fileUploadPath")+uploadFilePath;
		}else{
			saveDir = this.getServletContext().getRealPath(uploadFilePath);
		}
		
		System.out.println("saveDir : " + saveDir);
		File file = new File(saveDir + "/" + fileName);
		
		// ③ MIMETYPE 설정하기
		String mimeType = getServletContext().getMimeType(file.toString());
		if(mimeType == null)
		{
			response.setContentType("application/octet-stream");
		}
		
		// ④ 다운로드용 파일명을 설정
		String downName;
		if(request.getHeader("user-agent").indexOf("MSIE") == -1)
		{
			//downName = new String(fileName.getBytes("UTF-8"), "8859_1");
			//downName = new String(fileName.getBytes("EUC-KR"), "8859-1");
			//downName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");

			downName = new String(fileName.getBytes("KSC5601"), "8859_1");

			response.setHeader("Content-Disposition", "attachment; filename=\"" + downName + "\"");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setContentLength((int)file.length());
			response.setContentType("application/octet-stream");
			response.setHeader("Connection", "close");
		}
		else
		{
			downName = new String(fileName.getBytes("EUC-KR"), "ISO-8859-1");
		}
		
		// ⑤ 무조건 다운로드하도록 설정
		response.setHeader("Content-Disposition","attachment;filename=\"" + downName + "\";");
		
		// ⑥ 요청된 파일을 읽어서 클라이언트쪽으로 저장한다.
		FileInputStream fileInputStream = new FileInputStream(file);
		ServletOutputStream servletOutputStream = response.getOutputStream();
		
		byte b [] = new byte[1024];
		int data = 0;
		
		while((data=(fileInputStream.read(b, 0, b.length))) != -1)
		{
			servletOutputStream.write(b, 0, data);
		}
		
		servletOutputStream.flush();
		servletOutputStream.close();
		fileInputStream.close();
	}
}
