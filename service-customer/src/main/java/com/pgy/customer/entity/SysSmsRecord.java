package com.pgy.customer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 短信验证码
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysSmsRecord extends Model<SysSmsRecord> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String mobile;

    /**
     * 1登录2修改密码【字典】
     */
    private Integer smsType;

    private String code;

    private Date invalidTime;

    private Date addTime;


    public static final String ID = "id";

    public static final String MOBILE = "mobile";

    public static final String SMS_TYPE = "sms_type";

    public static final String CODE = "code";

    public static final String INVALID_TIME = "invalid_time";

    public static final String ADD_TIME = "add_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
