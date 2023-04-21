/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Repository;

import com.Residencia.proyecto.restaurant.Entity.PedidoEntity;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author claua
 */
public interface PedidoDao extends CrudRepository<PedidoEntity, Long>{
    
}
