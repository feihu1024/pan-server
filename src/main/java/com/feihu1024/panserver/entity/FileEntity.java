package com.feihu1024.panserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value = "ftp文件详情信息")

public class FileEntity  implements Serializable {
    @ApiModelProperty(value = "主键id")
    private Long id;
}
