/**
 * 개요
 * - 배너에 대한 DAO 클래스를 정의한다.
 * 
 * 상세내용
 * - 배너에 대한 등록, 수정, 삭제, 조회, 반영확인 기능을 제공한다.
 * - 배너의 조회기능은 목록조회, 상세조회로 구분된다.
 * @author 이문준
 * @version 1.0
 * @created 03-8-2009 오후 2:07:11
 */

package kr.co.sitglobal.aunuri.service.impl;
import egovframework.com.cmm.service.impl.EgovComOracleDAO;

import org.springframework.stereotype.Repository;

@Repository("aunuriLinkDAO")
public class AunuriLinkDAO extends EgovComOracleDAO {
	/**
	 * 배너목록 총 갯수를 조회한다.
	 * @param bannerVO BannerVO
	 * @return int
	 * @exception Exception
	 */
    public int selectTest() throws Exception {
    	 return (Integer)select("AunuriLinkDAO.selectCount");
    }

   
}
