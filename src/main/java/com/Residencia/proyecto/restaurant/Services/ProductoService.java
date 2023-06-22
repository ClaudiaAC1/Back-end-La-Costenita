package com.Residencia.proyecto.restaurant.Services;

import java.util.List;
import java.util.Optional;

import com.Residencia.proyecto.restaurant.Entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductoService {
    public List<ProductoEntity> getProductos();

    public void saveProducto(ProductoEntity producto); 

    public void deleteProducto(ProductoEntity producto); 

    public void updateProducto(ProductoEntity producto, Long id);

    public Optional<ProductoEntity> getProductoById(Long id);
    
    public Page<ProductoEntity> findAll(Pageable pageable);
}
