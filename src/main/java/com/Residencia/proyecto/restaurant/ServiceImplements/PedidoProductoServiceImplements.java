/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.ServiceImplements;

import com.Residencia.proyecto.restaurant.Entity.Pedido_ProductoEntity;
import com.Residencia.proyecto.restaurant.Exception.BlogAppException;
import com.Residencia.proyecto.restaurant.Repository.PedidoProductoDao;
import com.Residencia.proyecto.restaurant.Services.PedidoProductoService;
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
public class PedidoProductoServiceImplements implements PedidoProductoService {

    @Autowired
    private PedidoProductoDao pedidoProductoDao;

    @Override
    public List<Pedido_ProductoEntity> getPedidos() {
        return (List<Pedido_ProductoEntity>) pedidoProductoDao.findAll();
    }

    @Override
    public Optional<Pedido_ProductoEntity> getPedidoPById(Long id) {
        return pedidoProductoDao.findById(id);
    }

    @Override
    public void savePedidoP(Pedido_ProductoEntity pedido) {
        pedidoProductoDao.save(pedido);
    }

    @Override
    public void deletePedidoP(Long id) {
        Optional<Pedido_ProductoEntity> ppAux
                = pedidoProductoDao.findById(id);
        pedidoProductoDao.delete(ppAux.get());
    }

    @Override
    public void updatePedidoP(Pedido_ProductoEntity pedidoP, Long id) {
        Optional<Pedido_ProductoEntity> pedidoPAux
                = pedidoProductoDao.findById(id);
        
        if (!pedidoPAux.isPresent()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, 
                    "Sin registro del pridcto en el pedido");
        }
        
        pedidoP.setId(pedidoPAux.get().getId());
        pedidoP.setCantidad(pedidoPAux.get().getCantidad());
        pedidoP.setDescripcion(pedidoPAux.get().getDescripcion());
        
        pedidoProductoDao.save(pedidoP);
        
    }
}
