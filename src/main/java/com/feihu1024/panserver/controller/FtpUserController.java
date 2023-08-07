package com.feihu1024.panserver.controller;

import com.feihu1024.panserver.entity.FtpUser;
import com.feihu1024.panserver.interrupt.ResponseResult;
import com.feihu1024.panserver.service.FtpUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "ftp用户服务")

@Controller
@CrossOrigin
@RequestMapping("/ftp-user")
public class FtpUserController {

    @Autowired
    FtpUserService ftpUserService;

    @ApiOperation(value = "获取所有用户(临时接口)", notes = "")
    @ResponseBody
    @GetMapping("/queryAllUsers")
    public ResponseResult<List<FtpUser>> getAllUsers() {

        List<FtpUser> ftpUserList = ftpUserService.list();
        return new ResponseResult(ftpUserList);
    }

    @ApiOperation(value = "修改用户(临时接口)", notes = "")
    @ResponseBody
    @PostMapping("/saveUser")
    public ResponseResult<Boolean> saveUser(FtpUser user) {

        Boolean success = ftpUserService.updateById(user);
        return new ResponseResult(success);
    }

    @ApiOperation(value = "新增用户(临时接口)", notes = "")
    @ResponseBody
    @PostMapping("/updateUser")
    public ResponseResult<Boolean> updateUser(FtpUser user) {
        Boolean success = ftpUserService.save(user);
        return new ResponseResult(success);
    }
}
