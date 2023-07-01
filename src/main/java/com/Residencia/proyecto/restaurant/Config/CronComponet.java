/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Config;

import com.Residencia.proyecto.restaurant.Entity.ProductContEntity;
import com.Residencia.proyecto.restaurant.Entity.ProductoEntity;
import com.Residencia.proyecto.restaurant.Repository.ProductoDao;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author claua
 */
@Component
public class CronComponet {
    
    @Autowired
    ProductoDao proService;
    
    
    
    @Scheduled(cron = "0 0 5 1 * ?") //Este metodo se ejecutara cada inicio de mes a las 5 am para restablecer contadores
    public void resetVendidos() {
        List<ProductoEntity> products = (List<ProductoEntity>) proService.findAll();
        
        for (ProductoEntity product : products) {
            product.setCancelados(0);
            product.setContador(0);
            
            proService.save(product);
        }
        
        System.out.println("RESET CONTADORES  ");
       
    }

//    @Scheduled(cron = "0 0 0 L * ?")
//    public void resetCancelados() {
//        long now = System.currentTimeMillis() /1000;
//        System.out.println("cargado en " + now);
//    }
}
