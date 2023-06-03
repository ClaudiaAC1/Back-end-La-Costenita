/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Repository;

import com.Residencia.proyecto.restaurant.Entity.ItemProductEntity;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author claua
 */
public interface ItemProductDao extends CrudRepository<ItemProductEntity, Long>{
    
}
