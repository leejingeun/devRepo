package kr.co.sitglobal.oklms.lu.interview.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.atchFile.vo.AtchFileVO;
import kr.co.sitglobal.oklms.commbiz.util.AtchFileUtil;
import kr.co.sitglobal.oklms.lu.interview.service.InterviewService;
import kr.co.sitglobal.oklms.lu.interview.service.impl.InterviewMapper;
import kr.co.sitglobal.oklms.lu.interview.vo.InterviewCompanyVO;
import kr.co.sitglobal.oklms.lu.interview.vo.InterviewMemberVO;
import kr.co.sitglobal.oklms.lu.interview.vo.InterviewVO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Transactional(rollbackFor=Exception.class)
@Service("InterviewService")
public class InterviewServiceImpl extends EgovAbstractServiceImpl implements InterviewService{
	
	@Resource(name = "InterviewMapper")
    private InterviewMapper interviewMapper;
	
	@Resource(name = "atchFileService")
	private AtchFileService atchFileService;

	@Resource(name = "atchFileUtil")
	private AtchFileUtil atchFileUtil;
	
	/** ID Generation */
    @Resource(name="interviewNoteIdGnrService")
    private EgovIdGnrService interviewNoteIdGnrService;
	
	/** ID Generation */
    @Resource(name="interviewMemberIdGnrService")
    private EgovIdGnrService interviewMemberIdGnrService;
    
	@Override
	public List<InterviewVO> listInterview(InterviewVO interview) throws Exception {
		// TODO Auto-generated method stub
		return interviewMapper.listInterview(interview);
	}

	@Override
	public List<InterviewMemberVO> InterviewMembers(InterviewVO interview)
			throws Exception {
		// TODO Auto-generated method stub
		return interviewMapper.InterviewMembers(interview);
	}

	@Override
	public int insertInterview(InterviewVO interviewVO,
			MultipartHttpServletRequest multiRequest) throws Exception {
		// TODO Auto-generated method stub
		String pkInterviewNoteId = interviewNoteIdGnrService.getNextStringId();
		//첨부파일 저장	 		
		String storePathString ="Globals.fileStorePath";
		String atchFileId = "";
		String atchFileImgId = "";
		final List< MultipartFile > fileObj1 = multiRequest.getFiles("fileName-1");
		final List< MultipartFile > fileObj2 = multiRequest.getFiles("fileName-2");
		if(fileObj1!=null){
			atchFileId = atchFileService.saveAtchFile( fileObj1, "LIN", "", storePathString,"interview" );
			interviewVO.setAtchFileId(atchFileId);
		}
		if(fileObj2!=null){
			atchFileImgId = atchFileService.saveAtchFile( fileObj2, "LIN", "", storePathString ,"interview");
			interviewVO.setAtchFileImgId(atchFileImgId);			
		}
		interviewVO.setInterviewNoteId(pkInterviewNoteId);

		int data=0;
		data= interviewMapper.insertInterview(interviewVO);

		String [] temp_interviewMember=interviewVO.getInterviewMemberSeqs().split(",");

		for(int a=0;temp_interviewMember.length>a ;a++){

			if(temp_interviewMember[a]!=null&&!temp_interviewMember[a].equals("")){

				String pkInterviewMemberId = interviewMemberIdGnrService.getNextStringId();

				interviewVO.setInterviewMemberId(pkInterviewMemberId);
				interviewVO.setInterviewNoteId(pkInterviewNoteId);
				interviewVO.setInterviewMemberSeq(temp_interviewMember[a]);				

				data+= interviewMapper.insertInterviewMember(interviewVO);					
			}
		}
				
		return data;
	}
	@Override
	public int updateInterview(InterviewVO interviewVO,MultipartHttpServletRequest multiRequest) throws Exception {
		// TODO Auto-generated method stub
		//첨부파일 저장
		String storePathString ="Globals.fileStorePath";
		String atchFileId = "";
		String atchFileImgId = "";
		final List< MultipartFile > fileObj1 = multiRequest.getFiles("fileName-1");
		final List< MultipartFile > fileObj2 = multiRequest.getFiles("fileName-2");
		if(fileObj1!=null){
			atchFileId = atchFileService.saveAtchFile( fileObj1, "LIN", "", storePathString ,"interview");
			interviewVO.setAtchFileId(atchFileId);
		}
		if(fileObj2!=null){
			atchFileImgId = atchFileService.saveAtchFile( fileObj2, "LIN", "", storePathString ,"interview");
			interviewVO.setAtchFileImgId(atchFileImgId);			
		}
		
		int data=0;
		data= interviewMapper.updateInterview(interviewVO);
		// 기존학습자삭제후재등록
	 
		data = interviewMapper.deleteInterviewMember(interviewVO);
		
		String [] temp_interviewMember=interviewVO.getInterviewMemberSeqs().split(",");
		for(int a=0;temp_interviewMember.length>a ;a++){

			if(temp_interviewMember[a]!=null&&!temp_interviewMember[a].equals("")){

				String pkInterviewMemberId = interviewMemberIdGnrService.getNextStringId();
				interviewVO.setInterviewMemberId(pkInterviewMemberId);
				interviewVO.setInterviewMemberSeq(temp_interviewMember[a]);				
				data+= interviewMapper.insertInterviewMember(interviewVO);					
			}
		}
				
		return data;
	}
	
