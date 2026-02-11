package main.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import main.comment.dto.CommentDto;
import main.comment.dto.NewCommentDto;
import main.comment.dto.UpdateCommentDto;
import main.comment.entity.CommentEntity;
import main.comment.mapper.CommentEntityDto;
import main.comment.service.CommentService;
import main.exception.ConditionsNotMetException;
import main.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/events/{eventId}/comments")
public class CommentPrivateController {
    private final CommentService service;
    private final CommentEntityDto mapper;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto postComment(@RequestBody @Valid NewCommentDto commentDto,
                                  @PathVariable Long userId, @PathVariable Long eventId)
            throws NotFoundException, ConditionsNotMetException {
        CommentEntity comment = service.postComment(mapper.toEntity(commentDto), userId, eventId);
        return mapper.toDto(comment);
    }

    @PatchMapping("/{commentId}")
    public CommentDto patchComment(@PathVariable Long userId, @PathVariable Long eventId,
                                   @PathVariable Long commentId, @RequestBody @Valid UpdateCommentDto dto)
            throws ConditionsNotMetException, NotFoundException {
        CommentEntity comment = service.patchComment(userId, eventId, commentId, mapper.toDomain(dto));
        return mapper.toDto(comment);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long userId, @PathVariable Long eventId,
                              @PathVariable Long commentId) throws ConditionsNotMetException, NotFoundException {
        service.userDeleteComment(userId, eventId, commentId);
    }

    @GetMapping
    public List<CommentDto> getAllCommentsByEvent(@PathVariable Long userId, @PathVariable Long eventId) throws NotFoundException {
        List<CommentEntity> comments = service.getAllCommentsForUserByEventId(userId, eventId);
        return comments.stream()
                .map(mapper::toDto)
                .toList();
    }
}
