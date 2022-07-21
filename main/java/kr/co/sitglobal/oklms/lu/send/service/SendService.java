package kr.co.sitglobal.oklms.lu.send.service;

import java.util.List;

import kr.co.sitglobal.oklms.lu.activity.vo.ActivityVO;
import kr.co.sitglobal.oklms.lu.activity.vo.MemberVO;
import kr.co.sitglobal.oklms.lu.activity.vo.SubjweekStdVO;
import kr.co.sitglobal.oklms.lu.send.vo.SendVO;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Transactional(rollbackFor=Exception.class)
public interface SendService {

	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * ReportVO
	 */
	List<SendVO> listSendCdp(SendVO sendVO) throws Exception;
	
	
}
