package main.comment.storage;

import main.comment.entity.CommentEntity;
import main.core.BaseStorage;

import java.util.List;

public interface CommentStorage extends BaseStorage<Long, CommentEntity> {
    List<CommentEntity> getAllCommentsByEventId(Long eventId);
}
