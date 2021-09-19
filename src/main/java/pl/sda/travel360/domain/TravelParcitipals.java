package pl.sda.travel360.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class TravelParcitipals {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastNaame;
    private String phoneNumber;
    private String email;

}