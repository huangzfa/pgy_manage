package com.pgy.customer.service;

import com.pgy.customer.entity.req.OperatorLoginReq;
import com.pgy.customer.entity.SysSmsRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pgy.customer.entity.req.SendSmsCodeReq;

/**
 * <p>
 * 短信验证码 服务类
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-14
 */
public interface ISysSmsRecordService extends IService<SysSmsRecord> {
    /**
     *登陆发送短信
     * @param req
     * @return
     */
    String sendSms(SendSmsCodeReq req);

    /**
     *最后一次发送验证码
     * @param phone
     * @param type
     * @return
     */
    SysSmsRecord queryLastSmsVerifyCode(String phone,int type);

    /**
     * 校验短信
     * @param req
     * @param smsBizType
     */
    void checkVerifyCode(OperatorLoginReq req, int smsBizType);
}
