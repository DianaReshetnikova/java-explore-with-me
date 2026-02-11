package main.request.mapper;

import main.core.BaseDomainEntityMapper;
import main.request.domain.Request;
import main.request.model.RequestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestDomainEntity extends BaseDomainEntityMapper<Request, RequestEntity> {
}
