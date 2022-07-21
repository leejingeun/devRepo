/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2017. 05. 10.         First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.lu.qestnr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.lu.qestnr.service.QestnrService;
import kr.co.sitglobal.oklms.lu.qestnr.vo.QestnrVO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

 /**
 * 프로토타입 게시판 CRUD 비지니스 로직을 구현하는 클레스.
 * @author 이진근
 * @since 2017. 03. 02.
 */
@Transactional(rollbackFor=Exception.class)
@Service("qestnrService")
public class QestnrServiceImpl extends EgovAbstractServiceImpl implements QestnrService{

	/** ID Generation */
	//설문ID
    @Resource(name="qestnrIdGnrService")
    private EgovIdGnrService qestnrIdGnrService;

    //설문문항ID
    @Resource(name="qustnrQesitmIdGnrService")
    private EgovIdGnrService qustnrQesitmIdGnrService;

    @Resource(name = "qestnrMapper")
    private QestnrMapper qestnrMapper;

	@Override
	public List<QestnrVO> listQestnrInfo(QestnrVO qestnrVO) throws Exception {
		List<QestnrVO> data = qestnrMapper.listQestnrInfo(qestnrVO);
		return data;
	}

	@Override
	public List<QestnrVO> listQestnrStdInfo(QestnrVO qestnrVO) throws Exception {
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(qestnrVO); // session의 정보를 VO에 추가.

		List<QestnrVO> data = qestnrMapper.listQestnrStdInfo(qestnrVO);
		return data;
	}

	@Override
	public List<QestnrVO> listQestnrEtcAnswerCn(QestnrVO qestnrVO) throws Exception {
		List<QestnrVO> data = qestnrMapper.listQestnrEtcAnswerCn(qestnrVO);
		return data;
	}

	@Override
	public QestnrVO getQestnrInfo(QestnrVO qestnrVO) throws Exception {
		QestnrVO data = qestnrMapper.getQestnrInfo(qestnrVO);
		return data;
	}

	@Override
	public QestnrVO getQestnrItem(QestnrVO qestnrVO) throws Exception {
		QestnrVO data = qestnrMapper.getQestnrItem(qestnrVO);
		return data;
	}

	@Override
	public int insertQestnrInfo(QestnrVO qestnrVO) throws Exception {
		int sqlResultInt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(qestnrVO); // session의 정보를 VO에 추가.

		String pkStr = qestnrIdGnrService.getNextStringId();
		System.out.println("##### pkStr="+pkStr);
		qestnrVO.setQestnrId(pkStr);

		sqlResultInt = qestnrMapper.insertQestnrInfo(qestnrVO);

		return sqlResultInt;
	}

	@Override
	public int insertQestnrItem(QestnrVO qestnrVO) throws Exception {
		int sqlResultInt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(qestnrVO); // session의 정보를 VO에 추가.

		String pkStr = qustnrQesitmIdGnrService.getNextStringId();
		System.out.println("##### pkStr="+pkStr);
		qestnrVO.setQustnrQesitmId(pkStr);

		sqlResultInt = qestnrMapper.insertQestnrItem(qestnrVO);

		return sqlResultInt;
	}

	@Override
	public int insertQestnrAnswerResult(QestnrVO qestnrVO) throws Exception {
		int sqlResultInt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(qestnrVO); // session의 정보를 VO에 추가.

		sqlResultInt = qestnrMapper.insertQestnrAnswerResult(qestnrVO);

		return sqlResultInt;
	}

	@Override
	public int updateQestnrInfo(QestnrVO qestnrVO) throws Exception {
		int sqlResultInt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(qestnrVO); // session의 정보를 VO에 추가.

		sqlResultInt = qestnrMapper.updateQestnrInfo(qestnrVO);

		return sqlResultInt;
	}

	@Override
	public int updateQestnrItem(QestnrVO qestnrVO) throws Exception {
		int sqlResultInt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(qestnrVO); // session의 정보를 VO에 추가.

		sqlResultInt = qestnrMapper.updateQestnrItem(qestnrVO);

		return sqlResultInt;
	}

	@Override
	public int deleteQestnrInfo(QestnrVO qestnrVO) throws Exception {
		int sqlResultInt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(qestnrVO); // session의 정보를 VO에 추가.

		sqlResultInt = qestnrMapper.deleteQestnrInfo(qestnrVO);

		return sqlResultInt;
	}

	@Override
	public int deleteQestnrItem(QestnrVO qestnrVO) throws Exception {
		int sqlResultInt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(qestnrVO); // session의 정보를 VO에 추가.

		sqlResultInt = qestnrMapper.deleteQestnrItem(qestnrVO);

		return sqlResultInt;
	}

}
