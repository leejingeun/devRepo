package kr.co.sitglobal.oklms.la.company.service;

import java.util.List;

import kr.co.sitglobal.oklms.la.company.vo.CompanyVO;

import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor=Exception.class)
public interface CompanyService {
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param companyVO
	 * @return
	 * List<CompanyVO>
	 */
	List<CompanyVO> listCompany(CompanyVO companyVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
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
	 * DB에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param companyVO
	 * @return
	 * CompanyVO
	 */
	CompanyVO getCompany(CompanyVO companyVO) throws Exception;

     /**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param companyVO
	 * @return
	 * String
	 */
	String insertCompany(CompanyVO companyVO) throws Exception;


	/**
	 * DB에서 Data를 수정하는 로직을 수행한다.
	 * @param companyVO
	 * @return
	 * String
	 */
	int updateCompany(CompanyVO companyVO) throws Exception;


	/**
	 * DB에서 Data를 삭제하는 로직을 수행한다.
	 * @param companyVO
	 * @return
	 * int
	 */
	int deleteCompany(CompanyVO companyVO) throws Exception;

	/**
	 * DB에서 사업자 번호 중복 체크 Count 로직을 수행한다.
	 * @param companyVO
	 * @return
	 * int
	 */
	int getCompanyNoCnt(CompanyVO companyVO) throws Exception;

}
