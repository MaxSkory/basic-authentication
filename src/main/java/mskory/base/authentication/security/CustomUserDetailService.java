package mskory.base.authentication.security;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import mskory.base.authentication.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(u -> new User(u.getEmail(), u.getPassword(), Collections.emptyList()))
                .orElseThrow(() -> new UsernameNotFoundException("Can't find user by username "
                        + username));
    }
}
