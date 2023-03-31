package com.Residencia.proyecto.restaurant.Repository;

import com.Residencia.proyecto.restaurant.Entity.UserEntity;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByName(String name);
    Optional<UserEntity> findByEmail(String email);
}
