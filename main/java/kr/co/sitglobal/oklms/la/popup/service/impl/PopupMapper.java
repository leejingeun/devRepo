
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
package kr.co.sitglobal.oklms.la.popup.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.sitglobal.oklms.la.popup.vo.PopupVO;

import org.apache.ibatis.annotations.Param;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

 /**
 * COM_MENU에 대한 CRUD 쿼리를 마이바티스로 연결하는 클레스.
* 이진근
 * @since 2016. 7. 20.
 */
@Mapper("popupMapper")
public interface PopupMapper {

	/**
	 * PopupVO의 전체 목록을 조회하는 SQL 을 호출한다.
	 * @param popupVO
	 * @return
	 * List<PopupVO>
	 */
	List<PopupVO> getPopupAllList(PopupVO popupVO);
	
	/**
	 * PopupVO의 목록을 조회하는 SQL 을 호출한다.
	 * @param popupVO
	 * @return
	 * List<PopupVO>
	 */
	List<PopupVO> getPopupList(PopupVO popupVO);
	
	/**
	 * PopupVO의 목록수을 조회하는 SQL 을 호출한다.
	 * @param popupVO
	 * @return
	 * List<PopupVO>
	 */
	int getPopupListCount(PopupVO popupVO);
	
	/**
	 * PopupVO의 팝업정보를 조회하는 SQL 을 호출한다.
	 * @param popupVO
	 * @return
	 * PopupVO
	 */
	PopupVO getPopup(PopupVO popupVO);
	
	/**
	 * PopupVO의 팝업정보를 조회하는 SQL 을 호출한다.
	 * @param popupVO
	 * @return
	 * PopupVO
	 */
	List<PopupVO> getPopupMinDateMaxDate();
	
	
	/**
	 * PopupVO의 팝업정보를 조회하는 SQL 을 호출한다.
	 * @param popupVO
	 * @return
	 * PopupVO
	 */
	int insertPopup(PopupVO popupVO);
	
	int updatePopup(PopupVO popupVO);

	int updatePopupIsUse(PopupVO popupVO);
	
	int deletePopup(PopupVO popupVO);

}
