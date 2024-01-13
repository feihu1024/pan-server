package com.feihu1024.panserver.controller;

import com.feihu1024.panserver.common.Util;
import com.feihu1024.panserver.entity.file.PanFile;
import com.feihu1024.panserver.entity.file.UploadFile;
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
    public ResponseResult<List<PanFile[]>> listByPath(@ApiParam(value = "文件路径") String path, HttpServletRequest request) {

        ResponseResult result = null;
        try {
            PanFile[] files = fileService.listByPath(Util.getUserInfo(request).getLong("id"), path);
            result = new ResponseResult(files);
        } catch (Exception e) {
            result = new ResponseResult(false, 500, "获取列表失败，请检查文件路径!", null);
        }
        return result;
    }

    @ApiOperation(value = "上传文件到指定目录", notes = "")
    @ResponseBody
    @PostMapping("/upload")
    public ResponseResult uploadByPath(UploadFile file, HttpServletRequest request) {

        ResponseResult result = null;
        try {
            fileService.uploadByPath(Util.getUserInfo(request).getLong("id"),file);
            result = new ResponseResult(file);
        } catch (Exception e) {
            result = new ResponseResult(false, 500, "上传文件失败: "+e.getMessage(), null);
        }
        return result;
    }
}
