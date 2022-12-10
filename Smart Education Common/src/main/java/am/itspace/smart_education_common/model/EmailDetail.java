package am.itspace.smart_education_common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDetail {

    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private String message;

}
