package kr.co.sitglobal.ifx.service.impl;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.co.sitglobal.ifx.service.CmsIfxService;
import kr.co.sitglobal.ifx.vo.CmsCourseBaseVO;
import kr.co.sitglobal.ifx.vo.CmsCourseCodeVO;
import kr.co.sitglobal.ifx.vo.CmsCourseContentItemItemListVO;
import kr.co.sitglobal.ifx.vo.CmsCourseContentItemLessonVO;
import kr.co.sitglobal.ifx.vo.CmsCourseContentItemListVO;
import kr.co.sitglobal.ifx.vo.CmsCourseContentItemSubItemListVO;
import kr.co.sitglobal.ifx.vo.CmsCourseContentPropertiesVO;
import kr.co.sitglobal.ifx.vo.CmsCourseContentVO;
import kr.co.sitglobal.oklms.comm.util.StringUtil;
import kr.co.sitglobal.oklms.lu.online.web.OnlineTraningController;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Transactional(rollbackFor=Exception.class)
@Service("cmsIfxService")
public class CmsIfxServiceImpl extends EgovAbstractServiceImpl implements CmsIfxService{
	
	@Resource(name = "cmsIfxMapper")
    private CmsIfxMapper cmsIfxMapper;
	
	private static final Logger LOG = LoggerFactory.getLogger(CmsIfxServiceImpl.class);
	private final String USER_AGENT = "Mozilla/5.0";
	  
	
	/**
	 * CMS에서 과정 개발 코드 목록 가져오기
	 * @param cmsCourseBaseVO
	 * @return
	 * List<CmsCourseCodeVO>
	 */
	@Override
	public List<CmsCourseCodeVO> getCourseCodeSummaryList(CmsCourseBaseVO cmsCourseBaseVO) throws Exception{
		List<CmsCourseCodeVO> data = new ArrayList<CmsCourseCodeVO>();
		try {
			String jsonInfo =sendPost(cmsCourseBaseVO);
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonElement jsonTree = parser.parse(jsonInfo);
			JsonObject jsonObject = jsonTree.getAsJsonObject();
			JsonElement body = jsonObject.get("body");
			JsonElement total_count = null;
			JsonArray jsonArray = null;
			if(body != null && body.isJsonObject()){
			    JsonObject f2Obj  = body.getAsJsonObject();
			    JsonElement list = f2Obj.get("list");
			    total_count =  f2Obj.get("total_count");
			    if(list.isJsonArray()){
			    	jsonArray = list.getAsJsonArray();
			    }
			}
		    for (int i = 0; i < jsonArray.size(); i++) {
		        JsonElement str = jsonArray.get(i);
		        CmsCourseCodeVO result = gson.fromJson(str, CmsCourseCodeVO.class);
		        result.setTotal_count(total_count.toString());
//		        System.out.println(result);
//		        System.out.println(str);
//		        System.out.println("-------");
//		        System.out.println("result.setTotal_count : "+result.getTotal_count());
		       //온평원:A/학교:B 구분에 따라 저장
//		        System.out.println("result.getCode():"+result.getCode().substring(0, 1));
//		       if(result.getCode().substring(0, 1).equals("A")){
		    	   data.add(result);	
//		       }
		        
		    }
		 } catch (Exception e) {
		    e.printStackTrace();
		 }

		return data;
	}
	
	
	/**
	 * CMS에서 특정 과정 개발 코드 내의 콘텐츠 버전 목록 가져오기
	 * @param cmsCourseBaseVO
	 * @return
	 * List<CmsCourseContentVO>
	 */
	@Override
	public List<CmsCourseContentVO> getCourseContentSummaryList(CmsCourseBaseVO cmsCourseBaseVO) throws Exception{
		List<CmsCourseContentVO> data = new ArrayList<CmsCourseContentVO>();
		try {
			
			String jsonInfo =sendPost(cmsCourseBaseVO);
			Gson gson = new Gson();
			
			JsonParser parser = new JsonParser();
			JsonElement jsonTree = parser.parse(jsonInfo);
			JsonObject jsonObject = jsonTree.getAsJsonObject();
			JsonElement body = jsonObject.get("body");
			JsonArray jsonArray = null;
			JsonElement total_count = null;
			if(body != null && body.isJsonObject()){
			    JsonObject f2Obj  = body.getAsJsonObject();
			    JsonElement list = f2Obj.get("list");
			    total_count =  f2Obj.get("total_count");
			    
			    if(list != null){
			    	if(list.isJsonArray()){
				    	jsonArray = list.getAsJsonArray();
				    }
			    }
			}
			if(jsonArray != null){
				for (int i = 0; i < jsonArray.size(); i++) {
			        JsonElement str = jsonArray.get(i);
			        CmsCourseContentVO result = gson.fromJson(str, CmsCourseContentVO.class);
			        result.setTotal_count(total_count.toString());
			        System.out.println(result);
			        System.out.println(str);
			        System.out.println("-------");
			        data.add(result);
			    }
			}
		 } catch (Exception e) {
		    e.printStackTrace();
		 }

		return data;
	}
	
