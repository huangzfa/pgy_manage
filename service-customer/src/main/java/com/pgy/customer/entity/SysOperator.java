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
 * 系统用户
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysOperator extends Model<SysOperator> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "op_id", type = IdType.AUTO)
    private Integer opId;

    /**
     * 手机号
     */
    private String loginName;

    /**
     * 默认 123456
     */
    private String loginPwd;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 1启用0禁用
     */
    private Integer operatorState;

    /**
     * 系统分配session id
     */
    private String sessionId;

    /**
     * 最后一次登录ip
     */
    private String lastLoginIp;

    private Date lastLoginTime;

    /**
     * 添加时间
     */
    private Date addTime;

    private Integer addOperatorId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    private Integer modifyOperatorId;

    /**
     * !0删除
     */
    private Integer isDelete;


    public static final String OP_ID = "op_id";

    public static final String LOGIN_NAME = "login_name";

    public static final String LOGIN_PWD = "login_pwd";

    public static final String REAL_NAME = "real_name";

    public static final String OPERATOR_STATE = "operator_state";

    public static final String SESSION_ID = "session_id";

    public static final String LAST_LOGIN_IP = "last_login_ip";

    public static final String LAST_LOGIN_TIME = "last_login_time";

    public static final String ADD_TIME = "add_time";

    public static final String ADD_OPERATOR_ID = "add_operator_id";

    public static final String MODIFY_TIME = "modify_time";

    public static final String MODIFY_OPERATOR_ID = "modify_operator_id";

    public static final String IS_DELETE = "is_delete";

    @Override
    protected Serializable pkVal() {
        return this.opId;
    }

}
