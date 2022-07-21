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
package kr.co.sitglobal.oklms.commbiz.menu.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;
import kr.co.sitglobal.oklms.la.menu.vo.MenuVO;

/**
 * COM_MENU 테이블에 대응되는 VO 클래스입니다.
 * 
* 이진근
 * @since 2016. 07. 01.
 */
public class CommbizMenuVO extends MenuVO implements Serializable {

	private static final long serialVersionUID = -6629473162289485404L;

	private String menuLevel;
	private String rootMenuId; // 계층구조 쿼리에서 LEVEL이 0인 최상위 로우의 정보를 얻어 올 수 있다.
	private String isLeafMenu; // 계층구조 쿼리에서 로우의 최하위 레벨(Leaf) 여부를 반환한다. 최하이 레벨이면 1, 아니면 0
	private String menuPath; // 계층구조 쿼리에서 현재 로우 까지의 PATH 정보를 쉽게 얻어 올 수 있다. ( @통계/모니터링@통계정보@수리비용 순위 )
	private String menuIdPath; // 계층구조 쿼리에서 현재 로우 까지의 ID PATH 정보를 쉽게 얻어 올 수 있다 ( @mnu00006@mnu00007@mnu00012 )
	private String menuPathLeafNode;
	private String menuIdPathLeafNode;

	private String authGroupId; // 사용자 그룹 아이디
	private String deleteYn; // 권한 설정 삭제 여부
	private String createAuthYn; // 생성 권한 여부
	private String readAuthYn; // 조회 권한 여부
	private String updateAuthYn; // 수정 권한 여부
	private String deleteAuthYn; // 삭제 권한 여부
	private String printAuthYn; // 출력 권한 여부
	private String downloadAuthYn; // 다운로드 권한 여부
	private String otherAuthYn; // 기타 권한 여부
	private String listAuthYn; // 리스트 조회 권한 여부

	private boolean canRetrieve=false;
	private boolean canCreate=false;

	private String menuArea;
	
	
	
	public String getMenuArea() {
		return menuArea;
	}

	public void setMenuArea(String menuArea) {
		this.menuArea = menuArea;
	}

	public boolean getCanRetrieve() {
		if( "Y".equals( readAuthYn )){
			canRetrieve = true;
		}
		return canRetrieve;
	}

	public void setCanRetrieve(boolean canRetrieve) {
		this.canRetrieve = canRetrieve;
	}

	public boolean getCanCreate() {
		if( "Y".equals( createAuthYn )){
			canCreate = true;
		}
		return canCreate;
	}

	public void setCanCreate(boolean canCreate) {
		this.canCreate = canCreate;
	}

