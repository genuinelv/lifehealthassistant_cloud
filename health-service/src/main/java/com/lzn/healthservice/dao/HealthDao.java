package com.lzn.healthservice.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.lzn.healthservice.domain.Health;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HealthDao extends BaseMapper<Health> {

    @Select("SELECT * FROM ${id}_health")
    List<Health> selectAll(String id);

    @Insert("INSERT INTO ${id}_health(datetimehealth, age, height, weight,bloodpressurehigh,bloodpressurelow,bloodsugar) " +
            "VALUES(#{health.datetimehealth}, #{health.age}, #{health.height},#{health.weight}," +
            "#{health.bloodpressurehigh},#{health.bloodpressurelow},#{health.bloodsugar})")
    int insertHealth(Health health,String id);

    @Update("UPDATE ${id}_health SET age=#{health.age},height=#{health.height},weight=#{health.weight},bloodpressurehigh=#{health.bloodpressurehigh}" +
            ",bloodpressurelow=#{health.bloodpressurelow},bloodsugar=#{health.bloodsugar} WHERE datetimehealth=#{health.datetimehealth}")
    int updateHealth(Health health,String id);

    @Delete("DELETE FROM ${id}_health WHERE datetimehealth=#{health.datetimehealth}")
    int deleteHealth(Health health,String id);
}
