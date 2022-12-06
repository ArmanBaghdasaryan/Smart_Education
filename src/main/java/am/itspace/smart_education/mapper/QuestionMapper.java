package am.itspace.smart_education.mapper;

import am.itspace.smart_education.common.entity.Question;
import am.itspace.smart_education.dto.QuestionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {


    Question map(QuestionDto questionDto);
}
