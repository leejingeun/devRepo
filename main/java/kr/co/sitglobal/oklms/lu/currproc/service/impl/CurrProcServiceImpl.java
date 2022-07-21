/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 02. 20.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.lu.currproc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.la.company.vo.CompanyVO;
import kr.co.sitglobal.oklms.lu.currproc.service.CurrProcService;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

 /**
 * 프로토타입 게시판 CRUD 비지니스 로직을 구현하는 클레스.
 * @author 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
@Service("currProcService")
public class CurrProcServiceImpl extends EgovAbstractServiceImpl implements CurrProcService{

	/** ID Generation */
    /*@Resource(name="companyIdGnrService")
    private EgovIdGnrService companyIdGnrService;*/

    @Resource(name = "currProcMapper")
    private CurrProcMapper currProcMapper;

  	@Override
	public List<CurrProcVO> listSubjectSeach(CurrProcVO currProcVO) throws Exception {
		List<CurrProcVO> data = currProcMapper.listSubjectSeach(currProcVO);
		return data;
	}

  	@Override
	public List<CurrProcVO> listTrainSubjectSeach(CurrProcVO currProcVO) throws Exception {
		List<CurrProcVO> data = currProcMapper.listTrainSubjectSeach(currProcVO);
		return data;
	}

  	@Override
	public List<CurrProcVO> listCotMappingTrainSubjectDetail(CurrProcVO currProcVO) throws Exception {
		List<CurrProcVO> data = currProcMapper.listCotMappingTrainSubjectDetail(currProcVO);
		return data;
	}

  	@Override
	public List<CurrProcVO> listSubjectClassInfo(CurrProcVO currProcVO) throws Exception {
		List<CurrProcVO> data = currProcMapper.listSubjectClassInfo(currProcVO);
		return data;
	}

  	@Override
	public CurrProcVO getMySubjectInfo(CurrProcVO currProcVO) throws Exception {
  		CurrProcVO data = currProcMapper.getMySubjectInfo(currProcVO);
		return data;
	}

  	@Override
	public CurrProcVO getMyTrainSubjectInfo(CurrProcVO currProcVO) throws Exception {
  		CurrProcVO data = currProcMapper.getMyTrainSubjectInfo(currProcVO);
		return data;
	}

  	@Override
	public int getSubjectCnt(CurrProcVO currProcVO) throws Exception {
		return currProcMapper.getSubjectCnt(currProcVO);
	}


  	
}
