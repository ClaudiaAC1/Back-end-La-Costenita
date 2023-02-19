package com.Residencia.proyecto.restaurant.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*")

//@Slf4j
public class EmpleadoController{

    @Autowired
    EmpleadoService empleadoService;

   
    /***
     * Obtener la lista de empleados resgitrados en la base de datos
     * @return
     */
    @GetMapping("")
    public CustomResponse getEmpleados(){        

        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(empleadoService.getEmpleados());
        return customResponse;

    }

    /**
     * Obtener empleado con base a su id
     * @param id
     * @return 
     */
    @GetMapping("/{id}")
    public CustomResponse getEmpleadoId(@PathVariable Long id){        

        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(empleadoService.getEmpleado(id));

        if (customResponse.getData().hashCode() == 0) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de ese id");
          
        } else {
            throw new BlogAppException(HttpStatus.ACCEPTED, "ok", (Object)customResponse.getData());
       
        }
    }

    /**
     * 
     * @param  Entidad Empleado
     * @return 
     */
    @PostMapping("/")
    public ResponseEntity<EmpleadoEntity> saveEmpleado(@RequestBody @Valid EmpleadoEntity empleado){
        //, BindingResult bindingResult
        // CustomResponse customResponse = new CustomResponse();

        // if(bindingResult.hasErrors()){
        //     customResponse.setData("rellenar correctamente los datos");
        //     customResponse.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        // }else{
        //     empleadoService.saveEmpleado(empleado);
        //     customResponse.setData("mesero " + empleado.getNombre() + " registrado correctamente");
        //     customResponse.setHttpCode(HttpStatus.CREATED.value());
        // }
        // return customResponse;
            empleadoService.saveEmpleado(empleado);
            return new ResponseEntity<EmpleadoEntity>(empleado, HttpStatus.OK);

    }

    /**
     * 
     * @param Empleado
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public CustomResponse updateEmpleado(@RequestBody EmpleadoEntity empleado, @PathVariable Long id){
        CustomResponse customResponse = new CustomResponse();
        EmpleadoEntity m = empleadoService.getEmpleado(id).get();
        
        m.setNombre(empleado.getNombre());
        m.setApellidos(empleado.getApellidos());
        m.setTelefono(empleado.getTelefono());
        m.setStatus(empleado.getStatus());
        m.setSueldo(empleado.getSueldo());

        empleadoService.updateEmpleado(m, id);
        customResponse.setData("mesero " + empleado.getNombre() + " actualizado correctamente");
        return customResponse;
    }

    
}