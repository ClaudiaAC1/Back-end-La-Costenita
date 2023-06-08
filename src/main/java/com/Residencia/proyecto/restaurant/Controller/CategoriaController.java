package com.Residencia.proyecto.restaurant.Controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
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
import com.Residencia.proyecto.restaurant.Services.CategoriaService;
import com.Residencia.proyecto.restaurant.Utils.CustomResponse;
import com.Residencia.proyecto.restaurant.Exception.BlogAppException;
import com.Residencia.proyecto.restaurant.Repository.CategoriaDao;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/category")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    
    /**
     * obtiene lista de categorias
     * 
     * @return
     */
    @GetMapping("")
    public CustomResponse listarCategorias() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(categoriaService.getCategorias());
        return customResponse;
    }

    /**
     * Obtener categoria por id
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public CustomResponse obtenerCategoriaId(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(categoriaService.getCategoriaById(id));

        if (customResponse.getData().hashCode() == 0) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de ese id");
        } else {
            throw new BlogAppException(HttpStatus.OK, "ok", (Object) customResponse.getData());
 
        }
    }

    /**
     * Guarda una categoria
     * 
     * @param categoria
     * @return
     */
    @PostMapping("")
    public ResponseEntity<?> guardaCategoria(@Valid @RequestBody CategoriaEntity categoria) {
        CustomResponse customResponse = new CustomResponse();
        
        categoriaService.saveCategoria(categoria);
        customResponse.setData("Categoria " + categoria.getNombre() + " registrada correctamente");
        customResponse.setHttpCode(HttpStatus.CREATED.value());
        customResponse.setMensage(HttpStatus.CREATED.name());

        return new ResponseEntity<>(customResponse, HttpStatus.CREATED);
    }

    /**
     * Actualizar categoria
     * 
     * @param id
     * @param categoria
     * @return
     */
   
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoria(@RequestBody @Valid CategoriaEntity categoria,
            @PathVariable Long id) {
                CustomResponse customResponse = new CustomResponse();
                                
                categoriaService.updateCategoria(categoria, id);
        
                customResponse.setData("Categoria " + categoria.getNombre() + " actualizada correctamente");        
                return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }
    

    /**
     * Eliminar categoria y por ende se elimana el resgitro de los propductos
     * enlazados a esa categoria
     * 
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        Optional<CategoriaEntity> c = categoriaService.getCategoriaById(id);

        if (!c.isPresent()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de esa categoria");
        }
        categoriaService.deleteCategoria(c.get());
        customResponse.setData("Categoria eliminada correctamente");
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

}
