package com.innova.innova.controller;


import com.innova.innova.entity.Person;
import com.innova.innova.entity.Transaction;
import com.innova.innova.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        try {
            Transaction createdTransaction = transactionService.createTransaction(transaction);
            return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/person/{personId}")
    public ResponseEntity<List<Transaction>> getAllTransactionsByPersonId(@PathVariable("personId") Long personId) {
        try {
            List<Transaction> transactions = transactionService.getAllTransaction(personId);
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/person/{personId}/daily")
    public ResponseEntity<Double> getDailyTransactions(@PathVariable("personId") Long personId) {
        try {
            double dailyTotal = transactionService.getDailyTransaction(personId);
            return new ResponseEntity<>(dailyTotal, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/person/{personId}/weekly")
    public ResponseEntity<Double> getWeeklyTransactions(@PathVariable("personId") Long personId) {
        try {
            Double weeklyTotal = transactionService.getWeeklyTransaction(personId);
            return new ResponseEntity<>(weeklyTotal, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/person/{personId}/monthly")
    public ResponseEntity<Double> getMonthlyTransactions(@PathVariable("personId") Long personId) {
        try {
            Double monthlyTotal = transactionService.getMonthlyTransaction(personId);
            return new ResponseEntity<>(monthlyTotal, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/me")
    public ResponseEntity<List<Transaction>> getAllTransactionsByPersonUsername(@AuthenticationPrincipal Person person) {
        List<Transaction> transactions = transactionService.getAllTransactionByUsername(person.getUsername());
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

}
