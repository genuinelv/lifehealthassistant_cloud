package com.lzn.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.lzn.domain.Useremail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserEmailDao extends BaseMapper<Useremail> {
}
