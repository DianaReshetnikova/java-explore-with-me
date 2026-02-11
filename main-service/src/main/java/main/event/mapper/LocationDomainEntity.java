package main.event.mapper;

import main.core.BaseDomainEntityMapper;
import main.event.domain.Location;
import main.event.model.LocationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationDomainEntity extends BaseDomainEntityMapper<Location, LocationEntity> {
}
