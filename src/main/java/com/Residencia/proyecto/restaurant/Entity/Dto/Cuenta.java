/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Entity.Dto;

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
public class Cuenta {
    private Long idproducto;
    private String nombre;
    private Integer cantidad;
    private Double precio;
    
    public double getTotal(){
        return cantidad * precio;
    }
}
