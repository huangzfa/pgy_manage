package com.pgy.customer.entity.req;

import com.pgy.common.entity.ReqParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author huangzhongfa
 * @description 登录body
 * @date 2019/6/14
 */
@Data
public class OperatorLoginReq extends ReqParam {
    @NotBlank(message = "phone不能为空")
    @Pattern(regexp = "^1[0-9]{10}$", message = "phone格式不正确")
    private String phone;

    /**
     * 短信验证码
     */
    private String code;

    /**
     * 记住密码
     */
    private boolean rememberMe = false;

}
