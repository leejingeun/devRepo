package kr.co.sitglobal.oklms.lu.workcert.service.impl;

import java.util.List;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.lu.workcert.service.WorkCertService;
import kr.co.sitglobal.oklms.lu.workcert.vo.WorkCertVO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 재직증빙서류 제출을 위한 서비스 인터페이스 클래스
 * @author 이창현
 * @since 2016.12.29
 * @version 1.0
 *
 *
 */
@Transactional(rollbackFor=Exception.class)
@Service("workCertService")
public class WorkCertServiceImpl extends EgovAbstractServiceImpl implements WorkCertService {

	/** ID Generation */
    @Resource(name="workCertIdGnrService")
    private EgovIdGnrService workCertIdGnrService;

    @Resource(name = "workCertMapper")
    private WorkCertMapper workCertMapper;

    @Resource(name="workCertPeriodIdGnrService")
    private EgovIdGnrService workCertPeriodIdGnrService;
    
	@Resource(name = "atchFileService")
	private AtchFileService atchFileService;

	/**
	 * 제직징빙서류 제출 기간 등록 및 수정
	 */
	@Override
	public int goInsertWorkCertPeriod(WorkCertVO workCertVO) throws Exception {
 
		int sqlResultInt = 0;		
		sqlResultInt = workCertMapper.selectWorkCertPeriodCnt(workCertVO);
		if(sqlResultInt>0 ){
			sqlResultInt = workCertMapper.updateWorkCertPeriod(workCertVO);
		}else{
			String periodId =  workCertPeriodIdGnrService.getNextStringId();
			workCertVO.setPeriodId(periodId);
			sqlResultInt = workCertMapper.goInsertWorkCertPeriod(workCertVO);				
		}
		return sqlResultInt;
	}
 
	/**
	 * 제직징빙서류 제출 기간 상세
	 */
	@Override
	public WorkCertVO getWorkCertPeriod(WorkCertVO workCertVO) throws Exception {
		// TODO Auto-generated method stub
		WorkCertVO result = workCertMapper.getWorkCertPeriod(workCertVO);
		return result;
	}
	/**
	 * 제직징빙서류 제출 기간 리스트
	 */
	@Override
	public List<WorkCertVO> listWorkCertPeriod(WorkCertVO workCertVO)
			throws Exception {
		// TODO Auto-generated method stub
		List<WorkCertVO> data = workCertMapper.listWorkCertPeriod(workCertVO);
		return data;
	}
 

	/**
     * 재직증빙서류제출 목록을 조회 한다.
     *
     * @param WorkCertVO
     */
	@Override
	public List<WorkCertVO> selectWorkCert(WorkCertVO workCertVo) throws Exception {

		List<WorkCertVO> data = workCertMapper.selectWorkCert(workCertVo);
		return data;
	}

	/**
     * 재직증빙서류제출 첨부파일아이디를 조회한다.
     *
     * @param WorkCertVO
     */
	@Override
	public WorkCertVO selectAtchFileId(WorkCertVO workCertVo) throws Exception {

		WorkCertVO data = workCertMapper.selectAtchFileId(workCertVo);
		return data;
	}

    

	/**
     *  재직증빙서류를 다운로드 하면 DB를 수정한다.
     *
     * @param WorkCertVO
     */
	@Override
	public String downloadWorkCert(WorkCertVO workCertVo) throws Exception {
		String returnStr = "";
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(workCertVo);

		int sqlResultInt = workCertMapper.downloadWorkCert(workCertVo);
		if( 0 < sqlResultInt ){
			returnStr = loginInfo.getMemId();
		}
		return returnStr;
	}

	/**
     *  재직증빙서류를 다운로드 완료후 삭제에 대한 DB를 수정한다.
     *
     * @param WorkCertVO
     */
	@Override
	public String removeWorkCert(WorkCertVO workCertVo) throws Exception {
		String returnStr = "";
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(workCertVo);

		int sqlResultInt = workCertMapper.removeWorkCert(workCertVo);
		if( 0 < sqlResultInt ){
			returnStr = loginInfo.getMemId();
		}
		return returnStr;
	}
 
