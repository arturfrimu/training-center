package com.arturfrimu.training.center.service.order;

import com.arturfrimu.training.center.entity.order.Order;
import com.arturfrimu.training.center.entity.product.Product;
import com.arturfrimu.training.center.exception.ResourceNotFoundException;
import com.arturfrimu.training.center.repository.order.OrderRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Optional<Order> findOrderById(@NonNull final Long id) {
        List<Order> allOrders = orderRepository.findAll();

        return allOrders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();
    }

    public List<Order> findAllOrdersDidByCustomer(Long customerId) {
        List<Order> allOrders = orderRepository.findAll();

        return allOrders.stream()
                .filter(order -> order.getCustomer().getId().equals(customerId))
                .toList();
    }

    public BigDecimal calculateTotalAmountOfOrder(Long orderId) {
        List<Order> allOrders = orderRepository.findAll();

        Order order = allOrders.stream()
                .filter(currentOrder -> currentOrder.getCustomer().getId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: %s".formatted(orderId)));

        return order.getProducts().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<Order.OrderStatus, List<Order>> groupOrdersByOrderStatus() {
        List<Order> allOrders = orderRepository.findAll();

        Map<Order.OrderStatus, List<Order>> orderStatusToOrders = allOrders.stream()
                .collect(Collectors.groupingBy(Order::getOrderStatus));

        Arrays.asList(Order.OrderStatus.values())
                .forEach(status -> orderStatusToOrders.putIfAbsent(status, emptyList()));

        return orderStatusToOrders;
    }

    public Map<Boolean, List<Order>> findAllOrdersThatHaveTotalAmountGreaterThan(BigDecimal totalAmount) {
        List<Order> allOrders = orderRepository.findAll();

        Predicate<Order> orderPredicate = order -> order.getProducts()
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add).compareTo(totalAmount) > 0;

        return allOrders.stream().collect(Collectors.partitioningBy(orderPredicate));
    }
}
