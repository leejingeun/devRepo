package kr.co.sitglobal.oklms.lu.activitydesc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.lu.activitydesc.service.ActivityNoteDescService;
import kr.co.sitglobal.oklms.lu.activitydesc.vo.ActivityNoteVO;
import egovframework.com.cmm.LoginVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Transactional(rollbackFor=Exception.class)
@Service("activityNoteDescService")
public class ActivityNoteDescServiceImpl extends EgovAbstractServiceImpl implements ActivityNoteDescService{

	@Resource(name="activityNoteDescMapper")
	private ActivityNoteDescMapper activityNoteDescMapper;

    @Resource(name="activityNoteDescIdGnrService")
    private EgovIdGnrService activityNoteDescIdGnrService;

    @Resource(name="activityNoteDescDetailIdGnrService")
    private EgovIdGnrService activityNoteDescDetailIdGnrService;


	@Override
	public List<ActivityNoteVO> listActivityDesc(ActivityNoteVO activityVO)
			throws Exception {
		List<ActivityNoteVO> resultList = activityNoteDescMapper.listActivityDesc(activityVO);
		return resultList;
	}

	@Override
	public int insertActivityDesc(ActivityNoteVO activityVO) throws Exception {
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(activityVO); // session의 정보를 VO에 추가.

		int sqlResultInt = 0;
		String activityId = "";
 
		String activityDateArr[] = activityVO.getActivityDateArr();
		String memNameArr[] = activityVO.getMemNameArr();
		String activityContentArr[] = activityVO.getActivityContentArr();
		String leadTimeArr[] = activityVO.getLeadTimeArr(); 
		String activityDetailIdArr[] = activityVO.getActivityDetailIdArr();

		if( "COT".equals(activityVO.getActivityType())) {
			activityVO.setBigoCot(activityVO.getBigo());
		}else if("HRD".equals(activityVO.getActivityType())){
			activityVO.setBigoHrd(activityVO.getBigo());
		} 

		if(activityVO.getActivityId() != null && !activityVO.getActivityId().equals("")){
			sqlResultInt=	activityNoteDescMapper.updateActivityDesc(activityVO);
		}else{
		    activityId  = activityNoteDescIdGnrService.getNextStringId();
			activityVO.setActivityId(activityId);
			sqlResultInt = activityNoteDescMapper.insertActivityDesc(activityVO);
		}

		for(int i =0; i < memNameArr.length; i++ ){

			activityVO.setActivityDate(activityDateArr[i]);
			activityVO.setMemName(memNameArr[i]);
			activityVO.setActivityContent(activityContentArr[i]);
			activityVO.setLeadTime(leadTimeArr[i]);
			if(activityDetailIdArr==null || activityDetailIdArr[i]==null||activityDetailIdArr[i].equals("")){
				String activityDetailId  = activityNoteDescDetailIdGnrService.getNextStringId();
				activityVO.setActivityDetailId(activityDetailId);
				sqlResultInt = activityNoteDescMapper.insertActivityDescDetail(activityVO);				
			}else{
				activityVO.setActivityDetailId(activityDetailIdArr[i]);
				sqlResultInt = activityNoteDescMapper.updateActivityDetailDesc(activityVO);				
			}
		}

		return sqlResultInt;
	}

	@Override
	public ActivityNoteVO goInsertActivityDesc(ActivityNoteVO activityVO)
			throws Exception {
		ActivityNoteVO data = activityNoteDescMapper.goInsertActivityDesc(activityVO);
		return data;
	}

	@Override
	public List<ActivityNoteVO> listActivityDescDetail(ActivityNoteVO activityVO)
			throws Exception {
		List<ActivityNoteVO> resultListCot = activityNoteDescMapper.listActivityDescDetail(activityVO);
		return resultListCot;
	}
 
	@Override
	public int updateActivityDescPrint(ActivityNoteVO activityVO)throws Exception {

		int sqlResultInt = 0;
		sqlResultInt = activityNoteDescMapper.updateActivityDescPrint(activityVO);

		return sqlResultInt;
	}

	@Override
	public int deleteActivityDesc(ActivityNoteVO activityVO) throws Exception {
		// TODO Auto-generated method stub

		int sqlResultInt = 0;
		sqlResultInt = activityNoteDescMapper.deleteActivityDesc(activityVO);

		return sqlResultInt;
	}



}
