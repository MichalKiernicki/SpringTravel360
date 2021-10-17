package pl.sda.travel360.contoller.response;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.travel360.contoller.request.AddUserRequest;
import pl.sda.travel360.dto.UserDTO;
import pl.sda.travel360.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/registration")
public class UserRegistrationController {
    private final UserService service;

    @Autowired
    public UserRegistrationController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> addUser(@Valid @RequestBody AddUserRequest request) {
        var isExist = service.checkExistUser(request.getLogin(), request.getEmail());
        if (isExist) {
            return ResponseEntity.unprocessableEntity().build();
        }
        var userDto = UserDTO.builder()
                .login(request.getLogin())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .confirmEmail(request.getConfirmEmail())
                .password(request.getPassword())
                .build();
        service.addUser(userDto);
        return ResponseEntity.ok().build();
    }
}
