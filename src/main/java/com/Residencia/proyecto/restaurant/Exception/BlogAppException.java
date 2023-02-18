package com.Residencia.proyecto.restaurant.Exception;

import org.springframework.http.HttpStatus;

import com.Residencia.proyecto.restaurant.Utils.CustomResponse;

//Clase que controla las excepciones en tiempo de ejecucion
public class BlogAppException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    CustomResponse customResponse = new CustomResponse();
    private HttpStatus estado;
    private String mensaje;

    public BlogAppException(HttpStatus estado, String mensaje) {
        super();
        this.estado = estado;
        customResponse.setHttpCode(estado.value());
        this.mensaje = mensaje;
    }   
    
    public BlogAppException(HttpStatus estado, String mensaje, Object data) {
        super();
        this.estado = estado;
        customResponse.setHttpCode(estado.value());
        
        customResponse.setData(data);
        this.mensaje = mensaje;
    }

    public HttpStatus getEstado() {
        return estado;
    }

    public void setEstado(HttpStatus estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public CustomResponse getCustomResponse() {
        return customResponse;
    }

    public void setCustomResponse(CustomResponse customResponse) {
        this.customResponse = customResponse;
    }
}
