package mskory.base.authentication.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mskory.base.authentication.model.User;
import mskory.base.authentication.repository.UserRepository;
import mskory.base.authentication.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
