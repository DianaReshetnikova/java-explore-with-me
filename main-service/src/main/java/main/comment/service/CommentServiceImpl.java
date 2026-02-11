package main.comment.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import main.comment.entity.CommentEntity;
import main.comment.storage.CommentStorage;
import main.event.domain.EventState;
import main.event.model.EventEntity;
import main.event.service.EventService;
import main.exception.ConditionsNotMetException;
import main.exception.NotFoundException;
import main.user.User;
import main.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentStorage storage;
    private final EventService eventService;
    private final UserService userService;

    @Override
    @Transactional
    public CommentEntity postComment(CommentEntity comment, Long authorId, Long eventId) throws NotFoundException, ConditionsNotMetException {
        var event = eventService.getEventById(eventId);
        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new ConditionsNotMetException("Мероприятие должно быть опубликовано.");
        }
        User authorEntity = new User();
        authorEntity.setId(authorId);
        EventEntity eventEntity = new EventEntity();
        eventEntity.setId(eventId);
        comment.setAuthor(authorEntity);
        comment.setEvent(eventEntity);
        return storage.save(comment);
    }

    @Override
    @Transactional
    public CommentEntity patchComment(Long authorId, Long eventId, Long commentId, CommentEntity patchComment) throws NotFoundException, ConditionsNotMetException {
        CommentEntity comment = getCommentById(commentId);
        validateAuthorAndEvent(comment, authorId, eventId);
        comment.setText(patchComment.getText());
        return storage.save(comment);
    }

    @Override
    public CommentEntity getCommentById(Long commentId) throws NotFoundException {
        return storage.getById(commentId).orElseThrow(
                () -> new NotFoundException("Комментарий с id = " + commentId + " не существует.")
        );
    }

    @Override
    @Transactional
    public void userDeleteComment(Long authorId, Long eventId, Long commentId) throws NotFoundException, ConditionsNotMetException {
        CommentEntity comment = getCommentById(commentId);
        validateAuthorAndEvent(comment, authorId, eventId);
        storage.delete(commentId);
    }

    @Override
    @Transactional
    public void adminDeleteComment(Long commentId) throws NotFoundException {
        if (!storage.existById(commentId)) {
            throw new NotFoundException("Комментарий с id = " + commentId + " не существует.");
        }
        storage.delete(commentId);
    }

    @Override
    public List<CommentEntity> getAllCommentForAdminByEventId(Long eventId) throws NotFoundException {
        if (!eventService.existById(eventId)) {
            throw new NotFoundException("Событие с id = " + eventId + " не существует.");
        }
        return storage.getAllCommentsByEventId(eventId);
    }

    @Override
    public List<CommentEntity> getAllCommentsForUserByEventId(Long userId, Long eventId) throws NotFoundException {
        userService.findById(userId);

        if (!eventService.existById(eventId)) {
            throw new NotFoundException("Событие с id = " + eventId + " не существует.");
        }
        return storage.getAllCommentsByEventId(eventId);
    }

    private void validateAuthorAndEvent(CommentEntity comment, Long authorId, Long eventId) throws ConditionsNotMetException {
        if (!comment.getAuthor().getId().equals(authorId)) {
            throw new ConditionsNotMetException("Данный комментарий не принадлежит этому пользователю.");
        }
        if (!comment.getEvent().getId().equals(eventId)) {
            throw new ConditionsNotMetException("Данный комментарий не принадлежит этому событию.");
        }
    }
}
