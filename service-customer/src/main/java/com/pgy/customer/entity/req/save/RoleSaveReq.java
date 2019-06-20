package com.pgy.customer.entity.req.save;

import com.pgy.common.entity.ReqParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author huangzhongfa
 * @description 角色保存body
 * @date 2019/6/17
 */
@Data
public class RoleSaveReq extends ReqParam{

    private Integer roleId;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    /**
     * 1超级管理员2普通角色
     */
    @NotNull(message = "请设置角色类型")
    private Integer roleType;

    /**
     * 1有效0无效
     */
    @NotNull(message = "角色有效性不能为空")
    private Integer roleState;

    /**
     * 角色唯一标识：admin
     */
    @NotBlank(message = "请填写角色标识")
    private String role;

    /**
     * 菜单角色管理关联
     */
    private String menuIds;
}
