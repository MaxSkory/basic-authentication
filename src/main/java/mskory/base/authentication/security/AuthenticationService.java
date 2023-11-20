package mskory.base.authentication.security;

import mskory.base.authentication.dto.UserLoginRequestDto;
import mskory.base.authentication.dto.UserLoginResponseDto;
import mskory.base.authentication.dto.UserRegistrationRequestDto;
import mskory.base.authentication.dto.UserResponseDto;
import mskory.base.authentication.exception.RegistrationException;

public interface AuthenticationService {

    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;

    UserLoginResponseDto login(UserLoginRequestDto requestDto);
}
