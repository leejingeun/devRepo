package kr.co.sitglobal.aunuri.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.co.sitglobal.aunuri.service.AunuriLinkService;
import kr.co.sitglobal.aunuri.vo.AunuriLinkEvalPlanNcsVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkLessonVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkMemberMappingVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkMemberVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkScheduleVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkSubjectGradeVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkSubjectVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkSubjectWeekNcsVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkSubjectWeekVO;
import kr.co.sitglobal.ifx.service.impl.IfxMapper;
import kr.co.sitglobal.oklms.la.link.service.impl.LinkMapper;
import kr.co.sitglobal.oklms.la.member.service.impl.MemberMapper;
import kr.co.sitglobal.oklms.lu.attend.service.impl.AttendMapper;
import kr.co.sitglobal.oklms.lu.attend.vo.AttendVO;
import kr.co.sitglobal.oklms.lu.member.service.impl.MemberStdMapper;
import kr.co.sitglobal.oklms.lu.online.service.impl.OnlineTraningMapper;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningSchVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.com.cmm.annotation.OracleMapperScan;
import egovframework.com.uss.ion.bnr.service.BannerVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Transactional(rollbackFor=Exception.class)
@Service("aunuriLinkService")
public class AunuriLinkServiceImpl extends EgovAbstractServiceImpl implements AunuriLinkService{

	@Resource(name = "aunuriLinkMapper")
    private AunuriLinkMapper aunuriLinkMapper;
	
    

	@Override
	public List<AunuriLinkMemberVO> listAunuriMember(AunuriLinkMemberVO aunuriLinkMemberVO) throws Exception {
		List<AunuriLinkMemberVO> data = aunuriLinkMapper.listAunuriMember(aunuriLinkMemberVO);
		return data;
	}

	@Override
	public List<AunuriLinkSubjectVO> listAunuriSubject(AunuriLinkSubjectVO aunuriLinkSubjectVO) throws Exception {
		List<AunuriLinkSubjectVO> data = aunuriLinkMapper.listAunuriSubject(aunuriLinkSubjectVO);
		return data;
	}

	@Override
	public List<AunuriLinkLessonVO> listAunuriLesson(AunuriLinkLessonVO aunuriLinkLessonVO) throws Exception {
		List<AunuriLinkLessonVO> data = aunuriLinkMapper.listAunuriLesson(aunuriLinkLessonVO);
		return data;
	}

	@Override
	public List<AunuriLinkMemberMappingVO> listAunuriIns(AunuriLinkMemberMappingVO aunuriLinkMemberMappingVO) throws Exception {
		List<AunuriLinkMemberMappingVO> data = aunuriLinkMapper.listAunuriIns(aunuriLinkMemberMappingVO);
		return data;
	}

	@Override
	public List<AunuriLinkScheduleVO> listAunuriHaksaSchedule(AunuriLinkScheduleVO aunuriLinkScheduleVO) throws Exception {
		List<AunuriLinkScheduleVO> data = aunuriLinkMapper.listAunuriHaksaSchedule(aunuriLinkScheduleVO);
		return data;
	}


	@Override
	public AunuriLinkSubjectWeekVO getAunuriWeekLessonInfo(AunuriLinkSubjectWeekVO aunuriLinkSubjectWeekVO) throws Exception {
		AunuriLinkSubjectWeekVO data = aunuriLinkMapper.getAunuriWeekLessonInfo(aunuriLinkSubjectWeekVO);
		return data;
	}


	@Override
	public AunuriLinkSubjectWeekVO getAunuriWeekTime(AunuriLinkSubjectWeekVO aunuriLinkSubjectWeekVO) throws Exception {
		AunuriLinkSubjectWeekVO data = aunuriLinkMapper.getAunuriWeekTime(aunuriLinkSubjectWeekVO);
		return data;
	}

	@Override
	public AunuriLinkSubjectWeekNcsVO getAunuriWeekNcsUnit(AunuriLinkSubjectWeekNcsVO aunuriLinkSubjectWeekNcsVO) throws Exception {
		AunuriLinkSubjectWeekNcsVO data = aunuriLinkMapper.getAunuriWeekNcsUnit(aunuriLinkSubjectWeekNcsVO);
		return data;
	}

	@Override
	public List<AunuriLinkSubjectWeekNcsVO> listAunuriWeekNcsElem(AunuriLinkSubjectWeekNcsVO aunuriLinkSubjectWeekNcsVO) throws Exception {
		List<AunuriLinkSubjectWeekNcsVO> data = aunuriLinkMapper.listAunuriWeekNcsElem(aunuriLinkSubjectWeekNcsVO);
		return data;
	}


