package com.example.accountservice.security.service;

import com.example.accountservice.exception.EmailExistsException;
import com.example.accountservice.model.Account;
import com.example.accountservice.model.Role;
import com.example.accountservice.repository.AccountRepository;
import com.example.accountservice.security.dto.AuthenticationRequest;
import com.example.accountservice.security.dto.AuthenticationResponse;
import com.example.accountservice.security.dto.SingUpRequest;
import com.example.accountservice.security.utils.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AccountRepository repo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.createToken(authentication);
        String refreshToken = jwtTokenProvider.refreshToken(authentication);
        return (new AuthenticationResponse(jwt,refreshToken));
    }


    public AuthenticationResponse singup(SingUpRequest request) {
        try {
            if(repo.findByEmail(request.getEmail()) != null)
                throw new EmailExistsException("Email exists");
            var user = Account.builder().name(request.getName()).surname(request.getSurname()).city(request.getCity())
                    .address(request.getAddress()).age(request.getAge()).username(request.getUsername()).email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();
            repo.save(user);
            String jwt = jwtTokenProvider.Token(user);
            //String refreshJwt = jwtTokenProvider.refreshToken(user);
            return AuthenticationResponse.builder()
                    .accessToken(jwt).build();
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        // Get the JWT token from the Authorization header
        String token = jwtTokenProvider.resolveToken(request);

        if (token != null && jwtTokenProvider.validateToken(token)) {

            // Get the user details from the token
            UserDetails userDetails = userDetailsService.loadUserByUsername(
                    jwtTokenProvider.getUsername(token));

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.createToken(authentication);
            String refreshToken = jwtTokenProvider.refreshToken(authentication);
            return ResponseEntity.ok(new AuthenticationResponse(jwt,refreshToken));
        }
        else
            return (ResponseEntity<?>) ResponseEntity.badRequest();
    }

}
