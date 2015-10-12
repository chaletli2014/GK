package com.goodsquick.exception;

public class SessionTimeoutException extends RuntimeException {
	private static final long serialVersionUID = -4458895622412067478L;

	public SessionTimeoutException(String paramString) {
		super(paramString);
	}

	public SessionTimeoutException(String paramString, Throwable paramThrowable) {
		super(paramString, paramThrowable);
	}
}