	/**
	 * 제직징빙서류 제출 기간 삭제
	 */
	@Override
	public int deleteWorkCertPeriod(WorkCertVO workCertVO)throws Exception {
		// TODO Auto-generated method stub
		int sqlResultInt = 0;
		sqlResultInt =  workCertMapper.deleteWorkCertPeriod(workCertVO);
		return sqlResultInt;

	}
 
	/**
	 * 학습 근로자 제직징빙서류 등록 및 수정
	 */
	@Override
	public int goInsertWorkCert(WorkCertVO workCertVO,final MultipartHttpServletRequest multiRequest) throws Exception {
		
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(workCertVO); // session의 정보를 VO에 추가.

		
		String storePathString ="Globals.fileStorePath";
		String atchFileId = "";
		//첨부파일 저장	 		
		final List< MultipartFile > fileObj1 = multiRequest.getFiles("file_atchFileIdRec");
		final List< MultipartFile > fileObj2 = multiRequest.getFiles("file_atchFileIdInc");
		final List< MultipartFile > fileObj3 = multiRequest.getFiles("file_atchFileIdDoc");
		if(fileObj1!=null && fileObj1.size()>0){			
			atchFileId = atchFileService.saveAtchFile( fileObj1, "", "", storePathString  ,"workcert");		
			workCertVO.setAtchFileIdRec(atchFileId);	
		}
		if(fileObj2!=null && fileObj2.size()>0){			
			atchFileId = atchFileService.saveAtchFile( fileObj2, "", "", storePathString  ,"workcert");		
			workCertVO.setAtchFileIdInc(atchFileId);	
		}
		if(fileObj3!=null && fileObj3.size()>0){			
			atchFileId = atchFileService.saveAtchFile( fileObj3, "", "", storePathString  ,"workcert");		
			workCertVO.setAtchFileIdDoc(atchFileId);	
		}
		
		int sqlResultInt = 0;		
		if(workCertVO.getWorkProofId()!=null && !workCertVO.getWorkProofId().equals("")){
			sqlResultInt = workCertMapper.updateWorkCert(workCertVO);
		}else{
			String pkCompanyId = workCertIdGnrService.getNextStringId();
			workCertVO.setWorkProofId(pkCompanyId);
			sqlResultInt = workCertMapper.goInsertWorkCert(workCertVO);
		}
		return sqlResultInt;
	}
 

	@Override
	public int updateWorkCertMember(WorkCertVO workCertVO) throws Exception {

		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(workCertVO); // session의 정보를 VO에 추가.

		int sqlResultInt = 0;
		//workProofId
		String memIdArr[] =  workCertVO.getMemIdArr();
		for(int i= 0 ; i <memIdArr.length; i ++  ){

			workCertVO.setMemId(memIdArr[i]);
			sqlResultInt = workCertMapper.updateWorkCertMember(workCertVO);
		}

		return sqlResultInt;

	}
 
	@Override
	public List<WorkCertVO> listWorkCertStatePop(WorkCertVO workCertVO)
			throws Exception {
		List<WorkCertVO> resultList= workCertMapper.listWorkCertStatePop(workCertVO);
		return resultList;
	}

	@Override
	public List<WorkCertVO> listWorkCertStatePopup(WorkCertVO workCertVO)
			throws Exception {
		// TODO Auto-generated method stub
		return  workCertMapper.listWorkCertStatePopup(workCertVO);
	}

	@Override
	public List<WorkCertVO> listWorkCertDetail(WorkCertVO workCertVO)
			throws Exception {
		// TODO Auto-generated method stub
		return workCertMapper.listWorkCertDetail(workCertVO);
	}

	@Override
	public List<WorkCertVO> selectAtchFileIdList(WorkCertVO workCertVO)
			throws Exception {
		// TODO Auto-generated method stub
		return workCertMapper.selectAtchFileIdList(workCertVO);
	}

	@Override
	public int updateWorkCertMemberFiledown(WorkCertVO workCertVO)
			throws Exception {
		// TODO Auto-generated method stub
		return workCertMapper.updateWorkCertMemberFiledown(workCertVO);
	}

	@Override
	public int updateOffWorkCertMember(WorkCertVO workCertVO) throws Exception {
		// TODO Auto-generated method stub
		return workCertMapper.updateOffWorkCertMember(workCertVO);
	}
 
 

}
