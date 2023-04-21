package com.Residencia.proyecto.restaurant.ServiceImplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Residencia.proyecto.restaurant.Entity.EmpleadoEntity;
import com.Residencia.proyecto.restaurant.Entity.RolEntity;
import com.Residencia.proyecto.restaurant.Repository.EmpleadoDao;
import com.Residencia.proyecto.restaurant.Repository.RolDao;
import com.Residencia.proyecto.restaurant.Services.EmpleadoService;

@Service
public class EmpleadoServiceImplements implements EmpleadoService {

    @Autowired
    private EmpleadoDao empleadoDao;

    @Autowired
    private RolDao rolDao;

    /**
     * *
     * metodo que interractua con la bd para consultar los empleados
     *
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoEntity> getEmpleados() {
        return (List<EmpleadoEntity>) empleadoDao.findAll();
    }

    /**
     * *
     * metodo que interractua con la bd para el empleado con base a su id
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EmpleadoEntity> getEmpleadoById(Long id) {
        return empleadoDao.findById(id);
    }

    /**
     * *
     * metodo que interractua con la bd para el empleado con base a su nombre
     *
     * @param nombre
     * @return
     */
    @Override
    public Optional<EmpleadoEntity> getEmpleadoByNombre(String nombre) {
        return empleadoDao.findByNombre(nombre);
    }

    /**
     * *
     * metodo que interractua con la bd para el empleado con base a su telefono
     *
     * @param telefono
     * @return
     */
    @Override
    public Optional<EmpleadoEntity> getEmpleadoByTelefono(String telefono) {
        return empleadoDao.findByTelefono(telefono);
    }

    /**
     * *
     * metodo que interractua con la bd para guardar un empleado
     *
     * @param empleado
     */
    @Override
    //@Transactional
    public void saveEmpleado(EmpleadoEntity empleado) {    
        empleadoDao.save(empleado);
    }

    /**
     * *
     * metodo que interractua con la bd para eliminar un empleado
     *
     * @param id
     */
    @Override
    @Transactional
    public void deleteEmpleado(Long id) {
        Optional<EmpleadoEntity> empleadoOptional = empleadoDao.findById(id);
        
        empleadoDao.delete(empleadoOptional.get());
    }

    /**
     * *
     * metodo que interractua con la bd para actualizar un empleado
     *
     * @param empleado
     * @param id
     */
    @Override
    @Transactional
    public void updateEmpleado(EmpleadoEntity empleado, Long id) {
        Optional<EmpleadoEntity> m = empleadoDao.findById(id);
        Optional<RolEntity> r =  rolDao.findById(empleado.getRol().getId());
        
        empleado.setId(m.get().getId());
        empleado.setRol(r.get());
        empleadoDao.save(empleado);

    }

    /**
     * *
     * metodo que interractua con la bd y busca el uasuario con base al id para
     * comparar el codigo de acceso del empleado con el que recibe
     *
     * @param id
     * @param codigoAcceso
     * @return
     */
    @Override
    public boolean validCodigoAcceso(Long id, String codigoAcceso) {

        EmpleadoEntity empleado = empleadoDao.findById(id).get();
        System.out.println(empleado.getCodigoAcceso());

        return empleado.getCodigoAcceso().equals(codigoAcceso);

    }
}
