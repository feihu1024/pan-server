package com.feihu1024.panserver.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义工具类
 */
public class Util {
    /**
     * 通过header获取用户信息
     */
    public static JSONObject getUserInfo(HttpServletRequest request) {
        JSONObject userInfo = JSON.parseObject(request.getHeader("user_info"));
        return userInfo;
    }
}
