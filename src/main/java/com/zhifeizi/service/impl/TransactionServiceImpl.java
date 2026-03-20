package com.zhifeizi.service.impl;

import com.zhifeizi.entity.Transaction;
import com.zhifeizi.mapper.TransactionMapper;
import com.zhifeizi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public boolean saveTransaction(Transaction transaction) {
        return transactionMapper.insert(transaction) > 0;
    }

    @Override
    public boolean updateTransaction(Transaction transaction) {
        return transactionMapper.update(transaction) > 0;
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return transactionMapper.selectById(id);
    }

    @Override
    public List<Transaction> getTransactionsByUserId(Long userId) {
        return transactionMapper.selectByUserId(userId);
    }

    @Override
    public Transaction getTransactionByOrderId(Long orderId) {
        return transactionMapper.selectByOrderId(orderId);
    }
}
