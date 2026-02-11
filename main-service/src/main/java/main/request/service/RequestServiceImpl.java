package main.request.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import main.event.domain.Event;
import main.event.dto.EventRequestStatusUpdateRequest;
import main.event.domain.EventRequestStatus;
import main.event.domain.EventState;
import main.event.service.EventService;
import main.exception.ConditionsNotMetException;
import main.exception.NotFoundException;
import main.request.repository.RequestRepository;
import main.request.domain.Request;
import main.request.model.RequestStatus;
import main.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository storage;
    private final UserService userService;
    private final EventService eventService;

    @Override
    @Transactional
    public Request postRequest(Request request) throws NotFoundException, ConditionsNotMetException {
        Event event = eventService.getEventById(request.getEvent().getId());
        Optional<Request> check = getByRequesterIdAndEventId(request.getRequester().getId(), event.getId());
        userService.findById(request.getRequester().getId());

        if (check.isPresent()) {
            throw new ConditionsNotMetException("request already exist");
        }

        userService.findById(request.getRequester().getId());

        if (event.getInitiator().getId().equals(request.getRequester().getId())) {
            throw new ConditionsNotMetException("the initiator cannot create a request for his event");
        }
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new ConditionsNotMetException("user can not participate in unpublished event");
        }
        Integer confirmed = event.getConfirmedRequests() == null ? 0 : event.getConfirmedRequests();

        if (event.getParticipantLimit() != 0 && confirmed >= event.getParticipantLimit()) {
            throw new ConditionsNotMetException("event is already full");
        }
        if (!event.getRequestModeration() || event.getParticipantLimit() == 0) {
            request.setStatus(RequestStatus.CONFIRMED);
            event.setConfirmedRequests(confirmed + 1);
            eventService.saveEvent(event);
        }

        return storage.save(request);
    }

    @Override
    public List<Request> getRequestsByUserId(Long userId) throws NotFoundException {
        userService.findById(userId);

        return storage.getRequestsByUserId(userId);
    }

    @Override
    @Transactional
    public Request cancelRequest(Long userId, Long requestId) throws NotFoundException, ConditionsNotMetException {
        userService.findById(userId);

        Request request = storage.getById(requestId).orElseThrow(
                () -> new NotFoundException("request with id " + requestId + "does not exist")
        );
        if (!request.getRequester().getId().equals(userId)) {
            throw new ConditionsNotMetException("cancel request can only owner request's");
        }
        request.setStatus(RequestStatus.CANCELED);
        return storage.save(request);
    }

    @Override
    public List<Request> getRequestsByEventId(Long userId, Long eventId) throws NotFoundException {
        Event event = eventService.getEventById(eventId);
        if (!event.getInitiator().getId().equals(userId)) {
            throw new IllegalArgumentException("event does not belong to user");
        }
        return storage.getRequestsByEventId(eventId);
    }

    @Override
    @Transactional
    public List<Request> patchRequests(Long userId, Long eventId, EventRequestStatusUpdateRequest updateRequest) throws NotFoundException, ConditionsNotMetException {
        Event event = eventService.getEventById(eventId);
        Integer confirmed = event.getConfirmedRequests() == null ? 0 : event.getConfirmedRequests();

        if (!event.getInitiator().getId().equals(userId)) {
            throw new IllegalArgumentException("event does not belong to user");
        }
        if (!event.getRequestModeration() || event.getParticipantLimit() == 0) {
            return storage.getRequestsByIds(updateRequest.getRequestIds());
        }
        if (confirmed.equals(event.getParticipantLimit())) {
            throw new ConditionsNotMetException("event has already limit participant");
        }
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new ConditionsNotMetException("event  must have status PUBLISHED ");
        }
        List<Request> requests = storage.getRequestsByIds(updateRequest.getRequestIds());
        for (Request request : requests) {
            if (!request.getEvent().getId().equals(eventId)) {
                throw new IllegalArgumentException("request with id " + request.getId() + "dose not belong to event");
            }
            if (request.getStatus() != RequestStatus.PENDING) {
                throw new ConditionsNotMetException("Only pending requests can be updated");
            }
            if (confirmed >= event.getParticipantLimit()) {
                request.setStatus(RequestStatus.REJECTED);
                continue;
            }
            if (updateRequest.getStatus().equals(EventRequestStatus.CONFIRMED)) {
                request.setStatus(RequestStatus.CONFIRMED);
                confirmed++;
                event.setConfirmedRequests(confirmed);
            } else if (updateRequest.getStatus().equals(EventRequestStatus.REJECTED)) {
                request.setStatus(RequestStatus.REJECTED);
            }

        }
        eventService.saveEvent(event);
        storage.saveAll(requests);
        return requests;
    }

    @Override
    public Optional<Request> getByRequesterIdAndEventId(Long requesterId, Long eventId) {
        return storage.getByRequesterIdAndEventId(requesterId, eventId);
    }
}
