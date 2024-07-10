package instagram.server.comment.controller.dto.response;

import instagram.server.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
    public class CommentDto {
        private final Long commentId;
        private final String content;
        private final LocalDateTime createdAt;

        @Builder
    private CommentDto(Long commentId, String content, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static CommentDto of(Comment comment) {
        return CommentDto.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
