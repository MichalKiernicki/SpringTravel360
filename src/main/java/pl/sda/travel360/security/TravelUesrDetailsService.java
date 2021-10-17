package pl.sda.travel360.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.sda.travel360.repository.UserRepository;

@AllArgsConstructor
public class TravelUesrDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return userRepository.findUserByLogin(username)
               .map(TravelUserDiteils::new)
               .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
