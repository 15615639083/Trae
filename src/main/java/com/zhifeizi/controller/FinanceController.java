package com.zhifeizi.controller;

import com.zhifeizi.entity.Transaction;
import com.zhifeizi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
            return Map.of("success", true, "message", "支付成功", "transaction", transaction);
        } else {
            return Map.of("success", false, "message", "支付失败");
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
            return Map.of("success", true, "message", "提现申请已提交，等待审核", "transaction", transaction);
        } else {
            return Map.of("success", false, "message", "提现申请失败");
        }
    }

    // 审核提现
    @PutMapping("/withdraw/verify")
    public Map<String, Object> verifyWithdrawal(@RequestBody Map<String, Object> params) {
        Long transactionId = Long.valueOf(params.get("transactionId").toString());
        String status = params.get("status").toString();

        Transaction transaction = transactionService.getTransactionById(transactionId);
        if (transaction == null) {
            return Map.of("success", false, "message", "交易不存在");
        }

        if (!"withdraw".equals(transaction.getType())) {
            return Map.of("success", false, "message", "只有提现交易可以审核");
        }

        transaction.setStatus(status);
        boolean result = transactionService.updateTransaction(transaction);
        if (result) {
            return Map.of("success", true, "message", "审核成功", "transaction", transaction);
        } else {
            return Map.of("success", false, "message", "审核失败");
        }
    }

    // 获取用户的交易记录
    @GetMapping("/transactions/{userId}")
    public Map<String, Object> getUserTransactions(@PathVariable Long userId) {
        List<Transaction> transactions = transactionService.getTransactionsByUserId(userId);
        return Map.of("success", true, "transactions", transactions);
    }

    // 获取订单的交易记录
    @GetMapping("/transaction/order/{orderId}")
    public Map<String, Object> getOrderTransaction(@PathVariable Long orderId) {
        Transaction transaction = transactionService.getTransactionByOrderId(orderId);
        if (transaction == null) {
            return Map.of("success", false, "message", "交易记录不存在");
        }
        return Map.of("success", true, "transaction", transaction);
    }
}
