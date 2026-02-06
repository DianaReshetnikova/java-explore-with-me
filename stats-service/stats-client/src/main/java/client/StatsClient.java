package client;

import dto.HitDto;
import dto.StatsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class StatsClient {
    private final RestClient restClient;
    private final String statsUrl;
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //http://localhost:9090
    public StatsClient(RestClient restClient, @Value("${stats-server.url}") String statsUrl) {
        this.restClient = restClient;
        this.statsUrl = statsUrl;
    }


    //Сохранение информации о том, что на uri конкретного сервиса был отправлен запрос пользователем.
    // Название сервиса, uri и ip пользователя указаны в теле запроса.
    public void post(HitDto dto) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(statsUrl + "/hit")
                .build()
                .toUri();
        restClient
                .post()
                .uri(uri)
                .body(dto)
                .retrieve()
                .toBodilessEntity();
    }

    //получение статистики по посещениям
    //Дата и время начала и конца диапазона за который нужно выгрузить статистику (в формате "yyyy-MM-dd HH:mm:ss")
    //Список uri для которых нужно выгрузить статистику
    //Нужно ли учитывать только уникальные посещения (только с уникальным ip)
    public List<StatsDto> get(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(statsUrl + "/stats")
                .queryParam("start", start.format(DATE_TIME_FORMATTER))
                .queryParam("end", end.format(DATE_TIME_FORMATTER))
                .queryParam("uris", uris.toArray())
                .queryParam("unique", unique)
                .build()
                .toUri();
        return restClient
                .get()
                .uri(uri)
                .retrieve()
                .body(new ParameterizedTypeReference<List<StatsDto>>() {
                });
    }
}
