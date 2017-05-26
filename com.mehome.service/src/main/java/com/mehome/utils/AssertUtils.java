package com.mehome.utils;

import com.mehome.exceptions.InfoException;

/**
 * Created by Administrator on 2017/5/11.
 * 断言工具
 */
public class AssertUtils {
    /**
     * 若不是空则报异常
     *
     * @param object
     * @param message
     */
    public static void isNull(Object object, String message) {
        if (object == null || "".equals(object)) {
        } else {
            throw new InfoException(message);
        }
    }

    /**
     * 若不是空则报异常
     *
     * @param object
     * @param message
     */
    public static void isTrue(boolean object, String message) {
        if (object) {
            throw new InfoException(message);
        }
    }

    /**
     * 若是空
     *
     * @param object
     * @param message
     */
    public static void isNotNull(Object object, String message) {
        if (object == null || "".equals(object)) {
            throw new InfoException(message);
        }
    }
}
