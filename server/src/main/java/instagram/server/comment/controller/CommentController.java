package instagram.server.comment.controller;

import instagram.server.comment.controller.dto.request.RegisterCommentRequest;
import instagram.server.comment.controller.dto.request.UpdateCommentRequest;
import instagram.server.comment.entity.Comment;
import instagram.server.comment.service.CommentService;
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

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/comments")
    public ResponseEntity<Comment> registerComment(@RequestBody RegisterCommentRequest request, @LoginUser User user) {
        Comment savedComment = commentService.save(request, user.getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedComment);
    }

    @GetMapping("/api/comments/{commentId}")
    public void findComment(@PathVariable long commentId) {
        Comment comment = commentService.findComment(commentId);

    }

    @PutMapping("/api/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable long id, @RequestBody UpdateCommentRequest request) {
        Comment updatedComment = commentService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedComment);
    }
}
