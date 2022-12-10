package am.itspace.smart_education_common.repository;

import am.itspace.smart_education_common.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
