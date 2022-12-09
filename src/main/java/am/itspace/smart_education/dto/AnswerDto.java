package am.itspace.smart_education.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerDto {

    private String answerText;
    private int questionId;
    private String qUsername;
    private String aUsername;
    private String description;

}

