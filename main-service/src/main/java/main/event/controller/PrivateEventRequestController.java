package main.event.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import main.event.dto.EventRequestStatusUpdateRequest;
import main.event.dto.EventRequestStatusUpdateResult;
import main.exception.ConditionsNotMetException;
import main.exception.NotFoundException;
import main.request.domain.Request;
import main.request.dto.ParticipationRequestDto;
import main.request.mapper.RequestDomainDto;
import main.request.model.RequestStatus;
import main.request.service.RequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/events/{eventId}/requests")
public class PrivateEventRequestController {
    private final RequestService service;
    private final RequestDomainDto mapper;

    @GetMapping
    public List<ParticipationRequestDto> getRequestsByOwnerIdInEvent(@PathVariable Long userId,
                                                                     @PathVariable Long eventId) throws NotFoundException {
        List<Request> requests = service.getRequestsByEventId(userId, eventId);
        return requests.stream()
                .map(mapper::toDto)
                .toList();
    }

    @PatchMapping
    public EventRequestStatusUpdateResult patchRequest(@PathVariable Long userId, @PathVariable Long eventId,
                                                       @RequestBody @Valid EventRequestStatusUpdateRequest updateRequest) throws ConditionsNotMetException, NotFoundException, ConditionsNotMetException {
        List<Request> requests = service.patchRequests(userId, eventId, updateRequest);

        List<ParticipationRequestDto> confirmed = requests.stream()
                .filter(r -> r.getStatus().equals(RequestStatus.CONFIRMED))
                .map(mapper::toDto)
                .toList();

        List<ParticipationRequestDto> rejected = requests.stream()
                .filter(r -> r.getStatus().equals(RequestStatus.REJECTED))
                .map(mapper::toDto)
                .toList();
        return new EventRequestStatusUpdateResult(confirmed, rejected);
    }
}