package am.itspace.smart_education.mapper;

import am.itspace.smart_education.common.entity.User;
import am.itspace.smart_education.dto.CreateUserDto;
import am.itspace.smart_education.dto.RequestAdminUserDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(CreateUserDto createUserDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User mapFromDto(RequestAdminUserDto userDto);
}
