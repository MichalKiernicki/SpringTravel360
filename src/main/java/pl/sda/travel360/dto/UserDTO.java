package pl.sda.travel360.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    private String email;
    private String phoneNumber;
    private String confirmEmail;
    private boolean confirmationStatus;

}
