package am.itspace.smart_education.common.service;

import am.itspace.smart_education.common.entity.Comment;
import am.itspace.smart_education.common.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentRepository commentRepository;

    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public void deleteById(int id) {
        commentRepository.deleteById(id);

    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Optional<Comment> findById(int id) {
        return commentRepository.findById(id);
    }

    public void updateComment(Comment comment) {
        commentRepository.save(comment);
    }
}
