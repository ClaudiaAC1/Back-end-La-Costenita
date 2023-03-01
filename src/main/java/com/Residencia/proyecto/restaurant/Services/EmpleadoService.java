package com.Residencia.proyecto.restaurant.Services;


import java.util.List;
import java.util.Optional;

import com.Residencia.proyecto.restaurant.Entity.*;

public interface EmpleadoService {
    public List<EmpleadoEntity> getEmpleados();

    public Optional<EmpleadoEntity> getEmpleado(Long id);

    public Optional<EmpleadoEntity> getEmpleado(String nombre);

    public void saveEmpleado(EmpleadoEntity empleado); 

    public void deleteEmpleado(EmpleadoEntity empleado); 

    public void updateEmpleado(EmpleadoEntity empleado, Long id);

}
