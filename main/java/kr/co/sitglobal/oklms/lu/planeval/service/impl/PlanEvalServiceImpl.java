/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2017. 04. 17.         First Draft
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.lu.planeval.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.lu.planeval.service.PlanEvalService;
import kr.co.sitglobal.oklms.lu.planeval.vo.PlanEvalVO;

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
@Service("planEvalService")
public class PlanEvalServiceImpl extends EgovAbstractServiceImpl implements PlanEvalService{

	/** ID Generation */
    @Resource(name="planEvIdGnrService")
    private EgovIdGnrService planEvIdGnrService;

    @Resource(name="lectureNcsElemIdGnrService")
    private EgovIdGnrService lectureNcsElemIdGnrService;

    @Resource(name="manageItemSetIdGnrService")
    private EgovIdGnrService manageItemSetIdGnrService;

    @Resource(name="stdScoreIdGnrService")
    private EgovIdGnrService stdScoreIdGnrService;

    @Resource(name = "planEvalMapper")
    private PlanEvalMapper planEvalMapper;

    @Override
	public List<PlanEvalVO> listNcsEvalPlanInfo(PlanEvalVO planEvalVO) throws Exception {
    	//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		List<PlanEvalVO> data = planEvalMapper.listNcsEvalPlanInfo(planEvalVO);
		return data;
	}

    @Override
	public List<PlanEvalVO> listNcsEvalPlanElemInfo(PlanEvalVO planEvalVO) throws Exception {
    	//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		List<PlanEvalVO> data = planEvalMapper.listNcsEvalPlanElemInfo(planEvalVO);
		return data;
	}

    @Override
	public List<PlanEvalVO> listFistEvalPlanLessonMember(PlanEvalVO planEvalVO) throws Exception {
    	//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		List<PlanEvalVO> data = planEvalMapper.listFistEvalPlanLessonMember(planEvalVO);
		return data;
	}

    @Override
	public List<PlanEvalVO> listNcsEvalPlanFirstRowNumber(PlanEvalVO planEvalVO) throws Exception {
    	//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		List<PlanEvalVO> data = planEvalMapper.listNcsEvalPlanFirstRowNumber(planEvalVO);
		return data;
	}

    @Override
	public List<PlanEvalVO> listNcsEvalPlanFirstStdScoreRowNumber(PlanEvalVO planEvalVO) throws Exception {
    	//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		List<PlanEvalVO> data = planEvalMapper.listNcsEvalPlanFirstStdScoreRowNumber(planEvalVO);
		return data;
	}

    @Override
	public List<PlanEvalVO> listNcsEvalPlanFirstRowspan(PlanEvalVO planEvalVO) throws Exception {
    	//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		List<PlanEvalVO> data = planEvalMapper.listNcsEvalPlanFirstRowspan(planEvalVO);
		return data;
	}

    @Override
	public List<PlanEvalVO> listNcsEvalPlanFirstInfo(PlanEvalVO planEvalVO) throws Exception {
    	//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		List<PlanEvalVO> data = planEvalMapper.listNcsEvalPlanFirstInfo(planEvalVO);
		return data;
	}

    @Override
	public List<PlanEvalVO> listNcsEvalPlanFirstElemId(PlanEvalVO planEvalVO) throws Exception {
    	//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		List<PlanEvalVO> data = planEvalMapper.listNcsEvalPlanFirstElemId(planEvalVO);
		return data;
	}

    @Override
	public List<PlanEvalVO> listNcsEvalPlanFirstCtrlSet(PlanEvalVO planEvalVO) throws Exception {
    	//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		List<PlanEvalVO> data = planEvalMapper.listNcsEvalPlanFirstCtrlSet(planEvalVO);
		return data;
	}



    @Override
	public List<PlanEvalVO> listNcsEvalPlanFirstRowNumberPrt(PlanEvalVO planEvalVO) throws Exception {
    	//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		List<PlanEvalVO> data = planEvalMapper.listNcsEvalPlanFirstRowNumberPrt(planEvalVO);
		return data;
	}

