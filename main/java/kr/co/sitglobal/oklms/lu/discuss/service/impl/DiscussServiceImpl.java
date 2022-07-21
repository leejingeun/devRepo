/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2017. 03. 02.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.lu.discuss.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.lu.discuss.service.DiscussService;
import kr.co.sitglobal.oklms.lu.discuss.vo.DiscussVO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

 /**
 * 프로토타입 게시판 CRUD 비지니스 로직을 구현하는 클레스.
 * @author 이진근
 * @since 2017. 03. 02.
 */
@Transactional(rollbackFor=Exception.class)
@Service("discussService")
public class DiscussServiceImpl extends EgovAbstractServiceImpl implements DiscussService{

	/** ID Generation */
	//토론
    @Resource(name="discussIdGnrService")
    private EgovIdGnrService discussIdGnrService;
    
    //토론 의견
    @Resource(name="discussOpinionIdGnrService")
    private EgovIdGnrService discussOpinionIdGnrService;
    
    //토론 의견코멘트
    @Resource(name="discussCommentIdGnrService")
    private EgovIdGnrService discussCommentIdGnrService;
    
    //의견 추천 히스토리
    @Resource(name="discussGoodHistIdGnrService")
    private EgovIdGnrService discussGoodHistIdGnrService;
    
    @Resource(name = "discussMapper")
    private DiscussMapper discussMapper;

	@Override
	public List<DiscussVO> listDiscussInfo(DiscussVO discussVO) throws Exception {
		List<DiscussVO> data = discussMapper.listDiscussInfo(discussVO);
		return data;
	}
	
	@Override
	public List<DiscussVO> listDiscussOpinion(DiscussVO discussVO) throws Exception {
		List<DiscussVO> data = discussMapper.listDiscussOpinion(discussVO);
		return data;
	}
	
	@Override
	public List<DiscussVO> listDiscussComment(DiscussVO discussVO) throws Exception {
		List<DiscussVO> data = discussMapper.listDiscussComment(discussVO);
		return data;
	}
	
	@Override
	public List<DiscussVO> listDiscussEvalScoreResultStd(DiscussVO discussVO) throws Exception {
		List<DiscussVO> data = discussMapper.listDiscussEvalScoreResultStd(discussVO);
		return data;
	}
	
	@Override
	public DiscussVO getDiscussInfo(DiscussVO discussVO) throws Exception {
		DiscussVO data = discussMapper.getDiscussInfo(discussVO);
		return data;
	}
	
	@Override
	public DiscussVO getDiscussOpinionList(DiscussVO discussVO) throws Exception {
		DiscussVO data = discussMapper.getDiscussOpinionList(discussVO);
		return data;
	}
	
	@Override
	public DiscussVO getDiscussOpinionComment(DiscussVO discussVO) throws Exception {
		DiscussVO data = discussMapper.getDiscussOpinionComment(discussVO);
		return data;
	}
	
	@Override
	public int getDiscussOpinionCnt(DiscussVO discussVO) throws Exception {
		return discussMapper.getDiscussOpinionCnt(discussVO);
	}
	
	@Override
	public int getDiscussOpinionCommentCnt(DiscussVO discussVO) throws Exception {
		return discussMapper.getDiscussOpinionCommentCnt(discussVO);
	}
	
	@Override
	public int getDiscussOpinionHistCnt(DiscussVO discussVO) throws Exception {
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(discussVO); // session의 정보를 VO에 추가.
		
		return discussMapper.getDiscussOpinionHistCnt(discussVO);
	}
	
	@Override
	public int getDiscussEvalScoreStdCnt(DiscussVO discussVO) throws Exception {
		return discussMapper.getDiscussEvalScoreStdCnt(discussVO);
	}

	@Override
	public int insertDiscussInfo(DiscussVO discussVO) throws Exception {
		int sqlResultInt = 0;
		
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(discussVO); // session의 정보를 VO에 추가.
		
		String pkStr = discussIdGnrService.getNextStringId();
		System.out.println("##### pkStr="+pkStr);
		discussVO.setDiscussId(pkStr);
		
		sqlResultInt = discussMapper.insertDiscussInfo(discussVO);
		
		return sqlResultInt;
	}
	
