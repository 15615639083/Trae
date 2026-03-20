package com.zhifeizi.service.impl;

import com.zhifeizi.entity.Order;
import com.zhifeizi.mapper.OrderMapper;
import com.zhifeizi.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> listOrders() {
        return orderMapper.selectList(null);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    public boolean saveOrder(Order order) {
        return orderMapper.insert(order) > 0;
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderMapper.updateById(order) > 0;
    }

    @Override
    public boolean deleteOrder(Long id) {
        return orderMapper.deleteById(id) > 0;
    }

    @Override
    public List<Order> getByStatus(String status) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("status", status);
        return orderMapper.selectList(wrapper);
    }
}