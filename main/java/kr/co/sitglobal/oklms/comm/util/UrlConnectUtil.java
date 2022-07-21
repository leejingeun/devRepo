
/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 8. 23.         First Draft.
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.comm.util;

import java.util.ArrayList;
import java.util.regex.Pattern;

import kr.co.sitglobal.oklms.commbiz.menu.vo.CommbizMenuVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 /**
 * 호출 URL에 대한 처리.
 * @author 이진근
 * @since 2016. 8. 23.
 */
public class UrlConnectUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(UrlConnectUtil.class);
	/**
	 * menuId 와 호출된 URL 을 이용하여 접근 가능한 URL 인지 식별해준다.
	 * @param menuStructureList : 로그인 사용자에따라 매뉴에대한 권한을 가지고있다.(메뉴 정보는 로그인시 session에 저장하게되어있음. )
	 * @param originalURL
	 * @return
	 * boolean
	 */
	public static CommbizMenuVO isRequestUrlAble(ArrayList<CommbizMenuVO> menuStructureList , String originalURL) {

		LOGGER.info( "#### isRequestUrlAble() originalURL=" + originalURL );

//		originalURL = StringUtils.lowerCase(originalURL);
		
		String subStrOriginalURL = StringUtils.substringBefore(originalURL, "?");
		
		
		CommbizMenuVO menuObj = null;
//		boolean isPermittedURL = false;
		for (CommbizMenuVO menuVO : menuStructureList) {
			
			// menuStructureList 에 있는 권한 여부 값과 권한 첵크용 URL 패턴 값을이용하여 패턴 첵크한다.
			
			String createAuthYn = menuVO.getCreateAuthYn();
			String readAuthYn = menuVO.getReadAuthYn();
			String updateAuthYn = menuVO.getUpdateAuthYn();
			String deleteAuthYn = menuVO.getDeleteAuthYn();
			String printAuthYn = menuVO.getPrintAuthYn();
			String downloadAuthYn = menuVO.getDownloadAuthYn();
			String otherAuthYn = menuVO.getOtherAuthYn();
			String listAuthYn = menuVO.getListAuthYn();
			
			String createAuthUrlPattern = StringUtils.defaultIfBlank( menuVO.getCreateAuthUrlPattern() , "");	
			String readAuthUrlPattern = StringUtils.defaultIfBlank( menuVO.getReadAuthUrlPattern() , "");
			String updateAuthUrlPattern = StringUtils.defaultIfBlank( menuVO.getUpdateAuthUrlPattern() , "");
			String deleteAuthUrlPattern = StringUtils.defaultIfBlank( menuVO.getDeleteAuthUrlPattern() , "");
			String printAuthUrlPattern = StringUtils.defaultIfBlank( menuVO.getPrintAuthUrlPattern() , "");
			String downloadAuthUrlPattern = StringUtils.defaultIfBlank( menuVO.getDownloadAuthUrlPattern() , "");
			String otherAuthUrlPattern = StringUtils.defaultIfBlank( menuVO.getOtherAuthUrlPattern() , "");
			String listAuthUrlPattern = StringUtils.defaultIfBlank( menuVO.getListAuthUrlPattern() , "");
			

//			LOGGER.debug( "#### Check URL!!! (originalURL ) : " + originalURL 
//					+ " , menuVO.getMenuId() :" + menuVO.getMenuId()
//					+ " , menuVO.getMenuTitle() :" + menuVO.getMenuTitle()
//					+ ", listAuthUrlPattern :" + listAuthUrlPattern
//					+ ", createAuthUrlPattern :" + createAuthUrlPattern
//					+ ", readAuthUrlPattern :" + readAuthUrlPattern
//					+ ", updateAuthUrlPattern :" + updateAuthUrlPattern
//					+ ", deleteAuthUrlPattern :" + deleteAuthUrlPattern
//					+ ", printAuthUrlPattern :" + printAuthUrlPattern
//					+ ", downloadAuthUrlPattern :" + downloadAuthUrlPattern
//					+ ", otherAuthUrlPattern :" + otherAuthUrlPattern
//					);

//			if( Pattern.matches( listAuthUrlPattern , subStrOriginalURL) ){
			if( Pattern.matches( listAuthUrlPattern , originalURL) ){
				LOGGER.debug("");
				LOGGER.debug( "#### Match Found !!! [ LIST ] Pattern.matches (authUrlPattern :originalURL ) = (" + listAuthUrlPattern + " : " + originalURL + ") , menuVO.getMenuId():" + menuVO.getMenuId()  + "(" + menuVO.getMenuTitle() + ")" );
				if( "Y".equals( listAuthYn ) ){
					LOGGER.debug( "#### Accessible.!!! [ LIST ]" );
					menuObj = menuVO;
				}
				
				if( "1".equals( menuVO.getIsLeafMenu() ) ){
					LOGGER.debug("#### IsLeafMenu !!!");
					break;
				}
				LOGGER.debug("");
			}else if( Pattern.matches( createAuthUrlPattern , originalURL) ){
				LOGGER.debug("");
				LOGGER.debug( "#### Match Found !!! [ CREATE ] Pattern.matches (authUrlPattern :originalURL ) = (" + createAuthUrlPattern + " : " + originalURL + ") , menuVO.getMenuId():" + menuVO.getMenuId()  + "(" + menuVO.getMenuTitle() + ")" );
				if( "Y".equals( createAuthYn ) ){
					LOGGER.debug( "#### Accessible.!!! [ CREATE ]" );
					menuObj = menuVO;
				}
				
				if( "1".equals( menuVO.getIsLeafMenu() ) ){
					LOGGER.debug("#### IsLeafMenu !!!");
					break;
				}
				LOGGER.debug("");
			}else if( Pattern.matches( readAuthUrlPattern , originalURL) ){
				LOGGER.debug("");
				LOGGER.debug( "#### Match Found !!! [ READ ] Pattern.matches (authUrlPattern :originalURL ) = (" + readAuthUrlPattern + " : " + originalURL + ") , menuVO.getMenuId():" + menuVO.getMenuId()  + "(" + menuVO.getMenuTitle() + ")" );
				if( "Y".equals( readAuthYn ) ){
					LOGGER.debug( "#### Accessible.!!! [ READ ]" );
					menuObj = menuVO;
				}
				
				if( "1".equals( menuVO.getIsLeafMenu() ) ){
					LOGGER.debug("#### IsLeafMenu !!!");
					break;
				}
				LOGGER.debug("");
			}else if( Pattern.matches( updateAuthUrlPattern , originalURL) ){
				LOGGER.debug("");
				LOGGER.debug( "#### Match Found !!! [ UPDATE ] Pattern.matches (authUrlPattern :originalURL ) = (" + updateAuthUrlPattern + " : " + originalURL + ") , menuVO.getMenuId():" + menuVO.getMenuId()  + "(" + menuVO.getMenuTitle() + ")" );
				if( "Y".equals( updateAuthYn ) ){
					LOGGER.debug( "#### Accessible.!!! [ UPDATE ]" );
					menuObj = menuVO;
				}
				
				if( "1".equals( menuVO.getIsLeafMenu() ) ){
					LOGGER.debug("#### IsLeafMenu !!!");
					break;
				}
				LOGGER.debug("");
			}else if( Pattern.matches( deleteAuthUrlPattern , originalURL) ){
				LOGGER.debug("");
				LOGGER.debug( "#### Match Found !!! [ DELETE ] Pattern.matches (authUrlPattern :originalURL ) = (" + deleteAuthUrlPattern + " : " + originalURL + ") , menuVO.getMenuId():" + menuVO.getMenuId()  + "(" + menuVO.getMenuTitle() + ")" );
				if( "Y".equals( deleteAuthYn ) ){
					LOGGER.debug( "#### Accessible.!!! [ DELETE ]" );
					menuObj = menuVO;
				}
				
				if( "1".equals( menuVO.getIsLeafMenu() ) ){
					LOGGER.debug("#### IsLeafMenu !!!");
					break;
				}
				LOGGER.debug("");
			}else if( Pattern.matches( printAuthUrlPattern , originalURL) ){
				LOGGER.debug("");
				LOGGER.debug( "#### Match Found !!! [ PRINT ] Pattern.matches (authUrlPattern :originalURL ) = (" + printAuthUrlPattern + " : " + originalURL + ") , menuVO.getMenuId():" + menuVO.getMenuId()  + "(" + menuVO.getMenuTitle() + ")" );
				if( "Y".equals( printAuthYn ) ){
					LOGGER.debug( "#### Accessible.!!! [ PRINT ]" );
					menuObj = menuVO;
				}
				
				if( "1".equals( menuVO.getIsLeafMenu() ) ){
					LOGGER.debug("#### IsLeafMenu !!!");
					break;
				}
				LOGGER.debug("");
			}else if( Pattern.matches( downloadAuthUrlPattern , originalURL) ){
				LOGGER.debug("");
				LOGGER.debug( "#### Match Found !!! [ DOWNLOAD ] Pattern.matches (authUrlPattern :originalURL ) = (" + downloadAuthUrlPattern + " : " + originalURL + ") , menuVO.getMenuId():" + menuVO.getMenuId()  + "(" + menuVO.getMenuTitle() + ")" );
				if( "Y".equals( downloadAuthYn ) ){
					LOGGER.debug( "#### Accessible.!!! [ DOWNLOAD ]" );
					menuObj = menuVO;
				}
				
				if( "1".equals( menuVO.getIsLeafMenu() ) ){
					LOGGER.debug("#### IsLeafMenu !!!");
					break;
				}
				LOGGER.debug("");
			}else if( Pattern.matches( otherAuthUrlPattern , originalURL) ){
				LOGGER.debug("");
				LOGGER.debug( "#### Match Found !!! [ OTHER ] Pattern.matches (authUrlPattern :originalURL ) = (" + otherAuthUrlPattern + " : " + originalURL + ") , menuVO.getMenuId():" + menuVO.getMenuId()  + "(" + menuVO.getMenuTitle() + ")" );
				if( "Y".equals( otherAuthYn ) ){
					LOGGER.debug( "#### Accessible.!!! [ OTHER ]" );
					menuObj = menuVO;
				}
				
				if( "1".equals( menuVO.getIsLeafMenu() ) ){
					LOGGER.debug("#### IsLeafMenu !!!");
					break;
				}
				LOGGER.debug("");
			}
		} // for
		if( null == menuObj ){

			LOGGER.debug("");
			LOGGER.debug( "#### Match URL Not Found !!! originalURL : " + originalURL );
			LOGGER.debug("");
		}
		
//		return isPermittedURL;
		return menuObj;
	}
}