    @Override
	public List<PlanEvalVO> listNcsEvalPlanFirstStdScoreRowNumberPrt(PlanEvalVO planEvalVO) throws Exception {
    	//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		List<PlanEvalVO> data = planEvalMapper.listNcsEvalPlanFirstStdScoreRowNumberPrt(planEvalVO);
		return data;
	}

    @Override
	public List<PlanEvalVO> listNcsEvalPlanFirstRowspanPrt(PlanEvalVO planEvalVO) throws Exception {
    	//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		List<PlanEvalVO> data = planEvalMapper.listNcsEvalPlanFirstRowspanPrt(planEvalVO);
		return data;
	}

    @Override
	public List<PlanEvalVO> listNcsEvalPlanFirstInfoPrt(PlanEvalVO planEvalVO) throws Exception {
    	//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		List<PlanEvalVO> data = planEvalMapper.listNcsEvalPlanFirstInfoPrt(planEvalVO);
		return data;
	}

    @Override
	public List<PlanEvalVO> listNcsEvalPlanFirstElemIdPrt(PlanEvalVO planEvalVO) throws Exception {
    	//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		List<PlanEvalVO> data = planEvalMapper.listNcsEvalPlanFirstElemIdPrt(planEvalVO);
		return data;
	}

    @Override
	public List<PlanEvalVO> listNcsEvalPlanFirstCtrlSetPrt(PlanEvalVO planEvalVO) throws Exception {
    	//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		List<PlanEvalVO> data = planEvalMapper.listNcsEvalPlanFirstCtrlSetPrt(planEvalVO);
		return data;
	}

    @Override
	public List<PlanEvalVO> listNcsEvalPlanSubjectRebort(PlanEvalVO planEvalVO) throws Exception {
    	//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		List<PlanEvalVO> data = planEvalMapper.listNcsEvalPlanSubjectRebort(planEvalVO);
		return data;
	}

    @Override
	public List<PlanEvalVO> listNcsEvalPlanMemberRebort(PlanEvalVO planEvalVO) throws Exception {
    	//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		List<PlanEvalVO> data = planEvalMapper.listNcsEvalPlanMemberRebort(planEvalVO);
		return data;
	}

    @Override
	public PlanEvalVO getEvDivCd(PlanEvalVO planEvalVO) throws Exception {
    	//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		PlanEvalVO data = planEvalMapper.getEvDivCd(planEvalVO);
		return data;
	}

    @Override
	public PlanEvalVO getLessionMember(PlanEvalVO planEvalVO) throws Exception {
    	//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		PlanEvalVO data = planEvalMapper.getLessionMember(planEvalVO);
		return data;
	}

	@Override
	public int insertNcsEvalPlanStd(PlanEvalVO planEvalVO) throws Exception {
		int sqlResultInt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		sqlResultInt = planEvalMapper.insertNcsEvalPlanStd(planEvalVO);

		return sqlResultInt;
	}

	@Override
	public int insertNcsEvalPlanLrn(PlanEvalVO planEvalVO) throws Exception {
		int sqlResultInt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		String planEvId = planEvIdGnrService.getNextStringId();
		System.out.println("##### planEvId="+planEvId);
		planEvalVO.setPlanEvId(planEvId);

		sqlResultInt = planEvalMapper.insertNcsEvalPlanLrn(planEvalVO);

		return sqlResultInt;
	}

	@Override
	public int insertNcsEvalPlanLctreElem(PlanEvalVO planEvalVO) throws Exception {
		int sqlResultInt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		String lectureNcsElemId = lectureNcsElemIdGnrService.getNextStringId();
		System.out.println("##### lectureNcsElemId="+lectureNcsElemId);
		planEvalVO.setLectureNcsElemId(lectureNcsElemId);

		sqlResultInt = planEvalMapper.insertNcsEvalPlanLctreElem(planEvalVO);

		return sqlResultInt;
	}

