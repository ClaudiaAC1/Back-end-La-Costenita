package com.Residencia.proyecto.restaurant.Entity.Dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass //anotacion para poder realizar la herencia :)
@Data
public class Empleado {
    
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String telefono;
    private Byte status ;
    private Double sueldo;

    public Empleado(){}

    public Empleado(String nombre, String telefono, String apellidoP, String apellidoM, Byte status, Double sueldo){
        this.nombre = nombre;
        this.apellidoM = apellidoM;
        this.apellidoP =  apellidoP;
        this.telefono = telefono;
        this.status = status;
        this.sueldo =  sueldo;
    }

    

}
