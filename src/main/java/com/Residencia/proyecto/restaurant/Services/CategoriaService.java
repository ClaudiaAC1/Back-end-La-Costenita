package com.Residencia.proyecto.restaurant.Services;

import java.util.List;
import java.util.Optional;


import com.Residencia.proyecto.restaurant.Entity.*;

public interface CategoriaService {
    public List<CategoriaEntity> getCategorias();

    public void saveCategoria(CategoriaEntity categoria); 

    public void deleteCategoria(CategoriaEntity categoria); 

    public void updateCategoria(CategoriaEntity categoria, Long id);

    public Optional<CategoriaEntity> getCategoriaById(Long id);
}
