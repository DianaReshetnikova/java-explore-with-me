package main.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

import static client.StatsClient.DATE_TIME_PATTERN;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class NewEventDto {
    @Size(min = 20, max = 2000, message = "Размер аннотации не совпадает с заданными пределами")
    @NotBlank
    private String annotation;

    @NotNull
    private Long category;

    @Size(min = 20, max = 7000)
    @NotBlank
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_PATTERN)
    @NotNull
    private LocalDateTime eventDate;

    @NotNull
    private LocationDto location;

    private Boolean paid = false;

    @PositiveOrZero
    private Integer participantLimit = 0;

    private Boolean requestModeration = true;

    @NotBlank
    @Size(max = 120, min = 3, message = "Размер заголовка не совпадает с заданными пределами")
    private String title;
}