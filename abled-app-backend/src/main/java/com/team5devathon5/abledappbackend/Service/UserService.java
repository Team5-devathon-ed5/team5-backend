package com.team5devathon5.abledappbackend.Service;

import com.team5devathon5.abledappbackend.accounts.UserRepository;
import com.team5devathon5.abledappbackend.accounts.Users;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public Users create(Users user){
        var userToCreate = user;
        LocalDateTime now = LocalDateTime.now();
        userToCreate.setCreated(now);
        userToCreate.setUpdated(now);
        userRepository.save(userToCreate);

        return userToCreate;
    }

    public List<Users> getAllUser(){
        return userRepository.findAll();
    }

    public Users getById(Integer id){
        return userRepository.findById(id).orElseThrow();
    }

    public Users getByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void deleteById(Integer id){
        userRepository.deleteById(id);
    }

    public void deleteByEmail(String email){
        var accountToDelete = userRepository.findByEmail(email);
        userRepository.deleteById(accountToDelete.getId());
    }

    public Users updateUserById(Integer id, Users user){
        var userToUpdate = userRepository.findById(id).orElseThrow();
        //introducir la condición de que los get no sean nulos
        if (user.getName() != null) userToUpdate.setName(user.getName());
        if (user.getEmail() != null) userToUpdate.setEmail(user.getEmail());
        if (user.getUserActive() != null) userToUpdate.setUserActive(user.getUserActive());
        if (user.getDetail() != null) userToUpdate.setDetail(user.getDetail());
        if (user.getImageUrl() != null) userToUpdate.setImageUrl(user.getImageUrl());
        if (user.getPhoneNumber() != null) userToUpdate.setPhoneNumber(user.getPhoneNumber());
        if (user.getCountry() != null) userToUpdate.setCountry(user.getCountry());
        if (user.getAddress() != null) userToUpdate.setAddress(user.getAddress());
        //if(user.getRole() != null) userToUpdate.setRole(user.getRole());
        //El password deberá seguir otro mecanismo?
        if (user.getPassword() != null) userToUpdate.setPassword(user.getPassword());
        userToUpdate.setUpdated(LocalDateTime.now());

        var userUpdated = userRepository.save(userToUpdate);
        return userUpdated;
    }

    public Users updateUserByEmail(String email, Users user){
        var userToUpdate = userRepository.findByEmail(email);
        return updateUserById(userToUpdate.getId(), user);
    }

}
