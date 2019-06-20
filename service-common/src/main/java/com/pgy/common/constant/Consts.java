package com.pgy.common.constant;

/**
 * 字典常量
 *
 * @author: jason
 * @date:2019/3/1 17:39
 */
public class Consts {


    //图形验证码缓存时间
    public static final Long IMAGE_CODE_CACHE_TIME = 30 * 60 * 1000L;

    //登录图形缓存信息，后缀加上用户手机号码和productId
    public static final String LOGIN_IMAGE_CACHE_KEY = "login_image_cache_";

    public static final String STRING_ZERO = "0";

    public static final String STRING_ONE = "1";

    public static final Integer INT_ZERO = 0;

    public static final Integer INT_ONE = 1;

    //分页大小
    public static final Integer pageSize = 15;

    //测试环境验证码
    public static final String SMS_CODE_TEST = "8888";

    //登录密码
    public static final String LOGIN_PASSWORD = "123456";


}
