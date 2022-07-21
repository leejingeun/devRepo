package kr.co.sitglobal.oklms.lu.teamproject.service.impl;
 
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.util.AtchFileUtil;
import kr.co.sitglobal.oklms.lu.activity.vo.MemberVO;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningSchVO;
import kr.co.sitglobal.oklms.lu.teamproject.service.TeamprojectService;
import kr.co.sitglobal.oklms.lu.teamproject.vo.TeamprojectGroupVO;
import kr.co.sitglobal.oklms.lu.teamproject.vo.TeamprojectSubmitVO;
import kr.co.sitglobal.oklms.lu.teamproject.vo.TeamprojectVO;

@Transactional(rollbackFor=Exception.class)
@Service("TeamprojectService")
public class TeamprojectServiceImpl extends EgovAbstractServiceImpl implements TeamprojectService{
	
	@Resource(name = "TeamprojectMapper")
    private TeamprojectMapper teamprojectMapper;
	
	/** ID Generation */
    @Resource(name="teamprojectIdGnrService")
    private EgovIdGnrService teamprojectIdGnrService;
    
	/** ID Generation */
    @Resource(name="teamprojectSubmitIdGnrService")
    private EgovIdGnrService teamprojectSubmitIdGnrService;
    

	@Resource(name = "atchFileService")
	private AtchFileService atchFileService;

	@Resource(name = "atchFileUtil")
	private AtchFileUtil atchFileUtil;
	
	@Override
	public List<OnlineTraningSchVO> listLmsSubjWeek(CurrProcVO currProcVO)	throws Exception {
		// TODO Auto-generated method stub
		List<OnlineTraningSchVO> data= teamprojectMapper.listLmsSubjWeek(currProcVO);
		return data;
	}

	@Override
	public CurrProcVO getCurrproc(CurrProcVO currProcVO) throws Exception {

		CurrProcVO data = teamprojectMapper.getCurrproc(currProcVO);
		return data;
	}
	
	@Override
	public List<MemberVO> listStudents(CurrProcVO currProcVO) throws Exception {
		// TODO Auto-generated method stub
		List<MemberVO> data = teamprojectMapper.listStudents(currProcVO);
		return data;
	}
	
	@Override
	public List<TeamprojectVO> listTeamproject(TeamprojectVO teamprojectVO) throws Exception {
		// TODO Auto-generated method stub
		List<TeamprojectVO> data = teamprojectMapper.listTeamproject(teamprojectVO);
		return data;
	}
	
	@Override
	public TeamprojectVO getTeamproject(TeamprojectVO teamprojectVO) throws Exception {
		// TODO Auto-generated method stub
		return teamprojectMapper.getTeamproject(teamprojectVO);
	}

