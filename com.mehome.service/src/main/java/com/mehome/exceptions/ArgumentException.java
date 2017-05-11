package com.mehome.exceptions;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by dell on 2015/11/29.
 */
public class ArgumentException extends RuntimeException {
    public ArgumentException() {
    }

    public ArgumentException(Throwable t) {
        super(t);
    }

    public ArgumentException(String msg, Throwable t) {
        super(msg, t);
    }

    public ArgumentException(String msg) {
        super(msg);
    }
}

