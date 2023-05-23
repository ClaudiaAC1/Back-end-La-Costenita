/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Services;

import com.Residencia.proyecto.restaurant.Entity.CuentaEntity;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author claua
 */
public interface CuentaService {
    
    public List<CuentaEntity> getCuentas();
    
    public void saveCuenta(CuentaEntity cuenta);
    
    public Optional<CuentaEntity> getCuentaId(Long id);
    
    public void deleteCuentaId(Long id);
}
