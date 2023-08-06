package com.team5devathon5.abledappbackend.Filter;

import com.team5devathon5.abledappbackend.Service.TokenService;
import com.team5devathon5.abledappbackend.accounts.Account;
import com.team5devathon5.abledappbackend.accounts.AccountRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final AccountRepository accountRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var authHeader =  request.getHeader("Authorization");

        if(authHeader !=null) {

            var token = authHeader.replace("Bearer ", "");
            var subjectUser = tokenService.getSubject(token);

            if (subjectUser != null) {
                var account = accountRepository.findByEmail(subjectUser);
                var authentication = new UsernamePasswordAuthenticationToken(account, null, account.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
