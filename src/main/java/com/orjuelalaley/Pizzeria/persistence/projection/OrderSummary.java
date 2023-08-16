package com.orjuelalaley.Pizzeria.persistence.projection;

import java.time.LocalDateTime;

public interface OrderSummary {
    //no se tiene que respetar ningun alineamiento
     Integer getIdOrder();
    String getCustomerName();
    LocalDateTime getOrderDate();
    Double getOrderTotal();
    String PizzaNames();
}
