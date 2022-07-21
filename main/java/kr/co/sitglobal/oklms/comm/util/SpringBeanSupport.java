package kr.co.sitglobal.oklms.comm.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

 /**
 * ContextLoadListender 또는 DispatcherServlet에 로딩된 Bean 객체를 반환하기 위한 용도.
* 이진근
 */
public class SpringBeanSupport {

	public static Object getBean(final String beanId) throws Exception {

		Object beanObject = null;
		ServletContext sc;
		HttpSession hs;
		WebApplicationContext webApplicationContext;
		

		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = servletRequestAttribute.getRequest();
		
		// DispatcherServlet으로 로딩된 context를 가져 온다.
		webApplicationContext = RequestContextUtils.getWebApplicationContext((ServletRequest) request);

		// 빈을 검색해서 해당 빈 오브젝트를 가져 온다.
		if (webApplicationContext.containsBean(beanId)) {
			beanObject = webApplicationContext.getBean(beanId);
			return beanObject;
		}

		hs = servletRequestAttribute.getRequest().getSession(true);
		sc = hs.getServletContext();

		// ContextLoaderListener으로 로딩된 context를 가져 온다.
		webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(sc);

		if (webApplicationContext.containsBean(beanId)) {
			beanObject = webApplicationContext.getBean(beanId);
			return beanObject;
		}
		return beanObject;
	}
	
	public static Object getBean(final HttpServletRequest request, final String beanId) throws Exception {

		Object beanObject = null;
		ServletContext sc;
		HttpSession hs;
		WebApplicationContext webApplicationContext;
		// DispatcherServlet으로 로딩된 context를 가져 온다.
		webApplicationContext = RequestContextUtils.getWebApplicationContext(request);

		// 빈을 검색해서 해당 빈 오브젝트를 가져 온다.
		if (webApplicationContext.containsBean(beanId)) {
			beanObject = webApplicationContext.getBean(beanId);
			return beanObject;
		}

		hs = request.getSession();
		sc = hs.getServletContext();

		// ContextLoaderListener으로 로딩된 context를 가져 온다.
		webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(sc);

		if (webApplicationContext.containsBean(beanId)) {
			beanObject = webApplicationContext.getBean(beanId);
			return beanObject;
		}
		return beanObject;
	}
}
