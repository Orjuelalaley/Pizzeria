package com.orjuelalaley.Pizzeria.persistence.repository;

import com.orjuelalaley.Pizzeria.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This interface provides methods to retrieve orders from the database.
 * It extends the ListCrudRepository interface, which provides basic CRUD operations.
 * It also provides methods to retrieve orders.
 * @see ListCrudRepository
 * @see OrderEntity
 */

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByDateAfter(LocalDateTime date);
    List<OrderEntity> findAllByMethodIn(List<String> methods) ;
}