	@Override
	public int insertTeamproject(TeamprojectVO teamprojectVO,final MultipartHttpServletRequest multiRequest) throws Exception {
		// TODO Auto-generated method stub
		String pkTeamprojectId = teamprojectIdGnrService.getNextStringId();
		//첨부파일 저장	 		
		final List< MultipartFile > fileObj = multiRequest.getFiles("file-input");
		String storePathString ="Globals.fileStorePath";
		String atchFileId = atchFileService.saveAtchFile( fileObj, "RET", "", storePathString ,"teamproject");
		teamprojectVO.setTeamprojectId(pkTeamprojectId);
		teamprojectVO.setAtchFileId(atchFileId);
		int data = 0; 
		
		
		data=teamprojectMapper.insertTeamproject(teamprojectVO);
		
		// group 정보 저장.
		TeamprojectGroupVO teamprojectGroupVO = new TeamprojectGroupVO();
		TeamprojectSubmitVO teamprojectSubmitVO = new TeamprojectSubmitVO();
		
		// 기본정보 셋팅
		teamprojectGroupVO.setTeamprojectId(teamprojectVO.getTeamprojectId());
		teamprojectGroupVO.setSessionMemSeq(teamprojectVO.getSessionMemSeq());
		teamprojectSubmitVO.setTeamprojectId(teamprojectGroupVO.getTeamprojectId());
		teamprojectSubmitVO.setSessionMemSeq(teamprojectVO.getSessionMemSeq());
		
		String teamGroup = "";
		String groupNum = "";
		for(int i=1; i <= teamprojectVO.getTeamCnt(); i++ ){
			groupNum = EgovStringUtil.fillZeroStr(i, 2);
			teamGroup = StringUtils.defaultIfBlank(multiRequest.getParameter("teamGroup_"+groupNum), "");

			if(teamGroup.length() > 0){
				teamprojectGroupVO.setGroupId(i);
				teamprojectSubmitVO.setGroupId(teamprojectGroupVO.getGroupId());
				
				data = teamprojectMapper.insertTeamprojectGroup(teamprojectGroupVO);
				
				// 그룹별 학생정보 저장.
				String[] stds = multiRequest.getParameterValues("teamGroup_"+groupNum+"_memId");
				String leaderMemId = StringUtils.defaultIfBlank(multiRequest.getParameter("teamGroup_"+groupNum+"_leaderYn"), "");
				
				// 그룹에 학생정보가 존재할 경우에만, 학생정보 저장.
				if(stds != null && stds.length > 0){
					for(int j=0; j < stds.length; j++){
						teamprojectSubmitVO.setMemId(stds[j]);
						if(leaderMemId.length() > 0 && leaderMemId.equals(stds[j])){
							teamprojectSubmitVO.setLeaderYn("Y");
						}else{
							teamprojectSubmitVO.setLeaderYn("N");
						}
						
						teamprojectSubmitVO.setTeamprojectSubmitId( teamprojectSubmitIdGnrService.getNextStringId() );
						
						data = teamprojectMapper.insertTeamprojectSubmit(teamprojectSubmitVO);
					}// 그룹별 학생정보 저장 끝
				}
			}
		}// 그룹정보 저장 끝
		
		return data;
	}

