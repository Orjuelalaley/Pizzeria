package com.orjuelalaley.Pizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "order_item")
@IdClass(OrderItemId.class)
@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderItemEntity {

    @Id
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Id
    @Column(name = "id_item", nullable = false)
    private Integer idItem;

    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    @Column(nullable = false, columnDefinition = "Decimal(2,1)")
    private Double quantity;

    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private Double price;

}
