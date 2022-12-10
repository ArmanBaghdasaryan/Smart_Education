package am.itspace.smart_education_common.mapper;

import am.itspace.smart_education_common.entity.Question;
import am.itspace.smart_education_common.dto.QuestionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {


    Question map(QuestionDto questionDto);
}
