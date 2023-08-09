package com.orjuelalaley.Pizzeria.service;

import com.orjuelalaley.Pizzeria.persistence.entity.OrderEntity;
import com.orjuelalaley.Pizzeria.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        List<OrderEntity> orders = orderRepository.findAll();
        orders.forEach(o -> System.out.println(o.getCustomer().getName()));
        return this.orderRepository.findAll();
    }

}
