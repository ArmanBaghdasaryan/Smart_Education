package am.itspace.smart_education.dto;

import am.itspace.smart_education.common.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateQuestionDto {

    private String description;
    private User user;

}
