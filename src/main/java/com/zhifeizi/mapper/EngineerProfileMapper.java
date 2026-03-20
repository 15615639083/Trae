package com.zhifeizi.mapper;

import com.zhifeizi.entity.EngineerProfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EngineerProfileMapper {
    int insert(EngineerProfile engineerProfile);
    int update(EngineerProfile engineerProfile);
    @Select("SELECT * FROM engineer_profile WHERE user_id = #{userId}")
    EngineerProfile selectByUserId(Long userId);
    @Select("SELECT * FROM engineer_profile WHERE id = #{id}")
    EngineerProfile selectById(Long id);
}