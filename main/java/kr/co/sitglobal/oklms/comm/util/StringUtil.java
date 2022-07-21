package kr.co.sitglobal.oklms.comm.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 문자형식 Util: 문자형식 처리를 위한 콤포넌트 class
 * @author hsw (hsw@anamit.com)
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *
 *       수정일                         수정자                              수정내용
 *  ----------------    ------------    ---------------------------
 *  2014.06.19          hsw             최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
@Component
public class StringUtil {

	private static final Logger logger = LogManager.getLogger(StringUtil.class);

	// constructor
    public StringUtil() {}

    /**
	 * 문자열 null 처리/ trim 처리
	 * @param		(String) 문자형식
	 * @return 		(String) 입력 받은 문자형식을 null 경우 빈문자열로, 아닐경우 공백처리하여 반환
	 */
    public static String trimString(String data){
        try{
			if(data == null)
				return "";
	        return data.trim();
        }catch(Exception e){
        	logger.error(e.toString());
        	return "";
        }
    }

    /**
	 * 문자열 null 처리/ trim 처리
	 * @param		(String) 원본문자
	 * @param		(String) 패턴
	 * @return 		(String) 입력 받은 문자형식에서 patter 발견되면 pattern 까지 자르고 trimString 처리해서  반환
	 */
    public static String trimString(String data, String pattern){
        try{
			if(data == null)
				return "";

			int offset = data.indexOf(pattern);
			if(offset >= 0) data = data.substring(0, offset);
	        return data.trim();
        }catch(Exception e){
        	logger.error(e.toString());
        	return "";
        }
    }

    /**
	 * 문자열 HTML Tag 제거
	 * @param		(String) 문자형식
	 * @return 		(String) 입력 받은 문자에서 html tag 제거 후 반환
	 */
    public static String stripHTML(String htmlStr) {
    	try {
    		htmlStr = htmlStr.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
    		htmlStr = replaceWebSpecialCharcters(htmlStr);

    		return htmlStr;
    	} catch (Exception e) {
    		logger.error(e.toString());
    		return null;
    	}
    }

    /**
	 * 문자열 escape HTML 문자 제거
	 * @param		(String) 문자형식
	 * @return 		(String) 입력 받은 문자에서 html tag 제거 후 반환
	 */
    public static String replaceWebSpecialCharcters(String htmlStr) {
    	try {
    		htmlStr = htmlStr.replaceAll("&nbsp;"," ");
    		htmlStr = htmlStr.replaceAll("&quot;","'");
    		htmlStr = htmlStr.replaceAll("&lsquo;","‘");
    		htmlStr = htmlStr.replaceAll("&rsquo;","’");
    		htmlStr = htmlStr.replaceAll("&ldquo;","“");
    		htmlStr = htmlStr.replaceAll("&rdquo;","”");
    		htmlStr = htmlStr.replaceAll("&lt;","<");
    		htmlStr = htmlStr.replaceAll("&gt;",">");
    		htmlStr = htmlStr.replaceAll("&hellip;","…");
    		htmlStr = htmlStr.replaceAll("&middot;","·");
    		htmlStr = htmlStr.replaceAll("&amp;","&");

    		return htmlStr;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return htmlStr;
    	}
    }

    /**
	 * 숫자형 null처리/trim 처리
	 * @param		(String) 숫자형식
	 * @return 		(String) 입력 받은 문자형식을 null 또는 빈문자열일 경우 0을 반환한다.
	 */
    public static String trimNumber(String data){
        try{
	        if(data == null)  return "0";
	        data = data.trim();
    	    if(data.equals(""))	return "0";

        	return data;
        }catch(Exception e){
        	logger.error(e.toString());
        	return "0";
        }
    }

    /**
	 * 문자형 > 숫자형 변환
	 * @param		(String) 숫자형식
	 * @return 		(String) 입력 받은 문자형식을 null 또는 빈문자열일 경우 0을 반환한다.
	 */
    public static int convertNumberByString(String data){
        try{
        	int returnValue = 0;
	        if(data == null)
	        	returnValue = 0;
	        data = data.trim();
    	    if(data.equals(""))
    	    	returnValue = 0;

    	    try{
    	    	returnValue = Integer.parseInt(data);
    	    }catch(Exception e1){
    	    	returnValue = 0;
    	    }

        	return returnValue;
        }catch(Exception e){
        	logger.error(e.toString());
        	return 0;
        }
    }

