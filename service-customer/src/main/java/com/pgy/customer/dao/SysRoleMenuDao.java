package com.pgy.customer.dao;

import com.pgy.customer.entity.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色菜单关联表 Mapper 接口
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-14
 */
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenu> {

    /**
     * 查询该菜单哪些角色在使用
     * @param menuId
     * @return
     */
    String hasRoleByMenuId(@Param("menuId") Integer menuId);

    /**
     * 根据角色删除菜单
     * @param roleId
     */
    Integer deleteByRoleId(@Param("roleId") Integer roleId);

    /**
     * 批量写入
     * @param menuIds
     * @param roleId
     * @return
     */
    Integer batchInsert(@Param("menuIds") List<String> menuIds,@Param("roleId") Integer roleId);

}
