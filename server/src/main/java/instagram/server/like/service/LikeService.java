package instagram.server.like.service;

import instagram.server.like.controller.dto.response.LikeDto;
import instagram.server.like.entity.Like;
import instagram.server.like.repository.LikeRepository;
import instagram.server.post.entity.Post;
import instagram.server.post.repository.PostRepository;
import instagram.server.user.User;
import instagram.server.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public void insert(LikeDto likeDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("[Error] 사용자를 찾을 수 없습니다."));
        Post post = postRepository.findById(likeDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("[Error] 게시물을 찾을 수 없습니다."));

        if (likeRepository.findByUserAndPost(user, post).isPresent()) {
            throw new IllegalArgumentException("[Error] 이미 좋아요가 눌려있는 상태입니다.");
        }

        Like like = Like.builder()
                .post(post)
                .user(user)
                .build();

        int likeCount = post.getLikeCount();
        postRepository.save(like);
        post.updateLikeCount(++likeCount);
    }

    public void delete(LikeDto likeDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("[Error] 사용자를 찾을 수 없습니다."));
        Post post = postRepository.findById(likeDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("[Error] 게시물을 찾을 수 없습니다."));

        Like like = likeRepository.findByUserAndPost(user, post)
                .orElseThrow(() -> new IllegalArgumentException("[Error] 좋아요를 찾을 수 없습니다."));

        int likeCount = post.getLikeCount();
        likeRepository.delete(like);
        post.updateLikeCount(--likeCount);
    }

    public boolean isLiked(User user, Post post) {
        return likeRepository.findByUserAndPost(user, post).isPresent();
    }
}