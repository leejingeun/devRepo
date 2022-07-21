package egovframework.com.cmm.annotation;

/**
 * 컴포넌트의 포함 정보 표현을 위한 annotation 클래스
 * 기본적으로 Controller 클래스에 annotation을 부여하되, 
 * 하나의 Controller에 여러 개의 목록성 url mapping이 제공되는 경우에는
 * 메소드에 annotation을 부여한다. 
 * @author 공통컴포넌트 정진오
 * @since 2011.08.26
 * @version 2.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2011.08.26	정진오 		최초 생성
 *
 * </pre>
 */


import java.lang.annotation.Documented; 
import java.lang.annotation.ElementType; 
import java.lang.annotation.Retention; 
import java.lang.annotation.RetentionPolicy; 
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;



@Target({ElementType.TYPE}) 
@Retention(RetentionPolicy.RUNTIME) 
@Documented 
@Component 
public @interface OracleMapperScan { 
	String value() default "";
}

