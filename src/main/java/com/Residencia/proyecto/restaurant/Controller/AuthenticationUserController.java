/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Controller;

import com.Residencia.proyecto.restaurant.Entity.Dto.AuthenticationRequest;
import com.Residencia.proyecto.restaurant.Entity.Dto.TokenResponse;
import com.Residencia.proyecto.restaurant.Entity.UserEntity;
import com.Residencia.proyecto.restaurant.Exception.BlogAppException;
import com.Residencia.proyecto.restaurant.Security.JwtService;
import com.Residencia.proyecto.restaurant.Services.UserService;
import com.Residencia.proyecto.restaurant.Utils.CustomResponse;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author claua
 */
@RestController
@RequestMapping("/login")
public class AuthenticationUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/auth")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthenticationRequest authRequest) {
        CustomResponse customResponse = new CustomResponse();
        Optional<UserEntity> aux = userService.getUserByName(authRequest.getUsername());

        if (aux.isPresent()) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                customResponse.setData(new TokenResponse(
                        aux.get().getName(),
                        aux.get().getRoles(),
                        jwtService.generateToken(authRequest.getUsername())));

                customResponse.setHttpCode(202);
            }
        } else {
            throw new BlogAppException(HttpStatus.FORBIDDEN, "usuario inexistente");
        }

        return new ResponseEntity<>(customResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping("valid/token/{token}")
    public CustomResponse validToken(@PathVariable String token) {
        return jwtService.validarToken(token);
    }
}
