package com.orjuelalaley.Pizzeria.persistence.projection;

import java.time.LocalDateTime;

/**
 * This interface provides methods to retrieve an orderSummary from the database.
 */
public interface OrderSummary {
     Integer getIdOrder();
    String getCustomerName();
    LocalDateTime getOrderDate();
    Double getOrderTotal();
    String PizzaNames();
}
