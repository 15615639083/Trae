package com.zhifeizi.controller;


import com.zhifeizi.entity.Order;
import com.zhifeizi.entity.User;
import com.zhifeizi.service.EngineerProfileService;
import com.zhifeizi.service.OrderService;
import com.zhifeizi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private EngineerProfileService engineerProfileService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    // 工程师审核
    @PutMapping("/engineer/verify")
    public Map<String, Object> verifyEngineer(@RequestBody Map<String, Object> params) {
        Long profileId = Long.valueOf(params.get("profileId").toString());
        String status = params.get("status").toString();

        boolean result = engineerProfileService.verifyEngineer(profileId, status);
        if (result) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", true);
            map.put("message", "审核成功");
            return map;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "审核失败");
            return map;
        }
    }

    // 获取待审核工程师列表
    @GetMapping("/engineer/pending")
    public Map<String, Object> getPendingEngineers() {
        // 这里需要在EngineerProfileService中添加获取待审核工程师的方法
        // 暂时返回空列表，后续实现
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("engineers", new ArrayList<>());
        return map;
    }

    // 获取所有用户列表
    @GetMapping("/users")
    public Map<String, Object> getUsers() {
        List<User> users = userService.listUsers();
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("users", users);
        return map;
    }

    // 获取所有工程师列表
    @GetMapping("/engineers")
    public Map<String, Object> getEngineers() {
        List<User> engineers = userService.getByRole("engineer");
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("engineers", engineers);
        return map;
    }

    // 人工派单
    @PutMapping("/order/assign")
    public Map<String, Object> assignOrder(@RequestBody Map<String, Object> params) {
        Long orderId = Long.valueOf(params.get("orderId").toString());
        Long engineerId = Long.valueOf(params.get("engineerId").toString());
        Long adminId = Long.valueOf(params.get("adminId").toString());

        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "订单不存在");
            return map;
        }

        order.setEngineerId(engineerId);
        order.setAdminId(adminId);
        order.setStatus("assigned");

        orderService.updateOrder(order);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "派单成功");
        map.put("order", order);
        return map;
    }

    // 获取订单池
    @GetMapping("/orders/pending")
    public Map<String, Object> getPendingOrders() {
        List<Order> orders = orderService.getByStatus("pending");
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("orders", orders);
        return map;
    }

    // 获取所有订单
    @GetMapping("/orders")
    public Map<String, Object> getAllOrders() {
        List<Order> orders = orderService.listOrders();
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("orders", orders);
        return map;
    }

    // 更新订单状态
    @PutMapping("/order/status")
    public Map<String, Object> updateOrderStatus(@RequestBody Map<String, Object> params) {
        Long orderId = Long.valueOf(params.get("orderId").toString());
        String status = params.get("status").toString();

        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "订单不存在");
            return map;
        }

        order.setStatus(status);
        orderService.updateOrder(order);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "状态更新成功");
        map.put("order", order);
        return map;
    }
}
