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

@Data
@Entity
@Table(name= "mesa")
public class MesaEntity {
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

    public MesaEntity(){}
    public MesaEntity(String nombre, Byte status, String capacidad){
        this.nombre= nombre;
        this.status = status;
        this.capacidad =  capacidad;
    }
}
