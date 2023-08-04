package com.feihu1024.panserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feihu1024.panserver.dao.FtpUserMapper;
import com.feihu1024.panserver.entity.FtpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FtpUserService extends ServiceImpl<FtpUserMapper, FtpUser> implements IService<FtpUser> {

    @Autowired
    FtpUserMapper ftpUserMapper;
}
