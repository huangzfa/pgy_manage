package com.pgy.customer.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pgy.customer.entity.req.OperatorLoginReq;
import com.pgy.customer.entity.req.query.OperatorQuery;
import com.pgy.customer.entity.req.save.OperatorSaveReq;
import com.pgy.customer.entity.SysOperator;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pgy.customer.entity.credential.OperatorCredential;
import com.pgy.customer.entity.resp.OperatorResp;

import java.io.IOException;
import java.util.Set;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-14
 */
public interface ISysOperatorService extends IService<SysOperator> {
    /**
     *
     * @param reqParam
     * @return
     * @throws IOException
     */
    String imageCode(OperatorLoginReq reqParam) throws IOException;

    /**
     * 登录
     * @param userName
     * @return
     */
    SysOperator queryOperatorByLoginName(String userName);

    /**
     * 获取用户权限
     * @param opId
     * @return
     */
    Set<String> queryOperatorPermission(Integer opId);

    /**
     * 登录成功后更新信息
     * @param credential
     */
    void updateOperatorForLogin(OperatorCredential credential);

    /**
     * 员工列表分页
     * @param reqParam
     * @return
     */
    IPage<OperatorResp> getPage(OperatorQuery reqParam);

    /**
     * 员工保存
     * @param record
     * @return
     */
    String save(OperatorSaveReq record,OperatorCredential credential) throws Exception;

    /**
     * 员工详情
     * @param opId
     * @return
     */
    OperatorResp getByOpId(Integer opId);

    /**
     * 删除员工
     * @param credential
     * @param opId
     * @return
     */
    String delete(OperatorCredential credential,Integer opId);

}
