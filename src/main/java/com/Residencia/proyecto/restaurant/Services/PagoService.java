/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Services;

import com.Residencia.proyecto.restaurant.Entity.PagoEntity;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author claua
 */
public interface PagoService {
    public List<PagoEntity> getPagos();
    
    public void savePago(PagoEntity pago);
    
    public Optional<PagoEntity> getPagoById(Long id);
    
    public Iterable<PagoEntity> getPagosByFecha(String fecha);
    
    public void deletePago(PagoEntity pago);
}
