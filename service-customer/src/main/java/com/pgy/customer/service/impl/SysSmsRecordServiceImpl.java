package com.pgy.customer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pgy.common.cache.Cache;
import com.pgy.common.config.ComSystemConfig;
import com.pgy.common.constant.Consts;
import com.pgy.common.dic.ZD;
import com.pgy.common.enums.RespEnum;
import com.pgy.common.exception.RetMsgException;
import com.pgy.common.utils.RegExpValidatorUtils;
import com.pgy.customer.dao.SysSmsRecordDao;
import com.pgy.customer.entity.req.OperatorLoginReq;
import com.pgy.customer.entity.SysSmsRecord;
import com.pgy.customer.entity.req.SendSmsCodeReq;
import com.pgy.customer.service.ISysSmsRecordService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 短信验证码 服务实现类
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-14
 */
@Service
public class SysSmsRecordServiceImpl extends ServiceImpl<SysSmsRecordDao, SysSmsRecord> implements ISysSmsRecordService {

    private final int verifyCodeLength = 4;//验证码长度

    @Autowired
    private SysSmsRecordDao sysSmsRecordDao;
    @Autowired
    private Cache cache;

    /**
     * 查询最后一次验证码
     *
     * @param phone
     * @param type
     * @return
     */
    @Override
    public SysSmsRecord queryLastSmsVerifyCode(String phone, int type) {
        return sysSmsRecordDao.queryLastSmsVerifyCode(phone, type);
    }

    /**
     * 发送登录短信
     *
     * @param req
     */
    @Override
    public String sendSms(SendSmsCodeReq req) {
        String code = (String) cache.get(req.getPhone());
        if (StringUtils.isBlank(code)) {
            throw new RetMsgException(RespEnum.IMAGE_CODE_NOT_EXIST);
        }
        if (!req.getImageCode().equalsIgnoreCase(code)) {
            throw new RetMsgException(RespEnum.IMAGE_CODE_IS_ERROR);
        }
        String verifyCode = "";
        //查询上个验证码是否有效
        SysSmsRecord record = sysSmsRecordDao.queryLastSmsVerifyCode(req.getPhone(), ZD.smsType_login);
        //验证码有效的情况下
        if (record != null && record.getInvalidTime().getTime() > System.currentTimeMillis()) {
            verifyCode = record.getCode();
        } else {
            //获取随机验证码
            verifyCode = RandomStringUtils.random(verifyCodeLength, false, true);
        }
        //开发环境
        if (ComSystemConfig.isDev) {
            verifyCode = Consts.SMS_CODE_TEST;
        }
        SysSmsRecord entity = new SysSmsRecord()
                .setCode(verifyCode)
                .setMobile(req.getPhone())
                .setInvalidTime(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .setSmsType(ZD.smsType_login);
        sysSmsRecordDao.insert(entity);
        if (ComSystemConfig.isDev) {
            // TODO 发送短信验证码
        }
        return RespEnum.SUCCESS.getMsg();
    }

    /**
     * 校验短信
     *
     * @param req
     * @param smsBizType
     */
    @Override
    public void checkVerifyCode(OperatorLoginReq req, int smsBizType) {
        //校验验证码
        if (!RegExpValidatorUtils.isNumberExt(req.getCode()) || req.getCode().length() != verifyCodeLength) {
            throw new RetMsgException(RespEnum.PWD_SMS_GET_ERROR);
        }
        //判断验证码是否相等,判断验证码是否失效
        SysSmsRecord record = sysSmsRecordDao.queryLastSmsVerifyCode(req.getPhone(), ZD.smsType_login);
        if (record == null) {
            throw new RetMsgException(RespEnum.PWD_SMS_OVERDUE);
        }
        //验证码失效
        if (!req.getCode().equals(record.getCode())) {
            throw new RetMsgException(RespEnum.PWD_SMS_GET_ERROR);
        }
        //验证码过期
        if (System.currentTimeMillis() > record.getInvalidTime().getTime()) {
            throw new RetMsgException(RespEnum.PWD_SMS_OVERDUE);
        }

    }
}
