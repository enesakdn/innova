package com.innova.innova.dao;


import com.innova.innova.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT p FROM Person p WHERE p.username = :username")
    Optional<Person> findPersonByUsername(String username);
}
