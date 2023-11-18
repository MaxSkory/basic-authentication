package mskory.base.authentication.service.impl;

import lombok.RequiredArgsConstructor;
import mskory.base.authentication.dto.UserRegistrationRequestDto;
import mskory.base.authentication.dto.UserResponseDto;
import mskory.base.authentication.exception.RegistrationException;
import mskory.base.authentication.mapper.UserMapper;
import mskory.base.authentication.model.User;
import mskory.base.authentication.repository.UserRepository;
import mskory.base.authentication.service.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.email()).isPresent()) {
            throw new RegistrationException("There is user with email "
                    + requestDto.email() + " in the database");
        }
        User registeredUser = userRepository.save(userMapper.toModel(requestDto));
        return userMapper.toDto(registeredUser);
    }
}
