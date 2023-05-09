package com.Residencia.proyecto.restaurant.Controller;

import com.Residencia.proyecto.restaurant.Entity.EmpleadoEntity;
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

import com.Residencia.proyecto.restaurant.Entity.MesaEntity;
import com.Residencia.proyecto.restaurant.Exception.BlogAppException;
import com.Residencia.proyecto.restaurant.Services.EmpleadoService;
import com.Residencia.proyecto.restaurant.Services.MesaService;
import com.Residencia.proyecto.restaurant.Utils.CustomResponse;

import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/table")

public class MesaController {

    @Autowired
    private MesaService mesaService;

    @Autowired
    private EmpleadoService empleadoService;

    /**
     *
     * @return lista de mesas
     */
    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('admin','cajero')")
    public CustomResponse getMesas() {

        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(mesaService.getMesas());
        return customResponse;
    }

    /**
     *
     * @param nombre
     * @return objeto mesa con sus datos
     */
    @GetMapping("search-name/{nombre}")
    @PreAuthorize("hasAnyAuthority('admin','cajero')")
    public ResponseEntity<?> getMesaNombre(@PathVariable String nombre) {
        CustomResponse customResponse = new CustomResponse();
        Optional<MesaEntity> m = mesaService.getMesa(nombre);

        if (!m.isPresent()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de esa mesa");
        }
        customResponse.setData(m);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);

    }

    /**
     *
     * @param id
     * @return objeto mesa con coincidencia en id
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin','cajero')")
    public ResponseEntity<?> getMesaId(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        Optional<MesaEntity> m = mesaService.getMesa(id);

        if (!m.isPresent()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Sin registro de esa mesa");
        }
        customResponse.setData(m);
        return new ResponseEntity<>(customResponse, HttpStatus.OK);

    }

    /**
     *
     * @param mesa
     * @return
     */
    @PostMapping("/")
    @PreAuthorize("hasAnyAuthority('admin','cajero')")
    public ResponseEntity<?> saveTable(@RequestBody @Valid MesaEntity mesa) {
        CustomResponse customResponse = new CustomResponse();

        if (mesa.getEmpleado() == null) {

            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Asignar mesero a la mesa");
        }

        Optional<EmpleadoEntity> empleadoOptional = empleadoService
                .getEmpleadoById(mesa.getEmpleado().getId());

        if (!empleadoOptional.isPresent()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Mesero asignado no encontrado");
        }

        mesa.setEmpleado(empleadoOptional.get());
        mesaService.saveMesa(mesa);
        return new ResponseEntity<>(customResponse, HttpStatus.CREATED);

    }

    /**
     *
     * @param mesa
     * @param id
     * @return leyenda con creado o no correctamente
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin','cajero')")
    public ResponseEntity<?> updateMesa(@RequestBody @Valid MesaEntity mesa, @PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();

        mesaService.updateMesa(mesa, id);
        customResponse.setData("Mesa actualizada correctamente");
        customResponse.setHttpCode(HttpStatus.CREATED.value());
        customResponse.setMensage(HttpStatus.CREATED.name());

        return new ResponseEntity<>(customResponse, HttpStatus.CREATED);
    }

    /**
     * Eliminar mesa
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> deleteTable(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        mesaService.deleteMesa(id);

        return new ResponseEntity<>(customResponse, HttpStatus.OK);

    }

}
