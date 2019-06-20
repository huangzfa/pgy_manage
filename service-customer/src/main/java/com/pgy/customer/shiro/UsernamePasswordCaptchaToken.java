package com.pgy.customer.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 扩展Shiro默认的用户认证的Bean - UsernamePasswordToken
 * @author huangzhongfa
 * @description
 * @date 2019/6/14
 */
public class UsernamePasswordCaptchaToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 1L;

	public UsernamePasswordCaptchaToken() {
		super();
	}

	public UsernamePasswordCaptchaToken(final String username,
                                        final char[] password, final boolean rememberMe, final String host) {
		super(username, password, rememberMe, host);
	}

}
