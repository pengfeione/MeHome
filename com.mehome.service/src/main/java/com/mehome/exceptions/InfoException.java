package com.mehome.exceptions;

/**
 * Created by dell on 2015/11/29.
 */
public class InfoException extends RuntimeException {

    private String requestId;


    public InfoException() {
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public InfoException(Throwable t) {
        super(t);
    }

    public InfoException(String msg, Throwable t) {
        super(msg, t);
    }

    public InfoException(String msg) {
        super(msg);
    }
}

