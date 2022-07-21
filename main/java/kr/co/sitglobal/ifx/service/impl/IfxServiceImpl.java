package kr.co.sitglobal.ifx.service.impl;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import kr.co.sitglobal.ifx.service.IfxService;
import kr.co.sitglobal.ifx.vo.AunuriMemberVO;
import kr.co.sitglobal.ifx.vo.AunuriSubjectVO;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.impl.CommonCodeMapper;
import kr.co.sitglobal.oklms.commbiz.cmmcode.vo.CommonCodeVO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Transactional(rollbackFor=Exception.class)
@Service("ifxService")
public class IfxServiceImpl extends EgovAbstractServiceImpl implements IfxService{
	
	@Resource(name = "ifxMapper")
    private IfxMapper ifxMapper;

	/**
	 * Aunuri에서 학생정보를 가져온다.
	 * @param aunuriMemberVO
	 * @return
	 * List<AunuriMemberVO>
	 */
	@Override
	public List<AunuriMemberVO> getAunuriMember(AunuriMemberVO aunuriMemberVO) throws Exception{
		List<AunuriMemberVO> data = new ArrayList<AunuriMemberVO>();

		Gson gson = new Gson();
		 try {
		    //BufferedReader br = new BufferedReader(new FileReader("C:\\Temp\\jsonFileArr.json"));
		    String jsonStr = "[{memId:2016000001,memNm:학습자1},{memId:2016000002,memNm:학습자2}]";
		    JsonArray jsonArray = new JsonParser().parse(jsonStr).getAsJsonArray();
		    System.out.println(jsonArray.size());
		    for (int i = 0; i < jsonArray.size(); i++) {
		        JsonElement str = jsonArray.get(i);
		        AunuriMemberVO result = gson.fromJson(str, AunuriMemberVO.class);
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
	 * Aunuri에서 교과정보를 가져온다.
	 * @param aunuriSubjectVO
	 * @return
	 * List<AunuriSubjectVO>
	 */
	@Override
	public List<AunuriSubjectVO> getAunuriSubject(AunuriMemberVO aunuriMemberVO) throws Exception{
		List<AunuriSubjectVO> data = new ArrayList<AunuriSubjectVO>();
		Gson gson = new Gson();
		 try {
			 				
		    //BufferedReader br = new BufferedReader(new FileReader("C:\\Temp\\jsonFileArr.json"));
		    String jsonStr = "[{year:2016,term:1,subjectCode:AAA001,subjectName:한국사,subClass:01,weekCnt:1},{year:2016,term:1,subjectCode:AAA002,subjectName:컴퓨터입문,subClass:01,weekCnt:1}]";
		    JsonArray jsonArray = new JsonParser().parse(jsonStr).getAsJsonArray();
		    for (int i = 0; i < jsonArray.size(); i++) {
		        JsonElement str = jsonArray.get(i);
		        AunuriSubjectVO result = gson.fromJson(str, AunuriSubjectVO.class);
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
	 * Aunuri에서 OJT 교과정보를 가져온다.
	 * @param aunuriSubjectVO
	 * @return
	 * List<AunuriSubjectVO>
	 */
	@Override
	public List<AunuriSubjectVO> getOjtAunuriSubject(AunuriMemberVO aunuriMemberVO) throws Exception{
		List<AunuriSubjectVO> data = new ArrayList<AunuriSubjectVO>();
		Gson gson = new Gson();
		 try {
			 				
		    //BufferedReader br = new BufferedReader(new FileReader("C:\\Temp\\jsonFileArr.json"));
		    String jsonStr = "["
		    		+ "{subjectDivCd:01,year:2016,term:1,subjectCode:AAA001,subjectName:기계,subClass:01,weekCnt:1,onlineLectureCode:04,onlineLectureName:''},"
		    		+ "{subjectDivCd:01,year:2016,term:1,subjectCode:AAA002,subjectName:전기전자,subClass:01,weekCnt:2,onlineLectureCode:04,onlineLectureName:''},"
		    		+ "{subjectDivCd:01,year:2016,term:1,subjectCode:AAA003,subjectName:메카트로닉스,subClass:01,weekCnt:3,onlineLectureCode:04,onlineLectureName:''},"
		    		+ "{subjectDivCd:01,year:2016,term:1,subjectCode:AAA004,subjectName:정보통신,subClass:01,weekCnt:4,onlineLectureCode:04,onlineLectureName:''},"
		    		+ "{subjectDivCd:01,year:2016,term:1,subjectCode:AAA005,subjectName:디자인콘텐츠,subClass:01,weekCnt:5,onlineLectureCode:04,onlineLectureName:''},"
		    		+ "{subjectDivCd:01,year:2016,term:1,subjectCode:AAA006,subjectName:화학,subClass:01,weekCnt:6,onlineLectureCode:04,onlineLectureName:''}"
		    		+ "]";
		    JsonArray jsonArray = new JsonParser().parse(jsonStr).getAsJsonArray();
		    for (int i = 0; i < jsonArray.size(); i++) {
		        JsonElement str = jsonArray.get(i);
		        AunuriSubjectVO result = gson.fromJson(str, AunuriSubjectVO.class);
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
	 * Aunuri에서 OFF-JT 교과정보를 가져온다.
	 * @param aunuriSubjectVO
	 * @return
	 * List<AunuriSubjectVO>
	 */
	@Override
	public List<AunuriSubjectVO> getOffJtAunuriSubject(AunuriMemberVO aunuriMemberVO) throws Exception{
		List<AunuriSubjectVO> data = new ArrayList<AunuriSubjectVO>();
		Gson gson = new Gson();
		 try {
			 				
		    //BufferedReader br = new BufferedReader(new FileReader("C:\\Temp\\jsonFileArr.json"));
		    String jsonStr = "["
		    		+ "{subjectDivCd:02,year:2016,term:1,subjectCode:BBB001,subjectName:기계,subClass:01,weekCnt:1,onlineLectureCode:02,onlineLectureName:online},"
		    		+ "{subjectDivCd:02,year:2016,term:1,subjectCode:BBB002,subjectName:전기전자,subClass:01,weekCnt:2,onlineLectureCode:04,onlineLectureName:''},"
		    		+ "{subjectDivCd:02,year:2016,term:1,subjectCode:BBB003,subjectName:메카트로닉스,subClass:01,weekCnt:3,onlineLectureCode:02,onlineLectureName:online},"
		    		+ "{subjectDivCd:02,year:2016,term:1,subjectCode:BBB004,subjectName:정보통신,subClass:01,weekCnt:4,onlineLectureCode:04,onlineLectureName:''},"
		    		+ "{subjectDivCd:02,year:2016,term:1,subjectCode:BBB005,subjectName:디자인콘텐츠,subClass:01,weekCnt:5,onlineLectureCode:04,onlineLectureName:''},"
		    		+ "{subjectDivCd:02,year:2016,term:1,subjectCode:BBB006,subjectName:화학,subClass:01,weekCnt:6,onlineLectureCode:02,onlineLectureName:online}"
		    		+ "]";
		    JsonArray jsonArray = new JsonParser().parse(jsonStr).getAsJsonArray();
		    for (int i = 0; i < jsonArray.size(); i++) {
		        JsonElement str = jsonArray.get(i);
		        AunuriSubjectVO result = gson.fromJson(str, AunuriSubjectVO.class);
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
	 * 학습근로자 (ojt)개설교과에 수강 Data를 List형태로 Data를 가져온다.
	 * @param aunuriMemberVO
	 * @return
	 * List<AunuriSubjectVO>
	 */
	@Override
	public List<AunuriSubjectVO> getOjtAunuriSubjectLesson(AunuriMemberVO aunuriMemberVO) throws Exception {
		List<AunuriSubjectVO> resultList = ifxMapper.getOjtAunuriSubjectLessonList(aunuriMemberVO);
		return resultList;
	}
	
	/**
	 * 학습근로자 (off-jt)개설교과에 수강 Data를 List형태로 Data를 가져온다.
	 * @param aunuriMemberVO
	 * @return
	 * List<AunuriSubjectVO>
	 */
	@Override
	public List<AunuriSubjectVO> getOffJtAunuriSubjectLesson(AunuriMemberVO aunuriMemberVO) throws Exception {
		List<AunuriSubjectVO> resultList = ifxMapper.getOffJtAunuriSubjectLessonList(aunuriMemberVO);
		return resultList;
	}
	
	/**
	 * 담당교수 (ojt)개설교과에 메핑 Data를 List형태로 Data를 가져온다.
	 * @param aunuriMemberVO
	 * @return
	 * List<AunuriSubjectVO>
	 */
	@Override
	public List<AunuriSubjectVO> getOjtAunuriSubjectInsMapping(AunuriMemberVO aunuriMemberVO) throws Exception {
		List<AunuriSubjectVO> resultList = ifxMapper.getOjtAunuriSubjectInsMappingList(aunuriMemberVO);
		return resultList;
	}
	
	/**
	 * 담당교수 (off-jt)개설교과에 메핑 Data를 List형태로 Data를 가져온다.
	 * @param aunuriMemberVO
	 * @return
	 * List<AunuriSubjectVO>
	 */
	@Override
	public List<AunuriSubjectVO> getOffJtAunuriSubjectInsMapping(AunuriMemberVO aunuriMemberVO) throws Exception {
		List<AunuriSubjectVO> resultList = ifxMapper.getOffJtAunuriSubjectInsMappingList(aunuriMemberVO);
		return resultList;
	}
	
	/**
	 * 기업현장교사 (ojt)개설교과에 메핑 Data를 List형태로 Data를 가져온다.
	 * @param aunuriMemberVO
	 * @return
	 * List<AunuriSubjectVO>
	 */
	@Override
	public List<AunuriSubjectVO> getOjtAunuriSubjectTutMapping(AunuriMemberVO aunuriMemberVO) throws Exception {
		List<AunuriSubjectVO> resultList = ifxMapper.getOjtAunuriSubjectTutMappingList(aunuriMemberVO);
		return resultList;
	}
	
	/**
	 * 학과전담자 (off-jt)개설교과에 메핑 Data를 List형태로 Data를 가져온다.
	 * @param aunuriMemberVO
	 * @return
	 * List<AunuriSubjectVO>
	 */
	@Override
	public List<AunuriSubjectVO> getOffJtAunuriSubjectCdpMapping(AunuriMemberVO aunuriMemberVO) throws Exception {
		List<AunuriSubjectVO> resultList = ifxMapper.getOffJtAunuriSubjectCdpMappingList(aunuriMemberVO);
		return resultList;
	}
	
	/**
	 * DB에서 교수의 담당 고숙련 과정 갯수를 조회하는 로직을 수행한다.
	 * @param aunuriMemberVO
	 * @return
	 * Integer
	 */
	@Override
	public int getOjtAunuriSubjectInsHSkillCnt(AunuriMemberVO aunuriMemberVO) throws Exception {
		int iResult = ifxMapper.getOjtAunuriSubjectInsHSkillCnt(aunuriMemberVO);
		return iResult;
	}
	
	
//
//	public List<Object> covertJsonToList(String jsonStr,List<Object> objList,Object obj){
//		 try {
//				Gson gson = new Gson();
//				JsonArray jsonArray = new JsonParser().parse(jsonStr).getAsJsonArray();
//			    System.out.println(jsonArray.size());
//			    for (int i = 0; i < jsonArray.size(); i++) {
//			        JsonElement str = jsonArray.get(i);
//			        obj = gson.fromJson(str, obj);
//			        System.out.println(obj);
//			        System.out.println(str);
//			        System.out.println("-------");
//			        objList.add(result);
//			    }
//			 } catch (Exception e) {
//			    e.printStackTrace();
//			 }
//	}
	
	public static void main(String[] args) {
		try {
		IfxServiceImpl ifx = new IfxServiceImpl();
		List<AunuriMemberVO> data = ifx.getAunuriMember(null);
		List<AunuriSubjectVO> data2 = ifx.getAunuriSubject(null);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
}