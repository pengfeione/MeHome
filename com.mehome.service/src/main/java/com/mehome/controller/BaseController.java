package com.mehome.controller;

import com.mehome.exceptions.InfoException;
import com.mehome.utils.APIBaseResult;
import com.mehome.utils.SetAPIResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseController {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    protected APIBaseResult getAPIResult(APIBaseResult result) {
        return result;
    }

    public void printException(APIBaseResult result, String path, Exception e, Object param) {
        if (e instanceof InfoException) {
            log.info("\nRequestId:" + result.getRequestId()
                    + "\nRequestPath:" + path
                    + "\nParams:" + param
                    + "\nException:" + e.getMessage()
            );
            result.setMsg(e.getMessage());
            SetAPIResultUtil.setFail(result);
        } else {
            log.error("\n---------------------------------------------------------" +
                    "\nRequestId:" + result.getRequestId()
                    + "\nRequestPath:" + path
                    + "\nParams:" +
                    param +
                    "\n---------------------------------------------------------", e);
            result.setMsg("服务器接口异常！");
            SetAPIResultUtil.setFail(result);
        }
    }
}
