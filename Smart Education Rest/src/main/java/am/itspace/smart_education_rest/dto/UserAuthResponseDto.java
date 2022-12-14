package am.itspace.smart_education_rest.dto;

import am.itspace.smart_education_common.dto.RequestAdminLessonDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAuthResponseDto {

    private String token;
    private int userId;
}