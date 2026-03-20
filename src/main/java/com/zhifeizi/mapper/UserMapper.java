package com.zhifeizi.mapper;

import com.zhifeizi.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE role = #{role}")
    List<User> selectByRole(String role);
}