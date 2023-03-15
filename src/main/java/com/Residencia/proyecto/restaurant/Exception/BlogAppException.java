package com.Residencia.proyecto.restaurant.Exception;

import org.springframework.http.HttpStatus;

import com.Residencia.proyecto.restaurant.Utils.CustomResponse;
import com.Residencia.proyecto.restaurant.Utils.CustomResponseRol;

//Clase que controla las excepciones en tiempo de ejecucion
public class BlogAppException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    CustomResponse customResponse = new CustomResponse();
    CustomResponseRol customResponseRol = new CustomResponseRol();
    private HttpStatus status;
    private String mensaje;

    public BlogAppException(HttpStatus status, String mensaje) {
        super();
        this.status = status;
        customResponse.setHttpCode(status.value());
        this.mensaje = mensaje;
    }

    public BlogAppException(HttpStatus estado, String mensaje, Object data) {
        super();
        this.status = estado;
        customResponse.setHttpCode(estado.value());

        customResponse.setData(data);
        this.mensaje = mensaje;
    }

    public BlogAppException(HttpStatus estado, String mensaje, Object data, String rol) {
        super();
        this.status = estado;
        customResponseRol.setHttpCode(estado.value());

        customResponseRol.setData(data);
        customResponseRol.setRol(rol);
        this.mensaje = mensaje;
    }

    public HttpStatus getEstado() {
        return status;
    }

    public void setEstado(HttpStatus status) {
        this.status = status;
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

    public CustomResponseRol getCustomResponseRol() {
        return customResponseRol;
    }

    public void setCustomResponseRol(CustomResponseRol customResponseRol) {
        this.customResponseRol = customResponseRol;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
