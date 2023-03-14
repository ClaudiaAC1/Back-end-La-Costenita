package com.Residencia.proyecto.restaurant.Utils;

import java.util.LinkedList;

public class CustomResponse {

    private Integer Status;
    private Object data;
    private String mensage;
    
    
    public CustomResponse(){
        this.Status = 200;
	    data = new LinkedList<>();
	    this.mensage = "Ok";
    }    
    

    public Integer getHttpCode() {
        return Status;
    }

    public void setHttpCode(Integer httpCode) {
        this.Status = httpCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    
    public String getMensage() {
        return mensage;
    }

    public void setMensage(String mensage) {
        this.mensage = mensage;
    }
    
}
