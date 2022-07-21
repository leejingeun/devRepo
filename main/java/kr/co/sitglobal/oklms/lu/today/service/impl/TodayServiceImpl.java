package kr.co.sitglobal.oklms.lu.today.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import kr.co.sitglobal.oklms.lu.today.service.TodayService;
import kr.co.sitglobal.oklms.lu.today.vo.TodayVO;
import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl; 

@Transactional(rollbackFor=Exception.class)
@Service("TodayService")
public class TodayServiceImpl extends EgovAbstractServiceImpl implements TodayService {

	@Resource(name = "TodayMapper")
    private TodayMapper todayMapper;

	@Override
	public List<BoardVO> listQnaStd(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return todayMapper.listQnaStd(boardVO);
	}
	@Override
	public List<BoardVO> listQnaCot(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return todayMapper.listQnaCot(boardVO);
	}
	@Override
	public List<BoardVO> listQnaCdp(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return todayMapper.listQnaCdp(boardVO);
	}	
	@Override
	public List<TodayVO> listTraningNotePrt(TodayVO todayVO) throws Exception {
		// TODO Auto-generated method stub
		return todayMapper.listTraningNotePrt(todayVO);
	}

	@Override
	public List<TodayVO> listTraningNoteCot(TodayVO todayVO) throws Exception {
		// TODO Auto-generated method stub
		return todayMapper.listTraningNoteCot(todayVO);
	}

	@Override
	public List<TodayVO> listActivityNotePrt(TodayVO todayVO) throws Exception {
		// TODO Auto-generated method stub
		return todayMapper.listActivityNotePrt(todayVO);
	}

	@Override
	public List<TodayVO> listActivityNoteCot(TodayVO todayVO) throws Exception {
		// TODO Auto-generated method stub
		return todayMapper.listActivityNoteCot(todayVO);
	}

	@Override
	public List<TodayVO> listReportSubmitStd(TodayVO todayVO) throws Exception {
		// TODO Auto-generated method stub
		return todayMapper.listReportSubmitStd(todayVO);
	}

	@Override
	public List<TodayVO> listReportSubmitPrt(TodayVO todayVO) throws Exception {
		// TODO Auto-generated method stub
		return todayMapper.listReportSubmitPrt(todayVO);
	}

	@Override
	public List<TodayVO> listTeamProjectSubmitStd(TodayVO todayVO)	throws Exception {
		// TODO Auto-generated method stub
		return todayMapper.listTeamProjectSubmitStd(todayVO);
	}

	@Override
	public List<TodayVO> listTeamProjectSubmitPrt(TodayVO todayVO)throws Exception {
		// TODO Auto-generated method stub
		return todayMapper.listTeamProjectSubmitPrt(todayVO);
	}

	@Override
	public List<TodayVO> listDiscussStd(TodayVO todayVO) throws Exception {
		// TODO Auto-generated method stub
		return  todayMapper.listDiscussStd(todayVO);
	}

	@Override
	public List<TodayVO> listWorkCertStd(TodayVO todayVO) throws Exception {
		// TODO Auto-generated method stub
		return todayMapper.listWorkCertStd(todayVO);
	}

	@Override
	public List<TodayVO> listWorkCertCdp(TodayVO todayVO) throws Exception {
		// TODO Auto-generated method stub
		return todayMapper.listWorkCertCdp(todayVO);
	}

	@Override
	public List<TodayVO> listComMemberCcm(TodayVO todayVO) throws Exception {
		// TODO Auto-generated method stub
		return todayMapper.listComMemberCcm(todayVO);
	}

	@Override
	public List<TodayVO> listInterviewCot(TodayVO todayVO) throws Exception {
		// TODO Auto-generated method stub
		return  todayMapper.listInterviewCot(todayVO);
	}   
	@Override
	public List<TodayVO> listInterviewCcn(TodayVO todayVO) throws Exception {
		// TODO Auto-generated method stub
		return  todayMapper.listInterviewCcn(todayVO);
	}
	@Override
	public List<TodayVO> listDiscussCdp(TodayVO todayVO) throws Exception {
		// TODO Auto-generated method stub
		return todayMapper.listDiscussCdp(todayVO);
	}
	@Override
	public List<TodayVO> listOnlineStd(TodayVO todayVO) throws Exception {
		// TODO Auto-generated method stub
		return todayMapper.listOnlineStd(todayVO);
	}
	@Override
	public List<TodayVO> listOnlineCdp(TodayVO todayVO) throws Exception {
		// TODO Auto-generated method stub
		return todayMapper.listOnlineCdp(todayVO);
	}
	@Override
	public List<TodayVO> listActivityHrd(TodayVO todayVO) throws Exception {
		// TODO Auto-generated method stub
		return todayMapper.listActivityHrd(todayVO);
	}
	@Override
	public List<TodayVO> listWeekTraningNoteHrd(TodayVO todayVO) throws Exception {
		// TODO Auto-generated method stub
		return todayMapper.listWeekTraningNoteHrd(todayVO);
	}
	@Override
	public List<TodayVO> listWeekTraningNoteCompany(TodayVO todayVO)throws Exception {
		// TODO Auto-generated method stub
		return todayMapper.listWeekTraningNoteCompany(todayVO);
	}   

}
