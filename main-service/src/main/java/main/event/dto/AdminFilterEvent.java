package main.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import main.event.domain.EventState;

import java.time.LocalDateTime;
import java.util.List;

import static client.StatsClient.DATE_TIME_PATTERN;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminFilterEvent {
    private List<Long> users;

    private List<EventState> states;

    private List<Long> categories;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_PATTERN)
    private LocalDateTime rangeStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_PATTERN)
    private LocalDateTime rangeEnd;

    @Builder.Default
    private Integer from = 0;

    @Builder.Default
    private Integer size = 10;
}
