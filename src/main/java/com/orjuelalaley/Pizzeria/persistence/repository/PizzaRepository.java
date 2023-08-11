package com.orjuelalaley.Pizzeria.persistence.repository;

import com.orjuelalaley.Pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;
import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
    PizzaEntity findFirstByAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> searchAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
    List<PizzaEntity> findAllByAvailableTrueAndPriceBetweenOrderByPrice(double min, double max);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String ingredientName);
    Integer countAllByVeganTrue();
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(Double price);
}
