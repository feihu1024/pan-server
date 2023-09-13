package com.feihu1024.panserver.oauth2;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自定义oauth2异常
 */

@JsonSerialize(using = CustomOauthExceptionJackson2Serializer.class)

public class CustomOAuth2Exception extends OAuth2Exception {

    public CustomOAuth2Exception(String msg, Throwable t) {
        super(msg, t);
    }

    public CustomOAuth2Exception(String msg) {
        super(msg);
    }
}
