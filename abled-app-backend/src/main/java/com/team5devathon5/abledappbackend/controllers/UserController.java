package com.team5devathon5.abledappbackend.controllers;

import com.team5devathon5.abledappbackend.domain.User;
import com.team5devathon5.abledappbackend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(path="/api/v1/users")
@AllArgsConstructor
@Tag(name="User")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Return a list with all users")
    public ResponseEntity<List<User>> showAllUsers(){
        var accounts = userService.getAllUser();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    @Operation(summary = "return the specific user with this id from our list.")
    public ResponseEntity<User> showUserById(@PathVariable Integer id){
        var userToShow = userService.getById(id);
        if (userToShow == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userToShow);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete the account with the specified id from our list.")
    public ResponseEntity deleteUserById(@PathVariable Integer id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "update the user with an specific id with the values introduced.")
    public ResponseEntity<User> updateUserById(@PathVariable Integer id, @Valid @RequestBody User user){
        var accountToUpdate = userService.updateUserById(id, user);
        return ResponseEntity.noContent().build();
    }

}
