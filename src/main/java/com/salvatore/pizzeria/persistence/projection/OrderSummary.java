package com.salvatore.pizzeria.persistence.projection;

import java.time.LocalDateTime;

public interface OrderSummary {

    Integer getIdOrder();
    String getCustomerName();
    LocalDateTime getOrderDate();
    Double getOrderTotal();
    String getPizzasName();
}
