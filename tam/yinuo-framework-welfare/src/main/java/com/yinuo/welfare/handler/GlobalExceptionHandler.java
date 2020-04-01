package com.yinuo.welfare.handler;


import com.alibaba.fastjson.JSONObject;
import com.yinuo.common.common.BusinessException;
import com.yinuo.common.common.ErrCodeAndMsg;
import com.yinuo.common.common.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常拦截处理器
 *
 * @author tfan
 */

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final String logExceptionFormat = "Capture Exception By GlobalExceptionHandler: Code: %s Detail: %s";

    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

/*

    //运行时异常
    @ExceptionHandler(RuntimeException.class)
    public String runtimeExceptionHandler(RuntimeException ex) {
        return resultFormat(1, ex);
    }
    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException ex) {
        return resultFormat(2, ex);
    }

    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    public String classCastExceptionHandler(ClassCastException ex) {
        return resultFormat(3, ex);
    }
    //IO异常
    @ExceptionHandler(IOException.class)
    public String iOExceptionHandler(IOException ex) {
        return resultFormat(4, ex);
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    public String noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        return resultFormat(5, ex);
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public String indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        return resultFormat(6, ex);
    }

    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public String requestNotReadable(HttpMessageNotReadableException ex) {
        return resultFormat(7, ex);
    }



    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    public String requestTypeMismatch(TypeMismatchException ex) {
        System.out.println("400..TypeMismatchException");
        return resultFormat(8, ex);
    }



    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public String requestMissingServletRequest(MissingServletRequestParameterException ex) {
        System.out.println("400..MissingServletRequest");
        return resultFormat(9, ex);
    }







    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public String request406(HttpMediaTypeNotAcceptableException ex) {
        System.out.println("406...");
        return resultFormat(11, ex);
    }

    //栈溢出
    @ExceptionHandler({StackOverflowError.class})
    public String requestStackOverflow(StackOverflowError ex) {
        return resultFormat(13, ex);
    }*/

    //运行时异常
    @ExceptionHandler({RuntimeException.class})
    public String request555(RuntimeException ex) {
        log.error(ex.getMessage(),ex);
        return JSONObject.toJSONString(HttpResult.failure("555",ex.getMessage()));
    }



    @ExceptionHandler({ServletRequestBindingException.class})
    public String request521(ServletRequestBindingException ex) {
        return JSONObject.toJSONString(HttpResult.failure("521",ex.getMessage()));
    }


    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public String request405(HttpRequestMethodNotSupportedException ex) {
        return JSONObject.toJSONString(HttpResult.failure("405",ex.getMessage()));
    }
    /**
     * 处理所有业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public String handleBusinessException(BusinessException e){
        log.error(e.getMessage(), e);
        return JSONObject.toJSONString(HttpResult.failure(e.getCode(),e.getMessage()));
    }
    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public String server500(RuntimeException ex) {
        log.error(ex.getMessage(),ex);
        return JSONObject.toJSONString(HttpResult.failure(ErrCodeAndMsg.SERVER_ERROR));
    }

    /**
     * 处理所有接口数据验证异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String  handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        return JSONObject.toJSONString(HttpResult.failure("601",/*
                e.getBindingResult().getAllErrors().get(0).getObjectName()+"属性"+*/ e.getBindingResult().getAllErrors().get(0).getDefaultMessage()));
    }
    //其他错误
    @ExceptionHandler({Exception.class})
    public String exception(Exception ex) {
        log.error(ex.getMessage(),ex);
        return JSONObject.toJSONString(HttpResult.failure(ErrCodeAndMsg.SERVER_ERROR));
    }

    /*
    private <T extends Throwable> String resultFormat(Integer code, T ex) {
        log.error(String.format(logExceptionFormat, code, ex.getMessage()));
        return JSONObject.toJSONString(HttpResult.failure(code+"", ex.getMessage()));
    }*/

}
