package com.zhifeizi.service;

import com.zhifeizi.entity.Transaction;

import java.util.List;

public interface TransactionService {
    boolean saveTransaction(Transaction transaction);
    boolean updateTransaction(Transaction transaction);
    Transaction getTransactionById(Long id);
    List<Transaction> getTransactionsByUserId(Long userId);
    Transaction getTransactionByOrderId(Long orderId);
}
