package com.Residencia.proyecto.restaurant.ServiceImplements;

import com.Residencia.proyecto.restaurant.Entity.EmpleadoEntity;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Residencia.proyecto.restaurant.Entity.MesaEntity;
import com.Residencia.proyecto.restaurant.Repository.EmpleadoDao;
import com.Residencia.proyecto.restaurant.Repository.MesaDao;
import com.Residencia.proyecto.restaurant.Services.MesaService;

@Service
public class MesaServiceImplements implements MesaService {

    @Autowired
    private MesaDao mesaDao;

    @Autowired
    EmpleadoDao empleadoDao;

    @Override
    @Transactional(readOnly = true)
    public List<MesaEntity> getMesas() {
        return (List<MesaEntity>) mesaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MesaEntity> getMesa(Long id) {
        return mesaDao.findById(id);
    }

    @Override
    public Optional<MesaEntity> getMesa(String nombre) {
        return mesaDao.findByNombre(nombre);
    }

    @Override
    public void saveMesa(MesaEntity mesa) {
        mesaDao.save(mesa);
    }

    @Override
    @Transactional
    public void deleteMesa(Long id) {
        mesaDao.deleteById(id);
    }

    @Override
    @Transactional
    public void updateMesa(MesaEntity mesa, Long id) {
        Optional<MesaEntity> m = mesaDao.findById(id);

        Optional<EmpleadoEntity> e = empleadoDao
                .findById(mesa.getEmpleado().getId());

        mesa.setId(m.get().getId());
        mesa.setEmpleado(e.get());
        mesaDao.save(mesa);
    }

}


