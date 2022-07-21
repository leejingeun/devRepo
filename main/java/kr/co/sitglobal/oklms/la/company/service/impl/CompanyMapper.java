package kr.co.sitglobal.oklms.la.company.service.impl;

import java.util.List;

import kr.co.sitglobal.oklms.la.company.vo.CompanyVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("companyMapper")
public interface CompanyMapper {

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param companyVO
	 * @return
	 * List<CompanyVO>
	 */
	List<CompanyVO> listCompany(CompanyVO companyVO) throws Exception;

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param companyVO
	 * @return
	 * List<CompanyVO>
	 */
	List<CompanyVO> listCompanySearch(CompanyVO companyVO) throws Exception;

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param companyVO
	 * @return
	 * List<CompanyVO>
	 */
	List<CompanyVO> listCompanyTraningProcess(CompanyVO companyVO) throws Exception;

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param companyVO
	 * @return
	 * List<CompanyVO>
	 */
	List<CompanyVO> listCompanyTraningProcessSearch(CompanyVO companyVO) throws Exception;

	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param companyVO
	 * @return
	 * CompanyVO
	 */
	CompanyVO getCompany(CompanyVO companyVO) throws Exception;

	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param companyVO
	 * @return
	 * String
	 */
	int insertCompany(CompanyVO companyVO) throws Exception;

	/**
	 * 정보를 수정하는 SQL 을 호출한다.
	 * @param companyVO
	 * @return
	 * String
	 */
	int updateCompany(CompanyVO companyVO) throws Exception;

	/**
	 * 정보를 삭제하는 SQL 을 호출한다.
	 * @param companyVO
	 * @return
	 * int
	 */
	int deleteCompany(CompanyVO companyVO) throws Exception;


	/**
	 * 정보를 사업자 번호 중복 체크 Count하는 SQL 을 호출한다.
	 * @param companyVO
	 * @return
	 * int
	 */
	int getCompanyNoCnt(CompanyVO companyVO) throws Exception;

	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * MemberVO
	 */
	String getCompanyName(String companyName) throws Exception;
}
