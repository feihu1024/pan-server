package com.feihu1024.panserver.controller;

import com.feihu1024.panserver.common.CustomMD5PasswordEncoder;
import com.feihu1024.panserver.interrupt.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "权限管理服务")

@Controller
@CrossOrigin
@RequestMapping("/oauth")

public class AuthController {

    @Autowired
    private TokenStore tokenStore;

    @ApiOperation(value = "退出登录", notes = "清除access_token及refresh_token,后期更新完成后就不再需要传入token了")
    @ResponseBody
    @DeleteMapping("/logout")
    public ResponseResult<ResponseResult> logout(HttpServletRequest httpRequest) {
        String authorization = httpRequest.getHeader("Authorization");
        if (authorization == null) {
            return new ResponseResult(false, 401, "退出失败: 无效token", false);
        }
        String access_token = authorization.split(" ")[1];
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(access_token);
        if(accessToken != null) {
            tokenStore.removeAccessToken(accessToken);
            OAuth2RefreshToken refreshToken =  accessToken.getRefreshToken();
            if(refreshToken != null) {
                tokenStore.removeRefreshToken(refreshToken);
            }
            return new ResponseResult(true,200,"\"退出成功\"",true);
        }else {
            return new ResponseResult(false, 401, "退出失败: 无效token", false);
        }
    }
}
