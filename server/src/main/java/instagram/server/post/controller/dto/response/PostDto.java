package instagram.server.post.controller.dto.response;

import instagram.server.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDto {
    private final Long post;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    @Builder
    private PostDto(Long post, String title, String content, LocalDateTime createdAt) {
        this.post = post;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static PostDto of(Post post) {
        return PostDto.builder()
                .post(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
