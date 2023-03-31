package com.Residencia.proyecto.restaurant.Services;


import java.util.List;
import java.util.Optional;

import com.Residencia.proyecto.restaurant.Entity.*;

public interface EmpleadoService {
    public List<EmpleadoEntity> getEmpleados();

    public Optional<EmpleadoEntity> getEmpleadoById(Long id);

    public Optional<EmpleadoEntity> getEmpleadoByNombre(String nombre);

    public Optional<EmpleadoEntity> getEmpleadoByTelefono(String telefono);

    public boolean saveEmpleado(EmpleadoEntity empleado); 

    public void deleteEmpleado(EmpleadoEntity empleado); 

    public void updateEmpleado(EmpleadoEntity empleado, Long id);

    public boolean validCodigoAcceso(Long id, String codigoAcceso);

}
