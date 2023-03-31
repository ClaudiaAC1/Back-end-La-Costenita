package com.Residencia.proyecto.restaurant.Entity.Dto;

import lombok.Data;

@Data
public class CodigoAcceso {
    private String codigoAcceso;

    public CodigoAcceso() {

    }

    public CodigoAcceso(String codigoAcceso) {
        this.codigoAcceso = codigoAcceso;
    }
}