	/**
	 * CMS에서 특정 과정 개발 코드 내의 콘텐츠 버전 목록 가져오기
	 * @param cmsCourseBaseVO
	 * @return
	 * List<CmsCourseContentVO>
	 */
	@Override
	public List<CmsCourseContentVO> getCourseContentSummaryOldList(CmsCourseBaseVO cmsCourseBaseVO) throws Exception{
		List<CmsCourseContentVO> data = new ArrayList<CmsCourseContentVO>();
		try {
			String jsonInfo =sendPost(cmsCourseBaseVO);
			Gson gson = new Gson();
			
			JsonParser parser = new JsonParser();
			JsonElement jsonTree = parser.parse(jsonInfo);
			JsonObject jsonObject = jsonTree.getAsJsonObject();
			JsonElement body = jsonObject.get("body");
			JsonArray jsonArray = null;
			if(body != null && body.isJsonObject()){
			    JsonObject f2Obj  = body.getAsJsonObject();
			    JsonElement list = f2Obj.get("list");
			    if(list.isJsonArray()){
			    	jsonArray = list.getAsJsonArray();
			    }
			}
			
		    for (int i = 0; i < jsonArray.size(); i++) {
		        JsonElement str = jsonArray.get(i);
		        CmsCourseContentVO result = gson.fromJson(str, CmsCourseContentVO.class);
		        System.out.println(result);
		        System.out.println(str);
		        System.out.println("-------");
		        data.add(result);
		    }
		 } catch (Exception e) {
		    e.printStackTrace();
		 }

		return data;
	}

