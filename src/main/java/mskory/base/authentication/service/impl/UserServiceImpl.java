package mskory.base.authentication.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mskory.base.authentication.dto.UserResponseDto;
import mskory.base.authentication.mapper.UserMapper;
import mskory.base.authentication.repository.UserRepository;
import mskory.base.authentication.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }
}
