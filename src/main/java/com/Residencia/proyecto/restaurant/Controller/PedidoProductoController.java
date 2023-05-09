/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Controller;

import com.Residencia.proyecto.restaurant.Entity.Dto.ActProducPed;
import com.Residencia.proyecto.restaurant.Entity.PedidoEntity;
import com.Residencia.proyecto.restaurant.Entity.Dto.PedidoProducto;
import com.Residencia.proyecto.restaurant.Entity.Pedido_ProductoEntity;
import com.Residencia.proyecto.restaurant.Entity.ProductoEntity;
import com.Residencia.proyecto.restaurant.Exception.BlogAppException;
import com.Residencia.proyecto.restaurant.Services.PedidoProductoService;
import com.Residencia.proyecto.restaurant.Services.ProductoService;
import com.Residencia.proyecto.restaurant.Services.PedidoService;
import com.Residencia.proyecto.restaurant.Utils.CustomResponse;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author claua
 */
@RestController
@RequestMapping("/order/product")
public class PedidoProductoController {
    
    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private PedidoProductoService ppService;

    /**
     *
     * @param @return Lista de pedidosProductos
     */
    @GetMapping()
    public CustomResponse getProductoPedido() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(ppService.getPedidos());
        return customResponse;
    }

    /**
     *
     * @param id
     * @return PedidoProducto con base a id
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getPedidoProducto(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        Optional<Pedido_ProductoEntity> ppAux = ppService.getPedidoPById(id);
        
        if (!ppAux.isPresent()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de ese pedidoP");
        }
        customResponse.setData(ppAux);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
        
    }

    /**
     * Asignar un producto al pedido previamente creado
     *
     * @param pedidoProducto
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<?> addProductPedido(@RequestBody PedidoProducto pedidoProducto) {
        CustomResponse customResponse = new CustomResponse();
        
        Optional<PedidoEntity> pedidoAux = pedidoService.getPedidoById(pedidoProducto.getIdPedido());
        Optional<ProductoEntity> productoAux = productoService.getProductoById(pedidoProducto.getIdProducto());
        
        if (!pedidoAux.isPresent()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de ese pedido");
        }
        if (!productoAux.isPresent()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de ese producto");
        }
        
        Pedido_ProductoEntity pedido_producto
                = new Pedido_ProductoEntity(pedidoProducto.getCantidad(), pedidoAux.get(),
                        pedidoProducto.getDescripcion(), productoAux.get());
        
        ppService.savePedidoP(pedido_producto);
        customResponse.setData("Producto asignado al pedido exitosamente");
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    /**
     * Eliminar un producto del pedido
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductPedido(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        ppService.deletePedidoP(id);
        
        customResponse.setData("Producto eliminado del pedido exitosamente");
        return new ResponseEntity<>(customResponse, HttpStatus.OK);               
    }
    
    
    /**
     * Actualizar un producto del pedido 
     * solo se podra actualizar su cantidad y descripcion
     *
     * @param id
     * @param producto    Clase que contiene el modelo para los datos
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductPedido(@PathVariable Long id, @RequestBody ActProducPed producto) {
        CustomResponse customResponse = new CustomResponse();
        Optional<Pedido_ProductoEntity> ppAux = ppService.getPedidoPById(id);
        
        if (!ppAux.isPresent()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST,
                    "Sin registro de esa proucto en el pedido");
        }
        ppAux.get().setCantidad(producto.getCantidad());
        ppAux.get().setDescripcion(producto.getDescripcion());
        
        ppService.updatePedidoP(ppAux.get(), id);
        customResponse.setData("Producto actualizado del pedido exitosamente");
        return new ResponseEntity<>(customResponse, HttpStatus.OK);        
    }
}
