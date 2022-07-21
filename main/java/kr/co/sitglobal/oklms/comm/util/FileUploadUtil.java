/*
 * ******************************************************************************
 *  * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 *  * This software is the proprietary information of sitglobal LEARN.
 *  *
 *  * Revision History
 *  * Author   Date            Description
 *  * ------   ----------      ------------------------------------
 *  * 이진근    2016. 12. 14 오후 7:35         First Draft.
 *  *
 *  *******************************************************************************
 */
package kr.co.sitglobal.oklms.comm.util;

import java.io.*;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
    protected static Log logger = LogFactory.getLog(FileUploadUtil.class);
    
    /**
     * UUID로 임시 파일명을 생성하여 업로드
     *
     * @param formFile
     *            업로드 대상 파일
     * @param filePath
     *            업로드 패스
     * @return 생성된 파일명
     */
    public static String uploadFormFileAsRealName(MultipartFile formFile, String filePath) {
        return uploadFormFile(formFile, filePath, false);
    }
    
    /**
     * UUID로 임시 파일명을 생성하여 업로드
     *
     * @param formFile
     *            업로드 대상 파일
     * @param filePath
     *            업로드 패스
     * @return 생성된 파일명
     */
    public static String uploadFormFile(MultipartFile formFile, String filePath) {
        return uploadFormFile(formFile, filePath, true);
    }

    /**
     * UUID 임시 파일명 생성 여부를 선택하여 업로드
     *
     * @param formFile
     *            업로드 대상 파일
     * @param filePath
     *            업로드할 경로
     * @param usingUUID
     *            UID 임시 파일명 생성 여부
     * @return 생성된 파일명
     */
    public static String uploadFormFile(MultipartFile formFile,
            String filePath, boolean usingUUID) {
        InputStream stream;

        String tempFileName;
        if (usingUUID) {
            UUID uuid = UUID.randomUUID();
            tempFileName = uuid.toString();
        } else {
            tempFileName = formFile.getOriginalFilename();
        }

        try {
            stream = formFile.getInputStream();

            File pFile = new File(filePath);
            File upFile = new File(pFile, tempFileName);

            // directory가 없을시 생성
            if (!pFile.exists()) {
                pFile.mkdirs();
            }
            // 동일파일명 있을시 파일명 변경
            if (upFile.exists()) {

            }

            OutputStream bos = new FileOutputStream(upFile);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.close();
            stream.close();

            if (logger.isDebugEnabled()) {
                //logger.debug("The file has been written to \"" + filePath + tempFileName);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempFileName;
    }

    /**
     * 특정 파일명으로 파일을 업로드
     *
     * @param formFile 업로드 대상 파일
     * @param filePath 업로드 패스
     * @param keyId 업로드할 파일명
     * @return 파일 업로드 성공 여부
     */
    public static boolean uploadFormFile(MultipartFile formFile, String filePath, String keyId) {
        InputStream stream;
        boolean bSucess = true;

        try {

            stream = formFile.getInputStream();

            File pFile = new File(filePath);
            File upFile = new File(pFile, keyId);

            // directory가 없을시 생성
            if (!pFile.exists()) {
                pFile.mkdirs();
            }
            // 동일파일명 있을시 파일명 변경
            if (upFile.exists()) {

            }

            stream = formFile.getInputStream();
            String fileOutPath = CommonUtil.appendPath(filePath,keyId);
            OutputStream bos = new FileOutputStream(fileOutPath);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
            	logger.debug("The file has been written to : " + formFile + filePath + keyId);
            	logger.debug("bos.write : " + bos);
                bos.write(buffer, 0, bytesRead);
            }
            bos.close();
            stream.close();

            if (logger.isDebugEnabled()) {
                //logger.debug("The file has been written to \"" + filePath + keyId);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            bSucess = false;
        } catch (IOException e) {
            e.printStackTrace();
            bSucess = false;
        }

        return bSucess;
    }
    
    /**
     * 특정 파일명으로 업로드 된 파일을 삭제
     *
     * @param formFile 삭제 대상 파일
     * @param filePath 업로드 패스+파일명
     * @return 파일 삭제 성공 여부
     */
    public static boolean deleteFormFile(MultipartFile formFile, String filePath) {
        File delFile = new File(filePath);
        return delFile.delete();
    }
    
    public static int getRes_code(byte abyte0[]) {
        return getshort(abyte0, abyte0.length - 2);
    }

    public static final short getshort(byte abyte0[], int i) {
        return (short) ((abyte0[i] & 0xff) << 8 | abyte0[i + 1] & 0xff);
    }

    public static void putHeader(byte abyte0[], int i, int j) {
        abyte0[0] = (byte) i;
        setint(abyte0, 1, j);
    }

    public static final byte[] setlong(byte abyte0[], int i, long l) {
        setint(abyte0, i, (int) (l >> 32));
        setint(abyte0, i + 4, (int) (l & 0xffffffffL));
        return abyte0;
    }

    public static final byte[] setint(byte abyte0[], int i, int j) {
        abyte0[i + 3] = (byte) (j & 0xff);
        abyte0[i + 2] = (byte) (j >> 8 & 0xff);
        abyte0[i + 1] = (byte) (j >> 16 & 0xff);
        abyte0[i] = (byte) (j >> 24 & 0xff);
        return abyte0;
    }

    public static void setFilename(byte abyte0[], int i, String s) {
        setstring(abyte0, 7, i, s);
    }

    public static void setFilename_size(byte abyte0[], int i) {
        setshort(abyte0, 5, (short) i);
    }

    public static final byte[] setshort(byte abyte0[], int i, short word0) {
        abyte0[i + 1] = (byte) (word0 & 0xff);
        abyte0[i] = (byte) (word0 >> 8 & 0xff);
        return abyte0;
    }

    public static final byte[] setstring(byte abyte0[], int i, int j, String s) {
        byte abyte1[] = s.getBytes();
        if (abyte1.length > j)
            System.arraycopy(abyte1, 0, abyte0, i, j);
        else
            System.arraycopy(abyte1, 0, abyte0, i, abyte1.length);
        return abyte0;
    }

    // public static void main(String[] args) throws Exception {
    // try {
    // String path =
    // "D:/workspace/sitglobalLEARN_LMS/upload/lecture/non_scorm/2007LLEC0000008";
    // String name = "b1526ef75bf6.txt";
    //
    // File cFile = new File( path, name );
    //
    // //logger.debug("cFile.exists() : " + cFile.exists());
    //
    //
    // FileOutputStream fs = new FileOutputStream( cFile );
    // fs.write(1);
    //
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
}
