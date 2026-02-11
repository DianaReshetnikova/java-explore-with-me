package main.comment.mapper;

import main.comment.dto.AdminCommentDto;
import main.comment.dto.CommentDto;
import main.comment.dto.NewCommentDto;
import main.comment.dto.UpdateCommentDto;
import main.comment.entity.CommentEntity;
import main.event.mapper.EventDomainDto;
import main.user.UserDomainDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", uses = {UserDomainDto.class, EventDomainDto.class})
public interface CommentEntityDto {
    @Mapping(target = "created", expression = "java(mapTime())")
    CommentEntity toEntity(NewCommentDto dto);

    CommentDto toDto(CommentEntity comment);

    @Mapping(target = "author", source = "author")
    @Mapping(target = "event", source = "event")
    AdminCommentDto toAdminDto(CommentEntity comment);

    default LocalDateTime mapTime() {
        return LocalDateTime.now();
    }

    CommentEntity toDomain(UpdateCommentDto dto);
}
