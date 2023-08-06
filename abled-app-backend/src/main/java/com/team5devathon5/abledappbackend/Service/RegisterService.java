package com.team5devathon5.abledappbackend.Service;

import com.team5devathon5.abledappbackend.User.DataNewAccount;
import com.team5devathon5.abledappbackend.accounts.Account;
import com.team5devathon5.abledappbackend.accounts.AccountRepository;
import com.team5devathon5.abledappbackend.accounts.Role;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegisterService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public void registerAccount(DataNewAccount dataNewAccount){

        PasswordEncoder passwordBCrypt= new BCryptPasswordEncoder();
        String accountHashedPassword = passwordBCrypt.encode(dataNewAccount.password());

        Account newAccount =  new Account(null, dataNewAccount.name(), dataNewAccount.last_name(),null,null,
                                dataNewAccount.email(),null,null, LocalDateTime.now(),null, accountHashedPassword,
                                dataNewAccount.country(),null,null,null,null,null);
        newAccount.setRole(Role.LODGER);

        if (accountRepository.findByEmail(dataNewAccount.email()) != null) {
            throw new DataIntegrityViolationException("email exist");
        }else {
            accountRepository.save(newAccount);
        }
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepository.findByEmail(email);
    }
}
