package com.feihu1024.panserver.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.feihu1024.panserver.entity.FileEntity;
import com.feihu1024.panserver.interrupt.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "文件查询服务")

@Controller
@CrossOrigin
@RequestMapping("/files")
public class FileController {



    @ApiOperation(value = "测试接口", notes = "测试接口描述信息")
    @ResponseBody
    @GetMapping("/test")
    public ResponseResult<ResponseResult> getFiles(@ApiParam(value = "文件名称", name = "name", type = "String") String name) {
        FileEntity file1 = new FileEntity().setName(name);
        FileEntity file2 = new FileEntity().setName(name + "1");
        FileEntity[] files = new FileEntity[]{file1,file2};

        return new ResponseResult(files);
    }
}
