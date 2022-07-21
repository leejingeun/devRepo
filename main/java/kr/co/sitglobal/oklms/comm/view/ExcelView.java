package kr.co.sitglobal.oklms.comm.view;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.sitglobal.oklms.comm.util.ExcelUtil;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public class ExcelView extends ExcelAbstractView {

	@Override
	protected HSSFWorkbook buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

	    
		ExcelUtil excelUtil = new ExcelUtil();
		String fileName = excelUtil.makeFileName( "test_" );
		if (model.containsKey("fileName")) {
			fileName = StringUtils.defaultIfBlank( (String)model.get("fileName") , fileName );
		}

		if (model.containsKey("excelWorkMap")) {
			workbook = (HSSFWorkbook) model.get("workbook");
		}

		String sheetName = "Sheet1";
		if (model.containsKey("sheetName")) {
			sheetName = StringUtils.defaultIfBlank( (String)model.get("sheetName") , "Sheet1" );
		}

		String bodyTitle = "";
		if (model.containsKey("bodyTitle")) {
			bodyTitle = StringUtils.defaultIfBlank( (String)model.get("bodyTitle") , "" );
		}
		
		
		HSSFSheet sheet = workbook.createSheet(sheetName);
		sheet.setDefaultColumnWidth(12);
		

		// 폰트속성
		Font fontBLACK = workbook.createFont();
		fontBLACK.setColor(HSSFColor.BLACK.index);


		// 셀속성 
		CellStyle cHeadStyle = workbook.createCellStyle();
//		cHeadStyle.setAlignment(CellStyle.ALIGN_CENTER); // 정렬
		cHeadStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
		cHeadStyle.setFont(fontBLACK); // 폰트
		cHeadStyle.setBorderTop(CellStyle.BORDER_THIN); // 테두리 설정
		cHeadStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cHeadStyle.setBorderRight(CellStyle.BORDER_THIN);
		cHeadStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		cHeadStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);  
		cHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);


		// 셀속성 
		CellStyle cBodyStyle = workbook.createCellStyle();
//		cBodyStyle.setAlignment(CellStyle.ALIGN_CENTER); // 정렬
		cBodyStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
		cBodyStyle.setFont(fontBLACK); // 폰트
		cBodyStyle.setBorderTop(CellStyle.BORDER_THIN); // 테두리 설정
		cBodyStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cBodyStyle.setBorderRight(CellStyle.BORDER_THIN);
		cBodyStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		cBodyStyle.setFillForegroundColor(HSSFColor.WHITE.index);  
		cBodyStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		

		ArrayList<String> exportColNames = (ArrayList<String>) model.get("exportColNames");
		ArrayList<HashMap<String, Object>> headerListTemp = (ArrayList<HashMap<String, Object>>) model.get("headerList");
		ArrayList<HashMap<String, Object>> headerList = new ArrayList<HashMap<String, Object>>();
		if( 0 < exportColNames.size() ){
			
			for( HashMap<String,Object> m : headerListTemp ){
				for( String m1 : exportColNames ){
					if( m1.equals( m.get("columnNm") ) ){
						headerList.add(m);
					}
				}
			}
		}else{
			headerList = headerListTemp;
		}
		
		// set title
		HSSFCell cell = null;
		int c = headerList.size() / 2;
		cell = getCell(sheet, 0, c);
		setText(cell, bodyTitle);
		
		// set header information
		for (int i = 0; i < headerList.size(); i++) {
			HashMap<String, Object> headObj = headerList.get(i);
//			setText(getCell(sheet, 2, i), (String) headObj.get("headNm"));
			cell = getCell(sheet, 2, i);
			cell.setCellStyle(cHeadStyle);
			cell.setCellValue((String) headObj.get("headNm"));
		}
		

		
		List<Object> dataList = (List<Object>) model.get("dataList");
		for (int i = 0; i < dataList.size(); i++) {

			Object dataMap = null;
			if( dataList.get(i) instanceof EgovMap ){
				dataMap = (EgovMap) dataList.get(i);
			}else{
				
				dataMap = (HashMap<String, Object>) dataList.get(i);
			}
			

			for (int k = 0; k < headerList.size(); k++) {
				HashMap<String, Object> headObj = headerList.get(k);

				String columnNm = (String) headObj.get("columnNm");
				Object columnData = ((ListOrderedMap) dataMap).get(columnNm);
				String d = "";
				if (columnData instanceof BigDecimal) {
					d = columnData.toString();
				} else if (columnData instanceof Timestamp) {
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String dateString  = dateFormat.format(columnData);
					
					d = dateString;
				} else {
					d = (String) columnData;
				}

				cell = getCell(sheet, 3 + i, k);
//				setText(cell, d);

				cell.setCellStyle(cBodyStyle);
				cell.setCellValue(d);
			}
		}

		//xls확장자로 다운로드  
		setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		
		return workbook;
	}

//	protected void buildExcelDocument(Map<String, Object> model,
//			HSSFWorkbook workbook, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		// TODO Auto-generated method stub
//
//		String filename = "";
//        HashMap excelWorkMap = (HashMap)model.get("excelWorkMap");
//		
//		filename = (String)excelWorkMap.get("fileName");  
//		HSSFWorkbook workbook1 = (HSSFWorkbook)excelWorkMap.get("workbook");  
//		workbook = workbook1;
//
//          
//        //xls확장자로 다운로드  
//        response.setContentType("application/x-msdownload");  
//        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");  
//
//    	ServletOutputStream out = response.getOutputStream();
//    	workbook.write(out);
//    	out.flush();
//        
//	}
}
