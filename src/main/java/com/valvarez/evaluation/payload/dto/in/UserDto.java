package com.valvarez.evaluation.payload.dto.in;

import com.valvarez.evaluation.payload.dto.PhoneDto;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {
    @NotNull()
    @Size(min = 4)
    @Size(max = 50)
    private String name;

    @NotNull()
    @Size(min = 4)
    @Size(max = 50)
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "debe ser una dirección de correo electrónico con formato correcto"
    )
    private String email;

    @NotNull()
    @Size(min = 8,max = 12)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
            message = "La contraseña debe contener al menos una letra minúscula, una letra mayúscula y un número."
    )
    private String password;

    @NotEmpty
    private List<PhoneDto> phones;
}