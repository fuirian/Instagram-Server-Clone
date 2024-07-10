package instagram.server.post.controller.dto.responese;

import instagram.server.post.entity.Post;
import lombok.Builder;

@Builder
public class PostResponse {
    private final String title;
    private final String content;

    @Builder
    public PostResponse(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }
}
