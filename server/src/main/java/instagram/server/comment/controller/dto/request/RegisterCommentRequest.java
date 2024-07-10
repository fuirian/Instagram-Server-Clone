package instagram.server.comment.controller.dto.request;


import instagram.server.comment.entity.Comment;
import instagram.server.user.User;
import lombok.Getter;

@Getter
public class RegisterCommentRequest {

    private User user;
    private String content;

    public Comment toEntity(User user) {
        return comment.builder()
                .user(user)
                .content(content)
                .build();
    }
}