	@Override
	public int updateTeamproject(TeamprojectVO teamprojectVO,final MultipartHttpServletRequest multiRequest) throws Exception {
		// TODO Auto-generated method stub
		//첨부파일 저장	 		
		final List< MultipartFile > fileObj = multiRequest.getFiles("file-input");
		String storePathString ="Globals.fileStorePath";
		String atchFileId = "";
		if(fileObj!=null && fileObj.size()>0){			
			atchFileId = atchFileService.saveAtchFile( fileObj, "RET", "", storePathString  ,"teamproject");		
			teamprojectVO.setAtchFileId(atchFileId);	
		}		
		if(teamprojectVO.getTeamLeaderAutoYn() == null || "".equals(teamprojectVO.getTeamLeaderAutoYn())){
			teamprojectVO.setTeamLeaderAutoYn("N");
		}
		
		int data = teamprojectMapper.updateTeamproject(teamprojectVO);
		
		String isTeamRenew = StringUtils.defaultIfBlank(multiRequest.getParameter("isTeamRenew"), "");
		String isChangeLeader = StringUtils.defaultIfBlank(multiRequest.getParameter("isChangeLeader"), "");
		
		
		TeamprojectGroupVO teamprojectGroupVO = new TeamprojectGroupVO();
		TeamprojectSubmitVO teamprojectSubmitVO = new TeamprojectSubmitVO();
		
		// 기본정보 셋팅
		teamprojectGroupVO.setTeamprojectId(teamprojectVO.getTeamprojectId());
		teamprojectGroupVO.setSessionMemSeq(teamprojectVO.getSessionMemSeq());
		teamprojectSubmitVO.setTeamprojectId(teamprojectGroupVO.getTeamprojectId());
		teamprojectSubmitVO.setSessionMemSeq(teamprojectVO.getSessionMemSeq());
		teamprojectGroupVO.setGroupId(teamprojectVO.getTeamCnt());

		if("Y".equals(isTeamRenew)){
			// 팀정보를 수정했다면..
			
			teamprojectVO.setDeleteYn("N");
			int useMaxGroupCnt = teamprojectMapper.getMaxTeamprojectGroupId(teamprojectVO).getGroupId();
			
			teamprojectVO.setDeleteYn("");
			int deletedMaxGroupCnt = teamprojectMapper.getMaxTeamprojectGroupId(teamprojectVO).getGroupId();
			

			// group 정보 저장.
			if(teamprojectVO.getTeamCnt() < useMaxGroupCnt){
				// 기존에 저장되어있던 팀수가 현재보다 많았다면, 삭제 플래그 처리.
				teamprojectMapper.deleteTeamprojectGroup(teamprojectGroupVO);
				
			}else if(teamprojectVO.getTeamCnt() > useMaxGroupCnt){
				
				// 기존 삭제플래그 처리된 녀석들이 현재 사용해야 할 팀보다 많거나 같을경우. 사용으로 플래그 변경.
				teamprojectMapper.restoreTeamprojectGroup(teamprojectGroupVO);
				
				if(teamprojectVO.getTeamCnt() > deletedMaxGroupCnt){
					// 기생성된 그룹정보 모자라면, 신규 추가 생성.
					for(int i=deletedMaxGroupCnt; i <= teamprojectVO.getTeamCnt(); i++ ){

						teamprojectGroupVO.setGroupId(i);
						data = teamprojectMapper.insertTeamprojectGroup(teamprojectGroupVO);
					}
				}
				
			}
			

			// 학생정보 초기화(전체 삭제처리).
			teamprojectMapper.deleteAllTeamprojectSubmit(teamprojectVO);
			
			
			// 그룹별 학생정보 신규 저장.
			String teamGroup = "";
			String groupNum = "";
			for(int i=1; i <= teamprojectVO.getTeamCnt(); i++ ){
				groupNum = EgovStringUtil.fillZeroStr(i, 2);
				teamGroup = StringUtils.defaultIfBlank(multiRequest.getParameter("teamGroup_"+groupNum), "");

				if(teamGroup.length() > 0){
					teamprojectSubmitVO.setGroupId(i);
					
					
					String[] stds = multiRequest.getParameterValues("teamGroup_"+groupNum+"_memId");
					String leaderMemId = StringUtils.defaultIfBlank(multiRequest.getParameter("teamGroup_"+groupNum+"_leaderYn"), "");
					
					// 그룹에 학생정보가 존재할 경우에만, 학생정보 저장.
					if(stds != null && stds.length > 0){
						for(int j=0; j < stds.length; j++){
							teamprojectSubmitVO.setMemId(stds[j]);
							if(leaderMemId.length() > 0 && leaderMemId.equals(stds[j])){
								teamprojectSubmitVO.setLeaderYn("Y");
							}else{
								teamprojectSubmitVO.setLeaderYn("N");
							}
							
							teamprojectSubmitVO.setTeamprojectSubmitId( teamprojectSubmitIdGnrService.getNextStringId() );
							
							data = teamprojectMapper.insertTeamprojectSubmit(teamprojectSubmitVO);
						}// 그룹별 학생정보 저장 끝
					}
				}
			}// 그룹정보 저장 끝

		}else if("Y".equals(isChangeLeader)){
			// 팀정보는 그대로 두고, 팀장정보만 수정했다면..
			
			// 전체 팀장정보 초기화.
			teamprojectMapper.clearAllTeamprojectSubmitLeader(teamprojectVO);

			String groupNum = "";
			TeamprojectSubmitVO target;
			for(int i=1; i <= teamprojectVO.getTeamCnt(); i++ ){
				groupNum = EgovStringUtil.fillZeroStr(i, 2);

					
				String leaderMemId = StringUtils.defaultIfBlank(multiRequest.getParameter("teamGroup_"+groupNum+"_leaderYn"), "");
					
				if(leaderMemId.length() > 0 ){
					teamprojectSubmitVO.setGroupId(i);
					teamprojectSubmitVO.setMemId(leaderMemId);
					
					// 새로운 팀장의 디비값 가져오기.
					target = teamprojectMapper.getTeamprojectSubmit(teamprojectSubmitVO);
				
					// 그룹별 팀장만 업데이트.
					teamprojectSubmitVO.setLeaderYn("Y");
					teamprojectSubmitVO.setTeamprojectSubmitId(target.getTeamprojectSubmitId());
					
					// 팀장정보 저장.
					teamprojectMapper.updateTeamprojectSubmit(teamprojectSubmitVO);
					teamprojectSubmitVO.setTeamprojectSubmitId("");
				} // end if
			}// end for
		}
		
		return data;
	}

	@Override
	public int deleteTeamproject(TeamprojectVO teamprojectVO) throws Exception {
		// TODO Auto-generated method stub
		int data = teamprojectMapper.deleteTeamproject(teamprojectVO);
		return data;
	}

