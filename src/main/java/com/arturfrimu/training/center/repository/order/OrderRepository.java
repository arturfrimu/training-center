package com.arturfrimu.training.center.repository.order;

import com.arturfrimu.training.center.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
