package pl.sda.travel360.contoller.request;

import lombok.Data;

@Data
public class ResetPasswordRequest {

    private String oldPassword;
    private String newPassword;
}
