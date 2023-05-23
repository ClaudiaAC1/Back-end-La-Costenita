/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author claua
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemProduct {
    //esta clase guadara los productos vendidos para que si llegado el caso se elimanan o modifican no se pierda el control de las ventas reales

    private Long id;
    private Double cantidad;
    private Double precio;
    private String nombre;
    
   // private VentaEntity venta; //relacion con venta @ManyToOne

    
    public Double getTotal(){
        return this.cantidad * this.precio;
    }
}
