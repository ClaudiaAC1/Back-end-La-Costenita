/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Controller;

import com.Residencia.proyecto.restaurant.Entity.RolEntity;
import com.Residencia.proyecto.restaurant.Exception.BlogAppException;
import com.Residencia.proyecto.restaurant.Services.RolService;
import com.Residencia.proyecto.restaurant.Utils.CustomResponse;
import jakarta.validation.Valid;
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
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping()
    public CustomResponse getRol() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(rolService.getRols());
        return customResponse;
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/search-id/{id}")
    public CustomResponse getRolById(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(rolService.getRolById(id));

        if (customResponse.getData().hashCode() == 0) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de ese id");
        } else {
            throw new BlogAppException(HttpStatus.OK, "ok", (Object) customResponse.getData());

        }
    }

    @GetMapping("/search-name/{nombre}")
    public CustomResponse getRolByName(@PathVariable String nombre) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(rolService.getRolByNombre(nombre));

        if (customResponse.getData().hashCode() == 0) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de ese rol");
        } else {
            throw new BlogAppException(HttpStatus.OK, "ok", (Object) customResponse.getData());

        } 
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse> saveRol(@RequestBody @Valid RolEntity rol) {
        CustomResponse customResponse = new CustomResponse();
        rolService.saveRol(rol);
        customResponse.setData("Rol " + rol.getNombre() + " registrado correctamente");
        customResponse.setHttpCode(HttpStatus.CREATED.value());
        customResponse.setMensage(HttpStatus.CREATED.name());

        return new ResponseEntity<>(customResponse, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> updateRol(@RequestBody RolEntity rol,
            @PathVariable Long id) {
                CustomResponse customResponse = new CustomResponse();
                                
               rolService.updateRol(rol, id);
        
                customResponse.setData("Rol " + rol.getNombre() + " actualizada correctamente");
                customResponse.setHttpCode(HttpStatus.OK.value());
                customResponse.setMensage(HttpStatus.OK.name());
        
                return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<CustomResponse> deleteRol(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        Optional<RolEntity> c = rolService.getRolById(id);

        if (!c.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        rolService.deleteRol(c.get());
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }
}
