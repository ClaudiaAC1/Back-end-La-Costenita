/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.ServiceImplements;

import com.Residencia.proyecto.restaurant.Entity.VentaEntity;
import com.Residencia.proyecto.restaurant.Repository.VentaDao;
import com.Residencia.proyecto.restaurant.Services.VentaService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author claua
 */
@Service
public class VentaServiceImplements implements VentaService{

    @Autowired
    private VentaDao ventaDao;
    
    @Override
    public List<VentaEntity> getVentas() {
        return (List<VentaEntity>) ventaDao.findAll();
    }

    @Override
    public void saveVenta(VentaEntity venta) {
        ventaDao.save(venta);
    }

    @Override
    public Optional<VentaEntity> getVentaId(Long id) {
        return ventaDao.findById(id);
    }

    @Override
    public Page<VentaEntity> getVentasPage(Pageable pageable) {
        return ventaDao.findAll(pageable);
    }
    
}
