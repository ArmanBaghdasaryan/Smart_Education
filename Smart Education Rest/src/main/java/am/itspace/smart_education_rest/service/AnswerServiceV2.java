package am.itspace.smart_education_rest.service;

import am.itspace.smart_education_common.dto.AnswerRequestDto;
import am.itspace.smart_education_common.entity.Answer;
import am.itspace.smart_education_rest.exception.AuthenticationException;
import am.itspace.smart_education_rest.exception.EntityNotFoundException;
import am.itspace.smart_education_rest.security.CurrentUser;

public interface AnswerServiceV2 {

    Answer save(AnswerRequestDto answerDto, CurrentUser currentUser) throws AuthenticationException, EntityNotFoundException;
}
