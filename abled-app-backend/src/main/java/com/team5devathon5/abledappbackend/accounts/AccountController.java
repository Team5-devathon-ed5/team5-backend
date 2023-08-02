package com.team5devathon5.abledappbackend.accounts;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path="/api/v1/users")
@AllArgsConstructor
@Tag(name="Account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @Operation(summary = "Create a new account")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account){
        try{
            Account savedAccount = accountService.create(account);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedAccount.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }catch(DataIntegrityViolationException e){
            e.getMessage();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @Operation(summary = "Return a list with all accounts")
    public ResponseEntity<List<Account>> showAllAccounts(){
        var accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    @Operation(summary = "return the specific account with this id from our list.")
    public ResponseEntity<Account> showAccountById(@PathVariable Integer id){
        var accountToShow = accountService.getById(id);
        return ResponseEntity.ok(accountToShow);
    }

    @GetMapping("/mail")
    @Operation(summary = "return the specific account with an email introduced as a param, from our list.")
    public ResponseEntity<Account> showAccountByEmail(@RequestParam String email){
        var accountToShow = accountService.getByEmail(email);
        return ResponseEntity.ok(accountToShow);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete the account with the specified id from our list.")
    public ResponseEntity deleteAccountById(@PathVariable Integer id){
        accountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/mail")
    @Operation(summary = "delete the account with an email introduced as a param, from our list.")
    public ResponseEntity deleteAccountByEmail(@RequestParam String email){
        accountService.deleteByEmail(email);
        return ResponseEntity.noContent().build();
    }

    //status 200?
    @PutMapping("/{id}")
    @Operation(summary = "update the account with an specific id with the values introduced.")
    public ResponseEntity<Account> updateAccountById(@PathVariable Integer id, @Valid @RequestBody Account account){
        var accountToUpdate = accountService.updateAccountById(id, account);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/mail")
    @Operation(summary = "update the account with an specific email (introduced as a param) with the new values introduced.")
    public ResponseEntity<Account> updateAccountByEmail(@RequestParam String email, @Valid @RequestBody Account account){
        var accountToUpdate = accountService.updateAccountByEmail(email, account);
        return ResponseEntity.noContent().build();
    }
}
