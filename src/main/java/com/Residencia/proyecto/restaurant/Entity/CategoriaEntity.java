package com.Residencia.proyecto.restaurant.Entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "categoria")
//, uniqueConstraints = { @UniqueConstraint(columnNames = { "nombre" }) })

public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotBlank (message = "el nombre no puede estar vacio")
    private String nombre;

    @OneToMany(mappedBy = "categoria", cascade= CascadeType.ALL)
    private Set<ProductoEntity> productos =  new HashSet<>();

    public CategoriaEntity(){}
  
    public CategoriaEntity(String nombre, Set<ProductoEntity> productos) {
        this.nombre = nombre;
        this.productos = productos;
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

    public Set<ProductoEntity> getProductos() {
        return productos;
    }

    public void setProductos(Set<ProductoEntity> productos) {
        this.productos = productos;
        for (ProductoEntity producto : productos) {
            producto.setCategoria(this);
        }
    }}
