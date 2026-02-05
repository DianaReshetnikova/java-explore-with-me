package server.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "hits")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Hit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "app", nullable = false)
    private String app;//Идентификатор сервиса для которого записывается информация

    @Column(name = "uri", nullable = false)
    private String uri;//URI для которого был осуществлен запрос

    @Column(name = "ip", nullable = false)
    private String ip;//IP-адрес пользователя, осуществившего запрос

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;
}
