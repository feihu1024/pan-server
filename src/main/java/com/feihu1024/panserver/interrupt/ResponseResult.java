package com.feihu1024.panserver.interrupt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data

@ApiModel(value = "通用响应体")

public class ResponseResult<T> {
    @ApiModelProperty(value = "是否成功")
    private Boolean success = true;
    @ApiModelProperty(value = "状态码")
    private Integer code = HttpStatus.OK.value();
    @ApiModelProperty(value = "提示信息")
    private String msg = HttpStatus.OK.name();
    //数据
    @ApiModelProperty(value = "响应数据")
    private T data;

    public static Class getType() {
        return Object.class;
    }

    public ResponseResult(T data) {
        this();
        this.setData(data);
    }

    public ResponseResult(String msg, T data) {
        this();
        this.setMsg(msg);
        this.setData(data);
    }

    //自定义异常返回的结果
    public static ResponseResult defineError(CustomException de) {
        ResponseResult result = new ResponseResult();
        result.setSuccess(false);
        result.setCode(de.getErrorCode().value());
        result.setMsg(de.getErrorMsg());
        result.setData(de.getData());
        return result;
    }
}
