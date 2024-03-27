package com.salvatore.pizzeria.persistence.repository;

import com.salvatore.pizzeria.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends ListCrudRepository<CustomerEntity, String> {

    @Query(value = "SELECT c FROM CustomerEntity  c where c.phoneNumber = :phone")
    CustomerEntity findByPhone(@Param("phone") String phone);
}
