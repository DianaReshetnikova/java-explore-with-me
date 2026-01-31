package server;

import dto.HitDto;
import dto.StatsDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.exception.ValidationException;
import server.service.StatService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class StatsController {
    private final StatService statService;
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @PostMapping(path = "/hit", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void postHit(@RequestBody @Valid HitDto hitDto) {
        statService.postHit(hitDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "stats", produces = "application/json")
    public List<StatsDto> getStats(
            @RequestParam(name = "start") @DateTimeFormat(pattern = DATE_TIME_FORMAT)
            LocalDateTime start,
            @RequestParam(name = "end") @DateTimeFormat(pattern = DATE_TIME_FORMAT)
            LocalDateTime end,
            @RequestParam(required = false) List<String> uris,
            @RequestParam(defaultValue = "false") boolean unique
    ) {
        if ((start == null) || (end == null) || (end.isBefore(start))) {
            throw new ValidationException("Uncorrected parameters start " + start + " & end " + end);
        }

        return statService.getStats(start, end, uris, unique);
    }
}
