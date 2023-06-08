/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Controller;

import com.Residencia.proyecto.restaurant.Entity.Dto.ItemProducto;
import com.Residencia.proyecto.restaurant.Entity.ItemProductEntity;
import com.Residencia.proyecto.restaurant.Entity.VentaEntity;
import com.Residencia.proyecto.restaurant.Exception.BlogAppException;
import com.Residencia.proyecto.restaurant.Repository.ItemProductDao;
import com.Residencia.proyecto.restaurant.Repository.VentaDao;
import com.Residencia.proyecto.restaurant.Utils.CustomResponse;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author claua
 */
@RestController
@RequestMapping("/itemProduct")
public class ItemProductController {

    @Autowired
    private ItemProductDao itemProductService;
    
    @Autowired
    private VentaDao ventaService;

    @GetMapping("/")
    public CustomResponse getItemsP() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(itemProductService.findAll());
        return customResponse;
    }

    @PostMapping("/")
    public ResponseEntity<?> saveItemP(@RequestBody ItemProducto itemP) {
        CustomResponse customResponse = new CustomResponse();
        ItemProductEntity itemPAux = new ItemProductEntity(); 
        
        Optional<VentaEntity> ventaAux = ventaService.findById(itemP.getIdventa());
        
        if(!ventaAux.isPresent()){
               throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de esa venta");
        }
        
        itemPAux.setNombre(itemP.getNombre());
        itemPAux.setPrecio(itemP.getPrecio());
        itemPAux.setCantidad(itemP.getCantidad());
        itemPAux.setVenta(ventaAux.get());
        
        itemProductService.save(itemPAux);
        
        customResponse.setData("Producto " + itemP.getNombre() + " registrado correctamente a la venta");
        
        return new ResponseEntity<>(customResponse, HttpStatus.OK);

    }

}
