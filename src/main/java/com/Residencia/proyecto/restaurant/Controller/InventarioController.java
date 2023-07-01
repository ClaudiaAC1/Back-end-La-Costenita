/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Controller;

import com.Residencia.proyecto.restaurant.Entity.InventarioEntity;
import com.Residencia.proyecto.restaurant.Services.InventarioService;
import com.Residencia.proyecto.restaurant.Utils.CustomResponse;
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
@RequestMapping("/inventory")

public class InventarioController {
    @Autowired
    private InventarioService invService;
    
    
    @GetMapping()
    public CustomResponse getInventario(){
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(invService.getListProd());
        return customResponse;
    }
    
    @GetMapping("/{id}")
    public CustomResponse getProdInventario(@PathVariable Long id){
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(invService.getProdId(id));
        return customResponse;
    }
    
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody InventarioEntity prod) {
        CustomResponse customResponse = new CustomResponse();
        invService.save(prod);
        customResponse.setData("producto agregado exitosamente");
        customResponse.setHttpCode(HttpStatus.CREATED.value());
        return new ResponseEntity<>(customResponse, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
     public ResponseEntity<?> update(@RequestBody InventarioEntity prod, @PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        invService.update(prod, id);
        customResponse.setData("registro actualizado");
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public CustomResponse deleteUser(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        invService.delete(id);
        customResponse.setData("registro eliminado");
        return customResponse;
    }
    
}
