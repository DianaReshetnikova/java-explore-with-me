package server.mapper;

import dto.HitDto;
import dto.StatsDto;
import server.model.Hit;
import server.model.Stats;

public final class StatMapper {
    public static Hit toHit(HitDto dto) {
        return Hit.builder()
                .id(null)
                .app(dto.getApp())
                .ip(dto.getIp())
                .uri(dto.getUri())
                .timestamp(dto.getTimestamp())
                .build();
    }

    public static StatsDto toStatsDto(Stats stat) {
        return StatsDto.builder()
                .hits(stat.getHits())
                .app(stat.getApp())
                .uri(stat.getUri())
                .build();
    }
}
