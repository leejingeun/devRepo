/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
* 이진근    2016. 9. 21.         First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.comm.util;

import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



 /**
 * com-servlet.xml 파일에서 선언된 프로퍼티 파일의 값 핸들링 한다.
* 이진근
 * @since 2016. 9. 21.
 */
public class PropertyUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyUtil.class);
	
	/**
	 * .properties 파일을 찾기 위한 기본 경로
	 * .properties 파일은 resource 폴더 하위에 존재 하지만 실제 배포되는 위치는 classes을 root 경로로 설정
	 * */
	public final String BASE_RESOURCE = PropertyUtil.class.getResource("").getPath().substring(0, PropertyUtil.class.getResource("").getPath().lastIndexOf("kr"));
	
	//private final String BASE_RESOURCE = getClass().getClassLoader().getResource("").getPath();			
	
	/** 프로퍼티 설정파일 위치 목록 */
	private List<String> locations;

	/** 프로퍼티 설정정보를 가진 객체  */
	private CompositeConfiguration config;

	/**
	 * 생성자
	 */
	public PropertyUtil() {

	}
	/**
	 * 프로퍼티 설정 객체
	 * @return
	 */
	public CompositeConfiguration getConfig() {
		return this.config;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
		this.init();
	}
	
	/**
	 * 초기화
	 */
	public void init() {
		config = new CompositeConfiguration();
		for (String location : locations) {
			try {
				//URL url = getClass().getClassLoader().getResource(location);
				config.addConfiguration(new PropertiesConfiguration(BASE_RESOURCE + location));

				LOGGER.debug("##### PropertyUtil SUCCESS #####");
			} catch (ConfigurationException e) {
				LOGGER.error("##ERROR");
				LOGGER.error( e.getMessage() + "");
			}
		}
	}
	
	
	public static void main(String[] args){
	}
	
	/**
	 * 프로퍼티 키의 값을 가져 옵니다.
	 * @param key 프로퍼티 키
	 * @return 프로퍼티 값
	 */
	public String getProperty(String key) {
		return this.config.getString(key);
	}
	
}

