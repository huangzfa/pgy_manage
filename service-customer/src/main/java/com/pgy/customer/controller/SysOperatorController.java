package com.pgy.customer.controller;


import com.pgy.common.annotation.RespParamHandler;
import com.pgy.customer.entity.req.query.OperatorQuery;
import com.pgy.customer.entity.req.save.OperatorSaveReq;
import com.pgy.customer.entity.resp.OperatorResp;
import com.pgy.customer.service.ISysOperatorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-14
 */
@RestController
@RequestMapping("/sys/operator")
public class SysOperatorController extends BaseController{

    @Autowired
    private ISysOperatorService operatorService;

    /**
     * 用户分页
     * @param reqParam
     * @return
     * @throws IOException
     */
    @RespParamHandler
    @PostMapping(value = "/list")
    @RequiresPermissions("sys:operator:view")
    public Object list(@Valid @RequestBody OperatorQuery reqParam) throws IOException {
        return operatorService.getPage(reqParam);
    }

    /**
     * 保存
     * @param reqParam
     * @return
     * @throws IOException
     */
    @RespParamHandler
    @PostMapping(value = "/save")
    @RequiresPermissions("sys:operator:edit")
    public Object save(@Valid @RequestBody OperatorSaveReq reqParam) throws Exception{
        return operatorService.save(reqParam,getCredential());
    }
    /**
     * 员工详情
     * @param reqParam
     * @return
     * @throws IOException
     */
    @RespParamHandler
    @PostMapping(value = "/getByOpId")
    @RequiresPermissions("sys:operator:edit")
    public Object getByOpId(@Valid @RequestBody OperatorQuery reqParam)  {
        if(reqParam.getOpId()!=null ){
            return operatorService.getByOpId(reqParam.getOpId());
        }else{
            return new OperatorResp();
        }
    }

    @RespParamHandler
    @PostMapping(value = "/delete")
    @RequiresPermissions("sys:operator:delete")
    public Object delete(@Valid @RequestBody OperatorQuery reqParam)  {
        return operatorService.delete(getCredential(),reqParam.getOpId());
    }
}

