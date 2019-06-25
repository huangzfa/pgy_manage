package com.pgy.customer.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pgy.common.constant.Consts;
import com.pgy.common.dic.ZD;
import com.pgy.common.enums.RespEnum;
import com.pgy.common.exception.RetMsgException;
import com.pgy.customer.dao.SysMenuDao;
import com.pgy.customer.dao.SysOperatorDao;
import com.pgy.customer.dao.SysRoleDao;
import com.pgy.customer.dao.SysRoleMenuDao;
import com.pgy.customer.entity.SysMenu;
import com.pgy.customer.entity.SysRole;
import com.pgy.customer.entity.credential.OperatorCredential;
import com.pgy.customer.entity.req.save.MenuSaveReq;
import com.pgy.customer.service.ISysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统菜单 服务实现类
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-17
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements ISysMenuService {

    @Autowired
    private SysMenuDao menuDao;
    @Autowired
    private SysRoleDao roleDao;
    @Autowired
    private SysRoleMenuDao roleMenuDao;
    @Autowired
    private SysOperatorDao operatorDao;

    @Override
    public List<SysMenu> getAll() {
        return menuDao.selectList(new QueryWrapper<SysMenu>()
                .eq(SysMenu.IS_DELETE, Consts.INT_ZERO)
                .orderByAsc(SysMenu.PARENT_ID, SysMenu.MENU_SORT));
    }

    /**
     * 查询一个用户拥有的菜单
     * @param credential
     * @return
     */
    @Override
    public JSONObject getByOpId(OperatorCredential credential) {
        List<SysMenu> menuList = null;
        //超级管理员
        if (credential.isSuperAdmin()) {
            menuList = menuDao.getAll();
        } else {
            List<SysRole> roleList = roleDao.getByOpId(credential.getOpId());
            List<Integer> roleIds = roleList.stream().map(SysRole::getRoleId).collect(Collectors.toList());
            menuList = menuDao.getByRoleIds(roleIds);
        }
        JSONObject object = new JSONObject();
        object.put("list", getMenuList(menuList, Consts.INT_ZERO));
        return object;
    }

    /**
     * 查询登录后用户的所拥有的权限
     * @param credential
     * @return
     */
    @Override
    public JSONObject getPermissionByOpId(OperatorCredential credential){
        HashMap<String,Object> param = new HashMap<>();
        Set<String> pers = null;
        //超级管理员有所有权限
        if( credential.isSuperAdmin()){
            pers = menuDao.queryAllPermission();
        }else{
            param.put("opId",credential.getOpId());
            param.put("menuType", ZD.menuType_mo);
            param.put("menuState",ZD.dataState_valid);
            pers = operatorDao.queryOperatorPermission(param);
        }
        param = new HashMap<>();
        Iterator<String> it = pers.iterator();
        while (it.hasNext()) {
            param.put(it.next(),true);
        }
        JSONObject object = new JSONObject();
        object.put("list",param);
        return object;
    }

    /**
     * 只获取菜单，不获取权限菜单
     * @param menuList
     * @param parentId
     * @return
     */
    public JSONArray getMenuList(List<SysMenu> menuList, Integer parentId) {
        JSONArray array = new JSONArray();
        for (SysMenu menu : menuList) {
            if (menu.getMenuType().equals(ZD.menuType_m)) {
                if (parentId.equals(menu.getParentId())) {
                    JSONObject object = new JSONObject();
                    object.put("id", menu.getMenuId());
                    object.put("name", menu.getMenuName());
                    object.put("pId", menu.getParentId());
                    object.put("icon", menu.getMenuIcon());
                    object.put("url", menu.getMenuUrl());
                    if (menu.getIsParent().equals(Consts.INT_ONE)) {
                        JSONArray array1 = getMenuList(menuList, menu.getMenuId());
                        if (array1.size() > Consts.INT_ZERO) {
                            object.put("children", array1);
                        }
                    }
                    array.add(object);
                }
            }
        }
        return array;
    }

    /**
     * 系统管理->菜单列表
     * @return
     */
    @Override
    public JSONObject getList() {
        List<SysMenu> menuList = getAll();
        JSONObject object = new JSONObject();
        object.put("list", getChildrenMenu(menuList, Consts.INT_ZERO));
        return object;
    }

    /**
     * 保存菜单
     * @param menu
     * @param credential
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public String save(MenuSaveReq menu, OperatorCredential credential){
        if( menu.getMenuType().equals(ZD.menuType_mo) && StringUtils.isBlank(menu.getPermission())){
            throw new RetMsgException(RespEnum.DEFINE_MSG.getCode(), "权限项菜单权限标识不能为空");
        }
        SysMenu entity = new SysMenu();
        BeanUtils.copyProperties(menu,entity);
        if( entity.getMenuId() == null ){
            if( entity.getParentId() == null ){
                throw new RetMsgException(RespEnum.DEFINE_MSG.getCode(), "请指定父级菜单");
            }
            entity.setAddOperatorId(credential.getOpId());
            menuDao.insert(entity);
        }else{
            entity.setModifyOperatorId(credential.getOpId());
            entity.setModifyTime(new Date());
            menuDao.updateById(entity);
        }
        //上级父菜单设置is_parent =1
        SysMenu parent = menuDao.getByParentId(entity.getParentId());
        if( parent!=null ){
            parent.setIsParent(Consts.INT_ONE);
            menuDao.updateById(parent);
        }
        return RespEnum.SUCCESS.getMsg();
    }

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    @Override
    public String delete(Integer menuId, OperatorCredential credential) {
        SysMenu menu = menuDao.selectById(menuId);
        if (menu == null) {
            throw new RetMsgException(RespEnum.DEFINE_MSG.getCode(), "菜单不存在");
        }
        if (menu.getIsDelete() > Consts.INT_ZERO) {
            throw new RetMsgException(RespEnum.DEFINE_MSG.getCode(), "菜单已被删除，请勿重复操作");
        }
        if (menuDao.hasChildren(menuId) > Consts.INT_ZERO) {
            throw new RetMsgException(RespEnum.DEFINE_MSG.getCode(), "请先删除该菜单下面的子菜单");
        }
        String roleNames = roleMenuDao.hasRoleByMenuId(menuId);
        if (StringUtils.isNotBlank(roleNames)) {
            throw new RetMsgException(RespEnum.DEFINE_MSG.getCode(), roleNames + "正在使用该菜单,无法删除");
        }
        SysMenu record = new SysMenu()
                .setMenuId(menuId)
                .setModifyTime(new Date())
                .setModifyOperatorId(credential.getOpId())
                .setIsDelete(menuId);
        menuDao.updateById(record);
        return RespEnum.SUCCESS.getMsg();
    }

    /**
     * 菜单详情
     * @param menuId
     * @return
     */
    @Override
    public SysMenu getByMenuId(Integer menuId){
        return menuDao.selectOne(new QueryWrapper<SysMenu>()
                .eq(SysMenu.IS_DELETE,Consts.INT_ZERO)
                .eq(SysMenu.MENU_ID,menuId));
    }
    /**
     * 获取子节点（包括菜单和权限菜单）
     * @param menuList
     * @param parentId
     * @return
     */
    @Override
    public JSONArray getChildrenMenu(List<SysMenu> menuList, Integer parentId) {
        JSONArray array = new JSONArray();
        for (SysMenu menu : menuList) {
            if (parentId.equals(menu.getParentId())) {
                JSONObject object = new JSONObject();
                object.put("id", menu.getMenuId());
                object.put("name", menu.getMenuName());
                object.put("pId", menu.getParentId());
                object.put("menuSort", menu.getMenuSort());
                object.put("permission", menu.getPermission());
                object.put("url", menu.getMenuUrl());
                object.put("menuState", menu.getMenuState());
                if (menu.getMenuType().equals(ZD.menuType_m)) {
                    object.put("menuType", "菜单");
                } else {
                    object.put("menuType", "权限项");
                }
                if (menu.getIsParent().equals(Consts.INT_ONE)) {
                    JSONArray array1 = getChildrenMenu(menuList, menu.getMenuId());
                    if (array1.size() > Consts.INT_ZERO) {
                        object.put("children", array1);
                    }
                }
                array.add(object);
            }
        }
        return array;
    }
}