	/**
	 * CMS에서  버전 별 콘텐츠 정보 보기
	 * @param cmsCourseBaseVO
	 * @return
	 * List<CmsCourseContentPropertiesVO>
	 */
	@Override
	public List<CmsCourseContentPropertiesVO> getCourseContent(CmsCourseBaseVO cmsCourseBaseVO) throws Exception{
		List<CmsCourseContentPropertiesVO> data = new ArrayList<CmsCourseContentPropertiesVO>();
		try {
			String jsonInfo =sendPost(cmsCourseBaseVO);
			Gson gson = new Gson();
			
			JsonParser parser = new JsonParser();
			JsonElement jsonTree = parser.parse(jsonInfo);
			JsonObject jsonObject = jsonTree.getAsJsonObject();
			JsonElement body = jsonObject.get("body");
			JsonArray jsonArray = null;
			
			if(body != null && body.isJsonObject()){
				CmsCourseContentPropertiesVO result = new CmsCourseContentPropertiesVO();
			    JsonObject f2Obj  = body.getAsJsonObject();
			    result.setTitle(f2Obj.get("title").toString());
			  //  result.setCourse_image_url(f2Obj.get("course_image_url").toString());
			    result.setSubtitle(f2Obj.get("subtitle").toString());
			    result.setCourse_code_id(f2Obj.get("course_code_id").toString());
			    
			    JsonElement properties = f2Obj.get("properties");
			    if(properties != null && properties.isJsonObject()){
			    	JsonObject f3Obj  = properties.getAsJsonObject();
			    	result.setNcs_information((f3Obj.get("ncs_information")==null?"":f3Obj.get("ncs_information").toString()));
			    	result.setReference_data((f3Obj.get("reference_data")==null?"":f3Obj.get("reference_data").toString()));
			    	result.setInstructor_details((f3Obj.get("instructor_details")==null?"":f3Obj.get("instructor_details").toString()));
			    	result.setLearning_goal((f3Obj.get("learning_goal")==null?"":f3Obj.get("learning_goal").toString()));
			    	result.setCourse_introduction((f3Obj.get("course_introduction")==null?"":f3Obj.get("course_introduction").toString()));
			    	data.add(result);
			    }
			}
		 } catch (Exception e) {
		    e.printStackTrace();
		 }

		return data;
	}
	
	
	/**
	 * CMS에서  회차/클립 목록보기
	 * @param cmsCourseBaseVO
	 * @return
	 * List<CmsCourseContentItemListVO>
	 */
	@Override
	public List<CmsCourseContentItemListVO> getCourseContentItemList(CmsCourseBaseVO cmsCourseBaseVO) throws Exception{
		List<CmsCourseContentItemListVO> cmsCourseContentItemListVO = new ArrayList<CmsCourseContentItemListVO>();
		List<CmsCourseContentItemLessonVO> cmsCourseContentItemLessonVO = new ArrayList<CmsCourseContentItemLessonVO>();
		List<CmsCourseContentItemItemListVO> cmsCourseContentItemItemListVO = new ArrayList<CmsCourseContentItemItemListVO>();
		List<CmsCourseContentItemSubItemListVO> cmsCourseContentItemSubItemListVO = new ArrayList<CmsCourseContentItemSubItemListVO>();
		try {
			String jsonInfo =sendPost(cmsCourseBaseVO);
			Gson gson = new Gson();
			
			JsonParser parser = new JsonParser();
			JsonElement jsonTree = parser.parse(jsonInfo);
			JsonObject jsonObject = jsonTree.getAsJsonObject();
			JsonElement body = jsonObject.get("body");
			
			
			JsonArray jsonArray = null;
			JsonArray jsonArray2 = null;
			JsonArray jsonArray3 = null;
			if(body != null && body.isJsonObject()){
			    JsonObject f2Obj  = body.getAsJsonObject();
			    JsonElement list = f2Obj.get("list");
			    if(list.isJsonArray()){
			    	jsonArray = list.getAsJsonArray();
			    }
			}
			System.out.println("jsonArray.size():"+jsonArray.size());
		    for (int i = 0; i < jsonArray.size(); i++) {
		        JsonElement str = jsonArray.get(i);
		        CmsCourseContentItemListVO result = gson.fromJson(str, CmsCourseContentItemListVO.class);
		        
		        JsonElement lesson = str.getAsJsonObject().get("lesson");
		        if(lesson != null && lesson.isJsonObject()){
		        	CmsCourseContentItemLessonVO result2 = gson.fromJson(lesson, CmsCourseContentItemLessonVO.class);
		        	 JsonElement item_list = lesson.getAsJsonObject().get("item_list");
		        	 if(item_list.isJsonArray()){
		        		 	jsonArray2 = item_list.getAsJsonArray();
		        		 	System.out.println("jsonArray2.size():"+jsonArray2.size());
					    	for (int j = 0; j < jsonArray2.size(); j++) {
					    		 JsonElement str2 = jsonArray2.get(j);
//					    		 System.out.println("str2:"+str2.toString());
					    		 CmsCourseContentItemItemListVO result3 = gson.fromJson(str2, CmsCourseContentItemItemListVO.class);
					    		// CmsCourseContentItemItemListVO result3 =null;
					    		 JsonElement subitem_list = str2.getAsJsonObject().get("subitem_list");
					    		 if(subitem_list != null && subitem_list.isJsonArray()){
					    				jsonArray3 = subitem_list.getAsJsonArray();
					    				System.out.println("jsonArray3.size():"+jsonArray3.size());
					    				for (int z = 0; z < jsonArray3.size(); z++) {
					    					JsonElement str3 = jsonArray.get(z);
					    					CmsCourseContentItemSubItemListVO result4 = gson.fromJson(str3, CmsCourseContentItemSubItemListVO.class);
					    					cmsCourseContentItemSubItemListVO.add(result4);
					    					result3.setCmsCourseContentItemSubItemListVO(cmsCourseContentItemSubItemListVO);
					    				}
					    		 }
					    		 cmsCourseContentItemItemListVO.add(result3);
					    		 result2.setCmsCourseContentItemItemListVO(cmsCourseContentItemItemListVO);
					    	}
					 }
		        	 cmsCourseContentItemLessonVO.add(result2);
		        	 result.setCmsCourseContentItemLessonVO(cmsCourseContentItemLessonVO);
		        }
		        cmsCourseContentItemListVO.add(result);
//		        System.out.println("cmsCourseContentItemListVO:"+cmsCourseContentItemListVO.toString());
		    }
		 } catch (Exception e) {
		    e.printStackTrace();
		 }
   	    
		 System.out.println("cmsCourseContentItemListVO:"+cmsCourseContentItemListVO);
		return cmsCourseContentItemListVO;
	}
	
	
	/**
	 * CMS에서  회차/클립 목록보기
	 * @param cmsCourseBaseVO
	 * @return
	 * List<CmsCourseContentItemListVO>
	 */
	@Override
	public void getCourseContentItemList1(CmsCourseBaseVO cmsCourseBaseVO) throws Exception{
		List<CmsCourseContentItemListVO> cmsCourseContentItemListVO = new ArrayList<CmsCourseContentItemListVO>();
		List<CmsCourseContentItemLessonVO> cmsCourseContentItemLessonVO = new ArrayList<CmsCourseContentItemLessonVO>();
		List<CmsCourseContentItemItemListVO> cmsCourseContentItemItemListVO = new ArrayList<CmsCourseContentItemItemListVO>();
		List<CmsCourseContentItemSubItemListVO> cmsCourseContentItemSubItemListVO = new ArrayList<CmsCourseContentItemSubItemListVO>();
		String jsonInfo =sendPost(cmsCourseBaseVO);
		Gson gson = new Gson();
		
		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(jsonInfo);
		JsonObject jsonObject = jsonTree.getAsJsonObject();
		JsonElement body = jsonObject.get("body");
		
		System.out.println("=================== body  : "+body.toString());
		
		JsonArray jsonArray = null;
		JsonArray jsonArray2 = null;
		JsonArray jsonArray3 = null;
		if(body != null && body.isJsonObject()){
		    JsonObject f2Obj  = body.getAsJsonObject();
		    JsonElement list = f2Obj.get("list");
		    if(list.isJsonArray()){
		    	jsonArray = list.getAsJsonArray();
		    }
		}
	}
	
	
	
