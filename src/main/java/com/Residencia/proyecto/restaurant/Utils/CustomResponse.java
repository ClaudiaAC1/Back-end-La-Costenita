package com.Residencia.proyecto.restaurant.Utils;

import java.util.LinkedList;

public class CustomResponse {

    private Integer httpCode;
    private Object data;
    private String mensage;

    public CustomResponse() {
        this.httpCode = 200;
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
    
    public void addData(String valor) {
       LinkedList<Object> a = new LinkedList<>();
       a.add(data);
       a.add(valor);
       
    }

    public String getMensage() {
        return mensage;
    }

    public void setMensage(String mensage) {
        this.mensage = mensage;
    }

}
