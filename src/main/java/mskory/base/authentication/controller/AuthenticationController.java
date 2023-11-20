package mskory.base.authentication.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mskory.base.authentication.dto.UserLoginRequestDto;
import mskory.base.authentication.dto.UserLoginResponseDto;
import mskory.base.authentication.dto.UserRegistrationRequestDto;
import mskory.base.authentication.dto.UserResponseDto;
import mskory.base.authentication.exception.RegistrationException;
import mskory.base.authentication.security.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        return authenticationService.register(requestDto);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.login(requestDto);
    }
}
