package com.pgy.customer.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pgy.customer.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pgy.customer.entity.req.query.RoleQuery;
import com.pgy.customer.entity.req.save.RoleSaveReq;
import com.pgy.customer.entity.resp.RoleResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统角色 Mapper 接口
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-14
 */
public interface SysRoleDao extends BaseMapper<SysRole> {
    /**
     * 根据用户id查询角色
     * @param opId
     * @return
     */
    List<SysRole> getByOpId(@Param("opId") Integer opId);

    IPage<RoleResp> getPage(@Param("page") Page<RoleResp> page, @Param("req") RoleQuery req);

    List<RoleResp> getAll(@Param("req") RoleQuery req);

    /**
     * 判断次角色是否已经存在
     * @param req
     * @return
     */
    int hasByRole(@Param("req")RoleSaveReq req);

}
