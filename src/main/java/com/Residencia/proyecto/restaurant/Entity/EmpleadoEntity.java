package com.Residencia.proyecto.restaurant.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
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
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author claua
 */
@Entity
@Table(name = "empleado", uniqueConstraints={ //nombre de la tabla y le decimos que el teelfono sera unico para cada empleado
@UniqueConstraint(columnNames = {"telefono"})})

public class EmpleadoEntity implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotBlank(message = "el nombre no puede estar vacio")
    private String nombre;

    @Column
    @NotBlank(message = "el apellido no puede estar vacio")
    private String apellidos;

    @Column
    @Length(max = 10, min = 8)
    private String telefono;

    @Column
    private Byte status; //1:activo, 2:inactivo

    @Column
    private Double sueldo;

    //CODIGO DE ACCESO
    @Column
    @Length(max = 8, min = 4)
    private String codigoAcceso;

    @JsonIgnore
    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private Set<MesaEntity> mesas = new HashSet<>();

    /**
     * *
     * fetch = FetchType.LAZY lazy= solo trae lps datos cuanod se le pidan
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id") //joinColumn indica quien sera la clase propietaria
    @JsonProperty(access = Access.WRITE_ONLY)  //para que en api rest ignore la propiedad y pueda serializarla  
    private RolEntity rol;
    
    @Transient
    private String rolName;

    public EmpleadoEntity(String nombre, String apellidos, String telefono, Byte status, Double sueldo, String codigoAcceso, Set<MesaEntity> mesas, RolEntity rol) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.status = status;
        this.sueldo = sueldo;
        this.codigoAcceso = codigoAcceso;
        this.mesas = mesas;
        this.rol = rol;
        this.rolName = rol.getNombre();
    }

    public EmpleadoEntity() {
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public String getCodigoAcceso() {
        return codigoAcceso;
    }

    public void setCodigoAcceso(String codigoAcceso) {
        this.codigoAcceso = codigoAcceso;
    }

    public Set<MesaEntity> getMesas() {
        return mesas;
    }

    public void setMesas(Set<MesaEntity> mesas) {
        this.mesas = mesas;
        for (MesaEntity mesa : mesas) {
            mesa.setEmpleado(this);
        }
    }

    public RolEntity getRol() {
        return rol;
    }

    public void setRol(RolEntity rol) {
        this.rol = rol;
    }

    public String getRolName() {
        return rol.getNombre();
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }
}
