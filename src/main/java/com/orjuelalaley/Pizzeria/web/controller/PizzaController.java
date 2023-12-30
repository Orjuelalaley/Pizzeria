package com.orjuelalaley.Pizzeria.web.controller;

import com.orjuelalaley.Pizzeria.persistence.entity.PizzaEntity;
import com.orjuelalaley.Pizzeria.service.DTO.UpdatePizzaPriceDTO;
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
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "8") int elements){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(pizzaService.getAll(page, elements));
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
     * Retrieves all available pizzas in the pizzeria.
     * @return A list of PizzaEntity objects representing all available pizzas.
     */

    @GetMapping("/available")
    public ResponseEntity<?> GetAvailable(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "8") int elements,
                                          @RequestParam(defaultValue = "price") String sortBy,
                                          @RequestParam(defaultValue = "ASC") String sortDirection){
        return ResponseEntity.ok(this.pizzaService.getAvailable(page, elements, sortBy, sortDirection));
    }

    /**
     * Retrieves all available pizzas in the pizzeria that are vegan.
     * @return A list of PizzaEntity objects representing all available pizzas that are vegan.
     */

    @GetMapping("/available/vegan")
    public ResponseEntity<?> GetVegan(){
        try {
            if (this.pizzaService.getAllVegan() <= 0){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Vegan pizzas in the menu");
            }else if(this.pizzaService.getAllVegan() == 1){
                return ResponseEntity.status(HttpStatus.OK).body("there are "+ this.pizzaService.getAllVegan() + " vegan pizza available in the menu");
            }else {
                return ResponseEntity.status(HttpStatus.OK).body("There are " + this.pizzaService.getAllVegan() + " vegan pizzas available in the menu");
            }

        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Retrieves a pizza by name if it is available
     * @param name The name of the pizza to be retrieved
     * @return A PizzaEntity object representing the pizza retrieved
     */

    @GetMapping("/available/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name){
        try {
            PizzaEntity pizzaEntity = this.pizzaService.getByName(name);
            if (isNull(pizzaEntity)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The pizza doesn't exist or is not available");
            }else {
                return ResponseEntity.status(HttpStatus.OK).body(pizzaEntity);
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Retrieves all available pizzas in the pizzeria with a specific ingredient
     * @param ingredient The ingredient of the pizzas to be retrieved
     * @return A list of PizzaEntity objects representing all available pizzas with the ingredient
     */

    @GetMapping("with/{ingredient}")
    public ResponseEntity<?> getByIngredient(@PathVariable String ingredient){
        try {
            List<PizzaEntity> pizzaEntities = this.pizzaService.getWith(ingredient);
            if (pizzaEntities.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no pizzas with this ingredient");
            }else {
                return ResponseEntity.status(HttpStatus.OK).body(pizzaEntities);
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Retrieves all available pizzas in the pizzeria with a price between min and max
     * @param min The minimum price of the pizzas to be retrieved
     * @param max The maximum price of the pizzas to be retrieved
     * @return A list of PizzaEntity objects representing all available pizzas with the price between min and max
     */

    @GetMapping("/price/{min}/{max}")
    public ResponseEntity<?> getByPrice(@PathVariable double min, @PathVariable double max){
        try {
            List<PizzaEntity> pizzaEntities = this.pizzaService.getByPrice(min, max);
            if (pizzaEntities.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no pizzas with this price");
            }else {
                return ResponseEntity.status(HttpStatus.OK).body(pizzaEntities);
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Retrieves all available pizzas in the pizzeria without a specific ingredient
     * @param ingredient The ingredient of the pizzas to be retrieved
     * @return A list of PizzaEntity objects representing all available pizzas without the ingredient
     */

    @GetMapping("without/{ingredient}")
    public ResponseEntity<?> getByIngredientNot(@PathVariable String ingredient){
        try {
            List<PizzaEntity> pizzaEntities = this.pizzaService.getWithout(ingredient);
            if (pizzaEntities.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no pizzas without this ingredient");
            }else {
                return ResponseEntity.status(HttpStatus.OK).body(pizzaEntities);
            }
        }catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/price/{price}")
    public ResponseEntity<?> getCheapestPizza(@PathVariable double price){
        try {
            List<PizzaEntity> pizzaEntities = this.pizzaService.getCheapest(price);
            if (pizzaEntities.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no pizzas with this price");
            }else {
                return ResponseEntity.status(HttpStatus.OK).body(pizzaEntities);
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
        System.out.println(pizzaEntity);
        if (pizzaEntity.getId_pizza() != null && this.pizzaService.exist(pizzaEntity.getId_pizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizzaEntity));
        } else{
            return ResponseEntity.badRequest().body("The pizza doesn't exist");
        }
    }

    @PutMapping("/update/price")
    public ResponseEntity<?> updatePrice(@RequestBody UpdatePizzaPriceDTO DTO){
        if (this.pizzaService.exist(DTO.getIdPizza())) {
            this.pizzaService.updatePrice(DTO);
            return ResponseEntity.ok().body("The pizza price was updated");
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



