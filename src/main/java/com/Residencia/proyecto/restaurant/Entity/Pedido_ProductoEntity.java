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
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author claua
 */
@Entity
@Table(name = "pedido_producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido_ProductoEntity implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Integer cantidad; //cantidad de productos del mismo
    
    private String descripcion;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pedido")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private PedidoEntity idPedido;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_producto")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ProductoEntity idProducto;
    
    @Transient
    private Long id_Producto;
    
    @Transient
    private Long id_Pedido;

    public Pedido_ProductoEntity(Integer cantidad, PedidoEntity idPedido,String descripcion, ProductoEntity idProducto) {
        this.cantidad = cantidad;
        this.idPedido = idPedido;
        this.descripcion = descripcion;
        this.idProducto = idProducto;
        this.id_Pedido = idPedido.getId();
        this.id_Producto = idProducto.getId();
    }
            
    public Long getId_Pedido(){
        return idPedido.getId();
    }
    
    public Long getId_Producto(){
        return idProducto.getId();
    }
}