	/**
	 * CMS Data 가져온다.
	 * @param cmsCourseBaseVO
	 * @return
	 * String
	 */
	@Override
	public String getCmsData(CmsCourseBaseVO cmsCourseBaseVO) throws Exception{
		return sendPost(cmsCourseBaseVO);
	}
	
	
	public String sendPost(CmsCourseBaseVO cmsCourseBaseVO) throws Exception {
		
		   	String contentUrl = EgovProperties.getProperty("Globals.contentUrl")+cmsCourseBaseVO.getAddURL();
		    
		   	StringBuffer urlParameters = new StringBuffer();
		   	urlParameters.append("apiKey="+EgovProperties.getProperty("Globals.apiKey"));
		   	urlParameters.append("&institutionId="+EgovProperties.getProperty("Globals.institutionId"));
		   	
		   	if(!cmsCourseBaseVO.getYear().equals("")){
		   		urlParameters.append("&year="+cmsCourseBaseVO.getYear());	
		   	}
		   	
		   	if(!cmsCourseBaseVO.getCourseCodeId().equals("")){
		   		urlParameters.append("&courseCodeId="+cmsCourseBaseVO.getCourseCodeId());
		   	}
		   	
		   	if(!cmsCourseBaseVO.getId().equals("")){
		   		urlParameters.append("&id="+cmsCourseBaseVO.getId());
		   	}
		   	
			if(!cmsCourseBaseVO.getCourseContentId().equals("")){
		   		urlParameters.append("&courseContentId="+cmsCourseBaseVO.getCourseContentId());
		   	}
			
			if(!cmsCourseBaseVO.getLessonId().equals("")){
		   		urlParameters.append("&lessonId="+cmsCourseBaseVO.getLessonId());
		   	}
			
			if(!cmsCourseBaseVO.getLessonItemId().equals("")){
		   		urlParameters.append("&lessonItemId="+cmsCourseBaseVO.getLessonItemId());
		   	}
			
			if(!cmsCourseBaseVO.getLessonSubItemId().equals("")){
		   		urlParameters.append("&lessonSubitemId="+cmsCourseBaseVO.getLessonSubItemId());
		   	}
			
			if(cmsCourseBaseVO.getPage() > 0){
		   		urlParameters.append("&page="+cmsCourseBaseVO.getPage());
		   	}
			
			if(cmsCourseBaseVO.getCount() > 0){
		   		urlParameters.append("&count="+cmsCourseBaseVO.getCount());
		   	}
			
			if(cmsCourseBaseVO.getOrderByCode() > 0){
		   		urlParameters.append("&orderByCode="+cmsCourseBaseVO.getOrderByCode());
		   	}
			
			if(cmsCourseBaseVO.getIsAvailable() > 0){
		   		urlParameters.append("&isAvailable="+cmsCourseBaseVO.getIsAvailable());
		   	}
			
		   	System.out.println("contentUrl:"+contentUrl);
			URL obj = new URL(contentUrl);
			//HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			//add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			String parameters = urlParameters.toString();
			System.out.println("parameters:"+parameters);
			
			LOG.debug("============================= parameters :  "+parameters);
			
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(parameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + contentUrl);
			System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			//print result
			System.out.println(response.toString());
			//LOG.debug("=========== contentUrl : "+contentUrl);
			//LOG.debug("=========== urlParameters : "+urlParameters);
			//LOG.debug("=========== response.toString() : "+response.toString());
			return response.toString();
		}

	
	/**
	 * CMS에서  회차/클럽 정보 보기
	 * @param cmsCourseBaseVO
	 * @return
	 *  HashMap<String, String> 
	 */
	@Override
	public HashMap<String, String> viewLesson(CmsCourseBaseVO cmsCourseBaseVO) throws Exception{
		HashMap<String, String> retMap = new HashMap<String, String>(); 
		
		String width =  "";
		String height =  "";
		String contentUrl = "";
		
		try {
			String jsonInfo =sendPost(cmsCourseBaseVO);
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonElement jsonTree = parser.parse(jsonInfo);
			JsonObject jsonObject = jsonTree.getAsJsonObject();
			JsonElement body = jsonObject.get("body");
			JsonArray jsonArray = null;
			JsonArray jsonArray2 = null;
			JsonArray jsonArray3 = null;
			if(body != null && body.isJsonObject()){
			    JsonObject f2Obj  = body.getAsJsonObject();
			    JsonElement list = f2Obj.get("item_list");
			    if(list.isJsonArray()){
			    	jsonArray = list.getAsJsonArray();
			    }
			}
			
		    for (int i = 0; i < jsonArray.size(); i++) {
		        JsonElement str = jsonArray.get(i);
		        JsonObject f0Obj  = str.getAsJsonObject();
		        
		        if(f0Obj.get("width") != null ) width = f0Obj.get("width").toString();
		        if(f0Obj.get("height") != null ) height = f0Obj.get("height").toString();
		        
      	        JsonElement subitem_list = str.getAsJsonObject().get("subitem_list");
      	        if(subitem_list != null && subitem_list.isJsonArray()){
	  				jsonArray2 = subitem_list.getAsJsonArray();
	  				for (int y = 0; y < jsonArray2.size(); y++) {
	  					JsonElement str2 = jsonArray2.get(y);
	  					JsonObject f1Obj  = str2.getAsJsonObject();
	  					
	  					if(f1Obj.get("width") != null ) width = f1Obj.get("width").toString();
	  			        if(f1Obj.get("height") != null ) height = f1Obj.get("height").toString();
	  					
	  					if(cmsCourseBaseVO.getLessonSubItemId().equals(f1Obj.get("id").toString())){
	  						JsonElement body2 = str2.getAsJsonObject().get("body");
	  						if(body2 != null && body2.isJsonArray()){
	  			  				jsonArray3 = body2.getAsJsonArray();
	  			  				for (int z = 0; z < jsonArray3.size(); z++) {
		  			  				JsonElement str3 = jsonArray3.get(z);
		  		  					JsonObject f2Obj  = str3.getAsJsonObject();
		  		  					
		  		  					if(f2Obj.get("width") != null ) width = f2Obj.get("width").toString();
		  		  				 	if(f2Obj.get("height") != null ) height = f2Obj.get("height").toString();
		  		  				 	
		  		  				    if(f2Obj.get("url") != null ){
		  		  				    	contentUrl =  f2Obj.get("url").toString().replaceAll("\"", "");
		  		  				    } else if(f2Obj.get("video_source") != null ){
		  		  				    	contentUrl = f2Obj.get("video_source").toString().replaceAll("\"", "");
		  		  				    }
		  		  					
		  		  					retMap.put("contentUrl", contentUrl);
		  		  					retMap.put("width", width);
		  		  					retMap.put("height", height);
		  		  					
		  		  					break;
	  			  				}
	  						}
	  						break;
	  					}
	  					
	  					if(retMap.size()>0){
	  						break;
	  					}
	  				}
  				}
      	      if(retMap.size()>0){
					break;
			  }
		    }
		 } catch (Exception e) {
		    e.printStackTrace();
		 }

		return retMap;
	}
	
