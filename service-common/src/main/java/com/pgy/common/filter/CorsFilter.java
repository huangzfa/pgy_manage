package com.pgy.common.filter;

import com.alibaba.fastjson.JSON;
import com.pgy.common.constant.Consts;
import com.pgy.common.enums.RespEnum;
import com.pgy.common.exception.RetMsgException;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**跨域设置
 * @author huangzhongfa
 * @description
 * @date 2019/6/15
 */
@Component
public class CorsFilter implements Filter{
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        System.out.println("*********************************过滤器被使用**************************");
        HttpServletRequest httpRequest = (HttpServletRequest)req;
        //没有登陆返回登陆
        if(httpRequest.getRequestURI().indexOf("login.jsp") > Consts.INT_ZERO){
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setStatus(HttpServletResponse.SC_OK);
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            res.setCharacterEncoding("UTF-8");
            PrintWriter writer = res.getWriter();
            Map<String, Object> map= new HashMap<>();
            map.put("code", RespEnum.NOT_LOGIN.getCode());
            map.put("msg", RespEnum.NOT_LOGIN.getMsg());
            response.getWriter().print(JSON.toJSONString(map));
            writer.close();
            return;
        }
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }
}
