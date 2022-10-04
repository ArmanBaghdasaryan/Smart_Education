package am.itspace.smart_education.common.repositories;

import am.itspace.smart_education.common.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments,Integer> {
}
