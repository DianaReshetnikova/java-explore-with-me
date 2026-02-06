package main.event.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDto {
    @NotNull
    private Float lat;

    @NotNull
    private Float lon;
}

