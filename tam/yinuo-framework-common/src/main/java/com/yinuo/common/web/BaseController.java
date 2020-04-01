package com.yinuo.common.web;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by mrt on 2018/5/22.
 */
public class BaseController {
    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected HttpSession session;


    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {

        this.request = request;

        this.response = response;

        this.session = request.getSession();

    }
    
    public String bindingResult(BindingResult bindingResult) {

        if (null!=bindingResult&&bindingResult.getFieldErrorCount() > 0){
            StringBuffer str = new StringBuffer();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (int i = 0; i < fieldErrors.size(); i++) {
                if (i != fieldErrors.size()-1) {
                    str.append(fieldErrors.get(i).getDefaultMessage()+";");
                } else {
                    str.append(fieldErrors.get(i).getDefaultMessage());
                }
            }
            return str.toString();
        }
		return null;

    }




}
