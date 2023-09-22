package com.arturfrimu.training.center.streams.repository.order;

import com.arturfrimu.training.center.streams.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
