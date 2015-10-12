package com.goodsquick.exception;

public class LoginException extends RuntimeException {
	private static final long serialVersionUID = -4458895622412067478L;

	public LoginException(String paramString) {
		super(paramString);
	}

	public LoginException(String paramString, Throwable paramThrowable) {
		super(paramString, paramThrowable);
	}
}
