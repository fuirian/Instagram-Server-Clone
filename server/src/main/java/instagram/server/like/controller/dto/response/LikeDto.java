package instagram.server.like.controller.dto.response;

import instagram.server.like.entity.Like;
import instagram.server.post.controller.dto.response.PostDto;
import instagram.server.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LikeDto {
    private final Long like;
    private final Long post;

    @Builder
    private LikeDto(Long like, Long post) {
        this.like = like;
        this.post = post;
    }

    public static LikeDto of(Like like) {
        return LikeDto.builder()
                .like(like.getId())
                .post(like.getId())
                .build();
    }
}