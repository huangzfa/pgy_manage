package com.pgy.customer.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pgy.customer.entity.SysOperator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pgy.customer.entity.req.query.OperatorQuery;
import com.pgy.customer.entity.req.save.OperatorSaveReq;
import com.pgy.customer.entity.resp.OperatorResp;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.Set;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-14
 */
public interface SysOperatorDao extends BaseMapper<SysOperator> {

    /**
     * 查询用户拥有的权限
     * @param param
     * @return
     */
    Set<String> queryOperatorPermission(HashMap<String,Object> param);

    /**
     *
     * @param page
     * @param req
     * @return
     */
    IPage<OperatorResp> getPage(@Param("page") Page<OperatorResp> page, @Param("req") OperatorQuery req);

    /**
     * 是否存在该账号
     * @param req
     * @return
     */
    int hasOperator(@Param("req") OperatorSaveReq req);

    /**
     * 员工详情
     * @param opId
     * @return
     */
    OperatorResp getByOpId(@Param("opId") Integer opId);

}
