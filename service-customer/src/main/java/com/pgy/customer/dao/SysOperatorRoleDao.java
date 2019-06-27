package com.pgy.customer.dao;

import com.pgy.customer.entity.SysOperatorRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pgy.customer.entity.resp.OperatorRoleResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 Mapper 接口
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-17
 */
public interface SysOperatorRoleDao extends BaseMapper<SysOperatorRole> {
    /**
     * 查询用户拥有哪些角色
     * @param opIds
     * @return
     */
    List<OperatorRoleResp> getRoleByOpIds(@Param("opIds") List<Integer> opIds);

    /**
     * 根据用户di查询角色Id
     * @param opId
     * @return
     */
    List<SysOperatorRole> getByOpId(@Param("opId") Integer opId);

    /**
     * 删除绑定角色
     * @param opId
     * @return
     */
    Integer deleteByOpId(@Param("opId") Integer opId);

    /**
     * 批量写入
     * @param roleIds
     * @param opId
     * @return
     */
    Integer batchInsert(@Param("roleIds") List<String> roleIds,@Param("opId") Integer opId);

}
