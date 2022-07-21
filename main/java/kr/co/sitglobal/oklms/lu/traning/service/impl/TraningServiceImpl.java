
/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 07. 01.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.lu.traning.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.co.sitglobal.aunuri.service.impl.AunuriLinkMapper;
import kr.co.sitglobal.aunuri.vo.AunuriLinkSubjectWeekNcsVO;
import kr.co.sitglobal.oklms.comm.util.ExcelData;
import kr.co.sitglobal.oklms.comm.util.ExcelDataSet;
import kr.co.sitglobal.oklms.comm.util.ExcelHandler;
import kr.co.sitglobal.oklms.comm.util.FileUploadUtil;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.la.link.service.impl.LinkMapper;
import kr.co.sitglobal.oklms.lu.traning.service.TraningService;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningMemberVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningNoteVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningProcessMappingVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningScheduleVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningVO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.utl.sim.service.EgovClntInfo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

 /**
 * 프로토타입 게시판 CRUD 비지니스 로직을 구현하는 클레스.
 * @author 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
@Service("traningService")
public class TraningServiceImpl extends EgovAbstractServiceImpl implements TraningService{

	/** ID Generation */
    @Resource(name="traningProcessIdGnrService")
    private EgovIdGnrService traningProcessIdGnrService;

    @Resource(name="traningProcessMappingIdGnrService")
    private EgovIdGnrService traningProcessMappingIdGnrService;

    @Resource(name = "atchFileService")
   	private AtchFileService atchFileService;

    @Resource(name = "traningMapper")
    private TraningMapper traningMapper;
    
    @Resource(name = "aunuriLinkMapper")
    private AunuriLinkMapper aunuriLinkMapper;
    
    @Resource(name = "linkMapper")
    private LinkMapper linkMapper;

    /** 센터담당자용 Start ID Generation */
    //훈련시간표등록 화면용 시퀀스 서비스
    //훈련시간표-주차정보
    @Resource(name="traningSubjWeekSchIdGnrService")
    private EgovIdGnrService traningSubjWeekSchIdGnrService;

    //훈련시간표-주차별시간
    @Resource(name="traningSubjWeekTimeSchIdGnrService")
    private EgovIdGnrService traningSubjWeekTimeSchIdGnrService;

    //훈련시간표-주차별 NCS단위
    @Resource(name="traningSubNcsUnitSchIdGnrService")
    private EgovIdGnrService traningSubNcsUnitSchIdGnrService;

    //훈련시간표-주차별 NCS요소
    @Resource(name="traningSubNcsElemSchIdGnrService")
    private EgovIdGnrService traningSubNcsElemSchIdGnrService;

    //주차정보등록 화면용 시퀀스 서비스
    //주차정보
    @Resource(name="subjWeekIdGnrService")
    private EgovIdGnrService subjWeekIdGnrService;

    //주차별시간
    @Resource(name="subjWeekTimeIdGnrService")
    private EgovIdGnrService subjWeekTimeIdGnrService;

    //주차별 NCS단위
    @Resource(name="subjNcsUnitIdGnrService")
    private EgovIdGnrService subjNcsUnitIdGnrService;

    //주차별 NCS요소
    @Resource(name="subjNcsElemIdGnrService")
    private EgovIdGnrService subjNcsElemIdGnrService;
    /** 센터담당자용 End ID Generation */

	/** ID Generation */
	// 훈련 상세 lms_traning_note_detail
    @Resource(name="traningDetailIdGnrService")
    private EgovIdGnrService traningDetailIdGnrService;

	/** ID Generation */
	// 훈련 상세 lms_traning_note_detail
    @Resource(name="traningMasterIdGnrService")
    private EgovIdGnrService traningMasterIdGnrService;
    //lms_subj_week_time_enrichment
    @Resource(name="traningWeekTimeEnrichmentWeekIdGnrService")
    private EgovIdGnrService traningWeekTimeEnrichmentWeekIdGnrService;

    @Resource(name="traningWeekTimeEnrichmentTimeIdGnrService")
    private EgovIdGnrService traningWeekTimeEnrichmentTimeIdGnrService;



  	@Override
	public List<TraningVO> listTraningProcess(TraningVO traningVO) throws Exception {
		List<TraningVO> data = traningMapper.listTraningProcess(traningVO);
		return data;
	}

  	@Override
  	public List<TraningVO> listTraningProcessManage(TraningVO traningVO) throws Exception {
		List<TraningVO> data = traningMapper.listTraningProcessManage(traningVO);
		return data;
	}

  	@Override
  	public List<TraningProcessMappingVO> listOjtSubjectInfo(TraningProcessMappingVO traningProcessMappingVO) throws Exception {
		List<TraningProcessMappingVO> data = traningMapper.listOjtSubjectInfo(traningProcessMappingVO);
		return data;
	}

  	@Override
  	public List<TraningProcessMappingVO> listOffJtSubjectInfo(TraningProcessMappingVO traningProcessMappingVO) throws Exception {
		List<TraningProcessMappingVO> data = traningMapper.listOffJtSubjectInfo(traningProcessMappingVO);
		return data;
	}

  	@Override
  	public List<TraningProcessMappingVO> listTraningProcessInfo(TraningProcessMappingVO traningProcessMappingVO) throws Exception {
		List<TraningProcessMappingVO> data = traningMapper.listTraningProcessInfo(traningProcessMappingVO);
		return data;
	}

  	@Override
  	public List<TraningScheduleVO> listTraningScheduleExcelDownload(TraningScheduleVO traningScheduleVO) throws Exception {
		List<TraningScheduleVO> data = traningMapper.listTraningScheduleExcelDownload(traningScheduleVO);
		return data;
	}

  	@Override
  	public List<TraningScheduleVO> listTraningScheduleView(TraningScheduleVO traningScheduleVO) throws Exception {
		List<TraningScheduleVO> data = traningMapper.listTraningScheduleView(traningScheduleVO);
		return data;
	}

  	@Override
  	public List<TraningScheduleVO> listTraningScheduleModifyView(TraningScheduleVO traningScheduleVO) throws Exception {
		List<TraningScheduleVO> data = traningMapper.listTraningScheduleModifyView(traningScheduleVO);
		return data;
	}

	 @Override
	 public TraningProcessMappingVO getOffJtSubjectInfo(TraningProcessMappingVO traningProcessMappingVO) throws Exception {
		 TraningProcessMappingVO data = traningMapper.getOffJtSubjectInfo(traningProcessMappingVO);
		 return data;
	 }

	 @Override
	 public TraningScheduleVO getTraningSubjWeekInfo(TraningScheduleVO traningScheduleVO) throws Exception {
		TraningScheduleVO data = traningMapper.getTraningSubjWeekInfo(traningScheduleVO);
		return data;
	 }

	 @Override
	 public TraningScheduleVO getTraningSubjWeekTimeSum(TraningScheduleVO traningScheduleVO) throws Exception {
		TraningScheduleVO data = traningMapper.getTraningSubjWeekTimeSum(traningScheduleVO);
		return data;
	 }

  	@Override
	public int getTdOffJtTotalCnt(TraningVO traningVO) throws Exception {
		return traningMapper.getTdOffJtTotalCnt(traningVO);
	}

	@Override
	public int getTdOjtTotalCnt(TraningVO traningVO) throws Exception {
		return traningMapper.getTdOjtTotalCnt(traningVO);
	}

	@Override
	public int getTraningProcessMappingCnt(TraningProcessMappingVO traningProcessMappingVO) throws Exception {
		return traningMapper.getTraningProcessMappingCnt(traningProcessMappingVO);
	}

	@Override
	public int insertTraningProcessInfoList(TraningProcessMappingVO traningProcessMappingVO) throws Exception {
		int sqlResultInt = 0;
		int cnt = 0;

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(traningProcessMappingVO);

		//해당기업에 기존 훈련과정 정보가 저장되어있는지 Count 조회
		cnt = traningMapper.getTraningProcessCnt(traningProcessMappingVO);

		if(cnt > 0){
			cnt = 99;
		}

		if(cnt != 99){
			String pkStr = traningProcessIdGnrService.getNextStringId();
			System.out.println("##### pkStr="+pkStr);
			traningProcessMappingVO.setTraningProcessManageId(pkStr);

			sqlResultInt = traningMapper.insertTraningProcessInfoList(traningProcessMappingVO);
		} else {
			sqlResultInt = cnt;
		}

		return sqlResultInt;
	}

	@Override
	public int insertTraningProcessMappingInfoList(TraningProcessMappingVO traningProcessMappingVO) throws Exception {

		int sqlResultInt = 0;

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(traningProcessMappingVO);

		String pkStr = traningProcessMappingIdGnrService.getNextStringId();
		System.out.println("##### pkStr="+pkStr);
		traningProcessMappingVO.setTraningProcessMappingId(pkStr);

		sqlResultInt = traningMapper.insertTraningProcessMappingInfoList(traningProcessMappingVO);

		// 교과별 훈련방식을 가져옴 2017.04.21 csh
		String subjectTraningType =  traningMapper.getSubjectTraningType(traningProcessMappingVO);

		// 교과별 훈련방식이 OJT 일 경우만 회사정보 업데이트
		if("OJT".equals(subjectTraningType)){
			traningMapper.updateMemberCompanyId(traningProcessMappingVO);
		}
		return sqlResultInt;
	}
	
	@Override
	public int insertTraningProcessMappingInfoListUpdate(TraningProcessMappingVO traningProcessMappingVO) throws Exception {

		int sqlResultInt = 0;

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(traningProcessMappingVO);

		String pkStr = traningProcessMappingIdGnrService.getNextStringId();
		System.out.println("##### pkStr="+pkStr);
		traningProcessMappingVO.setTraningProcessMappingId(pkStr);

		sqlResultInt = traningMapper.insertTraningProcessMappingInfoListUpdate(traningProcessMappingVO);

		// 교과별 훈련방식을 가져옴 2017.04.21 csh
		String subjectTraningType =  traningMapper.getSubjectTraningType(traningProcessMappingVO);

		// 교과별 훈련방식이 OJT 일 경우만 회사정보 업데이트
		if("OJT".equals(subjectTraningType)){
			traningMapper.updateMemberCompanyId(traningProcessMappingVO);
		}
		return sqlResultInt;
	}

	@Override
	public String insertTraningSchedule(TraningScheduleVO traningScheduleVO) throws Exception {

		String sqlResult = "SUCCESS";
		String oldWeekId = "";
		String newWeekId = "";
		String newTimeId = "";
		String ncsUnitId = "";
		int sqlResultInt1 = 0;
		int sqlResultInt2 = 0;
		int sqlResultInt3 = 0;
		int sqlResultInt4 = 0;
		int sqlResultInt5 = 0;

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(traningScheduleVO);

		//주차정보 생성
		List<TraningScheduleVO> listTraningSubjWeek = traningMapper.listTraningSubjWeek(traningScheduleVO);
		for(TraningScheduleVO scheduleVO1 : listTraningSubjWeek){
			TraningScheduleVO insertVO1 = new TraningScheduleVO();
			loginInfo.putSessionToVo(insertVO1);

			oldWeekId = scheduleVO1.getWeekId();

			newWeekId = subjWeekIdGnrService.getNextStringId();
			insertVO1.setWeekId(newWeekId);
			insertVO1.setYyyy(traningScheduleVO.getSearchYyyy());
			insertVO1.setTerm(traningScheduleVO.getSearchTerm());
			insertVO1.setSubjectCode(traningScheduleVO.getSearchSubjectCode());
			insertVO1.setSubClass(traningScheduleVO.getSearchSubClass());
			insertVO1.setWeekCnt(scheduleVO1.getWeekCnt());

			System.out.println("##### insertVO1="+insertVO1.toString());
			sqlResultInt1 += traningMapper.insertSubjWeek(insertVO1);
			
			if(sqlResultInt1 == 0){
				sqlResult = "FAIL";
			} else { // 주차별 등록이 된다면 NCS 분류등록 (OJT)
				
				//============== 주차별 NCS 등록 START ====================================================
				/*
				egovLogger.debug("=================================== insertVO1.getYyyy() : "+insertVO1.getYyyy());
				egovLogger.debug("===================================  insertVO1.getTerm() :   "+insertVO1.getTerm());
				egovLogger.debug("===================================  insertVO1.getSubjectCode() :   "+insertVO1.getSubjectCode());
				egovLogger.debug("===================================  insertVO1.getSubClass() :   "+insertVO1.getSubClass());
				egovLogger.debug("===================================  insertVO1.getWeekCnt() :   "+insertVO1.getWeekCnt());
				
				
				AunuriLinkSubjectWeekNcsVO aunuriLinkSubjectWeekNcsVO = new AunuriLinkSubjectWeekNcsVO();
			
				aunuriLinkSubjectWeekNcsVO.setYyyy(insertVO1.getYyyy());
				aunuriLinkSubjectWeekNcsVO.setTerm(insertVO1.getTerm());
				aunuriLinkSubjectWeekNcsVO.setSubjectCode(insertVO1.getSubjectCode());
				aunuriLinkSubjectWeekNcsVO.setSubjectClass(insertVO1.getSubClass());
				aunuriLinkSubjectWeekNcsVO.setWeekId(insertVO1.getWeekId());
				aunuriLinkSubjectWeekNcsVO.setWeekCnt(insertVO1.getWeekCnt());
				
				// NCS 단위를 가져옴
				AunuriLinkSubjectWeekNcsVO weekNcsVO = aunuriLinkMapper.getAunuriWeekNcsUnit(aunuriLinkSubjectWeekNcsVO);
				
				
				if(weekNcsVO != null){
					weekNcsVO.setWeekId(aunuriLinkSubjectWeekNcsVO.getWeekId());
					
					egovLogger.debug("***************************************  weekNcsVO getYyyy :   "+weekNcsVO.getYyyy());
					egovLogger.debug("***************************************  weekNcsVO getTerm :   "+weekNcsVO.getTerm());
					egovLogger.debug("***************************************  weekNcsVO getSubjectCode :   "+weekNcsVO.getSubjectCode());
					egovLogger.debug("***************************************  weekNcsVO getSubjectClass :   "+weekNcsVO.getSubjectClass());
					egovLogger.debug("***************************************  weekNcsVO getWeekCnt :   "+weekNcsVO.getWeekCnt());
					egovLogger.debug("***************************************  weekNcsVO getNcsUnitId :   "+weekNcsVO.getNcsUnitId());
					egovLogger.debug("***************************************  weekNcsVO getNcsUnitName :   "+weekNcsVO.getNcsUnitName());
					
					// NCS 단위 등록
					sqlResultInt5 += linkMapper.insertAunuriWeekNcsUnit(weekNcsVO);
					
					// NCS 단위별 요소를 가져옴
					weekNcsVO.setWeekCnt(aunuriLinkSubjectWeekNcsVO.getWeekCnt());
					List<AunuriLinkSubjectWeekNcsVO> dataList = aunuriLinkMapper.listAunuriWeekNcsElem(weekNcsVO);
					
					for(int x=0; x < dataList.size(); x++){
						AunuriLinkSubjectWeekNcsVO elemVO = dataList.get(x);
						
						egovLogger.debug("############################### elemVO getYyyy :   "+weekNcsVO.getYyyy());
						egovLogger.debug("###############################  elemVO getTerm :   "+weekNcsVO.getTerm());
						egovLogger.debug("###############################  elemVO getSubjectCode :   "+weekNcsVO.getSubjectCode());
						egovLogger.debug("###############################  elemVO getSubjectClass :   "+weekNcsVO.getSubjectClass());
						egovLogger.debug("###############################  elemVO getWeekCnt :   "+weekNcsVO.getWeekCnt());
						egovLogger.debug("###############################  elemVO getNcsUnitId :   "+weekNcsVO.getNcsUnitId());
						egovLogger.debug("###############################  elemVO getNcsElemtId :   "+weekNcsVO.getNcsElemId());
						egovLogger.debug("###############################  elemVO getNcsElemName :   "+weekNcsVO.getNcsElemName());
						
						elemVO.setWeekId(aunuriLinkSubjectWeekNcsVO.getWeekId());
						sqlResultInt5 += linkMapper.insertAunuriWeekNcsElem(elemVO);
					}
				}
				*/
				//============== 주차별 NCS 등록 END ====================================================
			}
			
			egovLogger.debug("***************************************  sqlResultInt5 :   "+sqlResultInt5);

			traningScheduleVO.setOldWeekId(oldWeekId);

			//주차별 시간 생성
			List<TraningScheduleVO> listTraningSubjWeekTime = traningMapper.listTraningSubjWeekTime(traningScheduleVO);
			for(TraningScheduleVO scheduleVO2 : listTraningSubjWeekTime){
				TraningScheduleVO insertVO2 = new TraningScheduleVO();
				loginInfo.putSessionToVo(insertVO2);

				newTimeId = subjWeekTimeIdGnrService.getNextStringId();
				insertVO2.setWeekId(newWeekId);
				insertVO2.setTimeId(newTimeId);
				insertVO2.setYyyy(traningScheduleVO.getSearchYyyy());
				insertVO2.setTerm(traningScheduleVO.getSearchTerm());
				insertVO2.setSubjectCode(traningScheduleVO.getSearchSubjectCode());
				insertVO2.setSubClass(traningScheduleVO.getSearchSubClass());
				insertVO2.setTraningDate(scheduleVO2.getTraningDate());
				insertVO2.setTraningStHour(scheduleVO2.getTraningStHour());
				insertVO2.setTraningStMin(scheduleVO2.getTraningStMin());
				insertVO2.setTraningEdHour(scheduleVO2.getTraningEdHour());
				insertVO2.setTraningEdMin(scheduleVO2.getTraningEdMin());
				insertVO2.setLeadTime(scheduleVO2.getLeadTime());
				insertVO2.setNcsUnitName(scheduleVO2.getNcsUnitName());
				insertVO2.setNcsElemName(scheduleVO2.getNcsElemName());

				System.out.println("##### insertVO2="+insertVO2.toString());
				sqlResultInt2 += traningMapper.insertSubjWeekTime(insertVO2);

				if(sqlResultInt2 == 0){
					sqlResult = "FAIL";
				}
			}

			//아우누리에 데이타 연계를 통해 주차별 NCS단위, 주차별 NCS요소 받아올수 있어 주석처리함.

			//주차별 NCS단위
			/*List<TraningScheduleVO> listTraningSubjNcsUnit = traningMapper.listTraningSubjNcsUnit(traningScheduleVO);
			for(TraningScheduleVO scheduleVO3 : listTraningSubjNcsUnit){
				TraningScheduleVO insertVO3 = new TraningScheduleVO();
				loginInfo.putSessionToVo(insertVO3);

				ncsUnitId = subjNcsUnitIdGnrService.getNextStringId();
				insertVO3.setWeekId(newWeekId);
				insertVO3.setTimeId(newTimeId);
				insertVO3.setNcsUnitId(ncsUnitId);
				insertVO3.setYyyy(traningScheduleVO.getSearchYyyy());
				insertVO3.setTerm(traningScheduleVO.getSearchTerm());
				insertVO3.setSubjectCode(traningScheduleVO.getSearchSubjectCode());
				insertVO3.setSubClass(traningScheduleVO.getSearchSubClass());
				insertVO3.setNcsUnitName(scheduleVO3.getNcsUnitName());

				System.out.println("##### insertVO3="+insertVO3.toString());
				sqlResultInt3 += traningMapper.insertSubjWeekNcsUnit(insertVO3);

				if(sqlResultInt3 == 0){
					sqlResult = "FAIL";
				}

				traningScheduleVO.setOldNcsUnitId(scheduleVO3.getNcsUnitId());

				//주차별 NCS요소
				List<TraningScheduleVO> listTraningSubjNcsElem = traningMapper.listTraningSubjNcsElem(traningScheduleVO);
				for(TraningScheduleVO scheduleVO4 : listTraningSubjNcsElem){
					TraningScheduleVO insertVO4 = new TraningScheduleVO();
					loginInfo.putSessionToVo(insertVO4);

					String ncsElemId = subjNcsElemIdGnrService.getNextStringId();

					insertVO4.setNcsUnitId(ncsUnitId);
					insertVO4.setTimeId(newTimeId);
					insertVO4.setNcsElemId(ncsElemId);
					insertVO4.setWeekId(newWeekId);
					insertVO4.setYyyy(traningScheduleVO.getSearchYyyy());
					insertVO4.setTerm(traningScheduleVO.getSearchTerm());
					insertVO4.setSubjectCode(traningScheduleVO.getSearchSubjectCode());
					insertVO4.setSubClass(traningScheduleVO.getSearchSubClass());
					insertVO4.setNcsElemName(scheduleVO4.getNcsElemName());

					System.out.println("##### insertVO4="+insertVO4.toString());
					sqlResultInt4 += traningMapper.insertSubjWeekNcsElem(insertVO4);

					if(sqlResultInt4 == 0){
						sqlResult = "FAIL";
					}
				}
			}*/

			if("SUCCESS".equals(sqlResult)){
				//실제사용하는 주차정보에 생성 성공했을데 다음로직 수행
				//1.주차별 NCS단위 테이블에 시간아이디를 주차별시간 테이블에서 시간아이디 항목을 가지고와서 업데이트한다.
				//2.주차별 NCS요소 테이블에 시간아이디를 주차별시간 테이블에서 시간아이디 항목을 가지고와서 업데이트한다.
				traningMapper.updateTraningSubjWeekSchStatus(traningScheduleVO);
			}
		}

		return sqlResult;
	}

	@Override
	public String insertTraningScheduleExcel(TraningScheduleVO traningScheduleVO, MultipartHttpServletRequest multiRequest, HttpServletRequest request) throws Exception {
		int intWeekCnt1 = 0; //1주차
		int intWeekCnt2 = 0; //2주차
		int intWeekCnt3 = 0; //3주차
		int intWeekCnt4 = 0; //4주차
		int intWeekCnt5 = 0; //5주차
		int intWeekCnt6 = 0; //6주차
		int intWeekCnt7 = 0; //7주차
		int intWeekCnt8 = 0; //8주차
		int intWeekCnt9 = 0; //9주차
		int intWeekCnt10 = 0; //10주차
		int intWeekCnt11 = 0; //11주차
		int intWeekCnt12 = 0; //12주차
		int intWeekCnt13 = 0; //13주차
		int intWeekCnt14 = 0; //14주차
		int intWeekCnt15 = 0;//15주차
		int sqlResultIntTemp1 = 0;
		int sqlResultInt1 = 0;
		int sqlResultInt2 = 0;
		int sqlResultInt3 = 0;
		int sqlResultInt4 = 0;
		String sqlResult = "SUCCESS";
		String weekId = "";
		TraningScheduleVO traningSchVO = new TraningScheduleVO();

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(traningSchVO);

		MultipartFile mfile = multiRequest.getFile("uploadExcelFile");

		if (!mfile.isEmpty() ) {
			String realUploadPath = EgovProperties.getProperty("Globals.fileStorePath");
			String tmpFile = FileUploadUtil.uploadFormFile(mfile, realUploadPath );
			//물리경로
			String fullPath = realUploadPath + File.separator+tmpFile;

			ExcelData 		row;
			ExcelHandler 	eh 		= new ExcelHandler(fullPath);
			ExcelDataSet 	dataSet 	= eh.parseExcelData();

			for (int i = 0; i < dataSet.size(); i++) {
				row = dataSet.getRow(i);

				//주차
				String weekCnt = "";
				weekCnt = row.getColumn("주차").trim();
				if ("".equals(weekCnt)) {
					throw new Exception("주차 항목에 값이 없습니다 ");
				}

				//훈련일자
				String traningDate = "";
				traningDate = row.getColumn("훈련일자").trim();
				if ("".equals(traningDate)) {
					throw new Exception("훈련일자 항목에 값이 없습니다 ");
				}

				//교육-시작-시간
				String traningStHour = "";
				traningStHour = row.getColumn("교육-시작-시간").trim();
				if ("".equals(traningStHour)) {
					throw new Exception("교육-시작-시간 항목에 값이 없습니다 ");
				}

				//교육-시작-분
				String traningStMin = "";
				traningStMin = row.getColumn("교육-시작-분").trim();
				if ("".equals(traningStMin)) {
					throw new Exception("교육-시작-분 항목에 값이 없습니다 ");
				}

				//교육-종료-시간
				String traningEdHour = "";
				traningEdHour = row.getColumn("교육-종료-시간").trim();
				if ("".equals(traningEdHour)) {
					throw new Exception("교육-종료-시간 항목에 값이 없습니다 ");
				}

				//교육-종료-분
				String traningEdMin = "";
				traningEdMin = row.getColumn("교육-종료-분").trim();
				if ("".equals(traningEdMin)) {
					throw new Exception("교육-종료-분 항목에 값이 없습니다 ");
				}

				//소요시간
				String leadTime = "";
				leadTime = row.getColumn("소요시간").trim();
				if ("".equals(leadTime)) {
					throw new Exception("소요시간 항목에 값이 없습니다 ");
				}

				//단원명
				String ncsUnitName = "";
				ncsUnitName = row.getColumn("능력단위명").trim();
				//if ("".equals(ncsUnitName)) {
				//	throw new Exception("능력단위명 항목에 값이 없습니다 ");
				//}

				//단원요소명
				String ncsElemName = "";
				ncsElemName = row.getColumn("능력단위요소명").trim();
				//if ("".equals(ncsElemName)) {
				//	throw new Exception("능력단위요소명 항목에 값이 없습니다 ");
				//}

				//훈련장소
				String trainPlace = "";
				trainPlace = row.getColumn("훈련장소").trim();
				if ("".equals(trainPlace)) {
					throw new Exception("훈련장소 항목에 값이 없습니다 ");
				}

				traningSchVO.setYyyy(traningScheduleVO.getYyyy());
				traningSchVO.setTerm(traningScheduleVO.getTerm());
				traningSchVO.setSubjectCode(traningScheduleVO.getSubjectCode());
				traningSchVO.setSubClass(traningScheduleVO.getSubClass());
				traningSchVO.setSubjctTitle(traningScheduleVO.getSubjctTitle());
				traningSchVO.setRetunReason("훈련시간표 신규등록");
				traningSchVO.setWeekCnt(weekCnt);
				traningSchVO.setTraningDate(traningDate);
				traningSchVO.setTraningStHour(traningStHour);
				traningSchVO.setTraningStMin(traningStMin);
				traningSchVO.setTraningEdHour(traningEdHour);
				traningSchVO.setTraningEdMin(traningEdMin);
				traningSchVO.setLeadTime(leadTime);
				traningSchVO.setNcsUnitName(ncsUnitName);
				traningSchVO.setNcsElemName(ncsElemName);
				traningSchVO.setTrainPlace(trainPlace);
				
				EgovClntInfo clntInfo = new EgovClntInfo();
				String ipAddress = clntInfo.getClntIP(request);
				traningSchVO.setIpAddress(ipAddress);

				System.out.println("##### idx="+i);
				System.out.println("##### traningSchVO="+traningSchVO.toString());

				//훈련시간표-주차정보 저장
				if("1".equals(weekCnt) && intWeekCnt1 == 0){
					++intWeekCnt1;
					weekId = traningSubjWeekSchIdGnrService.getNextStringId();
					traningSchVO.setWeekId(weekId);
					System.out.println("##### weekId="+weekId);

					String strStorePath ="Globals.fileStorePath";
					String atchFileId = "";
					final List< MultipartFile > fileObj = multiRequest.getFiles("uploadExcelFile");
					atchFileId = atchFileService.saveAtchFile( fileObj, "WEEK_", "", strStorePath );
					traningSchVO.setAtchFileId(atchFileId);

					sqlResultIntTemp1 += traningMapper.insertTraningSubjWeekSchExcel(traningSchVO);
					sqlResultInt1 += sqlResultIntTemp1;
				} else if("2".equals(weekCnt) && intWeekCnt2 == 0){
					++intWeekCnt2;
					weekId = traningSubjWeekSchIdGnrService.getNextStringId();
					traningSchVO.setWeekId(weekId);
					System.out.println("##### weekId="+weekId);

					sqlResultIntTemp1 += traningMapper.insertTraningSubjWeekSchExcel(traningSchVO);
					sqlResultInt1 += sqlResultIntTemp1;
				} else if("3".equals(weekCnt) && intWeekCnt3 == 0){
					++intWeekCnt3;
					weekId = traningSubjWeekSchIdGnrService.getNextStringId();
					traningSchVO.setWeekId(weekId);
					System.out.println("##### weekId="+weekId);

					sqlResultIntTemp1 += traningMapper.insertTraningSubjWeekSchExcel(traningSchVO);
					sqlResultInt1 += sqlResultIntTemp1;
				} else if("4".equals(weekCnt) && intWeekCnt4 == 0){
					++intWeekCnt4;
					weekId = traningSubjWeekSchIdGnrService.getNextStringId();
					traningSchVO.setWeekId(weekId);
					System.out.println("##### weekId="+weekId);

					sqlResultIntTemp1 += traningMapper.insertTraningSubjWeekSchExcel(traningSchVO);
					sqlResultInt1 += sqlResultIntTemp1;
				} else if("5".equals(weekCnt) && intWeekCnt5 == 0){
					++intWeekCnt5;
					weekId = traningSubjWeekSchIdGnrService.getNextStringId();
					traningSchVO.setWeekId(weekId);
					System.out.println("##### weekId="+weekId);

					sqlResultIntTemp1 += traningMapper.insertTraningSubjWeekSchExcel(traningSchVO);
					sqlResultInt1 += sqlResultIntTemp1;
				} else if("6".equals(weekCnt) && intWeekCnt6 == 0){
					++intWeekCnt6;
					weekId = traningSubjWeekSchIdGnrService.getNextStringId();
					traningSchVO.setWeekId(weekId);
					System.out.println("##### weekId="+weekId);

					sqlResultIntTemp1 += traningMapper.insertTraningSubjWeekSchExcel(traningSchVO);
					sqlResultInt1 += sqlResultIntTemp1;
				} else if("7".equals(weekCnt) && intWeekCnt7 == 0){
					++intWeekCnt7;
					weekId = traningSubjWeekSchIdGnrService.getNextStringId();
					traningSchVO.setWeekId(weekId);
					System.out.println("##### weekId="+weekId);

					sqlResultIntTemp1 += traningMapper.insertTraningSubjWeekSchExcel(traningSchVO);
					sqlResultInt1 += sqlResultIntTemp1;
				} else if("8".equals(weekCnt) && intWeekCnt8 == 0){
					++intWeekCnt8;
					weekId = traningSubjWeekSchIdGnrService.getNextStringId();
					traningSchVO.setWeekId(weekId);
					System.out.println("##### weekId="+weekId);

					sqlResultIntTemp1 += traningMapper.insertTraningSubjWeekSchExcel(traningSchVO);
					sqlResultInt1 += sqlResultIntTemp1;
				} else if("9".equals(weekCnt) && intWeekCnt9 == 0){
					++intWeekCnt9;
					weekId = traningSubjWeekSchIdGnrService.getNextStringId();
					traningSchVO.setWeekId(weekId);
					System.out.println("##### weekId="+weekId);

					sqlResultIntTemp1 += traningMapper.insertTraningSubjWeekSchExcel(traningSchVO);
					sqlResultInt1 += sqlResultIntTemp1;
				} else if("10".equals(weekCnt) && intWeekCnt10 == 0){
					++intWeekCnt10;
					weekId = traningSubjWeekSchIdGnrService.getNextStringId();
					traningSchVO.setWeekId(weekId);
					System.out.println("##### weekId="+weekId);

					sqlResultIntTemp1 += traningMapper.insertTraningSubjWeekSchExcel(traningSchVO);
					sqlResultInt1 += sqlResultIntTemp1;
				} else if("11".equals(weekCnt) && intWeekCnt11 == 0){
					++intWeekCnt11;
					weekId = traningSubjWeekSchIdGnrService.getNextStringId();
					traningSchVO.setWeekId(weekId);
					System.out.println("##### weekId="+weekId);

					sqlResultIntTemp1 += traningMapper.insertTraningSubjWeekSchExcel(traningSchVO);
					sqlResultInt1 += sqlResultIntTemp1;
				} else if("12".equals(weekCnt) && intWeekCnt12 == 0){
					++intWeekCnt12;
					weekId = traningSubjWeekSchIdGnrService.getNextStringId();
					traningSchVO.setWeekId(weekId);
					System.out.println("##### weekId="+weekId);

					sqlResultIntTemp1 += traningMapper.insertTraningSubjWeekSchExcel(traningSchVO);
					sqlResultInt1 += sqlResultIntTemp1;
				} else if("13".equals(weekCnt) && intWeekCnt13 == 0){
					++intWeekCnt13;
					weekId = traningSubjWeekSchIdGnrService.getNextStringId();
					traningSchVO.setWeekId(weekId);
					System.out.println("##### weekId="+weekId);

					sqlResultIntTemp1 += traningMapper.insertTraningSubjWeekSchExcel(traningSchVO);
					sqlResultInt1 += sqlResultIntTemp1;
				} else if("14".equals(weekCnt) && intWeekCnt14 == 0){
					++intWeekCnt14;
					weekId = traningSubjWeekSchIdGnrService.getNextStringId();
					traningSchVO.setWeekId(weekId);
					System.out.println("##### weekId="+weekId);

					sqlResultIntTemp1 += traningMapper.insertTraningSubjWeekSchExcel(traningSchVO);
					sqlResultInt1 += sqlResultIntTemp1;
				} else if("15".equals(weekCnt) && intWeekCnt15 == 0){
					++intWeekCnt15;
					weekId = traningSubjWeekSchIdGnrService.getNextStringId();
					traningSchVO.setWeekId(weekId);
					System.out.println("##### weekId="+weekId);

					sqlResultIntTemp1 += traningMapper.insertTraningSubjWeekSchExcel(traningSchVO);
					sqlResultInt1 += sqlResultIntTemp1;
				}

				if(sqlResultInt1 > 0){
					String timeId = "";
					timeId = traningSubjWeekTimeSchIdGnrService.getNextStringId();
					System.out.println("##### timeId="+timeId);
					traningSchVO.setTimeId(timeId);

					//훈련시간표-주차별 시간 저장
					sqlResultInt2 = traningMapper.insertTraningSubjWeekTimeSchExcel(traningSchVO);
					if(sqlResultInt2 > 0){
						//훈련시간표-주차별 NCS단위 생성 로직은 아우누리에서 연계를 통해 받아오게 되어 있어서 주석처리함.
						//훈련시간표-주차별 NCS요소 생성 로직은 아우누리에서 연계를 통해 받아오게 되어 있어서 주석처리함.

						/*String ncsUnitId = "";
						ncsUnitId = traningSubNcsUnitSchIdGnrService.getNextStringId();
						System.out.println("##### ncsUnitId="+ncsUnitId);
						traningSchVO.setNcsUnitId(ncsUnitId);

						//훈련시간표-주차별 NCS단위 저장
						sqlResultInt3 = traningMapper.insertTraningSubjWeekNcsUnitSchExcel(traningSchVO);
						if(sqlResultInt3 > 0){
							String ncsElemId = "";
							ncsElemId = traningSubNcsElemSchIdGnrService.getNextStringId();
							System.out.println("##### ncsElemId="+ncsElemId);
							traningSchVO.setNcsElemId(ncsElemId);

							//훈련시간표-주차별 NCS요소 저장
							sqlResultInt4 = traningMapper.insertTraningSubjWeekNcsElemSchExcel(traningSchVO);
							if(sqlResultInt4 == 0){
								sqlResult = "FAIL";
							}
						}else{
							sqlResult = "FAIL";
						}*/
					}else{
						sqlResult = "FAIL";
					}
				}else{
					sqlResult = "FAIL";
				}
			}
		}

		System.out.println("##### sqlResult="+sqlResult);

		return sqlResult;
	}

	@Override
	public int updateTraningProcessMappingInfoList(TraningProcessMappingVO traningProcessMappingVO) throws Exception {

		int sqlResultInt = 0;

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(traningProcessMappingVO);

		sqlResultInt = traningMapper.updateTraningProcessMappingInfoList(traningProcessMappingVO);

		// 교과별 훈련방식을 가져옴 2017.04.21 csh
		String subjectTraningType =  traningMapper.getSubjectTraningType(traningProcessMappingVO);

		// 교과별 훈련방식이 OJT 일 경우만 회사정보 업데이트
		if("OJT".equals(subjectTraningType)){
			traningMapper.updateMemberCompanyId(traningProcessMappingVO);
		}

		return sqlResultInt;
	}

	@Override
	public int updateTraningSubjWeekSchFileId(TraningScheduleVO traningScheduleVO) throws Exception {

		int sqlResultInt = 0;

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(traningScheduleVO);

		sqlResultInt = traningMapper.updateTraningSubjWeekSchFileId(traningScheduleVO);

		return sqlResultInt;
	}

	@Override
	public int deleteTraningProcessInfoList(TraningVO traningVO) throws Exception {
		int sqlResultInt = 0;

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(traningVO);

		sqlResultInt = traningMapper.deleteTraningProcessInfoList(traningVO);
		return sqlResultInt;
	}

	@Override
	public int deleteTraningProcessMappingInfoList(TraningVO traningVO) throws Exception {
		int sqlResultInt = 0;

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(traningVO);

		sqlResultInt = traningMapper.deleteTraningProcessMappingInfoList(traningVO);
		return sqlResultInt;
	}
	
	@Override
	public int deleteTraningProcessMappingInfoSubjList(TraningProcessMappingVO traningProcessMappingVO) throws Exception {
		int sqlResultInt = 0;

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(traningProcessMappingVO);

		sqlResultInt = traningMapper.deleteTraningProcessMappingInfoSubjList(traningProcessMappingVO);
		return sqlResultInt;
	}
	
	@Override
	public String getTraningScheduleStatus(TraningScheduleVO traningScheduleVO) throws Exception {
		String data = traningMapper.getTraningScheduleStatus(traningScheduleVO);
		return data;
	}
	
	@Override
	public int deleteTraningSchedule(TraningScheduleVO traningScheduleVO) throws Exception {
		int sqlResultInt = 0;

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(traningScheduleVO);

		sqlResultInt += traningMapper.deletetTraningSchedule(traningScheduleVO);
		sqlResultInt += traningMapper.deletetTraningWeekTimeSchedule(traningScheduleVO);
		
		return sqlResultInt;
	}
	
	
	
	
	
	
}
