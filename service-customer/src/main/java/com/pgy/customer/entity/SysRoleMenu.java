package com.pgy.customer.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色菜单关联表
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRoleMenu extends Model<SysRoleMenu> {

    private static final long serialVersionUID = 1L;

    private Integer roleId;

    private Integer menuId;


    public static final String ROLE_ID = "role_id";

    public static final String MENU_ID = "menu_id";

    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
