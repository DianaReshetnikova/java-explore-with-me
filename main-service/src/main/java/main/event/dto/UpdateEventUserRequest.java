package main.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import main.event.domain.StateAction;

import java.time.LocalDateTime;

import static client.StatsClient.DATE_TIME_PATTERN;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEventUserRequest {
    @Size(max = 2000, min = 20, message = "Размер аннотации не совпадает с заданными пределами")
    private String annotation;

    private Long category;

    @Size(max = 7000, min = 20, message = "Размер описания не совпадает с заданными пределами")
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_PATTERN)
    private LocalDateTime eventDate;

    private LocationDto location;

    private Boolean paid;

    @Positive
    private Integer participantLimit;

    private Boolean requestModeration;

    private StateAction stateAction;

    @Size(max = 120, min = 3)
    private String title;
}
