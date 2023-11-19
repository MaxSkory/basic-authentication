package mskory.base.authentication.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mskory.base.authentication.dto.UserResponseDto;
import mskory.base.authentication.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll();
    }
}
