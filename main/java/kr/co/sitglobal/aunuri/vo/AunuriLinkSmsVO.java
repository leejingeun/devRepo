/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2017. 01. 06.         First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.aunuri.vo;

import java.io.Serializable;
import java.sql.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kr.co.sitglobal.oklms.comm.util.TextStringUtil;
import kr.co.sitglobal.oklms.comm.vo.BaseVO;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.web.multipart.MultipartFile;

/**
 * VO 클래스에 대한 내용을 작성한다.
 * 
 * @author 이진근
 * @since 2017. 01. 06.
 */
public class AunuriLinkSmsVO extends BaseVO implements Serializable {

	
	private String memSeq = "";
	private String memId = "";
	
}
