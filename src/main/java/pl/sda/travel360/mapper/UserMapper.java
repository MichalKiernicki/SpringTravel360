package pl.sda.travel360.mapper;

import org.springframework.stereotype.Service;
import pl.sda.travel360.domain.User;
import pl.sda.travel360.dto.UserDTO;

@Service
public class UserMapper {
// tu nie podajemy hasła
    public UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .confirmEmail(user.getConfirmEmail())
                .login(user.getLogin())
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
