package com.Residencia.proyecto.restaurant.Controller;

import com.Residencia.proyecto.restaurant.Entity.UserEntity;
import com.Residencia.proyecto.restaurant.Exception.BlogAppException;
import com.Residencia.proyecto.restaurant.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Residencia.proyecto.restaurant.Utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserService userService; 

    @GetMapping()
    @PreAuthorize("hasAuthority('admin')") 
    public CustomResponse getUsers() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(userService.getUsers());
        return customResponse;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<CustomResponse> getUserById(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(userService.getUserById(id));

        if (customResponse.getData().hashCode() == 0) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de ese id");

        } else {
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        }
    }

    @PostMapping()
    public CustomResponse saveUser(@RequestBody @Valid UserEntity user) {
        CustomResponse customResponse = new CustomResponse();
        userService.saveUser(user);
        customResponse.setData("usuario creado");
        return customResponse;
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public CustomResponse updateUser(@RequestBody @Valid UserEntity user, @PathVariable Long id){
        CustomResponse customResponse = new CustomResponse();
        userService.updateUser(user, id);
        customResponse.setData("usuario actualizado");
         return customResponse;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public CustomResponse deleteUser(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        userService.deleteUser(id);
        customResponse.setData("usuario eliminado");
        return customResponse;
    }

}
