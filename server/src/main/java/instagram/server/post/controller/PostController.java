package instagram.server.post.controller;

import instagram.server.post.controller.dto.request.RegisterPostRequest;
import instagram.server.post.controller.dto.request.UpdatePostRequest;
import instagram.server.post.entity.Post;
import instagram.server.post.service.PostService;
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
public class PostController {

    private final PostService postService;

    @PostMapping("/api/posts")
    public ResponseEntity<Post> registerPost(@RequestBody RegisterPostRequest request, @LoginUser User user) {
        Post savedPost = postService.save(request, user.getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedPost);
    }

    @GetMapping("/api/posts/{postId}")
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
