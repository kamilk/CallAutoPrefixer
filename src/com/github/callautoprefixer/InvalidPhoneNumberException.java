package com.github.callautoprefixer;

public class InvalidPhoneNumberException extends Exception {
	private static final long serialVersionUID = 2750673554227662849L;
	
	public InvalidPhoneNumberException() {
		super();
	}

	public InvalidPhoneNumberException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public InvalidPhoneNumberException(String detailMessage) {
		super(detailMessage);
	}

	public InvalidPhoneNumberException(Throwable throwable) {
		super(throwable);
	}
}
