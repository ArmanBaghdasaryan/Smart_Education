package am.itspace.smart_education_common.mapper;

import am.itspace.smart_education_common.dto.RequestAdminLessonDto;
import am.itspace.smart_education_common.dto.UserVerifyEmailDto;
import am.itspace.smart_education_common.entity.User;
import am.itspace.smart_education_common.dto.CreateUserDto;
import am.itspace.smart_education_common.dto.RequestAdminUserDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(CreateUserDto createUserDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User mapFromDto(RequestAdminUserDto userDto);

    RequestAdminLessonDto map(User user);

    UserVerifyEmailDto mapToUser(User user);
}
