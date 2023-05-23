/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.ServiceImplements;

import com.Residencia.proyecto.restaurant.Entity.MesaEntity;
import com.Residencia.proyecto.restaurant.Entity.PedidoEntity;
import com.Residencia.proyecto.restaurant.Exception.BlogAppException;
import com.Residencia.proyecto.restaurant.Repository.PedidoDao;
import com.Residencia.proyecto.restaurant.Services.PedidoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author claua
 */
@Service
public class PedidoServiceImplements implements PedidoService {

    @Autowired
    private PedidoDao pedidoDao;

    @Override
    public List<PedidoEntity> getPedidos() {
        return (List<PedidoEntity>) pedidoDao.findAll();
    }

    @Override
    public Optional<PedidoEntity> getPedidoById(Long id) {
        return pedidoDao.findById(id);
    }

    @Override
    public void savePedido(PedidoEntity pedido) {
        pedidoDao.save(pedido);
    }

    @Override
    public void deletePedido(Long id) {
        Optional<PedidoEntity> p = pedidoDao.findById(id);
        if(p.isPresent()) {
            pedidoDao.delete(p.get());
        }
        
        System.out.println("NO ENCONTROOO");

    }

    @Override
    public void updatePedido(PedidoEntity pedido, Long id, MesaEntity mesa) {
        Optional<PedidoEntity> pedidoAux = pedidoDao.findById(id);
        
        if (!pedidoAux.isPresent()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, 
                    "Sin registro del pedido");
        }
        
        pedido.setId(pedidoAux.get().getId());
        pedido.setMesa(mesa);
        pedido.setPedido_producto(pedidoAux.get().getPedido_producto());
        pedidoDao.save(pedido);
        
    }

}
