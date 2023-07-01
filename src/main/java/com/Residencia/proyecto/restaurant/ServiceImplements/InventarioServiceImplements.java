/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.ServiceImplements;

import com.Residencia.proyecto.restaurant.Entity.InventarioEntity;
import com.Residencia.proyecto.restaurant.Repository.InventarioDao;
import com.Residencia.proyecto.restaurant.Services.InventarioService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author claua
 */
@Service
public class InventarioServiceImplements implements InventarioService{

    @Autowired
    private InventarioDao inventarioDao;
    
    @Override
    public List<InventarioEntity> getListProd() {
        return (List<InventarioEntity>) inventarioDao.findAll();
    }

    @Override
    public Optional<InventarioEntity> getProdId(Long id) {
        return inventarioDao.findById(id);
    }

    @Override
    public void save(InventarioEntity prod) {
        inventarioDao.save(prod);
    }

    @Override
    public void update(InventarioEntity prod, Long id) {
        Optional<InventarioEntity > aux = inventarioDao.findById(id);
        
        prod.setId(aux.get().getId());
        inventarioDao.save(prod);
    }

    @Override
    public void delete(Long id) {
        inventarioDao.deleteById(id);
    }
    
}