	/**
	 * CMS에서  회차/클럽 정보 보기
	 * @param cmsCourseBaseVO
	 * @return
	 *  HashMap<String, String> 
	 */
	@Override
	public HashMap<String, String> viewLessonOne(CmsCourseBaseVO cmsCourseBaseVO) throws Exception{
		HashMap<String, String> retMap = new HashMap<String, String>(); 
		try {
			String jsonInfo =sendPost(cmsCourseBaseVO);
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonElement jsonTree = parser.parse(jsonInfo);
			JsonObject jsonObject = jsonTree.getAsJsonObject();
			JsonElement body = jsonObject.get("body");
			JsonArray jsonArray = null;
			JsonArray jsonArray2 = null;
			JsonArray jsonArray3 = null;
			if(body != null && body.isJsonObject()){
			    JsonObject f2Obj  = body.getAsJsonObject();
			    JsonElement list = f2Obj.get("item_list");
			    if(list.isJsonArray()){
			    	jsonArray = list.getAsJsonArray();
			    }
			}
			
		    for (int i = 0; i < jsonArray.size(); i++) {
		        JsonElement str = jsonArray.get(i);
		        JsonObject f0Obj  = str.getAsJsonObject();
      	        JsonElement subitem_list = str.getAsJsonObject().get("subitem_list");
      	        if(subitem_list != null && subitem_list.isJsonArray()){
	  				jsonArray2 = subitem_list.getAsJsonArray();
	  				for (int y = 0; y < jsonArray2.size(); y++) {
	  					JsonElement str2 = jsonArray2.get(y);
	  					JsonObject f1Obj  = str2.getAsJsonObject();
	  				//	if(cmsCourseBaseVO.getLessonSubItemId().equals(f1Obj.get("id").toString())){
	  						JsonElement body2 = str2.getAsJsonObject().get("body");
	  					
	  				
	  						if(body2 != null && body2.isJsonArray()){
	  			  				jsonArray3 = body2.getAsJsonArray();
	  			  				for (int z = 0; z < jsonArray3.size(); z++) {
		  			  				JsonElement str3 = jsonArray3.get(z);
		  		  					JsonObject f2Obj  = str3.getAsJsonObject();
		  		  					retMap.put("contentUrl", f2Obj.get("url").toString().replaceAll("\"", ""));
		  		  					retMap.put("width", f0Obj.get("width").toString());
		  		  					retMap.put("height", f0Obj.get("height").toString());
		  		  					break;
	  			  				}
	  						}
	  						break;
	  					}
	  					
	  					if(retMap.size()>0){
	  						break;
	  					}
	  				}
  				}
		    //}
		 } catch (Exception e) {
		    e.printStackTrace();
		 }

		return retMap;
	}
	
