package com.orjuelalaley.Pizzeria.web.controller;

import com.orjuelalaley.Pizzeria.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<?> findByPhone(@PathVariable String phone){
        try {
            System.out.println(phone);
            if (phone.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            else if (customerService.findByPhone(phone) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
            }
            else {
                return ResponseEntity.status(HttpStatus.OK).body(customerService.findByPhone(phone));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
