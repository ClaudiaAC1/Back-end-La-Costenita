/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Controller;

import com.Residencia.proyecto.restaurant.Entity.Dto.Cuenta;
import com.Residencia.proyecto.restaurant.Entity.CuentaEntity;
import com.Residencia.proyecto.restaurant.Entity.PedidoEntity;
import com.Residencia.proyecto.restaurant.Entity.Pedido_ProductoEntity;
import com.Residencia.proyecto.restaurant.Exception.BlogAppException;
import com.Residencia.proyecto.restaurant.Repository.ItemProductDao;
import com.Residencia.proyecto.restaurant.Services.CuentaService;
import com.Residencia.proyecto.restaurant.Services.PedidoService;
import com.Residencia.proyecto.restaurant.Utils.CustomResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author claua
 */
@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private ItemProductDao itemProductService;

    /**
     * Se optimen la cuenta contien el Id de la cuenta y el total generado con
     * base a los productos en ella
     *
     * @return
     */
    @GetMapping()
    public CustomResponse getCuentas() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(cuentaService.getCuentas());
        return customResponse;
    }

    /**
     * Guarda un cuenta el la base de datos para poder asignarle los pedidos
     *
     * @return
     */
    @PostMapping
    public ResponseEntity<?> save() {
        CustomResponse customResponse = new CustomResponse();
        CuentaEntity cuenta = new CuentaEntity();
        
        cuentaService.saveCuenta(cuenta);
        
        customResponse.setData(cuenta.getId());
        customResponse.setHttpCode(HttpStatus.CREATED.value());
        customResponse.setMensage(HttpStatus.CREATED.name());
        return new ResponseEntity<>(customResponse, HttpStatus.CREATED);

    }

    /**
     * Consulta la cuenta con base a su id y muestra la lista de pedidos
     * asigandos a ella
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public CustomResponse getCuentaId(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        Optional<CuentaEntity> c = cuentaService.getCuentaId(id); //busco la cuenta y la guardo

        if (!c.isPresent()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST,
                    "Sin registro de esa cuenta");
        }
        Set<PedidoEntity> pp = c.get().getPedidos(); //<- extraigo todos los pedidos de la cuenta
        List<Cuenta> lc = new ArrayList<>();

        Map<String, List<Cuenta>> prod = new HashMap<>();

        pp.forEach((p) -> {
            Set<Pedido_ProductoEntity> pedido_producto = p.getPedido_producto();
            pedido_producto.forEach((n) -> {
                lc.add(new Cuenta(n.getId_Producto(), n.getIdProducto().getNombre(), n.getCantidad(), n.getIdProducto().getPrecio()));
                prod.put("Cuenta", lc);

            });
        });

        customResponse.setData(prod );
        
        return customResponse;
    }

    /**
     * Consulta la cuenta final con base a su id y muestra los productos,
     * cantidades, precios y totales
     *
     * @param id
     * @return
     */
    @GetMapping("/items/{id}")
    public CustomResponse getCuentaItemsId(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        Optional<CuentaEntity> c = cuentaService.getCuentaId(id);//busco la cuenta y la guardo

        if (!c.isPresent()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST,
                    "Sin registro de esa cuenta");
        }
        Set<PedidoEntity> p = c.get().getPedidos(); //<- extraigo todos los pedidos de la cuenta

        List<Cuenta> lc = new ArrayList<>();
        Map<String, List<Cuenta>> prod = new HashMap<>();

        p.forEach((pp) -> {
            Set<Pedido_ProductoEntity> pedido_producto = pp.getPedido_producto();
            pedido_producto.forEach((n) -> {
                lc.add(new Cuenta(n.getId_Producto(), n.getIdProducto().getNombre(), n.getCantidad(), n.getIdProducto().getPrecio()));
                prod.put("cuenta ", lc);
            });
        });

        List<Cuenta> lc2 = lc;
        for (int i = 0; i < lc2.size(); i++) {
            for (int j = 0; j < lc2.size(); j++) {
                if (i != j) {
                    if (lc2.get(i).getIdproducto() == lc2.get(j).getIdproducto()) {
                        lc2.get(i).setCantidad(lc2.get(i).getCantidad() + lc2.get(j).getCantidad());
                        lc2.remove(j);
                    }
                }
            }
        }
        customResponse.setData(prod  );
        customResponse.setMensage(c.get().getIdMesa() +"");
        return customResponse;
    }

    //no pude eliminar los pedidos directamnete accediendo a cuenta pero lo factible 
    //seria que se eliminara directamente el pedido con base a su id desde su ruta 
    //     localhost:8080/order/id
    //asi que esta ruta solo mandara los ids de los pedidos que pertecen a la cuenta
    @GetMapping("/listId/{id}")
    public CustomResponse deletePedidosC(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        Optional<CuentaEntity> c = cuentaService.getCuentaId(id); //busco la cuenta y la guardo

        if (!c.isPresent()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST,
                    "Sin registro de esa cuenta");
        }
        Set<PedidoEntity> pp = c.get().getPedidos(); //<- extraigo todos los pedidos de la cuenta
        List<Long> p = new ArrayList<>();
        for (PedidoEntity pedidoEntity : pp) {
            p.add(pedidoEntity.getId());
        }

        customResponse.setData(p);
        return customResponse;
    }

    /**
     * Consulta que elimna la cuenta y todos los pedidos relacionados a ella
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public CustomResponse deleteC(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        Optional<CuentaEntity> c = cuentaService.getCuentaId(id); //busco la cuenta y la guardo

        if (!c.isPresent()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST,
                    "Sin registro de esa cuenta");
        }

        cuentaService.deleteCuentaId(id);

        return customResponse;
    }
    
    

}
