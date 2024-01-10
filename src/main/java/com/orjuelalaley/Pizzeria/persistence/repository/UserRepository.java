package com.orjuelalaley.Pizzeria.persistence.repository;

import com.orjuelalaley.Pizzeria.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {}
