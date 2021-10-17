package pl.sda.travel360.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sda.travel360.repository.UserRepository;

import javax.security.auth.message.config.AuthConfigProvider;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/v1/country/**").permitAll()
                .mvcMatchers(HttpMethod.POST,"/v1/registration").anonymous()
                .mvcMatchers("/v1/user/confirmation/**").anonymous()
                .mvcMatchers("/v1/user/**").authenticated()
                .anyRequest().permitAll()
             .and()
                .httpBasic()
             .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authConfigProvider());
//                .inMemoryAuthentication()
//                .withUser("michal")
//                .password("$2a$10$pHYm6clR9SAPEZn88tnRYuT56KNxo40zp1vG8I0LuYXworiQ2GEq2")
//                .roles("USER")
//                .and()
//                .withUser("admin")
//                .password("$2a$10$WvsMJmwtxK.H0yaZ84OFM.XyWF/nVRBk9WDuKwnpyjmkwZdG2DkKC")
//                .roles("ADMIN");
    }
    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authConfigProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        UserDetailsService userDetailsService = new TravelUesrDetailsService(userRepository);
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}