package com.team5devathon5.abledappbackend.accounts;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public User create(User user){
        var userToCreate = user;
        LocalDateTime now = LocalDateTime.now();
        userToCreate.setCreated(now);
        userToCreate.setUpdated(now);
        userRepository.save(userToCreate);

        return userToCreate;
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getById(Integer id){
        return userRepository.findById(id).orElseThrow();
    }

    public User getByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void deleteById(Integer id){
        userRepository.deleteById(id);
    }

    public void deleteByEmail(String email){
        var accountToDelete = userRepository.findByEmail(email);
        userRepository.deleteById(accountToDelete.getId());
    }

    public User updateUserById(Integer id, User account){
        var userToUpdate = userRepository.findById(id).orElseThrow();
        //introducir la condición de que los get no sean nulos
        if (account.getName() != null) userToUpdate.setName(account.getName());
        if (account.getEmail() != null) userToUpdate.setEmail(account.getEmail());
        if (account.getUserActive() != null) userToUpdate.setUserActive(account.getUserActive());
        if (account.getDetail() != null) userToUpdate.setDetail(account.getDetail());
        if (account.getImageUrl() != null) userToUpdate.setImageUrl(account.getImageUrl());
        if (account.getPhoneNumber() != null) userToUpdate.setPhoneNumber(account.getPhoneNumber());
        if (account.getCountry() != null) userToUpdate.setCountry(account.getCountry());
        if (account.getAddress() != null) userToUpdate.setAddress(account.getAddress());
        if(account.getRole() != null) userToUpdate.setRole(account.getRole());
        //El password deberá seguir otro mecanismo?
        if (account.getPassword() != null) userToUpdate.setPassword(account.getPassword());
        userToUpdate.setUpdated(LocalDateTime.now());

        var userUpdated = userRepository.save(userToUpdate);
        return userUpdated;
    }

    public User updateUserByEmail(String email, User user){
        var userToUpdate = userRepository.findByEmail(email);
        return updateUserById(userToUpdate.getId(), user);
    }

}
