package com.Residencia.proyecto.restaurant.Services;

import com.Residencia.proyecto.restaurant.Entity.MesaEntity;
import java.util.List;
import java.util.Optional;

public interface MesaService {

    public List<MesaEntity> getMesas();
    
    public Optional<MesaEntity> getMesa(Long id);

    public Optional<MesaEntity> getMesa(String nombre);

    public void saveMesa(MesaEntity mesa); 

    public void deleteMesa(Long id); 

    public void updateMesa(MesaEntity mesa, Long id);

}
