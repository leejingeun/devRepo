package kr.co.sitglobal.oklms.lu.report.service;

import java.util.List;

import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningSchVO;
import kr.co.sitglobal.oklms.lu.report.vo.ReportVO;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Transactional(rollbackFor=Exception.class)
public interface ReportService {
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param currProcVO
	 * @return
	 * OnlineTraningSchVO
	 */
	List<OnlineTraningSchVO> listLmsSubjWeek(CurrProcVO currProcVO) throws Exception;
	
	CurrProcVO getCurrproc(CurrProcVO currProcVO) throws Exception;
	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * ReportVO
	 */
	ReportVO getReport(ReportVO reportVO) throws Exception;
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * ReportVO
	 */
	List<ReportVO> listReport(ReportVO reportVO) throws Exception;
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * ReportVO
	 */
	List<ReportVO> listReportStd(ReportVO reportVO) throws Exception;		
 	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * String
	 */
	int insertReport(ReportVO reportVO,final MultipartHttpServletRequest multiRequest) throws Exception;
 	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * String
	 */
	int insertReportStd(ReportVO reportVO,final MultipartHttpServletRequest multiRequest) throws Exception;
	/**
	 * 정보를 수정하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * String
	 */ 
	int updateReport(ReportVO reportVO,final MultipartHttpServletRequest multiRequest) throws Exception;
	
	/**
	 * 정보를 삭제하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * int
	 */
	int deleteReport(ReportVO reportVO) throws Exception;	

	
	/**
	 * 정보를 사업자 번호 중복 체크 Count하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * int
	 */
	int getReportNoCnt(ReportVO reportVO) throws Exception;	
	
	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * ReportVO
	 */
	ReportVO getReportSubmit(ReportVO reportVO) throws Exception;
	
 	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * String
	 */
	int insertReportSubmit(ReportVO reportVO) throws Exception;
	
	/**
	 * 정보를 수정하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * String
	 */ 
	int updateReportSubmit(ReportVO reportVO) throws Exception;
	/**
	 * 정보를 수정하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * String
	 */ 
	int updateReportSubmitArr(ReportVO reportVO) throws Exception;
	/**
	 * 정보를 삭제하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * int
	 */
	int deleteReportSubmit(ReportVO reportVO) throws Exception;
	
	List<ReportVO> listReportSubmit(ReportVO reportVO) throws Exception;	
}
