package mskory.base.authentication.mapper;

import mskory.base.authentication.config.MapperConfig;
import mskory.base.authentication.dto.UserRegistrationRequestDto;
import mskory.base.authentication.dto.UserResponseDto;
import mskory.base.authentication.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    User toModel(UserRegistrationRequestDto dto);

    UserResponseDto toDto(User user);
}
