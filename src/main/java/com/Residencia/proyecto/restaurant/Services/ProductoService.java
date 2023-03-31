package com.Residencia.proyecto.restaurant.Services;

import java.util.List;
import java.util.Optional;

import com.Residencia.proyecto.restaurant.Entity.*;

public interface ProductoService {
    public List<ProductoEntity> getProductos();

    public void saveProducto(ProductoEntity producto); 

    public void deleteProducto(ProductoEntity producto); 

    public void updateProducto(ProductoEntity producto, Long id);

    public Optional<ProductoEntity> getProductoById(Long id);
}
