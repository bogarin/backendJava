package com.curso.api.dto;

import jakarta.validation.constraints.Size;
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
public class SaveUserDTO {

    @Size(min = 4)
    private String username;
    @Size(min = 4)
    private String name;
    @Size(min = 8)
    private String password;
    @Size(min = 8)
    private String repeatedPassword;
}
