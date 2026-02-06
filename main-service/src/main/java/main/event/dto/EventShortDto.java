package main.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import main.category.dto.CategoryDto;
import main.user.dto.UserShortDto;

import java.time.LocalDateTime;

import static client.StatsClient.DATE_TIME_PATTERN;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Builder
public class EventShortDto {
    private Long id;

    private String description;

    private CategoryDto category;

    private Integer confirmedRequests;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_PATTERN)
    private LocalDateTime eventDate;

    private UserShortDto initiator;

    private Boolean paid;

    private String title;

    private Long views;
}
