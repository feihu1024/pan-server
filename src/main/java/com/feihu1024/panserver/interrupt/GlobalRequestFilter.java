package com.feihu1024.panserver.interrupt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.feihu1024.panserver.ftpserver.CustomFtpPlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求过滤器，用于绑定token中的用户信息
 */

@WebFilter(filterName = "tokenFilter", urlPatterns = {"/pan-server/**"})
@Component
public class GlobalRequestFilter implements Filter {

    public static final Logger log = LoggerFactory.getLogger(CustomFtpPlet.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        CustomRequestWrapper requestWrapper = new CustomRequestWrapper((HttpServletRequest) servletRequest);

        String authorization = requestWrapper.getHeader("Authorization");

        if (!StringUtils.isEmpty(authorization)) {
            // 验签并获取PayLoad
            String tokenString = authorization.replace("Bearer ".toLowerCase(), "");
            String payLoad = null;
            try {
                Jwt jwt = JwtHelper.decodeAndVerify(tokenString, new MacSigner("auth_pan"));
                payLoad = jwt.getClaims();
            } catch (Exception e) {
                log.error("验签失败", e);
            }

            // 从payLoad中提取用户信息
            if (!StringUtils.isEmpty(payLoad)) {
                Map<String, Object> map = new HashMap<>();
                JSONObject authentication = JSON.parseObject(payLoad);
                map.put("id", authentication.get("id"));
                map.put("user_name", authentication.get("user_name"));
                map.put("rule", authentication.get("rule"));
                map.put("home_directory", authentication.get("home_directory"));
                requestWrapper.addHeader("user_info", JSON.toJSONString(map));
            }
        }
        filterChain.doFilter(requestWrapper, servletResponse);
    }
}
