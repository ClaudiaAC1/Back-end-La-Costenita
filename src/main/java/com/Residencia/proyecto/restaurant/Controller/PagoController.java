/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Controller;

import com.Residencia.proyecto.restaurant.Entity.Dto.GetDate;
import com.Residencia.proyecto.restaurant.Entity.PagoEntity;
import com.Residencia.proyecto.restaurant.Entity.VentaEntity;
import com.Residencia.proyecto.restaurant.Exception.BlogAppException;
import com.Residencia.proyecto.restaurant.Repository.PagoDao;
import com.Residencia.proyecto.restaurant.Services.PagoService;
import com.Residencia.proyecto.restaurant.Utils.CustomResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/paymets")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Autowired
    private PagoDao pagoService2;

    @GetMapping("/")
    public CustomResponse getPagos() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(pagoService.getPagos());
        return customResponse;
    }

    @GetMapping("/{id}")
    public CustomResponse getPagoById(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        Optional<PagoEntity> pagoAux = pagoService.getPagoById(id);

        if (!pagoAux.isPresent()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de pago con ese id");
        }

        customResponse.setData(pagoAux);
        return customResponse;
    }

    @PostMapping("/")
    public ResponseEntity<?> savePago(@RequestBody PagoEntity pago) {
        CustomResponse customResponse = new CustomResponse();

        pagoService.savePago(pago);
        customResponse.setData("Pago " + pago.getNombre() + " registrado correctamente");
        customResponse.setHttpCode(HttpStatus.CREATED.value());
        customResponse.setMensage(HttpStatus.CREATED.name());
        return new ResponseEntity<>(customResponse, HttpStatus.CREATED);

    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePago(@PathVariable Long id){
        CustomResponse customResponse = new CustomResponse();
        Optional<PagoEntity> pagoAux = pagoService.getPagoById(id);
        
        if(!pagoAux.isPresent()){
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de pago con ese id");
        }
        pagoService.deletePago(pagoAux.get());
        customResponse.setData("Pago eliminado correctamente");
        return new ResponseEntity<>(customResponse, HttpStatus.OK);  
    }
    
    @GetMapping("/date/getTotal")
    public CustomResponse getPagosTotalByFecha(@RequestBody GetDate fecha) {
        CustomResponse customResponse = new CustomResponse();
        List<PagoEntity> pagAux = pagoService.getPagos();
        double total = 0.0;
        
        for (PagoEntity pE : pagAux) {
            if(pE.getFecha().equals(fecha.getFecha())){
                total += pE.getPago();
            }
        }
        customResponse.setData(total);
        return customResponse;
    }
//////////////////////////////////////////////    
    @GetMapping("/date")
    public CustomResponse getPagoByFecha(@RequestBody GetDate fecha) {
        CustomResponse customResponse = new CustomResponse();
        Iterable<PagoEntity> pagoAux = pagoService.getPagosByFecha(fecha.getFecha());

//        if (!pagoAux.) {
//            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de pagos en esa fecha");
//        }
        customResponse.setData(pagoAux);
        return customResponse;
    }

    @GetMapping("/date2")
    public Iterable<PagoEntity> getPagoByFecha2() {
        CustomResponse customResponse = new CustomResponse();
        Iterable<PagoEntity> pagoAux
                = pagoService2.findByFecha("2023-05-25");

        customResponse.setData(pagoAux);
        return pagoAux;
    }

    //CONSULTAR PAGO POR FECHA :S
}
