package kr.co.sitglobal.oklms.lu.traningstatus.service.impl;
import java.util.List;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.lu.traningstatus.service.TraningStatusService;
import kr.co.sitglobal.oklms.lu.traningstatus.vo.TraningStatusVO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl; 


@Transactional(rollbackFor=Exception.class)
@Service("TraningStatusService")
public class TraningStatusServiceImpl extends EgovAbstractServiceImpl implements TraningStatusService {
	
	@Resource(name = "TraningStatusMapper")
    private TraningStatusMapper traningStatusMapper;
	
	@Override
	public TraningStatusVO getProgress(TraningStatusVO traningStatusVO)	throws Exception {
		// TODO Auto-generated method stub
		return traningStatusMapper.getProgress(traningStatusVO);
	}

	@Override
	public List<TraningStatusVO> listTraningStatus(	TraningStatusVO traningStatusVO) throws Exception {
		// TODO Auto-generated method stub
		 List<TraningStatusVO> data;
		if(traningStatusVO.getSessionMemType().equals("PRT")){
			data = traningStatusMapper.listTraningStatusPrt(traningStatusVO);
		} else if(traningStatusVO.getSessionMemType().equals("COT")){
			data = traningStatusMapper.listTraningStatusCot(traningStatusVO);
		} else if(traningStatusVO.getSessionMemType().equals("CCM")){
			data = traningStatusMapper.listTraningStatusCcm(traningStatusVO);
		} else {
			data = traningStatusMapper.listTraningStatus(traningStatusVO);
		}
		return data;
	}

	@Override
	public List<TraningStatusVO> listTraningStatusDetail(TraningStatusVO traningStatusVO) throws Exception {
		// TODO Auto-generated method stub
		List<TraningStatusVO> data;
		if(traningStatusVO.getSessionMemType().equals("PRT")){
			data = traningStatusMapper.listTraningStatusDetailPrt(traningStatusVO);
		} else if(traningStatusVO.getSessionMemType().equals("COT")){
			data = traningStatusMapper.listTraningStatusDetailCot(traningStatusVO);
		} else if(traningStatusVO.getSessionMemType().equals("CCM")){
			data = traningStatusMapper.listTraningStatusDetailCcm(traningStatusVO);
		} else {
			data = traningStatusMapper.listTraningStatusDetail(traningStatusVO);
		}
		return data;
	}

	@Override
	public List<TraningStatusVO> popupAttendListOff( TraningStatusVO traningStatusVO) throws Exception {
		// TODO Auto-generated method stub
		return  traningStatusMapper.popupAttendListOff(traningStatusVO);
	}
	
	@Override
	public List<TraningStatusVO> popupAttendListOnlineOff( TraningStatusVO traningStatusVO) throws Exception {
		// TODO Auto-generated method stub
		return  traningStatusMapper.popupAttendListOnlineOff(traningStatusVO);
	}
	@Override
	public List<TraningStatusVO> listOffTraningStatus(TraningStatusVO traningStatusVO) throws Exception {
		// TODO Auto-generated method stub
		return traningStatusMapper.listOffTraningStatus(traningStatusVO) ;
	}

	@Override
	public List<TraningStatusVO> listOjtTraningStatus(TraningStatusVO traningStatusVO) throws Exception {
		// TODO Auto-generated method stub
		return traningStatusMapper.listOjtTraningStatus(traningStatusVO);
	}

	@Override
	public List<TraningStatusVO> listWeekTraningStatus(	TraningStatusVO traningStatusVO) throws Exception {
		// TODO Auto-generated method stub
		return traningStatusMapper.listWeekTraningStatus(traningStatusVO);
	}

	@Override
	public List<TraningStatusVO> listTraningStatusPrt(	TraningStatusVO traningStatusVO) throws Exception {
		// TODO Auto-generated method stub
		return  traningStatusMapper.listTraningStatusPrt(traningStatusVO);
	}

	@Override
	public List<TraningStatusVO> listTraningStatusDetailPrt(TraningStatusVO traningStatusVO) throws Exception {
		// TODO Auto-generated method stub
		return  traningStatusMapper.listTraningStatusDetailPrt(traningStatusVO);
	}
 
}
