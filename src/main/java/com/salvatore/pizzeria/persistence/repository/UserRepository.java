package com.salvatore.pizzeria.persistence.repository;

import com.salvatore.pizzeria.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}
