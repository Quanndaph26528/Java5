package com.demo.repository;

import com.demo.model.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCartRepository extends JpaRepository<UserCart, Long> {
    List<UserCart> findByUserId(Long userId);

}

