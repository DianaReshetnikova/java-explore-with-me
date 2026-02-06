package main.user;

import main.user.dto.UserCreateDto;
import main.user.dto.UserFullDto;
import main.user.dto.UserShortDto;

public final class UserMapper {

    public static UserFullDto toUserFullDto(User user) {
        return UserFullDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public static UserShortDto toUserShortDto(User user) {
        return UserShortDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public static User toUser(UserCreateDto userCreateDto) {
        return User.builder()
                .id(null)
                .name(userCreateDto.getName())
                .email(userCreateDto.getEmail())
                .build();
    }
}