    /**
	 * 문자형 치환
	 * @param		(String) 원본 문자
	 * @param		(String) 비교 문자
	 * @param		(String) 치환 문자
	 * @return 		(String) 입력 받은 문자형식에서 비교문자를 추출하여 치환문자로 변환 후 반환
	 */
    public static String replaceAll(String text,String repl,String with){
		return org.apache.commons.lang.StringUtils.replace(text,repl,with);
    }

    /**
	 * 숫자형식 체크
	 * @param		(String) 원본 문자
	 * @return 		(String) 입력 받은 문자가 숫자형인지 확인 후  true/ false 반환
	 */
    public static boolean  isNumeric(String source){
        return org.apache.commons.lang.StringUtils.isNumeric(source);
    }

    /**
	 * 이메일 형식 체크
	 * @param		(String) 원본 문자열
	 * @return 		(String) 입력 받은 문자열이 이메일 형식인지 확인 후  true/ false 반환
	 */
    public static boolean isEmail(String data){
    	Matcher matcher = Pattern.compile("(^[_.0-9a-z-]+)@(([0-9a-z][0-9a-z-]+.)+)([a-z]{2,3}$)").matcher(data);
    	return matcher.find();
    }

    /**
	 * 핸드폰 번호 형식 체크
	 * @param		(String) 원본 문자열
	 * @return 		(String) 입력 받은 문자열이 핸드폰 번호 형식인지 확인 후  true/ false 반환
	 */
    public static boolean isMobileNo(String data){
    	Matcher matcher = Pattern.compile("^01[016789]{1}-(?:\\d{3}|\\d{4})-\\d{4}$").matcher(data);
    	return matcher.find();
    }

    /**
	 * 전화번호 형식 체크
	 * @param		(String) 원본 문자열
	 * @return 		(String) 입력 받은 문자열이 전화번호 형식인지 확인 후  true/ false 반환
	 */
    public static boolean isPhoneNo(String data){
    	Matcher matcher = Pattern.compile("^\\d{2,3}-\\d{3,4}-\\d{4}$").matcher(data);
    	return matcher.find();
    }

    /**
	 * 주민등록번호 형식 체크
	 * @param		(String) 원본 문자열
	 * @return 		(String) 입력 받은 문자열이 주민등록번호 형식인지 확인 후  true/ false 반환
	 */
    public static boolean isRRN(String data){
    	Matcher matcher = Pattern.compile("[0-9]{2}(0[1-9]|1[012])(0[1-9]|1[0-9]|2[0-9]|3[01])-?[012349][0-9]{6}").matcher(data);
    	return matcher.find();
    }

    /**
	 * 사업자등록번호 형식 체크
	 * @param		(String) 원본 문자열
	 * @return 		(String) 입력 받은 문자열이 사업자등록번호 형식인지 확인 후  true/ false 반환
	 */
    public static boolean isCRN(String data){
    	Matcher matcher = Pattern.compile("^\\d{3}-\\d{2}-\\d{5}$").matcher(data);
    	return matcher.find();
    }
    
    
    /**
	 * 사업자등록번호 형식 체크 - 개인주민등록번호인경우
	 * @param		(String) 원본 문자열
	 * @return 		(String) 입력 받은 문자열이 사업자등록번호 형식인지 확인 후  true/ false 반환
	 */
    public static boolean isCRN1(String data){
    	Matcher matcher = Pattern.compile("^\\d{6}-\\d{7}$").matcher(data);
    	return matcher.find();
    }

    /**
	 * 파일 확장자 찾기
	 * @param		(String) 파일명
	 * @return 		(String) 입력 받은 파일명에서 문자열을 추출
	 */
    public static String getFileExt(String fileName){
 		try{
 		    String ext = "";
 		    int i = fileName.lastIndexOf(".");

 		    if(i>-1)	ext = fileName.substring(i+1);

 		    return ext;
 		}catch(Exception e){
 			logger.error(e.toString());
 			return "";
 		}
    }

    /**
	 * 파일 확장자를 제외한 파일이름 찾기
	 * @param		(String) 파일명
	 * @return 		(String) 입력 받은 파일명에서 문자열을 추출
	 */
    public static String getFileName(String fileName){
 		try{
 		    String name = "";
 		    int i = fileName.lastIndexOf(".");

 		    if(i>-1)	name = fileName.substring(0, i);

 		    return name;
 		}catch(Exception e){
 			logger.error(e.toString());
 			return "";
 		}
    }

