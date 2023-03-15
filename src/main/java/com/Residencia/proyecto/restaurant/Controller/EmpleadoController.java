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
import com.Residencia.proyecto.restaurant.Utils.CustomResponseRol;

import jakarta.validation.Valid;
import java.util.Optional;

//import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employee")
// @CrossOrigin(origins = "*")

// @Slf4j
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    /***
     * Obtener la lista de empleados resgitrados en la base de datos
     * 
     * @return
     */
    @GetMapping("")
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
    public ResponseEntity<CustomResponseRol> getEmpleadoNombre(@PathVariable String nombre) {
        CustomResponseRol customResponse = new CustomResponseRol();
                
        customResponse.setData(empleadoService.getEmpleadoByNombre(nombre) );        

        if (customResponse.getData().hashCode() == 0) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de ese nombre");

        } else {
            Optional<EmpleadoEntity> e = empleadoService.getEmpleadoByNombre(nombre);
            customResponse.setRol(e.get().getRol().getNombre());
            return new ResponseEntity<>(customResponse, HttpStatus.CREATED);

        }
    }

    /**
     * Obtener empleado con base a su id
     * 
     * @param id
     * @return
     */
    @GetMapping("/search-id/{id}")
    public ResponseEntity<CustomResponseRol> getEmpleadoId(@PathVariable Long id) {

        CustomResponseRol customResponse = new CustomResponseRol();
        customResponse.setData(empleadoService.getEmpleadoById(id));

        if (customResponse.getData().hashCode() == 0) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de ese id");

        } else {
            Optional<EmpleadoEntity> e = empleadoService.getEmpleadoById(id);
            customResponse.setRol(e.get().getRol().getNombre());
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
    public CustomResponse getEmpleadoTelefono(@PathVariable String telefono) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(empleadoService.getEmpleadoByTelefono(telefono));

        if (customResponse.getData().hashCode() == 0) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de ese telefono");

        } else {
            throw new BlogAppException(HttpStatus.OK, "ok", (Object) customResponse.getData());

        }
    }

    /****
     * Guarda un empleado en bd
     * 
     * @param empleado
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<CustomResponse> saveEmpleado(@RequestBody @Valid EmpleadoEntity empleado) {
        CustomResponse customResponse = new CustomResponse();

        if (empleadoService.saveEmpleado(empleado)) {
            customResponse.setData("Empleado " + empleado.getNombre() + " registrado correctamente");
            customResponse.setHttpCode(HttpStatus.CREATED.value());
            customResponse.setMensage(HttpStatus.CREATED.name());
    
            return new ResponseEntity<>(customResponse, HttpStatus.CREATED);
        }
        else {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Numero de telefono ya asignado a otro empleado", (Object) customResponse.getData());

        }
    }

    /**
     * Edita el empleado
     * 
     * @param empleado
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> updateEmpleado(@RequestBody EmpleadoEntity empleado, @PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();

        empleadoService.updateEmpleado(empleado, id);

        customResponse.setData("Empleado " + empleado.getNombre() + " actualizado correctamente");
        customResponse.setHttpCode(HttpStatus.CREATED.value());
        customResponse.setMensage(HttpStatus.CREATED.name());

        return new ResponseEntity<>(customResponse, HttpStatus.CREATED);
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
}