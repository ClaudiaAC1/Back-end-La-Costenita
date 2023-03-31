package com.Residencia.proyecto.restaurant.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.Residencia.proyecto.restaurant.Utils.CustomResponse;

@RestControllerAdvice // anotacion para manejar excepciones originadas en el restcontroller
public class GlobalExceptionHandler {

    /**
     * Manejador de excepciones genarada con la clase BlogAppException
     * Manejamos las excepciones a la hora de ejecucion
     *
     * @see BlogAppException
     *
     * @param exception
     * @param webRequest
     * @return los detalles de la excepcion
     */
    @ExceptionHandler(BlogAppException.class)
    public ResponseEntity<CustomResponse> manejarBlogAppException(BlogAppException exception, WebRequest webRequest) {
        Object x = exception.getCustomResponse().getData();
        CustomResponse errorDetalles = new CustomResponse();

        errorDetalles.setHttpCode(exception.getEstado().value());
        errorDetalles.setMensage(exception.getMensaje());
        errorDetalles.setData(x);

        return new ResponseEntity<CustomResponse>(errorDetalles, exception.getEstado());
    }

    /**
     * Manejador de excepciones
     * EXCEPCION QUE SE LANZA CUANDO ALGUN CAMPO ES RELLENADO INCORRECTAMENTE
     * 
        * @param exception
     * @param webRequest
     * @return los detalles de la excepcion
     */
    
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomResponse> manejarhandleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        CustomResponse errorDetalles = new CustomResponse();
            Map<String, String> errores = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nombreCampo = ((FieldError) error).getField();
            String mensaje = error.getDefaultMessage();

            errores.put(nombreCampo, mensaje);
        });
        errorDetalles.setData(errores);
            errorDetalles.setHttpCode(ex.getStatusCode().value());
            errorDetalles.setMensage("Errores al ingresar datos");

        return new ResponseEntity<CustomResponse>(errorDetalles, ex.getStatusCode());
    }

    // @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
    //     Map<String, String> errores = new HashMap<String, String>();
    //     ex.getBindingResult().getAllErrors().forEach((error) -> {
    //         String nombreCampo = ((FieldError) error).getField();
    //         String mensaje = error.getDefaultMessage();

    //         errores.put(nombreCampo, mensaje);
    //     });

    //     return errores;
    // }

    // /**
    // * Manejador de excepciones genarada cuando no se rellenan los campos
    // correctamente
    // *
    // * @see DataIntegrityViolationException
    // *
    // * @param exception
    // * @param webRequest
    // * @return los detalles de la excepcion
    // */
    // @ExceptionHandler(DataIntegrityViolationException.class)
    // public ResponseEntity<CustomResponse> manejarExceptionVar(Exception
    // exception, WebRequest webRequest) {
    // CustomResponse errorDetalles = new CustomResponse();
    // errorDetalles.setHttpCode(HttpStatus.BAD_REQUEST.value());
    // //errorDetalles.setMensage("Rellenar todos los campo");
    // errorDetalles.setMensage(exception.getMessage());
    // return new ResponseEntity<>(errorDetalles, HttpStatus.BAD_REQUEST);
    // }

}
