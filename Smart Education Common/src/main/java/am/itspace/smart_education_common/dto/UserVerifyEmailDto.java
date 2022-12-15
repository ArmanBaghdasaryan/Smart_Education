package am.itspace.smart_education_common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVerifyEmailDto {

    private String verifyToken;
    private String email;
}
