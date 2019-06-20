package com.pgy.customer.entity.req.save;

import com.pgy.common.entity.ReqParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/6/19
 */
@Data
public class ResourceSaveReq extends ReqParam {
    private Integer id;

    /**
     * 资源名称
     */
    @NotBlank(message = "资源名称不能为空")
    private String name;

    /**
     * 主类型
     */
    @NotBlank(message = "资源主类型不能为空")
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
}
