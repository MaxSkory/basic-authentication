package mskory.base.authentication.security;

import lombok.RequiredArgsConstructor;
import mskory.base.authentication.dto.UserLoginRequestDto;
import mskory.base.authentication.dto.UserLoginResponseDto;
import mskory.base.authentication.dto.UserRegistrationRequestDto;
import mskory.base.authentication.dto.UserResponseDto;
import mskory.base.authentication.exception.RegistrationException;
import mskory.base.authentication.mapper.UserMapper;
import mskory.base.authentication.model.User;
import mskory.base.authentication.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.email()).isPresent()) {
            throw new RegistrationException("There is user with email "
                    + requestDto.email() + " in the database");
        }
        User mappedUser = userMapper.toModel(requestDto);
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        User registeredUser = userRepository.save(mappedUser);
        return userMapper.toDto(registeredUser);
    }

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto requestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.email(), requestDto.password()));
        return new UserLoginResponseDto(jwtUtil.generateToken(authentication.getName()));
    }
}
