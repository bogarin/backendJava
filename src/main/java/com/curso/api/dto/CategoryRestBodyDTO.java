package com.curso.api.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryRestBodyDTO implements Serializable{
    @NotBlank
    private String name;
}
