package com.zhifeizi.controller;

import com.zhifeizi.entity.Order;
import com.zhifeizi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public Map<String, Object> listOrders() {
        List<Order> orders = orderService.listOrders();
        return Map.of("success", true, "orders", orders);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            return Map.of("success", false, "message", "订单不存在");
        }
        return Map.of("success", true, "order", order);
    }

    @PostMapping
    public Map<String, Object> saveOrder(@RequestBody Order order) {
        // 设置初始状态为待派单
        order.setStatus("pending");
        boolean result = orderService.saveOrder(order);
        if (result) {
            return Map.of("success", true, "message", "订单创建成功", "order", order);
        } else {
            return Map.of("success", false, "message", "订单创建失败");
        }
    }

    @PutMapping
    public Map<String, Object> updateOrder(@RequestBody Order order) {
        boolean result = orderService.updateOrder(order);
        if (result) {
            return Map.of("success", true, "message", "订单更新成功", "order", order);
        } else {
            return Map.of("success", false, "message", "订单更新失败");
        }
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteOrder(@PathVariable Long id) {
        boolean result = orderService.deleteOrder(id);
        if (result) {
            return Map.of("success", true, "message", "订单删除成功");
        } else {
            return Map.of("success", false, "message", "订单删除失败");
        }
    }

    // 工程师接单
    @PutMapping("/accept")
    public Map<String, Object> acceptOrder(@RequestBody Map<String, Object> params) {
        Long orderId = Long.valueOf(params.get("orderId").toString());

        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return Map.of("success", false, "message", "订单不存在");
        }

        if (!"assigned".equals(order.getStatus())) {
            return Map.of("success", false, "message", "只有已派单的订单可以接单");
        }

        order.setStatus("processing");
        boolean result = orderService.updateOrder(order);
        if (result) {
            return Map.of("success", true, "message", "接单成功", "order", order);
        } else {
            return Map.of("success", false, "message", "接单失败");
        }
    }

    // 工程师完成服务
    @PutMapping("/complete")
    public Map<String, Object> completeOrder(@RequestBody Map<String, Object> params) {
        Long orderId = Long.valueOf(params.get("orderId").toString());

        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return Map.of("success", false, "message", "订单不存在");
        }

        if (!"processing".equals(order.getStatus())) {
            return Map.of("success", false, "message", "只有处理中的订单可以完成");
        }

        order.setStatus("completed");
        boolean result = orderService.updateOrder(order);
        if (result) {
            return Map.of("success", true, "message", "服务完成", "order", order);
        } else {
            return Map.of("success", false, "message", "服务完成失败");
        }
    }

    // 用户确认验收
    @PutMapping("/verify")
    public Map<String, Object> verifyOrder(@RequestBody Map<String, Object> params) {
        Long orderId = Long.valueOf(params.get("orderId").toString());

        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return Map.of("success", false, "message", "订单不存在");
        }

        if (!"completed".equals(order.getStatus())) {
            return Map.of("success", false, "message", "只有已完成的订单可以验收");
        }

        order.setStatus("verified");
        boolean result = orderService.updateOrder(order);
        if (result) {
            return Map.of("success", true, "message", "验收成功", "order", order);
        } else {
            return Map.of("success", false, "message", "验收失败");
        }
    }

    // 用户取消订单
    @PutMapping("/cancel")
    public Map<String, Object> cancelOrder(@RequestBody Map<String, Object> params) {
        Long orderId = Long.valueOf(params.get("orderId").toString());

        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return Map.of("success", false, "message", "订单不存在");
        }

        if (!"pending".equals(order.getStatus()) && !"assigned".equals(order.getStatus())) {
            return Map.of("success", false, "message", "只有待派单或已派单的订单可以取消");
        }

        order.setStatus("cancelled");
        boolean result = orderService.updateOrder(order);
        if (result) {
            return Map.of("success", true, "message", "订单取消成功", "order", order);
        } else {
            return Map.of("success", false, "message", "订单取消失败");
        }
    }

    // 获取用户的订单列表
    @GetMapping("/user/{userId}")
    public Map<String, Object> getUserOrders(@PathVariable Long userId) {
        // 这里需要在OrderService中添加根据用户ID获取订单的方法
        // 暂时返回所有订单，后续实现
        List<Order> orders = orderService.listOrders();
        return Map.of("success", true, "orders", orders);
    }

    // 获取工程师的订单列表
    @GetMapping("/engineer/{engineerId}")
    public Map<String, Object> getEngineerOrders(@PathVariable Long engineerId) {
        // 这里需要在OrderService中添加根据工程师ID获取订单的方法
        // 暂时返回所有订单，后续实现
        List<Order> orders = orderService.listOrders();
        return Map.of("success", true, "orders", orders);
    }
}
