package com.pgy.customer.entity.resp;

import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/6/17
 */
@Data
public class RoleResp {
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 1超级管理员2普通角色
     */
    private Integer roleType;

    /**
     * 1有效0无效
     */
    private Integer roleState;

    /**
     * 角色唯一标识：admin
     */
    private String role;
}
