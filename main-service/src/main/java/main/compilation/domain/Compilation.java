package main.compilation.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.event.domain.Event;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Compilation {
    private Long id;
    private List<Event> events;
    private Boolean pinned;
    private String title;
}
