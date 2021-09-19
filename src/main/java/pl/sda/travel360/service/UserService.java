package pl.sda.travel360.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.travel360.dto.UserDTO;
import pl.sda.travel360.mapper.UserMapper;
import pl.sda.travel360.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor

public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

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
        userRepository.save(user);
    }

}
