package com.pgy.customer.entity.helper;

import com.pgy.common.constant.Consts;
import com.pgy.customer.entity.SysOperator;
import com.pgy.customer.entity.SysRole;
import com.pgy.customer.entity.credential.OperatorCredential;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import java.util.Date;
import java.util.List;


/**
 * 用户帮助类
 * @author Hong
 *
 */
public class UserHelper {

	/**
	 * 创建用户通行证
	 * @param operator
	 * @param roleList
	 * @return
	 */
	public static OperatorCredential createCredential(SysOperator operator,List<SysRole> roleList){
		OperatorCredential credential=new OperatorCredential();
		credential.setOpId(operator.getOpId());
		credential.setLoginName(operator.getLoginName());
		for( SysRole role : roleList){
			//判断是不是超级管理员
			if( role.getRoleType().equals(Consts.INT_ONE)){
				credential.setSuperAdmin(true);
				break;
			}
		}
		credential.setLoginTime(new Date());
		credential.setLastLoginIp(operator.getLastLoginIp());
		credential.setLastLoginTime(operator.getLastLoginTime());
		return credential;
	}
	/**
	 * 获取运维用户凭证
	 * @return
	 */
	public static OperatorCredential getCredential() {
		Subject subject = SecurityUtils.getSubject();
		OperatorCredential myCredential=(OperatorCredential) subject.getPrincipal();
		return myCredential;
	}

	/**
	 * 强制踢出
	 */
	public static void forceLogout(String sessionId,String outKey)throws Exception {
		if(StringUtils.isNotBlank(sessionId)){
			DefaultWebSecurityManager securityManager=(DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
			DefaultWebSessionManager sessionManager=(DefaultWebSessionManager) securityManager.getSessionManager();
			try {
				Session session=sessionManager.getSession(new DefaultSessionKey(sessionId));
				if (session!=null) {
					session.setAttribute(outKey, "1");
				}
			} catch (Exception e) {
			}
		}
	}
	
}
