package com.orjuelalaley.Pizzeria.service;

import com.orjuelalaley.Pizzeria.persistence.entity.PizzaEntity;
import com.orjuelalaley.Pizzeria.persistence.repository.PizzaPagSortRepository;
import com.orjuelalaley.Pizzeria.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
     * This attribute is the repository in charge of paginating and sorting the pizzas
     * @see PizzaPagSortRepository
     */
    private final PizzaPagSortRepository pizzaPagSortRepository;

    /**
     * Constructor of the PizzaService class.
     *
     * @param pizzaRepository        The pizza repository to be injected via autowiring.
     * @param pizzaPagSortRepository The pizzaPagSortRepository to be injected via autowiring.
     */
    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    /**
     * Retrieves all available pizzas in the pizzeria.
     *
     * @return A list of PizzaEntity objects representing all available pizzas.
     */
    public Page<PizzaEntity> getAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
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

    /**
     * Retrieves a pizza by its name and if it is available.
     * @param name The name of the pizza to be retrieved it doesn't matter if it is in uppercase or lowercase.
     * @return A PizzaEntity object representing the found pizza, or null if no pizza is found with the given name.
     */
    public PizzaEntity getByName(String name) {
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name);
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

    public List<PizzaEntity> getCheapest(double price){
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }
}
