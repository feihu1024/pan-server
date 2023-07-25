package com.feihu1024.panserver.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor // 创建无参的构造方法
@AllArgsConstructor // 创建满参的构造方法
@Accessors(chain = true)// 使用链式方法
@Data // 重写getter、setter、toString等方法

@ApiModel(value = "文件对象", description = "文件对象描述信息")
public class FileEntity implements Serializable {
    @ApiModelProperty(value = "文件id")
    private Long id;

    @ApiModelProperty(value = "文件名称")
    private String name;

    @ApiModelProperty(value = "文件类型")
    private String type;

    @ApiModelProperty(value = "文件大小")
    private Long size;
}
