package com.pgy.customer.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pgy.common.enums.RespEnum;
import com.pgy.common.exception.RetMsgException;
import com.pgy.customer.dao.SysOperatorDao;
import com.pgy.customer.dao.SysOperatorRoleDao;
import com.pgy.customer.dao.SysRoleDao;
import com.pgy.customer.entity.SysOperatorRole;
import com.pgy.customer.entity.req.query.RoleQuery;
import com.pgy.customer.entity.req.save.OperatorRoleSaveReq;
import com.pgy.customer.entity.resp.OperatorResp;
import com.pgy.customer.entity.resp.RoleResp;
import com.pgy.customer.service.ISysOperatorRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-17
 */
@Service
public class SysOperatorRoleServiceImpl extends ServiceImpl<SysOperatorRoleDao, SysOperatorRole> implements ISysOperatorRoleService {

    @Autowired
    private SysOperatorDao operatorDao;
    @Autowired
    private SysOperatorRoleDao operatorRoleDao;
    @Autowired
    private SysRoleDao roleDao;
    @Autowired
    private TransactionTemplate transactionTemplate;

    /**
     * 分配角色列表
     * @param opId
     * @param req
     * @return
     */
    @Override
    public JSONObject list(Integer opId, RoleQuery req){
        OperatorResp operator = operatorDao.getByOpId(opId);
        if (operator == null) {
            throw new RetMsgException(RespEnum.USER_ACCOUNT_NOT_EXIST);
        }
        JSONObject object = new JSONObject();
        //登录手机号
        object.put("loginName", operator.getLoginName());
        //真实姓名
        object.put("realName", operator.getRealName());
        //该用户关联过的角色
        List<SysOperatorRole> list = operatorRoleDao.getByOpId(opId);
        //查询所有角色
        List<RoleResp> roleList = roleDao.getAll(req);
        JSONArray array = new JSONArray();
        for (RoleResp resp : roleList) {
            JSONObject object1 = new JSONObject();
            boolean checked = false;
            for (SysOperatorRole or : list) {
                if (resp.getRoleId().equals(or.getRoleId())) {
                    checked = true;
                    break;
                }
            }
            object1.put("roleName", resp.getRoleName());
            object1.put("roleId", resp.getRoleId());
            object1.put("checked", checked);
            array.add(object1);
        }
        object.put("roleList", array);
        return object;
    }

    /**
     * 分配角色保存
     * @param req
     * @return
     */
    @Override
    public String save(OperatorRoleSaveReq req){
        RespEnum respEnum = transactionTemplate.execute(new TransactionCallback<RespEnum>() {
            @Override
            public RespEnum doInTransaction(TransactionStatus status) {
                try {
                    //删除绑定的角色
                    operatorRoleDao.deleteByOpId(req.getOpId());
                    //插入角色菜单关联
                    if (StringUtils.isNotBlank(req.getRoleIds())) {
                        List<String> roleIdList = Arrays.asList(req.getRoleIds().split(","));
                        operatorRoleDao.batchInsert(roleIdList, req.getOpId());
                    }
                } catch (Exception e) {
                    status.setRollbackOnly();
                    return RespEnum.ERROR;
                }
                return RespEnum.SUCCESS;
            }
        });
        if (respEnum.getCode() != RespEnum.SUCCESS.getCode()) {
            throw new RetMsgException(respEnum);
        }
        return respEnum.getMsg();
    }

}
