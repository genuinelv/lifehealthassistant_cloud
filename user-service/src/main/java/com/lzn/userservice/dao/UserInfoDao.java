package com.lzn.userservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.lzn.userservice.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserInfoDao extends BaseMapper<User> {

    @Update("create table ${id}_diet" +
            "(" +
            "datetime datetime not null," +
            "food VARCHAR(255)," +
            "dietname VARCHAR(16)," +
            "picture1 VARCHAR(255)," +
            "picture2 VARCHAR(255)," +
            "picture3 VARCHAR(255)," +
            "PRIMARY KEY(datetime,dietname)" +
            ");")
    public boolean save_create_diet(User user);

    @Update("create table ${id}_disease" +
            "(" +
            "datestart datetime not null," +
            "dateend datetime not null," +
            "diseasename VARCHAR(255) not null," +
            "symptom VARCHAR(255)," +
            "sympic VARCHAR(255)," +
            "medicine VARCHAR(255)," +
            "medpic VARCHAR(255)," +
            "PRIMARY KEY(datestart,dateend,diseasename)" +
            ");")
    public boolean save_create_disease(User user);

    @Update("create table ${id}_health" +
            "(" +
            "datetimehealth datetime not null," +
            "age int," +
            "height double," +
            "weight double," +
            "bloodpressurehigh int," +
            "bloodpressurelow int," +
            "bloodsugar double," +
            "PRIMARY KEY(datetimehealth)" +
            ");")
    public boolean save_create_health(User user);


    @Delete("drop table ${id}_diet cascade;")
    public boolean delete_diet(String id);

    @Delete("drop table ${id}_disease cascade;")
    public boolean delete_disease(String id);
    @Delete("drop table ${id}_health cascade;")
    public boolean delete_health(String id);

    @Update("UPDATE user SET name=#{name},sex=#{sex},photo=#{photo} WHERE id=#{id}")
    Boolean updateByIdWithoutPs(User user);
    @Update("UPDATE user SET password=#{password} WHERE id=#{id}")
    Boolean updateByIdPs(User user);
}
