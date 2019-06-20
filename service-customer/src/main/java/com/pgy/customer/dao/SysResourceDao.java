package com.pgy.customer.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pgy.customer.entity.SysResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pgy.customer.entity.req.query.ResourceQuery;
import com.pgy.customer.entity.req.save.ResourceSaveReq;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 业务资源配置表 Mapper 接口
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-19
 */
public interface SysResourceDao extends BaseMapper<SysResource> {

    IPage<SysResource> getPage(@Param("page") Page<SysResource> page,@Param("query") ResourceQuery query);

    Integer hasType(ResourceSaveReq req);
}
