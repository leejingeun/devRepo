package kr.co.sitglobal.oklms.comm.util;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExcelUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);
	
	protected String doDownload(HttpServletResponse response, File file, String fileName) {
    	
        return doDownload(response, file, fileName, true);
    }
	
	public String doDownload(HttpServletResponse response, File file, String fileName, boolean deleteFlag) {
    	
    	String encodingFileName = fileName;
		try {
			encodingFileName = new String(fileName.getBytes("EUC-KR"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}  
           
        response.setHeader("Cache-Control", "");
        response.setHeader("Pragma", "");

        response.setContentType("Content-type:application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + encodingFileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");

        
    	BufferedInputStream 	bis = null;
        BufferedOutputStream 	bos = null;
        
        try {
        	      
    		bis = new BufferedInputStream(new FileInputStream(file));
    		bos = new BufferedOutputStream(response.getOutputStream());
    	        
    		byte[] buf = new byte[2048];
    		int read = 0;
    		while ((read = bis.read(buf)) != -1) {
    			bos.write(buf, 0, read);
    		}
    		bos.flush();    
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
            if (bis != null) {
              try {
                bis.close();
              } catch (Exception e) {
            	  LOGGER.error(e.getMessage());
              }
            }
            if (bos != null) {
              try {
                bos.close();
              } catch (Exception e) {
            	  LOGGER.error(e.getMessage());
              }

            }
            
            if(deleteFlag) file.delete();
    	}
        return fileName;
    }
	
	
	public void substitute(File zipfile, Map gridMap, OutputStream out)
		throws IOException {
		
		ZipFile zip = new ZipFile(zipfile);
		ZipOutputStream zos = new ZipOutputStream(out);
	
		Enumeration<ZipEntry> en = (Enumeration<ZipEntry>) zip.entries();
		while (en.hasMoreElements()) {
			ZipEntry ze = en.nextElement();
			if (!gridMap.containsKey(ze.getName())) {
				zos.putNextEntry(new ZipEntry(ze.getName()));
				InputStream is = zip.getInputStream(ze);
				copyStream(is, zos);
				is.close();
			}
		}

		Iterator it = gridMap.keySet().iterator();
		while (it.hasNext()) {
			String entry = (String) it.next();
			zos.putNextEntry(new ZipEntry(entry));
			InputStream is = new FileInputStream((File) gridMap.get(entry));
			copyStream(is, zos);
			is.close();
		}
		zos.close();
	}
		
	public void copyStream(InputStream in, OutputStream out) throws IOException {
		byte[] chunk = new byte[1024];
		int count;
		while ((count = in.read(chunk)) >=0 ) {
			out.write(chunk,0,count);
		}
	}
	
	public String makeFileName(String strFileName) {
    	StringBuilder sbFileName = new StringBuilder();
		Date toDay = new Date();
        String strToDay = DateFormatUtils.format(toDay, "yyyyMMddHHmmss");
        sbFileName.append(strFileName);
        sbFileName.append(strToDay);
        sbFileName.append(".xls");
        
        return sbFileName.toString();
    }
	
	public Map<String, XSSFCellStyle> createCellStyles(XSSFWorkbook workBook){
		
		Map<String, XSSFCellStyle> styleMap = new HashMap<String, XSSFCellStyle>();
	       
		XSSFFont headFont = workBook.createFont();
		headFont.setBold(true);
		headFont.setFontName("맑은 고딕");
		headFont.setColor(XSSFFont.COLOR_NORMAL);
		headFont.setFontHeight(10);
	    
		XSSFFont bodyFont = workBook.createFont();
		bodyFont.setBold(false);
		bodyFont.setFontName("맑은 고딕");
		bodyFont.setColor(XSSFFont.COLOR_NORMAL);
		bodyFont.setFontHeight(10);
		
		XSSFColor gray = new XSSFColor(Color.LIGHT_GRAY);
		
	    XSSFCellStyle headStyle = workBook.createCellStyle();
	    headStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	    headStyle.setWrapText(true);
        headStyle.setFont(headFont);
        
        styleMap.put("head", headStyle);
	    
        XSSFCellStyle bodyStyle = workBook.createCellStyle();
        bodyStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        bodyStyle.setFont(bodyFont);
	  
	    styleMap.put("body", bodyStyle);
	    
	    XSSFCellStyle bodyNumericStyle = workBook.createCellStyle();
	    bodyNumericStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	    bodyNumericStyle.setDataFormat(workBook.createDataFormat().getFormat("#,##0"));
	    bodyNumericStyle.setFont(bodyFont);
	    
	    styleMap.put("bodyNumeric", bodyNumericStyle);
	    
	    return styleMap;
	}

	
	
	
	
	
	
	
	
	
	

    public void copyFile(String source, String target) throws IOException {
        FileChannel inChannel = new FileInputStream(source).getChannel();
        FileChannel outChannel = new FileOutputStream(target).getChannel();
        try {
            int maxCount = (64 * 1024 * 1024) - (32 * 1024);
            long size = inChannel.size();
            long position = 0;
            while (position < size) {
                position += inChannel.transferTo(position, maxCount, outChannel);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (inChannel != null)
                inChannel.close();
            if (outChannel != null)
                outChannel.close();
        }
    }
    
    public int getLine (String civilCont, String reason, String procCont){
        String[] civilArr = civilCont.split("\r");
        String[] procArr = procCont.split("\r");
        
        int r = civilArr.length<procArr.length?procArr.length:civilArr.length;
        if(r < reason.getBytes().length/18){
            r=r+reason.getBytes().length/18;
        }
        return r;
    }
    
    /**
	 * Disposition 지정하기.
	 * EgovFileDownloadController 에서 가져옮
	 * @param filename
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void setDisposition( String filename, HttpServletRequest request, HttpServletResponse response ) throws Exception {
		String browser = getBrowser( request );

		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;

		if (browser.equals( "MSIE" )) {
			encodedFilename = URLEncoder.encode( filename, "UTF-8" ).replaceAll( "\\+", "%20" );
		} else if (browser.equals( "Trident" )) { // IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode( filename, "UTF-8" ).replaceAll( "\\+", "%20" );
		} else if (browser.equals( "Firefox" )) {
			encodedFilename = "\"" + new String( filename.getBytes( "UTF-8" ), "8859_1" ) + "\"";
		} else if (browser.equals( "Opera" )) {
			encodedFilename = "\"" + new String( filename.getBytes( "UTF-8" ), "8859_1" ) + "\"";
		} else if (browser.equals( "Chrome" )) {
			StringBuffer sb = new StringBuffer();
			for( int i = 0; i < filename.length(); i++ ) {
				char c = filename.charAt( i );
				if (c > '~') {
					sb.append( URLEncoder.encode( "" + c, "UTF-8" ) );
				} else {
					sb.append( c );
				}
			}
			encodedFilename = sb.toString();
		} else {
			throw new IOException( "Not supported browser" );
		}

		response.setHeader( "Content-Disposition", dispositionPrefix + encodedFilename );

		if ("Opera".equals( browser )) {
			response.setContentType( "application/octet-stream;charset=UTF-8" );
		}
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
	 * HSSFSheet Test
	 * @param s1
	 * @param i
	 * @return
	 * Row
	 */
	public static Row getRow(HSSFSheet s1 , int i){
		Row r = ((org.apache.poi.ss.usermodel.Sheet) s1).getRow(i);
		if(r==null)
			r = ((org.apache.poi.ss.usermodel.Sheet) s1).createRow(i);
		return r;
	}
	
	/**
	 * HSSFSheet Test
	 * @param s1
	 * @param row
	 * @param cell
	 * @return
	 * Cell
	 */
	public static Cell getCell(HSSFSheet s1 , int row,int cell){
		Row r = getRow(s1 , row);
		Cell c = r.getCell(cell);
		if(c==null)
			c = r.createCell(cell);
		return c;
	}
	
	/**
	 * HSSFSheet Test
	 * @param s1
	 * @param row
	 * @param cell
	 * @param cellvalue
	 * void
	 */
	public static void setCellValue(HSSFSheet s1 , CellStyle cStyle , int row, int cell, String cellvalue){
		Cell c = getCell(s1, row,cell);
		c.setCellStyle(cStyle);
		c.setCellValue(cellvalue);
	}
	
	/**
	 * HSSFSheet Test
	 * @param wb1
	 * @param s1
	 * @param fileName
	 * void
	 */
	public static void writeWorkbook(Workbook wb1 , HSSFSheet s1 , String fileName){
		long start = System.currentTimeMillis();
		try{
			
			
			// 폰트속성
			Font fontBLACK = wb1.createFont();
			fontBLACK.setColor(HSSFColor.BLACK.index);


			// 셀속성 
			CellStyle cHeadStyle = wb1.createCellStyle();
//			cHeadStyle.setAlignment(CellStyle.ALIGN_CENTER); // 정렬
			cHeadStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
			cHeadStyle.setFont(fontBLACK); // 폰트
			cHeadStyle.setBorderTop(CellStyle.BORDER_THIN); // 테두리 설정
			cHeadStyle.setBorderLeft(CellStyle.BORDER_THIN);
			cHeadStyle.setBorderRight(CellStyle.BORDER_THIN);
			cHeadStyle.setBorderBottom(CellStyle.BORDER_THIN);
			
			cHeadStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);  
			cHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);


			// 셀속성 
			CellStyle cBodyStyle = wb1.createCellStyle();
//			cBodyStyle.setAlignment(CellStyle.ALIGN_CENTER); // 정렬
			cBodyStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
			cBodyStyle.setFont(fontBLACK); // 폰트
			cBodyStyle.setBorderTop(CellStyle.BORDER_THIN); // 테두리 설정
			cBodyStyle.setBorderLeft(CellStyle.BORDER_THIN);
			cBodyStyle.setBorderRight(CellStyle.BORDER_THIN);
			cBodyStyle.setBorderBottom(CellStyle.BORDER_THIN);
			
			cBodyStyle.setFillForegroundColor(HSSFColor.WHITE.index);  
			cBodyStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			
			
//			s1 = (HSSFSheet) wb1.createSheet("sample Sheet");
			for(int i=0;i<10000;i++){
				CellStyle cStyle;
				if( 0 == i ){
					cStyle = cHeadStyle;
				}else{
					cStyle = cBodyStyle;
				}
				setCellValue( s1 , cStyle , i,0,"Start-"+ i);
				setCellValue( s1 , cStyle , i,1,"cont_"+ RandomStringUtils.randomAlphanumeric(11) );
				setCellValue( s1 , cStyle , i,2,"cont_"+ RandomStringUtils.randomAlphanumeric(12) );
				setCellValue( s1 , cStyle , i,3,"cont_"+ RandomStringUtils.randomAlphanumeric(13) );
				setCellValue( s1 , cStyle , i,4,"cont_"+ RandomStringUtils.randomAlphanumeric(14) );
				setCellValue( s1 , cStyle , i,5,"cont_"+ RandomStringUtils.randomAlphanumeric(15) );
				setCellValue( s1 , cStyle , i,6,"cont_"+ RandomStringUtils.randomAlphanumeric(16) );
				setCellValue( s1 , cStyle , i,7,"cont_"+ RandomStringUtils.randomAlphanumeric(17) );
				setCellValue( s1 , cStyle , i,8,"cont_"+ RandomStringUtils.randomAlphanumeric(18) );
				setCellValue( s1 , cStyle , i,9,"cont_"+ RandomStringUtils.randomAlphanumeric(19) );
				setCellValue( s1 , cStyle , i,10,"End-"+ i);
			}
			wb1.write(new FileOutputStream(fileName));
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		long end = System.currentTimeMillis();
		LOGGER.debug("write Time HSSFWorkbook : "+(end-start));
	}
	
	
	
	
	
	/**
	 * XSSFSheet Test
	 * @param s2
	 * @param i
	 * @return
	 * Row
	 */
	public static Row getRow(XSSFSheet s2 , int i){
		Row r = ((org.apache.poi.ss.usermodel.Sheet) s2).getRow(i);
		if(r==null)
			r = ((org.apache.poi.ss.usermodel.Sheet) s2).createRow(i);
		return r;
	}
	
	/**
	 * XSSFSheet Test
	 * @param s2
	 * @param row
	 * @param cell
	 * @return
	 * Cell
	 */
	public static Cell getCell(XSSFSheet s2 , int row,int cell){
		Row r = getRow(s2 , row);
		Cell c = r.getCell(cell);
		if(c==null)
			c = r.createCell(cell);
		return c;
	}
	
	/**
	 * XSSFSheet Test
	 * @param s2
	 * @param row
	 * @param cell
	 * @param cellvalue
	 * void
	 */
	public static void setCellValue(XSSFSheet s2 , int row, int cell, String cellvalue){
		Cell c = getCell(s2, row,cell);
		c.setCellValue(cellvalue);
	}
	
	/**
	 * XSSFSheet Test
	 * @param wb2
	 * @param s2
	 * @param fileName
	 * void
	 */
	private static void writeWorkbook(Workbook wb2, XSSFSheet s2, String fileName) {
		long start = System.currentTimeMillis();
		try{
//			s2 = (XSSFSheet) wb2.createSheet("sample Sheet");
			for(int i=0;i<10000;i++){
				setCellValue( s2 , i,0,"Start-"+ i);
				setCellValue( s2 , i,1,"cont_"+ RandomStringUtils.randomAlphanumeric(11) );
				setCellValue( s2 , i,2,"cont_"+ RandomStringUtils.randomAlphanumeric(12) );
				setCellValue( s2 , i,3,"cont_"+ RandomStringUtils.randomAlphanumeric(13) );
				setCellValue( s2 , i,4,"cont_"+ RandomStringUtils.randomAlphanumeric(14) );
				setCellValue( s2 , i,5,"cont_"+ RandomStringUtils.randomAlphanumeric(15) );
				setCellValue( s2 , i,6,"cont_"+ RandomStringUtils.randomAlphanumeric(16) );
				setCellValue( s2 , i,7,"cont_"+ RandomStringUtils.randomAlphanumeric(17) );
				setCellValue( s2 , i,8,"cont_"+ RandomStringUtils.randomAlphanumeric(18) );
				setCellValue( s2 , i,9,"cont_"+ RandomStringUtils.randomAlphanumeric(19) );
				setCellValue( s2 , i,10,"End-"+ i);
			}
			wb2.write(new FileOutputStream(fileName));
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		long end = System.currentTimeMillis();
		LOGGER.debug("write Time XSSFWorkbook : "+(end-start));
	}
	
	
	
	
	
	/**
	 * SXSSFSheet Test
	 * @param s2
	 * @param i
	 * @return
	 * Row
	 */
	public static Row getRow(SXSSFSheet s3 , int i){
		Row r = ((org.apache.poi.ss.usermodel.Sheet) s3).getRow(i);
		if(r==null)
			r = ((org.apache.poi.ss.usermodel.Sheet) s3).createRow(i);
		return r;
	}
	
	/**
	 * SXSSFSheet Test
	 * @param s2
	 * @param row
	 * @param cell
	 * @return
	 * Cell
	 */
	public static Cell getCell(SXSSFSheet s3 , int row,int cell){
		Row r = getRow(s3 , row);
		Cell c = r.getCell(cell);
		if(c==null)
			c = r.createCell(cell);
		return c;
	}
	
	/**
	 * SXSSFSheet Test
	 * @param s2
	 * @param row
	 * @param cell
	 * @param cellvalue
	 * void
	 */
	public static void setCellValue(SXSSFSheet s3 , int row, int cell, String cellvalue){
		Cell c = getCell(s3, row,cell);
		c.setCellValue(cellvalue);
	}
	
	/**
	 * SXSSFSheet Test
	 * @param wb2
	 * @param s2
	 * @param fileName
	 * void
	 */
	private static void writeWorkbook(Workbook wb3, SXSSFSheet s3, String fileName) {
		long start = System.currentTimeMillis();
		try{
//			s3 = (SXSSFSheet) wb3.createSheet("sample Sheet");
			for(int i=0;i<10000;i++){
				setCellValue( s3 , i,0,"Start-"+ i);
				setCellValue( s3 , i,1,"cont_"+ RandomStringUtils.randomAlphanumeric(11) );
				setCellValue( s3 , i,2,"cont_"+ RandomStringUtils.randomAlphanumeric(12) );
				setCellValue( s3 , i,3,"cont_"+ RandomStringUtils.randomAlphanumeric(13) );
				setCellValue( s3 , i,4,"cont_"+ RandomStringUtils.randomAlphanumeric(14) );
				setCellValue( s3 , i,5,"cont_"+ RandomStringUtils.randomAlphanumeric(15) );
				setCellValue( s3 , i,6,"cont_"+ RandomStringUtils.randomAlphanumeric(16) );
				setCellValue( s3 , i,7,"cont_"+ RandomStringUtils.randomAlphanumeric(17) );
				setCellValue( s3 , i,8,"cont_"+ RandomStringUtils.randomAlphanumeric(18) );
				setCellValue( s3 , i,9,"cont_"+ RandomStringUtils.randomAlphanumeric(19) );
				setCellValue( s3 , i,10,"End-"+ i);
			}
			wb3.write(new FileOutputStream(fileName));
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		long end = System.currentTimeMillis();
		LOGGER.debug("write Time SXSSFWorkbook : "+(end-start));
	}
	

	
	/**
	 * 메소드에 대한 설명을 작성한다.
	 * @param filePath
	 * @param excelHeaders
	 * @return
	 * @throws Exception
	 * Map<String,Object>
	 */
	public static Map<String, Object> excelValidate(String filePath , ArrayList<String> excelHeaders) throws Exception {

		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<Map<Object, String>> dataList = new ArrayList<Map<Object, String>>();

		Workbook workbook = null;
		FileInputStream fis = null;   
		File source = new File(filePath);  

		if(source.exists()){
			fis = new FileInputStream(source);
			
			if( 0 < filePath.indexOf(".xlsx") ){
				
				workbook = new XSSFWorkbook(fis);
				
				int rowindex=0;
				//시트 수 (첫번째에만 존재하므로 0을 준다)
				//만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
				XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
				//행의 수
				int rows=sheet.getPhysicalNumberOfRows();
				for (rowindex = 1; rowindex < rows; rowindex++) {
					// 행을읽는다
					XSSFRow row = sheet.getRow(rowindex);
					if (row != null) {

						Map<Object, String> data = new HashMap<Object, String>();
						// 셀의 수
						int cells = row.getPhysicalNumberOfCells();
						for ( int columnindex = 0; columnindex <= cells && columnindex < excelHeaders.size(); columnindex++) {
							// 셀값을 읽는다
							XSSFCell cell = row.getCell(columnindex);
							
							String header = excelHeaders.get(columnindex);
							String value = "";
							// 셀이 빈값일경우를 위한 널체크
							if (cell == null) {
								continue;
							} else {
								// 타입별로 내용 읽기
								switch (cell.getCellType()) {
								case XSSFCell.CELL_TYPE_FORMULA:
									value = cell.getCellFormula();
									break;
								case XSSFCell.CELL_TYPE_NUMERIC:
									value = cell.getNumericCellValue() + "";
									break;
								case XSSFCell.CELL_TYPE_STRING:
									value = cell.getStringCellValue() + "";
									break;
								case XSSFCell.CELL_TYPE_BLANK:
									value = cell.getBooleanCellValue() + "";
									break;
								case XSSFCell.CELL_TYPE_ERROR:
									value = cell.getErrorCellValue() + "";
									break;
								}
							}
							data.put(header, value);
						}
						System.out.println("data : " + data.toString() );
						dataList.add(data);
					}
					returnMap.put("dataList", dataList);
				}
			}else if( 0 < filePath.indexOf(".xls") ){
				workbook = new HSSFWorkbook(fis);
				int rowindex = 0;
				// 시트 수 (첫번째에만 존재하므로 0을 준다)
				// 만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
				HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
				// 행의 수
				int rows = sheet.getPhysicalNumberOfRows();
				for (rowindex = 1; rowindex < rows; rowindex++) {
					// 행을 읽는다
					HSSFRow row = sheet.getRow(rowindex);
					if (row != null) {
						
						Map<Object, String> data = new HashMap<Object, String>();

						// 셀의 수
						int cells = row.getPhysicalNumberOfCells();

						for (int columnindex = 0; columnindex <= cells && columnindex < excelHeaders.size() ; columnindex++) {
							// 셀값을 읽는다
							HSSFCell cell = row.getCell(columnindex);
							
							String header = excelHeaders.get(columnindex);
							
							String value = "";
							// 셀이 빈값일경우를 위한 널체크
							if (cell == null) {
								continue;
							} else {
								// 타입별로 내용 읽기
								switch (cell.getCellType()) {
								case HSSFCell.CELL_TYPE_FORMULA:
									value = cell.getCellFormula();
									break;
								case HSSFCell.CELL_TYPE_NUMERIC:
									value = cell.getNumericCellValue() + "";
									break;
								case HSSFCell.CELL_TYPE_STRING:
									value = cell.getStringCellValue() + "";
									break;
								case HSSFCell.CELL_TYPE_BLANK:
									value = cell.getBooleanCellValue() + "";
									break;
								case HSSFCell.CELL_TYPE_ERROR:
									value = cell.getErrorCellValue() + "";
									break;
								}
							}
							data.put(header, value);
						}
						System.out.println("data : " + data.toString() );
						dataList.add(data);
					}
					returnMap.put("dataList", dataList);
				}
			} // else if : filePath.indexOf(".xls")

		}else{
			System.out.println( "File path is not exist.");
		} 

		return returnMap;
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 자동 생성된 메소드 스텁
		
//		Workbook wb1 = new HSSFWorkbook();
//		HSSFSheet s1 = (HSSFSheet) wb1.createSheet("SOMESHEET");
//		writeWorkbook(wb1 , s1 , "E:\\hssf-sample.xls");
//
//		Workbook wb2 = new XSSFWorkbook();
//		XSSFSheet s2 = (XSSFSheet) wb2.createSheet("SOMESHEET");
//		writeWorkbook(wb2 , s2 , "E:\\xssf-sample.xls");
//
//		Workbook wb3 = new SXSSFWorkbook(-1);
//		SXSSFSheet s3 = (SXSSFSheet) wb3.createSheet("SOMESHEET");
//		writeWorkbook(wb3 , s3 , "E:\\sxssf-sample.xls");
		
		
		try {
			String filePath = "E:\\PJ_Temp\\20160608\\oklms-H.xlsx";
			ArrayList<String> excelHeaders = new ArrayList<String>();
			excelHeaders.add("No.");
			excelHeaders.add("사용자ID");
			excelHeaders.add("사용그룹");
			excelHeaders.add("사용권한");
			excelHeaders.add("사용자명");
			excelHeaders.add("사번");
			excelHeaders.add("부서");
			excelHeaders.add("팀");
			excelHeaders.add("직책");
			excelHeaders.add("사용자IP");
			excelHeaders.add("사용자Mac주소");
			
			Map<String, Object> excelData = excelValidate( filePath , excelHeaders);
			
			System.out.println( excelData.toString() );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
