package com.Residencia.proyecto.restaurant.Entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "mesa",uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nombre"})
})
public class MesaEntity implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  

    @Column
    @NotBlank (message = "el nombre no puede estar vacio")
    private String nombre;

    @Column
    private Byte status; //1-disponible, 2-ocupado, 0-no disponible

    @Column
    @NotBlank (message = "La capacidad no puede estar vacia")
    private String capacidad; //atributo que registrara cuantas personas estan siendo atendidas

    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "empleado_id") //joinColumn indica quien sera la clase propietaria
    @JsonProperty(access = Access.WRITE_ONLY)  //para que en api rest ignore la propiedad y pueda serializarla  
    private EmpleadoEntity empleado;
  
//    @JsonIgnore
    @OneToMany(mappedBy = "idMesa", cascade = CascadeType.ALL)
    private Set<PedidoEntity> pedidos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    @JsonManagedReference 
    public EmpleadoEntity getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }

    @JsonManagedReference
//    @JsonBackReference
    public Set<PedidoEntity> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<PedidoEntity> pedidos) {
        this.pedidos = pedidos;
        for (PedidoEntity pedido : pedidos) {
            pedido.setMesa(this);
            
        }
    }    
}
