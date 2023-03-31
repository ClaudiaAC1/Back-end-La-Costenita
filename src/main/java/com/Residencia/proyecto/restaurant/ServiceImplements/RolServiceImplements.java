/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.ServiceImplements;

import com.Residencia.proyecto.restaurant.Entity.RolEntity;
import com.Residencia.proyecto.restaurant.Repository.RolDao;
import com.Residencia.proyecto.restaurant.Services.RolService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author claua
 */
@Service
public class RolServiceImplements implements RolService{

    @Autowired
    private RolDao rolDao;
    
    @Override
    public List<RolEntity> getRols() {
        return (List<RolEntity>) rolDao.findAll();
    }

    @Override
    public Optional<RolEntity> getRolById(Long id) {
        return rolDao.findById(id);
    }

    @Override
    public Optional<RolEntity> getRolByNombre(String nombre) {
        return rolDao.findByNombre(nombre);
    }

    @Override
    public void saveRol(RolEntity rol) {
        rolDao.save(rol);
    }

    @Override
    public void updateRol(RolEntity rol, Long id) {
        Optional<RolEntity> rolOptional = rolDao.findById(id);
        
        rol.setId(rolOptional.get().getId());
        rolDao.save(rol);
    }

    @Override
    public void deleteRol(RolEntity rol) {
        rolDao.delete(rol);
    }
    
}
