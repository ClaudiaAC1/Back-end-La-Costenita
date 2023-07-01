/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Residencia.proyecto.restaurant.Services;

import com.Residencia.proyecto.restaurant.Entity.UserEntity;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author claua
 */
public interface UserService {
      public List<UserEntity> getUsers();
    
    public Optional<UserEntity> findUserById(Long id);
    
    public Optional<UserEntity> getUserByName(String name);
    
    public Optional<UserEntity> getUserByEmail(String email);    
    
    public void saveUser(UserEntity user);
    
    public void updateUser(UserEntity user, Long id);
    
    public void deleteUser(Long id);
    
}
