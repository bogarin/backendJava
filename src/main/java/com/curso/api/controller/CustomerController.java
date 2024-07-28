package com.curso.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.api.dto.RegisteredUserDTO;
import com.curso.api.dto.SaveUserDTO;
import com.curso.api.service.auth.AuthenticationService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<RegisteredUserDTO> postMethodName(@RequestBody @Valid SaveUserDTO newUser) {
        
        RegisteredUserDTO registeredUser= authenticationService.registerOneCustomer(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
    
}
