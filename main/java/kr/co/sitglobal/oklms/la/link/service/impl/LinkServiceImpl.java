
/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 07. 01.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.la.link.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.co.sitglobal.aunuri.service.impl.AunuriLinkMapper;
import kr.co.sitglobal.aunuri.vo.AunuriLinkLessonVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkMemberMappingVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkMemberVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkScheduleVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkSubjectVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkSubjectWeekNcsVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkSubjectWeekVO;
import kr.co.sitglobal.ifx.service.impl.CmsIfxServiceImpl;
import kr.co.sitglobal.oklms.comm.util.CommonUtil;
import kr.co.sitglobal.oklms.la.link.service.LinkService;
import kr.co.sitglobal.oklms.la.member.service.impl.MemberMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;









import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

 /**
 * 프로토타입 게시판 CRUD 비지니스 로직을 구현하는 클레스.
 * @author 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
@Service("linkService")
public class LinkServiceImpl extends EgovAbstractServiceImpl implements LinkService {
	
	private static final Logger LOG = LoggerFactory.getLogger(LinkServiceImpl.class);
	
	/** ID Generation */
    @Resource(name="memberIdGnrService")
    private EgovIdGnrService memberIdGnrService;
    
    /** ID Generation */
    @Resource(name="subjWeekIdGnrService")
    private EgovIdGnrService subjWeekIdGnrService;
    
    /** ID Generation */
    @Resource(name="subjWeekTimeIdGnrService")
    private EgovIdGnrService subjWeekTimeIdGnrService;
    
    /** ID Generation */
    @Resource(name="insMappingIdGnrService")
    private EgovIdGnrService insMappingIdGnrService;
    
    /** ID Generation */
    @Resource(name="cdpMappingIdGnrService")
    private EgovIdGnrService cdpMappingIdGnrService;
    
    /** ID Generation */
    @Resource(name="lessonIdGnrService")
    private EgovIdGnrService lessonIdGnrService;
    
    /** ID Generation */
    @Resource(name="subjectHistoryIdGnrService")
    private EgovIdGnrService subjectHistoryIdGnrService;
	
	@Resource(name = "aunuriLinkMapper")
    private AunuriLinkMapper aunuriLinkMapper;
	
    @Resource(name = "linkMapper")
    private LinkMapper linkMapper;
    
    @Resource(name = "memberMapper")
    private MemberMapper memberMapper;
	
	@Override
	public int insertAunuriMember(AunuriLinkMemberVO aunuriLinkMemberVO) throws Exception {
		
		List<AunuriLinkMemberVO> data = aunuriLinkMapper.listAunuriMember(aunuriLinkMemberVO);
		
		int iResult = 0;
		if( data.size() > 0 ) {
			for( int i=0; i < data.size(); i++ ){
				aunuriLinkMemberVO = data.get(i);
				aunuriLinkMemberVO.setMemSeq(memberIdGnrService.getNextStringId());
				iResult += linkMapper.insertAunuriMember(aunuriLinkMemberVO);
			}
		}
		
		return iResult;
	}
	

	@Override
    public int insertAunuriSubject(AunuriLinkSubjectVO aunuriLinkSubjectVO) throws Exception {
		
		List<AunuriLinkSubjectVO> data = aunuriLinkMapper.listAunuriSubject(aunuriLinkSubjectVO);
		
		int iResult = 0;
		if( data.size() > 0 ) {
			for( int i=0; i < data.size(); i++ ){
				aunuriLinkSubjectVO = data.get(i);
				
				// 개설교과목 등록
				iResult += linkMapper.insertAunuriSubject(aunuriLinkSubjectVO);
				
				// Off-JT 일 경우에만 주차등록
				if("OFF".equals(aunuriLinkSubjectVO.getSubjectTraningType())){
					// 주차, 주차별 시간, 주차별 NCS  정보등록
					for( int x=0;  x < 15; x++ ){	// 현시점에서는 듀얼쪽은 15주차라 하드 코딩 (학습계획서에는 15주차 이상이라해도 실제로 15주차)
						AunuriLinkSubjectWeekVO weekVO = new AunuriLinkSubjectWeekVO();
						
						weekVO.setWeekId(subjWeekIdGnrService.getNextStringId());
						weekVO.setWeekCnt(String.valueOf( x+1 ) ); 
						weekVO.setYyyy(aunuriLinkSubjectVO.getYyyy());
						weekVO.setTerm(aunuriLinkSubjectVO.getTerm());
						weekVO.setSubjectCode(aunuriLinkSubjectVO.getSubjectCode());
						weekVO.setSubjectClass(aunuriLinkSubjectVO.getSubjectClass());
						
						AunuriLinkSubjectWeekVO infoVO = aunuriLinkMapper.getAunuriWeekLessonInfo(weekVO);
						
						if(infoVO != null){
							weekVO.setLessonCn(infoVO.getLessonCn());
							weekVO.setLessonMthd(infoVO.getLessonMthd());
						}
						
						
						// 교과목 > 주차등록
						iResult += linkMapper.insertAunuriSubjectWeek(weekVO);
						
						AunuriLinkSubjectWeekVO weekTimeVO = aunuriLinkMapper.getAunuriWeekTime(weekVO);
						
						// 교과목 주차 > 시간
						if( weekTimeVO != null ) {
							weekTimeVO.setWeekId(weekVO.getWeekId());
							weekTimeVO.setTimeId(subjWeekTimeIdGnrService.getNextStringId());
							iResult += linkMapper.insertAunuriWeekTime(weekTimeVO);
						}
					}
				}
			}
		}
		
		return iResult;
	}
    
	
	@Override
    public int insertAunuriLesson(AunuriLinkLessonVO aunuriLinkLessonVO) throws Exception {
		
		List<AunuriLinkLessonVO> data = aunuriLinkMapper.listAunuriLesson(aunuriLinkLessonVO);
		
		int iResult = 0;
		if( data.size() > 0 ) {
			for( int i=0; i < data.size(); i++ ){
				aunuriLinkLessonVO = data.get(i);
				String memSeq = memberMapper.getMemberSeq(aunuriLinkLessonVO.getMemId());
				aunuriLinkLessonVO.setMemSeq(memSeq);
				aunuriLinkLessonVO.setLecStatus("A");
				aunuriLinkLessonVO.setLessonId(lessonIdGnrService.getNextStringId());
				
				iResult += linkMapper.insertAunuriLesson(aunuriLinkLessonVO);
				
			}
		}
		
		return iResult;
	}
	
	@Override
    public int insertAunuriIns(AunuriLinkMemberMappingVO aunuriLinkMemberMappingVO) throws Exception {
		
		List<AunuriLinkMemberMappingVO> data = aunuriLinkMapper.listAunuriIns(aunuriLinkMemberMappingVO);
		
		int iResult = 0;
		if( data.size() > 0 ) {
			for( int i=0; i < data.size(); i++ ){
				
				aunuriLinkMemberMappingVO = data.get(i);
				String memSeq = memberMapper.getMemberSeq(aunuriLinkMemberMappingVO.getMemId());
				
				// 회원정보가 있을경우에만 등록
				if(memSeq != null && !"".equals(memSeq)){
					aunuriLinkMemberMappingVO.setMemSeq(memSeq);
					aunuriLinkMemberMappingVO.setSubjInsMappingId(insMappingIdGnrService.getNextStringId());
					iResult += linkMapper.insertAunuriIns(aunuriLinkMemberMappingVO);
				}
			}
		}
		
		return iResult;
	}
	
	@Override
    public int insertAunuriCdp(AunuriLinkMemberMappingVO aunuriLinkMemberMappingVO) throws Exception {
		
		List<AunuriLinkMemberMappingVO> data = linkMapper.listSubjectCdp(aunuriLinkMemberMappingVO);
		
		int iResult = 0;
		if( data.size() > 0 ) {
			for( int i=0; i < data.size(); i++ ){
				
				aunuriLinkMemberMappingVO = data.get(i);
				aunuriLinkMemberMappingVO.setSubjCdpMappingId(cdpMappingIdGnrService.getNextStringId());
				
				iResult += linkMapper.insertAunuriCdp(aunuriLinkMemberMappingVO);
			}
		}
		
		return iResult;
	}
	
	@Override
    public int insertAunuriSchedule(AunuriLinkScheduleVO aunuriLinkScheduleVO) throws Exception {
		
		List<AunuriLinkScheduleVO> data = aunuriLinkMapper.listAunuriHaksaSchedule(aunuriLinkScheduleVO);
		
		int iResult = 0;
		if( data.size() > 0 ) {
			for( int i=0; i < data.size(); i++ ){
				
				aunuriLinkScheduleVO = data.get(i);
				
				iResult += linkMapper.insertAunuriSchedule(aunuriLinkScheduleVO);
			}
		}
		return iResult;
	}
	
	
	@Override
    public int insertAunuriWeekNcs(AunuriLinkSubjectWeekNcsVO aunuriLinkSubjectWeekNcsVO) throws Exception {
		
		
		List<AunuriLinkSubjectWeekNcsVO> data = linkMapper.listAunuriWeek(aunuriLinkSubjectWeekNcsVO);
		
		int iResult = 0;
		if( data.size() > 0 ) {
			for( int i=0; i < data.size(); i++ ){
				
				aunuriLinkSubjectWeekNcsVO = data.get(i);
				
				LOG.debug("===================================  list getWeekId : "+aunuriLinkSubjectWeekNcsVO.getWeekId());
				LOG.debug("===================================  list getYyyy :   "+aunuriLinkSubjectWeekNcsVO.getYyyy());
				LOG.debug("===================================  list getTerm :   "+aunuriLinkSubjectWeekNcsVO.getTerm());
				LOG.debug("===================================  list getSubjectCode :   "+aunuriLinkSubjectWeekNcsVO.getSubjectCode());
				LOG.debug("===================================  list getSubjectClass :   "+aunuriLinkSubjectWeekNcsVO.getSubjectClass());
				LOG.debug("===================================  list getWeekCnt :   "+aunuriLinkSubjectWeekNcsVO.getWeekCnt());
				
				// NCS 단위를 가져옴
				AunuriLinkSubjectWeekNcsVO weekNcsVO = aunuriLinkMapper.getAunuriWeekNcsUnit(aunuriLinkSubjectWeekNcsVO);
				
				if(weekNcsVO != null){
					weekNcsVO.setWeekId(aunuriLinkSubjectWeekNcsVO.getWeekId());
					
					LOG.debug("***************************************  weekNcsVO getYyyy :   "+weekNcsVO.getYyyy());
					LOG.debug("***************************************  weekNcsVO getTerm :   "+weekNcsVO.getTerm());
					LOG.debug("***************************************  weekNcsVO getSubjectCode :   "+weekNcsVO.getSubjectCode());
					LOG.debug("***************************************  weekNcsVO getSubjectClass :   "+weekNcsVO.getSubjectClass());
					LOG.debug("***************************************  weekNcsVO getWeekCnt :   "+weekNcsVO.getWeekCnt());
					LOG.debug("***************************************  weekNcsVO getNcsUnitId :   "+weekNcsVO.getNcsUnitId());
					LOG.debug("***************************************  weekNcsVO getNcsUnitName :   "+weekNcsVO.getNcsUnitName());
					
					// NCS 단위 등록
					iResult += linkMapper.insertAunuriWeekNcsUnit(weekNcsVO);
					
					// NCS 요소를 가져옴
					weekNcsVO.setWeekCnt(aunuriLinkSubjectWeekNcsVO.getWeekCnt());
					List<AunuriLinkSubjectWeekNcsVO> dataList = aunuriLinkMapper.listAunuriWeekNcsElem(weekNcsVO);
					
					for(int x=0; x < dataList.size(); x++){
						AunuriLinkSubjectWeekNcsVO elemVO = dataList.get(x);
						
						LOG.debug("############################### elemVO getYyyy :   "+weekNcsVO.getYyyy());
						LOG.debug("###############################  elemVO getTerm :   "+weekNcsVO.getTerm());
						LOG.debug("###############################  elemVO getSubjectCode :   "+weekNcsVO.getSubjectCode());
						LOG.debug("###############################  elemVO getSubjectClass :   "+weekNcsVO.getSubjectClass());
						LOG.debug("###############################  elemVO getWeekCnt :   "+weekNcsVO.getWeekCnt());
						LOG.debug("###############################  elemVO getNcsUnitId :   "+weekNcsVO.getNcsUnitId());
						LOG.debug("###############################  elemVO getNcsElemtId :   "+weekNcsVO.getNcsElemId());
						LOG.debug("###############################  elemVO getNcsElemName :   "+weekNcsVO.getNcsElemName());
						
						elemVO.setWeekId(aunuriLinkSubjectWeekNcsVO.getWeekId());
						iResult += linkMapper.insertAunuriWeekNcsElem(elemVO);
					}
				}
			}
		}
		return iResult;
	}
	
	
	@Override
    public int updateAunuriWeek(AunuriLinkSubjectWeekNcsVO aunuriLinkSubjectWeekNcsVO) throws Exception {
		
		
		List<AunuriLinkSubjectWeekNcsVO> data = linkMapper.listAunuriWeek(aunuriLinkSubjectWeekNcsVO);
		
		
		int iResult = 0;
		if( data.size() > 0 ) {
			for( int i=0; i < data.size(); i++ ){
				
				aunuriLinkSubjectWeekNcsVO = data.get(i);
				
				
				LOG.debug("===================================  list getWeekId : "+aunuriLinkSubjectWeekNcsVO.getWeekId());
				LOG.debug("===================================  list getYyyy :   "+aunuriLinkSubjectWeekNcsVO.getYyyy());
				LOG.debug("===================================  list getTerm :   "+aunuriLinkSubjectWeekNcsVO.getTerm());
				LOG.debug("===================================  list getSubjectCode :   "+aunuriLinkSubjectWeekNcsVO.getSubjectCode());
				LOG.debug("===================================  list getSubjectClass :   "+aunuriLinkSubjectWeekNcsVO.getSubjectClass());
				LOG.debug("===================================  list getWeekCnt :   "+aunuriLinkSubjectWeekNcsVO.getWeekCnt());
				
				// NCS 단위를 가져옴
				AunuriLinkSubjectWeekNcsVO weekNcsVO = aunuriLinkMapper.getAunuriWeekNcsUnit(aunuriLinkSubjectWeekNcsVO);
				
				if(weekNcsVO != null){
					weekNcsVO.setWeekId(aunuriLinkSubjectWeekNcsVO.getWeekId());
					
					LOG.debug("***************************************  weekNcsVO getYyyy :   "+weekNcsVO.getYyyy());
					LOG.debug("***************************************  weekNcsVO getTerm :   "+weekNcsVO.getTerm());
					LOG.debug("***************************************  weekNcsVO getSubjectCode :   "+weekNcsVO.getSubjectCode());
					LOG.debug("***************************************  weekNcsVO getSubjectClass :   "+weekNcsVO.getSubjectClass());
					LOG.debug("***************************************  weekNcsVO getWeekCnt :   "+weekNcsVO.getWeekCnt());
					LOG.debug("***************************************  weekNcsVO getNcsUnitId :   "+weekNcsVO.getNcsUnitId());
					LOG.debug("***************************************  weekNcsVO getNcsUnitName :   "+weekNcsVO.getNcsUnitName());
					
					// NCS 단위 등록
					iResult += linkMapper.insertAunuriWeekNcsUnit(weekNcsVO);
					
					// NCS 요소를 가져옴
					weekNcsVO.setWeekCnt(aunuriLinkSubjectWeekNcsVO.getWeekCnt());
					List<AunuriLinkSubjectWeekNcsVO> dataList = aunuriLinkMapper.listAunuriWeekNcsElem(weekNcsVO);
					
					for(int x=0; x < dataList.size(); x++){
						AunuriLinkSubjectWeekNcsVO elemVO = dataList.get(x);
						
						LOG.debug("############################### elemVO getYyyy :   "+weekNcsVO.getYyyy());
						LOG.debug("###############################  elemVO getTerm :   "+weekNcsVO.getTerm());
						LOG.debug("###############################  elemVO getSubjectCode :   "+weekNcsVO.getSubjectCode());
						LOG.debug("###############################  elemVO getSubjectClass :   "+weekNcsVO.getSubjectClass());
						LOG.debug("###############################  elemVO getWeekCnt :   "+weekNcsVO.getWeekCnt());
						LOG.debug("###############################  elemVO getNcsUnitId :   "+weekNcsVO.getNcsUnitId());
						LOG.debug("###############################  elemVO getNcsElemtId :   "+weekNcsVO.getNcsElemId());
						LOG.debug("###############################  elemVO getNcsElemName :   "+weekNcsVO.getNcsElemName());
						
						elemVO.setWeekId(aunuriLinkSubjectWeekNcsVO.getWeekId());
						iResult += linkMapper.insertAunuriWeekNcsElem(elemVO);
					}
				}
			}
		}
		return iResult;
	}
	
	public int executeBatch() throws Exception {
		int iResult = 0;
		AunuriLinkSubjectWeekNcsVO aunuriLinkSubjectWeekNcsVO = new AunuriLinkSubjectWeekNcsVO();
		iResult += linkMapper.comMemberTest(aunuriLinkSubjectWeekNcsVO);
		
		System.out.println("=====================  iResult : "+iResult);
		return iResult;
	}
	
	
	@Override
	public int insertAunuriLinkTerm(AunuriLinkSubjectVO aunuriLinkSubjectVO) throws Exception {
		
		// 연계 후 등록 교과목정보를 불러옴
		// 교과목정보 OKLMS 등록
		// 해당 교과목의 학습근로자 및 교수자 목록을 조회
		// 학습근로자 및 교수자가 OKLMS 에 없다면 회원등록
		// 수강정보 등록
		// 교수정보 매핑
		// 학과전담자 매핑
		
		// 끝으로 학사일정 연계
		int iResult = 0;
		String weekStartDate = "";
		List<AunuriLinkSubjectVO> data = aunuriLinkMapper.listAunuriSubject(aunuriLinkSubjectVO);
		
		if( data.size() > 0 ) {
			
			String historyId = subjectHistoryIdGnrService.getNextStringId();
			weekStartDate = aunuriLinkMapper.getAunuriWeekStartDate(aunuriLinkSubjectVO);
			
			for( int i=0; i < data.size(); i++ ){
				
				aunuriLinkSubjectVO = data.get(i);
				aunuriLinkSubjectVO.setHistoryId(historyId);
				
				// 개설교과목 등록
				iResult += linkMapper.insertAunuriSubjectTerm(aunuriLinkSubjectVO);
			
				// Off-JT 일 경우에만 주차등록
				if("OFF".equals(aunuriLinkSubjectVO.getSubjectTraningType())){
					
					// 주차, 주차별 시간, 주차별 NCS  정보등록
					for( int x=0;  x < 15; x++ ){	// 현시점에서는 듀얼쪽은 15주차라 하드 코딩 (학습계획서에는 15주차 이상이라해도 실제로 15주차)
						AunuriLinkSubjectWeekVO weekVO = new AunuriLinkSubjectWeekVO();
						
						weekVO.setWeekId(subjWeekIdGnrService.getNextStringId());
						weekVO.setWeekCnt(String.valueOf( x+1 ) ); 
						weekVO.setYyyy(aunuriLinkSubjectVO.getYyyy());
						weekVO.setTerm(aunuriLinkSubjectVO.getTerm());
						weekVO.setSubjectCode(aunuriLinkSubjectVO.getSubjectCode());
						weekVO.setSubjectClass(aunuriLinkSubjectVO.getSubjectClass());
						
						AunuriLinkSubjectWeekVO infoVO = aunuriLinkMapper.getAunuriWeekLessonInfo(weekVO);
						
						if(infoVO != null){
							weekVO.setLessonCn(infoVO.getLessonCn());
							weekVO.setLessonMthd(infoVO.getLessonMthd());
						}
						
						// 교과목 > 주차등록
						iResult += linkMapper.insertAunuriSubjectWeek(weekVO);
						
						
						AunuriLinkSubjectWeekVO weekTimeVO = aunuriLinkMapper.getAunuriWeekTime(weekVO);
						
						// 교과목 주차 > 시간 등록
						if( weekTimeVO != null ) {
							weekTimeVO.setWeekId(weekVO.getWeekId());
							weekTimeVO.setWeekCnt(weekVO.getWeekCnt());
							weekTimeVO.setTimeId(subjWeekTimeIdGnrService.getNextStringId());
							weekTimeVO.setTraningDate(CommonUtil.getLaterDay(weekStartDate,"yyyy.MM.dd", 7 * Integer.parseInt(weekTimeVO.getWeekCnt())));
							
							iResult += linkMapper.insertAunuriWeekTime(weekTimeVO);
						}
						
						// NCS 단위를 가져옴
						
						weekVO.setWeekCnt(String.valueOf( x+1 ) ); 
						weekVO.setYyyy(aunuriLinkSubjectVO.getYyyy());
						weekVO.setTerm(aunuriLinkSubjectVO.getTerm());
						weekVO.setSubjectCode(aunuriLinkSubjectVO.getSubjectCode());
						weekVO.setSubjectClass(aunuriLinkSubjectVO.getSubjectClass());
						
						AunuriLinkSubjectWeekNcsVO aunuriLinkSubjectWeekNcsVO = new AunuriLinkSubjectWeekNcsVO();
						aunuriLinkSubjectWeekNcsVO.setYyyy(weekVO.getYyyy());
						aunuriLinkSubjectWeekNcsVO.setTerm(weekVO.getTerm());
						aunuriLinkSubjectWeekNcsVO.setSubjectCode(weekVO.getSubjectCode());
						aunuriLinkSubjectWeekNcsVO.setSubjectClass(weekVO.getSubjectClass());
						aunuriLinkSubjectWeekNcsVO.setWeekCnt(weekVO.getWeekCnt());
						aunuriLinkSubjectWeekNcsVO.setWeekId(weekVO.getWeekId());
						
						AunuriLinkSubjectWeekNcsVO weekNcsVO = aunuriLinkMapper.getAunuriWeekNcsUnit(aunuriLinkSubjectWeekNcsVO);
						
						if(weekNcsVO != null){
							weekNcsVO.setWeekId(aunuriLinkSubjectWeekNcsVO.getWeekId());
							
							LOG.debug("***************************************  weekNcsVO getYyyy :   "+weekNcsVO.getYyyy());
							LOG.debug("***************************************  weekNcsVO getTerm :   "+weekNcsVO.getTerm());
							LOG.debug("***************************************  weekNcsVO getSubjectCode :   "+weekNcsVO.getSubjectCode());
							LOG.debug("***************************************  weekNcsVO getSubjectClass :   "+weekNcsVO.getSubjectClass());
							LOG.debug("***************************************  weekNcsVO getWeekCnt :   "+weekNcsVO.getWeekCnt());
							LOG.debug("***************************************  weekNcsVO getNcsUnitId :   "+weekNcsVO.getNcsUnitId());
							LOG.debug("***************************************  weekNcsVO getNcsUnitName :   "+weekNcsVO.getNcsUnitName());
							
							// NCS 단위 등록
							iResult += linkMapper.insertAunuriWeekNcsUnit(weekNcsVO);
							
							// NCS 요소를 가져옴
							weekNcsVO.setWeekCnt(aunuriLinkSubjectWeekNcsVO.getWeekCnt());
							List<AunuriLinkSubjectWeekNcsVO> dataList = aunuriLinkMapper.listAunuriWeekNcsElem(weekNcsVO);
							
							for(int y=0; y < dataList.size(); y++){
								AunuriLinkSubjectWeekNcsVO elemVO = dataList.get(y);
								
								LOG.debug("############################### elemVO getYyyy :   "+weekNcsVO.getYyyy());
								LOG.debug("###############################  elemVO getTerm :   "+weekNcsVO.getTerm());
								LOG.debug("###############################  elemVO getSubjectCode :   "+weekNcsVO.getSubjectCode());
								LOG.debug("###############################  elemVO getSubjectClass :   "+weekNcsVO.getSubjectClass());
								LOG.debug("###############################  elemVO getWeekCnt :   "+weekNcsVO.getWeekCnt());
								LOG.debug("###############################  elemVO getNcsUnitId :   "+weekNcsVO.getNcsUnitId());
								LOG.debug("###############################  elemVO getNcsElemtId :   "+weekNcsVO.getNcsElemId());
								LOG.debug("###############################  elemVO getNcsElemName :   "+weekNcsVO.getNcsElemName());
								
								elemVO.setWeekId(aunuriLinkSubjectWeekNcsVO.getWeekId());
								iResult += linkMapper.insertAunuriWeekNcsElem(elemVO);
							}
						}
					}
				}
				
				// 수강생 회원가입 여무 확인 및 lesson 등록
				AunuriLinkLessonVO aunuriLinkLessonVO = new AunuriLinkLessonVO();
				aunuriLinkLessonVO.setYyyy(aunuriLinkSubjectVO.getYyyy());
				aunuriLinkLessonVO.setTerm(aunuriLinkSubjectVO.getTerm());
				aunuriLinkLessonVO.setSubjectCode(aunuriLinkSubjectVO.getSubjectCode());
				aunuriLinkLessonVO.setSubjectClass(aunuriLinkSubjectVO.getSubjectClass());
				
				List<AunuriLinkLessonVO> lessonData = aunuriLinkMapper.listAunuriLessonTerm(aunuriLinkLessonVO);
				
				if( lessonData.size() > 0 ) {
					for( int ii =0; ii < lessonData.size(); ii++ ){
						aunuriLinkLessonVO =lessonData.get(ii);
						
						String memSeq = memberMapper.getMemberSeq(aunuriLinkLessonVO.getMemId());
						
						// 회원가입이 안된 수강생이라면 회원가입
						if(!"".equals(memSeq)){
							AunuriLinkMemberVO aunuriLinkMemberVO = new AunuriLinkMemberVO();
							aunuriLinkMemberVO.setMemId(aunuriLinkLessonVO.getMemId());
							// 아우누리에서 회원정보를 가져옴
							aunuriLinkMemberVO = aunuriLinkMapper.getAunuriMember(aunuriLinkMemberVO);
							
							aunuriLinkMemberVO.setMemSeq(memberIdGnrService.getNextStringId());
							iResult += linkMapper.insertAunuriMember(aunuriLinkMemberVO);
						}
						
						memSeq = memberMapper.getMemberSeq(aunuriLinkLessonVO.getMemId());
						
						aunuriLinkLessonVO.setMemSeq(memSeq);
						aunuriLinkLessonVO.setLecStatus("A");
						aunuriLinkLessonVO.setLessonId(lessonIdGnrService.getNextStringId());
						
						// 수강테이블 등록
						iResult += linkMapper.insertAunuriLesson(aunuriLinkLessonVO);
						
					}
				}
				
				// 교수 회원가입 여부 확인 및 mapping 테이블에 등록
				AunuriLinkMemberMappingVO aunuriLinkMemberMappingVO = new AunuriLinkMemberMappingVO();
				List<AunuriLinkMemberMappingVO> insData = aunuriLinkMapper.listAunuriInsTerm(aunuriLinkMemberMappingVO);
				
				if( insData.size() > 0 ) {
					for( int yy =0; yy < insData.size(); yy++ ){
						aunuriLinkMemberMappingVO = insData.get(yy);
						
						String memSeq = memberMapper.getMemberSeq(aunuriLinkMemberMappingVO.getMemId());
						
						// 회원가입이 안된 교수자라면 회원가입
						if(!"".equals(memSeq)){
							AunuriLinkMemberVO aunuriLinkMemberVO = new AunuriLinkMemberVO();
							aunuriLinkMemberVO.setMemId(aunuriLinkLessonVO.getMemId());
							// 아우누리에서 회원정보를 가져옴
							aunuriLinkMemberVO = aunuriLinkMapper.getAunuriMember(aunuriLinkMemberVO);
							
							aunuriLinkMemberVO.setMemSeq(memberIdGnrService.getNextStringId());
							iResult += linkMapper.insertAunuriMember(aunuriLinkMemberVO);
						}
						
						memSeq = memberMapper.getMemberSeq(aunuriLinkMemberMappingVO.getMemId());
						
						aunuriLinkMemberMappingVO.setMemSeq(memSeq);
						aunuriLinkMemberMappingVO.setSubjInsMappingId(insMappingIdGnrService.getNextStringId());
						iResult += linkMapper.insertAunuriIns(aunuriLinkMemberMappingVO);
					}
				}
				
				// 학과전담자 매핑
				aunuriLinkMemberMappingVO = new AunuriLinkMemberMappingVO();
				
				aunuriLinkMemberMappingVO.setYyyy(aunuriLinkSubjectVO.getYyyy());
				aunuriLinkMemberMappingVO.setTerm(aunuriLinkSubjectVO.getTerm());
				aunuriLinkMemberMappingVO.setSubjectCode(aunuriLinkSubjectVO.getSubjectCode());
				aunuriLinkMemberMappingVO.setSubjectClass(aunuriLinkSubjectVO.getSubjectClass());
				
				List<AunuriLinkMemberMappingVO> cdpData = linkMapper.listSubjectCdpTerm(aunuriLinkMemberMappingVO);
				
				if( data.size() > 0 ) {
					for( int z=0; z < cdpData.size(); z++ ){
						
						aunuriLinkMemberMappingVO = cdpData.get(z);
						aunuriLinkMemberMappingVO.setSubjCdpMappingId(cdpMappingIdGnrService.getNextStringId());
						
						iResult += linkMapper.insertAunuriCdp(aunuriLinkMemberMappingVO);
					}
				}
				
				// 학사스케쥴 연동
				
			}
		}
		
		return iResult;
	}
	
	
}
