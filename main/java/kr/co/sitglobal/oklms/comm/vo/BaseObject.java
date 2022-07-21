package kr.co.sitglobal.oklms.comm.vo;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;

import egovframework.com.cmm.EgovMessageSource;

public class BaseObject {

	@Resource(name = "egovMessageSource")
	protected EgovMessageSource egovMessageSource;

	public boolean getErrReturn(Exception exception, Map<String, Object> resultMap) {

		if (exception instanceof DuplicateKeyException) {
			resultMap.put("code", egovMessageSource.getMessage("sitglobal.rtn_code.NOK", null, Locale.getDefault()));
			resultMap.put("msg", egovMessageSource.getMessage("sitglobal.rtn_code.DUP", null, Locale.getDefault()));
		} else if (exception instanceof Exception) {
			resultMap.put("code", egovMessageSource.getMessage("sitglobal.rtn_code.NOK", null, Locale.getDefault()));
			resultMap.put("msg", egovMessageSource.getMessage("sitglobal.rtn_code.GENERAL_ERROR_MSG", null, Locale.getDefault()));
		} else {
			return false;
		}

		return true;
	}
}
