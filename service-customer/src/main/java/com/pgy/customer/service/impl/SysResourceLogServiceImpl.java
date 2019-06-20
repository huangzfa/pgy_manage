package com.pgy.customer.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pgy.customer.entity.SysResourceLog;
import com.pgy.customer.dao.SysResourceLogDao;
import com.pgy.customer.entity.credential.OperatorCredential;
import com.pgy.customer.entity.req.query.ResourceQuery;
import com.pgy.customer.service.ISysResourceLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源配置日志记录表 服务实现类
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-19
 */
@Service
public class SysResourceLogServiceImpl extends ServiceImpl<SysResourceLogDao, SysResourceLog> implements ISysResourceLogService {

    @Autowired
    private SysResourceLogDao resourceLogDao;

    /**
     * 分页查询
     * @param credential
     * @param query
     * @return
     */
    @Override
    public IPage<SysResourceLog> getPage(OperatorCredential credential, ResourceQuery query){
        Page<SysResourceLog> page= new Page<SysResourceLog>();
        page.setCurrent(query.getCurPage());
        return resourceLogDao.getPage(page ,query);
    }
}
