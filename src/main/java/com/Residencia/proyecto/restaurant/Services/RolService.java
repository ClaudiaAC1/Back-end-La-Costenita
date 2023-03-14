/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Services;

import com.Residencia.proyecto.restaurant.Entity.RolEntity;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author claua
 */
public interface RolService {
    public List<RolEntity> getRols();
    
    public Optional<RolEntity> getRolById(Long id);
    
    public Optional<RolEntity> getRolByNombre(String nombre);
    
    public void saveRol(RolEntity rol);
    
    public void updateRol(RolEntity rol, Long id);
    
    public void deleteRol(RolEntity rol);
            
    
}
