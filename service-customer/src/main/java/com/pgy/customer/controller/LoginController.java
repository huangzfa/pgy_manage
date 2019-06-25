package com.pgy.customer.controller;

import com.alibaba.fastjson.JSONObject;
import com.pgy.common.annotation.RespParamHandler;
import com.pgy.common.dic.ZD;
import com.pgy.common.enums.RespEnum;
import com.pgy.common.exception.RetMsgException;
import com.pgy.common.utils.RegExpValidatorUtils;
import com.pgy.common.utils.WebUtil;
import com.pgy.customer.entity.req.OperatorLoginReq;
import com.pgy.customer.entity.SysOperator;
import com.pgy.customer.entity.credential.OperatorCredential;
import com.pgy.customer.entity.req.SendSmsCodeReq;
import com.pgy.customer.service.IImageCodeService;
import com.pgy.customer.service.ISysOperatorLoginLogService;
import com.pgy.customer.service.ISysOperatorService;
import com.pgy.customer.service.ISysSmsRecordService;
import com.pgy.customer.shiro.UsernamePasswordCaptchaToken;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/6/14
 */
@RestController
public class LoginController extends BaseController{
    @Autowired
    private IImageCodeService iImageCodeService;
    @Autowired
    private ISysSmsRecordService smsRecordService;
    @Autowired
    private ISysOperatorService operatorService;
    @Autowired
    private ISysOperatorLoginLogService operatorLoginLogService;
    /**
     * 获取图形验证码 含数字字母
     * @param phone
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/createImage")
    public void createImage(String phone) throws IOException {
        if(!RegExpValidatorUtils.isMobile(phone)){
            throw new RetMsgException("手机号格式错误");
        }
        iImageCodeService.createImage(phone,getRequest(),getResponse());
    }

    /**
     * 发送短信
     * @param req
     * @return
     */
    @RespParamHandler
    @PostMapping(value = "/login/send/sms")
    public Object loginCode(@Valid @RequestBody SendSmsCodeReq req){
        return smsRecordService.sendSms(req);
    }

    /**
     * 登陆
     * @param req
     * @return
     */
    @RespParamHandler
    @PostMapping(value = "/login")
    public Object loginCode(@Valid @RequestBody OperatorLoginReq req) {
        if (StringUtils.isBlank(req.getCode())) {
            throw new RetMsgException(RespEnum.PWD_SMS_GET_ERROR);
        }
        SysOperator operator = operatorService.queryOperatorByLoginName(req.getPhone());
        if (operator == null) {
            throw new RetMsgException(RespEnum.USER_ACCOUNT_NOT_EXIST);
        }
        //账号被禁用
        if (operator.getOperatorState().equals(ZD.dataState_invalid)) {
            throw new RetMsgException(RespEnum.USER_ACCOUNT_NOT_EXIST);
        }
        //验证短信
        smsRecordService.checkVerifyCode(req, ZD.smsType_login);
        Subject subject = getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordCaptchaToken token = new UsernamePasswordCaptchaToken(req.getPhone(), req.getCode().toCharArray(), req.isRememberMe(), getRequest().getRemoteHost());
        subject.login(token);
        //清除其它地方登录
        getLoginedSession(subject);
        OperatorCredential credential = getCredential();
        credential.setIp(WebUtil.getRemoteIP(getRequest()));
        Session shiroSession = subject.getSession();
        credential.setSessionCreateTime(shiroSession.getStartTimestamp());
        credential.setSessionTimeout(shiroSession.getTimeout());

        // 登录成功后更新信息
        operatorService.updateOperatorForLogin(credential);
        // 记录日志
        operatorLoginLogService.save(credential,ZD.smsType_login);
        JSONObject object = new JSONObject();
        object.put("loginName",operator.getLoginName());
        object.put("realName",operator.getRealName());
        object.put("isSuperAdmin",credential.isSuperAdmin());
        return object;
    }

    /**
     * 清除以前设备登陆
     * @return
     */
    private void getLoginedSession(Subject currentUser) {
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager)securityManager.getSessionManager();
        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();//获取当前已登录的用户session列表
        OperatorCredential loginUser = (OperatorCredential) currentUser.getPrincipal();
        for(Session session:sessions){
            Subject s = new Subject.Builder().session(session).buildSubject();
            if (s.isAuthenticated()) {
                OperatorCredential user = (OperatorCredential) s.getPrincipal();
                if (user.getOpId().equals(loginUser.getOpId())) {
                    if (!session.getId().equals(
                            currentUser.getSession().getId())) {
                        //清除该用户以前登录时保存的session
                        sessionManager.getSessionDAO().delete(session);
                    }
                }
            }
        }
    }

    /**
     * 登出
     * @return
     */
    @RespParamHandler
    @PostMapping(value = "/logout")
    public Object logout() {
        Subject subject = SecurityUtils.getSubject();
        operatorLoginLogService.save(getCredential(),ZD.smsType_login_out);
        subject.logout();
        return RespEnum.SUCCESS.getMsg();
    }
}
