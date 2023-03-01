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
import com.Residencia.proyecto.restaurant.Exception.BlogAppException;
import com.Residencia.proyecto.restaurant.Services.EmpleadoService;
import com.Residencia.proyecto.restaurant.Utils.CustomResponse;

import jakarta.validation.Valid;

//import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employee")
//@CrossOrigin(origins = "*")

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

    @GetMapping("name/{nombre}")
    public CustomResponse getEmpleadoNombre(@PathVariable String nombre) {

        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(empleadoService.getEmpleado(nombre));

        if (customResponse.getData().hashCode() == 0) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de ese nombre");

        } else {
            throw new BlogAppException(HttpStatus.OK, "ok", (Object) customResponse.getData());

        }
    }
        // @GetMapping("")
        // public ResponseEntity<List<EmpleadoEntity>> getEmpleados() {

        //     // CustomResponse customResponse = new CustomResponse();
        //     // customResponse.setData(empleadoService.getEmpleados());
        //     // return customResponse;

        //     return new ResponseEntity<>(empleadoService.getEmpleados(), HttpStatus.OK);

        // }

    /**
     * Obtener empleado con base a su id
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public CustomResponse getEmpleadoId(@PathVariable Long id) {

        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(empleadoService.getEmpleado(id));

        if (customResponse.getData().hashCode() == 0) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de ese id");

        } else {
            throw new BlogAppException(HttpStatus.OK, "ok", (Object) customResponse.getData());

        }
    }

    /****
     * 
     * @param empleado
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<CustomResponse> saveEmpleado(@RequestBody @Valid EmpleadoEntity empleado) {
        CustomResponse customResponse = new CustomResponse();

        empleadoService.saveEmpleado(empleado);
        customResponse.setData("Empleado " + empleado.getNombre() + " registrado correctamente");
        customResponse.setHttpCode(HttpStatus.CREATED.value());
        customResponse.setMensage(HttpStatus.CREATED.name());

        return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.CREATED);        
    }

    /**
     * 
     * @param Empleado
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> updateEmpleado(@RequestBody EmpleadoEntity empleado, @PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        EmpleadoEntity m = empleadoService.getEmpleado(id).get();

        m.setNombre(empleado.getNombre());
        m.setApellidos(empleado.getApellidos());
        m.setTelefono(empleado.getTelefono());
        m.setStatus(empleado.getStatus());
        m.setSueldo(empleado.getSueldo());
        m.setCodigoAcceso(empleado.getCodigoAcceso());

        empleadoService.updateEmpleado(m, id);
        customResponse.setData("Empleado " + empleado.getNombre() + " actualizado correctamente");
        customResponse.setHttpCode(HttpStatus.CREATED.value());
        customResponse.setMensage(HttpStatus.CREATED.name());
        
        return new ResponseEntity<CustomResponse>(customResponse, HttpStatus.CREATED); 
    }

}