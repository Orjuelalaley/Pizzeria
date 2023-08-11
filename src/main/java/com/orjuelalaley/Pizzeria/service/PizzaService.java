package com.orjuelalaley.Pizzeria.service;

import com.orjuelalaley.Pizzeria.persistence.entity.PizzaEntity;
import com.orjuelalaley.Pizzeria.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class provides services related to pizzas.
 * It contains methods to retrieve:
 * all pizzas
 * get a pizza by its identifier.
 * post a pizza
 * edit the information of a pizza
 * delete a pizza
 * @author Sebastián Orjuela Sánchez.
 */
@Service
public class PizzaService {
    /**
     * This attribute is the repository of the pizza entity
     * @see PizzaRepository
     */

    private final PizzaRepository pizzaRepository;

    /**
     * Constructor of the PizzaService class.
     * @param pizzaRepository The pizza repository to be injected via autowiring.
     */
    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    /**
     * Retrieves all available pizzas in the pizzeria.
     *
     * @return A list of PizzaEntity objects representing all available pizzas.
     */
    public List<PizzaEntity> getAll() {
        return this.pizzaRepository.findAll();
    }

    /**
     * Retrieves all available pizzas in the pizzeria.
     * @return A list of PizzaEntity objects representing all available pizzas.
     */

    public List<PizzaEntity> getAllAvailable() {
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    /**
     * Retrieves a pizza by its identifier.
     *
     * @param id The unique identifier of the pizza to be retrieved.
     * @return A PizzaEntity object representing the found pizza, or null if no pizza is found with the given identifier.
     */
    public PizzaEntity getById(Integer id) {
        return this.pizzaRepository.findById(id).orElse(null);
    }
    /**
     * Saves a pizza in the database.
     *
     * @param pizzaEntity The pizza to be saved.
     * @return A PizzaEntity object representing the saved pizza in the database.
     */

    public PizzaEntity save(PizzaEntity pizzaEntity) {
        return this.pizzaRepository.save(pizzaEntity);
    }

    /**
     * Checks if a pizza exists in the database.
     * @param idPizza The unique identifier of the pizza to be checked.
     * @return A boolean value indicating if the pizza exists in the database.
     */

    public boolean exist(Integer idPizza) {
        return this.pizzaRepository.existsById(idPizza);
    }

    /**
     * Deletes a pizza from the database.
     * @param idPizza The unique identifier of the pizza to be deleted.
     */

    public void delete(Integer idPizza) {
        this.pizzaRepository.deleteById(idPizza);
    }

    public PizzaEntity getByName(String name) {
        return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }
    public List<PizzaEntity> getWith(String ingredient) {
        return this.pizzaRepository.searchAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }
    public List<PizzaEntity> getByPrice(double min, double max) {
        return this.pizzaRepository.findAllByAvailableTrueAndPriceBetweenOrderByPrice(min, max);
    }

    public List<PizzaEntity> getWithout(String ingredientName) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredientName);
    }

    public int getAllVegan(){
        return this.pizzaRepository.countAllByVeganTrue();
    }
}
