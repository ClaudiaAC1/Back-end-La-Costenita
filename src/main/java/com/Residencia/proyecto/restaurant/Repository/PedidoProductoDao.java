/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Repository;

import com.Residencia.proyecto.restaurant.Entity.Pedido_ProductoEntity;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author claua
 */
public interface PedidoProductoDao extends CrudRepository<Pedido_ProductoEntity, Long>{
    
}