	@Override
	public InterviewVO getInterview(InterviewVO interview) throws Exception {
		// TODO Auto-generated method stub
		return interviewMapper.getInterview(interview);
	}

	@Override
	public int deleteInterview(InterviewVO interviewVO) throws Exception {
		// TODO Auto-generated method stub
		return interviewMapper.deleteInterview(interviewVO);
	}

	@Override
	public List<InterviewCompanyVO> InterviewCompanys(InterviewCompanyVO interviewCompanyVO) throws Exception {
		// TODO Auto-generated method stub
		return interviewMapper.InterviewCompanys(interviewCompanyVO);
	}

	@Override
	public InterviewCompanyVO InterviewCompany(	InterviewCompanyVO interviewCompanyVO) throws Exception {
		// TODO Auto-generated method stub
		return interviewMapper.InterviewCompany(interviewCompanyVO);
	}

	@Override
	public List<InterviewVO> listInterviewCenter(InterviewVO interview) 	throws Exception {
		// TODO Auto-generated method stub
		 List<InterviewVO> data = interviewMapper.listInterviewCenter(interview);
		
		return data;
	}

	@Override
	public int updateInterviewCenterSend(InterviewVO interviewVO)
			throws Exception {
		// TODO Auto-generated method stub
		return interviewMapper.updateInterviewCenterSend(interviewVO);
	}

	@Override
	public List<InterviewVO> getInterviewCenter(InterviewVO interview)
			throws Exception {
		// TODO Auto-generated method stub
		 List<InterviewVO> data = interviewMapper.getInterviewCenter(interview);
		 if(data != null && data.size()>0){
			 for(int a=0;data.size()>a ;a++){
				InterviewVO interviewVO = (InterviewVO)data.get(a);
				AtchFileVO atchFileVO = new AtchFileVO();
				atchFileVO.setFileSn(1);
				atchFileVO.setAtchFileId(interviewVO.getAtchFileId());

				//첨부파일
				AtchFileVO resultFile = atchFileService.getAtchFile(atchFileVO);    
				interviewVO.setResultFile(resultFile);
				atchFileVO.setAtchFileId(interviewVO.getAtchFileImgId());
				//첨부이미지파일
				AtchFileVO resultFile2 = atchFileService.getAtchFile(atchFileVO);
				interviewVO.setResultImgFile(resultFile2);
				data.set(a, interviewVO);
			 }
		 } 
		
		return data;
	}

	@Override
	public InterviewCompanyVO InterviewCompanyMember(InterviewCompanyVO interview) throws Exception {
		// TODO Auto-generated method stub
		return interviewMapper.InterviewCompanyMember(interview);
	}

	@Override
	public List<InterviewCompanyVO> listCompanyini(	InterviewCompanyVO interviewCompanyVO) throws Exception {
		// TODO Auto-generated method stub
		return interviewMapper.listCompanyini(interviewCompanyVO);
	}

	@Override
	public List<InterviewCompanyVO> listCompanyCcn( InterviewCompanyVO interviewCompanyVO) throws Exception {
		// TODO Auto-generated method stub
		return  interviewMapper.listCompanyCcn(interviewCompanyVO);
	}

 
}
