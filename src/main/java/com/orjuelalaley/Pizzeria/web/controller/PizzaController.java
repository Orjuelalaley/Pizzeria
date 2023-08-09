package com.orjuelalaley.Pizzeria.web.controller;

import com.orjuelalaley.Pizzeria.persistence.entity.PizzaEntity;
import com.orjuelalaley.Pizzeria.service.PizzaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static java.util.Objects.isNull;

/**
 * This class is the controller of the pizza entity
 * in this class we can find the methods to get:
 * all the pizzas and a pizza by id
 * post a pizza
 * put a pizza
 * delete a pizza
 * @since 2023
 * @author orjuelalaley
 */

@Slf4j
@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    /**
     * This attribute is the service of the pizza entity
     * @since 2023
     * @see PizzaService
     */

    private final PizzaService pizzaService;

    /**
     * Constructor of the PizzaController class.
     * @param pizzaService The pizza service to be injected via autowiring.
     */

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    /**
     * Retrieves all available pizzas in the pizzeria.
     * @return A list of PizzaEntity objects representing all available pizzas.
     */
    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(pizzaService.getAll());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Retrieves a pizza by id
     * @param idPizza The id of the pizza to be retrieved
     * @return A PizzaEntity object representing the pizza retrieved
     */

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> GetById(@PathVariable Integer idPizza){
        try {
            PizzaEntity pizzaEntity = this.pizzaService.getById(idPizza);
            if (isNull(pizzaEntity)) {
                return ResponseEntity.notFound().build();
            }else {
                return ResponseEntity.status(HttpStatus.OK).body(pizzaEntity);
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Saves a pizza in the pizzeria database
     * @param pizzaEntity The pizza to be saved
     * @return A PizzaEntity object representing the pizza saved
     */

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PizzaEntity pizzaEntity){
        if (isNull(pizzaEntity)) {
            return ResponseEntity.badRequest().body("The pizza is null");
        } else if (isNull(pizzaEntity.getId_pizza()) || !this.pizzaService.exist(pizzaEntity.getId_pizza())){
            return ResponseEntity.ok(this.pizzaService.save(pizzaEntity));
        } else {
            return ResponseEntity.badRequest().body("The pizza already exists");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody PizzaEntity pizzaEntity){
        if (pizzaEntity.getId_pizza() != null && this.pizzaService.exist(pizzaEntity.getId_pizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizzaEntity));
        } else{
            return ResponseEntity.badRequest().body("The pizza doesn't exist");
        }
    }

    @DeleteMapping("/delete/{idPizza}")
    public ResponseEntity<?> delete(@PathVariable Integer idPizza){
        if (this.pizzaService.exist(idPizza)) {
            this.pizzaService.delete(idPizza);
            return ResponseEntity.ok().body("The pizza was deleted");
        } else {
            return ResponseEntity.badRequest().body("The pizza doesn't exist");
        }
    }
}
