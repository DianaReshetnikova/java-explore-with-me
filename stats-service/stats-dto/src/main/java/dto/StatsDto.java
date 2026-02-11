package dto;

import lombok.*;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatsDto {
    private String app;
    private String uri;
    private Long hits;//количество просмотров

    public Long extractIdFromUri(String uri) {
        return Long.parseLong(uri.replace("/events/", ""));
    }
}
