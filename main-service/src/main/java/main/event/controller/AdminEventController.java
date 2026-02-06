package main.event.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import main.event.domain.Event;
import main.event.dto.AdminFilterEvent;
import main.event.dto.EventFullDto;
import main.event.dto.UpdateEventAdminRequest;
import main.event.mapper.EventDomainDto;
import main.event.service.EventService;
import main.exception.ConditionsNotMetException;
import main.exception.NotFoundException;
import main.exception.ValidateException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
public class AdminEventController {
    private final EventService service;
    private final EventDomainDto mapper;

    @GetMapping
    public List<EventFullDto> getEventsForAdmin(AdminFilterEvent filter) {
        List<Event> events = service.getEventsByAdminFilter(filter);
        return events.stream()
                .map(mapper::toDto)
                .toList();
    }

    @PatchMapping("/{eventId}")
    public EventFullDto patchEvent(@Valid @RequestBody UpdateEventAdminRequest update, @PathVariable Long eventId) throws ConditionsNotMetException, NotFoundException, ValidateException {
        Event event = service.patchByAdminEvent(update, eventId);
        return mapper.toDto(event);
    }
}
