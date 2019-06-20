package com.pgy.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 请求header参数
 * @author: jason
 * @date:2019/2/22 15:38
 */
@Getter
@Setter
public class ReqHeaderParam implements Serializable {

    /** 请求id */
    private String id;

    /** 网络类型 */
    private String netType;

    /** 请求时间戳 */
    private String time;



}
