package main.event.mapper;

import main.event.dto.LocationDto;
import main.event.model.LocationEntity;

public final class LocationMapper {
    public static LocationDto toDto(LocationEntity location) {
        return LocationDto.builder()
                .lat(location.getLat())
                .lon(location.getLon())
                .build();
    }
}
