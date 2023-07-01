/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Services;

import com.Residencia.proyecto.restaurant.Entity.InventarioEntity;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author claua
 */
public interface InventarioService {
    
    public List<InventarioEntity> getListProd();
    
    public Optional<InventarioEntity> getProdId(Long id);
    
    public void save(InventarioEntity prod);
    
    public void update(InventarioEntity prod, Long id);
    
    public void delete(Long id);
            
    
}
