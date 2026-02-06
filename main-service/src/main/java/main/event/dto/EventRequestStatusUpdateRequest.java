package main.event.dto;

import lombok.*;
import main.event.domain.EventRequestStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventRequestStatusUpdateRequest {
    private List<Long> requestIds;
    private EventRequestStatus status;
}