	/**
	 * CMS에서  클립정보를 가져옴
	 * @param cmsCourseBaseVO
	 * @return
	 *  HashMap<String, String> 
	 */
	@Override
	public List<CmsCourseContentItemSubItemListVO> viewLessonList(CmsCourseBaseVO cmsCourseBaseVO) throws Exception{
		List<CmsCourseContentItemSubItemListVO> data = new ArrayList<CmsCourseContentItemSubItemListVO>();
		CmsCourseContentItemSubItemListVO itemVO;
		try {
			String jsonInfo =sendPost(cmsCourseBaseVO);
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonElement jsonTree = parser.parse(jsonInfo);
			JsonObject jsonObject = jsonTree.getAsJsonObject();
			JsonElement body = jsonObject.get("body");
			JsonArray jsonArray = null;
			JsonArray jsonArray2 = null;
			JsonArray jsonArray3 = null;
			String title = "";
			if(body != null && body.isJsonObject()){
			    JsonObject f2Obj  = body.getAsJsonObject();
			    JsonElement list = f2Obj.get("item_list");
			    if(list.isJsonArray()){
			    	jsonArray = list.getAsJsonArray();
			    }
			}
		    for (int i = 0; i < jsonArray.size(); i++) {
		    	//System.out.println("=================== jsonArray.size() :  "+jsonArray.size());
		        JsonElement str = jsonArray.get(i);
		        JsonObject f0Obj  = str.getAsJsonObject();
      	        JsonElement subitem_list = str.getAsJsonObject().get("subitem_list");
      	        if(f0Obj.get("title") != null)  title = f0Obj.get("title").toString().replaceAll("\"", "");
      	        
      	       // System.out.println("============== subitem_list : "+subitem_list.toString());
      	        if(subitem_list != null && subitem_list.isJsonArray()){
	  				jsonArray2 = subitem_list.getAsJsonArray();
	  				for (int y = 0; y < jsonArray2.size(); y++) {
	  					
	  					JsonElement str2 = jsonArray2.get(y);
	  					JsonObject f1Obj  = str2.getAsJsonObject();
	  					itemVO = new CmsCourseContentItemSubItemListVO();
	  					itemVO.setDisplay_order(f1Obj.get("display_order").toString());
	  					itemVO.setLesson_item_id(f1Obj.get("lesson_item_id").toString());
	  					itemVO.setLesson_sub_item(f1Obj.get("id").toString());
	  					
	  					if(title != null){
	  						itemVO.setTitle(title);
	  					} else {
	  						itemVO.setTitle(f1Obj.get("title").toString().replaceAll("\"", ""));
	  					}
	  					
	  					itemVO.setSub_item_length(String.valueOf(jsonArray2.size()));
	  					data.add(itemVO);
  						//if()
  						//System.out.println("============ body2.toString() : "+body2.toString());
  						
  						/*if(body2 != null && body2.isJsonArray()){
  			  				jsonArray3 = body2.getAsJsonArray();
  			  				for (int z = 0; z < jsonArray3.size(); z++) {
	  			  				JsonElement str3 = jsonArray3.get(z);
	  		  					JsonObject f2Obj  = str3.getAsJsonObject();
	  		  					
	  		  				   // System.out.println("==================================== y "+y);
	  		  				    itemVO.setId(f1Obj.get("id").toString());
	  		  				    itemVO.setLesson_item_id(f1Obj.get("lesson_item_id").toString());
	  		  				   
	  		  				    itemVO.setUrl(f2Obj.get("url").toString().replaceAll("\"", ""));
	  		  				    itemVO.setWidth(f0Obj.get("width").toString());
	  		  				    itemVO.setHeight(f0Obj.get("height").toString());
	  		  				    itemVO.setPackage_type_code( f2Obj.get("package_type_code").toString());
	  		  				    itemVO.setSub_item_length(String.valueOf(jsonArray2.size()));	
	  		  				    
	  		  				
  			  				}
  						}*/
	  					
	  				}
  				}
		    }
		 } catch (Exception e) {
		    e.printStackTrace();
		 }

		return data;
	}
	
	
	
