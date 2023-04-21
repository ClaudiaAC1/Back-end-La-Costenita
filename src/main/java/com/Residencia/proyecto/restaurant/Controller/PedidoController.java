/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Controller;

import com.Residencia.proyecto.restaurant.Entity.PedidoEntity;
import com.Residencia.proyecto.restaurant.Repository.PedidoDao;
import com.Residencia.proyecto.restaurant.Entity.Dto.Pedido;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author claua
 */
@RestController
@RequestMapping("/pedido")
public class PedidoController {
    
    @Autowired
    private PedidoDao pedido;
    
    @GetMapping()
    public List<PedidoEntity> getPedido(){
        return (List<PedidoEntity>) pedido.findAll();
    }
    
    /**
     *
     * @param p
     * @return
     */
    @PostMapping()
    public String guardarPedido(@RequestBody PedidoEntity p){
        pedido.save(p);
        return "save";
    }
    
}
