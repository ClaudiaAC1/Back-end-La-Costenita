package com.Residencia.proyecto.restaurant.Controller;

import org.springframework.http.HttpStatus;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Residencia.proyecto.restaurant.Entity.CategoriaEntity;
import com.Residencia.proyecto.restaurant.Entity.ProductoEntity;
import com.Residencia.proyecto.restaurant.Utils.CustomResponse;

import jakarta.validation.Valid;

import com.Residencia.proyecto.restaurant.Services.*;
import com.Residencia.proyecto.restaurant.Exception.BlogAppException;

@RestController
@RequestMapping("/product")
public class ProductoController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProductoService productoService;

    /**
     *
     * @return lista de Productos
     */
    @GetMapping("")
    public CustomResponse listarProductos() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(productoService.getProductos());
        return customResponse;

    }

    /**
     *
     * @param id
     * @return objeto producto con sus datos
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> obtenerProductoId(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        
        customResponse.setData(productoService.getProductoById(id));

        if (customResponse.getData().hashCode() == 0) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de ese id");
        } else {
            Optional<ProductoEntity> p = productoService.getProductoById(id);
            return new ResponseEntity<>(customResponse, HttpStatus.OK);

        }
    }

    /**
     *
     * @param producto
     * @return
     */
    @PostMapping("")
    public ResponseEntity<CustomResponse> guardarProducto(@Valid @RequestBody ProductoEntity producto) {
        CustomResponse customResponse = new CustomResponse();
            
        if(producto.getCategoria() == null){
            
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Asignar categoria al producto");
        }
        
        Optional<CategoriaEntity> categoriaOptional = categoriaService
                .getCategoriaById(producto.getCategoria().getId());

        if (!categoriaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        producto.setCategoria(categoriaOptional.get());
        productoService.saveProducto(producto);
        customResponse.setMensage("Producto guardado"+ producto.getNombre()+ "exitosamente");
        return new ResponseEntity<>(customResponse, HttpStatus.CREATED);
    }

    /**
     *
     * @param producto
     * @param id
     * @return leyenda con creado o no correctamente
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> actualizarProducto(@RequestBody ProductoEntity producto, @PathVariable Long id) {

        CustomResponse customResponse = new CustomResponse();

        productoService.updateProducto(producto, id);

        customResponse.setData("Producto " + producto.getNombre() + " actualizado correctamente");
        customResponse.setHttpCode(HttpStatus.OK.value());
        customResponse.setMensage(HttpStatus.OK.name());

        return new ResponseEntity<>(customResponse, HttpStatus.OK);

    }

    /**
     * Eliminar producto
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> eliminarProducto(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        Optional<ProductoEntity> productoOptional = productoService.getProductoById(id);

        if (!productoOptional.isPresent()) {
            return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
        }
        productoService.deleteProducto(productoOptional.get());
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

}
