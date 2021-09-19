package pl.sda.travel360.mapper;

import org.springframework.stereotype.Service;
import pl.sda.travel360.domain.User;
import pl.sda.travel360.dto.UserDTO;

@Service
public class UserMapper {

    public UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
    public User mapToUser(UserDTO userDTO){
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .confirmEmail(userDTO.getConfirmEmail())
                .password(userDTO.getPassword())
                .login(userDTO.getLogin())
                .phoneNumber(userDTO.getPhoneNumber())
                .build();
    }
}
