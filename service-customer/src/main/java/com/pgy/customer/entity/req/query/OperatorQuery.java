package com.pgy.customer.entity.req.query;

import com.pgy.common.entity.ReqParam;
import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/6/17
 */
@Data
public class OperatorQuery extends ReqParam{
    /**
     * 登陆账号
     */
    private String loginName;

    /**
     * 姓名
     */
    private String realName;


    private Integer opId;
}
