package com.pgy.customer.controller;

import com.pgy.common.annotation.RespParamHandler;
import com.pgy.customer.entity.req.query.RoleQuery;
import com.pgy.customer.entity.req.save.OperatorRoleSaveReq;
import com.pgy.customer.service.ISysOperatorRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**分配角色
 * @author huangzhongfa
 * @description
 * @date 2019/6/26
 */
@RestController
@RequestMapping("/sys/operatorRole")
public class SysOperatorRoleController extends BaseController{
    @Autowired
    private ISysOperatorRoleService operatorRoleService;


    /**
     * 角色分配列表展示
     * @param reqParam
     * @return
     */
    @RespParamHandler
    @PostMapping(value = "/list")
    @RequiresPermissions("sys:operatorRole:view")
    public Object list(@Valid @RequestBody RoleQuery reqParam){
        return operatorRoleService.list(getCredential().getOpId(),reqParam);
    }

    /**
     * 角色分配保存
     * @param req
     * @return
     */
    @RespParamHandler
    @PostMapping(value = "/save")
    @RequiresPermissions("sys:operatorRole:edit")
    public Object distribution(@Valid @RequestBody OperatorRoleSaveReq req){
        return operatorRoleService.save(req);
    }
}
