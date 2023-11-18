package mskory.base.authentication.service;

import mskory.base.authentication.dto.UserRegistrationRequestDto;
import mskory.base.authentication.dto.UserResponseDto;
import mskory.base.authentication.exception.RegistrationException;

public interface AuthenticationService {

    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;
}