	@Override
	public int insertNcsEvalPlanLctreCtrlSet(PlanEvalVO planEvalVO) throws Exception {
		int sqlResultInt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		String manageItemSetId = manageItemSetIdGnrService.getNextStringId();
		System.out.println("##### manageItemSetId="+manageItemSetId);
		planEvalVO.setManageItemSetId(manageItemSetId);

		sqlResultInt = planEvalMapper.insertNcsEvalPlanLctreCtrlSet(planEvalVO);

		return sqlResultInt;
	}

	@Override
	public int insertFirstEvalStdScore(PlanEvalVO planEvalVO) throws Exception {
		int sqlResultInt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		String stdScoreId = stdScoreIdGnrService.getNextStringId();
		System.out.println("##### stdScoreId="+stdScoreId);
		planEvalVO.setStdScoreId(stdScoreId);

		sqlResultInt = planEvalMapper.insertFirstEvalStdScore(planEvalVO);

		return sqlResultInt;
	}

	@Override
	public int updateFirstEvalStdScore(PlanEvalVO planEvalVO) throws Exception {
		int sqlResultInt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		sqlResultInt = planEvalMapper.updateFirstEvalStdScore(planEvalVO);

		return sqlResultInt;
	}

	@Override
	public int updateNcsEvalPlanFirstAppYn(PlanEvalVO planEvalVO) throws Exception {
		int sqlResultInt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		sqlResultInt = planEvalMapper.updateNcsEvalPlanFirstAppYn(planEvalVO);

		return sqlResultInt;
	}

	@Override
	public int updateNcsEvalPlanApproval(PlanEvalVO planEvalVO) throws Exception {
		int sqlResultInt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		sqlResultInt = planEvalMapper.updateNcsEvalPlanApproval(planEvalVO);

		return sqlResultInt;
	}

	@Override
	public int updateNcsEvalPlanFirstAtchFile(PlanEvalVO planEvalVO) throws Exception {
		int sqlResultInt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		sqlResultInt = planEvalMapper.updateNcsEvalPlanFirstAtchFile(planEvalVO);

		return sqlResultInt;
	}

	@Override
	public int deleteNcsEvalPlanStd(PlanEvalVO planEvalVO) throws Exception {
		int sqlResultInt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		sqlResultInt = planEvalMapper.deleteNcsEvalPlanStd(planEvalVO);

		return sqlResultInt;
	}

	@Override
	public int deleteNcsEvalPlanLrn(PlanEvalVO planEvalVO) throws Exception {
		int sqlResultInt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		sqlResultInt = planEvalMapper.deleteNcsEvalPlanLrn(planEvalVO);

		return sqlResultInt;
	}

	@Override
	public int deleteNcsEvalPlanLctreElem(PlanEvalVO planEvalVO) throws Exception {
		int sqlResultInt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		sqlResultInt = planEvalMapper.deleteNcsEvalPlanLctreElem(planEvalVO);

		return sqlResultInt;
	}

	@Override
	public int deleteNcsEvalPlanLctreCtrlSet(PlanEvalVO planEvalVO) throws Exception {
		int sqlResultInt = 0;

		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		sqlResultInt = planEvalMapper.deleteNcsEvalPlanLctreCtrlSet(planEvalVO);

		return sqlResultInt;
	}

	@Override
	public int getNcsEvalPlanCnt(PlanEvalVO planEvalVO) throws Exception {
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(planEvalVO); // session의 정보를 VO에 추가.

		return planEvalMapper.getNcsEvalPlanCnt(planEvalVO);
	}

	@Override
	public int getNcsEvalPlanStdScoreCnt(PlanEvalVO planEvalVO) throws Exception {
		return planEvalMapper.getNcsEvalPlanStdScoreCnt(planEvalVO);
	}


}
