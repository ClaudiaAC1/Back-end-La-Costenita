package com.Residencia.proyecto.restaurant.Utils;

import java.util.LinkedList;

public class CustomResponseRol {

    private Integer httpCode;
    private Object data;
    private String rol;
    private String mensage;

    public CustomResponseRol() {
        this.httpCode = 200;
        this.rol = "";
        data = new LinkedList<>();
        this.mensage = "Ok";
    }

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getMensage() {
        return mensage;
    }

    public void setMensage(String mensage) {
        this.mensage = mensage;
    }

}
