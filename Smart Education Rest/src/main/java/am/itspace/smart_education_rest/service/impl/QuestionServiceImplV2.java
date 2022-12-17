package am.itspace.smart_education_rest.service.impl;

import am.itspace.smart_education_common.dto.QuestionDto;
import am.itspace.smart_education_common.entity.Question;
import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.mapper.QuestionMapper;
import am.itspace.smart_education_common.repository.QuestionRepository;
import am.itspace.smart_education_common.repository.UserRepository;
import am.itspace.smart_education_rest.security.CurrentUser;
import am.itspace.smart_education_rest.security.CurrentUserDetailServiceImpl;
import am.itspace.smart_education_rest.service.QuestionServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class QuestionServiceImplV2 implements QuestionServiceV2 {

    private final UserRepository userRepository;
    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;
    private final CurrentUserDetailServiceImpl detailService;

    /**
     * due to this method, authenticated user can ask questions
     * and other users can answer to it.
     * questions and answers are saved separately in different tables of database
     */
    public Question saveQuestion(QuestionDto questionDto, @AuthenticationPrincipal CurrentUser currentUser) {
        UserDetails userDetails = detailService.loadUserByUsername(currentUser.getUsername());
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        Optional<User> byId = userRepository.findById(currentUser.getUser().getId());
        return byId.flatMap(value -> authorities.stream().map(user -> {
            Question question = questionMapper.map(questionDto);
            question.setUser(value);
            return questionRepository.save(question);
        }).findAny()).orElse(null);
    }

}
