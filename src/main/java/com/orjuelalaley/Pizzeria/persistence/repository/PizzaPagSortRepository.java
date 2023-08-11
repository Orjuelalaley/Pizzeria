package com.orjuelalaley.Pizzeria.persistence.repository;

import com.orjuelalaley.Pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListPagingAndSortingRepository;

/**
 * This interface provides methods to retrieve pizzas from the database using pagination and sorting.
 * It extends the ListPagingAndSortingRepository interface.
 */
public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<PizzaEntity, Integer> {
}
