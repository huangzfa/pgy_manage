package com.pgy.customer.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pgy.common.constant.Consts;
import com.pgy.common.enums.RespEnum;
import com.pgy.common.exception.RetMsgException;
import com.pgy.customer.dao.SysOperatorRoleDao;
import com.pgy.customer.dao.SysRoleDao;
import com.pgy.customer.dao.SysRoleMenuDao;
import com.pgy.customer.entity.SysMenu;
import com.pgy.customer.entity.SysOperatorRole;
import com.pgy.customer.entity.SysRole;
import com.pgy.customer.entity.credential.OperatorCredential;
import com.pgy.customer.entity.req.query.RoleQuery;
import com.pgy.customer.entity.req.save.RoleSaveReq;
import com.pgy.customer.entity.resp.RoleResp;
import com.pgy.customer.service.ISysMenuService;
import com.pgy.customer.service.ISysRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 系统角色 服务实现类
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-14
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private ISysMenuService menuService;
    @Autowired
    private SysRoleMenuDao roleMenuDao;
    @Autowired
    private SysOperatorRoleDao operatorRoleDao;
    @Autowired
    private TransactionTemplate transactionTemplate;

    /**
     * 根据用户id查询角色
     * @param opId
     * @return
     */
    @Override
    public List<SysRole> getList(Integer opId){
        return sysRoleDao.getByOpId(opId);
    }

    /**
     * 角色列表
     * @param req
     * @return
     */
    @Override
    public IPage<RoleResp> getPage(RoleQuery req){
        Page<RoleResp> page= new Page<RoleResp>();
        page.setCurrent(req.getCurPage());
        return sysRoleDao.getPage(page ,req);
    }

    /**
     * 编辑角色
     * @param req
     * @return
     */
    @Override
    public JSONObject getByRoleId(RoleQuery req){
        JSONObject object = new JSONObject();
        if( req.getRoleId() == null ){
            object.put("role",new SysRole());
        }else{
            object.put("role",sysRoleDao.selectOne(new QueryWrapper<SysRole>()
                .eq(SysRole.IS_DELETE,Consts.INT_ZERO)
                .eq(SysRole.ROLE_ID,req.getRoleId())));
        }
        //查询所有菜单
        List<SysMenu> menuList = menuService.getAll();
        object.put("menuList",menuService.getChildrenMenu(menuList,Consts.INT_ZERO));
        return object;
    }

    @Override
    public String saveRole(RoleSaveReq req, OperatorCredential credential) {
        if (sysRoleDao.hasByRole(req) > Consts.INT_ZERO) {
            throw new RetMsgException(RespEnum.DEFINE_MSG.getCode(), "该角色标识已存在");
        }
        RespEnum respEnum = transactionTemplate.execute(new TransactionCallback<RespEnum>() {
            @Override
            public RespEnum doInTransaction(TransactionStatus status) {
                try {
                    SysRole role = new SysRole();
                    BeanUtils.copyProperties(req, role);
                    if (role.getRoleId() == null) {
                        role.setAddOperatorId(credential.getOpId());
                        sysRoleDao.insert(role);
                    } else {
                        role.setModifyTime(new Date());
                        role.setModifyOperatorId(credential.getOpId());
                        sysRoleDao.updateById(role);
                    }
                    //删除绑定的菜单
                    if (req.getRoleId() != null) {
                        roleMenuDao.deleteByRoleId(req.getRoleId());
                    }
                    //插入角色菜单关联
                    if (StringUtils.isNotBlank(req.getMenuIds())) {
                        List<String> menuIdList = Arrays.asList(req.getMenuIds().split(","));
                        roleMenuDao.batchInsert(menuIdList, role.getRoleId());
                    }
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

    @Override
    public String delete(OperatorCredential credential, Integer roleId) {
        if (roleId == null) {
            throw new RetMsgException(RespEnum.DEFINE_MSG.getCode(), "参数不能为空");
        }
        SysRole role = sysRoleDao.selectOne(new QueryWrapper<SysRole>()
                .eq(SysRole.IS_DELETE, Consts.INT_ZERO)
                .eq(SysRole.ROLE_ID, roleId));
        if (role == null) {
            throw new RetMsgException(RespEnum.DEFINE_MSG.getCode(), "角色不存在");
        }
        // 校验角色是否在使用
        int count = operatorRoleDao.selectCount(new QueryWrapper<SysOperatorRole>()
                .eq(SysOperatorRole.ROLE_ID, roleId));
        if (count > Consts.INT_ZERO) {
            throw new RetMsgException(RespEnum.DEFINE_MSG.getCode(), "角色正在使用");
        }
        //删除绑定的菜单
        roleMenuDao.deleteByRoleId(roleId);
        SysRole record = new SysRole()
                .setIsDelete(roleId)
                .setRoleId(roleId)
                .setModifyOperatorId(credential.getOpId())
                .setModifyTime(new Date());
        sysRoleDao.updateById(record);
        return RespEnum.SUCCESS.getMsg();
    }
}
