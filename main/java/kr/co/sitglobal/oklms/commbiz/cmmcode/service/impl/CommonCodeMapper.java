
/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 11. 21.         First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.commbiz.cmmcode.service.impl;

import java.util.List;

import kr.co.sitglobal.oklms.commbiz.cmmcode.vo.CommonCodeVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
* CRUD 쿼리를 마이바티스로 연결하는 클레스.
* @author 이진근
* @since 2016. 10. 27.
*/
@Mapper("commonCodeMapper")
public interface CommonCodeMapper {

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyLectureCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectLecIdAllCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectPeriodCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectCmmCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectAuthGroupCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectSurveyTmplatCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectSmsTableList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectOjtSubjectCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectOffJtSubjectCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * 교과 코드를 가져 온다
	 * @param commonCodeVO
	 * @return
	 * @throws Exception
	 */
	List<CommonCodeVO> selectSubjectNameList(CommonCodeVO commonCodeVO)throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectYearCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectTermCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectClassCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectSubjectCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectSubjectNameCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectTraningProcessCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param CommonCodeVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectCompanyTraningProcessCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectCompanyNameCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectCompanyTraningSubjectCodeList(CommonCodeVO commonCodeVO) throws Exception;


	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyCotCompanyCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyCotCompanyTraningCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyCotSubjectCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyCcmCompanyCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyCcmCompanyTraningCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyCcmSubjectCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyStdSubjectCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyStdClassCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyPrtSubjectCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyPrtClassCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyCdpSubjectCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyCdpClassCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyCottSubjectCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyCotClassCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyStdSubjectList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyPrtSubjectList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyCdpSubjectList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyCotSubjectList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyStdSubjectTypeCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyPrtSubjectTypeCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyCotSubjectTypeCodeList(CommonCodeVO commonCodeVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lectureLcmsVO
	 * @return
	 * List<CommonCodeVO>
	 */
	List<CommonCodeVO> selectMyCdpSubjectTypeCodeList(CommonCodeVO commonCodeVO) throws Exception;
	/**
	 * 학년도 학기
	 * @param commonCodeVO
	 * @return
	 * @throws Exception
	 */
	CommonCodeVO selectYearTerm(CommonCodeVO commonCodeVO)throws Exception;
	/**
	 * 현재 학년도 학기
	 * @param commonCodeVO
	 * @return
	 * @throws Exception
	 */
	CommonCodeVO selectYearTerm()throws Exception;

}