    /**
	 * 문자열 Byte로 자르기
	 * @param		(String) 문자열
	 * @param		(int)	  최대 Byte 수
	 * @return 		(String) 입력 받은 문자열에서 최대 Byte를 초과하는 문자열을 자르고 반환
	 */
    public static String cutStringByte(String orig, int endIndex){
		try{
			byte[] byteString = orig.getBytes();

			if (byteString.length <= endIndex){
				return orig;
			}else{
				int minusByteCount = 0;
				for (int i = 0; i < endIndex; i++){
					minusByteCount += (byteString[i] < 0) ? 1 : 0;
				}
				if (minusByteCount % 2 != 0){
					endIndex--;
				}
				return new String(byteString, 0, endIndex);
			}
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}
	}

    /**
	 * 문자열 최대 길이(Byte)로 배열 처리
	 * @param		(String) 문자열
	 * @param		(int)	  최대 Byte 수
	 * @return 		(String) 입력 받은 문자열에서 최대 길이(Byte) 단위로 잘라 배열형식으로 반환
	 */
	public static ArrayList<String> alCutStringByte(String str, int endIndex){
		try{
			ArrayList<String> alReturnValue = new ArrayList<String>();
			if(str==null)
				return alReturnValue;
			else
				str = str.trim();

			String temp = str;
			String cutString = "";
			if(temp.getBytes().length <= endIndex) {
				alReturnValue.add(temp);
			}else{
				while (temp.getBytes().length > endIndex) {
					cutString = cutStringByte(temp, endIndex);
					alReturnValue.add(cutString);
					temp = temp.substring(cutString.length());
				}
				alReturnValue.add(temp);
			}

			return alReturnValue;
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}
	}

	/**
	 * 오브젝트 형태의 객체를 JSON string 으로 변환
	 * @param		(String) 원본 문자열
	 * @return 		(String) 입력 받은 문자열의 JSON String
	 */
    public static String objectToJson(Object data){
    	try{
    		Gson gson = new Gson();
        	return gson.toJson(data);
    	}catch(Exception e){
			logger.error(e.toString());
			return null;
		}
    }

    /**
     * JSON 문자열을 리스트 맵 형태로 리턴한다.
     * @param jsonString
     * @return
     */
    public static List<Map<String, Object>> jsonStringToListMap(String jsonString){
    	Gson gson = new Gson();
    	Type type = new TypeToken<List<Map<String, Object>>>(){}.getType();
    	return gson.fromJson(jsonString, type);
    }

    /**
     * JSON 문자열을 맵 형태로 리턴한다.
     * @param jsonString
     * @return
     */
    public static Map<String, Object> jsonStringToMap(String jsonString){
    	Gson gson = new Gson();
    	Type type = new TypeToken<Map<String, Object>>(){}.getType();
    	return gson.fromJson(jsonString, type);
    }

    /**
	 * 최초 리스트 데이터 로딩 여부 확인
	 * @param		(Map<String, Object>) 데이터 Param
	 * @return 		(boolean) 참진실/ 참거짓
	 */
    public static boolean isInitPageLoading(Map<String, Object> command){
    	try{
    		if(!trimString((String)command.get("countPerPage")).equals(""))
    			return true;
    		else
    			return false;

    	}catch(Exception e){
			logger.error(e.toString());
			return false;
		}
    }

    /**
	 * commandMap 받아서 String[] 배열 여부 체크 후 String[] 형식으로 리턴
	 * @param		(Map<String, Object>) 데이터 Param
	 * @param		(Strong) Hash Key
	 * @return 		(String[]) 배열 형식으로 리턴
	 */
    public static String[] convertStringArray(Map<String, Object> command, String key){
		String[] returnValue = null;		// 교재 연결 타입

		if(command.get(key) == null)
			return null;

		try{
			returnValue = (String[])command.get(key);
		}catch(Exception e){
			returnValue = new String[1];
			returnValue[0] = (String)command.get(key);
		}
		return returnValue;
	}

