package am.itspace.smart_education_common.mapper;

import am.itspace.smart_education_common.entity.Lesson;
import am.itspace.smart_education_common.dto.CreateLessonDto;
import am.itspace.smart_education_common.dto.RequestAdminLessonDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    Lesson map(CreateLessonDto lessonDto);

    Lesson map(RequestAdminLessonDto requestAdminLessonDto);

}
