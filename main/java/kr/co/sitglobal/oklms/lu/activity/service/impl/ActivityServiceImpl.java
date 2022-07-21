package kr.co.sitglobal.oklms.lu.activity.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.util.AtchFileUtil;
import kr.co.sitglobal.oklms.lu.activity.service.ActivityService;
import kr.co.sitglobal.oklms.lu.activity.vo.ActivityVO; 
import kr.co.sitglobal.oklms.lu.activity.vo.MemberVO;
import kr.co.sitglobal.oklms.lu.activity.vo.SubjweekStdVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Transactional(rollbackFor=Exception.class)
@Service("ActivityService")
public class ActivityServiceImpl extends EgovAbstractServiceImpl implements ActivityService {

	@Resource(name = "ActivityMapper")
    private ActivityMapper activityMapper;

	@Resource(name = "atchFileService")
	private AtchFileService atchFileService;

	@Resource(name = "atchFileUtil")
	private AtchFileUtil atchFileUtil;
	
	/** ID Generation */
    @Resource(name="activityNoteIdGnrService")
    private EgovIdGnrService activityNoteIdGnrService;
    
	@Override
	public List<ActivityVO> listActivity(ActivityVO activityVO)
			throws Exception {
		// TODO Auto-generated method stub
		return activityMapper.listActivity(activityVO);
	}

	@Override
	public ActivityVO getActivity(ActivityVO activityVO) throws Exception {
		// TODO Auto-generated method stub
		return activityMapper.getActivity(activityVO);
	}

	@Override
	public int insertActivity(ActivityVO activityVO,final MultipartHttpServletRequest multiRequest) throws Exception {
		// TODO Auto-generated method stub

		int data =0;
		if(activityVO.getActivityNoteId()==null || activityVO.getActivityNoteId().equals("")){

	  		String pkActivityId = activityNoteIdGnrService.getNextStringId();
			//첨부파일 저장	 		
			final List< MultipartFile > fileObj = multiRequest.getFiles("file-input");
			String storePathString ="Globals.fileStorePath";
			String atchFileId = atchFileService.saveAtchFile( fileObj, "ANI", "", storePathString ,"activity");
			activityVO.setActivityNoteId(pkActivityId);
			activityVO.setAtchFileId(atchFileId);
			data = activityMapper.insertActivity(activityVO);			
			
		}else{
			//첨부파일 저장	 		
			final List< MultipartFile > fileObj = multiRequest.getFiles("file-input");
			String storePathString ="Globals.fileStorePath";
			String atchFileId = atchFileService.saveAtchFile( fileObj, "ANI", "", storePathString ,"activity");
			activityVO.setAtchFileId(atchFileId);			
			data =activityMapper.updateActivity(activityVO);
			
		}	
		
		return data;
	}

	@Override
	public int updateActivity(ActivityVO activityVO,final MultipartHttpServletRequest multiRequest) throws Exception {
		// TODO Auto-generated method stub
		//첨부파일 저장	 		
		final List< MultipartFile > fileObj = multiRequest.getFiles("file-input");
		String storePathString ="Globals.fileStorePath";
		String atchFileId = atchFileService.saveAtchFile( fileObj, "ANI", "", storePathString ,"activity");
		activityVO.setAtchFileId(atchFileId);
		
		int data =activityMapper.updateActivity(activityVO);
		
		return data;
	}

	@Override
	public int deleteActivity(ActivityVO activityVO) throws Exception {
		// TODO Auto-generated method stub
		return activityMapper.deleteActivity(activityVO);
	}

	@Override
	public SubjweekStdVO getSubjWeek(SubjweekStdVO subjweekStdVO)
			throws Exception {
		// TODO Auto-generated method stub
		return activityMapper.getSubjWeek(subjweekStdVO);
	}

	@Override
	public List<MemberVO> listActivityMember(MemberVO memberVO)
			throws Exception {
		// TODO Auto-generated method stub
		
		List<MemberVO> data = activityMapper.listActivityMember(memberVO);
		
		for(int a=0 ;data.size()>a ;a++){
			ActivityVO activityVO = new ActivityVO();
			MemberVO mv=data.get(a);
			activityVO.setSubjectCode(memberVO.getSubjectCode());
			activityVO.setWeekCnt(memberVO.getWeekCnt());
			activityVO.setYyyy(memberVO.getYyyy());
			activityVO.setClassId(memberVO.getClassId());
			activityVO.setMemId(mv.getMemId());			
			List<ActivityVO> resultMember = activityMapper.getActivityMember(activityVO);
			mv.setArrActivityVO(resultMember);
		}		
		
		return data;
	}

	@Override
	public List<MemberVO> listActivityCompany(MemberVO memberVO)
			throws Exception {
		// TODO Auto-generated method stub
		return activityMapper.listActivityCompany(memberVO);
	}

	@Override
	public List<ActivityVO> getActivityMember(ActivityVO activityVO) throws Exception {
		// TODO Auto-generated method stub
		return activityMapper.getActivityMember(activityVO);
	}

	@Override
	public List<SubjweekStdVO> listActivityHrd(SubjweekStdVO subjweekStdVO)
			throws Exception {
		// TODO Auto-generated method stub
		return activityMapper.listActivityHrd(subjweekStdVO);
	}

	@Override
	public List<SubjweekStdVO> selectActivityHrd(SubjweekStdVO subjweekStdVO)
			throws Exception {
		// TODO Auto-generated method stub
		return activityMapper.selectActivityHrd(subjweekStdVO);
	}

	@Override
	public List<SubjweekStdVO> listWeekActivityStd(SubjweekStdVO subjweekStdVO)
			throws Exception {
		// TODO Auto-generated method stub
		return activityMapper.listWeekActivityStd(subjweekStdVO);
	}

	@Override
	public int updateActivitySubmit(ActivityVO activityVO ) throws Exception {
		// TODO Auto-generated method stub
		return activityMapper.updateActivitySubmit(activityVO);
	}

	@Override
	public List<ActivityVO> listActivityNotMake(ActivityVO activityVO)
			throws Exception {
		// TODO Auto-generated method stub
		return activityMapper.listActivityNotMake(activityVO);
	}

	@Override
	public List<SubjweekStdVO> listWeekActivityMakeStd(SubjweekStdVO activityVO)	throws Exception {
		// TODO Auto-generated method stub
		return activityMapper.listWeekActivityMakeStd(activityVO);
	}
 

}
