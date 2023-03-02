package com.Residencia.proyecto.restaurant.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data //notacion para generar gettes y settes
@Entity
@Table(name= "empleado")
//, uniqueConstraints={ //nombre de la tabla y le decimos que el teelfono sera unico para cada empleado
  //  @UniqueConstraint(columnNames = {"telefono"})})

public class EmpleadoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  

    @Column
    @NotBlank (message = "el nombre no puede estar vacio")
    private String nombre;

    @Column
    @NotBlank(message = "el apellido no puede estar vacio")
    private String apellidos;

    @Column
    private String telefono;

    @Column
    private Byte status ; //1:activo, 2:inactivo

    @Column
    private Double sueldo;
    
    //CODIGO DE ACCESO
    @Column
    private String codigoAcceso;

    public EmpleadoEntity(String nombre, String telefono, String apellidos, Byte status, Double sueldo, String codigoAcceso){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.status = status;
        this.sueldo =  sueldo;
        this.codigoAcceso =  codigoAcceso;
    }

    public EmpleadoEntity(){}

}