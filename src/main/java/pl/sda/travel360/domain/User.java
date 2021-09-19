package pl.sda.travel360.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "travel_user")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty
    @Column(unique = true)
    private String login;

    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String confirmEmail;


}
