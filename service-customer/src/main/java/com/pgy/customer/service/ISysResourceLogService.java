package com.pgy.customer.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pgy.customer.entity.SysResourceLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pgy.customer.entity.credential.OperatorCredential;
import com.pgy.customer.entity.req.query.ResourceQuery;

/**
 * <p>
 * 资源配置日志记录表 服务类
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-19
 */
public interface ISysResourceLogService extends IService<SysResourceLog> {

    /**
     * 分页查询
     * @param credential
     * @param query
     * @return
     */
    IPage<SysResourceLog> getPage(OperatorCredential credential, ResourceQuery query);
}
