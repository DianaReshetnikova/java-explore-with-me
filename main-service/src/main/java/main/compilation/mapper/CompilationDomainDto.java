package main.compilation.mapper;

import main.compilation.domain.Compilation;
import main.compilation.dto.CompilationDto;
import main.compilation.dto.NewCompilationDto;
import main.event.domain.Event;
import main.event.mapper.EventDomainDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = EventDomainDto.class)
public interface CompilationDomainDto {
    @Mapping(target = "events", expression = "java(mapEvents(dto.getEvents()))")
    Compilation toDomain(NewCompilationDto dto);

    CompilationDto toDto(Compilation compilation);

    default List<Event> mapEvents(Set<Long> events) {
        if (events == null) return List.of();
        return events.stream()
                .map(id -> {
                    Event event = new Event();
                    event.setId(id);
                    return event;
                })
                .toList();
    }
}
