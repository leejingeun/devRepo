package kr.co.sitglobal.oklms.comm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


/**
 * <pre>
 * Description  : Excel File 에 접근하기 위한 Handler Class
 *
 * System       : SBTI
 * Program ID   : ExcelHandler
 * Creater      : 안병진
 * Create Date  : 2008. 03. 31
 * Updater      :
 * Update Date  :
 * Update Desc. :
 * </pre>
 * @version 1.0
 * @author Copyright (c) 2008 by SBTI. All Rights Reserved.
 */
public class ExcelHandler {
    private static Logger logger = Logger.getLogger(ExcelHandler.class);

    /**
     * Excel 파일 경로
     */
    private String excelPath;

    /**
     * 기본 생성자
     * Excel 경로가 필수로 필요함
     * @param excelPath Excel 로컬 경로
     */
    public ExcelHandler(String excelPath) {
        this.excelPath = excelPath;
    }

    /**
     * Excel 파일의 Data 를 읽어들여 ExcelData 형태로 변환함
     * @return 변환된 ExcelDataSet instance
     */
    public ExcelDataSet parseExcelData() {
        // ExcelDataSet instance
        ExcelDataSet dataSet = new ExcelDataSet();
        
        try {
            // POI instance 생성
            POIFSFileSystem poif = null;
            
            // http:// 로 시작될 경우 url상의 stream을 이용하여 POIFSFileSystem 생성
            if( excelPath.toLowerCase().startsWith( "http://" )){
                URL urlFile = new URL( excelPath );
                poif = new POIFSFileSystem( urlFile.openStream() );
            }else {
                // File IO 생성
                File excelFile = new File(excelPath);
                // ExcelFile 읽어들임
                poif = new POIFSFileSystem(new FileInputStream(excelFile));
            }

            // Workbook 생성
            HSSFWorkbook hwork = new HSSFWorkbook(poif);
            // 항상 첫번째 sheet 를 사용하도록 함
            HSSFSheet hsheet = hwork.getSheetAt(0);
            // column명 정보를 위해 첫번째 row를 읽음
            HSSFRow titleRow = hsheet.getRow(0);

            HSSFRow hssfRow;
            ExcelData rowData;
            // 전체 row수만큼 읽어서 ExcelDataSet에 저장함
            for( int idx = (hsheet.getFirstRowNum() + 1) ; idx < (hsheet.getLastRowNum() +1); idx++ ) {
                hssfRow = hsheet.getRow( idx );
                
                if( hssfRow != null ) {
                    rowData = parseHssfRow( titleRow, hssfRow );
                    
                    if( !rowData.isEmpty() ) {
                        dataSet.add( parseHssfRow( titleRow, hssfRow ) );
                    }
                }
            }
        } catch (IOException ie) {
        	ie.printStackTrace();
            logger.error( "Excel 파일을 읽는 중 애러가 발생하였습니다." );
        }
        
        return dataSet;
    }
    
    /**
     * column name row와 data row 를 이용하여 column에 해당되는 data를 갖는
     * ExcelData instance를 생성한다
     * @param titleRow column name row
     * @param targetRow data row
     * @return ExcelData instance
     */
    private ExcelData parseHssfRow( HSSFRow titleRow, HSSFRow targetRow ) {
        ExcelData ed = new ExcelData();
        int iCellLen = titleRow.getLastCellNum();
        
        HSSFCell titleCell;
        HSSFCell targetCell;
        String celValue = "";
        
        for( int idx = 0 ; idx < iCellLen ; idx++ ) {
            titleCell = titleRow.getCell( (short)idx );
            targetCell = targetRow.getCell( (short)idx );
            celValue = "";
            if( targetCell != null ) {
                switch( targetCell.getCellType() ) {
                case HSSFCell.CELL_TYPE_NUMERIC :
                    celValue = "" + targetCell.getNumericCellValue();
                    break;
                case HSSFCell.CELL_TYPE_STRING :
                    celValue = targetCell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN :
                    celValue = "" + targetCell.getBooleanCellValue();
                    break;
                }
            }

            ed.put( titleCell.getStringCellValue(), celValue );
        }
        
        return ed;
    }
}
