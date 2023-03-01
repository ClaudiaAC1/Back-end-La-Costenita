package com.Residencia.proyecto.restaurant.Repository;

import com.Residencia.proyecto.restaurant.Entity.*;

import org.springframework.data.repository.CrudRepository;


import java.util.Optional;

public interface EmpleadoDao  extends CrudRepository<EmpleadoEntity, Long>{
    Optional<EmpleadoEntity> findByNombre(String nombre);
}
