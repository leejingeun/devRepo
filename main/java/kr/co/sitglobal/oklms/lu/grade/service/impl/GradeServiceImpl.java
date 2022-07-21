package kr.co.sitglobal.oklms.lu.grade.service.impl;

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
import kr.co.sitglobal.oklms.lu.grade.service.GradeService;
import kr.co.sitglobal.oklms.lu.grade.vo.GradeVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Transactional(rollbackFor=Exception.class)
@Service("GradeService")
public class GradeServiceImpl extends EgovAbstractServiceImpl implements GradeService {

	@Resource(name = "GradeMapper")
    private GradeMapper gradeMapper;
	
	/** ID Generation */
    @Resource(name="gradeSubmitIdGnrService")
    private EgovIdGnrService gradeSubmitIdGnrService;
	

	@Override
	public List<GradeVO> listGradeSendStatusList(GradeVO gradeVO) throws Exception {
		return gradeMapper.listGradeSendStatusList(gradeVO);
	}
	
	@Override
	public List<GradeVO> listGradeSendList(GradeVO gradeVO) throws Exception {
		return gradeMapper.listGradeSendList(gradeVO);
	}
	
	@Override
	public int insertGradeCdpSend(GradeVO gradeVO) throws Exception {
		
		int iResult = 0;
		String sessionMemSeq =  gradeVO.getSessionMemSeq();
		String sessionIp = gradeVO.getSessionIp();
		
		String [] idsArr = gradeVO.getSubmitIds().split(",");
		
		egovLogger.debug("==============  gradeVO.getSubmitIds() :  "+gradeVO.getSubmitIds());
		
		egovLogger.debug("==============  gidsArr.length :  "+idsArr.length);
		
		for( int i=0; i < idsArr.length; i++ ){
			GradeVO vo = new GradeVO();
			
			String [] submitArr = idsArr[i].split("\\|");
			
			egovLogger.debug("==============  submitArr.length :  "+submitArr.length);
			
			egovLogger.debug("==============  submitArr[0] :  "+submitArr[0]);
			egovLogger.debug("==============  submitArr[1]  :  "+submitArr[1]);
			egovLogger.debug("==============  submitArr[2] :  "+submitArr[2]);
			egovLogger.debug("==============  submitArr[3]  :  "+submitArr[3]);
			
			vo.setSessionMemSeq(sessionMemSeq);
			vo.setSessionIp(sessionIp);
			
			vo.setSubmitId(gradeSubmitIdGnrService.getNextStringId());
			vo.setCompanyId(submitArr[0]);
			vo.setYyyy(submitArr[1]);
			vo.setTerm(submitArr[2]);
			vo.setDeptNo(submitArr[3]);
			
			iResult += gradeMapper.insertGradeCdpSend(vo);
		}
		return iResult;
	}
	
	@Override
	public String getGradeGroupMemIds(GradeVO gradeVO) throws Exception {
		return gradeMapper.getGradeGroupMemIds(gradeVO);
	}
	
	@Override
	public GradeVO getGradeCcmConfirmInfo(GradeVO gradeVO) throws Exception {
		return gradeMapper.getGradeCcmConfirmInfo(gradeVO);
	}
	
	@Override
	public GradeVO getGradeCcmSubmitInfo(GradeVO gradeVO) throws Exception {
		return gradeMapper.getGradeCcmSubmitInfo(gradeVO);
	}
	
	@Override
	public List<GradeVO> listGradeConfirmList(GradeVO gradeVO) throws Exception {
		return gradeMapper.listGradeConfirmList(gradeVO);
	}
	
	@Override
	public int updateGradeCcmConfirmY(GradeVO gradeVO) throws Exception {
		int iResult = gradeMapper.updateGradeCcmConfirmY(gradeVO);
		return iResult;
	}
	
	 
}
