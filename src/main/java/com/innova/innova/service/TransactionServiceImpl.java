package com.innova.innova.service;


import com.innova.innova.dao.TransactionRepository;
import com.innova.innova.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Getter
@Setter
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        try {
            return repository.save(transaction);
        } catch (Exception e) {
            throw new RuntimeException("Error creating transaction", e);
        }
    }

    @Override
    public List<Transaction> getAllTransaction(Long id) {
        try {
            return repository.getAllTransactionByPersonId(id);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving transactions", e);
        }
    }
    @Override
    public List<Transaction> getAllTransactionByUsername(String username) {
        return repository.findByPersonUsername(username);
    }
    @Override
    public double getDailyTransaction(Long id) {
        try {
            return repository.dailyTransaction(id, LocalDate.now())
                    .stream()
                    .map(Transaction::getAmount)
                    .reduce(0.0, Double::sum);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving daily transactions", e);
        }
    }

    @Override
    public Double getWeeklyTransaction(Long id) {
        try {
            return repository.weeklyTransaction(id)
                    .stream()
                    .map(Transaction::getAmount)
                    .reduce(0.0, Double::sum);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving weekly transactions", e);
        }
    }

    @Override
    public Double getMonthlyTransaction(Long id) {
        try {
            return repository.monthlyTransaction(id)
                    .stream()
                    .map(Transaction::getAmount)
                    .reduce(0.0, Double::sum);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving monthly transactions", e);
        }
    }
}
