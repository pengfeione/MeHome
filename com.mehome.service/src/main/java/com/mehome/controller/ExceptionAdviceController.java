package com.mehome.controller;

import com.mehome.exceptions.InfoException;
import com.mehome.utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/5/17.
 */
@ControllerAdvice
public class ExceptionAdviceController {
    @Value("${cros}")
    private String cros;

    @ExceptionHandler(InfoException.class)
    @ResponseBody
    public ResponseEntity<Result> infoException(InfoException infoException) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result
                        .build()
                        .content(infoException.getMessage()));
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<Result> throwableException(Throwable throwable) {
        String msg = "";
        if (throwable instanceof MissingServletRequestParameterException) {
            String errorMsg = throwable.getMessage();
            msg = "缺少参数 : " + errorMsg.substring(errorMsg.indexOf("'") + 1, errorMsg.lastIndexOf("'"));
        } else if (throwable instanceof HttpMessageNotReadableException) {
            msg = throwable.getMessage();
            if (msg.contains("Required request body is missing")) {
                msg = "请至少传一个参数，或传‘{}’";
            } else {
                throwable.printStackTrace();
            }
        } else if (throwable instanceof BadSqlGrammarException) {
            System.out.println(((BadSqlGrammarException) throwable).getMessage());
        } else {
            throwable.printStackTrace();
            msg = throwable.getClass().getName();
        }
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.buildError(msg));
    }
}