	@Override
	public int insertDiscussOpinion(DiscussVO discussVO) throws Exception {
		int sqlResultInt = 0;
		
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(discussVO); // session의 정보를 VO에 추가.
		
		String pkStr = discussOpinionIdGnrService.getNextStringId();
		System.out.println("##### pkStr="+pkStr);
		discussVO.setDiscussOpinionId(pkStr);
		
		sqlResultInt = discussMapper.insertDiscussOpinion(discussVO);
		
		return sqlResultInt;
	}
	
	@Override
	public int insertDiscussComment(DiscussVO discussVO) throws Exception {
		int sqlResultInt = 0;
		
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(discussVO); // session의 정보를 VO에 추가.
		
		String pkStr = discussCommentIdGnrService.getNextStringId();
		System.out.println("##### pkStr="+pkStr);
		discussVO.setDiscussCommentId(pkStr);
		
		sqlResultInt = discussMapper.insertDiscussComment(discussVO);
		
		return sqlResultInt;
	}
	
	@Override
	public int insertDiscussGoodHist(DiscussVO discussVO) throws Exception {
		int sqlResultInt = 0;
		
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(discussVO); // session의 정보를 VO에 추가.
		
		String pkStr = discussGoodHistIdGnrService.getNextStringId();
		System.out.println("##### pkStr="+pkStr);
		discussVO.setHistoryId(pkStr);
		
		sqlResultInt = discussMapper.insertDiscussGoodHist(discussVO);
		
		return sqlResultInt;
	}
	
	@Override
	public int insertDiscussStdEvalScore(DiscussVO discussVO) throws Exception {
		int sqlResultInt = 0;
		
		//세션자동복사
		DiscussVO tempVO = new DiscussVO();
		DiscussVO evalScoreYnVO = new DiscussVO();
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(tempVO); // session의 정보를 VO에 추가.
		loginInfo.putSessionToVo(evalScoreYnVO); // session의 정보를 VO에 추가.
		int flagCnt = 0;
		
		//토론 학습자별 평가점수 업데이트	
		for( int idx = 0 ; idx < discussVO.getArrMemSeq().length ; idx++ ) {
			System.out.println("discussId ==> "+discussVO.getDiscussId());
			System.out.println("arrMemSeq ==> "+discussVO.getArrMemSeq()[idx]);
			System.out.println("arrEvalScore ==> "+discussVO.getArrEvalScore()[idx]);
			
			tempVO.setDiscussId(discussVO.getDiscussId());
			tempVO.setMemSeq(discussVO.getArrMemSeq()[idx]);
			
			String evalScore = "";
			evalScore = discussVO.getArrEvalScore()[idx];
			
			if( StringUtils.isBlank( evalScore ) ){
				tempVO.setEvalScore(null);
				flagCnt++;
			} else {
				tempVO.setEvalScore(discussVO.getArrEvalScore()[idx]);
			}
			
			sqlResultInt = discussMapper.insertDiscussStdEvalScore(tempVO); 
		}
		
		//토론 점수결과 업데이트
		if(sqlResultInt > 0){
			
			evalScoreYnVO.setDiscussId(discussVO.getDiscussId());
			
			if(flagCnt > 0){
				evalScoreYnVO.setEvalScoreResutlYn("N");
			} else {
				evalScoreYnVO.setEvalScoreResutlYn("Y");
			}
			
			discussMapper.updateDiscussStdEvalScoreYn(evalScoreYnVO); 
		}
		
		return sqlResultInt;
	}
	
	@Override
	public int updateDiscussInfo(DiscussVO discussVO) throws Exception {
		int sqlResultInt = 0;
		
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(discussVO); // session의 정보를 VO에 추가.
		
		sqlResultInt = discussMapper.updateDiscussInfo(discussVO);
		
		return sqlResultInt;
	}
	
	@Override
	public int updateDiscussOpinion(DiscussVO discussVO) throws Exception {
		int sqlResultInt = 0;
		
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(discussVO); // session의 정보를 VO에 추가.
		
		sqlResultInt = discussMapper.updateDiscussOpinion(discussVO);
		
		return sqlResultInt;
	}
	
