package main.event.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.category.Category;
import main.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Event {
    private Long id;
    private String annotation;
    private Category category;
    private Integer confirmedRequests = 0;
    private LocalDateTime createdOn;
    private String description;
    private LocalDateTime eventDate;
    private User initiator;
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private LocalDateTime publishedOn;
    private Boolean requestModeration;
    private EventState state;
    private String title;
    private Long views = 0L;
}
