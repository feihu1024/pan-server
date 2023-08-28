package com.feihu1024.panserver.interrupt;
import com.alibaba.fastjson.JSONObject;
import com.feihu1024.panserver.oauth2.AuthWebResponseExceptionTranslator;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * oauth2接口权限处理器（无效token或token过期）
 */

@Component
public class CustomAuthEntryPoint extends OAuth2AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        AuthWebResponseExceptionTranslator.UnauthorizedException unauthorizedException= new AuthWebResponseExceptionTranslator.UnauthorizedException(e.getMessage(), e);
        ResponseResult responseResult =new ResponseResult(false,unauthorizedException.getHttpErrorCode(),unauthorizedException.getOAuth2ErrorMessage(),null);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(responseResult));
    }
}
