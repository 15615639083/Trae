package com.zhifeizi.controller;

import com.zhifeizi.entity.Order;
import com.zhifeizi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public Map<String, Object> listOrders() {
        List<Order> orders = orderService.listOrders();
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("orders", orders);
        return map;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "订单不存在");
            return map;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("order", order);
        return map;
    }

    @PostMapping
    public Map<String, Object> saveOrder(@RequestBody Order order) {
        // 设置初始状态为待派单
        order.setStatus("pending");
        boolean result = orderService.saveOrder(order);
        if (result) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", true);
            map.put("message", "订单创建成功");
            map.put("order", order);
            return map;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "订单创建失败");
            return map;
        }
    }

    @PutMapping
    public Map<String, Object> updateOrder(@RequestBody Order order) {
        boolean result = orderService.updateOrder(order);
        if (result) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", true);
            map.put("message", "订单更新成功");
            map.put("order", order);
            return map;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "订单更新失败");
            return map;
        }
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteOrder(@PathVariable Long id) {
        boolean result = orderService.deleteOrder(id);
        if (result) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", true);
            map.put("message", "订单删除成功");
            return map;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "订单删除失败");
            return map;
        }
    }

    // 工程师接单
    @PutMapping("/accept")
    public Map<String, Object> acceptOrder(@RequestBody Map<String, Object> params) {
        Long orderId = Long.valueOf(params.get("orderId").toString());

        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "订单不存在");
            return map;
        }

        if (!"assigned".equals(order.getStatus())) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "只有已派单的订单可以接单");
            return map;
        }

        order.setStatus("processing");
        boolean result = orderService.updateOrder(order);
        if (result) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", true);
            map.put("message", "接单成功");
            map.put("order", order);
            return map;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "接单失败");
            return map;
        }
    }

    // 工程师完成服务
    @PutMapping("/complete")
    public Map<String, Object> completeOrder(@RequestBody Map<String, Object> params) {
        Long orderId = Long.valueOf(params.get("orderId").toString());

        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "订单不存在");
            return map;
        }

        if (!"processing".equals(order.getStatus())) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "只有处理中的订单可以完成");
            return map;
        }

        order.setStatus("completed");
        boolean result = orderService.updateOrder(order);
        if (result) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", true);
            map.put("message", "服务完成");
            map.put("order", order);
            return map;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "服务完成失败");
            return map;
        }
    }

    // 用户确认验收
    @PutMapping("/verify")
    public Map<String, Object> verifyOrder(@RequestBody Map<String, Object> params) {
        Long orderId = Long.valueOf(params.get("orderId").toString());

        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "订单不存在");
            return map;
        }

        if (!"completed".equals(order.getStatus())) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "只有已完成的订单可以验收");
            return map;
        }

        order.setStatus("verified");
        boolean result = orderService.updateOrder(order);
        if (result) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", true);
            map.put("message", "验收成功");
            map.put("order", order);
            return map;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "验收失败");
            return map;
        }
    }

    // 用户取消订单
    @PutMapping("/cancel")
    public Map<String, Object> cancelOrder(@RequestBody Map<String, Object> params) {
        Long orderId = Long.valueOf(params.get("orderId").toString());

        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "订单不存在");
            return map;
        }

        if (!"pending".equals(order.getStatus()) && !"assigned".equals(order.getStatus())) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "只有待派单或已派单的订单可以取消");
            return map;
        }

        order.setStatus("cancelled");
        boolean result = orderService.updateOrder(order);
        if (result) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", true);
            map.put("message", "订单取消成功");
            map.put("order", order);
            return map;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "订单取消失败");
            return map;
        }
    }

    // 获取用户的订单列表
    @GetMapping("/user/{userId}")
    public Map<String, Object> getUserOrders(@PathVariable Long userId) {
        // 这里需要在OrderService中添加根据用户ID获取订单的方法
        // 暂时返回所有订单，后续实现
        List<Order> orders = orderService.listOrders();
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("orders", orders);
        return map;
    }

    // 获取工程师的订单列表
    @GetMapping("/engineer/{engineerId}")
    public Map<String, Object> getEngineerOrders(@PathVariable Long engineerId) {
        // 这里需要在OrderService中添加根据工程师ID获取订单的方法
        // 暂时返回所有订单，后续实现
        List<Order> orders = orderService.listOrders();
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("orders", orders);
        return map;
    }
}
