package com.pgy.customer.dao;

import com.pgy.customer.entity.SysSmsRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 短信验证码 Mapper 接口
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-14
 */
public interface SysSmsRecordDao extends BaseMapper<SysSmsRecord> {
    /**
     * 查询验证码最后一条记录
     * @param mobile
     * @param smsBizType
     * @return
     */
    SysSmsRecord queryLastSmsVerifyCode(@Param("mobile")String mobile, @Param("smsType")int smsBizType);
}
