package am.itspace.smart_education.common.service;

import am.itspace.smart_education.common.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CommentsService {

    void save(Comment comment);

    void deleteById(int id);

    List<Comment> findAll();

    Optional<Comment> findById(int id);

    void updateComment(Comment comment);
}
