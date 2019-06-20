package com.pgy.customer.service.impl;

import com.pgy.customer.entity.SysOperatorLoginLog;
import com.pgy.customer.dao.SysOperatorLoginLogDao;
import com.pgy.customer.entity.credential.OperatorCredential;
import com.pgy.customer.service.ISysOperatorLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录日志 服务实现类
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-15
 */
@Service
public class SysOperatorLoginLogServiceImpl extends ServiceImpl<SysOperatorLoginLogDao, SysOperatorLoginLog> implements ISysOperatorLoginLogService {

    @Autowired
    private SysOperatorLoginLogDao operatorLoginLogDao;

    @Override
    public void save(OperatorCredential credential, int loginType){
        SysOperatorLoginLog log = new SysOperatorLoginLog()
                .setIp(credential.getIp())
                .setLoginType(loginType)
                .setSessionId(String.valueOf(credential.getSessionId()))
                .setOpId(credential.getOpId());
        operatorLoginLogDao.insert(log);
    }
}
