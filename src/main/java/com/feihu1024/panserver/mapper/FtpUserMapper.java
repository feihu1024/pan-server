package com.feihu1024.panserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feihu1024.panserver.entity.FtpUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface FtpUserMapper extends BaseMapper<FtpUser> {

    @Select("select * from pan.ftp_user where user_name = #{userName}")
    FtpUser selectByUserName(String userName);
}
