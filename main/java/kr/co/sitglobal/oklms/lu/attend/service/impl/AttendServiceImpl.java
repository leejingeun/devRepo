
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
package kr.co.sitglobal.oklms.lu.attend.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.comm.util.StringUtil;
import kr.co.sitglobal.oklms.lu.attend.service.AttendService;
import kr.co.sitglobal.oklms.lu.attend.vo.AttendVO;
import kr.co.sitglobal.oklms.lu.online.service.OnlineTraningService;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningSchVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningWeekVO;
import kr.co.sitglobal.oklms.lu.online.web.OnlineTraningController;
import kr.co.sitglobal.oklms.lu.traning.service.TraningService;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningProcessSearchVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

 /**
 * 프로토타입 게시판 CRUD 비지니스 로직을 구현하는 클레스.
 * @author 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
@Service("AttendService")
public class AttendServiceImpl extends EgovAbstractServiceImpl implements AttendService{
	
	private static final Logger LOG = LoggerFactory.getLogger(AttendServiceImpl.class);
	
	/** ID Generation */
    @Resource(name="progressAttendIdGnrService")
    private EgovIdGnrService progressAttendIdGnrService;
    
    /** ID Generation */
    @Resource(name="lessonTimeIdGnrService")
    private EgovIdGnrService lessonTimeIdGnrService;
    
    /** ID Generation */
    @Resource(name="lessonPageIdGnrService")
    private EgovIdGnrService lessonPageIdGnrService;
    
    
    @Resource(name = "AttendMapper")
    private AttendMapper attendMapper;
    
	
	public AttendVO insertAttendInfo(AttendVO attendVO) throws Exception {
		
		LOG.debug("======================================  11 attendVO.getPageInfo() : "+attendVO.getPageInfo());
		
		// TODO Auto-generated method stub
		
		String prevProgressId = "";
		
		if(attendVO.getLinkContentYn().equals("Y")){
			prevProgressId = attendMapper.getCmsProgressId(attendVO);
		} else {
			prevProgressId = attendMapper.getProgressId(attendVO);
		}
		
		//System.out.println("====================== prevProgressId : "+prevProgressId);
		
		LOG.debug("============================== prevProgressId : "+prevProgressId);
		
		int iResult = 0;
		// 주차 > 차시에 해당하는 출석아이디가 없을 경우
		if(!StringUtils.hasText(prevProgressId)){
			String progressId = progressAttendIdGnrService.getNextStringId();
			
			LOG.debug("============================== progressId : "+progressId);
			
			// 초기 시간진도율, 페이지진도율은 0으로 초기화
			attendVO.setProgressAttendId(progressId);
			attendVO.setTimeProgressRatio("0");
			
			// 페이지 진도방식이 P : 페이지 , H : 페이지+시간일 경우 페이지 진도율 100% 처리
			if("P".equals(attendVO.getProgressAttendTypeCode()) || "H".equals(attendVO.getProgressAttendTypeCode())){
				attendVO.setPageProgressRatio("100");
			} else {
				attendVO.setPageProgressRatio("0");
			}
			
			// 진도율 및 출석율 등록
			if(attendVO.getLinkContentYn().equals("Y")){
				attendMapper.insertCmsProgress(attendVO);
			} else {
				attendMapper.insertProgress(attendVO);
			}
			
			prevProgressId = progressId;
		} else {
			attendVO.setProgressAttendId(prevProgressId);
		}
		
		 // 수강 시작 시간 정보 추가
        String lessonTimeId = lessonTimeIdGnrService.getNextStringId();
        attendVO.setProgressTimeId(lessonTimeId);
        
        // 현재시간을 가져옴
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss"); 
        String vDateTime = formatter.format(new java.util.Date());
        
        attendVO.setvDateTime(vDateTime);
        
        iResult = attendMapper.getTimeCount(attendVO);
        
        if(iResult == 0){
        	attendMapper.insertTime(attendVO);
        } else {
        	attendMapper.updateTime(attendVO);
        }
        
        iResult = attendMapper.getTimeMaxCount(attendVO);
        
        if(iResult == 0){
        	attendMapper.insertTimeMax(attendVO);
        } else {
        	attendMapper.updateTimeMax(attendVO);
        }
        
        // 페이지 진도방식이 P : 페이지 , H : 페이지+시간일 경우에만 페이지 정보 등록
        if("P".equals(attendVO.getProgressAttendTypeCode()) || "H".equals(attendVO.getProgressAttendTypeCode())){
        	// 페이지 정보 확인
        	
        	LOG.debug("======================================  22 attendVO.getPageInfo() : "+attendVO.getPageInfo());
    		iResult = attendMapper.getPageProgressCount(attendVO);
    		if(iResult == 0){
    			
    			LOG.debug("======================================  33 attendVO.getPageInfo() : "+attendVO.getPageInfo());
    			String lessonPageId = lessonPageIdGnrService.getNextStringId();
    			attendVO.setPageProgressAttendId(lessonPageId);
    			attendMapper.insertPageProgress(attendVO);
    		}
    		
    		if(StringUtils.hasText(attendVO.getProgressAttendId())){
    			iResult = attendMapper.getPageProgressSumCount(attendVO);
    			
    			AttendVO pageVO = attendMapper.getPageProgressInfo(attendVO);
    			
    			attendVO.setProgressSum(pageVO.getProgressSum());
    			attendVO.setProgressCount(pageVO.getProgressCount());
    			
    			if(iResult == 0 ){
    				attendMapper.insertPageProgressSum(attendVO);
    			} else {
    				attendMapper.updatePageProgressSum(attendVO);
    			}
    		}
        }
        
        AttendVO aVO = new AttendVO();
        
        aVO.setProgressAttendId(prevProgressId);
        aVO.setProgressTimeId(lessonTimeId);
		
		return aVO;
	}
	
	
	public AttendVO updateAttend(AttendVO attendVO) throws Exception {
		int iResult = 0;
		
		// 현재시간을 가져옴
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss"); 
		String vDateTime = formatter.format(new java.util.Date());
    
		attendVO.setvDateTime(vDateTime);
    
		iResult += attendMapper.updateTime(attendVO);
		iResult += attendMapper.updateTimeMax(attendVO);
		
		if("T".equals(attendVO.getProgressAttendTypeCode()) || "H".equals(attendVO.getProgressAttendTypeCode())){
			// 시간대비 진도율 업데이트
			int timeProgressRatio = 0;
			if(attendVO.getLinkContentYn().equals("Y")){
				timeProgressRatio = attendMapper.getCmsTimeProgress(attendVO);
			} else {
				timeProgressRatio = attendMapper.getTimeProgress(attendVO);
			}
			
			if(timeProgressRatio >= 100) timeProgressRatio = 100;
			
			attendVO.setTimeProgressRatio(String.valueOf(timeProgressRatio));
			
			iResult += attendMapper.updateTimeProgress(attendVO);
		}
		
		// 주차별 진도율을 가져옴
		String weekProgressRate = attendMapper.getWeekProgressRate(attendVO);
		attendVO.setWeekProgressRate(weekProgressRate);
		//AttendVO aVO = new AttendVO();

		return attendVO;
    }
	
	
	public String getCmsLessonItemProgress(AttendVO attendVO) throws Exception {
		String result = attendMapper.getCmsLessonItemProgress(attendVO);
		return result;
	}
	
	public String getLessonProgress(AttendVO attendVO) throws Exception {
		String result = attendMapper.getLessonProgress(attendVO);
		return result;
	}
	
	public String getLessonScheduleProgress(AttendVO attendVO) throws Exception {
		String result = attendMapper.getLessonScheduleProgress(attendVO);
		return result;
	}
	
}
	
	
	
