package main.user.dto;

import lombok.*;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Builder
public class UserShortDto {
    private Long id;
    private String name;
}
