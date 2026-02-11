package main.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.user.dto.UserShortDto;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String text;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    private UserShortDto author;
}
