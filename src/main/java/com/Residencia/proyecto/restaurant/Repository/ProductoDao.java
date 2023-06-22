package com.Residencia.proyecto.restaurant.Repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.Residencia.proyecto.restaurant.Entity.ProductoEntity;

public interface ProductoDao extends CrudRepository<ProductoEntity, Long >{
   public Page<ProductoEntity> findAll(Pageable pageable);
}
