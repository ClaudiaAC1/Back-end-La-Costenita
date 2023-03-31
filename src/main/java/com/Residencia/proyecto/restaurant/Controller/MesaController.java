package com.Residencia.proyecto.restaurant.Controller;


import com.Residencia.proyecto.restaurant.Entity.EmpleadoEntity;
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
import com.Residencia.proyecto.restaurant.Services.EmpleadoService;
import com.Residencia.proyecto.restaurant.Services.MesaService;
import com.Residencia.proyecto.restaurant.Utils.CustomResponse;

import jakarta.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/table")

public class MesaController {

    @Autowired
    private MesaService mesaService;
    
    @Autowired
    private EmpleadoService empleadoService; 

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

    @GetMapping("search-name/{nombre}")
    public ResponseEntity<CustomResponse> getMesaNombre(@PathVariable String nombre) {

        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(mesaService.getMesa(nombre));

        if (customResponse.getData().hashCode() == 0) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de esa mesa");

        } else {
            Optional<MesaEntity> m = mesaService.getMesa(nombre);
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        }
    }

    /**
     * 
     * @param id
     * @return objeto mesa con coincidencia en id
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getMesaId(@PathVariable Long id) {

        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(mesaService.getMesa(id));

        if (customResponse.getData().hashCode() == 0) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de esa mesa");

        } else {
            Optional<MesaEntity> m = mesaService.getMesa(id);
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
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

        if(mesa.getEmpleado() == null){
            
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Asignar mesero a la mesa");
        }
        
        Optional <EmpleadoEntity> empleadoOptional = empleadoService
                              .getEmpleadoById(mesa.getEmpleado().getId());
        
        if(!empleadoOptional.isPresent()){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Mesero asignado no encontrado");
        }
        
        mesa.setEmpleado(empleadoOptional.get());        
        mesaService.saveMesa(mesa);
        return new ResponseEntity<>(customResponse, HttpStatus.CREATED);        
    
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
        
        mesaService.updateMesa(mesa, id);
        customResponse.setData("Mesa actualizada correctamente");
        customResponse.setHttpCode(HttpStatus.CREATED.value());
        customResponse.setMensage(HttpStatus.CREATED.name());
        
        return new ResponseEntity<>(customResponse, HttpStatus.CREATED); 
    }
    
    /**
     * Eliminar mesa
     * @param id
     * @return 
     */
    @DeleteMapping("{id}")
    public ResponseEntity<CustomResponse> deleteTable(@PathVariable Long id){
        CustomResponse customResponse = new CustomResponse();
        mesaService.deleteMesa(id);

        return new ResponseEntity<>(customResponse, HttpStatus.OK);  

    }   

}
