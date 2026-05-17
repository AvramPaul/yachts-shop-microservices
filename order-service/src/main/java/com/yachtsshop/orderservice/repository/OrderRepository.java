package com.yachtsshop.orderservice.repository;

import com.yachtsshop.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}