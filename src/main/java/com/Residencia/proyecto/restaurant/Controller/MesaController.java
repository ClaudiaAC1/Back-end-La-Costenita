package com.Residencia.proyecto.restaurant.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Residencia.proyecto.restaurant.Entity.MesaEntity;
import com.Residencia.proyecto.restaurant.Exception.BlogAppException;
import com.Residencia.proyecto.restaurant.Services.MesaService;
import com.Residencia.proyecto.restaurant.Utils.CustomResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/table")

public class MesaController {

    @Autowired
    private MesaService mesaService;

    /**
     * 
     * @return lista de mesas 
     */

    @GetMapping("")
    public CustomResponse getMesas() {

        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(mesaService.getMesas());
        return customResponse;
    }
    
    /**
     * 
     * @param nombre
     * @return objeto mesa con sus datos 
     */

    @GetMapping("name/{nombre}")
    public CustomResponse getMesaNombre(@PathVariable String nombre) {

        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(mesaService.getMesa(nombre));

        if (customResponse.getData().hashCode() == 0) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de esa mesa");

        } else {
            throw new BlogAppException(HttpStatus.OK, "ok", (Object) customResponse.getData());

        }
    }

    /**
     * 
     * @param id
     * @return objeto mesa con coincidencia en id
     */
    @GetMapping("/{id}")
    public CustomResponse getMesaId(@PathVariable Long id) {

        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(mesaService.getMesa(id));

        if (customResponse.getData().hashCode() == 0) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de esa mesa");

        } else {
            throw new BlogAppException(HttpStatus.OK, "ok", (Object) customResponse.getData());

        }
    }
    
    /**
     * 
     * @param mesa
     * @return 
     */
    @PostMapping("/")
    public ResponseEntity<CustomResponse> saveTable(@RequestBody @Valid MesaEntity mesa){
        CustomResponse customResponse = new CustomResponse();

        mesaService.saveMesa(mesa);
        customResponse.setData("Mesa registrada correctamente");
        customResponse.setHttpCode(HttpStatus.CREATED.value());
        customResponse.setMensage(HttpStatus.CREATED.name());

        return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.CREATED);        
    
    }

    /**
     * 
     * @param mesa
     * @param id
     * @return leyenda con creado o no correctamente
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> updateMesa(@RequestBody MesaEntity mesa, @PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        MesaEntity m = mesaService.getMesa(id).get();

        m.setNombre(mesa.getNombre());
        m.setStatus(mesa.getStatus());
        m.setCapacidad(mesa.getCapacidad());
        
        mesaService.updateMesa(m, id);
        customResponse.setData("Mesa actualizada correctamente");
        customResponse.setHttpCode(HttpStatus.CREATED.value());
        customResponse.setMensage(HttpStatus.CREATED.name());
        
        return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.CREATED); 
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CustomResponse> deleteTable(@PathVariable Long id){
        CustomResponse customResponse = new CustomResponse();
        mesaService.deleteMesa(id);

        return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.OK);  

    }   

}
