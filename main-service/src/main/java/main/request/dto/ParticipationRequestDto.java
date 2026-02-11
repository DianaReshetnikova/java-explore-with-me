package main.request.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.request.model.RequestStatus;

import java.time.LocalDateTime;

import static client.StatsClient.DATE_TIME_PATTERN;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipationRequestDto {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_PATTERN)
    private LocalDateTime created;
    private Long event;
    private Long requester;
    private RequestStatus status;
}
