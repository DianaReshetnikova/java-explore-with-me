package main.event.mapper;

import main.category.CategoryDomainDto;
import main.event.domain.Event;
import main.event.dto.EventFullDto;
import main.event.dto.EventShortDto;
import main.event.dto.NewEventDto;
import main.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;


@Mapper(componentModel = "spring", uses = {CategoryDomainDto.class, User.class, LocationDomainDto.class})
public interface EventDomainDto {

    @Mapping(target = "category", source = "category")
    @Mapping(target = "createdOn", expression = "java(mapTime())")
    @Mapping(target = "state", constant = "PENDING")
    Event toDomain(NewEventDto dto);

    EventFullDto toDto(Event event);

    EventShortDto toShortDto(Event event);

    default LocalDateTime mapTime() {
        return LocalDateTime.now();
    }
}