    /**
	 * 년도/학기/차수/반 정보의 화면 표시
	 * @param		(String) learnYear		년도
	 * @param		(String) term			학기
	 * @param		(String) seq			차수
	 * @param		(String) classNum		반
	 * @param		(boolean) fieldLabel	필드 Label
	 * @return 		(String) 년도-학기-차수-반 정보 리턴
	 */
    public static String getClassNumLabel(String learnYear, String term, String seq, String classNum, String separator){
    	try{
    		String classLabel = "";

    		if(StringUtil.trimString(separator).equals("")){
    			classLabel = learnYear + MessageUtil.getMessage("tit.years") +
    						convertNumberByString(term) + MessageUtil.getMessage("tit.term") +
    						convertNumberByString(seq) + MessageUtil.getMessage("tit.learnSeq") +
    						convertNumberByString(classNum) + MessageUtil.getMessage("tit.classNum");
    		}else{
    			classLabel = learnYear + separator + convertNumberByString(term) + separator + convertNumberByString(seq);
    			if(!"".equals(classNum)) { //null로넘어올대 추가안함
//    				classLabel += separator + (convertNumberByString(classNum) >= 10 ? classNum : "0"+convertNumberByString(classNum));
    				classLabel += separator + classNum;
    			}
    		}

    		return classLabel;
    	}catch(Exception e){
			logger.error(e.toString());
			return null;
		}
    }

    /**
	 * 컨텐츠에서 image tag의 src 추출
	 * @param		(String) 		content	컨텐츠
	 * @return 		(List<String>) 	파일 경로 배열 리턴
	 */
    public static List<String> getContentImageFileList(String content){
		try{
			Pattern nonValidPattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");

			List<String> result = new ArrayList<String>();

			Matcher matcher = nonValidPattern.matcher(content);
			while (matcher.find()) {
				result.add(matcher.group(1));
			}
			return result;

		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}
	}

    /**
     * HTML 특수문자
     */
    private static final String[][] XSS_VALUES = {
		{ "&", "&amp;" },
		{ "<", "&lt;" },
		{ ">", "&gt;" },
		{ "\'", "&#039;" },
		{ "\"", "&#034;" },
		{ "(", "&#40;" },
		{ ")", "&#41;" }
	};

	public static String equals(Object o, String eq, String d) {
		String returnStr = d;
		if (o != null) {
			if (eq.equals(o.toString())) {
				return eq;
			}
		}
		return returnStr;
	}

	public static boolean isNotNull(Object s) {
		return s != null;
	}

	public static boolean isNotEmpty(Object s) {
		return getString(s, "").length() > 0;
	}

	public static String getString(Object object) {
		return getString(object, null);
	}

	public static String getStringDefaultEmptry(Object object) {
		return getString(object, "");
	}

	private static String getString(Object object, String d) {
		if (object != null) {
			return object.toString().trim();
		}
		return d;
	}

	public static int getInt(Object object) {
		String s = getString(object, "0");
		int result = 0;
		try {
			result = Integer.parseInt(s);
		} catch (Exception e) {}
		return result;
	}

	/**
	 * script 라인을 제거하고 [[ &, <, >, ', ", (, ) ]] 태그를 코드값으로 치환한다.
	 * @param s
	 * @return
	 */
	public static String xssClean(String s) {
		if (s == null) return null;
		String output = s;
		while (true) {

			String lowserCaseOutput = output.toLowerCase();

			int i = lowserCaseOutput.indexOf("<script");

			String a = "";

			boolean isFirstSearch = false;

			if (i > -1) {
				isFirstSearch = true;
				a = output.substring(0, i);
			} else {
				break;
			}

			int q = lowserCaseOutput.indexOf("</script>");

			String b = "";

			if (isFirstSearch && (q > -1)) {
				b = output.substring(q + 9, output.length());
				output = a + b;
			} else if (isFirstSearch) {
				int t = lowserCaseOutput.indexOf("t>");
				if (t > -1) {
					output = a + output.substring(output.indexOf("t>") + 2, output.length());
				}
				t = lowserCaseOutput.indexOf("'>");
				if (t > -1) {
					output = a + output.substring(output.indexOf("'>") + 2, output.length());
				}
				t = lowserCaseOutput.indexOf("\">");
				if (t > -1) {
					output = a + output.substring(output.indexOf("\">") + 2, output.length());
				}

			}
		}
		s = output;
		for (String[] xss : XSS_VALUES) {
			s = s.replace(xss[0], xss[1]);
		}
		return s;
	}

	/**
	 * 숫자 반올림
	 * @param num : 반올림할 숫자
	 * @param point : 반올림할 자릿수
	 * @return
	 */
	public static String roundOff(float num, int point){
		String result = String.valueOf(Math.floor(num * Math.pow(10, point) + 0.5) / Math.pow(10, point));
		String cutResult = result.substring(result.indexOf("."));
		if(StringUtil.trimString(cutResult).equals(".0")){
			result = StringUtil.replaceAll(result, cutResult, "");
		}

		return result;
	}

}
