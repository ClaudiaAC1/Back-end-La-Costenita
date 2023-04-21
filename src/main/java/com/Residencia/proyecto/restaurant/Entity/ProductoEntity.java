package com.Residencia.proyecto.restaurant.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
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
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
@Entity 
@Table(name="producto")
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    

    private String nombre;
    private String tamanio; //chico, mediano, grande
    private Double precio;
    private Byte status; //1:activo, 2:inactivo

    private Integer contador; //anilozar bien este atributo
    private String url_img; //guarda la url de la imagen guardada en el backend

    //relacion con categoria
    //*p--------1c    1c--------*P
    //n a 1


    /***
     * fetch = FetchType.LAZY  lazy= solo trae lps datos cuanod se le pidan
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id") //joinColumn indica quien sera la clase propietaria
    @JsonProperty(access = Access.WRITE_ONLY)  //para que en api rest ignore la propiedad y pueda serializarla  
    private CategoriaEntity categoria;

    @Transient
    private String categoriaName;
    
    @JsonIgnore
    @OneToMany(mappedBy = "idProducto", cascade= CascadeType.ALL)
    private Set<Pedido_ProductoEntity> producto_pedido =  new HashSet<>();
            
     
    public ProductoEntity(){

    }

    public ProductoEntity(String nombre, String tamanio, Double precio, Byte status, Integer contador, String url_img, CategoriaEntity categoria) {
        this.nombre = nombre;
        this.tamanio = tamanio;
        this.precio = precio;
        this.status = status;
        this.contador = contador;
        this.url_img = url_img;
        this.categoria = categoria;
        this.categoriaName =  categoria.getNombre();
    }

    public String getcategoriaName(){
        return categoria.getNombre();
    }

}
