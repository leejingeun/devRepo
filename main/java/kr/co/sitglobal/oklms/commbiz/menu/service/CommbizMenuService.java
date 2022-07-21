
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
package kr.co.sitglobal.oklms.commbiz.menu.service;

import java.util.ArrayList;
import java.util.Map;

import kr.co.sitglobal.oklms.commbiz.menu.vo.CommbizMenuVO;

import org.springframework.transaction.annotation.Transactional;

 /**
 * COM_MENU에 대한 Service Interface 입니다.
* 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
public interface CommbizMenuService {

	
	/**
	 * 메뉴 목록 : 메인 메뉴 및 각 서브 메뉴에서 사용.
	 * authGroupId 값이 없으면 게스트 권한 '2016AUTH0000004' 로 셋팅 처리하여 조회한다.
	 * @param commandMap
	 * @return
	 * @throws Exception
	 * List<CommbizMenuVO>
	 */
 	ArrayList<CommbizMenuVO> listMenu(Map<String, Object> commandMap) throws Exception;
 	
 	ArrayList<CommbizMenuVO> mobilelistMenu(Map<String, Object> commandMap) throws Exception;
}
