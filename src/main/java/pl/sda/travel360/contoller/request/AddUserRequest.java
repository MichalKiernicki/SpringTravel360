package pl.sda.travel360.contoller.request;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class AddUserRequest {

    @NotNull
    @NotEmpty
    private String login;
    @Size(min = 8, max = 32)
    private String password;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @Email
    private String email;
    @Pattern(regexp = "(?<!\\w)(\\(?(\\+|00)?48\\)?)?[ -]?\\d{3}[ -]?\\d{3}[ -]?\\d{3}(?!\\w)")
    private String phoneNumber;
    @Email
    private String confirmEmail;

}
