package com.team5devathon5.abledappbackend.accounts;

import io.swagger.v3.oas.annotations.tags.Tag;
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
public class AccountService {

    private final AccountRepository accountRepository;

    public Account create(Account account){
        var accountToCreate = account;
        LocalDateTime now = LocalDateTime.now();
        accountToCreate.setCreatedAt(now);
        accountToCreate.setUpdatedAt(now);
        accountRepository.save(accountToCreate);

        return accountToCreate;
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Account getById(Integer id){
        return accountRepository.findById(id).orElseThrow();
    }

    public Account getByEmail(String email){
        return accountRepository.findByEmail(email);
    }

    public void deleteById(Integer id){
        accountRepository.deleteById(id);
    }

    public void deleteByEmail(String email){
        var accountToDelete = accountRepository.findByEmail(email);
        accountRepository.deleteById(accountToDelete.getId());
    }

    public Account updateAccountById(Integer id, Account account){
        var accountToUpdate = accountRepository.findById(id).orElseThrow();
        //introducir la condición de que los get no sean nulos
        if (account.getName() != null) accountToUpdate.setName(account.getName());
        if (account.getLastName() != null) accountToUpdate.setLastName(account.getLastName());
        if (account.getEmail() != null) accountToUpdate.setEmail(account.getEmail());
        if (account.getAccountActive() != null) accountToUpdate.setAccountActive(account.getAccountActive());
        if (account.getDescription() != null) accountToUpdate.setDescription(account.getDescription());
        if (account.getImageLink() != null) accountToUpdate.setImageLink(account.getImageLink());
        if (account.getPhoneCode() != null) accountToUpdate.setPhoneCode(account.getPhoneCode());
        if (account.getPhoneNumber() != null) accountToUpdate.setPhoneNumber(account.getPhoneNumber());
        if (account.getSharePhone() != null) accountToUpdate.setSharePhone(account.getSharePhone());
        if (account.getUsername() != null) accountToUpdate.setUsername(account.getUsername());
        if (account.getCountry() != null) accountToUpdate.setCountry(account.getCountry());
        if (account.getCity() != null) accountToUpdate.setCity(account.getCity());
        if (account.getAddress() != null) accountToUpdate.setAddress(account.getAddress());
        if (account.getPostalCode() != null) accountToUpdate.setPostalCode(account.getPostalCode());
        if(account.getRole() != null) accountToUpdate.setRole(account.getRole());
        //El password deberá seguir otro mecanismo?
        if (account.getPassword() != null) accountToUpdate.setPassword(account.getPassword());
        accountToUpdate.setUpdatedAt(LocalDateTime.now());

        var accountUpdated = accountRepository.save(accountToUpdate);
        return accountUpdated;
    }

    public Account updateAccountByEmail(String email, Account account){
        var accountToUpdate = accountRepository.findByEmail(email);
        return updateAccountById(accountToUpdate.getId(), account);
    }

}
