/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Entity;

import com.Residencia.proyecto.restaurant.Utils.FechaYhora;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "pedido")
public class PedidoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fechaYhora;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_mesa")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private MesaEntity idMesa;
    
    @Transient
    private Long id_mesa;
    
    @JsonIgnore
    @OneToMany(mappedBy = "idPedido", cascade = CascadeType.ALL)
    private Set<Pedido_ProductoEntity> pedido_producto = new HashSet<>();
  
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cuenta")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CuentaEntity idCuenta;
    
    
    public PedidoEntity() {
        this.fechaYhora = FechaYhora.obtenerFechaYHoraActual();
    }

    public PedidoEntity(MesaEntity idMesa) {
        this.fechaYhora = FechaYhora.obtenerFechaYHoraActual();
        this.idMesa = idMesa;
        this.id_mesa = idMesa.getId();
    }

    public PedidoEntity(Set<Pedido_ProductoEntity> pedido_producto) {
        this.fechaYhora = FechaYhora.obtenerFechaYHoraActual();
        this.pedido_producto = pedido_producto;
        this.id_mesa = idMesa.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getFechaYhora() {
        return fechaYhora;
    }

    public void setFechaYhora(String fechaYhora) {
        this.fechaYhora = fechaYhora;
    }

    public Set<Pedido_ProductoEntity> getPedido_producto() {
        return pedido_producto;
    }

    public void setPedido_producto(Set<Pedido_ProductoEntity> pedido_producto) {
        this.pedido_producto = pedido_producto;
        for (Pedido_ProductoEntity pedido_ProductoEntity : pedido_producto) {
            pedido_ProductoEntity.setIdPedido(this);
        }
    }

    @JsonBackReference
//    @JsonManagedReference
    public MesaEntity getMesa() {
        return idMesa;
    }

    public void setMesa(MesaEntity mesa) {
        this.idMesa = mesa;
    }

    public Long getId_Mesa(){
        return idMesa.getId();
    }
    
    public void setId_Mesa(Long idMesa){
        this.id_mesa= idMesa;
    }

    public CuentaEntity getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(CuentaEntity idCuenta) {
        this.idCuenta = idCuenta;
    }
    
    
}
