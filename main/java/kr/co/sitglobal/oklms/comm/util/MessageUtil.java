package kr.co.sitglobal.oklms.comm.util;

import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

/**
 * 메시지 Util: 다국어 지원을 위한 메시지 처리 콤포넌트 class
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
public class MessageUtil  {
	
	private static final Logger logger = LogManager.getLogger(MessageUtil.class);
	
	@Autowired
	private static MessageSourceAccessor messageSourceAccessor;
	
	public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
		MessageUtil.messageSourceAccessor = messageSourceAccessor;
    }
	
	/**
	 * 다국어 메시지
	 * @param key 		(String) 메시지 키
	 * @return 			(String) 요청 메시지에 대항하는 값 반환
	 */
	public static String getMessage(String key) {
		try{
			return messageSourceAccessor.getMessage(key, key + "[no message]");
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}		
    }
	
	/**
	 * 다국어 메시지+언어코드 처리
	 * @param key 		(String) 그룹코드아이디
	 * @param lang		(String) 언어코드
	 * @return 			(String) 요청 메시지에 해당하는  값 반환
	 */
	public static String getMessage(String key, String lang) {
		try{
			Locale locale = null;
			lang = lang.toLowerCase();
			
			if(lang.equals("ko"))
				locale = Locale.KOREAN;
			else if(lang.equals("en"))
				locale = Locale.ENGLISH;
			else if(lang.equals("ja"))
				locale = Locale.JAPANESE;
			else if(lang.equals("zh"))
				locale = Locale.CHINESE;
			else if(lang.equals("zh_tw"))
				locale = Locale.TAIWAN;
			
			return messageSourceAccessor.getMessage(key, key + "[no message]", locale);
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}		
    }
	
	/**
	 * 다국어 메시지+파라미터
	 * @param key 		(String) 그룹코드아이디
	 * @param objs		(String[]) 메시지에 삽입할 파라미터 
	 * @return			(String) 요청 메시지에 해당하는  값 반환
	 */
	public static String getMessage(String key, Object[] objs) {
		try{
			return messageSourceAccessor.getMessage(key, objs, key + "[no message]");
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}		
    }
	
	/**
	 * 다국어 메시지+파라미터 JSTL Custom Tag
	 * @param key 		(String) 그룹코드아이디
	 * @param objs		(String[]) 메시지에 삽입할 파라미터 
	 * @return			(String) 요청 메시지에 해당하는  값 반환
	 */
	public static String getMessageParam(String key, String param) {
		try{
			String[] objs = StringUtil.trimString(param).split(",");
			for(int i=0;i<objs.length;i++)
				objs[i] = messageSourceAccessor.getMessage(objs[i], objs[i]);			
			
			return messageSourceAccessor.getMessage(key, objs, key + "[no message]");
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}		
    }
	
	/**
	 * 다국어 메시지+파라미터+언어코드
	 * @param key 		(String) 그룹코드아이디
	 * @param objs		(String[]) 메시지에 삽입할 파라미터
	 * @param lang		(String) 언어코드 
	 * @return 			(String) 요청 메시지에 해당하는  값 반환
	 */
	public static String getMessage(String key, Object[] objs, String lang) {
		try{
			Locale locale = null;
			lang = lang.toLowerCase();
			
			if(lang.equals("ko"))
				locale = Locale.KOREAN;
			else if(lang.equals("en"))
				locale = Locale.ENGLISH;
			else if(lang.equals("ja"))
				locale = Locale.JAPANESE;
			else if(lang.equals("zh"))
				locale = Locale.CHINESE;
			else if(lang.equals("zh_tw"))
				locale = Locale.TAIWAN;
			
			return messageSourceAccessor.getMessage(key, objs, key + "[no message]", locale);
		}catch(Exception e){
			logger.error(e.toString());
			return null;
		}		
    }
}
