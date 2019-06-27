package com.pgy.customer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统菜单
 * </p>
 *
 * @author huangzhongfa
 * @since 2019-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父级id，root级为0
     */
    private Integer parentId;

    /**
     * 菜单类型：m菜单、mo权限【字典】
     */
    private String menuType;

    /**
     * 权限标识：sys:menu:edit
     */
    private String permission;

    /**
     * 菜单路径
     */
    private String menuUrl;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 菜单级别，默认0开始
     */
    private Integer menuLevel;

    /**
     * 是否是父级1是0不是
     */
    private Integer isParent;

    /**
     * 排序默认0
     */
    private Integer menuSort;

    /**
     * 0无效1有效
     */
    private Integer menuState;

    /**
     * 添加时间
     */
    private Date addTime;

    private Integer addOperatorId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    private Integer modifyOperatorId;

    /**
     * !0删除
     */
    private Integer isDelete;

    /** 非数据库字段
     * 父级菜单名称
     */
    @TableField(exist = false)
    private String parentName;


    public static final String MENU_ID = "menu_id";

    public static final String MENU_NAME = "menu_name";

    public static final String PARENT_ID = "parent_id";

    public static final String MENU_TYPE = "menu_type";

    public static final String PERMISSION = "permission";

    public static final String MENU_URL = "menu_url";

    public static final String MENU_ICON = "menu_icon";

    public static final String MENU_LEVEL = "menu_level";

    public static final String IS_PARENT = "is_parent";

    public static final String MENU_SORT = "menu_sort";

    public static final String MENU_STATE = "menu_state";

    public static final String ADD_TIME = "add_time";

    public static final String ADD_OPERATOR_ID = "add_operator_id";

    public static final String MODIFY_TIME = "modify_time";

    public static final String MODIFY_OPERATOR_ID = "modify_operator_id";

    public static final String IS_DELETE = "is_delete";

    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }

}
