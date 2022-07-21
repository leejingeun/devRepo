package kr.co.sitglobal.oklms.comm.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.springframework.util.StringUtils;

/**
 * <pre>
 * Description  : 
 *
 * System       : sitglobalLEARN_LMS
 * Program ID   : ExcelData
 * Creater      : 안병진
 * Create Date  : 2007. 04. 10
 * Updater      : 
 * Update Date  : 
 * Update Desc. : 
 * </pre>
 * @version 1.0
 * @author Copyright (c) 2007 by sitglobal. All Rights Reserved.
 */
public class ExcelData extends HashMap {
    /**
     * 
     */
    private static final long serialVersionUID = 4879532842222600420L;

    public String getColumn( String columnName ) {
        return (String)super.get( columnName );
    }
    
    public float getFloatValue( String columnName ){        
        return new Float((String)super.get( columnName )).floatValue();
    }
    
    public boolean isEmpty() {
        boolean isEmpty = true;
        Set keySet = super.keySet();
        Iterator i = keySet.iterator();
        Object value;
        while( i.hasNext() ) {
            value = super.get( i.next() );
            
            if( value != null && StringUtils.hasText( value.toString() ) ) {
                isEmpty = false;
                break;
            }
        }
        
        return isEmpty;
    }
}
