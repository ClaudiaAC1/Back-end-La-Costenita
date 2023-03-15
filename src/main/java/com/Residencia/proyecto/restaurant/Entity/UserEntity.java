package com.Residencia.proyecto.restaurant.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String email;
    private String password;
    
    //
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empleado_id")
    private EmpleadoEntity empleado; 

    


    public UserEntity(){}

    public UserEntity(String username, String email, String password, EmpleadoEntity empleado) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.empleado = empleado;
    }
    


    

    

   


    
        


    
}
