package com.feihu1024.panserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feihu1024.panserver.entity.SurfStation;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SurfStationMapper extends BaseMapper<SurfStation> {

    // 按名称模糊匹配
    @Select("select * from public.china_sufr_station where name like concat('%',#{name},'%');")
    SurfStation[] queryStationByName(String name);
}