	@Override
	public int updateDiscussComment(DiscussVO discussVO) throws Exception {
		int sqlResultInt = 0;
		
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(discussVO); // session의 정보를 VO에 추가.
		
		sqlResultInt = discussMapper.updateDiscussComment(discussVO);
		
		return sqlResultInt;
	}
	
	@Override
	public int updateDiscussOpinionHitCnt(DiscussVO discussVO) throws Exception {
		int sqlResultInt = 0;
		
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(discussVO); // session의 정보를 VO에 추가.
		
		sqlResultInt = discussMapper.updateDiscussOpinionHitCnt(discussVO);
		
		return sqlResultInt;
	}
	
	@Override
	public int updateDiscussOpinionGoodCnt(DiscussVO discussVO) throws Exception {
		int sqlResultInt = 0;
		
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(discussVO); // session의 정보를 VO에 추가.
		
		sqlResultInt = discussMapper.updateDiscussOpinionGoodCnt(discussVO);
		
		return sqlResultInt;
	}
	
	@Override
	public int updateDiscussStdEvalScore(DiscussVO discussVO) throws Exception {
		int sqlResultInt = 0;
		
		//세션자동복사
		DiscussVO tempVO = new DiscussVO();
		DiscussVO evalScoreYnVO = new DiscussVO();
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(tempVO); // session의 정보를 VO에 추가.
		loginInfo.putSessionToVo(evalScoreYnVO); // session의 정보를 VO에 추가.
		int flagCnt = 0;
		
		//토론 학습자별 평가점수 업데이트	
		for( int idx = 0 ; idx < discussVO.getArrMemSeq().length ; idx++ ) {
			System.out.println("discussId ==> "+discussVO.getDiscussId());
			System.out.println("arrMemSeq ==> "+discussVO.getArrMemSeq()[idx]);
			System.out.println("arrEvalScore ==> "+discussVO.getArrEvalScore()[idx]);
			
			tempVO.setDiscussId(discussVO.getDiscussId());
			tempVO.setMemSeq(discussVO.getArrMemSeq()[idx]);
			
			String evalScore = "";
			evalScore = discussVO.getArrEvalScore()[idx];
			
			if( StringUtils.isBlank( evalScore ) ){
				tempVO.setEvalScore(null);
				flagCnt++;
			} else {
				tempVO.setEvalScore(discussVO.getArrEvalScore()[idx]);
			}
			
			
			sqlResultInt = discussMapper.updateDiscussStdEvalScore(tempVO); 
		}
		
		//토론 점수결과 업데이트
		if(sqlResultInt > 0){
			
			evalScoreYnVO.setDiscussId(discussVO.getDiscussId());
			
			if(flagCnt > 0){
				evalScoreYnVO.setEvalScoreResutlYn("N");
			} else {
				evalScoreYnVO.setEvalScoreResutlYn("Y");
			}
			
			discussMapper.updateDiscussStdEvalScoreYn(evalScoreYnVO); 
		}
		
		return sqlResultInt;
	}
	
	@Override
	public int deleteDiscussInfo(DiscussVO discussVO) throws Exception {
		int sqlResultInt = 0;
		
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(discussVO); // session의 정보를 VO에 추가.
		
		sqlResultInt = discussMapper.deleteDiscussInfo(discussVO);
		
		return sqlResultInt;
	}
	
	@Override
	public int deleteDiscussOpinion(DiscussVO discussVO) throws Exception {
		int sqlResultInt = 0;
		
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(discussVO); // session의 정보를 VO에 추가.
		
		sqlResultInt = discussMapper.deleteDiscussOpinion(discussVO);
		
		return sqlResultInt;
	}
	
	@Override
	public int deleteDiscussComment(DiscussVO discussVO) throws Exception {
		int sqlResultInt = 0;
		
		//세션자동복사
		LoginInfo loginInfo = new LoginInfo();
		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
		loginInfo.putSessionToVo(discussVO); // session의 정보를 VO에 추가.
		
		sqlResultInt = discussMapper.deleteDiscussComment(discussVO);
		
		return sqlResultInt;
	}
  	
	
}