	/**
	 * @return the menuLevel
	 */
	public String getMenuLevel() {
		return menuLevel;
	}
	/**
	 * @param menuLevel the menuLevel to set
	 */
	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}
	/**
	 * @return the rootMenuId
	 */
	public String getRootMenuId() {
		return rootMenuId;
	}
	/**
	 * @param rootMenuId the rootMenuId to set
	 */
	public void setRootMenuId(String rootMenuId) {
		this.rootMenuId = rootMenuId;
	}
	/**
	 * @return the isLeafMenu
	 */
	public String getIsLeafMenu() {
		return isLeafMenu;
	}
	/**
	 * @param isLeafMenu the isLeafMenu to set
	 */
	public void setIsLeafMenu(String isLeafMenu) {
		this.isLeafMenu = isLeafMenu;
	}
	/**
	 * @return the menuPath
	 */
	public String getMenuPath() {
		return menuPath;
	}
	/**
	 * @param menuPath the menuPath to set
	 */
	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}
	/**
	 * @return the menuIdPath
	 */
	public String getMenuIdPath() {
		return menuIdPath;
	}
	/**
	 * @param menuIdPath the menuIdPath to set
	 */
	public void setMenuIdPath(String menuIdPath) {
		this.menuIdPath = menuIdPath;
	}
	/**
	 * @return the menuPathLeafNode
	 */
	public String getMenuPathLeafNode() {
		return menuPathLeafNode;
	}
	/**
	 * @param menuPathLeafNode the menuPathLeafNode to set
	 */
	public void setMenuPathLeafNode(String menuPathLeafNode) {
		this.menuPathLeafNode = menuPathLeafNode;
	}
	/**
	 * @return the menuIdPathLeafNode
	 */
	public String getMenuIdPathLeafNode() {
		return menuIdPathLeafNode;
	}
	/**
	 * @param menuIdPathLeafNode the menuIdPathLeafNode to set
	 */
	public void setMenuIdPathLeafNode(String menuIdPathLeafNode) {
		this.menuIdPathLeafNode = menuIdPathLeafNode;
	}
	/**
	 * @return the authGroupId
	 */
	public String getAuthGroupId() {
		return authGroupId;
	}
	/**
	 * @param authGroupId the authGroupId to set
	 */
	public void setAuthGroupId(String authGroupId) {
		this.authGroupId = authGroupId;
	}
	/**
	 * @return the deleteYn
	 */
	public String getDeleteYn() {
		return deleteYn;
	}
	/**
	 * @param deleteYn the deleteYn to set
	 */
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	/**
	 * @return the createAuthYn
	 */
	public String getCreateAuthYn() {
		return createAuthYn;
	}
	/**
	 * @param createAuthYn the createAuthYn to set
	 */
	public void setCreateAuthYn(String createAuthYn) {
		this.createAuthYn = createAuthYn;
	}
	/**
	 * @return the readAuthYn
	 */
	public String getReadAuthYn() {
		return readAuthYn;
	}
	/**
	 * @param readAuthYn the readAuthYn to set
	 */
	public void setReadAuthYn(String readAuthYn) {
		this.readAuthYn = readAuthYn;
	}
	/**
	 * @return the updateAuthYn
	 */
	public String getUpdateAuthYn() {
		return updateAuthYn;
	}
	/**
	 * @param updateAuthYn the updateAuthYn to set
	 */
	public void setUpdateAuthYn(String updateAuthYn) {
		this.updateAuthYn = updateAuthYn;
	}
	/**
	 * @return the deleteAuthYn
	 */
	public String getDeleteAuthYn() {
		return deleteAuthYn;
	}
	/**
	 * @param deleteAuthYn the deleteAuthYn to set
	 */
	public void setDeleteAuthYn(String deleteAuthYn) {
		this.deleteAuthYn = deleteAuthYn;
	}
	/**
	 * @return the printAuthYn
	 */
	public String getPrintAuthYn() {
		return printAuthYn;
	}
	/**
	 * @param printAuthYn the printAuthYn to set
	 */
	public void setPrintAuthYn(String printAuthYn) {
		this.printAuthYn = printAuthYn;
	}
	/**
	 * @return the downloadAuthYn
	 */
	public String getDownloadAuthYn() {
		return downloadAuthYn;
	}
	/**
	 * @param downloadAuthYn the downloadAuthYn to set
	 */
	public void setDownloadAuthYn(String downloadAuthYn) {
		this.downloadAuthYn = downloadAuthYn;
	}
	/**
	 * @return the otherAuthYn
	 */
	public String getOtherAuthYn() {
		return otherAuthYn;
	}
	/**
	 * @param otherAuthYn the otherAuthYn to set
	 */
	public void setOtherAuthYn(String otherAuthYn) {
		this.otherAuthYn = otherAuthYn;
	}
	/**
	 * @return the listAuthYn
	 */
	public String getListAuthYn() {
		return listAuthYn;
	}
	/**
	 * @param listAuthYn the listAuthYn to set
	 */
	public void setListAuthYn(String listAuthYn) {
		this.listAuthYn = listAuthYn;
	}

}
