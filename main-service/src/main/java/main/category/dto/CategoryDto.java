package main.category.dto;

import lombok.*;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Builder
@ToString
public class CategoryDto {
    private Long id;
    private String name;
}