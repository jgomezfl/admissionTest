package com.sprint3.admission_test.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class MedicationDto {

    @NotBlank
    @NotNull
    @Size(min=5, max=100)
    private String name;

    @NotBlank
    @NotNull
    @Size(min=30, max=255)
    private String description;

    @NotNull
    @Digits(integer = 12, fraction = 2)
    private BigDecimal price;

    @NotNull
    private LocalDate expiration_date;

    @NotBlank
    @NotNull
    @Size(min=3, max=50)
    private String category_name;

}
