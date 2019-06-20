package com.pgy.customer.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pgy.customer.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pgy.customer.entity.credential.OperatorCredential;
import com.pgy.customer.entity.req.query.RoleQuery;
import com.pgy.customer.entity.req.save.RoleSaveReq;
import com.pgy.customer.entity.resp.RoleResp;

import java.util.List;

/**
 * <p>
 * 系统角色 服务类
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-14
 */
public interface ISysRoleService extends IService<SysRole> {

     /**
      * 根据用户id查询拥有的角色
      * @param opId
      * @return
      */
     List<SysRole> getList(Integer opId);

     /**
      * 分页查询
      * @param req
      * @return
      */
     IPage<RoleResp> getPage(RoleQuery req);

     /**
      * 编辑角色
      * @param req
      * @return
      */
     JSONObject getByRoleId(RoleQuery req);

     /**
      * 保存角色
      * @param req
      * @return
      */
     String saveRole(RoleSaveReq req, OperatorCredential credential) ;

     /**
      * 删除
      * @param credential
      * @param roleId
      * @return
      */
     String delete(OperatorCredential credential,Integer roleId);
}
