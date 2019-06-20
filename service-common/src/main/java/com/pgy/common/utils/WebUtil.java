package com.pgy.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class WebUtil {

	/**
	 * 生存验证码图片，并保存到session
	 * 
	 * @return
	 */
	public static BufferedImage getCaptchaImage(final HttpSession session, String KEY) {
		int width = 90;
		int height = 30;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Graphics g = image.createGraphics();
		Random random = new Random();
		g.setColor(getColor(180, 250));
		g.fillRect(0, 0, width, height);
		for (int i = 0; i < 5; i++) {
			g.setColor(getColor(50, 100));
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			g.drawOval(x, y, 4, 4);
		}

		g.setFont(new Font("", Font.PLAIN, 28));
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			g.setColor(new Color(20 + random.nextInt(80), 20 + random.nextInt(100), 20 + random.nextInt(90)));
			g.drawString(sRand, 15, 25);
			for (int j = 0; j < 12; j++) {
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				int x1 = random.nextInt(9);
				int y1 = random.nextInt(9);
				g.drawLine(x, y, x + x1, y + y1);
			}
		}
		session.setAttribute(KEY, sRand);
		return image;

	}

	private static Color getColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}

		int red = fc + random.nextInt(bc - fc);
		int green = fc + random.nextInt(bc - fc);
		int blue = fc + random.nextInt(bc - fc);

		return new Color(red, green, blue);
	}
	/**
	 * 获取访问ip
	 * @param request
	 * @return
	 */
	public static String getRemoteIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");  
        if (!isEffectiveRemoteAddr(ip)) {  
            if (!isEffectiveRemoteAddr(ip)) {  
                ip = request.getHeader("Proxy-Client-IP");  
            }  
            if (!isEffectiveRemoteAddr(ip)) {  
                ip = request.getHeader("WL-Proxy-Client-IP");  
            }  
            if (!isEffectiveRemoteAddr(ip)) {  
                ip = request.getHeader("HTTP_CLIENT_IP");  
            }  
            if (!isEffectiveRemoteAddr(ip)) {  
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
            }  
            if (!isEffectiveRemoteAddr(ip)) {  
                ip = request.getRemoteAddr();  
            }  
        } else if (ip != null && ip.length() > 15) {  
            String[] ips = ip.split(",");  
            for (int index = 0; index < ips.length; index++) {  
                String strIp = (String) ips[index];  
                if (!("unknown".equalsIgnoreCase(strIp))) {  
                    ip = strIp;  
                    break;  
                }  
            }  
        }  
        return ip;  
	}
	/**
	 * 远程地址是否有效.
	 * 
	 * @param remoteAddr 远程地址
	 * @return true代表远程地址有效，false代表远程地址无效
	 */
	private static boolean isEffectiveRemoteAddr(final String remoteAddr) {
		if (remoteAddr == null || "".equals(remoteAddr.trim()) || "unknown".equalsIgnoreCase(remoteAddr.trim())){
			return false;
		}
		return true;
	}
	/**
	 * 校验请求是不是ajax
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request){
		return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"));
	}
	//判断 是否是微信浏览器
	public static boolean isWx(HttpServletRequest request){
		String userAgent = request.getHeader("user-agent").toLowerCase();
		return userAgent.indexOf("micromessenger")>-1;
	}
	
}
