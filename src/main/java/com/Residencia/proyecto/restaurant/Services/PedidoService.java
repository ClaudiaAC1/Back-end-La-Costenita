/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Services;

import com.Residencia.proyecto.restaurant.Entity.MesaEntity;
import com.Residencia.proyecto.restaurant.Entity.PedidoEntity;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author claua
 */
public interface PedidoService {

    public List<PedidoEntity> getPedidos();

    public Optional<PedidoEntity> getPedidoById(Long id);

    public void savePedido(PedidoEntity pedido);

    public void deletePedido(Long id);
    
     public void updatePedido(PedidoEntity pedido, Long id, MesaEntity mesa);

}
