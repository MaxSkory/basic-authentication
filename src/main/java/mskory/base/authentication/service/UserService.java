package mskory.base.authentication.service;

import mskory.base.authentication.dto.UserRegistrationRequestDto;
import mskory.base.authentication.model.User;

public interface UserService {

    User save(UserRegistrationRequestDto requestDto);
}
