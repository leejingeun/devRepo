
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

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.commbiz.cmmcode.vo.CommonCodeVO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
* Service Implement 클래스에 대한 내용을 작성한다.
* @author 이진근
* @since 2016. 10. 27.
*/
@Transactional(rollbackFor=Exception.class)
@Service("commonCodeService")
public class CommonCodeServiceImpl extends EgovAbstractServiceImpl implements CommonCodeService {

    @Resource(name = "commonCodeMapper")
    private CommonCodeMapper commonCodeMapper;

    // LCMS 관리자 > 나의강의 > 강의분류 코드조회
    @Override
	public List<CommonCodeVO> selectMyLectureCodeList(CommonCodeVO commonCodeVO) throws Exception {
		 List<CommonCodeVO> resultList = commonCodeMapper.selectMyLectureCodeList(commonCodeVO);
		return resultList;
	}

    // LMS 관리자 > 강의코드 조회
 	@Override
 	public List<CommonCodeVO> selectLecIdAllCodeList(CommonCodeVO commonCodeVO) throws Exception {
 		List<CommonCodeVO> resultList = commonCodeMapper.selectLecIdAllCodeList(commonCodeVO);
 		return resultList;
 	}

 	// LMS 관리자 > 기수코드 조회
 	@Override
 	public List<CommonCodeVO> selectPeriodCodeList(CommonCodeVO commonCodeVO) throws Exception {
 		List<CommonCodeVO> resultList = commonCodeMapper.selectPeriodCodeList(commonCodeVO);
 		return resultList;
 	}

 	// LMS 관리자 > 회원유형코드 조회
  	@Override
  	public List<CommonCodeVO> selectAuthGroupCodeList(CommonCodeVO commonCodeVO) throws Exception {
  		List<CommonCodeVO> resultList = commonCodeMapper.selectAuthGroupCodeList(commonCodeVO);
  		return resultList;
  	}

  	// LMS 관리자 > 설문템플릿유형코드 조회
   	@Override
   	public List<CommonCodeVO> selectSurveyTmplatCodeList(CommonCodeVO commonCodeVO) throws Exception {
   		List<CommonCodeVO> resultList = commonCodeMapper.selectSurveyTmplatCodeList(commonCodeVO);
   		return resultList;
   	}

    // LCMS, LMS 관리자, 사용자 > 공통코드 조회
	@Override
	public List<CommonCodeVO> selectCmmCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectCmmCodeList(commonCodeVO);
		return resultList;
	}

	// LMS 관리자 > SMS 이력 테이블 조회
	@Override
	public List<CommonCodeVO> selectSmsTableList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectSmsTableList(commonCodeVO);
		return resultList;
	}

	// LMS 사용자 > SMS 이력 테이블 조회
	@Override
	public List<CommonCodeVO> selectOjtSubjectCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectOjtSubjectCodeList(commonCodeVO);
		return resultList;
	}

	// LMS 관리자 > SMS 이력 테이블 조회
	@Override
	public List<CommonCodeVO> selectOffJtSubjectCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectOffJtSubjectCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectSubjectNameList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectSubjectNameList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectSubjectNameCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectSubjectNameCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectYearCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectYearCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectTermCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectTermCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectClassCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectClassCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectSubjectCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectSubjectCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectTraningProcessCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectTraningProcessCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectCompanyTraningProcessCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectCompanyTraningProcessCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectCompanyNameCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectCompanyNameCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectCompanyTraningSubjectCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectCompanyTraningSubjectCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectMyCotCompanyCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectMyCotCompanyCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectMyCotCompanyTraningCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectMyCotCompanyTraningCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectMyCotSubjectCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectMyCotSubjectCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectMyCcmCompanyCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectMyCcmCompanyCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectMyCcmCompanyTraningCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectMyCcmCompanyTraningCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectMyCcmSubjectCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectMyCcmSubjectCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectMyStdSubjectCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectMyStdSubjectCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectMyStdClassCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectMyStdClassCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectMyPrtSubjectCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectMyPrtSubjectCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectMyPrtClassCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectMyPrtClassCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectMyCdpSubjectCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectMyCdpSubjectCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectMyCdpClassCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectMyCdpClassCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectMyCottSubjectCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectMyCottSubjectCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectMyCotClassCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectMyCotClassCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectMyStdSubjectList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectMyStdSubjectList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectMyPrtSubjectList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectMyPrtSubjectList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectMyCdpSubjectList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectMyCdpSubjectList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectMyCotSubjectList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectMyCotSubjectList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectMyStdSubjectTypeCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectMyStdSubjectTypeCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectMyPrtSubjectTypeCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectMyPrtSubjectTypeCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectMyCotSubjectTypeCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectMyCotSubjectTypeCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public List<CommonCodeVO> selectMyCdpSubjectTypeCodeList(CommonCodeVO commonCodeVO) throws Exception {
		List<CommonCodeVO> resultList = commonCodeMapper.selectMyCdpSubjectTypeCodeList(commonCodeVO);
		return resultList;
	}

	@Override
	public CommonCodeVO selectYearTerm(CommonCodeVO commonCodeVO) throws Exception {
		CommonCodeVO result = commonCodeMapper.selectYearTerm(commonCodeVO);
		return result;
	}
	@Override
	public CommonCodeVO selectYearTerm() throws Exception {
		CommonCodeVO result = commonCodeMapper.selectYearTerm();
		return result;
	}
}
