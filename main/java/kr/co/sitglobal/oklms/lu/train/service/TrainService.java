
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
package kr.co.sitglobal.oklms.lu.train.service;

import java.util.List;

import kr.co.sitglobal.oklms.lu.train.vo.TrainVO;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

 /**
 * 클래스에 대한 내용을 작성한다.
 * @author 이진근
 * @since 2017. 01. 06.
 */
@Transactional(rollbackFor=Exception.class)
public interface TrainService {

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * List<TrainVO>
	 */
	List<TrainVO> listTrain(TrainVO trainVO) throws Exception;
	
	
}
