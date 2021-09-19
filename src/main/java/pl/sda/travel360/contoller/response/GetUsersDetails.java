package pl.sda.travel360.contoller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.travel360.dto.UserDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUsersDetails {

    private UserDTO user;

}
