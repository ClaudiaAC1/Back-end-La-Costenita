/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Entity;

import com.Residencia.proyecto.restaurant.Utils.FechaYhora;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author claua
 */
@Data
@AllArgsConstructor
public class VentaEntity {

    private Long id;
    private String fecha;
    private List<ItemProduct> productos;
    
    public VentaEntity(){
        this.fecha = FechaYhora.obtenerFechaYHoraActual();
    }
    
    public Double getTotal() {
        Double total = 0.0;
        for (ItemProduct producto : productos) {
            total += producto.getTotal();
        }
        return total;
    }
}
