package com.feihu1024.panserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.feihu1024.panserver.entity.FtpUser;
import com.feihu1024.panserver.interrupt.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "文件访问服务")

@Controller
@CrossOrigin
@RequestMapping("/pan-server/file")
public class FileController {

    @ApiOperation(value = "获取文件、目录列表", notes = "")
    @ResponseBody
    @GetMapping("/list")
    public ResponseResult<List<FtpUser>> getAllUsers(@ApiParam("文件路径") String path, HttpServletRequest request) {
        String ss = request.getHeader("user_info");
        JSONObject userInfo = JSON.parseObject(request.getHeader("user_info"));
        return new ResponseResult(userInfo);
    }
}
