/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Entity;

import com.Residencia.proyecto.restaurant.Utils.FechaYhora;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;

/**
 *
 * @author claua
 */
@Entity
@Table(name = "venta")
//@Data
@AllArgsConstructor
public class VentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fecha;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private Set<ItemProductEntity> productos = new HashSet<>();

    private Double total;
    
    private String nombreMesero;
    
    private String nombreMesa;

    public VentaEntity() {
        this.fecha = FechaYhora.obtenerFecha();
        this.total = 0.0;
    }
    
    public VentaEntity(String nombreMesero, String nombreMesa) {
        this.fecha = FechaYhora.obtenerFecha();
        this.total = 0.0;
        this.nombreMesa =  nombreMesa;
        this.nombreMesero=  nombreMesero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    } 

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Set<ItemProductEntity> getProductos() {
        return productos;
    }

    public void setProductos(Set<ItemProductEntity> productos) {
        this.productos = productos;
    }

    public Double getTotal() {
        for (ItemProductEntity producto : productos) {
            total += producto.getTotal();
        }
        return total;
    }

    public void setTotal() {
        for (ItemProductEntity producto : productos) {
            total += producto.getTotal();
        }
    }

    public String getNombreMesero() {
        return nombreMesero;
    }

    public void setNombreMesero(String nombreMesero) {
        this.nombreMesero = nombreMesero;
    }

    public String getNombreMesa() {
        return nombreMesa;
    }

    public void setNombreMesa(String nombreMesa) {
        this.nombreMesa = nombreMesa;
    }
}
