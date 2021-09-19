package pl.sda.travel360.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.travel360.dto.UserDTO;
import pl.sda.travel360.mapper.UserMapper;
import pl.sda.travel360.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor

public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public List<UserDTO> getAllUsers(){
        log.info("Get users");
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapToDTO)
                .collect(Collectors.toList());
    }
    public void addUser(UserDTO userDTO) {
        log.info("add user");
        var user = userMapper.mapToUser(userDTO);
        var confiramtionId = UUID.randomUUID().toString();
        var validTo = LocalDateTime.now().plusMinutes(15);
        user.setConfirmationId(confiramtionId);
        user.setValidTo(validTo);
        userRepository.save(user);
        emailService.sendEmail(userDTO.getEmail(),"Witam w travel360", "Witaj. Założyłeś konto w travel 360. link aktywacyjny "
                + "http://localhost:8080/v1/user/confirmation/"
                + user.getConfirmationId());
    }

    public Optional<UserDTO> getUser (Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::mapToDTO);
    }
    public boolean checkExistUser(String login, String email){
        return userRepository.existsByConfirmEmailOrLogin(login, email);
    }

    public void confirmUser(String confirmationId) {
        var user = userRepository.findByConfirmationId(confirmationId);
        user.ifPresent(u -> {
            u.setConfirmationStatus(true);
        });
    }
}
