package com.Residencia.proyecto.restaurant.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Residencia.proyecto.restaurant.Entity.EmpleadoEntity;
import com.Residencia.proyecto.restaurant.Entity.Dto.CodigoAcceso;
import com.Residencia.proyecto.restaurant.Exception.BlogAppException;
import com.Residencia.proyecto.restaurant.Services.EmpleadoService;
import com.Residencia.proyecto.restaurant.Utils.CustomResponse;

import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;

//import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employee")

public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    /***
     * Obtener la lista de empleados resgitrados en la base de datos
     * 
     * @return
     */
    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('admin','cajero')")
    
    public CustomResponse getEmpleados() {

        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(empleadoService.getEmpleados());
        return customResponse;

    }

    /***
     * Metodo que permite regresar un empleado con base a su nombre
     * 
     * @param nombre
     * @return
     */

    @GetMapping("/search-name/{nombre}")
    @PreAuthorize("hasAnyAuthority('admin','cajero')")
    public ResponseEntity<CustomResponse> getEmpleadoNombre(@PathVariable String nombre) {
        CustomResponse customResponse = new CustomResponse();
                
        customResponse.setData(empleadoService.getEmpleadoByNombre(nombre) );        

        if (customResponse.getData().hashCode() == 0) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de ese nombre");

        } else {
            Optional<EmpleadoEntity> e = empleadoService.getEmpleadoByNombre(nombre);
           
            return new ResponseEntity<>(customResponse, HttpStatus.OK);

        }
    }

    /**
     * Obtener empleado con base a su id
     * 
     * @param id
     * @return
     */
    @GetMapping("/search-id/{id}")
    @PreAuthorize("hasAnyAuthority('admin','cajero')")
    public ResponseEntity<CustomResponse> getEmpleadoId(@PathVariable Long id) {

        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(empleadoService.getEmpleadoById(id));

        if (customResponse.getData().hashCode() == 0) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de ese id");

        } else {
            Optional<EmpleadoEntity> e = empleadoService.getEmpleadoById(id);
            
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
            
            

        }
    }

    /**
     * Hace busqueda con base al numero de telefono
     * 
     * @param telefono
     * @return
     */
    @GetMapping("/search-tel/{telefono}")
    @PreAuthorize("hasAnyAuthority('admin','cajero')")
    public ResponseEntity<CustomResponse> getEmpleadoTelefono(@PathVariable String telefono) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(empleadoService.getEmpleadoByTelefono(telefono));

        if (customResponse.getData().hashCode() == 0) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de ese telefono");

        } else {
            Optional<EmpleadoEntity> e = empleadoService.getEmpleadoByTelefono(telefono);
            
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        }
    }

    /****
     * Guarda un empleado en bd
     * 
     * @param empleado
     * @return
     */
    @PostMapping("/")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<CustomResponse> saveEmpleado(@RequestBody @Valid EmpleadoEntity empleado) {
        CustomResponse customResponse = new CustomResponse();
        
        empleadoService.saveEmpleado(empleado);
        customResponse.setData("Empleado " + empleado.getNombre() + " registrado correctamente");
        customResponse.setHttpCode(HttpStatus.CREATED.value());
        customResponse.setMensage(HttpStatus.CREATED.name());

        return new ResponseEntity<>(customResponse, HttpStatus.CREATED);
    }

    /**
     * Edita el empleado
     * 
     * @param empleado
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<CustomResponse> updateEmpleado(@RequestBody @Valid EmpleadoEntity empleado, @PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();

        empleadoService.updateEmpleado(empleado, id);

        customResponse.setData("Empleado " + empleado.getNombre() + " actualizado correctamente");
        customResponse.setHttpCode(HttpStatus.OK.value());
        customResponse.setMensage(HttpStatus.OK.name());

        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    /**
     * Metodo que compara el codigo de acceso de un empleado
     * con el que esta pansanod en el cuerpo de la peticion
     * 
     * @param id
     * @param codigoAcceso
     * @return 
     **/
    @PostMapping("/valid/{id}")
    
    public CustomResponse validCodigoAcceso(@PathVariable Long id, @RequestBody CodigoAcceso codigoAcceso){
        CustomResponse customResponse = new CustomResponse();

        if(empleadoService.validCodigoAcceso(id, codigoAcceso.getCodigoAcceso())){
            customResponse.setMensage("true");  
            return customResponse;
            
        }else{
            customResponse.setMensage("false");
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "false");
         }
        
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public CustomResponse deleteUser(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        empleadoService.deleteEmpleado(id);
        customResponse.setData("usuario eliminado");
        return customResponse;
    }
}