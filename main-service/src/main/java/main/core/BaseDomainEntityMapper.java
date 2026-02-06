package main.core;

public interface BaseDomainEntityMapper<D, E> {
    E toEntity(D domain);

    D toDomain(E entity);
}
