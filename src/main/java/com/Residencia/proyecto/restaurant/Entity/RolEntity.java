package com.Residencia.proyecto.restaurant.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="rol")
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nombre;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rol", cascade= CascadeType.ALL)
    private Set<EmpleadoEntity> empleados =  new HashSet<>();
    
    public RolEntity(){}
    
    public RolEntity(String nombre){
        this.nombre = nombre;
    }

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

    public Set<EmpleadoEntity> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set<EmpleadoEntity> empleados) {
        this.empleados = empleados;
        for (EmpleadoEntity empleado : empleados) {
            empleado.setRol(this);            
        }
    }
    
}
