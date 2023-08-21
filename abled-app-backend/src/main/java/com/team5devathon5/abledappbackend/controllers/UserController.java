package com.team5devathon5.abledappbackend.controllers;

import com.team5devathon5.abledappbackend.domain.User;
import com.team5devathon5.abledappbackend.infraestructure.exceptions.IdNotFoundException;
import com.team5devathon5.abledappbackend.infraestructure.exceptions.NoAuthorizedException;
import com.team5devathon5.abledappbackend.services.UserService;
import com.team5devathon5.abledappbackend.utilities.Tables;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Return a list with all users")
    public ResponseEntity<List<User>> showAllUsers(){
        var users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "return the specific user with this id from our list.")
    public ResponseEntity<User> showUserById(@PathVariable Integer id) {
        var userToShow = userService.getById(id);
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        var userAuthn = this.userService.getByEmail(userDetail.getUsername()).getId();
        if (id != userAuthn) throw new NoAuthorizedException(Tables.users.name());
        if (userToShow.isEmpty()) throw new IdNotFoundException();

        return ResponseEntity.ok(userToShow.get());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete the account with the specified id from our list.")
    public ResponseEntity deleteUserById(@PathVariable Integer id){
        var userToDelete = userService.getById(id);
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        var userAuthn = this.userService.getByEmail(userDetail.getUsername()).getId();
        if (id != userAuthn) throw new NoAuthorizedException(Tables.users.name());
        if (userToDelete.isEmpty()) throw new IdNotFoundException();

        deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "update the user with an specific id with the values introduced.")
    public ResponseEntity<User> updateUserById(@PathVariable Integer id, @Valid @RequestBody User user){
        var userToUpdate = userService.getById(id);
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        var userAuthn = this.userService.getByEmail(userDetail.getUsername()).getId();
        if (id != userAuthn) throw new NoAuthorizedException(Tables.users.name());
        if (userToUpdate.isEmpty()) throw new IdNotFoundException();

        userService.updateUserById(id, user);
        return ResponseEntity.noContent().build();
    }

}
