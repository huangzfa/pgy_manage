package com.pgy.customer.entity.resp;

import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/6/18
 */
@Data
public class OperatorRoleResp {
    private String roleName;

    private Integer opId;

    private boolean checked;
}
