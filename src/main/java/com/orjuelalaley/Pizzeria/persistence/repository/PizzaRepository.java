package com.orjuelalaley.Pizzeria.persistence.repository;

import com.orjuelalaley.Pizzeria.persistence.entity.PizzaEntity;
import com.orjuelalaley.Pizzeria.service.DTO.UpdatePizzaPriceDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
    PizzaEntity findFirstByAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> searchAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
    List<PizzaEntity> findAllByAvailableTrueAndPriceBetweenOrderByPrice(double min, double max);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String ingredientName);
    Integer countAllByVeganTrue();
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(Double price);

    /**
     * This method is used to update the price of a pizza
     * we use native SQL to update the price
     * @param newPizzaPrice the new price of the pizza
     */
    @Query(value = "UPDATE pizza SET price = :#{#newPizzaPrice.price} WHERE id_pizza = :#{#newPizzaPrice.idPizza}", nativeQuery = true)
    @Modifying
    void updatePrice(UpdatePizzaPriceDTO newPizzaPrice);

    int countByVeganTrue();
}
