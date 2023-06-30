package com.demo.repository;

import com.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Các phương thức truy vấn cụ thể có thể được thêm vào đây nếu cần
}
