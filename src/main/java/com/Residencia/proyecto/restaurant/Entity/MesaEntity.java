package com.Residencia.proyecto.restaurant.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "mesa",uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nombre"})
})
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "empleado_id") //joinColumn indica quien sera la clase propietaria
    @JsonProperty(access = Access.WRITE_ONLY)  //para que en api rest ignore la propiedad y pueda serializarla  
    private EmpleadoEntity empleado;
    
}
