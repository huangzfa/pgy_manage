package com.pgy.customer.entity.resp;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/6/21
 */
@Data
public class ResourceResp {
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
    private String addTime;

    /**
     * 修改时间
     */
    private String modifyTime;

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
}
