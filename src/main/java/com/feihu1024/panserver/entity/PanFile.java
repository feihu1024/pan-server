package com.feihu1024.panserver.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.net.ftp.FTPFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor // 创建无参的构造方法
@AllArgsConstructor // 创建满参的构造方法
@Accessors(chain = true)// 使用链式方法
@Data // 重写getter、setter、toString等方法
@ApiModel(value = "ftp文件详情信息")

public class PanFile implements Serializable {
    @ApiModelProperty(value = "文件名称")
    private String name;

    @ApiModelProperty(value = "文件类型（文件:0,目录:1）")
    private Integer type;

    @ApiModelProperty(value = "文件大小(字节)")
    private Long size;

    @ApiModelProperty(value = "最后修改日期")
    private Date lastModifyDate;

    public PanFile(FTPFile file) {
        this.name = file.getName();
        this.type = file.getType();
        this.lastModifyDate = file.getTimestamp().getTime();
        this.size = file.getSize();
    }

    public static PanFile convertToPanFile(FTPFile file) {
        return new PanFile(file);
    }

    public static PanFile[] convertToPanFileList(FTPFile[] fileList) {
        PanFile[] list = new PanFile[fileList.length];
        for (int i = 0; i <fileList.length ; i++) list[i] = new PanFile(fileList[i]);
        return list;
    }
}
