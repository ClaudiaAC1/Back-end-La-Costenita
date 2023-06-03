/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Entity;

import com.Residencia.proyecto.restaurant.Utils.FechaYhora;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author claua
 */
@Entity
@Table(name = "pago")
@Data

public class PagoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private Double pago;
    private String descripcion;
    
    private String fecha;

    public PagoEntity() {
        this.fecha = FechaYhora.obtenerFecha();
    }

    public PagoEntity(String nombre, Double pago, String descripcion) {
        this.nombre = nombre;
        this.pago = pago;
        this.descripcion = descripcion;
        this.fecha = FechaYhora.obtenerFecha();
    }
    
    

}
