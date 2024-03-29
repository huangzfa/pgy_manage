package com.pgy.customer.dao;

import com.pgy.customer.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pgy.customer.entity.req.save.MenuSaveReq;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统菜单 Mapper 接口
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-17
 */
public interface SysMenuDao extends BaseMapper<SysMenu> {

    List<SysMenu> getAll();

    /**
     * 查询所有权限
     * @return
     */
    Set<String> queryAllPermission();

    /**
     * 查询一个用户拥有的所有角色菜单
     * @param roleIds
     * @return
     */
    List<SysMenu> getByRoleIds(@Param("roleIds") List<Integer> roleIds);

    /**
     * 查询该菜单下面是否有子菜单
     * @param menuId
     * @return
     */
    int hasChildren(@Param("menuId") Integer menuId);

    /**
     * 根据父id查询菜单，且is_parent = 0;
     * @param parentId
     * @return
     */
    SysMenu getByParentId(@Param("parentId") Integer parentId);

    /**
     * 判断该权限标识是否存在
     * @param menu
     * @return
     */
    int hasPermission(@Param("req") MenuSaveReq menu);
}
