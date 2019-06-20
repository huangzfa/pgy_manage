package com.pgy.customer.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysOperatorRole extends Model<SysOperatorRole> {

    private static final long serialVersionUID = 1L;

    private Integer roleId;

    private Integer opId;


    public static final String ROLE_ID = "role_id";

    public static final String OP_ID = "op_id";

    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
