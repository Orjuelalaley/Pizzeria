package com.orjuelalaley.Pizzeria.persistence.repository;

import com.orjuelalaley.Pizzeria.persistence.entity.OrderEntity;
import com.orjuelalaley.Pizzeria.persistence.projection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

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

    @Query(value = "SELECT * FROM pizza_order o WHERE id_customer = :id", nativeQuery = true)
    List<OrderEntity> findCustomerOrders(@Param("id") String id_customer);

    /**
     * This method is used to get the summary of an order
     * the summary contains the id of the order, the name of the customer,
     * the date of the order, the total of the order and the names of the pizzas
     * we use native SQL to get the summary
     * @param orderId the id of the order
     * @return the summary of the order
     */
    @Query(value =
            "SELECT po.id_order AS idOrder, cu.name AS customerName, po.date AS orderDate," +
            "       po.total AS orderTotal, GROUP_CONCAT(pi.name) AS pizzaNames " +
            "FROM  pizza_order po " +
            "INNER JOIN customer cu ON po.id_customer = cu.id_customer" +
            "    INNER JOIN order_item oi ON po.id_order = oi.id_order" +
            "    INNER JOIN pizza pi ON oi.id_pizza = pi.id_pizza " +
            "WHERE po.id_order = :orderId " +
            "GROUP BY po.id_order, cu.name, po.date, po.total", nativeQuery = true)
    OrderSummary findSummary(@Param("orderId") Integer orderId);
}
