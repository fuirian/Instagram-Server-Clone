package instagram.server.post.controller.dto.request;

import instagram.server.post.entity.Post;
import instagram.server.user.User;
import lombok.Getter;


@Getter
public class RegisterPostRequest {

    private User user;
    private String title;
    private String content;
    //private List<ExhibitionPhotoDto> exhibitionphotoDtos;

    public Post toEntity(User user) {
        return Post.builder()
                .user(user)
                .title(title)
                .content(content)
                .build();
    }
}
