package com.Residencia.proyecto.restaurant.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Residencia.proyecto.restaurant.Services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")

public class UserController {
    @Autowired
    UserService userService;


    // @GetMapping("/{id}")
    // public UsuarioEntity getUser(@PathVariable UsuarioPK llave){
    //     return userService.getEmpleado(llave).get();
    // }
}
