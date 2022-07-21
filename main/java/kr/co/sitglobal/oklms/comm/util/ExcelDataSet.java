package kr.co.sitglobal.oklms.comm.util;

import java.util.ArrayList;

/**
 * <pre>
 * Description  : 
 *
 * System       : sitglobalLEARN_LMS
 * Program ID   : ExcelDataSet
 * Creater      : 안병진
 * Create Date  : 2007. 04. 10
 * Updater      : 
 * Update Date  : 
 * Update Desc. : 
 * </pre>
 * @version 1.0
 * @author Copyright (c) 2007 by sitglobal. All Rights Reserved.
 */
public class ExcelDataSet extends ArrayList {
    
    /**
     * 
     */
    private static final long serialVersionUID = -7772150118684950381L;

    public ExcelData getRow(int idx ) {
        return (ExcelData)super.get(idx);
    }
    
}
