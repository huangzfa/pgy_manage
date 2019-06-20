package com.pgy.customer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统角色
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "role_id", type = IdType.AUTO)
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

    /**
     * !0删除
     */
    private Integer isDelete;

    private Integer modifyOperatorId;

    /**
     * 添加时间
     */
    private Date addTime;

    private Integer addOperatorId;

    /**
     * 修改时间
     */
    private Date modifyTime;


    public static final String ROLE_ID = "role_id";

    public static final String ROLE_NAME = "role_name";

    public static final String ROLE_TYPE = "role_type";

    public static final String ROLE_STATE = "role_state";

    public static final String ROLE = "role";

    public static final String IS_DELETE = "is_delete";

    public static final String MODIFY_OPERATOR_ID = "modify_operator_id";

    public static final String ADD_TIME = "add_time";

    public static final String ADD_OPERATOR_ID = "add_operator_id";

    public static final String MODIFY_TIME = "modify_time";

    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
