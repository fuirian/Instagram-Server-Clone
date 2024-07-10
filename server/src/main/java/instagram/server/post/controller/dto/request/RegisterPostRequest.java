package instagram.server.post.controller.dto.request;

import instagram.server.post.entity.Post;
import instagram.server.user.User;
import lombok.Getter;


@Getter
public class RegisterPostRequest {

    private User userId;
    private String title;
    private String content;

    public Post toEntity(User userId) {
        return Post.builder()
                .user(userId)
                .title(title)
                .content(content)
                .build();
    }
}
