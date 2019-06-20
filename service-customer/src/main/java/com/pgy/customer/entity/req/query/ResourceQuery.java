package com.pgy.customer.entity.req.query;

import com.pgy.common.entity.ReqParam;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/6/19
 */
@Data
public class ResourceQuery extends ReqParam {
    private String name;

    private String resType;

    private Integer id;
}
