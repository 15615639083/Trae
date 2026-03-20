package com.zhifeizi.mapper;

import com.zhifeizi.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper {
    int insert(Message message);
    @Select("SELECT * FROM message WHERE order_id = #{orderId} ORDER BY timestamp ASC")
    List<Message> selectByOrderId(Long orderId);
}