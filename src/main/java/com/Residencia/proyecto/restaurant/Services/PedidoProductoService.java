/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Services;

import com.Residencia.proyecto.restaurant.Entity.Pedido_ProductoEntity;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author claua
 */
public interface PedidoProductoService {

    public List<Pedido_ProductoEntity> getPedidos();

    public Optional<Pedido_ProductoEntity> getPedidoPById(Long id);

    public void savePedidoP(Pedido_ProductoEntity pedido);

    public void deletePedidoP(Long id);

    public void updatePedidoP(Pedido_ProductoEntity pedido, Long id);

}
