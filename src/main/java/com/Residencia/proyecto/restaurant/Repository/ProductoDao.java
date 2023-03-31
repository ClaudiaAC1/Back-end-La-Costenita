package com.Residencia.proyecto.restaurant.Repository;


import org.springframework.data.repository.CrudRepository;

import com.Residencia.proyecto.restaurant.Entity.ProductoEntity;

public interface ProductoDao extends CrudRepository<ProductoEntity, Long >{
   
}
