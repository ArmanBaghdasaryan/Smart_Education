package am.itspace.smart_education_rest.service;

import am.itspace.smart_education_common.dto.QuestionDto;
import am.itspace.smart_education_common.entity.Question;
import am.itspace.smart_education_rest.security.CurrentUser;

public interface QuestionServiceV2 {

    Question saveQuestion(QuestionDto questionDto, CurrentUser currentUser);
}
