
/*******************************************************************************
 * COPYRIGHT(C) 2016 WIZI LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of WIZI LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 12. 28.        First Draft.
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.lu.train.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

 /**
 * VO 클래스에 대한 내용을 작성한다.
 * @author 이진근
 * @since 2016. 12. 28.
 */
public class TrainVO extends BaseVO implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 7647510152514932222L;
	
	private String yyyy	= ""; 			//학년도
    private String term	= ""; 			//학기
    private String subjectCode	= ""; 	//교과목코드
    private String subjectName	= ""; 	//교과목명
    private String classs	= ""; 		//분반
    private String weekCnt	= ""; 		//학습주차
    private String field = ""; 		//분야
    
    private String category_code="";
    
    private String subjectClass                 ;
    private String subjectTraningType           ;
    private String subjectTraningTypeName       ;
    private String departmentName               ;
    private String grade                        ;
    private String pointUseYn                   ;
    private String point                        ;
    private String subjectType                  ;
    private String subjectTypeName              ;
    private String onlineType                   ;
    private String onlineTypeName               ;
    private String traningHour                  ;
    private String firstStudyYn                 ;
    private String insName                      ;
    private String gradeRatio										;
    
	public String getYyyy() {
		return yyyy;
	}
	public void setYyyy(String yyyy) {
		this.yyyy = yyyy;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getClasss() {
		return classs;
	}
	public void setClasss(String classs) {
		this.classs = classs;
	}
	public String getWeekCnt() {
		return weekCnt;
	}
	public void setWeekCnt(String weekCnt) {
		this.weekCnt = weekCnt;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	public String getcategory_code() {
		return category_code;
	}

	public void setcategory_code(String category_code) {
		this.category_code = category_code;
	}
	
	public String toString() {
        final String NL  = "\n";
        final String TAB = "    ";
        
        String retValue = "";
        
        retValue = "Lu > Train > Vo > TrainVO ( "
            + super.toString() + NL
            + TAB + "yyyy = " + this.yyyy + NL
            + TAB + "term = " + this.term + NL
            + TAB + "subjectCode = " + this.subjectCode + NL
            + TAB + "subjectName = " + this.subjectName + NL
            + TAB + "classs = " + this.classs + NL
            + TAB + "weekCnt = " + this.weekCnt + NL
            + TAB + "field = " + this.field + NL
            + TAB + "category_code = " + this.category_code + NL
            + " )";
    
        return retValue;
    }
}
