package com.pgy.customer.entity;

import java.math.BigDecimal;
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
 * 业务资源配置表
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysResource extends Model<SysResource> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 主类型
     */
    private String resType;

    /**
     * 附类型
     */
    private String resTypeSec;

    /**
     * 描述
     */
    private String remark;

    /**
     * 整型配置值
     */
    private Integer intValue;

    /**
     * 长整型配置值
     */
    private Long longValue;

    /**
     * 字符串配置值
     */
    private String stringValue;

    /**
     * 字符串配置值1
     */
    private String stringValue1;

    /**
     * 字符串配置值2
     */
    private String stringValue2;

    /**
     * decimal配置值
     */
    private BigDecimal decimalValue;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 添加人id
     */
    private Integer addOperatorId;

    /**
     * 修改人id
     */
    private Integer modifyOperatorId;

    /**
     * 是否删除
     */
    private Integer isDelete;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String RES_TYPE = "res_type";

    public static final String RES_TYPE_SEC = "res_type_sec";

    public static final String REMARK = "remark";

    public static final String INT_VALUE = "int_value";

    public static final String LONG_VALUE = "long_value";

    public static final String STRING_VALUE = "string_value";

    public static final String STRING_VALUE1 = "string_value1";

    public static final String STRING_VALUE2 = "string_value2";

    public static final String DECIMAL_VALUE = "decimal_value";

    public static final String ADD_TIME = "add_time";

    public static final String MODIFY_TIME = "modify_time";

    public static final String ADD_OPERATOR_ID = "add_operator_id";

    public static final String MODIFY_OPERATOR_ID = "modify_operator_id";

    public static final String IS_DELETE = "is_delete";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
