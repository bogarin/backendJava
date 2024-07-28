package com.curso.api.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class RegisteredUserDTO implements Serializable{


    private Long id;
    private String username;
    private String name;
    private String role;
    private String jwt;
}
