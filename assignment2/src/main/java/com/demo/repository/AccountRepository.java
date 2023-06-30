package com.demo.repository;

import com.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    // Add custom query methods if needed
    Account findByUsername(String username);
}

