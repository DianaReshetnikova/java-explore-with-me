package main.comment.service;

import main.comment.entity.CommentEntity;
import main.exception.ConditionsNotMetException;
import main.exception.NotFoundException;

import java.util.List;

public interface CommentService {
    CommentEntity postComment(CommentEntity comment, Long authorId, Long eventId) throws NotFoundException, ConditionsNotMetException;

    CommentEntity patchComment(Long authorId, Long eventId, Long commentId, CommentEntity comment) throws NotFoundException, ConditionsNotMetException;

    CommentEntity getCommentById(Long commentId) throws NotFoundException;

    void userDeleteComment(Long authorId, Long eventId, Long commentId) throws NotFoundException, ConditionsNotMetException;

    void adminDeleteComment(Long commentId) throws NotFoundException;

    List<CommentEntity> getAllCommentForAdminByEventId(Long eventId) throws NotFoundException;

    List<CommentEntity> getAllCommentsForUserByEventId(Long userId, Long eventId) throws NotFoundException;
}
