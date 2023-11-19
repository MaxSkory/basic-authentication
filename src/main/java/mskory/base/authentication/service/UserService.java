package mskory.base.authentication.service;

import java.util.List;
import mskory.base.authentication.dto.UserResponseDto;

public interface UserService {

    List<UserResponseDto> getAll();
}
