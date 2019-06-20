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
 * 资源配置日志记录表
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysResourceLog extends Model<SysResourceLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * resource表中类型res_type
     */
    private String resType;

    /**
     * resource表中类型res_type_sec
     */
    private String resTypeSec;

    /**
     * 老的配置
     */
    private String oldJson;

    /**
     * 修改后的配置
     */
    private String modifyJson;

    private Integer addOperatorId;

    private Date addTime;


    public static final String ID = "id";

    public static final String RES_TYPE = "res_type";

    public static final String RES_TYPE_SEC = "res_type_sec";

    public static final String OLD_JSON = "old_json";

    public static final String MODIFY_JSON = "modify_json";

    public static final String ADD_OPERATOR_ID = "add_operator_id";

    public static final String ADD_TIME = "add_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
