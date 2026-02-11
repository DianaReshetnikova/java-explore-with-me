package main.user;

import main.user.dto.UserCreateDto;
import main.user.dto.UserFullDto;
import main.user.dto.UserShortDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDomainDto {

    User toDomain(UserCreateDto dto);

    UserFullDto toDto(User user);

    UserShortDto toShortDto(User user);
}
