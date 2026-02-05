package server.service;

import dto.HitDto;
import dto.StatsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.exception.ValidationException;
import server.mapper.StatMapper;
import server.model.Stats;
import server.repository.StatRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StatServiceImpl implements StatService {
    private final StatRepository statRepository;

    @Override
    @Transactional(readOnly = false)
    public void postHit(HitDto hitDto) {
        statRepository.save(StatMapper.toHit(hitDto));
    }

    @Override
    public List<StatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        if ((start == null) || (end == null) || (end.isBefore(start))) {
            throw new ValidationException("Некорректные параметры start = " + start + " и end = " + end);
        }

        List<Stats> statsDto;
        if (unique) {
            statsDto = statRepository.findAllStatsUniqueIp(start, end, uris);
        } else {
            statsDto = statRepository.findAllStatsNotUniqueIp(start, end, uris);
        }
        return statsDto.stream()
                .map(StatMapper::toStatsDto)
                .sorted((stat1, stat2) -> Long.compare(stat2.getHits(), stat1.getHits()))
                .collect(Collectors.toList());
    }
}
