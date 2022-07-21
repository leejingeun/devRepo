package kr.co.sitglobal.oklms.comm.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import egovframework.com.cmm.service.EgovProperties;

public class ZipDownloadView extends AbstractView {
    public ZipDownloadView() {
        setContentType("application/download; charset=utf-8");
    }
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,HttpServletRequest request, HttpServletResponse response) throws Exception {


        String fileStr = (String)model.get("fileStr");
        String oldFileStr = (String)model.get("oldFileStr");

        String reNameStr = "";
        if(null!=model.get("reNameStr")){
            reNameStr= (String)model.get("reNameStr");
        }

        if(oldFileStr == null || oldFileStr.equals("")) return;
        String zipFileName = System.nanoTime()+"";
        FileOutputStream fos = new FileOutputStream(zipFileName+".zip");
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(fos);
        zos.setEncoding("UTF-8");
        String[] fileStrs = fileStr.split("\\?");
        String[] oldFileStrs = oldFileStr.split("\\?");
        byte[] buff = new byte[4096];
        for(int i=0; i<oldFileStrs.length; i++) {
            File file = new File(oldFileStrs[i]);
            FileInputStream fis = new FileInputStream(file);
            ZipArchiveEntry ze = new ZipArchiveEntry(fileStrs[i]);
            zos.putArchiveEntry(ze);
            int len;
            while((len = fis.read(buff)) > 0) {
                zos.write(buff, 0, len);
            }
            zos.closeArchiveEntry();
            fis.close();
        }
        zos.close();
        File zipFile = new File(zipFileName+".zip");
        response.setContentType(getContentType());
        response.setContentLength((int)zipFile.length());
        String userAgent = request.getHeader("User-Agent");
        boolean ie = userAgent.indexOf("MSIE") > -1;
        // ie 11체크
        if(!ie){
        	ie = userAgent.indexOf( "Trident" ) > -1;
        }
        String fileName = null;
        String orgFileName = "download.zip";
        if(reNameStr!=null && !reNameStr.equals("")){
        	 orgFileName = reNameStr;
        }
        if(ie) {
            fileName = URLEncoder.encode(orgFileName, "utf-8");
        } else {
            fileName = new String(orgFileName.getBytes("utf-8"), "iso-8859-1");
        }
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        OutputStream out = response.getOutputStream();
        FileInputStream fis2 = null;
        try {
            fis2 = new FileInputStream(zipFile);
            FileCopyUtils.copy(fis2, out);
        } finally {
            if(fis2 != null) { try { fis2.close(); } catch(IOException ioe) {} }
            zipFile.delete();
        }
        out.flush();
    }
}
