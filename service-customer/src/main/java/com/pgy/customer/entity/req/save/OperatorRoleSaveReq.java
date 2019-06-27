package com.pgy.customer.entity.req.save;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**分配角色保存
 * @author huangzhongfa
 * @description
 * @date 2019/6/26
 */
@Data
public class OperatorRoleSaveReq {
    /**
     * 员工id
     */
    @NotNull(message = "员工id不能为空")
    private Integer opId;

    /**
     * 角色id,多个逗号隔开
     */
    private String roleIds;
}
