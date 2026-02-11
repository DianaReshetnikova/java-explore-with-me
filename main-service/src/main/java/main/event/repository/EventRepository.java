package main.event.repository;

import main.core.BaseStorage;
import main.event.domain.Event;
import main.event.dto.AdminFilterEvent;
import main.event.dto.PublicEventFilter;

import java.util.List;

public interface EventRepository extends BaseStorage<Long, Event> {
    List<Event> getEventsByUserAndFilter(Long userId, Integer from, Integer size);

    List<Event> findAll(List<Long> ids);

    List<Event> getEventsByPublicFilter(PublicEventFilter filter);

    boolean existsByCategoryId(Long catId);

    List<Event> getEventsByAdminFilter(AdminFilterEvent filterEvent);
}
