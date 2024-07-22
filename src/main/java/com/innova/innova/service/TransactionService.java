package com.innova.innova.service;



import com.innova.innova.entity.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction createTransaction(Transaction transaction);
    List<Transaction> getAllTransaction(Long id);

    public List<Transaction> getAllTransactionByUsername(String username);

    double getDailyTransaction(Long id);
    Double getWeeklyTransaction(Long id);
    Double getMonthlyTransaction(Long id);

}
