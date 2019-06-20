package com.pgy.common.utils;

import java.util.UUID;

public class PasswordUtil {

	/** 
     * 生成含有随机盐的密码 
	 * @throws Exception 
     */  
    public static String encryptPwdForSalt(String password) throws Exception {  
        String salt = UUID.randomUUID().toString().replace("-", "");//32位
        password=MD5Util.encrypt(password);//32
        password = MD5Util.encrypt(password + salt); //32位
        char[] pwd = new char[64];  
        for (int i = 0; i < 64; i += 2) {  
            pwd[i] = password.charAt(i / 2);
            pwd[i + 1] = salt.charAt(i / 2);  
        }  
        return new String(pwd);  
    }  
  
    /** 
     * 校验密码是否正确 
     * @throws Exception 
     */  
    public static boolean verifyPwd(String password, String md5) throws Exception {  
        char[] cs1 = new char[32];  
        char[] saltArr = new char[32];  
        for (int i = 0; i < 64; i += 2) {  
            cs1[i / 2] = md5.charAt(i);
            saltArr[i / 2] = md5.charAt(i + 1);  
        }  
        String salt = new String(saltArr);  
        return MD5Util.encrypt(MD5Util.encrypt(password) + salt).equals(new String(cs1));  
    }  
  
	public static void main(String[] args) throws Exception {
		String md5Pwd=encryptPwdForSalt("pwd56msAdmin");
		System.out.println(md5Pwd);
		System.out.println(verifyPwd("pwd56msAdmin", md5Pwd));
		for (int i = 0; i < 2; i++) {
			String pwd=UUID.randomUUID().toString().replace("-", "");
			pwd="123456";
			String aaaa = encryptPwdForSalt(pwd);
			if (verifyPwd("123456", aaaa)) {
				System.out.println(aaaa+"<>"+aaaa.length());
			}
		}
	}
}
