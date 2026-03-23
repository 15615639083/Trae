package com.zhifeizi.controller;

import com.zhifeizi.entity.Transaction;
import com.zhifeizi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/finance")
public class FinanceController {

    @Autowired
    private TransactionService transactionService;

    // 发起支付
    @PostMapping("/pay")
    public Map<String, Object> createPayment(@RequestBody Transaction transaction) {
        // 设置交易类型为支付
        transaction.setType("pay");
        transaction.setStatus("pending");
        boolean result = transactionService.saveTransaction(transaction);
        if (result) {
            // 这里应该调用支付接口，暂时模拟支付成功
            transaction.setStatus("completed");
            transactionService.updateTransaction(transaction);
            Map<String, Object> map = new HashMap<>();
            map.put("success", true);
            map.put("message", "支付成功");
            map.put("transaction", transaction);
            return map;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "支付失败");
            return map;
        }
    }

    // 申请提现
    @PostMapping("/withdraw")
    public Map<String, Object> createWithdrawal(@RequestBody Transaction transaction) {
        // 设置交易类型为提现
        transaction.setType("withdraw");
        transaction.setStatus("pending");
        boolean result = transactionService.saveTransaction(transaction);
        if (result) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", true);
            map.put("message", "提现申请已提交，等待审核");
            map.put("transaction", transaction);
            return map;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "提现申请失败");
            return map;
        }
    }

    // 审核提现
    @PutMapping("/withdraw/verify")
    public Map<String, Object> verifyWithdrawal(@RequestBody Map<String, Object> params) {
        Long transactionId = Long.valueOf(params.get("transactionId").toString());
        String status = params.get("status").toString();

        Transaction transaction = transactionService.getTransactionById(transactionId);
        if (transaction == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "交易不存在");
            return map;
        }

        if (!"withdraw".equals(transaction.getType())) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "只有提现交易可以审核");
            return map;
        }

        transaction.setStatus(status);
        boolean result = transactionService.updateTransaction(transaction);
        if (result) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", true);
            map.put("message", "审核成功");
            map.put("transaction", transaction);
            return map;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "审核失败");
            return map;
        }
    }

    // 获取用户的交易记录
    @GetMapping("/transactions/{userId}")
    public Map<String, Object> getUserTransactions(@PathVariable Long userId) {
        List<Transaction> transactions = transactionService.getTransactionsByUserId(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("transactions", transactions);
        return map;
    }

    // 获取订单的交易记录
    @GetMapping("/transaction/order/{orderId}")
    public Map<String, Object> getOrderTransaction(@PathVariable Long orderId) {
        Transaction transaction = transactionService.getTransactionByOrderId(orderId);
        if (transaction == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "交易记录不存在");
            return map;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("transaction", transaction);
        return map;
    }
}
