package com.orjuelalaley.Pizzeria.service;

import com.orjuelalaley.Pizzeria.persistence.entity.OrderEntity;
import com.orjuelalaley.Pizzeria.persistence.projection.OrderSummary;
import com.orjuelalaley.Pizzeria.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class provides services related to orders.
 * @since 2023
 * @author orjuelalaley
 */

@Service
public class OrderService {
    /**
     * This attribute is the repository of the order entity
     * @since 2023
     * @see OrderRepository
     */
    private final OrderRepository orderRepository;

    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ON_SITE = "S";

    /**
     * Constructor of the OrderService class.
     * @param orderRepository The order repository to be injected via autowiring.
     */

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Retrieves all available orders in the pizzeria.
     * @return A list of OrderEntity objects representing all available orders.
     */

    public List<OrderEntity> getAll() {
        return this.orderRepository.findAll();
    }

    /**
     * Retrieves all orders that are planed after today
     * @return a list of OrderEntity
     */
    public List<OrderEntity> getAfterTodayOrders() {
        LocalDateTime today = LocalDate.now().atStartOfDay();
        return this.orderRepository.findAllByDateAfter(today);
    }

    /**
     * Retrieves all orders that are for delivery or carryout.
     * @return A list of OrderEntity objects representing all outside orders.
     */

    public List<OrderEntity> getOutsideOrders() {
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        return this.orderRepository.findAllByMethodIn(methods);
    }

    public List<OrderEntity> getCustomerOrders(String id_customer) {
        return this.orderRepository.findCustomerOrders(id_customer);
    }
    public OrderSummary getOrderSummary(Integer orderId) {
        return this.orderRepository.findSummary(orderId);
    }

}
