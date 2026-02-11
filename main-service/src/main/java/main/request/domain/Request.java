package main.request.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.event.domain.Event;
import main.request.model.RequestStatus;
import main.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Request {
    private Long id;
    private LocalDateTime created;
    private Event event;
    private User requester;
    private RequestStatus status;
}