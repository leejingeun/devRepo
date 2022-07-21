package kr.co.sitglobal.oklms.lu.send.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.commbiz.util.AtchFileUtil;
import kr.co.sitglobal.oklms.lu.activity.service.ActivityService;
import kr.co.sitglobal.oklms.lu.activity.vo.ActivityVO; 
import kr.co.sitglobal.oklms.lu.activity.vo.MemberVO;
import kr.co.sitglobal.oklms.lu.activity.vo.SubjweekStdVO;
import kr.co.sitglobal.oklms.lu.grade.service.GradeService;
import kr.co.sitglobal.oklms.lu.send.service.SendService;
import kr.co.sitglobal.oklms.lu.send.vo.SendVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Transactional(rollbackFor=Exception.class)
@Service("sendService")
public class SendServiceImpl extends EgovAbstractServiceImpl implements SendService {

	@Resource(name = "sendMapper")
    private SendMapper sendMapper;

	
	@Override
	public List<SendVO> listSendCdp(SendVO sendVO)
			throws Exception {
		// TODO Auto-generated method stub
		return sendMapper.listSendCdp(sendVO);
	}

}
