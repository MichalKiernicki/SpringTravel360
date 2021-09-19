package pl.sda.travel360.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.travel360.contoller.request.AddUserRequest;
import pl.sda.travel360.contoller.response.GetUserResponse;
import pl.sda.travel360.contoller.response.GetUsersDetails;
import pl.sda.travel360.domain.User;
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
    public ResponseEntity<Void> addUser(@Valid @RequestBody AddUserRequest request){
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
    @GetMapping("/{id}")
    public ResponseEntity <GetUsersDetails> getUsersDetails(@PathVariable Long id) {
        var user = service.getUser(id);
        return user.map(userDTO -> ResponseEntity.ok(new GetUsersDetails(userDTO)))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/confirmation/{confirmationId}")
    public void confirmUser(String confirmationId){
        service.confirmUser(confirmationId);

    }
}
