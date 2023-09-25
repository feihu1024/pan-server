package com.feihu1024.panserver.entity.file;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
@ApiModel(value = "文件上传参数")
public class UploadFile implements Serializable {

    @ApiModelProperty(value = "文件完整路径",required = true)
    private String path;

    @ApiModelProperty(value = "文件对象",required = true)
    private MultipartFile file;
}
