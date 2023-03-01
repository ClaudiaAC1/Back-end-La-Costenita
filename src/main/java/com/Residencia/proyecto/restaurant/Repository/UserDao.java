package com.Residencia.proyecto.restaurant.Repository;

import org.springframework.data.repository.CrudRepository;

import com.Residencia.proyecto.restaurant.Entity.UserEntity;

public interface UserDao extends CrudRepository<UserEntity, Long> {
    
}