	@Override
	public List<TeamprojectSubmitVO> listTeamprojectSubmit(TeamprojectSubmitVO teamprojectSubmitVO) throws Exception {
		// TODO Auto-generated method stub
		List<TeamprojectSubmitVO> data = teamprojectMapper.listTeamprojectSubmit(teamprojectSubmitVO);
		return data;
	}
	
	@Override
	public TeamprojectSubmitVO getTeamprojectSubmit(TeamprojectSubmitVO teamprojectSubmitVO) throws Exception {
		// TODO Auto-generated method stub
		return teamprojectMapper.getTeamprojectSubmit(teamprojectSubmitVO);
	}

	@Override
	public int insertTeamprojectSubmit(TeamprojectSubmitVO teamprojectVO) throws Exception {
		// TODO Auto-generated method stub
		String pkTeamprojectSubmitId = teamprojectSubmitIdGnrService.getNextStringId();
		teamprojectVO.setTeamprojectId(pkTeamprojectSubmitId);
		int data = teamprojectMapper.insertTeamprojectSubmit(teamprojectVO);
		return data;
	}

	@Override
	public int updateTeamprojectSubmit(TeamprojectSubmitVO teamprojectVO) throws Exception {
		// TODO Auto-generated method stub
		int data = teamprojectMapper.updateTeamprojectSubmit(teamprojectVO);
		return data;
	}
	
	@Override
	public int updateTeamprojectSubmitArr(TeamprojectSubmitVO teamprojectSubmitVO, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
		int data = 0;
		TeamprojectSubmitVO temp = null;
		for(int a=0 ;teamprojectSubmitVO.getArrTeamprojectSubmitId().length>a ; a++){
			temp = new TeamprojectSubmitVO();
			temp.setTeamprojectSubmitId(teamprojectSubmitVO.getArrTeamprojectSubmitId()[a]);
			temp.setEvalScore( Integer.parseInt( StringUtils.defaultIfBlank(request.getParameter("arrEvalScore_"+teamprojectSubmitVO.getArrTeamprojectSubmitId()[a]), "0") ) );
			temp.setSessionMemSeq(teamprojectSubmitVO.getSessionMemSeq());
			data=teamprojectMapper.updateTeamprojectSubmit(temp);	
		}	
		
		return data;
	}
	
	
	@Override
	public int deleteTeamprojectSubmitAttechFile(TeamprojectSubmitVO teamprojectSubmitVO) throws Exception {
		// TODO Auto-generated method stub
		TeamprojectVO teamprojectVO = teamprojectMapper.getTeamproject(teamprojectSubmitVO);
		
		// 팀장만 제출이면, 전체팀원 제출 취소 처리.
		if("T".equals(teamprojectVO.getSubmitType())){
			teamprojectSubmitVO.setTeamprojectSubmitId("");
		}
		
		int data = teamprojectMapper.deleteTeamprojectSubmitAttechFile(teamprojectSubmitVO);
		return data;
	}

	@Override
	public List<TeamprojectVO> listTeamprojectStd(TeamprojectVO teamprojectVO) throws Exception {
		// TODO Auto-generated method stub
		return teamprojectMapper.listTeamprojectStd(teamprojectVO);
	}

	@Override
	public int insertTeamprojectStd(TeamprojectSubmitVO teamprojectSubmitVO, MultipartHttpServletRequest multiRequest) throws Exception {
		//첨부파일 저장	 		
		final List< MultipartFile > fileObj = multiRequest.getFiles("file-input");
		String storePathString ="Globals.fileStorePath";
		String atchFileId = "";
		if(fileObj!=null && fileObj.size()>0){			
			atchFileId = atchFileService.saveAtchFile( fileObj, "RET", "", storePathString  ,"teamproject");		
			teamprojectSubmitVO.setAtchFileId(atchFileId);	
		}
		teamprojectSubmitVO.setSubmitDate("Y");
		int data = teamprojectMapper.updateTeamprojectSubmit(teamprojectSubmitVO);
		
		TeamprojectVO teamprojectVO = teamprojectMapper.getTeamproject(teamprojectSubmitVO);
		
		// 팀장만 제출이면, 전체팀원 제출완료 처리.
		if("T".equals(teamprojectVO.getSubmitType())){
			data = teamprojectMapper.updateAllTeamprojectSubmit(teamprojectSubmitVO);
		}
		
		return data;
	}

 
}
