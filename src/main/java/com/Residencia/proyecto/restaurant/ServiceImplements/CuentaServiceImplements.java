/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.ServiceImplements;

import com.Residencia.proyecto.restaurant.Entity.CuentaEntity;
import com.Residencia.proyecto.restaurant.Repository.CuentaDao;
import com.Residencia.proyecto.restaurant.Services.CuentaService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author claua
 */
@Service
public class CuentaServiceImplements implements CuentaService{

    @Autowired
    private CuentaDao cuentaDao;
    
    @Override
    public List<CuentaEntity> getCuentas() {
        return (List<CuentaEntity>) cuentaDao.findAll();
    }

    @Override
    public void saveCuenta(CuentaEntity cuenta) {
        cuentaDao.save(cuenta);
    }

    @Override
    public Optional<CuentaEntity> getCuentaId(Long id) {
        return cuentaDao.findById(id);
    }

    @Override
    public void deleteCuentaId(Long id) {
        cuentaDao.deleteById(id);
        
    }
    
}
