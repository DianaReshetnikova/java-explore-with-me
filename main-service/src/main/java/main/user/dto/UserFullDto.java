package main.user.dto;

import lombok.*;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserFullDto {
    private Long id;
    private String name;
    private String email;
}
