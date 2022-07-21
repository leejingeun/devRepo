package kr.co.sitglobal.oklms.lu.send.service.impl;

import java.util.List;

import kr.co.sitglobal.oklms.lu.activity.vo.ActivityVO; 
import kr.co.sitglobal.oklms.lu.activity.vo.MemberVO;
import kr.co.sitglobal.oklms.lu.activity.vo.SubjweekStdVO;
import kr.co.sitglobal.oklms.lu.send.vo.SendVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("sendMapper")
public interface  SendMapper {

	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * ActivityVO
	 */
	List<SendVO> listSendCdp(SendVO sendVO) throws Exception;
}
