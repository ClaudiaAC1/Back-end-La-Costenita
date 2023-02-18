package com.Residencia.proyecto.restaurant.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class index {

    @GetMapping("/")
    public String getC(){
        return "hola";
    }
    
}
