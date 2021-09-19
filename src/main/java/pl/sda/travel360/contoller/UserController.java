package pl.sda.travel360.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.travel360.contoller.request.AddUserRequest;
import pl.sda.travel360.contoller.response.GetUserResponse;
import pl.sda.travel360.dto.CountryDTO;
import pl.sda.travel360.dto.UserDTO;
import pl.sda.travel360.service.UserService;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService service;

    @GetMapping
    private GetUserResponse getUsers() {
        var users= service.getAllUsers();
        return GetUserResponse.of(users);
    }

    @PostMapping
    public void addUser(@Valid @RequestBody AddUserRequest request){
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
    }
}
