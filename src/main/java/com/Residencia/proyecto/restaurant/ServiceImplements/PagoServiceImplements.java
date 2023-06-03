/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.ServiceImplements;

import com.Residencia.proyecto.restaurant.Entity.PagoEntity;
import com.Residencia.proyecto.restaurant.Repository.PagoDao;
import com.Residencia.proyecto.restaurant.Services.PagoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author claua
 */
@Service
public class PagoServiceImplements implements PagoService{
    
    @Autowired
    private PagoDao pagoDao;

    @Override
    public List<PagoEntity> getPagos() {
        return (List<PagoEntity>) pagoDao.findAll();
    }

    @Override
    public void savePago(PagoEntity pago) {
        pagoDao.save(pago);
    }

    @Override
    public Optional<PagoEntity> getPagoById(Long id) {
       return pagoDao.findById(id);
    }

    @Override
    public Iterable<PagoEntity> getPagosByFecha(String fecha) {
        return pagoDao.findByFecha(fecha);
    }
    
    @Override
    public void deletePago(PagoEntity pago){
        pagoDao.delete(pago);
    }
    
}
