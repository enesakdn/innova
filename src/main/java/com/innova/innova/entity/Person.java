package com.innova.innova.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="person" ,schema = "innovatask")
public class Person implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Column(unique = true)
    private String username;
    private String password;
    private double dailyTransaction;
    private double weeklyTransaction;

    private double monthlyTransaction;

 @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Transaction> transactions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "person_role", schema = "innovatask",
            joinColumns = {@JoinColumn(name = "person_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> authorities = new HashSet<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getDailyTransaction() {
        return dailyTransaction;
    }

    public void setDailyTransaction(double dailyTransaction) {
        this.dailyTransaction = dailyTransaction;
    }

    public double getWeeklyTransaction() {
        return weeklyTransaction;
    }

    public void setWeeklyTransaction(double weeklyTransaction) {
        this.weeklyTransaction = weeklyTransaction;
    }

    public double getMonthlyTransaction() {
        return monthlyTransaction;
    }

    public void setMonthlyTransaction(double monthlyTransaction) {
        this.monthlyTransaction = monthlyTransaction;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }
}
