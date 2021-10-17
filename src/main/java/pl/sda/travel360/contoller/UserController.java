package pl.sda.travel360.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.sda.travel360.contoller.request.ResetPasswordRequest;
import pl.sda.travel360.contoller.response.GetUserResponse;
import pl.sda.travel360.contoller.response.GetUsersDetails;
import pl.sda.travel360.security.TravelUserDiteils;
import pl.sda.travel360.service.UserService;

import javax.validation.Valid;


@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    public GetUserResponse getUsers() {
        var users= service.getAllUsers();
        return GetUserResponse.of(users);
    }

//    @PostMapping
//    public ResponseEntity<Void> addUser(@Valid @RequestBody AddUserRequest request){
//        var isExist = service.checkExistUser(request.getLogin(), request.getEmail());
//        if (isExist) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        var userDto = UserDTO.builder()
//                .login(request.getLogin())
//                .email(request.getEmail())
//                .phoneNumber(request.getPhoneNumber())
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .confirmEmail(request.getConfirmEmail())
//                .password(request.getPassword())
//                .build();
//        service.addUser(userDto);
//        return ResponseEntity.ok().build();
//
//    }
    @GetMapping("/{id}")
    public ResponseEntity <GetUsersDetails> getUsersDetails(@PathVariable Long id) {
        var user = service.getUser(id);
        return user.map(userDTO -> ResponseEntity.ok(new GetUsersDetails(userDTO)))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/confirmation/{confirmationId}")
    public void confirmUser(@PathVariable String confirmationId){
        service.confirmUser(confirmationId);

    }
    @PutMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@Valid @RequestBody ResetPasswordRequest request){
        var user = (TravelUserDiteils) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        service.resetUserPassword(user.getUserId(), request.getOldPassword(), request.getNewPassword());
        return ResponseEntity.ok().build();
    }


}
