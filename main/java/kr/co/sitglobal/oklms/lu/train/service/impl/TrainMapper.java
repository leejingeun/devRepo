
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

import java.util.List;

import kr.co.sitglobal.oklms.lu.train.vo.TrainVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

 /**
 * 프로토타입 게시판 CRUD 쿼리를 마이바티스로 연결하는 클레스.
 * @author 이진근
 * @since 2017. 01. 06.
 */
@Mapper("TrainMapper")
public interface TrainMapper {

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * List<TrainVO>
	 */
	List<TrainVO> listTrain(TrainVO trainVO) throws Exception;
}
