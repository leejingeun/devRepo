
/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
* 이진근    2016. 07. 01.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.la.popup.service;

import java.util.List;

import kr.co.sitglobal.oklms.la.popup.vo.PopupVO;

import org.springframework.transaction.annotation.Transactional;

 /**
 * COM_MENU에 대한 Service Interface 입니다.
* 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
public interface PopupService {
	/**
	 * COM_POPUP 에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param popupVO
	 * @return
	 * List<PopupVO>
	 */
	public List<PopupVO> getPopupAllList(PopupVO popupVO) throws Exception;
	public List<PopupVO> getPopupList(PopupVO popupVO) throws Exception;
	public PopupVO getPopup(PopupVO popupVO) throws Exception;
	
	/**
	 * COM_POPUP 에서 Data를 추가하는 로직을 수행한다.
	 * @param popupVO
	 * @return
	 * String
	 */
	int insertPopup(PopupVO popupVO) throws Exception;

	/**
	 * COM_POPUP 에서 Data를 수정하는 로직을 수행한다.
	 * @param popupVO
	 * @return
	 * int
	 */
	int updatePopup(PopupVO popupVO) throws Exception;
	
	
	/**
	 * COM_POPUP 에서 Data를 사용여부 수정하는 로직을 수행한다.
	 * @param popupVO
	 * @return
	 * int
	 */
	int updatePopupIsUse(PopupVO popupVO) throws Exception;
	

	/**
	 * COM_POPUP 에서 Data를 삭제하는 로직을 수행한다.
	 * @param popupVO
	 * @return
	 * int
	 */
	int deletePopup(PopupVO popupVO) throws Exception;
}
