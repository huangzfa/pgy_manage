package com.pgy.customer.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pgy.customer.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pgy.customer.entity.credential.OperatorCredential;
import com.pgy.customer.entity.req.save.MenuSaveReq;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统菜单 服务类
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-17
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 获取所有有效菜单
     * @return
     */
    List<SysMenu> getAll();

    /**
     * 查询菜单下面所有子节菜单
     * @param menuList
     * @param parentId
     * @return
     */
    JSONArray getChildrenMenu(List<SysMenu> menuList, Integer parentId);

    /**
     * 查询一个用户拥有的菜单
     * @param credential
     * @return
     */
    JSONObject getByOpId(OperatorCredential credential);

    /**
     *
     * @param credential
     * @return
     */
    JSONObject getPermissionByOpId(OperatorCredential credential);
    /**
     * 系统管理->菜单列表
     * @return
     */
    JSONObject getList();

    /**
     * 菜单保存
     * @param menu
     * @param credential
     * @return
     */
    String save(MenuSaveReq menu,OperatorCredential credential);

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    String delete(Integer menuId,OperatorCredential credential);

    /**
     * 菜单详情
     * @param menuId
     * @return
     */
    SysMenu getByMenuId(Integer menuId);
}
