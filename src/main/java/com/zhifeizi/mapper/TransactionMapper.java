package com.zhifeizi.mapper;

import com.zhifeizi.entity.Transaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TransactionMapper {
    int insert(Transaction transaction);
    int update(Transaction transaction);
    @Select("SELECT * FROM transaction WHERE id = #{id}")
    Transaction selectById(Long id);
    @Select("SELECT * FROM transaction WHERE user_id = #{userId} ORDER BY created_at DESC")
    List<Transaction> selectByUserId(Long userId);
    @Select("SELECT * FROM transaction WHERE order_id = #{orderId}")
    Transaction selectByOrderId(Long orderId);
}