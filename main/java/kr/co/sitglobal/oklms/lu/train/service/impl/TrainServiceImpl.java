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
package kr.co.sitglobal.oklms.lu.train.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.comm.util.Config;
import kr.co.sitglobal.oklms.comm.util.ExcelData;
import kr.co.sitglobal.oklms.comm.util.ExcelDataSet;
import kr.co.sitglobal.oklms.comm.util.ExcelHandler;
//import kr.co.sitglobal.oklms.comm.util.FileUploadUtil;
//import kr.co.sitglobal.oklms.comm.util.UUID;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
//import kr.co.sitglobal.oklms.commbiz.util.IOUtills;
//import kr.co.sitglobal.oklms.lu.mail.service.MailService;
//import kr.co.sitglobal.oklms.lu.mail.vo.MailVO;
import kr.co.sitglobal.oklms.lu.member.service.MemberStdService;
import kr.co.sitglobal.oklms.lu.member.vo.ExcelMemberStdVO;
import kr.co.sitglobal.oklms.lu.member.vo.MemberStdVO;
import kr.co.sitglobal.oklms.lu.train.service.TrainService;
import kr.co.sitglobal.oklms.lu.train.vo.TrainVO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
//import org.apache.commons.beanutils.BeanUtils;
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
@Service("TrainService")
public class TrainServiceImpl extends EgovAbstractServiceImpl implements TrainService {

	/** ID Generation */
    /*@Resource(name="memberIdGnrService")
    private EgovIdGnrService memberIdGnrService;*/
    
/*    @Resource(name = "MemberStdMapper")
    private MemberStdMapper memberStdMapper;*/
    
    /*@Resource(name = "mailService")
    private MailService mailService;*/
	
	@Resource(name = "TrainMapper")
    private TrainMapper trainMapper;

	@Override
	public List<TrainVO> listTrain(TrainVO trainVO) throws Exception {

		List<TrainVO> data = trainMapper.listTrain(trainVO);
		return data;
	}
}
