package kr.co.sitglobal.oklms.commbiz.util;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;



//import org.codehaus.jackson.map.ObjectMapper; // spring 3.x , jackson 1.x
import com.fasterxml.jackson.databind.ObjectMapper; // spring 4.x , jackson 2.x

import egovframework.com.cmm.service.Globals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;


@Component("bizUtil")
@Scope("prototype")
@SuppressWarnings( {"rawtypes", "unchecked"} )
public class BizUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(BizUtil.class);


	/** 기본형식(yyyyMMddHHmmss)*/
	private static final String DF_BASE = "yyyyMMddHHmmss";
	/** 기본일자형식(yyyyMMdd) */
	private static final String DF_DATE = "yyyyMMdd";
	/** 기본시간형식(HHmmss) */
	private static final String DF_TIME = "HHmmss";

	/** 년월일 Display 형식 (yyyy-MM-dd) */
	private static final String DF_DATE_DP = "yyyy-MM-dd";
	/** 기본년월일시분형식 (yyyyMMddHHmm) */
	private static final String DF_YYYYMMDDHHMM = "yyyyMMddHHmm";

	// -------------------------------------------------------------------------
	// 문자열 --> 날짜 변경
	// -------------------------------------------------------------------------

	/**
	 * format형태인 String데이타를 java.util.Date로 변환
	 * 다음과 같이 지정형식보다 많은 데이터값이 들어오면 오류가 발생한다.
	 * 단, 지정 형식과 다른 데이터가 입력되면 널을 반환한다.
	 * BizUtil.toDate("20160321124040", "yyyyMMddHHmm");
	 * BizUtil.toDate("20160321124040", "yyyyMMdd");
	 * BizUtil.toDate("2016", "yyyy");
	 * BizUtil.toDate("201603", "yyyy");
	 * BizUtil.toDate("20160321", "yyyy-MM-dd");
	 * ※ 위의 결과에서 볼수 있듯이 지정형식과 같으면서 데이터가 많을 경우
	 * 원하는 결과값과 다른 결과를 반환하므로 사용시 주의해서 사용하도록 한다.
	 *
	 * 단 현재는 private 으로 사용하여 기본적인 것만 사용한다.
	 *
	 * @param dateString 변경대상이 되는 일자문자열
	 * @param dateFormat 날짜형식
	 * @return java.util.Date
	 */
	private static Date toDate(String dateString, String dateFormat){
		if (dateString == null || dateString.length() == 0)
			return null;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			return sdf.parse(dateString);
		} catch (Exception e) {
			return null;
		}

	}

	// -------------------------------------------------------------------------
	// 문자열 --> 문자열날짜형 변경
	// -------------------------------------------------------------------------

	/**
	 * 입력형식으로 된 현재날짜의 문자열을 반환한다.
	 * <pre>
	 *  예)
	 *      String dateTimeString = BizUtil.getCurrentDateString("yyyyMMddHHmm");
	 *      String dateTimeString = BizUtil.getCurrentDateString("yyyyMMdd");
	 * </pre>
	 * @param dateFormat 날짜형식
	 * @return 현재날짜에 대한 문자열
	 */
	public static String getCurrentDateString(String dateFormat) {
		Date currentDate = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(currentDate);
	}

	/**
	 * 입력형식으로 된 현재날짜의 문자열을 반환한다. 기본 포맷으로 반환
	 * <pre>
	 *  예)
	 *      String dateTimeString = BizUtil.getCurrentDateString();	"yyyy-MM-dd"
	 * </pre>
	 * @param dateFormat 날짜형식
	 * @return 현재날짜에 대한 문자열
	 */
	public static String getCurrentDateString() {
		return getCurrentDateString(DF_DATE_DP);
	}

	/**
	 * 기본일자형식({@link DF_DATE})으로 된 날짜를 기본문자열({@link DF_DATE_DP})로 반환한다.
	 * @return 날짜(yyyy-MM-dd)
	 */
	public static String toDateString(String dateString){
		return toDateString(dateString, DF_DATE, DF_DATE_DP);
	}

	/**
	 * 지정된일자형식으로된 날짜를 된 기본문자열({@link DF_DATE_DP})로 반환한다.
	 * @return 날짜(yyyy-MM-dd)
	 */
	public static String toDateString(String dateString, String inDateFormat){
		return toDateString(dateString, inDateFormat, DF_DATE_DP);
	}

	/**
	 * java.util.Date 인 데이타를 날짜형식에 맞게 java.lang.String으로 변환
	 * @param date 변경대상이 되는 일자
	 * @param dateFormat 날짜형식
	 * @return 입력된 날짜형식으로 변환된 날짜문자열
	 */
	public static String toDateString(Date date, String dateFormat) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}

	/**
	 * 오늘날짜를 기준으로 일자를 증감한다.
	 * @param date 기준일자
	 * @param nDay 증감(+ or -) 일수 nDay는 다음날부터 계산하기 때문에 오늘 일수를 포함을 원하면 -1을 해야함.
	 * @return
	 */
	public static Date addDate(int nDay) {
		Calendar cal = Calendar.getInstance();
		return addDate(cal.getTime(), nDay);
	}

	/**
	 * 기준일에서 일자를 증감한다.
	 * @param date 기준일자
	 * @param nDay 증감(+ or -) 일수 nDay는 다음날부터 계산하기 때문에 오늘 일수를 포함을 원하면 -1을 해야함.
	 * @return
	 */
	public static Date addDate(Date date, int nDay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, nDay);
		return cal.getTime();
	}

	/**
	 * 기준일에서 일자를 증감한다.
	 * @param dateString 기준일자(yyyyMMdd)
	 * @param nDay 증감(+ or -) 일수
	 * @return
	 */
	public static String addDate(String dateString, int nDay) {
		Date date = addDate(toDate(dateString, DF_DATE), nDay);
		return toDateString(date, DF_DATE);
	}

	/**
	 * String 형태의 날짜를 날짜형식의 포맷하여 String형으로 변환
	 *
	 * <pre>
	 *  예)
	 *      String dateTimeString = BizUtil.toDateString("20160321120531", "yyyyMMddHHmmss", "yyyy-MM-dd" );	2016-03-21
	 *      String dateTimeString = BizUtil.toDateString("201603211205", "yyyyMMddHHmm", "yyyy-MM-dd" );		2016-03-21
	 *      String dateTimeString = BizUtil.toDateString("20160321", "yyyyMMdd", "yyyy-MM-dd" );				2016-03-21
	 *      String dateTimeString = BizUtil.toDateString("120531", "HHmmss", "HH:mm:ss" );						12:05:31
	 * </pre>
	 * @param dateString 날짜형문자
	 * @param inDateFormat dateString에 포맷형식
	 * @param dateFormat 변환시킬 날짜형식
	 * @return 변경후날짜형식으로 변환된 날짜문자열
	 */

	public static String toDateString(String dateString, String inDateFormat, String dateformat){

		if(StringUtils.isEmpty(dateString))
			return "";

		try{
			Date date = toDate(dateString, inDateFormat);
			return DateFormatUtils.format(date, dateformat);
		}catch(Exception e){
			return "";
		}

	}

	/**
	 * String 형태의 날짜를 날짜형식의 포맷하여 String형으로 변환
	 *
	 * <pre>
	 *  예)
	 *      String dateTimeString = BizUtil.toLocaleDateString("Tue Mar 22 11:04:47 KST 2016 ", "EEE MMM dd HH:mm:ss Z yyyy" , Locale.ENGLISH , "yyyy-MM-dd" );		// 2016-03-22
	 * </pre>
	 * @param dateString 날짜형문자
	 * @param inDateFormat dateString에 포맷형식
	 * @param dateFormat 변환시킬 날짜형식
	 * @return 변경후날짜형식으로 변환된 날짜문자열
	 * String
	 */
	public static String toLocaleDateString(String dateString, String inDateFormat, Locale locale , String dateformat){
		try{
			  SimpleDateFormat parserSDF = new SimpleDateFormat( inDateFormat , locale);
			  Date date = parserSDF.parse(dateString);

			return DateFormatUtils.format(date, dateformat);
		}catch(Exception e){
			return "";
		}

	}

	public static Object convertMapToObject(Map<String, Object> map, Class<?> pClass) {

		ObjectMapper lObjectMapper = new ObjectMapper();

		return lObjectMapper.convertValue(map, pClass);

	}

	/**
	 * AJAX로 넘어온 map을 VO클래스로 변환환하여 VO의 setter 메소드에 저장.
	 * [※ commandMap으로 넘어온 key 값중 vo클래스의 setter 메소드가 있는 경우만 값을 매핑하여 저장]
	 *
	 * convertMapToObject를 하면 Map의 형태를 VO 클래스로 변환하여 사용할 수 있다.
	 * 단 이 경우에는 JSP의 name의 값들이 VO 클래스에서 존재를 해야만 사용할 수 있다.
	 * 그래서 convertMapToObject 를 사용하여 오류가 날 경우에 사용
	 * <pre>
	 *  예)
	 *      JSP의 name List = code, codeName, type
	 *      VO 클래스 변수 List = code, codeName, tyep 모두 존재할 경우  convertMapToObject 메소드를 사용
	 *
	 *   	JSP의 name List = code, codeName, type
	 *   	VO 클래스 변수 List = code, type (codeName은 존재하지 않음) mapToVO 메소드 사용
	 *
	 * 사용법 : VO vo = (VO) bizUtil.mapToVO(commandMap, VO.class);
	 * </pre>
	 * @param 서버로 넘어온 Map
	 * @param Class 변환시킬 VO 클래스
	 * @return Object vo
	 */
	public Object mapToVO(Map<String, Object> map, Class<?> clazz ){

		Method[] methods = clazz.getMethods();				// VO 클래스의 모든 method
		Iterator<String> it = map.keySet().iterator();

		try{
			Object objVO = clazz.newInstance();
			while(it.hasNext()){

				String key = it.next();						// JSP의 변수
				Object value = map.get(key);				// 변수의 VALUE 값
				String searchMethodName = "set" + StringUtils.capitalize(key);		// setter method name

				for(Method method : methods){
					String methodName = method.getName();
					if(searchMethodName.equals(methodName)){
						Type[] type = method.getParameterTypes();
						String typeName = type[0].toString();		// setter method의 파라미터 type 명[java.lang.String arg0]
						Object objValue = null;
						String str = (String)value;
						if(typeName.indexOf("String")>0){
							// Class[] paramTypes = { new String().getClass() };
							// Method m = objVO.getClass().getDeclaredMethod(methodName, paramTypes);
							objValue = new String((String)value );
						} else if(typeName.indexOf("int")>0 || typeName.indexOf("Integer")>0){
							str = str.replaceAll("\\,", "");
							str = str.replaceAll("\\.", "");
							if(StringUtils.isNotEmpty(str)){
								objValue = new Integer(Integer.parseInt(str)) ;
							}
						} else if(typeName.indexOf("long")>0 || typeName.indexOf("Long")>0){
							str = str.replaceAll("\\,", "");
							str = str.replaceAll("\\.", "");
							if(StringUtils.isNotEmpty(str)){
								objValue = new Long(Long.parseLong(str)) ;
							}
						} else if(typeName.indexOf("double")>0 || typeName.indexOf("Double")>0){
							str = str.replaceAll("\\,", "");
							if(StringUtils.isNotEmpty(str)){
								objValue = new Double(Double.parseDouble(str)) ;
							}
						} else if(typeName.indexOf("float")>0 || typeName.indexOf("Float")>0){
							str = str.replaceAll("\\,", "");
							if(StringUtils.isNotEmpty(str)){
								objValue = new Float(Float.parseFloat(str));
							}
						}

						if(objValue!=null){
							Object[] objs = { objValue };
							method.setAccessible(true);
							method.invoke(objVO, objs);
						}
					}
				}
			}	// end while
			return objVO;
		}catch(Exception e){
			LOGGER.debug("***** ERROR : " + clazz + " 변환 FAIL *****");
		}
		return null;
	}

	/**
	 * Get방식으로 넘어온 URL의 쿼리 문자열을 Map 형태로 변환해준다.
	 * String query = "http://local.test.or.kr/oklms/sys/bbs/savAnnnBbsMngm.do?tranBbsId=A00000000000001&tranBbctId=0000000000003";
	 * String query = "tranBbsId=A00000000000001&tranBbctId=0000000000003";
	 * @param query
	 * @return
	 * Map<String,String>
	 */
	public static Map<String, String> getURLQueryToMap(String query) {
		if( -1 < StringUtils.indexOf(query, "http") ){
			try {
				query =	new URL(query).getQuery();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		query = StringUtils.substringAfter(query, "?");

		String[] params = query.split("&");
		Map<String, String> map = new HashMap<String, String>();
		for (String param : params) {
			String name = param.split("=")[0];
			String value = "";
			if( 1 < param.split("=").length ){
				value = param.split("=")[1];
			}
			map.put(name, value);
		}
		return map;
	}

	public static List<Map<String, String>> getURLQueryToList(String query) {
		if( -1 < StringUtils.indexOf(query, "http") ){
			try {
				query =	new URL(query).getQuery();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		query = StringUtils.substringAfter(query, "?");

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String[] params = query.split("&");
		for (String param : params) {
			Map<String, String> map = new HashMap<String, String>();
			String name = param.split("=")[0];
			String value = "";
			if( 1 < param.split("=").length ){
				value = param.split("=")[1];
			}
			map.put(name, value);
			list.add(map);
		}
		return list;
	}

	/**
	 * 과목구분코드(01:ojt, 02:offjt)에 진행중인 개설교과에 속한 Left 메뉴에 Key가 되는 파라메터를 세션으로 저장하는 공통 메소드
	 * @param subjectTraningType //과목구분코드(01:ojt, 02:offjt)
	 * @param yyyy 학년도
	 * @param term 학기
	 * @param subjectCode 교과목코드
	 * @param subjectName 교과목명
	 * @param classs 분반
	 * @param weekCnt 학습주차
	 * @param lecMenuMarkYn 교과별메뉴 강의표시여부
	 * @author jglee
	 * @return
	 */
	public static void setLecInfo(HttpServletRequest request, @RequestParam Map<String, Object> paramMap){

		LOGGER.debug("############################################## ");
		LOGGER.debug("#### paramMap.toString() : " + paramMap.toString() );
		LOGGER.debug("############################################## ");

		String subjectTraningType	= StringUtils.defaultString( (String)paramMap.get("subjectTraningType") , "" ); 	//과목구분코드(OJT:OJT, OFF-JT:OFF)
		String yyyy	= StringUtils.defaultString( (String)paramMap.get("yyyy") , "" ); 					//학년도
	    String term	= StringUtils.defaultString( (String)paramMap.get("term") , "" ); 					//학기
	    String subjectCode	= StringUtils.defaultString( (String)paramMap.get("subjectCode") , "" ); 	//교과목코드
	    String subjectName	= StringUtils.defaultString( (String)paramMap.get("subjectName") , "" ); 	//교과목명
	    String classs	= StringUtils.defaultString( (String)paramMap.get("classs") , "" ); 			//분반
	    String weekCnt	= StringUtils.defaultString( (String)paramMap.get("weekCnt") , "" ); 			//학습주차
	    String lecMenuMarkYn	= StringUtils.defaultString( (String)paramMap.get("lecMenuMarkYn") , "" ); 	//교과별메뉴 강의표시여부
	    
	    String subjectType	= StringUtils.defaultString( (String)paramMap.get("subjectType") , "" ); 	// 학부/고숙련 ( NORMAL / HSKILL )
	    String onlineType	= StringUtils.defaultString( (String)paramMap.get("onlineType") , "" ); 	// 온라인학습타입
	    String preSubjectCode	= StringUtils.defaultString( (String)paramMap.get("preSubjectCode") , "" ); // 이전교과 코드 (진행중인 교과X)
	    
	    
		LOGGER.debug("################################# ");
		LOGGER.debug("#### subjectTraningType : " + subjectTraningType );
		LOGGER.debug("#### yyyy : " + yyyy );
		LOGGER.debug("#### term : " + term );
		LOGGER.debug("#### subjectCode : " + subjectCode );
		LOGGER.debug("#### subjectName : " + subjectName );
		LOGGER.debug("#### classs : " + classs );
		LOGGER.debug("#### weekCnt : " + weekCnt );
		LOGGER.debug("#### lecMenuMarkYn : " + lecMenuMarkYn );
		LOGGER.debug("#### preSubjectCode : " + preSubjectCode );
		LOGGER.debug("#### onlineType : " + onlineType );
		LOGGER.debug("################################# ");

		// 강의관리 관련 Key 세션 설정
		HttpSession session = request.getSession(true);
		session.setAttribute(Globals.YYYY , yyyy );         		// 학년도
		session.setAttribute(Globals.TERM , term );      			// 학기
		session.setAttribute(Globals.SUBJECT_NAME , subjectName );  // 교과목명
		session.setAttribute(Globals.SUBJECT_CODE , subjectCode );  // 교과목코드
		session.setAttribute(Globals.CLASS , classs );      		// 분반
		
		session.setAttribute(Globals.SUBJECT_TRANING_TYPE , subjectTraningType );      		// 훈련타입 ( OJT / OFF )
		session.setAttribute(Globals.SUBJECT_TYPE , subjectType );      		// 학부/고숙련 ( NORMAL / HSKILL )
		session.setAttribute(Globals.ONLINE_TYPE , onlineType );      		// 온라인학습타입
		
		session.setAttribute(Globals.PRE_SUBJECT_CODE , preSubjectCode ); // 이전교과
		
		session.setAttribute(Globals.WEEK_CNT , weekCnt );    		// 학습주차
		session.setAttribute("subjectTraningType" , subjectTraningType );       //과목구분코드(OJT:OJT, OFF-JT:OFF)
		session.setAttribute("lecMenuMarkYn" , lecMenuMarkYn );     // 교과별메뉴 강의표시여부
	}

	/**
	 * 과목구분코드(01:ojt, 02:offjt)에 진행중인 개설교과에 속한 세션 초기화
	 * @param subjectTraningType //과목구분코드(01:ojt, 02:offjt)
	 * @param yyyy 학년도
	 * @param term 학기
	 * @param subjectCode 교과목코드
	 * @param subjectName 교과목명
	 * @param classs 분반
	 * @param weekCnt 학습주차
	 * @param lecMenuMarkYn 교과별메뉴 강의표시여부
	 * @author jglee
	 * @return
	 */
	public static void setEmptyLecInfo(HttpServletRequest request){

		// 강의관리 관련 Key 세션 설정
		HttpSession session = request.getSession(true);
		session.setAttribute(Globals.YYYY , "" );         		// 학년도
		session.setAttribute(Globals.TERM , "" );      			// 학기
		session.setAttribute(Globals.SUBJECT_NAME , "" );  // 교과목명
		session.setAttribute(Globals.SUBJECT_CODE , "" );  // 교과목코드
		session.setAttribute(Globals.CLASS , "" );      		// 분반
		session.setAttribute(Globals.WEEK_CNT , "" );    		// 학습주차
		session.setAttribute(Globals.SUBJECT_TRANING_TYPE , "" );    		 // 훈련타입 ( OJT / OFF )
		session.setAttribute(Globals.SUBJECT_TYPE , "" );    		 // 학부/고숙련 ( NORMAL / HSKILL )
		session.setAttribute(Globals.ONLINE_TYPE , "" );    	  // 온라인학습타입
		session.setAttribute(Globals.PRE_SUBJECT_CODE , "" );    	  // 이전교과 코드 (진행중인 교과X)
		
		session.setAttribute("subjectTraningType" , "" );       //과목구분코드(OJT:OJT, OFF-JT:OFF)
		session.setAttribute("lecMenuMarkYn" , "" );     // 교과별메뉴 강의표시여부
	}

	public static void setSessionCompanyInfo(HttpServletRequest request, @RequestParam Map<String, Object> paramMap){

		LOGGER.debug("############################################## ");
		LOGGER.debug("#### paramMap.toString() : " + paramMap.toString() );
		LOGGER.debug("############################################## ");

	    String bizCompanyId	= StringUtils.defaultString( (String)paramMap.get("companyId") , "" ); 	//선택된 회사정보
	    String bizCompanyName = StringUtils.defaultString( (String)paramMap.get("companyName") , "" );//선택된 회사정보

		LOGGER.debug("################################# ");
		LOGGER.debug("#### bizCompanyId : " + bizCompanyId );
		LOGGER.debug("#### bizCompanyName : " + bizCompanyName );
		LOGGER.debug("################################# ");

		//기업 관련 Key 세션 설정
		HttpSession session = request.getSession(true);
		session.setAttribute(Globals.BIZCOMPANY_ID , bizCompanyId );	// 회사아이디
		session.setAttribute(Globals.BIZCOMPANY_NAME , bizCompanyName );// 회사명
	}
	/**
	 *  학년 계산 
	 */
    public static String getLevel(String memId){
    	
    	String levelName = "";
    	int levelNum = 0;
    	if(memId.length()>4){
    		String temp = memId.substring(0, 4);
    		int yyyy = Integer.parseInt(temp);
    		int nowyyyy= Integer.parseInt(getCurrentDateString("yyyy"));
    		
    		if(yyyy<2000){
    			yyyy= Integer.parseInt( "20"+memId.substring(0, 2));
    		}
    		
    		// 현재년도 - (학번연도-1)   
    		// 2017 - (2016-1)      2학년 
    		levelNum = nowyyyy-(yyyy-1);    		
    	}
    	return levelName+levelNum;
    }
	/**
	 *  입학연도 계산 
	 */
    public static String getYearAdmission(String memId){
    	
    	String yyyy = "";
    	if(memId.length()>4){
    		yyyy = memId.substring(0, 4);

			if(Integer.parseInt(yyyy)<2000){
				yyyy= "20"+memId.substring(0, 2);
			}
    	}
    	return yyyy;
    }
	/**
	 *  요일
	 */
    public static String getDayWeek(String day){
    	
	        Calendar cal= Calendar.getInstance ();
	        
	        day = day.replaceAll("-", "");
	        
	        day = day.replaceAll("\\.", "");
	        
	        if(day==null || day.length()<8){
	        	return "";
	        }
	        String temp_yyyy = day.substring(0, 4);
	        String temp_mm = day.substring(4, 6);
	        String temp_dd = day.substring(6, 8);
	        
	        String dayName="";
	        cal.set(Calendar.YEAR, Integer.parseInt(temp_yyyy));
		    cal.set(Calendar.MONTH, Integer.parseInt(temp_mm)-1);
		    cal.set(Calendar.DATE, Integer.parseInt(temp_dd));
    
            switch (cal.get(Calendar.DAY_OF_WEEK)){
		    case Calendar.SUNDAY:
		    	dayName="일요일";
		        break;
		    case Calendar.MONTH:
		    	dayName="월요일";
		        break;
		    case Calendar.TUESDAY:
		    	dayName="화요일";
		        break;
		    case Calendar.WEDNESDAY:
		    	dayName="수요일";
		        break;
		    case Calendar.THURSDAY:
		    	dayName="목요일";
		        break;
		    case Calendar.FRIDAY:
		    	dayName="금요일";
		        break;
		    case Calendar.SATURDAY:
		    	dayName="토요일";
		        break;
		    }
            
            return dayName;
    }
    //해당월에 마지막일 반환
    public static int getMonthLastday(String day){
    	
        Calendar cal= Calendar.getInstance ();
        
        day = day.replaceAll("-", "");
        
        day = day.replaceAll("\\.", "");
        
        if(day==null || day.length()<8){
        	return 0;
        }
        String temp_yyyy = day.substring(0, 4);
        String temp_mm = day.substring(4, 6);
        String temp_dd = day.substring(6, 8);
         
        cal.set(Calendar.YEAR, Integer.parseInt(temp_yyyy));
	    cal.set(Calendar.MONTH, Integer.parseInt(temp_mm)-1);
	    cal.set(Calendar.DATE, Integer.parseInt(temp_dd));

	    int returnValue=  cal.getActualMaximum(Calendar.DAY_OF_MONTH);  
        
        return returnValue;
    }
    
    
	public static void main(String[] arg){

//		String dateTimeString = BizUtil.toLocaleDateString("Tue Mar 22 11:04:47 KST 2016 ", "EEE MMM dd HH:mm:ss Z yyyy" , Locale.ENGLISH , "yyyy-MM-dd" );
//		System.out.println(dateTimeString);
	}
}

