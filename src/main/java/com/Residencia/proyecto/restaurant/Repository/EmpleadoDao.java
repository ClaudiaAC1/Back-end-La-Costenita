package com.Residencia.proyecto.restaurant.Repository;

import com.Residencia.proyecto.restaurant.Entity.*;
import org.springframework.data.repository.CrudRepository;

public interface EmpleadoDao extends CrudRepository<EmpleadoEntity, Long> {
    
}
