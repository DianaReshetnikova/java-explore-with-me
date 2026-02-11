package main.compilation.mapper;

import main.compilation.domain.Compilation;
import main.compilation.model.CompilationEntity;
import main.core.BaseDomainEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompilationDomainEntity extends BaseDomainEntityMapper<Compilation, CompilationEntity> {
}