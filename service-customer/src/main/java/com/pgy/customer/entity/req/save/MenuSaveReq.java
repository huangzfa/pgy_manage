package com.pgy.customer.entity.req.save;

import com.pgy.common.entity.ReqParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/6/18
 */
@Data
public class MenuSaveReq extends ReqParam {
    private Integer menuId;

    private Integer parentId;

    @NotBlank(message = "姓名不能为空")
    private String menuName;

    @NotNull(message = "有效性不能为空")
    private Integer menuState;

    @NotBlank(message = "菜单类型不能为空")
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
     * 排序默认0
     */
    private Integer menuSort;

}
