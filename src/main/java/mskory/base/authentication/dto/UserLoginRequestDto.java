package mskory.base.authentication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginRequestDto(
        @NotBlank
        @Email(regexp = "^\\w+([\\.\\-\\+]?\\w*)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")
        String email,
        @NotBlank
        @Size(min = 5, max = 100)
        String password
) {
}
