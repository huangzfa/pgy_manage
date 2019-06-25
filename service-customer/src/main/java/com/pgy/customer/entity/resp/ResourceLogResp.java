package com.pgy.customer.entity.resp;

import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/6/21
 */
@Data
public class ResourceLogResp {
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

    private String addTime;
}
