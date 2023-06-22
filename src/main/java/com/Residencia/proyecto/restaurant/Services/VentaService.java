/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Services;

import com.Residencia.proyecto.restaurant.Entity.VentaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author claua
 */
public interface VentaService {
    public List<VentaEntity> getVentas();
    
    public void saveVenta(VentaEntity venta);
    
    public Optional<VentaEntity> getVentaId(Long id);
    
    public Page<VentaEntity> getVentasPage(Pageable pageable);
}
