package com.mehome.utils;

/**
 * Created by pengf on 2014/9/26.
 */
public class SetAPIResultUtil {
    public static void setFail(APIBaseResult result) {
        result.setCode(ReturnCode.FAIL);
    }

    public static void setSuccess(APIBaseResult result) {
        result.setCode(ReturnCode.SUCCESS);
        result.setMsg(ReturnCode.MSG_SUCCESS);
    }
}
