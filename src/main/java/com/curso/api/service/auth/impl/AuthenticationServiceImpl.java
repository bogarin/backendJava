package com.curso.api.service.auth.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.curso.api.dto.RegisteredUserDTO;
import com.curso.api.dto.SaveUserDTO;
import com.curso.api.persistence.entities.UserEntity;
import com.curso.api.service.UserService;
import com.curso.api.service.auth.AuthenticationService;
import com.curso.api.service.auth.JwtService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public RegisteredUserDTO registerOneCustomer(@Valid SaveUserDTO newUser) {
        UserEntity user = userService.registerOneCustomer(newUser);
        String jwt=jwtService.generateToken(user,generateExtraClaims(user));
        return RegisteredUserDTO
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .role(user.getRole().name())
                .jwt(jwt)
                .build();

    }

    
    private Map<String, Object> generateExtraClaims(UserEntity user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name",user.getName());
        extraClaims.put("role",user.getRole().name());
        extraClaims.put("authorities",user.getAuthorities());

        return extraClaims;
    }

}
