package com.feihu1024.panserver.controller;


import com.feihu1024.panserver.entity.SurfStation;
import com.feihu1024.panserver.interrupt.ResponseResult;
import com.feihu1024.panserver.service.SurfStationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "地面气象站服务")

@Controller
@CrossOrigin
@RequestMapping("/pan-server/surfStation")
@PreAuthorize("hasAnyAuthority('admin','normal')")
public class SurfStationController {

    @Autowired
    SurfStationService surfStationService;

    @ApiOperation(value = "查询气象站", notes = "按名称查询气象站")
    @ResponseBody
    @GetMapping("/queryStationByName")
    public ResponseResult<SurfStation[]> getFiles(@ApiParam(value = "气象站名称", name = "name", type = "String" ,required = true) String name) {

        SurfStation[] stations = surfStationService.queryStationByName(name);
        return new ResponseResult(stations);
    }
}
