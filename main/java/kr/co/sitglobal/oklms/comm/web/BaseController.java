package kr.co.sitglobal.oklms.comm.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import kr.co.sitglobal.oklms.comm.vo.BaseObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class BaseController extends BaseObject {
	
//	private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected MessageSource messageSource;

	public void printRequest(HttpServletRequest pReq) {

		Enumeration<?> eParam = pReq.getParameterNames();
		while (eParam.hasMoreElements()) {
			String pName = (String) eParam.nextElement();
			String pValue = pReq.getParameter(pName);

			logger.info("#### RequestParamMap = " + pName + " : " + pValue);
		}
	}
}
