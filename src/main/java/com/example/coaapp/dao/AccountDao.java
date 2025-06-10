package com.example.coaapp.dao;

import com.example.coaapp.model.Account;
import java.util.List;
import java.util.Optional;

public interface AccountDao {
    void save(Account account);
    Optional<Account> findById(Long id);
    List<Account> findAll();
    void update(Account account);
    void delete(Account account);
}