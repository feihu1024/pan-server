package com.feihu1024.panserver.entity;

import com.baomidou.dynamic.datasource.annotation.DS;
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
@TableName("china_sufr_station")
@ApiModel(value = "地面气象站信息", description = "地面气象站信息")
public class SurfStation implements Serializable {
    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "区站号")
    private Integer code;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "站点名称")
    private String name;

    @ApiModelProperty(value = "站点类型")
    private String type;

    @ApiModelProperty(value = "站点经度")
    private Long longitude;

    @ApiModelProperty(value = "站点纬度")
    private Long latitude;

    @ApiModelProperty(value = "观测场拔海高度（米）")
    private Float stationElevation;

    @ApiModelProperty(value = "气压传感器拔海高度（米）")
    private Float sensorElevation;
}
