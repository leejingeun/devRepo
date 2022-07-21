
/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
* 이진근    2016. 7. 20.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.commbiz.menu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.co.sitglobal.oklms.commbiz.menu.vo.CommbizMenuVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

 /**
 * COM_MENU에 대한 CRUD 쿼리를 마이바티스로 연결하는 클레스.
* 이진근
 * @since 2016. 7. 20.
 */
@Mapper("commbizMenuMapper")
public interface CommbizMenuMapper {
 
	/**
	 * 메뉴 목록 : 메인 메뉴 및 각 서브 메뉴에서 사용.
	 * @param commandMap
	 * @return
	 * List<CommbizMenuVO>
	 */
	ArrayList<CommbizMenuVO> listMenu(Map<String, Object> commandMap) throws Exception;
	ArrayList<CommbizMenuVO> mobilelistMenu(Map<String, Object> commandMap) throws Exception;
}
