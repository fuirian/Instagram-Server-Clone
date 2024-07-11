package instagram.server.like.repository;

import java.util.Optional;

import instagram.server.like.entity.Like;
import instagram.server.post.entity.Post;
import instagram.server.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserAndPost(final User user, final Post post);
}