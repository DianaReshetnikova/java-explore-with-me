package server.service;

import dto.HitDto;
import dto.StatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatService {
    void postHit(HitDto hitDto);

    List<StatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique);
}
