package com.curso.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductRestBodyDTO implements Serializable {

    @NotBlank
    private String name;
    @DecimalMin(value = "0.01")
    private BigDecimal price;
    @Min(value = 1)
    private Long categoryId;
}
