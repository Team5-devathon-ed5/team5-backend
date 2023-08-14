package com.team5devathon5.abledappbackend.Controller;

import com.team5devathon5.abledappbackend.accounts.Users;
import com.team5devathon5.abledappbackend.Service.UserService;
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
@Tag(name="Users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Return a list with all users")
    public ResponseEntity<List<Users>> showAllUsers(){
        var accounts = userService.getAllUser();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    @Operation(summary = "return the specific user with this id from our list.")
    public ResponseEntity<Users> showUserById(@PathVariable Integer id){
        var userToShow = userService.getById(id);
        return ResponseEntity.ok(userToShow);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete the account with the specified id from our list.")
    public ResponseEntity deleteUserById(@PathVariable Integer id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //status 200?
    @PatchMapping("/{id}")
    @Operation(summary = "update the user with an specific id with the values introduced.")
    public ResponseEntity<Users> updateUserById(@PathVariable Integer id, @Valid @RequestBody Users user){
        var accountToUpdate = userService.updateUserById(id, user);
        return ResponseEntity.noContent().build();
    }
}
