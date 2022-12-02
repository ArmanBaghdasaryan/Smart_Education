package am.itspace.smart_education.dto;

import am.itspace.smart_education.common.entity.Lesson;
import am.itspace.smart_education.common.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDto {

    private String name;
    private String surname;
    private String email;
    private String password;
    private String bio;
    private String picture;
    private String phoneNumber;
    private Role role;
    private Set<Lesson> lessons;


}
