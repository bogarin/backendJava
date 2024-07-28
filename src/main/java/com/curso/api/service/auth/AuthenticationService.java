package com.curso.api.service.auth;

import com.curso.api.dto.RegisteredUserDTO;
import com.curso.api.dto.SaveUserDTO;

import jakarta.validation.Valid;

public interface AuthenticationService {

    RegisteredUserDTO registerOneCustomer(@Valid SaveUserDTO newUser);

}