	@Override
	public AunuriLinkMemberVO getAunuriUserImage(AunuriLinkMemberVO aunuriLinkMemberVO) throws Exception {
		AunuriLinkMemberVO data = aunuriLinkMapper.getAunuriUserImage(aunuriLinkMemberVO);
		return data;
	}

	@Override
	public List<AunuriLinkEvalPlanNcsVO> listAunuriWeekNcsEvalPlanCode(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception {
		List<AunuriLinkEvalPlanNcsVO> data = aunuriLinkMapper.listAunuriWeekNcsEvalPlanCode(evalPlanNcsVO);
		return data;
	}

	@Override
	public List<AunuriLinkEvalPlanNcsVO> listAunuriWeekNcsEvalPlanNote(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception {
		List<AunuriLinkEvalPlanNcsVO> data = aunuriLinkMapper.listAunuriWeekNcsEvalPlanNote(evalPlanNcsVO);
		return data;
	}

	@Override
	public List<AunuriLinkEvalPlanNcsVO> listAunuriWeekNcsEvalWay(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception {
		List<AunuriLinkEvalPlanNcsVO> data = aunuriLinkMapper.listAunuriWeekNcsEvalWay(evalPlanNcsVO);
		return data;
	}

	@Override
	public AunuriLinkEvalPlanNcsVO getAunuriWeekNcsEvalPlan(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception {
		AunuriLinkEvalPlanNcsVO data = aunuriLinkMapper.getAunuriWeekNcsEvalPlan(evalPlanNcsVO);
		return data;
	}

	@Override
	public AunuriLinkEvalPlanNcsVO getAunuriWeekNcsEvalPlanElem(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception {
		AunuriLinkEvalPlanNcsVO data = aunuriLinkMapper.getAunuriWeekNcsEvalPlanElem(evalPlanNcsVO);
		return data;
	}
	
	@Override
	public List<AunuriLinkSubjectGradeVO> listSubectGrade(AunuriLinkSubjectGradeVO aunuriLinkSubjectGradeVO) throws Exception {
		List<AunuriLinkSubjectGradeVO> data = aunuriLinkMapper.listSubectGrade(aunuriLinkSubjectGradeVO);
		return data;
	}
	
	@Override
	public AunuriLinkSubjectGradeVO getSubectGradeCnt(AunuriLinkSubjectGradeVO aunuriLinkSubjectGradeVO) throws Exception {
		AunuriLinkSubjectGradeVO data = aunuriLinkMapper.getSubectGradeCnt(aunuriLinkSubjectGradeVO);
		return data;
	}
	

	@Override
	public int getAunuriMemberCnt(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception {
		return aunuriLinkMapper.getAunuriMemberCnt(evalPlanNcsVO);
	}

	@Override
	public int getAunuriMemberTutCnt(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception {
		return aunuriLinkMapper.getAunuriMemberTutCnt(evalPlanNcsVO);
	}

	@Override
	public int getAunuriMemberTutSize(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception {
		return aunuriLinkMapper.getAunuriMemberTutSize(evalPlanNcsVO);
	}

	@Override
	public int insertAunuriSubjectInfoTutMapping(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception {
		return aunuriLinkMapper.insertAunuriSubjectInfoTutMapping(evalPlanNcsVO);
	}

	@Override
	public int updateAunuriSubjectInfoTutMapping(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception {
		return aunuriLinkMapper.updateAunuriSubjectInfoTutMapping(evalPlanNcsVO);
	}

	@Override
	public int deleteAunuriTutMappingNull(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception {
		return aunuriLinkMapper.deleteAunuriTutMappingNull(evalPlanNcsVO);
	}

	@Override
	public int deleteAunuriTutMapping(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception {
		return aunuriLinkMapper.deleteAunuriTutMapping(evalPlanNcsVO);
	}
	
	@Override
	public int updateAunuriOutMemberInfoEtc(AunuriLinkMemberVO aunuriLinkMemberVO) throws Exception {
		return aunuriLinkMapper.updateAunuriOutMemberInfoEtc(aunuriLinkMemberVO);
	}
	
	@Override
	public String getAunuriWeekStartDate(AunuriLinkSubjectVO aunuriLinkSubjectVO) throws Exception {
		return aunuriLinkMapper.getAunuriWeekStartDate(aunuriLinkSubjectVO);
	}
	
	
}