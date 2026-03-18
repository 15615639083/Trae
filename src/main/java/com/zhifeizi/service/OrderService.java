package com.zhifeizi.service;

import com.zhifeizi.entity.Order;
import java.util.List;

public interface OrderService {
    List<Order> listOrders();
    Order getOrderById(Long id);
    boolean saveOrder(Order order);
    boolean updateOrder(Order order);
    boolean deleteOrder(Long id);
}