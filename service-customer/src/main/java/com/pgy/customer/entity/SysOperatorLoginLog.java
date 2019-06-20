package com.pgy.customer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 登录日志
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysOperatorLoginLog extends Model<SysOperatorLoginLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 人员id
     */
    private Integer opId;

    /**
     * HTTPSESSIONID 或 终端SESSIONID
     */
    private String sessionId;

    /**
     * 1-登录，0-登出【字典】
     */
    private Integer loginType;

    private String ip;

    private LocalDateTime addTime;


    public static final String ID = "id";

    public static final String OP_ID = "op_id";

    public static final String SESSION_ID = "session_id";

    public static final String LOGIN_TYPE = "login_type";

    public static final String IP = "ip";

    public static final String ADD_TIME = "add_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
