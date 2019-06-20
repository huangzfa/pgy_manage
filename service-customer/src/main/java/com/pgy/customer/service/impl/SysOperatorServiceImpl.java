package com.pgy.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pgy.common.dic.ZD;
import com.pgy.common.enums.RandomTypeEnum;
import com.pgy.common.constant.Consts;
import com.pgy.common.enums.RespEnum;
import com.pgy.common.exception.RetMsgException;
import com.pgy.common.utils.PasswordUtil;
import com.pgy.customer.dao.SysOperatorRoleDao;
import com.pgy.customer.entity.req.OperatorLoginReq;
import com.pgy.customer.entity.req.query.OperatorQuery;
import com.pgy.customer.entity.req.save.OperatorSaveReq;
import com.pgy.customer.entity.SysOperator;
import com.pgy.customer.dao.SysOperatorDao;
import com.pgy.customer.entity.credential.OperatorCredential;
import com.pgy.customer.entity.resp.OperatorResp;
import com.pgy.customer.entity.resp.OperatorRoleResp;
import com.pgy.customer.service.IImageCodeService;
import com.pgy.customer.service.ISysOperatorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jodd.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-14
 */
@Service
public class SysOperatorServiceImpl extends ServiceImpl<SysOperatorDao, SysOperator> implements ISysOperatorService {

    @Autowired
    private IImageCodeService iImageCodeService;
    @Autowired
    private SysOperatorDao operatorDao;
    @Autowired
    private SysOperatorRoleDao operatorRoleDao;

    /**
     * 获取图形验证码
     *
     * @param reqParam
     * @return
     * @throws IOException
     */
    @Override
    public String imageCode(OperatorLoginReq reqParam) throws IOException {
        return iImageCodeService.getImageCode(Consts.LOGIN_IMAGE_CACHE_KEY, reqParam.getPhone(),  RandomTypeEnum.NUMBER.name(), Consts.IMAGE_CODE_CACHE_TIME);
    }

    /**
     * 通过手机号查询
     * @param userName
     * @return
     */
    @Override
    public SysOperator queryOperatorByLoginName(String userName){
        return operatorDao.selectOne(new QueryWrapper<SysOperator>()
                .eq(SysOperator.IS_DELETE,Consts.INT_ZERO)
                .eq(SysOperator.LOGIN_NAME,userName));
    }

    /**
     *
     * @param opId
     * @return
     */
    @Override
    public Set<String> queryOperatorPermission(Integer opId){
        HashMap<String,Object> param = new HashMap<>();
        param.put("opId",opId);
        param.put("menuType", ZD.menuType_mo);
        param.put("menuState",ZD.dataState_valid);
        return operatorDao.queryOperatorPermission(param);
    }

    /**
     * 登录成功后更新信息
     * @param credential
     */
    @Override
    public void updateOperatorForLogin(OperatorCredential credential){
        SysOperator updateOperator = new SysOperator()
                .setOpId(credential.getOpId())
                .setLastLoginIp(credential.getIp())
                .setLastLoginTime(credential.getLoginTime())
                .setSessionId(String.valueOf(credential.getSessionId()));
        operatorDao.updateById(updateOperator);
    }

    /**
     * 分页查询
     * @param req
     * @return
     */
    @Override
    public IPage<OperatorResp> getPage(OperatorQuery req){
        Page<OperatorResp> page= new Page<OperatorResp>();
        page.setCurrent(req.getCurPage());
        IPage<OperatorResp> pageList =  operatorDao.getPage(page ,req);
        if( pageList.getTotal() == Consts.INT_ZERO){
            return pageList;
        }
        //获取所有人员id
        List<Integer> opIds =pageList.getRecords().stream()
                .map(OperatorResp::getOpId)
                .collect(Collectors.toList());
        //查询所有人的角色，并分配
        List<OperatorRoleResp> list = operatorRoleDao.getRoleByOpIds(opIds);
        for(OperatorResp operator : pageList.getRecords() ){
            for(OperatorRoleResp roleVo:list){
                if( operator.getOpId().equals(roleVo.getOpId())){
                    if(StringUtil.isBlank(operator.getRoleNames())){
                        operator.setRoleNames(roleVo.getRoleName());
                    }else{
                        operator.setRoleNames(operator.getRoleNames()+","+roleVo.getRoleName());
                    }
                }
            }
        }
        return pageList;
    }

    /**
     * 保存账号
     * @param record
     * @return
     */
    @Override
    public String save(OperatorSaveReq record,OperatorCredential credential) throws Exception{
        if( operatorDao.hasOperator(record) >Consts.INT_ZERO){
            throw new RetMsgException(RespEnum.DEFINE_MSG.getCode(),"该手机号已注册");
        }
        SysOperator operator = new SysOperator();
        BeanUtils.copyProperties(record,operator);
        if( operator.getOpId() == null || operator.getOpId().equals(Consts.INT_ONE)){
            operator.setAddOperatorId(credential.getOpId());
            operator.setLoginPwd(PasswordUtil.encryptPwdForSalt(Consts.LOGIN_PASSWORD));
            operatorDao.insert(operator);
        }else{
            operator.setModifyOperatorId(credential.getOpId());
            operator.setModifyTime(new Date());
            operatorDao.updateById(operator);
        }
        return RespEnum.SUCCESS.getMsg();
    }

    /**
     * 员工详情
     * @param opId
     * @return
     */
    @Override
    public OperatorResp getByOpId(Integer opId){
        return operatorDao.getByOpId(opId);
    }

    /**
     * 删除
     * @param credential
     * @param opId
     * @return
     */
    @Override
    public String delete(OperatorCredential credential,Integer opId){
        if( opId == null ){
            throw new RetMsgException(RespEnum.DEFINE_MSG.getCode(),"员工id不能为空");
        }
        SysOperator operator = operatorDao.selectOne(new QueryWrapper<SysOperator>()
                .eq(SysOperator.IS_DELETE,Consts.INT_ZERO)
                .eq(SysOperator.OP_ID,opId));
        if( operator == null ){
            throw new RetMsgException(RespEnum.DEFINE_MSG.getCode(),"用户不存在");
        }
        SysOperator record = new SysOperator()
                .setIsDelete(opId)
                .setModifyTime(new Date())
                .setModifyOperatorId(credential.getOpId())
                .setOpId(opId);
        operatorDao.updateById(record);
        return RespEnum.SUCCESS.getCode();
    }
}
