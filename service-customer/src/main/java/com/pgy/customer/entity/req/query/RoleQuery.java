package com.pgy.customer.entity.req.query;

import com.pgy.common.entity.ReqParam;
import lombok.Data;

/**角色查询包体
 * @author huangzhongfa
 * @description
 * @date 2019/6/17
 */
@Data
public class RoleQuery extends ReqParam {
    private String roleName;

    private Integer roleId;
}
