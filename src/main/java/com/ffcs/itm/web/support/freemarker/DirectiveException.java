package com.ffcs.itm.web.support.freemarker;

public class DirectiveException extends RuntimeException {
	private static final long serialVersionUID = -2812607406643706780L;
	
	public DirectiveException() {
        super();
    }

    public DirectiveException(String message) {
        super(message);
    }

    public DirectiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public DirectiveException(Throwable cause) {
        super(cause);
    }

    protected DirectiveException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
