package com.pgy.customer.service;

import com.pgy.customer.entity.SysOperatorLoginLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pgy.customer.entity.credential.OperatorCredential;

/**
 * <p>
 * 登录日志 服务类
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-15
 */
public interface ISysOperatorLoginLogService extends IService<SysOperatorLoginLog> {

    void save(OperatorCredential credential, int loginType);

}
