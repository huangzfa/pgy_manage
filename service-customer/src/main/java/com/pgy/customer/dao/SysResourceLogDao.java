package com.pgy.customer.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pgy.customer.entity.SysResourceLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pgy.customer.entity.req.query.ResourceQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 资源配置日志记录表 Mapper 接口
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-19
 */
public interface SysResourceLogDao extends BaseMapper<SysResourceLog> {
    /**
     * 分页查询
     * @param page
     * @param query
     * @return
     */
    IPage<SysResourceLog> getPage(@Param("page") Page<SysResourceLog> page,@Param("query") ResourceQuery query);
}
