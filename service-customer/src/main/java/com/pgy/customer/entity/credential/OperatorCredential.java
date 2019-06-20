package com.pgy.customer.entity.credential;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/6/15
 */
@Data
public class OperatorCredential {
    private Integer opId;
    private String ip;//登录ip
    private String loginName;
    private Date loginTime;//登录时间
    private Serializable sessionId;//会话id
    private Date sessionCreateTime;//会话创建时间
    private long sessionTimeout;//会话有效时间
    private boolean isSuperAdmin=false;//是否是超级管理员-运维平台的
    private String lastLoginIp;//上次登录ip
    private Date lastLoginTime;//上次登录时间
}
