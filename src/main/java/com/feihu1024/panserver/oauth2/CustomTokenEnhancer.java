package com.feihu1024.panserver.oauth2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feihu1024.panserver.ftpserver.CustomFtpPlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * token增强配置
 */

@Component
public class CustomTokenEnhancer implements TokenEnhancer {

    public static final Logger log = LoggerFactory.getLogger(CustomFtpPlet.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String,Object> map = new HashMap<>();
        Object principal = oAuth2Authentication.getPrincipal();
        try{
            String str = objectMapper.writeValueAsString(principal);
            Map authentication = objectMapper.readValue(str, Map.class);
            map.put("id",authentication.get("id"));
            map.put("home_directory",authentication.get("homeDirectory"));
            map.put("rule",authentication.get("rule"));
            ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(map);
        } catch (IOException e) {
            log.error("",e);
        }

        return oAuth2AccessToken;
    }
}
