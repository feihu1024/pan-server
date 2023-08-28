package com.feihu1024.panserver.controller;


import com.feihu1024.panserver.common.CustomMD5PasswordEncoder;
import com.feihu1024.panserver.interrupt.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "通用工具类服务")

@Controller
@CrossOrigin
@RequestMapping("/utils")
public class UtilsController {

    @Autowired
    CustomMD5PasswordEncoder customMD5PasswordEncoder;

    @ApiOperation(value = "md5加密指字符串", notes = "一般用作密码加密")
    @ResponseBody
    @GetMapping("/encryptByMD5")
    public ResponseResult<String> encryptByMD5(@ApiParam(value = "明文字符串", type = "String" ,required = true) String text) {
        return new ResponseResult(customMD5PasswordEncoder.encode(text));
    }
}
