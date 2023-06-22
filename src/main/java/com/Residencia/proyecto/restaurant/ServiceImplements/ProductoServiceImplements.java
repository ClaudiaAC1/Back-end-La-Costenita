package com.Residencia.proyecto.restaurant.ServiceImplements;

import com.Residencia.proyecto.restaurant.Entity.CategoriaEntity;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Residencia.proyecto.restaurant.Entity.ProductoEntity;
import com.Residencia.proyecto.restaurant.Repository.CategoriaDao;
import com.Residencia.proyecto.restaurant.Repository.ProductoDao;
import com.Residencia.proyecto.restaurant.Services.ProductoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ProductoServiceImplements implements ProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Autowired
    private CategoriaDao categoriaDao;

    @Override
    public List<ProductoEntity> getProductos() {
        return (List<ProductoEntity>) productoDao.findAll();
    }

    @Override
    public void saveProducto(ProductoEntity producto) {
        productoDao.save(producto);
    }

    @Override
    public void deleteProducto(ProductoEntity producto) {
        productoDao.delete(producto);
    }

    @Override
    public void updateProducto(ProductoEntity producto, Long id) {
        Optional<ProductoEntity> productoOptional = productoDao.findById(id);
        Optional<CategoriaEntity> categoriaOptional = categoriaDao
                .findById(producto.getCategoria().getId());

        producto.setCategoria(categoriaOptional.get());
        producto.setId(productoOptional.get().getId());
        productoDao.save(producto);
    }

    @Override
    public Optional<ProductoEntity> getProductoById(Long id) {
        return productoDao.findById(id);
    }

    @Override
    public Page<ProductoEntity> findAll(Pageable pageable) {
        return productoDao.findAll(pageable);
    }

}
