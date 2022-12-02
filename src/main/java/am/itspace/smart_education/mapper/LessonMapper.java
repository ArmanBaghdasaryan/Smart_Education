package am.itspace.smart_education.mapper;

import am.itspace.smart_education.common.entity.Lesson;
import am.itspace.smart_education.dto.CreateLessonDto;
import am.itspace.smart_education.dto.RequestAdminLessonDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonMapper {


    Lesson map(CreateLessonDto lessonDto);

    Lesson map(RequestAdminLessonDto requestAdminLessonDto);

}
