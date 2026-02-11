package main.event.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import main.event.domain.Event;
import main.event.dto.EventFullDto;
import main.event.dto.NewEventDto;
import main.event.dto.UpdateEventUserRequest;
import main.event.mapper.EventDomainDto;
import main.event.service.EventService;
import main.exception.ConditionsNotMetException;
import main.exception.NotFoundException;
import main.exception.ValidateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/events")
public class PrivateEventController {
    private final EventService service;
    private final EventDomainDto mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventFullDto postEvent(@PathVariable Long userId, @RequestBody @Valid NewEventDto dto) throws NotFoundException, ValidateException {
        Event event = service.postEvent(userId, mapper.toDomain(dto));
        return mapper.toDto(event);
    }

    @GetMapping
    public List<EventFullDto> getEventsByUserId(@PathVariable Long userId,
                                                @RequestParam(defaultValue = "0") Integer from,
                                                @RequestParam(defaultValue = "10") Integer size) throws NotFoundException {
        List<Event> events = service.getEventsByUserId(userId, from, size);

        return events.stream()
                .map(mapper::toDto)
                .toList();
    }

    @GetMapping("/{eventId}")
    public EventFullDto getEventById(@PathVariable Long userId, @PathVariable Long eventId) throws NotFoundException {
        Event event = service.getEventByIdAndOwnerId(userId, eventId);
        return mapper.toDto(event);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto patchEvent(@PathVariable Long userId, @PathVariable Long eventId, @RequestBody @Valid UpdateEventUserRequest dto) throws ConditionsNotMetException, NotFoundException, ValidateException {
        Event event = service.patchByUserEvent(userId, eventId, dto);
        return mapper.toDto(event);
    }
}
