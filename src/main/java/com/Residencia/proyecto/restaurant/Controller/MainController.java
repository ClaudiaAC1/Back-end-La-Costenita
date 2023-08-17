/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Controller;
//
//import org.springframework.web.bind.annotation.RequestMapping;

import com.Residencia.proyecto.restaurant.Entity.UserEntity;
import com.Residencia.proyecto.restaurant.Services.UserService;
import com.Residencia.proyecto.restaurant.Utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author claua
 */
@RestController
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("main/hi")
    public ResponseEntity<?> hi() {
        CustomResponse customResponse = new CustomResponse();
    
        customResponse.setData("usuarios creado exitosamente");
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }


    @GetMapping("/main/user")
    public ResponseEntity<?> saveUser() {
        CustomResponse customResponse = new CustomResponse();
        UserEntity user = UserEntity.builder()
                .name("admin")
                .email("admin@gmail.com")
                .cel("9581419441")
                .roles("admin")
                .password("1234")
                .build();

        UserEntity user2 = UserEntity.builder()
                .name("venta")
                .email("venta@gmail.com")
                .cel("9581419442")
                .roles("user")
                .password("1234")
                .build();

        UserEntity user3 = UserEntity.builder()
                .name("cajero")
                .email("cajero@gmail.com")
                .cel("9581419443")
                .roles("cajero")
                .password("1234")
                .build();

        userService.saveUser(user);
        userService.saveUser(user2);
        userService.saveUser(user3);
        customResponse.setData("usuarios creado exitosamente");
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

   
}
