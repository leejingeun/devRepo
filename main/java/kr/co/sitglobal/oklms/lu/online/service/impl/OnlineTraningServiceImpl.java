
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
package kr.co.sitglobal.oklms.lu.online.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.lu.online.service.OnlineTraningService;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningSchElemVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningSchVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningWeekVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

 /**
 * 프로토타입 게시판 CRUD 비지니스 로직을 구현하는 클레스.
 * @author 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
@Service("OnlineTraningService")
public class OnlineTraningServiceImpl extends EgovAbstractServiceImpl implements OnlineTraningService{
	
	private static final Logger LOG = LoggerFactory.getLogger(OnlineTraningServiceImpl.class);
	
	/** ID Generation */
    @Resource(name="subjWeekSchIdGnrService")
    private EgovIdGnrService subjWeekSchIdGnrService;
    
    /** ID Generation */
    @Resource(name="subjWeekSchElemIdGnrService")
    private EgovIdGnrService subjWeekSchElemIdGnrService;
    
    @Resource(name = "OnlineTraningMapper")
    private OnlineTraningMapper onlineTraningMapper;
    
    
    
    
    @Override
	public List<OnlineTraningSchVO> listOnlineTraningSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception {
		List<OnlineTraningSchVO> data = onlineTraningMapper.listOnlineTraningSchedule(onlineTraningSchVO);
		return data;
	}
    
    @Override
   	public List<OnlineTraningSchVO> listOnlineTraningCdpSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception {
   		List<OnlineTraningSchVO> data = onlineTraningMapper.listOnlineTraningCdpSchedule(onlineTraningSchVO);
   		return data;
   	}
    
    @Override
   	public List<OnlineTraningSchVO> listOfflineTraningCdpSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception {
   		List<OnlineTraningSchVO> data = onlineTraningMapper.listOfflineTraningCdpSchedule(onlineTraningSchVO);
   		return data;
   	}
    
    
    
    @Override
	public List<OnlineTraningSchVO> listOnlineTraningAllWeekSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception {
		List<OnlineTraningSchVO> data = onlineTraningMapper.listOnlineTraningAllWeekSchedule(onlineTraningSchVO);
		
		List<OnlineTraningSchVO> list = new ArrayList<OnlineTraningSchVO>();
		
		List<OnlineTraningSchElemVO> elemList = null;
		
		for(int i=0; i < data.size(); i++){
			OnlineTraningSchVO schVO = data.get(i);
			
			// CMS 연계 차시라면 JSON 데이터 생성
			if("Y".equals(schVO.getLinkContentYn())){
				
				elemList = onlineTraningMapper.listOnlineTraningWeekScheduleElem(schVO);
				
				
				//OnlineTraningSchElemVO onlineTraningSchElemVO = null;
				JsonArray jsonArray = new JsonArray(); 
				for(int x=0; x < elemList.size(); x++){
					//onlineTraningSchElemVO = new OnlineTraningSchElemVO();
					OnlineTraningSchElemVO elemVO  = elemList.get(x);
					JsonObject jsonObject = new JsonObject(); 
					
					jsonObject.addProperty("lesson_id", elemVO.getLessonId());
					jsonObject.addProperty("lesson_item_id", elemVO.getLessonItemId());
					jsonObject.addProperty("lesson_sub_item_id", elemVO.getLessonSubItemId());
					jsonObject.addProperty("required_learning_time_in_seconds", elemVO.getRequiredLearningTimeInSeconds());
					
					jsonArray.add(jsonObject);
					
				}
				
				Gson gson = new Gson();
				String jsonLessonDate = gson.toJson(jsonArray);
				schVO.setJsonLessonData(jsonLessonDate);
				
				LOG.debug("============== schVO.getJsonLessonData : "+schVO.getJsonLessonData());
				
			} else {
				schVO.setJsonLessonData("");
			}
			
			list.add(schVO);
		}
		
		return list;
	}
    
    
    @Override
	public int insertOnlineTraningStand(OnlineTraningVO onlineTraningVO) throws Exception {
		int sqlResultInt = onlineTraningMapper.insertOnlineTraningStand(onlineTraningVO); 
		return sqlResultInt;
	}
    
    
    @Override
	public OnlineTraningVO getOnlineTraningStand(OnlineTraningVO onlineTraningVO) throws Exception {
    	OnlineTraningVO data = onlineTraningMapper.getOnlineTraningStand(onlineTraningVO); 
		return data;
	}
    
    @Override
	public int updateOnlineTraningStand(OnlineTraningVO onlineTraningVO) throws Exception {
		int sqlResultInt = onlineTraningMapper.updateOnlineTraningStand(onlineTraningVO); 
		return sqlResultInt;
	}
    
    @Override
   	public int insertOnlineWeekSchedule(OnlineTraningWeekVO onlineTraningWeekVO) throws Exception {
   		int weekResult = 0;
   		int weekSchResult = 0;
   		int weekSchNum = 0;
   		int weekSchElemResult = 0;
    	if( onlineTraningWeekVO.getWeekIds() != null && onlineTraningWeekVO.getWeekIds().length > 0 ){
    		String [] delim = onlineTraningWeekVO.getWeekSchDelim().split("\\|");
    		
    		
    		LOG.debug("======================= onlineTraningWeekVO.getWeekSchDelim() : "+onlineTraningWeekVO.getWeekSchDelim());
    		
    		OnlineTraningWeekVO onWeekVO;
    		
    		for(int i=0; i < onlineTraningWeekVO.getWeekIds().length; i++){
    			onWeekVO = new OnlineTraningWeekVO();
    			
    			onWeekVO.setWeekId(onlineTraningWeekVO.getWeekIds()[i]);
    			onWeekVO.setWeekCnt(onlineTraningWeekVO.getWeekCnts()[i]);
    			onWeekVO.setContentName(onlineTraningWeekVO.getContentNames()[i]);
    			onWeekVO.setWeekStDate(onlineTraningWeekVO.getWeekStDates()[i]);
    			onWeekVO.setWeekEdDate(onlineTraningWeekVO.getWeekEdDates()[i]);
    			onWeekVO.setWeekMin(onlineTraningWeekVO.getWeekMins()[i]);
    			
    			// 등록시 기존 주차별 콘텐츠 명 및 차시정보, 차시별 오소정보 삭제
    			weekResult += onlineTraningMapper.deleteOnlineTraningWeekContent(onWeekVO.getWeekId());
    			weekResult += onlineTraningMapper.deleteOnlineTraningWeekSchedule(onWeekVO.getWeekId());
    			weekResult += onlineTraningMapper.deleteOnlineTraningWeekScheduleElem(onWeekVO.getWeekId());
    			
    			LOG.debug("======================= onWeekVO.getWeekStDate : "+onWeekVO.getWeekStDate());
    			LOG.debug("======================= onWeekVO.getWeekEdDate : "+onWeekVO.getWeekStDate());
    					
    					
    			weekResult += onlineTraningMapper.insertOnlineTraningWeekContent(onWeekVO);
    			//System.out.println("==================== weekResult : "+weekResult);
    			LOG.debug("======================= delim[i] : "+delim[i]);
    			LOG.debug("======================= onWeekVO.getWeekCnt : "+onWeekVO.getWeekCnt());
    			int unit = Integer.parseInt(delim[i]);
    			
    			LOG.debug("======================= unit : "+unit);
    			
    			OnlineTraningSchVO onSchVO;
    			for(int x=0; x < unit; x++){
    				
    				onSchVO = new OnlineTraningSchVO();
    				String pkSubjSchId = subjWeekSchIdGnrService.getNextStringId();
    				
    				LOG.debug("======================= pkSubjSchId : "+pkSubjSchId);
    				
    				onSchVO.setYyyy(onlineTraningWeekVO.getYyyy());
    				onSchVO.setTerm(onlineTraningWeekVO.getTerm());
        			onSchVO.setSubjectClass(onlineTraningWeekVO.getSubjectClass());
        			onSchVO.setSubjectCode(onlineTraningWeekVO.getSubjectCode());
    				
    				
    				onSchVO.setSubjSchId(pkSubjSchId);
    				onSchVO.setWeekId(onWeekVO.getWeekId());
    				onSchVO.setWeekCnt(onWeekVO.getWeekCnt());
    				onSchVO.setSubjStep(String.valueOf(x+1));
    				
    				onSchVO.setStartDate(onlineTraningWeekVO.getWeekSchStDates()[weekSchNum]);
    				onSchVO.setEndDate(onlineTraningWeekVO.getWeekSchEdDates()[weekSchNum]);
    				onSchVO.setStudyTime(onlineTraningWeekVO.getWeekSchMins()[weekSchNum]);
    				
    				String linkContentTypes = onlineTraningWeekVO.getLinkContentTypes()[weekSchNum];
    				
    				
    				
    				if(linkContentTypes.equals("CMS")){
    					onSchVO.setLinkContentYn("Y");
    					// CMS 에서 끌어온 콘텐츠라면 요소들을 DB에 등록
    					String jsonLessonData = onlineTraningWeekVO.getJsonLessonDatas()[weekSchNum];
    					Gson gson = new Gson();
    					JsonArray jsonArray = new JsonParser().parse(jsonLessonData).getAsJsonArray();
    					LOG.debug("======================= jsonLessonData : "+jsonLessonData.toString());
    					LOG.debug("======================= elem jsonArray.size() : "+jsonArray.size());

    					for (int y = 0; y < jsonArray.size(); y++) {
					        JsonElement str = jsonArray.get(y);
					        OnlineTraningSchElemVO elemVO = gson.fromJson(str, OnlineTraningSchElemVO.class);
					        elemVO.setElemId(subjWeekSchElemIdGnrService.getNextStringId());
					        elemVO.setWeekId(onWeekVO.getWeekId());
					        elemVO.setWeekCnt(onWeekVO.getWeekCnt());
					        elemVO.setSubjSchId(onSchVO.getSubjSchId());
					        elemVO.setSubjStep(onSchVO.getSubjStep());
					        
					        
					        LOG.debug("======================= elemVO.getElemId() : "+elemVO.getElemId());
					        LOG.debug("======================= elemVO.getWeekId() : "+elemVO.getWeekId());
					        LOG.debug("======================= elemVO.getWeekCnt() : "+elemVO.getWeekCnt());
					        LOG.debug("======================= elemVO.getSubjSchId() : "+elemVO.getSubjSchId());
					        LOG.debug("======================= elemVO.getSubjStep() : "+elemVO.getSubjStep());
					        
					        LOG.debug("======================= elemVO.getLesson_id() : "+elemVO.getLesson_id());
					        LOG.debug("======================= elemVO.getLesson_item_id() : "+elemVO.getLesson_item_id());
					        LOG.debug("======================= elemVO.getLesson_sub_item_id() : "+elemVO.getLesson_sub_item_id());
					        
					        
					        weekSchElemResult += onlineTraningMapper.insertOnlineTraningWeekScheduleElem(elemVO);
					    }	
					    onSchVO.setPageCount(jsonArray == null ? 0 : jsonArray.size());
    				} else {
    					onSchVO.setLinkContentYn("N");
    					onSchVO.setPageCount(1);
    				}
    				
    				onSchVO.setSubjTitle(onlineTraningWeekVO.getSubjTitles()[weekSchNum]);
					onSchVO.setCmsCourseContentId(onlineTraningWeekVO.getCmsCourseContentIds()[weekSchNum]);
    				onSchVO.setCmsLessonId(onlineTraningWeekVO.getCmsLessonIds()[weekSchNum]);
    				onSchVO.setCmsId(onlineTraningWeekVO.getCmsIds()[weekSchNum]);
    				onSchVO.setDeviceTypeCode(onlineTraningWeekVO.getDeviceTypeCodes()[weekSchNum]);
    				onSchVO.setContentType(onlineTraningWeekVO.getContentTypes()[weekSchNum]);
    				onSchVO.setContentsDir(onlineTraningWeekVO.getContentsDirs()[weekSchNum]);
    				onSchVO.setContentsIdxFile(onlineTraningWeekVO.getContentsIdxFiles()[weekSchNum]);
    				onSchVO.setContentsRealFile(onlineTraningWeekVO.getContentsRealFiles()[weekSchNum]);
    				onSchVO.setUrl(onlineTraningWeekVO.getUrls()[weekSchNum]);
    				
    				
    				LOG.debug("======================= onSchVO.getSubjTitle() : "+onSchVO.getSubjTitle());
    				LOG.debug("======================= onSchVO.getCmsCourseContentId() : "+onSchVO.getCmsCourseContentId());
    				LOG.debug("======================= onSchVO.getCmsId() : "+onSchVO.getCmsId());
    				LOG.debug("======================= onSchVO.getDeviceTypeCode() : "+onSchVO.getDeviceTypeCode());
    				LOG.debug("======================= onSchVO.getContentType() : "+onSchVO.getContentType());
    				LOG.debug("======================= onSchVO.getContentsDir() : "+onSchVO.getContentsDir());
    				LOG.debug("======================= onSchVO.getContentsIdxFile() : "+onSchVO.getContentsIdxFile());
    				LOG.debug("======================= onSchVO.getContentsRealFile() : "+onSchVO.getContentsRealFile());
    				LOG.debug("======================= onSchVO.getUrl() : "+onSchVO.getUrl());
    				
    				
    				
    				weekSchResult += onlineTraningMapper.insertOnlineTraningWeekSchedule(onSchVO);
    				System.out.println("==================== weekSchResult : "+weekSchResult);
    				
    				weekSchNum++;
    			}
    			
        	}
    	}
    	
   		return (weekResult+weekSchResult+weekSchElemResult);
   	}
    
    
    @Override
	public List<OnlineTraningSchVO> listOnlineTraningStdSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception {
		List<OnlineTraningSchVO> data = onlineTraningMapper.listOnlineTraningStdSchedule(onlineTraningSchVO);
		return data;
	}
    
    @Override
	public OnlineTraningSchVO getAllProgressRateLesson(OnlineTraningSchVO onlineTraningSchVO) throws Exception {
    	OnlineTraningSchVO data = onlineTraningMapper.getAllProgressRateLesson(onlineTraningSchVO);
		return data;
	}
    
    @Override
	public List<OnlineTraningSchVO> listOjtTraningStdSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception {
		List<OnlineTraningSchVO> data = onlineTraningMapper.listOjtTraningStdSchedule(onlineTraningSchVO);
		return data;
	}
    @Override
   	public OnlineTraningSchVO getTraningStatus(OnlineTraningSchVO onlineTraningSchVO) throws Exception {
       	OnlineTraningSchVO data = onlineTraningMapper.getTraningStatus(onlineTraningSchVO);
   		return data;
   	}
    
    @Override
	public List<OnlineTraningSchVO> listOnlineTraningInsSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception {
		List<OnlineTraningSchVO> data = onlineTraningMapper.listOnlineTraningInsSchedule(onlineTraningSchVO);
		return data;
	}
    
    @Override
   	public List<OnlineTraningSchVO> listOjtTraningInsSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception {
   		List<OnlineTraningSchVO> data = onlineTraningMapper.listOjtTraningInsSchedule(onlineTraningSchVO);
   		return data;
   	}
    
    @Override
   	public List<OnlineTraningSchVO> listOjtTraningCotSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception {
   		List<OnlineTraningSchVO> data = onlineTraningMapper.listOjtTraningCotSchedule(onlineTraningSchVO);
   		return data;
   	}
    
    
    @Override
	public List<OnlineTraningSchVO> listOnlineTraningWeekSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception {
		List<OnlineTraningSchVO> data = onlineTraningMapper.listOnlineTraningWeekSchedule(onlineTraningSchVO);
		return data;
	}
    
    @Override
	public String getOnlineTraningWeekProgressYn(OnlineTraningSchVO onlineTraningSchVO) throws Exception {
		String data = onlineTraningMapper.getOnlineTraningWeekProgressYn(onlineTraningSchVO);
		return data;
	}
    

	/*@Override
	public List<TraningProcessSearchVO> listTraningProcess(
			TraningProcessSearchVO traningProcessSearchVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TraningVO> listTraningProcessManage(TraningVO traningVO)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}*/
  	
  	/*@Override
	public List<TraningProcessSearchVO> listTraningProcess(TraningProcessSearchVO traningProcessSearchVO) throws Exception {
		List<TraningProcessSearchVO> data = traningMapper.listTraningProcess(traningProcessSearchVO);
		return data;
	}
  	
  	@Override
  	public List<TraningVO> listTraningProcessManage(TraningVO traningVO) throws Exception {
		List<TraningVO> data = traningMapper.listTraningProcessManage(traningVO);
		return data;
	}*/
	
	
