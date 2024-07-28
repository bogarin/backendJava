package com.curso.api.service;

import com.curso.api.dto.SaveUserDTO;
import com.curso.api.persistence.entities.UserEntity;

public interface UserService {

    UserEntity registerOneCustomer(SaveUserDTO newUser);

}
