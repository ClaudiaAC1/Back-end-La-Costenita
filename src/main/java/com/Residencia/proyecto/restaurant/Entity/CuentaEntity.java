/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author claua
 */
@Entity
@Table(name = "cuenta")
public class CuentaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double total;

    @JsonIgnore
    @OneToMany(mappedBy = "idCuenta", cascade = CascadeType.ALL)
    private Set<PedidoEntity> pedidos = new HashSet<>();
    
    @Transient
    private Long idMesa;

    public CuentaEntity() {        
        this.total = 0.0;
        this.idMesa = getIdMesa();
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<PedidoEntity> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<PedidoEntity> pedidos) {
        this.pedidos = pedidos;
    }

    public Double getTotal() {
        for (PedidoEntity pedido : pedidos) {

            pedido.getPedido_producto().forEach((pp) -> {

                total += pp.getCantidad() * pp.getIdProducto().getPrecio();
            });
        }
        return total;
    }
    
    public void setTotal() {
        for (PedidoEntity pedido : pedidos) {

            pedido.getPedido_producto().forEach((pp) -> {
                this.total += pp.getCantidad() * pp.getIdProducto().getPrecio();
            });
        }
       
    }

    public Long getIdMesa() {
        long id = 0;
        for (PedidoEntity pedido : pedidos) {
           id =  pedido.getId_Mesa();
        }
        return this.idMesa = id;
    }

    
    

}
