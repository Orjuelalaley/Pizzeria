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
 *
 * @author Sebastián Orjuela Sánchez.
 */
@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    /**
     * Constructor of the PizzaService class.
     *
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
     * Retrieves a pizza by its identifier.
     *
     * @param id The unique identifier of the pizza to be retrieved.
     * @return A PizzaEntity object representing the found pizza, or null if no pizza is found with the given identifier.
     */
    public PizzaEntity getById(Integer id) {
        return this.pizzaRepository.findById(id).orElse(null);
    }
}
