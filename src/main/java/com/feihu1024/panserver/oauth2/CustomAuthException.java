package com.feihu1024.panserver.oauth2;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自定义oauth2异常
 */

@JsonSerialize(using = CustomAuthExceptionJackson2Serializer.class)

public class CustomAuthException extends OAuth2Exception {

    public CustomAuthException(String msg, Throwable t) {
        super(msg, t);
    }

    public CustomAuthException(String msg) {
        super(msg);
    }
}
