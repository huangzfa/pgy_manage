package com.pgy.customer.shiro;

import com.alibaba.fastjson.JSON;
import com.pgy.common.dic.ZD;
import com.pgy.common.exception.RetMsgException;
import com.pgy.customer.entity.SysOperator;
import com.pgy.customer.entity.SysRole;
import com.pgy.customer.entity.SysSmsRecord;
import com.pgy.customer.entity.credential.OperatorCredential;
import com.pgy.customer.entity.helper.UserHelper;
import com.pgy.customer.service.ISysOperatorService;
import com.pgy.customer.service.ISysRoleService;
import com.pgy.customer.service.ISysSmsRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**自实现用户与权限查询. 密码用明文存储，因此使用默认 的SimpleCredentialsMatcher
 * @author huangzhongfa
 * @description
 * @date 2019/6/14
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private ISysOperatorService operatorService;
    @Autowired
    private ISysSmsRecordService smsRecordService;
    @Autowired
    private ISysRoleService roleService;


    /**
     * 认证回调函数, 登录时调用.
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        // 通过登录账号保存用户信息
        Subject subject = SecurityUtils.getSubject();
        Session sessionLocal  = subject.getSession();
        log.info("获取用户信息>>>>>>>>>>>>>>>>>>>>>doGetAuthenticationInfo:" + subject.getSession().getId());
        UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authcToken;
        String userName = (String) token.getPrincipal();
        SysOperator operator = operatorService.queryOperatorByLoginName(userName);
        if (operator == null) {
            throw new UnknownAccountException();// 没找到帐号
        }
        List<SysRole> roleList = roleService.getList(operator.getOpId());
        OperatorCredential credential = UserHelper.createCredential(operator,roleList);
        // 获取session并设置sessionId
        credential.setSessionId(sessionLocal.getId());

        String pwd = "";
        //使用短信验证码登录
        SysSmsRecord smsVC = smsRecordService.queryLastSmsVerifyCode(userName, ZD.smsType_login);
        if (smsVC != null && System.currentTimeMillis() <= smsVC.getInvalidTime().getTime()) {
            pwd = smsVC.getCode();
        }

        //使用密码登录
        if(StringUtils.isBlank(pwd)){
            pwd = operator.getLoginPwd();
        }
        log.info("获取用户信息" + pwd + "===================>" + JSON.toJSONString(credential));
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(credential, // 用户
                pwd,
                operator.getLoginName()
        );
        // 若该方法什么都不做直接返回null的话,就会导致任何用户访问时都会自动跳转到unauthorizedUrl指定的地址
        return authenticationInfo;
    }


    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        OperatorCredential credential = (OperatorCredential) principals.getPrimaryPrincipal();
        log.info("获取权限>>>>>>>>>>>>>doGetAuthorizationInfo");
        if (credential != null) {
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            if (credential.isSuperAdmin()) {
                authorizationInfo.addStringPermission("*");
            } else {
                // 从数据库里查询用户的授权
                Set<String> pers = null;
                try {
                    pers = operatorService.queryOperatorPermission(credential.getOpId());
                } catch (RetMsgException e) {
                    log.warn("查询用户权限异常", e);
                }
                log.info("===========》权限：" + pers);
                authorizationInfo.setStringPermissions(pers);
            }

            // 若该方法什么都不做直接返回null的话,就会导致任何用户访问时都会自动跳转到unauthorizedUrl指定的地址
            return authorizationInfo;
        }
        return null;
    }

    /**
     * 超级管理员拥有所有权限
     * @param principals
     * @param permission
     * @return
     */
    @Override
    public  boolean isPermitted(PrincipalCollection principals, String permission){
        OperatorCredential credential = (OperatorCredential) principals.getPrimaryPrincipal();
        return credential.isSuperAdmin()||super.isPermitted(principals,permission);
    }

    /**
     * 超级管理员拥有所有角色
     * @param principals
     * @param roleIdentifier
     * @return
     */
    @Override
    public boolean hasRole(PrincipalCollection principals, String roleIdentifier) {
        OperatorCredential credential = (OperatorCredential) principals.getPrimaryPrincipal();
        return credential.isSuperAdmin() || super.hasRole(principals, roleIdentifier);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        log.info("用户退出===清除用户凭证缓存");
        OperatorCredential key = (OperatorCredential) getAuthenticationCacheKey(principals);
        if (key != null && getAuthenticationCache() != null) {
            getAuthenticationCache().remove(key.getLoginName());
        }
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        log.info("用户退出===清除用户权限缓存");
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        log.info("用户退出===清除用户缓存");
        super.clearCache(principals);
    }
}
