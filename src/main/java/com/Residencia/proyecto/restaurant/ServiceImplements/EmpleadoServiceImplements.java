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


    /***
     *  metodo que interractua con la bd para consultar los empleados
     */
    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoEntity> getEmpleados() {
        return  (List<EmpleadoEntity>) empleadoDao.findAll();
    }

    
    /***
     *  metodo que interractua con la bd para el empleado con base a su id
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EmpleadoEntity> getEmpleadoById(Long id) {
        return empleadoDao.findById(id);
    }


    /***
     *  metodo que interractua con la bd para el empleado con base a su nombre
     */
    @Override
    public Optional<EmpleadoEntity> getEmpleadoByNombre(String nombre) {
        return empleadoDao.findByNombre(nombre);    
    }


    /***
     *  metodo que interractua con la bd para el empleado con base a su telefono
     */
    @Override
    public Optional<EmpleadoEntity> getEmpleadoByTelefono(String telefono) {
        return empleadoDao.findByTelefono(telefono);    
    }
    

    /***
     *  metodo que interractua con la bd para guardar un empleado 
     */
    @Override
    //@Transactional
    public boolean saveEmpleado(EmpleadoEntity empleado) {
        //si existe el numero de teelfono ya en algun usuario
        // que no permita guardarlo
        
        if(empleadoDao.findByTelefono(empleado.getTelefono()).hashCode() == 0){
            empleadoDao.save(empleado);
            return true;
        }
        return false;
        
    }


    /***
     *  metodo que interractua con la bd para eliminar un empleado 
     */
    @Override
    @Transactional
    public void deleteEmpleado(EmpleadoEntity empleado) {
        empleadoDao.delete(empleado);
    }

    
    /***
     *  metodo que interractua con la bd para actualizar un empleado 
     */
    @Override
    @Transactional
    public void updateEmpleado(EmpleadoEntity empleado, Long id) {
        EmpleadoEntity m = empleadoDao.findById(id).get();

        m.setNombre(empleado.getNombre());
        m.setApellidos(empleado.getApellidos());
        m.setTelefono(empleado.getTelefono());
        m.setStatus(empleado.getStatus());
        m.setSueldo(empleado.getSueldo());
        m.setCodigoAcceso(empleado.getCodigoAcceso());
        
    }

    /***
     *  metodo que interractua con la bd y busca el uasuario con base al id 
     * para comparar el codigo de acceso del empleado con el que recibe 
     */
    @Override
    public boolean validCodigoAcceso(Long id, String codigoAcceso){

        EmpleadoEntity empleado = empleadoDao.findById(id).get();
        System.out.println(empleado.getCodigoAcceso());

        if(empleado.getCodigoAcceso().equals(codigoAcceso) )
            return true;
        else{
            return false;
        }
        
    }


    /***
     * localhost:8080/empleoyee/valid/1
     * 
     * {
     *  codigoAcceso:"1234"
     * }
     * 
     * 
     */
    

}
