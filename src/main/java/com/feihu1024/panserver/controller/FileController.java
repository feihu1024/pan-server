package com.feihu1024.panserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.feihu1024.panserver.entity.PanFile;
import com.feihu1024.panserver.interrupt.ResponseResult;
import com.feihu1024.panserver.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "文件访问服务")

@Controller
@CrossOrigin
@RequestMapping("/pan-server/file")
public class FileController {

    @Autowired
    FileService fileService;

    @ApiOperation(value = "获取文件、目录列表", notes = "")
    @ResponseBody
    @GetMapping("/list")
    public ResponseResult<List<PanFile[]>> getAllUsers(@ApiParam(value = "文件路径",defaultValue = "/") String path, HttpServletRequest request) {
        // 获取用户信息参数
        JSONObject userInfo = JSON.parseObject(request.getHeader("user_info"));
        ResponseResult result = null;

        // 设置默认path参数
        if(path ==null) path="/";

        try{
            PanFile[] files = fileService.getFileListByPath(userInfo.getLong("id"),path);
            result = new ResponseResult(files);
        }catch (Exception e) {
            result = new ResponseResult(false, 500,"获取列表失败，请检查文件路径!",null);
        }
        return result;
    }
}
