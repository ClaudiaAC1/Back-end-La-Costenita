/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.ServiceImplements;

import com.Residencia.proyecto.restaurant.Entity.UserEntity;
import com.Residencia.proyecto.restaurant.Repository.UserDao;
import com.Residencia.proyecto.restaurant.Services.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author claua
 */
@Service
public class UserServiceImplements implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

    @Override
    public List<UserEntity> getUsers() {
        return (List<UserEntity>) userDao.findAll();
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public Optional<UserEntity> getUserByName(String name) {
        return userDao.findByName(name);
    }
    
    @Override
    public Optional<UserEntity> getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public void saveUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public void updateUser(UserEntity user, Long id) {
        Optional<UserEntity> userOptional = userDao.findById(id);
        user.setId(userOptional.get().getId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<UserEntity> userOptional = userDao.findById(id);
        userDao.delete(userOptional.get());
    }

}
