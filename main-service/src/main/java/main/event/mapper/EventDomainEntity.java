package main.event.mapper;

import main.category.Category;
import main.core.BaseDomainEntityMapper;
import main.event.domain.Event;
import main.event.model.EventEntity;
import main.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {User.class, LocationDomainEntity.class, Category.class})
public interface EventDomainEntity extends BaseDomainEntityMapper<Event, EventEntity> {
}
