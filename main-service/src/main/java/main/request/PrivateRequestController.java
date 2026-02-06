package main.request;

import lombok.RequiredArgsConstructor;
import main.exception.ConditionsNotMetException;
import main.exception.NotFoundException;
import main.request.domain.Request;
import main.request.dto.ParticipationRequestDto;
import main.request.mapper.RequestDomainDto;
import main.request.service.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/requests")
public class PrivateRequestController {
    private final RequestService service;
    private final RequestDomainDto mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto postRequest(@PathVariable Long userId, @RequestParam Long eventId) throws ConditionsNotMetException, NotFoundException {
        Request request = service.postRequest(mapper.toNewRequest(userId, eventId));
        return mapper.toDto(request);
    }

    @GetMapping
    public List<ParticipationRequestDto> getRequestByUserId(@PathVariable Long userId) throws NotFoundException {
        List<Request> requests = service.getRequestsByUserId(userId);
        return requests.stream()
                .map(mapper::toDto)
                .toList();
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto canselRequest(@PathVariable Long userId, @PathVariable Long requestId) throws ConditionsNotMetException, NotFoundException {
        Request request = service.cancelRequest(userId, requestId);
        return mapper.toDto(request);
    }
}
