package com.pgy.customer.entity.resp;

import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/6/17
 */
@Data
public class OperatorResp {
    private Integer opId;

    /**
     * 手机号
     */
    private String loginName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 1启用0禁用
     */
    private Integer operatorState;

    /**
     * 最后登陆时间
     */
    private String lastLoginTime;

    /**
     * 拥有的角色
     */
    private String roleNames;

    /**
     * 最后一次登陆ip
     */
    private String lastLoginIp;

}
