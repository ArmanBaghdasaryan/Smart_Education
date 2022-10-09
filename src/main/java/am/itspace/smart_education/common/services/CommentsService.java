package am.itspace.smart_education.common.services;


import am.itspace.smart_education.common.entity.Comments;
import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.common.repositories.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;


    public List<Comments> findAll() {
        return commentsRepository.findAll();
    }

    public void save(Comments comments) {
        commentsRepository.save(comments);
    }

    public void deleteById(int id) {
        commentsRepository.deleteById(id);
    }

}
