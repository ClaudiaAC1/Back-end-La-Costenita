/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Controller;

import com.Residencia.proyecto.restaurant.Entity.Dto.VentaDto;
import com.Residencia.proyecto.restaurant.Entity.VentaEntity;
import com.Residencia.proyecto.restaurant.Exception.BlogAppException;
import com.Residencia.proyecto.restaurant.Repository.VentaDao;
import com.Residencia.proyecto.restaurant.Utils.CustomResponse;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private VentaDao ventaService;

    @GetMapping("/")
    public CustomResponse getVentas() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(ventaService.findAll());
        return customResponse;
    }
    
    @GetMapping("/{id}")
    public CustomResponse getVentaId(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        Optional<VentaEntity> ventaAux =  ventaService.findById(id);
        
        if(!ventaAux.isPresent()){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de esa venta");
        }
        customResponse.setData(ventaAux);
        return customResponse;
    }
    
    @PostMapping("/")
    public ResponseEntity<?> saveVenta(@RequestBody VentaDto venta) {
        CustomResponse customResponse = new CustomResponse();
        VentaEntity ventaS =  new VentaEntity(venta.getNombreMesa(), venta.getNombreMesero());
        
        ventaService.save(ventaS);
        customResponse.setData("Venta " + ventaS.getId() + " registrada correctamente");
        customResponse.setHttpCode(HttpStatus.CREATED.value());
        customResponse.setMensage(HttpStatus.CREATED.name());
        return new ResponseEntity<>(customResponse, HttpStatus.CREATED);

    }

}
