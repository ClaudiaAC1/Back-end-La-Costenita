package com.Residencia.proyecto.restaurant.ServiceImplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Residencia.proyecto.restaurant.Entity.EmpleadoEntity;
import com.Residencia.proyecto.restaurant.Repository.EmpleadoDao;
import com.Residencia.proyecto.restaurant.Services.EmpleadoService;

@Service
public class EmpleadoServiceImplements implements EmpleadoService {

    @Autowired
    private EmpleadoDao empleadoDao;


    
    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoEntity> getEmpleados() {
        return  (List<EmpleadoEntity>) empleadoDao.findAll();
    }

    
    @Override
    @Transactional(readOnly = true)
    public Optional<EmpleadoEntity> getEmpleado(Long id) {
        return empleadoDao.findById(id);
    }

    
    @Override
    //@Transactional
    public void saveEmpleado(EmpleadoEntity empleado) {
        empleadoDao.save(empleado);
    }

    
    @Override
    @Transactional
    public void deleteEmpleado(EmpleadoEntity empleado) {
        empleadoDao.delete(empleado);
    }

    
    @Override
    @Transactional
    public void updateEmpleado(EmpleadoEntity empleado, Long id) {
       
    }


    @Override
    public Optional<EmpleadoEntity> getEmpleado(String nombre) {
        return empleadoDao.findByNombre(nombre);    
    }


}
