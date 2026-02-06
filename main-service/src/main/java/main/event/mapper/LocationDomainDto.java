package main.event.mapper;

import main.event.domain.Location;
import main.event.dto.LocationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationDomainDto {
    Location toDomain(LocationDto dto);

    LocationDto toDto(Location location);
}