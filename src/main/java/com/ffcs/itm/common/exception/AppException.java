package com.ffcs.itm.common.exception;

public class AppException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8053876632622771629L;
	private String errorCode;

	public AppException() {
        super();
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    protected AppException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    public AppException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
    
    public AppException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

	public String getErrorCode() {
		return errorCode;
	}
}
