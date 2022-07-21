package kr.co.sitglobal.oklms.lu.report.service.impl;
 
import java.util.List;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.util.AtchFileUtil;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningSchVO;
import kr.co.sitglobal.oklms.lu.report.service.ReportService;
import kr.co.sitglobal.oklms.lu.report.vo.ReportVO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl; 
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Transactional(rollbackFor=Exception.class)
@Service("ReportService")
public class ReportServiceImpl extends EgovAbstractServiceImpl implements ReportService{
	
	@Resource(name = "ReportMapper")
    private ReportMapper reportMapper;
	
	/** ID Generation */
    @Resource(name="reportIdGnrService")
    private EgovIdGnrService reportIdGnrService;
    
	/** ID Generation */
    @Resource(name="reportSubmitIdGnrService")
    private EgovIdGnrService reportSubmitIdGnrService;
    

	@Resource(name = "atchFileService")
	private AtchFileService atchFileService;

	@Resource(name = "atchFileUtil")
	private AtchFileUtil atchFileUtil;
	
	@Override
	public List<OnlineTraningSchVO> listLmsSubjWeek(CurrProcVO currProcVO)	throws Exception {
		// TODO Auto-generated method stub
		List<OnlineTraningSchVO> data= reportMapper.listLmsSubjWeek(currProcVO);
		return data;
	}

	@Override
	public CurrProcVO getCurrproc(CurrProcVO currProcVO) throws Exception {

		CurrProcVO data = reportMapper.getCurrproc(currProcVO);
		return data;
	}
	
	@Override
	public List<ReportVO> listReport(ReportVO reportVO) throws Exception {
		// TODO Auto-generated method stub
		List<ReportVO> data = reportMapper.listReport(reportVO);
		return data;
	}
	
	@Override
	public ReportVO getReport(ReportVO reportVO) throws Exception {
		// TODO Auto-generated method stub
		return reportMapper.getReport(reportVO);
	}

	@Override
	public int insertReport(ReportVO reportVO,final MultipartHttpServletRequest multiRequest) throws Exception {
		// TODO Auto-generated method stub
		String pkReportId = reportIdGnrService.getNextStringId();
		//첨부파일 저장	 		
		final List< MultipartFile > fileObj = multiRequest.getFiles("file-input");
		String storePathString ="Globals.fileStorePath";
		String atchFileId = atchFileService.saveAtchFile( fileObj, "RET", "", storePathString ,"report");
		reportVO.setReportId(pkReportId);
		reportVO.setAtchFileId(atchFileId);
		int data = 0; 
		data=reportMapper.insertReport(reportVO);
		
		return data;
	}

	@Override
	public int updateReport(ReportVO reportVO,final MultipartHttpServletRequest multiRequest) throws Exception {
		// TODO Auto-generated method stub
		//첨부파일 저장	 		
		final List< MultipartFile > fileObj = multiRequest.getFiles("file-input");
		String storePathString ="Globals.fileStorePath";
		String atchFileId = "";
		if(fileObj!=null && fileObj.size()>0){			
			atchFileId = atchFileService.saveAtchFile( fileObj, "RET", "", storePathString  ,"report");		
			reportVO.setAtchFileId(atchFileId);	
		}		
		int data = reportMapper.updateReport(reportVO);
		return data;
	}

	@Override
	public int deleteReport(ReportVO reportVO) throws Exception {
		// TODO Auto-generated method stub
		int data = reportMapper.deleteReport(reportVO);
		return data;
	}

	@Override
	public int getReportNoCnt(ReportVO reportVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public List<ReportVO> listReportSubmit(ReportVO reportVO) throws Exception {
		// TODO Auto-generated method stub
		List<ReportVO> data = reportMapper.reportSubmitList(reportVO);
		return data;
	}
	
	@Override
	public ReportVO getReportSubmit(ReportVO reportVO) throws Exception {
		// TODO Auto-generated method stub
		return reportMapper.getReportSubmit(reportVO);
	}

	@Override
	public int insertReportSubmit(ReportVO reportVO) throws Exception {
		// TODO Auto-generated method stub
		String pkReportSubmitId = reportSubmitIdGnrService.getNextStringId();
		reportVO.setReportSubmitId(pkReportSubmitId);
		int data = reportMapper.insertReportSubmit(reportVO);
		return data;
	}

	@Override
	public int updateReportSubmit(ReportVO reportVO) throws Exception {
		// TODO Auto-generated method stub
		int data = reportMapper.updateReportSubmit(reportVO);
		return data;
	}
	@Override
	public int updateReportSubmitArr(ReportVO reportVO) throws Exception {
		// TODO Auto-generated method stub
		
		int data = 0;		 
		for(int a=0 ;reportVO.getArrReportSubmitId().length>a ; a++){
			ReportVO temp = new ReportVO();
			temp.setReportSubmitId(reportVO.getArrReportSubmitId()[a]);
			temp.setEvalScore(reportVO.getArrEvalScore()[a]);
			data=reportMapper.updateReportSubmit(temp);	
		}	
		
		return data;
	}
	@Override
	public int deleteReportSubmit(ReportVO reportVO) throws Exception {
		// TODO Auto-generated method stub
		int data = reportMapper.deleteReportSubmit(reportVO);
		return data;
	}

	@Override
	public List<ReportVO> listReportStd(ReportVO reportVO) throws Exception {
		// TODO Auto-generated method stub
		List<ReportVO> data = reportMapper.listReportStd(reportVO);
		return data;
	}

	@Override
	public int insertReportStd(ReportVO reportVO,	MultipartHttpServletRequest multiRequest) throws Exception {
		// TODO Auto-generated method stub
		//첨부파일 저장	 		
		final List< MultipartFile > fileObj = multiRequest.getFiles("file-input");
		String storePathString ="Globals.fileStorePath";
		String atchFileId = atchFileService.saveAtchFile( fileObj, "RET", "", storePathString ,"report");
		reportVO.setAtchFileId(atchFileId);
		
		int data = 0;	 
		if((reportVO.getReportSubmitId()==null||reportVO.getReportSubmitId().equals(""))&&atchFileId!=null&&!atchFileId.equals("")){
			String pkReportSubmitId = reportSubmitIdGnrService.getNextStringId();
			reportVO.setReportSubmitId(pkReportSubmitId);
			data = reportMapper.insertReportSubmit(reportVO);	
		}else{
			data = reportMapper.updateReportSubmit(reportVO);
		}
		
		return data;
	}

 
 
}
