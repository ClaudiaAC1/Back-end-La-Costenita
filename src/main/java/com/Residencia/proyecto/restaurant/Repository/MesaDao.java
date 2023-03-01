package com.Residencia.proyecto.restaurant.Repository;

import com.Residencia.proyecto.restaurant.Entity.*;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface MesaDao extends CrudRepository<MesaEntity, Long>{

    Optional<MesaEntity> findByNombre(String nombre);
    
}
