package kr.co.sitglobal.oklms.comm.exception;

import egovframework.rte.fdl.cmmn.exception.BaseException;

public class CommonServiceException extends BaseException {

	private static final long serialVersionUID = 1095892622510871937L;

	public CommonServiceException(String messageKey, Throwable causeThrowable) {
		super(messageKey, causeThrowable);
	}

	public CommonServiceException(String messageKey) {
		super(messageKey);
	}

	public CommonServiceException(String messageKey, Object[] args, Throwable causeThrowable) {
		super(messageKey, args, causeThrowable);
	}

}