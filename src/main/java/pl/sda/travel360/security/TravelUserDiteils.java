package pl.sda.travel360.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.sda.travel360.domain.User;
import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
public class TravelUserDiteils implements UserDetails {

    private User user;

    public Long getUserId(){
       return user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //TODO: do zmiany
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; //TODO: do zmiany
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //TODO: do zmiany
    }

    @Override
    public boolean isEnabled() {
        return user.isConfirmationStatus();
    }
}
