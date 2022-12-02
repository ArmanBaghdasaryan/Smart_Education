package am.itspace.smart_education.mapper;

import am.itspace.smart_education.common.entity.Answer;
import am.itspace.smart_education.dto.AnswerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {


    Answer map(AnswerDto answerDto);
}
