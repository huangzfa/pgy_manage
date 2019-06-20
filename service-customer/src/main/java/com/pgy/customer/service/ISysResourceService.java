package com.pgy.customer.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pgy.customer.entity.SysResource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pgy.customer.entity.credential.OperatorCredential;
import com.pgy.customer.entity.req.query.ResourceQuery;
import com.pgy.customer.entity.req.save.ResourceSaveReq;

/**
 * <p>
 * 业务资源配置表 服务类
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-19
 */
public interface ISysResourceService extends IService<SysResource> {

    IPage<SysResource> getPage(ResourceQuery query);

    /**
     * 查看详情
     * @param id
     * @return
     */
    SysResource getById(Integer id);

    /**
     * 保存
     * @param credential
     * @param resource
     * @return
     */
    String save(OperatorCredential credential,ResourceSaveReq resource);


    String delete(OperatorCredential credential,Integer id);
}
