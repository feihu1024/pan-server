package com.feihu1024.panserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName(schema = "pan",value = "ftp_user")
@ApiModel(value = "ftp用户信息")

public class FtpUser implements Serializable {

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "主目录")
    private String homeDirectory;

    @ApiModelProperty(value = "账户是否启用")
    private Boolean enableFlag;

    @ApiModelProperty(value = "是否具有写入权限")
    private Boolean writePermission;

    @ApiModelProperty(value = "空闲时间间隔(秒)")
    private Integer idleTime;

    @ApiModelProperty(value = "上传速率限制(字节每秒)")
    private Integer uploadRate;

    @ApiModelProperty(value = "下载速率限制(字节每秒)")
    private Integer downloadRate;

    @ApiModelProperty(value = "最大登陆用户数")
    private Integer maxLoginNumber;

    @ApiModelProperty(value = "最大同IP登陆用户数")
    private Integer maxLoginPerip;
}
