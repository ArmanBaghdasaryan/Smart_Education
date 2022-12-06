package am.itspace.smart_education.dto;

import am.itspace.smart_education.common.entity.Duration;
import am.itspace.smart_education.common.entity.Title;
import am.itspace.smart_education.common.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateLessonDto {

    private Title title;
    private double price;
    private Duration duration;
    private double totalHours;
    private boolean isOnline;
    private Set<User> userSet;
    private String picture;
}