//
//	 @Override
//	 public CompanyVO getCompany(CompanyVO companyVO) throws Exception {
//		 CompanyVO data = companyMapper.getCompany(companyVO);
//		 return data;
//	 }
//
//	@Override
//	public String insertCompany(CompanyVO companyVO) throws Exception {
//		String returnStr = "";
//		String pkCompanyId = companyIdGnrService.getNextStringId();
//		companyVO.setCompanyId(pkCompanyId);
//
//		LoginInfo loginInfo = new LoginInfo();
//		loginInfo.putSessionToVo(companyVO);
//		
//		//공백제거
//		companyVO.setCompanyId(StringUtils.delete(companyVO.getCompanyId(), " ").trim());
//		int sqlResultInt = companyMapper.insertCompany(companyVO);
//		if( 0 < sqlResultInt ){
//			returnStr = pkCompanyId;
//		}
//		return returnStr;
//	}
//	
//	@Override
//	public int updateCompany(CompanyVO companyVO) throws Exception {
//
//		LoginInfo loginInfo = new LoginInfo();
//		loginInfo.putSessionToVo(companyVO);
//		
//		int sqlResultInt = companyMapper.updateCompany(companyVO); 
//		return sqlResultInt;
//	}
//	
//	@Override
//	public int deleteCompany(CompanyVO companyVO) throws Exception {
//		
//		LoginInfo loginInfo = new LoginInfo();
//		loginInfo.putSessionToVo(companyVO);
//		
//		return companyMapper.deleteCompany(companyVO);
//	}
//	
//	
//	
//	@Override
//	public int getCompanyNoCnt(CompanyVO companyVO) throws Exception {
//		
//		return companyMapper.getCompanyNoCnt(companyVO);
//	}
}
