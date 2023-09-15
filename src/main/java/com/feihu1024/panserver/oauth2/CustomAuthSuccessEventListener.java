package com.feihu1024.panserver.oauth2;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * 认证成功事件监听器
 */
@Component
public class CustomAuthSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        if(!event.getSource().getClass().getName().equals("org.springframework.security.authentication.UsernamePasswordAuthenticationToken")){
            return ;
        }

        if(event.getAuthentication().getDetails() != null){

        }
    }
}
