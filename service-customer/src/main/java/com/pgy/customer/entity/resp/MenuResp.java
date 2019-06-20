package com.pgy.customer.entity.resp;

import lombok.Data;

/**
 * @author huangzhongfa
 * @description
 * @date 2019/6/17
 */
@Data
public class MenuResp {
    private Integer id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父级id，root级为0
     */
    private Integer parentId;

    /**
     * 是否选中
     */
    private boolean checked;
}
