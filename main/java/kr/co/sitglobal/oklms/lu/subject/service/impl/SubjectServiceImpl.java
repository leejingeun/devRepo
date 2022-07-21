/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2017. 01. 06.         First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.lu.subject.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.grade.vo.GradeVO;
import kr.co.sitglobal.oklms.lu.subject.service.SubjectService;
import kr.co.sitglobal.oklms.lu.subject.vo.SubjectCompanyVO;
import kr.co.sitglobal.oklms.lu.subject.vo.SubjectVO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.apache.commons.beanutils.BeanUtils;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

 /**
 * 프로토타입 게시판 CRUD 비지니스 로직을 구현하는 클레스.
 * @author 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
@Service("SubjectService")
public class SubjectServiceImpl extends EgovAbstractServiceImpl implements SubjectService {

	/** ID Generation */
    /*@Resource(name="subjectIdGnrService")
    private EgovIdGnrService subjectIdGnrService;*/

	@Resource(name = "SubjectMapper")
    private SubjectMapper subjectMapper;

	@Override
	public SubjectVO getSubjectInfo(SubjectVO SubjectVO) throws Exception {
		SubjectVO data = subjectMapper.getSubjectInfo(SubjectVO);
		return data;
	}


	@Override
	public List<SubjectVO> listSubjectPrt(SubjectVO subjectVO) throws Exception {

		List<SubjectVO> listSubjectPrt = null;

		if(subjectVO.getSubjectTraningType().equals("OJT")){
			if(subjectVO.getSearchPeriodType().equals("TERM")){
				listSubjectPrt =  subjectMapper.listInChargeSubjectPrtOjt(subjectVO);
			} else {
				listSubjectPrt =  subjectMapper.listInChargeSubjectPrtOjtWeek(subjectVO);
			}
		} else {
			if(subjectVO.getSearchPeriodType().equals("TERM")){
				listSubjectPrt = subjectMapper.listInChargeSubjectPrtOff(subjectVO);
			} else {
				listSubjectPrt = subjectMapper.listInChargeSubjectPrtOffWeek(subjectVO);
			}
		}
		return listSubjectPrt;
	}

	@Override
	public List<SubjectVO> listSubjectCdp(SubjectVO subjectVO) throws Exception {
		return subjectMapper.listInChargeSubjectCdpOff(subjectVO);
	}

	@Override
	public List<SubjectVO> listSubjectStd(SubjectVO subjectVO) throws Exception {
		List<SubjectVO> listSubjectStd = null;

		if(subjectVO.getSubjectTraningType().equals("OJT")){
			if(subjectVO.getSearchPeriodType().equals("TERM")){
				listSubjectStd =  subjectMapper.listInChargeSubjectStdOjt(subjectVO);
			} else {
				listSubjectStd =  subjectMapper.listInChargeSubjectStdOjtWeek(subjectVO);
			}
		} else {
			if(subjectVO.getSearchPeriodType().equals("TERM")){
				listSubjectStd = subjectMapper.listInChargeSubjectStdOff(subjectVO);
			} else {
				listSubjectStd = subjectMapper.listInChargeSubjectStdOffWeek(subjectVO);
			}
		}
		return listSubjectStd;
	}

	@Override
	public List<SubjectVO> listSubjectCot(SubjectVO subjectVO) throws Exception {
		List<SubjectVO> listSubjectCot = null;

		if(subjectVO.getSearchPeriodType().equals("TERM")){
			listSubjectCot =  subjectMapper.listInChargeSubjectCotOjt(subjectVO);
		} else {
			listSubjectCot =  subjectMapper.listInChargeSubjectCotOjtWeek(subjectVO);
		}

		return listSubjectCot;
	}

	@Override
	public List<SubjectCompanyVO> listCompanyCcn(SubjectCompanyVO subjectCompanyVO) throws Exception {
		List<SubjectCompanyVO> listCompanyCcn = null;
		if(subjectCompanyVO.getSearchStatusType().equals("STU")){
			listCompanyCcn =  subjectMapper.listInChargeCompanyStudyStateCcn(subjectCompanyVO);
		} else {
			listCompanyCcn =  subjectMapper.listInChargeCompanyStateCcn(subjectCompanyVO);
		}

		return listCompanyCcn;
	}


	@Override
	public SubjectCompanyVO getActivityNoteMemInfos(SubjectCompanyVO subjectCompanyVO) throws Exception {
		SubjectCompanyVO compVO = subjectMapper.getActivityNoteMemInfos(subjectCompanyVO);
		return compVO;
	}

	@Override
	public List<SubjectCompanyVO> listCompanyCcm(SubjectCompanyVO subjectCompanyVO) throws Exception {
		List<SubjectCompanyVO> listCompanyCcm = null;

		if(subjectCompanyVO.getSearchStatusType().equals("STU")){
			listCompanyCcm =  subjectMapper.listInChargeCompanyStudyStateCcm(subjectCompanyVO);
		} else {
			listCompanyCcm =  subjectMapper.listInChargeCompanyStateCcm(subjectCompanyVO);
		}

		return listCompanyCcm;
	}

	@Override
	public String getMinSubjectClass(SubjectVO subjectVO) throws Exception {
		return subjectMapper.getMinSubjectClass(subjectVO);
	}

  	@Override
	public List<SubjectVO> listOjtClassName(SubjectVO subjectVO) throws Exception {
		List<SubjectVO> data = subjectMapper.listOjtClassName(subjectVO);
		return data;
	}

  	@Override
	public List<SubjectVO> listOjtClassStdName(SubjectVO subjectVO) throws Exception {
		List<SubjectVO> data = subjectMapper.listOjtClassStdName(subjectVO);
		return data;
	}


}
