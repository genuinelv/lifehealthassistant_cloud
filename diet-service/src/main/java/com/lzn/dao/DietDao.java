package com.lzn.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.lzn.domain.Diet;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DietDao extends BaseMapper<Diet> {


    @Select("SELECT * FROM ${id}_diet")
    List<Diet> selectAll(String id);


    @Select("SELECT * FROM ${id}_diet WHERE food like '%${value}%'")
    List<Diet> selectByContent(String id, String value);

    @Select("SELECT * FROM ${id}_diet WHERE TO_DAYS(datetime) = TO_DAYS(#{date}) AND dietname=#{adietname}")
    List<Diet> selectPrimary(String date, String id, String adietname);

    @Insert("INSERT INTO ${id}_diet(datetime, food, dietname,picture1,picture2,picture3) " +
            "VALUES(#{diet.datetime}, #{diet.food}, #{diet.dietname},#{diet.picture1}," +
            "#{diet.picture2},#{diet.picture3})")
    int insertDiet(Diet diet,String id);

    @Delete("DELETE FROM ${id}_diet WHERE TO_DAYS(datetime) = TO_DAYS(#{diet.datetime}) AND dietname=#{diet.dietname}")
    int deleteDiet(Diet diet, String id);

}
