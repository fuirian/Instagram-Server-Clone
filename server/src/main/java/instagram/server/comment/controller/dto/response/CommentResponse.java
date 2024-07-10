package instagram.server.comment.controller.dto.response;

import instagram.server.comment.entity.Comment;
import lombok.Builder;

import java.util.List;

@Builder
public class CommentResponse {
    private final String content;

    @Builder
    public CommentResponse(String content) {
        this.content = content;
    }

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .content(comment.getContent())
                .build();
    }
}
