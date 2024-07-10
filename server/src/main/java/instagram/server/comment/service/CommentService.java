package instagram.server.comment.service;

import instagram.server.comment.controller.dto.request.RegisterCommentRequest;
import instagram.server.comment.controller.dto.request.UpdateCommentRequest;
import instagram.server.comment.entity.Comment;
import instagram.server.comment.repository.CommentRepository;
import instagram.server.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentService {

    private final CommentRepository commentitory;
    private final UserService userService;

    public Comment save(RegisterCommentRequest registerCommentRequest, Long userId) {
        Comment comment = registerCommentRequest.toEntity(userService.findUser(userId));
        return commentRepository.save(comment);
    }

    public Comment findComment(long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    @Transactional
    public Comment update(long id, UpdateCommentRequest request) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        comment.update(request.getContent());

        return comment;
    }
}
