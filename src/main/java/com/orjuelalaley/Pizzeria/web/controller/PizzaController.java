package com.orjuelalaley.Pizzeria.web.controller;

import com.orjuelalaley.Pizzeria.persistence.entity.PizzaEntity;
import com.orjuelalaley.Pizzeria.service.PizzaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static java.util.Objects.isNull;

/**
 * This class is the controller of the pizza entity
 * in this class we can find the methods to get:
 * all the pizzas and a pizza by id
 * @version 1.0
 * @since 2023
 * @see PizzaService
 * @author Sebastián Orjuela Sánchez
 */
@Slf4j
@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(pizzaService.getAll());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> GetById(@PathVariable Integer idPizza){
        try {
            PizzaEntity pizzaEntity = this.pizzaService.getById(idPizza);
            if (isNull(pizzaEntity)) {
                return ResponseEntity.noContent().build();
            }else {
                return ResponseEntity.status(HttpStatus.OK).body(pizzaEntity);
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
