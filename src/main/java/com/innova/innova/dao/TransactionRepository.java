package com.innova.innova.dao;


import com.innova.innova.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.person.id = :id AND t.date = :date")
    List<Transaction> dailyTransaction(@Param("id") Long id, @Param("date") LocalDate date);

    @Query(value = "SELECT * FROM transaction WHERE person_id = :id AND EXTRACT(YEAR FROM date) = EXTRACT(YEAR FROM CURRENT_DATE) AND EXTRACT(WEEK FROM date) = EXTRACT(WEEK FROM CURRENT_DATE)", nativeQuery = true)
    List<Transaction> weeklyTransaction(@Param("id") Long id);

    @Query(value = "SELECT * FROM transaction WHERE person_id = :id AND EXTRACT(YEAR FROM date) = EXTRACT(YEAR FROM CURRENT_DATE) AND EXTRACT(MONTH FROM date) = EXTRACT(MONTH FROM CURRENT_DATE)", nativeQuery = true)
    List<Transaction> monthlyTransaction(@Param("id") Long id);

    @Query("SELECT t FROM Transaction t WHERE t.person.id = :id")
    List<Transaction> getAllTransactionByPersonId(Long id);

    List<Transaction> findByPersonUsername(String username);
}