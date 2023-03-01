package com.Residencia.proyecto.restaurant.ServiceImplements;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Residencia.proyecto.restaurant.Repository.UserDao;
import com.Residencia.proyecto.restaurant.Services.UserService;


@Service
public class UserServiceImplements implements UserService{
    @Autowired
    UserDao userDao;

    // @Override
    // public Optional<UsuarioEntity> getEmpleado(UsuarioPK id) {
    //     return userDao.findById(id);
         
    // }
    
}
