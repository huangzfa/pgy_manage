package com.pgy.customer.controller;


import com.pgy.common.annotation.RespParamHandler;
import com.pgy.customer.entity.req.query.RoleQuery;
import com.pgy.customer.entity.req.save.RoleSaveReq;
import com.pgy.customer.service.ISysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

/**
 * <p>
 * 系统角色 前端控制器
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-14
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController{
    @Autowired
    private ISysRoleService roleService;

    /**
     * 角色分页
     * @param reqParam
     * @return
     * @throws IOException
     */
    @RespParamHandler
    @PostMapping(value = "/list")
    @RequiresPermissions("sys:role:view")
    public Object list(@Valid @RequestBody RoleQuery reqParam){
        return roleService.getPage(reqParam);
    }

    /**
     * 角色编辑
     * @param role
     * @return
     * @throws IOException
     */
    @RespParamHandler
    @PostMapping(value = "/getByRoleId")
    @RequiresPermissions("sys:role:edit")
    public Object getById(@Valid @RequestBody RoleQuery role) {
        return roleService.getByRoleId(role);
    }

    /**
     * 角色保存
     * @param reqParam
     * @return
     * @throws IOException
     */
    @RespParamHandler
    @PostMapping(value = "/save")
    @RequiresPermissions("sys:role:edit")
    public Object save(@Valid @RequestBody RoleSaveReq reqParam)  {
        return roleService.saveRole(reqParam,getCredential());
    }

    /**
     * 角色删除
     * @param reqParam
     * @return
     */
    @RespParamHandler
    @PostMapping(value = "/delete")
    @RequiresPermissions("sys:role:delete")
    public Object delete(@Valid @RequestBody RoleSaveReq reqParam) {
        return roleService.delete(getCredential(),reqParam.getRoleId());
    }
}

