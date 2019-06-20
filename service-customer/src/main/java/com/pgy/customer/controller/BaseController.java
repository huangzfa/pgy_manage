package com.pgy.customer.controller;

import com.pgy.common.enums.RespEnum;
import com.pgy.common.exception.RetMsgException;
import com.pgy.customer.entity.credential.OperatorCredential;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/6/14
 */
public class BaseController {

    protected Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    protected OperatorCredential getCredential() {
        Subject subject = getSubject();
        OperatorCredential myCredential = (OperatorCredential) subject.getPrincipal();
        if( myCredential == null ){
            throw new RetMsgException(RespEnum.USER_ACCOUNT_OVERDUE);
        }
        return myCredential;
    }

    public HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public HttpServletResponse getResponse(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
}
