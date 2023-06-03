/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author claua
 */
@Entity
@Table(name = "itemProduct")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemProductEntity {
    //esta clase guadara los productos vendidos para que si llegado el caso se elimanan o modifican no se pierda el control de las ventas reales

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Double cantidad;
    private Double precio;
    private String nombre;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "venta_id") //joinColumn indica quien sera la clase propietaria
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  //para que en api rest ignore la propiedad y pueda serializarla  
    private VentaEntity venta;

    
    
    @Transient
    private Long idVenta;
    
    public Double getTotal(){
        return this.cantidad * this.precio;
    }
    
    public Long getIdVenta(){
        return venta.getId();
    }
 
}
