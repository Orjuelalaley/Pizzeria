package com.orjuelalaley.Pizzeria.web.controller;

import com.orjuelalaley.Pizzeria.persistence.entity.OrderEntity;
import com.orjuelalaley.Pizzeria.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class is the controller of the order entity
 * in this class we can find the methods to get:
 * all the orders and an order by id
 * post an order
 * put an order
 * delete an order
 * @author orjuelalaley
 */

@Slf4j
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {this.orderService = orderService;}

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll(){
        try {
           return ResponseEntity.status(HttpStatus.OK).body(orderService.getAll());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * This method is used to get all the orders after today
     * @return a list of orders after today
     */

    @GetMapping("/today")
    public ResponseEntity<?> getAfterTodayOrders(){
        try {
            if (orderService.getAfterTodayOrders().isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No orders after today");
            else
                return ResponseEntity.status(HttpStatus.OK).body(orderService.getAfterTodayOrders());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/outside")
    public ResponseEntity<?> getOutsideOrders(){
        try {
            if (orderService.getOutsideOrders().isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No outside orders");
            else
                return ResponseEntity.status(HttpStatus.OK).body(orderService.getOutsideOrders());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/customer/{id_customer}")
    public ResponseEntity<?> getCustomerOrders(@PathVariable  String id_customer){
        try {
            if (orderService.getCustomerOrders(id_customer).isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No orders for this customer");
            else
                return ResponseEntity.status(HttpStatus.OK).body(orderService.getCustomerOrders(id_customer));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/summary/{id_order}")
    public ResponseEntity<?> getOrderById(@PathVariable Integer id_order){
        try {
            if(id_order == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is required");
            else if (orderService.getOrderSummary(id_order) == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
            else
                return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderSummary(id_order));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}
