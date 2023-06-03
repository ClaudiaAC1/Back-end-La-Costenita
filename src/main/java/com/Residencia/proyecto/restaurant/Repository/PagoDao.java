/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Repository;

import com.Residencia.proyecto.restaurant.Entity.PagoEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author claua
 */
public interface PagoDao extends CrudRepository<PagoEntity, Long>{
    
    
//    @Query("SELECT pago FROM PagoEntity pago WHERE pago.fecha = :fecha")
    Iterable<PagoEntity> findByFecha(String fecha);  
    
}
