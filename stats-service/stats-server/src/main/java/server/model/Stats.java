package server.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Stats {
    private String app;
    private String uri;
    private Long hits;
}
