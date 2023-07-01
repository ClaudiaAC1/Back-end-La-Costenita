/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Controller;

import com.Residencia.proyecto.restaurant.Entity.ProductContEntity;
import com.Residencia.proyecto.restaurant.Repository.ProductContDao;
import com.Residencia.proyecto.restaurant.Utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author claua
 */
@RestController
@RequestMapping("/product/cont")
public class ProductContController {

    @Autowired
    private ProductContDao productService;

    @GetMapping()
    public CustomResponse getProducts() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(
                productService.findAll());
        return customResponse;
    }

    @GetMapping("/vendidos")
    public CustomResponse getProductsV() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(productService.findAll());
        return customResponse;
    }

    @GetMapping("/cancelados")
    public CustomResponse getProductsC() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(productService.findAll());
        return customResponse;
    }
    
    @PostMapping("")
    public CustomResponse save(ProductContEntity p) {
        CustomResponse customResponse = new CustomResponse();
        productService.save(p);
        customResponse.setData("resgitro correcto");
        return customResponse;
    }

}
