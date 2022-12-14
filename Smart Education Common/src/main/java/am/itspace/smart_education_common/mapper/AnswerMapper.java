package am.itspace.smart_education_common.mapper;

import am.itspace.smart_education_common.dto.AnswerRequestDto;
import am.itspace.smart_education_common.entity.Answer;
import am.itspace.smart_education_common.dto.AnswerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {


    Answer map(AnswerDto answerDto);

    Answer mapTo(AnswerRequestDto answerRequestDto);
}
