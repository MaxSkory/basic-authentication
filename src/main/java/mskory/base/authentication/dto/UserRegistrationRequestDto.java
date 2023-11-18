package mskory.base.authentication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import mskory.base.authentication.validation.FieldMatch;

@FieldMatch(fieldName = "password", fieldMatchName = "repeatedPassword")
public record UserRegistrationRequestDto(
        @NotBlank
        @Email(regexp = "^\\w+([\\.\\-\\+]?\\w*)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")
        String email,
        @NotBlank
        @Size(min = 5, max = 100)
        String password,
        @NotBlank
        @Size(min = 5, max = 100)
        String repeatedPassword,
        @NotBlank
        @Pattern(regexp = "[a-zA-Z]{3,}")
        String firstName,
        @NotBlank
        @Pattern(regexp = "[a-zA-Z]{3,}")
        String lastName,
        String shippingAddress
) {
}
