/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author claua
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table
public class InventarioEntity implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    
    public String nombre;    
    public String categoria;
    public Double cantidad;
    public String unidad; ///kilos, botella...
    
    public String[] productos; ///listado productos(nombre), separados por comas
        
    public Byte status;
    
    public Double contador;

    public InventarioEntity(String nombre, String categoria, Double cantidad, String unidad, String[] productos, Byte status, Double contador) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.productos = productos;
        this.status = status;
        this.contador = contador;
    }

    
    
    
    
}
