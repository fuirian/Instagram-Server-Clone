package instagram.server.post.service;

import instagram.server.post.controller.dto.request.RegisterPostRequest;
import instagram.server.post.controller.dto.request.UpdatePostRequest;
import instagram.server.post.entity.Post;
import instagram.server.post.repository.PostRepository;
import instagram.server.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public Post save(RegisterPostRequest registerPostRequest, String username) {
        Post post = registerPostRequest.toEntity(userService.findUser(username));
        return postRepository.save(post);
    }

    public Post findPosts(long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    @Transactional
    public Post update(long id, UpdatePostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        post.update(request.getTitle(), request.getContent());

        return post;
    }
}
