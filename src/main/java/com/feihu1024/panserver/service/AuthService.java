package com.feihu1024.panserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feihu1024.panserver.entity.AuthUser;
import com.feihu1024.panserver.mapper.FtpUserMapper;
import com.feihu1024.panserver.entity.FtpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AuthService extends ServiceImpl<FtpUserMapper, FtpUser> implements IService<FtpUser> , UserDetailsService {

    @Autowired
    FtpUserMapper ftpUserMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        FtpUser user = ftpUserMapper.selectByUserName(userName);

        // 判断用户是否存在
        if(user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 添加权限
        authorities.add(new SimpleGrantedAuthority(user.getRule()));

        AuthUser authUser = new AuthUser(user.getUserName(),user.getPassword(),authorities);
        authUser.setId(user.getId());
        authUser.setHomeDirectory(user.getHomeDirectory());
        authUser.setRule(user.getRule());

        return authUser;
    }
}