	@Override
	public int getCourseContentSummaryCnt() throws Exception {
		int sqlResultInt = cmsIfxMapper.getCourseContentSummaryCnt(); 
		return sqlResultInt;
	}
	
	
	
	@Override
	public int insertCourseContentSummary(CmsCourseBaseVO cmsCourseBaseVO) throws Exception {
		int iResult = 0;
		List<CmsCourseContentVO> resultList = this.getCourseContentSummaryList(cmsCourseBaseVO);
		if(resultList.size() > 0){
			for(int i=0; i < resultList.size(); i++){
				CmsCourseContentVO cmsCourseContentVO = (CmsCourseContentVO)resultList.get(i);
				iResult +=  cmsIfxMapper.insertCourseContentSummary(cmsCourseContentVO);
			}
		}
		return iResult;
	}
	
	
	@Override
	public List<CmsCourseContentVO> listCourseContentSummary(CmsCourseBaseVO cmsCourseBaseVO) throws Exception {

		List<CmsCourseContentVO> data = cmsIfxMapper.listCourseContentSummary(cmsCourseBaseVO);
		return data;
	}
	
	
	
	public static void main(String[] args) {
		try {
		CmsIfxServiceImpl ifx = new CmsIfxServiceImpl();
		CmsCourseBaseVO cmsCourseBaseVO =  new CmsCourseBaseVO();
		
		
		/*
		cmsCourseBaseVO =  new CmsCourseBaseVO();
		cmsCourseBaseVO.setAddURL("viewLesson");  
		cmsCourseBaseVO.setLessonId("6995");
		cmsCourseBaseVO.setLessonItemId("66758");
		cmsCourseBaseVO.setLessonSubItemId("55191");
		
		HashMap<String, String>  data = ifx.viewLesson(cmsCourseBaseVO);
		  JSONObject jsonObj = new JSONObject();
		  jsonObj.put("result", data);
			System.out.println(data.toString());
	
	//	cmsCourseBaseVO.setLessonId("6995");
	//	cmsCourseBaseVO.setLessonSubItemId("94799");
		/*
		  HashMap<String, String>  data = ifx.viewLesson(cmsCourseBaseVO);
		  JSONObject jsonObj = new JSONObject();
		  jsonObj.put("result", data);
			System.out.println(data.toString());
		*/
		
		//cmsCourseBaseVO.setAddURL("viewLesson");
		//cmsCourseBaseVO.setLessonId("6995");
		
		//List<CmsCourseContentItemSubItemListVO> data2 = ifx.viewLessonList(cmsCourseBaseVO);
		//System.out.println(data2.toString());
		
		//for(int i=0; i < data2.size(); i++){
		//	CmsCourseContentItemSubItemListVO vo = data2.get(i);
			//System.out.println("======== vo.getTitle()"+vo.getTitle());
		//}
		

		/*
		*/
		
		/*
		
		cmsCourseBaseVO.setAddURL("getCourseCodeSummaryList");
		List<CmsCourseCodeVO> resultCodeList = ifx.getCourseCodeSummaryList(cmsCourseBaseVO);
		List<CmsCourseContentVO> result = null;
		List<CmsCourseContentVO> resultList = new ArrayList<CmsCourseContentVO>();
		if(resultCodeList.size() > 0){
			LOG.debug("============================= resultCodeList.size() :  "+resultCodeList.size());
			for(int i=0; i < resultCodeList.size(); i++){
				CmsCourseCodeVO vo = (CmsCourseCodeVO)resultCodeList.get(i);
				
				LOG.debug("============================= i :  "+i);
				LOG.debug("============================= vo.getId() :  "+vo.getId());
				
				cmsCourseBaseVO.setOrderByCode(1);
				cmsCourseBaseVO.setIsAvailable(1); // 게시여부
				cmsCourseBaseVO.setAddURL("getCourseContentSummaryList");
				cmsCourseBaseVO.setCourseCodeId(vo.getId());
				
				result = ifx.getCourseContentSummaryList(cmsCourseBaseVO);
				
				if(result.size() > 0){
					for(int x=0; x < result.size(); x++){
						CmsCourseContentVO contentVO = (CmsCourseContentVO)result.get(x);
						resultList.add(contentVO);
					}
				}
				
			}
		}
		
		LOG.debug("================================================== resultList.size() : "+resultList.size());
		
		for(int i=0; i < resultList.size(); i++){
			CmsCourseContentVO vo = (CmsCourseContentVO)resultList.get(i);
			
			LOG.debug("================= vo.getSubtitle() : "+vo.getSubtitle());
		}
		*/
		
		/*
		cmsCourseBaseVO.setAddURL("getCourseCodeSummaryList");
		cmsCourseBaseVO.setInstitutionId("FORMAL");
		cmsCourseBaseVO.setPage(1);
		cmsCourseBaseVO.setCount(10);
		cmsCourseBaseVO.setOrderByCode(1);
	//cmsCourseBaseVO.setYear("2016");
		List<CmsCourseCodeVO> data = ifx.getCourseCodeSummaryList(cmsCourseBaseVO);
		System.out.println(data.toString());*/
		
		
		
//		
		
		/*System.out.println("##############################################");
		cmsCourseBaseVO =  new CmsCourseBaseVO();
		cmsCourseBaseVO.setAddURL("getCourseContentSummaryList");  
		cmsCourseBaseVO.setCourseCodeId("40");
	
	List<CmsCourseContentVO> data2 = ifx.getCourseContentSummaryList(cmsCourseBaseVO);
		System.out.println(data2.toString());*/
		
		/*
		System.out.println("##############################################");
		cmsCourseBaseVO =  new CmsCourseBaseVO();
		cmsCourseBaseVO.setAddURL("getCourseContent");  
		cmsCourseBaseVO.setId("40");

		List<CmsCourseContentPropertiesVO> data3 = ifx.getCourseContent(cmsCourseBaseVO);
		System.out.println(data3.toString());
		*.
		
		/*
		System.out.println("##############################################");
		cmsCourseBaseVO =  new CmsCourseBaseVO();
		cmsCourseBaseVO.setAddURL("viewLesson");  
		//cmsCourseBaseVO.setLessonId("11252");
		//cmsCourseBaseVO.setLessonItemId("99307");
		//cmsCourseBaseVO.setLessonSubItemId("94799");
	
		cmsCourseBaseVO.setLessonId("7223");
		cmsCourseBaseVO.setLessonItemId("58952");
	//	cmsCourseBaseVO.setLessonSubItemId("94799");
		
		  HashMap<String, String>  data = ifx.viewLesson(cmsCourseBaseVO);
		  JSONObject jsonObj = new JSONObject();
		  jsonObj.put("result", data);
		   
		System.out.println(jsonObj.toString());
		*/
		
		
		//c2c1ca06539d4f1fa2a930a534f1526a&institutionId=FORMAL&lessonId=19231&lessonItemId=154025&lessonSubitemId=284933
		
		cmsCourseBaseVO =  new CmsCourseBaseVO();
		
		
		//Post parameters : apiKey=c2c1ca06539d4f1fa2a930a534f1526a&institutionId=FORMAL&lessonId=19201&lessonItemId=154042&lessonSubitemId=284977
		//Post parameters : apiKey=c2c1ca06539d4f1fa2a930a534f1526a&institutionId=FORMAL&lessonId=19199&lessonItemId=154022&lessonSubitemId=284906
		
		
		//cmsCourseBaseVO.setAddURL("getCourseContentItemList");  
		//cmsCourseBaseVO.setCourseCodeId("11");
		
		
		cmsCourseBaseVO.setAddURL("viewLesson");
		
		cmsCourseBaseVO.setLessonId("16952");
		cmsCourseBaseVO.setLessonItemId("137555");
		cmsCourseBaseVO.setLessonSubItemId("239182");

		// 데이터 조작
		String  data = ifx.getCmsData(cmsCourseBaseVO);
		JSONObject jsonObj = new JSONObject();
		System.out.println(data.toString());
		
		//System.out.println("##############################################");
	//	cmsCourseBaseVO =  new CmsCourseBaseVO();
	//	cmsCourseBaseVO.setAddURL("getCourseContentItemList");  
		//cmsCourseBaseVO.setCourseContentId("3");
		
		//cmsCourseBaseVO.setAddURL("getCourseContentItemList");  
		//List<CmsCourseContentItemListVO>  data = cmsIfxService.getCourseContentItemList(cmsCourseBaseVO);
		//String cmsData = ifx.getCmsData(cmsCourseBaseVO);
		//System.out.println(cmsData.toString());
		
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
}