package com.Residencia.proyecto.restaurant.ServiceImplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Residencia.proyecto.restaurant.Entity.CategoriaEntity;
import com.Residencia.proyecto.restaurant.Repository.CategoriaDao;
import com.Residencia.proyecto.restaurant.Services.CategoriaService;

@Service
public class CategoriaServiceImplements implements CategoriaService {

    @Autowired
    private CategoriaDao categoriaDao;

    @Override
    public List<CategoriaEntity> getCategorias() {
        return (List<CategoriaEntity>) categoriaDao.findAll();
    }

    @Override
    public void saveCategoria(CategoriaEntity categoria) {
        categoriaDao.save(categoria);

    }

    @Override
    public void deleteCategoria(CategoriaEntity categoria) {
        categoriaDao.delete(categoria);
    }

    @Override
    public void updateCategoria(CategoriaEntity categoria, Long id) {
        Optional<CategoriaEntity> c = categoriaDao.findById(id);

        categoria.setId(c.get().getId());
        categoriaDao.save(categoria);
    }

    @Override
    public Optional<CategoriaEntity> getCategoriaById(Long id) {
        return categoriaDao.findById(id);
    }

}
