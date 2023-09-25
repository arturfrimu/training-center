package com.arturfrimu.training.center.streams.service.order;

import com.arturfrimu.training.center.streams.entity.order.Order;
import com.arturfrimu.training.center.streams.repository.order.OrderRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Optional<Order> findOrderById(@NonNull final Long id) {
        List<Order> allOrders = orderRepository.findAll();

        // TODO: 25.09.2023

        return Optional.empty();
    }

    public List<Order> findAllOrdersDidByCustomer(Long customerId) {
        List<Order> allOrders = orderRepository.findAll();

        // TODO: 25.09.2023

        return Collections.emptyList();
    }

    public BigDecimal calculateTotalAmountOfOrder(Long orderId) {
        List<Order> allOrders = orderRepository.findAll();

        // TODO: 25.09.2023

        return BigDecimal.ZERO;
    }

    public Map<Order.OrderStatus, List<Order>> groupOrdersByOrderStatus() {
        List<Order> allOrders = orderRepository.findAll();

        // TODO: 25.09.2023

        return Map.of();
    }

    public Map<Boolean, List<Order>> findAllOrdersThatHaveTotalAmountGreaterThan(BigDecimal totalAmount) {
        List<Order> allOrders = orderRepository.findAll();

        // TODO: 25.09.2023

        return Map.of();
    }
}
