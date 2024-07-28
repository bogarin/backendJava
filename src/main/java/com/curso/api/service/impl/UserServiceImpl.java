package com.curso.api.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.curso.api.dto.SaveUserDTO;
import com.curso.api.exception.InvalidPasswordException;
import com.curso.api.persistence.entities.UserEntity;
import com.curso.api.persistence.repositories.UserRepository;
import com.curso.api.service.UserService;
import com.curso.api.utils.Role;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity registerOneCustomer(SaveUserDTO newUser) {
        validatePassword(newUser);
        UserEntity user = UserEntity.builder()
                .name(newUser.getName())
                .username(newUser.getUsername())
                .password(passwordEncoder.encode(newUser.getPassword()))
                .role(Role.ROLE_CUSTOMER)
                .build();
        return userRepository.save(user);
    }

    private void validatePassword(SaveUserDTO newUser) {
        if (!StringUtils.hasText(newUser.getPassword()) || !StringUtils.hasText(newUser.getRepeatedPassword())) {
            throw new InvalidPasswordException("password don't match");
        }
        if (!newUser.getPassword().equals(newUser.getRepeatedPassword())) {
            throw new InvalidPasswordException("password don't match");
        }

    }

}
