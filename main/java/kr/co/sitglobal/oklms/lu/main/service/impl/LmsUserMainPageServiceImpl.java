/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2017. 01. 06.         First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.lu.main.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.comm.util.Config;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.lu.main.service.LmsUserMainPageService;
import kr.co.sitglobal.oklms.lu.main.vo.LmsUserMainPageVO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

 /**
 * 프로토타입 게시판 CRUD 비지니스 로직을 구현하는 클레스.
 * @author 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
@Service("LmsUserMainPageService")
public class LmsUserMainPageServiceImpl extends EgovAbstractServiceImpl implements LmsUserMainPageService {

	/** ID Generation */
    /*@Resource(name="memberIdGnrService")
    private EgovIdGnrService memberIdGnrService;*/

/*    @Resource(name = "MemberStdMapper")
    private MemberStdMapper memberStdMapper;*/

    /*@Resource(name = "mailService")
    private MailService mailService;*/

	@Resource(name = "LmsUserMainPageMapper")
    private LmsUserMainPageMapper lmsUserMainPageMapper;
	
	
	
	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * List<LmsUserMainPageVO>
	 */
	
	@Override
	public List<LmsUserMainPageVO> listLmsUserMainPageStdSchedule(LmsUserMainPageVO lmsUserMainPageVO) throws Exception {
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(lmsUserMainPageVO);
		// TODO Auto-generated method stub
		return lmsUserMainPageMapper.listLmsUserMainPageStdSchedule(lmsUserMainPageVO);
	}
	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * List<LmsUserMainPageVO>
	 */
	@Override
	public List<LmsUserMainPageVO> listLmsUserMainPageTotalCnt(LmsUserMainPageVO lmsUserMainPageVO) throws Exception {

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(lmsUserMainPageVO);
		List<LmsUserMainPageVO> data = null;

		if(lmsUserMainPageVO.getSessionMemType().equals("STD")){
			data = lmsUserMainPageMapper.listLmsUserMainPageStdTotalCnt(lmsUserMainPageVO);
		} else if(lmsUserMainPageVO.getSessionMemType().equals("PRT")){
			data = lmsUserMainPageMapper.listLmsUserMainPageTchTotalCnt(lmsUserMainPageVO);
		} else if(lmsUserMainPageVO.getSessionMemType().equals("ATT")){
			data = lmsUserMainPageMapper.listLmsUserMainPageSubTchTotalCnt(lmsUserMainPageVO);
		} else if(lmsUserMainPageVO.getSessionMemType().equals("COT")){
			data = lmsUserMainPageMapper.listLmsUserMainPageSubTchTotalCnt(lmsUserMainPageVO);
		} else if(lmsUserMainPageVO.getSessionMemType().equals("CCM")){
			data = lmsUserMainPageMapper.listLmsUserMainPageCcmTotalCnt(lmsUserMainPageVO);
		} else if(lmsUserMainPageVO.getSessionMemType().equals("CCN")){
			data = lmsUserMainPageMapper.listLmsUserMainPageCcnTotalCnt(lmsUserMainPageVO);
		} else {
			data = lmsUserMainPageMapper.listLmsUserMainPageCdpTotalCnt(lmsUserMainPageVO);
		}

		return data;
	}

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * List<LmsUserMainPageVO>
	 */
	@Override
	public List<LmsUserMainPageVO> listLmsUserMainPageTodayCnt(LmsUserMainPageVO lmsUserMainPageVO) throws Exception {

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(lmsUserMainPageVO);
		List<LmsUserMainPageVO> data = null;

		if(lmsUserMainPageVO.getSessionMemType().equals("STD")){
			data = lmsUserMainPageMapper.listLmsUserMainPageStdTodayCnt(lmsUserMainPageVO);
		} else if(lmsUserMainPageVO.getSessionMemType().equals("PRT")){
			data = lmsUserMainPageMapper.listLmsUserMainPageTchTodayCnt(lmsUserMainPageVO);
		} else if(lmsUserMainPageVO.getSessionMemType().equals("COT")){
			data = lmsUserMainPageMapper.listLmsUserMainPageSubTchTodayCnt(lmsUserMainPageVO);
		} else if(lmsUserMainPageVO.getSessionMemType().equals("CCM")){
			data = lmsUserMainPageMapper.listLmsUserMainPageCcmTodayCnt(lmsUserMainPageVO);
		} else if(lmsUserMainPageVO.getSessionMemType().equals("CCN")){
			data = lmsUserMainPageMapper.listLmsUserMainPageCcnTodayCnt(lmsUserMainPageVO);
		} else {
			data = lmsUserMainPageMapper.listLmsUserMainPageCdpTodayCnt(lmsUserMainPageVO);
		}

		return data;
	}

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * List<LmsUserMainPageVO>
	 */
	@Override
	public List<LmsUserMainPageVO> listLectureS​chedule(LmsUserMainPageVO lmsUserMainPageVO) throws Exception {

		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(lmsUserMainPageVO);
		List<LmsUserMainPageVO> data = null;

		if(lmsUserMainPageVO.getSessionMemType().equals("STD")){
	//		data = lmsUserMainPageMapper.listLectureS​cheduleStd(lmsUserMainPageVO);
		}  else if(lmsUserMainPageVO.getSessionMemType().equals("PRT")){
	//		data = lmsUserMainPageMapper.listLectureOjtS​chedulePrt(lmsUserMainPageVO);
		} else if(lmsUserMainPageVO.getSessionMemType().equals("ATT")){
			
		} else if(lmsUserMainPageVO.getSessionMemType().equals("COT")){
		
		} else if(lmsUserMainPageVO.getSessionMemType().equals("CCM")){
		
		} else if(lmsUserMainPageVO.getSessionMemType().equals("CCN")){
			
		} else {
		
		}

		return data;
	}
	/**
	 * 학습관리 목록은 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * List<LmsUserMainPageVO>
	 */	
	@Override
	public List<LmsUserMainPageVO> listLmsUserMainPageStatusCnt( LmsUserMainPageVO lmsUserMainPageVO) throws Exception {
		// TODO Auto-generated method stub
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(lmsUserMainPageVO);
		List<LmsUserMainPageVO> data = null;

		if(lmsUserMainPageVO.getSessionMemType().equals("STD")){
			data = lmsUserMainPageMapper.listLmsUserMainPageStdStatusCnt(lmsUserMainPageVO);
		}else if(lmsUserMainPageVO.getSessionMemType().equals("COT")){
			data = lmsUserMainPageMapper.listLmsUserMainPageCotStatusCnt(lmsUserMainPageVO);
		}else if(lmsUserMainPageVO.getSessionMemType().equals("PRT")){
			data = lmsUserMainPageMapper.listLmsUserMainPagePrtStatusCnt(lmsUserMainPageVO);
		}
		 
		return data;
	}
	
	@Override
	public List<LmsUserMainPageVO> listLmsUserMainPageCcnCnt(LmsUserMainPageVO lmsUserMainPageVO) throws Exception {
		// TODO Auto-generated method stub
		return lmsUserMainPageMapper.listLmsUserMainPageCcnCnt(lmsUserMainPageVO);
	}
	@Override
	public List<LmsUserMainPageVO> listLmsUserMainPageCcnSchedule(LmsUserMainPageVO lmsUserMainPageVO) throws Exception {
		// TODO Auto-generated method stub
		return lmsUserMainPageMapper.listLmsUserMainPageCcnSchedule(lmsUserMainPageVO);
	}
	@Override
	public List<LmsUserMainPageVO> listLmsUserMainPageCotSchedule(LmsUserMainPageVO lmsUserMainPageVO) throws Exception {
		// TODO Auto-generated method stub
		return lmsUserMainPageMapper.listLmsUserMainPageCotSchedule(lmsUserMainPageVO);
	}
	@Override
	public List<LmsUserMainPageVO> listLmsUserMainPagePrtSchedule(	LmsUserMainPageVO lmsUserMainPageVO) throws Exception {
		// TODO Auto-generated method stub
		return lmsUserMainPageMapper.listLmsUserMainPagePrtSchedule(lmsUserMainPageVO);
	}
	@Override
	public List<LmsUserMainPageVO> listLmsUserMainPageCcmCnt(LmsUserMainPageVO lmsUserMainPageVO) throws Exception {
		// TODO Auto-generated method stub
		return  lmsUserMainPageMapper.listLmsUserMainPageCcmCnt(lmsUserMainPageVO);
	}
	@Override
	public List<LmsUserMainPageVO> listLmsUserMainPageCcmSchedule(LmsUserMainPageVO lmsUserMainPageVO) throws Exception {
		// TODO Auto-generated method stub
		return lmsUserMainPageMapper.listLmsUserMainPageCcmSchedule(lmsUserMainPageVO);
	}
	@Override
	public List<LmsUserMainPageVO> listLmsUserMainPagePrtStatusPrtCnt(LmsUserMainPageVO lmsUserMainPageVO) throws Exception {
		// TODO Auto-generated method stub
		return lmsUserMainPageMapper.listLmsUserMainPagePrtStatusPrtCnt(lmsUserMainPageVO);
	}

 
}
