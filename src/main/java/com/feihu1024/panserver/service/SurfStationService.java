package com.feihu1024.panserver.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feihu1024.panserver.entity.SurfStation;
import com.feihu1024.panserver.mapper.SurfStationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DS("base_gis")
public class SurfStationService extends ServiceImpl<SurfStationMapper, SurfStation> implements IService<SurfStation> {

    @Autowired
    SurfStationMapper surfStationMapper;

    /**
     * 按名称查询气象站
     *
     * @param name
     * @return
     */

    public SurfStation[] queryStationByName(String name) {
        return surfStationMapper.queryStationByName(name);
    }
}
