package com.zhifeizi.service.impl;

import com.zhifeizi.entity.Order;
import com.zhifeizi.mapper.OrderMapper;
import com.zhifeizi.service.OrderService;
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
}