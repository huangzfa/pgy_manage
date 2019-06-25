package com.pgy.customer.controller;


import com.pgy.common.annotation.RespParamHandler;
import com.pgy.common.enums.RespEnum;
import com.pgy.common.exception.RetMsgException;
import com.pgy.customer.entity.SysMenu;
import com.pgy.customer.entity.credential.OperatorCredential;
import com.pgy.customer.entity.req.save.MenuSaveReq;
import com.pgy.customer.service.ISysMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 系统菜单 前端控制器
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-17
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController{

    @Autowired
    private ISysMenuService menuService;

    /**
     * 登陆后获取菜单列表
     * @return
     */
    @RespParamHandler
    @PostMapping(value = "/operatorMenuList")
    public Object operatorMenuList()  {
        return menuService.getByOpId(getCredential());
    }

    /**
     * 登陆后获取权限列表
     * @return
     */
    @RespParamHandler
    @PostMapping(value = "/operatorPermissionList")
    public Object operatorPermissionList()  {
        return menuService.getPermissionByOpId(getCredential());
    }

    /**
     * 菜单列表
     * @return
     */
    @RespParamHandler
    @PostMapping(value = "/list")
    @RequiresPermissions("sys:menu:view")
    public Object list()  {
        return menuService.getList();
    }

    /**
     * 菜单详情
     * @return
     */
    @RespParamHandler
    @PostMapping(value = "/getByMenuId")
    @RequiresPermissions("sys:menu:edit")
    public Object getByMenuId(@RequestBody SysMenu menu)  {
        if( menu.getMenuId() == null){
            return new SysMenu();
        }else{
            return menuService.getByMenuId(menu.getMenuId());
        }
    }

    @RespParamHandler
    @PostMapping(value = "/save")
    @RequiresPermissions("sys:menu:edit")
    public Object getByMenuId(@Valid @RequestBody MenuSaveReq menu)  {
        return menuService.save(menu,getCredential());
    }

    /**
     * 菜单删除
     * @param menu
     * @return
     */
    @RespParamHandler
    @PostMapping(value = "/delete")
    @RequiresPermissions("sys:menu:delete")
    public Object delete(@RequestBody SysMenu menu)  {
        if( menu.getMenuId() == null ){
            throw new RetMsgException(RespEnum.DEFINE_MSG.getCode(),"菜单id不能为空");
        }
        return menuService.delete(menu.getMenuId(),getCredential());
    }
}

