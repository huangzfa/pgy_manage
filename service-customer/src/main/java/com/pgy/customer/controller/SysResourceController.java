package com.pgy.customer.controller;


import com.pgy.common.annotation.RespParamHandler;
import com.pgy.customer.entity.req.query.ResourceQuery;
import com.pgy.customer.entity.req.save.ResourceSaveReq;
import com.pgy.customer.service.ISysResourceLogService;
import com.pgy.customer.service.ISysResourceService;
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
 * 业务资源配置表 前端控制器
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-19
 */
@RestController
@RequestMapping("/sys/resource")
public class SysResourceController extends BaseController{

    @Autowired
    private ISysResourceService resourceService;
    @Autowired
    private ISysResourceLogService resourceLogService;
    /**
     * 资源分页
     * @param query
     * @return
     * @throws IOException
     */
    @RespParamHandler
    @PostMapping(value = "/list")
    @RequiresPermissions("sys:resource:view")
    public Object list(@Valid @RequestBody ResourceQuery query) throws IOException {
        return resourceService.getPage(query);
    }

    @RespParamHandler
    @PostMapping(value = "/getById")
    @RequiresPermissions("sys:resource:edit")
    public Object getById(@Valid @RequestBody ResourceQuery query) {
        return resourceService.getById(query.getId());
    }

    @RespParamHandler
    @PostMapping(value = "/save")
    @RequiresPermissions("sys:resource:edit")
    public Object save(@Valid @RequestBody ResourceSaveReq req)  {
        return resourceService.save(getCredential(),req);
    }

    @RespParamHandler
    @PostMapping(value = "/delete")
    @RequiresPermissions("sys:resource:delete")
    public Object delete(@Valid @RequestBody ResourceQuery query)  {
        return resourceService.delete(getCredential(),query.getId());
    }

    @RespParamHandler
    @PostMapping(value = "/log/list")
    @RequiresPermissions("sys:resourceLog:view")
    public Object getLogList(@Valid @RequestBody ResourceQuery query)  {
        return resourceLogService.getPage(getCredential(),query);
    }
}

