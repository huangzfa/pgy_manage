package com.pgy.customer.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pgy.common.constant.Consts;
import com.pgy.common.enums.RespEnum;
import com.pgy.common.exception.RetMsgException;
import com.pgy.customer.dao.SysResourceLogDao;
import com.pgy.customer.entity.SysResource;
import com.pgy.customer.dao.SysResourceDao;
import com.pgy.customer.entity.SysResourceLog;
import com.pgy.customer.entity.credential.OperatorCredential;
import com.pgy.customer.entity.req.query.ResourceQuery;
import com.pgy.customer.entity.req.save.ResourceSaveReq;
import com.pgy.customer.service.ISysResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;

/**
 * <p>
 * 业务资源配置表 服务实现类
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-19
 */
@Service
@Slf4j
public class SysResourceServiceImpl extends ServiceImpl<SysResourceDao, SysResource> implements ISysResourceService {

    @Autowired
    private SysResourceDao resourceDao;
    @Autowired
    private SysResourceLogDao resourceLogDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public IPage<SysResource> getPage(ResourceQuery query){
        Page<SysResource> page= new Page<SysResource>();
        page.setCurrent(query.getCurPage());
        return resourceDao.getPage(page ,query);
    }

    /**
     * 查看详情
     * @param id
     * @return
     */
    @Override
    public SysResource getById(Integer id){
        return resourceDao.selectOne(new QueryWrapper<SysResource>()
                .eq(SysResource.IS_DELETE, Consts.INT_ZERO)
                .eq(SysResource.ID,id));
    }

    /**
     * 保存
     * @param credential
     * @param req
     * @return
     */
    @Override
    public String save(OperatorCredential credential, ResourceSaveReq req) {
        if (resourceDao.hasType(req) > Consts.INT_ZERO) {
            throw new RetMsgException(RespEnum.DEFINE_MSG.getCode(), "该类型已存在");
        }
        RespEnum respEnum = transactionTemplate.execute(new TransactionCallback<RespEnum>() {
            @Override
            public RespEnum doInTransaction(TransactionStatus status) {
                try {
                    SysResource resource = new SysResource();
                    BeanUtils.copyProperties(req, resource);
                    SysResourceLog log = new SysResourceLog()
                            .setResType(resource.getResType())
                            .setResTypeSec(resource.getResTypeSec())
                            .setOldJson(JSON.toJSONString(resource))
                            .setAddOperatorId(credential.getOpId());
                    if (resource.getId() == null) {
                        resource.setAddOperatorId(credential.getOpId());
                        resourceDao.insert(resource);
                    } else {
                        log.setModifyJson(JSON.toJSONString(resource));
                        resource.setModifyOperatorId(credential.getOpId());
                        resource.setModifyTime(new Date());
                        resourceDao.updateById(resource);
                    }
                    resourceLogDao.insert(log);
                } catch (Exception e) {
                    log.info("资源配置："+e.getMessage());
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

    /**
     * 删除资源
     * @param credential
     * @param id
     * @return
     */
    @Override
    public String delete(OperatorCredential credential, Integer id) {
        if (id == null) {
            throw new RetMsgException(RespEnum.DEFINE_MSG.getCode(), "参数id不能为空");
        }
        SysResource resource = resourceDao.selectOne(new QueryWrapper<SysResource>()
                .eq(SysResource.IS_DELETE, Consts.INT_ZERO)
                .eq(SysResource.ID, id));
        if (resource == null) {
            throw new RetMsgException(RespEnum.DEFINE_MSG.getCode(), "该资源不存在");
        }
        RespEnum respEnum = transactionTemplate.execute(new TransactionCallback<RespEnum>() {
            @Override
            public RespEnum doInTransaction(TransactionStatus status) {
                try {
                    SysResource record = new SysResource()
                            .setId(id)
                            .setModifyOperatorId(credential.getOpId())
                            .setModifyTime(new Date())
                            .setIsDelete(id);
                    resourceDao.updateById(record);
                    SysResourceLog log = new SysResourceLog()
                            .setResType(resource.getResType())
                            .setResTypeSec(resource.getResTypeSec())
                            .setOldJson(JSON.toJSONString(resource))
                            .setModifyJson(JSON.toJSONString(resource))
                            .setAddOperatorId(resource.getModifyOperatorId());
                    resourceLogDao.insert(log);
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
