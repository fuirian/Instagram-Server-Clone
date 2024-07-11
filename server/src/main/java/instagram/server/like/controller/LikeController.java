package instagram.server.like.controller;

import instagram.server.like.entity.Like;
import instagram.server.like.service.LikeService;
import instagram.server.post.controller.dto.request.UpdatePostRequest;
import instagram.server.post.entity.Post;
import instagram.server.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/api/likes")
    public ResponseEntity<Like> registerLike(@RequestBody RegisterLikeRequest request, @LoginUser User user) {
        Like savedLike = likeService.save(request, user.getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedLike);
    }

    @GetMapping("/api/likes/{likeId}")
    public void findPost(@PathVariable long postId) {
        Post post = postService.findPosts(postId);

    }

    @PutMapping("/api/posts/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable long postId, @RequestBody UpdatePostRequest request) {
        Post updatedPost = postService.update(postId, request);

        return ResponseEntity.ok()
                .body(updatedPost);
    }
}
