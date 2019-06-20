package com.pgy.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * MD5加密工具
 *
 * @author JingChenglong 2018/11/13 20:15
 *
 */
public class MD5Util {

	private static Logger log = LoggerFactory.getLogger(MD5Util.class);

	public synchronized static String encrypt(String toEncrypt) {
		try {
			return DigestUtils.md5Hex(toEncrypt.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			log.error("MD5加密异常; e:" + e.getMessage());
			return null;
		}
	}

	public synchronized static byte[] hash(String toEncrypt) {
		try {
			return DigestUtils.md5(toEncrypt.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			log.error("MD5加密异常; e:" + e.getMessage());
			return null;
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(MD5Util.encrypt("123").length());
	}
}