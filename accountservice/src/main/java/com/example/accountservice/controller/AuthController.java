package com.example.accountservice.controller;


import com.example.accountservice.security.dto.AuthenticationRequest;
import com.example.accountservice.security.dto.SingUpRequest;
import com.example.accountservice.security.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {


    @Autowired
    private AuthenticationService authenticationService;



    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(
            @RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.login(authenticationRequest));
    }

    @PostMapping("/singup")
    public ResponseEntity<?> singUp(@RequestBody SingUpRequest request) {
        return ResponseEntity.ok(authenticationService.singup(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(
            HttpServletRequest request, HttpServletResponse response
    ) {
        return authenticationService.refreshToken(request);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logout successful");
    }
}
