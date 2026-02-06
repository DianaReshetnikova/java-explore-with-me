package main.event.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

import static client.StatsClient.DATE_TIME_PATTERN;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicEventFilter {
    private String text;

    private List<Long> categories;

    private Boolean paid;

    @DateTimeFormat(pattern = DATE_TIME_PATTERN)
    private LocalDateTime rangeStart;

    @DateTimeFormat(pattern = DATE_TIME_PATTERN)
    private LocalDateTime rangeEnd;

    @Builder.Default
    private Boolean onlyAvailable = false;

    private String sort;

    @Builder.Default
    private Integer from = 0;

    @Builder.Default
    private Integer size = 10;
}
