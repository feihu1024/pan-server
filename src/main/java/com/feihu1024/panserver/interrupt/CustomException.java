package com.feihu1024.panserver.interrupt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Accessors(chain = true)
@Data

public class CustomException extends RuntimeException {


    protected HttpStatus errorCode;
    protected String errorMsg;
    protected Object data;

    public CustomException(HttpStatus errorCode, String errorMsg) {
        this.setErrorCode(errorCode);
        this.setErrorMsg(errorMsg);
    }
}
