package am.itspace.smart_education_rest.dto;


import am.itspace.smart_education_common.entity.Lesson;
import am.itspace.smart_education_common.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersLessonsDto {

    private List<User> users;
    private List<Lesson> lessons;
}
