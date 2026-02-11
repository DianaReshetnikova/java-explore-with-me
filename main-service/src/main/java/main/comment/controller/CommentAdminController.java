package main.comment.controller;

import lombok.RequiredArgsConstructor;
import main.comment.dto.AdminCommentDto;
import main.comment.entity.CommentEntity;
import main.comment.mapper.CommentEntityDto;
import main.comment.service.CommentService;
import main.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/comments")
public class CommentAdminController {
    private final CommentService commentService;
    private final CommentEntityDto mapper;

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long commentId) throws NotFoundException {
        commentService.adminDeleteComment(commentId);
    }

    @GetMapping
    public List<AdminCommentDto> getAllCommentsByEvent(@RequestParam Long event) throws NotFoundException {
        List<CommentEntity> comments = commentService.getAllCommentForAdminByEventId(event);
        return comments.stream()
                .map(mapper::toAdminDto)
                .toList();
    }
}
