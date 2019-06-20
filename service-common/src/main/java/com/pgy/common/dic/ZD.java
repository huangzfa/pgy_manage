package com.pgy.common.dic;


public interface ZD {
	
	/**
	 *  菜单类型
	 */
    public String menuType = "menuType";
	
	/**
	 *  数据有效性
	 */
    public String dataState = " ";
	
	/**
	 *  短信类型
	 */
    public String smsType = " ";
	

    public String menuType_m = "m";
	

    public String menuType_mo = "mo";
	

    public int dataState_valid = 1;
	

    public int dataState_invalid = 0;
	

    public int smsType_login = 1;
	

    public int smsType_login_out = 0;
}