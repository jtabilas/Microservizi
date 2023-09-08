package com.example.orderserver.repository;

import com.example.orderserver.model.OrderServer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderServer, Integer> {
}
