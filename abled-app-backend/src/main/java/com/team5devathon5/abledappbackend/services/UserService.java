package com.team5devathon5.abledappbackend.services;

import com.team5devathon5.abledappbackend.domain.User;
import com.team5devathon5.abledappbackend.domain.UserRepository;
import com.team5devathon5.abledappbackend.infraestructure.exceptions.BadRequestException;
import com.team5devathon5.abledappbackend.infraestructure.exceptions.IdNotFoundException;
import com.team5devathon5.abledappbackend.utilities.Tables;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public User create(User user){
        var userToCreate = user;
        LocalDateTime now = LocalDateTime.now();
        userToCreate.setCreatedAt(now);
        userToCreate.setUpdatedAt(now);
        userRepository.save(userToCreate);

        return userToCreate;
    }

    public List<User> getAllUser(){

        return userRepository.findAll();
    }

    public Optional<User> getById(Integer id) {

        return userRepository.findById(id);
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

    public User updateUserById(Integer id, User user){
        var userToUpdate = userRepository.findById(id).orElseThrow(() -> new IdNotFoundException());
        if (user.getName() != null) userToUpdate.setName(user.getName());
        if (user.getEmail() != null) userToUpdate.setEmail(user.getEmail());
        if (user.getUserActive() != null) userToUpdate.setUserActive(user.getUserActive());
        if (user.getDetail() != null) userToUpdate.setDetail(user.getDetail());
        if (user.getImageUrl() != null) userToUpdate.setImageUrl(user.getImageUrl());
        if (user.getPhoneNumber() != null) userToUpdate.setPhoneNumber(user.getPhoneNumber());
        if (user.getPhoneCode() != null) userToUpdate.setPhoneCode(user.getPhoneCode());
        if (user.getPhoneShare() != null) userToUpdate.setPhoneShare(user.getPhoneShare());
        if (user.getCountry() != null) userToUpdate.setCountry(user.getCountry());
        if (user.getAddress() != null) userToUpdate.setAddress(user.getAddress());
        if(user.getRole() != null) userToUpdate.setRole(user.getRole());

        if (user.getPassword() != null) userToUpdate.setPassword(user.getPassword());
        userToUpdate.setUpdatedAt(LocalDateTime.now());

        var userUpdated = userRepository.save(userToUpdate);
        return userUpdated;

    }

    public User updateUserByEmail(String email, User user){
        var userToUpdate = userRepository.findByEmail(email);
        return updateUserById(userToUpdate.getId(), user);
    }

}
