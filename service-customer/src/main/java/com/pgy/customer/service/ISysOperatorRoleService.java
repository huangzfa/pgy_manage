package com.pgy.customer.service;

import com.alibaba.fastjson.JSONObject;
import com.pgy.customer.entity.SysOperatorRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pgy.customer.entity.req.query.RoleQuery;
import com.pgy.customer.entity.req.save.OperatorRoleSaveReq;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-17
 */
public interface ISysOperatorRoleService extends IService<SysOperatorRole> {

    /**
     * 分配角色列表
     * @param opId
     * @param req
     * @return
     */
    JSONObject list(Integer opId,RoleQuery req);

    /**
     * 分配角色保存
     * @param req
     * @return
     */
    String save(OperatorRoleSaveReq req);
}
