/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Controller;

import com.Residencia.proyecto.restaurant.Entity.CuentaEntity;
import com.Residencia.proyecto.restaurant.Entity.PedidoEntity;
import com.Residencia.proyecto.restaurant.Entity.Dto.Pedido;
import com.Residencia.proyecto.restaurant.Entity.MesaEntity;
import com.Residencia.proyecto.restaurant.Exception.BlogAppException;
import com.Residencia.proyecto.restaurant.Repository.CuentaDao;
import com.Residencia.proyecto.restaurant.Services.MesaService;
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
@RequestMapping("/order")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private MesaService mesaService;
    
    @Autowired
    private CuentaDao cuentaService;

    /**
     *
     * @param @return Lista de pedidos
     */
    @GetMapping()
    public CustomResponse getPedidos() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(pedidoService.getPedidos());
        return customResponse;
    }

    /**
     * Obtener pedido con base a id
     *
     * @param id
     * @return Pedido con base a id
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getPedidoId(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        Optional<PedidoEntity> pedidoAux = pedidoService.getPedidoById(id);

        if (!pedidoAux.isPresent()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de ese pedido");
        }
        customResponse.setData(pedidoAux);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);

    }

    /**
     * Guardar pedido
     *
     * @param pedido
     * @return
     */
    @PostMapping()
    public ResponseEntity<?> guardarPedido(@RequestBody Pedido pedido) {
        CustomResponse customResponse = new CustomResponse();
        PedidoEntity pedidoAux = new PedidoEntity();
        Optional<MesaEntity> mesaAux
                = mesaService.getMesa(pedido.getIdMesa());
        Optional<CuentaEntity> cuentaAux
                = cuentaService.findById(pedido.getIdCuenta());

        if (!mesaAux.isPresent()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST,
                    "Sin registro de esa mesa");
        }
        
        if (!cuentaAux.isPresent()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST,
                    "Sin registro de esa cuenta");
        }

        pedidoAux.setMesa(mesaAux.get());
        pedidoAux.setIdCuenta(cuentaAux.get());
        pedidoService.savePedido(pedidoAux);
        customResponse.setData(pedidoAux.getId()+"");
        return new ResponseEntity<>(customResponse, HttpStatus.OK);

    }

    /**
     * Actualiza el pedido Ya que solo pedido se registra con el idMesa, cuando
     * se actualiza solo es para cambiar la mesa asignada
     *
     * @param id
     * @param pedido
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        CustomResponse customResponse = new CustomResponse();
        Optional<PedidoEntity> pedidoAux = pedidoService.getPedidoById(id);
        Optional<MesaEntity> mesaAux = mesaService.getMesa(pedido.getIdMesa());

        if (!mesaAux.isPresent()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST,
                    "Sin registro de esa mesa");
        }

        pedidoService.updatePedido(pedidoAux.get(), id, mesaAux.get());

        customResponse.setMensage("Mesa actualizada exitosamente");
        return new ResponseEntity<>(customResponse, HttpStatus.OK);

    }

    /**
     * Elimina el pedido Ya que existen relaciones con otras tablas tambien
     * elimina su registro en ellas
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePedido(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        pedidoService.deletePedido(id);

        customResponse.setData("Pedido eliminado exitosamente");
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

}
